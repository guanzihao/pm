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
	
	$(function() {
						$("#admin").focus(function() {
					if($(this).val() == "请输入工号") {
						$(this).val("");
					}
				});
				$("#admin").blur(function() {
					if($(this).val() == "") {
						$(this).val("请输入工号");
					}
				});
				// 密码部分
				$('#login_showPwd').focus(function() {
					var text_value = $(this).val();
					if(text_value == this.defaultValue) {
						$('#login_showPwd').hide();
						$('#login_password').show().focus();
					}
				});
				$('#login_password').blur(function() {
					var text_value = $(this).val();
					if(text_value == "") {
						$('#login_showPwd').show();
						$('#login_password').hide();
					}
				});
						$("#logbtn").click(function(){
				var logintxt=$("#admin").val();
		if (logintxt=="请输入工号") {
			alert("请输入工号！")
		}
		})
		
		/*select*/
		
		$(".select").each(function() {
			var s = $(this);
			var z = parseInt(s.css("z-index"));
			var dt = $(this).children("dt");
			var dd = $(this).children("dd");
			var _show = function() {
				dd.slideDown(200);
				dt.addClass("cur");
				s.css("z-index", z + 1);
			}; //展开效果
			var _hide = function() {
				dd.slideUp(200);
				dt.removeClass("cur");
				s.css("z-index", z);
			}; //关闭效果
			dt.click(function() {
				dd.is(":hidden") ? _show() : _hide();
			});
			$(this).find('a').each(function(){
				$(this).click(function(){
					var v = $(this).html();
					dt.children('span').html(v);
					_hide();
				});
			})
			$("body").click(function(i) {
				!$(i.target).parents(".select").first().is(s) ? _hide() : "";
			});
		})
		var h3=$("#firstpane").children("h3");
		h3.click(function(){
			 $(this).addClass("current_menu");
       $(this).siblings().removeClass("current_menu");
		})
		
		
		
		
       var   w_btn=$(".w_btn").children("button");
        $("#hz_btn").addClass("default_color");
       w_btn.click(function(){
       $(this).addClass("default_color");
       $(this).siblings().removeClass("default_color");
       })
       var span=$(".function").children("p");
       span.click(function(){
       	$(this).addClass("fun_on");
       	$(this).siblings().removeClass("fun_on");
       })
      $("#hz_btn").click(function(){
      	$("#hz_page").css("display","block");
      	$("#mx_page").css("display","none");
      })
            $("#mx_btn").click(function(){
      	$("#mx_page").css("display","block");
      	$("#hz_page").css("display","none");
      })
  $(".transFee").hide();  
$("#fold").click(function(){
	if ($(".transFee").css("display") == "none") {
		
			$("#fold").html("&#xe60a;");
			 $(".transFee").show();
	} else{
		$("#fold").html("&#xe609;");
		$(".transFee").hide();
	}

})   

//侧导航
  		var h3=$("#firstpane").children("h3");
		h3.click(function(){
			 $(this).addClass("current_menu");
       $(this).siblings().removeClass("current_menu");
		})
	//新增	
$("#new").click(function(){
	window.location.href="new_pro.html";
})

$("#newpro").click(function(){
	window.location.href="new_tile.html";
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
  
  
  
  //*上传附件*
    var fileBtn = $("input[type=file]");
    fileBtn.on("change", function(){
        var index = $(this).val().lastIndexOf("\\");
        var sFileName = $(this).val().substr((index+1));
        $("#rightText").html(sFileName);
    });
    
    // Team月度分析报告
    
   	  $(".transFeetd").hide();  
$("#fold").click(function(){
	if ($(".transFeetd").css("display") == "none") {
		
			$("#fold").html("&#xe60a;");
						  $('#td1').attr('rowspan',7);
			 $(".transFeetd").show();

            	$('#td2').attr('rowspan',7);

	} else{
		$("#fold").html("&#xe609;");
					  $('#td1').attr('rowspan',4);
		$(".transFeetd").hide();

            	$('#td2').attr('rowspan',4);
	}

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

//新增
		$('.sure_btn').click(function(){
		$(".box_con").each(function(){
			
					$(".box_con").css("display","block");
			$("#op").css("display","block");
			

		})
	})
	$(".more_txt").click(function(){
	
	if ($(".more").css("display") == "none") {
$(".more").css("display","block");	
	}
	else{
		$(".more_txt").css("color","#000");
$(".more").css("display","none");		
	$(".more_txt").css("color","#38A4FC");

	}

})

	})
	