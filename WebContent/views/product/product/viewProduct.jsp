<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title" style="padding-bottom: 1px ;">
						<h5 style="margin-top: 6px;margin-left:10px;">产品信息</h5>
						<div class="ibox-tools">
							<a class="btn btn-xs btn-white myBtn2" href="${basePath }/sup/product/product/editProduct/${product.id }/${product.productType.id}">
				                 修改
				            </a>
			            </div>
					</div>
		            <div class="ibox-content">
						<div class="row" style="padding-left: 60px;padding-top:20px">
							<div class="col-sm-12 " style="padding-bottom: 20px">
								<div class="dl-horizontal">
									<span>产品目录</span>
									<span style="margin-left:40px ">${product.productType.typeName }</span>
								</div>
							</div>
							<div class="col-sm-12 " style="padding-bottom: 20px">
								<div class="dl-horizontal">
									<span>产品编号</span>
									<span style="margin-left:40px ">${product.productCode }</span>
								</div>
							</div>
							
							<div class="col-sm-12 " style="padding-bottom: 20px">
								<div class="dl-horizontal">
									<span>产品名称</span>
									<span style="margin-left:40px ">${product.productName }</span>
								</div>
							</div>
							<div class="col-sm-12 " style="padding-bottom: 20px">
								<div class="dl-horizontal">
									<span>产品单价</span>
									<span style="margin-left:40px ">${product.productPrice }</span>
								</div>
							</div>
							
							<div class="col-sm-12 " style="padding-bottom: 20px">
								<div class="dl-horizontal">
									<span>产品规格型号</span>
									<span style="margin-left:10px ">${product.productSpec }</span>
								</div>
							</div>
							<div class="col-sm-12 dx_jianju">
								<div class="dl-horizontal">
									<span>单位</span>
									<span style="margin-left:67px ">${product.productUnit }</span>
								</div>
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