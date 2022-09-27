package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter( filterName = "CartFilter",servletNames = {"CartCheckOutServlet"})
public class CartFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
	    String mem_mail = (String) session.getAttribute("mem_email");
	    if (mem_mail == null) {
	      session.setAttribute("location", req.getContextPath()+"/cart/getCart.do");
	      res.sendRedirect(req.getContextPath()+"/frontend/signin/login.jsp"); 
		} else {
			chain.doFilter(request, response);
		}
	}
}