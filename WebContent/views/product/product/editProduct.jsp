<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
	}
	
	
	.col-sm-2{
		float:left;
		width:120px;
		margin-left:40px;
		margin-top:7px;
		text-align: left;
	}
	
	.col-sm-10{
		float:left;
		padding-left:0px;
	}
	
</style>

<script type="text/javascript">
	 function selectProductType(){
			layer.open({
				title: '选择',
			    type: 2,
			    area: ['80%', '90%'],
			    fix: false, //不固定
			    maxmin: true,
			    content: '${basePath }/sup/product/productType/selectProductType',
			});
	};
</script>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <!-- <div class="ibox-title">
	                </div> -->
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/product/product/saveProduct" method="post" >
	                        <input type="hidden" name="parentId" value="${parent.id}">
							<input type="hidden" name="id" value="${product.id}">
							<div class="form-group">
	                        	<span class="col-sm-2 " style="float:left"><i class="ired">*</i>产品目录 </span>
	                           	<div class="col-sm-10" style="float:left;padding-left:0px"><input type="hidden" name="productTypeId" id="productTypeId" value="${product.productType.id }" required="" minlength="1" maxlength="100"><input type="text" id="productTypeName" name="productTypeName" class="form-control" value="${product.productType.typeName }" required="" minlength="1" maxlength="50" onclick="selectProductType()" ></div>	
	                        </div>
	                       <!--  <div class="hr-line-dashed"></div> -->
							<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>产品编号</span>
	                            <div class="col-sm-10"><input type="text" name="productCode" class="form-control" value="${product.productCode }" required="" minlength="1" maxlength="50" remote="${basePath}/sup/product/product/ajaxCheckProduct?productId=${product.id}"></div>
	                        </div>
	                        
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>产品名称</span>
	                            <div class="col-sm-10"><input type="text" name="productName" class="form-control" value="${product.productName }" required="" minlength="1"  maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>产品单价</span>
	                            <div class="col-sm-10"><input type="text" name="productPrice" class="form-control" value="${product.productPrice }" required="" minlength="1" min="0" maxlength="200"></div>
	                        </div>

	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span">产品规格型号</span>
	                            <div class="col-sm-10"><input type="text" name="productSpec" class="form-control" value="${product.productSpec }"  maxlength="50"></div>
	                        </div>
	                        
	                        
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>单位</span>
	                            <div class="col-sm-10"><input type="text" name="productUnit" class="form-control" value="${product.productUnit }" required=""  maxlength="50"></div>
	                        </div>

	                        <div class="form-group" style="margin-left:72px;margin-top: 20px;">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit" onclick="checkForm()">保存</button>
	                                
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
function fuzhi(vid,zhi)
{
	$(vid).val(zhi);
}
</script>
<%@ include file="/include/includeFooter.jsp" %>