package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;
import com.google.gson.Gson;


@WebServlet(name = "GetCartCountServlet", urlPatterns = { "/cart/getCartCount.do" })
public class GetCartCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		Gson gson = new Gson();
		
		String sessionId = (String)req.getSession().getAttribute("sessionId");
		CartItemService cartItemSrv = new CartItemService();
		List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
		cartItems = cartItemSrv.getCart(sessionId);
		
		PrintWriter out = res.getWriter();
		String cartItemsJson = gson.toJson(cartItems); // cartItems 轉乘 JSON 格式
		out.write(cartItemsJson);
		out.flush();
		out.close();
		
	}

}
