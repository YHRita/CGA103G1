<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<meta http-equiv="refresh" content="3;url=<%=request.getContextPath()%>/frontend/store/storeLogin.jsp">
	<style>
		*{
			box-sizing: border-box;
		}
		
		div.loginSection{
			width: auto;
			margin: 50px 100px;
		}
		h1{
			margin-bottom: 50px;
		}
		form.accPass{
		
		}
		
	</style>	
	
</head>

<body>
	<!-- header -->
	<%@ include file="/frontend/header.file" %>
	
<div class="loginSection">	
	<section class="login-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="login-box">
                            <div class="login-top">
                                <h3>密碼寄送成功!將回到登入畫面</h3>
                                <p><a href="<%=request.getContextPath()%>/frontend/store/storeLogin.jsp">請按此回登入頁面</a></p>
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