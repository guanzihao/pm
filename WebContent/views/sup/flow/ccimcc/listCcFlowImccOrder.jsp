<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link rel="stylesheet" href="${basePath}/resource/css/dingdanguanli_1.css" />
<style>
.hidden-mod {
    display: none
}

.mo-mod-header-title h1 a {
    text-indent: -5000em;
    background:
        url();
    background-size: 115px 43px;
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
.mo-main-wrap {
    padding: 0 !important;
}

</style>
<body class="alibaba page-esc" data-spm="8047887"
    data-widget-cid="widget-2">
        <!-- tangram:1026 begin-->
                <div class="main-wrap">
                    <div class="mo-main-wrap">
                        <div id="logistic-add" style="display: block;">
                            
                        </div>

                        <div
                            class="ui2-notice ui2-notice-small ui2-notice-warning ui2-notice-hasbd">
                            <i class="ui2-icon ui2-icon-warning"></i>
                            <div class="ui2-notice-content">
                                友情提示：如果一次下单，选择了多项服务，会按服务类型自动拆分到各个订单列表中！客户提交订单，服务商接收前，订单可以撤回！
                            </div>
                        </div>

                        <div class="mo-list-nav ui2-navigation ui2-navigation-wide">
                            <div class="ui2-navigation-content util-clearfix">

									 <a class="ui2-navigation-item " href="${basePath}/sup/supcompany/order/toFlowImccPage/${isCompany}" data-name="expressExportOrder"> 进口报关 </a> 
	                                <a class="ui2-navigation-item "  href="${basePath}/sup/supcompany/order/toBgFlowExccOrderPage/${isCompany}"  data-name="logisticOrder"> 出口报关 </a>
	                                <a class="ui2-navigation-item" href="${basePath}/sup/supcompany/order/toWlFlowImccOrder/${isCompany}"  data-name="partnerLogisticsOrder"> 进口物流 </a>
	                                <a class="ui2-navigation-item " href="${basePath}/sup/supcompany/order/toWlFlowExccOrder/${isCompany}"  data-name="forwarderLogisticsOrder"> 出口物流</a> 
	                                <a class="ui2-navigation-item "  href="${basePath}/sup/supcompany/order/toWlTransportOrder/${isCompany}"  data-name="co"> 物流运输 </a> 
	                                
	                                <a class="ui2-navigation-item ui2-navigation-item-current" href="${basePath}/sup/supcompany/order/toCCFlowImccOrder/${isCompany}"  data-name="creditOrder"> 仓储进库 </a>
	                                <a class="ui2-navigation-item" href="${basePath}/sup/supcompany/order/toCCFlowExccOrder/${isCompany}" data-name="deliverTask"> 仓储出库 </a> 
	                                <a  class="ui2-navigation-item " href="${basePath}/sup/supcompany/order/toWmFlowImccOrderPage/${isCompany}" data-name="zzzy"> 外贸进口 </a>
	                                <a class="ui2-navigation-item " href="${basePath}/sup/supcompany/order/toWmFlowExccOrderPage/${isCompany}" data-name="zzzy"> 外贸出口 </a>
                            </div>
                        </div>


                        <div class="export-orders">
                            <div class="ui2-tab ui2-tab-normal zh-cn" id="ordersTab">
                                <ul class="ui2-tab-nav">
                                     <c:if test="${isCompany eq '0' }">
                                        <!-- 服务商 -->
                                        <li class="ui2-tab-active"><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/0/${flowId}/${isCompany}">
                                            所有订单 <span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/${handle}/${flowId}/${isCompany}">
                                            处理中<span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/${complete}/${flowId}/${isCompany}">
                                            已完成<span class="number"></span>
                                    </a></li>
                                     <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/10/${flowId}/${isCompany}">
                                          已接收<span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/11/${flowId}/${isCompany}">
                                          拒绝订单<span class="number"></span>
                                    </a></li>
                                     <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/14/${flowId}/${isCompany}">
                                          已终止<span class="number"></span>
                                    </a></li>
                                    </c:if>
                                   <c:if test="${isCompany eq '1' }">
                                        <!-- 客户-->
                                        <li class="ui2-tab-active"><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/0/${flowId}/${isCompany}">
                                            所有订单 <span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/${handle}/${flowId}/${isCompany}">
                                            处理中<span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/${complete}/${flowId}/${isCompany}">
                                            已完成<span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/${cansole}/${flowId}/${isCompany}">
                                            已取消 <span class="number"></span>
                                    </a></li>
                                    <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/${draft}/${flowId}/${isCompany}">
                                            草稿<span class="number"></span>
                                    </a></li>
                                     <li><a
                                        target="CcFlowImccOrder" href="${basePath}/sup/supcompany/order/showCcFlowImccOrderList/14/${flowId}/${isCompany}">
                                            已终止<span class="number"></span>
                                    </a></li>
                                    </c:if>
                                </ul>
                                <!--

                                	订单管理表格的的iframe

                                -->
                                <iframe id="test" style="width: 100%;min-height: 660px;" name="CcFlowImccOrder" src="${basePath }/sup/supcompany/order/showCcFlowImccOrderList/0/${flowId}/${isCompany}" frameborder="0" scrolling="no" border="0" framespacing="0"></iframe>
                            
                            </div>
                        </div>
                    </div>
                </div>



    <script type="text/javascript">
   
        $('.sidebar-main-menu-item').hover(function(){
            // 移入显示
            $(this).children('ul').css('display','block');
        },function(){
            // 移出隐藏
            $(this).children('ul').css('display','none');
        }); 

        $('.hot-question-robot-root').click(function(event) {
            //如果存在（不存在）就删除（添加）一个类。
            $('.hot-question-robot-root').toggleClass("mini-size");
        });

        $('.spread-node').click(function(){
          
            $(this).parent().parent().prevAll().toggleClass("hide-border");
            $(this).parent().parent().toggleClass("hide-border");
           
            $(this).parent().parent().parent().next().toggleClass("show");
        });
        $(".ui2-tab-nav li").click(function(){
             $(this).addClass("ui2-tab-active").siblings().removeClass("ui2-tab-active");
        });
   </script>
   <!--
        	iframe 自适应高度
        -->
		<script type="text/javascript">
		function reinitIframe() {
			var iframe = document.getElementById("test");
			try {
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				iframe.height = height;
			} catch(ex) {}
		}
		window.setInterval("reinitIframe()", 200);
		</script>

</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>