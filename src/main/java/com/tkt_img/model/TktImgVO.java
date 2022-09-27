package com.tkt_img.model;

public class TktImgVO implements java.io.Serializable{
	private Integer tkt_Img_NO;
	private Integer tkt_NO;
	private byte[] tkt_img;
	
	public TktImgVO() {
		super();
	}

	public TktImgVO(Integer tktImgNO, Integer tktNO, byte[] tktImg) {
		super();
		this.tkt_Img_NO = tktImgNO;
		this.tkt_Img_NO = tktNO;
		this.tkt_img = tktImg;
	}

	public Integer gettktImgNO() {
		return tkt_Img_NO;
	}

	public void settktImgNO(Integer tktImgNO) {
		this.tkt_Img_NO = tktImgNO;
	}

	public Integer gettktNO() {
		return tkt_Img_NO;
	}

	public void settktNO(Integer tktNO) {
		this.tkt_Img_NO = tktNO;
	}

	public byte[] gettktimg() {
		return tkt_img;
	}

	public void settktimg(byte[] tktImg) {
		this.tkt_img = tktImg;
	}
	
	
}
