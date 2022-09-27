package com.tkt.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface I_TktDAO {
	
	public void insert(TktVO tktVO);
    public void update(TktVO tktVO);
//    public void delete(Integer tkt_no);
    public TktVO findByPrimaryKey(Integer tkt_no);
    public List<TktVO> getAll();
//    public void updateSoldAmount(TktVO tktVO);
    public void updateSoldAmount(Integer redisCount, Integer tkt_no, Connection con);
//    public void updateOriAmount(TktVO tktVO);
    public void updateOriAmount(Integer tkt_no, Connection con);
//    public List<TktVO> getLocate(String locate);
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<TktVO> getAll(Map<String, String[]> map); 
    public List<TktVO> getAllByStatus();
    public Integer find_tkt_rows();
}
