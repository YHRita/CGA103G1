<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act_rp.model.*"%>
<!DOCTYPE html>
<html>

<%
ActRpService arSvc = new ActRpService();
List<ActRpVO> actRp = arSvc.getall();
pageContext.setAttribute("actRp", actRp);
%>
<head>
<%@ include file="/backend/commonCSS.file"%>
</head>

<body>
	<%@ include file="/backend/loading.file"%>
	<!-- loading -->
	<%@ include file="/backend/header.file"%>
	<!-- Header -->
	<%@ include file="/backend/sidebar.file"%>
	<!-- sidebar -->

	<div class="main-content">



		<h3>揪團檢舉管理</h3>
		<table class="table" id="example4">
			<thead>
				<tr>
					<th>檢舉編號</th>
					<th>會員編號</th>
					<th>活動編號</th>
					<th>檢舉事由</th>
					<th>檢舉內容</th>
					<th>檢舉時間</th>
					<th>員工編號</th>
					<th>完成時間</th>
					<th>處理狀態</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="actRpVO" items="${actRp}">
					<tr>
						<td>${actRpVO.act_rp_no}</td>
						<td>${actRpVO.mem_no}</td>
						<td>${actRpVO.act_no}</td>
						<td>${actRpVO.act_rp_reason}</td>
						<td>${actRpVO.act_rp_content}</td>
						<td>${actRpVO.act_rp_time}</td>
						<td>${actRpVO.emp_no}</td>
						<td>${actRpVO.act_rp_done_time}</td>
						<td>${actRpVO.act_rp_status}</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRp"
								style="margin-bottom: 0px;">
								<input type="submit" value="處理">
								<input type="hidden" name="act_rp_no" value="${actRpVO.act_rp_no}">
								<input type="hidden" name="action" value="getone">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>


	<%@ include file="/backend/commonJS.file"%>
	<!-- 基本JS檔案 -->
	<script>
		$("#pagename").text("揪團檢舉管理");
	</script>
</body>
</html>