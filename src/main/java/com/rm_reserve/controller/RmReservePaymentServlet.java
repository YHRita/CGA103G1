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

@WebServlet(name= "RmReservePaymentServlet", urlPatterns = { "/RmRsv/RmRsvPayment.do" })
public class RmReservePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		 req.setCharacterEncoding("UTF-8"); 
		  res.setContentType("text/plain; charset = UTF-8");
		
		  HttpSession session = req.getSession();
		  
		 
		  
		// 到結帳頁

			// 處理錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
		
		
			try {
				String arrival = (String) session.getAttribute("arrival_date");
				if (arrival == null) {
					errorMsgs.add("請選擇住宿日期");
				}
				String departure = (String) session.getAttribute("departure_date");
				if (departure == null) {
					errorMsgs.add("請選擇退房日期");
				}
				Integer qty =  (Integer) session.getAttribute("qty");
				if (qty == null) {
					errorMsgs.add("請選擇房間數量");
				}

				Integer guest = (Integer) session.getAttribute("guest");
				if (guest == null) {
					errorMsgs.add("請選擇入住人數");
				}
				Integer rm_type_no = (Integer) session.getAttribute("rm_type_no");

				// 日期格式處理
				List<String> dateList = new LinkedList<String>();
				dateList = Arrays.asList(arrival.split("-"));
				String arrival_date = dateList.get(2) + "-" + dateList.get(1) + "-" + dateList.get(0);

				List<String> dateList1 = new LinkedList<String>();
				dateList1 = Arrays.asList(departure.split("-"));
				String departure_date = dateList1.get(2) + "-" + dateList1.get(1) + "-" + dateList1.get(0);

				java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
				java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(departure);
				long from = startDate.getTime();
				long to = endDate.getTime();
				int days = (int) ((to - from) / (1000 * 60 * 60 * 24));

				/*************************** 2.開始查詢資料 ****************************************/
				RmTypeService rmTypeSvc = new RmTypeService();
				// 符合條件的

////				HttpSession session = req.getSession();
////				session.setAttribute("rangedate", rangedate);
//				session.setAttribute("arrival_date", arrival_date);
//				session.setAttribute("departure_date", departure_date);
//				session.setAttribute("qty", qty);
//				session.setAttribute("guest", guest); // 人數只有搜尋時會用到，選完房型改記rm_type_no
//
				req.setAttribute("rm_type_no", rm_type_no);
				req.setAttribute("arrival_date", arrival_date);
				req.setAttribute("departure_date", departure_date);
				req.setAttribute("qty", qty);
				req.setAttribute("days", days); // 只有結帳頁會用到

				String url = "/frontend/room/payment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}

	
		

	}

}
