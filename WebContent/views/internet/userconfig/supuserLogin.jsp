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
				  <!-- 在网站首页中点击立即下单,登录后跳转至相应的下单页面的标识 litao -->
				  <input type="hidden" name="urlMenthod" value="${urlMenthod}"/>
					<div style="" class="bk_log">
						<div style="" class="log_txt">服务商登录</div>
						<div style="padding: 8px 0px;color: #999;font-size: 14px;font-family: '微软雅黑';">公共场所不建议自动登录，以防账号丢失</div>
						<div class="inp_c" >
							<LABEL class="col-sm-3 control-label div_cal" for="loginName" style="display:none;"></LABEL>
							<input type="text" name="loginName" id="loginName" placeholder="请输入邮箱" />
						</div>
						<div class="inp_c">
							<label class="col-sm-3 control-label div_cal" for="userpwd" style="display:none;"></label>
							<input type="password" name="loginPwd" id="loginPwd" value=""  placeholder="请输入密码"/>
						</div>
						<div >
							<LABEL class="col-sm-3 control-label div_cal" for="kaptchaId" style="display:none;"></LABEL>
							<input type="text" name="kaptchaId" id="kaptchaId" placeholder="请输入验证码" class="inp_d"  style="float:left;" />
							<img id="usercodeImg_gys" onclick="updatekaptcha('usercodeImg_gys')" width="40%" height="38" style="float:left;">
						</div>
						<div style="color:red;float:left;margin-right:115px;" >${wwxx }</div>
						<div style="clear:both;"></div>
						<div class="right" style="float:right;"><a href="${basePath }/views/internet/userconfig/findPassword.jsp">忘记密码</a><a href="${basePath }/views/internet/userconfig/userRegister.jsp" class="c_38A4FC">立即注册</a></div>
						<div class="zc_btn" style="float:left;"><input type="submit" name=""  id="" value="立即登录" /></div>
					</div>
				</div>
			</div>
		</form>
	<div style="height: 30px;"></div>
	<jsp:include page="/views/internet/footer.jsp"></jsp:include>
	
<script type="text/javascript">
$("#usercodeImg_gys").attr("src","${basePath }/kaptcha.jpg?log="+Math.random());


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