<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeTag.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>云贸通</title>
    <link rel="stylesheet" type="text/css" href="${basePath }/resource/css/pro.css"/>
	<script src="${basePath }/resource/js/jquery-1.11.3.min.js"></script>
	<script src="${basePath }/resource/plugins/validate/jquery.validate.min.js"></script>
	<script src="${basePath }/resource/plugins/validate/pm-validate-config.js"></script>
	<script src="${basePath }/resource/plugins/superSlide/superslide.js"></script>
	<script src="${basePath }/resource/plugins/security/security.js"></script>
</head>
<body style="background-color: #f9f9f9;">
	<form class="login_form1" action="${basePath }/common/userlogin/userLogin" method="post">
		<div style="width: 100%;height: auto;">
			<div style="width: 345px;height: 405px;background: #fff;margin: 0 auto;margin-top: 60px;float: right;margin-right: 100px;padding:30px;padding-top: 40px;">
				<div style="color: #333;font-size: 20px;font-weight: bold;padding-bottom: 10px;border-bottom: 1px solid #ccc;">管理员登录</div>
				<div><input type="text" id="loginName" name="loginName" placeholder="请输入账号" style="width: 333px;height: 40px;border: 1px solid #ccc;padding-left: 10px;line-height: 40px;font-size: 16px;color: #ccc;margin-top: 16px;margin-bottom: 10px;" /></div>
				<div>
					<input type="password" id="userpwd" name="userpwd" placeholder="请输入密码" style="width: 333px;height: 40px;border: 1px solid #ccc;padding-left: 10px;line-height: 40px;font-size: 16px;color: #ccc;margin-bottom: 10px;" />
					<input type="hidden" id="loginPwd" name="loginPwd">
				</div>
				<div>
					<input type="text" id="kaptchaId" name="kaptchaId" placeholder="获取验证码" onclick="updatekaptcha('usercodeImg_cgy')" style="width:197px;height: 40px;border: 1px solid #ccc;padding-left: 10px;line-height: 40px;font-size: 16px;color: #ccc;margin-bottom: 10px;"  /><img id="usercodeImg_cgy" onclick="updatekaptcha('usercodeImg_cgy')" style="font-size: 16px; width: 125px;height: 44px;  border: 1px solid #ccc; margin-left: 8px;background: #F7F7F7; color: #38A4FC;"/>
				</div>
				<div>
					<button type="submit" name="" id="" style="width: 345px; height: 50px;font-size: 20px; background: #38A4FC;color: #fff;border: none;" >登录</button>
				</div>
			</div>
		</div>
	</form>
</body>
	
<script type="text/javascript">
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

	$(".login_form1").validate({
		submitHandler: function(form) {
			var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);
			$(form).find("#loginPwd").val(RSAUtils.encryptedString(publicKey, $(form).find('#userpwd').val()));
			$(form).find('#userpwd').val("");
			$(form).find(":submit").attr("disabled", true).text("处理中...");
			form.submit();
		}
	});
</script>
	
</html>
