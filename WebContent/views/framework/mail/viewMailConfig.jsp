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
						<h5>邮件邮件管理</h5>
						<div class="ibox-tools">
		                    <pm:auth authCode="mail_editMailConfig">
								<a class="myBtn" href="javascript:;" onclick="openLayer('修改','${basePath }/framework/mail/editMailConfig', true)">
									<span>修改</span>
								</a>
							</pm:auth>
							<pm:auth authCode="mail_removeMailConfig">
								<a class="myBtn" href="${basePath }/framework/mail/removeMailConfig">
									<span> 删除</span>
								</a>
							</pm:auth>
							<pm:auth authCode="mail_viewMailLogTest">
								<a class="myBtn" href="${basePath }/framework/mail/viewMailLogTest">
									<span> 发送测试邮件</span>
								</a>
							</pm:auth>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>SMTP服务器</dt>
									<dd>${mailConfig.mailSmtp }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>端口</dt>
									<dd>${mailConfig.mailPort }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>邮箱用户名</dt>
									<dd>${mailConfig.mailName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>邮箱密码</dt>
									<dd>${mailConfig.mailPwd }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>超时设置</dt>
									<dd>${mailConfig.mailTimeout }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>Auth认证</dt>
									<dd>
										<c:if test="${not empty mailConfig }">
											<c:choose>
												<c:when test="${mailConfig.mailAuth }">是</c:when>
												<c:otherwise>否</c:otherwise>
											</c:choose>
										</c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>SSL认证</dt>
									<dd>
										<c:if test="${not empty mailConfig }">
											<c:choose>
												<c:when test="${mailConfig.mailSsl }">是</c:when>
												<c:otherwise>否</c:otherwise>
											</c:choose>
										</c:if>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>