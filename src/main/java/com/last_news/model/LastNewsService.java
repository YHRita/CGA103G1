package com.last_news.model;

import java.util.List;


public class LastNewsService {

		private I_LastNewsDAO dao;
		
		public LastNewsService() {
			dao = new LastNewsDAO();
		}
		public LastNewsVO  addLastNews(String news, byte[] news_img) {
			LastNewsVO lastNewsVO = new LastNewsVO();
			lastNewsVO.setNews(news);
			lastNewsVO.setNews_img(news_img);
			dao.insert(lastNewsVO);
			
			return lastNewsVO;
		}
		
//		public LastNewsVO  updateLastNews(Integer news_no,String news,byte[] news_img,Date news_time) {
//			LastNewsVO lastNewsVO = new LastNewsVO();
//			lastNewsVO.setNews_no(news_no);
//			lastNewsVO.setNews(news);
//			lastNewsVO.setNews_img(news_img);
//			lastNewsVO.setNews_time(news_time);
//			dao.update(lastNewsVO);
//			
//			return lastNewsVO;
//		}
		
		public void deleteLastNews(Integer news_no) {
			dao.delete(news_no);
		}
		
		public List<LastNewsVO> getLastNewsAll() {
			return dao.getall();
		}
		public List<LastNewsVO> getthree() {
			return dao.getthree();
		}
		
		public LastNewsVO getLastNewsOne(Integer news_no) {
			return dao.getone(news_no);
		}
		
		public LastNewsVO getOnePic(Integer news_no) {
			return dao.getOnePic(news_no);
		}
		
		public LastNewsVO getLastNewsONE() {
			return dao.getlast();
		}
}
