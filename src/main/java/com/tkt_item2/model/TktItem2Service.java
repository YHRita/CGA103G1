package com.tkt_item2.model;

import java.util.List;

public class TktItem2Service {
	
	private I_TktItem2DAO dao;
	
	public TktItem2Service() {
		dao = new TktItem2DAO();
	}
	
	public TktItem2VO addTktDetail(Integer tkt_no, Integer tkt_order_no, Integer amount, byte[] qrcode, Integer tkt_ori_price) {
		TktItem2VO tktItem2VO = new TktItem2VO();
		
		tktItem2VO.setTkt_no(tkt_no);
		tktItem2VO.setTkt_order_no(tkt_order_no);
		tktItem2VO.setAmount(amount);
		tktItem2VO.setQrcode(qrcode);
		tktItem2VO.setTkt_ori_price(tkt_ori_price);
		
		dao.insert(tktItem2VO);
		
		return tktItem2VO;
		
	}
	
	public List<TktItem2VO> findByTktOrderNo(Integer tkt_order_no) {
		return dao.findByPrimaryKey(tkt_order_no);
	}
	
//	public TktItem2VO getOneTtkDetail(Integer tkt_no) {
//		return dao.findByPrimaryKey(tkt_no);
//	}
	
	public List<TktItem2VO> getAll() {
		return dao.getAll();
	}
	
}
