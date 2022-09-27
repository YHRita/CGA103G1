const queryActBtn = document.getElementById("queryActBtn");
const tableBody = document.getElementById("tableBody");
const tableMain = document.getElementById("tableMain");

function createActTable(actVO){
    let table = `
   <tr>
       <th scope="row">${actVO.act_no}</th>
       <td>${actVO.act_type_no}</td>
       <td>${actVO.act_title}</td>
       <td>${actVO.act_content}</td>
       <td>${actVO.act_loc}</td>
       <td>${actVO.act_pl}</td>
       <td>${actVO.act_start}</td>
       <td>${actVO.act_end}</td>
       <td>${actVO.act_current_count}</td>
   </tr>
    `
    return table;
}

queryActBtn.addEventListener('click',function(){
    let outputHtml = "";
    fetch('/CGA103G1/getOwnAllAct',{
        method:"POST",
        headers:{
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json, application/x-www-form-urlencoded, multipart/form-data',
        }
    })
    .then(res => res.json())
    .then(actVOArray =>{
        console.log(actVOArray);
        if(actVOArray === "目前您無參與任何活動"){
            let msg = `
                <h1 class="text-center" style="color: red;">${actVOArray}</h1>
            `;
            outputHtml = msg;
            tableMain.insertAdjacentHTML('afterend',outputHtml);
        }else{
            actVOArray.forEach(actVO => {
                locationChange(actVO);
                outputHtml += createActTable(actVO);
            });
            tableBody.insertAdjacentHTML('beforeend',outputHtml);
        }
        console.log(outputHtml);
    })
});

function locationChange(actVO) {
    let locationArr = ["臺北市", '新北市', '桃園市', '臺中市', "臺南市", "高雄市", "宜蘭縣", "新竹縣", "苗栗縣", "彰化縣",
        "南投縣", "雲林縣", "嘉義縣", "屏東縣", "花蓮縣", "臺東縣", "澎湖縣", "基隆市", "新竹市", "嘉義市"];
    for (let i = 0; i < locationArr.length; i++) {
        if (actVO.act_loc === i + 1) {
            actVO.act_loc = locationArr[i];
        }
    };
}
