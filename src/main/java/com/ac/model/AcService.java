package com.ac.model;

import java.util.List;

import com.util.CoreService;

public interface AcService extends CoreService {
	
	 Integer createAc(AcVO acVo);

	 Integer alterAc(AcVO acVo);
	
	 AcVO getOneAcById(Integer id);
	
	 List<AcVO> getAll();

}
