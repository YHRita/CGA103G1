package com.tkt_order2.model;

import java.util.List;
import java.util.Set;

import com.tkt_item2.model.TktItem2VO;

public class TktOrder2Service {
	
	private I_TktOrder2DAO dao;
	
	public TktOrder2Service() {
		dao = new TktOrder2DAO();
	}
	
	public TktOrder2VO addTktOrder(Integer mem_no, Integer mem_coup_no, Integer original_price, Integer ttl_price) {
		
		TktOrder2VO tktOrder2VO = new TktOrder2VO();
		
		tktOrder2VO.setMem_no(mem_no);
		tktOrder2VO.setMem_coup_no(mem_coup_no);
//		tktOrder2VO.setTkt_no(tkt_no);
		tktOrder2VO.setOriginal_price(original_price);
		tktOrder2VO.setTtl_price(ttl_price);
		
		dao.insert(tktOrder2VO);
		
		return tktOrder2VO;
	}
	
	public TktOrder2VO getOneTktOrder(Integer tkt_order_no) {
		return dao.findByPrimaryKey(tkt_order_no);
	}
	
	public List<TktOrder2VO> getAll(){
		return dao.getAll();
	}
	
	public Set<TktItem2VO> getDetailByTktOrdNo(Integer tkt_order_no){
		return dao.getDetailByTktOrdNo(tkt_order_no);
	}
	
	public TktOrder2VO findNewetOrderByMem(Integer mem_no) {
		return dao.findNewetOrderByMem(mem_no);
	}
	
	public List<TktOrder2VO> findAllOrderByMem(Integer mem_no) {
		return dao.findAllOrderByMem(mem_no);
	}
	
//	public TktOrder2VO insertWithDetail(TktOrder2VO tktOrder2VO, List<TktItem2VO> list) {
//		
//		
//		return tktOrder2VO;
//	}

}
