<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>导入人员信息</h5>
	                    <div class="ibox-tools">
							<pm:auth authCode="organize_listUserAccount">
		                    	<a class="btn btn-xs btn-white myBtn" href="${basePath }/organize/organize/listUserAccount">
		                        	返回列表
		                    	</a>
							</pm:auth>
						</div>
	                </div>
	                <div class="ibox-content">
	                	<c:choose>
	                		<c:when test="${importState}">
	                			<c:choose>
	                				<c:when test="${importState}">
	                					<div class="dataTables_wrapper form-inline">
											<div class="table-responsive">
												<table class="table table-striped table-bordered table-hover dataTable">
													<thead>
														<tr>
															<th>行号</th>
															<th>错误说明</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${excelErrorList }" var="item" varStatus="status">
															<tr>
																<td>${item.rowNum }</td>
																<td>${item.errorText }</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
	                				</c:when>
	                				<c:otherwise>
	                					<div class="dataTables_wrapper form-inline">
											<div class="table-responsive">
												<table class="table table-striped table-bordered table-hover dataTable">
													<thead>
														<tr>
															<th>数据导入成功</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
	                				</c:otherwise>
	                			</c:choose>	
	                			
	                		</c:when>
	                		<c:otherwise>
	                			<form class="form-horizontal formValidate" action="${basePath }/organize/organize/saveImportUserAccount" method="post" enctype="multipart/form-data">
			                        <input type="hidden" name="token" value="${token}">
			                        <div class="form-group">
			                        	<label class="col-sm-2 control-label">选择文件<font>*</font></label>
			                            <div class="col-sm-10">
			                            	<input type="file" name="file" class="form-control" required="">
			                            	<span class="help-block m-b-none">请选 <a href="${basePath }/organize/organize/downUserAccountTemp">下载模板</a> ，然后按照格式正确填写后上传</span>
			                            </div>
			                        </div>
			                        <div class="hr-line-dashed"></div>
			                        <div class="form-group">
			                            <div class="col-sm-4 col-sm-offset-2">
			                                <button class="btn btn-primary" type="submit">上传</button>
			                            </div>
			                        </div>
			                    </form>
	                		</c:otherwise>
	                	</c:choose>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>