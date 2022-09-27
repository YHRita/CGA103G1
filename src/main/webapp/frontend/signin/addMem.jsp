
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("MemVO");
%>

<html lang="en">

<head>
  <title>會員註冊</title>
<%-- CSS --%>

<style>
#img0 {
 min-width: 100px;
 border: 1px solid lightgray;
 display: inline-block;
 min-height: 80px;
 max-width: 150px;
 position: relative;
 z-index: 1;
}

#preview {
 display: inline-block;
 position: relative;
}

#preview span.text {
 position: absolute;
 display: inline-block;
 left: 50%;
 top: 50%;
 transform: translate(-50%, -50%);
 color: lightgray;
}
</style>

	<%@ include file="/frontend/commonCSS.file" %>
</head>

<body>
 <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  <section class="registration">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="registration-box">
            <div class="reg-top">
              <h3>註冊</h3>
              <p></p>
            </div>
            <form class="reg-form" action="<%=request.getContextPath()%>/mem.do" METHOD="post" name="form1" enctype="multipart/form-data">
              <div class="row">
                <div class="col-md-12 email">
                  <label>信箱</label>
                  <input type="text" name="mem_email"value="<%=(memVO==null)? "jackypao103098@gmail.com" :memVO.getMem_email()%>" placeholder="Enter Email here">
                </div>
                <div class="col-md-12 password">
                  <label>密碼</label>
                  <input type="password" name="mem_pwd" placeholder="Enter your password here"value="<%=(memVO==null)? "vwxyz" :memVO.getMem_pwd()%>" >
                </div>
               
                <div class="col-md-6 name">
                  <label>姓名</label>
                  <input type="text" name="mem_name" placeholder="Enter your name here"value="<%= (memVO==null)? "豹仕豪" :memVO.getMem_name()%>">
                </div>
                <div class="col-md-6 sex">
                  <label>姓別</label>
                  <select class="custom-select select-big mb-3"name="mem_gender">
                    <option >男</option>
                    <option >女</option>
                  </select>
                </div>
                <div class="col-md-12 password">
                    <label>電話</label>
                    <input type="text" name="mem_mobile" placeholder="Enter your Phone here"value="<%=(memVO==null)? "0988682536" :memVO.getMem_mobile()%>">
                  </div>
                </div>
                 <div class="col-md-6 name">
                  <label>縣市</label>
                  <input type="text" name="mem_city" placeholder="Enter your name here"value="<%=(memVO==null)? "新北市" :memVO.getMem_city()%>">
                </div>
<!--                  <div class="col-md-12 password"> -->
<!--                     <label>加入時間</label> -->
<!--                     <input type="text" name="mem_reg_date" id="f_date1"> -->
<!--                   </div> -->








                  <div class="col-md-6 name">
                  <label>會員照片</label>
                  <input type="file" name="mem_pic" accept="image/*"
           id="file0"/>
                </div>

           <div class="col-md-6 name" id="preview">
                  <label>圖片預覽</label>
                  <div id="preview"><img class="img" id="img0"><span class="text">預覽圖</span>
                </div>
                </div>

                <div class="col-md-6 name">
                  <label>信用卡</label>
                  <input type="text" name="mem_card" placeholder="Enter your name here"value="<%=(memVO==null)? "5527921393052964" :memVO.getMem_card()%>" >
                </div>
               <input type="hidden" name="action" value="insert">
                <div class="col-md-12">
                  <button type="submit" name="button">創建帳號</button>
                </div>
                <div class="login-btm text-center">
              <p>已有帳號 ?<a href="<%=request.getContextPath()%>/frontend/signin/login.jsp">登入</a></p>
                </div>
              </div>
            </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- =======================
	footer  -->
 <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
    <!-- =======================
	footer  -->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/popper.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/functions.js" type="text/javascript"></script>
    <script src="js/owl.carousel.min.js" type="text/javascript"></script>
    <script src="js/slick.js" type="text/javascript"></script>
    <script src="js/swiper.min.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script src="js/jquery.fancybox.min.js" type="text/javascript"></script>
    <script src="js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui.min.js" type="text/javascript"></script>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = memVO.getMem_reg_date();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>

function addimage() {
	  let img = $("#img0");
	  img.attr('src', URL.createObjectURL(this.files[0]));
	  console.log(img);
	};
	$("#file0").change(addimage);
	
	
	
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>