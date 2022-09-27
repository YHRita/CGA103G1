package com.act_reply.model;

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

public class ActReplyDAO implements I_ActReplyDAO {
	// 新增
	private final String INSERT = "INSERT INTO act_reply(act_no,reply_content,mem_no,reply_time)  VALUES (?, ?, ?,now())";
	// 刪除
	private final String DELETE = "DELETE FROM act_reply WHERE act_reply_no = ?";
	// 顯示
	private final String GETALL = "select * from act_reply ";

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
	public void insert(ActReplyVO actReplyVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, actReplyVO.getAct_no());
			ps.setString(2, actReplyVO.getReply_content());
			ps.setInt(3, actReplyVO.getMem_no());
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
	public void delete(Integer act_reply_no) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, act_reply_no);
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
	public List<ActReplyVO> getall() {
		List<ActReplyVO> actreplyALL = new ArrayList<>();
		ActReplyVO ar = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {con = ds.getConnection();
				ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				ar = new ActReplyVO();
				ar.setAct_reply_no(rs.getInt("Act_reply_no"));
				ar.setAct_no(rs.getInt("Act_no"));
				ar.setReply_content(rs.getString("reply_content"));
				ar.setMem_no(rs.getInt("mem_no"));
				ar.setReply_time(rs.getDate("reply_time"));

				actreplyALL.add(ar);
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
		return actreplyALL;
	}
}
