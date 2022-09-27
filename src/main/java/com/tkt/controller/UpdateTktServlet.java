package com.tkt.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkt.model.TktService;
import com.tkt.model.TktVO;

@WebServlet(name = "UpdateTktServlet", urlPatterns = { "/tkt/updateTkt.do" })
public class UpdateTktServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		
		// getOneUpdate
		if ("getOneUpdate".equals(action)) {
			
			// ------------- getParameter -------------
			
			// tkt_no
			Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
			
			// -------------- getOne -------------
			
			TktService tktSrv = new TktService();
			TktVO tktVO = tktSrv.getOneTkt(tkt_no);
//			System.out.println(tkt_no);
			
			// -------------- forward -------------
			req.setAttribute("tktVO", tktVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/updateTkt.jsp");
			successView.forward(req, res);
		
		}
		
		
		// tktUpdate
		if ("tktUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// tkt_no
			Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));

			
			// tkt_name
			String tkt_name = req.getParameter("tkt_name");
			if (tkt_name == null || tkt_name.trim().length() == 0) {
				errorMsgs.add("請填入票券名稱");
			}

			// original_amount
			Integer original_amount = null;
			try {
				original_amount = Integer.valueOf(req.getParameter("original_amount").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請填入票券原始數量");
			}

			// price
			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請填入票券價格");
			}

			// tkt_startdate

			Date tkt_startdate = null;
			try {
				tkt_startdate = Date.valueOf(req.getParameter("tkt_startdate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請填入優惠券起始日期");
			}

			// tkt_enddate
			Date tkt_enddate = null;
			try {
				tkt_enddate = Date.valueOf(req.getParameter("tkt_enddate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請填入優惠券結束日期");
			}

			// locate
			String locate = req.getParameter("locate");
			if (locate == null || locate.trim().length() == 0) {
				errorMsgs.add("請填入票券地點");
			}

			// instruction
			String instruction = req.getParameter("instruction");
			if (instruction == null || instruction.trim().length() == 0) {
				errorMsgs.add("請填入票券說明");
			}

			// address
			String address = req.getParameter("address");
			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("請填入票券體驗地址");
			}

			// notice
			String notice = req.getParameter("notice");
			if (notice == null || notice.trim().length() == 0) {
				errorMsgs.add("請填入購買須知");
			}

			// howuse
			String howuse = req.getParameter("howuse");
			if (howuse == null || howuse.trim().length() == 0) {
				errorMsgs.add("請填入如何使用");
			}

			// canxpolicy
			String canxpolicy = req.getParameter("canxpolicy");
			if (canxpolicy == null || canxpolicy.trim().length() == 0) {
				errorMsgs.add("請填入取消政策");
			}

			// tkt_status
			Integer tkt_status = null;
			try {
				tkt_status = Integer.valueOf(req.getParameter("tkt_status").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請選擇票券狀態");
			}

			// kind
			Integer kind = null;
			try {
				kind = Integer.valueOf(req.getParameter("kind").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請選擇票券種類");
			}
			
			TktVO tktVO = new TktVO();
			tktVO.setTkt_no(tkt_no);
			tktVO.setTkt_name(tkt_name);
			tktVO.setOriginal_amount(original_amount);
			tktVO.setPrice(price);
			tktVO.setTkt_startdate(tkt_startdate);
			tktVO.setTkt_enddate(tkt_enddate);
			tktVO.setLocate(locate);
			tktVO.setInstruction(instruction);
			tktVO.setAddress(address);
			tktVO.setNotice(notice);
			tktVO.setHowuse(howuse);
			tktVO.setCanxpolicy(canxpolicy);
			tktVO.setTkt_status(tkt_status);
			tktVO.setSold_amount(0);
			tktVO.setKind(kind);
			

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("tktVO", tktVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tkt/updateTkt.jsp");
				failureView.forward(req, res);
				return; // 中斷
			}
			
			// ------------------------- 永續層 ----------------
			TktService tktSrv = new TktService();
			tktVO = tktSrv.updateTkt(tkt_no, tkt_name, original_amount, price, tkt_startdate,
					tkt_enddate, locate, instruction, address, notice, howuse,
					canxpolicy, tkt_status, 0, kind);
			
			// ------------------------- forward -------------------------
			req.setAttribute("tktVO", tktVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/listAllTkt.jsp");
			successView.forward(req, res);
		}
		
	}

}
