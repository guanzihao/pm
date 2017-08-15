<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeTag.jsp" %>
<!DOCTYPE html>

<html>
<head>
<style>
   .showlij{
     color:#1c569f;
   }
   .baoJia1{
   	margin-left: 15px;
   }
   .baoJia2{
   	margin-left: 22px;
   }
   .baoJia3{
   	margin-left: 22px;
   }
   .baoJia4{
   	margin-left: 22px;
   }
   .baoJia5{
   	margin-left: 25px;
   }
   .baoJiaMx1{
   	margin-left: 15px;
   }
   .baoJiaMx2{
   	margin-left: 22px;
   }
   .baoJiaMx3{
   	margin-left: 22px;
   }
   .baoJiaMx4{
   	margin-left: 22px;
   }
   .baoJiaMx5{
   	margin-left: 25px;
   }
   
   .ccBaoJia1{
   	margin-left: 35px;
   }
   .ccBaoJia2{
   	margin-left: 55px;
   }
   .ccBaoJia3{
   	margin-left: 85px;
   }
   .ccBaoJia4{
   	margin-left: 35px;
   }
   .ccBaoJiaMx1{
   	margin-left: 35px;
   }
   .ccBaoJiaMx2{
   	margin-left: 55px;
   }
   .ccBaoJiaMx3{
   	margin-left: 85px;
   }
   .ccBaoJiaMx4{
   	margin-left: 35px;
   }
   
</style>

		
</head>
	<body>
	
		<jsp:include page="/views/internet/header.jsp"></jsp:include>
		<input type="hidden" class="flowTypeClass" name="flowType" value="报关">
		<div class="banner">
			<div class="banner_k">

				<div class="b-img">
					<a href="#" style="background:url(${basePath }/resource/images/img/banner_index_2.jpg) center no-repeat;background-size: 100%;">
						<div class="width2">
							<div class="txt_lunbo4 ">
								<div style="font-size:36px;color:#fff;margin-top:30px;margin-bottom:50px;margin-left:30px;">以通关为核心的一站式跨境供应链服务平台</div>
								<div class="sure_btn"><input type="button" id="" onclick="toPageBycheckUser()" value="立即下单" /></div>
							</div>
						</div>
					</a>
					<a href="#" style="background:url(${basePath }/resource/images/img/banner_woyaoruzhu_1.jpg) center no-repeat;background-size: 100%;">
						<div class="width2">
							<div class="txt_lunbo ">
								<div style="font-size:36px;color:#fff;margin-top:90px;margin-bottom:50px;">自助下单&nbsp;&nbsp;自助查询&nbsp;&nbsp;订单跟踪</div>
								<div class="sure_btn"><input type="button" id="" onclick="toPageBycheckUser()" value="立即下单" /></div>
							</div>
						</div>
					</a>
					<a href="#" style="background:url(${basePath }/resource/images/img/banner_baoguan_4.jpg) center no-repeat;background-size: 100%;">
						<div class="width2">
							<div class="txt_lunbo3 ">
								<div style="font-size:36px;color:#1683dd;margin-top:30px;margin-bottom:50px;margin-left:110px;">单一窗口集成通关 线上线下融通海外</div>
								<div class="sure_btn"><input type="button" id="" onclick="toPageBycheckUser()" value="立即下单" /></div>
							</div>
						</div>
					</a>
				</div>
				<div class="b-list"></div>
			</div>
		</div>
		
		<div class="zzfu">
			<ul>
				<li  style="margin-left: 70px;">
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','1')"><img src="${basePath }/resource/images/img/icon1.jpg" width="50" height="50" alt="" style="display:block" />HS智通归类</div>
				</li>
				<li>
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','2')"><img src="${basePath }/resource/images/img/icon2.jpg" width="50" height="50" alt="" style="display:block" />模拟计税工具</div>
				</li>
				<li>
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','3')"><img src="${basePath }/resource/images/img/icon3.jpg" width="50" height="50" alt="" style="display:block" />3C目录查询</div>
				</li>
				<li>
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','4')"><img src="${basePath }/resource/images/img/icon4.jpg" width="50" height="50" alt="" style="display:block" />商品归类助手</div>
				</li>
				<li>
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','5')"><img src="${basePath }/resource/images/img/icon5.jpg" width="50" height="50" alt="" style="display:block" />双反查询</div>
				</li>
				<li>
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','6')"><img src="${basePath }/resource/images/img/icon6.jpg" width="50" height="50" alt="" style="display:block" />能效目录查询</div>
				</li>
				<li>
					<div onclick="dandianLogin('${basePath }/common/company/dandianLogin/DanDianLogin','7')"><img src="${basePath }/resource/images/img/icon8.jpg" width="50" height="50" alt="" style="display:block" />三人行</div>
				</li>
			</ul>
		</div>
		
		<div class="function_k" style="margin-top: 20px;">
			<div class="function">
				<div>业务委托</div>
			</div>
		</div>
		<div class="bjwt_k">
		<div class="z_p">认证服务商 <span style="color:#fa721a" id="sup"></span>&nbsp;&nbsp;| &nbsp;&nbsp;认证会员<span style="color:#fa721a" id="com"></span> &nbsp;&nbsp;|&nbsp;&nbsp; 累计委托<span style="color:#fa721a" id="ordercountinfo"></span>&nbsp;&nbsp; | &nbsp;&nbsp;累计成交<span style="color:#fa721a" id="order"> </span></div>
			<div class="bjwt_left left">
				<div class="content">
					<div class="c_list">
						<ul>
							<li><a style="cursor:pointer;font-size: 18px;font-weight: bold;" class="c_f87d30" onclick="bgOnclick()">报关</a></li>
							<li><a style="cursor:pointer;font-size: 18px;font-weight: bold;" class="showlij " onclick="wlOnclick()">物流</a></li>
							<li><a style="cursor:pointer;font-size: 18px;font-weight: bold;" class="showlij " onclick="ccOnclick()">仓储</a></li>
							<li><a style="cursor:pointer;font-size: 18px;font-weight: bold;" class="showlij " onclick="wmOnclick()">外贸</a></li>
						</ul>
						<input type="hidden" id="clickIndex"value="0"/>
					</div>
					
					 <div id="proxyPage"></div>
				</div>
			</div>
			<div class="bjwt_right left">
				<div class="content">
					<div class="c_list">
						<ul>
							<li>
								<a href="#" class="c_f87d30">服务报价</a>
							</li>
						</ul>
					</div>
					<div id="serviceQutation" class="t_type2 ty_li">
										
					</div>	
				</div>
				<div class="content">
					<div class="c_list">
						<ul>
							<li>
								<a href="#" class="c_f87d30">服务商排行榜</a>
							</li>
						</ul>
					</div>
					<div class="t_type2 ty_li">
						<ul>
							<li class="p_i"><img src="${basePath }/resource/images/img/s1.jpg" width="30" height="30" alt="" /></li>
							<li>上海某某公司</li>
							<li>交易总量 180</li>
						</ul>
					</div>
					<div class="t_type2 ty_li">
						<ul>
							<li class="p_i"><img src="${basePath }/resource/images/img/s2.jpg" width="30" height="30" alt="" /></li>
							<li>上海某某公司</li>
							<li>交易总量 170</li>
						</ul>
					</div>
					<div class="t_type2 ty_li">
						<ul>
							<li class="p_i"><img src="${basePath }/resource/images/img/s3.jpg" width="30" height="30" alt="" /></li>
							<li>上海某某公司</li>
							<li>交易总量 160</li>
						</ul>
					</div>

				</div>
			</div>
		</div>
		<div class="function_k" style="margin-top: 40px;">
			<div class="function">
				<div>业务介绍</div>
			</div>
		</div>
		<div class="content-four">
			<ul class="contentbox-four">
				
				<li class="con-four y1 t_z" style="margin-right: 49px;">
				<div class="m_le23">
						<span class="s_first">通关</span>
						<span class="s_second">平台立足于满足客户在关务<br>咨询、检务咨询、商品归类...</span>
					</div>
					<div class="txt-four">
						<h3>报关</h3>
						<p>平台立足于满足客户在关务咨询、检务咨询、商品<br>归类、报关报检、许可证件办理等增值服务需求</p>
					</div>
					
				</li>
				<li class="con-four y2 t_z" style="margin-right: 49px;">
				<div class="m_le23">
						<span class="s_first">物流</span>
						<span class="s_second">平台为客户提供空运、海运<br>进出口货物的国际运输代理...</span>
					</div>
					<div class="txt-four">
						<h3>物流</h3>
						<p>平台为客户提供空运、海运进出口货物的国际运输代理<br>业务，包括揽货、订舱、托运、仓储、包装、<br>集装箱拆箱等一站式门到门全程服。</p>
					</div>
					
				</li>
				<li class="con-four y3 t_z">
				<div class="m_le23">
						<span class="s_first">外贸</span>
						<span class="s_second">平台为跨境电商快消品进入中国<br>市场办理许可证资质、外贸...</span>
					</div>
					<div class="txt-four">
						<h3>外贸</h3>
						<p>平台为跨境电商快消品进入中国市场办理许可证资质、<br>外贸代理、品牌代理、企业备案、证件办理<br>等服务。</p>
					</div>
					
				</li>
				<li class="con-four y4 t_z" style="margin-right: 49px;">
				<div class="m_le23">
						<span class="s_first">仓储</span>
						<span>平台在全国多个城市建立<br>
                    了各类型的仓库，为企业...</span>
					</div>
					<div class="txt-four">
						<h3>仓储</h3>
						<p>平台在全国多个城市建立了各类型的仓库，为企业<br>提供 个性化仓储服务，发挥着区域广、功能全、
							<br>通关强、客户多的优势 客户优先找到并选择您。
						</p>
					</div>
					
				</li>
				<li class="con-four y5 t_z" style="margin-right: 49px;">
				<div class="m_le23">
						<span class="s_first">跨境电商</span>
						<span class="s_second">为跨境贸易商提供跨境供应<br>链服务，解决快消品进入...</span>
					</div>
					<div class="txt-four">
						<h3>跨境电商</h3>
						<p>为跨境贸易商提供跨境供应链服务，解决快消品进入市<br>场的供应链的问题，提供线上电商平台和线下<br>渠道的销售。</p>
					</div>
					
				</li>
				<li class="con-four y6 t_z">
					<div class="m_le23">
						<span class="s_first">信息</span>
						<span class="s_second">平台以“让通关更便捷”为使命，<br>打造一站式智能通关服务...</span>
					</div>
					<div class="txt-four">
						<h3>信息</h3>
						<p>平台以“让通关更便捷”为使命，打造一站式智能通关服<br>务平台，为企业提供报关管理、智能制单、通关<br>信息化服务</p>
					</div>
				</li>
				
			</ul>
		</div>
		<!--</div>-->

		<div class="zxxx">
			<div style="padding: 20px 0px;">
				<div class="function_k" style="margin-top: 0px;">
					<div class="function">
						<div style="background: #fff;">资讯信息</div>
					</div>
				</div>
			</div>
			<div class="zcfg left">
				<ul>
					<li id="one"  class="one" >政策法规</li>
					<li id="two"  class="one"  >行业新闻</li>
					<li id="three"  class="one" >服务商动态</li>
				</ul>
			</div>
			<div class="fg_img left"></div>
			<div class="content_01 left" id="webContent">
				
			</div>
		</div>
		<div class="function_k" style="margin-top: 40px;">
			<div class="function">
				<div>合作伙伴</div>
			</div>
		</div>
		<div class="zcfg2">
			<ul>
			  <li onMouseMove="JavaScript:ChangeDiv('0','JKDiv_',1)" style="border-right: 4px solid #FFFFFF;">行业协会</li>
              <li onMouseMove="JavaScript:ChangeDiv('1','JKDiv_',1)">服务客户</li>
			</ul>
		</div>
		<div class="hzhb" id="JKDiv_0" style="display:none">
			<ul>
				<li>
					<div class="img_logo logo15"></div>
					<div>中国报关协会</div>
				</li>
				<li>
					<div class="img_logo logo16"></div>
					<div>中国国际贸易促进委员会</div>
				</li>
				<li>
					<div class="img_logo logo11"></div>
					<div>中国出入境检验检疫协会</div>
				</li>
				<li>
					<div class="img_logo logo41"></div>
					<div>国际报关协会同盟</div>
				</li>

				<li>
					<div class="img_logo logo42"></div>
					<div>美国供应链管理专业协会</div>
				</li>

				<li>
					<div class="img_logo logo43"></div>
					<div>美商会</div>
				</li>
				<li>
					<div class="img_logo logo44"></div>
					<div>中国国际货运代理行业协会</div>
				</li>

			</ul>
		</div>
		<div class="hzhb2" id="JKDiv_1">
			<ul>
				<li>
					<div class="img_logo logo1"></div>
					<div>美国BD公司</div>
				</li>
				<li>
					<div class="img_logo logo2"></div>
					<div>IKEA</div>
				</li>
				<li>
					<div class="img_logo logo3"></div>
					<div>NIKE</div>
				</li>
				<li>
					<div class="img_logo logo4"></div>
					<div>DHL</div>
				</li>
				<li>
					<div class="img_logo logo5"></div>
					<div>3M</div>
				</li>
				<li>
					<div class="img_logo logo6"></div>
					<div>DISNEY</div>
				</li>
				<li>
					<div class="img_logo logo7"></div>
					<div>BMW</div>
				</li>
			</ul>
		</div>
		<div style="">
				<div class="function_k" style="margin-top: 40px;width: 230px;">
					<div class="function" style="width: 230px;">
						<div style="width: 230px;">平台推荐服务商</div>
					</div>
				</div>
			</div>
		<div class="hzhb" id="JKDiv_0">
			<ul>
				<li>
					<div class="img_logo logoe"></div>
					<div>欣海</div>
				</li>
				<li>
					<div class="img_logo logod"></div>
					<div>欧坚</div>
				</li>
				<li>
					<div class="img_logo logoa"></div>
					<div>华鹏飞</div>
				</li>
				<li>
					<div class="img_logo logoc"></div>
					<div>欧高</div>
				</li>
				<li>
					<div class="img_logo logoi"></div>
					<div>广州卓志</div>
				</li>
				<li>
					<div class="img_logo logok"></div>
					<div>江苏跨境</div>
				</li>
				<li>
					<div class="img_logo logol"></div>
					<div>普洛斯</div>
				</li>

			</ul>
		</div>
		<script>
        function ChangeDiv(divId,divName,zDivCount) 
		{ 
		 for(i=0;i<=zDivCount;i++)
		 {
		   document.getElementById(divName+i).style.display="none"; 
		 }
		 document.getElementById(divName+divId).style.display="block"; 
		}
        </script>
		
		<jsp:include page="/views/internet/footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
	
	$.ajax({
        type : "post",
        url:"${basePath}/common/ComPanyCount/companycount",
        dataType : "json",
	     success : function(data) {
	        $("#sup").html(data.map.sup+"个");
	        $("#com").html(data.map.com+"个");
	        $("#order").html(data.map.order+"单");
	        $("#ordercountinfo").html(data.map.ordercountinfo+"单");
	   }
  });
	
	
	
	
	
		$(function(){
			shownavselected('sy');
		});
	
	 function dandianLogin(url,interType){
	  		$.ajax({
	  			type:'post',
	  			url:url,
	  			dataType : "json",
	  			data:{interType:interType},
	  			async: true,
	  			success:function(data){
	  				window.open(data.map.map);
				}
	  		});
	 }
	$("#one").mouseover(function(){
		var zhengce="1";
		 $.ajax({
             type : "post",
             url:"${basePath }/common/content/ajaxfindCMSColumns",
             dataType : "json",
             async : false,
		     data:{zhengce:zhengce},
		     success : function(res) {
		     	$(".fg_img").html(null);
		     	$(".fg_img").append("<img src='${basePath }/imgServlet?imgName="+res+"' style='width:445px;height:301px' /> ");
		   }
       });
		var html="";
		 $("#webContent").html(null);
		 $.ajax({
             type : "post",
             url:"${basePath}/common/content/ajaxfindCMSContentss",
             dataType : "json",
		     data:{zhengce:zhengce},
		     success : function(res) {
		    	 $.each(res, function(i, e) {
		    		 if(i<3){
		    			 html=html+"<div style=\"margin-bottom: 25px;\">";
						 html=html+"<span class=\"num\">"+0+(i+1)+"</span>";
						 html=html+"<span style=\"font-weight: bold;\">"+e.Tile+"</span>";
						 if(e.getContent.length>34){
							 
					 		html=html+"<span style=\"font-size: 12px;\" class=\"c_999\">"+e.getContent.substr(0,32)+"......</span>";
						 }else{
							 html=html+"<span style=\"font-size: 12px;\" class=\"c_999\">"+e.getContent+"</span>";
						 }
						 html=html+"</div>";
		    		 }
		    	 })
		    	 html=html+"<a class='right' href=\"${basePath }/common/content/findCMSContentss/1\" >更多</a>";
		    	 $("#webContent").append(html);
		   }
       });
	})
	
	$("#two").mouseover(function(){
		var zhengce="2";
		 $.ajax({
             type : "post",
             url:"${basePath }/common/content/ajaxfindCMSColumns",
             dataType : "json",
             async : false,
		     data:{zhengce:zhengce},
		     success : function(res) {
		     	$(".fg_img").html(null);
		     	$(".fg_img").append("<img src='${basePath }/imgServlet?imgName="+res+"' style='width:445px;height:301px' /> ");
		   }
       });
		var html="";
		 $("#webContent").html(null);
		 $.ajax({
             type : "post",
             url:"${basePath}/common/content/ajaxfindCMSContentss",
             dataType : "json",
		     data:{zhengce:zhengce},
		     success : function(res) {
		    	 $.each(res, function(i, e) {
		    		 if(i<3){
		    			 html=html+"<div style=\"margin-bottom: 25px;\">";
						 html=html+"<span class=\"num\">"+0+(i+1)+"</span>";
						 html=html+"<span style=\"font-weight: bold;\">"+e.Tile+"</span>";
						 if(e.getContent.length>34){
							 
					 		html=html+"<span style=\"font-size: 12px;\" class=\"c_999\">"+e.getContent.substr(0,32)+"......</span>";
						 }else{
							 html=html+"<span style=\"font-size: 12px;\" class=\"c_999\">"+e.getContent+"</span>";
						 }
						 html=html+"</div>";
		    		 }
		    	 })
		    	 html=html+"<a class='right' href=\"${basePath }/common/content/findCMSContentss/2\" >更多</a>";
		    	 $("#webContent").append(html);
		   }
       });
	})
	$("#three").mouseover(function(){
		var zhengce="3";
		 $.ajax({
             type : "post",
             url:"${basePath }/common/content/ajaxfindCMSColumns",
             dataType : "json",
             async : false,
		     data:{zhengce:zhengce},
		     success : function(res) {
		     	$(".fg_img").html(null);
		     	$(".fg_img").append("<img src='${basePath }/imgServlet?imgName="+res+"' style='width:445px;height:301px' /> ");
		   }
       });
		var html="";
		 $("#webContent").html(null);
		 $.ajax({
             type : "post",
             url:"${basePath}/common/content/ajaxfindCMSContentss",
             dataType : "json",
		     data:{zhengce:zhengce},
		     success : function(res) {
		    	 $.each(res, function(i, e) {
		    		 if(i<3){
		    			 html=html+"<div style=\"margin-bottom: 25px;\">";
						 html=html+"<span class=\"num\">"+0+(i+1)+"</span>";
						 html=html+"<span style=\"font-weight: bold;\">"+e.Tile+"</span>";
						 if(e.getContent.length>34){
							 
					 		html=html+"<span style=\"font-size: 12px;\" class=\"c_999\">"+e.getContent.substr(0,32)+"......</span>";
						 }else{
							 html=html+"<span style=\"font-size: 12px;\" class=\"c_999\">"+e.getContent+"</span>";
						 }
						 html=html+"</div>";
		    		 }
		    	 })
		    	 html=html+"<a class='right' href=\"${basePath }/common/content/findCMSContentss/3\" >更多</a>";
		    	 $("#webContent").append(html);
		   }
       });
	})
	
	
	
	$(document).ready(function() {

		$("#navul > li").hover(function() {

			$(this).addClass("navmoon");

		}, function() {

			$(this).removeClass("navmoon");

		});

		$(".con-four").hover(function() {
			$(this).find(".txt-four").stop().animate({
				"left": 0
			});
		}, function() {
			$(this).find(".txt-four").stop().animate({
				"left": -365
			});
		})

		$('.y4').click(function() {
			window.location.href = "${basePath}/views/internet/server/ccServer.jsp";
		});

		$('.y1').click(function() {
			window.location.href = "${basePath}/views/internet/server/bgServer.jsp";
		});

		$('.y2').click(function() {
			window.location.href = "${basePath}/views/internet/server/wlServer.jsp";
		});

		$('.y5').click(function() {
			window.location.href = "http://www.yunmaotong.cn/";
		});

		$('.y6').click(function() {
			window.location.href = "${basePath}/views/internet/index/messageSever.jsp";
		});

		$('.y3').click(function() {
			window.location.href = "${basePath}/views/internet/server/wmServer.jsp";
		});

		$('.gc_ffe49d').click(function() {
			window.location.href = "${basePath}/views/internet/index/lawsRegulations.jsp";
		});

		$('#dians').click(function() {
			window.open('http://www.yunmaotong.cn/');
		});

		$('#yugui').click(function() {
			window.open('http://e.etongguan.com/HS_Search/Hs_Zhcx.aspx?Type=zngl');
		});
		loadWebContent();
		if($(".c_list ul li a").eq(0).hasClass("c_f87d30")){
			loadBgTabel(1);
		    loadTabel(1,'报关');
	     }

	});
	var bgIndex=1;
    var bgQuotation=1;
     function bgfwbj(){ 
    	 var quotationIndex=loadTotalPage("报关");
	   	  if (quotationIndex>1&&quotationIndex>=bgQuotation) {
	   		 bgQuotation=(bgQuotation==quotationIndex)?1:bgQuotation+1;
	   		loadTabel(bgQuotation,"报关");
	   	  } 
  		} 
     function bgOrder(){
    	 var index=loadTotalBgPage();
   	 	if (index>=bgIndex) {
   	 			bgIndex=(bgIndex==index)?1:bgIndex+1;
   	 			loadBgTabel(bgIndex);
			}
     }
       $(".c_list ul li a").click(function(){
       //alert(111);
		//$(this).removeClass("c_f87d30").siblings().addClass("c_f87d30");
		$(".c_list ul li a").removeClass("c_f87d30").addClass("showlij");
		$(this).addClass("c_f87d30");
		var flowType=$(this).text();
		$('.flowTypeClass').val(flowType);
	  });
     //报关
       function bgOnclick(){
      	 loadBgTabel(1);
      	 loadTabel(1,'报关');
       }
     	//外贸
       function wmOnclick(){
    	   loadWmTable(1);
           loadTabel(1,"外贸");
       }
     //物流
       function wlOnclick(){
      	 loadWlTable(1);
      	 loadTabel(1,'物流');
       }
     //仓储
       function ccOnclick(){
      	 loadCcTable(1);
      	 loadTabel(1,'仓储');
       }
       window.setInterval(function(){
    			var flowType=$('.flowTypeClass').val();
    			if(flowType=="报关"){
    				bgfwbj();
    			}else if(flowType=="物流"){
    				wlfwbj();
    			}else if(flowType=="仓储"){
    				ccfwbj();
    			}else if(flowType=="外贸"){
    			 	wmfwbj();
    			}
 		},5020); 
       window.setInterval(function(){
			var flowType=$('.flowTypeClass').val();
			if(flowType=="报关"){
				 bgOrder();
			}else if(flowType=="物流"){
				 wlOrder();
			}else if(flowType=="仓储"){
				 ccOrder();
			}else if(flowType=="外贸"){
			 	 wmOrder();
			}
		},5000); 
       var wmQuotations=1;
       function wmfwbj(){ 
    	   var quotationIndex=loadTotalPage("外贸");
    	   if (quotationIndex>1&&quotationIndex>=wmQuotations) {
    		   wmQuotations=(wmQuotations==quotationIndex)?1:wmQuotations+1;
        	   loadTabel(wmQuotations,"外贸");
			}
    	} 
       var wmIndex=1;
       function wmOrder(){
    	   var index=loadTotalWmPage();
    	   if (index>=wmIndex) {
    		  wmIndex=(wmIndex==index)?1:wmIndex+1;
        	   loadWmTable(wmIndex);
			}
       }
      //ajax请求加载外贸的总页码数
      function loadTotalWmPage(){
      var pageIndex=0;
            $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/wmProxyCount",
                 dataType : "json",
			     async : false,
			     success : function(res) {
			     pageIndex= parseInt(res.totalPage);
			   }
           });
           return pageIndex;
       }
       //ajax请求加载外贸委托单信息
      function loadWmTable(wmIndex){
           $("#proxyPage").html("");
            var html="<div class='tujing'><ul><li class='baoJia1'>装运口岸/目的口岸</li><li class='baoJia2'>类别</li><li class='baoJia3'>交货时间</li><li class='baoJia4'>任务发布日期</li>";
           html=html+"<li class='baoJia5'>操作</li></ul></div>";
           $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/listWmProxy",
                 dataType : "json",
			     async : false,
			     data:{"pageIndex":wmIndex},
			     success : function(res) {
			        $.each(res, function(i, e) {
			            html=html+"<div class='t_type' style='background-color:#fff'><ul>";
			            html=html+"<li class='c_333 baoJiaMx1'><div>"+e.port_shipment+"</div><div>"+e.port_destination+"</div></li>";
					    html=html+"<li class='baoJiaMx2'>"+e.type+"</li>";
					    html=html+"<li class='baoJiaMx3'>"+e.delivery_time+"</li>";
					    html=html+"<li class='baoJiaMx4'>"+e.obj_createDate+"</li>";
					    html=html+"<li class='baoJiaMx5' style='line-height: 80px'><input onclick='toWmQutationBycheckUser()' type='button' id='' value='报价' /></li>";
					    html=html+"</ul></div>";
				   });
				  
				   
			   }
           });
             $("#proxyPage").append(html);
       }
      var ccIndex=1;
      var ccQuotation=1;
      function ccfwbj(){ 
    	  var quotationIndex=loadTotalPage("仓储");
    	  if (quotationIndex>1&&quotationIndex>=ccQuotation) {
	    	  ccQuotation=(ccQuotation==quotationIndex)?1:ccQuotation+1;
	    	  loadTabel(ccQuotation,"仓储");
    	  } 
   		} 
      function ccOrder(){
    	  var index=loadTotalCcPage();
    	 if (index>=ccIndex) {
    		 ccIndex=(ccIndex==index)?1:ccIndex+1;
             loadCcTable(ccIndex);
		}
      }
        //ajax请求加载仓储委托单的总页码数
        function loadTotalCcPage(){
        var ccPageIndex=0;
            $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/ccProxyCount",
                 dataType : "json",
			     async : false,
			     success : function(res) {
			     ccPageIndex=parseInt(res.totalPage);
			   }
           });
           return ccPageIndex;
       }
       //ajax请求加载仓储委托单信息
       function loadCcTable(ccIndex){
           $("#proxyPage").empty();
          var html="<div class='tujing'><ul><li class='ccBaoJia1'>项目描述</li><li class='ccBaoJia2'>类别</li><li class='ccBaoJia3'>任务发布日期</li>";
           html=html+"<li class='ccBaoJia4'>操作</li></ul></div>";
           $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/listCcProxy",
                 dataType : "json",
			     async : false,
			     data:{"pageIndex":ccIndex},
			     success : function(res) {
			        $.each(res, function(i, e) {
			        html=html+"<div class='t_type' style='background-color:#fff'>";
			        html=html+"<ul><li class='c_333 ccBaoJiaMx1'>"+e.destination+"</li>";
			        html=html+"<li class='ccBaoJiaMx2'>"+e.type+"</li>";
			        html=html+"<li class='ccBaoJiaMx3'>"+e.createDate+"</li>";
			        html=html+"<li class='ccBaoJiaMx4' style='line-height: 80px;'><input  type='button' id='' onclick='toCcQutationBycheckUser()' value='报价' /></li>";
			        html=html+"</ul></div>";
				   });
				   $("#proxyPage").append(html);
				   
			   }
           });
       }
       var wlIndex=1;
       var wlQuotation=1;
       function wlfwbj(){ 
    	   var quotationIndex=loadTotalPage("物流"); 
     	  if (quotationIndex>1&&quotationIndex>=wlQuotation) {
     		 wlQuotation=(wlQuotation==quotationIndex)?1:wlQuotation+1;
     		 loadTabel(wlQuotation,"物流");
     	  } 
    		} 
       function wlOrder(){
    	   var index=loadTotalWlPage();
     	 	if (index>=wlIndex) {
	     		 wlIndex=(wlIndex==index)?1:wlIndex+1;
	     		 loadWlTable(wlIndex);
 			}
       }
       
       //ajax请求加载物流委托单信息
       function loadWlTable(wlIndex){
             $("#proxyPage").empty();
             var html="<div class='tujing'><ul><li class='baoJia1'>起运港/卸运港</li>";
             html=html+"<li class='baoJia2'>类别</li>";
             html=html+"<li class='baoJia3'>件数/毛重/体积</li>";
             html=html+"<li class='baoJia4'>任务发布日期</li>";
             html=html+"<li class='baoJia5'>操作</li></ul></div>";
             $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/listWlProxy",
                 dataType : "json",
			     async : false,
			     data:{"pageIndex":wlIndex},
			     success : function(res) {
			        $.each(res, function(i, e) {
			            html=html+"<div class='t_type'>";
			            html=html+"<ul><li class='baoJiaMx1'><div>"+e.departure_port+"</div><div>"+e.discharge_port+"</div></li>";
					    html=html+"<li class='baoJiaMx2'>"+e.type+"</li>";
					    html=html+"<li class='baoJiaMx3'>";
					    html=html+"<div>"+e.item+"</div>";
					    html=html+"<div>"+e.weight+"</div>";
					    html=html+"<div>"+e.volume+"</div></li>";
					    html=html+"<li class='baoJiaMx4'>"+e.createDate+"</li>";
					    html=html+"<li class='baoJiaMx5'><input  type='button' id='' onclick='toWlQutationBycheckUser()' value='报价' /></li>";
					    html=html+"</ul></div>";
				   });
				   
			   }
           });
            $("#proxyPage").append(html);
       }
       //ajax请求加载物流委托单总页码数
       function loadTotalWlPage(){
          var  wlPageIndex=0;
            $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/wlProxyCount",
                 dataType : "json",
			     async : false,
			     success : function(res) {
			     wlPageIndex=parseInt(res.totalPage);
			   }
           });
           return  wlPageIndex;
       }
       //ajax请求报关的委托单信息
       function loadBgTabel(pageIndex){
      
           $("#proxyPage").html("");
            var html="<div class='tujing'><ul><li class='baoJia1'>货物名称</li>";
           html=html+"<li class='baoJia2'>类别</li>";
           html=html+"<li class='baoJia3'>进出口日期</li>";
           html=html+"<li class='baoJia4'>任务发布日期</li>";
           html=html+"<li class='baoJia5'>操作</li></ul> </div>";
           $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/listBgProxy",
                 dataType : "json",
			     async : false,
			     data:{"pageIndex":pageIndex},
			     success : function(res) {
			        $.each(res, function(i, e) {
			            html=html+"<div class='t_type'>";
			            html=html+"<ul><li class='baoJiaMx1'>"+e.goodName+"</li>";
			            html=html+"<li class='baoJiaMx2'>"+e.type+"</li>"; 
			            html=html+"<li class='baoJiaMx3'>"+e.exDate+"</li>";
			            html=html+"<li class='baoJiaMx4'>"+e.createDate+"</li>";
					    html=html+"<li class='baoJiaMx5' style='line-height: 80px;'><input onclick='toBgQutationBycheckUser()'  type='button' id='' value='报价' /></li>";
					    html=html+"</ul></div>";
				   });
				  
			   }
           });
            $("#proxyPage").append(html);
       }
       //ajax请求报关的委托单的总条数
       function loadTotalBgPage(){
        var pageIndex=0;
            $.ajax({
                 type : "post",
                 url:"${basePath}/common/servicequotation/bgProxyCount",
                 dataType : "json",
			     async : false,
			     success : function(res) {
			    pageIndex= parseInt(res.totalPage);
			   }
           });
           return pageIndex;
       }
     //加载服务报价总页码数
     function loadTotalPage(type){
         var serviceIndex=0;
            $.ajax({
                 type : "post",
                 url:"${basePath }/common/servicequotation/totalPageByQutation",
                 dataType : "json",
			     async : false,
			     data:{flowType:type},
			     success : function(res) {
			     // console.log(res.totalPage);
			     serviceIndex=parseInt(res.totalPage);
			   }
           });
          
           return serviceIndex;
       }
        //加载服务报价的信息
        function loadTabel(pageIndex,type){
         var html="";
         $("#serviceQutation").html("");
           $.ajax({
                 type : "post",
                 url:"${basePath }/common/servicequotation/listServiceQuotation",
                 dataType : "json",
			     async : true,
			     data:{pageIndex:pageIndex,flowType:type},
			     success : function(res) {
			        $.each(res, function(i, e) {
			            html=html+"<div class='t_type2 ty_li'><ul>";
			            html=html+"<li><div>"+e.origin_place+"-"+e.purpose_place+"</div>";
			            if(e.tonnage==null){
			               html=html+"<div>0T&nbsp;&nbsp;&nbsp;&nbsp;"+e.comName+"</div></li>";
			            }else{
			                 html=html+"<div>"+e.tonnage+"T&nbsp;&nbsp;&nbsp;&nbsp;"+e.comName+"</div></li>";
			            }
			            if(e.price==null){
			               html=html+"<li>0</li>";
			            }else{
			              html=html+"<li>"+e.price+"</li>";
			            }
			            html=html+"<li style='line-height: 60px;'><input type='button' id='' value='委托' /></li></ul></div>";
				   });
				  $("#serviceQutation").append(html);
			   }
           });
            
       }
       //加载资讯信息 
       function loadWebContent(){
    	   var zhengce="1";
    	   $.ajax({
               type : "post",
               url:"${basePath }/common/content/ajaxfindCMSColumns",
               dataType : "json",
               async : false,
		     data:{zhengce:zhengce},
		     success : function(res) {
		     	$(".fg_img").html(null);
		     	$(".fg_img").append("<img src='${basePath }/imgServlet?imgName="+res+"' style='width:445px;height:301px' /> ");
		   }
         });
           var html="";
            $("#webContent").empty();
           $.ajax({
                 type : "post",
                 url:"${basePath }/common/servicequotation/listWebContent",
                 dataType : "json",
			     async : false,
			     success : function(res) {
			        $.each(res, function(i, e) {
			            html=html+"<div style='margin-bottom: 25px;'>";
			            html=html+"<span class='num'>"+(i+1)+"</span>";
			            if(e.title!=null){
			                html=html+"<span style='font-weight: bold;''>"+e.title+"</span>";
			            }else{
			                html=html+"<span style='font-weight: bold;''></span>";
			            }
			           
			            if(e.content!=null){
			            if(e.content.length>34){
			               html=html+"<span style='font-size: 12px;' class='c_999'>"+e.content.substr(0,32)+"...</span>";
			            }else{
			                html=html+"<span style='font-size: 12px;' class='c_999'>"+e.content.substr(0,32)+"</span>";
			            }
			            }else{
			               html=html+"<span style='font-size: 12px;' class='c_999'></span>";
			            }
			            html=html+"</div>";
			           
				   });
				   html=html+"<div class='right'>更多</div>";
				  $("#webContent").append(html);
			   }
           });
       }
       
       //跳转至报关委托报价
       function toBgQutationBycheckUser(){
          window.location.href="${basePath }/common/orderquotation/orderQutation/1";
       }
       // //跳转至仓储委托报价
       function toCcQutationBycheckUser(){
          window.location.href="${basePath }/common/orderquotation/orderQutation/3";
       }
       //跳转至物流委托报价
       function toWlQutationBycheckUser(){
          window.location.href="${basePath }/common/orderquotation/orderQutation/2";
       }
       //跳转至外贸委托报价
       function toWmQutationBycheckUser(){
          window.location.href="${basePath }/common/orderquotation/orderQutation/4";
       }
       //跳转至立即下单页面
       function  toPageBycheckUser(){
           window.location.href="${basePath }/common/servicequotation/orderImmediately";
       }
</script>

<script>
	$(function(){
		$('.fixbtn .fix-type-right a').hover(function(){
//			$(this).stop().animate({'right':'-188px'}, 300);
			$(this).addClass('on');
			$('.block-content', this).fadeIn();
		}, function(){
//			$(this).stop().animate({'left':'0px'}, 300);
			$(this).removeClass('on');
			$('.block-content', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-over a').hover(function(){
			$(this).addClass('on');
		}, function(){
			$(this).removeClass('on');
		});
		$('.fixbtn .fix-type-fade a').hover(function(){
			$('.block-fade', this).fadeIn();
		}, function(){
			$('.block-fade', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-top a').click(function(){
			$("html, body").animate({scrollTop: 0}, 600);
		}).addClass('on').parent('li').hide();
		
		$(window).scroll(function (){
			var height = document.documentElement.clientHeight;
			if(0===$(window).scrollTop())
				$('.fixbtn .fix-type-top').stop().slideUp();
			else
				$('.fixbtn .fix-type-top').slideDown();

			if($(window).scrollTop() > (2000 - height)){
				$('#fixbtn').css({'bottom':'40%'})
			}
			if($(window).scrollTop() === 0){
				$('#fixbtn').css({'bottom':'5%'});
			}
		}).trigger('scroll');

		$('.fixbtn .fix-type-top a').hover(function(){
			$('.back-top', this).fadeIn();
		}, function(){
			$('.back-top', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-over a').hover(function(){
			$('.online', this).fadeIn();
		}, function(){
			$('.online', this).stop().fadeOut();
		});
		$('.fixbtn .fix-type-over a').hover(function(){
			$('.send-mail', this).fadeIn();
		}, function(){
			$('.send-mail', this).stop().fadeOut();
		});
	});
</script>
</html>