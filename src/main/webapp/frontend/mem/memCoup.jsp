<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem_coup.model.*"%>

<% 

MemVO memVO = new MemVO();
memVO = (MemVO)request.getSession().getAttribute("memVO");
Integer mem_no = memVO.getMem_no();


MemCoupService memCoupSrv = new MemCoupService();
List<MemCoupVO> list = memCoupSrv.listOneMemCoupon(mem_no);
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員優惠券</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>
  <!-- mem-sidebar -->
  <%@ include file="/frontend/memSidebar.file" %>
	
      <!-- 主要內容 Start -->

      <div class="col-lg-9">
        <!-- 內容Start -->
        <div class="col-sm-12 col-md-11 mb-5">
        <h3 class="mt-5 mb-3">優惠券列表</h3>
			<!-- <ul class="nav nav-tabs nav-justified">
				<li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#tab-3-1"> 全部(20) </a> </li>
				<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-3-2"> 尚未使用(12) </a> </li>
				<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-3-3"> 即將到期(3) </a> </li>
				<li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-3-4"> 已使用(7) </a> </li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane show active" id="tab-3-1">
	
					全部優惠券
				</div>
				
				
				
				<div class="tab-pane" id="tab-3-2">
					尚未使用
				</div>
				<div class="tab-pane" id="tab-3-3">
					 即將到期
				</div>
				<div class="tab-pane" id="tab-3-4">
					 已使用
				</div>
			</div> -->
			
			<%@ include file="/frontend/mem/pageIndex.file"%>
			<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">優惠券名稱</th>
							<th scope="col">折抵金額</th>
							<th scope="col">到期日</th>
							<th scope="col">狀態</th>
						</tr>
					</thead>
					
					<tbody>
					
					
					<c:forEach var="memCoupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:if test="${memCoupVO.coupVO.status == 1}">
							<tr>
								<td>${memCoupVO.coupVO.coup_name}</td>
								<td>${memCoupVO.coupVO.discount}</td>
								<td>${memCoupVO.coupVO.enddate}</td>
								<td>
									<c:if test="${memCoupVO.coup_state == 0 }">
										<span class="badge badge-danger light">未使用</span>
									</c:if>
									<c:if test="${memCoupVO.coup_state == 1 }">
										<span class="badge badge-primary light">已使用</span>
									</c:if>
									<c:if test="${memCoupVO.coup_state == 2 }">
										<span class="badge badge-info light">已過期</span>
									</c:if>
								</td>
							</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
				<%@ include file="/frontend/mem/pagination.file"%>
		</div>
        
      </div>
      
      
      
      
      
      
      
      
      
      
    </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>

</body>
</html>