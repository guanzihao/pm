<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${basePath}/resource/css/gd.css" />
<script type="text/javascript">
$(function(){
	$('.lxkf_block').mouseleave(function(){
		$('.lxkf').show();
	});
	$('.fwrx_block').mouseleave(function(){
		$('.fwrx').show();
	});
	$('.gzwm_block').mouseleave(function(){
		$('.gzwm').show();
	});
	
})
</script>
</head>
<body>
<div class="about_me">
			<ul>
				<li>
					<a href="#">关于我们</a>
				</li>
				<li>
					<a href="#">联系我们</a>
				</li>
				<li>
					<a href="#">隐私策略</a>
				</li>
				<li>
					<a href="#">用户协议</a>
				</li>
				<li>
					<a href="#">服务承诺</a>
				</li>
				<li>
					<a href="#">法律声明</a>
				</li>
			</ul>
		</div>
		        
        
		<div class="line"></div>
		<div class="copyright">Copyright © 2017 . All rights reserved. &nbsp;&nbsp;.版权所有</div>
		<!-- 返回顶部 固定-->
	<div class="fixbtn" id="fixbtn">
			<ul>
				<li class="fix-type-right">
					<a href="javascript:;">
						<div class="block-icon fwrx_block">
							<i class="icon-sp icon-1"></i>
							<!--<p>联系我们</p>-->
						</div>
						<div class="block-content fwrx" style="display: none; opacity: 1;">
							<p class="mt10">服务热线：123-639-1234</p>
							<p>总部电话：123-12345678</p>
						</div>
					</a>
				</li>
				<li class="fix-type-over">
					<a onclick="javascript:;">
						<div class="block-icon lxkf_block">
							<i class="icon-sp icon-2" style="cursor: pointer"></i>
						</div>
						<div>
							<p class="online lxkf" style="display: none; opacity: 1;">点击联系客服</p>
						</div>
					</a>
				</li>
				<li class="fix-type-fade fix-type-over">
					<a href="javascript:;">
						<div class="block-icon gzwm_block">
							<i class="icon-sp icon-3"></i>
							<!--<p>关注我们</p>-->
						</div>
						<div class="block-fade block-code gzwm" style="display: none; opacity: 1;">
							<img class="img_code" src="${basePath }/resource/images/img/twocode_01.jpg" />
							<p class="scare" style="font-size: 12px;line-height: 18px;">扫一扫，关注微信号</p>
						</div>
					</a>
				</li>
				<li class="fix-type-over">
					<a href="Mailto:">
						<div class="block-icon" style="border-bottom: 1px solid #4db7f3">
							<i class="icon-sp icon-4"></i>
						</div>
						<p class="send-mail">点击发送邮件</p>
					</a>
				</li>
				<li class="fix-type-top">
					<a href="javascript:;">
						<div class="block-icon">
							<i class="icon-sp icon-5"></i>
							<p class="back-top">返回顶部</p>
						</div>
					</a>
				</li>
			</ul>
		</div>
</body>
</html>
