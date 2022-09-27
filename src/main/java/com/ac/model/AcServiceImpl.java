package com.ac.model;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.CoreService;

public class AcServiceImpl implements AcService {
	
	private AcDAO dao;
	
	public AcServiceImpl() {
		dao =  new AcDAOImpl();
	}
	
	@Override
	public Integer createAc(AcVO acVo) {
		AcVO ac = new AcVO();
		ac.setAc_no(acVo.getAc_no());
		ac.setMem_no(acVo.getMem_no());
		ac.setAc_title(acVo.getAc_title());
		ac.setAc_content(acVo.getAc_content());
		ac.setAc_type_no(acVo.getAc_type_no());
		return dao.insert(acVo);
	}

	@Override
	public Integer alterAc(AcVO acVo) {
		AcVO ac = new AcVO();
		ac.setAc_title(acVo.getAc_title());
		ac.setAc_content(acVo.getAc_content());
		ac.setAc_time(acVo.getAc_time());
		ac.setAc_type_no(acVo.getAc_type_no());
		return dao.update(acVo);
	}
	
	@Override
	public AcVO getOneAcById(Integer id) {
		return dao.getOneById(id);
	}

	@Override
	public List<AcVO> getAll(){
		return dao.getAll();
	}
	

}
