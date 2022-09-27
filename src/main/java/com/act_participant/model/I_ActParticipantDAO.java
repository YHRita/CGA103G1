package com.act_participant.model;

import java.util.List;

public interface I_ActParticipantDAO {

	
	// 新增 活動參與者
	void insert(ActParticipantVO actParticipantVO);
	
	// 更新、修改 活動參與者 報名狀態 ? 審核 活動參與者 ?
	void update(ActParticipantVO actParticipantVO);
	
	// 取得 所有 活動參與者
	List<ActParticipantVO> getAll();
	
	// 取得該揪團活動之所有報名參團者
	List<ActParticipantVO> getOneOfAll(Integer actNo);
	
	// 取得 活動參與者取得自身報名狀態 ActParticipantEnrollStatus
	List<ActParticipantVO> getAPES(Integer memNo);
		

}
