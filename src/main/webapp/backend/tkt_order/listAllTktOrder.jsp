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
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="tktImg2Srv" scope="page" class="com.tkt_img2.model.Tktimg2Service" />

<%
TktOrder2Service tktOrd2Srv = new TktOrder2Service();
List<TktOrder2VO> list = tktOrd2Srv.getAll();
pageContext.setAttribute("list", list);
%>

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
	width: 100px;
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
					<h1 class="coup-list-h1 list-h1">票券訂單列表</h1>
				</div>
				<!-- <div class="input-group search-area">
					<input type="text" class="form-control" placeholder="Search here">
					<span class="input-group-text"> <i
						class="flaticon-381-search-2"></i>

					</span>
				</div> -->
			</div>

			<div class="col-12 table-responsive">
				<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">訂單編號</th>
							<th scope="col">會員帳號</th>
							<th scope="col">原始金額</th>
							<th scope="col">折扣金額</th>
							<th scope="col">付款總金額</th>
							<th scope="col">訂單成立日期</th>
							<th scope="col">查看訂單明細</th>
							
						</tr>
					</thead>
					<tbody>
					<%@ include file="/backend/tkt_order/pageIndex.file"%>
						<c:forEach var="tktOrder2VO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						
							<tr>
								<td># ${tktOrder2VO.tkt_order_no}</td>
								<td>${tktOrder2VO.memVO.mem_name}</td>
								<td>NT$ ${tktOrder2VO.original_price}</td>
								<td>- ${tktOrder2VO.memCoupVO.coupVO.discount}, ${ tktOrder2VO.memCoupVO.coupVO.coup_name}</td>
								<td>NT$ ${tktOrder2VO.ttl_price}</td>
								<td>${tktOrder2VO.orderdate}</td>
								<td>
									<a href="/CGA103G1/tkt_order/tktItemBack.do?tkt_order_no=${tktOrder2VO.tkt_order_no}" class="btn btn-warning">明細</a>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<%@ include file="/backend/tkt_order/pagination.file"%>
			



	<%@ include file="/backend/commonJS.file"%>
	

	
	
	

</body>
</html>