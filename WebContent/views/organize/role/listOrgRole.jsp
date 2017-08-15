<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>角色管理</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/organize/role/listOrgRole" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="role_editOrgRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/organize/role/editOrgRole/0/1',true)">
												<span> 添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="role_removeOrgRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/organize/role/removeOrgRole', 'ids', 'removeToolTableTr', '删除')">
												<span> 删除</span>
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.roleCode'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.roleCode', ${!searchBean.searchOrderType})">代码</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.roleName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.roleName', ${!searchBean.searchOrderType})">名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">添加时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>${item[1] }</td>
													<td>${item[2] }</td>
													<td><fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<pm:auth authCode="role_editOrgRole">
															<a class="" href="javascript:;" onclick="openLayer('修改','${basePath }/organize/role/editOrgRole/${item[0] }/${item[3]}', true)">
																<span> 修改</span>
															</a>
														</pm:auth>
														<pm:auth authCode="right_bindAuthority">
															<a class="" href="javascript:;" onclick="openLayer('权限绑定','${basePath }/right/right/bindAuthority/${item[0] }/1', false)">
																<span>权限绑定</span>
															</a>
														</pm:auth>
														<pm:auth authCode="role_listOrgRoleUser">
															<a class="" href="javascript:;" onclick="openLayer('维护角色人员','${basePath }/organize/role/editOrgRoleUser/${item[0] }', false)">
																<span> 维护角色人员</span>
															</a>
														</pm:auth>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<%@ include file="/include/includePage.jsp" %>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>