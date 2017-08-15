<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<style>
<!--
	*{
		font-size:14px!important;
	}
	
	dt{
		font-weight: 400 !important;
	}
	
	.col-sm-12{
		padding-left:50px;
	}
	
	dl{
		margin-top: 20px;
	}
-->
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>基本信息</h5>
						<div class="ibox-tools">
							<wf:state code="${userAccount.userState }">
			                    <a class="btn btn-xs btn-white myBtn" href="javascript:;" onclick="openLayer('修改信息','${basePath }/organize/user/editUserAccount',true)">
			                         修改信息
			                    </a>
			                </wf:state>
		                    <a class="btn btn-xs btn-white myBtn" href="javascript:;" onclick="openLayer('修改密码','${basePath }/organize/user/editUserAccountPwd',false)">
		                         修改密码
		                    </a>
		                    <a class="btn btn-xs btn-white myBtn" href="javascript:;" onclick="openLayer('登录日志','${basePath }/organize/user/listLoginLog',false)">
		                        登录日志
		                    </a>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>邮箱</dt>
									<dd>${userAccount.userMail }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>用户名称</dt>
									<dd>${userAccount.userName }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>手机</dt>
									<dd>${userAccount.userTel }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>所属部门</dt>
									<dd>${orgDeptUser.orgDept.deptName }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>系统角色</dt>
									<dd>
										<c:forEach items="${orgRoleUserList }" var="item">
											${item.orgRole.roleName }&nbsp;
										</c:forEach>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${userAccount.userState eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${userAccount.userState eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${userAccount.userState eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
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