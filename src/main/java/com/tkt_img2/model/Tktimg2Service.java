package com.tkt_img2.model;

import java.util.List;

public class Tktimg2Service {
	
	private I_TktImg2DAO dao;
	
	public Tktimg2Service() {
		dao = new TktImg2DAO();
	}
	
	public void insertTktImg(TktImg2VO tktImg2VO) {
		dao.insert(tktImg2VO);
	}
	
	public void updateTktImg(TktImg2VO tktImg2VO) {
		dao.update(tktImg2VO);
	}
	
	public TktImg2VO getOne(Integer tkt_img_no) {
		return dao.getOne(tkt_img_no);
	}
	
	public List<TktImg2VO> getAll() {
		return dao.getAll();
	}
	
	public List<TktImg2VO> getAllByTktNo(Integer tkt_no) {
		return dao.getAllByTktNo(tkt_no);
	}

	public TktImg2VO insertTktImg(Integer tkt_no, byte[] tkt_img) {
		TktImg2VO tktImg2VO = new TktImg2VO();
		tktImg2VO.setTkt_no(tkt_no);
		tktImg2VO.setTkt_img(tkt_img);
		
		return dao.insert(tktImg2VO);
	}
	
//	public TktImg2VO addTktImgData(Integer tkt_no, String tkt_img) throws IOException {
//		
//		TktImg2VO tktImg2VO = new TktImg2VO();
//		byte[] pic = dao.insertTktImg(tkt_img);
//		
//		tktImg2VO.setTkt_no(tkt_no);
//		tktImg2VO.setTkt_img(pic);
//		
//		dao.insert(tktImg2VO);
//		
//		return tktImg2VO;
//	}
//	
//	
//	public String getOneTktImg(Integer tkt_img_no) {
//		byte[] img = dao.getOneTktImg(tkt_img_no);
//		String data = Base64.getEncoder().encodeToString(img);
//		System.out.println(data);
//		return data;
//	}
//	
//	
//	public void addTktImg(Integer tkt_no, byte[] tkt_img){
//		TktImg2VO tktImg2VO = new TktImg2VO();
//		tktImg2VO.setTkt_img_no(tkt_no);
//		tktImg2VO.setTkt_img(tkt_img);
//		dao.insert(tktImg2VO);
//	}
//	
//	public List<TktImg2VO> getTktImg(Integer tkt_no) {
//		return dao.getTktImg(tkt_no);
//	}
//	
//	public void delete(Integer tkt_img_no) {
//		dao.delete(tkt_img_no);
//	}
//	
//	
//	public static void main(String [] args) {
//		Tktimg2Service tktImgSrv = new Tktimg2Service();
//		try {
//			//新增圖片
//			tktImgSrv.addTktImgData(1, "tkt1.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("OK");
//	}
	
}
