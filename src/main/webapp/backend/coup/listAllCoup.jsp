<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
CoupService coupSvc = new CoupService();
List<CoupVO> list = coupSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>優惠券列表 - Memory</title>

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

.list-h1 {
	margin-right: 2rem;
}

.add-btn {
	padding: 1rem 1rem 0.5rem 2rem;
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
	<div class="content-body">
		<div class="container-fluid">
			<div class="col-12 d-flex justify-content-between mb-5">
				<div class="d-flex">
					<h1 class="coup-lis list-h1">優惠券列表</h1>
					<a href="<%=request.getContextPath()%>/backend/coup/addCoup.jsp" class="btn btn-rounded btn-primary add-btn">
						<span class="btn-icon-start text-primary">
							<i class="fa fa-plus color-primary"></i>
                        </span>
                        新增
                     </a>
				</div>
				<!-- search -->
				<form method="post" action="<%=request.getContextPath()%>/coup/getByEnddate.do" class="input-group search-area">
					<input type="text" class="form-control" placeholder="輸入截止日期" name="enddate" value="">
					<span class="input-group-text"> 
						<i class="flaticon-381-search-2"></i>
					</span>
				</form>
			</div>

			<div class="col-12 table-responsive">
				<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">編號</th>
							<th scope="col">優惠券名稱</th>
							<th scope="col">優惠券介紹</th>
							<th scope="col">優惠券金額</th>
							<th scope="col">起始日期</th>
							<th scope="col">結束日期</th>
							<th scope="col">狀態</th>
							<th scope="col"></th>
						</tr>
					</thead>
					
					<tbody>
					
					<%@ include file="/backend/coup/pageIndex.file"%>
					
					<c:forEach var="coupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td># ${coupVO.coup_no}</td>
								<td>${coupVO.coup_name}</td>
								<td>${coupVO.introduce}</td>
								<td>NT$ ${coupVO.discount}</td>
								<td>${coupVO.startdate}</td>
								<td>${coupVO.enddate}</td>
								<td>
									<c:if test="${coupVO.status == 0 }">
										<span class="badge badge-danger light">未上架</span>
									</c:if>
									<c:if test="${coupVO.status == 1 }">
										<span class="badge badge-primary light">已上架</span>
									</c:if>
								</td>
								<td>

									<form method="post" action="<%=request.getContextPath()%>/coup/updateCoup.do">
										<button type="submit" class="fa fa-pencil-alt btn" value="">
										</button>
										<input type="hidden" name="coup_no" value="${coupVO.coup_no}">
										<input type="hidden" name="action" value="getOneUpdate">
									</form>
									
								</td>


							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<%@ include file="/backend/coup/pagination.file"%>
				

			</div>
		</div>
	</div>
	
	

	<%@ include file="/backend/commonJS.file"%>
</body>
</html>