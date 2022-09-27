<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.system_notification_message.model.*"%>
<!DOCTYPE html>
<html>

<%
SystemNotificationMessageService snmSvc = new SystemNotificationMessageService();
List<SystemNotificationMessageVO> snm = snmSvc.getall();
pageContext.setAttribute("snm", snm);
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


		<h3>系統訊息列表
		<a href="<%=request.getContextPath()%>/backend/system_notification_message/addSNM.jsp">新增</a>
		</h3>
		<table class="table" id="example4">
			<thead>
				<tr>
					<th>編號</th>
					<th>內容</th>
					<th>圖片</th>
					<th>時間</th>
					<th>員工</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="snm" items="${snm}">
				<tr>
					<td>${snm.msg_no}</td>
					<td>${snm.msg}</td>
					<td><img 
					src="<%=request.getContextPath()%>/snm?msg_no=${snm.msg_no}&action=getpic" 
					width="100" height="100"></td>
					<td>${snm.msg_time}</td>
					<td>${snm.emp_no}</td>
					<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/snm"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> 
								<input type="hidden" name="msg_no" value="${snm.msg_no}"> 
								<input type="hidden" name="action" value="delete">
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
			$("#pagename").text("系統訊息管理");
// 	$(document).ready(
// 			function() {
// 				$(document).ready(function() {
// 					   $('#example4').DataTable();
// 			});
	</script>
</body>
</html>