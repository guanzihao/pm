<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>供应商信息</h5>
						<div class="ibox-tools">
							<pm:authCom authCode="company_editCompanyInfo">
								<a class="btn btn-xs btn-white myBtn" href="${basePath }/sup/supcompany/supcompany/editSupCompanyInfo">
								    <i class=""></i> 修改
								</a>
			                </pm:authCom>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${supcompanyInfo.comState eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${supcompanyInfo.comState eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${supcompanyInfo.comState eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商名称</dt>
									<dd>${supcompanyInfo.comName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商类别</dt>
									<dd>${enumitems.name }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商电话</dt>
									<dd>${supcompanyInfo.comTel }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商联系人邮箱</dt>
									<dd>${supcompanyInfo.comEmail }</dd>
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