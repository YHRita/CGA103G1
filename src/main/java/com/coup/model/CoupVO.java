package com.coup.model;

import java.io.Serializable;
import java.sql.Date;



public class CoupVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer coup_no;
	private String coup_name;
	private String introduce;
	private Integer discount;
	private Date startdate;
	private Date enddate;
	private Integer status;
	
	public CoupVO() {}
	
	public CoupVO(Integer coup_no, String coup_name, String introduce, Integer discount, Date startdate,
			Date enddate, Integer status) {
		super();
		this.coup_no = coup_no;
		this.coup_name = coup_name;
		this.introduce = introduce;
		this.discount = discount;
		this.startdate = startdate;
		this.enddate = enddate;
		this.status = status;
	}

	public Integer getCoup_no() {
		return coup_no;
	}

	public void setCoup_no(Integer coup_no) {
		this.coup_no = coup_no;
	}

	public String getCoup_name() {
		return coup_name;
	}

	public void setCoup_name(String coup_name) {
		this.coup_name = coup_name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CoupVO [coup_no=" + coup_no + ", coup_name=" + coup_name + ", introduce=" + introduce + ", discount="
				+ discount + ", startdate=" + startdate + ", enddate=" + enddate + ", status=" + status + "]";
	}
	
	
	
}
