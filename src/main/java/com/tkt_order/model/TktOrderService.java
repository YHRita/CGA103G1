package com.tkt_order.model;

import java.sql.Date;
import java.util.List;

public class TktOrderService {
	
	private I_TktOrderDAO dao;
	
	public TktOrderService() {
		dao = new TktOrderDAO();
	}
	public TktOrderVO addTktOrder(
			
			Integer    mem_no         ,
			Integer    mem_coup_no    ,
			Integer    tkt_no         ,
			Integer    original_price ,
			Date    orderdate         ,
			Integer    TTL_PRICE      
			
			) {
		TktOrderVO tktOrderVO = new TktOrderVO();
		
		tktOrderVO.setMem_no(mem_no);
		tktOrderVO.setMem_coup_no(mem_coup_no);
		tktOrderVO.setTkt_no(tkt_no);
		tktOrderVO.setTkt_no(tkt_no);
		tktOrderVO.setOriginal_price(original_price);
		tktOrderVO.setOrderdate(orderdate);
		tktOrderVO.setTTL_PRICE(TTL_PRICE);
		dao.insert(tktOrderVO);
		
		return tktOrderVO;
		
	}
	public TktOrderVO updateTktOrder(
			Integer tkt_order_no      ,
			Integer    mem_no         ,
			Integer    mem_coup_no    ,
			Integer    tkt_no         ,
			Integer    original_price ,
			Date    orderdate         ,
			Integer    TTL_PRICE      

			
			) {
		TktOrderVO tktOrderVO = new TktOrderVO();
		
		tktOrderVO.setMem_no(mem_no);
		tktOrderVO.setMem_coup_no(mem_coup_no);
		tktOrderVO.setTkt_no(tkt_no);
		tktOrderVO.setOriginal_price(original_price);
		tktOrderVO.setOrderdate(orderdate);
		tktOrderVO.setTTL_PRICE(TTL_PRICE);
		return tktOrderVO;
	}
	public void deleteTktOrder(Integer tkt_order_no) {
		dao.delete(tkt_order_no);
		
	}
	public TktOrderVO getOneTktOrder(Integer tkt_order_no) {
		return dao.findByPrimaryKey(tkt_order_no);
	}
	public List<TktOrderVO>getAll(){
		return dao.getAll();
	}
}
