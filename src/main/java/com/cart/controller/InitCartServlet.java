package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
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
import com.coup.model.CoupService;
import com.coup.model.CoupVO;

import javax.servlet.http.Cookie;

@WebServlet(name = "InitCartServlet", urlPatterns = { "/cart/initCart.do" })
public class InitCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
//		頁面先設一個假button作為進入網站得住發事件，須改為filter註冊到網站每個頁面

		Cookie[] cookies = req.getCookies();

		// cookiekey:shoppingCart cookieValue:sessionid
		// 檢視user是否已經有存放cookie，cookie 的值以List型態存放於Redis
		// Redis：(cookieValue(sessionid), {"itemId": "xxx","count": "x"})

//		String action = req.getParameter("action");

		for (int i = 0; i < cookies.length; i++) {
			Cookie userCookie = cookies[i];

			if ("shoppingCart".equals(userCookie.getName())) {
				// 讓每個頁面可以透過 sessionId 呼叫 CartService 的方法
				session.setAttribute("sessionId", userCookie.getValue());

//				RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/testTkt1.jsp"); // 看能不能改成動態
//				rd.forward(req, res);
				return;
			}

			// 若未找到 shoppingCart，新增 cookie，並將 session 作為 key 存入 Redis
			// cookie 的 key:shoppingCart value:sessionid
			Cookie shoppingCart = new Cookie("shoppingCart", session.getId());
			shoppingCart.setMaxAge(3 * 24 * 60 * 60); // 存活3天，以秒為單位
			shoppingCart.setHttpOnly(true);
			shoppingCart.setPath("/");

			session.setAttribute("sessionId", session.getId());
			res.addCookie(shoppingCart);
//				System.out.println(shoppingCart);

//			req.setAttribute("cartItems", cartItems);
//			RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/testTkt1.jsp");
//			rd.forward(req, res);


		}

	}

}
