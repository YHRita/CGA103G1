package com.ac_reply.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ac_reply")
public class AcReplyVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ac_reply_no", insertable = false)
	private Integer ac_reply_no;
	@Column(name = "ac_no")
	private Integer ac_no;
	@Column(name = "mem_no")
	private Integer mem_no;
	@Column(name = "reply_content")
	private String reply_content;
	@Column(name = "reply_time")
	private LocalDateTime reply_time;
	@Column(name = "ac_reply_update")
	private Integer ac_reply_update;
	
	public AcReplyVo() {
	}

	public AcReplyVo(Integer ac_reply_no, Integer ac_no, Integer mem_no, String reply_content, LocalDateTime reply_time,
			Integer ac_reply_update) {
		this.ac_reply_no = ac_reply_no;
		this.ac_no = ac_no;
		this.mem_no = mem_no;
		this.reply_content = reply_content;
		this.reply_time = reply_time;
		this.ac_reply_update = ac_reply_update;
	}

	public Integer getAc_reply_no() {
		return ac_reply_no;
	}

	public void setAc_reply_no(Integer ac_reply_no) {
		this.ac_reply_no = ac_reply_no;
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

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public LocalDateTime getReply_time() {
		return reply_time;
	}

	public void setReply_time(LocalDateTime reply_time) {
		this.reply_time = reply_time;
	}

	public Integer getAc_reply_update() {
		return ac_reply_update;
	}

	public void setAc_reply_update(Integer ac_reply_update) {
		this.ac_reply_update = ac_reply_update;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AcReplyVo [ac_reply_no=" + ac_reply_no + ", ac_no=" + ac_no + ", mem_no=" + mem_no + ", reply_content="
				+ reply_content + ", reply_time=" + reply_time + ", ac_reply_update=" + ac_reply_update + "]";
	}
	
}
