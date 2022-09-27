package com.auth_fun.model;

import java.util.List;

public class AuthFunService {
	private I_AuthFunDAO dao;
	
	public AuthFunService() {
		dao = new AuthFunDAO();
	}
	
	public AuthFunVO addAuthFun(Integer fun_no, String fun_name) {
		AuthFunVO authFunVO = new AuthFunVO();
		
		authFunVO.setFun_no(fun_no);
		authFunVO.setFun_name(fun_name);
		dao.insert(authFunVO);

		return authFunVO;
		
	}
	
	public void deleteAuthFun(Integer fun_no) {
		dao.delete(fun_no);
	}
	
	
	public AuthFunVO updateAuthFunVO(String fun_name,Integer fun_no) {
		AuthFunVO authFunVO = new AuthFunVO();
		
		authFunVO.setFun_name(fun_name);
		authFunVO.setFun_no(fun_no);
		dao.update(authFunVO);

		return authFunVO;
}
	public AuthFunVO getOneAuthFun(Integer fun_no) {
		return dao.findByPrimaryKey(fun_no);
}
	public List<AuthFunVO> getAll() {
		return dao.getAll();
}
}