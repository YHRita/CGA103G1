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
<title>修改文章</title>


	<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>
<body>
	<%-- header --%>
	<%@ include file="/frontend/header.file" %>


<form method="POST" action="${pageContext.request.contextPath}/updateAc" enctype="multipart/form-data">

<section class="pt80 pb80">
<div class="container-fluid">
	<h3>文章編號：
	<input class="form-control" placeholder="文章編號" name="no" value="${param.no}"/></h3>
	<h3>文章標題</h3>
	<div class="row">
		<div class="col-md-8">
			<div class="form-group">
				<input class="form-control" placeholder="標題" name="title" value="${param.title}"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="bg-light py-4 mb-4">
				<h6 style="color:red">${errorMsgs.title}</h6>
			</div>
		</div>
	</div>
	
	
	<h3>文章種類</h3> 	${param.type}
	<h6 style="color:red">${errorMsgs.customRadio}</h6>
	<div class="custom-control custom-radio" >
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
	</div>
	
	<script>
	let typeNumArr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
	let customRadio = document.getElementsByName("customRadio");
	for (let i = 0; i < typeNumArr.length; i++) {
	    if(${param.type} === typeNumArr[i]){
	        customRadio[i].checked = true;
// 	        ${param.type} = i;
	        break;
	    }
	}
	</script>
	
	

	<div class="row">
		<div class="col-md-12">
			<div class="bg-light py-4 mb-4">
				<h6 style="color:red">${errorMsgs.content}</h6>
				<h3>文章內容</h3>
				<div class="form-group">
					<textarea class="form-control" rows="8" placeholder="Example textarea" name="content" >${param.content}</textarea>			
				</div>	
			</div>
		</div>
	</div>
	
	
	<div class="bg-light py-6 mb-6">	
		<h3>發表時間</h3>
		<input type="text" id="currentDateTime" name="updateTime"><br>
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
	    <input type="file" name="upload" value="上傳圖片" />
	</div>
    
   	<div class="bg-light py-6 mb-6">
<%--    		<a href="<%=request.getContextPath()%>/updateAc?action=finalAlter&acNo=${acVO.ac_no}">   	 --%>
		<input class="btn btn-danger" type="submit" name="action" value="確定修改">
<!-- 	              		 </a> -->
	</div>
</div>
</section>

</form>

	<script src="/CGA103G1/frontend/homePage.js"></script>


	<%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
</body>
</html>