package com.rm_order_list.model;

import java.sql.Date;

public class RmOrderListVO {
	private Integer rm_order_list_no;
	private Integer rm_type_no;
	private Integer rm_order_no;
	private Integer rm_amount;
	private Integer rm_price;
	private Date arrival_date;
	private Date departure_date;
	private String rm_check_in;
	
	public Integer getRm_order_list_no() {
		return rm_order_list_no;
	}
	public void setRm_order_list_no(Integer rm_order_list_no) {
		this.rm_order_list_no = rm_order_list_no;
	}
	public Integer getRm_type_no() {
		return rm_type_no;
	}
	public void setRm_type_no(Integer rm_type_no) {
		this.rm_type_no = rm_type_no;
	}
	public Integer getRm_order_no() {
		return rm_order_no;
	}
	public void setRm_order_no(Integer rm_order_no) {
		this.rm_order_no = rm_order_no;
	}
	public Integer getRm_amount() {
		return rm_amount;
	}
	public void setRm_amount(Integer rm_amount) {
		this.rm_amount = rm_amount;
	}
	public Integer getRm_price() {
		return rm_price;
	}
	public void setRm_price(Integer rm_price) {
		this.rm_price = rm_price;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}
	public String getRm_check_in() {
		return rm_check_in;
	}
	public void setRm_check_in(String rm_check_in) {
		this.rm_check_in = rm_check_in;
	}
	
	
	
	
}

	
