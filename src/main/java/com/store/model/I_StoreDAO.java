package com.store.model;

import java.util.List;
import java.util.Map;

public  interface  I_StoreDAO {
	public void insert(StoreVO storeVO);
	
	public void update(StoreVO storeVO);
	
	public void updatePassword(StoreVO storeVO);
	
	public void delete(Integer store_no);
	
	public StoreVO getOneStore(Integer store_no);
	
	public StoreVO getOneStoreByAcc(String store_acc);
	
	public StoreVO getOneStoreByEmail(String store_email);
	
	public List<StoreVO> getAllStore();
	
	public StoreVO Login(String store_acc, String store_pwd);
	
	public List<StoreVO> Survy();
	
	public List<StoreVO> CompositeQuery(Map<String, String[]> map);
	
	public void backendUpdate(StoreVO storeVO);
	
}
