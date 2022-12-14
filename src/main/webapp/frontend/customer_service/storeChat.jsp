<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.store.*" %>

<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
* {
	margin: auto;
	padding: 0px;
}

html, body {
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif,
		Microsoft JhengHei;
	width: 90%;
	height: 90%;
	background: lightblue;
}

.panel {
	float: left;
	border: 2px solid #0078ae;
	border-radius: 5px;
	width: 100%;
}

.message-area {
	height: 70%;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
	background-color: #ffffff;
}

.input-area {
	background: #0078ae;
	box-shadow: inset 0 0 10px #00568c;
}

.input-area input {
	margin: 0.5em 0em 0.5em 0.5em;
}

.text-field {
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
}

h1 {
	font-size: 1.5em;
	padding: 5px;
	margin: 5px;
}

#message {
	min-width: 50%;
	max-width: 60%;
}

.statusOutput {
	background: #0078ae;
	text-align: center;
	color: #ffffff;
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
	width: 30%;
	margin-top: 10%;
	margin-left: 60%;
}

/* #row { */
/* 	float: left; */
/* 	width: 50%; */
/* } */
/* column = 旁邊的選擇列 */
/* .column { */
/* 	float: left; */
/* 	width: 50%; */
/* 	padding: 5%; */
/* 	margin-bottom: 5px; */
/* 	background-color: #ffffff; */
/* } */
ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul li {
	display: inline-block;
	clear: both;
	padding: 20px;
	border-radius: 30px;
	margin-bottom: 2px;
	font-family: Helvetica, Arial, sans-serif;
}

.friend {
	background: #eee;
	float: left;
}

.me {
	float: right;
	background: #0084ff;
	color: #fff;
}

.friend+.me {
	border-bottom-right-radius: 5px;
}

.me+.me {
	border-bottom-right-radius: 30px;
}
</style>
<title>客服聊天室</title>
</head>
<body onload="connect();" onunload="disconnect();">
	<h1 id="title">線上客服人員-上線</h1>
	<h3 id="statusOutput" class="statusOutput" visibility: hidden></h3>
	<div id="row"></div>
	<div id="messagesArea" class="panel message-area"></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text"
			placeholder="Message"
			onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
			type="submit" id="sendMessage" class="button" value="Send"
			onclick="sendMessage();" />
	</div>
</body>
<script>
	
	var MyPoint = "/FriendWS/${storeSvc.getOneStoreByAcc(store_acc).store_name}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = "${storeSvc.getOneStoreByAcc(store_acc).store_name}";
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me'
							: li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me'
						: li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}

		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = "MEMORY客服";
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var limitBox = 'MEMORY客服';
		addListener();
		
		for (var i = 0; i < friends.length; i++) {
			if (!(friends[i] === limitBox)) {	
			document.getElementById('message').disabled=true;	
			document.getElementById('message').style.visibility = 'hidden';			
			document.getElementById('title').innerHTML = '線上客服人員-下線';
			document.getElementById('submitMsg').style.visibility = 'hidden';
						
			} else {
			document.getElementById('message').disabled=false;			
			document.getElementById('message').style.visibility = 'visible';	
			document.getElementById('title').innerHTML = '線上客服人員-上線';
			document.getElementById('submitMsg').style.visibility = 'visible';
			
			}
		}
		
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var friend = 'MEMORY客服';
		updateFriendName(friend);
		var jsonObj = {
			"type" : "history",
			"sender" : self,
			"receiver" : friend,
			"message" : "",
		};
		webSocket.send(JSON.stringify(jsonObj));
		
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>