/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.store.model;

import java.util.*;

public class StoreCompositeQuery{

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("store_no".equals(columnName) || "acc_status".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("store_acc".equals(columnName) || "store_name".equals(columnName)  || "store_gui".equals(columnName)
				 || "store_rep".equals(columnName) || "store_tel".equals(columnName) || "store_fax".equals(columnName) 
				 || "store_add".equals(columnName)|| "bank_account".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("store_reg_date".equals(columnName))                          // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0]; 
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" or " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("store_no", new String[] { "7001" });
//		map.put("store_acc", new String[] { "KING" });
//		map.put("store_pwd", new String[] { "PRESIDENT" });
//		map.put("acc_status", new String[] { "1981-11-17" });
//		map.put("store_name", new String[] { "5000.5" });
//		map.put("store_gui", new String[] { "0.0" });
//		map.put("store_rep", new String[] { "10" });
//		map.put("store_tel", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = "select * from emp2 "
//				          + StoreCompositeQuery.get_WhereCondition(map)
//				          + "order by empno";
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
}
