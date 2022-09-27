package com.mem.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mem.model.*;
import com.mem.model.MemDAO;
import com.mem.model.MemVO;

import mail.MailService;


@MultipartConfig
@WebServlet("/mem.do")
public class MemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("mem_no","請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer mem_no = null;
				try {
					mem_no = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("mem_no","員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService MemSvc = new MemService();
				MemVO memVO = MemSvc.getOneMem(mem_no);
				if (memVO == null) {
					errorMsgs.put("mem_no","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
				
				 String  mem_acc = req.getParameter("mem_acc");           
				 String  mem_pwd  = req.getParameter("mem_pwd");         
				 Integer acc_status  =Integer.valueOf(req.getParameter("acc_status"));
				 String  mem_name    =req.getParameter("mem_name"); 
				 String  mem_gender  =req.getParameter("mem_gender"); 
				 String  mem_email   =req.getParameter("mem_email"); 
				 String  mem_mobile  =req.getParameter("mem_mobile");
				 String  mem_city    =req.getParameter("mem_city");
				 String  mem_dist=req.getParameter("mem_dist"); 
				 String mem_addr =req.getParameter("mem_addr");
				 java.sql.Date    mem_reg_date=null;
				 try {
					 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
					} catch (IllegalArgumentException e) {
						mem_reg_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
				 
				 //存入圖片
//				 Part part = req.getPart("mem_pic");
//			      InputStream in = part.getInputStream();
//			      ByteArrayOutputStream out = new ByteArrayOutputStream();
//			      byte[] buff = new byte[1024];
//			      int len;
//			    //若in.read(buff)能讀到資料，把資料長度設給len，讓out寫出資料(從0寫到len的長度)
//			      while((len = in.read(buff)) != -1) {
//			        out.write(buff, 0, len);
//			      }
//			      //out寫出完成後，把資料轉存為byte[]
//			      byte[] mem_pic = out.toByteArray();
		byte[] mem_pic =req.getPart("mem_pic").getInputStream().readAllBytes();
			     Integer mem_report_count = Integer.valueOf(req.getParameter("mem_report_count"));
			     String mem_card = req.getParameter("mem_card");
			      
			      
				  
				
				 
				 try {
					 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
					} catch (IllegalArgumentException e) {
						mem_reg_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

				  MemVO memVO = new MemVO();
				  	 memVO.setMem_no(mem_no);
					 memVO.setMem_acc(mem_acc);
					 memVO.setMem_pwd(mem_pwd);
					 memVO.setAcc_status(acc_status);
					 memVO.setMem_name(mem_name);
					 memVO.setMem_gender(mem_gender);
					 memVO.setMem_email(mem_email);
					 memVO.setMem_mobile(mem_mobile);
					 memVO.setMem_reg_date(mem_reg_date);
					 memVO.setMem_pic(mem_pic);
					 memVO.setMem_report_count(mem_report_count);
					 memVO.setMem_card(mem_card);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_acc, mem_pwd, acc_status, mem_name, mem_gender, mem_email, mem_mobile, mem_city, mem_dist, mem_addr, mem_reg_date, mem_pic, mem_report_count, mem_card,mem_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/frontend/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

		if ("updatemem".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
				 String  mem_pwd  = req.getParameter("mem_pwd");         
				 String  mem_name    =req.getParameter("mem_name"); 
				 String  mem_gender  =req.getParameter("mem_gender"); 
				 String  mem_email   =req.getParameter("mem_email"); 
				 String  mem_mobile  =req.getParameter("mem_mobile");
				 String  mem_city    =req.getParameter("mem_city");
				 //存入圖片
//				 Part part = req.getPart("mem_pic");
//			      InputStream in = part.getInputStream();
//			      ByteArrayOutputStream out = new ByteArrayOutputStream();
//			      byte[] buff = new byte[1024];
//			      int len;
//			    //若in.read(buff)能讀到資料，把資料長度設給len，讓out寫出資料(從0寫到len的長度)
//			      while((len = in.read(buff)) != -1) {
//			        out.write(buff, 0, len);
//			      }
//			      //out寫出完成後，把資料轉存為byte[]
//			      byte[] mem_pic = out.toByteArray();
				  MemVO memVO = new MemVO();
				  	 memVO.setMem_no(mem_no);	
					 memVO.setMem_pwd(mem_pwd);
					 memVO.setMem_name(mem_name);
					 memVO.setMem_gender(mem_gender);
					 memVO.setMem_email(mem_email);
					 memVO.setMem_mobile(mem_mobile);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.update( mem_pwd,  mem_name, mem_gender, mem_email, mem_mobile, mem_city,mem_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/frontend/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		 String  mem_acc = req.getParameter("mem_acc");           
		 String  mem_pwd  = req.getParameter("mem_pwd");         
		 String  mem_name    =req.getParameter("mem_name"); 
		 String  mem_gender  =req.getParameter("mem_gender"); 
		 String  mem_email   =req.getParameter("mem_email"); 
		 String  mem_mobile  =req.getParameter("mem_mobile");
		 String  mem_city    =req.getParameter("mem_city");
		 String  mem_dist=req.getParameter("mem_dist"); 
		 String mem_addr =req.getParameter("mem_addr");
//		 java.sql.Date    mem_reg_date=null;
//		 try {
//			 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
//			} catch (IllegalArgumentException e) {
//				mem_reg_date=new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
		 
		 //存入圖片
//		 Part part = req.getPart("mem_pic");
//	      InputStream in = part.getInputStream();
//	      ByteArrayOutputStream out = new ByteArrayOutputStream();
//	      byte[] buff = new byte[1024];
//	      int len;
//	    //若in.read(buff)能讀到資料，把資料長度設給len，讓out寫出資料(從0寫到len的長度)
//	      while((len = in.read(buff)) != -1) {
//	        out.write(buff, 0, len);
//	      }
//	      //out寫出完成後，把資料轉存為byte[]
//	      byte[] mem_pic = out.toByteArray();
byte[] mem_pic =req.getPart("mem_pic").getInputStream().readAllBytes();
//	     Integer mem_report_count = Integer.valueOf(req.getParameter("mem_report_count"));
	     String mem_card = req.getParameter("mem_card");
	      
	      
		  
		
		 
//		 try {
//			 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
//			} catch (IllegalArgumentException e) {
//				mem_reg_date=new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
		   
		  MemVO memVO = new MemVO();
		 memVO.setMem_acc(mem_acc);
		 memVO.setMem_pwd(mem_pwd);
		 
		 memVO.setMem_name(mem_name);
		 memVO.setMem_gender(mem_gender);
		 memVO.setMem_email(mem_email);
		 memVO.setMem_mobile(mem_mobile);
//		 memVO.setMem_reg_date(mem_reg_date);
		 memVO.setMem_pic(mem_pic);
//		 memVO.setMem_report_count(mem_report_count);
		 memVO.setMem_card(mem_card);
		 
			


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(mem_acc, mem_pwd,  mem_name, mem_gender, mem_email, mem_mobile, mem_city, mem_dist, mem_addr,  mem_pic,  mem_card);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				HttpSession session  = req.getSession();
				
				Integer checkMem = memSvc.getOneBymail(mem_email).getMem_no();
				MailService mail = new MailService();
				String authCode = mail.getRandom();
				String subject = "會員驗證碼";
				String message = "感謝您註冊本網站會員，請輸入以下驗證碼完成註冊:" + authCode;
				
				
				try {
					mail.sendMail(mem_email, subject, message);
					session.setAttribute("checkMem", checkMem);
		            session.setAttribute("authCode", authCode);
		            res.sendRedirect(req.getContextPath() + "/frontend/signin/checkMember.jsp");
				}catch(Exception e) {
					e.printStackTrace();
				}	

		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no"));
				
				/***************************2.開始刪除資料***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontend/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		  if("log_out".equals(action)) {
	        	HttpSession session = req.getSession();
	        	session.invalidate();
	        	//導回登入頁面-------------
	        	String url = "/frontend/homePage.jsp";
	        	RequestDispatcher successView = req.getRequestDispatcher(url);
	        	successView.forward(req, res);
	        }
		  if("forgetPassword".equals(action)) {
	        	
	        	List<String> errorMsgs = new LinkedList<String>();
	        	req.setAttribute("errorMsgs", errorMsgs);
	        	try {
	        		//請求
	        		String mem_email = req.getParameter("mem_email");
					MemService memSvc = new MemService();
					
					List<MemVO> listall = memSvc.getall();
					for (MemVO memVOList : listall) {
						if (memVOList.getMem_email() != mem_email) {
							errorMsgs.add("信箱無註冊資料，請重新輸入");
						}break;
					}
					MemVO memVO = memSvc.getOneBymail(mem_email);
					MailService mail = new MailService();
					String authCode = mail.getRandom();
					
					memSvc.updatePassword(authCode, memVO.getMem_no());
					
					String subject = "臨時密碼";
					String message = "臨時密碼:" + authCode + "請登入後修改密碼";
				

					try {
						mail.sendMail(mem_email, subject, message);
			            res.sendRedirect(req.getContextPath() + "/frontend/signin/login.jsp");
					}catch(Exception e) {
						e.printStackTrace();
					}
	        	}catch(Exception e) {
	        		errorMsgs.add(e.getMessage());
	        		RequestDispatcher failureView = req.getRequestDispatcher("/frontend/signin/forgetPassword.jsp");
	        		failureView.forward(req, res);
	        	}
	        	
	        	
	        }
		  
		  if("updateStatus".equals(action)) {
	        	List<String> memlist = new LinkedList<String>();
	        	req.setAttribute("memlist", memlist);
	        	try {
	        		Integer mem_no = new Integer(req.getParameter("mem_no"));
	        	
	        		
	        		MemVO memVO = new MemVO();
	        		MemService memSvc = new MemService();
	        		
	        		memVO = memSvc.updateStatus(1, mem_no);
	        		
	        		req.setAttribute("memVO", memVO);
	        		String url = "/backend/mem/listMemPage.jsp";
	        		RequestDispatcher success = req.getRequestDispatcher(url);
	        		success.forward(req, res);
	        	}catch(Exception e) {
	        		
	        	}
	        }
	        	if("unupdateStatus".equals(action)) {
	        		List<String> memlist = new LinkedList<String>();
	        		req.setAttribute("memlist", memlist);
	        		try {
	        			Integer mem_no = new Integer(req.getParameter("mem_no"));
	        			
	        			
	        			MemVO memVO = new MemVO();
	        			MemService memSvc = new MemService();
	        			
	        			memVO = memSvc.updateStatus(2, mem_no);
	        			
	        			req.setAttribute("memVO", memVO);
	        			String url = "/backend/mem/listMemPage.jsp";
	        			RequestDispatcher success = req.getRequestDispatcher(url);
	        			success.forward(req, res);
	        		}catch(Exception e) {
	        			
	        		}
	        	}
	        	if("getOneMem".equals(action)) {

	    			List<String> mem = new LinkedList<String>();
	    			req.setAttribute("mem", mem);

	    				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	    				String str = req.getParameter("mem_no");
	    				if (str == null || (str.trim()).length() == 0) {
	    					mem.add("請輸入會員編號");
	    				}
	    				// Send the use back to the form, if there were errors
	    				if (!mem.isEmpty()) {
	    					RequestDispatcher failureView = req
	    							.getRequestDispatcher("/backend/mem/listMemPage.jsp");
	    					failureView.forward(req, res);
	    					return;//程式中斷
	    				}
	    				
	    				Integer mem_no = null;
	    				try {
	    					mem_no = Integer.valueOf(str);
	    				} catch (Exception e) {
	    					mem.add("會員編號格式不正確");
	    				}
	    				// Send the use back to the form, if there were errors
	    				if (!mem.isEmpty()) {
	    					RequestDispatcher failureView = req
	    							.getRequestDispatcher("/backend/mem/listMemPage.jsp");
	    					failureView.forward(req, res);
	    					return;//程式中斷
	    				}
	    				
	    				/***************************2.開始查詢資料*****************************************/
	    				MemService memSvc = new MemService();
	    				MemVO memList = memSvc.getOneMem(mem_no);
	    				if (memList == null) {
	    					mem.add("查無資料");
	    				}
	    				// Send the use back to the form, if there were errors
	    				if (!mem.isEmpty()) {
	    					RequestDispatcher failureView = req.getRequestDispatcher("/backend/mem/listMemPage.jsp");
	    					failureView.forward(req, res);
	    					return;//程式中斷
	    				}
	    				
	    				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
	    				req.setAttribute("memList", memList); // 資料庫取出的memList物件,存入req
	    				String url = "/backend/mem/listMemPage.jsp";
	    				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listMemPage.jsp
	    				successView.forward(req, res);
	    		}
	        	if ("getOnePic".equals(action)) {
	        		
	        			/*************************** 1.接收請求參數 ****************************************/
	        			Integer mem_no = new Integer(req.getParameter("mem_no"));

	        			/*************************** 2.開始查詢資料 ****************************************/
	        			MemService memSvc = new MemService();
	        			MemVO memVO = memSvc.getOneMem(mem_no);

	        			/*************************** 3.輸出圖片 ************/
	        			byte[] content = memVO.getMem_pic();
	        			ServletOutputStream out = res.getOutputStream();
	        			out.write(content);
	        			out.close();
	        			return;
	        		}
	        	if ("updateMemB".equals(action)) {
	    			
	    			List<String> errorMsgs = new LinkedList<String>();
	    			// Store this set in the request scope, in case we need to
	    			// send the ErrorPage view.
	    			req.setAttribute("errorMsgs", errorMsgs);
	    		
	    				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	    				Integer mem_no = Integer.valueOf(req.getParameter("memNo").trim());
	    				 String mem_name = req.getParameter("memName");
	    				 String mem_gender = req.getParameter("memGender"); 
	    				 String mem_email = req.getParameter("memEmail"); 
	    				 String mem_mobile = req.getParameter("memMobile");
	    				 String mem_city = req.getParameter("memCity");
	    				 String mem_dist = req.getParameter("memDist");
	    				 String mem_addr = req.getParameter("memAddr");
	    				
	    				  MemVO memVO = new MemVO();
	    				  	 memVO.setMem_no(mem_no);
	    					 memVO.setMem_name(mem_name);
	    					 memVO.setMem_gender(mem_gender);
	    					 memVO.setMem_email(mem_email);
	    					 memVO.setMem_mobile(mem_mobile);
	    					 memVO.setMem_city(mem_city);
	    					 memVO.setMem_dist(mem_dist);
	    					 memVO.setMem_addr(mem_addr);
	    				// Send the use back to the form, if there were errors
	    				if (!errorMsgs.isEmpty()) {
	    					req.setAttribute("memVO", memVO);
	    					RequestDispatcher failureView = req
	    							.getRequestDispatcher("/backend/mem/listMemPage.jsp");
	    					failureView.forward(req, res);
	    					return; //程式中斷
	    				}
	    				
	    				/***************************2.開始修改資料*****************************************/
	    				MemService memSvc = new MemService();
	    				memVO = memSvc.updateMemB(mem_name, mem_gender, mem_email, mem_mobile, mem_city,mem_dist,mem_addr,mem_no);
	    				
	    				/***************************3.修改完成,準備轉交(Send the Success view)*************/
	    				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
	    				String url = "/backend/mem/listMemPage.jsp";
	    				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMemPage.jsp
	    				successView.forward(req, res);
	    		}
		
	}
	
}