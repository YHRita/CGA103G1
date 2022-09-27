package com.rm_reserve.model;

import java.sql.Connection;
import java.sql.Date;
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

public class RmReserveDAO implements I_RmReserveDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO rm_reserve(rm_type_no, rm_schedule_date, rm_total) "
			+ "WITH RECURSIVE dates (v_date) AS "
			+ "(SELECT CURDATE() "
			+ "UNION ALL "
			+ "SELECT v_date + INTERVAL 1 DAY "
			+ "FROM dates "
			+ "WHERE v_date + INTERVAL 1 DAY <= ADDDATE(CURDATE(), INTERVAL 1 MONTH)) "
			+ "SELECT z.rm_type_no, d.v_date, z.rm_total "
			+ "FROM dates d "
			+ "CROSS JOIN rm_type z "
			+ "LEFT JOIN rm_reserve r on (d.v_date = r.rm_schedule_date AND z.rm_type_no = r.rm_type_no) "
			+ "WHERE r.rm_schedule_date IS NULL";
	
	private static final String RESERVE = "UPDATE rm_reserve SET reservation_amount = reservation_amount+? "
			+ "WHERE rm_type_no = ? AND (rm_schedule_date BETWEEN ? AND SUBDATE( ?, INTERVAL 1 DAY))";
	
	private static final String CANCEL = "UPDATE rm_reserve SET reservation_amount = reservation_amount-? "
			+ "WHERE rm_type_no = ? AND (rm_schedule_date BETWEEN ? AND SUBDATE( ?, INTERVAL 1 DAY))";
	
	private static final String CHECK_OUT_EARLY = "UPDATE rm_reserve SET reservation_amount = reservation_amount-1 "
			+ "WHERE rm_type_no = ? AND (rm_schedule_date BETWEEN CURDATE() AND SUBDATE( ?, INTERVAL 1 DAY))";
	
	private static final String DELETE = "DELETE FROM rm_reserve WHERE rm_schedule_date < CURDATE()";
	
	private static final String UPDATE = "UPDATE RM_RESERVE SET rm_type_no=?, rm_total=?, rm_schedule_date=?, reservation_amount=? WHERE serial_no=?";
	private static final String GET_ONE = "SELECT * FROM RM_RESERVE WHERE serial_no=?";
	private static final String GET_ONE_BY_DATE_TYPE = "SELECT * FROM rm_reserve WHERE rm_schedule_date = CURDATE() AND rm_type_no = ?";
	private static final String GET_ALL = "SELECT * FROM RM_RESERVE";

	@Override
	public void insert() {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			ps.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void reserve(Integer qty, Integer rm_type_no, Date arrival_date, Date departure_date) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(RESERVE);

			ps.setInt(1, qty);
			ps.setInt(2, rm_type_no);
			ps.setDate(3, arrival_date);
			ps.setDate(4, departure_date);
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

	}

	@Override
	public void cancel(Integer qty, Integer rm_type_no, Date arrival_date, Date departure_date) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CANCEL);

			ps.setInt(1, qty);
			ps.setInt(2, rm_type_no);
			ps.setDate(3, arrival_date);
			ps.setDate(4, departure_date);
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

	}
	
	@Override
	public void checkOutEarly(Integer rm_type_no, Date departure_date) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CHECK_OUT_EARLY);

			ps.setInt(1, rm_type_no);
			ps.setDate(2, departure_date);
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
		
	}

	@Override
	public void delete() {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);
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
	public RmReserveVO findByPrimaryKey(Integer serial_no) {

		RmReserveVO rmReserveVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, serial_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				rmReserveVO = new RmReserveVO();
				rmReserveVO.setSerial_no(rs.getInt("serial_no"));
				rmReserveVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmReserveVO.setRm_total(rs.getInt("rm_total"));
				rmReserveVO.setRm_schedule_date(rs.getDate("rm_schedule_date"));
				rmReserveVO.setReservation_amount(rs.getInt("reservation_amount"));
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
		return rmReserveVO;
	}
	
	@Override
	public RmReserveVO getOneByRm(Integer rm_type_no) {
		
		RmReserveVO rmReserveVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_BY_DATE_TYPE);
			
			ps.setInt(1, rm_type_no);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				rmReserveVO = new RmReserveVO();
				rmReserveVO.setSerial_no(rs.getInt("serial_no"));
				rmReserveVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmReserveVO.setRm_total(rs.getInt("rm_total"));
				rmReserveVO.setRm_schedule_date(rs.getDate("rm_schedule_date"));
				rmReserveVO.setReservation_amount(rs.getInt("reservation_amount"));
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
		return rmReserveVO;
	}

	@Override
	public List<RmReserveVO> getAll() {
		List<RmReserveVO> list = new ArrayList<RmReserveVO>();
		RmReserveVO rmReserveVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();
			while (rs.next()) {
				rmReserveVO = new RmReserveVO();
				rmReserveVO.setSerial_no(rs.getInt("serial_NO"));
				rmReserveVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmReserveVO.setRm_total(rs.getInt("rm_total"));
				rmReserveVO.setRm_schedule_date(rs.getDate("rm_schedule_date"));
				rmReserveVO.setReservation_amount(rs.getInt("reservation_amount"));
				list.add(rmReserveVO);
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