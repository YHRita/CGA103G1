package com.tkt_item2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkt_item2.model.TktItem2Service;
import com.tkt_item2.model.TktItem2VO;

@WebServlet(name = "TktItemMemServlet", urlPatterns = { "/tkt_order/tktItemMem.do" })
public class TktItemMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// ------------------ getParameter ------------------
		Integer tkt_order_no = Integer.valueOf(req.getParameter("tkt_order_no"));
		
		// ------------------------- 永續層 -------------------------
		TktItem2Service tktItem2Srv = new TktItem2Service();
		List<TktItem2VO> tktItemList = tktItem2Srv.findByTktOrderNo(tkt_order_no);
		
		
		
		
		// ------------------------- forward -------------------------
		
		req.setAttribute("tktItemList", tktItemList);
		
		RequestDispatcher successView = req.getRequestDispatcher("/frontend/mem/listTktOrderItem.jsp");
		successView.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
