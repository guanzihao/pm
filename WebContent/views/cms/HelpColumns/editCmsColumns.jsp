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
							action="${basePath }/cms/HelpColumns/updateCMSColumns" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${cmsContent.id }">
							<input type="hidden" name="img" value="${cmsContent.img }">
							<div class="form-group">
								<label class="col-sm-2 control-label">上级栏目<font>*</font></label>
								<div class="col-sm-10">
									<select class="selectpicker" name="columnparent">
									<option value="0">--</option>
									
										<c:forEach items="${pageBean }" var="item">
											<c:choose>
										 	<c:when test="${item.id==cmsContent.columnparent }">
												 <option value="${item.id }" selected="selected">${item.name }</option>
											</c:when>
											<c:otherwise>
												 <option value="${item.id }">${item.name }</option>
											</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>  
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">栏目名称<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="name" class="form-control"
										value="${cmsContent.name }" required="" minlength="1"
										maxlength="50">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">栏目描述<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="colnumDesc" class="form-control"
										value="${cmsContent.colnumDesc }" required="" minlength="1"
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
									<select class="selectpicker" name="columnstatus">
										<c:if test="${cmsContent.columnstatus==0 }">
											  <option value="0"  selected="selected">启用</option>
											  <option value="1">禁用</option>
										</c:if>
										<c:if test="${cmsContent.columnstatus==1 }">
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
										value="${cmsContent.columnSort }" required="" minlength="1"
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