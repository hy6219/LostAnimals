/**
 * 
 */
//최외곽
//var outer = document.querySelector(".attach-apply-content");
var target = document.querySelector(".register-content");
//대댓글 틀이 복제될 위치를 위함
//var base= document.querySelector(".reply-content-container");
//대댓글 처리
var regRpToThis = document.getElementsByClassName(".register-rp-to-this");
//복사할 컨테이너
//var targetContainer = document.querySelector(".reply-content");
var focusing = "FOCUSING";
var ord = document.getElementsByName("reparent-order")[0];
	//몇 번째 버튼인지 확인하기
var btn = document.querySelectorAll(".register-rp-to-this");
var idx = 0;
var mode = document.querySelector("#changeMode");
var upBtn= document.querySelectorAll(".update-on-this");
var delBtn =document.querySelectorAll(".delete-on-this"); 
var id    = document.querySelector(".id");

function regReply(e){
	target.classList.toggle(focusing);
	
	if(target.classList.contains(focusing)){
		target.focus();
		target.style.boxShadow="0 0 10px dodgerblue";
		mode.value="reReplyLostAnimal";
	}
}

function cancelCk(){
	target.classList.remove(focusing);
	target.style.boxShadow="none";
}

function handlerCheck(e){
	console.log(btn.length);
	for(var i = 0 ; i < btn.length; i++){
		if(e===btn[i]){
			ord.value=i;
			//alert(`${ord.value}`);
			console.log(`ord: ${ord.value}, i: ${i}`);
			return;
		}
	}
}

function handleUpdate(e){
	for(var i = 0 ; i < upBtn.length; i++){
		if(e===upBtn[i]){
			ord.value=i;
			alert(`${ord.value}`);
			console.log(`ord: ${ord.value}, i: ${i}`);
			return;
		}
	}
}

function updateContent(e,writer,content){
	mode.value="updateReplyOnRegLost";
	alert("내용을 수정해주세요!");
	id.value=writer;
	id.readOnly=true;
	id.style.backgroundColor="lightgray";
	target.value=content;
	target.focus();
}

function handleDelete(e){
	for(var i = 0 ; i < delBtn.length; i++){
		if(e===delBtn[i]){
			ord.value=i;
			alert(`${ord.value}`);
			console.log(`ord: ${ord.value}, i: ${i}`);
			return;
		}
	}
}

function deleteContent(e,boardId, num){
	//mode.value="";
	location.href=`lost.do?command=deleteReplyOnReg&boardId=${boardId}&num=${num}&ordIdx=${ord.value}`;
}


//	ord.value=val;
	//console.log(val);

//regRpToThis.addEventListener("click",regReply);
target.addEventListener("click",cancelCk);