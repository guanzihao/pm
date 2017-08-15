<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<style>
<!--
	label{
		font-weight: 400 !important;
		width: 90px !important;
		text-align: right;
		margin-top: 3px;
	}
	.col-xs-3{
		padding-left: 6px;
	}
	
	.col-xs-2{
		padding-top: 4px;
	}
	
	select{
		height: 34px;
		width: 216px;
	}
-->
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>单证制作流程</h5>
	                </div>
	                <div class="ibox-content">
	                    <form id="tijiao" class="form-horizontal formValidate" action="${basePath }/sup/flow/wmimccs/saveDocsProduce" method="post">
	                        <input type="hidden" name="id" value="${wmFlowImccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">开始时间：</label>
	                        	</div>
	                            <div class="col-xs-3"><input type="text" name="startDate"id="startDate" class="form-control input-sm date-picker" value="<fmt:formatDate value="${wmFlowImccNode.startDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                            <div class="col-xs-2">
	                            <label class=" control-label">结束时间：</label>
	                            </div>
	                            <div class="col-xs-3"><input type="text" name="endDate"id="endDate" class="form-control input-sm date-picker"  value="<fmt:formatDate value="${wmFlowImccNode.endDate}" pattern="yyyy-MM-dd"/>" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2" style="padding-left: 0px;">
	                        	<label class=" control-label">合同生效时间：</label>
	                        	</div>
	                            <div class="col-xs-3"><input type="text" name="contractStartDate"id="contractStartDate" class="form-control input-sm date-picker" value="<fmt:formatDate value="${wmFlowImccNode.contractStartDate}" pattern="yyyy-MM-dd"/>" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
	                        		<div class="col-xs-2">
			                      		<label class=" control-label" style="">备注：</label>
			                      	</div>
			                      	<div class="col-xs-9" style="padding-left: 6px;padding-right: 33px;">
										<textarea name="remarks" class="form-control " rows="6" cols="5" style="">${wmFlowImccNode.remarks }</textarea>
									</div>
							</div>
	                        <div class="form-group">
	                        	<div class="col-xs-2">
									<label class=" control-label">状态</label>
								</div>
								<div class="col-xs-9" style="padding-left: 6px;">
									<select class="selectpicker" name="state" style="width: 100px;height: 30px;">
										<c:choose>
											<c:when test="${wmFlowImccNode.state==0 }">
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
	                        	<label class="control-label">附件</label>
	                        </div>
	                            <div class="col-xs-4" style="padding-left: 6px;">
	                            	<pm:fileList metaObject="${wmFlowImccNode }" delete="true" name="wmFlowImccNodeFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="wmFlowImccNodeFiles"/>
										<c:param name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
	                        
	                        
	                        <div class="form-group" style="margin-left: 10px;">
	                          <div class="col-xs-12 col-sm-offset-2" style="padding-left: 70px;">
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
	$(document).ready(function (){
		var ids = [${selectList}];
		for(var i=0; i < ids.length; i++){
			$('#' + ids[i]).attr("checked",'true');
		}
	});function duibi(a) {
		var arr = a.split("-");
		var starttime = new Date(arr[0], arr[1], arr[2]);
		var starttimes = starttime.getTime();
		return starttimes;
	}
	function tijiao(){
		var startDate =duibi( $("#startDate").val().split(" ")[0]);;
		var endDate = duibi($("#endDate").val().split(" ")[0]);
		var releaseDate =duibi($("#contractStartDate").val().split(" ")[0]);
		if(startDate<=endDate){
			if(releaseDate<=endDate){
				if(releaseDate>=startDate){
					 var flagsss=dateTime($("#startDate").val());
					 if (flagsss) { 
						$("#tijiao").submit();
					 } 
				}else{
					layer.alert("合同生效时间不能小于开始时间");
				}
			}else{
				layer.alert("合同生效时间不能大于结束时间");
			}
		}else{
			layer.alert("开始时间不能大于结束时间");
		}
	}
</script>

<%@ include file="/include/includeFooter.jsp" %>