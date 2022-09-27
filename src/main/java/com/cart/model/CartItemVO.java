package com.cart.model;

import java.io.Serializable;

public class CartItemVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer tkt_no;
	private Integer count;  // 當下加進購物出的數量
	private String tkt_name;
	private Integer price;
	
	public CartItemVO() {}
	
	public CartItemVO(Integer tkt_no, Integer count, String tkt_name, Integer price) {
		super();
		this.tkt_no = tkt_no;
		this.count = count;
		this.tkt_name = tkt_name;
		this.price = price;
	}

	public Integer getTkt_no() {
		return tkt_no;
	}

	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTkt_name() {
		return tkt_name;
	}

	public void setTkt_name(String tkt_name) {
		this.tkt_name = tkt_name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	@Override
//	public String toString() {
//		return "CartItemVO [tkt_no=" + tkt_no + ", count=" + count + ", tkt_name=" + tkt_name + ", price=" + price
//				+ "]";
//	}
	
	
	
}
