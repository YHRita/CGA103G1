<%@page import="com.ac.model.AcVO"%>
<%@page import="com.ac.model.AcServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ac.model.*"%>

<%

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章詳細頁</title>

 	<script src="<%=request.getContextPath()%>/frontend/assets/js/jquery.min.js" type="text/javascript"></script>

	<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>

</head>
<body>
	<%-- header --%>
	<%@ include file="/frontend/header.file" %>
	
	<section class="Blog-list pt80 pb80 blog-single-section">
  <div class="container">
    <div class="row">
		<div class="col-lg-8 col-md-12 col-xs-12">
	        <div class="blog-content">
	          <div class="post format-standard-image">
	            <div class="entry-media"> <img src="<%=request.getContextPath()%>/getOneAcImage?action=acImgList&acNo=${acVO.ac_no}" alt="圖片"></div>
					<ul class="entry-meta">
						<li><i class="far fa-clock"></i>${acVO.ac_time}</li>
						<li id="acType" value="${acVO.ac_type_no}"><i class="fas fa-comments"></i></li>              
					</ul>
					<h2>${acVO.ac_title}</h2>
	            <div id="content">
		            <p>${acVO.ac_content}</p>
	            </div>
	          </div>
          
	          <div class="author-box">
	            <div class="author-content"><a href="#" class="author-name"></a></div>
	          </div>
	        
	        </div>
		</div>
	      
	      <div class="col-lg-4 col-md-12 col-xs-12">
	        <div class="blog-sidebar">
	          <div class="widget">
	            <h3>修改文章內容</h3>
	            <form method="get" >
	              <h1 id="errorMsgs" style="color: red;">${errorMsgs.Msgs}</h1>
	              <div>
              		<a href="<%=request.getContextPath()%>/updateAc?action=alterAc&acNo=${acVO.ac_no}"
              		class="btn btn-success" >
              		修改文章
              		 </a>
	              </div>
	            </form>
	          </div>
	      </div>
	   	</div>
 	</div>
  </div>
</section>

	<script src="/CGA103G1/frontend/homePage.js"></script>
	
	<script type="text/javascript">
		let typeArr = ["競技", "運動", "遊記", "行程", "交通", "住宿", "景點", "購物", "飲食"]
		let acType = document.getElementById("acType")
	
		for (let i = 0; i < typeArr.length; i++) {
		    if(acType.value-1 == i){
		        acType.innerHTML = typeArr[i];
		        break;
		    }else{
		        acType.innerHTML = "無此種類";
		    }
		}
						
	</script>

	<%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
	
</body>
</html>