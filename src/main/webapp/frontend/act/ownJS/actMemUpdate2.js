const queryActBtn = document.getElementById("queryActBtn");
const search = document.getElementById("search");
const alterActBtn = document.getElementById("alterActBtn");
const saveActBtn = document.getElementById("saveActBtn");
const actNoTest =  document.getElementById('actNoTest');
const submitBtn = document.getElementById("submitBtn");
const tableBody1 = document.getElementById("tableBody1");
const inputNodes = document.getElementsByTagName('input');
const textareaNodes = document.getElementsByTagName('textarea');
const selectNodes = document.getElementsByTagName('select');
const mainContent = document.getElementById('mainContent');

const actNoSearch = document.getElementById("actNoSearch");
const searchMsg = document.querySelector("#searchMsg");

function validateSearch(){
    let flag = true;
    if(actNoSearch.value.length === 0 || actNoSearch.value === "" || actNoSearch.value === null){
        searchMsg.textContent = "請輸入活動編號";
        flag = false;
    }
    return flag;
}
function validate(){
    let flag = true;
    if(actTitle.value.length === 0 || actTitle.value === "" || actTitle.value === null){
        title.textContent = "活動名稱有誤，不得空白";
        flag = false;
    }
    if(address.value.length === 0 || address.value === "" || address.value === null ){
        addr.textContent = "活動地址有誤，不得空白";
        flag = false;
    }
    if(enrollStart.value === null || enrollEnd.value === null 
        || actBegin.value === null || actEnd.value === null || enrollStart.value === "" || enrollEnd.value === "" 
        || actBegin.value === "" || actEnd.value === "" || enrollStart.value === undefined || enrollEnd.value === undefined 
        || actBegin.value === undefined || actEnd.value === undefined){
        time.textContent = "任一時間不得空白";
        flag = false;
    }else if(enrollStart.value > enrollEnd.value ||
        enrollEnd.value >  actBegin.value ||
        actBegin.value > actEnd.value){
        time.textContent = 
        "時間有誤，活動報名開始時間不得大於截止時間 / 活動開始時間不得大於結束時間 / 活動報名截止時間不得大於活動開始時間";
        flag = false;
    }

    if(inputFile.length === 0 || inputFile.value === ""){
        image.textContent = "請上傳一張照片"
        flag = false;
    }
    if(typeOption.value === null || typeOption.value > 7 || typeOption.value < 1){
        type.textContent = "活動種類請勿空白"
        flag = false;
    }
    if(locOption.value === null || locOption.value > 20 || locOption.value < 1){
        actlocation.textContent = "活動舉辦縣市有誤，不得空白"
        flag = false;
    }
    if(Number(minCount.value) === "" || Number(minCount.value) < 1 || Number(maxCount.value) === "" || Number(maxCount.value) < 1 ||
    Number(minCount.value) > Number(maxCount.value)){
        min.textContent = "最小人數限制有誤，不得大於最大人數限制或空白"
        max.textContent = "最大人數限制有誤，不得小於最小人數限制或空白"       
        flag = false;
    }
    return flag;
}
function actTypeConvert(actVO){
    let typeArr = ["競技", '運動', '美食', '露營', "電影", "社會服務活動", "演場會"];
    for (let i = 0; i < typeArr.length; i++) {
        if (actVO.act_type_no === i + 1) {
            actVO.act_type_no = typeArr[i];
        }
    };
}
function locationConvert(actVO) {
    let locationArr = ["臺北市", '新北市', '桃園市', '臺中市', "臺南市", "高雄市", "宜蘭縣", "新竹縣", "苗栗縣", "彰化縣",
        "南投縣", "雲林縣", "嘉義縣", "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "基隆市", "新竹市", "嘉義市"];
    for (let i = 0; i < locationArr.length; i++) {
        if (actVO.act_loc === i + 1) {
            actVO.act_loc = locationArr[i];
        }
    };
}
function convertLocation(actVO) {
    let locationArr = ["臺北市", '新北市', '桃園市', '臺中市', "臺南市", "高雄市", "宜蘭縣", "新竹縣", "苗栗縣", "彰化縣",
        "南投縣", "雲林縣", "嘉義縣", "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "基隆市", "新竹市", "嘉義市"];
    for (let i = 0; i < locationArr.length; i++) {
        if (actVO.act_loc === locationArr[i]) {
            actVO.act_loc = i+1;
        }
    };
}
function convertActType(actVO){
    let typeArr = ["競技", '運動', '美食', '露營', "電影", "社會服務活動", "演場會"];
    for (let i = 0; i < typeArr.length; i++) {
        if (actVO.act_type_no === typeArr[i] ) {
            actVO.act_type_no = i + 1;
        }
    };
}
function createActTable1(actVO){
    let table = `
    <table class="table table-hover">
        <thead>
            <tr>
                <th class="col-1" scope="col">#活動編號</th>
                <th class="col-1" scope="col">活動標題</th>
            </tr>
        </thead>
        <tbody id="tableBody1">
            <tr>
                <th scope="row">${actVO.act_no}</th>
                <td><input class="inputC" disabled value="${actVO.act_title}"></td>
            </tr>    
        </tbody>
    </table>
    `
    return table;
}
function createMainContent(actVO){
    let mainContentInfo =`
    <div class="col-lg-9">
        <div class="row">
            <h3 class="mt-5 mb-3">
                更新揪團活動 / 目前活動編號：<span id="actNo">${actVO.act_no}</span>
            </h3>
        </div>

        <!-- 內容Start -->
        <div class=" mb-3">
            <h5>活動名稱</h5><span id="title" style="color: red;"></span>
            <input disabled type="text" id="actTitle" name="actTitle" class="form-control" placeholder="Title" value="${actVO.act_title}">
        </div>

        <div class="selectType col-12 d-flex ">
            <div class="col-6">
                <h5>活動種類 / 目前種類：${actVO.act_type_no}</h5><span id="type" style="color: red;"></span>
                <select disabled id="typeOption" class="custom-select select-big mb-3 col-5 d-inline-block">
                    <option value="1">競技</option>
                    <option value="2">運動</option>
                    <option value="3">美食</option>
                    <option value="4">露營</option>
                    <option value="5">電影</option>
                    <option value="6">社會服務活動</option>
                    <option value="7">演場會</option>
                </select>
            </div>

            <div class="col-6 d-inline-block">
                <h5>舉辦縣市／目前舉辦縣市：${actVO.act_loc}</h5><span id="location" style="color: red;"></span>
                <select disabled id="locOption" class="custom-select select-big mb-3 col-5">
                    <option value="1">臺北市</option>
                    <option value="2">新北市</option>
                    <option value="3">桃園市</option>
                    <option value="4">臺中市</option>
                    <option value="5">臺南市</option>
                    <option value="6">高雄市</option>
                    <option value="7">宜蘭縣</option>
                    <option value="8">新竹縣</option>
                    <option value="9">苗栗縣</option>
                    <option value="10">彰化縣</option>
                    <option value="11">南投縣</option>
                    <option value="12">雲林縣</option>
                    <option value="13">嘉義縣</option>
                    <option value="14">屏東縣</option>
                    <option value="15">花蓮縣</option>
                    <option value="16">臺東縣</option>
                    <option value="17">澎湖縣</option>
                    <option value="18">基隆市</option>
                    <option value="19">新竹市</option>
                    <option value="20">嘉義市</option>
                </select>
            </div>
        </div>

        <div class="d-flex">
            <h5 class="">舉辦地址</h5><span id="addr" style="color: red;"></span>
            <div class="col-9">
                <input disabled class="form-control" type="text" id="address" placeholder="地址" value="${actVO.act_pl}">
            </div>
        </div>

        <div><span id="time" style="color: red;"></span></div>
        <div class="col-12 d-flex mt-4">
            <lable for="enrollStart">活動報名起始時間</lable>
            <input disabled type="datetime-local" id="enrollStart" name="time" value="${actVO.act_enroll_begin}">
            <lable for="enrollEnd">活動報名截止時間</lable>
            <input disabled type="datetime-local" id="enrollEnd" name="time" value="${actVO.act_enroll_end}">
        </div>

        <div class="col-12 d-flex mt-4">
            <lable for="actBegin">活動開始時間</lable>
            <input disabled type="datetime-local" id="actBegin" name="time" value="${actVO.act_start}">
            <lable for="actEnd">活動結束時間</lable>
            <input disabled type="datetime-local" id="actEnd" name="time" value="${actVO.act_end}">
        </div>

        <div class="d-flex">
            <h5 class="mt-3">人數最小限制</h5><span id="min" style="color: red;"></span>
            <div class="mt-3 ps-3">
                <input disabled class="form-control" type="text" id="minCount" placeholder="人數最小限制" value="${actVO.act_min_count}">
            </div>
            <h5 class="mt-3">人數最大限制</h5><span id="max" style="color: red;"></span>
            <div class="mt-3">
                <input disabled class="form-control" type="text" id="maxCount" placeholder="人數最大限制" value="${actVO.act_max_count}">
            </div>
        </div>

        <div class="mt-3">
            <lable>活動說明</lable><span id="actDescribe" style="color: red;"></span>
            <textarea disabled id="actContent" name="actContent" class="form-control" placeholder="活動說明" rows="5" >${actVO.act_content}</textarea>
        </div>

        <div class="mb-3 mt-4">
            <h6>目前活動圖片</h6>
                <div><img src="/CGA103G1/getOneActPic?action=actImg&actNo=${actVO.act_no}" class="img-fluid" alt="圖片"></div>
            <h5><label for="inputFile">更新活動圖片</label></h5>
            <span id="image" style="color: red;"></span>
            <input disabled type="file" id="inputFile" name="inputFile" accept="image/png, image/jpeg">
        </div>

        <div>
            <h4 style="color: red;" id="msg"></h4>
        </div>
    </div>
    `;
    return mainContentInfo;
}

queryActBtn.addEventListener('click',function(){
    let outputHtml1 = "";
    fetch('/CGA103G1/getActHost',{
        method:"POST",
        headers:{
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json, application/x-www-form-urlencoded, multipart/form-data',
        }
    })
    .then(res => res.json())
    .then(actVOArray =>{
        console.log(actVOArray);
        if(actVOArray === "您目前無任何主辦活動"){
            let msg = `
            <h1 class="text-center" style="color: red;">${actVOArray}</h1>
        `;
            mainContent.insertAdjacentHTML("beforeend", msg);
        }else{
            actVOArray.forEach(actVO => {
                outputHtml1 += createActTable1(actVO);
            });
            mainContent.innerHTML = outputHtml1;
        }
    })
});

search.addEventListener('click', function(){ 
    if(validateSearch()){
       searchMsg.textContent = "";
       fetch('/CGA103G1/getMemOneAct',{
           method:"POST",
           headers:{
               'Accept': 'application/json, text/plain, */*',
               'Content-Type': 'application/json, application/x-www-form-urlencoded, multipart/form-data',
           },
           body:
           JSON.stringify({
               act_no: actNoSearch.value,
           })
       })
       .then(res => res.json())
       .then(actVo => {
           let output = "";
           console.log(actVo);
           if(actVo === "查無此揪團活動編號"){
                let msg = `
                <h1 class="text-center" style="color: red;">${actVo}</h1>
                `;
                mainContent.innerHTML = msg; 
            }else if(actVo === "該活動非您主辦，請查詢編號再次輸入"){
                let msg = `
                <h1 class="text-center" style="color: red;">${actVo}</h1>
                `;
                mainContent.innerHTML = msg; 
            }else{
               actTypeConvert(actVo);
               locationConvert(actVo);
            //    actNoTest.textContent = actVo.act_no;
               output = createMainContent(actVo);
               mainContent.innerHTML = output;
           }
       });
    }else{
        return;
    }

    const actNo = document.getElementById('actNo');
    const msg = document.getElementById('msg');
    const actTitle = document.getElementById("actTitle");
    const actContent = document.getElementById("actContent");
    const typeOption = document.getElementById("typeOption");
    const locOption = document.getElementById("locOption");
    const address = document.getElementById("address");
    const enrollStart = document.getElementById("enrollStart");
    const enrollEnd = document.getElementById("enrollEnd");
    const actBegin = document.getElementById("actBegin");
    const actEnd = document.getElementById("actEnd");
    const minCount = document.getElementById("minCount");
    const maxCount = document.getElementById("maxCount");
    const inputFile = document.getElementById("inputFile");

    //  錯誤訊息 tag
    const title = document.querySelector("#title");
    const type = document.querySelector("#type");
    const actlocation = document.querySelector("#location");
    const addr = document.querySelector("#addr");
    const time = document.querySelector("#time");
    const min = document.querySelector("#min");
    const max = document.querySelector("#max");
    const actDescribe = document.querySelector("#actDescribe");
    const image = document.querySelector("#image");
});

alterActBtn.addEventListener('click', function(){
    Array.from(inputNodes).forEach(node => node.removeAttribute('disabled'));
    Array.from(textareaNodes).forEach(node => node.removeAttribute('disabled'));
    Array.from(selectNodes).forEach(node => node.removeAttribute('disabled'));
});

saveActBtn.addEventListener('click', function(){
    Array.from(inputNodes).forEach(node => node.setAttribute('disabled',''));
    Array.from(textareaNodes).forEach(node => node.setAttribute('disabled',''));
    Array.from(selectNodes).forEach(node => node.setAttribute('disabled',''));
});

submitBtn.addEventListener("click", function(e){
    e.preventDefault();
    if(validate()){
        fetch('/CGA103G1/updateActCondition',
        {
            method:"POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json, application/x-www-form-urlencoded, multipart/form-data',
            },
            body:
            JSON.stringify({
                act_no: actNo.textContent,
                act_title: actTitle.value,
                act_content: actContent.value,
                act_type_no: typeOption.value,
                act_loc: locOption.value,
                act_pl: address.value,
                act_enroll_begin: (enrollStart.value).replace("T"," "),
                act_enroll_end: (enrollEnd.value).replace("T"," "),
                act_start: (actBegin.value).replace("T"," "),
                act_end: (actEnd.value).replace("T"," "),
                act_min_count: Number(minCount.value),
                act_max_count: Number(maxCount.value),
            }),
        })
        .then(res => {
            if(res.ok){
            msg.className = "success";
            msg.textContent = "更新揪團活動成功";
            return res.json();
            }else{
            msg.className = "fail";
            msg.textContent = "更新揪團活動失敗";
            return;
            }
        })
        .then(() =>{ 
            fetch("/CGA103G1/updateActImage",
                {
                    method:"POST",
                    headers:{
                        'Accept': 'application/json, text/plain, */*',
                        'Content-Type': 'multipart/form-data',
                    },
                    body:inputFile.files[0]
                })
        })
        .catch(
            error => console.log(error)
        );
    }
});
