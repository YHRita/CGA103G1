<%@page import="com.auth_fun.model.AuthFunService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunSvc" class="com.auth_fun.model.AuthFunService" />

<%
// 計算各狀態有幾筆資料
AuthService authSvc = new AuthService();
AuthFunService authFunSve = new AuthFunService();
pageContext.setAttribute("authSvc", authSvc);
pageContext.setAttribute("authFunSvc", authFunSvc);

//第一次進來執行if裡面，list是getAll
// 不是第一次進來(點擊狀態分類從controller過來的)，table中就用forward過來的list
/* if (request.getAttribute("list") == null) {	 */
if (request.getAttribute("list") == null) {
	List<AuthVO> list = authSvc.getAllAuth();
	pageContext.setAttribute("list", list);
}
%>
<!DOCTYPE html>
<html>

<head>
<title>員工權限列表 - Memory</title>
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
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="col-12 d-flex justify-content-between mb-5">
			<div class="d-flex">
				<a href="<%=request.getContextPath()%>/backend/auth/addAuth.jsp"
					class="btn btn-primary add-btn">新增員工</a>
			</div>
		</div>

		<table id="example4" class="display" style="min-width: 845px">
		<thead>
			<tr>
				<th>權限編號</th>
				<th>員工編號</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="authVO" items="${list}">
				<tr>
					<td>${authVO.fun_no}-[${authVO.authFunVO.fun_name}]</td>
					<td>${authVO.emp_no}-[${authVO.empVO.emp_name}]</td>
					<td><a class="btn btn-secondary btn-sm"
						href="<%=request.getContextPath()%>/backend/auth/updateAuth.jsp?emp_no=${authVO.emp_no}&fun_no=${authVO.fun_no}">修改</a>
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
		$("#pagename").text("MEMORY 員工權限管理");
	</script>
</body>
</html>