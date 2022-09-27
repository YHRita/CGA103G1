package com.act_reply.model;

import java.util.List;


public interface I_ActReplyDAO {

	//新增留言
	public void insert(ActReplyVO actReplyVO);
	//刪除留言
	public void delete(Integer act_reply_no);
	//顯示留言
	public List<ActReplyVO> getall();
	
}
