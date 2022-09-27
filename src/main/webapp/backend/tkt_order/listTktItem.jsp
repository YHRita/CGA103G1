<%@page import="com.tkt.model.TktVO"%>
<%@page import="com.tkt.model.TktService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_img2.model.*"%>
<%@ page import="com.tkt_order2.model.*"%>
<%@ page import="com.tkt_item2.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="tktImg2Srv" scope="page" class="com.tkt_img2.model.Tktimg2Service" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券訂單管理列表 - Memory</title>

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

/* tkt css */
.list-h1 {
	margin-right: 2rem;
}

.add-btn {
	padding: 1rem 1rem 0.5rem 2rem;
}
.mr-10 {
	margin-right: 10px;
}
.tktImgWidth {
	width: 80px;
}


</style>

</head>
<body>
	<!-- loading -->
	<%@ include file="/backend/loading.file"%>
	<!-- Header -->
	<%@ include file="/backend/header.file"%>
	<!-- sidebar -->
	<%@ include file="/backend/sidebar.file"%>

	<!-- main -->

	<div class="content-body">
		<div class="container-fluid">
			<div class="col-12 d-flex justify-content-between mb-5">
				<div class="d-flex">
					<h1 class="coup-list-h1 list-h1">訂單明細</h1>
				</div>
				<div>
					<a href="<%=request.getContextPath()%>/backend/tkt_order/listAllTktOrder.jsp" class="btn btn-primary">返回訂單管理</a>
				</div>
			</div>

			<div class="col-12 table-responsive">
				<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col"></th>
							<th scope="col">票券名稱</th>
							<th scope="col">付款金額</th>
							<th scope="col">票券數量</th>
							
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="tktItem2VO" items="${tktItemList}">
							<tr>
								<td>
									<img src="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${tktItem2VO.tktVO.tkt_no}&action=showFirstImages" class="tktImgWidth">
								</td>
								<td>${tktItem2VO.tktVO.tkt_name}</td>
								<td>${tktItem2VO.tkt_ori_price}</td>
								<td>${tktItem2VO.amount}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
			

	<%@ include file="/backend/commonJS.file"%>
	

	
	
	

</body>
</html>