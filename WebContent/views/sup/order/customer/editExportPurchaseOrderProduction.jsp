<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<link rel="stylesheet" href="${basePath }/resource/css/bootstrap-table.css">
<script src="${basePath }/resource/js/bootstrap-table.js"></script>

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
		parent.layer.msg('指定的table 行数不存在！',{icon: 1});
			return;
		}
		$tr.after(trHtml);
	}

	function delTr(ckb) {
		//获取选中的复选框，然后循环遍历删除
		var ckbs = $("input[name=" + ckb + "]:checked");
		if (ckbs.size() == 0) {
			parent.layer.msg('要删除指定行，需选中要删除的行！',{icon: 1});
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
	var num=1;
	function addTr2(tab, row) {
		var trHtml = '<tr align="center">'+
						'<td><input type="checkbox" name="ckb"/></td>'+
						'<td><input type="hidden" name="ids'+num+'" value="1"  class="form-control ids"><input type="text"  name="description'+num+'" class="form-control description input"  required=""  ></td>'+
						'<td><input type="text"  name="unit'+num+'" class="form-control input unit"  required=""  ></td>'+
						'<td><input type="text"  name="priceVilidityPeriod'+num+'" class="form-control input priceVilidityPeriod"  required=""  ></td>'+
						'<td><input type="text"  name="perPrice'+num+'" class="form-control input perPrice" number="true"  required="" ></td>'+
						'<td><input type="text"  name="remark'+num+'" class="form-control input remark"   required="" ></td>'
					+'</tr>';
		addTr(tab, row, trHtml);
		num++;
	}
	
	function initRow(){
		var tab='tab';
		var row='-1';
		 var jsonStr = $("#epopdId").html();
		var datas = JSON.parse(jsonStr);
		for (var i = 0; i < datas.length; i++) {
			var data = datas[i];
			var trHtml = '<tr align="center">'+
						'<td><input type="checkbox" name="ckb"/></td>'+
						'<td><input type="hidden" name="ids'+num+'" value="'+data.id+'"  class="form-control ids"><input type="text" value="'+data.description+'" name="description'+num+'" class="form-control input description"    ></td>'+
						'<td><input type="text" value="'+data.priceVilidityPeriod+'"  name="priceVilidityPeriod'+num+'" class="form-control input priceVilidityPeriod"     >'+
						'<td><input type="text"  name="unit'+num+'" value="'+data.unit+'" class="form-control input unit"    ></td>'+
						'<td><input type="text"  name="perPrice'+num+'" value="'+data.perPrice+'" class="form-control input perPrice" number="true"  >'+
						'<td><input type="text"  name="remark'+num+'" value="'+data.remark+'" class="form-control input remark"    ></td>'
					+'</tr>';
			
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
			font{
				color: red;
				
			}
			.col-sm-3 input{
				margin-left: 25px;
			}
			.col-sm-3 select{
				margin-left: 25px;
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
			.col-sm-8 textarea{
				margin-left: 25px;
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
<div id="boxCcEc" class="container">
			<div class="row">
				<div class="ibox-title">
                    <a class="btn btn-white btn-sm myBtn" onclick="JavaScript:history.back(-1);">
						<span>返回</span>
					</a>
                </div>
				<div id="main" class="col-xs-12">
				<form id="CcEcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/ExportOrderFrom/saveObject/${nodeType}/${flowId}/${isCompany}" method="post">
					<input type="hidden" name="id" value="${epop.id }">
					<input type="hidden" id="isDelegationId" name="isDelegation" value="${epop.isDelegation }">
						<input type="hidden" name="orderFromId" value="${epop.orderFromId }">
						<input type="hidden" name="userId" value="${epop.userId }">
						<input type="hidden" name="taskId" value="${epop.taskId }">
						<span id="epopdId" style="display: none;">${epopd }</span>
						<input type="hidden" id="idsId" name="ids">
					<input type="hidden" id="descriptionId" name="description">
					<input type="hidden" id="unitId" name="unit">
					<input type="hidden" id="priceVilidityPeriodId" name="priceVilidityPeriod">
					<input type="hidden" id="perPriceId" name="perPrices">
					<input type="hidden" id="remarkId" name="remark">
					<div id="myTabContent" class="col-xs-12">
						<article class="step col-xs-12 CcEc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon CcEc1-2" alt=""  />
								仓储出库
							</h2>

							<div class="ibox-content CcEc1-3">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.consignor }" id="consignorCcEcId" name="consignor" class="form-control"  required=""  ></div>
		                   <c:if test="${epop.isDelegation eq '0' }">
		                   			<label class="col-sm-2 control-label">交货时间<font>*</font></label>
		                            <div class="col-sm-3"><input type="text"  required="" name="deliveryDate"  value="<fmt:formatDate value="${epop.deliveryDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"  ></div>
	                        </c:if>
	                        	</div>
	                        <c:if test="${epop.isDelegation eq '0' }">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">保险<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.pcl }" name="pcl" class="form-control"  required=""  ></div>
	                        		<label class="col-sm-2 control-label">包装<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.refDocuments }" name="refDocuments" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">采购单编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.purOrderNo }" name="purOrderNo" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">买方地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.bAddress }" name="bAddress" class="form-control"  ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">发布日期<font></font></label>
		                            <div class="col-sm-3"><input type="text"  name="issueDate"  value="<fmt:formatDate value="${epop.issueDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true"  ></div>
		                        	<label class="col-sm-2 control-label">买方联系方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.bTelphone }" name="bTelphone" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">买方传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.bFax }" name="bFax" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">项目<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.project }" name="project" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">供应商<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.supplier }" name="supplier" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">供应商编号<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.supplierNo }" name="supplierNo" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">联系人<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.contactPerson }" name="contactPerson" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">联系人地址<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.cpAddress }" name="cpAddress" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">联系人电话<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.cpTelephone }" name="cpTelephone" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">联系人传真<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.cpFax }" name="cpFax" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">合同日期<font></font></label>
		                            <div class="col-sm-3"><input type="text"  name="contractTerm"  value="${epop.contractTerm }" class="form-control date-picker" dateISO="true"  ></div>
		                        	<label class="col-sm-2 control-label">买方<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.buyer }" name="buyer" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">交付说明<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.instrDestination }" name="instrDestination" class="form-control"    ></div>
		                        	<label class="col-sm-2 control-label">交付条款<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.paymentTerm }" name="paymentTerm" class="form-control"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">国际贸易条件<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.tradeCondition }" name="tradeCondition" class="form-control"    ></div>
	                        	</div>
	                        </c:if>
	                        	<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcEc1-4">下一步</button>
								</div>
							</div>

						</article>
				<c:if test="${epop.isDelegation eq '0' }">
						<article class="row step col-xs-12 CcEc2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon CcEc2-2" alt="" />
								详情
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 CcEc2-3" style="display: none;">
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
												<th>型号<font>*</font></th>
												<th>价格有效期<font>*</font></th>
												<th>单价<font>*</font></th>
												<th>数量<font>*</font></th>
											</tr>
										</thead>
									</table>
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

							<div class="ibox-content CcEc3-3" style="display: none;">
								<div class="form-group">
	                        		<label class="col-sm-2 control-label">总价<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.sharePerPrice }" name="sharePerPrice" class="form-control" number="true"  required="" ></div>
		                        	<label class="col-sm-2 control-label">其他<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.comments }" name="comments" class="form-control"  required="" ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">币种<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.currency }" name="currency" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">工装费用<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.toolingCost }" name="toolingCost" class="form-control"    ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">支付方式<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.payWay }" name="payWay" class="form-control"   ></div>
		                        	<label class="col-sm-2 control-label">工装寿命<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.toolingLife }" name="toolingLife" class="form-control" digits="true"   ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">分摊量<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${epop.shareAmount }" name="shareAmount" class="form-control" digits="true"  ></div>
	                        	</div>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right CcEc3-4">下一步</button>
								</div>
							</div>

						</article>
					</c:if>
						<article class="step col-xs-12 CcEc4-1">
							<c:if test="${epop.isDelegation eq '0' }">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f4.png" class="step_icon CcEc4-2" alt="" />
									期望完成时间
								</h2>
							</c:if>
							<c:if test="${epop.isDelegation eq '1' }">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon CcEc4-2" alt="" />
									期望完成时间
								</h2>
							</c:if>
								<div class="step_content col-xs-11 col-xs-offset-1 CcEc4-3" style="display: none;">
									<c:choose>
										<c:when test="${supCompanyInFo eq '1' }">
											<div class="form-group">
						                        	<label class="col-sm-2 control-label">开始时间<font>*</font></label>
						                           <div class="col-sm-3"><input type="text" id="startDateCcEcId" name="startDate" readonly="readonly" value="<fmt:formatDate value="${epop.startDate }" pattern="yyyy-MM-dd"/>"  class="form-control" dateISO="true"  ></div>
						                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
						                            <div class="col-sm-3"><input id="endDateCcEcId" type="text"  name="endDate" readonly="readonly" class="form-control"  value="<fmt:formatDate value="${epop.endDate }" pattern="yyyy-MM-dd"/>"   dateISO="true"></div>
					                        	</div>
					                        	<div class="form-group">
					                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
												<div class="col-sm-8"><textarea name="explain" readonly="readonly" id="explainCcEcId" class="form-control " rows="6" >${epop.explain }</textarea></div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
						                        	<label class="col-sm-2 control-label">开始时间<font>*</font></label>
						                           <div class="col-sm-3"><input type="text" id="startDateCcEcId" name="startDate" value="<fmt:formatDate value="${epop.startDate }" pattern="yyyy-MM-dd"/>"  class="form-control date-picker" dateISO="true"  ></div>
						                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
						                            <div class="col-sm-3"><input id="endDateCcEcId" type="text"  name="endDate" class="form-control date-picker"  value="<fmt:formatDate value="${epop.endDate }" pattern="yyyy-MM-dd"/>"   dateISO="true"></div>
					                        	</div>
					                        	<div class="form-group">
					                      		<label class="col-sm-2 control-label">内容详情<font></font></label>
												<div class="col-sm-8"><textarea name="explain" id="explainCcEcId" class="form-control " rows="6" >${epop.explain }</textarea></div>
											</div>
										</c:otherwise>
									</c:choose>
								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right  CcEc4-4">下一步</button>
								</div>
								</div>
	                        	
							</article>
					<c:if test="${epop.isDelegation eq '0' }">
						<article class="step col-xs-12 CcEc5-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f5.png" class="step_icon CcEc5-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 CcEc5-3" style="display: none;">
								<div class="form-group">
			                            <div class="col-sm-10">
			                            	<pm:fileList metaObject="${epop }" delete="true" name="fileArray" metaColums="columsCcEc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="fileArray"/>
												<c:param  name="metaColums" value="columsCcEc"/>
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
var aaa=false;
var bbb=false;
var ccc=false;
var ddd=false;
var isDelegation=0;
$(function() {
	initRow();
	isDelegation=$("#isDelegationId").val();
});
	$(".CcEc1-2").click(function() {
		if(aaa){
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_f5.png");
			$(".CcEc1-3").show();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").hide();
		}

	});
	$(".CcEc2-2").click(function() {
		if(bbb){
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_f5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").show();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").hide();
		}

	});
	$(".CcEc3-2").click(function() {
		if(bbb&&aaa){
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_c3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_f5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").show();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").hide();
		}

	});
	
	$(".CcEc4-2").click(function() {
		if(bbb&&aaa&&ccc){
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_c4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_f5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").show();
			$(".CcEc5-3").hide();
		}

	});
	$(".CcEc5-2").click(function() {
		if(bbb&&aaa&&ccc&&ddd){
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_c5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").show();
		}

	});
	$(".CcEc1-4").click(function() {
		$("#CcEcForm").validate();
		 if($("#CcEcForm").valid()){
			 if (isDelegation=='1') {
					 $(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
					$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_c2.png");
					$(".CcEc1-3").hide();
					$(".CcEc4-3").show();
				 aaa=true;
			}else{
				$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
				$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_f5.png");
				$(".CcEc1-3").hide();
				$(".CcEc2-3").show();
				$(".CcEc3-3").hide();
				$(".CcEc4-3").hide();
				$(".CcEc5-3").hide();
				aaa=true;
			}
			
		 }
	});
	$(".CcEc2-4").click(function() {
		$("#CcEcForm").validate();
		 if($("#CcEcForm").valid()){
			 huoQuShuJu();
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_c3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").show();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").hide();
			bbb=true;
		 }
	});
	$(".CcEc3-4").click(function() {
		$("#CcEcForm").validate();
		 if($("#CcEcForm").valid()){
			 huoQuShuJu();
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_c4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_f5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").show();
			$(".CcEc5-3").hide();
			ccc=true;
		 }
	});
	
	$(".CcEc4-4").click(function() {
		if (isDelegation=='1') {
			 $(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc1-3").show();
			$(".CcEc4-3").hide();
		 aaa=true;
		}else{
			$(".CcEc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".CcEc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".CcEc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".CcEc4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
			$(".CcEc5-2").attr("src", "${basePath }/resource/images/icon_c5.png");
			$(".CcEc1-3").hide();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").show();
			ddd=true;
		}
	});

	//提交订单
	$(".submit").on("click",function(){
		huoQuShuJu();
		$("#CcEcForm").submit();
		
	});
function huoQuShuJu(){
	$("#idsId").val("");
	 $("#descriptionId").val("");
	 $("#unitId").val("");
	 $("#priceVilidityPeriodId").val("");
	 $("#perPriceId").val("");
	 $("#remarkId").val("");
	var idsArray = [];
	$('.ids').each(function(i,e){
		idsArray[i]=e.value;
	});
	var descriptionArray = [];
	$('.description').each(function(i,e){
		descriptionArray[i]=e.value;
	});
	var unitArray = [];
	$('.unit').each(function(i,e){
		unitArray[i]=e.value;
	});
	var priceVilidityPeriodArray = [];
	$('.priceVilidityPeriod').each(function(i,e){
		priceVilidityPeriodArray[i]=e.value;
	});
	var perPriceArray = [];
	$('.perPrice').each(function(i,e){
		perPriceArray[i]=e.value;
	});
	var remarkArray = [];
	$('.remark').each(function(i,e){
		remarkArray[i]=e.value;
	});
	 $("#idsId").val(idsArray.toString());
	 $("#descriptionId").val(descriptionArray.toString());
	 $("#unitId").val(unitArray.toString());
	 $("#priceVilidityPeriodId").val(priceVilidityPeriodArray.toString());
	 $("#perPriceId").val(perPriceArray.toString());
	 $("#remarkId").val(remarkArray.toString());
}
</script>
<%@ include file="/include/includeFooter.jsp" %>
