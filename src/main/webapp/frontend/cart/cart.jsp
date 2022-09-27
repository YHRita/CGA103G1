<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.mem.model.*"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
	<style>
		.btn-small {
			padding: 0px 10px;
			margin-bottom: 0px;
			line-height: 20px;
			border-radius: 100%;
			width: 25px;
			height: 25px;
			text-align: center;
		}
		
		.tktImgWidth {
			width: 80px;
		}
	</style>
	
</head>
<body>

<% 
List<CartItemVO> cartItems = (List<CartItemVO>)request.getAttribute("cartItems"); 
%>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  
  
  
  
    <!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) center repeat; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">購物車</h1>
      </div>
    </div>
  </div>
</div>
  <!-- main start-->
  
  <form class="pt80 pb80" method="post" action="<%=request.getContextPath()%>/cart/cartCheckOut.do">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 mb-5">
					<h3 class="text-center mb-4">購物車清單</h3>
					<div class="table-responsive-sm">
						<table class="table table-lg table-noborder table-striped">
							<thead class="all-text-white bg-grad">
								<tr>
									<!-- <th scope="col">#</th> -->
									<th scope="col">
										<input type="checkbox" id="checkboxAll">
									</th>
									<th scope="col">票券名稱</th>
									<th scope="col"></th>
									<th scope="col">單價</th>
									<th scope="col">數量</th>
									<th scope="col">小計</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartItemVO" items="${cartItems}">
								<tr class="tr">
									<td>
										<input type="checkbox" name="tkt_no" value="${cartItemVO.tkt_no}" class="checkbox">
									</td>
									<td>
										<img src="<%=request.getContextPath()%>/tkt_img2/uploadTktImg.do?tkt_no=${cartItemVO.tkt_no}&action=showFirstImages" class="tktImgWidth">
									</td>
									<td>${cartItemVO.tkt_name}</td>
									<td>NT$ <span class="onePrice">${cartItemVO.price}</span></td>
									<td>
										<button type="button" class="btn btn-primary btn-small minus">-</button>
										<span class="itemCount">${cartItemVO.count}</span>
										<button type="button" class="btn btn-primary btn-small plus">+</button>
									</td>
									<td class="itemsPrice">${cartItemVO.price * cartItemVO.count}</td>
									<td>
              								<input class="tkt_no1" type="hidden" name="tkt_no1"  value="${cartItemVO.tkt_no}">
											<!-- 刪除按鈕 -->
											<button  type="button" class="btn btn-danger delItem"><i class="fas fa-trash-alt"></i></button>
										<!-- </form> -->
										
									</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
					
					<hr>
					
					<div class="d-flex justify-content-between">
						<!-- <div class="d-flex"> -->
							<a href="<%=request.getContextPath()%>/frontend/tkt/tktShopList.jsp" class="btn btn-outline-primary">繼續購物</a>  <!-- 返回票券列表要動態 -->
						<button type="submit" class="btn btn-primary checkOut" id="checkOut"><i class="fas fa-arrow-circle-right"></i>前往結賬</button>
					</div>
					
				</div>
			</div>
  		</div>
  </form>
  
  
  
     </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
  
  <!-- jquery cdn -->
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  <!-- sweetalert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
  
  <script>
  <!-- cart js -->
  const minusBtn = document.querySelectorAll('.minus');
  const plusBtn = document.querySelectorAll('.plus');
  const itemCount = document.querySelectorAll('.itemCount'); 
  const itemsPrice = document.querySelectorAll('.itemsPrice'); 
  const onePrice = document.querySelectorAll('.onePrice'); 
  const totalPrice = document.querySelector('.totalPrice'); 
  const tkt_no_s = document.querySelectorAll('.tkt_no1'); 
  const tr = document.querySelectorAll('.tr'); 
  const delItem = document.querySelectorAll('.delItem'); 
  const checkbox = document.querySelectorAll('.checkbox'); 
  const checkboxAll = document.querySelector('#checkboxAll'); 
  const checkOut = document.querySelector('#checkOut'); 
  
 
  // 當購物車沒商品時，前望結帳按鈕隱藏
  if(${cartItems.size()} === 0) {
		checkOut.setAttribute('style', 'display: none');
  }
  
  
  for(let i = 0; i < ${cartItems.size()}; i++) {
	  
	  
	  
	  
	  function noItem() {
			let counts = 0;
			for(let j = 0; j < ${cartItems.size()}; j++) {
			   		if(tr[j].style.display === "none") {
			   			counts++;
	 		   			console.log(counts);
	 		   		}
			}
			
			// 當購物車總數和 none 總數一樣，等於所有商品都被刪除了
			if(${cartItems.size()} === counts) {
	
				checkOut.setAttribute('style', 'display: none');
				
			}
		}
	  	
	  
	  
	  // 全選 checkbox
	  checkboxAll.addEventListener('change', () => {
		  checkbox[i].checked = true;
		  this.checked = !this.checked;
	  });
	  
 	 // 被選的 checkbox
 	 checkbox[i].addEventListener('change', () => {
 		 /* console.log(checkbox[i]); */
 		/* checkbox[i].checked = true; */
 		this.checked = !this.checked;
	  });
	  
	  
	  
	  /* 取出該項目的 tkt_no 轉型為數字送進 servlet */
	  let tkt_no = parseInt(tkt_no_s[i].value);
	  
	  /* sweetalert btn setting*/
		 const swalWithBootstrapButtons = Swal.mixin({
	        customClass: {
	            confirmButton: 'btn btn-success ml-3',
	            cancelButton: 'btn btn-danger mr-3'
	        },
	        buttonsStyling: false
	    })
	    
	    
	   
	  /* delItem */
	  
	  delItem[i].addEventListener('click', () => {
		  
			  swalWithBootstrapButtons.fire({
			        title: '確定將此票券商品移除購物車嗎?',
			        text: "刪除後，此票券商品將不在購物車中",
			        icon: 'warning',
			        showCancelButton: true,
			        confirmButtonText: '是的，我要刪掉',
			        cancelButtonText: '我還是想買票券',
			        reverseButtons: true
			    }).then((result) => {
			        if (result.isConfirmed) {
			            swalWithBootstrapButtons.fire(
			                '已成功刪除此票券商品!',
			                '若想再次將商品加入購物車，請至票券商城',
			                'success'
			            )
			            
			           axios({
			   				"method": "post", 
			   				"url": "delCartItem.do",
			   				"params": {
			   					"tkt_no": tkt_no
			   				}
			   		   }).then(function(response) { 
			   		   		tr[i].setAttribute('style', 'display: none');
			   		   		noItem();
			   		   }).catch((error) => console.log(error));

			        }

			    })
		 
	  });
	  
	 
	  /* minusBtn */
 	minusBtn[i].addEventListener('click', () => {
		if(parseInt(itemCount[i].innerText) > 1) {
 			let afterMinus = parseInt(itemCount[i].innerText) -1;
 			itemCount[i].innerText = afterMinus; 
 			/* 更新小計 */
 		 	itemsPrice[i].innerText = parseInt(itemCount[i].innerText) * parseInt(onePrice[i].innerText); 
 		 	
 				  
 			/* 更新 redis 數量 */
 			axios({
 		   		"method": "post", 
 		   		"url": "changeCount.do",
 		   		"params": {
 		   			"count": afterMinus,
 		   			"tkt_no": tkt_no
 		   		}
 		    }).then(function (response) {
 		    }).catch(function (error) {
 		         console.log(error);
 		    }); 
 			
 			
 		} else if (parseInt(itemCount[i].innerText) === 1) { // 當購物車商品已經只剩下 1 時，還想要繼續 -1 會詢問會員是否要刪除品項
 			
 		    swalWithBootstrapButtons.fire({
 		        title: '確定將此票券商品移除購物車嗎?',
 		        text: "刪除後，此票券商品將不在購物車中",
 		        icon: 'warning',
 		        showCancelButton: true,
 		        confirmButtonText: '是的，我要刪掉',
 		        cancelButtonText: '我還是想買票券',
 		        reverseButtons: true
 		    }).then((result) => {
 		        if (result.isConfirmed) {
 		            swalWithBootstrapButtons.fire(
 		                '已成功刪除此票券商品!',
 		                '若想再次將商品加入購物車，請至票券商城',
 		                'success'
 		            )
 		            /* 如果按確認刪除按鈕 */
 		           let afterMinus = parseInt(itemCount[i].innerText) -1;
 		            
 		           axios({
 		   				"method": "post", 
 		   				"url": "changeCount.do",
 		   				"params": {
 		   					"count": afterMinus,
 		   					"tkt_no": tkt_no
 		   				}
 		   		   }).then(function (response) {
 		   			  tr[i].setAttribute('style', 'display: none');
 		          }).catch(function (error) {
 		              console.log(error);
 		          }); 
 		        }

 		    })
 		}
 		
		
 		
  	});
	  
	  
	  /* plusBtn */
	  
 	plusBtn[i].addEventListener('click', () => {
 		
  		let afterPlus = parseInt(itemCount[i].innerText) +1;
  		itemCount[i].innerText = afterPlus; 
  		/* 更新小計 */
  		itemsPrice[i].innerText = parseInt(itemCount[i].innerText) * parseInt(onePrice[i].innerText); 
  	
 		
 		axios({
 			"method": "post", 
 			"url": "changeCount.do",
 			"params": {
 				"count": afterPlus,
 				"tkt_no": tkt_no
 			}
 		}).then(function (response) {
        }).catch(function (error) {
            console.log(error);
        });
 		
   	});
	  
 	
	
	
	
	  
 	 
 	 
	  
 } 
 
  
 
 	
  </script>

</body>
</html>