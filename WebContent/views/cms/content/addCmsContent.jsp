<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<script src="${basePath }/resource/editor/ueditor.config.js"></script>
	<script src="${basePath }/resource/editor/ueditor.all.min.js"></script>
<script src="//cdn.ckeditor.com/4.7.0/full/ckeditor.js"></script>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>添加栏目</h5>
					</div>
					<div class="ibox-content">
						<form id="tijiao" class="form-horizontal formValidate"
							action="${basePath }/cms/content/addCmsContent" method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-2 control-label">内容标题<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="tile" class="form-control"
										value="" required="" minlength="1"
										maxlength="50">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="form-group">
			                      	<label class="col-sm-2 control-label">内容详情<font>*</font></label>
									<div class="col-sm-10">
									
									<input type="text" style="display:none;" id="content" name="content" />
									  <script id="editor"  type="text/plain" style="width:500px;height:200px;"></script>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">关键字<font>*</font></label>
								<div class="col-sm-10">
									<input type="text" name="keyWord" class="form-control"
										value="" required="" minlength="1"
										maxlength="5">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">所属栏目<font>*</font></label>
								<div class="col-sm-10">
									<select class="selectpicker" name="columnID">
										<c:forEach items="${pageBean }" var="item">
										  <option value="${item.id }">${item.name }</option>
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
									  <option value="0">启用</option>
									  <option value="1">禁用</option>
									</select>  
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-12 col-sm-offset-2">
									<button class="btn btn-primary" type="button" onclick="tijiao()">保存</button>
									
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
 
	function tijiao(){
		$("#content").val(UE.getEditor('editor').getContent());
		$("#tijiao").submit();
	}
	
 
</script>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>