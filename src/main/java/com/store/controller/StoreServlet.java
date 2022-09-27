package com.store.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.email.MailService;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/store.do")
public class StoreServlet extends HttpServlet {
	



	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneStore".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//================== 接收請求參數===========================
			String str = req.getParameter("store_no");
			if(str == null || (str.trim()).length() == 0 ) {
				errorMsgs.put("store_no","請輸入帳號");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer store_no = null;
			try {
				store_no = Integer.valueOf(str);
			}catch(Exception e) {
				errorMsgs.put("store_no","帳號格式不正確");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/StoreSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			//======================存取資料============================
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.getOneStore(store_no);
			
			if(storevo == null) {
				errorMsgs.put("store_no","查無此帳號");;
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//======================轉交資料=============================
			req.setAttribute("storeVO", storevo);
			String url = "/backend/store/storeListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getOneStoreByAcc".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//================== 接收請求參數===========================
			String str = req.getParameter("store_acc");
			if(str == null || (str.trim()).length() == 0 ) {
				errorMsgs.put("store_acc","請輸入帳號");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			String store_acc = null;
			try {
				store_acc = String.valueOf(str);
			}catch(Exception e) {
				errorMsgs.put("store_acc","帳號格式不正確");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/StoreSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			//======================存取資料============================
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.getOneStoreByAcc(store_acc);
			
			if(storevo == null) {
				errorMsgs.put("store_acc","查無此帳號");;
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//======================轉交資料=============================
			req.setAttribute("storeVO", storevo);
			String url = "/backend/store/storeListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("updateStore".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//================== 接收請求參數===========================
			Integer store_no = Integer.valueOf(req.getParameter("store_no"));
			
			
			
			//======================存取資料============================
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.getOneStore(store_no);
			
			
			
			//======================轉交資料=============================
			req.setAttribute("storeVO", storevo);
			String url = "/backend/store/storeUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//========================接收參數==============================
				
			Integer store_no = Integer.valueOf(req.getParameter("store_no").trim());
			//======廠商帳號密碼正規表示=======
			String reg = "^[(a-zA-Z0-9)]{7,20}$";
			//======廠商帳號=======
			
			String store_acc = req.getParameter("store_acc");
			if(store_acc == null|| store_acc.trim().length() == 0) {
				errorMsgs.put("store_acc", "廠商帳號請勿空白");
			}else if(!store_acc.trim().matches(reg)) {
				errorMsgs.put("store_acc", "廠商帳號只能是大小寫英文字母、數字，長度必須在7~20之間");
			}		
			//======廠商密碼=======
			String store_pwd = req.getParameter("store_pwd");
			if(store_pwd == null|| store_pwd.trim().length() == 0) {
				errorMsgs.put("store_pwd", "廠商帳號請勿空白");
			}else if(!store_pwd.trim().matches(reg)) {
				errorMsgs.put("store_pwd", "廠商帳號只能是英文字母、數字，長度必須在7~20之間");
			}
			//======廠商帳號狀態 0, 1, 2=======
			Integer acc_status = Integer.valueOf(req.getParameter("acc_status").trim());
			//======廠商名稱=======
			String store_name = req.getParameter("store_name");
			if(store_name == null|| store_name.trim().length() == 0) {
				errorMsgs.put("store_name", "請輸入名稱");
			}
			//======廠商統編=======
			String guireg = "^[(0-9)]{8}$";
			String store_gui =req.getParameter("store_gui");
			if(store_gui == null|| store_gui.trim().length() == 0) {
				errorMsgs.put("store_gui", "廠商統編請勿空白");
			}else if(!store_gui.trim().matches(guireg)) {
				errorMsgs.put("store_gui", "廠商統編錯誤");
			}
			//==========廠商負責人==========
			String store_rep = req.getParameter("store_rep");
			if(store_rep == null || store_rep.trim().length() == 0) {
				errorMsgs.put("store_rep", "負責人請勿空白");
			}
			
			//===========廠商電話===========
			String store_tel = req.getParameter("store_tel");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "電話請勿空白");
			}else if(!store_tel.trim().matches("^[(0-9)]{9,10}$")) {
				errorMsgs.put("store_tel", "請輸入正確電話號碼");
			}
			//===========廠商傳真===========
			String store_fax = req.getParameter("store_fax");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "傳真請勿空白");
			}else if(!store_tel.trim().matches("^[(0-9)]{9,10}$")) {
				errorMsgs.put("store_tel", "請輸入正確傳真號碼");
			}
			//===========廠商登記地址===========
			String store_add = req.getParameter("store_add");
			if(store_add == null || store_add.trim().length() == 0) {
				errorMsgs.put("store_add", "地址請勿空白");
				}
			//===========廠商聯絡人===========
			String store_poc = req.getParameter("store_poc");
			//===========廠商連絡電話===========
			String store_con_phone = req.getParameter("store_con_phone");
			//===========廠商聯絡地址===========
			String store_con_add = req.getParameter("store_con_add");
			//===========廠商電子信箱===========
			String emailreg = "^[A-Za-z0-9+_.-]+@(.+)$";
			String store_email = req.getParameter("store_email");
			if(store_email == null || store_email.trim().length() == 0) {
				errorMsgs.put("store_email", "信箱請勿空白");
			}else if(!store_email.trim().matches(emailreg)) {
				errorMsgs.put("store_email", "請輸入正確信箱");
			}
			//===========廠商加入時間===========
			java.sql.Date store_reg_date = null;
			try {
			store_reg_date = java.sql.Date.valueOf(req.getParameter("store_reg_date"));
			}catch(IllegalArgumentException e) {
				store_reg_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("store_reg_date","請輸入日期");
			}
			//===========廠商轉帳帳號===========
			String bankreg = "^[(0-9)]{15,20}$";
			
			String bank_account = req.getParameter("bank_account");
			if(bank_account == null || bank_account.trim().length() == 0) {
				errorMsgs.put("bank_account","銀行帳號請勿空白");
				}else if(bank_account.trim().matches(bankreg)) {
					errorMsgs.put("bank_account","請輸入正確轉帳帳號");
				}
			
			//===========廠商票券總分數===========
			
			
			//===========廠商票券總評價數===========

			//===========廠商票券平均評價數===========
			
			//===========廠商訂房總分數===========
			Integer store_rm_rating_score = Integer.valueOf(req.getParameter("store_rm_rating_score"));
			//===========廠商訂房總評價數===========
			Integer store_rm_rating_count = Integer.valueOf(req.getParameter("store_rm_rating_count"));
			//===========廠商活動總分數===========
			
			//===========廠商活動總評價數===========
			
			//===========廠商被檢舉計點===========
			Integer store_report_count = Integer.valueOf(req.getParameter("store_report_count"));
		
			StoreVO storeVO = new StoreVO();
			storeVO.setStore_no(store_no);
			storeVO.setStore_acc(store_acc);
			storeVO.setStore_pwd(store_pwd);
			storeVO.setAcc_status(acc_status);
			storeVO.setStore_name(store_name);
			storeVO.setStore_gui(store_gui);
			storeVO.setStore_rep(store_rep);
			storeVO.setStore_tel(store_tel);
			storeVO.setStore_fax(store_fax);
			storeVO.setStore_add(store_add);
			storeVO.setStore_poc(store_poc);
			storeVO.setStore_con_phone(store_con_phone);
			storeVO.setStore_con_add(store_con_add);
			storeVO.setStore_email(store_email);
			storeVO.setStore_reg_date(store_reg_date);
			storeVO.setBank_account(bank_account);
			storeVO.setStore_rm_rating_count(store_rm_rating_count);
			storeVO.setStore_rm_rating_score(store_rm_rating_score);
			storeVO.setStore_report_count(store_report_count);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("storeVO", storeVO);
				String url = "/backend/store/storeUpdate.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			
			//=====================資料存取修改資料==============================
			
			StoreService storeSvc = new StoreService();
			storeVO = storeSvc.updateStore(store_no, store_acc, store_pwd, acc_status, store_name, store_gui, store_rep, store_tel, store_fax, store_add, store_poc, store_con_phone, store_con_add, store_email, store_reg_date, bank_account, store_rm_rating_score, store_rm_rating_count, store_report_count);
			
			//=========================轉交資料================================
			req.setAttribute("storeVO", storeVO);
			String url = "/backend/store/storeListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		
		
		}
		
		
		if ("listUpdateStore".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//================== 接收請求參數===========================
			Integer store_no = Integer.valueOf(req.getParameter("store_no"));
			
			
			
			//======================存取資料============================
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.getOneStore(store_no);
			
			
			
			//======================轉交資料=============================
			req.setAttribute("storeVO", storevo);
			String url = "/frontend/store/storeUpdateFrontend.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
		
		if("updatefrontstore".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//========================接收參數==============================
				
			Integer store_no = Integer.valueOf(req.getParameter("store_no").trim());
			//======廠商帳號密碼正規表示=======
			String reg = "^[(a-zA-Z0-9)]{7,20}$";
			//======廠商帳號=======
			
			String store_acc = req.getParameter("store_acc");
			if(store_acc == null|| store_acc.trim().length() == 0) {
				errorMsgs.put("store_acc", "廠商帳號請勿空白");
			}else if(!store_acc.trim().matches(reg)) {
				errorMsgs.put("store_acc", "廠商帳號只能是大小寫英文字母、數字，長度必須在7~20之間");
			}		
			//======廠商密碼=======
			String store_pwd = req.getParameter("store_pwd");
			if(store_pwd == null|| store_pwd.trim().length() == 0) {
				errorMsgs.put("store_pwd", "廠商密碼請勿空白");
			}else if(!store_pwd.trim().matches(reg)) {
				errorMsgs.put("store_pwd", "廠商密碼只能是英文字母、數字，長度必須在7~20之間");
			}
			//======廠商帳號狀態 0, 1, 2=======
			Integer acc_status = Integer.valueOf(req.getParameter("acc_status").trim());
			//======廠商名稱=======
			String store_name = req.getParameter("store_name");
			if(store_name == null|| store_name.trim().length() == 0) {
				errorMsgs.put("store_name", "請輸入名稱");
			}
			//======廠商統編=======
			String guireg = "^[(0-9)]{8}$";
			String store_gui =req.getParameter("store_gui");
			if(store_gui == null|| store_gui.trim().length() == 0) {
				errorMsgs.put("store_gui", "廠商統編請勿空白");
			}else if(!store_gui.trim().matches(guireg)) {
				errorMsgs.put("store_gui", "廠商統編錯誤");
			}
			//==========廠商負責人==========
			String store_rep = req.getParameter("store_rep");
			if(store_rep == null || store_rep.trim().length() == 0) {
				errorMsgs.put("store_rep", "負責人請勿空白");
			}
			
			//===========廠商電話===========
			String store_tel = req.getParameter("store_tel");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "電話請勿空白");
			}else if(!store_tel.trim().matches("^[(0-9)]{9,10}$")) {
				errorMsgs.put("store_tel", "請輸入正確電話號碼");
			}
			//===========廠商傳真===========
			String store_fax = req.getParameter("store_fax");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "傳真請勿空白");
			}else if(!store_tel.trim().matches("^[(0-9)]{9,10}$")) {
				errorMsgs.put("store_tel", "請輸入正確傳真號碼");
			}
			//===========廠商登記地址===========
			String store_add = req.getParameter("store_add");
			if(store_add == null || store_add.trim().length() == 0) {
				errorMsgs.put("store_add", "地址請勿空白");
				}
			//===========廠商聯絡人===========
			String store_poc = req.getParameter("store_poc");
			//===========廠商連絡電話===========
			String store_con_phone = req.getParameter("store_con_phone");
			//===========廠商聯絡地址===========
			String store_con_add = req.getParameter("store_con_add");
			//===========廠商電子信箱===========
			String emailreg = "^[A-Za-z0-9+_.-]+@(.+)$";
			String store_email = req.getParameter("store_email");
			if(store_email == null || store_email.trim().length() == 0) {
				errorMsgs.put("store_email", "信箱請勿空白");
			}else if(!store_email.trim().matches(emailreg)) {
				errorMsgs.put("store_email", "請輸入正確信箱");
			}
			//===========廠商加入時間===========
			java.sql.Date store_reg_date = null;
			try {
			store_reg_date = java.sql.Date.valueOf(req.getParameter("store_reg_date"));
			}catch(IllegalArgumentException e) {
				store_reg_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("store_reg_date","請輸入日期");
			}
			//===========廠商轉帳帳號===========
			String bankreg = "^[(0-9)]{8,14}$";
			
			String bank_account = req.getParameter("bank_account");
			if(bank_account == null || bank_account.trim().length() == 0) {
				errorMsgs.put("bank_account","銀行帳號請勿空白");
				}else if(!bank_account.trim().matches(bankreg)) {
					errorMsgs.put("bank_account","請輸入正確轉帳帳號");
				}
			
			//===========廠商票券總分數===========
			
			
			//===========廠商票券總評價數===========
		
			//===========廠商票券平均評價數===========

			//===========廠商訂房總分數===========
			Integer store_rm_rating_score = Integer.valueOf(req.getParameter("store_rm_rating_score"));
			//===========廠商訂房總評價數===========
			Integer store_rm_rating_count = Integer.valueOf(req.getParameter("store_rm_rating_count"));
			//===========廠商活動總分數===========
			
			//===========廠商活動總評價數===========
			
			//===========廠商被檢舉計點===========
			Integer store_report_count = Integer.valueOf(req.getParameter("store_report_count"));
		
			StoreVO storeVO = new StoreVO();
			storeVO.setStore_no(store_no);
			storeVO.setStore_acc(store_acc);
			storeVO.setStore_pwd(store_pwd);
			storeVO.setAcc_status(acc_status);
			storeVO.setStore_name(store_name);
			storeVO.setStore_gui(store_gui);
			storeVO.setStore_rep(store_rep);
			storeVO.setStore_tel(store_tel);
			storeVO.setStore_fax(store_fax);
			storeVO.setStore_add(store_add);
			storeVO.setStore_poc(store_poc);
			storeVO.setStore_con_phone(store_con_phone);
			storeVO.setStore_con_add(store_con_add);
			storeVO.setStore_email(store_email);
			storeVO.setStore_reg_date(store_reg_date);
			storeVO.setBank_account(bank_account);
			storeVO.setStore_rm_rating_count(store_rm_rating_count);
			storeVO.setStore_rm_rating_score(store_rm_rating_score);
			storeVO.setStore_report_count(store_report_count);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("storeVO", storeVO);
				String url = "/frontend/store/storeUpdateFrontend.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			
			//=====================資料存取修改資料==============================
			
			StoreService storeSvc = new StoreService();
			storeVO = storeSvc.updateStore(store_no, store_acc, store_pwd, acc_status, store_name, store_gui, store_rep, store_tel, store_fax, store_add, store_poc, store_con_phone, store_con_add, store_email, store_reg_date, bank_account, store_rm_rating_score, store_rm_rating_count, store_report_count);
			
			//=========================轉交資料================================
			req.setAttribute("storeVO", storeVO);
			String url = "/frontend/store/storeListOwn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		
		
		}
		
		if("backendUpdateStore".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//========================接收參數==============================
				
			Integer store_no = Integer.valueOf(req.getParameter("store_no").trim());
			//======廠商帳號密碼正規表示=======
			String reg = "^[(a-zA-Z0-9)]{7,20}$";
			//======廠商帳號=======
			
			String store_acc = req.getParameter("store_acc");
			if(store_acc == null|| store_acc.trim().length() == 0) {
				errorMsgs.put("store_acc", "廠商帳號請勿空白");
			}else if(!store_acc.trim().matches(reg)) {
				errorMsgs.put("store_acc", "廠商帳號只能是大小寫英文字母、數字，長度必須在7~20之間");
			}		
			//======廠商密碼=======
			String store_pwd = req.getParameter("store_pwd");
			if(store_pwd == null|| store_pwd.trim().length() == 0) {
				errorMsgs.put("store_pwd", "廠商帳號請勿空白");
			}else if(!store_pwd.trim().matches(reg)) {
				errorMsgs.put("store_pwd", "廠商帳號只能是英文字母、數字，長度必須在7~20之間");
			}
			//======廠商帳號狀態 0, 1, 2=======
			Integer acc_status = Integer.valueOf(req.getParameter("acc_status").trim());
			//======廠商名稱=======
			String store_name = req.getParameter("store_name");
			if(store_name == null|| store_name.trim().length() == 0) {
				errorMsgs.put("store_name", "請輸入名稱");
			}
			//======廠商統編=======
			String guireg = "^[(0-9)]{8}$";
			String store_gui =req.getParameter("store_gui");
			if(store_gui == null|| store_gui.trim().length() == 0) {
				errorMsgs.put("store_gui", "廠商統編請勿空白");
			}else if(!store_gui.trim().matches(guireg)) {
				errorMsgs.put("store_gui", "廠商統編錯誤");
			}
			//==========廠商負責人==========
			String store_rep = req.getParameter("store_rep");
			if(store_rep == null || store_rep.trim().length() == 0) {
				errorMsgs.put("store_rep", "負責人請勿空白");
			}
			
			//===========廠商電話===========
			String store_tel = req.getParameter("store_tel");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "電話請勿空白");
			}else if(!store_tel.trim().matches("^[(0-9)]{9,10}$")) {
				errorMsgs.put("store_tel", "請輸入正確電話號碼");
			}
			//===========廠商傳真===========
			String store_fax = req.getParameter("store_fax");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "傳真請勿空白");
			}else if(!store_tel.trim().matches("^[(0-9)]{9,10}$")) {
				errorMsgs.put("store_tel", "請輸入正確傳真號碼");
			}
			//===========廠商登記地址===========
			String store_add = req.getParameter("store_add");
			if(store_add == null || store_add.trim().length() == 0) {
				errorMsgs.put("store_add", "地址請勿空白");
				}
			//===========廠商聯絡人===========
			String store_poc = req.getParameter("store_poc");
			//===========廠商連絡電話===========
			String store_con_phone = req.getParameter("store_con_phone");
			//===========廠商聯絡地址===========
			String store_con_add = req.getParameter("store_con_add");
			//===========廠商電子信箱===========
			String emailreg = "^[A-Za-z0-9+_.-]+@(.+)$";
			String store_email = req.getParameter("store_email");
			if(store_email == null || store_email.trim().length() == 0) {
				errorMsgs.put("store_email", "信箱請勿空白");
			}else if(!store_email.trim().matches(emailreg)) {
				errorMsgs.put("store_email", "請輸入正確信箱");
			}
			//===========廠商加入時間===========
			java.sql.Date store_reg_date = null;
			try {
			store_reg_date = java.sql.Date.valueOf(req.getParameter("store_reg_date"));
			}catch(IllegalArgumentException e) {
				store_reg_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("store_reg_date","請輸入日期");
			}
			//===========廠商轉帳帳號===========
			String bankreg = "^[(0-9)]{15,20}$";
			
			String bank_account = req.getParameter("bank_account");
			if(bank_account == null || bank_account.trim().length() == 0) {
				errorMsgs.put("bank_account","銀行帳號請勿空白");
				}else if(bank_account.trim().matches(bankreg)) {
					errorMsgs.put("bank_account","請輸入正確轉帳帳號");
				}
			
			//===========廠商票券總分數===========
			
			
			//===========廠商票券總評價數===========
		
			//===========廠商票券平均評價數===========

			//===========廠商訂房總分數===========
			Integer store_rm_rating_score = Integer.valueOf(req.getParameter("store_rm_rating_score"));
			//===========廠商訂房總評價數===========
			Integer store_rm_rating_count = Integer.valueOf(req.getParameter("store_rm_rating_count"));
			//===========廠商活動總分數===========
			
			//===========廠商活動總評價數===========
			
			//===========廠商被檢舉計點===========
			Integer store_report_count = Integer.valueOf(req.getParameter("store_report_count"));
		
			StoreVO storeVO = new StoreVO();
			storeVO.setStore_no(store_no);
			storeVO.setStore_acc(store_acc);
			storeVO.setStore_pwd(store_pwd);
			storeVO.setAcc_status(acc_status);
			storeVO.setStore_name(store_name);
			storeVO.setStore_gui(store_gui);
			storeVO.setStore_rep(store_rep);
			storeVO.setStore_tel(store_tel);
			storeVO.setStore_fax(store_fax);
			storeVO.setStore_add(store_add);
			storeVO.setStore_poc(store_poc);
			storeVO.setStore_con_phone(store_con_phone);
			storeVO.setStore_con_add(store_con_add);
			storeVO.setStore_email(store_email);
			storeVO.setStore_reg_date(store_reg_date);
			storeVO.setBank_account(bank_account);
			storeVO.setStore_rm_rating_count(store_rm_rating_count);
			storeVO.setStore_rm_rating_score(store_rm_rating_score);
			storeVO.setStore_report_count(store_report_count);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("storeVO", storeVO);
				String url = "/backend/store/storeUpdate.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			
			//=====================資料存取修改資料==============================
			
			StoreService storeSvc = new StoreService();
			storeVO = storeSvc.updateStore(store_no, store_acc, store_pwd, acc_status, store_name, store_gui, store_rep, store_tel, store_fax, store_add, store_poc, store_con_phone, store_con_add, store_email, store_reg_date, bank_account, store_rm_rating_score, store_rm_rating_count, store_report_count);
			
			//=========================轉交資料================================
			req.setAttribute("storeVO", storeVO);
			String url = "/backend/store/storeListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		
		
		}
		
		
		
		if("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			StoreService storeSvc = new StoreService();
			List<StoreVO> allStores = storeSvc.getAllStore();
			
			//========================接收請求參數===========================
			
			//廠商帳號
			String store_acc = req.getParameter("store_acc");
			for(StoreVO allStore : allStores) {
				if(allStore.getStore_acc().equals(store_acc)) {
					errorMsgs.put("store_acc", "帳號重複請重新輸入");
					
				}
			}
			//帳號密碼正規表示
			String reg = "^[(a-zA-Z0-9)]{7,20}$";
			if(store_acc == null|| store_acc.trim().length() == 0) {
				errorMsgs.put("store_acc", "廠商帳號請勿空白");
			}else if(!store_acc.trim().matches(reg)) {
				errorMsgs.put("store_acc", "廠商帳號只能是大小寫英文字母、數字，長度必須在7~20之間");
			}
			//密碼
			String store_pwd = req.getParameter("store_pwd");
			if(store_pwd == null || store_pwd.trim().length() == 0) {
				errorMsgs.put("store_pwd", "請輸入密碼");
			}else if(!store_pwd.trim().matches(reg)) {
				errorMsgs.put("store_pwd", "密碼只能是大小寫英文字母、數字，長度必須在7~20之間");
			}
		    //店家名稱
			String store_name = req.getParameter("store_name");
			if(store_name == null || store_name.trim().length() == 0) {
				errorMsgs.put("store_name", "請輸入您的名稱");
			}
			//統編
			String store_gui = req.getParameter("store_gui");
			String guireg = "^[(0-9)]{8}$";
			if(store_gui == null || store_gui.trim().length() == 0) {
				errorMsgs.put("store_gui", "請輸入您的統一編號");
			}else if(!store_gui.trim().matches(guireg)) {
				errorMsgs.put("store_gui", "統一編號只能是8位數的數字");
			}
			//負責人
			String store_rep = req.getParameter("store_rep");
			if(store_rep == null || store_rep.trim().length() == 0) {
				errorMsgs.put("store_rep", "請輸入負責人");
			}
			//電話
			String telreg = "^[(0-9)]{8,10}$";
			String store_tel = req.getParameter("store_tel");
			if(store_tel == null || store_tel.trim().length() == 0) {
				errorMsgs.put("store_tel", "請輸入電話號碼");
			}else if(!store_tel.trim().matches(telreg)) {
				errorMsgs.put("store_tel", "電話請輸入數字，需包含區碼");
			}
			//傳真
			String faxreg = "^[(0-9)]{8,10}$";
			String store_fax = req.getParameter("store_fax");
			if(store_fax == null || store_fax.trim().length() == 0) {
				errorMsgs.put("store_fax", "請輸入傳真號碼");
			}else if(!store_fax.trim().matches(faxreg)) {
				errorMsgs.put("store_fax", "傳真請輸入數字，需包含區碼");
			}
			//登記地址
//			String store_add = req.getParameter("store_add");
//			if(store_add == null || store_add.trim().length()==0) {
//				errorMsgs.put("store_add", "請輸入登記地址");
//			}
			
			System.out.println(req.getParameter("county"));
			System.out.println(req.getParameter("district"));
//			System.out.println(req.getParameter("zipcode"));
			
			String store_add_de = req.getParameter("store_add_de");
			String county = req.getParameter("county");
			String district = req.getParameter("district");
//			String zipcode = req.getParameter("zipcode");
			
			StringBuffer sb =new StringBuffer();
			
			sb.append(county);
			sb.append(district);
//			sb.append(zipcode);
			sb.append(store_add_de);
			
			String store_add = sb.toString();
			if(store_add == null || store_add.length()==0) {
				errorMsgs.put("store_add", "請輸入登記地址");
			}
			
			
			//連絡電話
			String store_con_phone = req.getParameter("store_con_phone");
			//聯絡地址
			String store_con_add = req.getParameter("store_con_add");
			//信箱
			
			String emailreg = "^[A-Za-z0-9+_.-]+@(.+)$";
			String store_email = req.getParameter("store_email");
			if(store_email == null|| store_email.trim().length() == 0) {
				errorMsgs.put("store_email", "請輸入電子信箱");
			}else if(!store_email.trim().matches(emailreg)) {
				errorMsgs.put("store_email", "請輸入正確的格式");
			}
			//加入時間
//			java.sql.Date store_reg_date = null;
//				try {	
//			store_reg_date = java.sql.Date.valueOf(req.getParameter("store_reg_date"));
//				}catch(IllegalArgumentException e) {
//					store_reg_date = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.put("store_reg_date","請輸入日期");
//				}
			//轉帳帳號
			String bank_account = req.getParameter("bank_account");
			String bankreg = "^[(0-9)]{10,20}$";
			if(bank_account == null || bank_account.trim().length() == 0) {
				errorMsgs.put("bank_account", "請輸入轉帳帳號");
				
			}else if(!bank_account.trim().matches(bankreg)) {
				errorMsgs.put("bank_account", "轉帳帳號只能是數字");
			}
			
			StoreVO storeVO = new StoreVO();
			storeVO.setStore_acc(store_acc);
			storeVO.setStore_pwd(store_pwd);
			storeVO.setStore_name(store_name);
			storeVO.setStore_gui(store_gui);
			storeVO.setStore_rep(store_rep);
			storeVO.setStore_tel(store_tel);
			storeVO.setStore_fax(store_fax);
			storeVO.setStore_add(store_add);
			storeVO.setStore_con_phone(store_con_phone);
			storeVO.setStore_con_add(store_con_add);
			storeVO.setStore_email(store_email);
//			storeVO.setStore_reg_date(store_reg_date);
			storeVO.setBank_account(bank_account);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("storeVO", storeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/store/StoreSignUp.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			//===================資料存取===========================
			
			
			storeSvc = new StoreService();
			storeVO = storeSvc.addStore(store_acc, store_pwd, store_name, store_gui, store_rep, store_tel, store_fax, store_add, store_pwd, store_con_phone, store_con_add, store_email, bank_account);
			
			
			//===================資料轉交===========================
			req.setAttribute("storeVO", storeVO);
			String url = "/frontend/store/SignUpSucWin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if("logOut".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/frontend/store/logOutWin.jsp";
			RequestDispatcher ret = req.getRequestDispatcher(url);
			ret.forward(req, res);
			
			
		}
		
		if("listStoreByCompositeQuery".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			//===========接收資料=================
			Map<String, String[]> map = req.getParameterMap();
			//=================資料存取==================
			StoreService StoreSvc = new StoreService();
			List<StoreVO> list = StoreSvc.StoreCompositeQuery(map);
			if(list.isEmpty()) {
				errorMsgs.put("map", "查無資料，請重新輸入");
				
			}
			if(!errorMsgs.isEmpty()){
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
			}
			//==============資料送出============================
			req.setAttribute("listStoreByCompositeQuery", list);
			RequestDispatcher SuccessView = req.getRequestDispatcher("/backend/store/StoreCompositeQueryList.jsp");
			SuccessView.forward(req, res);
			
		}
		
		if("forgetPassword".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			String store_email = req.getParameter("store_email");
			StoreService storeSvc = new StoreService();
			if(storeSvc.getOneStoreByEmail(store_email) == null) {
				errorMsgs.put("store_email", "此信箱沒有註冊請重新輸入");
			}
//			List<StoreVO> lists = storeSvc.getAllStore();
//			
//			for(StoreVO list : lists) {
//				if(list.getStore_email() != store_email) {
//					errorMsgs.put("store_email", "此信箱沒有註冊請重新輸入");
//				}break;	
//			}
			if(!errorMsgs.isEmpty()){
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/store/forgetPassword.jsp");
				failureView.forward(req, res);
				return;
			}
		
			
			
		StoreVO	storeVO = storeSvc.getOneStoreByEmail(store_email);
		MailService mailSvc = new MailService();
		String randompass = mailSvc.getRandom();
		storeSvc.updatePassword(randompass, storeVO.getStore_no());
		
		String Main = "密碼更換";
		
		String subMain = "請輸入以下密碼 "+ randompass +" 登入後請馬上更新您的密碼";
		
//		try {
		mailSvc.sendMail(store_email, Main, subMain);
		
		RequestDispatcher successView = req.getRequestDispatcher("/frontend/store/newPasswordSendSuc.jsp");
		successView.forward(req, res);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
			


	
		}
	}
}