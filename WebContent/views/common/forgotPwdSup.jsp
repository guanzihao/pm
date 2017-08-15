<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="includeHeader.jsp" %>

<nav class="navbar navbar-default navbar-fixed-top site-navbar">
	<div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" type="button" data-toggle="collapse" data-target="#navbar">
            	<SPAN class="sr-only">EPS</SPAN>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand">
                <a href="${basePath }/common/index/index">
                    <span class="logo"></span>
                </a>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
            <UL class="nav navbar-nav">
                <LI><A href="${basePath }/common/index/index" class="fhsy"><i class="icon-home user-r fhsy-icon"></i>返回首页</A></LI>
            </UL>
        </div>
    </div>
</nav>
<div class="container zhmm">
  	<div class="col-sm-12" style="background:#eee;">
    	<div class="panel-body">
        	<h3>密码找回</h3>
            <div class="col-sm-6">
	            <form class="form-horizontal formValidate " role="form" action="${basePath }/common/suplogin/resetPwd" method="post">
	                <div class="form-group text-center">
	                    <label class="col-sm-3 control-label" for="userMail">请输入邮箱</label>
	                    <div class="col-sm-4"><input  type="text" id="userMail" name="userMail" class="form-control" email="email" required=""  maxlength="200" onblur="onblus()" ></div>
	                	<div id="div" style="display:none;color:red;margin-left:-100px;"></div>
	                </div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label" for="kaptchaId">输入验证码</label>
	                    <div class="col-sm-5">
	                        <input class="form-control" id="kaptchaId" name="kaptchaId" placeholder="验证码" type="text">
	                    </div>
	                    <div class="col-sm-4">
	                        <img id="usercodeImg" onclick="updatekaptcha()" src="${basePath }/kaptcha.jpg?log=<%=Math.random()*100%>" width="100%" height="34" style="cursor:pointer;">
	                    </div>
	                </div>
	                <div class="form-group">
	                	<div class="col-sm-offset-3 col-sm-10">
	                    <button class="btn btn-primary" type="submit">找回密码</button>
	                    </div>
	                </div>
	            </form>
	         </div>
        </div>
    </div>
</div>

<script type="text/javascript">
function updatekaptcha(){
	$("#usercodeImg").attr("src","${basePath }/kaptcha.jpg?log="+Math.random());
}
/* remotes="${basePath}/common/suplogin/ajaxCheckCompanyInfoUser" */
function onblus(){
	var email=$("#userMail").val();
	$.ajax({
		 url:"${basePath}/common/suplogin/ajaxCheckCompanyInfoUser", 
		 dataType:"json",
		 data:{userMail:email},
		 success:function(datas){
			 $("#div").css("display","block")
			 document.getElementById('div').innerHTML='邮箱不存在，请重新输入';
			/*   $("#div").html("邮箱不存在，请重新输入") */
		 },
		 error: function (xml) {
			 
			 
		 }
	});
}
</script>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="includeFooter.jsp" %>