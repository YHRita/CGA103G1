<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="memSvc" class="com.mem.model.MemService" />

<%
List<MemVO> memList = memSvc.getall();
pageContext.setAttribute("memList", memList);
%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="/backend/commonCSS.file"%>
<!-- CSS -->
<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
}

.container_radio {
	display: flex;
	align-items: center;
}

.center_mb {
	text-align: center;
	width: 115px;
}

.center_mb-1 {
	text-align: center;
	width: 90px;
}
</style>
</head>

<body>
	<%@ include file="/backend/loading.file"%>
	<!-- loading -->
	<%@ include file="/backend/header.file"%>
	<!-- Header -->
	<%@ include file="/backend/sidebar.file"%>
	<!-- sidebar -->

	<!-- 	內容寫在main-content這個div內    -->
	<div class="main-content card card-body table-responsive">
		<table
			class="table card-table default-table display mb-4 dataTablesCard table-responsive-xl"
			id="example4">
			<thead>
				<tr>
					<th>會員</th>
					<th>性別</th>
					<th>電話</th>
					<th>信箱</th>
					<th>地址</th>
					<th>被檢舉記點</th>
					<th>資料修改</th>
					<th style="width: 64; padding-right: 30px; padding-left: 30px;">狀態</th>
					<th class="bg-none"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="memVO" items="${memList}">
					<tr>
						<td>
							<div class="media-bx center_mb">
								<c:choose>
									<c:when test="${memVO.mem_pic != null}">
										<img
											src="<%=request.getContextPath()%>/mem.do?mem_no=${memVO.mem_no}&action=getOnePic">
									</c:when>
									<c:when test="${memVO.mem_pic == null}">
										<img
											src="<%=request.getContextPath()%>/backend/assets/img/nullpic.png"
											class="no-img">
									</c:when>
								</c:choose>
								<div>
									<span class="text-primary">#${memVO.mem_no}</span>
									<h4 class="mb-0 mt-1">
										<a class="text-black">${memVO.mem_name}</a>
									</h4>
								</div>
							</div>
						</td>
						<td>
							<div>${memVO.mem_gender}</div>
						</td>
						<td>
							<div>${memVO.mem_mobile}</div>
						</td>
						<td>
							<div>${memVO.mem_email}</div>
						</td>
						<td>
							<div class="center_mb-1">${memVO.mem_city}${memVO.mem_dist}${memVO.mem_addr}</div>
						</td>
						<td>
							<div class="container">${memVO.mem_report_count}</div>
						</td>
						<td>
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal"
								data-bs-target="#staticBackdrop${memVO.mem_no}">修改</button> <!--修改 Modal -->
							<%
							request.setCharacterEncoding("UTF-8");
							%> <jsp:include page="/backend/mem/updateModal.jsp">
								<jsp:param name="memNo" value="${memVO.mem_no}" />
								<jsp:param name="memName" value="${memVO.mem_name}" />
								<jsp:param name="memCity" value="${memVO.mem_city}" />
								<jsp:param name="memDist" value="${memVO.mem_dist}" />
								<jsp:param name="memAddr" value="${memVO.mem_addr}" />
								<jsp:param name="memGender" value="${memVO.mem_gender}" />
								<jsp:param name="memEmail" value="${memVO.mem_email}" />
								<jsp:param name="memMobile" value="${memVO.mem_mobile}" />
							</jsp:include>
						</td>
						<td><c:choose>
								<c:when test="${memVO.acc_status==0}">
									<i class='bx bxs-circle' style='color: #aaa'></i>未啟用</c:when>
								<c:when test="${memVO.acc_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>已啟用</c:when>
								<c:when test="${memVO.acc_status==2}">
									<i class='bx bxs-circle' style='color: red'></i>停權中</c:when>
							</c:choose></td>
						<td>
							<div class="dropdown dropstart">
								<a class="btn-link" data-bs-toggle="dropdown"
									aria-expanded="false" href="javascript:void(0);"> <img
									src="<%=request.getContextPath()%>/backend/assets/img/selectStatus.png">
								</a>
								<div class="dropdown-menu">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem.do">
										<input class="dropdown-item container" type="submit"
											value="${memVO.acc_status==1 ? '停權' : '啟用' }"> <input
											type="hidden" name="mem_no" value="${memVO.mem_no}">
										<input type="hidden" name="action"
											value="${memVO.acc_status==1 ? 'unupdateStatus' : 'updateStatus' }">
									</FORM>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="/backend/commonJS.file"%>
	<!-- JS -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script>
		$(document).ready(function() {
			$(document).on('shown.bs.modal', function() {
				$(this).find('[autofocus]').focus();
			});
		});
		// header標題
		$("#pagename").text("MEMORY 會員管理");

		function sendRequest(object) {
			let memName = object.elements['memName'].value;
			let memEmail = object.elements['memEmail'].value;
			let memMobile = object.elements['memMobile'].value;
			const namexp = /^[\u4E00-\u9FA5]{2,4}$/;
			const emailxp = /\S+@\S+\.\S+/;
			const mobilexp = /^[0-9]{10}$/;

			if (memName === '' || memEmail === '' || memMobile === '') {
				Swal.fire(
		                "修改作業失敗",
		                "欄位不得為空",
		                "error"
		            );
				return false;
			}else if (!namexp.test(memName)) {
				Swal.fire(
		                "修改作業失敗",
		                "姓名格式錯誤,請重新確認",
		                "error"
		            );
				return false;
			}else if (!emailxp.test(memEmail)) {
				Swal.fire(
		                "修改作業失敗",
		                "Email格式錯誤,請重新確認",
		                "error"
		            );
				return false;
			}else if (!mobilexp.test(memMobile)) {
				Swal.fire(
		                "修改作業失敗",
		                "手機格式錯誤,請重新確認",
		                "error"
		            );
				return false;
			}else
			object.submit();
		}

		function get(id) {
			return document.getElementById(id);
		}
	</script>
</body>
</html>