<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport" />

    <!-- Touch Icons -->
   	<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
    <title>
        會員中心 - 首頁
    </title>
</head>

<style>
    footer {
    	width: 100%;       
     }

</style>


<body>
    <!-- header -->
	<%@ include file="/frontend/header.file" %>

  	<!-- Main Banner -->
<%-- 	<%@ include file="/frontend/homePageBanner.file" %>  --%>
    <!-- Main banner -->
    
    <!-- 主內容 start -->
 	<%@ include file="/frontend/memSidebar.file" %>


            <!-- 主要內容 Start -->
            <div class="col-lg-9">


            </div>
            <!-- 主內容 end -->

            <!--  footer  -->

			<%@ include file="/frontend/footer.file" %>

            <!-- footer  -->
            
            <%@ include file="/frontend/commonJS.file" %>


        </div>
    </div>
</body>

</html>