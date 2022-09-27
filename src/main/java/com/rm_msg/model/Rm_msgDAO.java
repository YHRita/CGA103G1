package com.rm_msg.model;

import java.sql.Connection;
import com.mem.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Rm_msgDAO implements I_Rm_msgDAO{
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(
				Exception e) {
			e.getStackTrace();
		}
	}

	
	@Override
	public void insert(Rm_msgVO rm_msgVO) {
		Connection con = null;
		PreparedStatement ps =null;
		String sql = "INSERT INTO rm_msg ( mem_no, store_no, rm_msg_reason,rm_msg_date) \r\n"
				+ "VALUE(?,?,?, NOW());";
		
		try {
				con = ds.getConnection();
				 ps = con.prepareStatement(sql);
				
			
			ps.setInt(1, rm_msgVO.getMem_no());
			ps.setInt(2, rm_msgVO.getStore_no());
			ps.setString(3, rm_msgVO.getRm_msg_reason());
//			ps.setDate(5, rm_msgVO.getRm_msg_date());
			
			
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
	public void update(Rm_msgVO rm_msgVO) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE rm_msg SET emp_no = ?, store_no = ?, rm_msg_status=?, rm_msg_donetime=? WHERE rm_msg_no = ?;";
		
		try {    con = ds.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setInt(1, rm_msgVO.getEmp_no());
					ps.setInt(2, rm_msgVO.getStore_no());
					ps.setInt(3, rm_msgVO.getRm_msg_status());
					ps.setDate(4, rm_msgVO.getRm_msg_donetime());
					ps.setInt(5, rm_msgVO.getRm_msg_no());
					
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
	public void delete(Integer rm_msg_no) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM rm_msg WHERE rm_msg_no = ?;";
		Rm_msgVO rm_msgVO = new Rm_msgVO();
		try {
				 con = ds.getConnection();
				 ps = con.prepareStatement(sql);
				
			ps.setInt(1, rm_msgVO.getRm_msg_no());
			
			
			
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
	public Rm_msgVO findByStoreNumber(Integer store_no) {
		Connection con  =null;
		PreparedStatement ps = null;
		String sql = "SELECT "
				+" rm_msg_no, "
				+ "emp_no,\r\n"
				+ "mem_no,\r\n"
				+ "store_no,\r\n"
				+ "rm_msg_reason,\r\n"
				+ "rm_msg_date,\r\n"
				+ "rm_msg_status,\r\n"
				+ "rm_msg_donetime \r\n"
				+ "FROM \r\n"
				+ "rm_msg \r\n"
				+ "WHERE \r\n"
				+ "store_no = ?;";
		Rm_msgVO rm = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
				ps.setInt(1, store_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				rm = new Rm_msgVO();
				rm.setRm_msg_no(rs.getInt("rm_msg_no"));
				rm.setEmp_no(rs.getInt("emp_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_msg_reason(rs.getString("rm_msg_reason"));
				rm.setRm_msg_date(rs.getDate("rm_msg_date"));
				rm.setRm_msg_status(rs.getInt("rm_msg_status"));
				rm.setRm_msg_donetime(rs.getDate("rm_msg_donetime"));
			}
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
		return rm;
	}
	
	
	public Rm_msgVO findByMemNumber(Integer mem_no) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM rm_msg WHERE mem_no = ?";
		Rm_msgVO rm = null;
		try { con = ds.getConnection();
			  ps = con.prepareStatement(sql);	
			  ps.setInt(1, mem_no);
			  rs = ps.executeQuery();
			  
			while(rs.next()) {
				rm = new Rm_msgVO();
				rm.setRm_msg_no(rs.getInt("rm_msg_no"));
				rm.setEmp_no(rs.getInt("emp_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_msg_reason(rs.getString("rm_msg_reason"));
				rm.setRm_msg_date(rs.getDate("rm_msg_date"));
				rm.setRm_msg_status(rs.getInt("rm_msg_status"));
				rm.setRm_msg_donetime(rs.getDate("rm_msg_donetime"));
				
				
			}  
			  
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
		
		
		
		return rm;
	}
	

	@Override
	public List<Rm_msgVO> getAll() {
		List<Rm_msgVO> list = new ArrayList<Rm_msgVO>();
		Rm_msgVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM rm_msg ORDER BY rm_msg_no;";
		try{	con = ds.getConnection();
				 ps = con.prepareStatement(sql);
				 rs = ps.executeQuery();
				 
			while(rs.next()) {
				rm = new Rm_msgVO();
				rm.setRm_msg_no(rs.getInt("rm_msg_no"));
				rm.setEmp_no(rs.getInt("emp_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_msg_reason(rs.getString("rm_msg_reason"));
				rm.setRm_msg_date(rs.getDate("rm_msg_date"));
				rm.setRm_msg_status(rs.getInt("rm_msg_status"));
				rm.setRm_msg_donetime(rs.getDate("rm_msg_donetime"));
				list.add(rm);
				
			}
			
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
		return list;
	}
	
	public  MemVO getOneMemByMemAcc(String mem_acc) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemVO memvo = null;
		String sql = "SELECT mem_no,mem_acc FROM mem WHERE mem_acc = ?";
		
		try {con = ds.getConnection();
				ps = con.prepareStatement(sql);
			ps.setString(1, mem_acc);	
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
			memvo = new MemVO();
			memvo.setMem_no(rs.getInt("mem_no"));
			memvo.setMem_acc(rs.getString("mem_acc"));
			
			
			}
			return memvo;
				
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
	
	public List<Rm_msgVO> getAllMsgByNumber(Integer store_no) {
		List<Rm_msgVO> list = new ArrayList<>();
		Connection con  =null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM rm_msg WHERE store_no = ? ORDER BY rm_msg_no;";
		Rm_msgVO rm = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, store_no);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rm = new Rm_msgVO();
				rm.setRm_msg_no(rs.getInt("rm_msg_no"));
				rm.setEmp_no(rs.getInt("emp_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_msg_reason(rs.getString("rm_msg_reason"));
				rm.setRm_msg_date(rs.getDate("rm_msg_date"));
				rm.setRm_msg_status(rs.getInt("rm_msg_status"));
				rm.setRm_msg_donetime(rs.getDate("rm_msg_donetime"));
				list.add(rm);
			}
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
		return list;
	}
	
	
	
}
