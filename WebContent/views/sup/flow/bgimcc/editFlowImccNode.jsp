<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<style>
<!--
.ibox-title {
	min-height: 0px;
}

label {
	font-weight: 400 !important;
	width: 90px !important;
	text-align: right;
	margin-top: 6px;
}

select{
	height: 34px;
	width: 216px;
}

.col-xs-4{
	padding-left: 6px;
}
-->
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>报关进口接单流程</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/flow/bgimcc/saveFlowImccNode" method="post">
	                        <input type="hidden" name="id" value="${flowImccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <input type="hidden" name="">
	                        <div class="form-group">
	                        	<label class="col-xs-2 control-label">开始时间</label>
	                            <div class="col-xs-4"><input type="text" name="startDate" class="form-control  date-picker" value="${flowImccNode.startDate}" maxlength="200"></div>
	                            <label class="col-xs-2 control-label">完成时间</label>
	                            <div class="col-xs-4"><input type="text" name="endDate" class="form-control date-picker"  value="${flowImccNode.endDate}" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-xs-2 control-label">接单时间</label>
	                            <div class="col-xs-4"><input type="text" name="bussinessDate" class="form-control  date-picker" value="${flowImccNode.bussinessDate}" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
			                      	<label class="col-xs-2 control-label">供应商类型</label>
									 <div class="col-xs-4">
									      <select class="selectpicker" id="supplierType" name="type">
									      <c:choose>
									        <c:when test="${empty flowImccNode.type}">
									            <option value="0">平台供应商</option>  
									            <option value="1">外部供应商</option>  
									        </c:when>
									        <c:otherwise>
										  		<c:if test="${flowImccNode.type==0 }">
										  		   <option value="0" selected="selected">平台供应商</option>
										  		</c:if>
										  		<c:if test="${flowImccNode.type==1 }">
										  		   <option value="1" selected="selected">外部供应商</option>
										  		</c:if>
											</c:otherwise>
										</c:choose>
									      </select>
									</div>
							</div>
							<div id="outGroup">
							      <div class="form-group" >
									
									<label class="col-xs-2 control-label">操作公司</label>
									<div class="col-xs-4">
									<input type="text"  name="opeComp" class="form-control"
										value="${flowImccNode.opeComp}" required="" minlength="1"></div>
							</div>
							</div>
							<div id="inGroup">
							      <div class="form-group" >
			                          <label class="col-xs-2 control-label" style="padding-left: 0px;">操作一级部门</label>
									 <div class="col-xs-4">
									      <select class="selectpicker" name="firstDept">
									            <c:forEach items="${supplierList}" var="item" varStatus="status">
									               <c:choose>
															<c:when test="${item.comCode eq flowImccNode.firstDept}"> <option value="${item.id}" selected="selected">${item.comName}</option></c:when>
															<c:otherwise> <option value="${item.comCode}">${item.comName}</option></c:otherwise>
														</c:choose>
									            </c:forEach>
									      </select>
									</div>
									<label class="col-xs-2 control-label"  style="padding-left: 0px;">操作二级部门</label>
									<div class="col-xs-4">
									    <select class="selectpicker" name="secDept">
									             <c:forEach items="${supplierList}" var="item" varStatus="status">
									               <c:choose>
															<c:when test="${item.comCode eq flowImccNode.firstDept}"> <option value="${item.id}" selected="selected">${item.comName}</option></c:when>
															<c:otherwise> <option value="${item.comCode}">${item.comName}</option></c:otherwise>
														</c:choose>
									            </c:forEach>
									       </select>
									</div>
							</div>
							</div>
							
	                        <div class="form-group">
								<label class="col-xs-2 control-label">状态</label>
								<div class="col-xs-4">
									<select class="selectpicker" name="status">
										<c:choose>
											<c:when test="${flowImccNode.status==0 }">
												<option value="1" >完成</option>
										  		<option value="0" selected="selected">进行中</option>
											</c:when>
											<c:otherwise>
												<option value="1" selected="selected">完成</option>
										  		<option value="0" >进行中</option>
											</c:otherwise>
										</c:choose>
										  
									</select>
								</div>
							</div>
	                         
	                        <div class="form-group">
	                        	<label class="col-xs-2 control-label">附件</label>
	                            <div class="col-xs-4">
	                            	<pm:fileList metaObject="${flowImccNode }" delete="true" name="flowImccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="flowImccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-xs-2 control-label"  style="padding-left: 0px;">完成情况说明</label>
	                            <div class="col-xs-4"><input type="text" name="remarks" value="${flowImccNode.remarks}" class="form-control" minlength="1"></div>
	                        </div>
	                         <div class="form-group">
								<div class="form-group">
			                      	<label class="col-xs-2 control-label">备注</label>
									<div class="col-xs-9"><textarea name="note" class="form-control " rows="6">${flowImccNode.note }</textarea></div>
								</div>
							</div>
	                        
	                        
	                        <div class="form-group">
	                            <div class="col-xs12 col-sm-offset-2">
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
 
        var accountType=$("#supplierType").val();
           if(accountType=="0"){
              $("#inGroup").show();
              $("#outGroup").hide();
           }else{
              $("#inGroup").hide();
              $("#outGroup").show();
           }
          
      $("#supplierType").change(function () {
       var accountType1=$("#supplierType").val();
       if(accountType1=="0"){
           $("#inGroup").show();
           $("#outGroup").hide();
       }else{
            $("#inGroup").hide();
            $("#outGroup").show();
       }
 });
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>