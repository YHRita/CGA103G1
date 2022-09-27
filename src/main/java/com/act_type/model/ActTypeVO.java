package com.act_type.model;

import java.io.Serializable;

public class ActTypeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer act_type_no;
	private String act_type_name;
	
	public ActTypeVO() {
	}

	public ActTypeVO(Integer act_type_no, String act_type_name) {
		super();
		this.act_type_no = act_type_no;
		this.act_type_name = act_type_name;
	}

	public Integer getAct_type_no() {
		return act_type_no;
	}

	public void setAct_type_no(Integer act_type_no) {
		this.act_type_no = act_type_no;
	}

	public String getAct_type_name() {
		return act_type_name;
	}

	public void setAct_type_name(String act_type_name) {
		this.act_type_name = act_type_name;
	}

	@Override
	public String toString() {
		return "ActTypeVO [活動種類=" + act_type_no + ", 活動名稱=" + act_type_name + "]";
	}
	
	
}
