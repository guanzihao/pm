<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<ul id="myTab" class="nav nav-tabs">
   <li class="active"><a href="#gys" data-toggle="tab">供应商登录</a></li>
</ul>
<div id="myTabContent" class="tab-content" style="margin-top:100px;">
	<div class="tab-pane fade in active" id="gys">
        <DIV class="panel-body table-bordered">
            <form class="form-horizontal formValidate_2" role="form" action="${basePath }/common/suplogin/supLogin" method="post">
                <DIV class="form-group">
                    <LABEL class="col-sm-3 control-label div_cal" for="loginName">账号</LABEL>
                    <DIV class="col-sm-9 div_cal">
                        <INPUT class="form-control" id="loginName" name="loginName" required="" maxlength="100" placeholder="登录账号">
                    </DIV>
                </DIV>
                <DIV class="form-group">
                    <label class="col-sm-3 control-label div_cal" for="userpwd">密码</label>
                    <DIV class="col-sm-9 div_cal">
                    	<input type="password" id="userpwd" name="userpwd" class="form-control" required="" minlength="6" maxlength="50" placeholder="请输入密码">
         			<input type="hidden" id="loginPwd" name="loginPwd">
                    </DIV>
                </DIV>
                <DIV class="form-group">
                    <LABEL class="col-sm-3 control-label div_cal" for="kaptchaId">验证码</LABEL>
                    <DIV class="col-sm-5 div_cal">
                        <input type="text" id="kaptchaId" name="kaptchaId" class="form-control" required="" minlength="4" maxlength="4" placeholder="点击获取" onclick="updatekaptcha('usercodeImg_gys')">
                    </DIV>
                    <div class="col-sm-4 div_cal">
                        <img id="usercodeImg_gys" onclick="updatekaptcha('usercodeImg_gys')" width="100%" height="34" style="cursor: pointer;">
                    </div>
                </DIV>
                <DIV class="text-right form-group">
                    <a href="${basePath }/common/suplogin/forgotPwd" class="col-sm-12">找回密码？</a>
                </DIV>
                <c:if test="${not empty wwxx}">
	                <DIV class="form-group text-center">
	                    <font color="red">${wwxx }</font> 
	                </DIV>
                </c:if>
                <DIV class="form-group text-center">
                    <button class="btn btn-primary btn-1" disabled="disabled" type="submit">登录</button>
                    <a class="btn btn-default btn-1" href="${basePath }/common/register/registerRead">注册</a>
                </DIV>
            </form>
        </DIV>
   </div>
</div>

<script type="text/javascript">
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