<%@page import="com.tkt_item2.model.TktItem2VO"%>
<%@page import="com.tkt_item2.model.TktItem2Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem_coup.model.*"%>
<%@ page import="com.tkt_order2.model.*"%>
<%@ page import="com.tkt_item2.model.*"%>
<%@ page import="com.mem.model.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券詳情 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
	<style>
	
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
		
		.tktImgWidth {
			width: 80px;
		}
	
	</style>
	
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  	<!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) repeat center; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">票券訂單明細</h1>
      </div>
    </div>
  </div>
</div>
<!-- =======================
	Banner innerpage -->

  
  <!-- main -->
  
  <section class="pt80 pb80 listingDetails Campaigns">
  <div class="container">
    <div class="row"> 
      
      <div class="col-lg-12 col-md-12 col-sm-12 ">

        
        <a href="<%=request.getContextPath()%>/frontend/tkt_order/tktOrder.jsp" class="btn btn-info mb-3">返回我的票券訂單</a> 
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
    
</section>


  
  
     </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
	
	<!-- sweetalert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
 

  

</body>
</html>