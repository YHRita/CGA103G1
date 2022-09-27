const submitBtn = document.getElementById('submitBtn');

let msg = document.getElementById('msg');
let actTitle = document.getElementById("actTitle");
let actContent = document.getElementById("actContent");
let typeOption = document.getElementById("typeOption");
let locOption = document.getElementById("locOption");
let address = document.getElementById("address");
let enrollStart = document.getElementById("enrollStart");
let enrollEnd = document.getElementById("enrollEnd");
let actBegin = document.getElementById("actBegin");
let actEnd = document.getElementById("actEnd");
let minCount = document.getElementById("minCount");
let maxCount = document.getElementById("maxCount");
let inputFile = document.getElementById("inputFile");
// let timeTest = document.getElementById("timeTest");
let minCountV = Number(minCount.value);
let maxCountV = Number(maxCount.value);

//  錯誤訊息 tag
let title = document.querySelector("#title");
let type = document.querySelector("#type");
let actlocation = document.querySelector("#location");
let addr = document.querySelector("#addr");
let time = document.querySelector("#time");
let min = document.querySelector("#min");
let max = document.querySelector("#max");
let actDescribe = document.querySelector("#actDescribe");
let image = document.querySelector("#image");

function validate(){
    let flag = true;
    if(actTitle.value.length === 0 || actTitle.value === "" || actTitle.value === null){
        title.textContent = "活動名稱有誤或不得空白";
        flag = false;
    }
    if(actContent.value.length === 0 || actContent.value === "" || actContent.value === null){
        actDescribe.textContent = "活動內容有誤或不得空白";
        flag = false;
    }
    if(address.value.length === 0 || address.value === "" || address.value === null ){
        addr.textContent = "活動地址有誤或不得空白";
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
    if(typeOption.value === null || typeOption.value > 7 || typeOption.value< 1){
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

submitBtn.addEventListener('click', function(e){
    e.preventDefault();
    if(validate()){
        fetch("/CGA103G1/createAct",
        {
            method:"POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json, application/x-www-form-urlencoded, multipart/form-data',
            },
            body:
            JSON.stringify({
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
            msg.textContent = "創建揪團活動成功";
            return res.json();
            }else{
            msg.className = "fail";
            msg.textContent = "創建揪團活動失敗";
            return;
            }
        })
        .then(() =>{ 
            fetch("/CGA103G1/uploadActImage",
            {
                method:"POST",
                headers:{
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'multipart/form-data',
                },
                body:inputFile.files[0]
            })
            // const file = inputFile.files[0];
            // const formdata = new FormData();
            // formdata.append('file',file);
            // fetch("/CGA103G1/uploadActImage",
            // {
            //     method:"POST",
            //     headers:{
            //         'Accept': 'application/json, text/plain, */*',
            //         'Content-Type': 'multipart/form-data',
            //     },
            //     body:formdata
            // })
        })
        .then(()=>{
            let inputs = document.querySelectorAll('input, textarea, #uploadImg');
            inputs.forEach(input => {
            input.value = '';
            });   
        })
        .catch(
            error => console.log(error)
        );      
    }  
});