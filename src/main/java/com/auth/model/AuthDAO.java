package com.auth.model;

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

public class AuthDAO implements I_AuthDAO{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO auth(fun_no,emp_no)VALUES (?,?)";
	private static final String UPDATE = "UPDATE auth SET emp_no=? where fun_no=?";
	private static final String CANCEL=  "SELECT * FROM auth WHERE fun_no=?";
	private static final String GET_ONE= "SELECT * FROM auth_fun WHERE fun_no=?";
	private static final String GET_BY_EMP_NO= "SELECT * FROM auth WHERE emp_no=?";
	private static final String GET_ALL="SELECT * FROM auth order by fun_no";

	
	@Override
	public void insert(AuthVO authVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1,authVO.getFun_no());
			ps.setInt(2,authVO.getEmp_no());
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

	@Override
	public void update(AuthVO authVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setInt(1,authVO.getEmp_no());
			ps.setInt(2,authVO.getFun_no());
			ps.executeUpdate();
			
			// Handle any driver errors
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


	@Override
	public void cancel(AuthVO authVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CANCEL);
			
			ps.setInt(1,authVO.getFun_no());
			
			// Handle any driver errors
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

	@Override
	public AuthVO findByPrimaryKey(Integer fun_no) {
		
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);
			
			ps.setInt(1,fun_no);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthVO();
				authVO.setFun_no(rs.getInt("fun_no"));
//				authVO.setEmp_no(rs.getInt("emp_no"));
			}
			
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
		return authVO;
	}
	@Override
	public AuthVO findByFk(Integer emp_no) {
		
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_BY_EMP_NO);
			
			ps.setInt(1,emp_no);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthVO();
				authVO.setFun_no(rs.getInt("fun_no"));
				authVO.setEmp_no(rs.getInt("emp_no"));
			}
			
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
		return authVO;
	}

	@Override
	public List<AuthVO> getAllAuth() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO=null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try  {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();
			while(rs.next()) {
				authVO = new AuthVO();
				authVO.setFun_no(rs.getInt("fun_no"));
				authVO.setEmp_no(rs.getInt("emp_no"));
				list.add(authVO);
		}
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
}