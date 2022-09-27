package com.util;

import static com.util.HibernateUtil.*;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.HibernateUtil;

@WebFilter("/*")
public class HibernateFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Session session = getSessionFactory().getCurrentSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			chain.doFilter(req, res);
			transaction.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
