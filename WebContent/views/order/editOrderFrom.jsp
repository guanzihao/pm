<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/resource/css/multiple-select.css" />
<link rel="stylesheet" type="text/css" href="${basePath }/resource/css/bootstrap-datetimepicker.min.css" />
<script src="${basePath }/resource/js/multiple-select.js" type="text/javascript" charset="utf-8"></script>
<script src="${basePath }/resource/js/bootstrap-datetimepicker.js" type="text/javascript" charset="utf-8"></script>

<nav class="navbar navbar-default navbar-fixed-top site-navbar">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" type="button" data-toggle="collapse" data-target="#navbar">
				<SPAN class="sr-only">EPS</SPAN> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<div class="navbar-brand">
				<a href="${basePath }/common/index/index"> <span class="logo"></span>
				</a>
			</div>
		</div>
	</div>
</nav>
<div class="container">
	<form class="form-horizontal formValidate" role="form"
		action="${basePath }/task/saveTask" method="post">
		<input type="hidden" id="inputTackTypeId" name="tackType" value="">
		<input type="hidden" id="flagId" name="taskId" value="${taskId }" class="form-control" required="" maxlength="200">
		<div class="col-md-12" style="margin-top: 30px;">
			<div class="panel panel-grey">
				<div class="yhxx">
					<label class="label" style="color: #333">添加任务信息</label>
				</div>
				<div class="panel-body">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>任务流水号</label>
							<div class="col-sm-4">
								<input type="text" readonly="readonly" value="${task.taskId }" class="form-control" required="" maxlength="200">
							</div>
							<label class="col-sm-2 control-label"><i class="ired">*</i>任务类型</label>
							<div class="col-sm-4">
								<div class="form-group">
									<span>${flowType.flowName }</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>发布客户</label>
							<div class="col-sm-4">
								<select name="userId">
									<c:forEach items="${companyInfoUsers }" var="item">
										<option value="${item.companyInfo.id }">${item.companyInfo.comName }</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label"><i class="ired">*</i>发布日期</label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm" name="issueDate" value="${task.issueDate }">
							</div>

						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>项目名称</label>
							<div class="col-sm-10">
								<input id="taskNameId" type="text" name="taskName" value="${task.taskName }" class="form-control" required="" maxlength="500">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>期望开始日期</label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker" name="startDate" value="${task.startDate }">
							</div>
							<label class="col-sm-2 control-label"><i class="ired">*</i>期望完成时间</label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker" name="endDate" value="${task.endDate }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>计划开启日期</label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker" name="projectStartDate" value="${task.projectStartDate }">
							</div>
							<label class="col-sm-2 control-label"><i class="ired">*</i>计划完成日期</label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker" name="projectEndDate" value="${task.projectEndDate }">
							</div>
						</div>
						<div id="bgId" class="form-group">
							<label class="col-sm-2 control-label "><i class="ired">*</i>指定报关公司</label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfobaoguan }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="ccId" class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>指定仓储公司</label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfocangchu }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="wmId" class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>指定外贸公司</label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfowaimao }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="wlId" class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>指定物流公司</label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfowuliu }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="ired">*</i>任务描述</label>
							<div class="col-sm-10">
								<textarea id="noticeText" name="tackDesc" cols="98" rows="10">${notice.noticeText }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">附件</label>
							<div class="col-sm-10">
								<pm:fileList metaObject="${task  }" delete="true" name="tackFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="tackFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-center zc-bt">
			<button class="btn btn-primary btn-1" type="submit">提交</button>
			<button class="btn btn-1" type="submit">重置</button>
		</div>
	</form>
</div>

<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>
