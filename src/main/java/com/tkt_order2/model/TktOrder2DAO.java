package com.tkt_order2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tkt_item2.model.TktItem2DAO;
import com.tkt_item2.model.TktItem2VO;

public class TktOrder2DAO implements I_TktOrder2DAO {

	private static final String INSERT = "INSERT INTO tkt_order (mem_no, mem_coup_no, original_price, orderdate, ttl_price) VALUES ( ?, ?, ?, NOW(), ?)";
	private static final String INSERT_WITHOUT_COUP = "INSERT INTO tkt_order (mem_no, original_price, orderdate, ttl_price) VALUES ( ?, ?, NOW(), ?)";
	private static final String GET_ALL = "SELECT tkt_order_no, mem_no, mem_coup_no, original_price, orderdate, ttl_price FROM tkt_order ORDER BY tkt_order_no DESC";
	private static final String GET_ONE = "SELECT tkt_order_no, mem_no, mem_coup_no, original_price, orderdate, ttl_price FROM tkt_order WHERE tkt_order_no = ?";
	private static final String GET_DETAIL_BY_TKT_ORD_NO = "SELECT tkt_order_no, amount, qrcode, tkt_ori_price FROM tkt_item WHERE tkt_order_no =?";
	private static final String DEL_DETAILS = "DELETE FROM tkt_item WHERE tkt_order_no = ?";
	private static final String DEL_ORDER = "DELETE FROM tkt_order WHERE tkt_order_no = ?";
	private static final String UPDATE = "UPDATE tkt_order SET mem_no = ?, mem_coup_no = ?, original_price = ? , orderdate = ?, ttl_price = ? WHERE tkt_order_no = ?";
	// 查某會員的最新訂單
	private static final String FindNewetOrderByMem = "SELECT MAX(tkt_order_no),mem_no FROM tkt_order WHERE mem_no = ?;";
	// 查某會員的所有訂單
	private static final String FindAllOrderByMem = "SELECT tkt_order_no, mem_no, mem_coup_no, original_price, orderdate, ttl_price FROM tkt_order WHERE mem_no = ? ORDER BY tkt_order_no DESC";
	
	
	private static DataSource ds = null;

	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(TktOrder2VO tktOrder2VO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);


			ps.setInt(1, tktOrder2VO.getMem_no());
			ps.setInt(2, tktOrder2VO.getMem_coup_no());
//			ps.setInt(3, tktOrder2VO.getTkt_no());
			ps.setInt(3, tktOrder2VO.getOriginal_price());
			ps.setInt(4, tktOrder2VO.getTtl_price());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(TktOrder2VO tktOrder2VO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setInt(1, tktOrder2VO.getMem_no());
			ps.setInt(2, tktOrder2VO.getMem_coup_no());
//			ps.setInt(3, tktOrder2VO.getTkt_no());
			ps.setInt(3, tktOrder2VO.getOriginal_price());
			ps.setObject(4, tktOrder2VO.getOrderdate());
			ps.setInt(5, tktOrder2VO.getTtl_price());
			ps.setInt(6, tktOrder2VO.getTkt_order_no());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer tkt_order_no) {
		int updateCount_Details = 0;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			// 解除 AutoCommit 預設
			con.setAutoCommit(false);

			// 先刪多方 tktItem
			ps = con.prepareStatement(DEL_DETAILS);
			ps.setInt(1, tkt_order_no);
			updateCount_Details = ps.executeUpdate();
			// 再刪一方 tktOrder
			ps = con.prepareStatement(DEL_ORDER);
			ps.setInt(1, tkt_order_no);
			ps.executeUpdate();

			// 設定於 ps.executeUpdate()之後
			con.commit();
			// 恢復預設
			con.setAutoCommit(true);
//			System.out.println("刪除訂單編號" + tkt_order_no + "時,共有" + updateCount_Details + "筆訂單明細同時被刪除");

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public TktOrder2VO findByPrimaryKey(Integer tkt_order_no) {

		TktOrder2VO tktOrder2VO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, tkt_order_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				tktOrder2VO = new TktOrder2VO();

				tktOrder2VO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrder2VO.setMem_no(rs.getInt("mem_no"));
				tktOrder2VO.setMem_coup_no(rs.getInt("mem_coup_no"));
//				tktOrder2VO.setTkt_no(rs.getInt("tkt_no"));
				tktOrder2VO.setOriginal_price(rs.getInt("original_price"));
				tktOrder2VO.setOrderdate(rs.getDate("orderdate"));
				tktOrder2VO.setTtl_price(rs.getInt("ttl_price"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return tktOrder2VO;
	}

	@Override
	public List<TktOrder2VO> getAll() {

		List<TktOrder2VO> list = new ArrayList<TktOrder2VO>();
		TktOrder2VO tktOrder2VO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();

			while (rs.next()) {
				tktOrder2VO = new TktOrder2VO();

				tktOrder2VO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrder2VO.setMem_no(rs.getInt("mem_no"));
				tktOrder2VO.setMem_coup_no(rs.getInt("mem_coup_no"));
//				tktOrder2VO.setTkt_no(rs.getInt("tkt_no"));
				tktOrder2VO.setOriginal_price(rs.getInt("original_price"));
				tktOrder2VO.setOrderdate(rs.getDate("orderdate"));
				tktOrder2VO.setTtl_price(rs.getInt("ttl_price"));

				list.add(tktOrder2VO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	@Override
	public Set<TktItem2VO> getDetailByTktOrdNo(Integer tkt_order_no) {
		Set<TktItem2VO> set = new HashSet<TktItem2VO>();
		TktItem2VO tktItem2VO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_DETAIL_BY_TKT_ORD_NO);

			rs = ps.executeQuery();

			while (rs.next()) {
				tktItem2VO = new TktItem2VO();
//				tktItem2VO.setTkt_no(rs.getInt("tkt_no"));
				tktItem2VO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktItem2VO.setAmount(rs.getInt("amount"));
				tktItem2VO.setQrcode(rs.getBytes("qrcode"));
				tktItem2VO.setTkt_ori_price(rs.getInt("tkt_ori_price"));

				set.add(tktItem2VO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return set;
	}

	@Override
	public void insertWithDetail(Connection con, TktOrder2VO tktOrder2VO, List<TktItem2VO> list) {
//		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

//			 設定於 ps.executeUpdate()之前
//			 解除預設
			con.setAutoCommit(false);

			// 先新增一方 tktOrder
			String cols[] = { "TKT_ORDER_NO" };
			ps = con.prepareStatement(INSERT, cols);
//			ps.setInt(1, tktOrder2VO.getTkt_order_no());
			ps.setInt(1, tktOrder2VO.getMem_no());
			ps.setInt(2, tktOrder2VO.getMem_coup_no());
//			ps.setInt(3, tktOrder2VO.getTkt_no());
			ps.setInt(3, tktOrder2VO.getOriginal_price());
//			ps.setObject(6, tktOrder2VO.getOrderdate());
			ps.setInt(4, tktOrder2VO.getTtl_price());
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=1;"); // 自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
			ps.executeUpdate();
			// 掘取對應的自增主鍵值
			Integer next_tkt_order_no = null;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				next_tkt_order_no = rs.getInt(1);
//				System.out.println("自增主鍵值= " + next_tkt_order_no + "(剛新增成功的 tkt_order_no 編號)");
			} else {
//				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			// 再同時新增多方 TktDetail
			TktItem2DAO dao = new TktItem2DAO();
			System.out.println("list.size()-A=" + list.size());
			for (TktItem2VO aTktItem : list) {
				aTktItem.setTkt_order_no(Integer.valueOf(next_tkt_order_no));
				dao.insertDetailWithOrder(aTktItem, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("list.size()-B=" + list.size());
//			System.out.println("新增票券訂單編號" + next_tkt_order_no + "時,共有" + list.size() + "筆訂單明細同時被新增");

		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("訂單、訂單明細新增失敗");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void insertWithDetailWithoutCoup(Connection con, TktOrder2VO tktOrder2VO, List<TktItem2VO> list) {
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			// 設定於 ps.executeUpdate()之前
			// 解除預設
			con.setAutoCommit(false);

			// 先新增一方 tktOrder
			String cols[] = { "TKT_ORDER_NO" };
			ps = con.prepareStatement(INSERT_WITHOUT_COUP, cols);
//			ps.setInt(1, tktOrder2VO.getTkt_order_no());
			ps.setInt(1, tktOrder2VO.getMem_no());
//			ps.setInt(2, tktOrder2VO.getTkt_no());
			ps.setInt(2, tktOrder2VO.getOriginal_price());
//			ps.setObject(5, tktOrder2VO.getOrderdate());
			ps.setInt(3, tktOrder2VO.getTtl_price());
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=4;"); // 自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
			ps.executeUpdate();
			// 掘取對應的自增主鍵值
			Integer next_tkt_order_no = null;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				next_tkt_order_no = rs.getInt(1);
//				System.out.println("自增主鍵值= " + next_tkt_order_no + "(剛新增成功的 tkt_order_no 編號)");
			} else {
//				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增多方 TktDetail
			TktItem2DAO dao = new TktItem2DAO();
//			System.out.println("list.size()-A=" + list.size());
			for (TktItem2VO aTktItem : list) {
				aTktItem.setTkt_order_no(Integer.valueOf(next_tkt_order_no));
				dao.insertDetailWithOrder(aTktItem, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("list.size()-B=" + list.size());
//			System.out.println("新增票券訂單編號" + next_tkt_order_no + "時,共有" + list.size() + "筆訂單明細同時被新增");

		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("訂單、訂單明細新增失敗");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	// 查某會員的最新訂單
	@Override
	public TktOrder2VO findNewetOrderByMem(Integer mem_no) {
		TktOrder2VO tktOrder2VO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			
			ps = con.prepareStatement(FindNewetOrderByMem);
			ps.setInt(1, mem_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				tktOrder2VO = new TktOrder2VO();
				tktOrder2VO.setMem_no(rs.getInt("mem_no"));
				tktOrder2VO.setTkt_order_no(rs.getInt("max(tkt_order_no)"));

			}

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return tktOrder2VO;
	}

	@Override
	public List<TktOrder2VO> findAllOrderByMem(Integer mem_no) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TktOrder2VO> list = new ArrayList<TktOrder2VO>();
		TktOrder2VO tktOrder2VO = null;
		
		try {
			tktOrder2VO = new TktOrder2VO();
			con = ds.getConnection();
			ps = con.prepareStatement(FindAllOrderByMem);
			ps.setInt(1, mem_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				tktOrder2VO = new TktOrder2VO();

				tktOrder2VO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrder2VO.setMem_no(rs.getInt("mem_no"));
				tktOrder2VO.setMem_coup_no(rs.getInt("mem_coup_no"));
//				tktOrder2VO.setTkt_no(rs.getInt("tkt_no"));
				tktOrder2VO.setOriginal_price(rs.getInt("original_price"));
				tktOrder2VO.setOrderdate(rs.getDate("orderdate"));
				tktOrder2VO.setTtl_price(rs.getInt("ttl_price"));

				list.add(tktOrder2VO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		return list;
	}
	
	
}
