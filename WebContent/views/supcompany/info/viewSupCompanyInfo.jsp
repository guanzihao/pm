<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>供应商信息</h5>
						<div class="ibox-tools">
		                     <pm:auth authCode="info_editCompanyInfo">
			                     <a class="btn btn-xs btn-white myBtn" href="${basePath }/supcompany/info/editSupCompanyInfo/${SupCompanyInfo.id}">
			                         <i class=""></i> 修改
			                     </a>
		                     </pm:auth>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${SupCompanyInfo.comState eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${SupCompanyInfo.comState eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${SupCompanyInfo.comState eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商名称</dt>
									<dd>${SupCompanyInfo.comName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商编号</dt>
									<dd>${SupCompanyInfo.comCode }</dd>
								</dl>
							</div>
							
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商邮箱</dt>
									<dd>${SupCompanyInfo.comEmail }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商电话</dt>
									<dd>${SupCompanyInfo.comTel }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>供应商类别</dt>
									<dd>${enumitems.name }</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
	        </div>
	    </div>
	 	  <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>联系人信息</h5>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="dataTables_wrapper form-inline">
		                        <form id="pageForm" method="post">
			                        <div class="row">
										<div class="col-sm-12 tables_search_label">
											<pm:auth authCode="info_editCompanyInfoUser">
												<a class="btn btn-white btn-sm myBtn" href="${basePath }/supcompany/info/editSupCompanyInfoUser/${SupCompanyInfo.id}/0">
													<span><i class=""></i> 添加</span>
												</a>
											</pm:auth>
											
											<pm:auth authCode="info_enableCompanyInfoUser">
												<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/supcompany/info/enableSupCompanyInfoUser', 'ids', 'passToolTable', '启用')">
													<span><i class=""></i>通过</span>
												</a>
											</pm:auth>
											<pm:auth authCode="info_disableCompanyInfoUser">
												<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/supcompany/info/disableSupCompanyInfoUser', 'ids', 'rejectToolTable', '禁用')">
													<span><i class=""></i>反驳</span>
												</a>
											</pm:auth>
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
												<c:forEach items="${SupCompanyInfo.companyInfoUsers }" var="item" varStatus="status">
													<tr id="toolTr_${item.id}">
														<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item.id }"></td>
														<td>${item.userMail }</td>
														<td>${item.userName }</td>
														<td>${item.userNumber }</td>
														<td>${item.userTel }</td>
														<td>${item.userFax }</td>
														<td>
															<c:if test="${item.userAdmin }">是</c:if>
														</td>
														<td>
															<c:choose>
																<c:when test="${item.userState eq 'W'}"><span class="label label-warning" id="label_${item.id}">待审核</span></c:when>
																<c:when test="${item.userState eq 'Y'}"><span class="label label-info" id="label_${item.id}">启用</span></c:when>
																<c:when test="${item.userState eq 'N'}"><span class="label label-danger" id="label_${item.id}">禁用</span></c:when>
																<c:otherwise><span class="label label-danger" id="label_${item.id}">禁用</span></c:otherwise>
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
															<pm:auth authCode="info_editCompanyInfoUser">
																<a class="" href="${basePath }/supcompany/info/editSupCompanyInfoUser/${SupCompanyInfo.id}/${item.id }">
																	<span><i class=""></i> 修改</span>
																</a>
																<a class="" href="${basePath }/supcompany/info/editSupCompanyInfoUserPwd/${SupCompanyInfo.id}/${item.id }">
																	<span><i class=""></i> 修改密码</span>
																</a>
																<a class="" href="${basePath }/supcompany/info/saveSupCompanyInfoUserAdmin/${SupCompanyInfo.id}/${item.id }">
																	<span><i class=""></i> 设置为主联系人</span>
																</a>
															</pm:auth>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</form>
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