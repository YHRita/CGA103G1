package com.coup.model;




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

public class CoupDAO implements I_CoupDAO {

	private static final String INSERT = "INSERT INTO coup (coup_name, introduce, discount, startdate, enddate, `status`) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE coup SET coup_name = ?, introduce = ?, discount = ?, startdate = ?, enddate = ?, `status` = ? WHERE coup_no = ?;";
	private static final String UPDATE_STATUS = "UPDATE coup SET status = 0 WHERE coup_no = ?;";
	private static final String GET_ONE = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup WHERE coup_no = ?;";
	private static final String GET_ALL = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup ORDER BY coup_no DESC;";
	private static final String GET_ONE_BY_ENDDATE = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup WHERE enddate = ? ORDER BY coup_no;";
	
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
	public void insert(CoupVO coupVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, coupVO.getCoup_name());
			ps.setString(2, coupVO.getIntroduce());
			ps.setInt(3, coupVO.getDiscount());
			ps.setObject(4, coupVO.getStartdate());
			ps.setObject(5, coupVO.getEnddate());
			ps.setInt(6, coupVO.getStatus());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	public void update(CoupVO coupVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, coupVO.getCoup_name());
			ps.setString(2, coupVO.getIntroduce());
			ps.setInt(3, coupVO.getDiscount());
			ps.setObject(4, coupVO.getStartdate());
			ps.setObject(5, coupVO.getEnddate());
			ps.setInt(6, coupVO.getStatus());
			ps.setInt(7, coupVO.getCoup_no());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	public void updateStatus(Integer coup_no) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STATUS);

			ps.setInt(1, coup_no);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
		
	};

	@Override
	public CoupVO findByPrimaryKey(Integer coup_no) {
		CoupVO coupVO = null;
		ResultSet rs = null;
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, coup_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				coupVO = new CoupVO();

				coupVO.setCoup_no(rs.getInt("coup_no"));
				coupVO.setCoup_name(rs.getString("coup_name"));
				coupVO.setIntroduce(rs.getString("introduce"));
				coupVO.setDiscount(rs.getInt("discount"));
				coupVO.setStartdate(rs.getDate("startdate"));
				coupVO.setEnddate(rs.getDate("enddate"));
				coupVO.setStatus(rs.getInt("status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
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

		return coupVO;
	}

	@Override
	public List<CoupVO> getAll() {
		List<CoupVO> list = new ArrayList<CoupVO>();
		CoupVO coupVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();

			while (rs.next()) {
				coupVO = new CoupVO();

				coupVO.setCoup_no(rs.getInt("coup_no"));
				coupVO.setCoup_name(rs.getString("coup_name"));
				coupVO.setIntroduce(rs.getString("introduce"));
				coupVO.setDiscount(rs.getInt("discount"));
				coupVO.setStartdate(rs.getDate("startdate"));
				coupVO.setEnddate(rs.getDate("enddate"));
				coupVO.setStatus(rs.getInt("status"));

				list.add(coupVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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

		return list;
	}
	
	@Override
	public List<CoupVO> getByEndDate(Date enddate) {
		List<CoupVO> list = new ArrayList<CoupVO>();
		CoupVO coupVO = null;
		ResultSet rs = null;
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_BY_ENDDATE);

			ps.setObject(1, enddate);
			rs = ps.executeQuery();

			while (rs.next()) {
				coupVO = new CoupVO();

				coupVO.setCoup_no(rs.getInt("coup_no"));
				coupVO.setCoup_name(rs.getString("coup_name"));
				coupVO.setIntroduce(rs.getString("introduce"));
				coupVO.setDiscount(rs.getInt("discount"));
				coupVO.setStartdate(rs.getDate("startdate"));
				coupVO.setEnddate(rs.getDate("enddate"));
				coupVO.setStatus(rs.getInt("status"));

				list.add(coupVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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

		return list;
	}

}
