package com.mem_coup.model;

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

public class MemCoupDAO implements I_MemCoupDAO {

	private static final String INSERT = "INSERT INTO mem_coup (mem_no, coup_no, coup_state) VALUES (?, ?, 0);";
	private static final String CHANGE_STATE = "UPDATE mem_coup SET coup_state = ? WHERE mem_coup_no = ?;";
	private static final String USED_STATE = "UPDATE mem_coup SET coup_state = 1 WHERE mem_coup_no = ?;";
	private static final String ORVERDUE_STATE = "UPDATE mem_coup SET coup_state = 2 WHERE mem_coup_no = ?;";
	private static final String GET_ONE = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup WHERE mem_coup_no = ?;";
	private static final String GET_ALL = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup ORDER BY mem_coup_no DESC;";
	private static final String GET_MEMCOUP_BY_MEMNO = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup WHERE mem_no =? ORDER BY mem_coup_no DESC";

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
	public void insert(MemCoupVO memCoupVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, memCoupVO.getMem_no());
			ps.setInt(2, memCoupVO.getCoup_no());

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
	public void update(MemCoupVO memCoupVO) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(CHANGE_STATE);

			ps.setInt(1, memCoupVO.getCoup_state());
			ps.setInt(2, memCoupVO.getMem_coup_no());

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
	public MemCoupVO findByPrimaryKey(Integer mem_coup_no) {

		MemCoupVO memCoupVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, mem_coup_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				memCoupVO = new MemCoupVO();

				memCoupVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				memCoupVO.setMem_no(rs.getInt("mem_no"));
				memCoupVO.setCoup_no(rs.getInt("coup_no"));
//				memCoupVO.setTkt_order_no(null);
				memCoupVO.setCoup_state(rs.getInt("coup_state"));
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

		return memCoupVO;
	}

	@Override
	public List<MemCoupVO> getAll() {

		List<MemCoupVO> list = new ArrayList<MemCoupVO>();
		MemCoupVO memCoupVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();

			while (rs.next()) {
				memCoupVO = new MemCoupVO();

				memCoupVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				memCoupVO.setMem_no(rs.getInt("mem_no"));
				memCoupVO.setCoup_no(rs.getInt("coup_no"));
//				memCoupVO.setTkt_order_no(null);
				memCoupVO.setCoup_state(rs.getInt("coup_state"));

				list.add(memCoupVO);
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

	// 讓會員查看自己擁有哪些優惠券
	@Override
	public List<MemCoupVO> getOneMember(Integer mem_no) {
		List<MemCoupVO> list = new ArrayList<MemCoupVO>();
		MemCoupVO memCoupVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_MEMCOUP_BY_MEMNO);

			ps.setInt(1, mem_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				memCoupVO = new MemCoupVO();

				memCoupVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				memCoupVO.setMem_no(rs.getInt("mem_no"));
				memCoupVO.setCoup_no(rs.getInt("coup_no"));
//				memCoupVO.setTkt_order_no(null);
				memCoupVO.setCoup_state(rs.getInt("coup_state"));

				list.add(memCoupVO);
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
	public void changeState(Integer mem_coup_no, Integer coup_state) {
		Connection con = null;
		PreparedStatement ps = null;
//		MemCoupVO memCoupVO = null;
		
		try {
//			memCoupVO = new MemCoupVO();
			con = ds.getConnection();
			ps = con.prepareStatement(CHANGE_STATE);

			ps.setInt(1, mem_coup_no);
			ps.setInt(2, coup_state);

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
	public void memCoupUsedState(Integer mem_coup_no) {
		Connection con = null;
		PreparedStatement ps = null;
//		MemCoupVO memCoupVO = null;
		
		try {
//			memCoupVO = new MemCoupVO();
			con = ds.getConnection();
			ps = con.prepareStatement(USED_STATE);

			ps.setInt(1, mem_coup_no);

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
	public void memCoupOverdueState(Integer mem_coup_no) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(ORVERDUE_STATE);

			ps.setInt(1, mem_coup_no);

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

}
