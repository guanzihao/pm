<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑角色人员关系</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/organize/role/saveOrgRoleUser" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="roleId" value="${orgRole.id}">

	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">角色编号</label>
	                            <div class="col-sm-10 control-value">${orgRole.roleCode }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">角色名称</label>
	                            <div class="col-sm-10 control-value">${orgRole.roleName }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">人员列表</label>
	                            <div class="col-sm-10">
	                            	<select multiple="multiple" size="15" name="userIds" class="duallistbox">
										<c:forEach items="${userAccountList }" var="item">
											<pm:execute id="check" bean="organizeBusinImpl" method="checkOrgRoleUser">
												<pm:execute-param type="java.util.List" value="${orgRoleUserList }"/>
												<pm:execute-param type="com.pm.organize.bean.UserAccount" value="${item }"/>
											</pm:execute>
											<option value="${item.id }" <c:if test="${check }">selected="selected"</c:if>>${item.userMail } [${item.userName }]</option>
										</c:forEach>
									</select>
									<c:import url="/include/includeDuallistbox.jsp" />
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
<%@ include file="/include/includeFooter.jsp" %>