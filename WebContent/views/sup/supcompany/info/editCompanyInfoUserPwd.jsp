<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑联系人密码</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white" href="${basePath }/company/info/viewCompanyInfo/${companyInfo.id}">
		                         <i class="fa fa-share"></i> 返回
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/company/info/saveCompanyInfoUserPwd" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${companyInfoUser.id}">
	                        <input type="hidden" name="comId" value="${companyInfo.id}">
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">公司名称</label>
	                            <div class="col-sm-10 control-value">${companyInfo.comName }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">联系人邮箱(登录名)</label>
	                            <div class="col-sm-10 control-value">${companyInfoUser.userMail }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">联系人名称</label>
	                            <div class="col-sm-10 control-value">${companyInfoUser.userName }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">密码<font>*</font></label>
	                            <div class="col-sm-10"><input type="password" id="loginPwd" name="loginPwd" class="form-control" required="" minlength="6" maxlength="50"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">确认密码<font>*</font></label>
	                            <div class="col-sm-10"><input type="password" id="loginPwd2" name="loginPwd2" class="form-control" required="" minlength="6" maxlength="50" equalTo="#loginPwd"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>

							<div class="form-group">
	                        	<label class="col-sm-2 control-label">邮箱(登录名)<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="userMail" class="form-control" value="${companyInfoUser.userMail }" required="" email="true" minlength="5" maxlength="100" remote="${basePath}/organize/organize/ajaxCheckUser?userId=${userAccount.id}"></div>
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