<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員訊息</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>
  <!-- mem-sidebar -->
  <%@ include file="/frontend/memSidebar.file" %>
	
      <!-- 主要內容 Start -->

      <div class="col-lg-9">
        <!-- h5 改 h3 -->
        <h3 class="mt-5 mb-3">會員訊息</h3>
        <!-- 這塊刪掉放內容Start -->
        <blockquote class="blockquote bg-dark" cite="#">
          <h3 class="mb-2 ">這塊刪掉放內容</h3>
        </blockquote>
        <!-- 這塊刪掉放內容 end -->
      </div>
      
      
      
      
      
      
      
      
      
      
    </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>

</body>
</html>