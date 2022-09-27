package com.rm_msg.model;

import java.util.List;

import com.mem.model.MemVO;


public interface I_Rm_msgDAO {
	public void insert(Rm_msgVO rm_msgVO);
    public void update(Rm_msgVO rm_msgVO);
    public void delete(Integer rm_msg_no);
    public Rm_msgVO findByStoreNumber(Integer store_no);
    public Rm_msgVO findByMemNumber(Integer mem_no);
    public List<Rm_msgVO> getAll();
    public  MemVO getOneMemByMemAcc(String mem_acc);
    public List<Rm_msgVO> getAllMsgByNumber(Integer store_no);
}
