<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑角色</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/supcompany/role/saveSupRole" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${orgRole.id }">
	                        <input type="hidden" name="roleType" value="${roleType }">
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">角色代码<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="roleCode" class="form-control" value="${orgRole.roleCode }" required="" minlength="1" maxlength="50" remote="${basePath}/supcompany/role/ajaxCheckOrgRole?roleId=${orgRole.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">角色名称<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="roleName" class="form-control" value="${orgRole.roleName }" required="" minlength="1" maxlength="100"></div>
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
<%@ include file="/include/includeFooter.jsp" %>