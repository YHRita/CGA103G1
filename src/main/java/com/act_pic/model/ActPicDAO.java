package com.act_pic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActPicDAO implements I_ActPicDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO act_pic(act_no, act_pic_file) VALUES (?, ?)";
	
	private static final String UPDATE = "update act_pic set act_pic_file = ? where act_no = ?";
	
	private static final String GET_ONE_OF_ACT_PIC = "select act_pic_file from act_pic where act_no = ?";
	
	private static final String GET_ALL = "select act_pic_no, act_no, act_pic_file from act_pic";
	
	@Override
	public void insert(ActPicVO actPicVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT);) {
				ps.setInt(1, actPicVO.getAct_no());
				ps.setBytes(2, actPicVO.getAct_pic());
				ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	@Override
	public void update(ActPicVO actPicVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE);) {
			ps.setBytes(1, actPicVO.getAct_pic());
			ps.setInt(2, actPicVO.getAct_no());
			ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public List<ActPicVO> findActPic(Integer actNo) {
		List<ActPicVO> list = new ArrayList<>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ONE_OF_ACT_PIC);) {
			ps.setInt(1, actNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				byte[] actPicFile = rs.getBytes(1);
				ActPicVO actPicVO = 
						new ActPicVO(actPicFile);
				list.add(actPicVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return list;
	}
	
	@Override
	public List<ActPicVO> getAll() {
		List<ActPicVO> list = new ArrayList<>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ALL);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer actPic_No = rs.getInt(1);
				Integer act_No = rs.getInt(2);
				byte[] actPicFile = rs.getBytes(3);
				ActPicVO actPicVO = 
						new ActPicVO(actPic_No,
								act_No, actPicFile);
				list.add(actPicVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return list;
	}

}
