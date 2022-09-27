package com.emp.model;

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

import com.mem.model.MemVO;

public class EmpDAO implements I_EmpDAO {
	private static final String INSERT = "INSERT INTO EMP(emp_acc,emp_pwd,emp_name,emp_email,emp_state)VALUES(?,?,?,?,?)";
	private static final String UPDATE = "UPDATE EMP SET emp_acc=?,emp_pwd=?, emp_name=?,emp_email=?,emp_state=? WHERE emp_no=?";
	private static final String GET_ONE = "SELECT * FROM EMP WHERE emp_no=?";
	private static final String GET_ALL = "SELECT * FROM EMP";
	private static final String UPDATE_STATUS = "UPDATE emp set emp_state=? where emp_no = ?";
	private static final String LOGIN = "SELECT * FROM EMP WHERE emp_acc=? and emp_pwd =?";
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
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, empVO.getEmp_acc());
			ps.setString(2, empVO.getEmp_pwd());
			ps.setString(3, empVO.getEmp_name());
			ps.setString(4, empVO.getEmp_email());
			ps.setInt(5, empVO.getEmp_state());
			ps.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, empVO.getEmp_acc());
			ps.setString(2, empVO.getEmp_pwd());
			ps.setString(3, empVO.getEmp_name());
			ps.setString(4, empVO.getEmp_email());
			ps.setInt(5, empVO.getEmp_state());
			ps.setInt(6, empVO.getEmp_no());
			ps.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public EmpVO getOne(Integer emp_no) {
		ResultSet rs = null;
		EmpVO emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, emp_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new EmpVO();
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setEmp_acc(rs.getString("emp_acc"));
				emp.setEmp_pwd(rs.getString("emp_pwd"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_state(rs.getInt("emp_state"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return emp;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> empAll = new ArrayList<>();
		EmpVO emp = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new EmpVO();
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setEmp_pwd(rs.getString("emp_pwd"));
				emp.setEmp_acc(rs.getString("emp_acc"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_state(rs.getInt("emp_state"));

				empAll.add(emp);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empAll;
	}

	public EmpVO login(String emp_acc, String emp_pwd) {
		EmpVO emp = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(LOGIN);
			ps.setString(1, emp_acc);
			ps.setString(2, emp_pwd);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new EmpVO();
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setEmp_pwd(rs.getString("emp_pwd"));
				emp.setEmp_acc(rs.getString("emp_acc"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_state(rs.getInt("emp_state"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return emp;
	}

	@Override
	public void updateStatus(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			pstmt.setInt(1, empVO.getEmp_state());
			pstmt.setInt(2, empVO.getEmp_no());
			
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
}