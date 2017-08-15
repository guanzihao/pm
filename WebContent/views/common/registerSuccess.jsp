<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="includeHeader.jsp" %>

<nav class="navbar navbar-default navbar-fixed-top site-navbar">
	<div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" type="button" data-toggle="collapse" data-target="#navbar">
            	<SPAN class="sr-only">EPS</SPAN>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand">
                <a href="${basePath }/common/index/index">
                    <span class="logo"></span>
                </a>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
            <UL class="nav navbar-nav">
                <LI><A href="${basePath }/common/index/index" class="fhsy"><i class="icon-home user-r fhsy-icon"></i>返回首页</A></LI>
            </UL>
        </div>
    </div>
</nav>
<div class="container">
  	<div class="col-md-12 zc-bt">
		<div style="margin-top:50px;">
        	<div class="col-xs-6 ">
	        	<div class="col-xs-8 fr text-center"><img src="${basePath }/resource/images/woshou.jpg" width="238" height="220" class="text-right img-responsive"></div>
	        </div>
	        <div class="col-xs-6 zccg_right">
	            <div class="zccg_right_tou">恭喜您，注册成功了！</div>
	            <span>PM+电子采购系统欢迎您</span>
	            <span>您的用户名是：<i>${userName }</i></span>
	            <span>账号正在审批中，请等待！</span>
	        </div>
        </div>
    </div>
</div>

<%@ include file="includeFooter.jsp" %>