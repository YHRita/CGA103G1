package com.ac.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ac")
public class AcVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ac_no", updatable = false, insertable = false)
	private Integer ac_no;
	@Column(name = "mem_no", updatable = false)
	private Integer mem_no;
	@Column(name = "ac_type_no")
	private Integer ac_type_no;
	@Column(name = "ac_title")
	private String ac_title;
	@Column(name = "ac_content")
	private String ac_content;
	@Column(name = "ac_time")
	private LocalDateTime ac_time;
	@Column(name = "ac_update")
	private Integer ac_update;
	
	public AcVO() {
	}

	public AcVO(Integer ac_no, Integer mem_no, Integer ac_type_no, String ac_title, String ac_content,
			LocalDateTime ac_time, Integer ac_update) {
		this.ac_no = ac_no;
		this.mem_no = mem_no;
		this.ac_type_no = ac_type_no;
		this.ac_title = ac_title;
		this.ac_content = ac_content;
		this.ac_time = ac_time;
		this.ac_update = ac_update;
	}

	public Integer getAc_no() {
		return ac_no;
	}

	public void setAc_no(Integer ac_no) {
		this.ac_no = ac_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getAc_type_no() {
		return ac_type_no;
	}

	public void setAc_type_no(Integer ac_type_no) {
		this.ac_type_no = ac_type_no;
	}

	public String getAc_title() {
		return ac_title;
	}

	public void setAc_title(String ac_title) {
		this.ac_title = ac_title;
	}

	public String getAc_content() {
		return ac_content;
	}

	public void setAc_content(String ac_content) {
		this.ac_content = ac_content;
	}

	public LocalDateTime getAc_time() {
		return ac_time;
	}

	public void setAc_time(LocalDateTime ac_time) {
		this.ac_time = ac_time;
	}

	public Integer getAc_update() {
		return ac_update;
	}

	public void setAc_update(Integer ac_update) {
		this.ac_update = ac_update;
	}

	@Override
	public String toString() {
		return "AcVo [ac_no=" + ac_no + ", mem_no=" + mem_no + ", ac_type_no=" + ac_type_no + ", ac_title=" + ac_title
				+ ", ac_content=" + ac_content + ", ac_time=" + ac_time + ", ac_update=" + ac_update + "]";
	}
	
}
