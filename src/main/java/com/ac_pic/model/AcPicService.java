package com.ac_pic.model;

import java.util.List;

public class AcPicService {
	private I_AcPicDAO dao;

	public AcPicService() {
		dao = new AcPicDAO();
	}

	
	public void insertImage(AcPicVO acPicVO) {
		dao.insert(acPicVO);
	}
	
	public void updateImage(AcPicVO acPicVO) {
		dao.update(acPicVO);
	}
	
	
	public AcPicVO getOne(Integer ac_pic_no) {
		return dao.getOne(ac_pic_no);
	}
	
	public List<AcPicVO> getAll() {
		return dao.getAll();
	}

	public List<AcPicVO> getOneByAcNo(Integer acNo){
		return dao.getOneByAcNo(acNo);
	}
}
