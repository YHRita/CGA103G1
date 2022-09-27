package com.last_news.model;

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

public class LastNewsDAO implements I_LastNewsDAO {
	// 新增訊息
	private final String INSERT = "INSERT INTO last_news(news,news_img,news_time)  VALUES (?, ?, now())";
	// 下架訊息
	private final String DELETE = "DELETE FROM last_news WHERE news_no = ?";
	// 顯示訊息
	private final String GETALL = "select * from last_news order by news_no desc";
	// 顯示訊息
	private final String GETONE = "select * from last_news WHERE news_no = ?";
	// 顯示圖片
	private final String GETONEPIC = "select news_img from last_news WHERE news_no = ?";
	// 顯示訊息
	private final String GETLAST = "select * from last_news order by news_no desc limit 0,1";
	private final String GETTHREE = "select * from last_news order by news_no desc limit 0,3";
	
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
	public void insert(LastNewsVO lastNewsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setString(1, lastNewsVO.getNews());
			ps.setBytes(2, lastNewsVO.getNews_img());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
	public void delete(Integer news_no) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, news_no);
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
	public List<LastNewsVO> getall() {
		List<LastNewsVO> newsALL = new ArrayList<>();
		LastNewsVO ln = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				ln = new LastNewsVO();
				ln.setNews_no(rs.getInt("News_no"));
				ln.setNews(rs.getString("News"));
				ln.setNews_img(rs.getBytes("News_img"));
				ln.setNews_time(rs.getDate("News_time"));

				newsALL.add(ln);
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
		return newsALL;
	}
	@Override
	public List<LastNewsVO> getthree() {
		List<LastNewsVO> newsALL = new ArrayList<>();
		LastNewsVO ln = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETTHREE);
			rs = ps.executeQuery();
			while (rs.next()) {
				ln = new LastNewsVO();
				ln.setNews_no(rs.getInt("News_no"));
				ln.setNews(rs.getString("News"));
				ln.setNews_img(rs.getBytes("News_img"));
				ln.setNews_time(rs.getDate("News_time"));
				
				newsALL.add(ln);
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
		return newsALL;
	}

	@Override
	public LastNewsVO getone(Integer news_no) {
		LastNewsVO newsOne = new LastNewsVO();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETONE);
			ps.setInt(1, news_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				newsOne = new LastNewsVO();
				newsOne.setNews_no(rs.getInt("news_no"));
				newsOne.setNews(rs.getString("news"));
				newsOne.setNews_img(rs.getBytes("news_img"));
				newsOne.setNews_time(rs.getDate("news_time"));
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
		return newsOne;
	}

	@Override
	public LastNewsVO getOnePic(Integer news_no) {
		LastNewsVO lastNews = new LastNewsVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONEPIC);

			pstmt.setInt(1, news_no);
			rs = pstmt.executeQuery();

			lastNews = new LastNewsVO();
			lastNews.setNews_img(rs.getBytes("news_img"));

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
		return lastNews;
	}
	@Override
	public LastNewsVO getlast() {
		LastNewsVO ln = new LastNewsVO();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETLAST);
			rs = ps.executeQuery();
			while (rs.next()) {
				ln = new LastNewsVO();
				ln.setNews(rs.getString("News"));
				ln.setNews_img(rs.getBytes("News_img"));
				ln.setNews_no(rs.getInt("News_no"));
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
