<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="${basePath}/resource/css/gd.css" />
<%-- <link rel="stylesheet" type="text/css" href="${basePath}/resource/css/common12.css" /> --%>
		<script src="${basePath }/resource/js/jquery.js" type="text/javascript"></script>
		<script src="${basePath }/resource/js/banner.js" type="text/javascript"></script>
		<script src="${basePath }/resource/js/aad.js" type="text/javascript" charset="utf-8"></script>
		
		
		<script src="${basePath }/resource/js/unslider.min.js" type="text/javascript"></script>
		
		<script src="${basePath }/resource/js/lb.js" type="text/javascript"></script>
		
		<script src="${basePath }/resource/js/fz.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="first_nav">
			<span class="left c_999">您好，欢迎进入云贸通</span>
			<span class="c_f87d30 padleft"><a href="${basePath}/views/internet/userconfig/userRegister.jsp" class="c_f87d30">注册</a></span>
			<span class="right bz_msg"><a href="${basePath}/common/help/findCMSContents" class="c_f87d30 ">帮助信息</a></span>
			<span class="right bz_msg"><a href="${basePath}/views/internet/server/internetMessage.jsp" class="c_f87d30 ">网站留言</a></span>
			<span class="right bz_msg"><a href="${basePath}/views/internet/userconfig/adminLogin.jsp" class="c_f87d30 ">客服登录</a></span>
			<span class="right bz_msg"><a href="${basePath}/views/internet/userconfig/supuserLogin.jsp" class="c_f87d30 ">服务商登录</a></span>
			<span class="right bz_msg"><a href="${basePath}/views/internet/userconfig/userLogin.jsp" class="c_f87d30 ">客户登录</a></span>
		</div>
		<div class="logo">
			<div><a href="${basePath}/views/internet/index/indexHomePage.jsp"><img src="${basePath }/resource/images/img/logo.png" width="160px" style="position: relative;top: -24px;" /></a>

				<div class="navbg" style="    width: 810px;display: inline-block;margin-top:15px;margin-left: 60px; ">
					<ul id="navul" class="cl">
						<li class="pad_14 sy">
							<a href="${basePath}/views/internet/index/indexHomePage.jsp">首页</a>
						</li>
						<li class="pad_14 tg">
							<a href="${basePath}/common/help/findCMSConterType/4">通关专区</a>
						</li>
						<li class="pad_14 fw">
							<a href="javascrip:void(0);">供应链服务</a>
							<ul>
								<li>
									<a href="${basePath}/common/help/findCMSConterType/1"><span>物流服务</span></a>
								</li>
								<li>
									<a href="${basePath}/common/help/findCMSConterType/3"><span>外贸服务</span></a>
								</li>
								<li>
									<a href="${basePath}/common/help/findCMSConterType/2"><span>仓储服务</span></a>
								</li>
							</ul>
						</li>
						<li class="pad_14 kjds">
							<a href="${basePath}/views/internet/server/kjRetailers.jsp">跨境电商</a>
						</li>
						<li class="pad_14 xx">
							<a href="${basePath}/views/internet/index/messageSever.jsp">信息服务</a>
						</li>
						<li class="pad_14 zj">
							<a href="${basePath}/views/internet/server/expertTeam.jsp">专家团队</a>
						</li>
						<li class="pad_14 wyrz">
							<a href="${basePath}/views/internet/server/Entering.jsp">我要入驻</a>
						</li>
						<li class="pad_14 gywm">
							<a href="${basePath}/views/internet/index/aboutUs.jsp">关于我们</a>
						</li>
					</ul>
				</div>

				<span class="right strong_f">400-892-5156</span>
				<img src="${basePath }/resource/images/img/iphone.png" class="right" style="margin-top: 29px;    width: 30px;" /></div>
	
			<script type="text/javascript">
			
			$(document).ready(function() {

				$("#navul > li").hover(function() {

					$(this).addClass("navmoon");

				}, function() {

					$(this).removeClass("navmoon");

				});
				
				console.log('==');
				
			});
			
			function shownavselected(i)
            {
				//$('#navul li').removeClass('navmoon2');
				$('.'+i).addClass('navmoon2');
			}
			
			</script>
	
		</div>
</body>
</html>