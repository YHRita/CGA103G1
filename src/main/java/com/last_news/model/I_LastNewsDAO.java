package com.last_news.model;

import java.util.List;


public interface I_LastNewsDAO {
	//新增訊息
	public void insert(LastNewsVO lastNewsVO);
	//下架訊息
	public void delete(Integer news_no);
	//顯示訊息
	public List<LastNewsVO> getall();
	public List<LastNewsVO> getthree();
	
	public LastNewsVO getone(Integer news_no);
	
	public LastNewsVO  getOnePic(Integer news_no);
	
	public LastNewsVO  getlast();

}
