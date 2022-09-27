package com.mem_coup.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem_coup.model.MemCoupService;


@WebServlet(name = "GetByOneMem", urlPatterns = { "/mem_coup/getByOneMem.do" })
public class GetByOneMem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
		
		MemCoupService memCoupSrv = new MemCoupService();
		memCoupSrv.getOneMemCoup(mem_no);
		
		
		
		RequestDispatcher successView = req.getRequestDispatcher("/backend/mem_coup/getByOneMem.jsp");
		successView.forward(req, res);
		
		
	}

}
