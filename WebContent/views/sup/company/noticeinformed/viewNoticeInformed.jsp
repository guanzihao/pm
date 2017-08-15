<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>查看公告通知</h5>
	                </div>
	                <div class="ibox-content">
                		<h1 style="text-align: center;">${noticeInformed.noticeTitle }</h1>
                        <div class="form-group" >
                        	<div class="col-sm-1">
                            </div>
                            <div class="col-sm-10">
                            	<textarea id="contentText" name="contentText" style="display: none;" required>${noticeinformed.noticeText}</textarea>
                            	<div class="summernote" htmlTxt="contentText">${noticeInformed.noticeText}</div>
                            </div>
                            <div class="col-sm-1">
                            </div>
                            
                        </div>
	                </div>
	            </div>
	            <div class="ibox-content">
		            <div class="row">
		            	<div class="col-sm-12">
							<dl class="dl-horizontal">
								<dt>附件</dt>
								<dd><pm:fileList metaObject="${noticeInformed}" delete="false" metaColums="noticeInformed"/></dd>
							</dl>
						</div>
						
						<div class="col-sm-12">
							<dl class="dl-horizontal">
								<dt>时间</dt>
								<dd><fmt:formatDate value="${noticeInformed.updateDate }" pattern="yyyy-MM-dd"/></dd>
							</dl>
						</div>
		            </div>
		        </div>    
	            
	            
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>