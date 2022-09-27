package com.coup.model;

import java.sql.Date;
import java.util.List;

public interface I_CoupDAO {
	
	public void insert(CoupVO coupVO);
    public void update(CoupVO coupVO);
    public void updateStatus(Integer coup_no);
    public CoupVO findByPrimaryKey(Integer coup_no);
    public List<CoupVO> getAll();
    public List<CoupVO> getByEndDate(Date enddate);
    
//  public void delete(Integer coupVO);
    
}
