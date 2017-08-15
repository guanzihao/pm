<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑机构[部门]</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/organize/dept/listOrgDept">
		                         返回列表
		                     </a>
		                 </div>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/organize/dept/saveOrgDept" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="parentId" value="${parent.id}">
							<input type="hidden" name="id" value="${orgDept.id}">

	                        <c:if test="${not empty parent}">
		                        <div class="form-group">
		                        	<label class="col-sm-2 control-label">上级机构[部门]编号</label>
		                            <div class="col-sm-10 control-value">${parent.deptCode }</div>
		                        </div>
		                        <div class="hr-line-dashed"></div>
		                        <div class="form-group">
		                        	<label class="col-sm-2 control-label">上级机构[部门]名称</label>
		                            <div class="col-sm-10 control-value">${parent.deptName }</div>
		                        </div>
		                        <div class="hr-line-dashed"></div>
		                    </c:if>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">机构[部门]编号<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="deptCode" class="form-control" value="${orgDept.deptCode }" required="" minlength="1" maxlength="50" remote="${basePath}/organize/dept/ajaxCheckOrgDept?deptId=${orgDept.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">机构[部门]名称<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="deptName" class="form-control" value="${orgDept.deptName }" required="" minlength="1" maxlength="100"></div>
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