package com.rm_pic.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.rm_pic.model.RmPicService;
import com.rm_pic.model.RmPicVO;

@WebServlet("/rmPic/rmPic.do")
@MultipartConfig
public class RmPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("image/jpeg");
		String action = req.getParameter("action");

		// 顯示第一張圖片
		if ("showFirstImages".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			Integer rm_type_no = new Integer(req.getParameter("rm_type_no"));
			/***************************
			 * 2.開始查詢資料，篩選資料
			 ****************************************/
			RmPicService rmPicSvc = new RmPicService();
			List<RmPicVO> images = rmPicSvc.getAllByType(rm_type_no);

			Optional<RmPicVO> firstImages = null;
			firstImages = images.stream().findFirst();

			/*************************** 3.輸出圖片 ************/
			byte[] content = firstImages.get().getRm_pic();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}
		// 連到房型圖片頁面,顯示該房型全部圖片
		if ("getOneForShowImages".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			Integer rm_type_no = new Integer(req.getParameter("rm_type_no"));
			/***************************
			 * 2.開始查詢資料，篩選資料
			 ****************************************/
			RmPicService rmPicSvc = new RmPicService();
			List<RmPicVO> images = rmPicSvc.getAllByType(rm_type_no);

			/*************************** 3.查詢完成,準備轉交 ************/
			req.setAttribute("images", images);
			req.setAttribute("rm_type_no", rm_type_no);

			String url = "/frontend/room/listOneTypePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

		// 顯示單張圖片
		if ("showImages".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			Integer rm_pic_no = new Integer(req.getParameter("rm_pic_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			RmPicService rmPicSvc = new RmPicService();
			RmPicVO rmPicVO = rmPicSvc.getOneRmPic(rm_pic_no);

			/*************************** 3.輸出圖片 ************/
			byte[] content = rmPicVO.getRm_pic();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}

		// 刪除圖片
		if ("delete".equals(action)) {
			/*************************** 1.接收請求參數 ********************/
			Integer rm_pic_no = new Integer(req.getParameter("rm_pic_no"));
			Integer rm_type_no = new Integer(req.getParameter("rm_type_no"));

			/*************************** 2.刪除圖片,查詢新的list ************/
			RmPicService rmPicSvc = new RmPicService();
			rmPicSvc.deleteRmPic(rm_pic_no);

			List<RmPicVO> images = rmPicSvc.getAllByType(rm_type_no);

			/*************************** 3.查詢完成,準備轉交 ************/
			req.setAttribute("images", images);
			req.setAttribute("rm_type_no", rm_type_no);

			String url = "/frontend/room/listOneTypePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		
//		  if ("insert".equals(action)) { 
//				
//				List<String> errorMsgs = new LinkedList<String>();
//				req.setAttribute("errorMsgs", errorMsgs);
//
//					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer rm_type_no = Integer.valueOf(req.getParameter("rm_type_no"));
//				
//				Part part = req.getPart("rm_pic");
//				InputStream in = part.getInputStream();
//				byte[] rm_pic;
//				if(in.available() == 0) {
//					rm_pic = null;
//				} else {
//					rm_pic = new byte[in.available()];
//					in.read(rm_pic);
//				}
//				in.close();
//				
//				RmPicVO rmPicVO = new RmPicVO();
//				rmPicVO.setRm_pic_no(rm_type_no);
//				rmPicVO.setRm_pic(rm_pic);
//				
//					// Send the use back to the form, if there were errors
//					//下列if為管理員驗證失敗保正確值返回
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("RmPicVO", rmPicVO); // 含有輸入格式錯誤的boxVO物件,也存入req
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/frontend/room/listOneTypePic.jsp");
//						failureView.forward(req, res);
//						return;
//					}
//
//					/***************************2.開始新增資料***************************************/
//					RmPicService rmPicSrv = new RmPicService();
//					rmPicVO = rmPicSrv.addRmPic(rm_type_no, rm_pic);
//					
//					/***************************3.新增完成,準備轉交(Send the Success view)***********/
//					String url = "/frontend/room/listOneTypePic.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交.jsp
//					successView.forward(req, res);
//					return;
//					}
	        

		// 新增圖片
		if (action.equals("insert")) {
			Set<String> errorMsgs = new LinkedHashSet<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer rm_type_no = new Integer(req.getParameter("rm_type_no"));
			
			/*************************** 2.新增圖片,查詢新的list ********************************/
			RmPicService rmPicSvc = new RmPicService();
//			Part part = req.getPart("imageFile");
		Collection<Part> parts = req.getParts();
			String filename = null;
//			Part part;
		for (Part part : parts) {

//				System.out.println("filename=" + filename); // 測試用
//				System.out.println(part.getContentType()); // 測試用
				InputStream in = part.getInputStream();
				if (getFileNameFromPart(part) != null && part.getContentType() != null
						&& !part.getContentType().equals("application/octet-stream")) {
					filename = getFileNameFromPart(part);
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					rmPicSvc.addRmPic(rm_type_no, buf);
//					System.out.println("action");
			 	 }
				}

			if (filename == null)
				errorMsgs.add("請選擇圖片新增");
			
			List<RmPicVO> images = rmPicSvc.getAllByType(rm_type_no);
			req.setAttribute("images", images);

			/** 如果沒圖片 **/
			req.setAttribute("rm_type_no", rm_type_no);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/listOneTypePic.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交 ************/
			String url = "/frontend/room/listOneTypePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
