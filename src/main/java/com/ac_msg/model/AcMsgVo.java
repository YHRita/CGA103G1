package com.ac_msg.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ac_msg")
public class AcMsgVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ac_msg_no", insertable = false)
	private Integer ac_msg_no;
	@Column(name = "emp_no")
	private Integer emp_no;
	@Column(name = "mem_no")
	private Integer mem_no;
	@Column(name = "ac_no")
	private Integer ac_no;
	@Column(name = "ac_msg_reason")
	private Integer ac_msg_reason;
	@Column(name = "ac_msg_done_time")
	private LocalDateTime ac_msg_done_time;
	@Column(name = "ac_msg_status")
	private Integer ac_msg_status;
	@Column(name = "ac_msg_note")
	private String ac_msg_note;
	
	public AcMsgVo() {
	}

	public AcMsgVo(Integer ac_msg_no, Integer emp_no, Integer mem_no, Integer ac_no, Integer ac_msg_reason,
			LocalDateTime ac_msg_done_time, Integer ac_msg_status, String ac_msg_note) {
		this.ac_msg_no = ac_msg_no;
		this.emp_no = emp_no;
		this.mem_no = mem_no;
		this.ac_no = ac_no;
		this.ac_msg_reason = ac_msg_reason;
		this.ac_msg_done_time = ac_msg_done_time;
		this.ac_msg_status = ac_msg_status;
		this.ac_msg_note = ac_msg_note;
	}

	@Override
	public String toString() {
		return "AcMsgVo [ac_msg_no=" + ac_msg_no + ", emp_no=" + emp_no + ", mem_no=" + mem_no + ", ac_no=" + ac_no
				+ ", ac_msg_reason=" + ac_msg_reason + ", ac_msg_done_time=" + ac_msg_done_time + ", ac_msg_status="
				+ ac_msg_status + ", ac_msg_note=" + ac_msg_note + "]";
	}

	
	
	
}
