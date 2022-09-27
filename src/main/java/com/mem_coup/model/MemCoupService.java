package com.mem_coup.model;

import java.util.List;

import com.coup.model.CoupVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class MemCoupService {
	
	private I_MemCoupDAO dao;
	
	public MemCoupService() {
		dao = new MemCoupDAO();
	}
	
	public MemCoupVO getOne(Integer mem_coup_no, Integer mem_no, Integer coup_no, Integer tkt_order_no, Integer coup_state) {
		return dao.findByPrimaryKey(mem_coup_no);
	}
	
	public MemCoupVO getOneMemCoup(Integer mem_coup_no) {
		return dao.findByPrimaryKey(mem_coup_no);
	}
	
	public List<MemCoupVO> getAll() {
		return dao.getAll();
	}
	
	public void changeState(Integer mem_coup_no, Integer coup_state) {
//		MemCoupVO memCoupVO = new MemCoupVO();
//		
//		memCoupVO.setMem_coup_no(mem_coup_no);
//		memCoupVO.setCoup_state(coup_state);
//		
//		dao.update(memCoupVO);
		
		MemCoupVO memCoupVO = dao.findByPrimaryKey(mem_coup_no);
		Integer status = memCoupVO.getCoup_state();
		// 排成器偵測到會員的優惠券在截止日時尚未使用(狀態 0)才會改變狀態為“過期
		if (status == 0) {
			dao.changeState(mem_coup_no, coup_state);
		}
		
	}
	
	
	public void memCoupUsedState(Integer mem_coup_no) {
		dao.memCoupUsedState(mem_coup_no);
	}
	public void memCoupOverdueState(Integer mem_coup_no) {
		dao.memCoupOverdueState(mem_coup_no);
	}
	
	public MemVO sendCoupToMem(Integer mem_no, Integer coup_no, Integer coup_state) {
		
		MemService memSrv = new MemService();
		// 透過會員帳號找出會員
//		MemVO memVO = memSrv.getByMemAcc(mem_acc); // getByMemAcc
		
		// 透過會員編號找出會員
		MemVO memVO = memSrv.getOneMem(mem_no);
		
		// 新增優惠券給會員
		MemCoupVO memCoupVO = new MemCoupVO();
		memCoupVO.setMem_no(mem_no);
		memCoupVO.setCoup_no(coup_no);
		memCoupVO.setCoup_state(coup_state);
		dao.insert(memCoupVO);
		
		return memVO;
	}
	
	// 讓會員查看自己擁有哪些優惠券
	public List<MemCoupVO> listOneMemCoupon(Integer mem_no) {
		return dao.getOneMember(mem_no);
	}
	
}