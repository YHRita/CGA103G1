package com.mem_coup.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem_coup.model.MemCoupService;
import com.mem_coup.model.MemCoupVO;

@WebServlet(name = "SendCoupServlet", urlPatterns = { "/mem_coup/sendCoup.do" })
public class SendCoupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		
		// ------------------ getParameter ------------------
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		Integer mem_no = null;
		try {
			mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
		} catch (NumberFormatException e) {
			errorMsgs.add("請填入會員編號");
		}
		
		
		Integer coup_no = null;
		try {
			coup_no = Integer.valueOf(req.getParameter("coup_no").trim());
		} catch (NumberFormatException e) {
			errorMsgs.add("請填入要發放的票券編號");
		}
		
		Integer coup_state = 0; // 預設未使用
		
		MemCoupVO memCoupVO = new MemCoupVO();
		memCoupVO.setMem_no(mem_no);
		memCoupVO.setCoup_no(coup_no);
		memCoupVO.setCoup_state(coup_state);
		
		// ----------------- 永續層 -----------------
		MemCoupService memCoupSrv = new MemCoupService();
		memCoupSrv.sendCoupToMem(mem_no, coup_no, coup_state);
		
		// ----------------- forward -----------------
		
		req.setAttribute("memCoupVO", memCoupVO);
		RequestDispatcher successView = req.getRequestDispatcher("/backend/mem_coup/listSendMemCoup.jsp");
		successView.forward(req, res);
		
		
	}

}
