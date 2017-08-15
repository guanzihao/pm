<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<style type="text/css">

.sm_txt{
	padding: 0px !important;
}

</style>
<script src="${basePath }/resource/js/echarts.min.js"></script>
	<body class="gray-bg">	
		<div style="position: relative; z-index: 999;">
		<div class="m_body" style="margin-left:0px;margin-top:0px;background:#fff">
		<div class="white_place" id="white_place">
         <div class="bg_zzt" style="margin-top:15px;height: 210px;">
			<div class="bg_txt" >公告通知</div>
			<c:forEach items="${informationData}" var="item" varStatus="status">
			   <div style="float:left;margin:10px;padding-left:15px;">
			   <a onclick="toDetailPage(this)">
			      <c:choose>      
                          <c:when test="${fn:length(item[1]) > 20}">          
                              <c:out value="${fn:substring(item[1], 0, 20)}......" />      
                         </c:when>    
                         <c:otherwise>        
                              <c:out value="${item[1]}" />      
                         </c:otherwise> 
                  </c:choose>
			   </a>
			   <input  type="hidden" value="${item[0]}"/>
			   </div>
               <div style="float:right;margin:10px;padding-right:15px;">
                    <a href="#">
                       <c:out value="${fn:substring(item[2], 0, 10)}" />
                    </a>
               </div>
               <div class="clear"></div>
			</c:forEach>
            <div class="clear"></div>
		</div>
		   <div class="bg_zzt" style="    margin-left: 15px;margin-top:15px;height: 210px;margin-bottom: 20px">
			<div class="bg_txt" >消息</div>
            <c:forEach items="${noticeData}" var="item" varStatus="status">
			    <div style="float:left;margin:10px;padding-left:15px;">
			       <a onclick="toDetailNoticePage(this)">
			             <c:choose>      
                          <c:when test="${fn:length(item[1]) > 20}">          
                              <c:out value="${fn:substring(item[1], 0, 20)}......" />      
                         </c:when>    
                         <c:otherwise>        
                              <c:out value="${item[1]}" />      
                         </c:otherwise> 
                  </c:choose>                 
			       </a>
			       <input type="hidden" value="${item[0]}">
			    </div>
                <div style="float:right;margin:10px;padding-right:15px;">
                   <a href="#">
                          <c:out value="${fn:substring(item[2], 0, 10)}" /> 
                   </a>
                </div>
                <div class="clear"></div>  
		     </c:forEach>
		</div>
		 		
		<div style="font-size: 16px ;z-index: 1"> 您有<font><a href="${basePath }/sup/order/OrderFrom/listSupStatusOrder?searchName1=88" style="color: red;">${orderNum}</a></font>个订单 </div>
             <div class="sm_txt left g_f4a362 submitClass" style="margin-top: 10px !important;"><div>已提交</div><div style="font-size: 28px;">${ytjData}</div></div>
             <div class="sm_txt left g_94eb83 distributionClass" style="margin-top: 10px !important;"><div>已分配</div><div style="font-size: 28px;">${yfpData}</div></div>
             <div class="sm_txt left g_40a6fa accomplishClass" style="margin-left:0px;margin-left:15px;margin-top: 10px !important;"><div>已完成</div><div style="font-size: 28px;">${ywcData}</div></div>
             <div class="bg_zzt" style="margin-top:15px;height:400px;">
			<div class="bg_txt" >订单数</div>
			<div class="bg_inp left" >
				<input type="checkbox" name="checkbox" id="" value="1"  onclick="checkboxOnclick(this)" checked="checked"/><label title="本周按日统计">按日汇总</label>
				<input type="checkbox" name="checkbox" id="" value="2" onclick="checkboxOnclick(this)"/><label title="本月按周统计">按周汇总</label>
				<input type="checkbox" name="checkbox" id="" value="3" onclick="checkboxOnclick(this)"/><label>按月汇总</label>
				<input type="checkbox" name="checkbox" id="" value="4" onclick="checkboxOnclick(this)"/><label>按季汇总</label>
			</div>
			<div style="text-align: center;">
			    <div id="main1" style="width: 456px;height:350px;float:left;"></div>
				<div id="main2" style="width: 456px;height:350px;float:left;"></div>
				<div id="main3" style="width: 456px;height:350px;float:left;"></div>
				<div id="main4" style="width: 456px;height:350px;float:left;"></div>
			</div>
		</div>
				<div class="bg_zzt" style="margin-top:15px;margin-left: 15px;height:400px;">
			<div class="bg_txt" >接收到的订单数量及占比</div>
			<div style="text-align: center;">
			     <div id="echartsPie" style="width: 456px;margin-left:25px;height:300px;"></div>
			</div>
		</div>
		</div>

</div>

</div>

<div class="op" id="op"></div>
</body>
<script type="text/javascript">
        var myChart1 = echarts.init(document.getElementById('main1'));
		var myChart2 = echarts.init(document.getElementById('main2'));
		var myChart3 = echarts.init(document.getElementById('main3'));
		var myChart4 = echarts.init(document.getElementById('main4'));
		showBar(myChart1,"1");
		showBar(myChart2,"2");
		showBar(myChart3,"3");
		showBar(myChart4,"4");
		var echartsPie = echarts.init(document.getElementById('echartsPie'));
		showProp(echartsPie);
	   $("#main1").show();
       $("#main2").hide();
       $("#main3").hide();
       $("#main4").hide();
     $("input[type=checkbox]").click(function(){
             $(this).attr("checked","checked").siblings().removeAttr("checked");
        });
       function checkboxOnclick(checkbox){
	//alert(checkbox);
	   var check=document.getElementsByName("checkbox");
	   for(var i=0;i<check.length;i++){
         if(check[i].value==checkbox.value){
         //alert(check[i].value);
               $("#main"+check[i].value).show();
          }else{
               $("#main"+check[i].value).hide();
          }
          
       }
	   
	}
	function toDetailPage(obj){
	   var id=$(obj).next().val();
	   window.location.href="${basePath}/sup/company/company/viewComNoticeInformed/"+id;
	}
	function toDetailNoticePage(obj){
	    var id=$(obj).next().val();
	    window.location.href="${basePath}/sup/notice/notice/viewNotice/"+id;
	}
	function showProp(obj) {
		$.ajax({
			type : "post",
			url : "${basePath }/sup/supcompany/homePage/SearchBillPropBySupplier",
			dataType : "json",
			async : false,
			success : function(res) {
				var data = res;
				var nums = []; //该单据类型所占订单的数量
				var names = [];//该单据类型的名称
				var colors=[]; //该单据类型所对应的颜色码
				$.each(data, function(i, e) {
					var option = {
						name : e.name,
						value : e.value
					};
					nums.push(option);
					names.push(e.name);
					colors.push(e.color);
				});
				var pieOption = {
					title : {
						text : '',
						subtext : '',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {d} %"
					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data :[]
					},
					color:colors,
					toolbox : {
						show : true,
						 left: '350px',
						feature : {
							mark : {
								show : true
							},
							dataView : {
								show : true,
								readOnly : false
							},
							magicType : {
								show : true,
								type : [ 'pie', 'funnel' ],
								option : {
									funnel : {
										x : '25%',
										width : '50%',
										funnelAlign : 'left',
										max : 1548
									}
								}
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					series : [ {
						type : 'pie',
						radius : '55%',//饼图的半径大小  
						center : [ '50%', '60%' ],//饼图的位置  
						data : nums,
						 itemStyle:{ 
                             normal:{ 
                                label:{ 
                                    show: true, 
                                    formatter: '{b} : {c} ({d}%)' 
                                 }, 
                               labelLine :{show:true} 
                                 } 
                         } 
					} ]
				};
				//echarts.hideLoading();
				obj.setOption(pieOption);
			}
		});
	}
	//显示柱形图
	function showBar(echarts,checkData) {
		$.ajax({
			type : "post",
			url : "${basePath }/sup/supcompany/homePage/searchOrderBySupplier",
			dataType : "json",
			async : false,
			data:{"checkId":checkData},
			success : function(res) {
				//alert(data.name);
				var data = res;
				var names = [];
				var nums = [];
				$.each(data, function(i, e) {
					names.push(e.name);
					nums.push(e.num);
				});
				var option = {
					title : {
						text : ''
					},
					tooltip : {},
					legend : {
						data : [ '订单数' ]
					},
					xAxis : {
						data : names
					},
					yAxis : {},
					series : [ {
						type : 'bar',
						data : nums,
						barWidth : 30,//柱图宽度
						itemStyle : {
							normal : {
								color : function(params) {
									return '#29AAE3';
								}
							}
						}

					} ]
				};
				echarts.setOption(option,true);
			}
		});
	}
	$(".submitClass").on("click",function(){
		window.location.href='${basePath }/sup/order/OrderFrom/listSupStatusOrder?searchName1=9';
	});
	$(".distributionClass").on("click",function(){
		window.location.href='${basePath }/sup/order/OrderFrom/listSupStatusOrder?searchName1=13';
	});
	$(".accomplishClass").on("click",function(){
		window.location.href='${basePath }/sup/order/OrderFrom/listSupStatusOrder?searchName1=7';
	});
</script>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>
