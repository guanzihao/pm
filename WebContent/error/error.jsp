<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox ">
	                <div class="ibox-content">
	                    <div class="row">
							<div class="col-xs-12">
								<i class="ace-icon fa fa-hand-o-right blue"></i> 检查浏览器URL地址是否正确<br/>
								<i class="ace-icon fa fa-hand-o-right blue"></i> 检查提交的数据是否正确（包括：数字，金额，端口号等）<br/>
								<i class="ace-icon fa fa-hand-o-right blue"></i> 请求次数太频繁，系统拒绝访问<br/>
								<i class="ace-icon fa fa-hand-o-right blue"></i> 非法请求，系统拒绝访问<br/>
								<i class="ace-icon fa fa-hand-o-right blue"></i> 尝试重新操作，如重复出现联系管理员
								<c:if test="${not empty exception}">
									<br/><i class="ace-icon fa fa-hand-o-right blue"></i> 错误信息：<p>${exception }</p>
								</c:if>
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