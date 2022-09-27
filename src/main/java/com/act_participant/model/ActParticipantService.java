package com.act_participant.model;

import java.time.LocalDateTime;
import java.util.List;

public class ActParticipantService {
	
	private I_ActParticipantDAO dao;
	
	public ActParticipantService() {
		dao = new ActParticipantDAO();
	}
	
	// 新增 活動參與者
	public void addActParticipant(Integer act_no, Integer mem_no, LocalDateTime enroll_time) {
		ActParticipantVO actParticipantVO = new ActParticipantVO();
		actParticipantVO.setAct_no(act_no);
		actParticipantVO.setMem_no(mem_no);
		actParticipantVO.setEnroll_time(enroll_time);
		dao.insert(actParticipantVO);
	}
	
	// 更新、修改 活動參與者 報名狀態 、 審核 活動參與者 
	public void alterActParticipantStatus(Integer act_no, Integer mem_no, Integer enroll_status) {
		ActParticipantVO actParticipantVO = new ActParticipantVO();
		actParticipantVO.setAct_no(act_no);
		actParticipantVO.setMem_no(mem_no);
		actParticipantVO.setEnroll_status(enroll_status);
		dao.update(actParticipantVO);
	}
	
	// 取得 所有 報名活動之參與者
	public List<ActParticipantVO> getAll() {
		return dao.getAll();
	}
	
	// 取得該揪團活動之所有報名參團者
	public List<ActParticipantVO> getOneActOfParticipants(Integer actNo) {
		// return dao.getOneOfAll(actNo);
		return dao.getAll()
		.stream()
		.filter(act -> act.getAct_no() == actNo)
		.toList();

	}
	
	// 活動參與者取得自身報名狀態 ActParticipantEnrollStatus
	public List<ActParticipantVO> getParticipantOwnActStatus(Integer memNo) {
		return dao.getAPES(memNo);
	}

}
