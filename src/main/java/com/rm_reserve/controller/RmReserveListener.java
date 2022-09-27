package com.rm_reserve.controller;


import java.util.Timer;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;





@WebListener
public class RmReserveListener implements ServletContextListener {
	Timer timer;
	private Logger logger = Logger.getLogger(RmReserveServlet.class.getName());
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("***********************排程器啟動**************************");
		timer = new Timer();
		logger.info("新增訂房預約表");
		logger.info("刪除過期訂房預約表");
		logger.info("逾期訂單狀態改為已完成");
		timer.schedule(new RmReserveTimer(), 1000, 86400000);
		System.out.println("--------++結束++------");
	}
	
	
}
