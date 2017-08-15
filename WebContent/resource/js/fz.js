				window.onload=function(){	
	function $(id){
	return document.getElementById(id);
}

function scroll(){
	if (window.pageXOffset!=null) {
		return{
			left:window.pageXOffset,
			top:window.pageYOffset
		}
	}
	else if (document.compatMode=="CSS1Compat") {
		return {
			left:document.documentElement.scrollLeft,
			top:document.documentElement.scrollTop
		}
	}
	return{
		left:document.body.scrollLeft,
		top:document.body.scrollTop
	}
}
	
		var pic=$("pic");
		var leader=0,target=0;
		var timer=null;
		var top=pic.offsetTop;
		window.onscroll=function(){
			clearInterval(timer);
			target=scroll().top+top;
			timer=setInterval(function(){
				leader=leader+(target-leader)/10;
				pic.style.top=leader+"px";
			},30)
		}	
		}