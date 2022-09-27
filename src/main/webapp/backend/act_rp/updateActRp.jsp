<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act_rp.model.*" %>
<!DOCTYPE html>
<html>
<% 
ActRpVO actRpVO = (ActRpVO) request.getAttribute("actRpVO");
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
		<h3>檢舉揪團</h3>
		<div id="error">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/actRp"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>檢舉編號:</td>
					<td><%=actRpVO.getAct_rp_no()%></td>
				</tr>
				<tr>
					<td>活動編號:</td>
					<td><%=actRpVO.getAct_no()%></td>
				</tr>
				<tr>
					<td>檢舉事由:</td>
					<td><%=actRpVO.getAct_rp_reason()%></td>
				</tr>
				<tr>
					<td>員工編號:</td>
					<td><input name="emp_no"></td>
				</tr>
				<tr>
					<td>檢舉狀態:</td>
					<td><select name="act_rp_status">
							<option value="0" disabled selected>未處理</option>
							<option value="1">檢舉成功</option>
							<option value="2">檢舉失敗</option>
					</select></td>
				</tr>
				<tr>
					<td>
						<hr> 
						<input type="hidden" name="act_rp_no" value="${actRpVO.act_rp_no}">
						<input type="hidden" name="act_no" value="${actRpVO.act_no}">
						<input type="hidden" name="act_rp_reason" value="${actRpVO.act_rp_reason}">
						<input type="hidden" name="action" value="update">
						<input type="submit" value="送出檢舉">
					</td>
				</tr>
			</table>

		</FORM>

	</div>
	<%@ include file="/backend/commonJS.file"%>
	<!-- 基本JS檔案 -->
	<script>
		$("#pagename").text("揪團檢舉管理");
	</script>
</body>
</html>