<%@page import="com.auth.model.AuthVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authSvc" class="com.auth.model.AuthService" />

<%
AuthVO authVO = (AuthVO) request.getAttribute("authVO");
%>

<!DOCTYPE html>
<html>

<head>
<title>新增權限 - Memory</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<%@ include file="/backend/commonCSS.file"%>
<!-- CSS -->

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

/*  css */
.coup-list-h1 {
	margin-right: 2rem;
}

.input-mr {
	margin-right: 1rem;
}

.input-mb {
	margin-bottom: 1rem;
}
</style>
</head>

<body>
	<%@ include file="/backend/loading.file"%>
	<!-- loading -->
	<%@ include file="/backend/header.file"%>
	<!-- Header -->
	<%@ include file="/backend/sidebar.file"%>
	<!-- sidebar -->

	<div class="main-content">
		<a class="btn btn-secondary light"
			href="<%=request.getContextPath()%>/backend/auth/listAllAuth.jsp">&lt;回權限列表</a>
		<div class="container-fluid">
			<div class="col-10 d-flex justify-content-between mb-5">
				<h1 class="coup-list-h1">新增權限</h1>
			</div>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<div class="col-11">
			<form action="<%=request.getContextPath()%>/auth/addAuth.do"
				method="post" class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">新增權限</h3>
				</div>
				<div class="modal-body">
					<div class="form-row input-mb d-flex">
						<div class="form-group col-md-5 input-mr">
							<label for="fun_no">權限名稱</label> 
							<%-- <input type="text" class="form-control" id="fun_no" name="fun_no"
								value="<%=(authVO == null) ? "" : authVO.getFun_no()%>"> --%>
								<select class="form-control" name="fun_no" >
									<jsp:useBean id="authFunSvc" scope="page"
										class="com.auth_fun.model.AuthFunService" />
									<c:forEach var="authFunVO" items="${authFunSvc.all}">
										<option value="${authFunVO.fun_no}"
											${(authVO.fun_no==authFunVO.fun_no)?'selected':''}>${authFunVO.fun_name}</option>
									</c:forEach>
								</select>
						</div>
						<div class="form-group col-md-5 input-mr">
						<label for="emp_no">員工姓名</label>
							<%-- <input type="text" class="form-control" id="emp_no" name="emp_no"
								value="<%=(authVO == null) ? "" : authVO.getEmp_no()%>"> --%>
								<!-- <div class="form-control"> -->
								<select class="form-control" name="emp_no" >
									<jsp:useBean id="empSvc" scope="page"
										class="com.emp.model.EmpService" />
									<c:forEach var="empVO" items="${empSvc.all}">
										<option value="${empVO.emp_no}"
											${(authVO.emp_no==empVO.emp_no)?'selected':''}>${empVO.emp_name}</option>
									</c:forEach>
								</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- hidden input -->
					<a
						href="<%=request.getContextPath()%>/backend/auth/listAllAuth.jsp" class="btn btn-secondary"
						data-dismiss="modal">取消</a>
						<input type="hidden" name="action" value="insert"> 
						<input type="hidden" name="fun_no" value="${authVO.fun_no}"> 
						<!-- <input type="hidden" name="emp_no" value="1"> -->
					<button type="submit" class="btn btn-primary">儲存</button>
				</div>
			</form>

		</div>
	</div>
	</div>



	<%@ include file="/backend/commonJS.file"%>
	<!-- JS -->
	<script>
		// 		header標題
		$("#pagename").text("MEMORY 後台管理");
	</script>
</body>
</html>