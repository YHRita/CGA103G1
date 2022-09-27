<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_pic.model.*"%>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新增/查看 房型照片 - Memory</title>
	
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css"/>
    	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css"/>
		<%@ include file="/frontend/commonCSS.file" %> <!-- 基本CSS檔案 -->
		<style>
		.card {
			background-color: #f7f6f2;
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
		h3 {
 			font-weight: 600; 
			color: #30504F;
  			padding: 5px; 
		}
		h4 {
			color: #996A4D;
			padding-bottom: 10px;
		}
		.form-select {
			color: #30504F;
			font-size: 16px;
 			height: 50px;  
		}
		textarea.form-control {
			height: auto;
			font-size: 16px;
		}
		.img-container {
			width: 45%;
			cursor: pointer;
			position: relative;
			margin: 0 auto;
			display: inline-block;
		}
		img {
			transition: 0.5s;
			max-width: 100%;
		}
		.delete{
		    transition: 0.5s;
		    opacity: 0;
		    visibility: hidden;
		    position: absolute;
		    bottom: 0;
		    width: 100%;
		    text-align: center;
		}
		.img-container:hover .delete {
			bottom: 30px;
			opacity: 1;
			visibility: visible;
		}
		.img-container:hover img {
			transition: 0.5s;
			opacity: 0.6;
		}
		.btn-delete {
			background-color: white;
			border: 1px solid #aaa;
			padding: 10px;
			border-radius: 30px;
		}
		.btn-delete img {
			width: 36px;
		}
 		.btn-delete:hover img {
 			transition: 0.5s;
 		} 
		.preview div {
			display: inline-block;
			width: 45%;
			margin: 5px;
		}
		.main-content {
	    display: block;
	    width: 100%;
	    overflow-x: auto;
	   padding: 6px 89px 45px 160px;
	    -webkit-overflow-scrolling: touch;
       }
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
		<a class="btn btn-secondary1 light"
			href="<%=request.getContextPath()%>/frontend/room/listAllRmType_S.jsp">&lt;
			回房型列表</a>
<!-- 錯誤表列 -->
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
			<div class="card col-xl-11">
				<div class="card-body d-flex justify-content-around">
					<div class="col-xl-6">
						<h3 class="title d-flex justify-content-center">照片列表</h3>
						<c:choose>
							<c:when test="${images.size() != 0 }">
								<h4 class="title d-flex justify-content-center">共${images.size()}張照片</h4>
							</c:when>
							<c:otherwise>
								<h4 class="title d-flex justify-content-center">還沒有照片喔</h4>
							</c:otherwise>
						</c:choose>
						<c:forEach var="img" items="${images}">
							<div class="mb-3 img-container">
								<img
									src="<%=request.getContextPath()%>/rmPic/rmPic.do?rm_pic_no=${img.rm_pic_no}&action=showImages">
	
								<div class="delete">
									<form method="post"
										action="<%=request.getContextPath()%>/rmPic/rmPic.do">
										<input type="hidden" name="rm_pic_no" value="${img.rm_pic_no}">
										<input type="hidden" name="rm_type_no" value="${img.rm_type_no}">
										<input type="hidden" name="action" value="delete">
										<button type="submit" class="btn-delete">
											<img class="img-delete" 
												src="<%=request.getContextPath()%>/frontend/assets/images/hotels/trash.png">
										</button>
									</form>
								</div>
	
							</div>
						</c:forEach>
					</div>
	
					<div class="col-xl-6 imgs">
						<h3 class="title d-flex justify-content-center">新增照片</h3>
						<form method="post" action="<%=request.getContextPath()%>/rmPic/rmPic.do" enctype="multipart/form-data">
							<div class="pic-upload">
								<input type="file" class="form-control upload-pic" id="formFile" accept="image/*" name="imageFile">
<!-- 								<input type="file" accept="image/*" name="rm_pic" class="upload-pic" multiple /> -->
							</div>
							<div class="preview"></div>
							
							<div class="mb-3 d-flex justify-content-center align-items-center">
<!-- 								insert_rm_type -->
								<input type="hidden" name="rm_type_no" value="${rm_type_no}">
								<input type="hidden" name="action" value="insert"> 
 								<button type="submit" class="btn btn-primary1 col-lg-4">新增</button>	
<!--							<input type="submit" class="btn btn-primary1 col-lg-4" value="新增">  -->
								
							</div>
						</form>

					</div>
					
				</div>
			</div>
		</div>

		<%@ include file="/frontend/footer.file" %>
		<%@ include file="/frontend/commonJS.file" %>
		<script>
			$(document).ready(function() {
				$("#pagename").text("房型圖片【房型 No. ${rm_type_no}】");
				
 				$(".img-delete").hover(function(){
			    $(this).attr('src', '<%=request.getContextPath()%>/frontend/assets/images/hotels/trashopen.png');
 			    },function(){
			        $(this).attr('src', '<%=request.getContextPath()%>/frontend/assets/images/hotels/f_trash.png');
 			    }); 
				
				//General Upload Pic Preview js
	           let upload = $(".upload-pic");
	            upload.change(function(){
	            	if (this.files) {
	                    files = this.files;
	                    let preview = $(this).parents(".pic-upload").next();
	                    for (let i = 0; i < files.length; i++) {
	                        if (files[i].type.indexOf("image") >= 0) {
	                            let reader = new FileReader();
	                            reader.addEventListener("load", (ex) => {
	                                let div = document.createElement("div");
	                                let img = document.createElement("img");
	                                img.src = ex.target.result;
	                                img.classList.add("previewImg");
	                                div.append(img);
	                                preview.append(div);
	                            });
	                            reader.readAsDataURL(files[i]);
	                        } else {
	                            window.close();
	                        }
	                    }
	                }
	            }) 
			} );
			
			
			
			
		</script>
	</body>
</html>