<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.rm_msg.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
Rm_msgVO rm_msgVO = (Rm_msgVO)request.getAttribute("rm_msgVO"); 

%>

<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/frontend/commonCSS.file"%>
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		 <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

	</head>

	<body>
		
	<%@ include file="/frontend/header.file"%>
	<!-- Header -->
	<%@ include file="/frontend/store/storeSidebar.file"%>
	<!-- sidebar -->
		
	

		

<!-- 	內容寫在main-content這個div內    -->
<div class=" col-lg-9 card card-body table-responsive" >
	<form method="post" name="form1" action="<%=request.getContextPath()%>/RmMsgServlet" >
		<table id="example4" class="display" style="min-width: 845px">
		
			 	 	<tr>
			 	 	<td>訊息編號:</td>
			 	 	<td>${rm_msgVO.rm_msg_no}</td>
			 	 	</tr>
					<tr>
					<td>處理人員:</td>
					<td><input type="text" name="emp_no" value="${rm_msgVO.emp_no}"></td>
					</tr>
					<tr>
					<td>會員編號:</td>
					<td><input type="hidden" name="mem_no" value="${rm_msgVO.mem_no}">${rm_msgVO.mem_no}</td>
					</tr>
					
					<tr>
					<td>廠商編號:</td>
					<td><input type="hidden" name="store_no" value="${rm_msgVO.store_no}">${rm_msgVO.store_no}</td>
					</tr>
					<tr>
					<td>投訴日期:</td>
					<td><input type="hidden" name="rm_msg_date" value="${rm_msgVO.rm_msg_date}">${rm_msgVO.rm_msg_date}</td>
					</tr>
					<tr>
					<td>處理日期:</td>
					<td><input type="date" name="rm_msg_donetime" value="${rm_msgVO.rm_msg_donetime}"></td>
					</tr>
					<tr>
					<td>處理狀態:</td>
					<td>
						<select  name="rm_msg_status" size="1">
    							<option value="0" ${rm_msgVO.rm_msg_status == 0?'selected':'' }>未處理</option>
							    <option value="1" ${rm_msgVO.rm_msg_status == 1?'selected':'' }>已通過</option>
							    <option value="2" ${rm_msgVO.rm_msg_status == 2?'selected':'' }>未通過</option>
  						 </select>
						
					</td>
					</tr>
					
					<tr>
					<td>修改資料</td>
				 	<td>
					 	<input type="hidden" name="action" value="rmMsgUpdated">
						<input type="hidden" name="rm_msg_no" value="${rm_msgVO.rm_msg_no}">
						<input type="submit" value="送出修改">	
					</td>
					</tr>		
				 
				 <tr>
				 <td>
				 	
				 </td>
				 
				 </tr>
				
			 </table>
			</form> 
		</div>

	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>
	<%@ include file="/backend/commonJS.file"%>
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>