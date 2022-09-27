<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="empSvc" class="com.emp.model.EmpService" />

<%
// 計算各狀態有幾筆資料
// pageContext.setAttribute("rmOderSvc", rmOderSvc);
List<EmpVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>

	<head>
	<title>所有員工列表 - Memory</title>
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->
	</head>

	<body>
		<%@ include file="/backend/loading.file" %> <!-- loading -->
		<%@ include file="/backend/header.file" %> <!-- Header -->
		<%@ include file="/backend/sidebar.file" %> <!-- sidebar -->

		

<!-- 	內容寫在main-content這個div內    -->
<div class="main-content card card-body table-responsive">
			<div class="col-12 d-flex justify-content-between mb-5">
				<div class="d-flex">
					<a href="<%=request.getContextPath()%>/backend/emp/addEmp.jsp"
						class="btn btn-primary add-btn">新增員工</a></div>
		</div>
			<table id="example4" class="display" style="min-width: 845px">
				<thead>
				<tr>
					<th>員工編號</th>
					<th>員工帳號</th>
					<th>員工密碼</th>
					<th>員工姓名</th>
					<th>員工Email</th>
					<th>是否在職</th>
					<th>員工狀態更新</th>
					<th>員工資料修改</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="empVO" items="${list}">
					<tr class="view">
						<td>${empVO.emp_no}</td>
						<td>${empVO.emp_acc}</td>
						<td>${empVO.emp_pwd}</td>
						<td>${empVO.emp_name}</td>
						<td>${empVO.emp_email}</td>
						<td><span class="badge light badge-success">${empVO.emp_state == 0 ? '離職' : '在職'}</span></td>
						<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-outline-secondary" value="${empVO.emp_state == 0 ? '復職' : '離職'}">
						<input type="hidden" name="emp_no" value="${empVO.emp_no}"> 
						<input type="hidden" name="action" value="${empVO.emp_state == 0 ? 'onbo' : 'fire'}"></FORM>
						</td>
						<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-outline-secondary" value="修改"> 
						<input type="hidden" name="emp_no" value="${empVO.emp_no}"> 
						<input type="hidden" name="action" value="updateOne"></FORM>
						</td>
						</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 員工管理");
		</script>
	</body>
</html>
Footer
© 2022 GitHub, Inc.
Footer navigation
Terms
Privacy
Security
Status
Docs
