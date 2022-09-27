//package com.rm_pic.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//import java.io.BufferedInputStream;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.servlet.annotation.MultipartConfig;
//import javax.sql.DataSource;
//
//@WebServlet("/readPic/")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//public class readPicServlet extends HttpServlet {
//	
//	private static final long serialVersionUID = 1L;
//	
//
//	
//	Connection con;
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		res.setContentType("image/gif");
//		ServletOutputStream out = res.getOutputStream();
//
//		try {
//			Statement ps = con.createStatement();
//			String rm_pic_no = req.getParameter("rm_pic_no").trim();
//			ResultSet rs = ps.executeQuery("select rm_pic from rm_pic where rm_pic_no = " + rm_pic_no);
//
//			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("rm_pic_no"));
//
//				// java 9寫法
//				byte[] buf = in.readAllBytes();
//				out.write(buf);
//
//				in.close();
//			} else {
////				res.sendError(HttpServletResponse.SC_NOT_FOUND);
//				// sql錯誤處理
//				InputStream in = getServletContext().getResourceAsStream("/frontend/assets/images/hotels/noimages.png");
//				byte[] b = new byte[in.available()];
//				in.read(b);
//				out.write(b);
//				in.close();
//			}
//			rs.close();
//			ps.close();
//		} catch (Exception e) {
//			// exception錯誤處理
//			InputStream in = getServletContext().getResourceAsStream("/frontend/assets/images/hotels/room1.jpg");
//			byte[] b = new byte[in.available()];
//			in.read(b);
//			out.write(b);
//			in.close();
//		}
//	}
//
//	public void init() throws ServletException {
//		try {
//			Context ctx = new InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
//			con = ds.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("連線池錯誤");
//		}
//	}
//
//	public void destroy() {
//		try {
//			if (con != null)
//				con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
//
//}