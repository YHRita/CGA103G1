package com.mem_coup.model;

import java.io.Serializable;

import com.coup.model.CoupService;
import com.coup.model.CoupVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class MemCoupVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer mem_coup_no;
	private Integer mem_no;
	private Integer coup_no;
	private Integer tkt_order_no;
	private Integer coup_state;
	
	public MemCoupVO() {}

	public MemCoupVO(Integer mem_coup_no, Integer mem_no, Integer coup_no, Integer tkt_order_no, Integer coup_state) {
		super();
		this.mem_coup_no = mem_coup_no;
		this.mem_no = mem_no;
		this.coup_no = coup_no;
		this.tkt_order_no = tkt_order_no;
		this.coup_state = coup_state;
	}

	public Integer getMem_coup_no() {
		return mem_coup_no;
	}

	public void setMem_coup_no(Integer mem_coup_no) {
		this.mem_coup_no = mem_coup_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getCoup_no() {
		return coup_no;
	}

	public void setCoup_no(Integer coup_no) {
		this.coup_no = coup_no;
	}

	public Integer getTkt_order_no() {
		return tkt_order_no;
	}

	public void setTkt_order_no(Integer tkt_order_no) {
		this.tkt_order_no = tkt_order_no;
	}

	public Integer getCoup_state() {
		return coup_state;
	}

	public void setCoup_state(Integer coup_state) {
		this.coup_state = coup_state;
	}

	@Override
	public String toString() {
		return "MemCoupVO [mem_coup_no=" + mem_coup_no + ", mem_no=" + mem_no + ", coup_no=" + coup_no
				+ ", tkt_order_no=" + tkt_order_no + ", coup_state=" + coup_state + "]";
	}


	// join MemVO
	public MemVO getMemVO() {
		MemService memSrv = new MemService();
		MemVO memVO = memSrv.getOneMem(mem_no);
		return memVO;
	}
	
	// join CoupVO
	public CoupVO getCoupVO() {
		CoupService coupSrv = new CoupService();
		CoupVO coupVO = coupSrv.getOneCoup(coup_no);
		return coupVO;
	}
	
}
