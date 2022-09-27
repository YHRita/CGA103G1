package com.rm_order.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rm_order.model.*;
import com.rm_order_list.model.*;
import com.rm_reserve.model.RmReserveService;

@WebServlet("/RmOrder")
public class RmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAllRmOrder".equals(action)) { // 取得該廠商所有訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				/*************************** 開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getAllByStore(store_no);

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/store/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/store/listStoreOrder.jsp").forward(req, res);
			}
		}
		if ("getAllMemRmOrder".equals(action)) { // 取得該會員所有訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				/*************************** 開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getAllByStore(store_no);
				
				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/mem/listMemOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/mem/listMemOrder.jsp").forward(req, res);
			}
		}

		if ("getStoreStatus".equals(action)) { // 取得該廠商訂單狀態

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				Integer rm_order_status = new Integer(req.getParameter("rm_order_status"));

				/*************************** 2.開始查詢資料 ****************************************/
				// 取得廠商訂單資料(不包含已取消訂單)
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = 
						rmOrderSvc.getStoreStatus(store_no, rm_order_status)
						.stream().filter(t -> t.getRm_charge()!=0).toList();
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/store/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/store/listStoreOrder.jsp").forward(req, res);
			}
		}
		
		if ("getStoreStatusCancel".equals(action)) { // 取得該廠商訂單狀態
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				Integer rm_order_status = new Integer(req.getParameter("rm_order_status"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				// 取得廠商 已取消訂單資料
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = 
						rmOrderSvc.getStoreStatus(store_no, rm_order_status)
						.stream().filter(t -> t.getRm_charge()==0).toList();
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/store/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/store/listStoreOrder.jsp").forward(req, res);
			}
		}
		
		if ("getOneStore".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				Integer store_no = new Integer(req.getParameter("store_no"));
				RmOrderService rmOrderSvc = new RmOrderService();
				if (rmOrderSvc.getOne(rm_order_no)==null) {
					errorMsgs.add("查無此訂單編號");
				}
				Integer exp = rmOrderSvc.getOne(rm_order_no).getStore_no();
				
				if (!exp.equals(store_no)) {
					errorMsgs.add("查無此訂單編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/store/listStoreOrder.jsp");	//查無資料返回登入
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ****************************************/
				List<RmOrderVO> orderlist = new ArrayList<RmOrderVO>();
				orderlist.add(rmOrderSvc.getOne(rm_order_no));
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/store/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/store/listStoreOrder.jsp").forward(req, res);
			}
		}
		
		if ("getOneMem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				RmOrderService rmOrderSvc = new RmOrderService();
				if (rmOrderSvc.getOne(rm_order_no)==null) {
					errorMsgs.add("查無此訂單編號");
				}
				Integer exp = rmOrderSvc.getOne(rm_order_no).getMem_no();
				if (!exp.equals(mem_no)) {
					errorMsgs.add("查無此訂單編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/listMemOrder.jsp");	//查無資料返回登入
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ****************************************/
				List<RmOrderVO> orderlist = new ArrayList<RmOrderVO>();
				orderlist.add(rmOrderSvc.getOne(rm_order_no));
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/mem/listMemOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/mem/listMemOrder.jsp").forward(req, res);
			}
		}

		if ("storeCancel".equals(action)) { // 取消訂單

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));

				/*************************** 2.開始修改資料 ****************************************/
				RmOrderListService rmOrderListSvc = new RmOrderListService();
				List<RmOrderListVO> list = rmOrderListSvc.getAllByRmOrderNo(rm_order_no);
				RmReserveService rmReserveSvc = new RmReserveService();
				
				// 釋出空房
				for(RmOrderListVO e:list) {
					rmReserveSvc.cancelRmRsv(e.getRm_amount(), e.getRm_type_no()
							, e.getArrival_date(), e.getDeparture_date());
				}
			
				// 修改訂單狀態&金額
				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderSvc.cancel(rm_order_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
				
				String url = "/frontend/store/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/store/listStoreOrder.jsp").forward(req, res);
			}
		}
		
		if ("workCancel".equals(action)) { // 取消訂單
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				
				/*************************** 2.開始修改資料 ****************************************/
				RmOrderListService rmOrderListSvc = new RmOrderListService();
				List<RmOrderListVO> list = rmOrderListSvc.getAllByRmOrderNo(rm_order_no);
				RmReserveService rmReserveSvc = new RmReserveService();
				
				// 釋出空房
				for(RmOrderListVO e:list) {
					rmReserveSvc.cancelRmRsv(e.getRm_amount(), e.getRm_type_no()
							, e.getArrival_date(), e.getDeparture_date());
				}
				
				// 修改訂單狀態&金額
				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderSvc.cancel(rm_order_no);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
				
				String url = "/frontend/room/rmWork.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/rmWork.jsp").forward(req, res);
			}
		}
		
		if ("memCancel".equals(action)) { // 取消訂單
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				// 判斷入住日期是否大於七日
				RmOrderListService rmSvc = new RmOrderListService();
				RmOrderListVO rm = rmSvc.getAllByRmOrderNo(rm_order_no).get(0);
				java.util.Date rmDate = rm.getArrival_date();
		        Calendar calendar = Calendar.getInstance();
		        java.util.Date dateObj = calendar.getTime();
		        
		        long day = (rmDate.getTime()-dateObj.getTime())/(24*60*60*1000);
		        
				if(day < 6) {
					errorMsgs.add("入住日小於七日,請來電洽詢");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/listMemOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始修改資料 ****************************************/
				RmOrderListService rmOrderListSvc = new RmOrderListService();
				List<RmOrderListVO> list = rmOrderListSvc.getAllByRmOrderNo(rm_order_no);
				RmReserveService rmReserveSvc = new RmReserveService();
				
				// 釋出空房
				for(RmOrderListVO e:list) {
					rmReserveSvc.cancelRmRsv(e.getRm_amount(), e.getRm_type_no()
							, e.getArrival_date(), e.getDeparture_date());
				}
				
				// 修改訂單狀態&金額
				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderSvc.cancel(rm_order_no);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
				
				String url = "/frontend/mem/listMemOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/mem/listMemOrder.jsp").forward(req, res);
			}
		}
		
		if ("checkIn".equals(action)) { // checkIn

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));

				/*************************** 2.開始修改資料 ****************************************/

				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderSvc.checkIn(rm_order_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/

				String url = "/frontend/room/rmWork.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}

		if ("checkOut".equals(action)) { // checkOut

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));

				/*************************** 2.開始修改資料 ****************************************/
				
				// 修改訂單狀態
				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderSvc.checkOut(rm_order_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/

				String url = "/frontend/room/rmWork.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/rmWork.jsp").forward(req, res);
			}
		}
		if ("checkOutEarly".equals(action)) { // checkOutEarly
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				Integer rm_type_no = new Integer(req.getParameter("rm_type_no"));
				String departure_date = req.getParameter("departure_date");
				/*************************** 2.開始修改資料 ****************************************/
				
				// 提早退房釋出空房
				RmReserveService rmReserveSvc = new RmReserveService();
				rmReserveSvc.checkOutEarly(rm_type_no, java.sql.Date.valueOf(departure_date));
				
				// 修改訂單狀態
				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderSvc.checkOut(rm_order_no);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
				
				String url = "/frontend/room/rmWork.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/rmWork.jsp").forward(req, res);
			}
		}
	}
}