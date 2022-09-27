$(document).ready(function (){
const actAll = document.getElementById("actAll");
const actListDiv = document.getElementById("actListDiv");
const actCount = document.getElementById("actCount");
const filterAct = document.getElementById("filterAct");
let loc = document.getElementById("loc");
let datepickerF  = $('#datepicker').datepicker({dateFormat:"yy-mm-dd"}); 
let datepickerOutF = $('#datepicker-out').datepicker({dateFormat:"yy-mm-dd"});
let type = document.getElementById("type");
let minCount = document.getElementById("minCount");
let actArr = [];

    $("#actAll").on('change',function(){
      $.ajax({
        url:"/CGA103G1/getAllAct",
        data:"action=doPost",
        type:"POST",
        dataType : "json",
        success:function (data) {
          let result = "";
          jsonLength = Object.keys(data).length;
          console.log(jsonLength);
          
          if(jsonLength === 0){
            actListDiv.innerHTML = `<h1 class="text-center" style="color: red;">目前無任何揪團活動</h1>`;
          }else{

            data.forEach(function(actVO){

              actStatusChange(actVO);

              locationChange(actVO);

              result += actList(actVO);
                  
            });
            $("#actListDiv").html(result);            
          }
          actArr = data;
          console.log(actArr);
          $("#actCount").html(data.length);    					
        }
      });
    });


    filterAct.addEventListener('click', function(){
      const locV  = locVChange(Number(loc.value));
      const minCountV  = Number(minCount.value);
      console.log(datepickerF);
      console.log(datepickerOutF);
      let datepickerFV  = datepickerF.val();
      let datepickerOutFV  = datepickerOutF.val();
      console.log(datepickerFV);
      console.log(datepickerOutFV);
      console.log(datepickerFV < actArr[0].act_enroll_begin);
      const typeV  = Number(type.value);
      let copyArr = [];
      let j = 0;
      if(datepickerFV === ""){
        datepickerFV = "0000-00-00";
      }
      if(datepickerOutFV === ""){
        datepickerOutFV = "9999-12-31";
      }
      if(actArr.length === 0){
        actListDiv.innerHTML = `<h1 class="text-center" style="color: red;">目前無任何揪團活動</h1>`;
      }else{
        while (j < actArr.length) {
          if(actArr[j].act_loc === locV && actArr[j].act_enroll_begin >= datepickerFV && actArr[j].act_enroll_end <= datepickerOutFV &&
            typeV === 0 && actArr[j].act_min_count >= minCountV){
            copyArr.push(actArr[j]);
          }
          if(actArr[j].act_loc === locV && actArr[j].act_enroll_begin >= datepickerFV && actArr[j].act_enroll_end <= datepickerOutFV &&
            actArr[j].act_type_no === typeV && actArr[j].act_min_count >= minCountV){
            copyArr.push(actArr[j]);
          }
          if(locV === 0 && actArr[j].act_enroll_begin >= datepickerFV && actArr[j].act_enroll_end <= datepickerOutFV &&
            typeV === 0 && actArr[j].act_min_count >= minCountV){
            copyArr.push(actArr[j]);
          }
          if(locV === 0 && actArr[j].act_enroll_begin >= datepickerFV && actArr[j].act_enroll_end <= datepickerOutFV &&
            actArr[j].act_type_no === typeV  && actArr[j].act_min_count >= minCountV){
            copyArr.push(actArr[j]);
          }
          j++;
        }
      }
      printFilterAct(copyArr);              
      console.log(copyArr);
    });
  }
);
function filterArr(locV, datepickerFV, datepickerOutFV, typeV , minCountV){

}
function printFilterAct(actArr){
  let result = "";
  let actNum = 0;
  if(actArr.length !== 0){
    for (let i = 0; i < actArr.length; i++) {
      result += actList(actArr[i]);
      actNum++;
    }
  }else{
    result = `<h1 class="text-center" style="color: red;">沒有符合篩選之揪團活動</h1>`;
  }
  actCount.innerHTML = actNum;
  actListDiv.innerHTML = result;
}
function actStatusChange(actVO) {
  if (actVO.act_status === 0) {
    actVO.act_status = "揪團中";
  } else if (actVO.act_status === 1) {
    actVO.act_status = "已成團";
  };
}
function starRateChange(actVO) {
  let starRate = 0;
  if (actVO.act_rate_sum === 0 || actVO.act_eval_sum === 0) {
    starRate = 0;
  } else {
    starRate = (actVO.act_rate_sum / actVO.act_eval_sum);
  }
  return starRate;
}
function actList(actVO){
  let output = "";
  output += `
      <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
          <div class="row listroBox">
            <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
              <figure> 
                <a href="/CGA103G1/redirectDetailPage?action=actInner&actNo=${actVO.act_no}" id="imageList">
                <div class="read_more"><span>Read more</span></div>
                </a> 
                <img src="/CGA103G1/getOneActPic?action=actImg&actNo=${actVO.act_no}" class="img-fluid" alt="">
              </figure>
            </div>
            <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
              <div class="listroBoxmain">
                <h3><a href="/CGA103G1/redirectDetailPage?action=actInner&actNo=${actVO.act_no}" alt="揪團活動詳情頁面">${actVO.act_title}</a></h3>
              </div>
              <ul>
                <li><span class="Ropen">${actVO.act_status}</span></li>
                <li><span class="Ropen">揪團編號：${actVO.act_no}</span></li>         
                <li>
                  <div class="R_retings"><span><em>舉辦縣市：</em> </span><strong>${actVO.act_loc}</strong></div>
                </li>
              </ul>
              <li><span class="Ropen">報名開始：${actVO.act_enroll_begin}</span></li>
              <li class="my-2"><span class="Ropen">報名截止：${actVO.act_enroll_end}</span></li>
            </div>
          </div>
        </div>
      </div>
    `;    
  return output;             
}
function locationChange(actVO) {
  let locationArr = ["臺北市", '新北市', '桃園市', '臺中市', "臺南市", "高雄市", "宜蘭縣", "新竹縣", "苗栗縣", "彰化縣",
      "南投縣", "雲林縣", "嘉義縣", "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "基隆市", "新竹市", "嘉義市"];
  for (let i = 0; i < locationArr.length; i++) {
      if (actVO.act_loc === i + 1) {
          actVO.act_loc = locationArr[i];
      }
  };
}
function locVChange(loc){
  let locationArr = ["臺北市", '新北市', '桃園市', '臺中市', "臺南市", "高雄市", "宜蘭縣", "新竹縣", "苗栗縣", "彰化縣",
    "南投縣", "雲林縣", "嘉義縣", "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "基隆市", "新竹市", "嘉義市"];
  for (let i = 0; i < locationArr.length; i++) {
    if (loc === (i+1)) {
      loc = locationArr[i];
    }
  };
  return loc;
}
