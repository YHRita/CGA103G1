package com.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemService;

@WebServlet(name = "ChangeCountServlet", urlPatterns = { "/cart/changeCount.do" })
public class ChangeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// -------------- getParameter --------------
		Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
		Integer count = Integer.valueOf(req.getParameter("count"));		
		String sessionId = (String) req.getSession().getAttribute("sessionId"); // 取得 sessionId
		// -------------- 永續層 --------------
		CartItemService cartItemSrv = new CartItemService();
		cartItemSrv.changeCount(sessionId, tkt_no, count); // 把更改數量存到 redis
	
		
	}

}
