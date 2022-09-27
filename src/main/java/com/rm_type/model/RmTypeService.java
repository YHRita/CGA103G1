package com.rm_type.model;

import java.sql.Date;
import java.util.List;

import com.emp.model.EmpVO;


public class RmTypeService {
	private I_RmTypeDAO dao;

	public RmTypeService() {
		dao = new RmTypeDAO();
	}
	public RmTypeVO getOneRm(Integer rm_type_no) {
		return dao.getOne(rm_type_no);
	}

	public RmTypeVO insertRm(Integer store_no, String rm_name, Integer rm_total
			, Integer rm_people, Integer rm_price, Integer rm_area, String rm_intro) {
	
		RmTypeVO rm = new RmTypeVO();

		rm.setStore_no(store_no);
		rm.setRm_name(rm_name);
		rm.setRm_total(rm_total);
		rm.setRm_people(rm_people);
		rm.setRm_price(rm_price);
		rm.setRm_area(rm_area);
		rm.setRm_intro(rm_intro);
	
		
		return dao.insert(rm);
	}

	public RmTypeVO updateRm(String rm_name, Integer rm_total
			, Integer rm_people, Integer rm_price, Integer rm_area, String rm_intro, Integer rm_update, Integer rm_type_no ,Integer store_no) {
		RmTypeVO rm = new RmTypeVO();

		rm.setRm_name(rm_name);
		rm.setRm_total(rm_total);
		rm.setRm_people(rm_people);
		rm.setRm_price(rm_price);
		rm.setRm_area(rm_area);
		rm.setRm_intro(rm_intro);
		rm.setRm_eval_sum(rm_type_no);
		rm.setRm_update(rm_update);
		rm.setRm_type_no(rm_type_no);
//		rm.setRm_type_no(store_no);
		
		return dao.update(rm);
	}

	
	// 房型上下架
	public RmTypeVO changeState(Integer rm_update, Integer rm_type_no) {
		RmTypeVO rmTypeVO = new RmTypeVO();
		rmTypeVO.setRm_update(rm_update);
		rmTypeVO.setRm_type_no(rm_type_no);
		
		dao.changeState(rm_type_no, rm_update);
		return rmTypeVO; 
		
	}

	public List<RmTypeVO> getAllRm() {
		
		return dao.getAll();
	}
	
	public List<RmTypeVO> getAllByStoreNo(Integer store_no) {
		
		return dao.getAllByStoreNo(store_no);
	}
	
	// 所有已上架房型
	public List<RmTypeVO> getAllRmRsv() {
		
		return dao.getAllRsv();
	}
	
	// 所有篩選後房型(日期 廠商 人數 等等...)
	public List<RmTypeVO> getEnoughType(Date arrival_date, Date departure_date, Integer qty, Integer guest, String add) {
		return dao.getEnoughType(arrival_date, departure_date, qty, guest, add);
	}
	
	public List<RmTypeVO> getEnoughTypeByStore(Date arrival_date, Date departure_date, Integer store_no) {
		return dao.getEnoughTypeByStore(arrival_date, departure_date, store_no);
	}
	
	public List<RmTypeVO> getNotEnoughType(Date arrival_date, Date departure_date, Integer qty, Integer guest) {
		return dao.getNotEnoughType(arrival_date, departure_date, qty, guest);
	}
}
