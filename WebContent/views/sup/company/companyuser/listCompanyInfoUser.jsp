<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
.float-e-margins .btn {
    margin-bottom: 3px; 
}
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>联系人信息</h5>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="dataTables_wrapper form-inline">
		                        <div class="row">
									<div class="col-sm-12 tables_search_label" style="margin-bottom:5px;">
										<pm:authCom authCode="company_editCompanyInfoUser">
											<a class="btn btn-white btn-sm myBtn" href="${basePath }/sup/company/companyuser/editCompanyInfoUser/0">
												<span> 添加</span>
											</a>
										</pm:authCom>
										<pm:authCom authCode="company_enableCompanyInfoUser">	
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/company/companyuser/enableCompanyInfoUser', 'ids', 'passToolTable', '启用')">
												<span>启用</span>
											</a>
										</pm:authCom>
										<pm:authCom authCode="company_disableCompanyInfoUser">	
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/company/companyuser/disableCompanyInfoUser', 'ids', 'rejectToolTable', '禁用')">
												<span>禁用</span>
											</a>
										</pm:authCom>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th>邮箱(登录名)</th>
												<th>联系人</th>
												<th>联系电话</th>
												<th>手机号码</th>
												<th>传真</th>
												<th>主联系人</th>
												<th>状态</th>
												<th>角色</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${companyInfo.companyInfoUsers }" var="item" varStatus="status">
												<tr id="toolTr_${item.id}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item.id }"></td>
													<td>${item.userMail }</td>
													<td>${item.userName }</td>
													<td>${item.userNumber }</td>
													<td>${item.userTel }</td>
													<td>${item.userFax }</td>
													<td>
														<c:if test="${item.userAdmin }">是</c:if>
														<c:if test="${!item.userAdmin }">否</c:if>
													</td>
													<td>
														<c:choose>
															<c:when test="${item.userState eq 'W'}"><span class="label label-warning shenghe" id="label_${item.id}">待审核</span></c:when>
															<c:when test="${item.userState eq 'Y'}"><span class="label label-info qiyong" id="label_${item.id}">启用</span></c:when>
															<c:when test="${item.userState eq 'N'}"><span class="label label-danger jinyong" id="label_${item.id}">禁用</span></c:when>
															<c:otherwise><span class="label label-danger jinyong" id="label_${item.id}">禁用</span></c:otherwise>
														</c:choose>
													</td>
													<td>
														<pm:execute id="comRoleUserList" bean="companyInfoBusinImpl" method="getComRoleUserList">
															<pm:execute-param type="com.pm.company.bean.CompanyInfoUser" value="${item }"/>
														</pm:execute>
														<c:forEach items="${comRoleUserList }" var="comRoleUser" varStatus="status">
															${comRoleUser.orgRole.roleName }
														</c:forEach>
													</td>
													<td>
														<pm:authCom authCode="company_editCompanyInfoUser">
															<a class="" href="${basePath }/sup/company/companyuser/editCompanyInfoUser/${item.id }">
																<span> 修改</span>
															</a>
														</pm:authCom>
														<pm:authCom authCode="company_editCompanyInfoUserPwd">
															<a class="" href="${basePath }/sup/company/companyuser/editCompanyInfoUserPwd/${item.id }">
																<span>修改密码</span>
															</a>
														</pm:authCom>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
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