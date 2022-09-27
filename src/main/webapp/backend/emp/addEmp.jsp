<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="empSvc" class="com.emp.model.EmpService" />

 <%
 EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%> 


<!DOCTYPE html>
<html>
<head>
<title>新增員工資料 - Memory</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />


<%@ include file="/backend/commonCSS.file"%>


<style>
table.fold-table tbody tr.view {
	cursor: pointer;
}

table.fold-table tbody tr.view:hover {
	box-shadow: 0 0.125rem 1rem rgb(0 0 0/ 19%);
}

table.fold-table tbody tr.view.open {
	background: #8FC2C2;
}

table.fold-table tbody tr.view.open td {
	color: white;
}

table.fold-table tbody tr.fold {
	display: none;
}

table.fold-table tbody tr.fold.open {
	display: table-row;
}

table {
	width: 90%;
}

table.fold-table>thead>tr>th {
	align: center;
	font-size: 1.125rem;
	text-transform: capitalize;
	font-weight: 600;
	padding: 1.25rem 0.9375rem;
}

thead {
	background: #F7F6F2;
}

td, div {
	font-size: 1rem;
	letter-spacing: 0.5px;
}

/* coup css */
.coup-list-h1 {
	margin-right: 2rem;
}

.input-mr {
	margin-right: 1rem;
}

.input-mb {
	margin-bottom: 1rem;
}

.error-list-mb{
	margin-bottom: 0.6rem;
}
 
</style>

</head>
<body>

	<%@ include file="/backend/loading.file"%>
	<!-- Header -->
	<%@ include file="/backend/header.file"%>
	<!-- sidebar -->
	<%@ include file="/backend/sidebar.file"%>
	
	
	

	<!-- main -->
	<div class="content-body">
		<div class="container-fluid">
			<div class="col-12 d-flex justify-content-between mb-5">
				<h1 class="coup-list-h1">新增員工資料</h1>
			</div>
			
			<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red" class="error-list-mb">${message}</li>
					</c:forEach>
				</ul>
		</c:if>


			<div class="col-12">
				<form action="<%=request.getContextPath()%>/emp/emp.do" method="post" class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">新增員工</h3>
					</div>
					
					
					<div class="modal-body">
						<div class="form-row input-mb d-flex">
							<div class="form-group col-md-5 input-mr">
								<label for="emp_name">姓名</label> 
								<input type="text" class="form-control" id="emp_name" name="emp_name" value="${empVO.emp_name}">
							</div>
							<div class="form-group col-md-3 input-mr">
								<label for="emp_acc">帳號</label> 
								<input type="text" class="form-control" id="emp_acc" name="emp_acc" value="${empVO.emp_acc}">
							</div>
							<div class="form-group col-md-3">
								<label for="emp_pwd">密碼</label> 
								<input type="text" class="form-control" id="emp_pwd" name="emp_pwd" value="${empVO.emp_pwd}">
							</div>
							</div>
							
							<div class="form-row d-flex input-mb">
							<div class="form-group col-md-5 input-mr">
								<label for="emp_email">Email</label> 
								<input type="text" class="form-control" id="emp_email" name="emp_email" value="${empVO.emp_email}">
							</div>
							</div>
					</div>
					<div class="modal-footer">
						<!-- hidden input -->
						<a href="<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp" class="btn btn-secondary" data-dismiss="modal">取消</a>
						<input type="hidden" name="action" value="insert">
						<input type="hidden" name="emp_no" value="${empVO.emp_no}">
						<input type="hidden" name="emp_state" value="1">
						<input type="submit" class="btn btn-primary" value="儲存">
					</div>
				</form>

			</div>
		</div>
	</div>

	<%@ include file="/backend/commonJS.file"%>
	
</body>
</html>