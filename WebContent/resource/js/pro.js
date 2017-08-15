	$(function() {
				
		

		var h3=$("#firstpane").children("h3");
		h3.click(function(){
			 $(this).addClass("current_menu");
       $(this).siblings().removeClass("current_menu");
		})
		
		
		


//侧导航
  		var h3=$("#firstpane").children("h3");
		h3.click(function(){
			 $(this).addClass("current_menu");
       $(this).siblings().removeClass("current_menu");
		})
	
  //弹窗		

	$('.fun_btn').click(function(){
		$(this).addClass('fun_on');
		$(this).siblings().removeClass('fun_on');
		var v = $(this).attr('value');
		$(".box_con").each(function(){
			if($(this).attr('value') == v){
				$(this).css("display","block");
			$("#op").css("display","block");
			}

		})
	})
	$('.fun_close').click(function(){
		var s = $(".fun_btn").attr('value');
		$(".fun_close").each(function(){
			if($(this).attr('value') == s){
				$(".box_con").css("display","none");
				$('.fun_btn').removeClass('fun_on');
			$("#op").css("display","none");
			}

		})
	})


//历史考勤记录弹窗
    $("#history").click(function(){
	$("#show5").css("display","block");
		$("#show4").css("display","none");
	$("#open_num").addClass("fun_on");
	$("#op").css("display","block");
	 return false;
})
$("#closed").click(function(){
	$("#show5").css("display","none");
	$("#open_num").removeClass("fun_on");
	$("#op").css("display","none");	
	 return false;

})  


  
  $(".inp").hover(function(){
  	$("#iconf").css("color","#38A4FC");
  },function(){
  $("#iconf").css("color","");	
  }
  );
   $(".inp1").hover(function(){
  	$("#iconf1").css("color","#38A4FC");
  },function(){
  $("#iconf1").css("color","");	
  }
   );
   
   $(".SearchEngine").click(function(){
   	  $(".SearchEngine").addClass("fun_on_d");
   	$(".all_num").css("display","block");
   })
  
  



	$("#no_access").click(function(){
		$("#show5").css("display","none");
						$(".noaccess").css("display","block");
			$("#op").css("display","block");
	})
$(".sure").click(function(){
							$(".noaccess").css("display","none");
			$("#op").css("display","none");
})
$("").click(function(){
							$(".noaccess").css("display","none");
			$("#op").css("display","none");
})
//未填项目提示

$(".blank_hint").hide();
$("#apply_for").click(function(){
	var inp=$("input").val();
	if (inp=="") {
	$(".blank_hint").show();
	$(".inp_hint").addClass("hint_bginp");
	}
})
$(".hint_close").click(function(){
	$(".blank_hint").hide();
	$(".inp_hint").removeClass("hint_bginp");
})



	})
