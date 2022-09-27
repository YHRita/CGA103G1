<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem_coup.model.*"%>




<% 
MemVO memVO = new MemVO();
memVO = (MemVO)request.getSession().getAttribute("memVO");
Integer mem_no = memVO.getMem_no();

MemCoupService memCoupSrv = new MemCoupService();
List<MemCoupVO> memCoupList = memCoupSrv.listOneMemCoupon(mem_no);
pageContext.setAttribute("memCoupList", memCoupList);
/* CartItemVO cartItemVO = new CartItemVO();
System.out.println("cart.jsp cartItemVO:" + cartItemVO); */
List<CartItemVO> checkedList = (List<CartItemVO>)request.getAttribute("checkedList"); 

session.setAttribute("checkedList", checkedList);
/* System.out.println("cart.jsp cartItems:" + cartItems); */
 /*  CartItemService cartItemSrv = new CartItemService();
  List<CartItemVO> list = cartItemSrv.getCart(sessionId);
  pageContext.setAttribute("list", list); */
  /* Integer cartItemSize = cartItems.size(); */
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
	<style>
	
		.mt-60 {
		  margin-top: 60px;
		}
		.mb-20 {
		  margin-bottom: 20px;
		}
	
	</style>
</head>
<body>


  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  
  
  <!-- main start-->
  <!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) repeat center; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">結帳</h1>
      </div>
    </div>
  </div>
</div>
  
  <!-- TABLE -->
  <form class="pt80" method="post" action="<%=request.getContextPath()%>/tktOrder/addTktOrd.do">
		
  
  
  
  <!-- =======================
	Banner innerpage -->

<section class="pb80 booking-section login-area">
  <div class="container">
    <div class="row">
      
      <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
          <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="login-box Booking-box">
            

             
 
                <div class="row">
                
                
                <table>
            

			
				<div class="col-sm-12 mb-3">
					<h3 class="text-center mb-4">結帳清單</h3>
					<div class="table-responsive-sm">
						<table class="table table-lg table-noborder table-striped text-center">
							<thead class="all-text-white bg-grad">
								<tr>
									<!-- <th scope="col">#</th> -->
									<th scope="col">票券名稱</th>
									<th scope="col">單價</th>
									<th scope="col">數量</th>
									<th scope="col">小計</th>
								</tr>
							</thead>
							<tbody>
								 <c:forEach var="cartItemVO" items="${checkedList}">
								<tr>
									<!-- <th scope="row">1</th> -->
									<td scope="row">${cartItemVO.tkt_name}</td>
									<td>NT$ <span class="onePrice">${cartItemVO.price}</span></td>
									<td>${cartItemVO.count}</td> 
									<td>NT$ <span class="itemPrice">${cartItemVO.price * cartItemVO.count}</span></td>
								</tr>
								
									<input type="hidden" name="tkt_no"  value="${cartItemVO.tkt_no}">
								 </c:forEach> 

								
							</tbody>
						</table>
					
					
					<hr>
					
					
					
					<%-- <div class="d-flex justify-content-between">
						<a href="<%=request.getContextPath()%>/frontend/cart/testTkt1.jsp" class="btn btn-outline-primary">繼續購物</a>  <!-- 返回票券列表要動態 -->
						<button type="submit" class="btn btn-primary"><i class="fas fa-arrow-circle-right"></i>前往結賬</button>
					</div> --%>
					
				</div>
			
  	         
            
            </table>
                
               <h2 class="mt-60">訂購者資訊</h2>
                <p class="text-danger mb-20">＊為必填資訊</p>
                
                 <!--  <div class="d-flex justify-content-between mb-20">
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>會員編號</label>
                    <p>0001</p>
                    <input type="hidden" name="mem_no"  value="1">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label><span class="text-danger">*</span>姓名</label>
                    <input type="text" name="tkt_order_mem_name" class="form-control">
                  </div>
                  
                  </div>
                  <div class="d-flex justify-content-between mb-20">
                  
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label><span class="text-danger">*</span>Email</label>
                    <input type="text" name="tkt_order_mem_email" class="form-control">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 password">
                    <label><span class="text-danger">*</span>聯絡電話</label>
                    <input type="text" name="text" placeholder="自動帶入會員電話號碼" class="form-control">
                  </div> -->
                  
                  
                  
                  <div class="d-flex justify-content-between mb-20">
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label><span class="text-danger">*</span>姓名</label>
                    <input type="text" name="tkt_order_mem_name" class="form-control" value="${memVO.mem_name}">
                  </div>
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label><span class="text-danger">*</span>Email</label>
                    <input type="text" name="tkt_order_mem_email" class="form-control" value="${memVO.mem_email}">
                  </div>
                  </div>
                  
                  
               
                  
                  <div class="divider divider-dotted"></div>
                  
                  <div class="login-top cardInfo col-lg-12 col-md-12 col-sm-12">
                    <h3>信用卡付款</h3>
                  </div>
                  
                  <div class="d-flex justify-content-between mb-20">
                  
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>選擇付款方式</label>
                    <select class="custom-select select-big mb-3">
                      <option selected>信用卡付款</option>
                    </select>
                  </div>
                  
                  </div>
                  
                   <div class="divider divider-dotted"></div>
                   
                   <div class="login-top cardInfo col-lg-12 col-md-12 col-sm-12">
                    <h3>使用優惠券</h3>
                    <p>可選擇已擁有的優惠券</p>
                  </div>
                  
                <div class="d-flex justify-content-between mb-20">
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>優惠券選單</label>
                    <select class="custom-select select-big mb-3 mem_coup_selected" name="mem_coup_no">
                      <option value="0" selected>--- 請選擇 ---</option>
                      <c:forEach var="memCoupVO" items="${memCoupList}">
						<c:if test="${memCoupVO.coupVO.status == 1 && memCoupVO.coup_state == 0}">
							<option value="${memCoupVO.mem_coup_no}">${memCoupVO.coupVO.coup_name}, -NT$ ${memCoupVO.coupVO.discount}</option>
							</c:if>
						</c:forEach>
                    </select>
                  </div>
                  
                  	<div class="d-flex flex-column align-items-end mb-3"> 
							<p>商品總金額： <span class="totalPrice" style="color: red;"></span> 元</p>
							<p>折扣金額： - <span class="coup_discount" style="color: red;"></span> 元</p>
							<p>總付款金額： <span class="payPrice" style="color: red;"></span> 元</p>
							
							<!-- js setAttrubute--> 
							<input type="hidden" name="original_price" id="original_price" value="">
							<input type="hidden" name="ttl_price" id="ttl_price" value="">
					</div>
                 
               </div>
                  
                  
                  <div class="col-md-12">
						
                    	<!-- <input type="hidden" name="mem_no" value="1"> -->
                    	<input type="hidden" name="mem_no" value="${memVO.mem_no}"> 
                    	
						<div class="d-flex justify-content-between">
							<a href="<%=request.getContextPath()%>/cart/getCart.do" class="btn btn-outline-primary">返回購物車</a>
							<button type="submit" class="btn btn-info ml-3" id="addTktOrder">確認付款</button>
						</div>
					
                  </div>
                </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
  </form>
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
  
<script>
	const totalPrice = document.querySelector('.totalPrice'); 
	const itemPrice = document.querySelectorAll('.itemPrice'); 
	const mem_coup_selected = document.querySelector('.mem_coup_selected');
	const coup_discount = document.querySelector('.coup_discount');
	const discount_selected = document.querySelectorAll('.discount_selected');
	const payPrice = document.querySelector('.payPrice');
	const addTktOrder = document.querySelector('#addTktOrder');
	 /* input hidden  original_price */
	const original_price = document.querySelector('#original_price');
	 /* input hidden  ttl_price */
	const ttl_price = document.querySelector('#ttl_price');
	
	
	/* ----------- totalPrice -----------*/
	/* 未使用優惠券的總價 */
	let sum = 0;
	
	for(let i = 0; i < ${checkedList.size()}; i++) {
	  sum += parseInt(itemPrice[i].innerText);
	  totalPrice.innerText = sum;
	}
	
	/* original_price setAttribute */
	original_price.setAttribute("value", sum);
	
	
	/* ----------- coup discount -----------*/
	/* 優惠券折扣價 */
	let result = 0;
	/* 總價 - 折扣 */
	let pay = 0
	
	const options = mem_coup_selected.options;
	

	mem_coup_selected.addEventListener('change', () => { 
		let coupDiscount = options[mem_coup_selected.selectedIndex].innerText.split(', -NT$ ', 2)[1];
		coup_discount.innerText = coupDiscount;
		pay = sum - coupDiscount;
		payPrice.innerText = pay;
		/* ttl_price setAttribute */
		ttl_price.setAttribute("value", pay);
	});
	
		
	
	
	
	
</script>

</body>
</html>