<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_msg.model.*"%>
<%@ page import="java.time.LocalDate"%>


<%
// 取得自己的store所有資訊存放至pageContext
 Rm_msgService rmMsgSvc = new Rm_msgService();

List<Rm_msgVO> list = rmMsgSvc.getAll();

pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>

	<head>
	<!-- 基本CSS檔案 -->
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

div.order-data>div {
	font-size: 1.1rem;
	padding: 1%;
}

table tbody tr td:nth-child(1), table tbody tr td:nth-child(2) {
	padding-left: 45px;
}

table.table-striped {
	width: 90%;
	margin: auto;
	table-layout: fixed;
}

table.table-striped tbody tr:last-child {
	min-width: 400px;
}

h4 {
	color: #996A4D;
}

table.table-striped i {
	font-size: 12px;
}

table.table-striped th, table.table-striped td {
	padding: 0.6rem 0.3rem;
}

table.table-striped tfoot td {
	font-weight: 600;
	font-size: 1.1rem;
}

.search-area {
	max-width: 20rem;
	margin-left: auto;
	margin-right: 3%;
}

.custom-dropdown {
	margin: 0 1%;
}

i.bxs-chevron-down {
	font-size: 18px;
	padding-left: 7px;
}

table i {
	padding: 5px;
}

.btn-sm, .btn-group-sm>.btn {
	font-size: 1rem !important;
	padding: 0.625rem 1.6rem;
}

.dropdown-menu {
	z-index: 3;
	min-width: 9rem;
	padding: .5rem 0;
	text-align: center;
	border-radius: .5rem;
}

div.main-content {
	margin-top: 30px;
	margin-left: 30px;
	margin-bottom: 300px;
}

.mt-3, .my-3 {
	margin-top: 0rem !important;
}
</style>


<!-- CSS -->
	<%@ include file="/backend/commonCSS.file"%>
	
	</head>

<body>
	
		<%@ include file="/backend/loading.file" %> <!-- loading -->
		<%@ include file="/backend/header.file" %> <!-- Header -->
		<%@ include file="/backend/sidebar.file" %> <!-- sidebar -->

	<div class="main-content">
	
		<div class="d-flex mb-4 align-items-center flex-wrap">

			<div class="card-tabs mt-3 mt-sm-0">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/backend/store/storeListAll.jsp">所有廠商
							</a></li>
					<li class="nav-item">
						<!-- Search -->
						<form METHOD="post" action="<%=request.getContextPath()%>/RmMsgServlet">
							<div class="input-group search-area" style="margin-left:75%;">
								<input class="form-control" type="text" name="store_no"
									placeholder="請輸入廠商編號" />
								<input type="submit"
									class="btn btn-grad border-radius-left-0 mb-0" value="Search">
								<input type="hidden" name="action" value="getOneMsgByStore">
							</div>
						</form>
					</li>		
					
				</ul>

			</div>
		</div>

		<table class="table fold-table">
			<thead>
				<tr>
					<th>訊息編號</th>
					<th>員工編號</th>
					<th>會員編號</th>
					<th>廠商編號</th>
					<th>投訴日期</th>
					<th>處理完成日期</th>
					<th>處理狀況</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="Rm_msgVO" items="${list}">
					<tr class="view">
						<td>${Rm_msgVO.rm_msg_no}</td>
						<td>${Rm_msgVO.emp_no}</td>
						<td>${Rm_msgVO.mem_no}</td>
						<td>${Rm_msgVO.store_no}</td>
						<td>${Rm_msgVO.rm_msg_date}</td>
						<td>${Rm_msgVO.rm_msg_date}</td>
						<td><c:choose>
								<c:when test="${Rm_msgVO.rm_msg_status==0}">
									<i class='bx bxs-circle' style='color: red'></i>未處理</c:when>
								<c:when test="${Rm_msgVO.rm_msg_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>通過</c:when>
								<c:when test="${Rm_msgVO.rm_msg_status==2}">
									<i class='bx bxs-circle' style='color: #aaa'></i>不通過</c:when>
							</c:choose>
						</td>
						
					</tr>
					<tr class="fold">
						<td colspan="8">
							<div class="row d-flex justify-content-around my-2">
								<div class="col-4 order-data">	
									<div>投訴內容:${Rm_msgVO.rm_msg_reason}</div>
								</div>
								
							</div>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	
	
	<!-- 放置基本JS檔案 -->
	<%@ include file="/backend/commonJS.file"%>
	<script>
		$(document).ready(
				function() {
					$("#pagename").text("廠商管理");
					$(".fold-table tr.view").on(
							"click",
							function() {
								$(this).toggleClass("open").next(".fold")
										.toggleClass("open");
							});
					$("li.nav-item:eq(${ord_state+1})").children().addClass(
							"nav-link active");
				});
	</script>
</body>
</html>