package com.cart.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.mem_coup.model.I_MemCoupDAO;
import com.mem_coup.model.MemCoupDAO;
import com.tkt.model.I_TktDAO;
import com.tkt.model.TktDAO;
import com.tkt.model.TktVO;
import com.tkt_item2.model.TktItem2VO;
import com.tkt_order2.model.I_TktOrder2DAO;
import com.tkt_order2.model.TktOrder2DAO;
import com.tkt_order2.model.TktOrder2VO;

public class CheckOutService {
	private Gson gson = new Gson();

	private I_TktDAO dao;
	private I_TktOrder2DAO daoTktOrder2;
	private I_MemCoupDAO daoMemCoup;

	private static DataSource ds = null;

	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public CheckOutService() {
		dao = new TktDAO();
		daoTktOrder2 = new TktOrder2DAO();
		daoMemCoup = new MemCoupDAO();
	}
	
	CartItemService cartItemSrv = new CartItemService();

	// 購物車結帳所需要做的事：
	// 結帳後同時新增訂單、訂單明細
	// 更新票券庫存量、銷售量
	// 更改會員優惠券使用狀態
	// 生成 QR Code
	// 等交易都正常 commit 後才能清掉 redis 購物車的紀錄
	// 寄出信件通知訂購成功

	public boolean checkOut(Integer mem_no, Integer mem_coup_no,
			Integer original_price, Integer ttl_price, List<TktItem2VO> list,
			String sessionId) {

		Boolean transaction = false;
		Connection con = null;

		try {
			con = ds.getConnection();

			con.setAutoCommit(false); // 關閉自動 commit

			// 結帳後同時新增訂單、訂單明細
			// 若沒有使用優惠券付款，號碼 0 代表沒選優惠券
			if (mem_coup_no.equals(0)) {
				TktOrder2VO tktOrder2VO = new TktOrder2VO();
				tktOrder2VO.setMem_no(mem_no);
//				tktOrder2VO.setTkt_no(tkt_no);
				tktOrder2VO.setOriginal_price(original_price);
				tktOrder2VO.setTtl_price(ttl_price);

				daoTktOrder2.insertWithDetailWithoutCoup(con, tktOrder2VO, list);
//				
			} else {
				// 有使用優惠券
				TktOrder2VO tktOrder2VO = new TktOrder2VO();
				tktOrder2VO.setMem_no(mem_no);
				tktOrder2VO.setMem_coup_no(mem_coup_no);
//				tktOrder2VO.setTkt_no(tkt_no);
				tktOrder2VO.setOriginal_price(original_price);
				tktOrder2VO.setTtl_price(ttl_price);

				daoTktOrder2.insertWithDetail(con, tktOrder2VO, list);

			}
			
			
			// 更新票券庫存量、銷售量
			List<String> cartItems = CartItemJedisDAO.getCart(sessionId);
			List<TktItem2VO> listForUpdate = list;

			TktVO tktVO = new TktVO();
			Integer redisCount = 0;
			Integer sold_amount = 0;
			for (int i = 0; i < listForUpdate.size(); i++) {
				TktItem2VO tktItem2VO = listForUpdate.get(i);
				Integer tkt_no = tktItem2VO.getTkt_no();
				
//				List<String> cartItems = CartItemJedisDAO.getCart(sessionId);
				
				for (int j = 0; j < cartItems.size(); j++) {
					CartItemVO oldItems = gson.fromJson(cartItems.get(j), CartItemVO.class);
					
					Integer checkedItemId = tkt_no;
					Integer oldItemId = oldItems.getTkt_no(); // 原本就在購物車的票券
//					System.out.println("--------oldItemId" + oldItemId);
					
					if (tkt_no.equals(oldItemId)) {
						redisCount = oldItems.getCount();
						sold_amount = dao.findByPrimaryKey(oldItemId).getSold_amount();
//						tktVO.setSold_amount(sold_amount);
//						tktVO.setTkt_no(tkt_no);
//						System.out.println("----------redisCount"+redisCount);
					}
				}
				sold_amount += redisCount;
				dao.updateSoldAmount(sold_amount, tkt_no, con);
				dao.updateOriAmount(tkt_no, con);
//				dao.updateOriAmount( (sold_amount - redisCount)  , tkt_no, con);
			}
			
			
			// 更改會員優惠券使用狀態
			if (!mem_coup_no.equals(0)) {
				daoMemCoup.memCoupUsedState(mem_coup_no);
			}
//			System.out.println(mem_coup_no);
//			daoMemCoup.memCoupUsedState(mem_coup_no, 1);
			

			//所有流程都成功後送出交易，將 autoCommit 恢復預設，避免死結問題
			con.commit();
			con.setAutoCommit(true);
			transaction = true;
			
			
			// 寄出信件通知訂購成功

			
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println(e);
				System.err.println("交易失敗");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println(e1);
				System.err.println("交易失敗");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return transaction;
	}

}
