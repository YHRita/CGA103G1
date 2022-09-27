package com.rm_order.model;

import java.sql.Date;
import java.util.List;

import com.rm_order_list.model.RmOrderListVO;

public class RmOrderService {
	private I_RmOrderDAO dao;

	public RmOrderService() {
		dao = new RmOrderDAO();
	}
	
	public RmOrderVO insert(Integer mem_no, Integer store_no, Integer rm_order_status, Integer rm_charge) {
		RmOrderVO rm = new RmOrderVO();
		rm.setMem_no(mem_no);
		rm.setStore_no(store_no);
		rm.setRm_order_status(rm_order_status);
		rm.setRm_charge(rm_charge);
		return dao.insert(rm);
	}
	
	public Integer insertWithLists(Integer mem_no, Integer store_no, Integer rm_order_status, Integer rm_charge, List<RmOrderListVO> list) {
		RmOrderVO rm = new RmOrderVO();
		rm.setMem_no(mem_no);
		rm.setStore_no(store_no);
		rm.setRm_order_status(rm_order_status);
		rm.setRm_charge(rm_charge);
		
		return dao.insertWithLists(rm, list);
	}
	
	public List<RmOrderVO> getAllRmOrder() {
		return dao.getAll();
	}
	
	public List<RmOrderVO> getAllStatus(Integer rm_order_status) {
		return dao.getAllStatus(rm_order_status);
	}
	
	public List<RmOrderVO> getStoreStatus(Integer store_no, Integer rm_order_status) {
		return dao.getStoreStatus(store_no, rm_order_status);
	}
	
	public List<RmOrderVO> getAllByStore(Integer store_no) {
		return dao.getAllByStore(store_no);
	}
	
	public List<RmOrderVO> getAllByMem(Integer mem_no) {
		return dao.getAllByMem(mem_no);
	}
	
	public RmOrderVO getOne(Integer rm_order_no) {
		return dao.getOne(rm_order_no);
	}

	public void checkIn(Integer rm_order_no) {
		dao.checkIn(rm_order_no);
	}
	
	public void checkOut(Integer rm_order_no) {
		dao.checkOut(rm_order_no);
	}

	public RmOrderVO cancel(Integer rm_order_no) {

		RmOrderVO rmOrderVO = new RmOrderVO();
		rmOrderVO.setRm_order_status(2);
		rmOrderVO.setRm_charge(0);
		rmOrderVO.setRm_order_no(rm_order_no);

		dao.updateStatus(rmOrderVO);
		return rmOrderVO;
	}
	
	public void overdue() {
		dao.overdue();
	}
}