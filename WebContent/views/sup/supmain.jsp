<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeTag.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${basePath}/resource/images/favicon.ico" type="image/x-icon" />
<link href="${basePath}/resource/css/font_1435131046_7356622.css" rel="stylesheet" type="text/css">
<link href="${basePath}/resource/css/saved_resource(4).css" rel="stylesheet" type="text/css">
<link href="${basePath}/resource/css/saved_resource(1).css" rel="stylesheet" type="text/css">
<link href="${basePath}/resource/css/css-services-module.css" rel="stylesheet" type="text/css" media="all">
<script src="${basePath }/resource/js/jquery-1.11.3.min.js"></script>
<style>
.hidden-mod {
    display: none
}

.mo-mod-header-title h1 a {
    text-indent: -5000em;
    background:
    url(../../resource/images/logo.jpg);
     background-size : 115px 43px;
    width: 115px;
    height: 43px;
}

.mo-mod-header-title {
    margin: 10px 0 0 2px;
}

body {
    -webkit-font-smoothing: auto;
}

.mo-list-nav .ui2-navigation-item {
    margin-right: 18px;
}
</style>


</head>

<body class="alibaba page-esc" data-spm="8047881" >
    <div class="body-container" data-spm="1999227025">

        <div style="display: none;" id="user-info">
            (有些老页面用了这个埋点): <span id="user-code" data-customerid="308088">1028009</span>
        </div>
        <!-- 头部的DIV -->
        <div class="mo-mod-header">
            <div class="mo-row util-clearfix">
                <div class="util-left mo-mod-header-title">
                    <h1>
                        <a href="https://onetouch.yunmaotong.com/" title="返回云贸通首页">云贸通</a>
                    </h1>
                    <h3>
                        <a href="https://onetouch.yunmaotong.com/mo/home.htm"
                            title="返回操作平台首页">操作平台</a>
                    </h3>
                </div>
                <div class="util-right">
                    <div class="mo-user" data-role="mo-header">
                        <ul class="">
                            <li id="user-info"><a href="${basePath}/sup/company/user/viewUser" target="iframe_PM">我的云贸通</a>ID: <span id="user-code"
                                data-customerid="308088">${PM_SUP_SESSION_KEY.userMail }</span>
                            </li>
                            <li id="user-info">Hi. <span id="user-name">${userName}</span> <a
                                target="_self" href="${basePath }/common/suplogin/supExit">登出</a></li>
                            <li><a
                               href="${basePath }/views/internet/server/internetMessage.jsp"
                                target="_blank">意见反馈</a></li>
                            <li><a
                               href="${basePath }/sup/notice/notice/listNotice" target="iframe_PM">消息<span
                                    id="mo-message-tip">${noticeCount}</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <div class="row-fluid mo-row util-clearfix mo-row-c2-s7">
            <!-- 主体的DIV -->
            <div class="col-main" >
                <div class="main-wrap">
                    <div class="" style="margin-left:20px;">
                        <div class="row J_mainContent" style="width:100%;background-color:white;">
                                                                   <c:choose>                  
		                                                                  <c:when test="${not empty url}">
		                                                                             <iframe id="J_iframe" class="J_iframe" name="iframe_PM"  width="100%" height="100%"; scrolling="no" src="${url}" frameborder="0" data-id="${basePath }/organize/user/index" seamless></iframe>
		                                                                        </c:when>
										                                        <c:otherwise>
										                                                 <iframe id="J_iframe" class="J_iframe" name="iframe_PM" width="100%" height="100%" scrolling="no" src="${basePath }/sup/supcompany/homePage/supplierHome" frameborder="0" data-id="${basePath }/organize/user/index" seamless></iframe>  
										                                        </c:otherwise>
									                                        </c:choose>
                      </div>

                    </div>
                </div>
            </div>
            <div class="col-sub" style="height:100%">
                <!-- tangram:1190 begin-->
                <div data-role="menu-container">
                    <div class="mo-mod-user-photo">
                        <a href="https://onetouch.yunmaotong.com/mo/interest.htm"
                            target="_blank">
                            <div class="mo-photo-box">
                                <img width="54"
                                    src="${basePath}/resource/images/TB1vj2SIXXXXXb7XXXXGQA0HXXX-52-51.png"
                                    alt="下单客户A" title="下单客户A">
                                <div class="mo-photo-mask"></div>
                            </div>
                        </a> <i class="mo-icon-user-ts"></i>
                    </div>
                    <c:choose>
					<c:when test="${not empty currSup.companyInfo }">
						<pm:TreeViewCom orgtype="2"/>
					</c:when>
					<c:when test="${not empty currSup.supCompanyInfo }">
						<pm:TreeViewCom orgtype="3"/>
					</c:when>
				</c:choose>

                <!-- tangram:1190 end-->

            </div>
        </div>
            </div>
            
        <!-- tangram:1027 begin-->
        <div id="ui-footer" class="ui-footer ui-footer-wrapper" style="background-color:#f6f8f7">
            <hr>
            <div class="ui-footer-seo">
                <p class="ui-footer-copyright">
                   Copyright &copy; 2017. All rights reserved.   上海欧坚网络发展股份有限公司.版权所有
                </p>
            </div>
        </div>
        <span id="cnzz_stat_icon_1255867744"></span>

    </div>

    <!-- tangram:1026 end-->
   <script type="text/javascript">
   
        $('.sidebar-main-menu-item').hover(function(){
            $(this).children('ul').css('display','block');
        },function(){
            $(this).children('ul').css('display','none');
        }); 

      $('.hot-question-robot-root').click(function(event) {
        //如果存在（不存在）就删除（添加）一个类。
        $('.hot-question-robot-root').toggleClass("mini-size");

        });
   
   </script>
    <!--
        	iframe 自适应高度
        -->
		<script type="text/javascript">
	function reinitIframe(){
			var iframe = document.getElementById("J_iframe");
			try{
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				iframe.height = height;
				console.log(height);
			}catch (ex){}
	}
	window.setInterval("reinitIframe()", 200);
	
	getIndexHome();
			function getIndexHome(){
				    $.ajax({
				       type : "post",
			           url : "${basePath }/sup/index/getSuplierUser",
			           dataType : "json",
			           async : false,
			           success:function(data){
			              $("#mo-message-tip").text(data.noticeCount);
			              $("#user-name").text(data.userName);
			              $("#user-code").text(data.LoginName);
			           }
				    });
				}
</script>

</body>
</html>