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
							<form id="pageForm" action="${basePath }/company/role/listComRole" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <span class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                </span>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="comrole_editComRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/company/role/editComRole/0/2',true)">
												<span> 添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="comrole_removeComRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/company/role/removeComRole', 'ids', 'removeToolTableTr', '删除')">
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
														<pm:auth authCode="comrole_editComRole">
															<a class="" href="javascript:;" onclick="openLayer('修改','${basePath }/company/role/editComRole/${item[0] }/${item[3]}', true)">
																<span>修改</span>
															</a>
														</pm:auth>
														<pm:auth authCode="right_bindAuthorityCom">
															<a class="" href="javascript:;" onclick="openLayer('权限绑定','${basePath }/right/right/bindAuthorityCom/${item[0] }/2', false)">
																<span> 权限绑定</span>
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