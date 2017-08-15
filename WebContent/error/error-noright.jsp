<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn" >
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox ">
	                <div class="ibox-content">
	                    <div class="row">
							<div class="col-xs-12">
								<i class="ace-icon fa fa-hand-o-right blue"></i> 没有相应的操作权限，无法执行本次操作<br/>
								<i class="ace-icon fa fa-hand-o-right blue"></i> 操作过程中有非法操作，系统自动拦截<br/>
								<br/><br/>
								<a href="javascript:history.back()" class="btn btn-primary">
									<i class="ace-icon fa fa-arrow-left"></i> 返回
								</a>
								<a href="javascript:goHome();" class="btn btn-default">
									<i class="ace-icon fa fa-home"></i> 首页
								</a>
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