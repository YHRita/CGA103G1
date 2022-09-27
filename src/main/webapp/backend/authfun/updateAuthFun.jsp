<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunService"
	class="com.auth_fun.model.AuthFunService" />

<%
AuthFunVO authFunVO = (AuthFunVO) request.getAttribute("authFunVO");

%>

<!DOCTYPE html>
<html>
<head>
<title>修改權限 - Memory</title>

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
	<!-- Header -->
	<%@ include file="/backend/header.file"%>
	<!-- sidebar -->
	<%@ include file="/backend/sidebar.file"%>

	<!-- main -->
	<div class="main-content">
		<a class="btn btn-secondary light"
			href="<%=request.getContextPath()%>/backend/authfun/listAllAuthFun.jsp">&lt;回權限列表</a>
		<div class="container-fluid">
			<div class="col-10 d-flex justify-content-between mb-5">
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

			<div class="col-11">
				<form action="<%=request.getContextPath()%>/authfun/authfun.do" name="update" method="post" class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" >修改權限名稱</h3>
					</div>
					<div class="modal-body">
						<div class="form-row input-mb d-flex">
							<div class="form-group col-md-5 input-mr">
							<label for="fun_no">權限編號</label>
									<div class="form-control"><%=request.getParameter("fun_no")%></div>
							</div>
							<div class="form-group col-md-5 input-mr">
								<label for="fun_name">權限名稱</label> 
								<input type="text" class="form-control" name="fun_name" value="<%=authFunVO.getFun_name()%>">
							</div>
					</div>		
					</div>
					
					<div class="modal-footer">
					
					<!-- hidden input -->
						<a href="<%=request.getContextPath()%>/backend/authfun/listAllAuthFun.jsp" class="btn btn-secondary" data-dismiss="modal">取消</a>
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="fun_no" value="<%=authFunVO.getFun_no()%>">
						<input type="submit" class="btn btn-primary" id="update" value="儲存">
					</div>
				</form>

			</div>
		</div>
	</div>

	<%@ include file="/backend/commonJS.file"%>
	<script>
				// 		header標題
				$("#pagename").text("MEMORY 權限管理");
			</script>
	
</body>
</html>