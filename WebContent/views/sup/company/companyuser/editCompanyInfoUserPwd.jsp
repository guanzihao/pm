<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<style>
	span{
		text-align: right;
		margin-top: 5px;
	}
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>修改密码</h5>
	                    <a style="float:right;" onclick="JavaScript:history.back(-1);">
							<span>返回</span>
						</a>	
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/company/companyuser/saveCompanyInfoUserPwd" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${companyInfoUser.id}">
	                        
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired" style="color: red;margin: 5px;">*</i>新密码</span>
	                            <div class="col-sm-6"><input type="password" id="loginPwd" name="loginPwd" class="form-control" required="" minlength="6" maxlength="50"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired" style="color: red;margin: 5px;">*</i>确认密码</span>
	                            <div class="col-sm-6"><input type="password" name="loginPwd2" class="form-control" required="" equalTo="#loginPwd"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
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