<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>添加栏目</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal formValidate"
							action="${basePath }/cms/columns/updateCMSColumns" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${cmsColumns.id }">
							<input type="hidden" name="img" value="${cmsColumns.img }">
							<div class="form-group">
								<label class="col-sm-2 control-label">栏目名称<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="name" class="form-control"
										value="${cmsColumns.name }" required="" minlength="1"
										maxlength="50">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">栏目描述<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="desc" class="form-control"
										value="${cmsColumns.desc }" required="" minlength="1"
										maxlength="100">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">栏目缩略图<font>*</font></label>
								<div class="col-sm-10">
									<input type="file" name="files" class="form-control"
										value=""  minlength="1"
										maxlength="100">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">是否启用<font>*</font></label>
								<div class="col-sm-10" >
									<select class="selectpicker" name="columnStatus">
										<c:if test="${cmsColumns.columnStatus==0 }">
											  <option value="0"  selected="selected">启用</option>
											  <option value="1">禁用</option>
										</c:if>
										<c:if test="${cmsColumns.columnStatus==1 }">
											  <option value="0"  >启用</option>
											  <option value="1" selected="selected">禁用</option>
										</c:if>
									</select>  
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">栏目顺序<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="columnSort" class="form-control"
										value="${cmsColumns.columnSort }" required="" minlength="1"
										maxlength="100">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-12 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">保存</button>
									
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>