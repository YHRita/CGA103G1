package com.ac.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicService;
import com.ac_pic.model.AcPicVO;
import com.mem.model.MemService;

@WebServlet("/updateAc")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UpdateAcServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");			
//        System.out.println("request 過來了，updateAcServlet doGet");
        String action = req.getParameter("action");
        
		HttpSession session = req.getSession();
        Integer memSpecNo = (Integer) session.getAttribute("memSpecNo");
//        Integer memNo1 = (Integer) session.getAttribute("memNo1");
//        Integer memNo2 = (Integer) session.getAttribute("memNo2");
//        Integer memNo3 = (Integer) session.getAttribute("memNo3");

		AcServiceImpl acServiceImpl = new AcServiceImpl();
		AcPicService acPicService = new AcPicService();
		List<AcPicVO> acPicList = new LinkedList<AcPicVO>();
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		Integer acNo = Integer.valueOf(req.getParameter("acNo"));
		System.out.println("傳來的文章編號：" + acNo);				
		AcVO acVO = new AcVO();
		AcPicVO acPicVO = new AcPicVO();				
		acVO = acServiceImpl.getOneAcById(acNo);
		Integer findAcMenNo =  acServiceImpl
				.getAll()
				.stream()
				.filter(ac -> ac.getAc_no() == acNo).findFirst().get().getMem_no();
		System.out.println("發表此篇文章的會員編號：" + findAcMenNo);
		if (findAcMenNo == memSpecNo) {
			if ("alterAc".equals(action)) {				
				/***************************查詢完成,準備轉交(Send the Success view)************/
				String param = "?title="  + acVO.getAc_title() +
						"&no="  + acVO.getAc_no() +
						"&content="  + acVO.getAc_content() +
						"&type=" + acVO.getAc_type_no() +
						"&image="  + acPicVO.getAc_pic_img();
				
				String url = "/frontend/ac/acUpdate.jsp" + param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			}
		} else {
			acVO = acServiceImpl.getOneAcById(acNo);
			acPicList = acPicService.getOneByAcNo(acNo);
			byte[] acImage = acPicList.stream().findFirst().get().getAc_pic_img();
			req.setAttribute("acVO", acVO);
			req.setAttribute("acImage", acImage);
			errorMsgs.put("Msgs","無權修改此文章");
			RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ac/acDetailPage.jsp");
			failureView.forward(req, res);
		}
		
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("request 過來了，updateAcServlet doPost");
		String action = req.getParameter("action");
		Integer acNo = Integer.parseInt(req.getParameter("no"));
		
		HttpSession session = req.getSession();
        Integer memSpecNo = (Integer) session.getAttribute("memSpecNo");
//        Integer memNo1 = (Integer) session.getAttribute("memNo1");
//        Integer memNo2 = (Integer) session.getAttribute("memNo2");
//        Integer memNo3= (Integer) session.getAttribute("memNo3");

		if ("確定修改".equals(action)) { 			
//			Integer acNo = Integer.valueOf(req.getParameter("acNo"));
//			System.out.println(acNo);
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			Part filePart = req.getPart("upload");
			InputStream fileContent = filePart.getInputStream();
			byte[] image = org.apache.commons.io.IOUtils.toByteArray(fileContent);

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String title = req.getParameter("title");
			if (title == null || title.trim().length() == 0) {
				errorMsgs.put("title","文章標題請勿空白");
			}
			Integer value = 0;
			String customRadio = req.getParameter("customRadio");
			System.out.println("Radio的值" + customRadio);
			if (customRadio == null || customRadio.trim().length() == 0) {
				errorMsgs.put("customRadio","請選擇文章種類");
			}else {
				value = Integer.parseInt(customRadio);
			}

			String content = req.getParameter("content");
			if (content == null || content.trim().length() == 0) {
				errorMsgs.put("content","文章內容請勿空白");
			}
			
			String updateTime = req.getParameter("updateTime");
			System.out.println(updateTime);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.TAIWAN);
			LocalDateTime time = LocalDateTime.parse(updateTime, formatter);
			if (time == null) {
				errorMsgs.put("time","請填寫時間");
			}
			
			if (image.length == 0) {
				errorMsgs.put("upload","請上傳一張照片");
			}

			System.out.println(errorMsgs);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("acVo", acVo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ac/acUpdate.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			AcVO acVo = new AcVO();
			acVo.setAc_title(title);
			acVo.setAc_type_no(value);
			acVo.setAc_content(content);
			acVo.setAc_time(time);
			acVo.setAc_no(acNo);
			acVo.setMem_no(memSpecNo);
			AcPicVO acPicVO = new AcPicVO();
			acPicVO.setAc_pic_img(image);
			acPicVO.setAc_no(acNo);
	

			/*************************** 2.開始新增資料 ***************************************/
			AcServiceImpl acServiceImpl = new AcServiceImpl();
			acServiceImpl.alterAc(acVo);
			
			AcPicService acPicService =  new AcPicService();
			acPicService.updateImage(acPicVO);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/frontend/ac/acCardPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
	}
	
	
}
