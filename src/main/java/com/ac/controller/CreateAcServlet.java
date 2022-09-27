package com.ac.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;

@WebServlet("/createAc")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CreateAcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        System.out.println("request 過來了");
        HttpSession session = req.getSession();
        Integer memSpecNo = (Integer) session.getAttribute("memSpecNo");
//        Integer memNo1 = (Integer) session.getAttribute("memNo1");
        
        
		// String createAc = req.getParameter("action");

		BufferedReader br = req.getReader();
		Gson gson = new Gson();
		GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();
        AcVO acVO = gson.fromJson(br, AcVO.class);
		
		// Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		
//		Part filePart = req.getPart("upload");
//		
//		InputStream fileContent = filePart.getInputStream();
//		byte[] image = org.apache.commons.io.IOUtils.toByteArray(fileContent);
				
		
		// if ("發表".equals(createAc)) { 

		// 	req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// String title = req.getParameter("title");
			// if (title == null || title.trim().length() == 0) {
			// 	errorMsgs.put("title","文章標題請勿空白");
			// }
			// Integer value = 0;
			// String customRadio = req.getParameter("customRadio");
			// System.out.println("Radio的值" + customRadio);
			// if (customRadio == null || customRadio.trim().length() == 0) {
			// 	errorMsgs.put("customRadio","請選擇文章種類");
			// }else {
			// 	value = Integer.parseInt(customRadio);
			// }

			// String content = req.getParameter("content");
			// if (content == null || content.trim().length() == 0) {
			// 	errorMsgs.put("content","文章內容請勿空白");
			// }
			
			// String createTime = req.getParameter("createTime");
			// System.out.println(createTime);
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.TAIWAN);
			// LocalDateTime time = LocalDateTime.parse(createTime, formatter);
			// if (time == null) {
			// 	errorMsgs.put("time","請填寫時間");
			// }
			
			// if (image.length == 0) {
			// 	errorMsgs.put("upload","請上傳一張照片");
			// }

			// System.out.println(errorMsgs);
			
			// // Send the use back to the form, if there were errors
			// if (!errorMsgs.isEmpty()) {
			// 	RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ac/acCreate.jsp");
			// 	failureView.forward(req, res);
			// 	return;
			// }
			
			/*************************** 2.開始新增資料 ***************************************/
			AcServiceImpl acServiceImpl = new AcServiceImpl();
			acVO.setMem_no(memSpecNo);
			acVO.setAc_time(LocalDateTime.now());
			Integer i = acServiceImpl.createAc(acVO);
//			System.out.println("i： " + i);
			session.setAttribute("afterCreateAcNo", i);
			
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// String url = "/frontend/ac/acCardPage.jsp";
			// RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			// successView.forward(req, res);
		}
	}

