package com.auth.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.emp.model.EmpService;

@WebServlet(name = "UpdateAuthServlet", urlPatterns = { "/auth/updateAuth.do" })
public class UpdateAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    @Override   
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

    @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
 
		
		if ("getOneForUpdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 ****************************************/
				Integer	emp_no = new Integer(req.getParameter("emp_no"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				AuthService authSvc = new AuthService();
				AuthVO authVO = authSvc.getOneAuth(emp_no);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("authVO", authVO); // 資料庫取出的VO物件,存入req
				String url = "/backend/auth/updateAuth.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
		}
				/*************************** 其他可能的錯誤處理 **********************************/
			
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				AuthService authSvc = new AuthService();
				EmpService empSvc = new EmpService();

				Integer fun_no = Integer.valueOf(req.getParameter("fun_no").trim());
//				if(authSvc.getOneAuth(fun_no) == null) {
//					errorMsgs.add("不存在的權限編號，請重新新增");
//				} else if (fun_no == null) {
//					errorMsgs.add("權限編號 請勿空白");
//				} 

				Integer emp_no = Integer.valueOf(req.getParameter("emp_no").trim());
//				if(empSvc.getOneEmp(emp_no) == null) {
//					errorMsgs.add("不存在的員工編號，請重新新增");	
//				} else if (emp_no == null) {
//					errorMsgs.add("員工編號 請勿空白");
//				}
				
//				if (authSvc.getOneByEmp(emp_no) != null) {   // 判斷此員工是否已存在權限
//					errorMsgs.add("該員工已有設定權限");
//				}
				
				AuthVO authVO = new AuthVO();
				authVO.setFun_no(fun_no);
				authVO.setEmp_no(emp_no);
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/updateAuth.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 ****************************************/
				authVO = authSvc.updateAuth(fun_no, emp_no);
				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
				req.setAttribute("authVO", authVO); // 資料庫取出的VO物件,存入req
				String url = "/backend/auth/listAllAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改權限資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/updateAuth.jsp");
//				failureView.forward(req, res);
//			}
		}

	}
}

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	
		
		
	


