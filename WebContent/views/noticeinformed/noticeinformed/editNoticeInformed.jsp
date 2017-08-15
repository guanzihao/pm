<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />

<style>

	span{
		text-align: right;
	}

</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑通知</h5>
	                    <a class="btn btn-xs btn-white" style="margin-left:50px;" href="${basePath }/noticeinformed/noticeinformed/listMyNoticeInformed">
		                     		    返回
		                     </a>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/noticeinformed/noticeinformed/saveNoticeInformed" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${noticeinformed.id}">
	                        <input type="hidden" id="noticeState" name="noticeState" value="true">
	                        
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>标题</span>
	                            <div class="col-sm-10">
	                            	<input type="text" name="noticeTitle" class="form-control my_input" value="${noticeinformed.noticeTitle }" required="" maxlength="250">
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>内容</span>
	                            <div class="col-sm-10">
	                            	<textarea id="noticeText" name="noticeText" style="display: none;" required>${noticeinformed.noticeText }</textarea>
	                            	<div class="summernote" htmlTxt="noticeText">${noticeinformed.noticeText }</div>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>可见类型</span>
	                            <div class="col-sm-10">
	                            	<div><span><input type="radio" <c:if test="${noticeinformed==null || noticeinformed.noticeType == 1}">checked="checked"</c:if> value="1" name="noticeType" required="">所有用户</span></div>
	                            	<div><span><input type="radio" <c:if test="${noticeinformed!=null && noticeinformed.noticeType == 2}">checked="checked"</c:if> value="2" name="noticeType" required="">只平台用户</span></div>
	                            	<div><span><input type="radio" <c:if test="${noticeinformed!=null && noticeinformed.noticeType == 3}">checked="checked"</c:if> value="3" name="noticeType" required="">只客户用户</span></div>
	                            	<div><span><input type="radio" <c:if test="${noticeinformed!=null && noticeinformed.noticeType == 4}">checked="checked"</c:if> value="4" name="noticeType" required="">只供应商用户</span></div>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span">附件</span>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${noticeinformed }" delete="true" name="noticeinformedFiles" metaColums="noticeInformed"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="noticeinformedFiles"/>
										<c:param name="metaColums" value="noticeInformed"/>
								    </c:import>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit">发布</button>
	                                
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
	$("#noticeState").val(false);
	$('.formValidate').submit();
}
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>