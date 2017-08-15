function fun_fixednavbar(){
	if($("#fixednavbar").is(":checked")){$(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top");$("body").removeClass("boxed-layout");$("body").addClass("fixed-nav");$("#boxedlayout").prop("checked",false);if(localStorageSupport){localStorage.setItem("boxedlayout","off")}if(localStorageSupport){localStorage.setItem("fixednavbar","on")}}
	else{$(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top");$("body").removeClass("fixed-nav");if(localStorageSupport){localStorage.setItem("fixednavbar","off")}}
}
function fun_collapsemenu(){
	if($("#collapsemenu").is(":checked")){$("body").addClass("mini-navbar");SmoothlyMenu();if(localStorageSupport){localStorage.setItem("collapse_menu","on")}}
	else{$("body").removeClass("mini-navbar");SmoothlyMenu();if(localStorageSupport){localStorage.setItem("collapse_menu","off")}}
}
function fun_boxedlayout(){
	if($("#boxedlayout").is(":checked")){$("body").addClass("boxed-layout");$("#fixednavbar").prop("checked",false);$(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top");$("body").removeClass("fixed-nav");if(localStorageSupport){localStorage.setItem("fixednavbar","off")}if(localStorageSupport){localStorage.setItem("boxedlayout","on")}}
	else{$("body").removeClass("boxed-layout");if(localStorageSupport){localStorage.setItem("boxedlayout","off")}}
}
function fun_skin_0(){
	$("body").removeClass("skin-1");$("body").removeClass("skin-2");$("body").removeClass("skin-3");if (localStorageSupport) {localStorage.setItem("skin",'0');}
}
function fun_skin_1(){
	$("body").removeClass("skin-2");$("body").removeClass("skin-3");$("body").addClass("skin-1");if (localStorageSupport) {localStorage.setItem("skin",'1');}
}
function fun_skin_3(){
	$("body").removeClass("skin-1");$("body").removeClass("skin-2");$("body").addClass("skin-3");if (localStorageSupport) {localStorage.setItem("skin",'3');}
}
function skinConfig(){
	layer.open({
	    type: 1,
	    content: '<div class="skin-setttings"><div class="title">主题设置</div>'
        +'<div class="setings-item"><span>收起左侧菜单</span><div class="switch"><div class="onoffswitch"><input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu" onclick="fun_collapsemenu()"><label class="onoffswitch-label" for="collapsemenu"><span class="onoffswitch-inner"></span><span class="onoffswitch-switch"></span></label></div></div></div>'
        +'<div class="setings-item"><span>固定顶部</span><div class="switch"><div class="onoffswitch"><input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar" onclick="fun_fixednavbar()"><label class="onoffswitch-label" for="fixednavbar"><span class="onoffswitch-inner"></span><span class="onoffswitch-switch"></span></label></div></div></div>'
        +'<div class="setings-item"><span>固定宽度</span><div class="switch"><div class="onoffswitch"><input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout" onclick="fun_boxedlayout()"><label class="onoffswitch-label" for="boxedlayout"><span class="onoffswitch-inner"></span><span class="onoffswitch-switch"></span></label></div></div></div>'
        +'<div class="title">皮肤选择</div>'
        +'<div class="setings-item default-skin"><span class="skin-name"><a href="javascript:fun_skin_0();" class="s-skin-0">默认皮肤</a></span></div>'
        +'<div class="setings-item blue-skin"><span class="skin-name"><a href="javascript:fun_skin_1();" class="s-skin-1">蓝色主题</a></span></div>'
        +'<div class="setings-item yellow-skin"><span class="skin-name"><a href="javascript:fun_skin_3();" class="s-skin-3">黄色主题</a></span></div></div>'
	});
	if(localStorageSupport){
		var collapse=localStorage.getItem("collapse_menu");
		var fixednavbar=localStorage.getItem("fixednavbar");
		var boxedlayout=localStorage.getItem("boxedlayout");
		var skin = localStorage.getItem("skin");
		if(collapse=="on"){$("#collapsemenu").prop("checked","checked")}
		if(fixednavbar=="on"){$("#fixednavbar").prop("checked","checked")}
		if(boxedlayout=="on"){$("#boxedlayout").prop("checked","checked")}
	}
}

/** main页面方法*/
$(function () {
	createWebSocket("/noticeHandler", 'showIdCount');
});

function showIdCount(data) {
	var counts = data.split('@');
	if(Number(counts[0]) > 0){
		$('#notice_id_count').html(counts[0]);
		$('#notice_id_count').addClass("label label-warning");
	}
	if(Number(counts[1]) > 0){
		$('#sysnews_id_count').html(counts[1]);
		$('#sysnews_id_count').addClass("label label-primary");
	}
}

function clearCount(id){
	$('#'+ id).html("");
	$('#' + id).removeClass("label label-warning");
}