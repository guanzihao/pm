<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑联系人</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white" href="${basePath }/sup/supcompany/supcompanyuser/listSupCompanyInfoUser">
		                         <i class="fa fa-share"></i> 返回
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/supcompany/supcompanyuser/saveSupCompanyInfoUser" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${companyInfoUser.id}">

							<div class="form-group">
	                        	<label class="col-sm-2 control-label">邮箱(登录名)<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="userMail" class="form-control" value="${companyInfoUser.userMail }" required="" email="true" minlength="5" maxlength="100" remote="${basePath}/sup/supcompany/supcompanyuser/ajaxCheckSupCompanyInfoUser?id=${companyInfoUser.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>

	                        <c:if test="${empty companyInfoUser}">
		                        <div class="form-group">
		                        	<label class="col-sm-2 control-label">密码<font>*</font></label>
		                            <div class="col-sm-4"><input type="password" id="loginPwd" name="loginPwd" class="form-control" required="" minlength="6" maxlength="50"></div>
		                        	<label class="col-sm-2 control-label">确认密码<font>*</font></label>
		                            <div class="col-sm-4"><input type="password" id="loginPwd2" name="loginPwd2" class="form-control" required="" minlength="6" maxlength="50" equalTo="#loginPwd"></div>
		                        </div>
		                        <div class="hr-line-dashed"></div>
		                    </c:if>

	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">联系人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="userName" class="form-control" value="${companyInfoUser.userName }" required="" maxlength="100"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">手机号码<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="userTel" class="form-control" value="${companyInfoUser.userTel }" required="" maxlength="15" digits="true" remote="${basePath}/sup/supcompany/supcompanyuser/ajaxCheckSupCompanyInfoUser?id=${companyInfoUser.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">角色<font>*</font></label>
	                            <div class="col-sm-10">
	                            	<c:forEach items="${orgRoleList }" var="orgRole" varStatus="status">
	                            		<div><label><input type="radio" <c:if test="${comRoleUser.orgRole.id == orgRole.id}">checked="checked"</c:if> value="${orgRole.id }" name="orgRoleId" required="">${orgRole.roleName }</label></div>
									</c:forEach>
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
<%@ include file="/include/includeFooter.jsp" %>