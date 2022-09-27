package com.auth_fun.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth_fun.model.AuthFunService;
import com.auth_fun.model.AuthFunVO;

//@WebServlet(name = "UpdateAuthServlet", urlPatterns = { "/authfun/updateAuthFun.do" })
@WebServlet("/authfun/authfun.do")
public class UpdateAuthFunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("getOneUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer fun_no = Integer.valueOf(req.getParameter("fun_no"));

			AuthFunService authFunSvc = new AuthFunService();
			AuthFunVO authFunVO = authFunSvc.getOneAuthFun(fun_no);

			req.setAttribute("authFunVO", authFunVO);

			String url = "/backend/authfun/updateAuthFun.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

		if ("update".equals(action)) {
			// ------------------------- 輸入格式錯誤處理 -----------------

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer fun_no = Integer.valueOf(req.getParameter("fun_no"));
			String fun_name = req.getParameter("fun_name").trim();
			if (fun_name == null || fun_name.trim().length() == 0) {
				errorMsgs.add("權限名稱請勿空白");
			}

			AuthFunVO authFunVO = new AuthFunVO();
			authFunVO.setFun_name(fun_name);
			authFunVO.setFun_no(fun_no);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("authFunVO", authFunVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/authfun/updateAuthFun.jsp");
				failureView.forward(req, res);
				return; // 中斷
			}

			// ----------------------- 修改資料 ---------------------
			AuthFunService authFunSrc = new AuthFunService();
			authFunVO = authFunSrc.updateAuthFunVO(fun_name, fun_no);

			// --------------------- 修改完成,準備轉交 ---------------
			req.setAttribute("authFunVO", authFunVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/authfun/listAllAuthFun.jsp");
			successView.forward(req, res);

		}

	}
}