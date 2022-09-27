package com.system_notification_message.model;

import java.io.Serializable;
import java.sql.Date;

public class SystemNotificationMessageVO implements Serializable{
    private Integer msg_no;
    private String msg;
    private byte[] msg_img;
    private Date msg_time;
    private Integer emp_no;
    private Integer emp_read;
    
	public Integer getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(Integer msg_no) {
		this.msg_no = msg_no;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public byte[] getMsg_img() {
		return msg_img;
	}
	public void setMsg_img(byte[] msg_img) {
		this.msg_img = msg_img;
	}
	public Date getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(Date msg_time) {
		this.msg_time = msg_time;
	}
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public Integer getEmp_read() {
		return emp_read;
	}
	public void setEmp_read(Integer emp_read) {
		this.emp_read = emp_read;
	}
	
}
