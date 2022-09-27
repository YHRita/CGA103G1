<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>會員登入</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>

<body>

  <!-- =======================
<%-- header --%>
  <%@ include file="/frontend/header.file" %>
 
  <!-- =======================
	header End-->
<div class="main">
  <div>請至您註冊的信箱收取驗證碼 並輸入驗證碼完成會員驗證</div>
   <div><a href="<%=request.getContextPath()%>/homePage.jsp">回首頁</a></div>
    <div><a href="">回會員中心</a></div>
    <h2>輸入驗證碼</h2>
    <form method="post" action="<%=request.getContextPath()%>/checkMember" role="form">
      <div class="col-md-9 col-sm-12">
        <div class="form-group form-group-lg">
          <input type="text" class="form-control col-md-6 col-sm-6 col-sm-offset-2" name="authcode" required>
          <input class="btn btn-primary btn-lg col-md-2 col-sm-2" type="submit" value="驗證">
        </div>
      </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<c:if test="${authSuccess == 'authSuccess'}">

<script>
swal("驗證成功","回首頁或會員中心","success");
</script>	
</c:if>
    
  </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>

</body>

</html>