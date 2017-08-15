<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>修改信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/organize/user/saveUserAccount" method="post">                        
	                        <input type="hidden" name="token" value="${token}">
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">邮箱(登录名)</label>
	                            <div class="col-sm-10 control-value">${userAccount.userMail }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<label class="col-sm-2 control-label">用户名称<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="userName" class="form-control" value="${userAccount.userName }" required="" maxlength="100"></div>
	                        	<label class="col-sm-2 control-label">手机号码<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="userTel" class="form-control" value="${userAccount.userTel }" required="" maxlength="15" remote="${basePath}/organize/organize/ajaxCheckUser?userId=${userAccount.id}"></div>
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