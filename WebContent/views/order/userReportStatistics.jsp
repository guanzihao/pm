<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
  <script src="${basePath }/resource/js/echarts.min.js"></script>
	<style type="text/css">
		#main{
			margin-left: 90px;
			margin-top: 60px;
		}
	</style>
<body class="gray-bg">
	<%
		List<Date> listDate=(List<Date>)request.getAttribute("listDate");
		List<Integer> orderMemberNum=(List<Integer>)request.getAttribute("orderMemberNum");
		List<Integer> newRegisterMember=(List<Integer>)request.getAttribute("newRegisterMember");
		List<Integer> newCheckMember=(List<Integer>)request.getAttribute("newCheckMember");
	%>
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>平台用户统计表</h5>
	                </div>
	                <div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/order/listUserReportStatistics/0" class="form-horizontal formValidate" method="post">
								<div class="row">
									<div class="col-sm-12" style="padding-left: 200px;">
										<label class="tables_search_label"> 查询时间：
											<input type="text" class="form-control input-sm date-picker my_input startDate" name="startDate" value="${startDate }" required="">-<input type="text" class="form-control input-sm date-picker my_input endDate" name="endDate" value="${endDate }" required="">
											<input type="hidden" name="valueDate" value="0">
										</label>
										<label class="tables_search_label"> 
											<button type="button" class="btn btn-sm btn-primary submit button1" >查询</button>
										</label>
										<label class="tables_search_label"> 
										    <select class="form-control valueDateClass" name="valueDate" onchange="valueDateOnchange()">
										      <c:if test="${valueDate eq '0' }">
										      	 <option value="-1" selected>--快速查看--</option>
										      	 <option value="1">最近1个月</option>
											      <option value="2">最近2个月</option>
											      <option value="3">最近3个月</option>
										      </c:if>
										      <c:if test="${valueDate eq '1' }">
										      	 <option value="-1">--快速查看--</option>
										      	 <option value="1" selected>最近1个月</option>
											      <option value="2">最近2个月</option>
											      <option value="3">最近3个月</option>
										      </c:if>
										      <c:if test="${valueDate eq '2' }">
										      	 <option value="-1">--快速查看--</option>
										      	 <option value="1">最近1个月</option>
											      <option value="2" selected>最近2个月</option>
											      <option value="3">最近3个月</option>
										      </c:if>
										      <c:if test="${valueDate eq '3' }">
										      	 <option value="-1">--快速查看--</option>
										      	 <option value="1">最近1个月</option>
											      <option value="2">最近2个月</option>
											      <option value="3" selected>最近3个月</option>
										      </c:if>
										      
										    </select>
										</label>
									 </div>
								</div>
								<div class="table-responsive">
									<div id="main"  style="width: 800px;height:450px;"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
option = {
    title: {
        text: '平台用户统计表',
        subtext: ''
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
    },
    legend: {
        data: ['新注册会员数', '新认证会员数','下单会员数']
    },
    toolbox: {
        show: true,
        left: '600px',
        feature: {
            dataZoom: {},
            dataView: {
                readOnly: false
            },
            magicType: {
                type: ['line', 'bar']
            },
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data:<%=listDate%>
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value}'
        }
    },
    series: [{
        name: '新注册会员数',
        type: 'line',
        data: <%=newRegisterMember%>,
        markPoint: {
            data: [{
                type: '',
                name: ''
            }, {
                type: '',
                name: ''
            }]
        },
        markLine: {
            data: [{
                type: 'average',
                name: '平均值'
            }]
        }
    }, {
        name: '新认证会员数',
        type: 'line',
        data: <%=newCheckMember%>,
        markPoint: {
            data: [{
                name: '周最低',
                value: -2,
                xAxis: 1
               
            }]
        },
        markLine: {
            data: [{
                type: 'average',
                name: '平均值'
            }]
        }
    }, {
        name: '下单会员数',
        type: 'line',
        data: <%=orderMemberNum%>,
        markPoint: {
            data: [{
                name: '周最低',
                value: -2,
                xAxis: 1
                
            }]
        },
        markLine: {
            data: [{
                type: 'average',
                name: '平均值'
            }]
        }
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
function valueDateOnchange(){
	var valueDate=$(".valueDateClass").val();
	if (valueDate!='-1') {
		window.location.href="${basePath }/order/listUserReportStatistics/"+valueDate;
	}
}
$(".submit").on("click",function(){
	 $("#pageForm").validate();
	if($("#pageForm").valid()){
		var endDate=$(".endDate").val();
		var startDate=$(".startDate").val();
		var flag=duibi(startDate,endDate,"开始时间","结束时间");
		if (flag) {
			$("#pageForm").submit();
		}
	}
	/* 
	alert(endDate);
	
	alert(startDate);
	if (startDate==null||startDate=='') {
		alert(123);
		layer.msg('必须填写开始时间！',{icon: 2});
		return false;
	}
	if (endDate==null||endDate=='') {
		layer.msg('必须填写开始时间！',{icon: 2});
		return false;
	} */
	
});
function duibi(startDate, endDate,startMessage,endMessage) {
	var arr = startDate.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();
	var arrs = endDate.split("-");
	var lktime = new Date(arrs[0], arrs[1], arrs[2]);
	var lktimes = lktime.getTime();
	if(starttimes > lktimes) {
		layer.msg(startMessage+'不能大于'+endMessage+'，请重新输入！',{icon: 2});
		return false;
	} 
	return true;
}

</script>
