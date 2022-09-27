<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
MemService memSvc = new MemService();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員基本資料管理</title>
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
        <!-- h5 改 h3 -->
        <h3 class="mt-5 mb-3">會員基本資料管理</h3>
        <div>
<h2>${memVO.mem_name}</h2>您好
</div>
		
		
		<div class="col-sm-12 mb-5">
					<h5 class="text-center mb-4"></h5>
					<div class="table-responsive-sm">
						<table class="table table-lg table-bordered table-striped">
							<thead>
								<tr>
									<th scope="col">Name</th>
									<th scope="col">${memVO.mem_name}</th>
					
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">Sex</th>
									<td>${memVO.mem_gender}</td>
								
								</tr>
								<tr>
									<th scope="row">Email</th>
									<td>${memVO.mem_email}</td>
								
								</tr>
								<tr>
									<th scope="row">Password</th>
									<td>${memVO.mem_pwd}</td>
								
								</tr>
								<tr>
									<th scope="row">Mobile</th>
									<td>${memVO.mem_mobile}</td>
								
								</tr>
								<tr>
									<th scope="row">Address</th>
									<td>${memVO.mem_city}</td>
								</tr>

							</tbody>
						</table>
						<form action="<%=request.getContextPath()%>/mem.do" method="post" name="goto_update" enctype="multipart/form-data">
							<input class="btn btn-outline-primary" type="submit" value="修改基本資料"> 
							<input name="mem_no" type="hidden" value="${memVO.mem_no }"> <input name="action" type="hidden" value="getOne_For_Update">
								</form>
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