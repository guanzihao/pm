<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>添加接口配置</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal formValidate"
							action="${basePath}/inter/interConfig/saveInterfaceConfig"  method="post">
							<input type="hidden" name="id" value="${id}"/>
							<input type="hidden" name="suppersId"value=""/>
							<input type="hidden" name="companyId"value=""/>
							<div class="form-group">
	                        	<label class="col-sm-2 control-label">实体类型<font>*</font></label>
	                            <div class="col-sm-4">
	                                 <select class="selectpicker" name="accountTypeId" id="accountSeletor">
	                                    <c:forEach items="${accountType}" var="item" varStatus="status">
	                                                   <c:choose>
															<c:when test="${ editInterConfig.accountTypeId eq item.id}"> <option value="${item.id}" selected="selected">${item.name}</option></c:when>
															<c:otherwise> <option value="${item.id}">${item.name}</option></c:otherwise>
														</c:choose>
	                                    </c:forEach>
	                                 </select>
	                            </div>
	                            <label class="col-sm-2 control-label">接口<font>*</font></label>
	                            <div class="col-sm-4">
	                                <select class="selectpicker" name="intefaceId">
	                                    <c:forEach items="${interfaces}" var="item" varStatus="status">
	                                          
	                                                   <c:choose>
															<c:when test="${item.id eq editInterConfig.intefaceId}"> <option value="${item.id}" selected="selected">${item.intername}</option></c:when>
															<c:otherwise><option value="${item.id}">${item.intername}</option></c:otherwise>
														</c:choose>
	                                    </c:forEach>
	                                 </select>
	                            </div>
	                        </div>
							 <div class="form-group">
							    <div id="supperDiv">
	                        	    <label class="col-sm-2 control-label">供应商<font>*</font></label>
	                                    <div class="col-sm-4">
	                                       <select class="selectpicker" id="supperSelector" >
	                                           <c:forEach items="${sups}" var="item" varStatus="status">
	                                                   <c:choose>
															<c:when test="${item.id eq editInterConfig.suppersId}"> <option value="${item.id}" selected="selected">${item.comName}</option></c:when>
															<c:otherwise><option value="${item.id}">${item.comName}</option></c:otherwise>
														</c:choose>
	                                    </c:forEach>
	                                 </select>
	                               </div>
	                            </div>
	                             <div id="companyDiv">
	                                 <label class="col-sm-2 control-label">客户<font>*</font></label>
	                            <div class="col-sm-4" >
									<select class="selectpicker" id="companySelector">
	                                    <c:forEach items="${companys}" var="item" varStatus="status">
	                                           <c:choose>
													<c:when test="${item.id eq editInterConfig.companyId}"> <option value="${item.id}" selected="selected">${item.comName}</option></c:when>
													<c:otherwise><option value="${item.id}">${item.comName}</option></c:otherwise>
											 </c:choose>
	                                    </c:forEach>
	                                 </select>  
								</div>
	                                 </div>
	                              </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">接口地址<font>*</font></label>
	                            <div class="col-sm-4">
	                               <input type="text" name="interfaceUrl" class="form-control"
										value="${editInterConfig.interfaceUrl}" required="" minlength="1">
	                            </div>
	                           <label class="col-sm-2 control-label">接口连接方式</label>
	                            <div class="col-sm-4" >
									<select class="selectpicker" name="joinType">
	                                    <c:forEach items="${enumitems}" var="item" varStatus="status">
	                                           <c:choose>
													<c:when test="${item.id eq editInterConfig.joinType}"> <option value="${item.id}" selected="selected">${item.name}</option></c:when>
													<c:otherwise><option value="${item.id}">${item.name}</option></c:otherwise>
											 </c:choose>
	                                    </c:forEach>
	                                 </select>  
								</div>
	                         </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">开启日期<font>*</font></label>
	                            <div class="col-sm-4">
	                               <input type="text" class="form-control input-sm date-picker" required=""name="startTime" value="${editInterConfig.startDate}"/>
	                            </div>
	                           <label class="col-sm-2 control-label">结束日期<font>*</font></label>
	                            <div class="col-sm-4" >
									 <input type="text" class="form-control input-sm date-picker" required="" name="endTime" value="${editInterConfig.endDate}"/>
								</div>
	                         </div>
	                          <div class="form-group">
	                        	<label class="col-sm-2 control-label">显示顺序<font>*</font></label>
	                            <div class="col-sm-4">
	                                 <input type="text" name="sort" value="${editInterConfig.sort}" class="form-control"  maxlength="10">
	                            </div>
	                           <label class="col-sm-2 control-label">是否启用<font>*</font></label>
	                            <div class="col-sm-4" >
									<select class="selectpicker" name="status">
									  <c:if test="${empty editInterConfig.status}">
									  <option value="0">启用</option>
									  <option value="1">禁用</option>
									  </c:if>
									  <c:if test="${editInterConfig.status==0 }">
											  <option value="0"  selected="selected">启用</option>
											  <option value="1">禁用</option>
										</c:if>
										<c:if test="${editInterConfig.status==1 }">
											  <option value="0" >启用</option>
											  <option value="1" selected="selected">禁用</option>
										</c:if>
									</select>  
								</div>
	                         </div>
	                          <div class="form-group">
	                               <label class="col-sm-2 control-label">备注信息</label>
	                            <div class="col-sm-4">
									<input type="text" name="content" class="form-control"
										value="${editInterConfig.content}" required="" minlength="1"
										maxlength="100">
								</div>
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
<script type="text/javascript">
           var accountType=$("#accountSeletor").text();
           if(accountType=="客户"){
              $("#companyDiv").show();
              $("#supperDiv").hide();
           }else{
              $("#companyDiv").hide();
              $("#supperDiv").show();
           }
          
      $("#accountSeletor").change(function () {
       var accountType=$("#accountSeletor").find("option:selected").text();
       if(accountType=="客户"){
           $("#companyDiv").show();
           $("#supperDiv").hide();
           $("input[name='suppersId']").val($("#supperSelector").val());
       }else if(accountType=="供应商"){
            $("#companyDiv").hide();
            $("#supperDiv").show();
            $("input[name='companyId']").val($("#companySelector").val());
       }
 });
 
</script>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>