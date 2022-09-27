package com.system_notification_message.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.system_notification_message.model.SystemNotificationMessageService;
import com.system_notification_message.model.SystemNotificationMessageVO;

@MultipartConfig
@WebServlet("/snm")
public class SystemNotificationMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SystemNotificationMessageServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			// -----------------------錯誤處理-------------------------

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// msg
			String msg = req.getParameter("msg");
			if (msg == null || msg.length() == 0) {
				errorMsgs.add("請填入系統訊息");
			}

			// img
			byte[] img = null;
			Part msg_img = req.getPart("msg_img");
			InputStream in = msg_img.getInputStream();
			img = new byte[in.available()];
			in.read(img);
			in.close();
			
			//員工
			Integer emp_no = null;
			try {
			emp_no = Integer.valueOf(req.getParameter("emp_no").trim());
			} catch(Exception e) {
				errorMsgs.add("請填入員工編號");
			}

			SystemNotificationMessageVO SNMVO = new SystemNotificationMessageVO();

			SNMVO.setMsg(msg);
			SNMVO.setMsg_img(img);
			SNMVO.setEmp_no(emp_no);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("SNMVO", SNMVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/system_notification_message/addSNM.jsp");
				failureView.forward(req, res);
				return; // 中斷
			}
			// ----------------------- 修改資料 ---------------------
			SystemNotificationMessageService SNMSV = new SystemNotificationMessageService();
			SNMVO = SNMSV.insertSNM(msg, img, emp_no);

			// --------------------- 修改完成,準備轉交 ---------------
			req.setAttribute("SNMVO", SNMVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/system_notification_message/listSNM.jsp");
			successView.forward(req, res);
		}

	
	
	if ("getpic".equals(action)) {

		/*************************** 1.接收請求參數 ****************************************/
		Integer msg_no = new Integer(req.getParameter("msg_no"));

		/*************************** 2.開始查詢資料 ****************************************/
		SystemNotificationMessageService snmSvc = new SystemNotificationMessageService();
		SystemNotificationMessageVO snmVO = snmSvc.getone(msg_no);

		/*************************** 3.輸出圖片 ************/
		byte[] content = snmVO.getMsg_img();
		ServletOutputStream out = res.getOutputStream();
		out.write(content);
		out.close();
		return;
	}
	
	if("delete".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 ***************************************/
		Integer msg_no = Integer.valueOf(req.getParameter("msg_no"));

		/*************************** 2.開始刪除資料 ***************************************/
		SystemNotificationMessageService snmSvc = new SystemNotificationMessageService();
		snmSvc.deleteSNM(msg_no);

		/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
		String url = "/backend/system_notification_message/listSNM.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(req, res);
	}
	}
}
