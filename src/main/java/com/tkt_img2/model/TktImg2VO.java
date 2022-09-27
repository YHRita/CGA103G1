package com.tkt_img2.model;

import java.io.Serializable;

public class TktImg2VO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer tkt_img_no;
	private Integer tkt_no;
	private byte[] tkt_img;
	
	public TktImg2VO() {}
	
	public TktImg2VO(byte[] tkt_img) {
		super();
		this.tkt_img = tkt_img;
	}
	
	public TktImg2VO(Integer tkt_img_no, Integer tkt_no, byte[] tkt_img) {
		super();
		this.tkt_img_no = tkt_img_no;
		this.tkt_no = tkt_no;
		this.tkt_img = tkt_img;
	}

	public Integer getTkt_img_no() {
		return tkt_img_no;
	}

	public void setTkt_img_no(Integer tkt_img_no) {
		this.tkt_img_no = tkt_img_no;
	}

	public Integer getTkt_no() {
		return tkt_no;
	}

	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}

	public byte[] getTkt_img() {
		return tkt_img;
	}

	public void setTkt_img(byte[] tkt_img) {
		this.tkt_img = tkt_img;
	}

	
	// join CoupVO
//	public TktVO getTktVO() {
//		TktService tktSrv = new TktService();
//		TktVO tktVO = tktSrv.getOneTkt(tkt_no);
//		return tktVO;
//	}

}
