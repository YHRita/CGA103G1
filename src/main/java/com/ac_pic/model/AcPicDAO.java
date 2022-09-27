package com.ac_pic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.act.model.ActVO;

public class AcPicDAO implements I_AcPicDAO {
	private static final String INSERT = "INSERT INTO ac_pic(ac_no, ac_pic_img)VALUES(?,?)";
	private static final String UPDATE = "UPDATE ac_pic SET ac_pic_img=? WHERE ac_no=?";
	private static final String DELETE = "DELETE FROM ac_pic WHERE ac_pic_no=?";
	private static final String GET_ONE = "SELECT * FROM ac_pic WHERE ac_pic_no=?";
	private static final String GET_ALL = "SELECT * FROM ac_pic";
	
	private static final String GET_ONE_PIC_BY_AC_NO = "select ac_pic_img from ac_pic where ac_no = ?";
	
	
	
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
	public void insert(AcPicVO acPicVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			ps.setInt(1, acPicVO.getAc_no());
			ps.setBytes(2, acPicVO.getAc_pic_img());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(AcPicVO acPicVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setBytes(1, acPicVO.getAc_pic_img());
			ps.setInt(2, acPicVO.getAc_no());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer ac_pic_no) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, ac_pic_no);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public AcPicVO getOne(Integer ac_pic_no) {
		ResultSet rs = null;
		AcPicVO ac = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);
			ps.setInt(1, ac_pic_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				ac = new AcPicVO();
				ac.setAc_pic_no(rs.getInt("ac_pic_no"));
				ac.setAc_no(rs.getInt("ac_no"));
				ac.setAc_pic_img(rs.getBytes("ac_pic_img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ac;
	}

	@Override
	public List<AcPicVO> getAll() {
		List<AcPicVO> acAll = new ArrayList<>();
		ResultSet rs = null;
		AcPicVO ac = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();
			while (rs.next()) {
				ac = new AcPicVO();
				ac.setAc_pic_no(rs.getInt("ac_pic_no"));
				ac.setAc_no(rs.getInt("ac_no"));
				ac.setAc_pic_img(rs.getBytes("ac_pic_img"));
				acAll.add(ac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return acAll;
	}
	
	
	
	@Override
	public List<AcPicVO> getOneByAcNo(Integer acNo){
		List<AcPicVO> acPic = new ArrayList<>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ONE_PIC_BY_AC_NO)) {
			ps.setInt(1, acNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				byte[] ac_pic = rs1.getBytes(1);
				AcPicVO acPicVO =  new AcPicVO(ac_pic);			
				acPic.add(acPicVO);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acPic;
	}
}
