package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
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



public class StoreDAO implements I_StoreDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	private static final String INSERT = 
//			"INSERT INTO store (\r\n"
//			+ " store_acc, store_pwd,acc_status,store_name,store_gui,store_rep,store_tel,store_fax,store_add,store_poc,store_con_phone,\r\n"
//			+ " store_con_add,store_email,store_reg_date,bank_account,store_tkt_rating_score,store_tkt_rating_count,store_tkt_rating,\r\n"
//			+ " store_rm_rating_score,store_rm_rating_count,store_act_rating_score, store_act_rating_count,store_report_count \r\n"
//			+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT = 
			"INSERT INTO store (\r\n"
			+ " store_acc, store_pwd,store_name,store_gui,store_rep,store_tel,store_fax,store_add,store_poc,store_con_phone,\r\n"
			+ " store_con_add,store_email,store_reg_date,bank_account\r\n"
			+ ") \r\n"
			+ "VALUES\r\n"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?);";
	private static final String GETALL = 
			" SELECT store_no,store_acc, store_pwd,acc_status,store_name,store_gui,store_rep,store_tel,store_fax,store_add,store_poc,store_con_phone,\r\n"
			+ " store_con_add,store_email,store_reg_date,bank_account,\r\n"
			+ " store_rm_rating_score,store_rm_rating_count,store_report_count FROM store ORDER BY store_no";
	private static final String GETONE = 
			" SELECT store_no,store_acc, store_pwd,acc_status,store_name,store_gui,store_rep,store_tel,store_fax,store_add,store_poc,store_con_phone,\r\n"
			+ " store_con_add,store_email,store_reg_date,bank_account,\r\n"
			+ " store_rm_rating_score,store_rm_rating_count,store_report_count FROM store WHERE store_no = ?";
	
	private static final String GETONEBYACC = 
			" SELECT store_no,store_acc, store_pwd,acc_status,store_name,store_gui,store_rep,store_tel,store_fax,store_add,store_poc,store_con_phone,\r\n"
			+ " store_con_add,store_email,store_reg_date,bank_account,\r\n"
			+ " store_rm_rating_score,store_rm_rating_count,store_report_count FROM store WHERE store_acc = ?";
	
	private static final String LOGIN = " SELECT store_no,store_acc,store_pwd,acc_status,store_name,store_gui,store_rep,store_tel,store_fax,store_add,store_poc,store_con_phone,\r\n"
			+ "			+ store_con_add,store_email,store_reg_date,bank_account,\r\n"
			+ "			+ store_rm_rating_score,store_rm_rating_count,store_report_count FROM store WHERE store_acc = ? AND store_pwd =?;";
	
	private static final String DELETE = 
			"DELETE FROM store WHERE store_no= ?";
	private static final String	UPDATE = 
			" UPDATE store SET \r\n"
			+ " store_acc = ?, store_pwd = ?,acc_status = ?,store_name = ?,store_gui = ?,store_rep = ?,store_tel = ?,store_fax = ?,\r\n"
			+ " store_add = ?,store_poc = ?,store_con_phone = ?,store_con_add = ?,store_email = ?,store_reg_date = ?,bank_account = ?,\r\n"
			+ " store_rm_rating_score = ?,\r\n"
			+ " store_rm_rating_count = ?,store_report_count = ?\r\n"
			+ " WHERE store_no = ?";
	
	private static final String UPDATEPASSWORD = "UPDATE store SET store_pwd = ? WHERE store_no = ?;";
	
	private static final String GETONEBYEMAIL ="SELECT * FROM store WHERE store_email = ?;";
	
	
	private static final String SURVY = "SELECT * FROM store WHERE acc_status = 0;";
	
	private static final String BACKENDUPDATE = "UPDATE store SET store_pwd = ?,acc_status = ?,store_name = ?,store_gui = ?,store_rep = ?,store_tel = ?,store_fax = ?,\r\n"
			+ "store_add = ?,store_poc = ?,store_con_phone = ?,store_con_add = ?,store_email = ?,bank_account = ? WHERE store_no = ?;";
	@Override
	public void insert(StoreVO storeVO) {
		 Connection con = null;
		 PreparedStatement ps = null;
		
		try{
				con = ds.getConnection();
				ps = con.prepareStatement(INSERT);
			ps.setString(1, storeVO.getStore_acc());
			ps.setString(2, storeVO.getStore_pwd());
			ps.setString(3,storeVO.getStore_name());
			ps.setString(4,storeVO.getStore_gui());
			ps.setString(5,storeVO.getStore_rep());
			ps.setString(6,storeVO.getStore_tel());
			ps.setString(7,storeVO.getStore_fax());
			ps.setString(8,storeVO.getStore_add());
			ps.setString(9,storeVO.getStore_poc());
			ps.setString(10,storeVO.getStore_con_phone());
			ps.setString(11,storeVO.getStore_con_add());
			ps.setString(12,storeVO.getStore_email());
//			ps.setDate(13,storeVO.getStore_reg_date());
			ps.setString(13,storeVO.getBank_account());
			
			
			ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
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
	public void update(StoreVO storeVO) {
		Connection con = null;
			PreparedStatement ps = null;
		try { con = ds.getConnection();
				ps = con.prepareStatement(UPDATE);
					ps.setString(1, storeVO.getStore_acc());
					ps.setString(2, storeVO.getStore_pwd());
					ps.setInt(3,storeVO.getAcc_status());
					ps.setString(4,storeVO.getStore_name());
					ps.setString(5,storeVO.getStore_gui());
					ps.setString(6,storeVO.getStore_rep());
					ps.setString(7,storeVO.getStore_tel());
					ps.setString(8,storeVO.getStore_fax());
					ps.setString(9,storeVO.getStore_add());
					ps.setString(10,storeVO.getStore_poc());
					ps.setString(11,storeVO.getStore_con_phone());
					ps.setString(12,storeVO.getStore_con_add());
					ps.setString(13,storeVO.getStore_email());
					ps.setDate(14,storeVO.getStore_reg_date());
					ps.setString(15,storeVO.getBank_account());
					ps.setInt(16,storeVO.getStore_rm_rating_score());
					ps.setInt(17,storeVO.getStore_rm_rating_count());
					ps.setInt(18,storeVO.getStore_report_count());
					ps.setInt(19, storeVO.getStore_no());
					
					ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
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
	public void backendUpdate(StoreVO storeVO) {
		Connection con = null;
			PreparedStatement ps = null;
		try { con = ds.getConnection();
				ps = con.prepareStatement(BACKENDUPDATE);
					ps.setString(1, storeVO.getStore_pwd());
					ps.setInt(2,storeVO.getAcc_status());
					ps.setString(3,storeVO.getStore_name());
					ps.setString(4,storeVO.getStore_gui());
					ps.setString(5,storeVO.getStore_rep());
					ps.setString(6,storeVO.getStore_tel());
					ps.setString(7,storeVO.getStore_fax());
					ps.setString(8,storeVO.getStore_add());
					ps.setString(9,storeVO.getStore_poc());
					ps.setString(10,storeVO.getStore_con_phone());
					ps.setString(11,storeVO.getStore_con_add());
					ps.setString(12,storeVO.getStore_email());
					ps.setString(13,storeVO.getBank_account());
					ps.setInt(14, storeVO.getStore_no());
					
					ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
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
	
	
	public void updatePassword(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement ps =null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATEPASSWORD);
			ps.setString(1, storeVO.getStore_pwd());
			ps.setInt(2, storeVO.getStore_no());
			
			ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
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
	public void delete(Integer store_no) {
			Connection con = null;
			PreparedStatement ps =null;
			try{
				con = ds.getConnection();
				ps = con.prepareStatement(DELETE);
				
				ps.setInt(1, store_no);
				
				ps.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public StoreVO getOneStore(Integer store_no) {
		
		StoreVO	storeVO = null;
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{   con = ds.getConnection();
				 ps = con.prepareStatement(GETONE);
								
			ps.setInt(1, store_no);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return storeVO;
	}
	
	public StoreVO getOneStoreByEmail(String store_email) {
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETONEBYEMAIL);
			ps.setString(1, store_email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		
		return storeVO;
	}
	
	@Override
	public StoreVO getOneStoreByAcc(String store_acc) {
		
		StoreVO	storeVO = null;
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{   con = ds.getConnection();
				 ps = con.prepareStatement(GETONEBYACC);
								
			ps.setString(1, store_acc);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return storeVO;
	}

	@Override
	public List<StoreVO> getAllStore() {

		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;
		
		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{ 
				con = ds.getConnection();
				 ps = con.prepareStatement(GETALL);
					 rs =ps.executeQuery();
			while(rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
				list.add(storeVO);
				
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public StoreVO Login(String store_acc, String Store_pwd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StoreVO storeVO = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(LOGIN);
			ps.setString(1, store_acc);
			ps.setString(2, Store_pwd);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
		return storeVO;
		
	}
	
	public List<StoreVO>  Survy() {

		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;
		
		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{ 
				con = ds.getConnection();
				 ps = con.prepareStatement(SURVY);
				rs =ps.executeQuery();
			while(rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
				list.add(storeVO);
				
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public List<StoreVO> CompositeQuery(Map<String, String[]> map){
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO= null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		
		try {
		 con = ds.getConnection();
		 String finalSQL = "SELECT * FROM store "+ 
				 		   StoreCompositeQuery.get_WhereCondition(map)+
				 		   " ORDER BY store_no ";
		 ps = con.prepareStatement(finalSQL);
		 rs = ps.executeQuery();
		 
		 while(rs.next()) {
			 
			 storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getInt("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
				list.add(storeVO);	 
		 }
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
}
