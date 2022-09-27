<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunService"
	class="com.auth_fun.model.AuthFunService" />


<!DOCTYPE html>
<html>
<head>
<title>新增權限 - Memory</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />

<!-- jquery-ui -->
<!-- <link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css" /> -->


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
.auth-list-h1 {
	margin-right: 2rem;
}

.input-mr {
	margin-right: 1rem;
}

.input-mb {
	margin-bottom: 1rem;
}

.error-list-mb {
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
				<h1 class="auth-list-h1">新增權限</h1>
			</div>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red" class="error-list-mb">${message}</li>
					</c:forEach>
				</ul>
			</c:if>


			<div class="col-12">
				<form action="<%=request.getContextPath()%>/authfun/addAuthFun.do"
					method="post" class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">新增權限</h3>
					</div>
					<div class="modal-body">
						<div class="form-row input-mb d-flex">
							<div class="form-group col-md-6 input-mr">
								<label for="coupName">權限名稱</label> <input type="text"
									class="form-control" id="authFunName" name="fun_name"
									value="${authFunVO.fun_name}">
							</div>
						</div>
					</div>
							<div class="modal-footer">
								 <a href="<%=request.getContextPath()%>backend/authfun/listAllAuthFun.jsp"
									class="btn btn-secondary" data-dismiss="modal">取消</a> 
									<input type="hidden" name="action" value="${authFunVO.fun_name}">
									<input type="submit" class="btn btn-primary" value="儲存">
							</div>
				</form>

			</div>
		</div>
	</div>

	<%@ include file="/backend/commonJS.file"%>

	<!-- jquery-ui -->
	<!-- <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script>
		$( function() {
	    	$( "#datepicker" ).datepicker();
	 	} );
	</script> -->

	<!-- bootstrap cdn 用了會跑版先註解-->
	<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script> -->
</body>
</html>