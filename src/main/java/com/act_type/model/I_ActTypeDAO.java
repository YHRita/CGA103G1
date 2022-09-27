package com.act_type.model;

import java.util.List;

public interface I_ActTypeDAO {
		
	// 建立 新的活動類別
	void insert(ActTypeVO actTypeVO);
	
	// 更新、調整 活動類別編號
//	void update(ActTypeVO actTypeVO);
	
	// 取得 所有活動類別
	List<ActTypeVO> getAll();
	
//	// 取得其中之一 活動類別
//	void getOneByPk(ActTypeVO actTypeVO);
}
