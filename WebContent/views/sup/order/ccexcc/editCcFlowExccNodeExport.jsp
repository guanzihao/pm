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
	                    <h5>仓库出库信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form id="tijiao"class="form-horizontal formValidate" action="${basePath }/sup/flow/ccexccs/saveCcFlowExccNodeExport" method="post">
	                        <input type="hidden" name="id" value="${ccFlowExccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                         <div class="col-xs-2">
	                        	<label class="width control-label">开始时间：</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="startDate" id="startDate" class="form-control input-sm date-picker" value="<fmt:formatDate value="${ccFlowExccNode.startDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                            <!-- input-sm date-picker -->
	                             <div class="col-xs-2">
	                            <label class=" control-label">结束时间：</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="endDate"id="endDate" class="form-control input-sm date-picker"  value="<fmt:formatDate value="${ccFlowExccNode.endDate}" pattern="yyyy-MM-dd"/>" maxlength="50"></div>
	                        </div>
	                        
	                       <div class="form-group">
	                        <div class="col-xs-2">
	                       		<label class=" control-label">送货地址：</label>
	                       		</div>
	                            <div class="col-xs-4">
	                               <input type="text" name="deliveryAddr" class="form-control" value="${ccFlowExccNode.deliveryAddr}">
	                            </div>
	                       </div>
	                       <div class="form-group">
	                        <div class="col-xs-2" style="padding-left: 0px;">
	                        	<label class=" control-label">司机签收时间：</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="driverSignDate"id="driverSignDate" class="form-control input-sm date-picker" value="<fmt:formatDate value="${ccFlowExccNode.driverSignDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
								<div class="form-group">
								 <div class="col-xs-2">
			                      	<label class=" control-label">备注</label>
			                      	</div>
									<div class="col-xs-10"><textarea name="remarks" class="form-control " rows="6">${ccFlowExccNode.remarks }</textarea></div>
								</div>
							</div>
	                        <div class="form-group">
	                         <div class="col-xs-2">
								<label class=" control-label">状态</label>
								</div>
								<div class="col-xs-10">
									<select class="selectpicker" name="state">
										<c:choose>
											<c:when test="${ccFlowExccNode.state==0 }">
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
	                            <div class="col-xs-9">
	                            	<pm:fileList metaObject="${ccFlowExccNode }" delete="true" name="ccFlowExccNodeFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="ccFlowExccNodeFiles"/>
										<c:param name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
	                        
	                        
	                        <div class="form-group" style="margin-left: 10px;">
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
function saveNoticeDraft(){
	$("#noticeDraft").val(true);
	$('.formValidate').submit();
}
function duibi(a) {
	var arr = a.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();
	return starttimes;
}
function tijiao(){
	var startDate =duibi( $("#startDate").val().split(" ")[0]);;
	var endDate = duibi($("#endDate").val().split(" ")[0]);
	var releaseDate =duibi($("#driverSignDate").val().split(" ")[0]);
	if(startDate<=endDate){
		if(releaseDate<=endDate){
			if(releaseDate>=startDate){
				 var flagsss=dateTime($("#startDate").val());
				 if (flagsss) { 
					$("#tijiao").submit();
				 } 
			}else{
				layer.alert("司机签收时间不能小于开始时间");
			}
		}else{
			layer.alert("司机签收时间不能大于结束时间");
		}
	}else{
		layer.alert("开始时间不能大于结束时间");
	}
}
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>