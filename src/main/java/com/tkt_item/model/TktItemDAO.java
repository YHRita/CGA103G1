package com.tkt_item.model;

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

import com.tkt_img.model.TktImgVO;

public class TktItemDAO implements I_TktItemDAO{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g1");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		private static final String INSERT_STMT = 
				"INSERT INTO tkt_item (tkt_no,tkt_order_no,amount,qrcode,tkt_ori_price) VALUES (?, ?, ?, ?,?)";
			private static final String GET_ALL_STMT = 
				"SELECT tkt_no,tkt_order_no,amount,qrcode,tkt_ori_price FROM tkt_item order by tkt_no";
			private static final String GET_ONE_STMT = 
				"SELECT tkt_no,tkt_order_no,amount,qrcode,tkt_ori_price FROM tkt_item where tkt_no = ?";
			private static final String DELETE = 
				"DELETE FROM tkt_item where tkt_no = ?";
			private static final String UPDATE = 
				"UPDATE tkt_item set tkt_order_no=?, amount=?, qrcode=?, tkt_ori_price=?  where tkt_no = ?";
	@Override
	public void insert(TktItemVO tktitemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tktitemVO.getTkt_no());
			pstmt.setInt(2, tktitemVO.getTKT_ORDER_NO());
			pstmt.setInt(3, tktitemVO.getAmount());
			pstmt.setBytes(4, tktitemVO.getQrcode());
			pstmt.setInt(5, tktitemVO.getTkt_ori_price());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	@Override
	public void update(TktItemVO tktitemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tktitemVO.getTKT_ORDER_NO());
			pstmt.setInt(2, tktitemVO.getAmount());
			pstmt.setBytes(3, tktitemVO.getQrcode());
			pstmt.setInt(4, tktitemVO.getTKT_ORDER_NO());
			pstmt.setInt(5, tktitemVO.getTkt_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	@Override
	public void delete(Integer tktno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tktno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	@Override
	public TktItemVO findByPrimaryKey(Integer tktno) {
		TktItemVO tktItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tktno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				tktItemVO = new TktItemVO();
				tktItemVO.setTkt_no(rs.getInt("tkt_no"));
				tktItemVO.setTKT_ORDER_NO(rs.getInt("tkt_order_no"));
				tktItemVO.setAmount(rs.getInt("amount"));
				tktItemVO.setQrcode(rs.getBytes("qrcode"));
				tktItemVO.setTkt_ori_price(rs.getInt("tkt_ori_price"));
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
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return tktItemVO;
	}

	@Override
	public List<TktItemVO> getAll() {
		List<TktItemVO> list = new ArrayList<TktItemVO>();
		TktItemVO tktItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				 tktItemVO = new TktItemVO();
					tktItemVO.setTkt_no(rs.getInt("tkt_no"));
					tktItemVO.setTKT_ORDER_NO(rs.getInt("tkt_order_no"));
					tktItemVO.setAmount(rs.getInt("amount"));
					tktItemVO.setQrcode(rs.getBytes("qrcode"));
					tktItemVO.setTkt_ori_price(rs.getInt("tkt_ori_price"));
					list.add(tktItemVO);
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
			if (pstmt != null) {
				try {
					pstmt.close();
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
	
}
