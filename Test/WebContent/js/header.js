var menu = document.querySelector(".menu");
var drop = document.querySelector(".drop");
function showHeader(){
    var hidden = document.querySelector(".hide");
    var check  = window.getComputedStyle(hidden).getPropertyValue("display");

    if(check==="none"){
        hidden.style.display="block";
    }
}
function hideHeader(){
    var hidden = document.querySelector(".hide");
    var check  = window.getComputedStyle(hidden).getPropertyValue("display");

    if(check==="block"){
        hidden.style.display="none";
    }
}

menu.addEventListener("mouseover",showHeader);
drop.addEventListener("mouseover",showHeader);
menu.addEventListener("mouseleave",hideHeader);
drop.addEventListener("mouseleave",hideHeader);



