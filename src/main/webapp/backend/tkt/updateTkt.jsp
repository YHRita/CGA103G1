<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="tktSvc" class="com.tkt.model.TktService" />

<%
TktVO tktVO = (TktVO) request.getAttribute("tktVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>修改票券 - Memory</title>

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
				<h1 class="coup-list-h1">修改票券</h1>
			</div>
			
			<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
				
				<div class="alert alert-danger alert-dismissible fade show">
					<svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="me-2"><polygon points="7.86 2 16.14 2 22 7.86 22 16.14 16.14 22 7.86 22 2 16.14 2 7.86 7.86 2"></polygon><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
					<strong>Error!</strong> 請修正以下錯誤: <br>
					<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red" class="error-list-mb">${message}</li>
					</c:forEach>
					</ul>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="btn-close"></button>
				</div>
		</c:if>
			
			<div class="col-12">
				<form action="<%=request.getContextPath()%>/tkt/updateTkt.do" method="post" class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">${tktVO.tkt_no} 修改票券</h3>
					</div>
					<div class="modal-body">
						<div class="form-row input-mb d-flex">
							<!-- 1 -->
							<div class="form-group col-md-5 input-mr">
								<label class="form-label" for="tkt_name">票券名稱</label> <input
									type="text" class="form-control" id="tkt_name" name="tkt_name"
									value="${tktVO.tkt_name}">
							</div>
							<div class="form-group col-md-4 input-mr">
								<label class="form-label" for="price">價格</label> <input
									type="number" class="form-control" id="price" name="price"
									value="${tktVO.price}">
							</div>
							<div class="form-group col-md-2">
								<label for="">狀態</label>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="tkt_status"
										id="statusOn" value="0" checked> <label
										class="form-check-label" for="statusOn"> 未上架 </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="tkt_status"
										id="statusOff" value="1"> <label
										class="form-check-label" for="statusOff"> 上架 </label>
								</div>
							</div>
						</div>
						<!-- 2 -->
						<div class="form-row d-flex input-mb">
							<div class="form-group col-md-3 input-mb input-mr">
								<label class="form-label" for="">原始數量</label> <input
									type="number" class="form-control" id="original_amount"
									name="original_amount" value="${tktVO.original_amount}">
							</div>



							<div class="form-group col-md-9 input-mb">
								<label class="form-label" for="locate">地點</label> 
									<select id="locate" class="default-select form-control wide" name=locate>
									<option selected>${tktVO.locate}</option>
									<option value="基隆市">基隆市</option>
									<option value="台北市">台北市</option>
									<option value="新北市">新北市</option>
									<option value="桃園市">桃園市</option>
									<option value="新竹縣">新竹縣</option>
									<option value="新竹市">新竹市</option>
									<option value="苗栗縣">苗栗縣</option>
									<option value="台中市">台中市</option>
									<option value="彰化縣">彰化縣</option>
									<option value="南投縣">南投縣</option>
									<option value="雲林縣">雲林縣</option>
									<option value="嘉義縣">嘉義縣</option>
									<option value="嘉義市">嘉義市</option>
									<option value="台南市">台南市</option>
									<option value="高雄市">高雄市</option>
									<option value="屏東縣">屏東縣</option>
									<option value="宜蘭縣">宜蘭縣</option>
									<option value="花蓮縣">花蓮縣</option>
									<option value="台東縣">台東縣</option>
									<option value="澎湖縣">澎湖縣</option>
									<option value="金門縣">金門縣</option>
									<option value="連江縣">連江縣</option>
								</select>
							</div>
						</div>

						<!-- 3 -->

						<div class="form-row d-flex input-mb">
							<div class="form-group col-md-4 input-mr">
								<label class="form-label" for="tkt_startdate">票券開始日期</label> <input
									type="date" class="form-control" id="tkt_startdate"
									name="tkt_startdate" value="${tktVO.tkt_startdate}">
							</div>
							<div class="form-group col-md-4 input-mr">
								<label class="form-label" for="tkt_enddate">票券結束日期</label> <input
									type="date" class="form-control" id="tkt_enddate"
									name="tkt_enddate" value="${tktVO.tkt_enddate}">
							</div>
							<div class="form-group col-md-3">
								<label class="form-label" for="kind">票券種類</label></br> <select
									id="inputState" class="default-select form-control wide"
									name="kind">
									<option value="0">景點門票</option>
									<option value="1">主題樂園</option>
									<option value="2">博物館展覽</option>
								</select>
							</div>
						</div>
						<!-- 4 -->
						<div class="form-group col-md-12 input-mb">
							<label class="form-label" for="address">體驗地點</label>
							<textarea class="form-control" id="address" name="address">${tktVO.address}</textarea>
						</div>
						<!-- 5 -->
						<div class="form-row d-flex input-mb">
						<div class="form-group col-md-6 input-mb input-mr">
							<label class="form-label" for="instruction">票券說明</label>
							<textarea class="form-control" id="instruction"
								name="instruction" rows="7">${tktVO.instruction}</textarea>
						</div>
						<!-- 6 -->
						<div class="form-group col-md-6 input-mb">
							<label class="form-label" for="instruction">購買須知</label>
							<textarea class="form-control" id="notice"
								name="notice" rows="7">${tktVO.notice}</textarea>
						</div>
						</div>
						<!-- 7 -->
						<div class="form-row d-flex input-mb">
						<div class="form-group col-md-6 input-mb input-mr">
							<label class="form-label" for="howuse">如何使用</label>
							<textarea class="form-control" id="howuse" name="howuse" rows="7">${tktVO.howuse}</textarea>
						</div>
						<!-- 8 -->
						<div class="form-group col-md-6 input-mb">
							<label class="form-label" for="canxpolicy">取消政策</label>
							<textarea class="form-control" id="canxpolicy" name="canxpolicy" rows="7">${tktVO.canxpolicy}</textarea>
						</div>
						</div>
					</div>
					</div>
					<div class="modal-footer">
						<!-- hidden input -->
						<input type="hidden" name="action" value="tktUpdate"> 
						<input type="hidden" name="tkt_no" value="${tktVO.tkt_no}">
						<a href="<%=request.getContextPath()%>/backend/tkt/listAllTkt.jsp"
							class="btn btn-secondary" data-dismiss="modal">取消</a> 
							
						<input type="submit" class="btn btn-primary" value="儲存">
					</div>
				</form>

			</div>
		</div>
	</div>


	

	<%@ include file="/backend/commonJS.file"%>

</body>
</html>