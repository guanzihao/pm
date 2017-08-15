<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="middle-box text-center animated fadeInDown">
        <h2 class="font-bold">登录失效</h2>
        <div class="error-desc">
        	<i class="ace-icon fa fa-hand-o-right blue"></i> 用户长时间未操作，登录信息已失效 <br/>
			<i class="ace-icon fa fa-hand-o-right blue"></i> 用户信息不存在，系统无法执行操作
			<br/><br/><br/>
            <a href="${basePath }/views/internet/index/indexHomePage.jsp" class="btn btn-primary" target="_parent">
				<i class="ace-icon fa fa-arrow-left"></i> 重新登录
			</a>
        </div>
    </div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>