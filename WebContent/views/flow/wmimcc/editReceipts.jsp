<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>收货款流程</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/flow/wmimcc/saveReceipts" method="post">
	                        <input type="hidden" name="id" value="${wmFlowImccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">开始时间</label>
	                            <div class="col-sm-4"><input type="text" name="startDate" class="form-control input-sm date-picker" value="${wmFlowImccNode.startDate}" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">结束时间</label>
	                            <div class="col-sm-4"><input type="text" name="endDate" class="form-control input-sm date-picker"  value="${wmFlowImccNode.endDate}" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">货款分拆确认时间</label>
	                            <div class="col-sm-4"><input type="text" name="divDate" class="form-control input-sm date-picker" value="${wmFlowImccNode.divDate}" maxlength="200"></div>
	                        	<label class="col-sm-2 control-label">收货款金额</label>
	                            <div class="col-sm-4"><input type="text" name="recAmount" class="form-control" value="${wmFlowImccNode.recAmount}" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">币种</label>
	                            <div class="col-sm-4"><input type="text" name="recCurrency" class="form-control" value="${wmFlowImccNode.recCurrency}" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
								<div class="form-group">
			                      	<label class="col-sm-2 control-label">备注<font>*</font></label>
									<div class="col-sm-10"><textarea name="remarks" class="form-control " rows="6">${wmFlowImccNode.remarks }</textarea></div>
								</div>
							</div>
	                        <div class="form-group">
								<label class="col-sm-2 control-label">状态<font>*</font></label>
								<div class="col-sm-10">
									<select class="selectpicker" name="state">
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
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">附件</label>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${wmFlowImccNode }" delete="true" name="wmFlowImccNodeFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="wmFlowImccNodeFiles"/>
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