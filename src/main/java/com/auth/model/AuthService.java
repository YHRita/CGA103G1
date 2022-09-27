package com.auth.model;

import java.util.List;

import com.auth_fun.model.AuthFunVO;

public class AuthService {
	
	private I_AuthDAO dao;
	
	public AuthService() {
		dao = new AuthDAO();
	}
	
	public AuthVO addAuth(Integer fun_no,Integer emp_no) {
		AuthVO authVO = new AuthVO();
		
		authVO.setFun_no(fun_no);
		authVO.setEmp_no(emp_no);
		dao.insert(authVO);
		
		return authVO;
	}

	public AuthVO updateAuth(Integer fun_no,Integer emp_no) {
		AuthVO authVO = new AuthVO();
		
		authVO.setFun_no(fun_no);
		authVO.setEmp_no(emp_no);
		dao.update(authVO);
		
		return authVO;
	}
	
	
	public AuthVO getOneAuth(Integer fun_no) {
		return dao.findByPrimaryKey(fun_no);
}
	public AuthVO getOneByEmp(Integer emp_no) {
		return dao.findByFk(emp_no);
	}
	public List<AuthVO> getAllAuth() {
		return dao.getAllAuth();
}
	
}
