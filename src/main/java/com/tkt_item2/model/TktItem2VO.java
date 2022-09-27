package com.tkt_item2.model;


import com.tkt.model.TktService;
import com.tkt.model.TktVO;

public class TktItem2VO {
	private Integer tkt_no;
	private Integer tkt_order_no;
	private Integer amount;
	private byte[] qrcode;
	private Integer tkt_ori_price;

	public TktItem2VO() {
	}

	public TktItem2VO(Integer tkt_no, Integer tkt_order_no, Integer amount, byte[] qrcode, Integer tkt_ori_price) {
		super();
		this.tkt_no = tkt_no;
		this.tkt_order_no = tkt_order_no;
		this.amount = amount;
		this.qrcode = qrcode;
		this.tkt_ori_price = tkt_ori_price;
	}

	public Integer getTkt_no() {
		return tkt_no;
	}

	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}

	public Integer getTkt_order_no() {
		return tkt_order_no;
	}

	public void setTkt_order_no(Integer tkt_order_no) {
		this.tkt_order_no = tkt_order_no;
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

	// join TktVO
	public TktVO getTktVO() {
		TktService tktSrv = new TktService();
		TktVO tktVO = tktSrv.getOneTkt(tkt_no);
		return tktVO;
	}
}
