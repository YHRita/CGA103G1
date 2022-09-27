package com.tkt_img.model;

import java.util.List;

public class TktImgService {
	
	public I_TktImgDAO dao;

	public TktImgService() {
		dao = new TktImgDAO();
	}
	
	public TktImgVO addTktImg(Integer tkt_no ,byte[] tkt_img) {
		TktImgVO tktImgVO  = new TktImgVO();
		
		tktImgVO.settktNO(tkt_no);
		tktImgVO.settktimg(tkt_img);
		dao.insert(tktImgVO);
		
		return tktImgVO;
	}
	public TktImgVO updateTktImg(Integer tkt_no ,byte[] tkt_img) {
		TktImgVO tktImgVO  = new TktImgVO();
		
		tktImgVO.settktNO(tkt_no);
		tktImgVO.settktimg(tkt_img);
		dao.update(tktImgVO);
		
		return tktImgVO;
	}
	public void deleteTktImg(Integer tktimgno) {
		dao.delete(tktimgno);
	}
	public  TktImgVO getOneTktImg(Integer tktimgno) {
		return dao.findByPrimaryKey(tktimgno);
	}
	public List<TktImgVO> getAll(){
		return dao.getAll();
	}
	
}
