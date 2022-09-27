package com.rm_order.model;

import java.util.List;

import com.rm_order_list.model.RmOrderListVO;

public interface I_RmOrderDAO {

	public RmOrderVO insert(RmOrderVO rmOrderVO); // 新增

	public void update(RmOrderVO rmOrderVO);

	public void updateStatus(RmOrderVO rmOrderVO); // 修改狀態 金額(取消金額=0)   ps:訂房預約->已預定數量-1

	public RmOrderVO getOne(Integer rm_order_no); // 查詢一筆

	public List<RmOrderVO> getAll(); // 查詢全部
	
	public List<RmOrderVO> getAllStatus(Integer rm_order_no); // 查詢各狀態筆數

	public void checkIn(Integer rm_order_no);  // CHECKIN
	
	public void checkOut(Integer rm_order_no);  // CHECKOUT

	public List<RmOrderVO> getAllByStore(Integer store_no); // 該廠商所有訂單
	
	public List<RmOrderVO> getStoreStatus(Integer store_no, Integer rm_order_status); // 廠商編號找訂單狀態

    public Integer insertWithLists(RmOrderVO rmOrderVO , List<RmOrderListVO> list); // 同時新增訂單與多筆訂單明細
    
    public void overdue(); // 修改逾期訂單狀態為已完成(排程器用)

	public List<RmOrderVO> getAllByMem(Integer mem_no); // 該會員所有訂單
}
