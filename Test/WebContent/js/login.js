/**
 * 
 */
var user = document.querySelector("#loginUser");
var val = user.innerText;
var hide = document.querySelector("#loginHide");
var show = document.querySelector("#loginShow");

function nonLoginBtn(){
	
	if(val!==""){
		//로그인 된 유저가 있다면 버튼 숨기기
		hide.style.display="none";
	}else{
		hide.style.display="block";
	}
}

function duringLoginBtn(){
	if(val!==""){
		//로그인 된 유저가 있다면 버튼 보이기
		show.style.display="show";
	}else{
		show.style.display="none";
	}
}

nonLoginBtn();
duringLoginBtn();