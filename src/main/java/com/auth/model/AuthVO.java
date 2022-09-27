package com.auth.model;

import java.io.Serializable;

import com.auth_fun.model.AuthFunService;

public class AuthVO implements Serializable{
	
	private Integer fun_no;
	private Integer emp_no;
	
	
	public AuthVO() {}
	
	public AuthVO(Integer fun_no, Integer emp_no) {
		super();
		this.fun_no = fun_no;
		this.emp_no = emp_no;
	}
	public Integer getFun_no() {
		return fun_no;
	}
	public void setFun_no(Integer fun_no) {
		this.fun_no = fun_no;
	}
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public com.emp.model.EmpVO getEmpVO() {
	    com.emp.model.EmpService empSvc = new com.emp.model.EmpService();
	    com.emp.model.EmpVO empVO = empSvc.getOneEmp(emp_no);
	    return empVO;
    }
	public com.auth_fun.model.AuthFunVO getAuthFunVO() {
	    com.auth_fun.model.AuthFunService authFunSvc = new com.auth_fun.model.AuthFunService();
	    com.auth_fun.model.AuthFunVO authFunVO = authFunSvc.getOneAuthFun(fun_no);
	    return authFunVO;
    }
	
}
