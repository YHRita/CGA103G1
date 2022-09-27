package com.rm_msg.model;

import java.sql.Date;
import java.util.List;

import com.mem.model.MemVO;

public class Rm_msgService {
	private	I_Rm_msgDAO dao; 
	
	public Rm_msgService() {
		dao = new Rm_msgDAO();
	}
	
	public void addRm_msg(
		
		Integer mem_no,
		Integer store_no,
		String rm_msg_reason
		
			) {
		Rm_msgVO rm_msgVO = new Rm_msgVO();
		
		rm_msgVO.setMem_no(mem_no);
		rm_msgVO.setStore_no(store_no);
		rm_msgVO.setRm_msg_reason(rm_msg_reason);
		dao.insert(rm_msgVO);

	}
	
	public Rm_msgVO updateRm_msg(
			Integer rm_msg_no,
			Integer emp_no,			
			Integer store_no,
			Integer rm_msg_status,
			Date rm_msg_donetime
			) {
		Rm_msgVO rm_msgVO = new Rm_msgVO();
		rm_msgVO.setRm_msg_no(rm_msg_no);
		rm_msgVO.setEmp_no(emp_no);
		rm_msgVO.setStore_no(store_no);
		rm_msgVO.setRm_msg_status(rm_msg_status);
		rm_msgVO.setRm_msg_donetime(rm_msg_donetime);
		dao.update(rm_msgVO);
		
		
		return rm_msgVO;
		
	}
	
	
	public void deleteRm_msg(Integer rm_msg_no) {
		dao.delete(rm_msg_no);
	}
	
	public Rm_msgVO getOneByMemNumber(Integer mem_no) {
		
		return	dao.findByMemNumber( mem_no);
	}
	
	public Rm_msgVO getOnByStoreNumber(Integer store_no) {
		return dao .findByStoreNumber(store_no);
	}
	
	
	public List<Rm_msgVO> getAll(){
		return dao.getAll();
	}
	
	public MemVO getOneByMemAcc(String mem_acc) {
		return dao.getOneMemByMemAcc(mem_acc);
	}
	
	public List<Rm_msgVO> getAllMsgByStoreNumber(Integer store_no) {
		return dao.getAllMsgByNumber(store_no);
	}
	
	
}
