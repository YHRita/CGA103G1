<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_img.model.*"%>
<%@ page import="com.mem.model.*"%>

<jsp:useBean id="tktImg2Srv" scope="page" class="com.tkt_img2.model.Tktimg2Service" />

<%
TktService tktSvc = new TktService();
List<TktVO> list = tktSvc.getAllByStatus();
pageContext.setAttribute("list", list);


/* Integer allNum = (Integer)request.getSession().getAttribute("cartAllNum");
System.out.println(allNum);  */ // 這邊刷新可取到購物車值


/* Integer cartItemNum = (Integer)request.getAttribute("cartItemNum");
System.out.println(cartItemNum);
pageContext.setAttribute("cartItemNum", cartItemNum); */

%>


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


	.cartBtn {
			position: fixed;
  			border-radius: 100%;
  			background-color: #5bc9e2;
  			width: 60px;
  			height: 60px;
  			right: 10%;
  			bottom: 10%;
  			cursor: pointer;
  			z-index: 10;
		}
		
	.cartIcon {
		padding-top: 12px;
		font-size: 20px;
		color: white;
	}
	.tktImgHeight {
		max-height: 220px;
	}
	
	#cartNum {
    font-size: 8px;
    background: #ff0000;
    color: #fff;
    padding: 0 2px;
    vertical-align: top;
    margin-left: -15px;
}
.badgeNum {
  padding-left: 5px !important;
  padding-right: 5px !important;
  border-radius: 100%;
}

.label-warning[href],
.badgeNum-warning[href] {
  background-color: #c67605;
}

.cartBtnDrop {
	left: 50px;

}
.dropdown-toggle::after {
	display: none !important;
}
/* .padding-20 {
	padding: 20px;
} */
.dropdown-menu {
	width: 280px;
	text-align: center;
	border: 3px solid #5bc6df;
	border-radius: 15px;
}
.pr-15 {
	padding-right: 15px !important;
}
.pd-10 {
	padding: 10px;
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
      <div class="col-lg-12 col-md-12 col-sm-12 Filter-left mb-60">
        
  <form class="col-lg-12 col-md-12 col-sm-12 d-flex row" method="post" action="<%=request.getContextPath()%>/tkt/tktCqList.do">
       
       <div class="col-lg-3 col-md-3 col-sm-12">
       	 <label for="form_dates" class="form-label">透過關鍵字查詢</label>
       	 <div class="form-group">
           <input class="form-control" type="text" placeholder="請搜尋關鍵字" name="tkt_name">
       	  </div>
       </div>
       
      <div class="col-lg-3 col-md-3 col-sm-12">
      
      	 <label for="form_neighbourhood" class="form-label">票券體驗區域</label>
           <select id="locate" class="default-select form-control wide" name="locate">
           		<option value="">--- 請選擇 ---</option>
				<option value="基隆市">基隆市</option>
				<option value="台北市">台北市</option>
				<option value="新北市">新北市</option>
				<option value="桃園市">桃園市</option>
				<option value="新竹縣">新竹縣</option>
				<option value="新竹市">新竹市</option>
				<option value="苗栗縣">苗栗縣</option>
				<option value="台中市">台中市</option>
				<option value="彰化縣">彰化縣</option>
				<option value="南投縣">南投縣</option>
				<option value="雲林縣">雲林縣</option>
				<option value="嘉義縣">嘉義縣</option>
				<option value="嘉義市">嘉義市</option>
				<option value="台南市">台南市</option>
				<option value="高雄市">高雄市</option>
				<option value="屏東縣">屏東縣</option>
				<option value="宜蘭縣">宜蘭縣</option>
				<option value="花蓮縣">花蓮縣</option>
				<option value="台東縣">台東縣</option>
				<option value="澎湖縣">澎湖縣</option>
				<option value="金門縣">金門縣</option>
				<option value="連江縣">連江縣</option>
			</select>
      
      </div>
       
       
       
      <div class="col-lg-4 col-md-4 col-sm-12">
      	<label for="form_neighbourhood" class="form-label">票券種類</label>
      	 <ul class="list-unstyled mb-0 d-flex">
                  <li class="mr-10">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" id="amenities_12" name="kind" class="custom-control-input" value="0">
                      <label for="amenities_12" class="custom-control-label"> 景點門票</label>
                    </div>
                  </li>
                  <li class="mr-10">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" id="amenities_13" name="kind" class="custom-control-input" value="1">
                      <label for="amenities_13" class="custom-control-label">主題樂園</label>
                    </div>
                  </li>
                  <li>
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" id="amenities_14" name="kind" class="custom-control-input" value="2">
                      <label for="amenities_14" class="custom-control-label">博物館展覽</label>
                    </div>
                  </li>
                </ul>
      
      </div>
      
       <div class="col-lg-2 col-md-2 col-sm-12">
			<button type="submit" class="btn btn-primary btn-grad FilterBtn"> <i class="fas fa-filter mr-1"></i>查詢 </button>  
       </div>
       	
       </form>
      </div>
      
      
      <!-- tkt -->
      
      
        <%@ include file="/frontend/tkt/pageIndex.file"%>
      <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
        <c:forEach var="tktVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
  		<%@ include file="/frontend/tkt/pagination.file"%>
      </div>
      
       <!-- 可能要加在全站下面 -->
      
 	  <%-- <a href="<%=request.getContextPath()%>/cart/getCart.do" class="btn cartBtn">	
		<i class="fa fa-shopping-cart cartIcon" aria-hidden="true"></i>      	
		<span class="badgeNum badgeNum-warning" id="cartNum">0</span>
      </a> --%>
      

      
      	<div class="btn-group dropup cartBtn">
  		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    		<i class="fa fa-shopping-cart cartIcon" aria-hidden="true"></i>      	
			<span class="badgeNum badgeNum-warning" id="cartNum">0</span>
  		</button>
  		<div class="dropdown-menu pd-10">
  			<p>購物車明細</p>
  			<div class="dropdown-divider"></div>
  			<table>
  				<thead class="all-text-white bg-grad">
					<tr>
						<th class="pr-15">票券名稱</th>
						<th class="pr-15">單價</th>
						<th class="pr-15">數量</th>
					</tr>
				</thead>
				<tbody class="cartDropUp">
				</tbody>
  			</table>
  			<div class="dropdown-divider"></div>
    		<a href="<%=request.getContextPath()%>/cart/getCart.do" class="btn btn-primary">前往購物車</a>	
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
 	const cartNum = document.querySelector('#cartNum');
 	const cartDropUp = document.querySelector('.cartDropUp');
 	/* const cartQty = document.querySelector('#cartNum').innerText; */
	/* cartNum.innerText = ${allNum} */
	
	// 載入商品單一頁面或商品列表頁就自動初始化
 	
	
	
	
 	function init(){ 
 		axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/initCart.do"
 		}).then(function (response) {
 	    	console.log(response);
 	    	getCartNum();
 		}).catch(function (error) {
 	    	console.log(error);
 		});
 		
 	}
 	// 當畫面載入時初始化
 	window.onload = init();
 	
 	// 存購物車商品數量
 	/* let cartNums = 0; */
 	// 取得後端傳來的購物車JSON
 	function getCartNum() {
 		axios({
	 		"method": "post", 
	 		"url": "/CGA103G1/cart/getCartCount.do"
		}).then(function (data) {
			console.log(data);
			console.log(data.data.length);
			/* cartNums = data.data.length; */
			let html ="";
			cartNum.innerText = data.data.length;
			for(let i = 0; i < data.data.length; i++) {
				let obj = data.data[i];
		 		console.log(obj);
		 		console.log(obj.tkt_name);
		 		console.log(obj.count);
		 		console.log(obj.price);
		 		
		 		html += `<tr class="tr">
		 			<td class="pr-15">\${obj.tkt_name}</td>
		 			<td class="pr-15">\${obj.price}</td>
		 			<td class="pr-15">\${obj.count}</td>
		 		</tr>
		 		`;
		 	}
			cartDropUp.innerHTML = html;
		}).catch(function (error) {
	    	console.log(error);
		});
 	}
 	
 	
 	
 	
 	/* axios({
	 		"method": "get", 
	 		"url": "/CGA103G1/cart/getCartNum.do"
	 		"data": {
	 			"cartItemNum": cartItemNum
	 		} 
		}).then(function (response) {
			console.log(response.data);
	 	    console.log(response.status);
	 	    console.log(response.headers);
	 	    console.log(response.config);
	 	    console.log(cartItemNum);
		}).catch(function (error) {
	    	console.log(error);
		}); */
		
	
		
 	
 	/* sweetalert btn setting*/
	const swalWithBootstrapButtons = Swal.mixin({
       customClass: {
           confirmButton: 'btn btn-success ml-3',
           cancelButton: 'btn btn-danger mr-3'
       },
       buttonsStyling: false
    })
    
    
 	
 	for(let i = 0; i < ${list.size()}; i++) {
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
				getCartNum();
				// 加入成功改變數量到 cart icon
				/* cartNum.innerHTML = parseInt(cartQty) + 1; */
 	        }).catch(function (error) {
 	            console.log(error);
 	        });
 	 	});
 	}
 	
		
		
 	// 取得購物車列表總數
 	/* axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/getCart.do"
 		}).then(function (response) {
 	    	console.log(response);
 		}).catch(function (error) {
 	    	console.log(error);
 		}); */
 	
 	
 	
 	
	
	</script>
	
</body>
</html>