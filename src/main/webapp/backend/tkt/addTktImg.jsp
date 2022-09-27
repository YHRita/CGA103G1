<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="coupSvc" class="com.coup.model.CoupService" />

<%
Integer tkt_no = (Integer)request.getAttribute("tkt_no");
%>

<!DOCTYPE html>
<html>
<head>
<title>上傳票券圖片 - Memory</title>

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
.coup-list-h1 {
	margin-right: 2rem;
}

.input-mr {
	margin-right: 1rem;
}

.input-mb {
	margin-bottom: 1rem;
}

.error-list-mb {
	margin-bottom: 0.6rem;
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
				<h1 class="coup-list-h1">${tkt_no} 上傳票券圖片</h1>
			</div>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">

				<div class="alert alert-danger alert-dismissible fade show">
					<svg viewBox="0 0 24 24" width="24" height="24"
						stroke="currentColor" stroke-width="2" fill="none"
						stroke-linecap="round" stroke-linejoin="round" class="me-2">
						<polygon
							points="7.86 2 16.14 2 22 7.86 22 16.14 16.14 22 7.86 22 2 16.14 2 7.86 7.86 2"></polygon>
						<line x1="15" y1="9" x2="9" y2="15"></line>
						<line x1="9" y1="9" x2="15" y2="15"></line></svg>
					<strong>Error!</strong> 請修正以下錯誤: <br>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red" class="error-list-mb">${message}</li>
						</c:forEach>
					</ul>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="btn-close"></button>
				</div>
			</c:if>


			<div class="col-12">
				<form action="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do" method="post" class="modal-content"  enctype="multipart/form-data">
					<div class="modal-header">
						<h3 class="modal-title">上傳票券圖片</h3>
					</div>
					<div class="modal-body">
						<div class="form-row input-mb d-flex">
							<div class="form-group col-md-3 input-mr">
								<label for="tkt_img">請選擇您要上傳的票券圖片</label> 
								<input type="file" class="form-control" id="formFile" accept="image/*" name="imageFile">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<a href="<%=request.getContextPath()%>/backend/tkt/listAllTkt.jsp" class="btn btn-secondary" data-dismiss="modal">取消</a> 
							<input type="hidden" name="action" value="uploadTktImg">
							<input type="hidden" name="tkt_no" value="${tkt_no}">
							<input type="submit" class="btn btn-primary" value="儲存">
					</div>
				</form>

			</div>
		</div>
	</div>

	<%@ include file="/backend/commonJS.file"%>


</body>
</html>