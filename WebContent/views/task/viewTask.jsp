<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>

	span{
		text-align: right;
	}
	
	dt {
	    font-weight: 300;
	}

</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>人任务明细</h5>
					</div>
					<c:if test="${task.isIssue eq '0'}">
						<div class="ibox-title">
		                    <div class="ibox-tools">
		                       <a href="${basePath }/task/submitTask/${task.id}" class="btn btn-primary myBtn"><i class=""></i> 提交</a>
		                    </div>
		                </div>
		             </c:if>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>任务流水号</dt>
									<dd>${task.taskId }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>项目名称</dt>
									<dd>${task.taskName }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>发布日期</dt>
									<dd>
										<fmt:formatDate value="${task.issueDate }"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>期望开始日期</dt>
									<dd>
										<fmt:formatDate value="${task.startDate }"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>期望结束日期</dt>
									<dd>
										<fmt:formatDate value="${task.endDate }" pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>计划开始日期</dt>
									<dd>
										<fmt:formatDate value="${task.projectStartDate }"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>计划结束日期</dt>
									<dd>
										<fmt:formatDate value="${task.projectEndDate }"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>备注</dt>
									<dd>${task.taskDesc }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>任务类型</dt>
									<dd>${flowTypeName }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>启用状态</dt>
									<dd>
										<c:if test="${task.isIssue eq '0'}">
											<span class="label label-danger"> 
											<span><i class=""></i>未提交</span>
											</span>
										</c:if>
										<c:if test="${task.isIssue eq '3' }">
											<span class=" label label-info"> 已分配</span>
										</c:if>
										<c:if test="${task.isIssue eq '1' }">
											<span class=" label label-warning"> 未分配</span>
										</c:if>
										<c:if test="${task.isIssue eq '2' }">
											<span class=" label label-danger"> 分配中</span>
										</c:if>
									</dd>
								</dl>
							</div>

							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>附件</dt>
									<dd>
										<pm:fileList metaObject="${task}" delete="false" metaColums="colums" />
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