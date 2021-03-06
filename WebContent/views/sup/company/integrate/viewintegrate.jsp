<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	.myBtn:hover {
		background: #FFFFFF;
		border:2px solid #ff7519;
		padding: 2px 9px;
		border-radius: 4px;
		margin-top: 15px;
		margin-bottom: 7px;
		margin-right: 4px;
		color: black;
	}
	
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>集成登录信息</h5>
						<div class="ibox-tools">
		                     <pm:auth authCode="info_editCompanyInfo">
			                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/sup/integrate/integrate/editintegrate/${integrate.id}">
			                          修改
			                     </a>
		                     </pm:auth>
						</div>
					</div>
		            <div class="ibox-content" style="font-size:14px;">
						<div class="row">
							<div class="col-sm-12" style="margin-bottom:30px;">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${integrate.loginstate eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${integrate.loginstate eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${integrate.loginstate eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6" style="margin-bottom:30px;">
								<dl class="dl-horizontal">
									<dt>单点登录系统</dt>
									<dd>${integrate.integrate.loginname }</dd>
								</dl>
							</div>
							<div class="col-sm-6" style="margin-bottom:30px;">
								<dl class="dl-horizontal">
									<dt>账号</dt>
									<dd>${integrate.loginusercode }</dd>
								</dl>
							</div>

							<div class="col-sm-6" style="margin-bottom:30px;">
								<dl class="dl-horizontal">
									<dt>密码</dt>
									<dd>${integrate.loginpassword }</dd>
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