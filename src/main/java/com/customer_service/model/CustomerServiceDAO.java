package com.customer_service.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CustomerServiceDAO implements I_CustomerServiceDAO {
	private static final String INSERT = "INSERT INTO Customer_Service(STORE_NO,EMP_NO,MEM_NO,MSG_CONTENT,MSG_DIRECTION,MSG_TIME)VALUES(?,?,?,?,?,now())";
	private static final String FIND = "select * from Customer_Service "
			+ "where (msg_direction =? and (store_no=? or  mem_no=?))";

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
	public void insert(CustomerServiceVO customerServiceVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {con = ds.getConnection();
				ps = con.prepareStatement(INSERT);
				Integer storeNo = customerServiceVO.getStore_no();
				if (storeNo == null) {
					ps.setNull(1, Types.INTEGER);
				} else {
					ps.setInt(1, customerServiceVO.getStore_no());
				}
				
				ps.setInt(2, customerServiceVO.getEmp_no());
				
				Integer memNo = customerServiceVO.getMem_no();
				if (memNo == null) {
					ps.setNull(3, Types.INTEGER);
				} else {
					ps.setInt(3, customerServiceVO.getMem_no());
				}
				
				ps.setString(4, customerServiceVO.getMsg_content());
				ps.setInt(5, customerServiceVO.getMsg_direction());
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
	public List<CustomerServiceVO> findMsg(CustomerServiceVO customerServiceVO) {
		List<CustomerServiceVO> csMSG = new ArrayList<>();
		CustomerServiceVO cs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FIND);
			ps.setInt(1, customerServiceVO.getMsg_direction());
			Integer storeNo = customerServiceVO.getStore_no();
			if (storeNo == null) {
				ps.setNull(2, Types.INTEGER);
			} else {
				ps.setInt(2, customerServiceVO.getStore_no());
			}
			
			Integer memNo = customerServiceVO.getMem_no();
			if (memNo == null) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, customerServiceVO.getMem_no());
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				cs = new CustomerServiceVO();
				cs.setMsg_id(rs.getInt("msg_id"));
				cs.setStore_no(rs.getInt("Store_no"));
				cs.setEmp_no(rs.getInt("Emp_no"));
				cs.setMem_no(rs.getInt("Mem_no"));
				cs.setMsg_content(rs.getString("Msg_content"));
				cs.setMsg_direction(rs.getInt("Msg_direction"));
				cs.setMsg_time(rs.getDate("Msg_time"));
				csMSG.add(cs);
			}

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
		return csMSG;
	}
	
}
