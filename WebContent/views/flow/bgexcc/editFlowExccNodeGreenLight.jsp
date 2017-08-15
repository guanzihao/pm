<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>报关出口放行流程</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/flow/bgexcc/saveFlowExccNodeGreenLight" method="post">
	                        <input type="hidden" name="id" value="${flowExccNode.id}">
	                        <input type="hidden" name="billId" value="${billId}">
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">开始时间</label>
	                            <div class="col-sm-4"><input type="text" name="startDate" class="form-control  date-picker" value="${flowExccNode.startDate}" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">完成时间</label>
	                            <div class="col-sm-4"><input type="text" name="endDate" class="form-control date-picker"  value="${flowExccNode.endDate}" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">放行时间</label>
	                            <div class="col-sm-4"><input type="text" name="releaseDate" class="form-control  date-picker" value="${flowExccNode.releaseDate}" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
			                      	<label class="col-sm-2 control-label">供应商类型</label>
									 <div class="col-sm-4">
									      <select class="selectpicker" id="supplierType2" name="type">
									      <c:choose>
									        <c:when test="${empty flowExccNode.type}">
									            <option value="0">平台供应商</option>  
									            <option value="1">外部供应商</option>  
									        </c:when>
									        <c:otherwise>
										  		<c:if test="${flowExccNode.type==0 }">
										  		   <option value="0" selected="selected">平台供应商</option>
										  		</c:if>
										  		<c:if test="${flowExccNode.type==1 }">
										  		   <option value="1" selected="selected">外部供应商</option>
										  		</c:if>
											</c:otherwise>
										</c:choose>
									      </select>
									</div>
							</div>
							 <div id="outGroup2">
							      <div class="form-group" >
							      <label class="col-sm-2 control-label">操作公司<font>*</font></label>
									<div class="col-sm-4">
									<input type="text"  name="opeComp" class="form-control"
										value="${flowExccNode.opeComp}" required="" minlength="1"></div>
							     </div>
							</div>
							<div id="inGroup2">
							      <div class="form-group" >
			                          <label class="col-sm-2 control-label">操作一级部门</label>
									 <div class="col-sm-4">
									      <select class="selectpicker" name="firstDept">
									            <c:forEach items="${supplierList}" var="item" varStatus="status">
									               <c:choose>
															<c:when test="${item.comCode eq flowExccNode.firstDept}"> <option value="${item.id}" selected="selected">${item.comName}</option></c:when>
															<c:otherwise> <option value="${item.comCode}">${item.comName}</option></c:otherwise>
														</c:choose>
									            </c:forEach>
									      </select>
									</div>
									<label class="col-sm-2 control-label">操作二级部门</label>
									<div class="col-sm-4">
									    <select class="selectpicker" name="secDept">
									             <c:forEach items="${supplierList}" var="item" varStatus="status">
									               <c:choose>
															<c:when test="${item.comCode eq flowExccNode.firstDept}"> <option value="${item.id}" selected="selected">${item.comName}</option></c:when>
															<c:otherwise> <option value="${item.comCode}">${item.comName}</option></c:otherwise>
														</c:choose>
									            </c:forEach>
									       </select>
									</div>
							</div>
							</div>
	                        <div class="form-group">
								<label class="col-sm-2 control-label">状态<font>*</font></label>
								<div class="col-sm-10">
									<select class="selectpicker" name="status">
										<c:choose>
											<c:when test="${flowExccNode.status==0 }">
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
	                         <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">附件</label>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${flowExccNode }" delete="true" name="flowImccNodeFiles" metaColums="colums"/>
		                            	<c:import url="/include/includeUploadify.jsp">
											<c:param name="propertyName" value="flowImccNodeFiles"/>
											<c:param name="metaColums" value="colums"/>
									    </c:import>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">完成情况说明</label>
	                            <div class="col-sm-4"><input type="text" name="remarks" value="${flowExccNode.remarks}" class="form-control" minlength="1"></div>
	                        </div>
	                         <div class="form-group">
								<div class="form-group">
			                      	<label class="col-sm-2 control-label">备注<font>*</font></label>
									<div class="col-sm-10"><textarea name="note" class="form-control " rows="6">${flowExccNode.note }</textarea></div>
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