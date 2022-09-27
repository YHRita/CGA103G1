<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.emp.model.*"%>

<%@ page import="com.rm_msg.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="empSvc" class="com.emp.model.EmpService" />

<!DOCTYPE html>
<html>


<!--  

-->
<% 


 String store_acc =(String) session.getAttribute("store_acc");

 StoreService storeSvc = new StoreService();
 
 Rm_msgService rmMsgSvc = new Rm_msgService();

 StoreVO storeVO = storeSvc.getOneStoreByAcc(store_acc);
 
 Integer store_no = storeVO.getStore_no();
 
 List<Rm_msgVO> list = rmMsgSvc.getAllMsgByStoreNumber(store_no);
 
 pageContext.setAttribute("list", list);
 
 Rm_msgVO rmmsgVO =(Rm_msgVO)request.getAttribute("rmmsgVO");
 
 
  
 
	
%>
	<head>



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
    <%@ include file="/frontend/commonCSS.file" %>
	<%@ include file="/backend/commonCSS.file"%>
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
	
	</head>

<body>

	
	<%@ include file="/frontend/header.file"%>
	<!-- Header -->
	<%@ include file="/frontend/store/storeSidebar.file"%>
	<!-- sidebar -->

	<div class=" col-lg-9 card card-body table-responsive">
	
	
		<table class="table fold-table">
			<thead>
				<tr>
					<th>訊息編號</th>
					<th>處理人員</th>
					<th>會員編號</th>
					<th>廠商編號</th>
					<th>投訴日期</th>
					<th>處理狀態</th>
					
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="rm_msgVO" items="${list}">
					<tr class="view">
						<td>${rm_msgVO.rm_msg_no}</td>
						<td>${empSvc.getOneEmp(rm_msgVO.emp_no).emp_name}</td>
						<td>${rm_msgVO.mem_no}</td>
						<td>${rm_msgVO.store_no}</td>
						<td>${rm_msgVO.rm_msg_date}</td>
						<td>
							<c:choose>
								<c:when test="${rm_msgVO.rm_msg_status == 0}">
								<i>未處理</i>
								</c:when>
								<c:when test="${rm_msgVO.rm_msg_status == 1}">
								<i>已通過</i>
								</c:when>
								<c:when test="${rm_msgVO.rm_msg_status == 2}">
								<i>未通過</i>
								</c:when>
								
							</c:choose>
						</td>

					</tr>
					<tr class="fold">
						<td colspan="8">
							<div class="row d-flex justify-content-around my-2">
								<div class="col-4 order-data">
									<div>訊息內容：${rm_msgVO.rm_msg_reason}</div>
								</div>
							</div>
							<div>
								<div>
									<form method="post" action="<%=request.getContextPath()%>/RmMsgServlet">
										<input type="submit" value="審核">
										<input type="hidden" name="storeNO" value="${rm_msgVO.store_no}">
										<input type="hidden" name="action" value="rmMsgUpdate">
									</form>	
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>

	
	
	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>
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