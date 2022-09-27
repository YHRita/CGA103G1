package com.tkt_img2.model;

import java.util.List;



public interface I_TktImg2DAO {
	public TktImg2VO insert(TktImg2VO tktImg2VO); // 新增
	public void update(TktImg2VO tktImg2VO); // 修改
	public void delete(Integer tkt_img_no); //刪除
	public TktImg2VO getOne(Integer tkt_img_no); // 查詢一筆
	public List<TktImg2VO> getAll();
	public List<TktImg2VO> getAllByTktNo(Integer tkt_no);
}
