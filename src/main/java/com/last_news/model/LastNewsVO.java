package com.last_news.model;

import java.io.Serializable;
import java.sql.Date;

public class LastNewsVO implements Serializable {
	private Integer news_no;
	private String news;
	private byte[] news_img;
	private Date news_time;
	
	public Integer getNews_no() {
		return news_no;
	}
	public void setNews_no(Integer news_no) {
		this.news_no = news_no;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public byte[] getNews_img() {
		return news_img;
	}
	public void setNews_img(byte[] news_img) {
		this.news_img = news_img;
	}
	public Date getNews_time() {
		return news_time;
	}
	public void setNews_time(Date news_time) {
		this.news_time = news_time;
	}
	
	
}
