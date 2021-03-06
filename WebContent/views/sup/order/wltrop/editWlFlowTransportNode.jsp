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
	                    <h5>物流运输接单信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form id="tijiao" class="form-horizontal formValidate" action="${basePath }/sup/flow/wltransports/saveWlFlowTransportNode" method="post">
	                        <input type="hidden" name="id" value="${wlFlowTransportNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">开始时间：</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="startDate" id="startDate" class="form-control input-sm date-picker" value="<fmt:formatDate value="${wlFlowTransportNode.startDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                            <div class="col-xs-2">
	                            <label class=" control-label">结束时间：</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="endDate" id="endDate" class="form-control input-sm date-picker"  value="<fmt:formatDate value="${wlFlowTransportNode.endDate}" pattern="yyyy-MM-dd"/>" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">接单时间：</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="businessDate" id="businessDate" class="form-control input-sm date-picker" value="<fmt:formatDate value="${wlFlowTransportNode.businessDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
								<div class="form-group">
								<div class="col-xs-2">
			                      	<label class=" control-label">备注</label>
			                      	</div>
									<div class="col-xs-9"><textarea name="remarks" class="form-control " rows="6">${wlFlowTransportNode.remarks }</textarea></div>
								</div>
							</div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
								<label class=" control-label">状态</label>
								</div>
								<div class="col-xs-4">
									<select class="selectpicker" name="state">
										<c:choose>
											<c:when test="${wlFlowTransportNode.state==0 }">
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
	                            	<pm:fileList metaObject="${wlFlowTransportNode }" delete="true" name="wlFlowTransportNodeFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="wlFlowTransportNodeFiles"/>
										<c:param name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
	                        <div class="form-group" style="margin-left: 20px;">
	                            <div class="col-xs-12 col-sm-offset-2">
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
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
function saveNoticeDraft(){
	$("#noticeDraft").val(true);
	$('.formValidate').submit();
}function duibi(a) {
	var arr = a.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();
	return starttimes;
}
function tijiao(){
	var startDate =duibi( $("#startDate").val().split(" ")[0]);;
	var endDate = duibi($("#endDate").val().split(" ")[0]);
	var releaseDate =duibi($("#businessDate").val().split(" ")[0]);
	if(startDate<=endDate){
		if(releaseDate<=endDate){
			if(releaseDate>=startDate){
				 var flagsss=dateTime($("#startDate").val());
				 if (flagsss) { 
					$("#tijiao").submit();
				 } 
			}else{
				layer.alert("接单时间不能小于开始时间");
			}
		}else{
			layer.alert("接单时间不能大于结束时间");
		}
	}else{
		layer.alert("开始时间不能大于或等于结束时间");
	}
}
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>