<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>員工資料修改 - Memory</title>

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
			href="<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp">&lt;回員工列表</a>
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
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" name="update">
		<table id="example4" class="display" style="min-width: 845px">
	<tr>
		<td>員工編號:</td>
		<td><%=empVO.getEmp_no()%></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="emp_name" size="20" value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><%=empVO.getEmp_acc()%></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input type="TEXT" name="emp_pwd" size="20" value="<%=empVO.getEmp_pwd()%>" /></td>
	</tr>
	<tr>
		<td>員工郵件:</td>
		<td><input type="TEXT" name="emp_email" size="50" value="<%=empVO.getEmp_email()%>" /></td>
	</tr>

	<%-- <tr>
		<td>員工狀態:</td>
		<td><input type="radio" id="true" name="emp_state"  value="true" ${(empVO.emp_state== 1)?'checked':'' }>在職</td>
		<td><input type="radio" id="false" name="emp_state"  value="false" ${(empVO.emp_state== 0)?'checked':'' }>離職</td>
	</tr> --%>
	

	<%-- <jsp:useBean id="authFunSvc" scope="page" class="com.auth_fun.model.AuthFunService" />
	<tr>
		<td>擁有權限:</td>
		<td><select size="1" name="fun_no" >
			<c:forEach var="AuthFunVO" items="${authFunSvc.all}">
				<option value="${AuthFunVO.fun_no}" ${(empVO.fun_no==authFunVO.fun_no)?'selected':'' } >${authFunVO.fun_name}
			</c:forEach>
		</select></td>
	</tr> --%>

</table>
<br>

<div class="modal-footer">
			<a href="<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp" class="btn btn-secondary" data-dismiss="modal">取消</a> 				
			<input type="hidden" name="action" value="update"> 
			<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>"> 
			<input type="hidden" name="emp_acc" value="<%=empVO.getEmp_acc()%>"> 
			<input type="hidden" name="emp_name" value="<%=empVO.getEmp_name()%>"> 
			<input type="hidden" name="emp_state" value="<%=empVO.getEmp_state()%>"> 
			<button type="submit" class="btn btn-primary">儲存</button>
			</div>
			</FORM>
</div>
</div>
</div>
	<%@ include file="/backend/commonJS.file"%>
	<script>
				// 		header標題
				$("#pagename").text("MEMORY 修改員工資料");
			</script>
</body>
</html>