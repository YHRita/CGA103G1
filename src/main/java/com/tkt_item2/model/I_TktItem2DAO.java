package com.tkt_item2.model;

import java.util.List;

public interface I_TktItem2DAO {
	public void insert(TktItem2VO tktItem2VO);
//    public void update(TktItem2VO tktItem2VO);

	public List<TktItem2VO> findByPrimaryKey(Integer tkt_order_no);
    public List<TktItem2VO> getAll();
//    public List<TktItem2VO> getTktItemsByOrderNo(Integer tkt_order_no);
    //同時新增票券訂單與訂單明細
    public void insertDetailWithOrder (TktItem2VO tktItem2VO , java.sql.Connection con);
}
