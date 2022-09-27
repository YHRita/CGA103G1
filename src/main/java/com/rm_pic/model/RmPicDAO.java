package com.rm_pic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RmPicDAO implements I_RmPicDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO rm_pic(rm_type_no, rm_pic)VALUES(?,?)";
//	private static final String UPDATE = "UPDATE rm_pic SET rm_type_no=?, rm_pic=? WHERE rm_pic_no=?";
	private static final String DELETE = "DELETE FROM rm_pic WHERE rm_pic_no=?";
	private static final String GET_ONE = "SELECT * FROM rm_pic WHERE rm_pic_no=?";
	private static final String GET_ALL_BY_TYPE = "SELECT * FROM rm_pic WHERE rm_type_no = ?";
	private static final String GET_ALL = "SELECT * FROM rm_pic";

	@Override
	public RmPicVO insert(RmPicVO rmPicVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, rmPicVO.getRm_type_no());
			ps.setBytes(2, rmPicVO.getRm_pic());
			ps.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return null;

	}

//	@Override
//	public void update(RmPicVO rmPicVO) {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(UPDATE);
//
//			ps.setInt(1, rmPicVO.getRm_type_no());
//			ps.setBytes(2, rmPicVO.getRm_pic());
//			ps.setInt(3, rmPicVO.getRm_pic_no());
//			ps.executeUpdate();
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void delete(Integer rm_pic_no) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, rm_pic_no);
			ps.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public RmPicVO findByPrimaryKey(Integer rm_pic_no) {

		RmPicVO rmPicVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, rm_pic_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				rmPicVO = new RmPicVO();
				rmPicVO.setRm_pic_no(rs.getInt("rm_pic_no"));
				rmPicVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmPicVO.setRm_pic(rs.getBytes("rm_pic"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return rmPicVO;
	}

	@Override
	public List<RmPicVO> getAll() {
		List<RmPicVO> list = new ArrayList<RmPicVO>();
		RmPicVO rmPicVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				rmPicVO = new RmPicVO();
				rmPicVO.setRm_pic_no(rs.getInt("rm_pic_no"));
				rmPicVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmPicVO.setRm_pic(rs.getBytes("rm_pic"));
				list.add(rmPicVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<RmPicVO> getAllByType(Integer rm_type_no) {
		List<RmPicVO> list = new ArrayList<RmPicVO>();
		RmPicVO rmPicVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_BY_TYPE);
			ps.setInt(1, rm_type_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				rmPicVO = new RmPicVO();
				rmPicVO.setRm_pic_no(rs.getInt("rm_pic_no"));
				rmPicVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmPicVO.setRm_pic(rs.getBytes("rm_pic"));
				list.add(rmPicVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
}