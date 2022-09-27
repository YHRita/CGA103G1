package com.tkt_img2.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashSet;
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

import com.tkt.model.TktService;
import com.tkt.model.TktVO;
import com.tkt_img2.model.TktImg2VO;
import com.tkt_img2.model.Tktimg2Service;

@WebServlet(name = "UploadTktImg", urlPatterns = { "/tkt_img2/uploadTktImg.do" })
@MultipartConfig
public class UploadTktImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");

		String action = req.getParameter("action");

		// getOneToUpload // 前往上傳圖片頁面
//		if ("getOneToUpload".equals(action)) {
//
//			// ------------------------- getParameter ----------------
//			Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
//			System.out.println("getOneToUpload---------" + tkt_no);
//
//			// ------------------------- 永續層 ----------------
//
//			TktService tktSrv = new TktService();
//			TktVO tktVO = tktSrv.getOneTkt(tkt_no);
//
//			// ------------------------- forward ----------------
//			req.setAttribute("tktVO", tktVO);
//			RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/addTktImg.jsp");
//			successView.forward(req, res);
//		}
		// 在 listAllTkt、 tktShopList、tktDetail 用 queryString 取得照片
		if ("showFirstImages".equals(action)) {
			// ------------------------- getParameter ----------------
			Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
			// ------------------------- 永續層 ----------------
			Tktimg2Service tktimg2Srv = new Tktimg2Service();
			List<TktImg2VO> images = tktimg2Srv.getAllByTktNo(tkt_no);

			Optional<TktImg2VO> firstImages = null;
			firstImages = images.stream().findFirst();

			// ------------------------- forward ----------------
			byte[] content = firstImages.get().getTkt_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}

		// getOneToUpload // 前往新增票券圖片頁面
		if ("getOneToUpload".equals(action)) {
			// ------------------------- getParameter ----------------
			Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));

			// ------------------------- 永續層 ----------------
			Tktimg2Service tktimg2Srv = new Tktimg2Service();
			List<TktImg2VO> images = tktimg2Srv.getAllByTktNo(tkt_no);

			// ------------------------- forward ----------------
			req.setAttribute("images", images);
			req.setAttribute("tkt_no", tkt_no);

			RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/addTktImg.jsp");
			successView.forward(req, res);
		}


		// 顯示單張圖片
//		if ("showImages".equals(action)) {
//			// ------------------------- getParameter ----------------
//			Integer tkt_img_no = Integer.valueOf(req.getParameter("tkt_img_no"));
//
//			// ------------------------- 永續層 ----------------
//			Tktimg2Service tktimg2Srv = new Tktimg2Service();
//			TktImg2VO tktImg2VO = tktimg2Srv.getOne(tkt_img_no);
//			
//			// ------------------------- forward ----------------
//			byte[] content = tktImg2VO.getTkt_img();
//			ServletOutputStream out = res.getOutputStream();
//			out.write(content);
//			out.close();
//			return;
//		}

		// uploadTktImg 上傳照片
		if ("uploadTktImg".equals(action)) {

			Set<String> errorMsgs = new LinkedHashSet<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ------------------------- getParameter ----------------
			Integer tkt_no = Integer.valueOf(req.getParameter("tkt_no"));
//			System.out.println("-------" + tkt_no);

			Tktimg2Service tktImgSrv = new Tktimg2Service();
			Collection<Part> parts = req.getParts();
			String filename = null;

			for (Part part : parts) {

				InputStream in = part.getInputStream();
				if (getFileNameFromPart(part) != null && part.getContentType() != null
						&& !part.getContentType().equals("application/octet-stream")) {
					filename = getFileNameFromPart(part);
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					tktImgSrv.insertTktImg(tkt_no, buf);
				}
			}

			if (filename == null)
				errorMsgs.add("請選擇圖片新增");

			List<TktImg2VO> images = tktImgSrv.getAllByTktNo(tkt_no);
			req.setAttribute("images", images);

			req.setAttribute("tkt_no", tkt_no);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tkt/addTktImg.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			// forward
			RequestDispatcher successView = req.getRequestDispatcher("/backend/tkt/listAllTkt.jsp");
			successView.forward(req, res);
			return;

		}

	}
	
	// 上傳圖片取得檔案名用
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
