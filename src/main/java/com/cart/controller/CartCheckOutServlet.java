package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;


@WebServlet(name = "CartCheckOutServlet", urlPatterns = { "/cart/cartCheckOut.do" })
public class CartCheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// -------------- getParameter --------------
		String sessionId = null;
		
		Enumeration<String> en = req.getParameterNames(); // 接收表單送來的所有 checkbox name
		
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String values[] = req.getParameterValues(name); // 	取出前端表單送來已選取的 tkt_no
		
			
			// 創一個新的 List 裝已被選取的 checkbox
			List<CartItemVO> checkedList = new ArrayList<CartItemVO>();
			
			Integer tkt_no = 0;
			
			if (values != null) { 
				for (int i = 0; i < values.length; i++) {
					sessionId = (String) req.getSession().getAttribute("sessionId"); // 取得 session 的 ID
					CartItemService cartItemSrv = new CartItemService();
					tkt_no = Integer.valueOf(values[i]);
					CartItemVO itemChecked = cartItemSrv.getOneChecked(sessionId, tkt_no);
					
					checkedList.add(itemChecked);
				}
			}
			
			
			req.setAttribute("checkedList", checkedList);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/payment.jsp");
		rd.forward(req, res);
		

		
	}

}
