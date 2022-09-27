<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%-- <%@ page import="com.rm_pic.model.*"%> --%>

<jsp:useBean id="rmTypeSvc" scope="page" class="com.rm_type.model.RmTypeService" />
<%-- <jsp:useBean id="rmPicSvc" scope="page" class="com.rm_pic.model.RmPicService" /> --%>

<%
  RmTypeVO rmTypeVO = (RmTypeVO) request.getAttribute("rmTypeVO");
%>


<!DOCTYPE html>
<html> 
	<head>
	<meta charset="UTF-8">
	<title>修改房型 - Memory</title>
	
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css"/>
    	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css"/>
		<%@ include file="/frontend/commonCSS.file" %>
		<style>
		.card {
			background-color: #f7f6f2;
			margin: 45px auto;
		}
		.imgs {
			background-color: #fff;
		}
		.col-form-label {
			font-size: 20px;
			color: #30504F;
			text-align: right;
			padding-top: 13px;
		}
		ul {
			display: inline-block;
		}
		textarea.form-control {
			height: auto;
			font-size: 16px;
		}
		.btn-primary {
    background-color: #5bc9e2;
    padding: 0 20px;
    border-radius: 2px 2px 2px 2px;
    -moz-border-radius: 2px 2px 2px 2px;
    -webkit-border-radius: 12px;
    border: none;
    display: inline-block;
    line-height: 42px;
}
.btn-primary {
    color: #fff;
    background-color: #5bc9e2;
    border-color: #ffcd22;
}
.btn-secondary1 {
    color: #fff;
    border: 2px solid #5bc9e2;
    padding: 2px 20px;
    margin: 60px 60px 0px 60px;
    border-radius: 3px 3px 3px 3px;
    -moz-border-radius: 3px 3px 3px 3px;
    -webkit-border-radius: 12px;
    height: 48px;
    line-height: 39px;
    display: inline-block;
    margin-right: 17px;
    background-color: #5bc9e2;
}
		
	</style>
		
	</head>

	<body>
	
	<%@ include file="/frontend/loading.file" %> <!-- loading -->
	 <%@ include file="/frontend/header.file" %>
	<%@ include file="/frontend/roomSidebar.file" %>
		
		<div class="main-content">
			<a class="btn btn-secondary1 light" href="<%=request.getContextPath()%>/frontend/room/listAllRmType_S.jsp">&lt; 回房型列表</a>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
<!-- 				<font style="color:red">請修正以下錯誤:</font> -->
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
			</c:if>
			<div class="card col-xl-9">
<!-- 				<div class="card-header"></div>                     -->
				<form method="post" action="<%=request.getContextPath()%>/rmtype/rmtype.do" name="updateRmType">
					<div class="card-body d-flex justify-content-center">
						<div class="col-xl-8">
							<div class="row mb-2">
							    <label for="rm_type_no" class="col-sm-3 col-form-label">房型編號</label>
							    <div class="pk col-sm-8">${rmTypeVO.rm_type_no}</div>
							</div>
							<div class="row mb-2">
							    <label for="store_no" class="col-sm-3 col-form-label">廠商編號</label>
							    <div class="pk col-sm-8">${rmTypeVO.store_no}</div>
							</div>
							<div class="row mb-2">
							    <label for="rm_name" class="col-sm-3 col-form-label">房型名稱</label>
							    <div class="col-sm-8">
							    	<input type="text" name="rm_name" class="form-control" id="rm_name" value="${rmTypeVO.rm_name}">
							    </div>
							</div>
							
							<div class="row mb-2">
							    <label for="rm_total" class="col-sm-3 col-form-label">房間數量</label>
							    <div class="col-sm-8">
							    	<input type="text" name="rm_total" class="form-control" id="rm_total" value="${rmTypeVO.rm_total}">
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="rm_people" class="col-sm-3 col-form-label">可入住人數</label>
							    <div class="col-sm-8">
							    	<input type="text" name="rm_people" class="form-control" id="rm_people" value="${rmTypeVO.rm_people}">
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="rm_price" class="col-sm-3 col-form-label">房間價格</label>
							    <div class="col-sm-8">
							    	<input type="text" name="rm_price" class="form-control" id="rm_price" value="${rmTypeVO.rm_price}">
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="rm_area" class="col-sm-3 col-form-label">房型坪數</label>
							    <div class="col-sm-8">
							    	<textarea name="rm_area" rows="3" class="form-control" id="rm_area">${rmTypeVO.rm_area}</textarea>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="rm_intro" class="col-sm-3 col-form-label">房型資訊</label>
							    <div class="col-sm-8">
							    	<textarea name="rm_intro" rows="3" class="form-control" id="rm_intro">${rmTypeVO.rm_intro}</textarea>
							    </div>
							</div>
<!-- 							<div class="row mb-2"> -->
<!-- 							    <label class="col-sm-3 col-form-label">房型狀態</label> -->
<!-- 							    <div class="col-sm-8"> -->
<!-- 								    <select class="mt-2 form-select" id="type_facility" name="type_state"> -->
<%-- 	                                    <option value = true ${rmTypeVO.rm_update == 0 ? 'selected' : '' }>上架</option> --%>
<%-- 	                                    <option value = false ${rmTypeVO.rm_update == １ ? 'selected' : '' }>下架</option> --%>
<!-- 	                                </select> -->
<!--                                 </div>	 -->
<!-- 							</div> -->
						</div>
					</div>
					<div class="mb-3 d-flex justify-content-center align-items-center">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="rm_type_no" value="${rmTypeVO.rm_type_no}">
						<input type="hidden" name="store_no" value="${rmTypeVO.store_no}">
						<button type="submit" class="btn btn-primary col-lg-3">修改</button>
                	</div>
				</form>
			</div>
		</div>
		
		
		
		<%@ include file="/frontend/footer.file" %>
		<%@ include file="/frontend/commonJS.file" %>
		<script type="text/javascript" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js"></script> <!-- 基本JS檔案 -->
		<script>
			$(document).ready(function() {
				$("#pagename").text("新增房型");
			} );
		</script>
	</body>
</html>