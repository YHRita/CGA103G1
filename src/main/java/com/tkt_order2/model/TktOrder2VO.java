package com.tkt_order2.model;

import java.sql.Date;

import com.coup.model.CoupService;
import com.coup.model.CoupVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.mem_coup.model.MemCoupService;
import com.mem_coup.model.MemCoupVO;
import com.tkt.model.TktService;
import com.tkt.model.TktVO;

public class TktOrder2VO {
	private Integer tkt_order_no;
	private Integer mem_no;
	private Integer mem_coup_no;
//	private Integer tkt_no;
	private Integer original_price;
	private Date orderdate;
	private Integer ttl_price;

	public TktOrder2VO() {
	}

	public TktOrder2VO(Integer tkt_order_no, Integer mem_no, Integer mem_coup_no, Integer original_price,
			Date orderdate, Integer ttl_price) {
		super();
		this.tkt_order_no = tkt_order_no;
		this.mem_no = mem_no;
		this.mem_coup_no = mem_coup_no;
//		this.tkt_no = tkt_no;
		this.original_price = original_price;
		this.orderdate = orderdate;
		this.ttl_price = ttl_price;
	}

	public Integer getTkt_order_no() {
		return tkt_order_no;
	}

	public void setTkt_order_no(Integer tkt_order_no) {
		this.tkt_order_no = tkt_order_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getMem_coup_no() {
		return mem_coup_no;
	}

	public void setMem_coup_no(Integer mem_coup_no) {
		this.mem_coup_no = mem_coup_no;
	}

//	public Integer getTkt_no() {
//		return tkt_no;
//	}
//
//	public void setTkt_no(Integer tkt_no) {
//		this.tkt_no = tkt_no;
//	}

	public Integer getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(Integer original_price) {
		this.original_price = original_price;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Integer getTtl_price() {
		return ttl_price;
	}

	public void setTtl_price(Integer ttl_price) {
		this.ttl_price = ttl_price;
	}

	// join MemVO
	public MemVO getMemVO() {
		MemService memSrv = new MemService();
		MemVO memVO = memSrv.getOneMem(mem_no);
		return memVO;
	}
	

	// join MemCoupVO
	public MemCoupVO getMemCoupVO() {
		MemCoupService memCoupSrv = new MemCoupService();
		MemCoupVO memCoupVO = memCoupSrv.getOneMemCoup(mem_coup_no);
		return memCoupVO;
	}
	

}
