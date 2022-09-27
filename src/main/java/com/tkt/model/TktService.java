package com.tkt.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class TktService {
	
	private I_TktDAO dao;
	
	public TktService() {
		dao = new TktDAO();
	}
	
	public TktVO addTkt(String tkt_name, Integer original_amount, Integer price, Date tkt_startdate,
			Date tkt_enddate, String locate, String instruction, String address, String notice, String howuse,
			String canxpolicy, Integer tkt_status, Integer kind) {
		
		TktVO tktVO = new TktVO();
		
		tktVO.setTkt_name(tkt_name);
		tktVO.setOriginal_amount(original_amount);
		tktVO.setPrice(price);
		tktVO.setTkt_startdate(tkt_startdate);
		tktVO.setTkt_enddate(tkt_enddate);
		tktVO.setLocate(locate);
		tktVO.setInstruction(instruction);
		tktVO.setAddress(address);
		tktVO.setNotice(notice);
		tktVO.setHowuse(howuse);
		tktVO.setCanxpolicy(canxpolicy);
		tktVO.setTkt_status(tkt_status);
//		tktVO.setSold_amount(sold_amount);
		tktVO.setKind(kind);
		
		dao.insert(tktVO);
		
		return tktVO;
		
	}
	
	public TktVO updateTkt(Integer tkt_no, String tkt_name, Integer original_amount, Integer price, Date tkt_startdate,
			Date tkt_enddate, String locate, String instruction, String address, String notice, String howuse,
			String canxpolicy, Integer tkt_status, Integer sold_amount, Integer kind) {
		
		TktVO tktVO = new TktVO();
		
		tktVO.setTkt_no(tkt_no);
		tktVO.setTkt_name(tkt_name);
		tktVO.setOriginal_amount(original_amount);
		tktVO.setPrice(price);
		tktVO.setTkt_startdate(tkt_startdate);
		tktVO.setTkt_enddate(tkt_enddate);
		tktVO.setLocate(locate);
		tktVO.setInstruction(instruction);
		tktVO.setAddress(address);
		tktVO.setNotice(notice);
		tktVO.setHowuse(howuse);
		tktVO.setCanxpolicy(canxpolicy);
		tktVO.setTkt_status(tkt_status);
		tktVO.setSold_amount(sold_amount);
		tktVO.setKind(kind);
		
		dao.update(tktVO);
		
		return tktVO;
	}
	
	public TktVO getOneTkt(Integer tkt_no) {
		return dao.findByPrimaryKey(tkt_no);
	}
	
	public List<TktVO> getAll() {
		return dao.getAll();
	}
	
	public List<TktVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<TktVO> getAllByStatus() {
		return dao.getAllByStatus();
	}
	

	public Integer find_tkt_rows() {
		return dao.find_tkt_rows();
	}
}
	