<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="listStoreByCompositeQuery" scope="request" type="java.util.List<EmpVO>" />


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
						<form METHOD="post" action="<%=request.getContextPath()%>/store.do">
							<div class="input-group search-area" style="margin-left:75%;">
								<input class="form-control" type="text" name="store_no"
									placeholder="請輸入廠商編號" />
								<input type="submit"
									class="btn btn-grad border-radius-left-0 mb-0" value="Search">
								<input type="hidden" name="action" value="getOneStore">
							</div>
						</form>
					</li>
				</ul>

			</div>
		</div>

		<table class="table fold-table">
			<thead>
				<tr>
					<th>廠商會員編號</th>
					<th>帳號</th>
					<th>密碼</th>
					<th>帳號狀態</th>
					<th>店家名稱</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="storeVO" items="${listStoreByCompositeQuery}">
					<tr class="view">
						<td>${storeVO.store_no}</td>
						<td>${storeVO.store_acc}</td>
						<td>${storeVO.store_pwd}</td>
						<td><c:choose>
								<c:when test="${storeVO.acc_status==0}">
									<i class='bx bxs-circle' style='color: red'></i>帳號未啟用</c:when>
								<c:when test="${storeVO.acc_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>帳號已啟用</c:when>
								<c:when test="${storeVO.acc_status==2}">
									<i class='bx bxs-circle' style='color: #aaa'></i>帳號停權</c:when>
							</c:choose>
						</td>
						<td>${storeVO.store_name}</td>
					</tr>
					<tr class="fold">
						<td colspan="8">
							<div class="row d-flex justify-content-around my-2">
								<div class="col-4 order-data">
									<div>統編：${storeVO.store_gui}</div>
									<div>負責人：${storeVO.store_rep}</div>
									<div>電話:${storeVO.store_tel}</div>
									<div>傳真:${storeVO.store_fax}</div>
									<div>登記地址:${storeVO.store_add}</div>
									<div>聯絡人:${storeVO.store_poc}</div>
									<div>連絡電話:${storeVO.store_con_phone}</div>
									
									
								</div>
								<div class="col-4 order-data">
									<div>聯絡地址:${storeVO.store_con_add}</div>
									<div>信箱:${storeVO.store_email}</div>
									<div>加入時間:${storeVO.store_reg_date}</div>
									<div>轉帳帳號:${storeVO.bank_account}</div>
									<div>訂房總分數:${storeVO.store_rm_rating_score}</div>
									<div>訂房總評價數:${storeVO.store_rm_rating_count}</div>
									<div>被檢舉計點:${storeVO.store_report_count}</div>
								</div>
							</div>
							<table class="table table-striped">
								
								<tfoot>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td>
												<form method="post" action="<%=request.getContextPath()%>/store.do">
													<input type="submit" value="修改">
													<input type="hidden" name="store_no" value="${storeVO.store_no}">
													<input type="hidden" name="action" value="updateStore">
												</form>
										</td>
									</tr>
								</tfoot>
							</table>
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