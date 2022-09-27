package com.tkt_item.model;

public class TktItemVO {
	private Integer tkt_no;
	private Integer TKT_ORDER_NO;
	private Integer amount;
	private byte[] qrcode;
	private Integer tkt_ori_price;
	public Integer getTkt_no() {
		return tkt_no;
	}
	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}
	public Integer getTKT_ORDER_NO() {
		return TKT_ORDER_NO;
	}
	public void setTKT_ORDER_NO(Integer tKT_ORDER_NO) {
		TKT_ORDER_NO = tKT_ORDER_NO;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public byte[] getQrcode() {
		return qrcode;
	}
	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}
	public Integer getTkt_ori_price() {
		return tkt_ori_price;
	}
	public void setTkt_ori_price(Integer tkt_ori_price) {
		this.tkt_ori_price = tkt_ori_price;
	}
	
}
