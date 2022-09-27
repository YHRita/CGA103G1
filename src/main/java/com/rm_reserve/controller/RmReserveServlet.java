package com.rm_reserve.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rm_order.model.*;
import com.rm_order_list.model.*;
import com.rm_reserve.model.RmReserveService;
import com.rm_type.model.*;

@WebServlet("/RmRsv/RmRsv.do")
public class RmReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 從首頁搜尋，房型列表顯示符合的結果
		if ("getEnoughType".equals(action)) {

			// 處理錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			String arrival = req.getParameter("arrival_date");
			if (arrival == null) {
				errorMsgs.add("請選擇住宿日期");
			}
			String departure = req.getParameter("departure_date");
			if (departure == null) {
				errorMsgs.add("請選擇退房日期");
			}

			String add = req.getParameter("add");
			String addReg = "^[(\u4e00-\u9fa5)]{2,10}$";
			if (add == null || add.trim().length() == 0) {
				errorMsgs.add("住宿地區請勿空白");
			} else if (!add.trim().matches(addReg)) {
				errorMsgs.add("地區只能是中文 ,且長度須在2到10個字之間");
			}

			Integer qty = Integer.valueOf(req.getParameter("roomtotal"));
			if (qty == null) {
				errorMsgs.add("請選擇房間數量");
			}
			Integer guest = Integer.valueOf(req.getParameter("people"));
			if (guest == null) {
				errorMsgs.add("請選擇入住人數");
			}

		
			// 日期格式處理
			List<String> dateList = new LinkedList<String>();
			dateList = Arrays.asList(arrival.split("-"));
			String arrival_date = dateList.get(2) + "-" + dateList.get(1) + "-" + dateList.get(0);

			List<String> dateList1 = new LinkedList<String>();
			dateList1 = Arrays.asList(departure.split("-"));
			String departure_date = dateList1.get(2) + "-" + dateList1.get(1) + "-" + dateList1.get(0);

//			String rangedate = req.getParameter("rangedate");
//			//將住宿期間分成入住日跟退房日
//			List <String> dateList = new LinkedList<String>();
//			dateList =Arrays.asList(rangedate.split(" to "));
//			String arrival_date = dateList.get(0);
//			String departure_date = dateList.get(1);

			/*************************** 2.開始查詢資料 ****************************************/
			RmTypeService rmTypeSvc = new RmTypeService();
			// 符合條件的
			List<RmTypeVO> ableList = rmTypeSvc.getEnoughType(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), qty, guest, add);
			// 不符合條件的
			List<RmTypeVO> notList = rmTypeSvc.getNotEnoughType(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), qty, guest);

//			if(notList == null) {
//				errorMsgs.add("請確認所輸入的資料是否正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/listAllRmType.jsp");	//查無資料返回登入
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
//			session.setAttribute("rangedate", rangedate);
			session.setAttribute("arrival_date", arrival_date);
			session.setAttribute("departure_date", departure_date);
			session.setAttribute("qty", qty);
			session.setAttribute("guest", guest); // 人數只有搜尋時會用到，選完房型改記rm_type_no
			session.setAttribute("ableList", ableList);
			session.setAttribute("notList", notList);
			String url = "/frontend/room/rmList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的rmList.jsp
			successView.forward(req, res);
		}

		if ("getEnoughTypeByStore".equals(action)) {

			/*************************** 1.接收請求參數 ****************************************/
			Integer store_no = Integer.valueOf(req.getParameter("store_no"));
			String rangedate = req.getParameter("rangedate");
			// 將住宿期間分成入住日跟退房日
			List<String> dateList = new LinkedList<String>();
			dateList = Arrays.asList(rangedate.split(" to "));
			String arrival_date = dateList.get(0);
			String departure_date = dateList.get(1);

			/*************************** 2.開始查詢資料 ****************************************/
			RmTypeService rmTypeSvc = new RmTypeService();
			// 符合條件的
			List<RmTypeVO> ableList = rmTypeSvc.getEnoughTypeByStore(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), store_no);

			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
			session.setAttribute("arrival_date", arrival_date);
			session.setAttribute("departure_date", departure_date);
			session.setAttribute("store_no", store_no);
			session.setAttribute("ableList", ableList);
			String url = "/frontend/room/rmWork.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的rmList.jsp
			successView.forward(req, res);
		}

		// 到結帳頁
//		if ("payment".equals(action)) {
//
//			// 處理錯誤訊息
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			/*************************** 1.接收請求參數 ****************************************/
//			try {
//				String arrival = req.getParameter("arrival_date");
//				if (arrival == null) {
//					errorMsgs.add("請選擇住宿日期");
//				}
//
//				String departure = req.getParameter("departure_date");
//				if (departure == null) {
//					errorMsgs.add("請選擇退房日期");
//				}
//
//				Integer qty = Integer.valueOf(req.getParameter("qty"));
//				if (qty == null) {
//					errorMsgs.add("請選擇房間數量");
//				}
//
//				Integer guest = Integer.valueOf(req.getParameter("guest"));
//				if (guest == null) {
//					errorMsgs.add("請選擇入住人數");
//				}
//				Integer rm_type_no = Integer.valueOf(req.getParameter("rm_type_no"));
//
//				// 日期格式處理
//				List<String> dateList = new LinkedList<String>();
//				dateList = Arrays.asList(arrival.split("-"));
//				String arrival_date = dateList.get(2) + "-" + dateList.get(1) + "-" + dateList.get(0);
//
//				List<String> dateList1 = new LinkedList<String>();
//				dateList1 = Arrays.asList(departure.split("-"));
//				String departure_date = dateList1.get(2) + "-" + dateList1.get(1) + "-" + dateList1.get(0);
//
//				java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
//				java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(departure);
//				long from = startDate.getTime();
//				long to = endDate.getTime();
//				int days = (int) ((to - from) / (1000 * 60 * 60 * 24));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				RmTypeService rmTypeSvc = new RmTypeService();
//				// 符合條件的
//
//				HttpSession session = req.getSession();
////				session.setAttribute("rangedate", rangedate);
//				session.setAttribute("arrival_date", arrival_date);
//				session.setAttribute("departure_date", departure_date);
//				session.setAttribute("qty", qty);
//				session.setAttribute("guest", guest); // 人數只有搜尋時會用到，選完房型改記rm_type_no
//
//				req.setAttribute("rm_type_no", rm_type_no);
//				req.setAttribute("arrival_date", arrival_date);
//				req.setAttribute("departure_date", departure_date);
//				req.setAttribute("qty", qty);
//				req.setAttribute("days", days); // 只有結帳頁會用到
//
//				String url = "/frontend/room/payment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的rmList.jsp
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}

		// 產生訂單
		if ("paying".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no"));
				Integer store_no = Integer.valueOf(req.getParameter("store_no"));
				Integer rm_type_no = Integer.valueOf(req.getParameter("rm_type_no"));
				// Integer rm_charge =Integer.valueOf(req.getParameter("rm_charge"));
				// Integer rm_review =Integer.valueOf(req.getParameter("rm_review")); 新增不用評價
				Integer rm_price = Integer.valueOf(req.getParameter("rm_price"));
				String arrival = req.getParameter("arrival_date");
				String departure = req.getParameter("departure_date");
				Integer qty = Integer.valueOf(req.getParameter("qty"));
				Integer days = Integer.valueOf(req.getParameter("days"));

				RmOrderListService ordListSvc = new RmOrderListService();
				RmTypeVO rmVO = new RmTypeVO();
				RmOrderService rmOrderSvc = new RmOrderService();
				Integer rm_charge = rm_price * qty * days; // 總價 單價*間數*天數
				// 日期格式處理
				List<String> dateList = new LinkedList<String>();
				dateList = Arrays.asList(arrival.split("-"));
				String arrival_date = dateList.get(2) + "-" + dateList.get(1) + "-" + dateList.get(0);

				List<String> dateList1 = new LinkedList<String>();
				dateList1 = Arrays.asList(departure.split("-"));
				String departure_date = dateList1.get(2) + "-" + dateList1.get(1) + "-" + dateList1.get(0);

				// 新增明細資料
				RmOrderDAO irm = new RmOrderDAO();
				List<RmOrderListVO> List = new ArrayList<RmOrderListVO>(); // 準備置入訂單明細數筆
				RmOrderListVO ro = new RmOrderListVO(); // 訂單明細POJO1 如有多筆明細改成while(next()){...}

				ro.setRm_type_no(rm_type_no);
				ro.setRm_amount(qty);
				ro.setRm_price(rm_price);
				ro.setArrival_date(java.sql.Date.valueOf(arrival_date));
				ro.setDeparture_date(java.sql.Date.valueOf(departure_date));
				ro.setRm_check_in("朱小妹"); // payment 頁面 需添加入住人參數
				List.add(ro);

				// 新增訂單資料
				Integer rm_order_no = rmOrderSvc.insertWithLists(mem_no, store_no, 1, rm_charge, List);

				// 添加預約表房間數量
				RmReserveService rmReserveSvc = new RmReserveService();
				rmReserveSvc.reserveRmRsv(qty, rm_type_no, java.sql.Date.valueOf(arrival_date),
						java.sql.Date.valueOf(departure_date));

				// 查詢訂單資料
				rmOrderSvc.getAllByMem(mem_no);

				HttpSession session = req.getSession();
				session.removeAttribute("arrival_date");
				session.removeAttribute("departure_date");
				session.removeAttribute("qty");
				session.removeAttribute("guest");
				session.removeAttribute("ableList");
				session.removeAttribute("notList");

				/*** 3.查詢完成,準備轉交 ***/

				req.setAttribute("days", days);
				req.setAttribute("rm_type_no", rm_type_no);
				req.setAttribute("mem_no", mem_no);
				req.setAttribute("rm_order_no", rm_order_no);
				String url = "/frontend/room/confirmation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的結帳完成頁
				successView.forward(req, res);

				/*** 1.接收請求參數 - 輸入格式的錯誤處理 ***/
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/rmList.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
