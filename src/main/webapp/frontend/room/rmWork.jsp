<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.rm_reserve.model.*"%>
<%@ page import="com.rm_order.model.*"%>
<%@ page import="com.rm_order_list.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="rmTypeSvc" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmReserveSvc"
	class="com.rm_reserve.model.RmReserveService" />
<jsp:useBean id="rmOrderSvc" class="com.rm_order.model.RmOrderService" />
<jsp:useBean id="rmOrderListSvc"
	class="com.rm_order_list.model.RmOrderListService" />
<jsp:useBean id="memSvc" class="com.mem.model.MemService" />
<jsp:useBean id="storeSvc" class="com.store.model.StoreService" />

<%
StoreVO storeVO = new StoreVO();
storeVO = (StoreVO)request.getSession().getAttribute("storevo");
Integer store_no = storeVO.getStore_no();
if (request.getAttribute("orderlist") == null) {
	List<RmOrderVO> orderlist = rmOrderSvc.getAllByStore(store_no);
	pageContext.setAttribute("orderlist", orderlist);
}
pageContext.setAttribute("store_no", store_no);
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<%@ include file="/frontend/commonCSS.file"%>

<!-- 基本JS檔案 -->
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<style>
.bxs {
	font-size: 50px;
	color: #aaa;
}

.modal-content {
	background: #f5f3ee;
}

h3.card-title {
	color: #996A4D;
}

h4 {
	font-size: 16px;
	font-weight: 600;
	color: #30504F;
}

h5 {
	font-size: 17px;
	font-weight: 500;
	color: #30504F;
}

.to-jump {
	padding: 15px 30px;
	background: #D1E6E6;
}

.to-jump:last-child {
	padding: 10px 30px 5px 30px;
	cursor: default;
}


.to-jump h3 {
	font-size: 25px;
	font-weight: 600;
	color: #996A4D;
	padding-top: 10px;
}


div.main-content {
	margin-top: 50px;
}

div.main-content>h3 {
	/* 			margin: 40px 10px 0px 10px; */
	font-size: 26px;
}

a {
	cursor: pointer;
}

table.dataTable tbody td {
	padding: 5px;
	font-size: 20px;
	font-weight: 500;
	border-bottom: 0;
	letter-spacing: 0.5px;
}

h3.checkInTitle {
	background: #F2F2F2;
	padding: 10px;
	font-weight: 600;
	position: relative;
	bottom: -55px;
}

table#checkInTable tr.odd {
	background: #edf2f1 !important;
}

h3.checkOutTitle {
	background: #F2F2F2;
	padding: 10px;
	font-weight: 600;
	position: relative;
	bottom: -55px;
}

table#checkOutTable tr.odd {
	background: #f5f5f0 !important;
}

h3.stayTitle {
	background: #F2F2F2;
	padding: 10px;
	font-weight: 600;
	position: relative;
	bottom: -55px;
}

table#stayTable tr.odd {
	background: #f5f6f8 !important;
}

td.sorting_1 {
	border-bottom: #ddd !important;
	padding-left: 35px !important;
}

.dataTables_filter>label {
	font-size: 20px;
	color: #fff;
}

.hidden {
	display: none;
}

.card {
	margin-bottom: 0;
}

.date {
	display: flex;
	justify-content: center;
}

.pt-6, .py-6 {
	padding-top: 4.5rem !important;
	width: -webkit-fill-available;
}
</style>


</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<%@ include file="/frontend/header.file"%>
	<!-- sidebar -->
	<%@ include file="/frontend/store/storeSidebar.file"%>
	<div class="main-content col-lg-9">
		<div class="d-flex justify-content-around">
			<div class="col-xl-2 card to-jump">
				<a href="#checkInTable">
					<div class="text-center">
						<div class="col">
							<h4>今日待入住訂單</h4>
							<h3>
								<svg style="width: 30px; height: 30px;" class="ionicon"
									viewBox="0 0 512 512">
									<path
										d="M384 240H96V136a40.12 40.12 0 0140-40h240a40.12 40.12 0 0140 40v104zM48 416V304a64.19 64.19 0 0164-64h288a64.19 64.19 0 0164 64v112"
										fill="none" stroke="currentColor" stroke-linecap="round"
										stroke-linejoin="round" stroke-width="32" />
									<path
										d="M48 416v-8a24.07 24.07 0 0124-24h368a24.07 24.07 0 0124 24v8M112 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16M256 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16"
										fill="none" stroke="currentColor" stroke-linecap="round"
										stroke-linejoin="round" stroke-width="32" /></svg>
								${rmOrderListSvc.getCheckInByStore(store_no).size()}
							</h3>
						</div>
						<div
							class="col-4 bxs d-flex justify-content-center align-items-center">
							<i class='bx bx-log-in'></i>
						</div>
					</div>
				</a>
			</div>

			<div class="col-xl-2 card to-jump">
				<a href="#checkOutTable">
					<div class="text-center">
						<div class="col">
							<h4>今日待退房</h4>
							<h3>
								<svg style="width: 30px; height: 30px;" class="ionicon"
									viewBox="0 0 512 512">
									<path
										d="M384 240H96V136a40.12 40.12 0 0140-40h240a40.12 40.12 0 0140 40v104zM48 416V304a64.19 64.19 0 0164-64h288a64.19 64.19 0 0164 64v112"
										fill="none" stroke="currentColor" stroke-linecap="round"
										stroke-linejoin="round" stroke-width="32" />
									<path
										d="M48 416v-8a24.07 24.07 0 0124-24h368a24.07 24.07 0 0124 24v8M112 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16M256 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16"
										fill="none" stroke="currentColor" stroke-linecap="round"
										stroke-linejoin="round" stroke-width="32" /></svg>
								${rmOrderListSvc.getCheckOutByStore(store_no).size()}
							</h3>
						</div>

						<div
							class="col-4 bxs d-flex justify-content-center align-items-center">
							<i class='bx bx-log-out'></i>
						</div>
					</div>
				</a>
			</div>

			<div class="col-xl-2 card to-jump">
				<a href="#stayTable">
					<div class="text-center">
						<div class="col">
							<h4>入住中房間</h4>
							<h3>
								<svg style="width: 30px; height: 30px;" class="ionicon"
									viewBox="0 0 512 512">
									<path
										d="M384 240H96V136a40.12 40.12 0 0140-40h240a40.12 40.12 0 0140 40v104zM48 416V304a64.19 64.19 0 0164-64h288a64.19 64.19 0 0164 64v112"
										fill="none" stroke="currentColor" stroke-linecap="round"
										stroke-linejoin="round" stroke-width="32" />
									<path
										d="M48 416v-8a24.07 24.07 0 0124-24h368a24.07 24.07 0 0124 24v8M112 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16M256 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16"
										fill="none" stroke="currentColor" stroke-linecap="round"
										stroke-linejoin="round" stroke-width="32" /></svg>
								${rmOrderListSvc.getStayByStore(store_no).size()}
							</h3>
						</div>
						<div
							class="col-4 bxs d-flex justify-content-center align-items-center">
							<i class='bx bx-home-smile'></i>
						</div>
					</div>
				</a>
			</div>

			<div>
				<div>
					<div class="col">
						<h4>&emsp;&emsp;&emsp;&emsp;可預訂空房</h4>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/RmRsv/RmRsv.do"
							id="getEnoughTypeByStore">
							<div class="date">
								<div>
									<input type="text" id="rangeDate" name="rangedate"
										placeholder="請選擇查詢期間" class="form-control"
										style="width: 210px;" data-input> <input type="hidden"
										name="store_no" value="${store_no}">
								</div>
								<div>
									<input type="hidden" name="action" value="getEnoughTypeByStore">
									<input type="submit" class="btn btn-grad" value="查詢">
								</div>
							</div>
							<c:forEach var="rmVO" items="${ableList}">
								<h5>
									<svg style="width: 20px; height: 20px;" class="ionicon"
										viewBox="0 0 512 512">
									<path
											d="M384 240H96V136a40.12 40.12 0 0140-40h240a40.12 40.12 0 0140 40v104zM48 416V304a64.19 64.19 0 0164-64h288a64.19 64.19 0 0164 64v112"
											fill="none" stroke="currentColor" stroke-linecap="round"
											stroke-linejoin="round" stroke-width="32" />
									<path
											d="M48 416v-8a24.07 24.07 0 0124-24h368a24.07 24.07 0 0124 24v8M112 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16M256 240v-16a32.09 32.09 0 0132-32h80a32.09 32.09 0 0132 32v16"
											fill="none" stroke="currentColor" stroke-linecap="round"
											stroke-linejoin="round" stroke-width="32" /></svg>
									<a href="">&emsp;${rmVO.rm_name}&emsp;剩餘${rmVO.minqty}間 /
										${rmReserveSvc.getOneByRm(rmVO.rm_type_no).rm_total} total </a>
								</h5>
							</c:forEach>
						</Form>
					</div>
				</div>
			</div>
		</div>

		<h3 class="checkInTitle text-center">
			<i class='bx bx-log-in-circle'></i> 今日待入住
		</h3>
		<div class="main-content">
			<div class="align-items-center flex-wrap">
				<div class="card-tabs mt-3 mt-sm-0">
					<table id="checkInTable" class="display table fold-table">
						<thead>
							<tr>
								<th>訂單編號</th>
								<th>房型名稱</th>
								<th>房間數量</th>
								<th>會員資料</th>
								<th class="hidden">會員帳號</th>
								<th>會員電話</th>
								<th>入住日</th>
								<th>退房日</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="checkInVO"
								items="${rmOrderListSvc.getCheckInByStore(store_no)}">
								<tr>
									<td>${checkInVO.rm_order_no}</td>
									<td>${rmTypeSvc.getOneRm(checkInVO.rm_type_no).rm_name}</td>
									<td>${checkInVO.rm_amount}</td>
									<td>${memSvc.getOneMem(rmOrderSvc.getOne(checkInVO.rm_order_no).mem_no).mem_name}</td>
									<td class="hidden">${rmOrderSvc.getOne(checkInVO.rm_order_no).mem_no}</td>
									<td>${memSvc.getOneMem(rmOrderSvc.getOne(checkInVO.rm_order_no).mem_no).mem_mobile}</td>
									<td>${checkInVO.arrival_date}</td>
									<td>${checkInVO.departure_date}</td>
									<td><div>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/RmOrder">
												<input type="submit" class="btn btn-grad" value="訂單資訊">
												<input type="hidden" name="rm_order_no"
													value="${checkInVO.rm_order_no}"> <input
													type="hidden" name="store_no" value="${store_no}">
												<input type="hidden" name="action" value="getOneStore">
											</FORM>
										</div></td>
									<td><div>
											<FORM METHOD="post" id="checkIn${checkInVO.rm_order_no}"
												ACTION="<%=request.getContextPath()%>/RmOrder">
												<input onclick="checkIn(${checkInVO.rm_order_no})" type="button" class="btn btn-grad" value="入住">
												<input type="hidden" name="rm_order_no"
													value="${checkInVO.rm_order_no}"> <input
													type="hidden" name="action" value="checkIn">
											</FORM>
										</div></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<h3 class="checkOutTitle text-center">
			<i class='bx bx-log-in-circle'></i> 今日待退房
		</h3>
		<div class="main-content">
			<div class="align-items-center flex-wrap">
				<div class="card-tabs mt-3 mt-sm-0">
					<table id="checkOutTable" class="display table fold-table">
						<thead>
							<tr>
								<th>訂單編號</th>
								<th>房型名稱</th>
								<th>房間數量</th>
								<th>會員資料</th>
								<th class="hidden">會員帳號</th>
								<th>會員電話</th>
								<th>入住日</th>
								<th>退房日</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="checkOutVO"
								items="${rmOrderListSvc.getCheckOutByStore(store_no)}">
								<tr>
									<td>${checkOutVO.rm_order_no}</td>
									<td>${rmTypeSvc.getOneRm(checkOutVO.rm_type_no).rm_name}</td>
									<td>${checkOutVO.rm_amount}</td>
									<td>${memSvc.getOneMem(rmOrderSvc.getOne(checkOutVO.rm_order_no).mem_no).mem_name}</td>
									<td class="hidden">${rmOrderSvc.getOne(checkOutVO.rm_order_no).mem_no}</td>
									<td>${memSvc.getOneMem(rmOrderSvc.getOne(checkOutVO.rm_order_no).mem_no).mem_mobile}</td>
									<td>${checkOutVO.arrival_date}</td>
									<td>${checkOutVO.departure_date}</td>
									<td>
										<div>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/RmOrder">
												<input type="submit" class="btn btn-grad" value="訂單資訊">
												<input type="hidden" name="rm_order_no"
													value="${checkOutVO.rm_order_no}"> <input
													type="hidden" name="store_no" value="${store_no}">
												<input type="hidden" name="action" value="getOneStore">
											</FORM>
										</div>
									</td>
									<td>
										<div>
											<FORM METHOD="post" id="checkOut${checkOutVO.rm_order_no}"
												ACTION="<%=request.getContextPath()%>/RmOrder">
												<input onclick="checkOut(${checkOutVO.rm_order_no})" type="button" class="btn btn-grad" value="退房">
												<input type="hidden" name="rm_order_no"
													value="${checkOutVO.rm_order_no}"> <input
													type="hidden" name="action" value="checkOut">
											</FORM>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<h3 class="stayTitle text-center">
			<i class='bx bx-log-in-circle'></i> 入住中房間
		</h3>
		<div class="main-content">
			<div class="align-items-center flex-wrap">
				<div class="card-tabs mt-3 mt-sm-0">
					<table id="stayTable" class="display table fold-table">
						<thead>
							<tr>
								<th>訂單編號</th>
								<th>房型名稱</th>
								<th>房間數量</th>
								<th>會員資料</th>
								<th class="hidden">會員帳號</th>
								<th>會員電話</th>
								<th>入住日</th>
								<th>退房日</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="stayVO"
								items="${rmOrderListSvc.getStayByStore(store_no)}">
								<tr>
									<td>${stayVO.rm_order_no}</td>
									<td>${rmTypeSvc.getOneRm(stayVO.rm_type_no).rm_name}</td>
									<td>${stayVO.rm_amount}</td>
									<td>${memSvc.getOneMem(rmOrderSvc.getOne(stayVO.rm_order_no).mem_no).mem_name}</td>
									<td class="hidden">${rmOrderSvc.getOne(stayVO.rm_order_no).mem_no}</td>
									<td>${memSvc.getOneMem(rmOrderSvc.getOne(stayVO.rm_order_no).mem_no).mem_mobile}</td>
									<td>${stayVO.arrival_date}</td>
									<td>${stayVO.departure_date}</td>
									<td>
										<div>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/RmOrder">
												<input type="submit" class="btn btn-grad" value="訂單資訊">
												<input type="hidden" name="rm_order_no"
													value="${stayVO.rm_order_no}"> <input type="hidden"
													name="store_no" value="${store_no}"> <input
													type="hidden" name="action" value="getOneStore">
											</FORM>
										</div>
									</td>
									<td>
										<div>
											<FORM METHOD="post" id="stay${stayVO.rm_order_no}"
												ACTION="<%=request.getContextPath()%>/RmOrder">
												<input onclick="stay(${stayVO.rm_order_no})" type="button" class="btn btn-grad" value="退房">
												<input type="hidden" name="rm_order_no"
													value="${stayVO.rm_order_no}"> <input type="hidden"
													name="rm_type_no" value="${stayVO.rm_type_no}"> <input
													type="hidden" name="departure_date"
													value="${stayVO.departure_date}"> <input
													type="hidden" name="action" value="checkOutEarly">
											</FORM>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>
	<%@ include file="/backend/commonJS.file"%>
	<!-- flatpickr CSS -->
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
	<!-- 放置基本JS檔案 -->
	<script>
		$("#rangeDate").flatpickr({
			mode : "range",
			dateFormat : "Y-m-d",
			defaultDate : [ "${arrival_date}", "${departure_date}" ],
			minDate : "today",
			maxDate : new Date().fp_incr(90),
			disable : [],
		});

		function checkIn(rm_order_no) {
			let checkIn = document.getElementById("checkIn"+rm_order_no);
			Swal.fire({
                title: "操作確認",
                text: "確認資料無誤，辦理入住?",
                showCancelButton: true
            }).then(function(result) {
               if (result.value) {
                    checkIn.submit();
               }
               else {
              
               }
            });
		};
		
		function checkOut(rm_order_no) {
			let checkOut = document.getElementById("checkOut"+rm_order_no);
			Swal.fire({
                title: "操作確認",
                text: "確定辦理退房?",
                showCancelButton: true
            }).then(function(result) {
               if (result.value) {
            	   checkOut.submit();
               }
               else {
              
               }
            });
		};
		
		function stay(rm_order_no) {
			let stay = document.getElementById("stay"+rm_order_no);
			Swal.fire({
                title: "操作確認",
                text: "確定辦理提前退房?",
                showCancelButton: true
            }).then(function(result) {
               if (result.value) {
            	   stay.submit();
               }
               else {
              
               }
            });
		};
		
	</script>
</body>
</html>