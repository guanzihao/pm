<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	dt{
		margin: 10px 0px !important;
	}
	.dl-horizontal dd {
	    margin-left: 90px;
	    padding-top: 10px;
	}
	.dl-horizontal dt {
		width: 60px;
	}
	
	dl{
		margin-bottom:20px;
	}
	
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
	    <div class="row" >
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title" >
						<h5>集成登录信息</h5>
						<div class="ibox-tools" style="" >
		                     <pm:auth authCode="info_editCompanyInfo">
			                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/integrate/integrate/editintegrate/${integrate.id}">
			                         修改
			                     </a>
		                     </pm:auth>
						</div>
					</div>
		            <div class="ibox-content row" style="padding-left:160px">
						<div class="col-sm-12" style="">
							<div class="col-sm-6">
								<dl class="dl-horizontal" >
									<dt style="">状态</dt>
									<dd>
										<c:if test="${integrate.loginstate eq 'W'}"><span class="label label-warning shenghe">待审核</span></c:if>
										<c:if test="${integrate.loginstate eq 'Y'}"><span class="label label-info tongguo">已通过</span></c:if>
										<c:if test="${integrate.loginstate eq 'N'}"><span class="label label-danger bohui">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>名称</dt>
									<dd>${integrate.loginname }</dd>
								</dl>
							</div>
							</div>
							<div class="col-sm-12">
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>内网地址</dt>
									<dd>${integrate.logininnerurl }</dd>
								</dl>
							</div>

							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>外网地址</dt>
									<dd>${integrate.loginouturl }</dd>
								</dl>
							</div>
							
							
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>账户</dt>
									<dd>${integrate.loginusercode}</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>密码</dt>
									<dd>${integrate.loginpassword}</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>提交方式</dt>
									<dd>${integrate.loginpostType}</dd>
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