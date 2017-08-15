<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	.myBtn:hover {
	background: #FFFFFF;
	border:2px solid #ff7519;
	padding: 4px 9px !important;
	border-radius: 4px !important;
	margin-top: 15px !important;
	margin-bottom: 3px !important;
	margin-right: 4px !important;
	color: black;
}
</style>
<body class="gray-bg" style="font-size:14px">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑个人信息</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/company/info/viewUser">
		                         返回
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath}/company/info/saveUser" method="post">
	                        <input type="hidden" name="token" value="${token}">

							<div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>邮箱(登录名)</label>
	                            <div class="col-sm-10 control-value" style="height:35px;line-height: 35px">${UserAccount.userMail }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>密码</label>
	                            <div class="col-sm-3"><input type="password" id="loginPwd" name="loginPwd" class="form-control" required="" maxlength="100"></div>
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>确认密码</label>
	                            <div class="col-sm-3"><input type="password" id="loginPwd1" name="loginPwd1" class="form-control" required="" maxlength="15" equalTo="#loginPwd"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>手机号码</label>
	                            <div class="col-sm-3"><input type="text" name="userTel" class="form-control" value="${UserAccount.userTel }" required="" maxlength="15" digits="true"></div>
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>名称</label>
	                            <div class="col-sm-3"><input type="text" name="userName" class="form-control" value="${UserAccount.userName }" required="" maxlength="15" ></div>
	                        </div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit">保存</button>
	                                
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>