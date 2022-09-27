package com.rm_order_list.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;


public class RmOrderListService {
	
	private I_RmOrderListDAO dao;
	
	public RmOrderListService() {
		dao = new RmOrderListDAO();
	}
	
	public RmOrderListVO addRmOrderList(Integer rm_type_no,Integer rm_order_no, Integer rm_amount,
			Integer rm_price, String rm_check_in)
	{
		RmOrderListVO rmOrderListVO = new RmOrderListVO();
		rmOrderListVO.setRm_type_no(rm_type_no);
		rmOrderListVO.setRm_order_no(rm_order_no);
		rmOrderListVO.setRm_amount(rm_amount);
		rmOrderListVO.setRm_price(rm_price);
		rmOrderListVO.setRm_check_in(rm_check_in);
		dao.insert(rmOrderListVO);
		
		return rmOrderListVO;
	}
	
	public RmOrderListVO updateRmOrderList(Integer rm_type_no,Integer rm_order_no, Integer rm_amount,
			Integer rm_price, Date arrival_date, Date departure_date, String rm_check_in, Integer rm_order_list_no)
	{
		
		RmOrderListVO rmOrderListVO = new RmOrderListVO();
		
		rmOrderListVO.setRm_type_no(rm_type_no);
		rmOrderListVO.setRm_order_no(rm_order_no);
		rmOrderListVO.setRm_amount(rm_amount);
		rmOrderListVO.setRm_price(rm_price);
		rmOrderListVO.setArrival_date(arrival_date);
		rmOrderListVO.setDeparture_date(departure_date);
		rmOrderListVO.setRm_check_in(rm_check_in);
		rmOrderListVO.setRm_order_list_no(rm_order_list_no);
		
		dao.update(rmOrderListVO);
		return rmOrderListVO;
	}
	
	public RmOrderListVO changeROL(Integer rm_type_no, Integer rm_amount, Integer rm_price
			, Date arrival_date, Date departure_date, String rm_check_in, Integer rm_order_list_no)
	{
		RmOrderListVO rmOrderListVO = new RmOrderListVO();
		
		rmOrderListVO.setRm_type_no(rm_type_no);
		rmOrderListVO.setRm_amount(rm_amount);
		rmOrderListVO.setRm_price(rm_price);
		rmOrderListVO.setArrival_date(arrival_date);
		rmOrderListVO.setDeparture_date(departure_date);
		rmOrderListVO.setRm_check_in(rm_check_in);
		rmOrderListVO.setRm_order_list_no(rm_order_list_no);
		
		dao.changeROL(rmOrderListVO);
		return rmOrderListVO;
	}
	
	public void deleteRmOrderList(Integer rm_order_list_no) {
		dao.delete(rm_order_list_no);
	}
	
	public RmOrderListVO getOneRmOrderList(Integer rm_order_list_no) {
		return dao.findByPrimaryKey(rm_order_list_no);
	}
	
	public List<RmOrderListVO> getAllByRmOrderNo(Integer rm_order_no) {
		return dao.getAllByRmOrderNo(rm_order_no);
	}
	
	public List<RmOrderListVO> getAll() {
		return dao.getAll();
	}

	public List<RmOrderListVO> getCheckOutByStore(Integer store_no) {
		return dao.getCheckOutByStore(store_no);
	}
	
	public List<RmOrderListVO> getCheckInByStore(Integer store_no) {
		return dao.getCheckInByStore(store_no);
	}
	
	public List<RmOrderListVO> getStayByStore(Integer store_no) {
		return dao.getStayByStore(store_no);
	}

}
