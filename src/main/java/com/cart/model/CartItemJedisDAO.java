package com.cart.model;

import java.util.List;

import com.google.gson.Gson;
import com.tkt.model.TktService;
import com.tkt.model.TktVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CartItemJedisDAO {

	// 取得連線池
	private static JedisPool pool = JedisUtil.getJedisPool();

	// --------------------- getCart ---------------------
	// 購物車用 List 裝一筆一筆的商品
	// session Id
	public static List<String> getCart(String sessionId) {
		Jedis jedis = null;
		jedis = pool.getResource(); // 連線
		jedis.select(1);

		List<String> cartItems = jedis.lrange(sessionId, 0, -1); // lrange(key, start, stop) 取得對應的 start~stop 範圍的值
//			System.out.println("cartItemJedisDAO cartItems jedis.lrange:" + cartItems);

		jedis.close();

		return cartItems;
	}

	// --------------------- addItemToCart ---------------------

	public static void addItemToCart(String sessionId, CartItemVO cartItemVO) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource(); // 連線
		jedis.select(1);

		List<String> cartItems = getCart(sessionId); // 先取得購物車內容
//		System.out.println("CartItemJedisDAO cartItems:" + cartItems);

		if (cartItems != null) { // 如果購物車內有東西
			for (int i = 0; i < cartItems.size(); i++) {
				CartItemVO oldItems = gson.fromJson(cartItems.get(i), CartItemVO.class); // JSON to Object，把購物車原本就有的商品  (oldItems) 一一取出

				Integer newItemId = cartItemVO.getTkt_no(); // 新加入購物車的票券
				Integer oldItemId = oldItems.getTkt_no(); // 原本就在購物車的票券

				// 如果新加入購物車的票券原本就在購物車，只要更動原本在購物車票券購買數量即可
				// 點擊加入購物車按鈕就加一
				if (newItemId.equals(oldItemId)) {
					Integer count = oldItems.getCount();

					count += 1; // 目前購物車和票券頁面還沒有 +, - 按鈕控制數量
//					count = cartItemVO.getCount(); // 取得加減後的 count
					oldItems.setCount(count); // 給予原本在購物車的票券更新數量

					String str = gson.toJson(oldItems); // Object To JSON，轉回 JSON 格式存回 Redis
					jedis.lset(sessionId, i, str); // lset(key, index, value) 將 key 對應的元素，從指定的 index 替換成
					jedis.expire(sessionId, 60*60*24*3); // 加入三天後沒購買就清空購物車
					jedis.close();
					return;
				}
			}
			// 若沒有該商品ID則新增票券
			String strVO = gson.toJson(cartItemVO);
			jedis.rpush(sessionId, strVO);
			jedis.expire(sessionId, 60*60*24*3); // 加入三天後沒購買就清空購物車
			jedis.close();
		}

	}

	// --------------------- delItem ---------------------
	public static void delItem(String sessionId, Integer tkt_no) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource(); // 連線
		jedis.select(1);

		List<String> cartItems = getCart(sessionId); // 先取得購物車內容

		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO oldItems = gson.fromJson(cartItems.get(i), CartItemVO.class); // JSON to Object，把購物車原本就有的商品
																						// oldItems 一一取出

			if (oldItems.getTkt_no().equals(tkt_no)) {
				jedis.lrem(sessionId, 0, cartItems.get(i)); // 刪除該商品 lrem(key, count, value)
				jedis.close();
				return;
			}
		}

	}

	// --------------------- delAll---------------------
	public static void delAll(String sessionId) {
		Jedis jedis = null;
		jedis = pool.getResource(); // 連線
		jedis.select(1);

		jedis.del(sessionId); // 直接刪掉 sessionId 清空購物車
		jedis.close();
	}

	// --------------------- changeCount ---------------------

	public static void changeCount(String sessionId, CartItemVO cartItemVO) {
		Gson gson = new Gson();

		Jedis jedis = null;
		jedis = pool.getResource(); // 連線
		jedis.select(1);

		List<String> cartItems = getCart(sessionId); // 先取得購物車內容

		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO oldItems = gson.fromJson(cartItems.get(i), CartItemVO.class); // JSON to Object，把購物車原本就有的商品 (oldItems) 一一取出

			Integer newItemId = cartItemVO.getTkt_no(); // 新加入購物車的票券
			Integer oldItemId = oldItems.getTkt_no(); // 原本就在購物車的票券

			// 如果新加入購物車的票券原本就在購物車，只要更動原本在購物車票券購買數量即可
			// 點擊加入購物車按鈕就加一
			if (newItemId.equals(oldItemId)) {
				Integer count = oldItems.getCount();

				count = cartItemVO.getCount(); // 取得加減後的 count
				oldItems.setCount(count); // 給予原本在購物車的票券更新數量

				String str = gson.toJson(oldItems); // Object To JSON，轉回 JSON 格式存回 Redis
				jedis.lset(sessionId, i, str); // lset(key, index, value) 將 key 對應的元素，從指定的 index 替換成
				jedis.close();
				return;
			}
		}
		// 若沒有該商品ID則新增
		String strVO = gson.toJson(cartItemVO);
		jedis.rpush(sessionId, strVO);
		jedis.close();

	}
	
	// -------------- getOneChecked ---------------
	public static CartItemVO getOneChecked(String sessionId, Integer tkt_no) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource(); // 連線
		jedis.select(1);
		
		// 先把購物車抓出來比較
		List<String> cartItems = getCart(sessionId);
		
		CartItemVO cartItemVO = new CartItemVO();
		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO oldItems = gson.fromJson(cartItems.get(i), CartItemVO.class); // JSON to Object，把購物車原本就有的商品 (oldItems) 一一取出
			
			Integer checkedItemId = tkt_no; // 新加入購物車的票券
			Integer oldItemId = oldItems.getTkt_no(); // 原本就在購物車的票券
			
			if (checkedItemId.equals(oldItemId)) {	
				TktService tktSrv = new TktService();
				TktVO tktVO = tktSrv.getOneTkt(tkt_no); // 透過編號抓出一筆票券(要透過 tkt model 撈 MySQL 資料)
				String tkt_name = tktVO.getTkt_name();
				Integer price = tktVO.getPrice();
				Integer count = oldItems.getCount(); // redis 呼叫 getCount
				
				cartItemVO.setTkt_no(tkt_no);
				cartItemVO.setTkt_name(tkt_name);
				cartItemVO.setCount(count);
				cartItemVO.setPrice(price);
				
				jedis.close();
			}
		}
		
		
		return cartItemVO;
	}
	
	// cartAllNum
//	public static Integer cartAllNum(String sessionId) {
//		Jedis jedis = null;
//		jedis = pool.getResource(); // 連線
//		jedis.select(1);
//
//		List<String> cartItems = getCart(sessionId); // 先取得購物車內容
//		
//		Integer cartAllNum = cartItems.size();
//		
//		jedis.close();
//		
//		return cartAllNum;
//	}
	
	// ------------------- 結帳後更新 tkt 的 sold_amount -------------------
//	public static void updateTktsoldAmount(String sessionId, Integer tkt_no) {
//		Gson gson = new Gson();
//		Jedis jedis = null;
//		jedis = pool.getResource(); // 連線
//		jedis.select(1);
//		
//		// getCart 取得購物車再取數量
//		
//		
//		// update 庫存時需要拿到結帳刪除前的 count 
//	}
	
	// ------------------- 結帳後更新 tkt 的 original_amount -------------------
//	public static void updateTktoriAmount() {
//		
//	}
	

}
