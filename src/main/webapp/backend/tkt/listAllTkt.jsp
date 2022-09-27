<%@page import="com.tkt.model.TktVO"%>
<%@page import="com.tkt.model.TktService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_img2.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="tktImg2Srv" scope="page" class="com.tkt_img2.model.Tktimg2Service" />

<%
TktService tktSvc = new TktService();
List<TktVO> list = tktSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券列表 - Memory</title>

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
.mt-20 {
	margin-top: 20px;
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
					<h1 class="coup-list-h1 list-h1">票券列表</h1>
					
					<a href="<%=request.getContextPath()%>/backend/tkt/addTkt.jsp" class="btn btn-rounded btn-primary add-btn">
						<span class="btn-icon-start text-primary">
							 <i class="fa fa-plus color-primary"></i>
                        </span>
                        新增
                     </a>
				</div>
				<div class="input-group search-area">
					<input type="text" class="form-control" placeholder="Search here">
					<span class="input-group-text"> <i
						class="flaticon-381-search-2"></i>

					</span>
				</div>
			</div>

			<div class="col-12 table-responsive">
				<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">編號</th>
							<th scope="col"></th>
							<th scope="col">票券名稱</th>
							<th scope="col">票券價格</th>
							<th scope="col">地點</th>
							<th scope="col">售出量</th>
							<th scope="col">剩餘數量</th>
							<th scope="col">票券種類</th>
							<th scope="col">票券狀態</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					<%@ include file="/backend/tkt/pageIndex.file"%>
						<c:forEach var="tktVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						
							<tr>
								<td># ${tktVO.tkt_no}</td>
								<td>
									<c:choose>
									<c:when test="${tktImg2Srv.getAllByTktNo(tktVO.tkt_no).size() > 0}">
										<img src="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${tktVO.tkt_no}&action=showFirstImages" class="tktImgWidth">
									</c:when>
									<c:otherwise>
										<img src="<%=request.getContextPath()%>/frontend/assets/images/hotels/noimages.png" class="no-img tktImgWidth">
									</c:otherwise>
								</c:choose>
								</td>
								<td>${tktVO.tkt_name}</td>
								<td>NT$ ${tktVO.price}</td>
								<td>${tktVO.locate}</td>
								<td>${tktVO.sold_amount}</td>
								<td>${tktVO.original_amount - tktVO.sold_amount}</td>
								
								
								<td><c:if test="${tktVO.kind == 0 }" var="true">
										<span class="badge badge-secondary">景點門票</span>
									</c:if> <c:if test="${tktVO.kind == 1 }" var="true">
										<span class="badge dark badge-warning">主題樂園</span>
									</c:if> <c:if test="${tktVO.kind == 2 }" var="true">
										<span class="badge badge-info">博物館展覽</span>
									</c:if>
								</td>

								<td><c:if test="${tktVO.tkt_status == 0 }" var="true">
										<span class="badge badge-danger light">未上架</span>
									</c:if> <c:if test="${tktVO.tkt_status == 1 }" var="true">
										<span class="badge badge-primary light">已上架</span>
									</c:if>
								</td>

								<td class="d-flex">
								

									<!-- 修改 -->
									<form method="post" action="<%=request.getContextPath()%>/tkt/updateTkt.do" class="mr-10 mt-20">
										<button type="submit" class="fa fa-pencil-alt btn" value=""></button>
										<input type="hidden" name="tkt_no" value="${tktVO.tkt_no}">
										<input type="hidden" name="action" value="getOneUpdate">
									</form>

									
									

									<a href="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${tktVO.tkt_no}&action=getOneToUpload" class="btn goToAddImg mt-20">
										<i class="fa fa-image"></i>
									</a>

								</td>


							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<%@ include file="/backend/tkt/pagination.file"%>
			

	

	<%@ include file="/backend/commonJS.file"%>
	


	
	

</body>
</html>