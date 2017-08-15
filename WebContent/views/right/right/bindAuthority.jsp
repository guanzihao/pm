<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>权限绑定</h5>
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
						<div class="space-10"></div>
						<form id="pageForm" action="${basePath }/right/right/saveAuthority" method="post">
							<input type="hidden" name="token" value="${token}">
							<input type="hidden" name="id" value="${orgRole.id}">
							<input type="hidden" name="roleType" value="${roleType}">
							<div class="row">
								<div class="col-sm-12">
		                        	<c:import url="/include/includeTree.jsp">
										<c:param name="name" value="authorityIds"/>
										<c:param name="value" value="${authorityIds}"/>
										<c:param name="checkbox" value="true"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
		                        </div>
		                    </div>
		                    <div class="row">
		                    	<div class="hr-line-dashed"></div>
		                        <div class="form-group">
		                            <div class="col-sm-12">
		                                <button class="btn btn-primary" type="submit">保存</button>
		                            </div>
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