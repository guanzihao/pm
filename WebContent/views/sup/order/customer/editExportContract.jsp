<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />

	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
	function addTr(tab, row, trHtml) {
		//获取table最后一行 $("#tab tr:last")
		//获取table第一行 $("#tab tr").eq(0)
		//获取table倒数第二行 $("#tab tr").eq(-2)
		var $tr = $("#" + tab + " tr").eq(row);
		if ($tr.size() == 0) {
			parent.layer.msg('指定的table 行数不存在！', {
				icon : 1
			});
			return;
		}
		$tr.after(trHtml);
	}

	function delTr(ckb) {
		//获取选中的复选框，然后循环遍历删除
		var ckbs = $("input[name=" + ckb + "]:checked");
		if (ckbs.size() == 0) {
			parent.layer.msg('要删除指定行，需选中要删除的行！', {
				icon : 1
			});
			return;
		}
		ckbs.each(function() {
			$(this).parent().parent().remove();
		});
	}

	/**
	 * 全选
	 * 
	 * allCkb 全选复选框的id
	 * items 复选框的name
	 */
	function allCheck(allCkb, items) {
		$("#" + allCkb).click(function() {
			$('[name=' + items + ']:checkbox').attr("checked", this.checked);
		});
	}

	////////添加一行、删除一行测试方法///////
	$(function() {
		//全选
		allCheck("allCkb", "ckb");
	});
	var num = 1;
	function addTr2(tab, row) {
		var trHtml = '<tr align="center">'
				+ '<td><input type="checkbox" name="ckb"/></td>'
				+ '<td><input  type="hidden" name="ids" value="1"><input  type="hidden"  name="commodityId" value="1123123" id="commodityNameId_'+num+'"><input id="commodityName_'+ num+ '" required="" class="form-control input" type="text"  name="commodityName" onclick="wmOnclick(\'_'+ num+ '\',\'gys\')" readonly="readonly"/></td>'
				+ '<td><input type="text"  id="models_'+num+'" name="models"       class="form-control input" readonly="readonly">'
				+ '<td><input type="text" id="quantity_'+ num+ '" required="" name="quantity'+ num+ '"  class="form-control input quantity"   number="true"   onkeyup="quantitySj(\''+ num+ '\')" onblur="quantitySj(\''+ num+ '\')" ></td>'
				+ '<td><input type="text"  id="unitPrice_'+num+'" name="unitPrice" class="form-control input" readonly="readonly"/>'
				+ '<td><input type="text" name="totalAmount" required="" id="totalAmount_'+num+'" class="form-control input" readonly="readonly"/></td>'
				+ '</tr>';
		addTr(tab, row, trHtml);
		num++;
	}
	function initRow() {
		var tab = 'tab';
		var row = '-1';
		var jsonStr = $("#wmEcDetailId").html();
		var datas = JSON.parse(jsonStr);
		for (var i = 0; i < datas.length; i++) {
			var data = datas[i];
			var trHtml = '<tr align="center">'
					+ '<td><input type="checkbox" name="ckb"/></td>'
					+ '<td><input  type="hidden" name="ids" value="'+data.id+'"><input  type="hidden" name="commodityId" value="'+data.commodityId+'" id="commodityNameId_'+num+'"><input id="commodityName_'+num+'"  type="text"  name="commodityName" value="'+data.commodityName+'" readonly="readonly"/></td>'
					+ '<td><input type="text"  name="models" value="'+data.model+'" class="fodels"   readonly="readonly"  ></td>'
					+ '<td><input type="text" id="quantity_'+ num+ '" name="quantity'
					+ num+ '" value="'+ data.quantity+ '" class="form-control input quantity" required=""  number="true"   onkeyup="quantityKeyup(\''+ num+ '\')" onblur="quantityKeyup(\''+ num+ '\')" ></td>'
					+ '<td><input type="text" id="unitPrice_'+num+'"  name="unitPrice" value="'+data.unitPrice+'" readonly="readonly"/></td>'
					+ '<td><input type="text" id="totalAmount_'+num+'" name="totalAmount" value="'+data.totalAmount+'" readonly="readonly"/></td>'
					+ '</tr>';

			addTr(tab, row, trHtml);
			num++;
		}
	}

	function delTr2() {
		delTr('ckb');
	}
</script>
		<style>
			p{
				margin: 0;
			}
			button{
				border: none;
			}
			span{
				display: inline;
			}
			
			.container{
				margin: 0;
				padding: 0;
			}
			
			.row{
				height: 100%;
				margin: 0;
				padding: 0;
			}
			.col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12{
				padding: 0;
			}
			.col-sm-8 textarea{
				margin-left: 25px;
			}
			html,body{
			    width: 100%;
			    background: #f5f7fa;
			    /*height: 100%;*/
			}
			
			#box{
			    width: 100%;
			    /*height: 100%;
			    overflow: scroll;*/
			}
			#main{
			    min-width: 600px;
			    /*min-height: 100%;*/
			    background: #f5f7fa;
			    float: none;
			    margin: 0 auto; 
			}
			
			
			// 清除浮动
			#main:after{display:block;clear:both;content:"";visibility:hidden;height:0} 
			
			#main{zoom:1}
			
			/*上方订单编号*/
			.top{
				background: #fff;
				padding: 10px 20px;
			}
			
			.top_item{
				height: 26px;
			}
			
			/* 按钮 */
			.nav_button{
				padding: 0 10px;
				height: 38px;
				background: #f5f7fb;
				border:1px solid #dbe3ed;
				color: #000;
				border-top-left-radius: 4px;
				border-top-right-radius: 4px;
				
			}
			.nav_button:focus{
				outline:none;
			}
			
			.active{
				background: #fff;
				color: #ee7600;
				border-top: 3px solid #EE7600;
			}
			
			/*主要内容*/
			#myTabContent{
				border: 1px solid #dedede;
				padding: 0 5px 20px 0;
				
			}
			
			/*当前选择*/
			#title{
				margin-top: 10px;
				margin-bottom: 20px;
				padding: 0 20px;
				height: 60px;
				line-height: 60px;
				background: #FFFFFF;
				border: 1px solid #e9eef5;				
			}
			
			.choose{
				background: #999999;
				color: #fff;
				padding: 2px 10px;
				border-radius: 2px;
				font-weight: bold;
				margin-right: 4px;
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
			.choose_explain{
				width: 50%;
				text-overflow: ellipsis;
				overflow: hidden;
				white-space: nowrap;
			}
			
			a {
			    color: #06c;
			    text-decoration: none;
			}
			

			a:hover {
			    color: #f90;
			    text-decoration: underline;
			}

			.fr{
				float: right;
			}
			
			/*每个大步骤*/
			.step {
			    position: relative;
			    padding-bottom: 30px;
			}
			.step::before {
			    content: '';
			    position: absolute;
			    top: 0;
			    left: 14px;
			    width: 1px;
			    height: 100%;
			    background: #DAE2ED;
			}
			
			/*标题*/
			h2{
				margin-top: 0;
			}
			
			.step_header {
			    font-size: 16px;
			    font-weight: 500;
			    color: #333;
			    padding-bottom: 10px;
			    margin-bottom: 0;
			    position: relative;
			}
			
			.step_icon{
				margin-right: 10px;
			}
			
			.step_content{
				background: #FFFFFF;
				border: 1px solid #e9eef5;	
				padding: 20px 20px 20px 50px;	
			}
			
			.ui_form_item{
				height: 40px;
				line-height: 40px;
				margin-bottom: 10px;
				position: relative;
			}
			
			.ui_form_item_left{
				width: 100px;
				height: 40px;
				display: inline-block;
				line-height: 40px;
			}
			
			.ui_form_required{
				color: red;
				font-size: 12px;
			}
			
			
			input {
				height: 30px;
				width: 180;
				border: 1px solid #cccccc;
				border-radius: 4px;
				text-indent: 10px;
			}
			
			select {
				height: 30px;
				width: 250px;
				border: 1px solid #cccccc;
				border-radius: 4px;
				text-indent: 10px;
			}
			
			
			.ui_form_button{
				background: #FF7519;
    			color: #FFF;
    			padding: 4px 10px;
    			border-radius: 4px;
    			display: block;
			}
			
			.ui_form_right{
				margin-left: 104px;
			}
			
			.ui_button{
				padding: 6px 20px;
				background: #fff;
				color: #333;
				border: 1px solid #c6cad1;
				border-radius: 2px;
				font-size: 18px;
			}
			
			.disabled{
				background: #f5f7fa;
				color: #cccccc;
				border-color:#dae2ed;
			}
			
			.submit{
				margin-left: 116px;
				margin-right: 4px;
			}
			
			.pam{
				position: absolute;
				top: 0;
				bottom: 0;
				margin: auto;
			}
		</style>
	</head>
	<body>
 <div id="boxWmEc" class="container wmEc">
			<div class="row">
				<div class="ibox-title">
                    <a class="btn btn-white btn-sm myBtn" onclick="JavaScript:history.back(-1);">
						<span>返回</span>
					</a>
                </div>
				<div id="main" class="col-xs-12">
				<form  class="form-horizontal formValidate wmForm" action="${basePath }/sup/order/ex/saveObject/${nodeType}/${flowId}/${isCompany}" method="post">
					<input type="hidden" name="id" value="${importContract.id }">
					<input type="hidden" id="isDelegationIdWmEc" name="isDelegation" value="${importContract.isDelegation }">
					<input type="hidden" name="orderFromId" value="${importContract.orderFromId }">
					<input type="hidden" name="userId" value="${importContract.userId }">
					<input type="hidden" name="taskId" value="${importContract.taskId }">
					<input type="hidden" id="typeIdEc" value="${flowId }">
					<input type="hidden" id="quantityId" name="quantity">
						<span id="wmEcDetailId" style="display: none;">${exContractDetails }</span>
					<div id="myTabContent" class="col-xs-12">
						<article class="step col-xs-12 wm1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wm1-2" alt=""  />
								外贸出口
							</h2>
							<div class="ibox-content wm1-3">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" required="" value="${importContract.consignor }" id="consignorwmEcId" name="consignor" class="form-control"    ></div>
		                        	<c:if test="${importContract.isDelegation eq '0' }">
			                        	<label class="col-sm-2 control-label">合同号码<font></font></label>
			                            <div class="col-sm-3"><input type="text" value="${importContract.contractNo }" name="contractNo" class="form-control"    ></div>
	                        		</c:if>
	                        	</div>
	                        <c:if test="${importContract.isDelegation eq '0' }">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">合同日期<font></font></label>
		                           <div class="col-sm-3"><input type="text"  name="contractDate"  value="<fmt:formatDate value="${importContract.contractDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">买方<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.buyers }" name="buyers"  class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">买方地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.bAddress }" name="bAddress" class="form-control"     ></div>
		                        	<label class="col-sm-2 control-label">买方传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.bFax }" name="bFax" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">买方联系方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.bTelphone }" name="bTelphone"  class="form-control"    ></div>
	                        		
		                        	<label class="col-sm-2 control-label">卖方<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.sellers }"  name="sellers"  class="form-control sellersName"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">卖方地址<font></font></label>
		                            <div class="col-sm-3"><input type="text"  value="${importContract.sAddress }" name="sAddress" class="form-control sAddress"    ></div>
		                        	<label class="col-sm-2 control-label">卖方传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.sFax }" name="sFax" class="form-control sFax"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">卖方联系方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.sTelphone }" name="sTelphone"  class="form-control sTelphone"    ></div>
	                        	</div>
	                       </c:if> 	
	                        	<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wm1-4">下一步</button>
								</div>
							</div>
						</article>
					<c:if test="${importContract.isDelegation eq '0' }">
						<article class="row step col-xs-12 wm2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wm2-2" alt="" />
								产品及开票人信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wm2-3" style="display: none;">
									<div class="row">
										<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="addTr2('tab', -1)">
												<span><i class="fa fa-plus"></i> 添加一行</span>
											</a>
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="delTr2()">
												<span><i class="fa fa-plus"></i> 删除行</span>
											</a>
										</div>
									</div>
									<table id="tab" class="table table-striped table-bordered table-hover">
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
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right wm2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 wm3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wm3-2" alt="" />
								运输及付款信息
							</h2>
							<div class="ibox-content wm3-3" style="display: none;">
	                    		<div class="form-group">
	                    			<label class="col-sm-2 control-label">成交方式 <font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.manufactory }" required=""  name="manufactory" class="form-control"    ></div>
	                        		<label class="col-sm-2 control-label">币制<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.inspection }" required=""  name="inspection" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">付款条件<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.termPayment }" name="termPayment" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">仲裁条款<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.arbitration }" name="arbitration" class="form-control"    ></div>
	                        	</div>
	                    		<div class="form-group">
		                        	<label class="col-sm-2 control-label">装运时间<font></font></label>
		                           <div class="col-sm-3"><input type="text"  name="shipmentTime"  value="<fmt:formatDate value="${importContract.shipmentTime }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
		                        	<label class="col-sm-2 control-label">交货时间<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="<fmt:formatDate value="${importContract.deliveryTime }" pattern="yyyy-MM-dd"/>" name="deliveryTime" class="form-control date-picker"     dateISO="true"></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">装运口岸<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.portShipment }" name="portShipment" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">目的口岸<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.portDestination }" name="portDestination" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">包装<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.packing }" name="packing" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">保险<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${importContract.insurance }" name="insurance" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">注解及说明<font>*</font></label>
		                            <div class="col-sm-8"><input type="text" value="${importContract.others }" required=""  name="others" class="form-control"    ></div>
	                        	</div>
	                        	<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wm3-4">下一步</button>
								</div>
							</div>
						</article>
					</c:if>
						<article class="step col-xs-12 wm4-1">
						
							<c:choose>
								<c:when test="${importContract.isDelegation eq '1' }">
									<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wm4-2" alt="" />
									期望完成时间
								</h2>
								</c:when>
								<c:otherwise>
									<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wm4-2" alt="" />
									期望完成时间
								</h2>
								</c:otherwise>
								
							</c:choose>
								<div class="step_content col-xs-11 col-xs-offset-1 wm4-3" style="display: none;">
									<c:choose>
										<c:when test="${supCompanyInFo eq '1' }">
											<div class="form-group">
					                        	<label class="col-sm-2 control-label">开始时间<font>*</font></label>
					                           <div class="col-sm-3"><input type="text" required="" id="startDateWmEcId" name="startDate" readonly="readonly" value="<fmt:formatDate value="${importContract.startDate }" pattern="yyyy-MM-dd"/>" class="form-control" dateISO="true"   ></div>
					                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
					                            <div class="col-sm-3"><input id="endDateWmEcId" required="" type="text"  name="endDate" class="form-control"  readonly="readonly" value="<fmt:formatDate value="${importContract.endDate }" pattern="yyyy-MM-dd"/>"   dateISO="true" ></div>
				                        	</div>
					                        <div class="form-group">
					                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
												<div class="col-sm-8"><textarea name="explain" id="explainWmEcId" class="form-control " readonly="readonly" rows="6" >${importContract.explain }</textarea></div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
					                        	<label class="col-sm-2 control-label">开始时间<font>*</font></label>
					                           <div class="col-sm-3"><input type="text" required="" id="startDateWmEcId" name="startDate"  value="<fmt:formatDate value="${importContract.startDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"   ></div>
					                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
					                            <div class="col-sm-3"><input id="endDateWmEcId" required="" type="text"  name="endDate" class="form-control date-picker"   value="<fmt:formatDate value="${importContract.endDate }" pattern="yyyy-MM-dd"/>"   dateISO="true" ></div>
				                        	</div>
					                        <div class="form-group">
					                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
												<div class="col-sm-8"><textarea name="explain" id="explainWmEcId" class="form-control " rows="6" >${importContract.explain }</textarea></div>
											</div>
										</c:otherwise>
									</c:choose>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right  wm4-4">下一步</button>
								</div>
								</div>
	                        	
							</article>
					<c:if test="${importContract.isDelegation eq '0' }">
						<article class="step col-xs-12 wm5-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f5.png" class="step_icon wm5-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 wm5-3" style="display: none;">
								<div class="form-group">
				                            <div class="col-sm-10">
				                            	<pm:fileList metaObject="${importContract }" delete="true"  name="fileArray" metaColums="columsWmEc"/>
				                            	<c:import url="/include/includeUploadify.jsp">
				                            		<c:param  name="propertyName" value="fileArray"/>
													<c:param  name="metaColums" value="columsWmEc"/>
											    </c:import>
				                            </div>
				                      </div>
							</div>
						</article>
				</c:if>
						<div class="col_xs_12">
							<button type="submit" class="ui_button submit disabled">提交订单</button>
						</div>
					</div>
				</form>
				</div>
				<div>
				</div>
			</div>
		  </div>
	</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">

$(function(){
	initRow();
});
var hhhhh="";
function wmOnclick(id,flag) {
	var taskTypeId=$("#typeIdEc").val();
	var url='';
	if (flag=='gys') {
		hhhhh=id;
		url='${basePath }/sup/task/listProduct/'+taskTypeId+"/updatewmEc";
	}else{
		url='${basePath }/sup/task/listCompanyInfo/'+taskTypeId+"/editWmEc";
	}
	var taskTypeId=$("#typeIdEc").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  offset: '50px',
	  area: ['900px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: url
	});
}
function returnValueSpUpdateWmEc(selectValues){
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
function returnValueWmEc(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var strName = selectValues[1];
		var sTelphone = selectValues[2];
		var sAddress = selectValues[3];
		$(".sellersName").val(strName);
		$(".sellersId").val(strId);
		$(".sAddress").val(sAddress);
		$(".sTelphone").val(sTelphone);
	}
}
function quantitySj(num){
	var quantity=$("#quantity_"+num).val();
	var unitPrice=$("#unitPrice_"+num).val();
	$("#totalAmount_"+num).val(Number(quantity)*Number(unitPrice));
}
function huoQuShuJu(){
	$("#quantityId").val("");
	var quantityArray = [];
	$('.quantity').each(function(i,e){
		quantityArray[i]=e.value;
	});
	 $("#quantityId").val(quantityArray.toString());
}
</script>
<script type="text/javascript" src="${basePath }/resource/js/wm.js"></script>
<%@ include file="/include/includeFooter.jsp" %>
