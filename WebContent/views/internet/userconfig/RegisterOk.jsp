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

		<form class="formValidate_2" role="form" action="${basePath }/common/suplogin/supLogin" method="post">
			<div class="bannerzc" >
				<div class="banner_k" style="height: 404px;">
					<div style="" class="bk_log">
						
						<c:if test="${info eq '1' }">
							<div style="" class="log_txt">密码重置成功！</div>
						</c:if>
						<c:if test="${info eq '' }">
							<div style="" class="log_txt">恭喜您，注册成功了！</div>
						</c:if>
						<div style="padding: 8px 0px;color: orange;font-size: 14px;font-family: '微软雅黑';"></div>
						<div class="inp_c" style="color:orange;font-size:18px;margin-top:20px;margin-bottom:40px;text-align:center">云贸通欢迎您</div>
						<div></div>
						<div class="inp_c" style="line-height:30px;margin-bottom:60px;text-align:center">用户名:${userName }</div>
						<a href="${basePath}/views/internet/userconfig/userLogin.jsp"><div class="zc_btn"><input type="button" name="" id="" value="立即登录" /></div></a>
					</div>
				</div>
			</div>
		</form>
		
	<div style="height: 30px;"></div>
	<jsp:include page="/views/internet/footer.jsp"></jsp:include>
<script type="text/javascript">
$("#usercodeImg_gys").attr("src","${basePath }/kaptcha.jpg?log="+Math.random());
$("#loginName").click(function(){
	if($("#loginName").val()=='请输入邮箱'){
		$("#loginName").val('');
	}
	if($("#kaptchaId").val()==''){
		$("#kaptchaId").val('请输入验证码');
	}
});

$("#kaptchaId").click(function(){
	if($("#loginName").val()==''){
		$("#loginName").val('请输入邮箱');
		
	}
	if($("#kaptchaId").val()=='请输入验证码'){
		$("#kaptchaId").val('');
	}
});

$("#loginPwd").click(function(){
	if($("#loginName").val()==''){
		$("#loginName").val('请输入邮箱');
	}
	if($("#kaptchaId").val()==''){
		$("#kaptchaId").val('请输入验证码');
	}
});



$(document).ready(function(){
	$("#navul > li").hover(function(){

		$(this).addClass("navmoon");

	},function(){

		$(this).removeClass("navmoon");

	});
});
function toRegister(){
    wind.location.href="${basePath }/views/internet/userconfig/userRegister.jsp";
}
var exponent = '';
var modulus = '';
$(document).ready(function() {
	$.ajax({type:"post", url:"${basePath}/common/index/jsonSecurityKey", async:true, dataType:"json",success:function (data){
		exponent = data[0].exponent;
		modulus = data[0].modulus;
		$('.btn-1').removeAttr("disabled");
	}});
});
function updatekaptcha(id){
	$("#" + id).attr("src","${basePath }/kaptcha.jpg?log="+Math.random());
}
$(".formValidate_2").validate({
	submitHandler: function(form) {
		var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);
		$(form).find("#loginPwd").val(RSAUtils.encryptedString(publicKey, $(form).find('#userpwd').val()));
		$(form).find('#userpwd').val("");
		$(form).find(":submit").attr("disabled", true).text("处理中...");
		form.submit();
	}
});

	</script>
	</body>
</html>