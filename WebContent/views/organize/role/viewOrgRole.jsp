<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>查看角色</h5>
	                    <div class="ibox-tools">
		                     <pm:auth authCode="role_editOrgRole">
			                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/organize/role/editOrgRole/${orgRole.id}/1">
			                          修改
			                     </a>
		                     </pm:auth>
		                 </div>
	                </div>
	                <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>角色代码</dt>
									<dd>${orgRole.roleCode }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>角色名称</dt>
									<dd>${orgRole.roleName }</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>