<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
		margin-top:5px;
	}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title" style="margin-top:20px;margin-left:20px;">
	                    <h5>编辑补充信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form id="tijiao" class="form-horizontal formValidate" action="${basePath }/sup/order/add/savelistorderadd" method="post">
	                        <input type="hidden" name="token" value="${token}">
   							<input type="hidden" name="id" value="${dingdanAddtions.id}">
                        	<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>订单编号</span>
	                        	<input value="${order.id }" name="orderID" style="display:none;"/>
	                       		<div  class="col-sm-4">${order.orderCode }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>订单类型</span>
	                        	<input value="${flowType.id }" name="ordertype" style="display:none;"/>
	                            <div class="col-sm-4">${flowType.flowName }</div>
	                           </div>
	                      
	                           <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>内容</span>
	                            <div class="col-sm-4"><input type="text" name="content" class="form-control" required="" value="${dingdanAddtions.content }" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>附件</span>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${dingdanAddtions }" delete="true" name="infoFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
	                            		<c:param  name="propertyName" value="infoFiles"/>
										<c:param  name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary"  type="submit">保存</button>
	                                
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

</script>
<%@ include file="/include/includeFooter.jsp" %>