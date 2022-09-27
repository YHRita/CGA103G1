package com.act_participant.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActParticipantVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer act_no;
	private Integer mem_no;
	private LocalDateTime enroll_time;
	private Integer enroll_status;
	
	public ActParticipantVO() {
	}

	public ActParticipantVO(Integer act_no, Integer mem_no, LocalDateTime enroll_time, Integer enroll_status) {
		this.act_no = act_no;
		this.mem_no = mem_no;
		this.enroll_time = enroll_time;
		this.enroll_status = enroll_status;
	}
	

	public Integer getAct_no() {
		return act_no;
	}

	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public LocalDateTime getEnroll_time() {
		return enroll_time;
	}

	public void setEnroll_time(LocalDateTime enroll_time) {
		this.enroll_time = enroll_time;
	}

	public Integer getEnroll_status() {
		return enroll_status;
	}

	public void setEnroll_status(Integer enroll_status) {
		this.enroll_status = enroll_status;
	}

	@Override
	public String toString() {
		return "ActParticipantVO [活動編號=" + act_no + ", 會員編號=" + mem_no + ", 報名時間=" + enroll_time
				+ ", 報名狀態=" + enroll_status + "]";
	}
	
	
	
	
}
