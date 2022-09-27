package com.customer_service.model;

import java.io.Serializable;
import java.sql.Date;

public class CustomerServiceVO implements Serializable{
	private Integer msg_id;
	private Integer store_no;
	private Integer emp_no;
	private Integer mem_no;
	private String msg_content;
	private Integer msg_direction;
	private Date msg_time;
	
	public Integer getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}
	public Integer getStore_no() {
		return store_no;
	}
	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
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
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public Integer getMsg_direction() {
		return msg_direction;
	}
	public void setMsg_direction(Integer msg_direction) {
		this.msg_direction = msg_direction;
	}
	public Date getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(Date msg_time) {
		this.msg_time = msg_time;
	}
	
	
}
