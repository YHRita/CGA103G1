<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.rm_pic.model.*"%>

<jsp:useBean id="rmTypeSvc" scope="page" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmPicSvc" scope="page" class="com.rm_pic.model.RmPicService" />

<!DOCTYPE html>
<html>

	<head>
	<meta charset="UTF-8">
	<title>房型列表_廠商 - Memory</title>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css"/>
    	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css"/>
		<%@ include file="/frontend/commonCSS.file" %>
		<!-- 基本CSS檔案 -->
		<style>
	
.btn-primary1 {
    background-color: #5bc9e2;
    padding: 0 20px;
    border-radius: 2px 2px 2px 2px;
    -moz-border-radius: 2px 2px 2px 2px;
    -webkit-border-radius: 12px;
    border: none;
    display: inline-block;
    line-height: 42px;
}
.btn-primary1 {
    color: #fff;
    background-color:#5bc9e2;
    border-color: #ffcd22;
    margin: 20px 20px 10px 60px;
}
.table-responsive1 {
    display: block;
    width: 100%;
    overflow-x: auto;
    padding: 10px 7px;
    -webkit-overflow-scrolling: touch;}
     
.btn-secondary1 {
    color: #FFF;
    border: 2px solid #5bc9e2;
    padding: 0 20px;
    border-radius: 3px 3px 3px 3px;
    -moz-border-radius: 3px 3px 3px 3px;
    -webkit-border-radius: 12px;
    height: 46px;
    line-height: 42px;
    display: inline-block;
    margin-right: 17px;
    background-color: #5bc9e2;
		}
	.state input:checked+label {
	    background-color: #996A4D;
	}
	.state label {
			position: relative;
			display: inline-block;
			width: 42px;
			height: 22px;
			background-color: #E4D6C4;
			border-radius: 25px;
			cursor: pointer;
			box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.3);
			margin-top: 16px;
		}

		.state label:before {
			content: '';
			position: absolute;
			top: 3px;
			left: 4px;
			height: 15px;
			width: 15px;
			background-color: white;
			border-radius: 24px;
			box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
		}
		
		.state input {
			display: none;
		}
		
		.state input:checked+label {
			background-color: #5bc9e2;
		}
		
		.state input:checked+label:before {
			transform: translateX(20px);
			box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
		}
		h3>span {
			color: #30504F;
			font-weight: 700;
		}
		.img-container {
			width: 250px;
			border-radius: 0 30px 0 30px;
			overflow: hidden;
			background-color: #F7F6F2;
		}
		img.no-img {
			padding-left: 20px;
		}
		img {
			max-width: 100%;
		}
		
       
       
  
</style>
	
	</head>

	<body>
	<%@ include file="/frontend/loading.file"%>
	<%@ include file="/frontend/header.file" %>
	<%@ include file="/frontend/roomSidebar.file" %>
		
		<div class="main-content">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="add">
				<a href='<%=request.getContextPath()%>/frontend/room/addRmType_S.jsp'>
					<button type="button" class="btn btn-rounded btn-primary1">
					<span class="btn-icon-start text-primary"><i class='bx bx-plus' style='color:#fff' ></i> </span>新增房型</button>		
					
				</a>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
<!-- 					<div class="modal-content"> -->
<!-- 						<div class="modal-header"> -->
<!-- 							<h3 class="modal-title" id="exampleModalLabel">上下架確認</h3> -->
<!-- 						</div> -->
<!-- 						<div class="modal-body"> -->
<!-- 							<h3>是否要將編號 <span id="no_text"></span> 的房型 <span id="state_text"></span> ?</h3> -->
<!-- 						</div> -->
<!-- 						<div class="modal-footer"> -->
<%-- 							<form method="post" action="<%=request.getContextPath()%>/rmtype/rmtype.do"> --%>
<!-- 								<input type="hidden" id="rm_type_no" name="rm_type_no"> -->
<!-- 								<input type="hidden" id="rm_update" name="rm_update"> -->
<!-- 				     			<input type="hidden" name="action"	value="changeState"> -->
<!-- 				     			<button type="button" class="btn btn-primary1 cancel">取消</button> -->
<!-- 								<button type="submit" class="btn btn-primary1">確定</button> -->
<!-- 			     			</form> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
			</div>	
			
			<div class="table-responsive1">
				<table id="roomTypeTable" class="display default-table" style="min-width: 800px;">
					<thead>
						<tr>
							<th>房型編號</th>
							<th>房型圖片</th>
							<th>廠商編號</th>
							<th>房型名稱</th>
							<th>可入住人數</th>
							<th>房間數量</th>
							<th>價格</th>
							<th>坪數</th>
<!-- 							<th>房型介紹</th> -->
							<th>上下架</th>
							<th>修改狀態</th>
							<th>照片</th>
							<th>修改房型</th>
							<th class="none">房型資訊</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="rmTypeVO" items="${rmTypeSvc.getAllRm()}">
						<tr>
							<td>${rmTypeVO.rm_type_no}</td>
							<td>
							<div class="img-container">
								<c:choose>
									<c:when test="${rmPicSvc.getAllByType(rmTypeVO.rm_type_no).size() > 0}">
										<img src="<%=request.getContextPath()%>/rmPic/rmPic.do?rm_type_no=${rmTypeVO.rm_type_no}&action=showFirstImages">
									</c:when>
									<c:otherwise>
										<img src="<%=request.getContextPath()%>/frontend/assets/images/hotels/noimages.png" class="no-img">
									</c:otherwise>
								</c:choose>
								</div>
							</td>
							<td>${rmTypeVO.store_no}-[${rmTypeVO.storeVO.store_name}]</td>
							<td>${rmTypeVO.rm_name}</td>
							<td>${rmTypeVO.rm_people}人</td>							
							<td>${rmTypeVO.rm_total}</td>
							<td><fmt:formatNumber value="${rmTypeVO.rm_price}"    pattern="$###,###"/></td>
							<td>${rmTypeVO.rm_area} m<sup>2</sup></td>
							
							<td><span class="badge light badge-success">${rmTypeVO.rm_update == 0 ? '已下架' : '已上架'}</span></td>
							<td>	
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmtype/rmtype.do" style="margin-bottom: 0px;">	
						<input type="submit" class="btn btn-outline-secondary" value="${rmTypeVO.rm_update == 0 ? '改為上架' : '改為下架'}">
						<input type="hidden" name="rm_type_no" value="${rmTypeVO.rm_type_no}"> 
						<input type="hidden" name="action" value="${rmTypeVO.rm_update == 0 ? 'no' : 'on'}"></FORM>
						</td>
						<td>
			     		<a class="btn btn-secondary1 btn-sm" href="<%=request.getContextPath()%>/rmPic/rmPic.do?rm_type_no=${rmTypeVO.rm_type_no}&action=getOneForShowImages"><i class='bx bxs-image'></i>查看/新增照片</a>
						</td>
						<td>
						<a class="btn light btn-secondary1" href="<%=request.getContextPath()%>/rmtype/rmtype.do?rm_type_no=${rmTypeVO.rm_type_no}&action=getOneForUpdate"><i class='bx bxs-pencil'></i>修改</a>
						</td>
						<td>${rmTypeVO.rm_intro}</td>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<%@ include file="/frontend/footer.file" %>
		<%@ include file="/frontend/commonJS.file" %>
		<script type="text/javascript" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js"></script>
		
		<script>
			$(document).ready(function() {
				$("#pagename").text("房型列表");
			    $("#roomTypeTable").DataTable( {
			    	"responsive": true,
			    	"lengthMenu": [3, 5, 10],
			        "language": {
			        	"processing": "處理中...",
			            "loadingRecords": "載入中...",
			            "lengthMenu": "顯示 _MENU_ 項結果",
			            "zeroRecords": "沒有符合的結果",
			            "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
			            "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
			            "infoFiltered": "(從 _MAX_ 項結果中過濾)",
			            "infoPostFix": "",
			            "search": "搜尋",
			            "searchPlaceholder": "...",
			            "paginate": {
			                "first": "第一頁",
			                "previous": "上一頁",
			                "next": "下一頁",
			                "last": "最後一頁"
			            },
			        }
			    } );
			    $(".cancel").click(function(){
			    	$("#staticBackdrop").modal('hide');
			    });
			} );
		</script>
	</body>
</html>