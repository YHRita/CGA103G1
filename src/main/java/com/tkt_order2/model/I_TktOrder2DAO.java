package com.tkt_order2.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.tkt_item2.model.TktItem2VO;

public interface I_TktOrder2DAO {
	public void insert(TktOrder2VO tktOrder2VO);
    public void update(TktOrder2VO tktOrder2VO);
    public void delete(Integer tkt_order_no);
    public TktOrder2VO findByPrimaryKey(Integer tkt_order_no);
    public List<TktOrder2VO> getAll();
    //查詢某會員票券訂單的訂單明細(一對多)(回傳 Set)
    public Set<TktItem2VO> getDetailByTktOrdNo(Integer tkt_order_no);
    
    //同時新增訂單與訂單明細，有使用優惠券
    public void insertWithDetail(Connection con, TktOrder2VO tktOrder2VO , List<TktItem2VO> list);
  //同時新增訂單與訂單明細，沒使用優惠券
    public void insertWithDetailWithoutCoup(Connection con, TktOrder2VO tktOrder2VO , List<TktItem2VO> list);
    // 查某會員的最新訂單
    public TktOrder2VO findNewetOrderByMem(Integer mem_no);
    // 查某會員的所有訂單
    public List<TktOrder2VO> findAllOrderByMem(Integer mem_no);
}
