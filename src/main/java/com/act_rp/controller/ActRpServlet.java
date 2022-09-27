package com.act_rp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.act_rp.model.*;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@MultipartConfig
@WebServlet("/actRp")
public class ActRpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActRpServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			// ------------------------- 輸入格式錯誤處理 ----------------

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 會員編號
//						Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
			Integer mem_no = 1;

			// 活動編號
			Integer act_no = null;
			try {
				act_no = Integer.valueOf(req.getParameter("act_no").trim());
			} catch (Exception e) {
				errorMsgs.add("請輸入活動編號");
			}

			// 檢舉事由
			String act_rp_reason = req.getParameter("act_rp_reason").trim();
			if (act_rp_reason == null || act_rp_reason.trim().length() == 0) {
				errorMsgs.add("請選擇檢舉事由");
			}

			// 檢舉時間
			java.sql.Date act_rp_time = null;

			// 檢舉文字內容
			String act_rp_content = req.getParameter("act_rp_content").trim();
			if (act_rp_content == null || act_rp_content.trim().length() == 0) {
				errorMsgs.add("請輸入檢舉文字內容");
			}

			// 檢舉狀態
			Integer act_rp_status = null;

			ActRpVO actRpVO = new ActRpVO();

			actRpVO.setMem_no(mem_no);
			actRpVO.setAct_no(act_no);
			actRpVO.setAct_rp_reason(act_rp_reason);
			actRpVO.setAct_rp_time(act_rp_time);
			actRpVO.setAct_rp_content(act_rp_content);
			actRpVO.setAct_rp_status(act_rp_status);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actRpVO", actRpVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/act_rp/act_rp.jsp");
				failureView.forward(req, res);
				return; // 中斷
			}

			// ----------------------- 修改資料 ---------------------
			ActRpService aRSrv = new ActRpService();
			actRpVO = aRSrv.insert(mem_no, act_no, act_rp_reason, act_rp_content, act_rp_time, act_rp_status);

			// --------------------- 修改完成,準備轉交 ---------------
			req.setAttribute("actRpVO", actRpVO);
			RequestDispatcher successView = req.getRequestDispatcher("/frontend/homePage.jsp");
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer act_rp_no = Integer.valueOf(req.getParameter("act_rp_no").trim());
			
			Integer act_no = Integer.valueOf(req.getParameter("act_no").trim());

			String act_rp_reason = req.getParameter("act_rp_reason").trim();

			Integer emp_no = null;
			try {
				emp_no = Integer.valueOf(req.getParameter("emp_no").trim());
			} catch (NumberFormatException e) {
				System.out.println(123);
				errorMsgs.add("請填員工編號(數字)");
			}
			java.sql.Date act_rp_done_time = null;

			Integer act_rp_status = null;
			try {
				act_rp_status = Integer.valueOf(req.getParameter("act_rp_status").trim());
			}catch(NullPointerException |IllegalStateException e ) {
				System.out.println(456);
				errorMsgs.add("請選擇狀態");
			}
			
			ActRpVO actRpVO = new ActRpVO();
			actRpVO.setAct_rp_no(act_rp_no);
			actRpVO.setAct_no(act_no);
			actRpVO.setAct_rp_reason(act_rp_reason);
			actRpVO.setEmp_no(emp_no);
			actRpVO.setAct_rp_done_time(act_rp_done_time);
			actRpVO.setAct_rp_status(act_rp_status);
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actRpVO", actRpVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/act_rp/updateActRp.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ActRpService actRpSvc = new ActRpService();
			 actRpVO = actRpSvc.update(act_rp_no, act_no, act_rp_reason, emp_no, act_rp_done_time, act_rp_status);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actRpVO", actRpVO); 
			String url = "/backend/act_rp/allActRp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		
		if ("getone".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				/***************************1.接收請求參數****************************************/
				Integer act_rp_no = Integer.valueOf(req.getParameter("act_rp_no"));
				
				/***************************2.開始查詢資料****************************************/
				ActRpService actRpSvc = new ActRpService();
				ActRpVO actRpVO = actRpSvc.getone(act_rp_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actRpVO", actRpVO);        
				String url = "/backend/act_rp/updateActRp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}

	}
}
