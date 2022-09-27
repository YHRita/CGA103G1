package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter( filterName = "RmFilter",servletNames = {"RmReservePaymentServlet"})

public class RmFilter implements Filter {

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
		String arrival = (String) session.getAttribute("arrival_date");
		String departure = (String) session.getAttribute("departure_date");
		Integer guest = (Integer) session.getAttribute("guest");
		Integer rm_type_no =(Integer) session.getAttribute("rm_type_no");
		Integer qty = (Integer) session.getAttribute("qty");
		
//		System.out.println(arrival);
//		System.out.println(departure);
//		System.out.println(guest);
//		System.out.println(rm_type_no);
//		System.out.println(qty);
		
		
		
//		String arrival = req.getParameter("arrival_date");
//		String departure = req.getParameter("departure_date");
//		Integer guest = new Integer(req.getParameter("guest"));
////		Integer guest1 = Integer.parseInt(guest.trim());
//		System.out.println(guest);
//		Integer rm_type_no = Integer.valueOf(req.getParameter("rm_type_no"));
//		Integer qty = Integer.valueOf(req.getParameter("qty"));
		
//		System.out.println(arrival);
//		System.out.println(departure);
//		System.out.println(guest);
//		System.out.println(rm_type_no);
//		System.out.println(qty);
		
//		session.setAttribute("arrival_date", arrival);
//		session.setAttribute("departure_date", departure);
//		session.setAttribute("qty", qty);
//		session.setAttribute("guest", guest); // 人數只有搜尋時會用到，選完房型改記rm_type_no
//		session.setAttribute("rm_type_no", rm_type_no); // 人數只有搜尋時會用到，選完房型改記rm_type_no
		
		
		// 【從 session 判斷此user是否登入過】
	    String mem_mail = (String) session.getAttribute("mem_email");
	    if (mem_mail == null) {
	    	session.setAttribute("location", req.getContextPath()+"/RmRsv/RmRsvPayment.do");
	      res.sendRedirect(req.getContextPath()+"/frontend/signin/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}
}