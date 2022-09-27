package com.tkt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TktDAO implements I_TktDAO {

	private static final String INSERT = "INSERT INTO tkt (tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`,  instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE tkt SET tkt_name = ?, original_amount = ?, price = ?, tkt_startdate = ?, tkt_enddate = ?, `locate` = ?,  instruction = ?, address = ?, notice = ?, howuse = ?, canxpolicy = ?, tkt_status = ?, sold_amount = ?, kind = ? WHERE tkt_no = ?;";
//	private static final String UPDATE = "UPDATE tkt SET price = ? WHERE tkt_name = ?;";
	private static final String GET_ONE = "SELECT tkt_no ,tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`, "
			+ " instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind FROM tkt WHERE tkt_no = ?;";
	private static final String GET_ALL = "SELECT tkt_no ,tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`, "
			+ " instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind FROM tkt ORDER BY tkt_no;";

	private static final String UPDATE_SOLD_AMOUNT = "UPDATE tkt SET sold_amount = ? WHERE tkt_no = ?;";
	private static final String UPDATE_ORI_AMOUNT = "UPDATE tkt SET original_amount = (original_amount - sold_amount) WHERE tkt_no = ?;";

	private static final String GET_ALL_BY_STATUS = "SELECT tkt_no ,tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`, "
			+ " instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind FROM tkt WHERE tkt_status = 1 ORDER BY tkt_no;";
	private static final String FIND_TKT_ROWS = "SELECT COUNT(tkt_no) FROM tkt;";

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
	public void insert(TktVO tktVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setString(1, tktVO.getTkt_name());
			ps.setInt(2, tktVO.getOriginal_amount());
			ps.setInt(3, tktVO.getPrice());
			ps.setObject(4, tktVO.getTkt_startdate());
			ps.setObject(5, tktVO.getTkt_enddate());
			ps.setString(6, tktVO.getLocate());
			ps.setString(7, tktVO.getInstruction());
			ps.setString(8, tktVO.getAddress());
			ps.setString(9, tktVO.getNotice());
			ps.setString(10, tktVO.getHowuse());
			ps.setString(11, tktVO.getCanxpolicy());
			ps.setInt(12, tktVO.getTkt_status());
			ps.setInt(13, 0);
			ps.setInt(14, tktVO.getKind());

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
	public void update(TktVO tktVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, tktVO.getTkt_name());
			ps.setInt(2, tktVO.getOriginal_amount());
			ps.setInt(3, tktVO.getPrice());
			ps.setObject(4, tktVO.getTkt_startdate());
			ps.setObject(5, tktVO.getTkt_enddate());
			ps.setString(6, tktVO.getLocate());
			ps.setString(7, tktVO.getInstruction());
			ps.setString(8, tktVO.getAddress());
			ps.setString(9, tktVO.getNotice());
			ps.setString(10, tktVO.getHowuse());
			ps.setString(11, tktVO.getCanxpolicy());
			ps.setInt(12, tktVO.getTkt_status());
			ps.setInt(13, tktVO.getSold_amount());
			ps.setInt(14, tktVO.getKind());
			ps.setInt(15, tktVO.getTkt_no());

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
	public TktVO findByPrimaryKey(Integer tkt_no) {

		TktVO tktVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, tkt_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				tktVO = new TktVO();

				tktVO.setTkt_no(rs.getInt("tkt_no"));
				tktVO.setTkt_name(rs.getString("tkt_name"));
				tktVO.setOriginal_amount(rs.getInt("original_amount"));
				tktVO.setPrice(rs.getInt("price"));
				tktVO.setTkt_startdate(rs.getDate("tkt_startdate"));
				tktVO.setTkt_enddate(rs.getDate("tkt_enddate"));
				tktVO.setLocate(rs.getString("locate"));
				tktVO.setInstruction(rs.getString("instruction"));
				tktVO.setAddress(rs.getString("address"));
				tktVO.setNotice(rs.getString("notice"));
				tktVO.setHowuse(rs.getString("howuse"));
				tktVO.setCanxpolicy(rs.getString("canxpolicy"));
				tktVO.setTkt_status(rs.getInt("tkt_status"));
				tktVO.setSold_amount(rs.getInt("sold_amount"));
				tktVO.setKind(rs.getInt("kind"));

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

		return tktVO;
	}

	@Override
	public List<TktVO> getAll() {

		List<TktVO> list = new ArrayList<TktVO>();
		TktVO tktVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();

			while (rs.next()) {
				tktVO = new TktVO();

				tktVO.setTkt_no(rs.getInt("tkt_no"));
				tktVO.setTkt_name(rs.getString("tkt_name"));
				tktVO.setOriginal_amount(rs.getInt("original_amount"));
				tktVO.setPrice(rs.getInt("price"));
				tktVO.setTkt_startdate(rs.getDate("tkt_startdate"));
				tktVO.setTkt_enddate(rs.getDate("tkt_enddate"));
				tktVO.setLocate(rs.getString("locate"));
				tktVO.setInstruction(rs.getString("instruction"));
				tktVO.setAddress(rs.getString("address"));
				tktVO.setNotice(rs.getString("notice"));
				tktVO.setHowuse(rs.getString("howuse"));
				tktVO.setCanxpolicy(rs.getString("canxpolicy"));
				tktVO.setTkt_status(rs.getInt("tkt_status"));
				tktVO.setSold_amount(rs.getInt("sold_amount"));
				tktVO.setKind(rs.getInt("kind"));

				list.add(tktVO);
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
	public void updateSoldAmount(Integer redisCount, Integer tkt_no, Connection con) {

//		Connection con = null;
		PreparedStatement ps = null;
		TktVO tktVO = null;

		try {
			tktVO = new TktVO();
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_SOLD_AMOUNT);

			ps.setInt(1, redisCount);
			ps.setInt(2, tkt_no);

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
//	public void updateSoldAmount(TktVO tktVO) {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(UPDATE_SOLD_AMOUNT);
//
//			ps.setInt(1, tktVO.getSold_amount());
//			ps.setInt(2, tktVO.getTkt_no());
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
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
	public void updateOriAmount(Integer tkt_no, Connection con) {

//		Connection con = null;
//		TktVO tktVO = null;
		PreparedStatement ps = null;

		try {
//			tktVO = new TktVO();
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_ORI_AMOUNT);

//			ps.setInt(1, num);
			ps.setInt(1, tkt_no);

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
//	public void updateOriAmount(TktVO tktVO) {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		try {
//
//			con = ds.getConnection();
//			ps = con.prepareStatement(UPDATE_ORI_AMOUNT);
//
//			ps.setInt(1, tktVO.getTkt_no());
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
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

	// 複合查詢 getAll
	@Override
	public List<TktVO> getAll(Map<String, String[]> map) {

		List<TktVO> list = new ArrayList<TktVO>();
		TktVO tktVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();

			String finalSQL = "select * from tkt " + TktCompositeQuery.get_WhereCondition(map) + "order by tkt_no";

			ps = con.prepareStatement(finalSQL);

			rs = ps.executeQuery();

			while (rs.next()) {
				tktVO = new TktVO();

				tktVO.setTkt_no(rs.getInt("tkt_no"));
				tktVO.setTkt_name(rs.getString("tkt_name"));
				tktVO.setOriginal_amount(rs.getInt("original_amount"));
				tktVO.setPrice(rs.getInt("price"));
				tktVO.setTkt_startdate(rs.getDate("tkt_startdate"));
				tktVO.setTkt_enddate(rs.getDate("tkt_enddate"));
				tktVO.setLocate(rs.getString("locate"));
				tktVO.setInstruction(rs.getString("instruction"));
				tktVO.setAddress(rs.getString("address"));
				tktVO.setNotice(rs.getString("notice"));
				tktVO.setHowuse(rs.getString("howuse"));
				tktVO.setCanxpolicy(rs.getString("canxpolicy"));
				tktVO.setTkt_status(rs.getInt("tkt_status"));
				tktVO.setSold_amount(rs.getInt("sold_amount"));
				tktVO.setKind(rs.getInt("kind"));

				list.add(tktVO);
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

	// 顯示所有已上架的票券 tktVO.tkt_status == 1
	public List<TktVO> getAllByStatus() {

		List<TktVO> list = new ArrayList<TktVO>();
		TktVO tktVO = null;
		ResultSet rs = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_BY_STATUS);

			rs = ps.executeQuery();

			while (rs.next()) {
				tktVO = new TktVO();

				tktVO.setTkt_no(rs.getInt("tkt_no"));
				tktVO.setTkt_name(rs.getString("tkt_name"));
				tktVO.setOriginal_amount(rs.getInt("original_amount"));
				tktVO.setPrice(rs.getInt("price"));
				tktVO.setTkt_startdate(rs.getDate("tkt_startdate"));
				tktVO.setTkt_enddate(rs.getDate("tkt_enddate"));
				tktVO.setLocate(rs.getString("locate"));
				tktVO.setInstruction(rs.getString("instruction"));
				tktVO.setAddress(rs.getString("address"));
				tktVO.setNotice(rs.getString("notice"));
				tktVO.setHowuse(rs.getString("howuse"));
				tktVO.setCanxpolicy(rs.getString("canxpolicy"));
				tktVO.setTkt_status(rs.getInt("tkt_status"));
				tktVO.setSold_amount(rs.getInt("sold_amount"));
				tktVO.setKind(rs.getInt("kind"));

				list.add(tktVO);
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
	public Integer find_tkt_rows() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FIND_TKT_ROWS);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1); // 取得第一個欄位的值即為筆數
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
		return 0;
	}

}
