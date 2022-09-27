package com.act_pic.model;

import java.io.Serializable;

public class ActPicVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer act_pic_no;
	private Integer act_no;
	private byte[] act_pic;
	
	public ActPicVO() {
	}
	
	
	
	public ActPicVO(byte[] act_pic) {
		super();
		this.act_pic = act_pic;
	}



	public ActPicVO(Integer act_pic_no, Integer act_no, byte[] act_pic) {
		super();
		this.act_pic_no = act_pic_no;
		this.act_no = act_no;
		this.act_pic = act_pic;
	}

	public Integer getAct_pic_no() {
		return act_pic_no;
	}

	public void setAct_pic_no(Integer act_pic_no) {
		this.act_pic_no = act_pic_no;
	}

	public Integer getAct_no() {
		return act_no;
	}

	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}

	public byte[] getAct_pic() {
		return act_pic;
	}

	public void setAct_pic(byte[] act_pic) {
		this.act_pic = act_pic;
	}

	@Override
	public String toString() {
		return "ActPicVO [活動照片流水號=" + act_pic_no + ", 活動編號=" + act_no + "]";
	}
	
	
}
