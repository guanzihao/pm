<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeTag.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

		<jsp:include page="/views/internet/header.jsp"></jsp:include>

		<form class="form-horizontal formValidate" role="form" >
		<div class="bannerzc" >
			<div class="banner_k" style="height: 404px;">

			<div style="" class="bk_log">
				<div style="" class="log_txt">快速注册</div>
				<div style="padding: 8px 0px;color: #999;font-size: 14px;font-family: '微软雅黑';" >公共场所不建议自动登录，以防账号丢失</div>
				<div class="inp_c"><input type="text" name="userMail" id="email"  placeholder="请输入邮箱" /></div>
				<div><input type="text" name="registcode" id="registcode" onblur="yanzheng()" placeholder="请输入验证码" value="" class="inp_d" /><input type="button" id="yzmBtn" value="发送验证码" class="yz_btn" />
					
				</div>
				<div class="inp_c"><input type="password" name="loginPwd" placeholder="请输入密码"  id="loginPwd" value="" /></div>
				<div class="inp_c"><input type="password" name="loginPwd2"  placeholder="请输入密码" id="loginPwd2" value="" /></div>
				<div class="k_inp"><input type="checkbox" name="" id="checked" value="" checked="checked" />我已阅读并接受<a href="注册协议.html">服务</a><div style="color:red; font-size:12px;" id="info"></div></div>
				<div class="zc_btn"><button type="button" name="" class="submitclass" onclick="postfrom()" id=""  >立即注册</button>
				</div>
			</div>

			</div>
		</div>
		</form>
	<div style="height: 30px;"></div>
	<jsp:include page="/views/internet/footer.jsp"></jsp:include>

		<script type="text/javascript">

		$("#email").click(function(){
				$("#info").html('');
		});

		$("#registcode").click(function(){
				$("#info").html('');
		});

		$("#loginPwd").click(function(){
				$("#info").html('');
		});
		
		$("#loginPwd2").click(function(){
				$("#info").html('');
		});

		
		
		
$(document).ready(function(){


	$("#navul > li").hover(function(){

		$(this).addClass("navmoon");

	},function(){

		$(this).removeClass("navmoon");

	});
	
     

});

function yanzheng(){
	var flag=false;
	var registcode=$("#registcode").val();
	var email=$("#email").val();
	if(email!=''){
	$.ajax({
		 url:"${basePath }/common/register/ajaxyangzhengma", 
		 dataType:"json",
		 data:{registcode:registcode,
			   email:email},
		 success:function(data){
			 if(data.statusCode=='200'){
				 $("#info").html('');
				 flag=true;
			 }else {
				 if($("#registcode").val()!=''){
					 $("#info").html('验证码数据错误请重新输入!');
					}
			 }
		 }
	});
	}
	return flag;
}
function chkEmail(strEmail) {
	if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(strEmail)) {
	return false;
	}else {
		return true;
	}
} 
function postfrom()
{
	 submitFunction("submitclass");
	if(chkEmail($("#email").val())){
		
		if($("#info").html()!='邮箱名称重复请重新输入'&&$("#email").val().length>3&&$("#email").val()!='请输入邮箱'){
			if($("#info").html()!='请输入正确的验证码'&&$("#registcode").val().length>3&&$("#registcode").val()!='请输入验证码'&&yanzheng()){
				if($("#loginPwd2").val().length>=6 && $("#loginPwd").val().length>=6){
					if($("#loginPwd2").val() == $("#loginPwd").val()){
						if($("input[type='checkbox']").is(':checked')){
							
							 document.forms[0].action = '${pageContext.request.contextPath}/common/register/saveCompany.do';
							 document.forms[0].submit(); 
							 $("#info").html('');
						}else{
							removeFunction("submitclass")
							$("#info").html('必须同意协议，否则不能注册');
						}
					}else{
						removeFunction("submitclass")
						 $("#info").html('两次密码不一致，请重新输入');
					}
				}else{
					removeFunction("submitclass")
					 $("#info").html('密码至少6位数');
				}
			}else{
				removeFunction("submitclass")
				 $("#info").html('请输入正确的验证码');
			}
		}else{
			removeFunction("submitclass")
			$("#info").html("邮箱名称重复请重新输入");
		}
	}else{
		removeFunction("submitclass")
		 $("#info").html('请输入正确的邮箱格式');
	}
}

//验证码倒计时
var wait=120;
var t;
//点击触发事件
function clickTimeOut(obj){
var email=$("#email").val();
	if(chkEmail($("#email").val())){
	    $.ajax({
			 url:"${basePath }/common/register/yanzhengma", 
			 dataType:"json",
			 data:{email:email},
			 success:function(data){
				 if(data==300){
					 $("#info").html('该邮箱已注册！请重新选择邮箱');
				 }else{
					 alert("邮件发送成功!");
					 yzmTimeOut(obj);
				 }
			 }
		}); 
	}else{
		 $("#info").html('请输入正确的邮箱格式');
	}
}
//验证码延迟标识（0:有效;-1:失效）
var timeout=0;
//倒计时
function yzmTimeOut(obj){
   if(wait==0){
     clearInterval(t);
     obj.removeAttribute("disabled");
     obj.value="发送验证码";
     wait=120;
    
     timeout=-1;
   }else{
       obj.setAttribute("disabled", true);
       obj.value= wait+"秒可重发" ;
       wait--;
       t=setTimeout(function() {  
                yzmTimeOut(obj);  
            },  
            1000);  
   }
}
function submitFunction(className){
	$("."+className).attr("disabled", true).text("处理中...");
}
function removeFunction(className){
	$("."+className).attr("disabled", false).text("立即注册");
}
//绑定点击事件
document.getElementById("yzmBtn").onclick=function(){clickTimeOut(this);}; 
</script>
	</body>
	
</html>