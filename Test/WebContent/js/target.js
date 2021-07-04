/**
 * 
 */
//최외곽
//var outer = document.querySelector(".attach-apply-content");
var target = document.querySelector(".register-content");
//대댓글 틀이 복제될 위치를 위함
//var base= document.querySelector(".reply-content-container");
//대댓글 처리
var regRpToThis = document.querySelector(".register-rp-to-this");
//복사할 컨테이너
//var targetContainer = document.querySelector(".reply-content");
var focusing = "FOCUSING";
var ord = document.getElementsByName("reparent-order")[0];
	//몇 번째 버튼인지 확인하기
var btn = document.querySelectorAll(".register-rp-to-this");
var idx = 0;


function regReply(e){
	target.classList.toggle(focusing);
	
	if(target.classList.contains(focusing)){
		target.focus();
		target.style.boxShadow="0 0 10px dodgerblue";
	}
}

function cancelCk(){
	target.classList.remove(focusing);
	target.style.boxShadow="none";
}

function handlerCheck(val){
	ord.value=val;
	console.log(val);
}

//regRpToThis.addEventListener("click",regReply);
target.addEventListener("click",cancelCk);