package com.act_type.model;

import java.util.List;

public class ActTypeService {
	
	private I_ActTypeDAO dao;
	
	public ActTypeService() {
		dao = new ActTypeDAO();
	}
			
	// 建立 新的活動類別
	public void createNewActType(String actTypeName) {
		ActTypeVO actTypeVO = new ActTypeVO();
		actTypeVO.setAct_type_name(actTypeName);
		dao.insert(actTypeVO);
	}
		
	// 取得 所有活動類別
	public List<ActTypeVO> getAll() {
		return dao.getAll();
	}
	
}
