package com.tkt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkt.model.TktService;
import com.tkt.model.TktVO;

@WebServlet(name = "TktDetail", urlPatterns = { "/tkt/tktDetail.do" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TktDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// ------------------ getParameter ------------------
		// 接收 tkt_no
		
		Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
		
		// ------------------------- 永續層 -------------------------
		TktVO tktVO = new TktVO();
		TktService tktSrv = new TktService();
		tktVO = tktSrv.getOneTkt(tkt_no);
		
		// ------------------------- forward -------------------------
		
		req.setAttribute("tktVO", tktVO);
		
		RequestDispatcher successView = req.getRequestDispatcher("/frontend/tkt/tktDetail.jsp");
		successView.forward(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
