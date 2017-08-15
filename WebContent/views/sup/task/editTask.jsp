<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${basePath }/resource/css/multiple-select.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath }/resource/css/bootstrap-datetimepicker.min.css" />
<script src="${basePath }/resource/js/multiple-select.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${basePath }/resource/js/bootstrap-datetimepicker.js"
	type="text/javascript" charset="utf-8"></script>

<nav class="navbar navbar-default navbar-fixed-top site-navbar">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" aria-expanded="false"
				aria-controls="navbar" type="button" data-toggle="collapse"
				data-target="#navbar">
				<SPAN class="sr-only">EPS</SPAN> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<div class="navbar-brand">
				<a href="${basePath }/common/index/index"> <span class="logo"></span>
				</a>
			</div>
		</div>
	</div>
</nav>
<div class="container">
	<form class="form-horizontal formValidate" role="form" action="${basePath }/sup/task/saveTask" method="post">
		<input type="hidden" id="inputTackTypeId" name="tackType" value="">
		<input type="hidden" id="flagId" name="taskId" value="${taskId }">
		<input type="hidden" id="userId" name="userId" value="${user.id }">
		<div class="col-md-12" style="margin-top: 30px;">
			<div class="panel panel-grey">
				<div class="yhxx">
					<label class="label" style="color: #333">添加任务信息</label>
				</div>
				<div class="panel-body">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">任务流水号<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" readonly="readonly" value="${taskId }" class="form-control" required="" maxlength="200">
							</div>
							<label class="col-sm-2 control-label">任务类型<font>*</font></label>
							<div class="col-sm-4">
								<div class="form-group">
									<select id="ms" multiple="multiple">
										<c:forEach items="${flowTypes }" var="item">
											<option value="${item.id }">${item.flowName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">发布客户<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" readonly="readonly" value="${user.companyInfo.comName }" class="form-control" required="" maxlength="200">
							</div>
							<label class="col-sm-2 control-label">发布日期<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker" name="issueDate" value="">
							</div>

						</div>
						<!-- <div class="form-group">
							<label class="col-sm-2 control-label">项目名称<font>*</font></label>
							<div class="col-sm-10">
								<input id="taskNameId" type="text" name="taskName"
									class="form-control" required="" maxlength="500">
							</div>
						</div> -->
						<div class="form-group">
							<label class="col-sm-2 control-label">期望开始日期<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker"
									name="startDate" value="">
							</div>
							<label class="col-sm-2 control-label">期望完成时间<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker"
									name="endDate" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">计划开启日期<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker"
									name="projectStartDate" value="">
							</div>
							<label class="col-sm-2 control-label">计划完成日期<font>*</font></label>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm date-picker"
									name="projectEndDate" value="">
							</div>
						</div>
						<%-- <div id="bgId" class="form-group" style="display: none;">
							<label class="col-sm-2 control-label ">指定报关公司<font>*</font></label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfobaoguan }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="ccId" class="form-group"  style="display: none;">
							<label class="col-sm-2 control-label">指定仓储公司<font>*</font></label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfocangchu }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="wmId" class="form-group"  style="display: none;">
							<label class="col-sm-2 control-label">指定外贸公司<font>*</font></label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfowaimao }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="wlId" class="form-group"  style="display: none;">
							<label class="col-sm-2 control-label">指定物流公司<font>*</font></label>
							<div class="col-sm-4">
								<select>
									<c:forEach items="${supCompanyInfowuliu }" var="item">
										<option value="${item.id }">${item.comName }</option>
									</c:forEach>
								</select>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="col-sm-2 control-label">任务描述<font>*</font></label>
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
<script type="text/javascript">
	$(function() {
		$('#ms')
				.change(
						function() {
							var mycars = $(this).val();
							$("#inputTackTypeId").val(mycars);
							var flagId = $("#flagId").val();
							$("#bgId").hide();
							$("#wlId").hide();
							$("#ccId").hide();
							$("#wmId").hide();
							for (var i = 0; i < mycars.length; i++) {
								if (mycars[i] == '7994F33B-798E-4495-885B-FB8F2F39B809'
										|| mycars[i] == 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C') {
									$("#bgId").show();
								}
								if (mycars[i] == 'D33A899D-C46B-438A-90BC-C2F395E8AFCE'
										|| mycars[i] == 'D8994C5C-533C-42E2-9BFD-46E5639844D2') {
									$("#ccId").show();
								}
								if (mycars[i] == '0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE'
										|| mycars[i] == 'B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7'
										|| mycars[i] == 'CF734401-5F26-4A3B-82B3-1B7A6DAFE35D') {
									$("#wlId").show();
								}
								if (mycars[i] == 'E8EA1196-D803-4040-B309-C65965A4F142'
										|| mycars[i] == '8EF423F7-8AF0-43F2-B03E-2F6E42276FE5') {
									$("#wmId").show();
								}
							}
							$(this).find("option:selected").text();
						}).multipleSelect({
					width : '100%'
				});
	});
</script>