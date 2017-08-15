<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeTag.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<jsp:include page="/views/internet/header.jsp"></jsp:include>
	<div class="banner">
			<div class="banner_k">
				<div class="b-img">
					<a href="#" style="background:url(${basePath }/resource/images/img/banner_cangchu_1.jpg) center no-repeat;background-size: 100%;">
						<div class="width2">
							<div class="txt_lunbo ">
							</div>
						</div>
					</a>
					<a href="#" style="background:url(${basePath }/resource/images/img/banner_cangchu_2.jpg) center no-repeat;background-size: 100%;">
						<div class="width2">
							<div class="txt_lunbo ">
							</div>
						</div>
					</a>
				</div>
				<div class="b-list"></div>
			</div>
		</div>
	<div class="function_k">
		<div class="function">
			<div>服务介绍</div>
		</div>
	</div>
	<div class="fwjs">
			<div class="left" style="width:600px;">
				<div class="fwjs_txt">
					<div class="circle left"></div>
					<div class="txt_one left">平台仓储服务商具备海关监管、保税物流功能及普通仓储物流功能，仓储服务除常规性仓储外，以配套汽车产业的报关、报检、国际货运代理, 仓储分装、定点定时的配送为主要特色，同时全力发展进口快消食品仓库的运营。在全国多个城市建立了大型仓库，发挥着区域广、功能全、通关强、客户多的优势。</div>
					<div class="left" style="margin-right:40px;margin-top:40px;"></div>
					<div class="left" style="margin-top:40px;"></div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="left">
				<div style="margin-top:20px;margin-left:80px;"><img src="${basePath }/resource/images/img/c1.jpg" height="125" alt="" />&nbsp;&nbsp;&nbsp;&nbsp;<img src="${basePath }/resource/images/img/c2.jpg" height="125" alt="" /></div>
			</div>
		</div>
	<div class="function_k" style="margin-top: 40px;">
		<div class="function">
			<div>服务范围</div>
		</div>
	</div>
	<div class="fwjs1">
		<div class="jy_type">
			<ul>
				<li></li>
			</ul>
		</div>
		<div class="jy_content">
				<ul>
					<li>
						<dl class="m_one g1" style="margin-top:20px;width:400px;">

							<dd style="font-size: 16px !important;"><b>汽配仓库</b></dd>
							<dd style="font-size: 16px !important;"><b>电商仓库</b></dd>
							<dd style="font-size: 16px !important;"><b>医疗器械仓库</b></dd>
							<dd>保税仓库</dd>
							<dd>非保税仓库</dd>
							<dd>恒温仓库</dd>
						</dl>
					</li>
					<li>
						<dl class="m_two">
							<img src="${basePath }/resource/images/img/ct.jpg" height="350" alt="" style="margin-top:10px;margin-left:70px;" />
						</dl>
					</li>

				</ul>
			</div>
	</div>
	<div class="function_k" style="margin-top: 40px;">
		<div class="function">
			<div>服务价值</div>
		</div>
	</div>
	<div class="fwjs1 fwjz_li">
		<ul>
				<li>
					<div class="zs"></div>
					<div class="zy_txt">规模</div>
					<div>百万平米的多类型仓库遍布全国，满足不同仓储需求</div>
				</li>
				<li>
					<div class="gx"></div>
					<div class="zy_txt">高效</div>
					<div><span>24小时</span>在线客服，全程跟踪货物信息</div>
				</li>
				<li>
					<div class="aq"></div>
					<div class="zy_txt">安全</div>
					<div>严格执行<span>'6S'</span>现场管理，确保货物存储安全</div>
				</li>
				<li>
					<div class="bl"></div>
					<div class="zy_txt">便捷</div>
					<div>一站式仓库服务，提供各项增值和配套服务</div>
				</li>
			</ul>

	</div>
	<!--</div>-->
	<div class="function_k" style="margin-top: 40px;width: 230px;">
			<div class="function" style="width: 230px;">
				<div style="width: 230px;">平台推荐服务商</div>
			</div>
		</div>
		<div class="hzhb2">
			<ul>
				<li>
					<div class="img_logo logol"></div>
					<div>普洛斯</div>
				</li>
				<li>
					<div class="img_logo logo21"></div>
					<div>上海欧兴储运有限公司</div>
				</li>
			</ul>
		</div>
		
	<div class="function_k" style="margin-top: 40px;">
		<div class="function">
			<div>客户案例</div>
		</div>
		</div>
		<div class="fwjs1 adidas " id="adidas1">
			<div class="left pn_num unslider-arrow04 prev">
				<a href="javascript:void(0);" class="unslider-arrow06 prev"><img src="${basePath }/resource/images/img/pre.png" alt="" class="shang" class="arrow" id="al" alt="prev" /></a>
			</div>

			<div class="banner1" id="b06">
				<ul style="">
				<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
					<li style="">
						<div class="a1">
							<div class="left cj_pic">
								<img src="${basePath }/imgServlet?imgName=${item[2] }" style="" />
							</div>
							<div class="left ad_txt">
								<div class="ad_bt">${item[7]}：${item[3] }</div>
								<div class="detile_a" id="info">
									 <c:out value="${fn:substring(item[4],0,142)}..." />
								</div>
								<a href="${basePath }/common/help/findCMSConterTypeDetail/${item[0]}"  style="margin-top:40px;float:right;color:black;">查看详情</a>
							</div>
						</div>
					</li>
				</c:forEach>
				</ul>
			</div>

			<div class="right pn_num unslider-arrow04 next" style="" class="xia">
				<a href="javascript:void(0);" class="unslider-arrow06 next"><img src="${basePath }/resource/images/img/next.png" class="xia" class="arrow" id="ar" alt="next" /></a>
			</div>
		</div>
	

		
	</div>
	<jsp:include page="/views/internet/footer.jsp"></jsp:include>
	<script type="text/javascript">
	$(function(){
		shownavselected('fw');
	});
		//跳转至立即下单页面
		function toPageBycheckUser() {
			window.location.href = "${basePath }/common/servicequotation/orderImmediately";
		}
	</script>
</body>
</html>