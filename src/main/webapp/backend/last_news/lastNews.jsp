<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.last_news.model.*"%>
<!DOCTYPE html>
<html>

<%
LastNewsService lnSvc = new LastNewsService();
List<LastNewsVO> lastNews = lnSvc.getLastNewsAll();
pageContext.setAttribute("lastNews", lastNews);
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



		<h3>
			消息列表 <a
				href="<%=request.getContextPath()%>/backend/last_news/addNews.jsp">新增</a>
		</h3>
		<table class="table" id="example4">
			<thead>
				<tr>
					<th>#</th>
					<th>文字</th>
					<th>圖片</th>
					<th>時間</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lastNewsVO" items="${lastNews}">
					<tr>
						<td>${lastNewsVO.news_no}</td>
						<td>${lastNewsVO.news}</td>
						<td><img
							src="<%=request.getContextPath()%>/lastNews?news_no=${lastNewsVO.news_no}&action=getOnePic"
							width="100" height="100"></td>
						<td>${lastNewsVO.news_time}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/lastNews"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> 
								<input type="hidden" name="news_no" value="${lastNewsVO.news_no}"> 
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
	$("#pagename").text("最新消息管理");
// 		$(document).ready(
// 				function() {
// 					$(document).ready(function() {
// 						   $('#example4').DataTable();
// 				});
	</script>
</body>
</html>