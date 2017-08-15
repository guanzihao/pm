<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<style>
<!--
.col-sm-6{
	margin-bottom:15px;
}
-->
</style>

<body class="gray-bg" style="font-size:14px;">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>任务明细</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12" style="margin-bottom:15px;">
								<dl class="dl-horizontal">
									<dt>订单号</dt>
									<dd>${task.taskId }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>项目名称</dt>
									<dd>${task.taskName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>发布人</dt>
									<dd>${userName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>接单公司</dt>
									<dd>${supper.comName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>发布日期</dt>
									<dd>
										<fmt:formatDate value="${task.issueDate }"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>是否分配</dt>
									<dd>
										<c:if test="${orderFrom.orderCheck eq '1'}">
											<span class="label label-warning">已分配</span>
										</c:if>
										<c:if test="${orderFrom.orderCheck eq '0'}">
											<span class="label label-danger"> 
											<span>未分配</span>
											</span>
										</c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12" style="margin:20px 0;">
								<dl class="dl-horizontal">
									<dt>任务描述</dt>
									<dd>${task.taskDesc }</dd>
								</dl>
							</div>
							<div class="col-sm-12" style="margin:20px 0;">
								<dl class="dl-horizontal">
									<dt>客服备注</dt>
									<dd>${orderFrom.orderOpinion }</dd>
								</dl>
							</div>
							<div class="col-sm-12" style="margin:20px 0;" 	>
								<dl class="dl-horizontal">
									<dt>附件</dt>
									<dd>
										<pm:fileList metaObject="${orderFrom}" delete="false" metaColums="colums" />
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
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>