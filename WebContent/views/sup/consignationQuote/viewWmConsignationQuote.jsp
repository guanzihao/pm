<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
<!--
 body {
	font-size:14px;
}

.col-sm-6{
	margin:20px 0;
}

*{
	font-size: 13px;
}

.ibox-title{
	min-height: 0px;
}

dt{
	float: left;
	font-weight: 400 !important;
	margin-right: 10px;
	width: 100px;
	margin-left: 30px;
}

dl{
	margin-top:20px;
}


-->
</style>


<body class="gray-bg" >
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>报关业务委托报价详情</h5>
					</div>
					<c:if test="${consignationQuote.checkState eq '-1'}">
						<div class="ibox-title">
		                    <div class="ibox-tools">
		                       <a href="${basePath }/sup/consignationQuote/saveSubmitConsignationQuote/${consignationQuote.id}" class="btn btn-primary">提交</a>
		                    </div>
		                </div>
		             </c:if>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>起始地点</dt>
									<dd>${consignationQuote.originPlace }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>起始地点</dt>
									<dd>${consignationQuote.purposePlace }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>价格</dt>
									<dd>${consignationQuote.price }元</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<pm:execute id="flowType" bean="supplierConsignationQuoteServiceImpl" method="getFlowType">
										<pm:execute-param type="java.lang.String" value="${consignationQuote.supplierType}" />
									</pm:execute>
									<dt>业务类型</dt>
									<dd>${flowType.flowName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${consignationQuote.checkState eq '0'}"><span class="label label-warning shenghe">未审核</span></c:if>
										<c:if test="${consignationQuote.checkState eq '1'}"><span class="label label-danger yishenhe">已审核</span></c:if>
										<c:if test="${consignationQuote.checkState eq '-1'}"><span class=" label label-danger weitijiao"> 未提交</span></c:if>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
	        </div>
	    </div>
	    
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>