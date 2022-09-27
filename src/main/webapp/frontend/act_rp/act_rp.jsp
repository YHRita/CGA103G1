<%@page import="com.act_rp.model.ActRpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act_rp.model.*"%>
<%@ page import="com.act.model.*"%>

<jsp:useBean id="act_rpSvc" class="com.act_rp.model.ActRpService" />

<%
ActRpVO actRpVO = (ActRpVO) request.getAttribute("actRpVO");
%>
<%
ActVO actVO = (ActVO) request.getAttribute("actVO");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>美陌旅</title>

<%-- CSS --%>
<%@ include file="/frontend/commonCSS.file"%>

<style>
h3 {
	margin-left: 400px;
}

table {
	width: 550px;
	background-color: white;
	margin-top: 10px;
	margin-left: 400px;
	margin-bottom: 100px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

div#error {
	margin-left: 400px;
	margin-top: 10px;
}
</style>



</head>
<body>
	<!-- header -->
	<%@ include file="/frontend/header.file"%>

	<div class="main-content">
		<hr>
		<br> <br>
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
					<td>活動編號:</td>
					<td><input type="text" name="act_no"></td>
				</tr>
				<tr>
					<td>活動檢舉事由:</td>
					<td><select name="act_rp_reason">
							<option value="" ></option>
							<option value="活動內容與標題不符">活動內容與標題不符</option>
							<option value="活動內容違反善良風俗">活動內容違反善良風俗</option>
							<option value="騷擾行為">騷擾行為</option>
							<option value="非主辦人出席宣傳之營利廣告">非主辦人出席宣傳之營利廣告</option>
							<option value="活動包含詐騙內容">活動包含詐騙內容</option>
							<option value="其他">其他</option>
					</select></td>
				</tr>
				<tr>
					<td>檢舉文字內容:</td>
					<td><textarea name="act_rp_content" rows="3" cols="30"></textarea></td>
				</tr>
				<tr>
					<td>
						<hr> <input type="hidden" name="action" value="insert">
						<input type="submit" value="送出檢舉">
					</td>
				</tr>
			</table>

		</FORM>

	</div>

	<!--  footer  -->
	<%@ include file="/frontend/footer.file"%>
	<!--  footer  -->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<%@ include file="/frontend/commonJS.file"%>


</body>
</html>