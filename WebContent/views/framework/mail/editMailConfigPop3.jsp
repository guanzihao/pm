<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />


<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑邮件配置</h5>
	                </div>
	                <div class="ibox-content">
						<form class="form-horizontal formValidate" action="${basePath }/framework/mail/saveMailConfig" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${mailConfig.id}">
	
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>SMTP服务器</label>
	                            <div class="col-sm-4"><input type="text" name="mailSmtp" class="form-control" value="${mailConfig.mailSmtp }" required="" minlength="1" maxlength="100"></div>
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>端口</label>
	                            <div class="col-sm-4"><input type="text" name="mailPort" class="form-control" value="${mailConfig.mailPort }" required="" digits="true" maxlength="8"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>邮箱地址</label>
	                            <div class="col-sm-10"><input type="text" name="mailName" class="form-control" value="${mailConfig.mailName }" required="" maxlength="100"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>邮箱密码</label>
	                            <div class="col-sm-4"><input type="text" name="mailPwd" class="form-control" value="${mailConfig.mailPwd }" required="" maxlength="50"></div>
	                            <label class="col-sm-2 control-label"><i class="ired">*</i>超时设置</label>
	                            <div class="col-sm-4"><input type="text" name="mailTimeout" class="form-control" value="${mailConfig.mailTimeout }" required="" digits="true" maxlength="8"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>Auth认证</label>
	                            <div class="col-sm-4">
	                            	<div><span><input type="radio" <c:if test="${mailConfig!=null && mailConfig.mailAuth}">checked="checked"</c:if> value="true" name="mailAuth" required=""> 是</span></div>
	                            	<div><span><input type="radio" <c:if test="${mailConfig!=null && !mailConfig.mailAuth}">checked="checked"</c:if> value="false" name="mailAuth" required=""> 否</span></div>
	                            </div>
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>SSL认证</label>
	                            <div class="col-sm-4">
	                            	<div><span><input type="radio" <c:if test="${mailConfig!=null && mailConfig.mailSsl}">checked="checked"</c:if> value="true" name="mailSsl" required=""> 是</span></div>
	                            	<div><span><input type="radio" <c:if test="${mailConfig!=null && !mailConfig.mailSsl}">checked="checked"</c:if> value="false" name="mailSsl" required=""> 否</span></div>
	                            </div>
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