<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<style>
dt{
	margin: 10px 0px !important;
}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>邮箱模板信息</h5>
						<div class="ibox-tools">
		                     <pm:auth authCode="info_editCompanyInfo">
			                     <a class="btn btn-xs btn-white myBnt" href="${basePath }/sysconfig/Systemplate/editSystemplate/${systemplate.id}">
			                         <i class=""></i> 修改
			                     </a>
		                     </pm:auth>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${systemplate.tempstatus eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${systemplate.tempstatus eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${systemplate.tempstatus eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
						
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>模板名称</dt>
									<dd>${systemplate.tempname }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>模板内容</dt>
									<dd>${systemplate.tempcontet }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>模板类型</dt>
									<dd>${enumitems.name }</dd>
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