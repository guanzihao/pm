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

.dl-horizontal{
	font-size:12px !important;
}

*{
		font-size:14px!important;
	}
	
	dt{
		font-weight: 400 !important;
	}
	
	.col-sm-8{
		padding-left:50px;
	}
	
	dl{
		margin-top: 20px;
	}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	        	<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5 style="">个人信息</h5>
						<div class="ibox-tools">
		                     <a class="" href="${basePath }/company/info/editUser">
		                          修改信息
		                     </a>
						</div>
					</div>
		            <div class="ibox-content" style="">
						<div class="row">
							<div class="col-sm-8" style="margin-top:20px;">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${UserAccount.userState eq 'W'}"><span class="label label-warning shenghe">待审核</span></c:if>
										<c:if test="${UserAccount.userState eq 'Y'}"><span class="label label-info tongguo">已通过</span></c:if>
										<c:if test="${UserAccount.userState eq 'N'}"><span class="label label-danger bohui">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							
							<div class="col-sm-8"  style="margin-top:20px;">
								<dl class="dl-horizontal">
									<dt>邮箱[登录名]</dt>
									<dd>${UserAccount.userMail }</dd>
								</dl>
							</div>
							<div class="col-sm-8"  style="margin-top:20px;">
								<dl class="dl-horizontal">
									<dt>名称</dt>
									<dd>${UserAccount.userName }</dd>
								</dl>
							</div>

							<div class="col-sm-8"  style="margin-top:20px;">
								<dl class="dl-horizontal">
									<dt>手机号码</dt>
									<dd>${UserAccount.userTel }</dd>
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