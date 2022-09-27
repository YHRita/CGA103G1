package com.rm_type.model;

import java.sql.Date;
import java.util.List;

public interface I_RmTypeDAO {

	public RmTypeVO insert(RmTypeVO rmtypeVO);  // 新增

	public RmTypeVO update(RmTypeVO rmtypeVO);  // 修改

	public void changeState(Integer rm_type_no, Integer rm_update);   // 房型上下架
	
	public RmTypeVO getOne(Integer rm_pic_no); // 查詢一筆

	public List<RmTypeVO> getAll();  // 查詢全部
	
	public List<RmTypeVO> getAllRsv();  // 查詢全部已上架

	public List<RmTypeVO> getAllByStoreNo(Integer store_no);  // 廠商所有房型
	
	public List<RmTypeVO> getEnoughType(Date arrival_date, Date departure_date, Integer qty, Integer guest, String add);
	
	public List<RmTypeVO> getNotEnoughType(Date arrival_date, Date departure_date, Integer qty, Integer guest);
	
	public List<RmTypeVO> getAllFront();
	
	public List<RmTypeVO> getEnoughTypeByStore(Date arrival_date, Date departure_date, Integer store_no);
}
