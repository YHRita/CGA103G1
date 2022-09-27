package com.store.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class StoreService {
	private I_StoreDAO dao;
	
	public StoreService() {
		dao = new StoreDAO();
	}
	
	public StoreVO addStore(
	 String store_acc,
	 String store_pwd,
	 String store_name,
	 String store_gui,
	 String store_rep,
	 String store_tel,
	 String store_fax,
	 String store_add,
	 String store_poc,
	 String store_con_phone,
	 String store_con_add,
	 String store_email,
//	 Date store_reg_date,
	 String bank_account
	 ) {
		StoreVO storeVO = new StoreVO();
		
		storeVO.setStore_acc(store_acc);
		storeVO.setStore_pwd(store_pwd);
		storeVO.setStore_name(store_name);
		storeVO.setStore_gui(store_gui);
		storeVO.setStore_rep(store_rep);
		storeVO.setStore_tel(store_tel);
		storeVO.setStore_fax(store_fax);
		storeVO.setStore_add(store_add);
		storeVO.setStore_poc(store_poc);
		storeVO.setStore_con_phone(store_con_phone);
		storeVO.setStore_con_add(store_con_add);
		storeVO.setStore_email(store_email);
//		storeVO.setStore_reg_date(store_reg_date);
		storeVO.setBank_account(bank_account);

		dao.insert(storeVO);
		return storeVO;
	}
	
	public StoreVO updateStore (
			
			 Integer store_no,
			 String store_acc,
			 String store_pwd,
			 Integer acc_status,
			 String store_name,
			 String store_gui,
			 String store_rep,
			 String store_tel,
			 String store_fax,
			 String store_add,
			 String store_poc,
			 String store_con_phone,
			 String store_con_add,
			 String store_email,
			 Date store_reg_date,
			 String bank_account,
			 Integer store_rm_rating_score,
			 Integer store_rm_rating_count,
			 Integer store_report_count
			) {
		
		StoreVO storeVO = new StoreVO();
		
		storeVO.setStore_no(store_no);
		storeVO.setStore_acc(store_acc);
		storeVO.setStore_pwd(store_pwd);
		storeVO.setAcc_status(acc_status);
		storeVO.setStore_name(store_name);
		storeVO.setStore_gui(store_gui);
		storeVO.setStore_rep(store_rep);
		storeVO.setStore_tel(store_tel);
		storeVO.setStore_fax(store_fax);
		storeVO.setStore_add(store_add);
		storeVO.setStore_poc(store_poc);
		storeVO.setStore_con_phone(store_con_phone);
		storeVO.setStore_con_add(store_con_add);
		storeVO.setStore_email(store_email);
		storeVO.setStore_reg_date(store_reg_date);
		storeVO.setBank_account(bank_account);
		storeVO.setStore_rm_rating_score(store_rm_rating_score);
		storeVO.setStore_rm_rating_count(store_rm_rating_count);
		storeVO.setStore_report_count(store_report_count);
		dao.update(storeVO);
		
		return storeVO;
	} 
	
	public void deleteStoreService(Integer store_no) {
		dao.delete(store_no);
		
	}
	
	public StoreVO getOneStore(Integer store_no) {
		return dao.getOneStore(store_no);
	}
	
	public StoreVO getOneStoreByAcc(String store_acc) {
		return dao.getOneStoreByAcc(store_acc);
	}
	
	public StoreVO getOneStoreByEmail(String store_email) {
		return dao.getOneStoreByEmail(store_email);
	}
	
	
	public List<StoreVO> getAllStore(){
		return dao.getAllStore();
	}
	
	public StoreVO logIn(String store_acc, String store_pwd) {
		return dao.Login(store_acc, store_pwd);
	}
	
	public List<StoreVO> getAllStoreNotSurvy(){
		return dao.Survy();
	};
	
	public List<StoreVO> StoreCompositeQuery(Map<String, String[]> map){
		return dao.CompositeQuery(map);
	}
	
	public StoreVO updatePassword(String store_pwd, Integer store_no) {
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_pwd(store_pwd);
		storeVO.setStore_no(store_no);
		 dao.updatePassword(storeVO);
		 return storeVO;
	}
	
	public StoreVO backendUpdateStore (
			

			 Integer store_no,	
			 String store_pwd,
			 Integer acc_status,
			 String store_name,
			 String store_gui,
			 String store_rep,
			 String store_tel,
			 String store_fax,
			 String store_add,
			 String store_poc,
			 String store_con_phone,
			 String store_con_add,
			 String store_email,
			 String bank_account

			) {
		
		StoreVO storeVO = new StoreVO();

		storeVO.setStore_no(store_no);
		storeVO.setStore_pwd(store_pwd);
		storeVO.setAcc_status(acc_status);
		storeVO.setStore_name(store_name);
		storeVO.setStore_gui(store_gui);
		storeVO.setStore_rep(store_rep);
		storeVO.setStore_tel(store_tel);
		storeVO.setStore_fax(store_fax);
		storeVO.setStore_add(store_add);
		storeVO.setStore_poc(store_poc);
		storeVO.setStore_con_phone(store_con_phone);
		storeVO.setStore_con_add(store_con_add);
		storeVO.setStore_email(store_email);
		storeVO.setBank_account(bank_account);
		dao.backendUpdate(storeVO);
		
		return storeVO;
	} 
}
