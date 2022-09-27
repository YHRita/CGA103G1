package com.tkt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import com.google.gson.Gson;
import com.tkt.model.TktService;
import com.tkt.model.TktVO;
import com.tkt_img.model.TktImgVO;
import com.tkt_img2.model.TktImg2VO;
import com.tkt_img2.model.Tktimg2Service;
import com.tkt_img.model.TktImgService;

@WebServlet(name = "AddTktServlet", urlPatterns = { "/tkt/addTkt.do" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AddTktServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		// 傳送 JSON 格式
//		res.setContentType("application/json; charset=utf-8");
//		TktService tktSrv = new TktService();
//		Integer tkt_rows = tktSrv.find_tkt_rows() + 1; // 找下一筆當下一筆 tkt_no
//		System.out.println(tkt_rows);
		// ------------------------- getParameter ----------------

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		// 算 tkt_no 要用 list 長度
//		TktService tktService = new TktService();
//		List<TktVO> tktList =  tktService.getAll();

		// tkt_no
		// Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));

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
		
		// 接收票券圖片
		// tkt_img
//		Part imgPart = req.getPart("tkt_img");
//		InputStream fileContent = imgPart.getInputStream();
//		byte[] tkt_img = org.apache.commons.io.IOUtils.toByteArray(fileContent);
//		if (tkt_img.length == 0) {
//			errorMsgs.add("請上傳票券圖片");
//		}
		

		TktVO tktVO = new TktVO();
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
		tktVO.setKind(kind);
		
//		TktImg2VO tktImg2VO = new TktImg2VO();
//		
//		tktImg2VO.setTkt_no(tkt_rows);
//		tktImg2VO.setTkt_img(tkt_img);
		
//		TktImgVO tktImgVO = new TktImgVO();
//		tktImgVO.settktNO(tkt_rows);
//		tktImgVO.settktimg(tkt_img);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("tktVO", tktVO);
			RequestDispatcher failureView = req.getRequestDispatcher("/backend/tkt/addTkt.jsp");
			failureView.forward(req, res);
			return; // 中斷
		}

		// ------------------------- 永續層 ----------------
		TktService tktSrv = new TktService();
		tktVO = tktSrv.addTkt(tkt_name, original_amount, price, tkt_startdate, tkt_enddate, locate, instruction,
				address, notice, howuse, canxpolicy, tkt_status, kind);
		
//		Tktimg2Service tktImgSrv = new Tktimg2Service();
//		tktImg2VO = tktImgSrv.addTktImg(tkt_rows, tkt_img);

//		TktImgService tktImgSrv = new TktImgService();
//		tktImgSrv.addTktImg(tkt_no, tkt_img);
		
//		TktImgService tktImgSrv = new TktImgService();
//		tktImgSrv.addTktImg(tktList.size()+1 ,tkt_img);
//		TktImgVO tktImgVO = new TktImgVO();
//		for (int i = 0; i < tktList.size(); i++) {
//			tktImgVO.settktNO(tktList.size()+1); // 現有長度加 1
//			tktImgVO.settktimg(tkt_img);
//			tktImgSrv.addTktImg(tktList.size()+1 ,tkt_img);
//		}

		// ------------------------- forward -------------------------
		req.setAttribute("tktVO", tktVO);
//		req.setAttribute("tktImgVO", tktImgVO);
		RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/listAllTkt.jsp");
		successView.forward(req, res);
//		RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt_img/uploadTktImg.jsp");
//		successView.forward(req, res);

		//////////////////////////////////////////////////// to JSON
		//////////////////////////////////////////////////// ////////////////////////////////////////////////////

		// ------------ getParameter ------------

		// tkt_name
//		String tkt_name = req.getParameter("tkt_name");

		// original_amount
//		Integer original_amount = Integer.valueOf(req.getParameter("original_amount"));

		// price
//		Integer price = Integer.valueOf(req.getParameter("price"));

		// tkt_startdate
//		Date tkt_startdate = Date.valueOf(req.getParameter("tkt_startdate"));

		// tkt_enddate
//		Date tkt_startdate = Date.valueOf(req.getParameter("tkt_enddate"));

		// locate
//		String locate = req.getParameter("locate");

		// instruction
//		String instruction = req.getParameter("instruction");

		// address
//		String address = req.getParameter("address");

		// notice
//		String notice = req.getParameter("notice");

		// howuse
//		String howuse = req.getParameter("howuse");

		// canxpolicy
//		String canxpolicy = req.getParameter("canxpolicy");

		// tkt_status
//		Integer tkt_status = Integer.valueOf("tkt_status");

		// kind
//		Integer kind = Integer.valueOf("kind");

		// ------------ to JSON ------------
//		Gson gson = new Gson();
//		TktVO tktVO = new TktVO(4, tkt_name, original_amount, price, tkt_startdate,
//				tkt_enddate, locate, instruction, address, notice, howuse,
//				canxpolicy, 0, 0, 2);
//		
//		String result = gson.toJson(tktVO);

		// ------------ Send ------------
//		res.getWriter().println(result);
//		System.err.println(result);
//		RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/listAllTkt.jsp");
//		successView.forward(req, res);

		// 可以成功把 json 送到前端，但是還不知道要怎麼套到畫面上
	}

}
