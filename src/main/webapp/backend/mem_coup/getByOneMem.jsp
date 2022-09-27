<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
CoupService coupSrv = new CoupService();
CoupVO coupVO = (CoupVO) request.getAttribute("coupVO");
List<CoupVO> list = coupSrv.getByEndDate(java.sql.Date.valueOf("2022-09-09")); // 這邊動態抓不到，施工中
pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html>
<head>
<title>優惠券截止日期列表 - Memory</title>

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
	padding: 1rem 2.5rem 0.5rem 2.5rem;
}

.go-listAll-btn {
	margin-right: 4rem;
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
					<h1 class="coup-lis list-h1">${mem_no } 號會員_優惠券</h1>
					<a href="<%=request.getContextPath()%>/backend/mem_coup/listSendMemCoup.jsp" class="btn btn-primary add-btn go-listAll-btn">返回優惠券發放列表</a>
			</div>

			<div class="col-12">
				<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">編號</th>
							<th scope="col">會員姓名</th>
							<th scope="col">優惠券名稱</th>
							<th scope="col">折抵金額</th>
							<th scope="col">到期日</th>
							<th scope="col">狀態</th>
						</tr>
					</thead>
					
					<tbody>
					
					<%@ include file="/backend/coup/pageIndex.file"%>
					
					<c:forEach var="memCoupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td># ${memCoupVO.mem_coup_no}</td>
								<td>${memCoupVO.memVO.mem_name}</td>
								<td>${memCoupVO.coupVO.coup_name}</td>
								<td>${memCoupVO.coupVO.discount}</td>
								<td>${memCoupVO.coupVO.enddate}</td>
								<td>
									<c:if test="${memCoupVO.coup_state == 0 }">
										<span class="badge badge-danger light">未使用</span>
									</c:if>
									<c:if test="${memCoupVO.coup_state == 1 }">
										<span class="badge badge-primary light">已使用</span>
									</c:if>
									<c:if test="${memCoupVO.coup_state == 2 }">
										<span class="badge badge-info light">已過期</span>
									</c:if>
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