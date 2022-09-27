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


<!-- CSS -->
<%@ include file="/frontend/commonCSS.file"%>
</head>

<body>
	<%@ include file="/frontend/header.file"%>
	<%@ include file="/frontend/memSidebar.file"%>
	<div class="col-lg-9 mb-5">
		<h3 class="mt-5 mb-3">會員票券訂單查看 - Memory</h3>

<%@ include file="/frontend/tkt_order/pageIndex.file"%>

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
								<a href="/CGA103G1/tkt_order/tktItemMem.do?tkt_order_no=${tktOrder2VO.tkt_order_no}" class="btn btn-primary">訂單明細</a>
									<!-- <button type="button" class="btn btn-primary">訂單明細</button> -->
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				 <%@ include file="/frontend/tkt_order/pagination.file"%>
	</div>
	</div>
	</div>

	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>


</body>
</html>