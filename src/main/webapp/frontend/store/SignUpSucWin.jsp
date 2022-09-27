<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%

%>
<!--  
StoreVO storeVO = (StoreVO)request.getAttribute("storeVO"); 
-->

<!DOCTYPE html>
<html>

	<head>
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js"></script>
		
		<script src="/frontend/tw-city-selector.js"></script>
		
		<style>
			select,.zipcode{
				  width: 33%;
				  display: inline-block;
				  height: calc(1.5em + .75rem + 2px);
				  padding: .375rem .75rem;
				  font-size: 1rem;
				  font-weight: 400;
				  line-height: 1.5;
				  color: #495057;
				  background-color: #fff;
				  background-clip: padding-box;
				  border: 1px solid #ced4da;
				  border-radius: .25rem;
				  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
				}
		
		</style>
		 <meta http-equiv="refresh" content="3;url=<%=request.getContextPath()%>/frontend/store/storeLogin.jsp">
	</head>

	<body>
		<%@ include file="/backend/loading.file" %> <!-- loading -->
		<%@ include file="/backend/header.file" %> <!-- Header -->
		

		
	
<!-- 	內容寫在main-content這個div內    -->
<div class="main-content card card-body table-responsive" >

	<div>
		<div><h1>註冊成功，請重新登入</h1><div>
		<div>
		<a href="<%=request.getContextPath()%>/frontend/store/StoreLogin.jsp">如沒反應請按此到登入畫面</a>
		</div>
	</div>

</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY");
		</script>
		<script>
		  new TwCitySelector();
		</script>
	</body>
</html>