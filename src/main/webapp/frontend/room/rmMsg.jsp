<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.rm_msg.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.util.*" %>
<% Rm_msgService RmMsgSvc = new Rm_msgService();
	List<Rm_msgVO> RmMsgVO = RmMsgSvc.getAll();
	pageContext.setAttribute("RmMsgVO", RmMsgVO);
	
	StoreService StoreSvc = new StoreService();
	List<StoreVO> StoreVO = StoreSvc.getAllStore();
	pageContext.setAttribute("StoreVO", StoreVO);
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>美陌旅</title>

		<%-- CSS --%>
		<%@ include file="/frontend/commonCSS.file" %>
		
	<style>
		*{
			box-sizing: border-box;
		}
		
		div.loginSection{
			width: 60%;
			margin: 10px 10px;
		}
		h1{
			margin-bottom: 50px;
		}
		form.accPass{
		
		}
		
	</style>
	
		<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->	

</head>

<body>
	<!-- header -->
		
		<%@ include file="/frontend/header.file"%>
		<%@ include file="/frontend/store/storeSidebar.file" %> <!-- sidebar -->
	

<!-- =======================
	Banner innerpage -->
<div class="loginSection">
<section class=" login-area">
  <div class="container">
    <div class="row">
      
      <div class="col-lg-9 col-md-6 col-sm-11">
        <div class="row">
          <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="login-box Booking-box">
              <div class="login-top">
                <h3>您的資料</h3>
                <p>請留下您的個人資料</p>
              </div>
              <form class="login-form" action="<%=request.getContextPath() %>/RmMsgServlet">
                <div class="row">
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>請輸入您的帳號</label>${errorMsgs.mem_acc}
                    <input type="text" name="mem_acc" placeholder="帳號">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>請選擇入住的飯店或民宿名稱</label>${errorMsgs.store_no}
	                    <select size="1" name="store_no">
                       	<c:forEach  var="StoreVO" items="${StoreVO}">
	                    	<option value="${StoreVO.store_no}">${StoreVO.store_name}</option>
	                 	</c:forEach>  
	                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                  	<label>請輸入您的意見</label>${errorMsgs.rm_msg_reason}
                  	<div>
                  		<textarea  name="rm_msg_reason" placeholder="請輸入您的意見(200字內)" rows="4" cols="50"></textarea>
                  	</div>
                  </div>
                  


                  <div class="col-md-12">
                    <button class="Confirm" type="submit" name="action" value="rmMsgSend">送出</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</div>    
  
<!-- 額外Section 區域  -->
 


<!-- 額外Section 區域  end-->


    <!-- Search -->
    <section class="bg-light pattern-overlay-1-dark">
      <div class="container">
        <div class="col-md-12 col-lg-9 mx-auto p-4 p-sm-5">
          <div class="text-center px-0 px-sm-5">
            <p class="mb-3 lead">想尋找其他東西嗎？</p>
            <form>
              <div class="input-group">
                <input
                  class="form-control border-radius-right-0 border-right-0"
                  type="text"
                  name="search"
                  placeholder="What are you looking for?"
                />
                <button
                  type="button"
                  class="btn btn-grad border-radius-left-0 mb-0"
                >
                  Search
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
    <!-- Search -->

  	<!--  footer  -->
	<%@ include file="/frontend/footer.file" %>
  	<!--  footer  -->

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<%@ include file="/frontend/commonJS.file" %>

</body>
</html>