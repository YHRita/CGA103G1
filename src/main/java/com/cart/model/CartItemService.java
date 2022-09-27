package com.cart.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.tkt.model.I_TktDAO;
import com.tkt.model.TktDAO;
import com.tkt.model.TktVO;


public class CartItemService {
	private Gson gson = new Gson();
	
	private I_TktDAO dao;
//	private CartItemJedisDAO jedisDao = new CartItemJedisDAO();
	
	
	public CartItemService() {
		dao = new TktDAO();
//		jedisDao = new CartItemJedisDAO();
	}
	
	// getOneTkt
//	public TktVO getOneTkt(Integer tkt_no) {
//		return dao.findByPrimaryKey(tkt_no);
//	}
	
	// getCart
	//user cookie("cart", sessionId)
	//Redis List (sessionId, {"tkt_no": "xxx", "count": "x"})
	//使用者進入首頁時，檢查是否有 key 為 shoppingCart 的 cookie，有的話取得 sessionId，沒有則用給予 cookie
	//(可以做在 filter，讓使用者不管進入哪個註冊到的頁面都先做此動作?)
	//用 sessionId 自 redis 取得購物車內的資料：jedis.lrange(key, 0, -1)，用 tkt_no 比對 SQL取得商品價格、比對庫存
	public List<CartItemVO> getCart(String sessionId) {
		
		List cartItemList = new ArrayList();
		
		List<String> cartItems = CartItemJedisDAO.getCart(sessionId); // 透過 sessionId 取得 {"tkt_no": "xxx", "count": "x"}
//		System.out.println("cartItemService cartItems:" + cartItems);
		for (int i = 0; i < cartItems.size(); i++) {
			
			CartItemVO cartItemVO = gson.fromJson(cartItems.get(i), CartItemVO.class); // JSON to Object
//			System.out.println("cartItemService cartItems.get(i)" + cartItems.get(i));
//			System.out.println("cartItemService cartItemVO:" + cartItemVO);
			
			TktVO tktVO = dao.findByPrimaryKey(cartItemVO.getTkt_no()); // getOneTkt //從資料庫找出該商品名字及價錢，並設值到購物車的商品上
//			System.out.println("tktVO--------------50" + tktVO);
//			System.out.println("cartItemService cartItemVO.getTkt_no():" + cartItemVO.getTkt_no());
//			System.out.println("cartItemService tktVO:" + tktVO); // 這邊開始取不到 tktVO 值
			cartItemVO.setTkt_name(tktVO.getTkt_name());
			cartItemVO.setPrice(tktVO.getPrice());
			
			cartItemList.add(cartItemVO);
			
		}
		
		return cartItemList; // 讓servlet取得後渲染於購物車頁面
	}
	
	// addItemToCart
	public void addItemToCart(String sessionId, Integer tkt_no, Integer count) {
		CartItemVO cartItemVO = new CartItemVO();
		
		cartItemVO.setTkt_no(tkt_no);
		cartItemVO.setCount(count);
		
		CartItemJedisDAO.addItemToCart(sessionId, cartItemVO);
	}
	
	// delItem
	public void delItem(String sessionId, Integer tkt_no) {
		CartItemJedisDAO.delItem(sessionId, tkt_no);
	}
	
	// delAll
	public void delAll(String sessionId) {
		CartItemJedisDAO.delAll(sessionId);
	}
	
	// changeCount
	public void changeCount(String sessionId, Integer tkt_no, Integer count) {
		if (count > 0) { // 數量大於 0 才可以做商品數量更動
			CartItemVO cartItemVO = new CartItemVO();
			cartItemVO.setTkt_no(tkt_no);
			cartItemVO.setCount(count);
			
			CartItemJedisDAO.changeCount(sessionId, cartItemVO);
		} else {
			CartItemJedisDAO.delItem(sessionId, tkt_no); // 商品數量等於 0 就刪除該商品項目
		}
	}
	
	// getOneChecked
	public CartItemVO getOneChecked(String sessionId, Integer tkt_no) {
		return CartItemJedisDAO.getOneChecked(sessionId, tkt_no);
	}
	
	// updateTktsoldAmount
	public void updateTktsoldAmount(String sessionId, Integer tkt_no) {
		List<String> cartItems = CartItemJedisDAO.getCart(sessionId);
		
		Integer redisCount;
		Integer sold_amount;
		TktVO tktVO = new TktVO();
		
		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO oldItems = gson.fromJson(cartItems.get(i), CartItemVO.class);
			
			Integer checkedItemId = tkt_no;
			Integer oldItemId = oldItems.getTkt_no(); // 原本就在購物車的票券
//			System.out.println("--------oldItemId" + oldItemId);
			
			if (tkt_no.equals(oldItemId)) {
				redisCount = oldItems.getCount();
				sold_amount = redisCount;
				tktVO.setSold_amount(sold_amount);
				tktVO.setTkt_no(tkt_no);
//				System.out.println("----------redisCount"+redisCount);
			}
		}
//		dao.updateSoldAmount(tktVO);
//		dao.updateOriAmount(tktVO);
	}
	
	// updateTktoriAmount
//	public void updateTktoriAmount(String sessionId, Integer tkt_no) {
//		List<String> cartItems = CartItemJedisDAO.getCart(sessionId);
//		TktVO tktVO = new TktVO();
//		Integer redisCount;
//		for (int i = 0; i < cartItems.size(); i++) {
//			CartItemVO oldItems = gson.fromJson(cartItems.get(i), CartItemVO.class);
//			
//			Integer checkedItemId = tkt_no;
//			Integer oldItemId = oldItems.getTkt_no(); // 原本就在購物車的票券
//			
//			if (tkt_no.equals(oldItemId)) {
//				tktVO.setTkt_no(tkt_no);
//				dao.updateOriAmount(tktVO);
//			}
//		}
//	}
}
