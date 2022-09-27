package com.act_participant.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActParticipantDAO implements I_ActParticipantDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT = "insert into act_participant (act_no, mem_no, enroll_time) "
			+ "values(?, ?, ?)";
	
	private static final String UPDATE = "update act_participant set enroll_status = ? where act_no = ? and mem_no = ?";
	
	private static final String GET_ALL = "select act_no, mem_no, enroll_time, enroll_status from act_participant";
	
	private static final String GET_ONE_OF_ACT_PARTICIPANT = "select act_no, mem_no, enroll_time, enroll_status from act_participant where act_no = ?";
	
	private static final String GET_APES = "select act_no, mem_no, enroll_time, enroll_status from act_participant where mem_no = ?";
	
	@Override
	public void insert(ActParticipantVO actParticipantVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT)) {
			ps.setInt(1, actParticipantVO.getAct_no());
			ps.setInt(2, actParticipantVO.getMem_no());
			ps.setObject(3, actParticipantVO.getEnroll_time());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	
	@Override
	public void update(ActParticipantVO actParticipantVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			ps.setInt(1, actParticipantVO.getEnroll_status());
			ps.setInt(2, actParticipantVO.getAct_no());
			ps.setInt(3, actParticipantVO.getMem_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	
	@Override
	public List<ActParticipantVO> getAll() {
		List<ActParticipantVO> actPartiList = new ArrayList<ActParticipantVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer memNo = rs1.getInt(2);
				LocalDateTime enrollTime = (LocalDateTime) rs1.getObject(3);
				Integer enrollStatus = rs1.getInt(4);
				ActParticipantVO actParticipant = 
						new ActParticipantVO(actNo,
								memNo, enrollTime, enrollStatus);
				actPartiList.add(actParticipant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return actPartiList;
	}
	@Override
	public List<ActParticipantVO> getOneOfAll(Integer actNo) {
		List<ActParticipantVO> actPartiList = new ArrayList<ActParticipantVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ONE_OF_ACT_PARTICIPANT)) {
			ps.setInt(1, actNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer act_No = rs1.getInt(1);
				Integer memNo = rs1.getInt(2);
				LocalDateTime enrollTime = (LocalDateTime) rs1.getObject(3);
				Integer enrollStatus = rs1.getInt(4);
				ActParticipantVO actParticipant = 
						new ActParticipantVO(act_No,
								memNo, enrollTime, enrollStatus);
				actPartiList.add(actParticipant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return actPartiList;
	}
	@Override
	public List<ActParticipantVO> getAPES(Integer memNo) {
		List<ActParticipantVO> actPartiList = new ArrayList<ActParticipantVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_APES)) {
			ps.setInt(1, memNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer mem_No = rs1.getInt(2);
				LocalDateTime enrollTime = (LocalDateTime) rs1.getObject(3);
				Integer enrollStatus = rs1.getInt(4);
				ActParticipantVO actParticipant = 
						new ActParticipantVO(actNo,
								mem_No, enrollTime, enrollStatus);
				actPartiList.add(actParticipant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return actPartiList;
	}
}
