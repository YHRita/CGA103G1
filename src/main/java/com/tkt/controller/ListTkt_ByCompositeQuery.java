package com.tkt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tkt.model.TktService;
import com.tkt.model.TktVO;


import java.util.*;

@WebServlet(name = "ListTkt_ByCompositeQuery", urlPatterns = { "/tkt/tktCqList.do" })
public class ListTkt_ByCompositeQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		// --------------- 將輸入資料轉為 Map --------------
		//採用Map<String,String[]> getParameterMap()的方法 
		//注意:an immutable java.util.Map 
		//Map<String, String[]> map = req.getParameterMap();
		HttpSession session = req.getSession();
		Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
		
		// 以下的 if 區塊只對第一次執行時有效
		if (req.getParameter("whichPage") == null){
			Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
			session.setAttribute("map",map1);
			map = map1;
//			System.out.println(map);
		} 
		
		// --------------- 將輸入資料轉為 Map --------------
		TktService tktSrv = new TktService();
		List<TktVO> list= tktSrv.getAll(map);
		
		// --------------- forward --------------
		req.setAttribute("listTkt_ByCompositeQuery", list);
		RequestDispatcher successView = req.getRequestDispatcher("/frontend/tkt/cqTktList.jsp"); 
		successView.forward(req, res);
	}

}
