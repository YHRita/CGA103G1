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
<%@ include file="/frontend/commonCSS.file"%>
</head>

<body>
<%@ include file="/frontend/header.file"%>
	<!-- =======================
<%-- header --%>
 
  <!-- =======================
	header End-->
	<section class="login-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="login-box">
						<div class="login-top">
							<h3>歡迎回來</h3>
						</div>
						<form class="login-form" action="loginhandler" method="post">
							<div class="row">
								<div class="col-md-12 email">
									<label>信箱</label> <input type="text" name="mem_email"
										placeholder="Enter your email here">
								</div>
								<div class="col-md-12 password">
									<label>密碼</label> <input type="password" name="mem_pwd"
										placeholder="Enter password">
								</div>

								<div class="forget-btn">
									<a href="forgetPassword.jsp">忘記密碼?</a>
								</div>
								<div class="forget-btn">
									<a href="addMem.jsp">註冊帳號</a>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" name="button">登入</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- 主內容 end -->

	<%-- footer --%>
<%@ include file="/frontend/footer.file"%>
	<%-- commonJS --%>
<%@ include file="/frontend/commonJS.file"%>
</body>

</html>