package com.act.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer act_no;
	private Integer mem_no;
	private Integer act_type_no;
	private String act_title;
	private String act_content;
	private Integer act_current_count;
	private Integer act_max_count;
	private Integer act_min_count;
	private LocalDateTime act_enroll_begin;
	private LocalDateTime act_enroll_end;
	private LocalDateTime act_start;
	private LocalDateTime act_end;
	private Integer act_loc;
	private String act_pl;
	private Integer act_rate_sum;
	private Integer eval_sum;
	private Integer star_avg;
	private Integer act_status;
	
	public ActVO() {
	}
	
	

	public ActVO(String act_title, String act_content) {
		super();
		this.act_title = act_title;
		this.act_content = act_content;
	}



	public ActVO(Integer act_no, Integer men_no, Integer act_type_no, String act_title, String act_content,
			Integer act_current_count, Integer act_max_count, Integer act_min_count, LocalDateTime act_enroll_begin,
			LocalDateTime act_enroll_end, LocalDateTime act_start, LocalDateTime act_end, Integer act_loc, String act_pl
			,Integer star_avg , Integer act_status) {
		this.act_no = act_no;
		this.mem_no = men_no;
		this.act_type_no = act_type_no;
		this.act_title = act_title;
		this.act_content = act_content;
		this.act_current_count = act_current_count;
		this.act_max_count = act_max_count;
		this.act_min_count = act_min_count;
		this.act_enroll_begin = act_enroll_begin;
		this.act_enroll_end = act_enroll_end;
		this.act_start = act_start;
		this.act_end = act_end;
		this.act_loc = act_loc;
		this.act_pl = act_pl;
		this.star_avg = star_avg;
		this.act_status = act_status;
	}

	
	public ActVO(Integer act_no, Integer men_no, Integer act_type_no, String act_title, String act_content,
			Integer act_current_count, Integer act_max_count, Integer act_min_count, LocalDateTime act_enroll_begin,
			LocalDateTime act_enroll_end, LocalDateTime act_start, LocalDateTime act_end, Integer act_loc, String act_pl,
			Integer act_rate_sum, Integer eval_sum, Integer act_status) {
		this.act_no = act_no;
		this.mem_no = men_no;
		this.act_type_no = act_type_no;
		this.act_title = act_title;
		this.act_content = act_content;
		this.act_current_count = act_current_count;
		this.act_max_count = act_max_count;
		this.act_min_count = act_min_count;
		this.act_enroll_begin = act_enroll_begin;
		this.act_enroll_end = act_enroll_end;
		this.act_start = act_start;
		this.act_end = act_end;
		this.act_loc = act_loc;
		this.act_pl = act_pl;
		this.act_rate_sum = act_rate_sum;
		this.eval_sum = eval_sum;
		this.act_status = act_status;
	}

	
	public Integer getAct_no() {
		return act_no;
	}

	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}

	public Integer getMen_no() {
		return mem_no;
	}

	public void setMen_no(Integer men_no) {
		this.mem_no = men_no;
	}

	public Integer getAct_type_no() {
		return act_type_no;
	}

	public void setAct_type_no(Integer act_type_no) {
		this.act_type_no = act_type_no;
	}

	public String getAct_title() {
		return act_title;
	}

	public void setAct_title(String act_title) {
		this.act_title = act_title;
	}

	public String getAct_content() {
		return act_content;
	}

	public void setAct_content(String act_content) {
		this.act_content = act_content;
	}

	public Integer getAct_current_count() {
		return act_current_count;
	}

	public void setAct_current_count(Integer act_current_count) {
		this.act_current_count = act_current_count;
	}

	public Integer getAct_max_count() {
		return act_max_count;
	}

	public void setAct_max_count(Integer act_max_count) {
		this.act_max_count = act_max_count;
	}

	public Integer getAct_min_count() {
		return act_min_count;
	}

	public void setAct_min_count(Integer act_min_count) {
		this.act_min_count = act_min_count;
	}

	public LocalDateTime getAct_enroll_begin() {
		return act_enroll_begin;
	}

	public void setAct_enroll_begin(LocalDateTime act_enroll_begin) {
		this.act_enroll_begin = act_enroll_begin;
	}

	public LocalDateTime getAct_enroll_end() {
		return act_enroll_end;
	}

	public void setAct_enroll_end(LocalDateTime act_enroll_end) {
		this.act_enroll_end = act_enroll_end;
	}

	public LocalDateTime getAct_start() {
		return act_start;
	}

	public void setAct_start(LocalDateTime act_start) {
		this.act_start = act_start;
	}

	public LocalDateTime getAct_end() {
		return act_end;
	}

	public void setAct_end(LocalDateTime act_end) {
		this.act_end = act_end;
	}

	public Integer getAct_loc() {
		return act_loc;
	}

	public void setAct_loc(Integer act_loc) {
		this.act_loc = act_loc;
	}

	public String getAct_pl() {
		return act_pl;
	}

	public void setAct_pl(String act_pl) {
		this.act_pl = act_pl;
	}

	public Integer getAct_rate_sum() {
		return act_rate_sum;
	}

	public void setAct_rate_sum(Integer act_rate_sum) {
		this.act_rate_sum = act_rate_sum;
	}

	public Integer getEval_sum() {
		return eval_sum;
	}

	public void setEval_sum(Integer eval_sum) {
		this.eval_sum = eval_sum;
	}

	public Integer getAct_status() {
		return act_status;
	}

	public void setAct_status(Integer act_status) {
		this.act_status = act_status;
	}
	
	public Integer getStar_avg() {
		return star_avg;
	}

	public void setStar_avg(Integer star_avg) {
		this.star_avg = star_avg;
	}

	@Override
	public String toString() {
		return "ActVO [活動編號=" + act_no + ", 舉辦者(會員)編號=" + mem_no + ", 活動種類=" + act_type_no + ", 活動標題="
				+ act_title + ", 活動內容=" + act_content + ", 目前報名人數=" + act_current_count
				+ ", 活動人數最大限制=" + act_max_count + ", 活動人數最小限制=" + act_min_count + ", 報名起始日="
				+ act_enroll_begin + ", 報名截止日=" + act_enroll_end + ", 活動起始日=" + act_start + ", 活動結束日="
				+ act_end + ", 揪團活動舉辦縣市=" + act_loc + ", 活動舉辦地址=" + act_pl + ", 評價總星數=" + act_rate_sum
				+ ", 評價總人數=" + eval_sum + ", 活動狀態=" + act_status + "]";
	}

}