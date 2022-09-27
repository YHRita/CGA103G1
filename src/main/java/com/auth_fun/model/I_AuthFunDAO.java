package com.auth_fun.model;

import java.util.List;

public interface I_AuthFunDAO {
	
	public void insert (AuthFunVO authFunVO);
	
	public void update(AuthFunVO authFunVO);
	
	public void delete(Integer fun_no);
	
	public AuthFunVO findByPrimaryKey(Integer fun_no);
	
	public List<AuthFunVO> getAll();


	
}
