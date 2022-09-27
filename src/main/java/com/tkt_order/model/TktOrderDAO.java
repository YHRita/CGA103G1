package com.tkt_order.model;

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

import com.mem.model.MemVO;

public class TktOrderDAO implements I_TktOrderDAO{
	
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
			"INSERT INTO tkt_order (tkt_order_no,mem_no,mem_coup_no,tkt_no,original_price,orderdate,ttl_price) VALUES (?, ?, ?, ?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT tkt_order_no,mem_no,mem_coup_no,tkt_no,original_price,orderdate,ttl_price FROM tkt_order order by tkt_order_no";
		private static final String GET_ONE_STMT = 
			"SELECT tkt_order_no,mem_no,mem_coup_no,tkt_no,original_price,orderdate,ttl_price FROM tkt_order where tkt_order_no = ?";
		private static final String DELETE = 
			"DELETE FROM tkt_order where tkt_order_no = ?";
		private static final String UPDATE = 
			"UPDATE tkt_order set mem_no=?, mem_coup_no=?, tkt_no=?, original_price=? ,orderdate=?,ttl_price=?  where tkt_order_no = ?";
	@Override
	public void insert(TktOrderVO tktOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tktOrderVO.getTkt_order_no());
			pstmt.setInt(2, tktOrderVO.getMem_no());
			pstmt.setInt(3, tktOrderVO.getMem_coup_no());
			pstmt.setInt(4, tktOrderVO.getTkt_no());
			pstmt.setInt(5, tktOrderVO.getOriginal_price());
			pstmt.setDate(6, tktOrderVO.getOrderdate());
			pstmt.setInt(7, tktOrderVO.getTTL_PRICE());
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
	public void update(TktOrderVO tktOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tktOrderVO.getMem_no());
			pstmt.setInt(2, tktOrderVO.getMem_coup_no());
			pstmt.setInt(3, tktOrderVO.getTkt_no());
			pstmt.setInt(4, tktOrderVO.getOriginal_price());
			pstmt.setDate(5, tktOrderVO.getOrderdate());
			pstmt.setInt(6, tktOrderVO.getTTL_PRICE());
			pstmt.setInt(7, tktOrderVO.getTkt_order_no());
			
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
	public void delete(Integer tkt_order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkt_order_no);

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
	public TktOrderVO findByPrimaryKey(Integer tkt_order_no) {
		TktOrderVO tktOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				tktOrderVO  = new TktOrderVO();
				tktOrderVO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrderVO.setMem_no(rs.getInt("mem_no"));
				tktOrderVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				tktOrderVO.setTkt_no(rs.getInt("tkt_no"));
				tktOrderVO.setOriginal_price(rs.getInt("original_price"));
				tktOrderVO.setOrderdate(rs.getDate("orderdate"));
				tktOrderVO.setTTL_PRICE(rs.getInt("ttl_price"));
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
		return tktOrderVO;
	}

	@Override
	public List<TktOrderVO> getAll() {
		List<TktOrderVO> list = new ArrayList<TktOrderVO>();
		TktOrderVO tktOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				tktOrderVO  = new TktOrderVO();
				tktOrderVO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrderVO.setMem_no(rs.getInt("mem_no"));
				tktOrderVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				tktOrderVO.setTkt_no(rs.getInt("tkt_no"));
				tktOrderVO.setOriginal_price(rs.getInt("original_price"));
				tktOrderVO.setOrderdate(rs.getDate("orderdate"));
				tktOrderVO.setTTL_PRICE(rs.getInt("ttl_price"));
				list.add(tktOrderVO);
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
