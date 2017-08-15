<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
	}
	.myBtn:hover {
	background: #FFFFFF;
	border:2px solid #ff7519;
	padding: 2px 8px;
	border-radius: 4px;
	margin-top: 15px;
	margin-bottom: 7px;
	margin-right: 4px;
	color: black;
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
			    content: '${basePath }/sup/product/productType/selectOrgDept',
			});
	};
</script>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑产品目录</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/sup/product/productType/listProductType">
		                         返回列表
		                     </a>
		                 </div>
	                </div>
	                <div class="ibox-content">
	                     <form class="form-horizontal formValidate" action="${basePath }/sup/product/productType/saveProductType" method="post" >
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="parentId" value="${parent.id}">
							<input type="hidden" name="id" value="${productType.id}">

	                        <c:if test="${not empty parent}">
		                        <div class="form-group">
		                        	<span class="col-sm-2 control-span">产品目录编号</span>
		                            <div class="col-sm-10 control-value">${parent.code }</div>
		                        </div>
		                        <div class="hr-line-dashed"></div>
		                        <div class="form-group">
		                        	<span class="col-sm-2 control-span">产品目录名称</span>
		                            <div class="col-sm-10 control-value">${parent.typeName }</div>
		                        </div>
		                    </c:if>
		                    <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>产品目录编号</span>
	                            <div class="col-sm-3"><input type="text" name="code" class="form-control" value="${productType.code }" required="" minlength="1" maxlength="50" remote="${basePath}/sup/product/productType/ajaxCheckProductType?productTypeId=${productType.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>产品目录名称</span>
	                            <div class="col-sm-3"><input type="text" name="typeName" class="form-control" value="${productType.typeName }" required="" minlength="1" maxlength="100"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                         <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit">保存</button>
	                                
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
<%@ include file="/include/includeFooter.jsp" %>