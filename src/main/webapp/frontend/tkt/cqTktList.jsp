<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_img.model.*"%>
<%@ page import="com.mem.model.*"%>

<%-- <%
TktService tktSvc = new TktService();
List<TktVO> list = tktSvc.getAll();
%> --%>

<jsp:useBean id="listTkt_ByCompositeQuery" scope="request" type="java.util.List<TktVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="tktImg2Srv" scope="page" class="com.tkt_img2.model.Tktimg2Service" />



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券商城</title>
<%-- CSS --%>
<%@ include file="/frontend/commonCSS.file"%>

<style>
	.TravelGo-category-footer{
		padding: 15px 0px !important;
	}
	.badge {
		padding: 10px 10px 5px 10px !important;
	}
	
	.mr-10{
		margin-right: 10px;
	}
	
	.mb-60{
		margin-bottom: 60px;
	}
	.tktImgHeight {
		max-height: 220px;
	}
</style>

</head>
<body>

<%-- header --%>
	<%@ include file="/frontend/header.file"%>
	
	
	<!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) repeat center; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">票券商城</h1>
      </div>
    </div>
  </div>
</div>
<!-- =======================
	Banner innerpage -->
	
	<section class="pt80 pb80 cruise-grid-view">
  <div class="container">
    <div class="row">
      
      
      
      <!-- tkt -->
      
        <a href="<%=request.getContextPath()%>/frontend/tkt/tktShopList.jsp" class="btn btn-outline-info mb-5">查看所有票券</a>
      
      <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
        
        
        <c:forEach var="tktVO" items="${listTkt_ByCompositeQuery}">
        <c:if test="${tktVO.tkt_status == 1 }" var="true">
          <div class="col-lg-4 col-md-4 col-sm-12">
            <div class="listing-item ">
              <article class="TravelGo-category-listing fl-wrap">
                <div class="TravelGo-category-img">
                 <a href="/CGA103G1/tkt/tktDetail.do?tkt_no=${tktVO.tkt_no}">
                 <img src="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${tktVO.tkt_no}&action=showFirstImages" class="tktImgHeight">
                 </a>
                  <div class="TravelGo-category-opt">
                    <div class="listing-rating card-popup-rainingvis" data-starrating2="5">
                    <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i>
                    </div>
                    <div class="rate-class-name">
                      <span>5.0</span> </div>
                  </div>
                </div>
                <div class="TravelGo-category-content fl-wrap title-sin_item">
                  <div class="TravelGo-category-content-title fl-wrap">
                    <div class="TravelGo-category-content-title-item">
                      <h3 class="title-sin_map"><a href="hotel-detailed.html">${tktVO.tkt_name}</a></h3>
                      <div class="TravelGo-category-location fl-wrap"><a href="#" class="map-item"><i class="fas fa-map-marker-alt"></i> ${tktVO.address}</a> <span>NT$ ${tktVO.price}</span> </div>
                      
                    </div>
                  </div>
                  <p>${tktVO.instruction}</p>
                  <div class="TravelGo-category-footer fl-wrap d-flex justify-content-between">
                    
                    <c:if test="${tktVO.kind == 0 }" var="true">
						<span class="badge badge-pill badge-primary text-white"># 景點門票</span>
					</c:if> 
					<c:if test="${tktVO.kind == 1 }" var="true">
						<span class="badge badge-pill badge-success text-white"># 主題樂園</span>
					</c:if> 
					<c:if test="${tktVO.kind == 2 }" var="true">
						<span class="badge badge-pill badge-info text-white"># 博物館展覽</span>
					</c:if>
					
                  <input type="hidden" name="tkt_no" value="${tktVO.tkt_no}" class="tkt_no">
                  <input type="hidden" name="count"  value="1" class="count">
                    <button  type="button" class="TravelGo-category-price btn-grad addItem"><span>加入購物車</span><i class="fas fa-cart-arrow-down"></i></button>
                  </div>
                </div>
              </article>
            </div>
          </div>
          </c:if>
		</c:forEach>
 
  
  

        </div>
      </div>
    </div>
  </div>
</section>
	
	
	<%-- footer --%>
	<%@ include file="/frontend/footer.file"%>

	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file"%>
	
	<!-- sweetalert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
		<!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
	
	
	<script>
	
	const addItem = document.querySelectorAll('.addItem');
 	const tkt_no = document.querySelectorAll('.tkt_no');
 	const count = document.querySelectorAll('.count');
 	
 	
 	/* sweetalert btn setting*/
	const swalWithBootstrapButtons = Swal.mixin({
       customClass: {
           confirmButton: 'btn btn-success ml-3',
           cancelButton: 'btn btn-danger mr-3'
       },
       buttonsStyling: false
    })
 	
 	for(let i = 0; i < ${listTkt_ByCompositeQuery.size()}; i++) {
 		addItem[i].addEventListener('click', () => {
 	 		axios({
 	 			"method": "post", 
 	 			"url": "/CGA103G1/cart/addCart.do",
 	 			"params": {
 	 				"count": parseInt(count[i].value),
 	 				"tkt_no": parseInt(tkt_no[i].value)
 	 			}
 	 		}).then(function (response) {
 	            //console.log(response);
 	            // 在這加入 sweetAlert
 	            swalWithBootstrapButtons.fire({
  					position: 'center',
  					icon: 'success',
  					title: '您已成功將票券加入購物車',
  					showConfirmButton: false,
  					timer: 1500
				})
 	        }).catch(function (error) {
 	            console.log(error);
 	        });
 	 	});
 	}
 	
 	// 載入商品單一頁面或商品列表頁就自動初始化
 	
 	function init(){ 
 		axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/initCart.do"
 		}).then(function (response) {
 	    	console.log(response);
 		}).catch(function (error) {
 	    	console.log(error);
 		});
 	}
 	// 當畫面載入時初始化
 	window.onload = init();
	
	</script>
	
</body>
</html>