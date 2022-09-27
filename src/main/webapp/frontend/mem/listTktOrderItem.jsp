<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem_coup.model.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_order2.model.*"%>
<%@ page import="com.tkt_item2.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.time.LocalDate"%>



<!DOCTYPE html>
<html>
<head>
	<style>

		.tktImgWidth {
			width: 80px;
		}
	
	</style>

<!-- CSS -->
<%@ include file="/frontend/commonCSS.file"%>
</head>

<body>
	<%@ include file="/frontend/header.file"%>
	<%@ include file="/frontend/memSidebar.file"%>
	<div class="col-lg-9 mb-5">
		<h3 class="mt-5 mb-3">會員票券訂單詳情 - Memory</h3>



		<a href="<%=request.getContextPath()%>/frontend/mem/listTktOrder.jsp" class="btn btn-info mb-3">返回我的票券訂單</a> 
        <table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr class="text-center">
							<th scope="col">票券訂單編號</th>
							<th scope="col"></th>
							<th scope="col">票券名稱</th>
							<th scope="col">票券小計</th>
							<th scope="col">票券數量</th>
							
						</tr>
					</thead>
					
					<tbody class="text-center">
					
					
					<c:forEach var="tktItem2VO" items="${tktItemList}">
							<tr>
								<td>${tktItem2VO.tkt_order_no}</td>
								<td>
									<img src="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${tktItem2VO.tktVO.tkt_no}&action=showFirstImages" class="tktImgWidth">
								</td>
								<td>${tktItem2VO.tktVO.tkt_name}</td>
								<td><fmt:formatNumber value="${tktItem2VO.tkt_ori_price}" type="currency" /></td>
								<td>${tktItem2VO.amount}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
	</div>
	</div>
	</div>

	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>


</body>
</html>