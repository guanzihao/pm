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
<div class="container zhmm">
  	<div class="dl row">
        <div class="col-xs-6">
        	<div class="col-xs-8 text-center" style="float:right;"><img src="${basePath }/resource/images/duihao.png" width="220" height="200" class="text-right img-responsive"></div>
        </div>
        <div class="col-xs-6 zccg_right">
            <div class="zccg_right_tou">恭喜，密码找回成功！</div>
            <span>您的密码已重设，请妥善保管您的密码！</span>
            <span>您可以登录邮箱查收新密码。</span>
            <span><input type="button" value="返回首页" class="btn btn-primary" onclick="window.location='${basePath }/common/index/index'"></span>
        </div>
    </div>
</div>
<%@ include file="includeFooter.jsp" %>