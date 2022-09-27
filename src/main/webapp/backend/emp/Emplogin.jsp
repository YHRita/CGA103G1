<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="empSvc" class="com.emp.model.EmpService" />

<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>


<!DOCTYPE html>
<html>
<head>
<title>員工登入</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />


<%@ include file="/backend/commonCSS.file"%>


<style>
table.fold-table tbody tr.view {
	cursor: pointer;
}

table.fold-table tbody tr.view:hover {
	box-shadow: 0 0.125rem 1rem rgb(0 0 0/ 19%);
}

table.fold-table tbody tr.view.open {
	background: #8FC2C2;
}

table.fold-table tbody tr.view.open td {
	color: white;
}

table.fold-table tbody tr.fold {
	display: none;
}

table.fold-table tbody tr.fold.open {
	display: table-row;
}

table {
	width: 90%;
}

table.fold-table>thead>tr>th {
	align: center;
	font-size: 1.125rem;
	text-transform: capitalize;
	font-weight: 600;
	padding: 1.25rem 0.9375rem;
}

thead {
	background: #F7F6F2;
}

td, div {
	font-size: 1rem;
	letter-spacing: 0.5px;
}

/* coup css */
.coup-list-h1 {
	margin-right: 2rem;
}

.input-mr {
	margin-right: 1rem;
}

.input-mb {
	margin-bottom: 1rem;
}

.error-list-mb {
	margin-bottom: 0.6rem;
}
</style>

</head>
<body>

	<%@ include file="/backend/loading.file"%>
<body class="vh-100">
	<div class="authincation h-100">
		<div class="container h-100">
			<div class="row justify-content-center h-100 align-items-center">
				<div class="col-md-6">
					<div class="authincation-content">
						<div class="row no-gutters">
							<div class="col-xl-12">
								<div class="auth-form">
									<div class="text-center mb-3">
										<a href="index.html"><img src="images/logo-full.png"
											alt=""></a>
									</div>
									<h1 class="text-center mb-4">員工登入</h1>
									<form action="EmpLogin" method="post">
										<div class="mb-3">
											<label class="mb-1"><strong>account</strong></label> <input
												type="text" name="emp_acc" class="form-control"
												value="jackypao">
										</div>
										<div class="mb-3">
											<label class="mb-1"><strong>Password</strong></label> <input
												type="password" name="emp_pwd" class="form-control"
												value="vwxyz">
										</div>
										<div class="row d-flex justify-content-between mt-4 mb-2">
											<div class="mb-3">
												<div class="form-check custom-checkbox ms-1">
													<input type="checkbox" class="form-check-input"
														id="basic_checkbox_1"> <label
														class="form-check-label" for="basic_checkbox_1">Remember
														my preference</label>
												</div>
											</div>
											<div class="mb-3">
												<a href="page-forgot-password.html">Forgot Password?</a>
											</div>
										</div>
										<div class="text-center">
											<button type="submit" class="btn btn-primary btn-block">登入</button>
										</div>
									</form>
									<div class="new-account mt-3">
										<p>
											Don't have an account? <a class="text-primary"
												href="./page-register.html">Sign up</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/backend/commonJS.file"%>

</body>
</html>