package com.act_rp.model;

import java.io.Serializable;
import java.sql.Date;

public class ActRpVO implements Serializable{
	private Integer act_rp_no;
	private Integer mem_no;
	private Integer act_no;
	private String act_rp_reason;
	private String act_rp_content;
	private Date act_rp_time;
	private Integer emp_no;
	private Date act_rp_done_time;
	private Integer act_rp_status;
	private String act_rp_note;
	
	public Integer getAct_rp_no() {
		return act_rp_no;
	}
	public void setAct_rp_no(Integer act_rp_no) {
		this.act_rp_no = act_rp_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getAct_no() {
		return act_no;
	}
	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}
	public String getAct_rp_reason() {
		return act_rp_reason;
	}
	public void setAct_rp_reason(String act_rp_reason) {
		this.act_rp_reason = act_rp_reason;
	}
	public String getAct_rp_content() {
		return act_rp_content;
	}
	public void setAct_rp_content(String act_rp_content) {
		this.act_rp_content = act_rp_content;
	}
	public Date getAct_rp_time() {
		return act_rp_time;
	}
	public void setAct_rp_time(Date act_rp_time) {
		this.act_rp_time = act_rp_time;
	}
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public Date getAct_rp_done_time() {
		return act_rp_done_time;
	}
	public void setAct_rp_done_time(Date act_rp_done_time) {
		this.act_rp_done_time = act_rp_done_time;
	}
	public Integer getAct_rp_status() {
		return act_rp_status;
	}
	public void setAct_rp_status(Integer act_rp_status) {
		this.act_rp_status = act_rp_status;
	}
	public String getAct_rp_note() {
		return act_rp_note;
	}
	public void setAct_rp_note(String act_rp_note) {
		this.act_rp_note = act_rp_note;
	}
	

}
