package com.mem.model;

import java.util.List;

public interface I_MemDAO {
	public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void updateMem(MemVO memVO);
    public void delete(Integer memno);
    public MemVO findByPrimaryKey(Integer memno);
    public List<MemVO> getAll();
    MemVO getOneBymail(String mem_email);
    public void upatePassword(MemVO memVO);
    public void updateStatus(MemVO mem_no);
    public MemVO login(String mem_email, String mem_pwd);
    
}
