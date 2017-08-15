<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />


<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑角色</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/company/role/saveComRole" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${orgRole.id }">
	                        <input type="hidden" name="roleType" value="${roleType }">
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-label"><i class="ired">*</i>角色代码</span>
	                            <div class="col-sm-10"><input type="text" name="roleCode" class="form-control" value="${orgRole.roleCode }" required="" minlength="1" maxlength="50" remote="${basePath}/company/role/ajaxCheckOrgRole?roleId=${orgRole.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-label"><i class="ired">*</i>角色名称</span>
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