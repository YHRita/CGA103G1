package com.last_news.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.last_news.model.*;

@MultipartConfig
@WebServlet("/lastNews")
public class LastNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LastNewsServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOnePic".equals(action)) {

			/*************************** 1.接收請求參數 ****************************************/
			Integer news_no = new Integer(req.getParameter("news_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			LastNewsService lnSvc = new LastNewsService();
			LastNewsVO lastNewsVO = lnSvc.getLastNewsOne(news_no);

			/*************************** 3.輸出圖片 ************/
			byte[] content = lastNewsVO.getNews_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}

		if ("insert".equals(action)) {

			// ------------------------- 輸入格式錯誤處理 ----------------

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// news
			String news = req.getParameter("news");
			if (news == null || news.length() == 0) {
				errorMsgs.add("請填入最新消息");
			}

			// img
			byte[] img = null;
			Part news_img = req.getPart("news_img");
			InputStream in = news_img.getInputStream();
			img = new byte[in.available()];
			in.read(img);
			in.close();

			LastNewsVO lastNewsVO = new LastNewsVO();

			lastNewsVO.setNews(news);
			lastNewsVO.setNews_img(img);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("lastNewsVO", lastNewsVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/last_news/addNews.jsp");
				failureView.forward(req, res);
				return; // 中斷
			}

			// ----------------------- 修改資料 ---------------------
			LastNewsService lnSrv = new LastNewsService();
			lastNewsVO = lnSrv.addLastNews(news, img);

			// --------------------- 修改完成,準備轉交 ---------------
			req.setAttribute("lastNewsVO", lastNewsVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/last_news/lastNews.jsp");
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer news_no = Integer.valueOf(req.getParameter("news_no"));

			/*************************** 2.開始刪除資料 ***************************************/
			LastNewsService lnSvc = new LastNewsService();
			lnSvc.deleteLastNews(news_no);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/last_news/lastNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
