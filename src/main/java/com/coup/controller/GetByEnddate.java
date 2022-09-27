package com.coup.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coup.model.CoupService;
import com.coup.model.CoupVO;

@WebServlet(name = "GetByEnddate", urlPatterns = { "/coup/getByEnddate.do" })
public class GetByEnddate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// ------------- getParameter -------------
		// enddate
		Date enddate = null;

		try {
			enddate = Date.valueOf(req.getParameter("enddate").trim());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("抓截止");
		System.out.println(enddate); // 有抓到截止日期
					
		// -------------- getByEnddate -------------
		CoupService coupSrv = new CoupService();
		List<CoupVO> list = coupSrv.getByEndDate(enddate);
		
		// if no data
		if (list.isEmpty()) {
			CoupVO coupVO = new CoupVO();
			req.setAttribute("coupVO", coupVO);
			RequestDispatcher failureView = req.getRequestDispatcher("/backend/coup/listAllCoup.jsp");
			failureView.forward(req, res);
			return; // 中斷 
		}
					
		// ------------- forward -------------
		
//		CoupVO coupVO = new CoupVO();
//		req.setAttribute("coupVO", coupVO);
		RequestDispatcher successView = req.getRequestDispatcher("/backend/coup/getByEndDate.jsp");
		// 回傳 list 到前端
		res.getWriter().println(list);
		System.out.println(list); // 可以取到值
		successView.forward(req, res);
	}

}
