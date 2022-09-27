package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	private I_MemDAO dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO addMem(String mem_acc, String mem_pwd, String mem_name, String mem_gender, String mem_email,
			String mem_mobile, String mem_city, String mem_dist, String mem_addr, byte[] mem_pic, String mem_card

	) {
		MemVO memVO = new MemVO();

		memVO.setMem_acc(mem_acc);
		memVO.setMem_pwd(mem_pwd);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_city(mem_city);
		memVO.setMem_dist(mem_dist);
		memVO.setMem_addr(mem_addr);
//		memVO.setMem_reg_date(mem_reg_date);
		memVO.setMem_pic(mem_pic);
//		memVO.setMem_report_count(mem_report_count);
		memVO.setMem_card(mem_card);
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(String mem_acc, String mem_pwd, Integer acc_status, String mem_name, String mem_gender,
			String mem_email, String mem_mobile, String mem_city, String mem_dist, String mem_addr, Date mem_reg_date,
			byte[] mem_pic, Integer mem_report_count, String mem_card, Integer mem_no

	) {
		MemVO memVO = new MemVO();

		memVO.setMem_acc(mem_acc);
		memVO.setMem_pwd(mem_pwd);
		memVO.setAcc_status(acc_status);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_city(mem_city);
		memVO.setMem_dist(mem_dist);
		memVO.setMem_addr(mem_addr);
		memVO.setMem_reg_date(mem_reg_date);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_report_count(mem_report_count);
		memVO.setMem_card(mem_card);
		memVO.setMem_no(mem_no);
		dao.update(memVO);

		return memVO;
	}

	public MemVO update(String mem_pwd, String mem_name, String mem_gender, String mem_email, String mem_mobile,
			String mem_city, Integer mem_no) {
		MemVO memVO = new MemVO();

		memVO.setMem_pwd(mem_pwd);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_city(mem_city);
		memVO.setMem_no(mem_no);
		dao.update(memVO);

		return memVO;
	}

	public void deleteMem(Integer memno) {
		dao.delete(memno);
	}

	public MemVO getOneMem(Integer memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<MemVO> getall() {
		return dao.getAll();
	}

	public MemVO getOneBymail(String mem_email) {

		return dao.getOneBymail(mem_email);
	}

	public MemVO updatePassword(String mem_password, Integer mem_no) {

		MemVO memVO = new MemVO();
		memVO.setMem_pwd(mem_password);
		memVO.setMem_no(mem_no);

		dao.upatePassword(memVO);
		return memVO;
	}

	public MemVO updateStatus(Integer acc_status, Integer mem_no) {
		MemVO memVO = new MemVO();
		memVO.setAcc_status(acc_status);
		memVO.setMem_no(mem_no);
		dao.updateStatus(memVO);
		return memVO;
	}

	public MemVO login(String mem_email, String mem_pwd) {
		return dao.login(mem_email, mem_pwd);
	}

	public MemVO updateMemB(String mem_name, String mem_gender, String mem_email, String mem_mobile, String mem_city,
			String mem_dist, String mem_addr, Integer mem_no) {
		MemVO memVO = new MemVO();
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_city(mem_city);
		memVO.setMem_dist(mem_dist);
		memVO.setMem_addr(mem_addr);
		memVO.setMem_no(mem_no);
		dao.update(memVO);
		return memVO;
	}
}
