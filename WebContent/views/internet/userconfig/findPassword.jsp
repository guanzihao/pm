<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeTag.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${basePath}/resource/css/gd.css"/>
		<script src="${basePath }/resource/js/jquery-1.11.3.min.js" type="text/javascript" ></script>
</head>
<body>
     
     <jsp:include page="/views/internet/header.jsp"></jsp:include>
     
<div class="white_bg" style="    padding-top: 30px;">
	<form id="tijiao" action="${basePath }/common/register/password" method="post">
	<div class="w_content">
		<div class="clear"></div>
		<div class="verify">
			<div>
			<label for="">邮箱</label>
			<input type="text" id="userMail" name="userMail" value="" onblur="onblus()"/></div>
            <div>
			<label for="">邮件收到的验证码</label>
				<input type="text" id="registcode" style="width:250px;" onblur="yanzheng()" name="registcode" value="" />
				<input type="button" id="yzmBtn" value="发送验证码"style="width:130px;" class="yz_btn" />
			</div>
			 <div>
			<label for="">新密码</label>
			<input type="password" name="loginPwd" id="loginPwd" value="" />
			</div>
			<div id="info" style="color:red;"></div>
			<div>
				<input type="button" onclick="tijiao()" name="" id="" value="确认"  class="next_btn"/>
			</div>
						<div>
				<input type="button" name="" id="" onclick="history.go(-1)" value="返回登录"  class="next_btn g_ffffff c_333333"/>
			</div>
		</div>
	</div>
	</form>
</div>

		<jsp:include page="/views/internet/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">

function tijiao(){loginPwd
	var loginPwd=$("#loginPwd").val();
	var email=$("#userMail").val();
	var registcode=$("#registcode").val();
	if(email!=null&&email.length>0){
		if(registcode!=null&&registcode.length>0){
			if(loginPwd!=null&&loginPwd.length>0){
				$("#tijiao").submit();
			}else{
				$("#info").html("请输入密码") 
			}
		}else{
			$("#info").html("请输入验证码")
		}
	}else{
		$("#info").html("请输入邮箱") 
	}
}

function updatekaptcha(){
	$("#usercodeImg").attr("src","${basePath }/kaptcha.jpg?log="+Math.random());
}
/* remotes="${basePath}/common/suplogin/ajaxCheckCompanyInfoUser" */
function onblus(){
	var email=$("#userMail").val();
	if(email.length>0){
		$("#info").html("") 
		$.ajax({
			 url:"${basePath}/common/suplogin/ajaxCheckCompanyInfoUser", 
			 dataType:"json",
			 data:{userMail:email},
			 success:function(datas){
					if(datas==0){
						 $("#info").html("邮箱不存在，请重新输入") 
					}				
			 }
		});
	}else{
		$("#info").html("请输入邮箱") 
	}
}


function yanzheng(){
	var registcode=$("#registcode").val();
	if(registcode.length>0){
		var email=$("#userMail").val();
	$.ajax({
		 url:"${basePath }/common/register/ajaxyangzhengma", 
		 dataType:"json",
		 data:{registcode:registcode,
			   email:email},
		 success:function(data){
			 if(data.statusCode=='200'){
				 $("#info").html('');
			 }else {
				 $("#info").html('验证码数据错误请重新输入!');
			 }
		 }
	});
	}else{
		$("#info").html('请输入验证码');
	}
}
//验证码倒计时
var wait=120;
var t;
//点击触发事件
function clickTimeOut(obj){
var email=$("#userMail").val();
   
    $.ajax({
		 url:"${basePath }/common/register/yanzhengmapass", 
		 dataType:"json",
		 data:{email:email},
		 success:function(data){
			 if(data!=300){
				 yzmTimeOut(obj);
			 }
		 }
	}); 
	  
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
//绑定点击事件
document.getElementById("yzmBtn").onclick=function(){clickTimeOut(this);}; 
</script>
</html>