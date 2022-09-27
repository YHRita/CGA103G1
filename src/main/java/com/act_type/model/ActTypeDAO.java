package com.act_type.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActTypeDAO implements I_ActTypeDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into act_type(act_type_name) values(?)";
		
	private static final String GET_ALL = "select act_type_no, act_type_name from act_type";
	
	@Override
	public void insert(ActTypeVO actTypeVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT)) {
			ps.setString(1, actTypeVO.getAct_type_name());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public List<ActTypeVO> getAll() {
		List<ActTypeVO> actTypelist = new ArrayList<ActTypeVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actTypeNo = rs1.getInt(1);
				String actTypeName = rs1.getString(2);
				ActTypeVO actTypesName = 
						new ActTypeVO(actTypeNo, actTypeName);
				actTypelist.add(actTypesName);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actTypelist;
	}
}
