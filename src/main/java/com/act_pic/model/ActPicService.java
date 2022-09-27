package com.act_pic.model;

import java.util.List;
import java.util.stream.Collectors;

public class ActPicService {
	
	private I_ActPicDAO dao;
	
	public ActPicService() {
		dao = new ActPicDAO();
	}

	// 新增 活動照片
	public void uploadActPic(ActPicVO actPicVO) {
		ActPicVO actPic = new ActPicVO();
		actPic.setAct_pic(actPicVO.getAct_pic());
		actPic.setAct_no(actPicVO.getAct_no());
		dao.insert(actPic);
	}

	// 更新、修改 活動照片
	public void alterActPic(ActPicVO actPicVO) {
		ActPicVO actPic = new ActPicVO();
		actPic.setAct_pic(actPicVO.getAct_pic());
		actPic.setAct_no(actPicVO.getAct_no());
		dao.update(actPic);
	}
	
	// 取得 活動 照片
	public List<ActPicVO> getOneActPic(Integer actNo) {	
		return dao.findActPic(actNo);
	}
	
	public List<ActPicVO> getAll() {
		return dao.getAll();
	}
	
	
}
