package com.act_rp.model;

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

import com.emp.model.EmpVO;

public class ActRpDAO implements I_ActRpDAO{
	// 新增
	private final String INSERT =  "INSERT INTO act_rp(mem_no,act_no,act_rp_reason,act_rp_content,act_rp_time,act_rp_status)  VALUES ( ?, ?, ?, ?, now(),0)";
//	// 刪除
//	private final String DELETE = "DELETE FROM act_rp WHERE act_rp_no = ?";
	// 修改
	private final String UPDATE = "update  act_rp  set  act_no=?  ,act_rp_reason =?, emp_no=?  , act_rp_done_time= now(), act_rp_status=? WHERE act_rp_no = ?";
	// 顯示
	private final String GETALL = "select * from act_rp ";
	
	private final String GETONE ="select * from act_rp where act_rp_no = ?";

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
	public void insert(ActRpVO actRpVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try { con = ds.getConnection();
				ps = con.prepareStatement(INSERT);

			ps.setInt(1, actRpVO.getMem_no());
			ps.setInt(2, actRpVO.getAct_no());
			ps.setString(3, actRpVO.getAct_rp_reason());
			ps.setString(4, actRpVO.getAct_rp_content());
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

//	@Override
//	public void delete(ActRpVO ActRpVO) {
//
//	}
	

	@Override
	public void update(ActRpVO actRpVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try { con = ds.getConnection();
				ps = con.prepareStatement(UPDATE);
			ps.setInt(1, actRpVO.getAct_no());
			ps.setString(2, actRpVO.getAct_rp_reason());
			ps.setInt(3, actRpVO.getEmp_no());
			ps.setInt(4, actRpVO.getAct_rp_status());
			ps.setInt(5, actRpVO.getAct_rp_no());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	public List<ActRpVO> getall() {
		List<ActRpVO> actrpALL = new ArrayList<>();
		ActRpVO ar = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				ar = new ActRpVO();
				ar.setAct_rp_no(rs.getInt("Act_rp_no"));
				ar.setMem_no(rs.getInt("Mem_no"));
				ar.setAct_no(rs.getInt("Act_no"));
				ar.setAct_rp_reason(rs.getString("Act_rp_reason"));
				ar.setAct_rp_content(rs.getString("Act_rp_content"));
				ar.setAct_rp_time(rs.getDate("Act_rp_time"));
				ar.setEmp_no(rs.getInt("Emp_no"));
				ar.setAct_rp_done_time(rs.getDate("Act_rp_done_time"));
				ar.setAct_rp_status(rs.getInt("Act_rp_status"));
				ar.setAct_rp_note(rs.getString("Act_rp_note"));

				actrpALL.add(ar);
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
		return actrpALL;
	}
	
	@Override
	public ActRpVO getone(Integer act_rp_no) {
		ActRpVO actRpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONE);

			pstmt.setInt(1, act_rp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actRpVO = new ActRpVO();
				actRpVO.setAct_rp_no(rs.getInt("act_rp_no"));
				actRpVO.setMem_no(rs.getInt("mem_no"));
				actRpVO.setAct_no(rs.getInt("act_no"));
				actRpVO.setAct_rp_reason(rs.getString("act_rp_reason"));
				actRpVO.setAct_rp_content(rs.getString("act_rp_content"));
				actRpVO.setAct_rp_time(rs.getDate("act_rp_time"));
				actRpVO.setEmp_no(rs.getInt("emp_no"));
				actRpVO.setAct_rp_done_time(rs.getDate("act_rp_done_time"));
				actRpVO.setAct_rp_status(rs.getInt("act_rp_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
		return actRpVO;
	}
}
