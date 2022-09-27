package com.tkt_item2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class TktItem2DAO implements I_TktItem2DAO{
	
	private static final String INSERT = "INSERT INTO tkt_item (tkt_no, tkt_order_no, amount, qrcode, tkt_ori_price) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL = "SELECT tkt_no, tkt_order_no, amount, qrcode, tkt_ori_price FROM tkt_item ORDER BY tkt_no";
	private static final String GET_ONE = "SELECT tkt_no, tkt_order_no, amount, qrcode, tkt_ori_price FROM tkt_item WHERE tkt_order_no = ?";
	
	
	private static DataSource ds = null;

	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(TktItem2VO tktItem2VO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, tktItem2VO.getTkt_no());
			ps.setInt(2, tktItem2VO.getTkt_order_no());
			ps.setInt(3, tktItem2VO.getAmount());
			ps.setBytes(4, tktItem2VO.getQrcode());
			ps.setInt(5, tktItem2VO.getTkt_ori_price());
			
			ps.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

//	@Override
//	public void update(TktItem2VO tktItem2VO) {
//		// TODO Auto-generated method stub
//		
//	}
	
	@Override
	public List<TktItem2VO> findByPrimaryKey(Integer tkt_order_no) {
		List<TktItem2VO> list = new ArrayList<TktItem2VO>();
		
		TktItem2VO tktItem2VO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, tkt_order_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				tktItem2VO = new TktItem2VO();
				tktItem2VO.setTkt_no(rs.getInt("tkt_no"));
				tktItem2VO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktItem2VO.setAmount(rs.getInt("amount"));
				tktItem2VO.setQrcode(rs.getBytes("qrcode"));
				tktItem2VO.setTkt_ori_price(rs.getInt("tkt_ori_price"));
				
				list.add(tktItem2VO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	@Override
	public List<TktItem2VO> getAll() {
		List<TktItem2VO> list = new ArrayList<TktItem2VO>();
		
		TktItem2VO tktItem2VO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);


			rs = ps.executeQuery();

			while (rs.next()) {

				tktItem2VO = new TktItem2VO();
				tktItem2VO.setTkt_no(rs.getInt("tkt_no"));
				tktItem2VO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktItem2VO.setAmount(rs.getInt("amount"));
				tktItem2VO.setQrcode(rs.getBytes("qrcode"));
				tktItem2VO.setTkt_ori_price(rs.getInt("tkt_ori_price"));
				
				list.add(tktItem2VO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	//同時新增票券訂單與訂單明細
	@Override
	public void insertDetailWithOrder(TktItem2VO tktItem2VO, Connection con) {
		PreparedStatement ps = null;

		try {


			ps = con.prepareStatement(INSERT);

			ps.setInt(1, tktItem2VO.getTkt_no());
			ps.setInt(2, tktItem2VO.getTkt_order_no());
			ps.setInt(3, tktItem2VO.getAmount());
			ps.setBytes(4, tktItem2VO.getQrcode());
			ps.setInt(5, tktItem2VO.getTkt_ori_price());
			
			ps.executeUpdate();
			

//			Statement stmt=	con.createStatement();
//
//			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增


			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-TktDetail");
					System.out.println(se);
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}


}
