package com.tkt_order.model;

import java.sql.Date;

public class TktOrderVO {
	private Integer tkt_order_no           ; 
	private Integer    mem_no              ; 
	private Integer    mem_coup_no         ; 
	private Integer    tkt_no              ; 
	private Integer    original_price      ; 
	private Date    orderdate              ; 
	private Integer    TTL_PRICE           ;
	public Integer getTkt_order_no() {
		return tkt_order_no;
	}
	public void setTkt_order_no(Integer tkt_order_no) {
		this.tkt_order_no = tkt_order_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getMem_coup_no() {
		return mem_coup_no;
	}
	public void setMem_coup_no(Integer mem_coup_no) {
		this.mem_coup_no = mem_coup_no;
	}
	public Integer getTkt_no() {
		return tkt_no;
	}
	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}
	public Integer getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(Integer original_price) {
		this.original_price = original_price;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public Integer getTTL_PRICE() {
		return TTL_PRICE;
	}
	public void setTTL_PRICE(Integer tTL_PRICE) {
		TTL_PRICE = tTL_PRICE;
	} 
	
	
}
