package com.emp.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;


@WebServlet("/emp/emp.do")
public class EmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	       
	    public EmpServlet() {
	        super();
	    }
	
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}
		
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			if("getOneEmp".equals(action)) {
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				EmpService ser = new EmpService();
				EmpVO empVO = ser.getOneEmp(emp_no);
				
				req.setAttribute("empVO", empVO);
				
				String url = "backend/emp/personal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
			}
			if("logout".equals(action)) {
				HttpSession session = req.getSession();
				session.invalidate();
				//導回登入頁面-------------
				String url = "/backend/login/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			
			if("login".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
					/*************************** 1.接收請求參數 **********************/
				String emp_acc = req.getParameter("emp_acc");
				String emp_pwd = req.getParameter("emp_pwd");
				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO1 = empSvc.login(emp_acc, emp_pwd);
				if(empVO1==null) {
					errorMsgs.add("查無資料或輸入錯誤");
				}else if(empVO1.getEmp_state()==0) {
					errorMsgs.add("您已離職無法登入。");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/後台登入/後台登入.jsp");	//查無資料返回登入
					failureView.forward(req, res);
					return;// 程式中斷
				}else {
					HttpSession session = req.getSession();
				    session.setAttribute("empVO1", empVO1);
				    try {
				    	 String location = (String) session.getAttribute("location2");
				         if (location != null) {
				           session.removeAttribute("location2");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				           res.sendRedirect(location);            
				           return;}
				    }catch (Exception ignored) { }
				      res.sendRedirect(req.getContextPath()+"/backend/後台主頁/後台主頁.jsp");
				}
		}
			
			if("updateOne".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				
				req.setAttribute("empVO", empVO);
				
				String url = "/backend/emp/updateEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					return;
			}
			
			if("update".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				Integer emp_no =Integer.valueOf(req.getParameter("emp_no").trim());
				String emp_acc =req.getParameter("emp_acc").trim();
				String emp_pwd =req.getParameter("emp_pwd").trim();
				String emp_pwdReg = "^[(a-zA-Z0-9)]{5,20}$";
				if(emp_pwd == null || emp_pwd.trim().length() ==0 ) {
					errorMsgs.add("密碼不可為空白");
				}else if(!emp_pwd.trim().matches(emp_pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼只能是英文字母、數字,且密碼長度需大於6");
	            }
				
				String emp_name =req.getParameter("emp_name").trim();
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            } 
				
				String emp_email =req.getParameter("emp_email").trim();
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if(emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				}
					else if(!emp_email.trim().matches(e_emailReg)) {
						errorMsgs.add("電子信箱: 請依照電子郵件格式輸入");
	            }
				Integer emp_state =Integer.valueOf(req.getParameter("emp_state").trim());
					
				EmpVO empVO = new EmpVO();
				empVO.setEmp_no(emp_no);
				empVO.setEmp_acc(emp_acc);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_state(emp_state);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/updateEmp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_acc, emp_pwd, emp_name, emp_email, emp_state, emp_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}
			
			if(action.equals("insert")){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_acc = req.getParameter("emp_acc");
				String emp_accReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if(emp_acc == null || emp_acc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if(!emp_acc.trim().matches(emp_accReg)) { 
					errorMsgs.add("帳號只能英文字母、數字 , 且長度必需在2到10之間");
	            }
				
				String emp_pwd = req.getParameter("emp_pwd");
				String emp_pwdReg = "^[(a-zA-Z0-9)]{6,10}$";
				if(emp_acc == null || emp_acc.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if(!emp_pwd.trim().matches(emp_pwdReg)) { 
					errorMsgs.add("密碼只能英文字母、數字 , 且長度必需在6到10之間");
	            }
				
				String emp_name = req.getParameter("emp_name");		
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if(emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字 , 且長度必需在2到10之間");
	            }
				
				String emp_email =req.getParameter("emp_email").trim();
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if(emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				}
					else if(!emp_email.trim().matches(e_emailReg)) {
						errorMsgs.add("電子信箱: 請依照電子郵件格式輸入");
	            }
				
				Integer emp_state =Integer.valueOf(req.getParameter("emp_state"));
				

				EmpVO empVO = new EmpVO();
				empVO.setEmp_acc(emp_acc);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_state(emp_state);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/addEmp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_acc, emp_pwd, emp_name, emp_email, emp_state);
				
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			}

		if(action.equals("fire")) {
			Integer emp_no = Integer.valueOf(req.getParameter("emp_no").trim());
			
			/***************************2.開始查詢資料*****************************************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.updateEmpstate(0, emp_no);
			req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/backend/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
		}
		if(action.equals("onbo")) {
			Integer emp_no = Integer.valueOf(req.getParameter("emp_no").trim());
			
			/***************************2.開始查詢資料*****************************************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.updateEmpstate(1, emp_no);
			req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/backend/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
		}
		
		if("logOutEmp".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/backend/emp/logOutEmp.jsp";
			RequestDispatcher ret = req.getRequestDispatcher(url);
			ret.forward(req, res);
			
			
		}
		
		
}
}
