
fetch("/CGA103G1/getOneAct",
    {
        method:"GET",
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json, application/x-www-form-urlencoded, multipart/form-data'
        }
    })
    .then(res => res.json())
    .then((actVO) => {
        console.log(actVO)
        let output = "";
        let detailAct = document.getElementById("detailAct");
        output += detailInfo(actVO, output);
        detailAct.innerHTML = output;
    })
    .catch(
        err => console.log(err)
    );

function detailInfo(actVO, output){
      output =
      `  <ul class="nav nav-tabs tab-line">
          <li class="nav-item">
            <a class="nav-link active " data-toggle="tab" href="#tab-de-1" checked>
              活動標題${actVO.act_title}／揪團活動編號：${actVO.act_no}  
            </a>
          </li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane active" id="tab-de-1">
            <div class="text-block NopaddingDetails">
              <h5 class="mb-4">活動詳細說明</h5>
              <p class="text-muted font-weight-light">
                活動內容${actVO.act_content}
              </p>
            </div>
            <div class="">
             
            </div>
            <div class="text-block">
    
              <h5 class="mb-4">活動圖片</h5>
              <div class="row gallery ml-n1 mr-n1">
                <div class="col-12 px-1 mb-2">
                  <a href="#">
                    <img src="/CGA103G1/getOneActPic?action=actImg&actNo=${actVO.act_no}" alt="imagesloaded" class="img-fluid" />
                  </a>
                </div>           
              </div>
            </div>
    
            <div class="text-block">
              <h5 class="mb-4">各項說明</h5>
              <div class="row">
                <div class="col-md-6">
    
                  <ul class="list-unstyled text-muted">
                    <li class="mb-2">
                      <i class="fa-solid fa-person-arrow-down-to-line text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">人數最小限制：${actVO.act_min_count}</span>
                    </li>
                    <li class="mb-2">
                      <i class="fa-solid fa-calendar-week text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">活動報名起始日：${actVO.act_enroll_begin}</span>
                    </li>
                    <li class="mb-2">
                      <i class="fa-solid fa-calendar-week text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">活動開始日：${actVO.act_start}</span>
                    </li>
                    <li class="mb-2">
                      <i class="fa-solid fa-location-pin-lock text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">詳細地址：${actVO.act_pl}</span>
                    </li>
                  </ul>
    
                </div>
                
                <div class="col-md-6">
                  <ul class="list-unstyled text-muted">
                    <li class="mb-2">
                      <i class="fa-solid fa-person-arrow-up-from-line text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">人數最大限制：${actVO.act_max_count}</span>
                    </li>
                    <li class="mb-2">
                      <i class="fa-solid fa-calendar-week text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">活動報名截止日：${actVO.act_enroll_end}</span>
                    </li>
                    <li class="mb-2">
                      <i class="fa-solid fa-calendar-week text-secondary w-1rem mr-3 text-center"></i>
                      <span class="text-sm">活動結束日：${actVO.act_end}</span>
                    </li>
                    <li class="mb-2">
                    <i class="fa-solid fa-people-group text-secondary w-1rem mr-3 text-center"></i>
                    <span class="text-sm">目前報名人數：${actVO.act_current_count}</span>
                    </li>             
                  </ul>
                </div>
              </div>
            </div>          
          </div>
      `;
        return output;
}