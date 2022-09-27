package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable{
private Integer	mem_no             ; 
private String  mem_acc            ; 
private String  mem_pwd            ; 
private Integer acc_status         ; 
private String  mem_name           ; 
private String  mem_gender         ; 
private String  mem_email          ; 
private String  mem_mobile         ; 
private String  mem_city           ; 
private String  mem_dist           ; 
private String  mem_addr           ; 
private Date    mem_reg_date       ; 
private byte[]  mem_pic            ; 
private Integer mem_report_count   ; 
private String  mem_card           ;

public MemVO() {
	super();
}

public Integer getMem_no() {
	return mem_no;
}
public void setMem_no(Integer mem_no) {
	this.mem_no = mem_no;
}
public String getMem_acc() {
	return mem_acc;
}
public void setMem_acc(String mem_acc) {
	this.mem_acc = mem_acc;
}
public String getMem_pwd() {
	return mem_pwd;
}
public void setMem_pwd(String mem_pwd) {
	this.mem_pwd = mem_pwd;
}
public Integer getAcc_status() {
	return acc_status;
}
public void setAcc_status(Integer acc_status) {
	this.acc_status = acc_status;
}
public String getMem_name() {
	return mem_name;
}
public void setMem_name(String mem_name) {
	this.mem_name = mem_name;
}
public String getMem_gender() {
	return mem_gender;
}
public void setMem_gender(String mem_gender) {
	this.mem_gender = mem_gender;
}
public String getMem_email() {
	return mem_email;
}
public void setMem_email(String mem_email) {
	this.mem_email = mem_email;
}
public String getMem_mobile() {
	return mem_mobile;
}
public void setMem_mobile(String mem_mobile) {
	this.mem_mobile = mem_mobile;
}
public String getMem_city() {
	return mem_city;
}
public void setMem_city(String mem_city) {
	this.mem_city = mem_city;
}
public String getMem_dist() {
	return mem_dist;
}
public void setMem_dist(String mem_dist) {
	this.mem_dist = mem_dist;
}
public String getMem_addr() {
	return mem_addr;
}
public void setMem_addr(String mem_addr) {
	this.mem_addr = mem_addr;
}
public Date getMem_reg_date() {
	return mem_reg_date;
}
public void setMem_reg_date(Date mem_reg_date) {
	this.mem_reg_date = mem_reg_date;
}
public byte[] getMem_pic() {
	return mem_pic;
}
public void setMem_pic(byte[] mem_pic) {
	this.mem_pic = mem_pic;
}
public Integer getMem_report_count() {
	return mem_report_count;
}
public void setMem_report_count(Integer mem_report_count) {
	this.mem_report_count = mem_report_count;
}
public String getMem_card() {
	return mem_card;
}
public void setMem_card(String mem_card) {
	this.mem_card = mem_card;
}


	
}
