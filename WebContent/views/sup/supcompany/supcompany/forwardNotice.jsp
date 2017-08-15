<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>转发通知</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white" href="${basePath }/sup/notice/notice/listNotice">
		                          返回列表
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content">
	                    <form id="noticeForm" class="form-horizontal formValidate" action="${basePath }/sup/notice/notice/saveNotice" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" id="noticeDraft" name="noticeDraft" value="false">
	
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">收件人<font>*</font></label>
	                            <div class="col-sm-10">
	                            	<select id="userIds" name="userIds" class="form-control select2" multiple="multiple" style="width:100%" required="">
										<c:forEach items="${userAccountList }" var="item">
											<option value="${item.id }">${item.userMail } [${item.userName }]</option>
										</c:forEach>
									</select>
	                            	<c:import url="/include/includeSelect2.jsp">
										<c:param name="name" value="userIds"/>
										<c:param name="value" value="${selectList}"/>
								    </c:import>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">标题<font>*</font></label>
	                            <div class="col-sm-10">
	                            	<input type="text" name="noticeTitle" class="form-control" value="${notice.noticeTitle }" required="" maxlength="250">
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">内容<font>*</font></label>
	                            <div class="col-sm-10">
	                            	<textarea id="noticeText" name="noticeText" style="display: none;" required>${notice.noticeText}</textarea>
	                            	<div class="summernote" htmlTxt="noticeText">${notice.noticeText}</div>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">附件</label>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${notice }" delete="true" name="noticeFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="noticeFiles"/>
										<c:param name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit">发送</button>
	                                <button class="btn btn-grey" type="button" onclick="saveNoticeDraft()">暂存</button>
	                                
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
function saveNoticeDraft(){
	$("#noticeDraft").val(true);
	$('#noticeForm').submit();
}
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>