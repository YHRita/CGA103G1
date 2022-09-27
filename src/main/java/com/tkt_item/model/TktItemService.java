package com.tkt_item.model;

import java.util.List;

public class TktItemService {

	public I_TktItemDAO dao;

	public TktItemService() {
		dao = new TktItemDAO();
	}

	public TktItemVO addTktItem( Integer TKT_ORDER_NO, Integer amount, byte[] qrcode,
			Integer tkt_ori_price

	) {

		TktItemVO tktItemVO = new TktItemVO();
		tktItemVO.setTKT_ORDER_NO(TKT_ORDER_NO);
		tktItemVO.setAmount(amount);
		tktItemVO.setQrcode(qrcode);
		tktItemVO.setTkt_ori_price(tkt_ori_price);
		dao.insert(tktItemVO);
		

		return tktItemVO;

	}
	public TktItemVO updateTktItem( Integer tkt_no,
	 Integer TKT_ORDER_NO,
	 Integer amount,
	 byte[] qrcode,
	 Integer tkt_ori_price) {
		TktItemVO tktItemVO = new TktItemVO();
		tktItemVO.setTkt_no(tkt_no);
		tktItemVO.setTKT_ORDER_NO(TKT_ORDER_NO);
		tktItemVO.setAmount(amount);
		tktItemVO.setQrcode(qrcode);
		tktItemVO.setTkt_ori_price(tkt_ori_price);
		dao.update(tktItemVO);
		
		return tktItemVO;
	}
	public void deletTktItem(Integer tkt_no) {
		dao.delete(tkt_no);
		
	}
	public TktItemVO getOneTktItem(Integer tkt_no) {
		return dao.findByPrimaryKey(tkt_no);
				
	}
	public List <TktItemVO>getAll(){
		return dao.getAll();
	}
}
