package com.system_notification_message.model;

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

import com.last_news.model.LastNewsVO;

public class SystemNotificationMessageDAO implements I_SystemNotificationMessageDAO {
	// 新增訊息
	private final String INSERT = "INSERT INTO system_notfication_message(msg,msg_img,msg_time,emp_no)  VALUES (?, ?, NOW(),?)";
	// 下架訊息
	private final String DELETE = "DELETE FROM system_notfication_message WHERE msg_no = ?";
	// 顯示訊息
	private final String GETALL = "select * from system_notfication_message order by msg_no desc";
	// 顯示圖片
	private final String GETPIC = "select msg_img from system_notfication_message where where msg_no = ?";
	// 取得一筆資料
	private final String GETONE ="select * from system_notfication_message WHERE msg_no = ?";
	
	// 顯示訊息
		private final String GETLAST = "select * from system_notfication_message order by msg_no desc limit 0,1";
		
		private final String GETTHREE = "select * from system_notfication_message order by msg_no desc limit 0,3";
	
	
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
	public void insert(SystemNotificationMessageVO systemNotificationMessageVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, systemNotificationMessageVO.getMsg());
			ps.setBytes(2, systemNotificationMessageVO.getMsg_img());
			ps.setInt(3, systemNotificationMessageVO.getEmp_no());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer msg_no) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, msg_no);
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<SystemNotificationMessageVO> getall() {
		List<SystemNotificationMessageVO> snmALL = new ArrayList<>();
		SystemNotificationMessageVO snm = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				snm = new SystemNotificationMessageVO();
				snm.setMsg_no(rs.getInt("msg_no"));
				snm.setMsg(rs.getString("msg"));
				snm.setMsg_img(rs.getBytes("msg_img"));
				snm.setMsg_time(rs.getDate("msg_time"));
				snm.setEmp_no(rs.getInt("emp_no"));

				snmALL.add(snm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return snmALL;
	}
	@Override
	public List<SystemNotificationMessageVO> getthree() {
		List<SystemNotificationMessageVO> snmALL = new ArrayList<>();
		SystemNotificationMessageVO snm = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETTHREE);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				snm = new SystemNotificationMessageVO();
				snm.setMsg_no(rs.getInt("msg_no"));
				snm.setMsg(rs.getString("msg"));
				snm.setMsg_img(rs.getBytes("msg_img"));
				
				snmALL.add(snm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return snmALL;
	}
	
	@Override
	public SystemNotificationMessageVO getpic(Integer msg_no) {
		SystemNotificationMessageVO snmVO = new SystemNotificationMessageVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETPIC);

			pstmt.setInt(1, msg_no);
			rs = pstmt.executeQuery();

			snmVO = new SystemNotificationMessageVO();
			snmVO.setMsg_img(rs.getBytes("msg_img"));

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
		return snmVO;
	}
	
	@Override
	public SystemNotificationMessageVO getone(Integer msg_no) {
		SystemNotificationMessageVO snmVO = new SystemNotificationMessageVO();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETONE);
			ps.setInt(1, msg_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				snmVO = new SystemNotificationMessageVO();
				snmVO.setMsg_no(rs.getInt("msg_no"));
				snmVO.setMsg(rs.getString("msg"));
				snmVO.setMsg_img(rs.getBytes("msg_img"));
				snmVO.setMsg_time(rs.getDate("msg_time"));
				snmVO.setEmp_no(rs.getInt("emp_no"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return snmVO;
	}
	
	
	public SystemNotificationMessageVO getlast() {
		SystemNotificationMessageVO ln = new SystemNotificationMessageVO();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETLAST);
			rs = ps.executeQuery();
			while (rs.next()) {
				ln = new SystemNotificationMessageVO();
				ln.setMsg(rs.getString("msg"));
				ln.setMsg_img(rs.getBytes("msg_img"));
				ln.setMsg_no(rs.getInt("msg_no"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ln;
	}
}
