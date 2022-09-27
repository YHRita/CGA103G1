<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_order.model.*"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.store.model.*" %>
<jsp:useBean id="rmOderSvc" class="com.rm_order.model.RmOrderService" />
<jsp:useBean id="memSvc" class="com.mem.model.MemService" />
<jsp:useBean id="storeSvc" class="com.store.model.StoreService" />


<%
// 計算各狀態有幾筆資料
// pageContext.setAttribute("rmOderSvc", rmOderSvc);
 String store_acc = (String)session.getAttribute("store_acc");
 StoreVO storevo = storeSvc.getOneStoreByAcc(store_acc);
 Integer store_no = storevo.getStore_no();
List<RmOrderVO> orderlist = rmOderSvc.getAllByStore(store_no);
pageContext.setAttribute("orderlist", orderlist);
%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="/frontend/commonCSS.file"%>
<%@ include file="/backend/commonCSS.file"%>
<!-- CSS -->

</head>

<body>
	
	
	<%@ include file="/frontend/header.file"%>
	<!-- Header -->
	<%@ include file="/frontend/store/storeSidebar.file"%>
	<!-- sidebar -->



	<!-- 	內容寫在main-content這個div內    -->
	<div class=" col-lg-9 card card-body table-responsive">
		<table id="example4 " class="stripe table fold-table" style="min-width: 845px">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>會員</th>
					<th>廠商</th>
					<th>廠商電話</th>
					<th>訂單日期</th>
					<th>訂單總金額</th>
					<th>訂單狀態</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rmOrderVO" items="${orderlist}">
					<tr class="view">
					<td>
						<div class="container text-primary">${rmOrderVO.rm_order_no}</div>
					</td>
					<td>
							<h4 class="mb-0 mt-1">
								<a class="text-black">${memSvc.getOneMem(rmOrderVO.mem_no).mem_name}</a>
							</h4>
					</td>
					<td>
						
						<h4 class="mb-0 mt-1">
								<a class="text-black">${storeSvc.getOneStore(rmOrderVO.store_no).store_name}</a>
						</h4>
					</td>
					<td>${storeSvc.getOneStore(rmOrderVO.store_no).store_tel}</td>
					<td>${rmOrderVO.order_date}</td>
					<td>${rmOrderVO.rm_charge}</td>
					<td><c:choose>
							<c:when test="${rmOrderVO.rm_order_status==0}">
									<i class='bx bxs-circle' style='color: red'></i>入住中</c:when>
							<c:when test="${rmOrderVO.rm_order_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>正常</c:when>
							<c:when
									test="${rmOrderVO.rm_order_status==2 && rmOrderVO.rm_charge!=0}">
									<i class='bx bxs-circle' style='color: #aaa'></i>已實現</c:when>
							<c:when
									test="${rmOrderVO.rm_order_status==2 && rmOrderVO.rm_charge==0}">
									<i class='bx bxs-circle' style='color: yellow'></i>已取消</c:when>
						</c:choose>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>
	<%@ include file="/backend/commonJS.file"%>
	<!-- JS -->
	<script>
		// 		header標題
		$("#pagename").text("MEMORY 後台管理");
		$(document).ready(function() {
			$('#example4').DataTable();
		});
	</script>
</body>
</html>