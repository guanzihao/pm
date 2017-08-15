var aaa=false;
var bbb=false;
var ccc=false;
var ddd=false;
var basePath="/pm";
var isDelegation=0;
var isDelegationIdWmEc=0;
$(function(){
		isDelegation=$("#isDelegationId").val();
		isDelegationIdWmEc=$("#isDelegationIdWmEc").val();
		$(".wm2-3").hide();
		$(".wm3-3").hide();
		$(".wm4-3").hide();
		$(".wm5-3").hide();
});
$(".wm1-2").click(function() {
	$(".wmForm").validate();
	if($(".wmForm").valid()&&aaa){
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_c1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
		$(".wm4-2").attr("src", basePath+"/resource/images/icon_f4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
		$(".wm1-3").show();
		$(".wm2-3").hide();
		$(".wm3-3").hide();
		$(".wm4-3").hide();
		$(".wm5-3").hide();
	}

});
$(".wm2-2").click(function() {
	$(".wmForm").validate();
	if($(".wmForm").valid()&&bbb){
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_c2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
		$(".wm4-2").attr("src",basePath+ "/resource/images/icon_f4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
		$(".wm1-3").hide();
		$(".wm2-3").show();
		$(".wm3-3").hide();
		$(".wm4-3").hide();
		$(".wm5-3").hide();
	}

});
$(".wm3-2").click(function() {
	$(".wmForm").validate();
	if($(".wmForm").valid()&&bbb&&aaa){
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_c3.png");
		$(".wm4-2").attr("src", basePath+"/resource/images/icon_f4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
		$(".wm1-3").hide();
		$(".wm2-3").hide();
		$(".wm3-3").show();
		$(".wm4-3").hide();
		$(".wm5-3").hide();
	}

});

$(".wm4-2").click(function() {
	$(".wmForm").validate();
	if($(".wmForm").valid()&&bbb&&aaa&&ccc){
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
		$(".wm4-2").attr("src", basePath+"/resource/images/icon_c4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
		$(".wm1-3").hide();
		$(".wm2-3").hide();
		$(".wm3-3").hide();
		$(".wm4-3").show();
		$(".wm5-3").hide();
	}

});
$(".wm5-2").click(function() {
	$(".wmForm").validate();
	if($(".wmForm").valid()&&bbb&&aaa&&ccc&&ddd){
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
		$(".wm4-2").attr("src", basePath+"/resource/images/icon_f4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_c5.png");
		$(".wm1-3").hide();
		$(".wm2-3").hide();
		$(".wm3-3").hide();
		$(".wm4-3").hide();
		$(".wm5-3").show();
	}
	
});
$(".wm1-4").click(function() {
	$(".wmForm").validate();
	 if($(".wmForm").valid()){
		 if (isDelegation=='1'||isDelegationIdWmEc=='1') {
			 $(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
			 $(".wm4-2").attr("src", basePath+"/resource/images/icon_c2.png");
			 $(".wm1-3").hide();
			 $(".wm4-3").css('display','block'); 

		}else{
			$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
			$(".wm2-2").attr("src", basePath+"/resource/images/icon_c2.png");
			$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
			$(".wm4-2").attr("src", basePath+"/resource/images/icon_f4.png");
			$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
			$(".wm1-3").hide();
			$(".wm2-3").css('display','block'); 
			$(".wm3-3").hide();
			$(".wm4-3").hide();
			$(".wm5-3").hide();
			aaa=true;
		}
	 }
});
$(".wm2-4").click(function() {
	$(".wmForm").validate();
	 if($(".wmForm").valid()){
		 huoQuShuJu();
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_c3.png");
		$(".wm4-2").attr("src", basePath+"/resource/images/icon_f4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
		$(".wm1-3").hide();
		$(".wm2-3").hide();
		$(".wm3-3").css('display','block'); 
		$(".wm4-3").hide();
		$(".wm5-3").hide();
		bbb=true;
	 }
});
$(".wm3-4").click(function() {
	$(".wmForm").validate();
	 if($(".wmForm").valid()){
		$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
		$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
		$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
		$(".wm4-2").attr("src", basePath+"/resource/images/icon_c4.png");
		$(".wm5-2").attr("src", basePath+"/resource/images/icon_f5.png");
		$(".wm1-3").hide();
		$(".wm2-3").hide();
		$(".wm3-3").hide();
		$(".wm4-3").css('display','block'); 
		$(".wm5-3").hide();
		ccc=true;
	 }
});

$(".wm4-4").click(function() {
	$(".wmForm").validate();
	 if($(".wmForm").valid()){
			if (isDelegation=='1'||isDelegationIdWmEc=='1') {
				 $(".wm1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				 $(".wm4-2").attr("src", basePath+"/resource/images/icon_f2.png");
				 $(".wm1-3").show();
				 $(".wm4-3").css('display','none'); 
		
			}else{
				$(".wm1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wm2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wm3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wm4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wm5-2").attr("src", basePath+"/resource/images/icon_c5.png");
				$(".wm1-3").hide();
				$(".wm2-3").hide();
				$(".wm3-3").hide();
				$(".wm4-3").hide();
				$(".wm5-3").css('display','block'); 
				ddd=true;
			}
	 }
});
//提交订单
$(".submit").on("click",function(){
	huoQuShuJu();
	$("#wmForm").submit();
	
});
