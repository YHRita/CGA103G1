package com.rm_reserve.model;

import java.sql.Date;
import java.util.List;

public class RmReserveService {

	private I_RmReserveDAO dao;
	
	public RmReserveService() {
		dao = new RmReserveDAO();
	}
	
	public void addRmRsv() {
		dao.insert();
	}
	
	public void reserveRmRsv(Integer qty, Integer rm_type_no, Date arrival_date, Date departure_date) {
		dao.reserve(qty, rm_type_no, arrival_date, departure_date);
	}
	
	public void cancelRmRsv(Integer qty, Integer rm_type_no, Date arrival_date, Date departure_date) {
		dao.cancel(qty, rm_type_no, arrival_date, departure_date);
	}
	
	public void checkOutEarly(Integer rm_type_no, Date departure_date) {
		dao.checkOutEarly(rm_type_no, departure_date);
	}
	
	public void deleteRmRsv() {
		dao.delete();
	}
	
	
	public RmReserveVO getOneRmReserve(Integer serial_no) {
		return dao.findByPrimaryKey(serial_no);
}
	
	public RmReserveVO getOneByRm(Integer rm_type_no) {
		return dao.getOneByRm(rm_type_no);
	}
	
	public List<RmReserveVO> getAll() {
		return dao.getAll();
}
}
