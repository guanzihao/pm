<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link rel="stylesheet" href="${basePath }/resource/css/bootstrap-table.css">
<link rel="stylesheet" href="${basePath }/resource/css/editContract_1.css">

<script src="${basePath }/resource/js/bootstrap-table.js"></script>

	<head>
		<meta charset="utf-8" />
		<title></title>
		<style>
			
		</style>
	</head>
	<body>
<!-- ########################## 1.外贸进口############################################################# -->
		<input type="hidden" id="iniPage" value="${taskTypeId}">
        <input type="hidden" id="taskId"  name="taskId" value="${task.id}">
        <input type="hidden" id="userId" name="buyers" value="${companyInFo.id }">
		<div id="boxWmIc" class="container wmIc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
                    <input type="hidden" id="sellersId" name="sellers" value="">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 wmIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wmIc1-2" alt=""  />
								外贸进口
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wmIc1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同号码:</span>
									</div>

									<input type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="50"  id="contractNoWmIcId">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同日期:</span>
									</div>

									<input type="text"  name="contractDate" class="col-xs-6 date-picker" dateISO="true" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" id="contractDateWmIcId">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方:</span>
									</div>

									<input type="text" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  id="buyersWmIcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方地址:</span>
									</div>

									<input type="text" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50"  id="bAddressWmIcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方传真:</span>
									</div>

									<input type="text" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50"  id="bFaxWmIcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方联系方式:</span>
									</div>

									<input type="text" id="bTelphoneWmIcId" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >卖方:</span>
									</div>

									<input id="companyInfoNameWmIcId" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方地址:</span>
									</div>

									<input id="sAddressWmIcId" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方传真:</span>
									</div>

									<input id="sFaxWmIcId" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方联系方式:</span>
									</div>

									<input type="text" id="sTelphoneWmIcId" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wmIc1-4">下一步</button>
								</div>

							</div>

						</article>

						<article class="row step col-xs-12 wmIc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wmIc2-2" alt="" />
								产品及开票人信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wmIc2-3">

								<div class="container">
									       <div id="toolbar">
									           <button id="buttonWmIc" class="btn btn-default" type="button">添加一行</button>
									           <button id="buttonRemoveWmIc" class="btn btn-default" type="button">删除选中行</button>
									       </div>
									       <table id="tableWmIc"
									              data-toggle="table"
									              data-toolbar="#toolbar"
									              data-height="460"
									              data-url="../json/data1.json">
									           <thead>
									           <tr>
									           		<th data-field="btSelectItem" data-checkbox="true"></th>
									               <th data-field="commodityName">商品名称</th>
									               <th data-field="models">型号</th>
									               <th data-field="quantity">数量</th>
									               <th data-field="unitPrice">单价</th>
									               <th data-field="totalAmount">总价</th>
									               
									           </tr>
									           </thead>
									       </table>
									   </div>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right wmIc2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 wmIc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wmIc3-2" alt="" />
								运输及付款信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wmIc3-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>装运时间:</span>
									</div>

	     							<input type="text" id="shipmentTimeWmIcId" name="shipmentTime" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交货时间:</span>
									</div>

									<input type="text" id="deliveryTimeWmIcId" name="deliveryTime" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>装运口岸:</span>
									</div>

									<input type="text" id="portShipmentWmIcId" name="portShipment" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>目的口岸:</span>
									</div>

									<input type="text" id="portDestinationWmIcId" name="portDestination" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>包装:</span>
									</div>

									<input type="text" id="packingWmIcId" name="packing" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>保险:</span>
									</div>

									<input type="text" id="insuranceWmIcId" name="insurance" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>制造厂商:</span>
									</div>

									<input type="text" id="manufactoryWmIcId" name="manufactory" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>付款条件:</span>
									</div>

									<input type="text" id="termPaymentWmIcId" name="termPayment" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>检查条款:</span>
									</div>

									<input type="text" id="inspectionWmIcId" name="inspection" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>仲裁条款:</span>
									</div>

									<input type="text" id="arbitrationWmIcId" name="arbitration" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他:</span>
									</div>

									<input type="text" id="othersWmIcId" name="others" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wmIc3-4">下一步</button>
								</div>

							</div>

						</article>
						
						<article class="step col-xs-12 wmIc4-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wmIc4-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 wmIc4-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${importContract }" delete="true" name="accessory" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="accessory"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled wmIc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		</div>
<!--##########################2.外贸出口合同###################################################################################################################-->
 <div id="boxWmEc" class="container wmEc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 wmEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wmEc1-2" alt=""  />
								外卖出口
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wmEc1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同号码:</span>
									</div>

									<input type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="50"  id="contractNoWmEcId">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同日期:</span>
									</div>

									<input type="text"  name="contractDate" class="col-xs-6 date-picker" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" id="contractDateWmEcId">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方:</span>
									</div>

									<input type="text" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  id="buyersWmEcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方地址:</span>
									</div>

									<input type="text" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50"  id="bAddressWmEcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方传真:</span>
									</div>

									<input type="text" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50"  id="bFaxWmEcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方联系方式:</span>
									</div>

									<input type="text" id="bTelphoneWmEcId" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >卖方:</span>
									</div>

									<input id="companyInfoNameWmEcId" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方地址:</span>
									</div>

									<input id="sAddressWmEcId" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方传真:</span>
									</div>

									<input id="sFaxWmEcId" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方联系方式:</span>
									</div>

									<input type="text" id="sTelphoneWmIcId" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wmEc1-4">下一步</button>
								</div>

							</div>

						</article>

						<article class="row step col-xs-12 wmEc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wmEc2-2" alt="" />
								产品及开票人信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wmEc2-3">

								<div class="container">
									       <div id="toolbar">
									           <button id="buttonWmEc" class="btn btn-default" type="button">添加一行</button>
									           <button id="buttonRemoveWmEc" class="btn btn-default" type="button">删除选中行</button>
									       </div>
									       <table id="tableWmEc"
									              data-toggle="table"
									              data-toolbar="#toolbarWmEc"
									              data-height="460"
									              data-url="../json/data1.json">
									           <thead>
									           <tr>
									           		<th data-field="btSelectItemWmEc" data-checkbox="true"></th>
									               <th data-field="commodityNameWmEc">商品名称</th>
									               <th data-field="modelsWmEc">型号</th>
									               <th data-field="quantityWmEc">数量</th>
									               <th data-field="unitPriceWmEc">单价</th>
									               <th data-field="totalAmountWmEc">总价</th>
									               
									           </tr>
									           </thead>
									       </table>
									   </div>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right wmEc2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 wmEc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wmEc3-2" alt="" />
								运输及付款信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wmEc3-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>装运时间:</span>
									</div>

	     							<input type="text" id="shipmentTimeWmEcId" name="shipmentTime" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交货时间:</span>
									</div>

									<input type="text" id="deliveryTimeWmEcId" name="deliveryTime" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>装运口岸:</span>
									</div>

									<input type="text" id="portShipmentWmEcId" name="portShipment" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>目的口岸:</span>
									</div>

									<input type="text" id="portDestinationWmEcId" name="portDestination" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>包装:</span>
									</div>

									<input type="text" id="packingWmEcId" name="packing" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>保险:</span>
									</div>

									<input type="text" id="insuranceWmEcId" name="insurance" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>制造厂商:</span>
									</div>

									<input type="text" id="manufactoryWmEcId" name="manufactory" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>付款条件:</span>
									</div>

									<input type="text" id="termPaymentWmEcId" name="termPayment" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>检查条款:</span>
									</div>

									<input type="text" id="inspectionWmEcId" name="inspection" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>仲裁条款:</span>
									</div>

									<input type="text" id="arbitrationWmEcId" name="arbitration" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他:</span>
									</div>

									<input type="text" id="othersWmEcId" name="others" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wmEc3-4">下一步</button>
								</div>

							</div>

						</article>
						
						<article class="step col-xs-12 wmEc4-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wmEc4-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 wmEc4-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${exportContract }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled wmEc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
		
<!-- ######################3.报关进口########################################################################################### -->
 <div id="boxBgIc" class="container bgIc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>
                        <!-- start 委托方  -->
						<article class="step col-xs-12 bgIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon bgIc1-2" alt=""  />
								报关进口委托方
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 bgIc1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>委托方:</span>
									</div>

									<input type="text" name="consignor" class="col-xs-6 pam" required="" value="" maxlength="50"  id="consignorBgIcId"/>
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>主要货物名称:</span>
									</div>
                                     <input type="text" name="goodsName" class="col-xs-6 pam" required="" value="" maxlength="50"  id="goodsNameBgIcId"/>
									
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>HS编号:</span>
									</div><input type="text" name="hsCode" class="col-xs-6 pam" required="" value="" maxlength="10"  id="hsCodeBgIcId"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>重量:</span>
									</div><input id="weightBgIcId" type="text" name="weight" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>包装情况:</span>
									</div><input id="packDetailBgIcId" type="text" name="packDetail" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同号:</span>
									</div><input id="contractNoBgIcId" type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>许可文件号:</span>
									</div><input id="licenseFileNoBgIcId" type="text" name="licenseFileNo" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货物总价:</span>
									</div>
									<input type="text" name="goodsTotalPrice" class="col-xs-6 pam" required="" value="" maxlength="50"  id="goodsTotalPriceBgIcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>进出口日期:</span>
									</div>
									<input type="text" id="imExDateBgIcId" name="imExDate" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>提单号:</span>
									</div>

									<input type="text" id="deliveryNumberBgIcId" name="deliveryNumber" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >贸易方式:</span>
									</div>

									<input id="tradeTypeBgIcId"  type="text" name="tradeType" class="col-xs-6 pam" required="" value="" maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货源地:</span>
									</div>

									<input id="originPlaceBgIcId" type="text" name="originPlace" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他要求:</span>
									</div>

									<input type="text" id="othersBgIcId" name="others" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right bgIc1-4">下一步</button>
								</div>

							</div>

						</article>
						 <!--  end 委托方  -->
						 <!-- start 被委托方 -->
                        <article class="step col-xs-12 bgIc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon bgIc2-2" alt=""  />
								报关进口被委托方
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 bgIc2-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>被委托方:</span>
									</div>

									<input type="text" name="consignee" class="col-xs-6 pam" required="" value="" maxlength="50"  id="consigneeBgIcId"/>
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>报关单编码:</span>
									</div>
                                     <input type="text" name="customsCode" class="col-xs-6 pam" required="" value="" maxlength="50"  id="customsCodeBgIcId"/>
									
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收到单证日期:</span>
									</div><input type="text" id="receiveDocsDateBgIcId" name="receiveDocsDate" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收到单证情况:</span>
									</div>
									<input type="text" name="receiveDocsCondition" class="col-xs-6 pam" required="" value="" maxlength="50"  id="receiveDocsConditionBgIcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>报关收费:</span>
									</div>
									<input type="text" name="customsCharge" class="col-xs-6 pam" required="" value="" maxlength="50"  id="customsChargeBgIcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他单证情况:</span>
									</div>
									<input  id="docsOthersBgIcId" type="text" name="docsOthers" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>承诺说明:</span>
									</div>
									<input  id="comIllustrationBgIcId" type="text" name="comIllustration" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right bgIc2-4">下一步</button>
								</div>

							</div>

						</article>
						<!--  end 被委托方  -->
						<!-- start 附件上传 -->
						<article class="step col-xs-12 bgIc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon bgIc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 bgIc3-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${customsDeAgreement }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>
                        <!-- end 附件上传 -->
						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled bgIc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
		
<!-- ####################4.报关出口############################################################################################# -->
 <div id="boxBgEc" class="container bgEc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>
                        <!-- start 委托方  -->
						<article class="step col-xs-12 bgIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon bgEc1-2" alt=""  />
								报关出口委托方
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 bgEc1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>委托方:</span>
									</div>

									<input type="text" name="consignor" class="col-xs-6 pam" required="" value="" maxlength="50"  id="consignorBgEcId"/>
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>主要货物名称:</span>
									</div>
                                     <input type="text" name="goodsName" class="col-xs-6 pam" required="" value="" maxlength="50"  id="goodsNameBgEcId"/>
									
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>HS编号:</span>
									</div><input type="text" name="hsCode" class="col-xs-6 pam" required="" value="" maxlength="10"  id="hsCodeBgEcId"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>重量:</span>
									</div><input id="weightBgEcId" type="text" name="weight" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>包装情况:</span>
									</div><input id="packDetailBgEcId" type="text" name="packDetail" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同号:</span>
									</div><input id="contractNoBgEcId" type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>许可文件号:</span>
									</div><input id="licenseFileNoBgEcId" type="text" name="licenseFileNo" class="col-xs-6 pam" required="" value="" maxlength="10"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货物总价:</span>
									</div>
									<input type="text" name="goodsTotalPrice" class="col-xs-6 pam" required="" value="" maxlength="50"  id="goodsTotalPriceBgEcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>进出口日期:</span>
									</div>
									<input type="text" id="imExDateBgEcId" name="imExDate" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>提单号:</span>
									</div>

									<input type="text" id="deliveryNumberBgEcId" name="deliveryNumber" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >贸易方式:</span>
									</div>

									<input id="tradeTypeBgEcId"  type="text" name="tradeType" class="col-xs-6 pam" required="" value="" maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货源地:</span>
									</div>

									<input id="originPlaceBgEcId" type="text" name="originPlace" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他要求:</span>
									</div>

									<input type="text" id="othersBgEcId" name="others" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right bgEc1-4">下一步</button>
								</div>

							</div>

						</article>
						 <!--  end 委托方  -->
						 <!-- start 被委托方 -->
                        <article class="step col-xs-12 bgEc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon bgEc2-2" alt=""  />
								报关出口被委托方
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 bgEc2-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>被委托方:</span>
									</div>

									<input type="text" name="consignee" class="col-xs-6 pam" required="" value="" maxlength="50"  id="consigneeBgEcId"/>
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>报关单编码:</span>
									</div>
                                     <input type="text" name="customsCode" class="col-xs-6 pam" required="" value="" maxlength="50"  id="customsCodeBgEcId"/>
									
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收到单证日期:</span>
									</div><input type="text" id="receiveDocsDateBgEcId" name="receiveDocsDate" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true"/>
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收到单证情况:</span>
									</div>
									<input type="text" name="receiveDocsCondition" class="col-xs-6 pam" required="" value="" maxlength="50"  id="receiveDocsConditionBgEcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>报关收费:</span>
									</div>
									<input type="text" name="customsCharge" class="col-xs-6 pam" required="" value="" maxlength="50"  id="customsChargeBgEcId">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他单证情况:</span>
									</div>
									<input  id="docsOthersBgIcId" type="text" name="docsOthers" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>承诺说明:</span>
									</div>
									<input  id="comIllustrationBgEcId" type="text" name="comIllustration" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right bgEc2-4">下一步</button>
								</div>

							</div>

						</article>
						<!--  end 被委托方  -->
						<!-- start 附件上传 -->
						<article class="step col-xs-12 bgEc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon bgEc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 bgEc3-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${exportCustomsDeAgreement }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>
                        <!-- end 附件上传 -->
						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled bgEc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
		
<!-- #######################5.仓储进库########################################################################################## -->
 <div id="boxCcIc" class="container CcIc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 CcIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon CcIc1-2" alt=""  />
								仓储进库
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcIc1-3">
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方:</span>
									</div>

									<input type="text" id="buyersCcIcId" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>采购单编号:</span>
									</div>

									<input type="text" id="purOrderNoCcIcId" name="purOrderNo" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方地址:</span>
									</div>

									<input type="text" id="bAddressCcIcId" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>发布日期:</span>
									</div>

									<input type="text" id="issueDateCcIcId"  name="issueDate" class="col-xs-6 date-picker" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方联系方式:</span>
									</div>

									<input type="text" id="bTelphoneCcIcId" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方传真:</span>
									</div>

									<input type="text" id="bFaxCcIcId" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>项目:</span>
									</div>

									<input type="text" id="projectCcIcId" name="project" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>供应商:</span>
									</div>

									<input type="text" id="supplierCcIcId" name="supplier" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>供应商编号:</span>
									</div>

									<input type="text" id="supplierNoCcIcId" name="supplierNo" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人:</span>
									</div>

									<input type="text" id="contactPersonCcIcId" name="contactPerson" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人地址:</span>
									</div>

									<input type="text" id="cpAddressCcIcId" name="cpAddress" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人电话:</span>
									</div>

									<input type="text" id="cpTelephoneCcIcId" name="cpTelephone" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人传真:</span>
									</div>

									<input type="text" id="cpFaxCcIcId" name="cpFax" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同日期:</span>
									</div>

	     							<input type="text" id="contractTermCcIcId" name="contractTerm" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交付日期:</span>
									</div>

									<input type="text" id="deliveryDateCcIcId" name="deliveryDate" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交付说明:</span>
									</div>

									<input type="text" id="instrDestinationCcIcId" name="instrDestination" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交付条款:</span>
									</div>

									<input type="text" id="paymentTermCcIcId" name="paymentTerm" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>国际贸易条件:</span>
									</div>

									<input type="text" id="tradeConditionCcIcId" name="tradeCondition" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>相关文件:</span>
									</div>

									<input type="text" id="refDocumentsCcIcId" name="refDocuments" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>项目产能要求:</span>
									</div>

									<input type="text" id="pclCcIcId" name="pcl" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcIc1-4">下一步</button>
								</div>

							</div>

						</article>

						<article class="row step col-xs-12 CcIc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon CcIc2-2" alt="" />
								详情
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcIc2-3">

								<div class="container">
									       <div id="toolbar">
									           <button id="buttonCcIc" class="btn btn-default" type="button">添加一行</button>
									           <button id="buttonRemoveCcIc" class="btn btn-default" type="button">删除选中行</button>
									       </div>
									       <table id="tableCcIc"
									              data-toggle="table"
									              data-toolbar="#toolbarCcIc"
									              data-height="460"
									              data-url="../json/data1.json">
									           <thead>
									           <tr>
									           		<th data-field="idCcIc" data-checkbox="true"></th>
									               <th data-field="detailNoCcIc">编号</th>
									               <th data-field="descriptionCcIc">描述</th>
									               <th data-field="unitCcIc">计量单位</th>
									               <th data-field="priceVilidityPeriodCcIc">价格有效期</th>
									               <th data-field="perPriceCcIc">单价</th>
									               <th data-field="remarkCcIc">备注</th>
									               
									           </tr>
									           </thead>
									       </table>
									   </div>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right CcIc2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 CcIc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon CcIc3-2" alt="" />
								币种及费用
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcIc3-3">
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>币种:</span>
									</div>

									<input type="text" id="currencyCcIcId" name="currency" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>工装费用:</span>
									</div>

									<input type="text" id="toolingCostCcIcId" name="toolingCost" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>支付方式:</span>
									</div>

									<input type="text" id="payWayCcIcId" name="payWay" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>工装寿命:</span>
									</div>

									<input type="text" id="toolingLifeCcIcId" name="toolingLife" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>分摊量:</span>
									</div>

									<input type="text" id="shareAmountCcIcId" name="shareAmount" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
								<div class="ui_form_item_left col-xs-3">
									<span class="ui_form_required">*</span>
									<span>分摊单价:</span>
								</div>

								<input type="text" id="sharePerPriceCcIcId" name="sharePerPrice" class="col-xs-6 pam" required="" value="" maxlength="50">
							</div>
							<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>注解及说明:</span>
									</div>

									<input type="text" id="commentsCcIcId" name="comments" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcIc3-4">下一步</button>
								</div>

							</div>

						</article>
						
						<article class="step col-xs-12 CcIc4-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f4.png" class="step_icon CcIc4-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 CcIc4-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${wmFlowExccNode }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled CcIc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
		
<!-- #######################6.仓储出库########################################################################################## -->
 <div id="boxCcEc" class="container CcEc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 CcEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon CcEc1-2" alt=""  />
								仓储进库
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcEc1-3">
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方:</span>
									</div>

									<input type="text" id="buyersCcEcId" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>采购单编号:</span>
									</div>

									<input type="text" id="purOrderNoCcEcId" name="purOrderNo" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方地址:</span>
									</div>

									<input type="text" id="bAddressCcEcId" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>发布日期:</span>
									</div>

									<input type="text" id="issueDateCcEcId"  name="issueDate" class="col-xs-6 date-picker" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方联系方式:</span>
									</div>

									<input type="text" id="bTelphoneCcEcId" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方传真:</span>
									</div>

									<input type="text" id="bFaxCcEcId" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>项目:</span>
									</div>

									<input type="text" id="projectCcEcId" name="project" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>供应商:</span>
									</div>

									<input type="text" id="supplierCcEcId" name="supplier" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>供应商编号:</span>
									</div>

									<input type="text" id="supplierNoCcEcId" name="supplierNo" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人:</span>
									</div>

									<input type="text" id="contactPersonCcEcId" name="contactPerson" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人地址:</span>
									</div>

									<input type="text" id="cpAddressCcEcId" name="cpAddress" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人电话:</span>
									</div>

									<input type="text" id="cpTelephoneCcEcId" name="cpTelephone" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>联系人传真:</span>
									</div>

									<input type="text" id="cpFaxCcEcId" name="cpFax" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同日期:</span>
									</div>

	     							<input type="text" id="contractTermCcEcId" name="contractTerm" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交付日期:</span>
									</div>

									<input type="text" id="deliveryDateCcEcId" name="deliveryDate" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交付说明:</span>
									</div>

									<input type="text" id="instrDestinationCcEcId" name="instrDestination" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交付条款:</span>
									</div>

									<input type="text" id="paymentTermCcEcId" name="paymentTerm" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>国际贸易条件:</span>
									</div>

									<input type="text" id="tradeConditionCcEcId" name="tradeCondition" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>相关文件:</span>
									</div>

									<input type="text" id="refDocumentsCcEcId" name="refDocuments" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>项目产能要求:</span>
									</div>

									<input type="text" id="pclCcEcId" name="pcl" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcEc1-4">下一步</button>
								</div>

							</div>

						</article>

						<article class="row step col-xs-12 CcEc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon CcEc2-2" alt="" />
								详情
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcEc2-3">

								<div class="container">
									       <div id="toolbar">
									           <button id="buttonCcEc" class="btn btn-default" type="button">添加一行</button>
									           <button id="buttonRemoveCcEc" class="btn btn-default" type="button">删除选中行</button>
									       </div>
									       <table id="tableCcEc"
									              data-toggle="table"
									              data-toolbar="#toolbarCcEc"
									              data-height="460"
									              data-url="../json/data1.json">
									           <thead>
									           <tr>
									           		<th data-field="id" data-checkbox="true"></th>
									               <th data-field="detailNoCcEc">编号</th>
									               <th data-field="descriptionCcEc">描述</th>
									               <th data-field="unitCcEc">计量单位</th>
									               <th data-field="priceVilidityPeriodCcEc">价格有效期</th>
									               <th data-field="perPriceCcEc">单价</th>
									               <th data-field="remarkCcEc">备注</th>
									               
									           </tr>
									           </thead>
									       </table>
									   </div>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right CcEc2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 CcEc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon CcEc3-2" alt="" />
								币种及费用
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcEc3-3">
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>币种:</span>
									</div>

									<input type="text" id="currencyCcEcId" name="currency" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>工装费用:</span>
									</div>

									<input type="text" id="toolingCostCcEcId" name="toolingCost" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>支付方式:</span>
									</div>

									<input type="text" id="payWayCcEcId" name="payWay" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>工装寿命:</span>
									</div>

									<input type="text" id="toolingLifeCcEcId" name="toolingLife" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>分摊量:</span>
									</div>

									<input type="text" id="shareAmountCcEcId" name="shareAmount" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
								<div class="ui_form_item_left col-xs-3">
									<span class="ui_form_required">*</span>
									<span>分摊单价:</span>
								</div>

								<input type="text" id="sharePerPriceCcEcId" name="sharePerPrice" class="col-xs-6 pam" required="" value="" maxlength="50">
							</div>
							<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>注解及说明:</span>
									</div>

									<input type="text" id="commentsCcEcId" name="comments" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcEc3-4">下一步</button>
								</div>

							</div>

						</article>
						
						<article class="step col-xs-12 CcEc4-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f4.png" class="step_icon CcEc4-2" alt="" />
								<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wmEc4-2" alt="" />
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wmEc2-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 CcEc4-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${exportPurchaseOrderProduction }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled CcEc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>						
						 
		
<!-- #######################7.物流进库########################################################################################## -->
 <div id="boxWlIc" class="container wlIc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 wlIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wlIc1-2" alt=""  />
								物流进库
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wlIc1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运人:</span>
									</div>

									<input type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlIcshipper">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运人地址:</span>
									</div>

									<input type="text"  name="sAddress" class="col-xs-6" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" id="wlIcsAddress">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>起运港:</span>
									</div>

									<input type="text" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlIcdeparturePort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卸货港:</span>
									</div>

									<input type="text" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlIcdischargePort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卸货港:</span>
									</div>

									<input type="text" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlIcdestinationPort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>编号:</span>
									</div>

									<input type="text" id="wlIcxhsNo" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >收货人:</span>
									</div>

									<input id="wlIcconsignee" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收货人地址:</span>
									</div>

									<input id="wlIccAddress" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货到时间:</span>
									</div>

									<input id="wlIcarrivalDate" type="text" name="sFax" class="col-xs-6 date-picker" dateISO="true" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>支付方式:</span>
									</div>

									<input type="text" id="wlIcpayWay" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>箱型/箱量:</span>
									</div>

									<input type="text" id="wlIccontainer" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >提单类型:</span>
									</div>

									<input id="wlIcblt" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>通知人:</span>
									</div>

									<input id="wlIcnotifier" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>件数:</span>
									</div>

									<input id="wlIcitem" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>毛重:</span>
									</div>

									<input type="text" id="wlIcweight" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>体积:</span>
									</div>

									<input type="text" id="wlIcvolume" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >唛头:</span>
									</div>

									<input id="wlIcmarks" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>品名:</span>
									</div>

									<input id="wlIcgoodsName" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>运费确认:</span>
									</div>

									<input id="wlIctransExpenseCharge" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>运费条款:</span>
									</div>

									<input type="text" id="wlIctransClause" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运联系人:</span>
									</div>

									<input type="text" id="wlIccontactPerson" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >电话:</span>
									</div>

									<input id="wlIccpPhone" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>传真:</span>
									</div>

									<input id="wlIccpFax" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>邮箱:</span>
									</div>

									<input id="wlIccpMail" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>预配航班:</span>
									</div>

									<input type="text" id="wlIcpreFlight" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>自拉自报:</span>
									</div>

									<input id="wlIcselfFull" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>特殊要求:</span>
									</div>

									<input type="text" id="wlIcspecialNotes" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>签名盖章:</span>
									</div>

									<input type="text" id="wlIcsignature" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wlIc1-4">下一步</button>
								</div>

							</div>

						</article>

						
						<article class="step col-xs-12 wlIc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlIc2-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 wlIc2-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${purchaseOrderProduction }" delete="true" name="wlIcFiles" metaColums="wlIcFilesColums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wlIcFiles"/>
											<c:param name="metaColums" value="wlIcFilesColums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled wlIc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
		
<!-- #######################8.物流出库########################################################################################## -->
 <div id="boxWlEc" class="container wlEc" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 wlEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wlEc1-2" alt=""  />
								物流出库
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wlEc1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运人:</span>
									</div>

									<input type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlEcshipper">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运人地址:</span>
									</div>

									<input type="text"  name="contractDate" class="col-xs-6 date-picker" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" id="wlEcsAddress">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>起运港:</span>
									</div>

									<input type="text" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlEcdeparturePort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卸货港:</span>
									</div>

									<input type="text" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlEcdischargePort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卸货港:</span>
									</div>

									<input type="text" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlEcdestinationPort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>编号:</span>
									</div>

									<input type="text" id="wlEcxhsNo" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >收货人:</span>
									</div>

									<input id="wlEcconsignee" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收货人地址:</span>
									</div>

									<input id="wlEccAddress" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货到时间:</span>
									</div>

									<input id="wlEcarrivalDate" type="text" name="sFax" class="col-xs-6 date-picker" dateISO="true" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>支付方式:</span>
									</div>

									<input type="text" id="wlEcpayWay" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>箱型/箱量:</span>
									</div>

									<input type="text" id="wlEccontainer" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >提单类型:</span>
									</div>

									<input id="wlEcblt" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>通知人:</span>
									</div>

									<input id="wlEcnotifier" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>件数:</span>
									</div>

									<input id="wlEcitem" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>毛重:</span>
									</div>

									<input type="text" id="wlEcweight" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>体积:</span>
									</div>

									<input type="text" id="wlEcvolume" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >唛头:</span>
									</div>

									<input id="wlEcmarks" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>品名:</span>
									</div>

									<input id="wlEcgoodsName" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>运费确认:</span>
									</div>

									<input id="wlEctransExpenseCharge" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>运费条款:</span>
									</div>

									<input type="text" id="wlEctransClause" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运联系人:</span>
									</div>

									<input type="text" id="wlEccontactPerson" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >电话:</span>
									</div>

									<input id="wlEccpPhone" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>传真:</span>
									</div>

									<input id="wlEccpFax" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>邮箱:</span>
									</div>

									<input id="wlEccpMail" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>预配航班:</span>
									</div>

									<input type="text" id="wlEcpreFlight" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>自拉自报:</span>
									</div>

									<input id="wlEcselfFull" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>特殊要求:</span>
									</div>

									<input type="text" id="wlEcspecialNotes" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>签名盖章:</span>
									</div>

									<input type="text" id="wlEcsignature" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wlEc1-4">下一步</button>
								</div>

							</div>

						</article>

						
						<article class="step col-xs-12 wlEc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlEc2-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 wlEc2-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${exportPurchaseOrderProduction }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled wlEc_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
		
		
<!-- #######################9.物流运输########################################################################################## -->
 <div id="boxTrop" class="container wlTrop" style="display: none;">
			<div class="row">
				<div id="main" class="col-xs-12">
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 wlTrop1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wlTrop1-2" alt=""  />
								物流运输
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wlTrop1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运人:</span>
									</div>

									<input type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlTropshipper">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运人地址:</span>
									</div>

									<input type="text"  name="contractDate" class="col-xs-6 date-picker" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true" id="wlTropsAddress">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>起运港:</span>
									</div>

									<input type="text" name="buyers" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlTropdeparturePort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卸货港:</span>
									</div>

									<input type="text" name="bAddress" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlTropdischargePort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卸货港:</span>
									</div>

									<input type="text" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50"  id="wlTropdestinationPort">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>编号:</span>
									</div>

									<input type="text" id="wlTropxhsNo" name="bTelphone" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >收货人:</span>
									</div>

									<input id="wlTropconsignee" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收货人地址:</span>
									</div>

									<input id="wlTropcAddress" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>货到时间:</span>
									</div>

									<input id="wlTroparrivalDate" type="text" name="sFax" class="col-xs-6 date-picker" dateISO="true" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>支付方式:</span>
									</div>

									<input type="text" id="wlTroppayWay" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>箱型/箱量:</span>
									</div>

									<input type="text" id="wlTropcontainer" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >提单类型:</span>
									</div>

									<input id="wlTropblt" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>通知人:</span>
									</div>

									<input id="wlTropnotifier" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>件数:</span>
									</div>

									<input id="wlTropitem" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>毛重:</span>
									</div>

									<input type="text" id="wlTropweight" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>体积:</span>
									</div>

									<input type="text" id="wlTropvolume" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >唛头:</span>
									</div>

									<input id="wlTropmarks" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>品名:</span>
									</div>

									<input id="wlTropgoodsName" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>运费确认:</span>
									</div>

									<input id="wlTroptransExpenseCharge" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>运费条款:</span>
									</div>

									<input type="text" id="wlTroptransClause" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>托运联系人:</span>
									</div>

									<input type="text" id="wlTropcontactPerson" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel  }"  maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span >电话:</span>
									</div>

									<input id="wlTropcpPhone" onclick="SupCompanyInfoOnclick()"  type="text" name="sellers" class="col-xs-6 pam" required="" value=""  maxlength="50">
									
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>传真:</span>
									</div>

									<input id="wlTropcpFax" type="text" name="sAddress" class="col-xs-6 pam" required="" value=""  maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>邮箱:</span>
									</div>

									<input id="wlTropcpMail" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>预配航班:</span>
									</div>

									<input type="text" id="wlTroppreFlight" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>自拉自报:</span>
									</div>

									<input id="wlTropselfFull" type="text" name="sFax" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>特殊要求:</span>
									</div>

									<input type="text" id="wlTropspecialNotes" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>签名盖章:</span>
									</div>

									<input type="text" id="wlTropsignature" name="sTelphone" class="col-xs-6 pam" required="" value="" maxlength="50" >
								</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wlTrop1-4">下一步</button>
								</div>

							</div>

						</article>

						
						<article class="step col-xs-12 wlTrop2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wlTrop2-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 wlTrop2-3">
							   <div class="form-group">
		                            <div class="col-sm-10">
		                            	<pm:fileList metaObject="${tropShippingExCommission }" delete="true" name="wmFlowExccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="wmFlowExccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
		                            </div>
	                            </div>
							</div>
						</article>

						<div class="col_xs_12">
							<button type="button" class="ui_button submit disabled wlTrop_submit">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
<!-- ################################################################################################################# -->
	</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
$(document).ready(function (){
	var ids = ${selectList};
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});
function SupCompanyInfoOnclick(){
	var taskTypeId=$("#taskTypeId").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['800px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listCompanyInfo/'+taskTypeId
	});
}
function returnValue(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var strName = selectValues[1];
		var sTelphone = selectValues[2];
		var sAddress = selectValues[3];
		$("#companyInfoNameWmIcId").val(strName);
		$("#sellersId").val(strId);
		$("#sAddressWmIcId").val(sAddress);
		$("#sTelphoneWmIcId").val(sTelphone);
	}
}

var num=1;
	//外贸
	var $tableWmIc = $('#tableWmIc'),
    $buttonWmIc = $('#buttonWmIc');
	$buttonRemoveWmIc = $('#buttonRemoveWmIc');
	var $tableWmEc = $('#tableWmEc'),
    $buttonWmEc = $('#buttonWmEc');
	$buttonRemoveWmEc = $('#buttonRemoveWmEc');
	//仓储
	var $tableCcIc = $('#tableCcIc'),
    $buttonCcIc = $('#buttonCcIc');
	$buttonRemoveCcIc = $('#buttonRemoveCcIc');
	var $tableCcEc = $('#tableCcEc'),
    $buttonCcEc = $('#buttonCcEc');
	$buttonRemoveCcEc = $('#buttonRemoveCcEc');

$(function () {
	//外贸
    $buttonWmIc.click(function () {
        $tableWmIc.bootstrapTable('append', randomDataWmIc());
        $tableWmIc.bootstrapTable('scrollTo', 'bottom');
        num++;
    });
    $buttonRemoveWmIc.click(function () {
        var ids = $.map($tableWmIc.bootstrapTable('getSelections'), function (row) {
            return row.btSelectItem;
        });
        $tableWmIc.bootstrapTable('remove', {
            field: 'btSelectItem',
            values: ids
        });
    });
    
    $buttonWmEc.click(function () {
        $tableWmEc.bootstrapTable('append', randomDataWmEc());
        $tableWmEc.bootstrapTable('scrollTo', 'bottom');
        num++;
    });
    $buttonRemoveWmEc.click(function () {
        var ids = $.map($tableWmEc.bootstrapTable('getSelections'), function (row) {
            return row.btSelectItemWmEc;
        });
        $tableWmEc.bootstrapTable('remove', {
            field: 'btSelectItemWmEc',
            values: ids
        });
    });
    //仓储
    $buttonCcIc.click(function () {
        $tableCcIc.bootstrapTable('append', randomDataCcIc());
        $tableCcIc.bootstrapTable('scrollTo', 'bottom');
        num++;
    });
    $buttonRemoveCcIc.click(function () {
        var ids = $.map($tableCcIc.bootstrapTable('getSelections'), function (row) {
            return row.btSelectItem;
        });
        $tableCcIc.bootstrapTable('remove', {
            field: 'btSelectItem',
            values: ids
        });
    });
    $buttonCcEc.click(function () {
        $tableCcEc.bootstrapTable('append', randomDataCcEc());
        $tableCcEc.bootstrapTable('scrollTo', 'bottom');
        num++;
    });
    $buttonRemoveCcEc.click(function () {
        var ids = $.map($tableCcEc.bootstrapTable('getSelections'), function (row) {
            return row.btSelectItem;
        });
        $tableCcEc.bootstrapTable('remove', {
            field: 'btSelectItem',
            values: ids
        });
    });
});
//外贸进口添加输入框
function randomDataWmIc() {
    var startId = ~~(Math.random() * 100),
            rows = [];
		for (var i = 0; i < 1; i++) {
			rows.push({
			btSelectItem:'<input data-index="'+num+'" value="'+num+'" name="btSelectItem"   type="checkbox">',	
            commodityName: '<input class="commodityName"  type="hidden" name="commodityNameId" id="commodityNameId_'+num+'"><input id="commodityName_'+num+'"  type="text"  name="commodityName" readonly="readonly"/><button type="button" onclick="randomDataWmIcOnclick(\'_'+num+'\')">获取值</button>',
            models: '<input type="text" class="models" id="models_'+num+'" name="models" readonly="readonly"/>',
            quantity: '<input type="text" class="quantity"  name="quantity" id="quantity_'+num+'" onkeyup="quantityKeyup(\''+num+'\')" onblur="quantityBlur(\''+num+'\')" />',
            unitPrice: '<input type="text" class="unitPrice" id="unitPrice_'+num+'" name="unitPrice" readonly="readonly"/>',
            totalAmount: '<input type="text" class="totalAmount" name="totalAmount" id="totalAmount_'+num+'" readonly="readonly"/>'
       		});
		}
    return rows;
    
}
//外贸出口添加输入框
function randomDataWmEc() {
    var startId = ~~(Math.random() * 100),
            rows = [];
		for (var i = 0; i < 1; i++) {
			rows.push({
			btSelectItemWmEc:'<input data-index="'+num+'" value="'+num+'" name="btSelectItem"   type="checkbox">',	
            commodityNameWmEc: '<input class="commodityName"  type="hidden" name="commodityNameId" id="commodityNameId_'+num+'"><input id="commodityName_'+num+'"  type="text"  name="commodityName" readonly="readonly"/><button type="button" onclick="randomDataWmEcOnclick(\'_'+num+'\')">获取值</button>',
            modelsWmEc: '<input type="text" class="models" id="models_'+num+'" name="models" readonly="readonly"/>',
            quantityWmEc: '<input type="text" class="quantity"  name="quantity" id="quantity_'+num+'" onkeyup="quantityKeyup(\''+num+'\')" onblur="quantityBlur(\''+num+'\')" />',
            unitPriceWmEc: '<input type="text" class="unitPrice" id="unitPrice_'+num+'" name="unitPrice" readonly="readonly"/>',
            totalAmountWmEc: '<input type="text" class="totalAmount" name="totalAmount" id="totalAmount_'+num+'" readonly="readonly"/>'
       		});
		}
    return rows;
}
//仓储进口添加输入框
function randomDataCcIc() {
    var startId = ~~(Math.random() * 100),
            rows = [];
		for (var i = 0; i < 1; i++) {
			rows.push({
				idCcIc:'<input data-index="'+num+'" value="'+num+'" name="id"   type="checkbox">',	
				descriptionCcIc: '<input class="description"  type="hidden" name="detailNo" id="description_'+num+'"><input id="descriptionName_'+num+'"  type="text"  name="descriptionName"/><button type="button" onclick="randomDataCcIcOnclick(\'_'+num+'\')">获取值</button>',
				unitCcIc: '<input type="text" class="unit"  name="unit" id="unit_'+num+'" onkeyup="quantityKeyup(\''+num+'\')" onblur="quantityBlur(\''+num+'\')" />',
				priceVilidityPeriodCcIc: '<input type="text" class="priceVilidityPeriod" id="priceVilidityPeriod_'+num+'" name="unitPrice"/>',
				perPriceCcIc: '<input type="text" class="perPrice" id="perPrice_'+num+'"/>',
				remarkCcIc: '<input type="text" class="remark"  id="remark_'+num+'"/>'
       		});
		}
    return rows;
}
function randomDataCcEc() {
    var startId = ~~(Math.random() * 100),
            rows = [];
		for (var i = 0; i < 1; i++) {
			rows.push({
				idCcEc:'<input data-index="'+num+'" value="'+num+'" name="id"   type="checkbox">',	
				descriptionCcEc: '<input class="description"  type="hidden" name="detailNo" id="description_'+num+'"><input id="descriptionName_'+num+'"  type="text"  name="descriptionName" /><button type="button" onclick="randomDataCcEcOnclick(\'_'+num+'\')">获取值</button>',
				unitCcEc: '<input type="text" class="unit"  name="unit" id="unit_'+num+'" onkeyup="quantityKeyup(\''+num+'\')" onblur="quantityBlur(\''+num+'\')" />',
				priceVilidityPeriodCcEc: '<input type="text" class="priceVilidityPeriod" id="priceVilidityPeriod_'+num+'" name="unitPrice" />',
				perPriceCcEc: '<input type="text" class="perPrice" id="perPrice_'+num+'" />',
				remarkCcEc: '<input type="text" class="remark"  id="remark_'+num+'" />'
       		});
		}
    return rows;
}
var hhhhh="";
//外贸进口
function randomDataWmIcOnclick(id) {
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
//外贸出口
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
		$("#commodityNameIdWmEc"+hhhhh).val(strId);
		$("#modelsWmEc"+hhhhh).val(models);
		$("#unitPriceWmEc"+hhhhh).val(unitPrice);
	}
}
function quantityKeyup(num){
	var quantity=$("#quantity_"+num).val();
	var unitPrice=$("#unitPrice_"+num).val();
	$("#totalAmount_"+num).val(Number(quantity)*Number(unitPrice));
}
function quantityBlur(num){
	var quantity=$("#quantity_"+num).val();
	var unitPrice=$("#unitPrice_"+num).val();
	$("#totalAmount_"+num).val(Number(quantity)*Number(unitPrice));
}
function oooooooo(){
	
}

</script>
<script type="text/javascript" src="${basePath }/resource/js/contract.js"></script>
<%@ include file="/include/includeFooter.jsp" %>
