package com.act_reply.model;

import java.io.Serializable;
import java.sql.Date;

public class ActReplyVO implements Serializable{
	private Integer act_reply_no;
	private Integer act_no;
	private String reply_content;
	private Integer mem_no;
	private Date reply_time;
	
	public Integer getAct_reply_no() {
		return act_reply_no;
	}
	public void setAct_reply_no(Integer act_reply_no) {
		this.act_reply_no = act_reply_no;
	}
	public Integer getAct_no() {
		return act_no;
	}
	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Date getReply_time() {
		return reply_time;
	}
	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
	}
	
}
