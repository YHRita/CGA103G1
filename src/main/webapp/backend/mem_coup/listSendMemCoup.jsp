<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem_coup.model.*"%>
<%@ page import="java.time.LocalDate"%>
<%-- <jsp:useBean id="coupSvc" class="com.coup.model.CoupService" /> --%>

<%
MemCoupService memCoupSrv = new MemCoupService();
List<MemCoupVO> list = memCoupSrv.getAll();
pageContext.setAttribute("list", list);
/* CoupVO coupVO = new CoupVO();
request.setAttribute("coupVO", coupVO); */

/* System.out.println("listAll -s"); // 印出來看
System.out.println(coupVO); // 印出來看
System.out.println("listAll -e"); // 印出來看 */
%>

<!DOCTYPE html>
<html>
<head>
<title>優惠券發放列表 - Memory</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
	
<!-- bootstrap cdn 用了會跑版先註解 -->	
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
    integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> -->

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
					<h1 class="coup-lis list-h1">優惠券發放列表</h1>
					<a href="<%=request.getContextPath()%>/backend/mem_coup/sendMemCoup.jsp" class="btn btn-rounded btn-primary add-btn">
						<span class="btn-icon-start text-primary">
							<i class="fa fa-plus color-primary"></i>
                        </span>
                        發放優惠券
                     </a>
					
				</div>
				
			</div>

			<div class="col-12 table-responsive">
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