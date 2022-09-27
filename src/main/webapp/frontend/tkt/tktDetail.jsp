<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券詳情 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
	<style>
	
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
  <%@ include file="/frontend/header.file" %>

  
  <!-- main -->
  
  <section class="pt80 pb80 listingDetails Campaigns">
  <div class="container">
    <div class="row"> 
      
      <!-- Tab line -->
      <div class="col-lg-8 col-md-12 col-sm-12 ">
        
        <a href="<%=request.getContextPath()%>/frontend/tkt/tktShopList.jsp" class="btn btn-info mb-3">返回票券商城</a> 
        <div class="tab-content">
          <div class="tab-pane active" id="tab-de-1">
            <div class="text-block NopaddingDetails">
              <h3 class="mb-4">${tktVO.tkt_name}</h3>
              <p class="mb-2">詳細說明：${tktVO.instruction}</p>
              <p class="mb-2">開始日期：${tktVO.tkt_startdate}</p>
              <p class="mb-2">結束日期：${tktVO.tkt_enddate}</p>
              <p class="mb-2">地區：${tktVO.locate}</p>
              <p class="mb-2">體驗地址：${tktVO.address}</p>
              
              
              <c:if test="${tktVO.kind == 0 }" var="true">
					<span class="badge badge-pill badge-primary text-white"># 景點門票</span>
			  </c:if> 
			  <c:if test="${tktVO.kind == 1 }" var="true">
					<span class="badge badge-pill badge-success text-white"># 主題樂園</span>
			  </c:if> 
			  <c:if test="${tktVO.kind == 2 }" var="true">
					<span class="badge badge-pill badge-info text-white"># 博物館展覽</span>
			  </c:if>
              
            </div>
            <div class="text-block"> 
              <h5 class="mb-4"></h5>
              <div class="row gallery ml-n1 mr-n1">
                <div class="col-lg-12 col-6 px-1 mb-2">
                <img src="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${tktVO.tkt_no}&action=showFirstImages" class="img-fluid">
                </div>
              </div>
            </div>
            <div class="text-block">
              
              
              <div class="media d-block d-sm-flex review">
                <div class="media-body">
                  <p class="mb-2">購買須知：${tktVO.notice}</p>
              	<p class="mb-2">如何使用：${tktVO.howuse}</p>
              	<p class="mb-2">取消政策：${tktVO.canxpolicy}</p>
                  
                </div>
              </div>
            </div>
          </div>
         

        </div>
      </div>
      <!-- cart form -->
      <div class="col-lg-4 col-md-12 col-sm-12 right_Details">
        <div class="p-4 shadow ml-lg-4 rounded sticky-top" style="top: 100px;">
        
        <h5 class="mb-4">${tktVO.tkt_name}</h5>
          <p class="text-muted"><span class="text-primary h2">${tktVO.price}</span> / 人</p>
          <hr class="my-4">
            <div class="form-group">
            <div class="form-group mb-4">
            </div>
               <input type="hidden" name="tkt_no"  value="${tktVO.tkt_no}" class="tkt_no"> 
			   <input type="hidden" name="count"  value="1" class="count">
              <button type="submit" class="btn btn-primary btn-block addItem">加入購物車<i class="fas fa-cart-arrow-down"></i></button>
			   <input type="hidden" name="action"	value="addItem">
            </div>
          
          
        </div>
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


  
  
     </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
	
	<!-- sweetalert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
 
 <!-- init 加到 footer 或 header 或 JS file-->
<%-- <script src="<%=request.getContextPath()%>/frontend/cart/init.js"></script> --%>
 
 
 <script>
 
 	const addItem = document.querySelector('.addItem');
 	const tkt_no = document.querySelector('.tkt_no');
 	const count = document.querySelector('.count');
 	const cartDropUp = document.querySelector('.cartDropUp');
 	
 	/* sweetalert btn setting*/
	const swalWithBootstrapButtons = Swal.mixin({
       customClass: {
           confirmButton: 'btn btn-success ml-3',
           cancelButton: 'btn btn-danger mr-3'
       },
       buttonsStyling: false
    })
 	
 	addItem.addEventListener('click', () => {
 		axios({
 			"method": "post", 
 			"url": "/CGA103G1/cart/addCart.do",
 			"params": {
 				"count": parseInt(count.value),
 				"tkt_no": parseInt(tkt_no.value)
 			}
 		}).then(function (response) {
            console.log(response);
         // 在這加入 sweetAlert
	        swalWithBootstrapButtons.fire({
				position: 'center',
				icon: 'success',
				title: '您已成功將票券加入購物車',
				showConfirmButton: false,
				timer: 1500
			})
			getCartNum();
        }).catch(function (error) {
            console.log(error);
        });
 	});
 	
 	// 載入商品單一頁面或商品列表頁就自動初始化
 	
 	function init(){ 
 		axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/initCart.do"
 		}).then(function (response) {
 	    	console.log(response);
 	    	console.log("初始化成功");
 	    	getCartNum();
 		}).catch(function (error) {
 	    	console.log(error);
 		});
 	}

 	// 當畫面載入時初始化
 	window.onload = init();
 	
 	
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
 
 </script>
  

</body>
</html>