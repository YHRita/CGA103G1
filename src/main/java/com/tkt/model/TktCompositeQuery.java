package com.tkt.model;

import java.util.*;

public class TktCompositeQuery {

	public static String get_aCondition_for_db(String columnName, String value) {

		String aCondition = null;

		// 搜尋條件
		// tkt_name, locate, kind

		if ("tkt_name".equals(columnName) || "locate".equals(columnName)) {

			aCondition = columnName + " like '%" + value + "%'";

		} else if ("kind".equals(columnName)) {

			aCondition = columnName + "=" + value;

		}

		return aCondition + " ";

	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		
		Set<String> keys = map.keySet();
		
		StringBuffer whereCondition = new StringBuffer();
		
		int count = 0;
		
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_for_db(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

//				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}

		return whereCondition.toString();
	}

}
