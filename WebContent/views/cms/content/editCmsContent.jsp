<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<script src="${basePath }/resource/editor/ueditor.config.js"></script>
	<script src="${basePath }/resource/editor/ueditor.all.min.js"></script>
	
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>添加栏目</h5>
					</div>
					<div class="ibox-content">
						<form ID="tijiao" class="form-horizontal formValidate"
							action="${basePath }/cms/content/updateCMSContent" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${cmsContent.id }">
							<input type="hidden" name="img" value="${cmsContent.img }">
							<input type="hidden" name="bigImg" value="${cmsContent.bigImg }">
							<div class="form-group">
								<label class="col-sm-2 control-label">内容标题<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="tile" class="form-control"
										value="${cmsContent.tile }" required="" minlength="1"
										maxlength="50">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">内容详情<font>*</font></label>
								<div class="col-sm-10">
										<span style="display: none;" id="content">${cmsContent.content }</span>
									  <script id="editor"  type="text/plain" style="width:800px;height:600px;"></script>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">关键字<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="keyWord" class="form-control"
										value="${cmsContent.keyWord }" required="" minlength="1"
										maxlength="100">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">内容顺序<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="sort" class="form-control"
										value="${cmsContent.sort }" required="" minlength="1"
										maxlength="100">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">所属栏目<font>*</font></label>
								<div class="col-sm-10">
									<select class="selectpicker" name="columnID">
										<c:forEach items="${pageBean }" var="item">
											<c:choose>
												<c:when test="${item.id==cmsContent.cmsColumns.id }">
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
								<label class="col-sm-2 control-label">栏目缩略图<font>*</font></label>
								<div class="col-sm-10">
									<input type="file" name="imgFile" class="form-control"
										value=""  
										maxlength="100">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">内容大图<font>*</font></label>
								<div class="col-sm-10">
									<input type="file" name="bigImgFile" class="form-control"
										value=""  
										maxlength="100">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">是否启用<font>*</font></label>
								<div class="col-sm-10" >
									<select class="selectpicker" name="status">
										<c:if test="${cmsContent.status==0 }">
											<option value="0" selected="selected">启用</option>
										  <option value="1">禁用</option>
										</c:if>
										<c:if test="${cmsContent.status==1 }">
											<option value="0">启用</option>
										  <option value="1" selected="selected">禁用</option>
										</c:if>
									  
									</select>  
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-12 col-sm-offset-2">
									<button class="btn btn-primary" type="button" onclick="center()">保存</button>
									
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var ue = UE.getEditor('editor');
	ue.ready(function() {
		UE.getEditor('editor').setContent($("#content").html());
	});  
	
	
	function center(){
		$("#content").val(UE.getEditor('editor').getContent());
		$("#tijiao").submit();
	}
	
 
</script>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>