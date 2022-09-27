package com.rm_pic.model;

import java.util.List;

import com.auth_fun.model.AuthFunVO;


public class RmPicService {
	
	private I_RmPicDAO dao;
	
	public RmPicService() {
		dao = new RmPicDAO();
	}

	public RmPicVO addRmPic(Integer rm_type_no,byte[] rm_pic) {
		
		RmPicVO rmPicVO = new RmPicVO();
		rmPicVO.setRm_type_no(rm_type_no);
		rmPicVO.setRm_pic(rm_pic);
		
		return dao.insert(rmPicVO);
		
	}

//	public RmPicVO updateRmPic(Integer rm_type_no,byte[] rm_pic) {
//		RmPicVO rmPicVO = new RmPicVO();
//		
//		rmPicVO.setRm_type_no(rm_type_no);
//		rmPicVO.setRm_pic(rm_pic);
//		dao.update(rmPicVO);
//		
//		return rmPicVO;
//	}
	
	public void deleteRmPic(Integer rm_pic_no) {
		dao.delete(rm_pic_no);
	}
	
	
	public List<RmPicVO> getAllByType(Integer rm_type_no) {
		return dao.getAllByType(rm_type_no);
	}
	
	public RmPicVO getOneRmPic(Integer rm_pic_no) {
		return dao.findByPrimaryKey(rm_pic_no);
	}
	

	public List<RmPicVO> getAll() {
		return dao.getAll();
}
	
}	
