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
	                    <h5>报关进口查验流程</h5>
	                </div>
	                <div class="ibox-content">
	                    <form id="tijiao" class="form-horizontal formValidate" action="${basePath }/sup/flow/bgimccs/saveFlowImccNodeCheck" method="post">
	                        <input type="hidden" name="id" value="${flowImccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">开始时间：</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="startDate" id="startDate" class="form-control  date-picker" value="<fmt:formatDate value="${flowImccNode.startDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                           <div class="col-xs-2">
	                            <label class=" control-label">完成时间：</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="endDate"id="endDate" class="form-control date-picker"  value="<fmt:formatDate value="${flowImccNode.endDate}" pattern="yyyy-MM-dd"/>" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">查验时间：</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="inspectionDate"id="inspectionDate" class="form-control  date-picker" value="<fmt:formatDate value="${flowImccNode.inspectionDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                        </div>
	                        <%-- <div class="form-group">
	                          <div class="col-xs-2">
			                      	<label class=" control-label">供应商类型</label>
			                      	</div>
									 <div class="col-xs-4">
									      <select class="selectpicker" id="supplierType1"name="type">
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
							<div id="outGroup1">
							      <div class="form-group" >
							      <div class="col-xs-2">
							      <label class=" control-label">操作公司</label>
							      </div>
									<div class="col-xs-4">
									<input type="text"  name="opeComp" class="form-control"
										value="${flowImccNode.opeComp}" required="" minlength="1"></div>
							     </div>
							</div>
							<div id="inGroup1">
							      <div class="form-group" >
							      <div class="col-xs-2" style="padding-left: 0px;">
			                          <label class=" control-label">操作一级部门</label>
			                          </div>
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
									<div class="col-xs-2" style="padding-left: 0px;">
									<label class=" control-label">操作二级部门</label>
									</div>
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
							</div> --%>
	                        <div class="form-group" >
	                        <div class="col-xs-2">
								<label class=" control-label">状态</label>
								</div>
								<div class="col-xs-4">
									<select class="selectpicker" name="status">
										<c:choose>
											<c:when test="${flowImccNode.status==0 }">
												<option value="1" >完成</option>
										  		<option value="0" selected="selected">未完成</option>
											</c:when>
											<c:otherwise>
												<option value="1" selected="selected">完成</option>
										  		<option value="0" >未完成</option>
											</c:otherwise>
										</c:choose>
										  
									</select>
								</div>
							</div>
	                         
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">附件</label>
	                        	</div>
	                            <div class="col-xs-4">
	                            	<pm:fileList metaObject="${flowImccNode }" delete="true" name="flowImccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="flowImccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2" style="padding-left: 0px;">
	                        	<label class=" control-label">完成情况说明</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="remarks" value="${flowImccNode.remarks}" class="form-control" minlength="1"></div>
	                        </div>
	                         <div class="form-group">
								<div class="form-group">
								<div class="col-xs-2">
			                      	<label class=" control-label">备注</label>
			                      	</div>
									<div class="col-xs-9"><textarea name="note" class="form-control " rows="6">${flowImccNode.note }</textarea></div>
								</div>
							</div>
	                        
	                        
	                        <div class="form-group" style="margin-left: 10px;">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                 <div class="btn btn-primary" onclick="tijiao()">保存</div>
	                                
	                          </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript">
 
        var accountType=$("#supplierType1").val();
           if(accountType=="0"){
              $("#inGroup1").show();
              $("#outGroup1").hide();
           }else{
              $("#inGroup1").hide();
              $("#outGroup1").show();
           }
          
      $("#supplierType1").change(function () {
       if(accountType=="0"){
           $("#inGroup1").show();
           $("#outGroup1").hide();
       }else{
            $("#inGroup1").hide();
            $("#outGroup1").show();
       }
 });
      function duibi(a) {
    		var arr = a.split("-");
    		var starttime = new Date(arr[0], arr[1], arr[2]);
    		var starttimes = starttime.getTime();
    		return starttimes;
    	}
    	function tijiao(){
    		var startDate =duibi( $("#startDate").val().split(" ")[0]);;
    		var endDate = duibi($("#endDate").val().split(" ")[0]);
    		var releaseDate =duibi($("#inspectionDate").val().split(" ")[0]);
    		if(startDate<=endDate){
    			if(releaseDate<=endDate){
    				if(releaseDate>=startDate){
    					 var flagsss=dateTime($("#startDate").val());
    					 if (flagsss) { 
    						$("#tijiao").submit();
    					 } 
    				}else{
    					layer.alert("查验时间不能小于开始时间");
    				}
    			}else{
    				layer.alert("查验时间不能大于结束时间");
    			}
    		}else{
    			layer.alert("开始时间不能大于结束时间");
    		}
    	}
</script>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>