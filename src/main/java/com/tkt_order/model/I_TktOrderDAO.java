package com.tkt_order.model;

import java.util.List;

public interface I_TktOrderDAO {
	public void insert(TktOrderVO tktOrderVO );
	public void update(TktOrderVO tktOrderVO) ;
	public void delete(Integer tkt_order_no);
	public TktOrderVO findByPrimaryKey(Integer tkt_order_no);
	public List<TktOrderVO> getAll();
		
	
}
