/**
 * init 初始化 拿 cookie
 * 需載入 axios cdn
 * <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
 */

function init(){ 
	axios({
 		"method": "post", 
 		"url": "/CGA103G1/cart/initCart.do"
	}).then(function (response) {
    	//console.log(response);
	}).catch(function (error) {
    	console.log(error);
	});
}

// 當畫面載入時初始化
window.onload = init();
