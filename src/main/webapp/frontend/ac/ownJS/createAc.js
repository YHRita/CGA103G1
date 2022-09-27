const submitBtn = document.getElementById('submitBtn');
let inputFile = document.getElementById("inputFile");
let msg = document.getElementById('msg');
let title = document.getElementById('title');
let content = document.getElementById('content');
let customRadio = document.getElementById('customRadio');
let titleMsg = document.getElementById('titleMsg');
let radioMsg = document.getElementById('radioMsg');
let contentMsg = document.getElementById('contentMsg');
let imageMsg = document.getElementById('imageMsg');

function validate(){
    let flag = true;
    if(title.value.length === 0 || title.value === "" || title.value === null){
        titleMsg.textContent = "文章名稱有誤或不得空白";
        flag = false;
    }
    if(content.value.length === 0 || content.value === "" || content.value === null){
        contentMsg.textContent = "內容有誤或不得空白";
        flag = false;
    }
    
    if(inputFile.length === 0 || inputFile.value === ""){
        imageMsg.textContent = "請上傳一張照片"
        flag = false;
    }
    if(customRadio.value === null || customRadio.value > 9 || customRadio.value < 1){
        radioMsg.textContent = "活動種類請勿空白"
        flag = false;
    }

    return flag;
}

submitBtn.addEventListener('click', function(){
    if(validate()){
            fetch("/CGA103G1/createAc",{
                method:"POST",
                headers:{
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'application/json, text/plain, */*, multipart/form-data',
                },
                body:
                JSON.stringify({
                    ac_title: title.value,
                    ac_content: content.value,
                    ac_type_no: customRadio.value,
                }),
            })
            .then(res => res.ok? msg.textContent="成功":msg.textContent="失敗")
            .then(()=>{
                    fetch("/CGA103G1/uploadAcImage",
                    {
                        method:"POST",
                        headers:{
                            'Accept': 'application/json, text/plain, */*',
                            'Content-Type': 'multipart/form-data',
                        },
                        body:inputFile.files[0]
                    })
                }
            )
        }
    }
)

