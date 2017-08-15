<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑接口</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/organize/organize/saveUserAccount" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${userAccount.id}">

	                        <c:if test="${not empty interface}">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">接口名称</label>
		                            <div class="col-sm-10 control-value">${interface.name }</div>
		                        </div>
		                        <div class="hr-line-dashed"></div>
	                        </c:if>

	                        <c:if test="${empty interface}">
	                        	<div class="form-group">
		                        	<label class="col-sm-2 control-label">接口详情<font>*</font></label>
		                            <div class="col-sm-10">${interface.content}</div>
		                        </div>
		                        <div class="hr-line-dashed"></div>
		                       
							</c:if>
							
							<div class="form-group">
	                        	<label class="col-sm-2 control-label">用户名称<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="userName" class="form-control" value="${userAccount.userName }" required="" maxlength="100"></div>
	                        	<label class="col-sm-2 control-label">手机号码<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="userTel" class="form-control" value="${userAccount.userTel }" required="" maxlength="15" remote="${basePath}/organize/organize/ajaxCheckUser?userId=${userAccount.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">系统角色</label>
	                            <div class="col-sm-10">
	                            	<c:forEach items="${orgRoleList }" var="item">
	                            		<div><label><input type="checkbox" name="roleIds" id="${item.id }" value="${item.id }"> ${item.roleName }</label></div>
	                            	</c:forEach>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">所属部门</label>
	                            <div class="col-sm-10">
	                            	<c:import url="/include/includeTree.jsp">
										<c:param name="name" value="deptIds"/>
										<c:param name="value" value="${deptIds}"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
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