package com.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;
import com.google.gson.Gson;

@WebServlet(name = "GetCartServlet", urlPatterns = { "/cart/getCart.do" })
public class GetCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		
		String sessionId = (String) req.getSession().getAttribute("sessionId"); // 取得 session 的 ID
//		System.out.println("getcartservlet sessionid:-------------------" + sessionId);
		CartItemService cartItemSrv = new CartItemService();
		
		List<CartItemVO> cartItems = null;
		cartItems = cartItemSrv.getCart(sessionId);
//		System.out.println("getcartservlet cartitems:----------------" + cartItems == null);
		
		req.setAttribute("cartItems", cartItems);
		RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/cart.jsp");
		rd.forward(req, res);
		
	}

}
