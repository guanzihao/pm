<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<link href="${basePath }/resource/css/editContract_2.css" rel="stylesheet" />
<link href="${basePath }/resource/css/lijixiadan_2.css" rel="stylesheet" />

	<head>
		<meta charset="utf-8" />
		<title></title>
		<style>
			li {
				list-style-type: none;
			}
			
			.selected-package li {
				float: left;
				width: 280px;
				font-size: 14px;
				height: 24px!important;
				 line-height: 24px!important;
			}
			font{
				color: red;
				
			}
			.col-sm-3 input{
				margin-left: 25px;
			}
			.col-sm-8 input{
				margin-left: 25px;
			}
			.col-sm-3 select{
				margin-left: 25px;
			}
			.col-sm-8 textarea{
				margin-left: 25px;
			}
			/*点击前的样式*/
			
			#daohang .nav-tabs>li>a {
				padding-top: 8px;
				padding-bottom: 8px;
				line-height: 20px;
				border: 1px solid transparent;
				border-top-color: transparent;
				border-right-color: transparent;
				border-bottom-color: transparent;
				border-left-color: transparent;
				-webkit-border-radius: 4px 4px 0 0;
				-moz-border-radius: 4px 4px 0 0;
				border-radius: 4px 4px 0 0;
				color: #000000;
				background-color: #F7F8FA;
				border-top: 1px solid #DCDEE3;
				border-left: 1px solid #DCDEE3;
				border-right: 1px solid #DCDEE3;
				font-size: 14px;
				border-bottom: 0px solid #ddd;
			}
			/*点击后的样式*/
			
			#daohang .nav-tabs>.active>a,
			.nav-tabs>.active>a:hover {
				color: #EE7600;
				cursor: default;
				background-color: #ffffff;
				border: 1px solid #ddd;
				border-bottom-color: rgb(221, 221, 221);
				border-bottom-color: transparent;
				border-top: 3px solid #EE7600;
				font-size: 14px;
			}
			
			#daohang li {
				margin-left: 0px;
				display: list-item;
			}
			
			nav{
				background-color: #F5F7FA!important;
			}
			
			.nav {
			    height: 100%; 
			    /* line-height: 50px; */
			    /* color: #fff; */
			    background: #F5F7FA !important;
			    background-image: none !important; 
			    /* min-width: 1100px; */
			    position: static !important; 
			    top: 0;
			    left: 0;
			    /* z-index: 9999; */
			    width: 100%;
			    /* color: #fff; */
			    font-size: 14px;
			}
			
			/*表头的复选框*/
			input[type="checkbox"]{
				 height: 15px !important; 
				 width: 15px; 
			}
			/*表头的复选框*/
			input[type="radio"]{
				 height: 15px !important; 
			}
			/* 自助委托和全权委托的 单选按钮样式 */
			#title input[type="radio"]{
				 width: 20px !important; 
				 margin-bottom: 7px;
			}
			/* 全权委托 的边距 */
			.m_left{
				margin-left: 20px;
			}
			
		
			
			.choose_explain{
				margin-right: 50px;
			}
			
			/* 右对齐  */
			.col-xs-3{
				text-align: right;
				margin-right: 10px;
			}
			
			td input[type="text"]{
				 height: 30px !important;
				 width: 160px; 
				 padding:5px 5px;
			}
			
			/* 表头上的 功能键 */
			.tables_search_label{
				margin-bottom: 5px;			
			}
			
			/* 标签页里面的填写资料 */
			.ibox-content{
				margin: 10px 20px;
				padding-bottom: 60px;
			}
			
			
			/*标签页里面的当前所在位置   */
			#title{
				margin: 10px 20px;
				width: 1005px;
			}
			
			table tr td th{
				text-align: center;
				margin: 0 auto !important;
			}
			
		</style>
	</head>
	<body>
	<div class="dynamic-form-header" style="margin: 5px 0px ;width: 100%;height: 50px;">
				<div class="selected-package" style="">

					<ul>
						<li><span>订单编号：</span><span>${task.taskId}</span></li>
						<li><span>下单日期：</span><span>${newDate}</span></li>
						<li><span>客户名称：</span><span>${companyInfo.comName}</span></li>
						<!-- <li><span><input type="checkbox" id="isCarteBlancheId" ></span></li> -->
					</ul>

				</div>
	</div>
	<nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom: 0px;min-height:0;">
			<div class="">
				<div class="navbar-header">
					<button class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="navbar-collapse" id="navbar-collapse">
				<div id="daohang" style="">
					<ul class="nav nav-tabs" id="myTab">
						<c:forEach items="${names}" var="item" varStatus="status">
							<li <c:if test="${status.count == '1'}">class="${item.url }Class active"</c:if> <c:if test="${status.count != '1'}">class="${item.url }Class"</c:if>><a target="jjjjj" href="#box${item.url }" data-toggle="tab"> ${item.name } </a></li>
						</c:forEach>
					</ul>
				</div>
				</div>
			</div>
		</nav>
		<input type="hidden" id="bgIcOrderFromId">
		<input type="hidden" id="bgEcOrderFromId">
		<input type="hidden" id="wmIcOrderFromId">
		<input type="hidden" id="wmEcOrderFromId">
		<input type="hidden" id="ccIcOrderFromId">
		<input type="hidden" id="ccEcOrderFromId">
		<input type="hidden" id="wlIcOrderFromId">
		<input type="hidden" id="wlEcOrderFromId">
		<input type="hidden" id="wlTcOrderFromId">
	
		<input type="hidden" id="bgIciniPage" value="1">
		<input type="hidden" id="bgEciniPage" value="1">
		<input type="hidden" id="wmIciniPage" value="1">
		<input type="hidden" id="wmEciniPage" value="1">
		<input type="hidden" id="wlIciniPage" value="1">
		<input type="hidden" id="wlEciniPage" value="1">
		<input type="hidden" id="wlTciniPage" value="1">
		<input type="hidden" id="ccIciniPage" value="1">
		<input type="hidden" id="ccEciniPage" value="1">
		<input type="hidden" id="iniPage" value="${taskTypeId}">
		
		<input type="hidden" id="taskTypesId" value="${taskTypes}">
        <input type="hidden" id="taskId"  name="taskId" value="${task.id}">
        <input type="hidden" id="userId" name="buyers" value="${companyInfo.id }">
        
<!-- ########################## 1.外贸进口############################################################# -->
<div id="" class="tab-content">
<c:if test="${WmIc eq 'WmIc' }">       
	<div id="boxWmIc"  class=" wmIc tab-pane aafff tab-pane fade in " >
				<div class="row">
					<div id="main" class="col-xs-12">
					<form id="insertWmIcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
	                    
						<div id="myTabContent" class="col-xs-12">
								<div id="title" class="col-xs-12 ">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">外贸进口</span>
						            <label for="wmIcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="wmIcRadio1"   type="radio" value="0" name="radio" checked>
						            <label for="wmIcRadio2" class="font-bolder m_left">
						              		<span>全权委托</span>
						            </label>
							        <input id="wmIcRadio2" class="styled" type="radio" value="1" name="radio">
								</div>
							<article class="step col-xs-12 wmIc1-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wmIc1-2" alt=""  /> 
									
									外贸进口
								</h2>
	
								<div class="ibox-content wmIc1-3" style="padding-bottom: 30px;">
			                        	<div class="form-group">
			                        		<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            		<div class="col-sm-3"><input type="text" value="${importContract.consignor }" id="consignorwmIcId" name="consignor" class="form-control"  required=""  ></div>
				                        	<label class="col-sm-2 control-label wmIcHidden">合同号码<font></font></label>
				                            <div class="col-sm-3 wmIcHidden"><input type="text" value="${importContract.contractNo }" name="contractNo" id="contractNoWmIcId" class="form-control"    ></div>
			                        	</div>
			                        	<div class="form-group wmIcHidden">
				                        	<label class="col-sm-2 control-label">合同日期<font></font></label>
				                           <div class="col-sm-3"><input type="text"  name="contractDate"  value="<fmt:formatDate value="${importContract.contractDate }" pattern="yyyy-MM-dd"/>" id="contractDateWmIcId" class="form-control date-picker" dateISO="true"   ></div>
			                        		<pm:execute id="companyInfo" bean="companyInfoUserBusinImpl" method="getCompanyInfo">
												<pm:execute-param type="java.lang.String" value="${importContract.buyers }" />
											</pm:execute>
				                        	<label class="col-sm-2 control-label">买方<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="" name="buyers" class="form-control" id="buyersWmIcId"   ></div>
			                        	</div>
			                        	<div class="form-group wmIcHidden">
				                        	<label class="col-sm-2 control-label">买方传真<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="" name="bFax" class="form-control" id="bFaxWmIcId"    ></div>
				                        	<label class="col-sm-2 control-label">买方联系方式<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="" name="bTelphone"  id="bTelphoneWmIcId"  class="form-control"    ></div>
			                        	</div>
			                        	<div class="form-group wmIcHidden">
				                        	<label class="col-sm-2 control-label">买方地址<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="" name="bAddress" class="form-control"    id="bAddressWmIcId"  ></div>
				                        	<label class="col-sm-2 control-label">卖方<font></font></label>
				                            <div class="col-sm-3"><input type="text" value=""   id="sellersWmIcId" class="form-control sellersName"    ></div>
			                        	</div>
			                        	<div class="form-group wmIcHidden">
				                        	<label class="col-sm-2 control-label">卖方地址<font></font></label>
				                            <div class="col-sm-3"><input type="text"  value="${importContract.sAddress }"  id="sAddressWmIcId" name="sAddress" class="form-control sAddress"    ></div>
				                        	<label class="col-sm-2 control-label">卖方传真<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${importContract.sFax }" name="sFax" class="form-control sFax" id="sFaxWmIcId"    ></div>
			                        	</div>
			                        	<div class="form-group wmIcHidden">
				                        	<label class="col-sm-2 control-label">卖方联系方式<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${importContract.sTelphone }" name="sTelphone"  id="sTelphoneWmIcId" class="form-control sTelphone"    ></div>
			                        	</div>
									<div class="col-xs-12 ">
										<button type="button" class="ui_form_button ui_form_right wmIc1-4" style="">下一步</button>
									</div>
	
								</div>
	
							</article>
	
							<article class="row step col-xs-12 wmIc2-1 wmIcHidden">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wmIc2-2" alt="" />
									产品及开票人信息
								</h2>
	
								<div class="step_content ibox-content wmIc2-3" style="display: none;">
									<div class="row">
										<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="wmIcaddTr2('wmIcTab', -1)">
												 <span> 添加一行</span>
											</a> <a class="btn btn-white btn-sm" href="javascript:;" onclick="wmIcdelTr2()"> 
												<span> 删除行</span>
											</a>
										</div>
									</div>
									<table id="wmIcTab" class="table table-striped table-bordered table-hover">
										<thead>
											<tr align="center">
												<th></th>
												<th>商品名称<i class="ired">*</i></th>
												<th>型号</th>
												<th>数量<i class="ired">*</i></th>
												<th>单价</th>
												<th>总价<i class="ired">*</i></th>
											</tr>
										</thead>
									</table>
									
									<div class="col-xs-12">
										<button type="button"  class="btn btn-primary ui-form-control J-children ui_form_button ui_form_right wmIc2-4" >下一步</button>
									</div>
	
								</div>
	
							</article>
	
							<article class="step col-xs-12 wmIc3-1 wmIcHidden">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wmIc3-2" alt="" />
									运输及付款信息
								</h2>
								<div class="ibox-content wmIc3-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">成交方式<i class="ired">*</i></label>
			                            <div class="col-sm-3"><input type="text" id="manufactoryWmIcId" value="${importContract.currency }" name="manufactory" class="form-control"  required=""  ></div>
			                        	<label class="col-sm-2 control-label">币种<i class="ired">*</i></label>
			                            <div class="col-sm-3"><input id="inspectionWmIcId" type="text" value="${importContract.payWay }" name="payWay" class="form-control"  required=""  ></div>
		                        	</div>
		                        	<div class="form-group">
			                        	<label class="col-sm-2 control-label">付款条件<font></font></label>
			                            <div class="col-sm-3"><input type="text" id="termPaymentWmIcId" value="${importContract.termPayment }" name="termPayment" class="form-control"    ></div>
			                        	<label class="col-sm-2 control-label">仲裁条款<font></font></label>
			                            <div class="col-sm-3"><input type="text" id="arbitrationWmIcId" value="${importContract.arbitration }" name="arbitration" class="form-control"    ></div>
		                        	</div>
		                    		<div class="form-group">
			                        	<label class="col-sm-2 control-label">装运时间<font></font></label>
			                           <div class="col-sm-3"><input type="text" id="shipmentTimeWmIcId" name="shipmentTime"  value="<fmt:formatDate value="${importContract.shipmentTime }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
			                        	<label class="col-sm-2 control-label">交货时间<font></font></label>
			                            <div class="col-sm-3"><input id="deliveryTimeWmIcId" type="text" value="${importContract.deliveryTime }" name="deliveryTime" class="form-control date-picker"     dateISO="true"></div>
		                        	</div>
		                        	<div class="form-group">
			                        	<label class="col-sm-2 control-label">装运口岸<font></font></label>
			                            <div class="col-sm-3"><input id="portShipmentWmIcId" type="text" value="${importContract.portShipment }" name="portShipment" class="form-control"    ></div>
			                        	<label class="col-sm-2 control-label">目的口岸<font></font></label>
			                            <div class="col-sm-3"><input type="text" id="portDestinationWmIcId" value="${importContract.portDestination }" name="portDestination" class="form-control"    ></div>
		                        	</div>
		                        	<div class="form-group">
			                        	<label class="col-sm-2 control-label">包装<font></font></label>
			                            <div class="col-sm-3"><input type="text" id="packingWmIcId" value="${importContract.packing }" name="packing" class="form-control"    ></div>
			                        	<label class="col-sm-2 control-label">保险<font></font></label>
			                            <div class="col-sm-3"><input type="text" id="insuranceWmIcId" value="${importContract.insurance }" name="insurance" class="form-control"    ></div>
		                        	</div>
		                        	<div class="form-group">
			                        	<label class="col-sm-2 control-label">注解及说明<i class="ired">*</i></label>
			                            <div class="col-sm-8"><input type="text" id="othersWmIcId" value="${importContract.others }" name="others" class="form-control"  required="" ></div>
		                        	</div>
	                        	<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wmIc3-4" style="">下一步</button>
								</div>
							</div>
							</article>
							<article class="step col-xs-12 wmIc4-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wmIc4-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content wmIc4-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateWmIcId" name="shipmentTime"  value="<fmt:formatDate value="${importContract.shipmentTime }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateWmIcId" type="text" value="${importContract.deliveryTime }" name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
			                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
										<div class="col-sm-8"><textarea name="explain" id="explainWmIcId" class="form-control " rows="6" ></textarea></div>
									</div>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  wmIc4-4">下一步</button>
									</div>
								</div>
							</article>
							<article class="step col-xs-12 wmIc5-1 wmIcHidden">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f5.png" class="step_icon wmIc5-2" alt="" />
									附件上传
								</h2>
								<div class="step_content ibox-content wmIc5-3" style="display: none;">
								
									<div class="form-group">
					                            <div class="col-sm-8">
					                            	<pm:fileList metaObject="${importContract }" delete="true" name="filesWmIc" metaColums="columsWmIc"/>
					                            	<c:import url="/include/includeUploadify.jsp">
					                            		<c:param  name="propertyName" value="filesWmIc"/>
														<c:param  name="metaColums" value="columsWmIc"/>
												    </c:import>
					                            </div>
					                      </div>
								</div>
							</article>
	
							<div class="col_xs_12">
								<button type="button" class="ui_button btn btn-primary ui-form-control J-children submit wmIc_submit">提交订单</button>
								
							</div>
						</div>
					</form>
					</div>
					<div>
					</div>
				</div>
	</div>
</c:if>
<!--##########################2.外贸出口合同###################################################################################################################-->
<c:if test="${WmEc eq 'WmEc' }">
 <div id="boxWmEc" class=" wmEc  aafff tab-pane fade in " >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertWmEcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
								<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">外贸进口</span>
						            <label for="wmEcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="wmEcRadio1" class="styled" type="radio" value="0" name="radio" checked>
						            <label for="wmEcRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="wmEcRadio2" class="styled" type="radio" value="1" name="radio">
								</div>

						<article class="step col-xs-12 wmEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wmEc1-2" alt=""  />
								外贸出口
							</h2>
							<div class="ibox-content wmEc1-3">
	                        			<div class="form-group">
				                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            		<div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="consignorwmEcId" name="consignor" class="form-control"  required=""  ></div>
				                        	<label class="col-sm-2 control-label wmEcHidden">合同号码<font></font></label>
				                            <div class="col-sm-3 wmEcHidden"><input type="text" value="${importContract.contractNo }" name="contractNo" id="contractNoWmEcId" class="form-control"    ></div>
			                        	</div>
	                        			<div class="form-group wmEcHidden">
				                        	<label class="col-sm-2 control-label">合同日期<font></font></label>
				                           <div class="col-sm-3"><input type="text"  name="contractDate"  value="<fmt:formatDate value="${importContract.contractDate }" pattern="yyyy-MM-dd"/>" id="contractDateWmEcId" class="form-control date-picker" dateISO="true"   ></div>
			                        		<pm:execute id="companyInfo" bean="companyInfoUserBusinImpl" method="getCompanyInfo">
												<pm:execute-param type="java.lang.String" value="${importContract.buyers }" />
											</pm:execute>
				                        	<label class="col-sm-2 control-label">买方<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="" name="buyers" id="buyersWmEcId" class="form-control"    ></div>
			                        	</div>
			                        	<div class="form-group wmEcHidden">
				                        	<label class="col-sm-2 control-label">买方地址<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${companyInfo.userTel }" name="bAddress" class="form-control"    id="bAddressWmEcId"  ></div>
				                        	<label class="col-sm-2 control-label">买方传真<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${companyInfo.userFax }" name="bFax" class="form-control" id="bFaxWmEcId"    ></div>
			                        	</div>
			                        	<div class="form-group wmEcHidden">
				                        	<label class="col-sm-2 control-label">买方联系方式<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${companyInfo.userTel }" name="bTelphone"  id="bTelphoneWmEcId"  class="form-control"    ></div>
				                        	<label class="col-sm-2 control-label">卖方<font></font></label>
				                            <div class="col-sm-3"><input type="text" id="sellersWmEcId" name="sellers" class="form-control sellersName"    ></div>
			                        	</div>
			                        	<div class="form-group wmEcHidden">
				                        	<label class="col-sm-2 control-label">卖方地址<font></font></label>
				                            <div class="col-sm-3"><input type="text"  value="${importContract.sAddress }"  id="sAddressWmEcId" name="sAddress" class="form-control sAddress"    ></div>
				                        	<label class="col-sm-2 control-label">卖方传真<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${importContract.sFax }" name="sFax" class="form-control sFax" id="sFaxWmEcId"    ></div>
			                        	</div>
			                        	<div class="form-group wmEcHidden">
				                        	<label class="col-sm-2 control-label">卖方联系方式<font></font></label>
				                            <div class="col-sm-3"><input type="text" value="${importContract.sTelphone }" name="sTelphone"  id="sTelphoneWmEcId" class="form-control sTelphone"    ></div>
			                        	</div>
	                        	<div class="col-xs-12 ">
									<button type="button" class="ui_form_button ui_form_right wmEc1-4">下一步</button>
								</div>
							</div>
							

						</article>

						<article class="row step col-xs-12 wmEc2-1 wmEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wmEc2-2" alt="" />
								产品及开票人信息
							</h2>

							<div class="step_content ibox-content wmEc2-3" style="display: none;">

								<div class="row">
										<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="wmEcaddTr2('wmEcTab', -1)">
												 <span> 添加一行</span>
											</a> <a class="btn btn-white btn-sm" href="javascript:;" onclick="wmEcdelTr2()"> 
												<span> 删除行</span>
											</a>
										</div>
									</div>
									<table id="wmEcTab" class="table table-striped table-bordered table-hover">
										<thead>
											<tr align="center">
												<th></th>
												<th>商品名称<font>*</font></th>
												<th>型号</th>
												<th>数量<font>*</font></th>
												<th>单价</th>
												<th>总价<font>*</font></th>
											</tr>
										</thead>
									</table>
								<div class="col-xs-12 ">
									<button type="button"  class="ui_form_button ui_form_right wmEc2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 wmEc3-1 wmEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wmEc3-2" alt="" />
								运输及付款信息
							</h2>

							<div class="step_content ibox-content wmEc3-3" style="display: none;">
								<div class="form-group wmEcHidden">
		                        	<label class="col-sm-2 control-label">成交方式<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="manufactoryWmEcId" value="${importContract.currency }" name="currency" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">币种<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input id="inspectionWmEcId" type="text" value="${importContract.payWay }" name="payWay" class="form-control"  required=""  ></div>
		                        </div>
		                        <div class="form-group">
		                        	<label class="col-sm-2 control-label">付款条件<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="termPaymentWmEcId" value="${importContract.termPayment }" name="termPayment" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">仲裁条款<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="arbitrationWmEcId" value="${importContract.arbitration }" name="arbitration" class="form-control"    ></div>
	                        	</div>
								<div class="form-group">
		                        	<label class="col-sm-2 control-label">装运时间<font></font></label>
		                           <div class="col-sm-3"><input type="text" id="shipmentTimeWmEcId" name="shipmentTime"  value="<fmt:formatDate value="${importContract.shipmentTime }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">交货时间<font></font></label>
		                            <div class="col-sm-3"><input id="deliveryTimeWmEcId" type="text" value="${importContract.deliveryTime }" name="deliveryTime" class="form-control date-picker"     dateISO="true"></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">装运口岸<font></font></label>
		                            <div class="col-sm-3"><input id="portShipmentWmEcId" type="text" value="${importContract.portShipment }" name="portShipment" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">目的口岸<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="portDestinationWmEcId" value="${importContract.portDestination }" name="portDestination" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">包装<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="packingWmEcId" value="${importContract.packing }" name="packing" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">保险<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="insuranceWmEcId" value="${importContract.insurance }" name="insurance" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">注解及说明<i class="ired">*</i></label>
		                            <div class="col-sm-8"><input type="text" id="othersWmEcId" value="${importContract.others }" name="others" class="form-control"   required="" ></div>
	                        	</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wmEc3-4">下一步</button>
								</div>

							</div>

						</article>
						<article class="step col-xs-12 wmEc4-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wmEc4-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content wmEc4-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateWmEcId" name="shipmentTime" class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateWmEcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
		                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
									<div class="col-sm-8"><textarea name="explain" id="explainWmEcId" class="form-control " rows="6" ></textarea></div>
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right  wmEc4-4">下一步</button>
								</div>
								</div>
	                        	
							</article>
						<article class="step col-xs-12 wmEc5-1 wmEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f5.png" class="step_icon wmEc5-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content wmEc5-3" style="display: none;">
								<div class="form-group">
				                            <div class="col-sm-8">
				                            	<pm:fileList metaObject="${exportContract }" delete="true"  name="filesWmEc" metaColums="columsWmEc"/>
				                            	<c:import url="/include/includeUploadify.jsp">
				                            		<c:param  name="propertyName" value="filesWmEc"/>
													<c:param  name="metaColums" value="columsWmEc"/>
											    </c:import>
				                            </div>
				                      </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button btn btn-primary ui-form-control J-children submit wmEc_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
</c:if>		
<!-- ######################3.报关进口########################################################################################### -->
 <c:if test="${BgIc eq 'BgIc' }">
 	<div id="boxBgIc" class="tab-pane fade in bgIc aafff" >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertBgIcForm" class="form-horizontal formValidate" action="" method="post">
					<div id="myTabContent" class="col-xs-12">
						 <div id="title" class="col-xs-11  col-xs-offset-1">
						 		 <span class="choose">您当前选择的服务类型是: </span>
						 		<span class="choose_explain">进口报关</span>
					            <label for="bgIcRadio1" class="font-bolder">
					                	<span class="">自助委托</span>
					            </label>
					            <input id="bgIcRadio1" class="styled" value="0" type="radio" name="radio" checked>
					            <label for="bgIcRadio2" class="font-bolder m_left">
					              		<span class="">全权委托</span>
					            </label>
						        <input id="bgIcRadio2" class="styled" value="1" type="radio" name="radio">
						 		
						</div>
                        <!-- start 委托方  -->
						<article class="step col-xs-12 bgIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon bgIc1-2" alt=""  />
								报关进口委托方
							</h2>

							<div class="ibox-content bgIc1-3">
								<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="consignorBgIcId" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label bgIcHidden">主要货物名称<i class="ired">*</i></label>
		                            <div class="col-sm-3 bgIcHidden"><input type="text" value="${bgic.goodsName }" id="goodsNameBgIcId" name="goodsName" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgIcHidden">
		                        	<label class="col-sm-2 control-label">HS编号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.hsCode }" id="hsCodeBgIcId" name="hsCode" class="form-control"  required="" maxlength="10" minlength="10"  digits="true" ></div>
		                        	<label class="col-sm-2 control-label">重量<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" name="weight" value="${bgic.weight }" id="weightBgIcId" class="form-control"  required=""  number="true"></div>
	                        	</div>
	                        	<div class="form-group bgIcHidden">
		                        	
		                        	<label class="col-sm-2 control-label">合同号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.contractNo }" id="contractNoBgIcId" name="contractNo" class="form-control"  required=""  ></div>
	                        		<label class="col-sm-2 control-label">其他要求<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.others }" id="othersBgIcId" name="others" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgIcHidden">
		                        	<label class="col-sm-2 control-label">许可文件号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.licenseFileNo }" id="licenseFileNoBgIcId" name="licenseFileNo" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">货物总价<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.goodsTotalPrice }" id="goodsTotalPriceBgIcId" name="goodsTotalPrice" class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	
	                        	<div class="form-group bgIcHidden">
		                        	<label class="col-sm-2 control-label">进出口日期<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text"  name="imExDate" id="imExDateBgIcId" value="<fmt:formatDate value="${bgic.imExDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">提单号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.deliveryNumber }" id="deliveryNumberBgIcId" name="deliveryNumber" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgIcHidden">
		                        	<label class="col-sm-2 control-label">贸易方式<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.tradeType }"  name="tradeType" id="tradeTypeBgIcId" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">货源地<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.originPlace }" id="originPlaceBgIcId" name="originPlace" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgIcHidden">
	                        	<label class="col-sm-2 control-label">包装情况<font></font></label>
		                            <div class="col-sm-8"><input type="text" value="${bgic.packDetail }" id="packDetailBgIcId" name="packDetail" class="form-control"    ></div>
	                        	</div>
								<div class="col-xs-12 ">
									<button type="button" class="ui_form_button ui_form_right bgIc1-4">下一步</button>
								</div>

							</div>

						</article>
						<article class="step col-xs-12 bgIc2-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon bgIc2-2" alt="" />
									期望完成时间
								</h2>
								<div class=" ibox-content step_content  bgIc2-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateBgIcId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateBgIcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
		                      			<label class="col-sm-2 control-label">内容详情<font></font></label>
										<div class="col-sm-8"><textarea name="explain" id="explainBgIcId" class="form-control " rows="6" ></textarea></div>
									</div>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  bgIc2-4">下一步</button>
									</div>
								</div>
							</article>
						<!-- start 附件上传 -->
						<article class="step col-xs-12 bgIc3-1 bgIcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon bgIc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content bgIc3-3" style="display: none;">
								<div class="form-group">
									
			                            <div class="col-sm-8">
			                            	<pm:fileList metaObject="${customsDeAgreement }" delete="true" name="filesBgIc" metaColums="columsBgIc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="filesBgIc"/>
												<c:param  name="metaColums" value="columsBgIc"/>
										    </c:import>
			                            </div>
			                      </div>
							</div>
						</article>
						<div class="col_xs_12">
							<button type="button" class="btn btn-primary ui-form-control J-children ui_button submit  bgIc_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
</c:if>		
<!-- ####################4.报关出口############################################################################################# -->
 <c:if test="${BgEc eq 'BgEc' }">
 	<div id="boxBgEc" class="tab-pane fade in bgEc aafff">
			<div class="row">
				<form id="insertBgEcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
							<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">出口报关</span>
						            <label for="bgEcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="bgEcRadio1" class="styled" type="radio" name="radio" checked>
						            <label for="bgEcRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="bgEcRadio2" class="styled" type="radio" name="radio">
							</div>
                        <!-- start 委托方  -->
						<article class="step col-xs-12 bgIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon bgEc1-2" alt=""  />
								报关出口委托方
							</h2>

							<div class="ibox-content bgEc1-3">
								<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="consignorBgEcId" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label bgEcHidden">主要货物名称<i class="ired">*</i></label>
		                            <div class="col-sm-3 bgEcHidden"><input type="text" value="${bgic.goodsName }" id="goodsNameBgEcId" name="goodsName" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgEcHidden">
		                        	<label class="col-sm-2 control-label">HS编号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.hsCode }" id="hsCodeBgEcId" name="hsCode" class="form-control"  required="" maxlength="10" minlength="10"  digits="true"></div>
		                        	<label class="col-sm-2 control-label">重量<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" name="weight" value="${bgic.weight }" id="weightBgEcId" class="form-control"  required=""  number="true"></div>
	                        	</div>
	                        	<div class="form-group bgEcHidden">
		                        	<label class="col-sm-2 control-label">合同号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.contractNo }" id="contractNoBgEcId" name="contractNo" class="form-control"  required=""  ></div>
	                        		<label class="col-sm-2 control-label">其他要求<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.others }" id="othersBgEcId" name="others" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgEcHidden">
		                        	<label class="col-sm-2 control-label">许可文件号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.licenseFileNo }" id="licenseFileNoBgEcId" name="licenseFileNo" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">货物总价<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.goodsTotalPrice }" id="goodsTotalPriceBgEcId" name="goodsTotalPrice" class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	
	                        	<div class="form-group bgEcHidden">
		                        	<label class="col-sm-2 control-label">进出口日期<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text"  name="imExDate" id="imExDateBgEcId" value="<fmt:formatDate value="${bgic.imExDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">提单号<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.deliveryNumber }" id="deliveryNumberBgEcId" name="deliveryNumber" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgEcHidden">
		                        	<label class="col-sm-2 control-label">贸易方式<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.tradeType }"  name="tradeType" id="tradeTypeBgEcId" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">货源地<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.originPlace }" id="originPlaceBgEcId" name="originPlace" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group bgEcHidden">
		                        	<label class="col-sm-2 control-label">包装情况<font></font></label>
		                            <div class="col-sm-8"><input type="text" value="${bgic.packDetail }" id="packDetailBgEcId" name="packDetail" class="form-control"   ></div>
	                        	</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right bgEc1-4">下一步</button>
								</div>

							</div>
						</article>
						<article class="step col-xs-12 bgEc2-1 ">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon bgEc2-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content bgEc2-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateBgEcId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateBgEcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
		                      			<label class="col-sm-2 control-label">内容详情<font></font></label>
										<div class="col-sm-8"><textarea name="explain" id="explainBgEcId" class="form-control " rows="6" ></textarea></div>
									</div>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  bgEc2-4">下一步</button>
									</div>
								</div>
							</article>
						<article class="step col-xs-12 bgEc3-1 bgEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon bgEc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content bgEc3-3" style="display: none;">
								<div class="form-group">
			                            <div class="col-sm-8">
			                            	<pm:fileList metaObject="${exportCustomsDeAgreement }" delete="true" name="filesBgEc" metaColums="columsBgEc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="filesBgEc"/>
												<c:param  name="metaColums" value="columsBgEc"/>
										    </c:import>
			                            </div>
			                      </div>
							</div>
						</article>
                        <!-- end 附件上传 -->
						<div class="col_xs_12">
							<button type="button" class="btn btn-primary ui-form-control J-children ui_button submit bgEc_submit">提交订单</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
</c:if>		
<!-- #######################5.仓储进库########################################################################################## -->
<c:if test="${CcIc eq 'CcIc' }">
 	<div id="boxCcIc" class="CcIc aafff tab-pane fade in "  >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertCcIcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
							<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">仓储进库</span>
						            <label for="ccIcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="ccIcRadio1" class="styled" type="radio" name="radio" checked>
						            <label for="ccIcRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="ccIcRadio2" class="styled" type="radio" name="radio">
							</div>
						<article class="step col-xs-12 CcIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon CcIc1-2" alt=""  />
								仓储进库
							</h2>
							<div class="ibox-content CcIc1-3">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="consignorCcIcId" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label ccIcHidden">交货时间<i class="ired">*</i></label>
		                            <div class="col-sm-3 ccIcHidden"><input type="text"  name="deliveryDate" id="deliveryDateCcIcId" value="<fmt:formatDate value="${pop.deliveryDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true" required=""  ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
	                        		<label class="col-sm-2 control-label">保险<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.pcl }" id="pclCcIcId" name="pcl" class="form-control"   required=""  ></div>
		                        	<label class="col-sm-2 control-label">包装<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.refDocuments }" id="refDocumentsCcIcId" name="refDocuments" class="form-control" required=""  ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">采购单编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.purOrderNo }" id="purOrderNoCcIcId" name="purOrderNo" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">买方地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.bAddress }" id="bAddressCcIcId" name="bAddress" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">发布日期<font></font></label>
		                            <div class="col-sm-3"><input type="text"  name="issueDate" id="issueDateCcIcId" value="<fmt:formatDate value="${pop.issueDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">买方联系方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.bTelphone }" id="bTelphoneCcIcId" name="bTelphone" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">买方传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.bFax }" id="bFaxCcIcId" name="bFax" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">项目<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.project }" id="projectCcIcId" name="project" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">供应商<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.supplier }" id="supplierCcIcId" name="supplier" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">供应商编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.supplierNo }" id="supplierNoCcIcId" name="supplierNo" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">联系人<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.contactPerson }" id="contactPersonCcIcId" name="contactPerson" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">联系人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.cpAddress }" id="cpAddressCcIcId" name="cpAddress" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">联系人电话<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.cpTelephone }" id="cpTelephoneCcIcId" name="cpTelephone" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">联系人传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.cpFax }" id="cpFaxCcIcId" name="cpFax" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">合同日期<font></font></label>
		                            <div class="col-sm-3"><input type="text"  name="contractTerm" id="contractTermCcIcId" value="${pop.contractTerm }" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">采购单编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.purOrderNo }" id="purOrderNoCcIcId" name="purOrderNo" class="form-control"     ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">交付说明<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.instrDestination }" id="instrDestinationCcIcId" name="instrDestination" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">交付条款<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.paymentTerm }" id="paymentTermCcIcId" name="paymentTerm" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">国际贸易条件<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.tradeCondition }" id="tradeConditionCcIcId" name="tradeCondition" class="form-control"    ></div>
	                        	</div>
	                        	<div class="col-xs-12 ">
									<button type="button" class="ui_form_button ui_form_right CcIc1-4">下一步</button>
								</div>
							</div>
						</article>

						<article class="row step col-xs-12 CcIc2-1 ccIcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon CcIc2-2" alt="" />
								详情
							</h2>

							<div class="step_content ibox-content CcIc2-3" style="display: none;">

								<div class="row">
										<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="ccIcaddTr2('ccIcTab', -1)">
												<span> 添加一行</span>
											</a>
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="ccIcdelTr2()">
												<span> 删除行</span>
											</a>
										</div>
									</div>
									<table id="ccIcTab" class="table table-striped table-bordered table-hover" >
										<thead>
											<tr align="center">
												<th></th>
												<th>商品名称<i class="ired">*</i></th>
												<th>型号<i class="ired">*</i></th>
												<th>价格有效期<i class="ired">*</i></th>
												<th>单价<i class="ired">*</i></th>
												<th>数量<i class="ired">*</i></th>
											</tr>
										</thead>
									</table>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right CcIc2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 CcIc3-1 ccIcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon CcIc3-2" alt="" />
								币种及费用
							</h2>
							<div class="ibox-content  CcIc3-3 " style="display: none;">
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">总价<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="sharePerPriceCcIcId" value="${pop.sharePerPrice }" name="sharePerPrice" class="form-control"   number="true" required=""></div>
	                        		<label class="col-sm-2 control-label">其他<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="commentsCcIcId" value="${pop.comments }" name="comments" class="form-control"  required=""  ></div>
		                        </div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">支付方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="payWayCcIcId" value="${pop.payWay }" name="payWay" class="form-control"  ></div>
		                        	<label class="col-sm-2 control-label">工装寿命<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="toolingLifeCcIcId" value="${pop.toolingLife }" name="toolingLife" class="form-control"   digits="true" ></div>
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">分摊量<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="shareAmountCcIcId" value="${pop.shareAmount }" name="shareAmount" class="form-control"  digits="true" ></div>
		                        		<label class="col-sm-2 control-label">工装费用<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="toolingCostCcIcId" value="${pop.toolingCost }" name="toolingCost" class="form-control"   ></div>
	                        	
	                        	</div>
	                        	<div class="form-group ccIcHidden">
		                        	<label class="col-sm-2 control-label">币种<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="currencyCcIcId" value="${pop.currency }" name="currency" class="form-control"    ></div>
		                        	
	                        	</div>
								<div class="col-xs-12 ccIcHidden">
									<button type="button" class="ui_form_button ui_form_right CcIc3-4">下一步</button>
								</div>
							</div>
						</article>
						<article class="step col-xs-12 CcIc4-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f4.png" class="step_icon CcIc4-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content CcIc4-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateCcIcId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateCcIcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
			                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
										<div class="col-sm-8"><textarea name="explain" id="explainCcIcId" class="form-control " rows="6" ></textarea></div>
									</div>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  CcIc4-4">下一步</button>
									</div>
								</div>
							</article>
						
						
						
						<article class="step col-xs-12 CcIc5-1 ccIcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f5.png" class="step_icon CcIc5-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content CcIc5-3" style="display: none;">
								<div class="form-group">
			                            <div class="col-sm-8">
			                            	<pm:fileList metaObject="${purchaseOrderProduction }" delete="true" name="filesCcIc" metaColums="columsCcIc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="filesCcIc"/>
												<c:param  name="metaColums" value="columsCcIc"/>
										    </c:import>
			                            </div>
			                      </div>
	                        </div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="btn btn-primary ui-form-control J-children ui_button submit CcIc_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
</c:if>		
<!-- #######################6.仓储出库########################################################################################## -->
 <c:if test="${CcEc eq 'CcEc' }">
 	<div id="boxCcEc" class=" CcEc aafff tab-pane fade in " >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertCcEcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">仓储进库</span>
						            <label for="ccEcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="ccEcRadio1" class="styled" type="radio" name="radio" checked>
						            <label for="ccEcRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="ccEcRadio2" class="styled" type="radio" name="radio">
							</div>
						<article class="step col-xs-12 CcEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon CcEc1-2" alt=""  />
								仓储出库
							</h2>

							<div class="step_content ibox-content CcEc1-3">
								<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="consignorCcEcId" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label ccEcHidden">交货时间<i class="ired">*</i></label>
		                            <div class="col-sm-3 ccEcHidden"><input type="text"  name="deliveryDate" id="deliveryDateCcEcId" value="<fmt:formatDate value="${pop.deliveryDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true" required=""  ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
	                        		<label class="col-sm-2 control-label">保险<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" required=""  value="${pop.pcl }" id="pclCcEcId" name="pcl" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">包装<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.refDocuments }" required=""  id="refDocumentsCcEcId" name="refDocuments" class="form-control"    ></div>
	                        	</div>
								<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">采购单编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.purOrderNo }" id="purOrderNoCcEcId" name="purOrderNo" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">买方地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.bAddress }" id="bAddressCcEcId" name="bAddress" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">发布日期<font></font></label>
		                            <div class="col-sm-3"><input type="text"  name="issueDate" id="issueDateCcEcId" value="<fmt:formatDate value="${pop.issueDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">买方联系方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.bTelphone }" id="bTelphoneCcEcId" name="bTelphone" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">买方传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.bFax }" id="bFaxCcEcId" name="bFax" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">项目<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.project }" id="projectCcEcId" name="project" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">供应商<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.supplier }" id="supplierCcEcId" name="supplier" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">供应商编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.supplierNo }" id="supplierNoCcEcId" name="supplierNo" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">联系人<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.contactPerson }" id="contactPersonCcEcId" name="contactPerson" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">联系人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.cpAddress }" id="cpAddressCcEcId" name="cpAddress" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">联系人电话<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.cpTelephone }" id="cpTelephoneCcEcId" name="cpTelephone" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">联系人传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.cpFax }" id="cpFaxCcEcId" name="cpFax" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">合同日期<font></font></label>
		                            <div class="col-sm-3"><input type="text"  name="contractTerm" id="contractTermCcEcId" value="${pop.contractTerm }" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">买方<font></font></label>
		                            <div class="col-sm-3 ccEcHidden"><input type="text" value="${pop.buyer }" id="buyersCcEcId" name="buyer" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">交付说明<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.instrDestination }" id="instrDestinationCcEcId" name="instrDestination" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">交付条款<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.paymentTerm }" id="paymentTermCcEcId" name="paymentTerm" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group ccEcHidden">
		                        	<label class="col-sm-2 control-label">国际贸易条件<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${pop.tradeCondition }" id="tradeConditionCcEcId" name="tradeCondition" class="form-control"     ></div>
	                        	</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcEc1-4">下一步</button>
								</div>

							</div>

						</article>

						<article class="row step col-xs-12 CcEc2-1 ccEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon CcEc2-2" alt="" />
								详情
							</h2>

							<div class="step_content ibox-content CcEc2-3" style="display: none;">

								<div class="row">
										<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="ccEcaddTr2('ccEcTab', -1)">
												<span> 添加一行</span>
											</a>
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="ccEcdelTr2()">
												<span> 删除行</span>
											</a>
										</div>
									</div>
									<table id="ccEcTab" class="table table-striped table-bordered table-hover">
										<thead>
											<tr align="center">
												<th></th>
												<th>商品名称<i class="ired">*</i></th>
												<th>型号<i class="ired">*</i></th>
												<th>价格有效期<i class="ired">*</i></th>
												<th>单价<i class="ired">*</i></th>
												<th>数量<i class="ired">*</i></th>
											</tr>
										</thead>
									</table>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right CcEc2-4" >下一步</button>
								</div>

							</div>
						</article>
						<article class="step col-xs-12 CcEc3-1 ccEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon CcEc3-2" alt="" />
								币种及费用
							</h2>

							<div class="step_content ibox-content CcEc3-3" style="display: none;">
								<div class="form-group">
		                        	<label class="col-sm-2 control-label">币种<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="currencyCcEcId" value="${pop.currency }" name="currency" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">工装费用<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="toolingCostCcEcId" value="${pop.toolingCost }" name="toolingCost" class="form-control"     ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">支付方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="payWayCcEcId" value="${pop.payWay }" name="payWay" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">工装寿命<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="toolingLifeCcEcId" value="${pop.toolingLife }" name="toolingLife" class="form-control"  digits="true"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">分摊量<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="shareAmountCcEcId" value="${pop.shareAmount }" name="shareAmount" class="form-control"  digits="true"  ></div>
		                        	<label class="col-sm-2 control-label">总价<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" required=""  id="sharePerPriceCcEcId" value="${pop.sharePerPrice }" name="sharePerPrice" class="form-control" number="true"></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">其他<i class="ired">*</i></label>
		                            <div class="col-sm-8"><input type="text" id="commentsCcEcId" value="${pop.comments }" name="comments" class="form-control"  required=""  ></div>
	                        	</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcEc3-4">下一步</button>
								</div>

							</div>

						</article>
						<article class="step col-xs-12 CcEc4-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f4.png" class="step_icon CcEc4-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content CcEc4-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateCcEcId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateCcEcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
		                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
									<div class="col-sm-8"><textarea name="explain" id="explainCcEcId" class="form-control " rows="6" ></textarea></div>
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right  CcEc4-4">下一步</button>
								</div>
								</div>
	                        	
							</article>
						<article class="step col-xs-12 CcEc5-1 ccEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f5.png" class="step_icon CcEc5-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content CcEc5-3" style="display: none;">
								<div class="form-group">
			                            <div class="col-sm-8">
			                            	<pm:fileList metaObject="${exportPurchaseOrderProduction }" delete="true" name="filesCcEc" metaColums="columsCcEc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="filesCcEc"/>
												<c:param  name="metaColums" value="columsCcEc"/>
										    </c:import>
			                            </div>
		                       		 </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button btn btn-primary ui-form-control J-children submit CcEc_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>						
</c:if>		
<!-- #######################7.物流进库########################################################################################## -->
 <c:if test="${WlIc eq 'WlIc' }">
 	<div id="boxWlIc" class="wlIc aafff tab-pane fade in " >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertWlIcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
								<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">物流进库</span>
						            <label for="wlIcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="wlIcRadio1" class="styled" type="radio" value="0" name="radio" checked>
						            <label for="wlIcRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="wlIcRadio2" class="styled" type="radio" value="1" name="radio">
								</div>

						<article class="step col-xs-12 wlIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wlIc1-2" alt=""  />
								进口物流
							</h2>
							<div class="ibox-content wlIc1-3">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="wlIcconsignor" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label wlIcHidden">起运港<i class="ired">*</i></label>
		                            <div class="col-sm-3 wlIcHidden"><input type="text" id="wlIcdeparturePort" value="${ese.departurePort }" name="departurePort" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">最终目的地<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcdestinationPort" value="${ese.dischargePort }" name="dischargePort" class="form-control"  required=""   ></div>
		                        	<label class="col-sm-2 control-label">箱型/箱量<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIccontainer" value="${ese.container }" name="container" digits="true" class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">支付方式<i class="ired">*</i></label>
		                            <div class="col-sm-3">
		                            	<select class="selectpicker" name="payWay" id="wlIcpayWay">
		                            		<c:forEach items="${wlzffsList1 }" var="item">
			                            		<option value="${item.name }">${item.name }</option>
		                            		</c:forEach>
		                            	</select>
		                            </div>
		                        	<label class="col-sm-2 control-label">提单类型<font></font></label>
		                            <div class="col-sm-3">
		                            	<select class="selectpicker" name="blt" id="wlIcblt">
		                            		<c:forEach items="${wltdlxList1 }" var="item">
			                            		<option value="${item.name }">${item.name }</option>
		                            		</c:forEach>
		                            	</select>
		                            </div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">件数<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcitem" value="${ese.item }" name="item" class="form-control" digits="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">毛重<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcweight" value="${ese.weight }" name="weight"  class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">体积<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcvolume" value="${ese.volume }" name="volume" class="form-control" digits="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">电话<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIccpPhone" value="${ese.cpPhone }" name="cpPhone" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">品名<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcgoodsName" value="${ese.goodsName }" name="goodsName" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">运费确认<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIctransExpenseCharge" value="${ese.transExpenseCharge }" name="transExpenseCharge" class="form-control"  required=""  number="true" ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">运费条款<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIctransClause" value="${ese.transClause }" name="transClause" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">托运联系人<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIccontactPerson" value="${ese.contactPerson }" name="contactPerson" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">邮箱<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIccpMail" value="${ese.cpMail }" name="cpMail" class="form-control"  required="" email="true"  ></div>
		                        	<label class="col-sm-2 control-label">预配航班<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcpreFlight" value="${ese.preFlight }" name="preFlight" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
	                        	<label class="col-sm-2 control-label">运输方式 <i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcsignature" value="${ese.signature }" name="signature" class="form-control"    required="" ></div>
		                        	<label class="col-sm-2 control-label">特殊要求<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcspecialNotes" value="${ese.specialNotes }" name="specialNotes" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcxhsNo" value="${ese.xhsNo }" name="xhsNo" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">收货人<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcconsignee" value="${ese.consignee }" name="consignee" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label wlIcHidden">托运人<font></font></label>
		                            <div class="col-sm-3 wlIcHidden"><input type="text" id="wlIcshipper" value="${ese.shipper }" name="shipper" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">托运人地址<font></font></label>
		                            <div class="col-sm-3 wlIcHidden"><input type="text" id="wlIcsAddress" value="${ese.sAddress }" name="sAddress" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">收货人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIccAddress" value="${ese.cAddress }" name="cAddress" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">货到时间<font></font></label>
									<div class="col-sm-3"><input type="text" id="wlIcarrivalDate" name="arrivalDate"  value="<fmt:formatDate value="${ese.arrivalDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">唛头<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcmarks" value="${ese.marks }" name="marks" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIccpFax" value="${ese.cpFax }" name="cpFax" class="form-control"  ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">卸货港<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcdischargePort" value="${ese.destinationPort }" name="destinationPort" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">通知人<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcnotifier" value="${ese.notifier }" name="notifier" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group wlIcHidden">
		                        	<label class="col-sm-2 control-label">自拉自报<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlIcselfFull" value="${ese.selfFull }" name="selfFull" class="form-control"    ></div>
	                        	</div>
	                        	<div class="col-xs-12 ">
									<button type="button" class="ui_form_button ui_form_right wlIc1-4">下一步</button>
								</div>
							</div>
						</article>
						<article class="step col-xs-12 wlIc2-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlIc2-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content wlIc2-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateWlIcId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
			                            <div class="col-sm-3"><input id="endDateWlIcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
		                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
									<div class="col-sm-8"><textarea name="explain" id="explainWlIcId" class="form-control " rows="6" ></textarea></div>
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right  wlIc2-4">下一步</button>
								</div>
								</div>
	                        	
							</article>
						<article class="step col-xs-12 wlIc3-1 wlIcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wlIc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content wlIc3-3" style="display: none;">
								<div class="form-group">
				                            <div class="col-sm-8">
				                            	<pm:fileList metaObject="${shippingExCommission }" delete="true" name="filesWlIc" metaColums="columsWlIc"/>
				                            	<c:import url="/include/includeUploadify.jsp">
				                            		<c:param  name="propertyName" value="filesWlIc"/>
													<c:param  name="metaColums" value="columsWlIc"/>
											    </c:import>
				                            </div>
				                      </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button btn btn-primary ui-form-control J-children submit wlIc_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
</c:if>		
<!-- #######################8.物流出库########################################################################################## -->
 <c:if test="${WlEc eq 'WlEc' }">
 	<div id="boxWlEc" class=" wlEc aafff tab-pane fade in " >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertWlEcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
								<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">物流出库</span>
						            <label for="wlEcRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="wlEcRadio1" class="styled" type="radio" value="0" name="radio" checked>
						            <label for="wlEcRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="wlEcRadio2" class="styled" type="radio" value="1" name="radio">
								</div>
						<article class="step col-xs-12 wlEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wlEc1-2" alt=""  />
								出口物流
							</h2>
							<div class="ibox-content wlEc1-3">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="wlEcconsignor" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label wlEcHidden">起运港<i class="ired">*</i></label>
		                            <div class="col-sm-3 wlEcHidden"><input type="text" id="wlEcdeparturePort" value="${ese.departurePort }" name="departurePort" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">最终目的地<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcdestinationPort" value="${ese.dischargePort }" name="dischargePort" class="form-control"  required=""   ></div>
		                        	<label class="col-sm-2 control-label">箱型/箱量<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEccontainer" value="${ese.container }" name="container" digits="true" class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">支付方式<i class="ired">*</i></label>
		                            <div class="col-sm-3">
		                            	<select class="selectpicker" name="payWay"  id="wlEcpayWay">
		                            		<c:forEach items="${wlzffsList2 }" var="item">
			                            		<option value="${item.name }">${item.name }</option>
		                            		</c:forEach>
		                            	</select>
		                            </div>
		                        	<label class="col-sm-2 control-label">提单类型<font></font></label>
		                            <div class="col-sm-3">
		                            	<select class="selectpicker" name="blt" id="wlEcblt">
		                            		<c:forEach items="${wltdlxList2 }" var="item">
			                            		<option value="${item.name }">${item.name }</option>
		                            		</c:forEach>
		                            	</select>
		                            </div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">件数<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcitem" value="${ese.item }" name="item" class="form-control" digits="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">毛重<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcweight" value="${ese.weight }" name="weight"  class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">体积<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcvolume" value="${ese.volume }" name="volume" class="form-control" digits="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">电话<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEccpPhone" value="${ese.cpPhone }" name="cpPhone" class="form-control"  required=""  ></div>
		                        	
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">品名<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcgoodsName" value="${ese.goodsName }" name="goodsName" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">运费确认<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEctransExpenseCharge" value="${ese.transExpenseCharge }" name="transExpenseCharge" class="form-control"  required=""  number="true" ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">运费条款<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEctransClause" value="${ese.transClause }" name="transClause" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">托运联系人<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEccontactPerson" value="${ese.contactPerson }" name="contactPerson" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">邮箱<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEccpMail" value="${ese.cpMail }" name="cpMail" class="form-control"  required="" email="true"  ></div>
		                        	<label class="col-sm-2 control-label">预配航班<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcpreFlight" value="${ese.preFlight }" name="preFlight" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
	                        	<label class="col-sm-2 control-label">运输方式 <i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcsignature" value="${ese.signature }" name="signature" class="form-control"   required="" ></div>
		                        	<label class="col-sm-2 control-label">特殊要求<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcspecialNotes" value="${ese.specialNotes }" name="specialNotes" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label ">托运人地址<font></font></label>
		                            <div class="col-sm-3 "><input type="text" id="wlEcsAddress" value="${ese.sAddress }" name="sAddress" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label wlEcHidden">托运人<font></font></label>
		                            <div class="col-sm-3 wlEcHidden"><input type="text" id="wlEcshipper" value="${ese.shipper }" name="shipper" class="form-control" ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">收货人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEccAddress" value="${ese.cAddress }" name="cAddress" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">货到时间<font></font></label>
									<div class="col-sm-3"><input type="text" id="wlEcarrivalDate" name="arrivalDate"  value="<fmt:formatDate value="${ese.arrivalDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcxhsNo" value="${ese.xhsNo }" name="xhsNo" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">收货人<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcconsignee" value="${ese.consignee }" name="consignee" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">唛头<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcmarks" value="${ese.marks }" name="marks" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEccpFax" value="${ese.cpFax }" name="cpFax" class="form-control"     ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                            <label class="col-sm-2 control-label">卸货港<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcdischargePort" value="${ese.destinationPort }" name="destinationPort" class="form-control"  ></div>
		                        	<label class="col-sm-2 control-label">通知人<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcnotifier" value="${ese.notifier }" name="notifier" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group wlEcHidden">
		                        	<label class="col-sm-2 control-label">自拉自报<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlEcselfFull" value="${ese.selfFull }" name="selfFull" class="form-control"    ></div>
	                        	</div>
	                        	<div class="col-xs-12 ">
									<button type="button" class="ui_form_button ui_form_right wlEc1-4">下一步</button>
								</div>
							</div>
						</article>
						<article class="step col-xs-12 wlEc2-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlEc2-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content wlEc2-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateWlEcId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<i class="ired">*</i></label>
			                            <div class="col-sm-3"><input id="endDateWlEcId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
			                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
										<div class="col-sm-8"><textarea name="explain" id="explainWlEcId" class="form-control " rows="6" ></textarea></div>
									</div>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  wlEc2-4">下一步</button>
									</div>
									</div>
							</article>
						
						<article class="step col-xs-12 wlEc3-1 wlEcHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wlEc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content wlEc3-3" style="display: none;">
							   <div class="form-group">
			                            <div class="col-sm-8">
			                            	<pm:fileList metaObject="${exportShippingExCommission }" delete="true" name="filesWlEc" metaColums="columsWlEc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="filesWlEc"/>
												<c:param  name="metaColums" value="columsWlEc"/>
										    </c:import>
			                            </div>
		                        	</div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button btn btn-primary ui-form-control J-children submit wlEc_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
</c:if>		
<!-- #######################9.物流运输########################################################################################## -->
 <c:if test="${Trop eq 'Trop' }">
 	<div id="boxTrop" class="wlTrop aafff tab-pane fade in " >
			<div class="row">
				<div id="main" class="col-xs-12">
				<form id="insertWlTropForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
								<div id="title" class="col-xs-11 col-xs-offset-1">
							 		<span class="choose">您当前选择的服务类型是: </span>
							 		<span class="choose_explain">物流运输</span>
						            <label for="wlTropRadio1" class="font-bolder">
						                	<span class="">自助委托</span>
						            </label>
						            <input id="wlTropRadio1" class="styled" type="radio" value="0" name="radio" checked>
						            <label for="wlTropRadio2" class="font-bolder m_left">
						              		<span class="">全权委托</span>
						            </label>
							        <input id="wlTropRadio2" class="styled" type="radio" value="1" name="radio">
								</div>

						<article class="step col-xs-12 wlTrop1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wlTrop1-2" alt=""  />
								物流运输
							</h2>

							<div class="ibox-content wlTrop1-3">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" id="wlTropconsignor" name="consignor" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label wlEcHidden">起运地<i class="ired">*</i></label>
		                            <div class="col-sm-3 wlEcHidden"><input type="text" id="wlTropdeparturePort" value="${tsc.departurePort }" name="departurePort" class="form-control"  required=""  ></div>
	                        	</div>
	                        	
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">目的地<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropdestinationPort" value="${tsc.dischargePort }" name="dischargePort" class="form-control"  required=""   ></div>
		                        	<label class="col-sm-2 control-label">体积<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropvolume" value="${tsc.volume }" name="volume" class="form-control" digits="true" required=""  ></div>

	                        	</div>
	                        	
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">支付方式<i class="ired">*</i></label>
		                            <div class="col-sm-3">
		                            	<select class="selectpicker" name="payWay" id="wlTroppayWay">
		                            		<c:forEach items="${wlzffsList3 }" var="item">
			                            		<option value="${item.name }">${item.name }</option>
		                            		</c:forEach>
		                            	</select>
		                            </div>
		                            <label class="col-sm-2 control-label">提单类型<font></font></label>
		                            <div class="col-sm-3">
		                            	<select class="selectpicker" name="blt" id="wlTropblt">
		                            		<c:forEach items="${wltdlxList3 }" var="item">
			                            		<option value="${item.name }">${item.name }</option>
		                            		</c:forEach>
		                            	</select>
		                            </div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">电话<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropcpPhone" value="${tsc.cpPhone }" name="cpPhone" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">邮箱<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropcpMail" value="${tsc.cpMail }" name="cpMail" class="form-control"  required="" email="true"  ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">件数<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropitem" value="${tsc.item }" name="item" class="form-control" digits="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">毛重<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropweight" value="${tsc.weight }" name="weight"  class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">品名<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropgoodsName" value="${tsc.goodsName }" name="goodsName" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">托运联系人<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropcontactPerson" value="${tsc.contactPerson }" name="contactPerson" class="form-control"  required=""   ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
	                        	<label class="col-sm-2 control-label">特殊要求<i class="ired">*</i></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropspecialNotes" value="${tsc.specialNotes }" name="specialNotes" class="form-control"  required=""   ></div>
		                        	<label class="col-sm-2 control-label">收货人<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropconsignee" value="${tsc.consignee }" name="consignee" class="form-control"  ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">收货人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropcAddress" value="${tsc.cAddress }" name="cAddress" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">货到时间<font></font></label>
									<div class="col-sm-3"><input type="text" id="wlTroparrivalDate" name="arrivalDate"  value="<fmt:formatDate value="${tsc.arrivalDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
	                        	</div>
	                            <div class="form-group wlTropHidden">
	                        		<label class="col-sm-2 control-label wlTropHidden">托运人<font></font></label>
		                            <div class="col-sm-3 wlTropHidden"><input type="text" id="wlTropshipper" value="${tsc.shipper }" name="shipper" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label ">托运人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropsAddress" value="${tsc.sAddress }" name="sAddress" class="form-control"   ></div>
                        		</div>
	                        	<div class="form-group wlTropHidden">
	                        		<label class="col-sm-2 control-label">箱型/箱量</label>
		                            <div class="col-sm-3"><input type="text" id="wlTropcontainer" value="${tsc.container }" name="container" digits="true" class="form-control"   number="true"  ></div>
		                        	<label class="col-sm-2 control-label">通知人<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropnotifier" value="${tsc.notifier }" name="notifier" class="form-control"     ></div>
	                        	</div>
	                        	
	                        	<div class="form-group wlTropHidden">
		                        			                        	<label class="col-sm-2 control-label">卸货港<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropdischargePort" value="${tsc.destinationPort }" name="destinationPort" class="form-control"  ></div>
		                        	<label class="col-sm-2 control-label">唛头<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropmarks" value="${tsc.marks }" name="marks" class="form-control"     ></div>
	                        	</div>
	                        	
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">运费条款</label>
		                            <div class="col-sm-3"><input type="text" id="wlTroptransClause" value="${tsc.transClause }" name="transClause" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">运费确认</label>
		                            <div class="col-sm-3"><input type="text" id="wlTroptransExpenseCharge" value="${tsc.transExpenseCharge }" name="transExpenseCharge" class="form-control"   number="true"  ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropcpFax" value="${tsc.cpFax }" name="cpFax" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">预配航班</label>
		                            <div class="col-sm-3"><input type="text" id="wlTroppreFlight" value="${tsc.preFlight }" name="preFlight" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
	                        		<label class="col-sm-2 control-label">运输方式 </label>
		                            <div class="col-sm-3"><input type="text" id="wlTropsignature" value="${tsc.signature }" name="signature" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropxhsNo" value="${tsc.xhsNo }" name="xhsNo" class="form-control"     ></div>
	                        	</div>
	                        	<div class="form-group wlTropHidden">
		                        	<label class="col-sm-2 control-label">自拉自报<font></font></label>
		                            <div class="col-sm-3"><input type="text" id="wlTropselfFull" value="${tsc.selfFull }" name="selfFull" class="form-control"    ></div>
	                        	</div>
	                        	<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wlTrop1-4">下一步</button>
								</div>
							</div>

						</article>
						<article class="step col-xs-12 wlTrop2-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlTrop2-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content ibox-content wlTrop2-3" style="display: none;">
									<div class="form-group">
			                        	<label class="col-sm-2 control-label">开始时间<i class="ired">*</i></label>
			                           <div class="col-sm-3"><input type="text" id="startDateWlTropId" name="shipmentTime"   class="form-control date-picker" dateISO="true"  required="" ></div>
			                        	<label class="col-sm-2 control-label">结束时间<i class="ired">*</i></label>
			                            <div class="col-sm-3"><input id="endDateWlTropId" type="text"  name="deliveryTime" class="form-control date-picker"     dateISO="true" required=""></div>
		                        	</div>
		                        	<div class="form-group">
			                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
										<div class="col-sm-8"><textarea name="explain" id="explainWlTropId" class="form-control " rows="6" ></textarea></div>
									</div>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  wlTrop2-4">下一步</button>
									</div>
								</div>
							</article>
						
						<article class="step col-xs-12 wlTrop3-1 wlTropHidden">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlTrop3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content ibox-content wlTrop3-3" style="display: none;">
							   <div class="form-group">
			                            <div class="col-sm-8">
			                            	<pm:fileList metaObject="${tropShippingExCommission }" delete="true" name="filesWlTrop" metaColums="columsWlTrop"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="filesWlTrop"/>
												<c:param  name="metaColums" value="columsWlTrop"/>
										    </c:import>
			                            </div>
		                        	</div> 
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button btn btn-primary ui-form-control J-children submit wlTrop_submit">提交订单</button>
							
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
</c:if>
</div>

<div class="container">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="height: 30px;padding: 6px;">
						<h4 class="modal-title" id="myModalLabel">
                   </h4>
					</div>
					<div class="modal-body" style="width: 600px;text-align: center;font-size: 16px;">

						<p>您已成功下单，请等待客户为您分配服务商，届时服务商将主动跟您联系</p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary tjqbdd" data-dismiss="modal">
									                      	提交全部订单
									                    </button>
						<button type="button" class="btn btn-primary bccg" >
									                      	保存草稿
									                    </button>
						<button type="button" class="btn btn-primary jxxd" >
									                      	继续下单
									                    </button>
						<button type="button" class="btn btn-primary wddd">
									                      	我的订单
									                    </button>

					</div>
				</div>
			</div>
		</div>
</div>
<!-- ################################################################################################################# -->
</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
$(document).ready(function (){
	var ids = [${selectList}];
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});
/* function SupCompanyInfoOnclick(){
	var taskTypeId=$("#iniPage").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['80%', '80%'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listCompanyInfo/'+taskTypeId+"/wmIc"
	});
} */
/* function returnValue(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var strName = selectValues[1];
		var sTelphone = selectValues[2];
		var sAddress = selectValues[3];
		$("#companyInfoNameWmIcId").val(strName);
		$("#sellersWmIcId").val(strId);
		$("#sAddressWmIcId").val(sAddress);
		$("#sTelphoneWmIcId").val(sTelphone);
	}
} */
function SupCompanyInfoWmEcOnclick(){
	var taskTypeId=$("#iniPage").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['80%', '80%'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listCompanyInfo/'+taskTypeId+"/wmEc"
	});
}
function returnValueWmEc(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var strName = selectValues[1];
		var sTelphone = selectValues[2];
		var sAddress = selectValues[3];
		$("#companyInfoNameWmEcId").val(strName);
		$("#sellersWmEcId").val(strId);
		$("#sAddressWmEcId").val(sAddress);
		$("#sTelphoneWmEcId").val(sTelphone);
	}
}
var num=1;
var hhhhh="";
//外贸进口
function randomDataWmIcOnclick(id) {
	hhhhh=id;
	var taskTypeId=$("#iniPage").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  offset: '50px',
	  area: ['900px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listProduct/'+taskTypeId+"/wmIc"
	});
}
//外贸出口
function randomDataWmEcOnclick(id) {
	hhhhh=id;
	var taskTypeId=$("#iniPage").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  offset: '50px',
	  area: ['900px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listProduct/'+taskTypeId+"/wmEc"
	});
}
//仓储进口
function randomDataCcIcOnclick(id) {
	hhhhh=id;
	var taskTypeId=$("#iniPage").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['900px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listProduct/'+taskTypeId
	});
}
//仓储进口
function randomDataCcEcOnclick(id) {
	hhhhh=id;
	var taskTypeId=$("#iniPage").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['900px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listProduct/'+taskTypeId
	});
}



function returnValueSpWmIc(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var commodityName = selectValues[1];
		var models = selectValues[2];
		var unitPrice = selectValues[3];
		$("#commodityName"+hhhhh).val(commodityName);
		$("#commodityNameId"+hhhhh).val(strId);
		$("#models"+hhhhh).val(models);
		$("#unitPrice"+hhhhh).val(unitPrice);
	}
}
function returnValueSpWmEc(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var commodityName = selectValues[1];
		var models = selectValues[2];
		var unitPrice = selectValues[3];
		$("#commodityNameWmEc"+hhhhh).val(commodityName);
		$("#commodityNameWmEcId"+hhhhh).val(strId);
		$("#modelsWmEc"+hhhhh).val(models);
		$("#unitPriceWmEc"+hhhhh).val(unitPrice);
	}
}
</script>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript" src="${basePath }/resource/js/contract.js"></script>
<%@ include file="/include/includeFooter.jsp" %>
