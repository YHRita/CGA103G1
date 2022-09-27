package com.customer_service.model;

import java.util.List;


public interface I_CustomerServiceDAO {
	// 新增訊息
	public void insert(CustomerServiceVO customerServiceVO);
	
	// 顯示訊息
	public List<CustomerServiceVO> findMsg(CustomerServiceVO customerServiceVO);	
			
}
