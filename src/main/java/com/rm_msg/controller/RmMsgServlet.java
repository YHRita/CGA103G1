package com.rm_msg.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.rm_msg.model.Rm_msgService;
import com.rm_msg.model.Rm_msgVO;


@WebServlet("/RmMsgServlet")
public class RmMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//=====================接收請求參數===================================
			if("rmMsgSend".equals(action)) {
				Map<String, String> errorMsg = new LinkedHashMap<>();
				req.setAttribute("errorMsg", errorMsg);
				
				String mem_acc = req.getParameter("mem_acc");
				
				if(mem_acc.isEmpty() || mem_acc.trim().length() == 0) {
					errorMsg.put("mem_acc", "請輸入帳號");
					
				}
				if(!errorMsg.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/frontend/room/rmMsg.jsp");
					failure.forward(req, res);
					return;
				}
				
				String store_no = req.getParameter("store_no");
				if(store_no.isEmpty() || store_no.trim().length() == 0) {
					errorMsg.put("store_no","請選擇");
					
				}
				if(!errorMsg.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/frontend/room/rmMsg.jsp");
					failure.forward(req, res);
					return;
				}
				Integer storeNo = Integer.valueOf(store_no);
				
				String rm_msg_reason = req.getParameter("rm_msg_reason");
				if(rm_msg_reason.isEmpty() || rm_msg_reason.trim().length() == 0) {
					errorMsg.put("rm_msg_reason","請輸入您的問題");
					
				}
				if(!errorMsg.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/frontend/room/rmMsg.jsp");
					failure.forward(req, res);
					return;
				}
				
			//============================資料存取=================================	
				Rm_msgService rmmsgSvc = new Rm_msgService();
				MemVO onermVO = rmmsgSvc.getOneByMemAcc(mem_acc);

				if(onermVO == null) {
					errorMsg.put("mem_acc", "查無此帳號");
				}
				if(!errorMsg.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/frontend/room/rmMsg.jsp");
					failure.forward(req, res);
					return;
				}
				Integer mem_no = onermVO.getMem_no();
				
				rmmsgSvc.addRm_msg(mem_no, storeNo, rm_msg_reason);
				
				
			//==========================轉交資料==============================
				req.setAttribute("onermVO", onermVO);
				String url = "/frontend/room/rmMsgSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}
			
			if("getOneMsgByStore".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				//===========接收參數===================
				
				String store_no = req.getParameter("store_no");
				
				if(store_no == null || store_no.trim().length() == 0) {
					errorMsgs.put("store_no", "請輸入廠商編號");
					
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/backend/store/ListAllMsgs.jsp");
					failure.forward(req, res);
					return;
				}
				
				Integer storeno = Integer.valueOf(store_no);
				
				//=============資料存取==================
				Rm_msgService rmmsgSvc = new Rm_msgService();
				List<Rm_msgVO> list = rmmsgSvc.getAllMsgByStoreNumber(storeno);
			
				//=================轉交資料======================
				req.setAttribute("list", list);
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
			}
			
			if("rmMsgUpdate".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				String storeNO = req.getParameter("storeNO");
				Integer storeno = Integer.valueOf(storeNO);
				
				Rm_msgService rmSvc = new Rm_msgService();
				Rm_msgVO rm_msgVO = rmSvc.getOnByStoreNumber(storeno);
				
				req.setAttribute("rm_msgVO", rm_msgVO);
				String url = "/frontend/store/rmMsgUpdate.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);
				sucessView.forward(req, res);
				
			}
			
			if("rmMsgUpdated".equals(action)) {
				
				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				String rm_msg_no = req.getParameter("rm_msg_no");
				Integer rmmsgno = Integer.valueOf(rm_msg_no);
				
				String emp_no = req.getParameter("emp_no");
				Integer empno = Integer.valueOf(emp_no);
				
				String store_no = req.getParameter("store_no");
				Integer storeno = Integer.valueOf(store_no);
				
				java.sql.Date rmmsgdonetime = null;
				try {
					rmmsgdonetime = java.sql.Date.valueOf(req.getParameter("rm_msg_donetime"));
				}catch(IllegalArgumentException e) {
					rmmsgdonetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("store_reg_date","請輸入日期");
				}
				
				
				Integer rmmsgstatus = Integer.valueOf(req.getParameter("rm_msg_status"));
			
				Rm_msgVO rmmsgVO = new Rm_msgVO();
				rmmsgVO.setRm_msg_no(rmmsgno);
				rmmsgVO.setEmp_no(empno);
				rmmsgVO.setStore_no(storeno);
				rmmsgVO.setRm_msg_donetime(rmmsgdonetime);
				rmmsgVO.setRm_msg_status(rmmsgstatus);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("frontend/store/rmMsgUpdate.jsp");
					failView.forward(req, res);
					
				}
				
				Rm_msgService rmSvc = new Rm_msgService();
				rmmsgVO = rmSvc.updateRm_msg(rmmsgno, empno, storeno, rmmsgstatus, rmmsgdonetime);
				
				req.setAttribute("rmmsgVO", rmmsgVO);
				String url = "/frontend/store/rmMsgList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}
	}
}
