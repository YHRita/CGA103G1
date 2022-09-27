package com.system_notification_message.model;

import java.util.List;

import com.last_news.model.LastNewsVO;

public class SystemNotificationMessageService {

	private I_SystemNotificationMessageDAO dao;

	public SystemNotificationMessageService() {
		dao = new SystemNotificationMessageDAO();
	}

	public SystemNotificationMessageVO insertSNM(String msg, byte[] msg_img, Integer emp_no) {
		SystemNotificationMessageVO snmVO = new SystemNotificationMessageVO();
		snmVO.setMsg(msg);
		snmVO.setMsg_img(msg_img);
		snmVO.setEmp_no(emp_no);
		dao.insert(snmVO);

		return snmVO;
	}

	public void deleteSNM(Integer msg_no) {
		dao.delete(msg_no);
	}

	public List<SystemNotificationMessageVO> getall() {
		return dao.getall();
	}
	public List<SystemNotificationMessageVO> getthree() {
		return dao.getthree();
	}

	public SystemNotificationMessageVO getpic(Integer msg_no) {
		return dao.getpic(msg_no);
	}

	public SystemNotificationMessageVO getone(Integer msg_no) {
		return dao.getone(msg_no);
	}
	
	public SystemNotificationMessageVO getLastONE() {
		return dao.getlast();
	}
}
