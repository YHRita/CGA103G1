package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartItemJedisDAO;
import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;
import com.google.gson.Gson;

@WebServlet(name = "AddCartServlet", urlPatterns = { "/cart/addCart.do" })
public class AddCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
//		HttpSession session = req.getSession();
		
		// ------------ getParameter ------------
		Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
//		System.out.println("addCartServlet---------" + tkt_no);
		Integer count = Integer.valueOf(req.getParameter("count"));		
//		System.out.println("addCartServlet---------" + count);
		String sessionId = (String) req.getSession().getAttribute("sessionId"); // 取得 sessionId
//		System.out.println("addCartServlet sessionId:" + sessionId);

		// ------------ 永續層 -------------
		CartItemService cartItemSrv = new CartItemService();
		cartItemSrv.addItemToCart(sessionId, tkt_no, count);
		
//		Integer cartAllNum = CartItemJedisDAO.cartAllNum(sessionId);
//		session.setAttribute("cartAllNum", cartAllNum);
	}

}
