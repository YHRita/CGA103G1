<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.time.LocalDate"%>


<%
// 取得自己的store所有資訊存放至pageContext
 StoreService storeSvc = new StoreService();

List<StoreVO> list = storeSvc.getAllStore();

pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>

	<head>
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->
	</head>

	<body>
		<%@ include file="/backend/loading.file" %> <!-- loading -->
		<%@ include file="/backend/header.file" %> <!-- Header -->
		<%@ include file="/backend/sidebar.file" %> <!-- sidebar -->

		

<!-- 	內容寫在main-content這個div內    -->

<div class="main-content card card-body table-responsive">

<h1><a href="<%=request.getContextPath()%>/backend/store/storeSelectPage.jsp">回到首頁</a></h1>
			<table id="example4" class="display" style="min-width: 845px">
				<thead>
				<tr>
					<th>廠商會員編號</th>
					<th>帳號</th>
					<th>密碼</th>
					<th>帳號狀態</th>
					<th>店家名稱</th>
					<th>統編</th>
					<th>負責人</th>
					<th>電話</th>
					<th>傳真</th>
					<th>登記地址</th>
					<th>聯絡人</th>
					<th>連絡電話</th>
					<th>聯絡地址</th>
					<th>信箱</th>
					<th>加入時間</th>
					<th>轉帳帳號</th>
					<th>票券總分數</th>
					<th>票券總評價數</th>
					<th>票券平均評價數</th>
					<th>訂房總分數</th>
					<th>訂房總評價數</th>
					<th>活動總分數</th>
					<th>活動總評價數</th>
					<th>被檢舉計點</th>
					<th>修改資料</th>  
				</tr>
			</thead>
			<tbody>
				<c:forEach var="storeVO" items="${list}">
					<tr class="view">
						<td>${storeVO.store_no}</td>
						<td>${storeVO.store_acc}</td>
						<td>${storeVO.store_pwd}</td>
						<td><c:choose>
								<c:when test="${storeVO.acc_status==0}">
									<i class='bx bxs-circle' style='color: red'></i>帳號未啟用</c:when>
								<c:when test="${storeVO.acc_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>帳號已啟用</c:when>
								<c:when test="${storeVO.acc_status==2}">
									<i class='bx bxs-circle' style='color: #aaa'></i>帳號停權</c:when>
							</c:choose>
						</td>
						<td>${storeVO.store_name}</td>
						<td>${storeVO.store_gui}</td>
						<td>${storeVO.store_rep}</td>
						<td>${storeVO.store_tel}</td>
						<td>${storeVO.store_fax}</td>
						<td>${storeVO.store_add}</td>
						<td>${storeVO.store_poc}</td>
						<td>${storeVO.store_con_phone}</td>
						<td>${storeVO.store_con_add}</td>
						<td>${storeVO.store_email}</td>
						<td>${storeVO.store_reg_date}</td>
						<td>${storeVO.bank_account}</td>
						<td>${storeVO.store_tkt_rating_score}</td>
						<td>${storeVO.store_tkt_rating_count}</td>
						<td>${storeVO.store_tkt_rating}</td>
						<td>${storeVO.store_rm_rating_score}</td>
						<td>${storeVO.store_rm_rating_count}</td>
						<td>${storeVO.store_act_rating_score}</td>
						<td>${storeVO.store_act_rating_count}</td>
						<td>${storeVO.store_report_count}</td>  
						<td>
							<form method="post" action="<%=request.getContextPath()%>/store.do">
								<input type="submit" value="修改">
								<input type="hidden" name="store_no" value="${storeVO.store_no}">
								<input type="hidden" name="action" value="updateStore">
							</form>
						</td>
						</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>