package com.rm_msg.model;

import java.io.Serializable;
import java.sql.Date;

public class Rm_msgVO implements Serializable{
	private Integer rm_msg_no;
	private Integer emp_no;
	private Integer mem_no;
	private Integer store_no;
	private String rm_msg_reason;
	private Date rm_msg_date;
	private Integer rm_msg_status;
	private Date rm_msg_donetime;
	public Integer getRm_msg_no() {
		return rm_msg_no;
	}
	public void setRm_msg_no(Integer rm_msg_no) {
		this.rm_msg_no = rm_msg_no;
	}
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getStore_no() {
		return store_no;
	}
	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}
	public String getRm_msg_reason() {
		return rm_msg_reason;
	}
	public void setRm_msg_reason(String rm_msg_reason) {
		this.rm_msg_reason = rm_msg_reason;
	}
	public Date getRm_msg_date() {
		return rm_msg_date;
	}
	public void setRm_msg_date(Date rm_msg_date) {
		this.rm_msg_date = rm_msg_date;
	}
	public Integer getRm_msg_status() {
		return rm_msg_status;
	}
	public void setRm_msg_status(Integer rm_msg_status) {
		this.rm_msg_status = rm_msg_status;
	}
	public Date getRm_msg_donetime() {
		return rm_msg_donetime;
	}
	public void setRm_msg_donetime(Date rm_msg_donetime) {
		this.rm_msg_donetime = rm_msg_donetime;
	}
	
	
}
