package com.act_rp.model;

import java.util.List;

public class ActRpService {
	
	private I_ActRpDAO dao;
	
	public ActRpService() {
		dao = new ActRpDAO();
	}
	
	public ActRpVO insert(Integer mem_no, Integer act_no,String act_rp_reason ,
			String act_rp_content, java.sql.Date act_rp_time, Integer act_rp_status) {
		ActRpVO VO = new ActRpVO();
		VO.setMem_no(mem_no);
		VO.setAct_no(act_no);
		VO.setAct_rp_reason(act_rp_reason);
		VO.setAct_rp_content(act_rp_content);;
		VO.setAct_rp_time(act_rp_time);
		VO.setAct_rp_status(act_rp_status);
		
		dao.insert(VO);
		
		return VO;
	}
	
	public ActRpVO update(Integer act_rp_no, Integer act_no, String act_rp_reason, Integer emp_no, java.sql.Date act_rp_done_time, 
			Integer act_rp_status ) {
		ActRpVO VO = new ActRpVO();
		VO.setAct_rp_no(act_rp_no);
		VO.setAct_no(act_no);
		VO.setAct_rp_reason(act_rp_reason);
		VO.setEmp_no(emp_no);
		VO.setAct_rp_done_time(act_rp_done_time);
		VO.setAct_rp_status(act_rp_status);
	
		dao.update(VO);
		
		return VO;
	}
	
	public List<ActRpVO> getall() {
		return dao.getall();
	}
	
	public ActRpVO getone(Integer act_rp_no) {
		return dao.getone(act_rp_no);
	}
}
