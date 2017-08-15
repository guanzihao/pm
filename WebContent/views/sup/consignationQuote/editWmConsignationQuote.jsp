<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />

<style>
	.form-group{
		margin:20px 0;
		font-size:14px;
	}
	
	label {
	font-weight: 400 !important;
	text-align: right;
}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>外贸信息详情</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/consignationQuote/saveSupplierConsignationQuote" method="post">
	                       <input type="hidden" name="type" value="${type}">
	                        <input type="hidden" name="id" value="${consignationQuote.id}">
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>起始地点</label>
	                            <div class="col-sm-3"><input type="text" name="originPlace" class="form-control" required="" value="${consignationQuote.originPlace }" ></div>
	                            <label class="col-sm-2 control-label"><i class="ired">*</i>目的地点</label>
	                            <div class="col-sm-3"><input type="text" name="purposePlace" class="form-control" required="" value="${consignationQuote.purposePlace }" ></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>委托报价类型</label>
	                            <div class="col-sm-3">
	                            	<select name="supplierType" class="select">
	                            		<c:forEach items="${flowTypes }" var="item">
                            				<c:choose>
		                            			<c:when test="${item.id==consignationQuote.supplierType }">
                            						<option value="${item.id }" selected="selected">${item.flowName }</option>
                            					</c:when>
			                            		<c:otherwise>
                            						<option value="${item.id }">${item.flowName }</option>
			                            		</c:otherwise>
                            				</c:choose>
                            			</c:forEach>
	                            	</select>
	                            </div>
	                            	 <label class="col-sm-2 control-label"><i class="ired">*</i>价格</label>
	                           		 <div class="col-sm-3"><input type="text" name="price" class="form-control" required="" number="true" value="${consignationQuote.price }"></div>
	                            
	                        </div>
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
<script type="text/javascript">
$(document).ready(function (){
	var ids = [${selectList}];
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});
</script>
<%@ include file="/include/includeFooter.jsp" %>