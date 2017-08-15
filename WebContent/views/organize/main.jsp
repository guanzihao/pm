<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="fixed-sidebar full-height-layout gray-bg">
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span>
	                    		<i class="fa fa-user fa-4x white"></i>
	                        </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear">
                               		<span class="block m-t-xs"><strong class="font-bold">欢迎，${currUser.userName }</strong><b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="${basePath }/organize/user/viewUserAccount">账号信息</a></li>
                                <li><a class="J_menuItem" href="${basePath }/organize/user/editUserAccountPwd">修改密码</a></li>
                                <li class="divider"></li>
                                <li><a href="javascript:;" onclick="skinConfig()">选择主题</a></li>
                                <li class="divider"></li>
                                <li><a href="${basePath }/common/userlogin/userExit">退出</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">EPS</div>
                    </li>
                    <pm:treeView/>
                </ul>
            </div>
        </nav>
        <div id="page-wrapper" class="gray-bg dashbard-1" >
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                    	<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:;"><i class="fa fa-bars"></i></a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
		                    <a class="count-info" href="javascript:;" onclick="openLayer('站内消息','${basePath }/notice/notice/listNotice?searchInt1=1',false);clearCount('notice_id_count')">
		                        <i class="fa fa-envelope"></i><span id="notice_id_count"></span>
		                    </a>
		                </li>
		                <li>
		                    <a class="count-info" href="javascript:;" onclick="openLayer('通知提醒','${basePath }/notice/sysnews/listSysNews?searchInt1=1',false);clearCount('sysnews_id_count')">
		                        <i class="fa fa-bell"></i><span id="sysnews_id_count"></span>
		                    </a>
		                </li>
		                <li>
		                    <a href="${basePath }/common/userlogin/userExit">
		                        <i class="fa fa-sign-out"></i>退出
		                    </a>
		                </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${basePath }/organize/user/index">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown" aria-expanded="false">关闭操作<span class="caret"></span></button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:;" class="roll-nav roll-right J_tabRefresh J_tabExit"><i class="fa fa fa-refresh"></i> 刷新</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe_PM" width="100%" height="100%" src="${basePath }/organize/user/index" frameborder="0" data-id="${basePath }/organize/user/index" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right"></div>
                <div><strong>EPS</strong> Copyright  2017. All rights reserved.   上海欧坚网络发展股份有限公司.版权所有 </div>
            </div>
        </div>
    </div>
    <script src="${basePath }/resource/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${basePath }/resource/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${basePath }/resource/plugins/layer/layer.js"></script>
    <script src="${basePath }/resource/js/contabs.min.js"></script>
    <script src="${basePath }/resource/js/commom.min.js"></script>
    <script src="${basePath }/resource/js/pm.min.js"></script>
    <script src="${basePath }/resource/plugins/sockjs/sockjs.min.js"></script>
    <script src="${basePath }/resource/js/main.js"></script>
</body>
<%@ include file="/include/includeFooter.jsp" %>