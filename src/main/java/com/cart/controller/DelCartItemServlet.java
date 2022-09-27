package com.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;

@WebServlet(name = "DelCartItemServlet", urlPatterns = { "/cart/delCartItem.do" })
public class DelCartItemServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// ------------ getParameter ------------
		Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
		String sessionId = (String) req.getSession().getAttribute("sessionId"); // 強轉型為 String
		// ------------ 永續層 ------------
		// delItem	
		CartItemService cartItemSrv = new CartItemService();
		cartItemSrv.delItem(sessionId, tkt_no);
//		System.out.println(tkt_no);
		
		// getCart
//		List<CartItemVO> cartItems = null;
//		cartItems = cartItemSrv.getCart(sessionId);
		
		//  ------------ forward ------------ 
//		req.setAttribute("cartItems", cartItems);
//		RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/cart.jsp");
//		rd.forward(req, res);
		
	}

}
