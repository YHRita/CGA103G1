package com.rm_pic.model;

import java.util.List;



public interface I_RmPicDAO {
	
	public RmPicVO insert(RmPicVO rmPicVO);   // 新增

//	public void delete(RmPicVO rmPicVO);      // 修改

	public void delete(Integer rm_pic_no);    // 刪除
	
	public RmPicVO findByPrimaryKey(Integer rm_pic_no); // 查詢一筆
	
	public List<RmPicVO> getAll();  // 查詢全部

	public List<RmPicVO> getAllByType(Integer rm_type_no);

	
}