package com.tkt_img.model;

import java.util.List;

public interface I_TktImgDAO {
	 public void insert(TktImgVO tktimgVO);
     public void update(TktImgVO tktimgVO);
     public void delete(Integer TktImgno);
     public TktImgVO findByPrimaryKey(Integer TktImgno);
     public List<TktImgVO> getAll();
}
