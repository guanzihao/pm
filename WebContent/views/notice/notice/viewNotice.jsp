<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>查看通知</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white" href="${basePath }/notice/notice/listNotice">
		                          返回列表
		                     </a>
		                     <a class="btn btn-xs btn-white" href="${basePath }/notice/notice/forwardNotice/${notice.id }">
		                          转发
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content">
	                    <div class="row">
	                    	<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>标题</dt>
									<dd>${notice.noticeTitle }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内容</dt>
									<dd>${notice.noticeText}</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>附件</dt>
									<dd><pm:fileList metaObject="${notice}" delete="false" metaColums="colums"/></dd>
								</dl>
							</div>
							
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>时间</dt>
									<dd><fmt:formatDate value="${notice.updateDate }" pattern="yyyy-MM-dd"/></dd>
								</dl>
							</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>