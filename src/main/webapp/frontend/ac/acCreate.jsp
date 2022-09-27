<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ac.model.*"%>


<%
AcVO ac = (AcVO)request.getAttribute("ac");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增文章</title>


	<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>
<body>
	<%-- header --%>
	<%@ include file="/frontend/header.file" %>
	

<%-- ${pageContext.request.contextPath}/createAc multipart/form-data --%>

<%-- <form method="POST" action="" enctype=""> --%>

<section class="pt80 pb80">
<div class="container-fluid">
	<h3>文章標題</h3>
	<span id="titleMsg" style="color: red;"></span>
	<div class="row">
		<div class="col-md-8">
			<div class="form-group">
				<input class="form-control" id="title" placeholder="標題" name="title" value="<%= (ac==null)? "" : ac.getAc_title()%>"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="bg-light py-4 mb-4">
				<h6 style="color:red">${errorMsgs.title}</h6>
			</div>
		</div>
	</div>
	
	
	<h3>文章種類</h3>
	<h6 style="color:red">${errorMsgs.customRadio}</h6>
	<span id="radioMsg" style="color: red;"></span>
	<div class="">
		<select id="customRadio" class="custom-select select-big mb-3 col-5 d-inline-block">
			<option value="0">請選擇</option>
			<option value="1">競技</option>
			<option value="2">運動</option>
			<option value="3">遊記</option>
			<option value="4">交通</option>
			<option value="5">住宿</option>
			<option value="6">景點</option>
			<option value="7">購物</option>
			<option value="8">演場會</option>
			<option value="9">飲食</option>
		</select>
	</div>
	<%-- <div class="custom-control custom-radio" >
		<input type="radio" id="customRadio1" name="customRadio" class="custom-control-input" value="1" >
		<label class="custom-control-label" for="customRadio1">競技</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio2" name="customRadio" class="custom-control-input" value="2"  >
		<label class="custom-control-label" for="customRadio2">運動</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio3" name="customRadio" class="custom-control-input" value="3" >
		<label class="custom-control-label" for="customRadio3">遊記</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio4" name="customRadio" class="custom-control-input" value="4" >
		<label class="custom-control-label" for="customRadio4">行程</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio5" name="customRadio" class="custom-control-input" value="5" >
		<label class="custom-control-label" for="customRadio5">交通</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio6" name="customRadio" class="custom-control-input" value="6">
		<label class="custom-control-label" for="customRadio6">住宿</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio7" name="customRadio" class="custom-control-input" value="7">
		<label class="custom-control-label" for="customRadio7">景點</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio8" name="customRadio" class="custom-control-input" value="8">
		<label class="custom-control-label" for="customRadio8">購物</label>
	</div>
	<div class="custom-control custom-radio">
		<input type="radio" id="customRadio9" name="customRadio" class="custom-control-input" value="9">
		<label class="custom-control-label" for="customRadio9">飲食</label>
	</div> --%>

	<div class="row">
		<div class="col-md-12">
			<div class="bg-light py-4 mb-4">
				<h6 style="color:red">${errorMsgs.content}</h6>
				<h3>文章內容</h3><span id="contentMsg" style="color: red;"></span>
				<div class="form-group">
					<textarea class="form-control" rows="8" placeholder="Example textarea" name="content" id="content"></textarea>			
				</div>	
			</div>
		</div>
	</div>
	
	
	<div class="bg-light py-6 mb-6">	
		<h3>發表時間</h3>
		<input type="text" id="currentDateTime" name="createTime"><br>
	</div>
	
	<script>
  	let today = new Date();
  	mon = ((today.getMonth()+1) < 10 ? "0": "") + (today.getMonth()+1);
  	day = (today.getDate() < 10 ? "0": "") + today.getDate();
  	h = ((today.getHours()) < 10 ? "0": "") + (today.getHours());
  	min = (today.getMinutes() < 10 ? "0": "") + (today.getMinutes());
  	s = (today.getSeconds() < 10 ? "0": "") + (today.getSeconds());
  	let date = today.getFullYear()+'-'+mon+'-'+day;
  	let time = h + ":" + min + ":" + s;
  	let dateTime = date+' '+time;
 	document.getElementById("currentDateTime").value = dateTime;
	</script>
	
	<div class="bg-light py-6 mb-6">
		<h6 style="color:red">${errorMsgs.upload}</h6>
		<span id="imageMsg" style="color: red;"></span>
	    <input type="file" name="upload" value="上傳圖片" id="inputFile" />
	</div>

   	<div class="bg-light py-6 mb-6">
		<input class="btn btn-danger" type="submit" name="action" value="發表" id="submitBtn">
		<h4 style="color: red;" id="msg"></h4>
	</div>
</div>
</section>

<%-- </form> --%>

	<script src="/CGA103G1/frontend/homePage.js"></script>

	<script src="/CGA103G1/frontend/ac/ownJS/createAc.js"></script>


	<%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
</body>
</html>