<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ac.model.*"%>
<%-- <jsp:useBean id="acService" class="com.ac.model.AcServiceImpl" /> --%>

<%
	AcServiceImpl acServiceImpl = new AcServiceImpl();
    List<AcVO> aclist = acServiceImpl.getAll();
    pageContext.setAttribute("aclist",aclist);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區文章列表</title>

	<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>

</head>
<body>
	<%-- header --%>
	<%@ include file="/frontend/header.file" %>

<!-- <form method="POST" action="CreateAc"> -->
<a class="btn btn-grad btn-xs" href="acCreate.jsp"><i class="fas fa-arrow-right"></i>發表文章</a>
<a class="btn btn-grad btn-xs" href="acSelfCardPage.jsp"><i class="fas fa-arrow-right"></i>自己的文章列表</a>
<!-- </form> -->
<section class="Blog-list pt80 pb80">
  <div class="container">
    <div class="row">
    	<c:if test="${empty aclist}">
    		<h1 class="text-center" style="color: red;">目前沒有文章</h1>
    	</c:if>     
    	<c:forEach var="acVO" items="${aclist}">
	      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 mb-4">
	        <div class="card shadow border-0 h-100">
	        <a href="<%=request.getContextPath()%>/getOneAc?action=acInner&acNo=${acVO.ac_no}">
	        <img src="<%=request.getContextPath()%>/getOneAcImage?action=acImgList&acNo=${acVO.ac_no}" alt="jpg" class="img-fluid card-img-top">
	        </a>
	          <div class="card-body">
	            <h5 class="my-2"><a href="<%=request.getContextPath()%>/getOneAc?action=acInner&acNo=${acVO.ac_no}" class="text-dark">${acVO.ac_title}</a></h5>
	            <p class="text-gray-500 text-sm my-3"><i class="far fa-clock mr-2"></i>${acVO.ac_time}</p>
	            <p class="my-2 text-muted text-sm">${acVO.ac_content}</p>
	            <a href="<%=request.getContextPath()%>/getOneAc?action=acInner&acNo=${acVO.ac_no}" class="btn btn-link pl-0">Read more<i class="fa fa-long-arrow-alt-right ml-2"></i></a> 
	           </div>
	        </div>
	      </div>
      	</c:forEach>
	      
    </div>
  </div>
  
</section>

	<script src="/CGA103G1/frontend/homePage.js"></script>

	<%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
	
</body>
</html>