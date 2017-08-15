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
						<h5>添加接口</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal formValidate"
							action="${basePath}/inter/interColumn/saveInterfaceColumns"  method="post">
							<div class="form-group">
	                        	<label class="col-sm-2 control-label">接口名称</label>
	                            <div class="col-sm-4"><input type="text" name="intername" value="" class="form-control"  maxlength="50"></div>
	                            <label class="col-sm-2 control-label">接口详情</label>
	                            <div class="col-sm-4"><input type="text" name="intercontent" value="" class="form-control"  maxlength="200"></div>
	                        </div>
							 <div class="form-group">
	                        	<label class="col-sm-2 control-label">显示顺序</label>
	                            <div class="col-sm-4"><input type="text" name="intersort" value="" class="form-control"  maxlength="10"></div>
	                           <label class="col-sm-2 control-label">是否启用<font>*</font></label>
	                            <div class="col-sm-4" >
									<select class="selectpicker" name="interstatus">
									  <option value="0">启用</option>
									  <option value="1">禁用</option>
									</select>  
								</div>
	                         </div>
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