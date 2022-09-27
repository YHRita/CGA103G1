package com.tkt_img.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class TktImgDAO implements I_TktImgDAO{
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
				"INSERT INTO tkt_img (tkt_no,tkt_img) VALUES ( ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT tkt_img_no,tkt_no,tkt_img FROM tkt_img order by tkt_img_no";
			private static final String GET_ONE_STMT = 
				"SELECT tkt_img_no,tkt_no,tkt_img FROM tkt_img where tkt_img_no = ?";
			private static final String DELETE = 
				"DELETE FROM tkt_img where tkt_img_no = ?";
			private static final String UPDATE = 
				"UPDATE tkt_img set  tkt_no=?, tkt_img=? where tkt_img_no = ?";
	@Override
	public void insert(TktImgVO tktImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tktImgVO.gettktNO());
			pstmt.setBytes(2, tktImgVO.gettktimg());
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
	public void update(TktImgVO tktimgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tktimgVO.gettktNO());
			pstmt.setBytes(2, tktimgVO.gettktimg());
			pstmt.setInt(3, tktimgVO.gettktImgNO());

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
	public void delete(Integer TktImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, TktImgVO);

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
	public TktImgVO findByPrimaryKey(Integer TktImgVO) {

		TktImgVO tktImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, TktImgVO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				tktImgVO = new TktImgVO();
				tktImgVO.settktImgNO(rs.getInt("tkt_img_no"));
				tktImgVO.settktNO(rs.getInt("tkt_no"));
				tktImgVO.settktimg(rs.getBytes("tkt_img"));
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
		return tktImgVO;
	}

	@Override
	public List<TktImgVO> getAll() {
		List<TktImgVO> list = new ArrayList<TktImgVO>();
		TktImgVO tktimgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tktimgVO = new TktImgVO();
				tktimgVO.settktImgNO(rs.getInt("tkt_img_no"));
				tktimgVO.settktNO(rs.getInt("tkt_no"));
				tktimgVO.settktimg(rs.getBytes("tkt_img"));
				list.add(tktimgVO);
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
	public static void main(String[] args) {
		TktImgDAO dao = new TktImgDAO();
		
		//新增
		TktImgVO tktImgVO1 = new TktImgVO();
		
		tktImgVO1.settktNO(4);
		tktImgVO1.settktimg(null);
		dao.insert(tktImgVO1);
	}
}
