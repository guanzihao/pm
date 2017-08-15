<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>物流进口订舱流程</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/flow/wlimcc/saveBookingSpace" method="post">
	                        <input type="hidden" name="id" value="${wlFlowImccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">开始时间</label>
	                            <div class="col-sm-4"><input type="text" name="startDate" class="form-control input-sm date-picker" value="${wlFlowImccNode.startDate}" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">结束时间</label>
	                            <div class="col-sm-4"><input type="text" name="endDate" class="form-control input-sm date-picker"  value="${wlFlowImccNode.endDate}" maxlength="50"></div>
	                        </div>
	                       	<div class="form-group">
	                       		<label class="col-sm-2 control-label">起运港</label>
	                            <div class="col-sm-4">
	                               <input type="text" name="pol" class="form-control" value="${wlFlowImccNode.pol}">
	                            </div>
	                            <label class="col-sm-2 control-label">目的港</label>
	                            <div class="col-sm-4">
	                               <input type="text" name="pod" class="form-control" value="${wlFlowImccNode.pod}">
	                            </div>
	                       	</div>
	                       	<div class="form-group">
	                       		<label class="col-sm-2 control-label">船名</label>
	                            <div class="col-sm-4">
	                               <input type="text" name="shipName" class="form-control" value="${wlFlowImccNode.shipName}">
	                            </div>
	                            <label class="col-sm-2 control-label">航次/航班</label>
	                            <div class="col-sm-4">
	                               <input type="text" name="flight" class="form-control" value="${wlFlowImccNode.flight}">
	                            </div>
	                       	</div>
	                       	<div class="form-group">
	                       		<label class="col-sm-2 control-label">主单号</label>
	                            <div class="col-sm-4">
	                               <input type="text" name="mblNo" class="form-control" value="${wlFlowImccNode.mblNo}">
	                            </div>
	                            <label class="col-sm-2 control-label">分单号</label>
	                            <div class="col-sm-4">
	                               <input type="text" name="hblNo" class="form-control" value="${wlFlowImccNode.hblNo}">
	                            </div>
	                       	</div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">开航日期</label>
	                            <div class="col-sm-4"><input type="text" name="sailDate" class="form-control input-sm date-picker" value="${wlFlowImccNode.sailDate}" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
								<div class="form-group">
			                      	<label class="col-sm-2 control-label">备注<font>*</font></label>
									<div class="col-sm-10"><textarea name="remarks" class="form-control " rows="6">${wlFlowImccNode.remarks }</textarea></div>
								</div>
							</div>
	                        <div class="form-group">
								<label class="col-sm-2 control-label">状态<font>*</font></label>
								<div class="col-sm-10">
									<select class="selectpicker" name="state">
										<c:choose>
											<c:when test="${wlFlowImccNode.state==0 }">
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
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">附件</label>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${wlFlowImccNode }" delete="true" name="wlFlowImccNodeFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="wlFlowImccNodeFiles"/>
										<c:param name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
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
<script type="text/javascript">
$(document).ready(function (){
	var ids = [${selectList}];
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});
</script>
<%@ include file="/include/includeFooter.jsp" %>