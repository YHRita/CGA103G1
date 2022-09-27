package com.customer_service.model;

import java.util.List;

public class CustomerServiceService {
	private I_CustomerServiceDAO dao;
	
	public CustomerServiceService() {
		dao = new CustomerServiceDAO();
	}
	
	public CustomerServiceVO insert( Integer store_no, Integer emp_no,
			Integer mem_no, String msg_content
			, Integer msg_direction,java.sql.Date msg_time) {
		CustomerServiceVO VO = new CustomerServiceVO();
		VO.setStore_no(store_no);
		VO.setEmp_no(emp_no);
		VO.setMem_no(mem_no);
		VO.setMsg_content(msg_content);
		VO.setMsg_direction(msg_direction);
		VO.setMsg_time(msg_time);
		
		dao.insert(VO);
		
		return VO;
	}
	
	public List<CustomerServiceVO> findMsg(Integer store_no,Integer mem_no,Integer msg_direction) {
		CustomerServiceVO VO = new CustomerServiceVO();
		VO.setStore_no(store_no);
		VO.setMem_no(mem_no);
		VO.setMsg_direction(msg_direction);
		
		return dao.findMsg(VO);
		
	}
}
