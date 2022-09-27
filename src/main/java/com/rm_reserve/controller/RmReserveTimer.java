package com.rm_reserve.controller;

import java.util.TimerTask;

import com.rm_order.model.RmOrderService;
import com.rm_reserve.model.RmReserveService;

public class RmReserveTimer extends TimerTask {

	@Override
	public void run() {
		RmReserveService rmReserveSvc = new RmReserveService();
		rmReserveSvc.addRmRsv();     // 新增訂房預約表
		
		rmReserveSvc.deleteRmRsv();  // 刪除過期訂房預約表
		
		RmOrderService rmOrderSvc = new RmOrderService();
		rmOrderSvc.overdue();        // 逾期訂單狀態改為已完成
	}
}
