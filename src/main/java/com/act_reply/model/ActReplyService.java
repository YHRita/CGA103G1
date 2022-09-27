package com.act_reply.model;

import java.util.List;

public class ActReplyService {

	private I_ActReplyDAO dao;

	public ActReplyService() {
		dao = new ActReplyDAO();
	}

	public ActReplyVO insert(Integer act_no, String reply_content,
			Integer mem_no,java.sql.Date reply_time) {
		
		ActReplyVO VO = new ActReplyVO();
	
		VO.setAct_no(act_no);
		VO.setReply_content(reply_content);
		VO.setMem_no(mem_no);
		VO.setReply_time(reply_time);
		dao.insert(VO);
		
		return VO;
	}
	
	public void deleteAC(Integer act_reply_no) {
		dao.delete(act_reply_no);
	}
	
	public List<ActReplyVO> getALL(){
		return dao.getall();
	}
}
