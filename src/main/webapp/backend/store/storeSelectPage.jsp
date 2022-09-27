<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%--
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="storeSvc" class="com.store.model.StoreService" />


// 取得自己的store所有資訊存放至pageContext
// StoreService storeSvc = new StoreService();

List<StoreVO> storeVO = storeSvc.getAllStore();

pageContext.setAttribute("storeVO", storeVO);
--%>

<!DOCTYPE html>
<html>

	<head>
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->
	</head>

	<body>
		<%@ include file="/backend/loading.file" %> <!-- loading -->
		<%@ include file="/backend/header.file" %> <!-- Header -->
		<%@ include file="/backend/sidebar.file" %> <!-- sidebar -->

		

<!-- 	內容寫在main-content這個div內    -->

<div class="main-content card card-body table-responsive">
<!--  
	<div>
		<div class="nav-item">
			<h4><a href="<%=request.getContextPath()%>/backend/store/storeListAll.jsp">列出所有廠商</a></h4>
		</div>
		
		<div>
			<p>請輸入廠商編號:</p>
			<form method="post" action="<%=request.getContextPath()%>/store.do">
				<input type="text" name="store_no">
				<input type="hidden" name="action" value="getOneStore">
				<input type="submit" value="送出"><font>${errorMsgs.store_no}</font>
			</form>	
		</div>
		<div>
			<p>請輸入廠商帳號:</p>
			<form method="post" action="<%=request.getContextPath()%>/store.do">
				<input type="text" name="store_acc">
				<input type="hidden" name="action" value="getOneStoreByAcc">
				<input type="submit" value="送出"><font>${errorMsgs.store_acc}</font>
			</form>	
		</div>
		
	</div>
		<div>
			<form method="post" action="<%=request.getContextPath()%>/store.do">
				<select size="1" name="store_no">
					<c:forEach var="StoreVO" items="${storeVO}">
						<option value="${StoreVO.store_no}">${StoreVO.store_name}
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="getOneStore">
				<input type="submit" value="送出">
				
			</form>	
		</div>	
	
-->	
		<jsp:useBean id="StoreSvc" scope="page" class="com.store.model.StoreService"/>
		
		<% List<StoreVO> storeVO = StoreSvc.getAllStore();
			pageContext.setAttribute("storeVO", storeVO);
		%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store.do" name="form1">
        <h2><font >查詢:</font></h2><h2 style="color:red;">${errorMsgs.map}</h2> <br>
        <div>
        <p>輸入廠商會員編號:</p>
        <input type="text" name="store_no" value=""><br>
        </div>
        
        <div>   
       <p>輸入廠商帳號:</p>
       <input type="text" name="store_acc" value=""><br>
       </div>
       
       <div>
       <p>輸入店家名稱:</p>
       <input type="text" name="store_name" value=""><br>
    	</div>
    	
    	<div>
       <p>選擇帳號狀態:</p>
       <select  name="acc_status" size="">
       			<option value="">請選擇</option>
    			<option value="0">帳號未啟用</option>
				<option value="1">帳號已啟用</option>
				<option value="2">帳號停權</option>
  		</select><br>
        </div>
        
        <div>   
       <p>加入時間:</p>
	   <input name="store_reg_date" id="f_date1" type="date">
		</div>        
        <input type="submit" value="查詢">
        <input type="hidden" name="action" value="listStoreByCompositeQuery">
        
     </FORM>		
     
</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>