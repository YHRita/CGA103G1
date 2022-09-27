<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade mar" id="staticBackdrop${param.memNo}"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title" id="staticBackdropLabel">會員資料修改</h2>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<!-- <%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message.value}</li>
						</c:forEach>
					</ul>
				</c:if> -->
				
				<form id="updateMemForm${param.memNo}" method="post"
					action="<%=request.getContextPath()%>/mem.do">

					<label id="memNameLabel" for="memName">姓名</label> <input
						class="form-control form-control-lg" type="text"
						id="updateMemName" name="memName" value="${param.memName}"
						autofocus>
					<label id="memGenderLabel" for="memGender">性別</label><br>
					<div class="form-control form-control-lg container_radio">
						<input type="radio" name="memGender" value="男" ${param.memGender=='男' ? 'Checked' : ''}/>男 
						<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="radio" name="memGender" value="女" ${param.memGender=='女' ? 'Checked' : ''}/>女
					</div>
					<label id="memAccLabel" for="memAcc">地址</label>
					<div class="form-control-lg container_radio">
					<input class="form-control form-control-lg" type="text"
						id="updateMemAcc" name="memCity" value="${param.memCity}" autofocus>-
					<input class="form-control form-control-lg" type="text"
						id="updateMemAcc" name="memDist" value="${param.memDist}" autofocus>-
					<input class="form-control form-control-lg" type="text"
						id="updateMemAcc" name="memAddr" value="${param.memAddr}" autofocus>
					</div>
					<label id="memEmailLabel" for="memEmail">信箱</label> <input
						class="form-control form-control-lg" type="text"
						id="updateMemEmail" name="memEmail" value="${param.memEmail}"
						autofocus> <label id="memMobileLabel" for="memMobile">手機</label>
					<input class="form-control form-control-lg" type="text"
						id="updateMemMobile" name="memMobile" value="${param.memMobile}"
						autofocus> <input type="hidden" name="memNo"
						value="${param.memNo}"> <input type="hidden" name="action"
						value="updateMemB">
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="sendRequest(get('updateMemForm${param.memNo}'));">確定</button>
			</div>
		</div>
	</div>
</div>