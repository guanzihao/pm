<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

	<head>
		<meta charset="utf-8" />
		<title></title>
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
		<div id="box" class="container">
			<div class="row">
				<div id="main" class="col-xs-12">
					<div class="top col-xs-12">
						<div class="top_item col-xs-6">
							<span>订单编号: </span>
							<span>${task.taskId }</span>
						</div>
						<div class="top_item col-xs-6">
							<span>订单时间: </span>
							<span><fmt:formatDate value="${task.issueDate }" pattern="yyyy-MM-dd"/></span>
						</div>
						<div class="top_item col-xs-6">
							<span>下单客户: </span>
							<span>${companyInFo.comName }</span>
						</div>
					</div>
					
				<form class="form-horizontal formValidate" action="${basePath }/sup/order/ex/saveExportContract" method="post">
					<input type="hidden" id="taskTypeId" name="taskTypeId" value="${taskTypeId}">
                    <input type="hidden"  name="taskId" value="${task.id}">
                    <input type="hidden" name="buyers" value="${companyInFo.id }">
                    <input type="hidden" id="sellersId" name="sellers" value="">
					<div id="myTabContent" class="col-xs-12">
						<div id="title" class="col-xs-11 col-xs-offset-1">
							<span class="choose">您当前选择的服务类型是: </span>
							<span class="choose_explain">综合出口订单（通关/外汇/退税/退税融资） 退税方式：一达通负责退税（垫付退税服务）</span>
							<a class="fr">编辑</a>
						</div>

						<article class="step col-xs-12 wl1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wl1-2" alt=""  />
								选择收汇方式与报关方式
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wl1-3">

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同号码:</span>
									</div>

									<input type="text" name="contractNo" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>合同日期:</span>
									</div>

									<input type="text" name="contractDate" class="col-xs-6 date-picker" required="" value="${companyInFo.comFoundingtime }" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方:</span>
									</div>

									<input type="text" name="buyers" class="col-xs-6 pam" required="" value="${companyInfo.comName }" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方地址:</span>
									</div>

									<input type="text" name="bAddress" class="col-xs-6 pam" required="" value="${companyInFo.comWebsite }" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方传真:</span>
									</div>

									<input type="text" name="bFax" class="col-xs-6 pam" required="" value="" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>买方联系方式:</span>
									</div>

									<input type="text" name="bTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方:</span>
									</div>

									<input id="companyInfoName" type="text" name="sellers" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方地址:</span>
									</div>

									<input id="sAddressId" type="text" name="sAddress" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方传真:</span>
									</div>

									<input id="sFaxId" type="text" name="sFax" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>卖方联系方式:</span>
									</div>

									<input type="text" name="sTelphone" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>装运时间:</span>
									</div>

									<input type="text" name="shipmentTime" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>交货时间:</span>
									</div>

									<input type="text" name="deliveryTime" class="col-xs-6 date-picker" required="" value="" maxlength="15" dateISO="true">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>装运口岸:</span>
									</div>

									<input type="text" name="portShipment" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>目的口岸:</span>
									</div>

									<input type="text" name="portDestination" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>包装:</span>
									</div>

									<input type="text" name="packing" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>保险:</span>
									</div>

									<input type="text" name="insurance" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>制造厂商:</span>
									</div>

									<input type="text" name="manufactory" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>付款条件:</span>
									</div>

									<input type="text" name="termPayment" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>检查条款:</span>
									</div>

									<input type="text" name="inspection" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>
								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>仲裁条款:</span>
									</div>

									<input type="text" name="arbitration" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="ui_form_item col-xs-6">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>其他:</span>
									</div>

									<input type="text" name="others" class="col-xs-6 pam" required="" value="${companyInFo.comTel }" maxlength="50">
								</div>

								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wl1-4">下一步</button>
								</div>

							</div>

						</article>

						<article class="row step col-xs-12 wl2-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wl2-2" alt="" />
								产品及开票人信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wl2-3">

								<div class="ui_form_item col-xs-7">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收汇方式:</span>
									</div>
									<input type="button" id="b1" value="添加一行" onclick="add()" class="btn btn-default" />
									<table width="700" border="0" cellspacing="0" cellpadding="0" id="addtr">
										<tr>
											<td height="30" align="center" bgcolor="#CCCCCC">商品名称</td>
											<td align="center" bgcolor="#CCCCCC">型号</td>
											<td align="center" bgcolor="#CCCCCC">数量</td>
											<td align="center" bgcolor="#CCCCCC">单价</td>
											<td align="center" bgcolor="#CCCCCC">总价</td>
										</tr>
										<tr>
											<td height="30" align="center">
												<select id="kesssId" name="commodityName" onchange="kkk()">
													<option>请选择</option>
													<c:forEach items="${products}" var="item">
														<option value="${item.id }">${item.productName }</option>
													</c:forEach>
												</<select>
											</td>
											<td align="center"><input type="text" name="models" /></td>
											<td align="center">
												<input type="text" name="quantity" />
											</td>
											<td align="center"><input type="text" name="unitPrice" /></td>
											<td align="center"><input type="text" name="totalAmount" /></td>
										</tr>
									</table>
								</div>

								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="ui_form_item col-xs-6"></div>
								<div class="col-xs-12">
									<button type="button"  class="ui_form_button ui_form_right wl2-4" >下一步</button>
								</div>

							</div>

						</article>

						<article class="step col-xs-12 wl3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wl3-2" alt="" />
								报关信息
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wl3-3">

								<div class="ui_form_item col-xs-7">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收汇方式:</span>
									</div>

									<input type="text" class="col-xs-7 pam" />
								</div>

								<div class="ui_form_item col-xs-7">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收汇方式:</span>
									</div>

									<input type="text" class="col-xs-7 pam" />
								</div>

								<div class="ui_form_item col-xs-7">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>收汇方式:</span>
									</div>

									<input type="text" class="col-xs-7 pam" />
								</div>

								<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right wl3-4">下一步</button>
								</div>

							</div>

						</article>
						
						<article class="step col-xs-12 wl4-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f4.png" class="step_icon wl4-2" alt="" />
								附件上传
							</h2>

							<div class="step_content col-xs-11 col-xs-offset-1 wl4-3">

								<div class="ui_form_item col-xs-7">
									<div class="ui_form_item_left col-xs-3">
										<span class="ui_form_required">*</span>
										<span>上传附件:</span>
									</div>

									<input type="text" class="col-xs-7 pam" />
								</div>

								<div class="col-xs-12">
									<button class="ui_form_button ui_form_right wl4-4">下一步</button>
								</div>

							</div>

						</article>

						<div class="col_xs_12">
							<button class="ui_button submit disabled">提交订单</button>
							<button class="ui_button">保存草稿</button>
						</div>

					</div>
				</form>
				</div>

			</div>
		</div>
	</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">

$(document).ready(function (){
	var ids = [${selectList}];
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});

function add() { //欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
	var oTr = document.getElementById("addtr").rows[1];
	var newTr = oTr.cloneNode(true);
	document.getElementById("addtr").getElementsByTagName("tbody")[0].appendChild(newTr);
	document.getElementById("b1").disabled = newTr.rowIndex == 20;
}

var aaa=false;
var bbb=false;
var ccc=false;
var ddd=false;

	$(function() {

		
		$(".wl2-3").hide();
		$(".wl3-3").hide();
		$(".wl4-3").hide();
		function add() { //欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
			var oTr = document.getElementById("addtr").rows[1];
			var newTr = oTr.cloneNode(true);
			document.getElementById("addtr").getElementsByTagName("tbody")[0].appendChild(newTr);
			document.getElementById("b1").disabled = newTr.rowIndex == 20;
		}
		$(".wl1-2").click(function() {
			if(aaa){
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".wl1-3").show();
				$(".wl2-3").hide();
				$(".wl3-3").hide();
				$(".wl4-3").hide();
			}

		});
		$(".wl2-2").click(function() {
			if(bbb){
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".wl1-3").hide();
				$(".wl2-3").show();
				$(".wl3-3").hide();
				$(".wl4-3").hide();
			}

		});
		$(".wl3-2").click(function() {
			if(bbb&&aaa){
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_c3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".wl1-3").hide();
				$(".wl2-3").hide();
				$(".wl3-3").show();
				$(".wl4-3").hide();
			}

		});
		
		$(".wl4-2").click(function() {
			if(bbb&&aaa&&ccc){
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_c4.png");
				$(".wl1-3").hide();
				$(".wl2-3").hide();
				$(".wl3-3").hide();
				$(".wl4-3").show();
			}

		});
		$(".wl1-4").click(function() {
			var flag = true;
			if(flag) {
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".wl1-3").hide();
				$(".wl2-3").show();
				$(".wl3-3").hide();
				$(".wl4-3").hide();
				aaa=true;
			}

		});
		$(".wl2-4").click(function() {
			var flag = true;
			if(flag) {
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_c3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".wl1-3").hide();
				$(".wl2-3").hide();
				$(".wl3-3").show();
				$(".wl4-3").hide();
				bbb=true;
			} 

		});
		$(".wl3-4").click(function() {
			var flag = true;
			if(flag) {
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_c4.png");
				$(".wl1-3").hide();
				$(".wl2-3").hide();
				$(".wl3-3").hide();
				$(".wl4-3").show();
				ccc=true;
			} 
		});
		
		$(".wl4-4").click(function() {
			var flag = true;
			if(flag) {
				$(".wl1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
				$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
				$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
				$(".wl4-2").attr("src", "${basePath }/resource/images/icon_f4.png");
				$(".wl1-3").show();
				$(".wl2-3").hide();
				$(".wl3-3").hide();
				$(".wl4-3").hide();
				ddd=true;
			} 
		});
		
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
			$("#companyInfoName").val(strName);
			$("#sellersId").val(strId);
			$("#sAddressId").val(sAddress);
			$("#sTelphoneId").val(sTelphone);
		}
	}
	
</script>

<%@ include file="/include/includeFooter.jsp" %>