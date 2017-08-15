<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>接口定义详情</h5>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>接口名称</dt>
									<dd>${interConfig.interfaceName}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>供应商名称</dt>
									<dd>${interConfig.supperName}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>客户名称</dt>
									<dd>${interConfig.companyName}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>接口地址</dt>
									<dd>${interConfig.interfaceUrl}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>接口连接方式</dt>
									<dd>${interConfig.joinName}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>开启日期</dt>
									<dd><fmt:formatDate value="${interConfig.startDate}" pattern="yyyy-MM-dd"/>
											&nbsp;</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>结束日期</dt>
									<dd><fmt:formatDate value="${interConfig.endDate}" pattern="yyyy-MM-dd"/>
											&nbsp;</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>显示顺序</dt>
									<dd>${interConfig.sort}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>创建日期</dt>
									<dd>
										<fmt:formatDate value="${interConfig.createDate}" pattern="yyyy-MM-dd"/>
											&nbsp;
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>更新日期</dt>
									<dd>
										<fmt:formatDate value="${interConfig.updateDate }" pattern="yyyy-MM-dd"/>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>启用状态</dt>
									<dd>
										<c:if test="${interConfig.status eq '0'}"><span class="label label-warning">启用</span></c:if>
										<c:if test="${interConfig.status eq '1'}"><span class="label label-danger">禁止</span></c:if>
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