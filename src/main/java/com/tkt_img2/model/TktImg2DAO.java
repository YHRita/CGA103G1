package com.tkt_img2.model;


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

import com.rm_pic.model.RmPicVO;



public class TktImg2DAO implements I_TktImg2DAO {

	private static final String INSERT = "INSERT INTO tkt_img (tkt_no, tkt_img) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE tkt_img SET tkt_img = ? where tkt_no = ?";
	private static final String DELETE = "DELETE FROM tkt_img WHERE tkt_img_no = ?";
	private static final String GET_TKT_IMG_BY_TKT_NO = "SELECT tkt_img FROM tkt_img WHERE tkt_no = ?";
	private static final String GET_ONE_BY_TKT_IMG_NO = "SELECT * FROM tkt_img WHERE tkt_img_no = ?";
	private static final String GET_ALL = "SELECT * FROM tkt_img";
	private static final String GET_ALL_BY_TKT_NO = "SELECT * FROM tkt_img WHERE tkt_no = ?";

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
	public TktImg2VO insert(TktImg2VO tktImg2VO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, tktImg2VO.getTkt_no());
			ps.setBytes(2, tktImg2VO.getTkt_img());

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
		return tktImg2VO;
	}
	
	@Override
	public void update(TktImg2VO tktImg2VO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setBytes(1, tktImg2VO.getTkt_img());
			ps.setInt(2, tktImg2VO.getTkt_no());

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
	public void delete(Integer tkt_img_no) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
//			TktImg2VO tktImg2VO = new TktImg2VO();

			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, tkt_img_no);

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
	public TktImg2VO getOne(Integer tkt_img_no) {
		TktImg2VO tktImg2VO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_BY_TKT_IMG_NO);
			ps.setInt(1, tkt_img_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				tktImg2VO = new TktImg2VO();
				tktImg2VO.setTkt_img_no(rs.getInt("tkt_img_no"));
				tktImg2VO.setTkt_no(rs.getInt("tkt_no"));
				tktImg2VO.setTkt_img(rs.getBytes("tkt_img"));
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
		return tktImg2VO;
		
	}

	@Override
	public List<TktImg2VO> getAll() {
		List<TktImg2VO> TktImgList = new ArrayList<>();
		TktImg2VO tktImg2VO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				tktImg2VO = new TktImg2VO();
				tktImg2VO.setTkt_img_no(rs.getInt("tkt_img_no"));
				tktImg2VO.setTkt_no(rs.getInt("tkt_no"));
				tktImg2VO.setTkt_img(rs.getBytes("tkt_img"));
				TktImgList.add(tktImg2VO);
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
		
		return TktImgList;
	}

	@Override
	public List<TktImg2VO> getAllByTktNo(Integer tkt_no) {
		List<TktImg2VO> list = new ArrayList<TktImg2VO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TktImg2VO tktImg2VO = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_BY_TKT_NO);
			ps.setInt(1, tkt_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				tktImg2VO = new TktImg2VO();
				tktImg2VO.setTkt_img_no(rs.getInt("tkt_no"));
				tktImg2VO.setTkt_no(rs.getInt("tkt_no"));
				tktImg2VO.setTkt_img(rs.getBytes("tkt_img"));
				list.add(tktImg2VO);
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
	


	

//	public String tkt_img_base64(byte[] b) {
//		String tkt_img = Base64.getEncoder().encodeToString(b);
//		return tkt_img;
//	}

	// 取得照片
//	@Override
//	public List<TktImg2VO> getTktImg(Integer tkt_no) {
//
//		TktImg2VO tktImg2VO;
//		ResultSet rs = null;
//		Connection con = null;
//		PreparedStatement ps = null;
//		List<TktImg2VO> list = new ArrayList<TktImg2VO>();
//
//		try {
//
//			con = ds.getConnection();
//			ps = con.prepareStatement(GET_TKT_IMG_BY_TKT_NO);
//
//			ps.setInt(1, tkt_no);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				tktImg2VO = new TktImg2VO();
//
//				tktImg2VO.setTkt_img_no(rs.getInt("tkt_img_no"));
//				tktImg2VO.setTkt_no(rs.getInt("tkt_no"));
//				if (rs.getBytes("tkt_img") != null) {
//					tktImg2VO.setTkt_img_base64(tkt_img_base64(rs.getBytes("tkt_img")));
//				}
//
//			}
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
//		return list;
//	}

//	// 插入照片
//	@Override
//	public byte[] insertTktImg(String path) {
//
//		byte[] tktImg = null;
//
//		try {
//			FileInputStream fis = new FileInputStream(path);
//			tktImg = new byte[fis.available()];
//			fis.read(tktImg);
//			fis.close();
//		} catch (Exception e) {
//
//		}
//		return tktImg;
//	}
//
//	@Override
//	public byte[] getOneTktImg(Integer tkt_img_no) {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		byte[] img = null;
//
//		try {
//
//			con = ds.getConnection();
//			ps = con.prepareStatement(GET_ONE_BY_TKT_IMG_NO);
//
//			ps.setInt(1, tkt_img_no);
//
//			rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				img = rs.getBytes("tkt_img");
//			}
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
//		return img;
//	}

	

	

//	@Override
//	public List<TktImg2VO> getOneList(Integer tkt_no) {
//
//		return null;
//	}

}
