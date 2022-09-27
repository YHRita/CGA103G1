package com.tkt.model;

import java.io.Serializable;
import java.sql.Date;

public class TktVO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer tkt_no;
	private String tkt_name;
	private Integer original_amount;
	private Integer price;
	private Date tkt_startdate;
	private Date tkt_enddate;
	private String locate;
	private String instruction;
	private String address;
	private String notice;
	private String howuse;
	private String canxpolicy;
	private Integer tkt_status;
	private Integer sold_amount;
	private Integer kind;
	
	public TktVO() {}
	
	public TktVO(Integer tkt_no, String tkt_name, Integer original_amount, Integer price, Date tkt_startdate,
			Date tkt_enddate, String locate, String instruction, String address, String notice, String howuse,
			String canxpolicy, Integer tkt_status, Integer sold_amount, Integer kind) {
		super();
		this.tkt_no = tkt_no;
		this.tkt_name = tkt_name;
		this.original_amount = original_amount;
		this.price = price;
		this.tkt_startdate = tkt_startdate;
		this.tkt_enddate = tkt_enddate;
		this.locate = locate;
		this.instruction = instruction;
		this.address = address;
		this.notice = notice;
		this.howuse = howuse;
		this.canxpolicy = canxpolicy;
		this.tkt_status = tkt_status;
		this.sold_amount = sold_amount;
		this.kind = kind;
	}

	public Integer getTkt_no() {
		return tkt_no;
	}

	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}

	public String getTkt_name() {
		return tkt_name;
	}

	public void setTkt_name(String tkt_name) {
		this.tkt_name = tkt_name;
	}

	public Integer getOriginal_amount() {
		return original_amount;
	}

	public void setOriginal_amount(Integer original_amount) {
		this.original_amount = original_amount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getTkt_startdate() {
		return tkt_startdate;
	}

	public void setTkt_startdate(Date tkt_startdate) {
		this.tkt_startdate = tkt_startdate;
	}

	public Date getTkt_enddate() {
		return tkt_enddate;
	}

	public void setTkt_enddate(Date tkt_enddate) {
		this.tkt_enddate = tkt_enddate;
	}

	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getHowuse() {
		return howuse;
	}

	public void setHowuse(String howuse) {
		this.howuse = howuse;
	}

	public String getCanxpolicy() {
		return canxpolicy;
	}

	public void setCanxpolicy(String canxpolicy) {
		this.canxpolicy = canxpolicy;
	}

	public Integer getTkt_status() {
		return tkt_status;
	}

	public void setTkt_status(Integer tkt_status) {
		this.tkt_status = tkt_status;
	}

	public Integer getSold_amount() {
		return sold_amount;
	}

	public void setSold_amount(Integer sold_amount) {
		this.sold_amount = sold_amount;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "TktVO [tkt_no=" + tkt_no + ", tkt_name=" + tkt_name + ", original_amount=" + original_amount
				+ ", price=" + price + ", tkt_startdate=" + tkt_startdate + ", tkt_enddate=" + tkt_enddate + ", locate="
				+ locate + ", instruction=" + instruction + ", address=" + address + ", notice=" + notice + ", howuse="
				+ howuse + ", canxpolicy=" + canxpolicy + ", tkt_status=" + tkt_status + ", sold_amount=" + sold_amount
				+ ", kind=" + kind + "]";
	}


	
	
	
}
