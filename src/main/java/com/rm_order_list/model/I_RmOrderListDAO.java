package com.rm_order_list.model;

import java.util.List;

public interface I_RmOrderListDAO {
	public RmOrderListVO insert(RmOrderListVO rmOrderListVO);
    public void update(RmOrderListVO rmOrderListVO);
    public void delete(Integer rm_order_list_no);
    public RmOrderListVO findByPrimaryKey(Integer rm_order_list_no);
    public List<RmOrderListVO> getAll();
	public List<RmOrderListVO> getAllByRmOrderNo(Integer rm_order_no);
	public void changeROL(RmOrderListVO rmOrderListVO);
	public List<RmOrderListVO> getAllByRmTypeNo(Integer rm_type_no);
	public List<RmOrderListVO> getCheckOutByStore(Integer store_no);  // 該廠商今日待CheckOut 訂單明細
	public void insert2 (RmOrderListVO rmOrderListVO , java.sql.Connection con);  // 建立訂單自動新增訂單明細用
	public List<RmOrderListVO> getStayByStore(Integer store_no);  // 該廠商入住中 訂單明細
	public List<RmOrderListVO> getCheckInByStore(Integer store_no); // 該廠商今日待CheckIn訂單
	Integer addRmOrderList(RmOrderListVO rmOrderListVO, List<RmOrderListVO> list);
}
