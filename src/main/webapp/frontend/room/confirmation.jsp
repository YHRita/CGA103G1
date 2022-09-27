<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>

<jsp:useBean id="rmTypeSvc" scope="page" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmPicSvc" scope="page" class="com.rm_pic.model.RmPicService" />
<jsp:useBean id="rmorderlistSvc" scope="page" class="com.rm_order_list.model.RmOrderListService" />
<jsp:useBean id="memSvc" class="com.mem.model.MemService" />


<!doctype html>
<html>
    <head>
        <%@ include file="/frontend/commonCSS.file" %> <!-- 基本CSS檔案 -->
        <style>
	<%-- body {
 		background-color: #f7f9fa;
	}
 	.mt-5.mb-5.pt-20.container { 
  		padding: 3%; 
  		background-image: linear-gradient(rgba(238, 224, 206, 0.05) 30%, rgba(228, 214, 196, 0.2)), 
		url(<%=request.getContextPath()%>/front_end/assets/img/color_bg1.png); 
 	}
 	h1.confirmation-head-title {
 		color: #996A4D;
 		margin: 2% 0;
 	}
 	.bottom-area { 
 		margin: 2% 0 5% 0; 
 		box-shadow: 5px 5px #f0e9df; 
 		border: 1px solid #f0e8df; 
 		background: #fcfbf9;
 		padding: 4%;
 	} 
 	.top-area>p>span {
 		font-size: 24px;
 		font-weight: 600;
 		color: #30503f;
     	letter-spacing: 1px;
     	margin-left: 1%;
 	}
 	i {
 		padding: 0 10px;
 	}
 	.top-area>p {
	 	padding: 0;
	 	margin: 5px;
	 	font-size: 20px;
 		align-items: center;
 	}
 	.ord-img {
 		max-width: 250px;
 		padding-right: 4%;
 		border-right: 2px solid #a3785e;
 	}
 	.ord-info {
 		align-items: center;
 		padding-left: 6%;
 	}
 	.ord-info>p {
 		color: #888;
 		align-items: center;
 		margin-bottom: 6px;
 	}
 	.ord-info>p>span {
	 	color: #333;
	 	font-size: 18px;
 	}
 	.guest-details {
 		background: #ededed;
 		padding: 2% 2% 0 2%;
 		margin-left: auto; 
 		letter-spacing: 0.5spx;
 		font-size: 18px;
 	}
 	.guest-details>span {
 		font-weight: 600;
 	}
 	.guest-details>div:nth-child(4) {
 		margin-bottom: 10px;
 	}
 	.guest-details>p {
 		font-size: 14px;
 		color: #a3785e;
 	}
 	.price-area>div>div:first-child {
 		font-size: 18px;
 	}
 	.price-area>div>div:last-child {
 		font-size: 18px;
 		font-weight: 600;
 	}
 	.price-area>div:last-child {
 		margin-bottom: 10px;
 	}
 	.middle-line {
 		border: 1px solid #ededed;
 	}
 --%>
	</style>
    </head>
    <body>
        <%@ include file="/frontend/header.file" %> <!-- Header -->
		
		<div class="mt-5 mb-5 pt-20 container">
			<!-- **上方 -->
			<c:forEach var="rmOrderListVO" items="${rmorderlistSvc.getAllByRmOrderNo(rm_order_no)}">
			<div class="top-area col-lg-12">
				<h1 class="confirmation-head-title"><i class='bx bx-check-circle' ></i>您的預訂已確認</h1>
                <p class="row">預訂確認單至發送至民宿，民宿確認後會立即與您聯繫。</p>
                <p class="row">入住日期/退房日期：
					<span id="guest_checkinDate" class="mr-10">${rmOrderListVO.arrival_date}</span> <i class='bx bx-right-arrow-alt'></i> <span id="guest_checkoutDate">${rmOrderListVO.departure_date}</span>
            	</p>
            	<p class="row">預訂確認編號：
            		<span>${rm_order_no}</span>
            	</p>      
			</div>
             
            <!-- **下方 -->
			<div class="bottom-area col-lg-12">
				<div class="row">
					<div class="col-lg-7 col-xs-5 px-0">
						<div class="row col-12d-flex">
							<div class="ord-img col-5">
									<c:choose>
										<c:when test="${rmPicSvc.getAllByType(rm_type_no).size() > 0}">
											<img src="<%=request.getContextPath()%>/rmPic/rmPic.do?rm_type_no=${rm_type_no}&action=showFirstImages">
										</c:when>
										<c:otherwise>
											<img src="<%=request.getContextPath()%>/frontend/assets/images/room/no-img.jpg" class="no-img">
										</c:otherwise>
									</c:choose>
							</div>
							<div class="ord-info col-5">
								<p class="row">房型：
									<span>${rmTypeSvc.getOneRm(rm_type_no).rm_name}</span>
				            	</p>
				            	<p class="row">天數：
									<span>${days} 晚</span>
				            	</p>
								<p class="row">間數：
									<span>${rmOrderListVO.rm_amount} 間</span>
				            	</p>
							</div>
						</div>
	
						<hr class="middle-line">
						
						<div class="col-12 price-area">
							<div class="row col-12 d-flex justify-content-between">
								<div>每晚每間單價</div>
								<div><fmt:formatNumber value="${rmOrderListVO.rm_price}" pattern="NT$ ###,###" /></div>
								<div>本次訂房總價</div>
								<div><fmt:formatNumber value="${rmOrderListVO.rm_price*days}" pattern="NT$ ###,###" /></div>
							</div>
							<hr>
<!-- 							<div class="row col-12 d-flex justify-content-between"> -->
<!-- 								<div>總價</div> -->
<%-- 								<div><fmt:formatNumber value="${rmorderlistSvc.getOneRmOrderList(rm_order_list_no).total_price}" pattern="NT$ ###,###,###" /></div>	 --%>
<!-- 							</div> -->
						</div>
					</div>
					
					<div class="col-lg-5 col-xs-7 guest-details">
						<span>住客資料</span>
						<div>姓名: ${memSvc.getOneMem(mem_no).mem_name}</div>
						<div>電話: ${memSvc.getOneMem(mem_no).mem_mobile}</div>
						<div>email: ${memSvc.getOneMem(mem_no).mem_email}</div>
						
						<span>偏好</span>
<%-- 						<div>${orderSvc.getOneRoomOrder(ord_no).note}</div> --%>
<%-- 						<c:if test="${orderSvc.getOneRoomOrder(ord_no).note.equals('')}"> --%>
							<div>您沒有填寫備註，若有任何需求可用客服即時通與我們聯繫。</div>
<%-- 						</c:if> --%>
						<p>房間偏好需視實際住房情況而定。我們將會竭盡所能滿足您的要求。</p>
					</div>
				</div>
            </div>
            </c:forEach>
		</div>
		
        <%@ include file="/frontend/footer.file" %> <!-- Footer -->      
        <%@ include file="/frontend/commonJS.file" %> <!-- 基本JS檔案 -->
        
        <script>
        
        </script>
        
    </body>
</html>
