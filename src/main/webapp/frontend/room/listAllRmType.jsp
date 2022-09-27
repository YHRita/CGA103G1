<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.rm_pic.model.*"%>

<jsp:useBean id="rmTypeSvc" scope="page"
	class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmPicSvc" scope="page"
	class="com.rm_pic.model.RmPicService" />
<jsp:useBean id="storeSvc" scope="page"
	class="com.store.model.StoreService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<%
//如果session中沒有間數跟人數
if (session.getAttribute("people") == null) {
	pageContext.setAttribute("people", 2);
}
if (session.getAttribute("roomtotal") == null) {
	pageContext.setAttribute("roomtotal", 1);
}

// 日曆的日期，arrival_date、departure_date
if (session.getAttribute("rangedate") != null) {
	String rangedate = (String) session.getAttribute("rangedate");
	List<String> dateList = new LinkedList<String>();
	dateList = Arrays.asList(rangedate.split(" to "));
	String arrival_date = dateList.get(0);
	String departure_date = dateList.get(1);
	pageContext.setAttribute("arrival_date", arrival_date);
	pageContext.setAttribute("departure_date", departure_date);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>尋找下榻民宿</title>
<%@ include file="/frontend/commonCSS.file"%>
<%@ include file="/frontend/commonCSS.file"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />


<style>
.TravelGo-category-footer {
	padding: 15px 0px !important;
}

.badge {
	padding: 10px 10px 5px 10px !important;
}

.mr-10 {
	margin-right: 10px;
}

.mb-60 {
	margin-bottom: 60px;
}

.cartBtn {
	position: fixed;
	border-radius: 100%;
	background-color: #5bc9e2;
	width: 60px;
	height: 60px;
	right: 10%;
	bottom: 10%;
	cursor: pointer;
	z-index: 10;
}

.cartIcon {
	padding-top: 12px;
	font-size: 20px;
	color: white;
}

.tktImgHeight {
	max-height: 220px;
}

.FilterBtn1 {
	display: block;
	width: 100%;
	margin: 43px 0px 0px 38px;
	padding: 6px 0;
	text-transform: uppercase;
}

.room-title {
	font-size: 40px;
	color: #30504F;
}

.room-subtitle {
	padding: 50px 50px;
	color: #996A4D;
}

color
:
 
#996A4D
;

    
}
.a.more-btn btn-bg-one {
	display: block;
	width: 100%;
	margin: 43px 0px 0px 38px;
	padding: 6px 0;
	text-transform: uppercase;
}

.room-img img {
	border-radius: 50px;
}

.room-content {
	margin-bottom: 30px;
	padding: 50px;
	background-color: #f7f6f2;
	position: relative;
	right: -30px;
}

.btn-bg {
	background-color: #a3785e;
	color: #ffffff;
	box-shadow: 0px 4px 4px 1px rgb(179 140 97/ 20%);
}

.more-btn {
	background-color: #7eced7;
	margin: -25px 59px 31px 220px;
	display: inline-block;
	padding: 12px 24px;
	text-align: center;
	position: relative;
	overflow: hidden;
	z-index: 1;
	transition: all 0.7s;
	border-radius: 50px;
	font-size: 16px;
	font-weight: 600;
	letter-spacing: 1px;
	border: none;
	cursor: pointer;
}

.ui-widget-content {
	border: 1px solid #aaaaaa00;
	background: #fff url(images/ui-bg_flat_75_ffffff_40x100.png) 50% 50%
		repeat-x;
	color: #222;
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/frontend/header.file"%>
	<%@ include file="/frontend/roomSidebar.file"%>
	<link
		href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
		rel="stylesheet" />

	<section class="pt80 pb80 cruise-grid-view">

		<div class="container">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red" class="error-list-mb">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 Filter-left mb-60">
					<form class="col-lg-12 col-md-12 col-sm-12 d-flex row"
						method="post"
						action="<%=request.getContextPath()%>/RmRsv/RmRsv.do"
						id="getEnoughType">

						<div class="col-lg-4 col-md-3 col-sm-12">
							<label for="form_dates" class="form-label">透過關鍵字查詢</label>
							<div class="form-group">
								<input class="form-control" type="text" placeholder="請搜尋關鍵字"
									name="add">
							</div>
						</div>

						<div class="col-lg-3 col-md-3 col-sm-12">

							<!--         <label for="check_in" class="form-label">入住時間</label> -->
							<!--           <input class="form-control" type="text"  id="rangeDate"  name="rangedate"  placeholder="Check-in"  /> -->
							<label for="check_in" class="form-label">入住時間</label> <input
								class="form-control" type="text" id="datepicker"
								name="arrival_date" autocomplete="off" placeholder="Check-in" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<label for="check_out" class="form-label">退房時間</label> <input
								class="form-control" type="text" id="datepicker-out"
								name="departure_date" autocomplete="off" placeholder="Check-out" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<label for="peoplet" class="form-label">入住人數</label> <select
								id="people" name="people" class="form-control" required>
								<option value="1" ${people == 1 ? 'selected' : '' }>1人</option>
								<option value="2" ${people == 2 ? 'selected' : '' }>2人</option>
								<option value="3" ${people == 3 ? 'selected' : '' }>3人</option>
								<option value="4" ${people == 4 ? 'selected' : '' }>4人</option>
							</select>
						</div>

						<div class="col-lg-3 col-md-3 col-sm-12">
							<label for="peoplet" class="form-label">預定房間數量</label> <select
								id="roomtotal" name="roomtotal" class="form-control" required>
								<option value="1" ${roomtotal == 1 ? 'selected' : '' }>1間</option>
								<option value="2" ${roomtotal == 2 ? 'selected' : '' }>2間</option>
								<option value="3" ${roomtotal == 3 ? 'selected' : '' }>3間</option>
								<option value="4" ${roomtotal == 4 ? 'selected' : '' }>4間</option>
								<option value="5" ${roomtotal == 5 ? 'selected' : '' }>5間</option>
								<option value="6" ${roomtotal == 6 ? 'selected' : '' }>6間</option>
							</select>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-12">
							<input type="hidden" name="action" value="getEnoughType">
							<button type="button" class="btn btn-primary btn-grad FilterBtn1"
								onclick="check();">
								<i class="fas fa-filter mr-1"></i>查詢
							</button>
						</div>

					</form>
				</div>
			</div>
		</div>


		<div class="reservation-area pt-100 pb-70">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-6">
						<div class="room-content">
							<div class="section-title text-center">
								<h2 class="room-title">精選民宿</h2>
								<hr>
								<h3 class="room-subtitle text-left ">
									每週挑選網友票選最佳民宿，讓您能在眾多民宿中找到最適合您的住宿方案</h3>
							</div>
							<a href="<%=request.getContextPath()%>/frontend/room/rmList.jsp"
								class="more-btn btn-bg">更多精選房型</a>
						</div>
					</div>

					<div class="col-lg-5">
						<div class="room-img">
							<img
								src="<%=request.getContextPath()%>/frontend/assets/images/hotels/no-img.jpeg">
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
	<!--  footer  -->
	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
		rel='stylesheet'>



	<script> 
		function check() {
			let datepicker = document.getElementById('datepicker').value.trim().length;
			let datepickerout = document.getElementById('datepicker-out').value
					.trim().length;
			let getEnoughType = document.getElementById('getEnoughType');

			if (datepicker === 0 || datepickerout === 0) {
				datepickerIsNull();
				return false;
			} else {
				getEnoughType.submit();
			}
		}
		// alert樣式
		function datepickerIsNull() {
			Swal.fire("查詢作業失敗", "請選擇 入住日 和 退房日", "error");
		}
	</script>
</body>
</html>