package com.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class StoreLoginFilter
 */
@WebFilter("/StoreLoginFilter")
public class StoreLoginFilter extends HttpFilter implements Filter {
	
    private FilterConfig config;   
    
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}



	public void destroy() {
		config = null;
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		String store_acc =(String) session.getAttribute("store_acc");
		if(store_acc == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/frontend/store/storeLogin.jsp");
			return;
		}else {
		chain.doFilter(request, response);
		}
	}




}
