<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunSvc" class="com.auth_fun.model.AuthFunService" />

<%
// 計算各狀態有幾筆資料
// pageContext.setAttribute("authFunSvc", authFunSvc);
/* AuthFunService authFunSvc = new AuthFunService(); */
List<AuthFunVO> list = authFunSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>

<head>
<title>所有權限列表 - Memory</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<%@ include file="/backend/commonCSS.file"%>
<!-- CSS -->
</head>

<body>
	<%@ include file="/backend/loading.file"%>
	<!-- loading -->
	<%@ include file="/backend/header.file"%>
	<!-- Header -->
	<%@ include file="/backend/sidebar.file"%>
	<!-- sidebar -->



	<!-- 	內容寫在main-content這個div內    -->
	<div class="main-content card card-body table-responsive">
		<div class="col-12 d-flex justify-content-between mb-5">
			<div class="d-flex">
				<a
					href="<%=request.getContextPath()%>/backend/authfun/addAuthFun.jsp"
					class="btn btn-primary add-btn">新增權限</a>
			</div>
		</div>
		<table id="example4" class="display" style="min-width: 845px">
			<thead class="thead-dark">
				<tr>
					<th>權限編號</th>
					<th>權限名稱</th>
					<th>名稱修改</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="authFunVO" items="${list}">
					<tr class="view">
						<td>${authFunVO.fun_no}</td>
						<td>${authFunVO.fun_name}</td>
						<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/authfun/authfun.do" style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-secondary btn-sm" value="修改權限名稱"> 
						<input type="hidden" name="fun_no" value="${authFunVO.fun_no}"> 
						<input type="hidden" name="action" value="getOneUpdate"></FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="/backend/commonJS.file"%>
	<!-- JS -->
	<script>
		// 		header標題
		$("#pagename").text("MEMORY 權限管理");
	</script>
</body>
</html>