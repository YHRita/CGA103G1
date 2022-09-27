package com.auth_fun.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth_fun.model.AuthFunService;
import com.auth_fun.model.AuthFunVO;


@WebServlet("/AuthFun")
public class AuthFunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getAll".equals(action)) { // 
			try {
				/*************************** 開始查詢資料 ****************************************/
				AuthFunService authFunSvc = new AuthFunService();
				List<AuthFunVO> list = authFunSvc.getAll();

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list", list); // 資料庫取出的VO物件,存入req
				String url = "/backend/authfun/listAllAuthFun.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/backend/authfun/llistAllAuthFun.jsp").forward(req, res);
			}
		}
	}
}