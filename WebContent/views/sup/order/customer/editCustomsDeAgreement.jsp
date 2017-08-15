<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link rel="stylesheet" href="${basePath }/resource/css/bootstrap-table.css">
<script src="${basePath }/resource/js/bootstrap-table.js"></script>

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
			.col-sm-8 textarea{
				margin-left: 25px;
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
			font{
				color: red;
				
			}
			.col-sm-3 input{
				margin-left: 25px;
			}
			.col-sm-3 select{
				margin-left: 25px;
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
 
<div id="boxBgIc" class="container">
			<div class="row">
				<div class="ibox-title">
                    <a class="btn btn-white btn-sm myBtn" onclick="JavaScript:history.back(-1);">
						<span>返回</span>
					</a>
                </div>
				<div id="main" class="col-xs-12">
				<form id="bgIcForm" class="form-horizontal formValidate" action="${basePath }/sup/order/CustomsDeAgreementimcc/saveObject/${nodeType}/${flowId}/${isCompany}" method="post">
					<div id="myTabContent" class="col-xs-12">
						<input type="hidden" name="id" value="${bgic.id }">
						<input type="hidden" id="isDelegationId" name="isDelegation" value="${bgic.isDelegation }">
						<input type="hidden" name="orderFromId" value="${bgic.orderFromId }">
						<input type="hidden" name="userId" value="${bgic.userId }">
						<input type="hidden" name="taskId" value="${bgic.taskId }">
                        <!-- start 委托方  -->
						<article class="step col-xs-12 bgIc1-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_c1.png" class="step_icon bgIc1-2" alt=""  />
								报关进口委托方
							</h2>
							<div class="ibox-content bgIc1-3">
	                    		<div class="form-group">
		                        	<label class="col-sm-2 control-label">委托方<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.consignor }" name="consignor" class="form-control"  required=""  ></div>
		                        	<c:if test="${bgic.isDelegation eq '0'}">
			                        	<label class="col-sm-2 control-label">主要货物名称<font>*</font></label>
			                            <div class="col-sm-3"><input type="text" value="${bgic.goodsName }" name="goodsName" class="form-control"  required=""  ></div>
		                        	
		                        	</c:if>
	                        	</div>
	                        <c:if test="${bgic.isDelegation eq '0'}">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">HS编号<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.hsCode }" name="hsCode" class="form-control"  required="" maxlength="10" minlength="10"  digits="true" ></div>
		                        	<label class="col-sm-2 control-label">重量<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" name="weight" value="${bgic.weight }" class="form-control"  required=""  number="true"></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">其他要求<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.others }" name="others" class="form-control"  required="" ></div>
		                        	<label class="col-sm-2 control-label">合同号<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.contractNo }" name="contractNo" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">许可文件号<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.licenseFileNo }" name="licenseFileNo" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">货物总价<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.goodsTotalPrice }" name="goodsTotalPrice" class="form-control"  required="" number="true"  ></div>
	                        	</div>
	                        	
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">进出口日期<font>*</font></label>
		                            <div class="col-sm-3"><input type="text"  name="imExDate"  value="<fmt:formatDate value="${bgic.imExDate }" pattern="yyyy-MM-dd"/>" class="form-control date-picker" dateISO="true" required=""  ></div>
		                        	<label class="col-sm-2 control-label">提单号<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.deliveryNumber }" name="deliveryNumber" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">贸易方式<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.tradeType }" name="tradeType" class="form-control"  required=""  ></div>
		                        	<label class="col-sm-2 control-label">货源地<font>*</font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.originPlace }" name="originPlace" class="form-control"  required=""  ></div>
	                        	</div>
	                        	<div class="form-group">
									<label class="col-sm-2 control-label">包装情况<font></font></label>
		                            <div class="col-sm-3"><input type="text" value="${bgic.packDetail }" name="packDetail" class="form-control"   ></div>
	                        	</div>
	                       </c:if>
	                        	<div class="col-xs-12">
									<button type="button" class="ui_form_button ui_form_right bgIc1-4">下一步</button>
								</div>
							</div>
						</article>
						<article class="step col-xs-12 bgIc2-1">
								<h2 class="step_header">
									<img src="${basePath }/resource/images/icon_f2.png" class="step_icon bgIc2-2" alt="" />
									期望完成时间
								</h2>
								<div class="step_content col-xs-11 col-xs-offset-1 bgIc2-3" style="display: none;">
									<c:choose>
										<c:when test="${supCompanyInFo eq '1' }">
											<div class="form-group">
					                        	<label class="col-sm-2 control-label">开始时间<font>*</font></label>
					                           <div class="col-sm-3"><input type="text" id="startDateBgIcId" name="startDate" readonly="readonly" value="<fmt:formatDate value="${bgic.startDate }" pattern="yyyy-MM-dd"/>"  class="form-control " dateISO="true"  required="" ></div>
					                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
					                            <div class="col-sm-3"><input id="endDateBgIcId" type="text"  name="endDate" class="form-control" readonly="readonly"   value="<fmt:formatDate value="${bgic.endDate }" pattern="yyyy-MM-dd"/>"   dateISO="true" required=""></div>
				                        	</div>
				                        	<div class="form-group">
				                      			<label class="col-sm-2 control-label">内容详情<font></font></label>
												<div class="col-sm-8"><textarea name="explain" readonly="readonly" id="explainBgIcId" class="form-control " rows="6" >${bgic.explain }</textarea></div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-group">
					                        	<label class="col-sm-2 control-label">开始时间<font>*</font></label>
					                           <div class="col-sm-3"><input type="text" id="startDateBgIcId" name="startDate" value="<fmt:formatDate value="${bgic.startDate }" pattern="yyyy-MM-dd"/>"  class="form-control date-picker" dateISO="true"  required="" ></div>
					                        	<label class="col-sm-2 control-label">结束时间<font>*</font></label>
					                            <div class="col-sm-3"><input id="endDateBgIcId" type="text"  name="endDate" class="form-control date-picker"   value="<fmt:formatDate value="${bgic.endDate }" pattern="yyyy-MM-dd"/>"   dateISO="true" required=""></div>
				                        	</div>
				                        	<div class="form-group">
				                      			<label class="col-sm-2 control-label">内容详情<font></font></label>
												<div class="col-sm-8"><textarea name="explain" id="explainBgIcId" class="form-control " rows="6" >${bgic.explain }</textarea></div>
											</div>
										</c:otherwise>
									</c:choose>
									<div class="col-xs-12">
										<button type="button" class="ui_form_button ui_form_right  bgIc2-4 top">下一步</button>
									</div>
								</div>
							</article>
					<c:if test="${bgic.isDelegation eq '0'}">	
						<article class="step col-xs-12 bgIc3-1">
							<h2 class="step_header">
								<img src="${basePath }/resource/images/icon_f3.png" class="step_icon bgIc3-2" alt="" />
								附件上传
							</h2>
							<div class="step_content col-xs-11 col-xs-offset-1 bgIc3-3">
								<div class="form-group">
			                            <div class="col-sm-10">
			                            	<pm:fileList metaObject="${bgic }" delete="true" name="fileArray" metaColums="columsBgIc"/>
			                            	<c:import url="/include/includeUploadify.jsp">
			                            		<c:param  name="propertyName" value="fileArray"/>
												<c:param  name="metaColums" value="columsBgIc"/>
										    </c:import>
			                            </div>
			                      </div>
							</div>
						</article>
					</c:if>
                        <!-- end 附件上传 -->
						<div class="col_xs_12">
							<button type="submit" class="ui_button submit disabled ">提交订单</button>
							
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
$(function(){
	isDelegation=$("#isDelegationId").val();
});

$(".bgIc1-3").show();
$(".bgIc2-3").hide();
$(".bgIc3-3").hide();
$(".bgIc1-2").click(function() {
	$("#bgIcForm").validate();
	if($("#bgIcForm").valid()&&aaa){
		$(".bgIc1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
		$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
		$(".bgIc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
		$(".bgIc1-3").show();
		$(".bgIc2-3").hide();
		$(".bgIc3-3").hide();
	}

});
$(".bgIc2-2").click(function() {
	$("#bgIcForm").validate();
	if($("#bgIcForm").valid()&&bbb){
		$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_f1.png");
		$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
		$(".bgIc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
		$(".bgIc1-3").hide();
		$(".bgIc2-3").show();
		$(".bgIc3-3").hide();
		
	}

});
$(".bgIc3-2").click(function() {
	$("#bgIcForm").validate();
	if($("#bgIcForm").valid()&&bbb&&aaa){
		$(".bgIc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
		$(".bgIc3-2").attr("src", "${basePath }/resource/images/icon_f2.png");
		$(".bgIc3-2").attr("src", "${basePath }/resource/images/icon_c2.png");
		$(".bgIc1-3").hide();
		$(".bgIc2-3").hide();
		$(".bgIc3-3").show();
		
	}

});


$(".bgIc1-4").click(function() {
	$("#bgIcForm").validate();
	 if($("#bgIcForm").valid()){
		 if (isDelegation=='1') {
			 $(".bgIc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
			$(".bgIc1-3").hide();
			$(".bgIc2-3").show();
			 aaa=true;
		}else{
			$(".bgIc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
			$(".bgIc3-2").attr("src", "${basePath }/resource/images/icon_f3.png");
			$(".bgIc1-3").hide();
			$(".bgIc2-3").show();
			$(".bgIc3-3").hide();
			aaa=true;
		}
	}
})
$(".bgIc2-4").click(function() {
	$("#bgIcForm").validate();
	 if($("#bgIcForm").valid()){
		 if (isDelegation=='1') {
			 $(".bgIc1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
			$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".bgIc1-3").show();
			$(".bgIc2-3").hide();
			 aaa=true;
		}else{
			$(".bgIc1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
			$(".bgIc2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
			$(".bgIc3-2").attr("src", "${basePath }/resource/images/icon_c3.png");
			$(".bgIc1-3").hide();
			$(".bgIc2-3").hide();
			$(".bgIc3-3").show();
		}
		
		bbb=true;
	}

});
</script>
<%@ include file="/include/includeFooter.jsp" %>
