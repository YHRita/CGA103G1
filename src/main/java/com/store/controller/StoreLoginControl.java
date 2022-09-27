package com.store.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.model.StoreService;
import com.store.model.StoreVO;


@WebServlet("/StoreLoginControl")
public class StoreLoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html;charset=utf-8");
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			//==========================取得參數========================
			String store_acc = req.getParameter("store_acc");
			String store_pwd = req.getParameter("store_pwd");
			
			
			//=========================資料存取===========================
			
		
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.logIn(store_acc, store_pwd);

			if(storevo == null) {
				
				errorMsgs.put("storevo", "請輸入帳號密碼");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/store/storeLogin.jsp");
					failureView.forward(req, res);
				}
			} else if(storevo.getAcc_status() == 0) {
				errorMsgs.put("storevo", "帳號未啟用");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/store/storeLogin.jsp");
					failureView.forward(req, res);
				}
			}else if(storevo.getAcc_status() == 2) {
				errorMsgs.put("storevo", "帳號停權");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/store/storeLogin.jsp");
					failureView.forward(req, res);
				}
			}
			else {
				
				session.setAttribute("store_acc", store_acc);
				session.setAttribute("storevo", storevo);
				try {
					String location = (String)session.getAttribute("location");
					if(location != null) {
						res.sendRedirect(location);
						return;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				res.sendRedirect(req.getContextPath()+"/frontend/store/storeFrontendPage.jsp");
			}
		}
		
	
	
	

}
