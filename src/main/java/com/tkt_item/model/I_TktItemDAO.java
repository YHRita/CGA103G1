package com.tkt_item.model;

import java.util.List;

public interface I_TktItemDAO {
	public void insert(TktItemVO tktitemVO);
    public void update(TktItemVO tktitemVO);
    public void delete(Integer tktno);
    public TktItemVO findByPrimaryKey(Integer tktno);
    public List<TktItemVO> getAll();
}
