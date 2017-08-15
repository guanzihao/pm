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
                <LI><A href="${basePath }/views/internet/index/indexHomePage.jsp" class="fhsy"><i class="icon-home user-r fhsy-icon"></i>返回首页</A></LI>
            </UL>
        </div>
    </div>
</nav>
<div class="container">
  	<form class="form-horizontal formValidate" role="form" >
  		<input type="hidden" name="token" value="${token}">
  		<div class="col-md-12" style=" margin-top:30px;">
			<div class="panel panel-grey">
	        	<div class="yhxx">
	          		<label class="label" style="color:#333">用户信息</label>
	          	</div>
	          	<div class="panel-body">
	            	<div class="col-sm-12">
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label">邮箱(登录名)<font>*</font></label>
	                        <div class="col-sm-4"><input  type="text" id="email" name="userMail" class="form-control" email="email" required=""  maxlength="200" remote="${basePath}/common/register/ajaxCheckCompanyInfoUser"></div>
	                        <div class="col-sm-2 control-label" ><input type="button" class="btn btn-primary btn-1"  onclick="getma()" value="发送验证码"/></div>
	                        <div class="col-sm-4"><input type="text" placeholder="请输入验证码" id="registcode" name="registcode" class="form-control" digits="true" required="" maxlength="50"  onblur="yanzheng()"></div>
	                        <div style="color:red; font-size:12px;" id="info"></div>
	                    </div>
                        <div class="form-group">
                        	<label class="col-sm-2 control-label">登录密码<font>*</font></label>
	                        <div class="col-sm-4"><input type="password" id="loginPwd" name="loginPwd" class="form-control" required="" minlength="6" maxlength="50"></div>
                        	<label class="col-sm-2 control-label">确认密码<font>*</font></label>
                            <div class="col-sm-4"><input type="password" id="loginPwd2" name="loginPwd2" class="form-control" required="" minlength="6" maxlength="50" equalTo="#loginPwd"></div>
                        </div>
					</div>
				</div>
			</div>
		</div>
        <div class="text-center zc-bt">
      		<button class="btn btn-primary btn-1"  onclick="postfrom()">提交</button>
        	<button class="btn btn-1" type="reset">重置</button>
      	</div>
	</form>
</div>
<script type="text/javascript">
	
	function getma()
	{
		var email=$("#email").val();
		$.ajax({
			 url:"${basePath }/common/register/yanzhengma", 
			 dataType:"json",
			 data:{email:email},
			 success:function(data){
			 }
		});
	}
	
	function yanzheng(){
		var registcode=$("#registcode").val();
		var email=$("#email").val();
		$.ajax({
			 url:"${basePath }/common/register/ajaxyangzhengma", 
			 dataType:"json",
			 data:{registcode:registcode,
				   email:email},
			 success:function(data){
				 if(data==123){
					 $("#info").html('验证码数据错误请重新输入!');
				 }
			 },error : function(data) {  
				 if(data!=123){
					 $("#info").html('');
				 }
	        }
		});
	}
	
	
	function postfrom()
	{
		 document.forms[0].action = '${pageContext.request.contextPath}/common/register/saveCompany.do';
		document.forms[0].submit(); 
		/*  var email=$("#userMail").val();
		var registcode=$("#registcode").val();
		var loginPwd=$("#loginPwd").val();
		var loginPwd2=$("#loginPwd2").val();
		$.ajax({
			 url:"${basePath }/common/register/saveCompany", 
			 dataType:"json",
			 type:"post",
			 data:{email:email,
				 registcode:registcode,
				 loginPwd:loginPwd,
				 loginPwd2:loginPwd2
			 },
		});  */
	}

</script>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="includeFooter.jsp" %>