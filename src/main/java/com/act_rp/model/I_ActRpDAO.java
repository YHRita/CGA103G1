package com.act_rp.model;

import java.util.List;


public interface I_ActRpDAO {
		//新增
		public void insert(ActRpVO actRpVO);
//		//刪除
//		public void delete(ActRpVO actRpVO);
		//修改
		public void update(ActRpVO actRpVO);
		//顯示
		public List<ActRpVO> getall();
		
		public ActRpVO getone(Integer act_rp_no);
		
}
