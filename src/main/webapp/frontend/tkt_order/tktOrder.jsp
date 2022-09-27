<%@page import="com.tkt_order2.model.TktOrder2Service"%>
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

<% 

MemVO memVO = new MemVO();
memVO = (MemVO)request.getSession().getAttribute("memVO");
Integer mem_no = memVO.getMem_no();

TktOrder2Service tktOrder2Srv = new TktOrder2Service();
List<TktOrder2VO> list = tktOrder2Srv.findAllOrderByMem(mem_no);
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的票券訂單查看 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	

	
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>


  	<!-- =======================
	Banner innerpage -->
<!-- <div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) repeat center; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">我的票券訂單</h1>
      </div>
    </div>
  </div>
</div> -->
<!-- =======================
	Banner innerpage -->
  <!-- main -->
  
  <section class="pt80 pb80 listingDetails Campaigns">
  <div class="container">
    <div class="row"> 
      <%@ include file="/frontend/tkt_order/pageIndex.file"%>
	<div class="col-lg-12 col-md-12 col-sm-12 Filter-left mb-60">
      	
      	<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr class="text-center">
							<th scope="col">票券訂單編號</th>
							<th scope="col">原價</th>
							<th scope="col">折扣</th>
							<th scope="col">付款金額</th>
							<th scope="col">訂單成立日期</th>
							<th scope="col">查看詳情</th>
							
						</tr>
					</thead>
					
					<tbody class="text-center">
					
					
					<c:forEach var="tktOrder2VO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td># ${tktOrder2VO.tkt_order_no}</td>
								<td><fmt:formatNumber value="${tktOrder2VO.original_price}" type="currency" /></td>
								<td>- ${tktOrder2VO.memCoupVO.coupVO.discount}, ${ tktOrder2VO.memCoupVO.coupVO.coup_name}</td>
								<td class="text-danger"><fmt:formatNumber value="${tktOrder2VO.ttl_price}" type="currency" /></td>
								<td>${tktOrder2VO.orderdate}</td>
								<td>
								<a href="/CGA103G1/tkt_order/tktItem.do?tkt_order_no=${tktOrder2VO.tkt_order_no}" class="btn btn-primary">訂單明細</a>
									<!-- <button type="button" class="btn btn-primary">訂單明細</button> -->
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
      </div>
      <%@ include file="/frontend/tkt_order/pagination.file"%>
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