<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>栏目管理</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/cms/HelpColumns/findCMSColumns" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                	<button type="button" class="btn btn-sm button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="role_editOrgRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/cms/HelpColumns/cmsColumns',true)">
												<span><i class="fa fa-plus"></i> 添加</span>
											</a>
										</pm:auth>
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/cms/HelpColumns/removeCMSColumns', 'ids', 'removeToolTableTr', '删除')">
											<span>删除</span>
										</a>
									</div>
								</div>
								<div class="table-responsive">
									<!-- table-striped table-bordered table-hover dataTable -->
									<table class="table ">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th>操作</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.name', ${!searchBean.searchOrderType})">栏目名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.columnparent'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.columnparent', ${!searchBean.searchOrderType})">上级栏目</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.colnumDesc'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.colnumDesc', ${!searchBean.searchOrderType})">栏目描述</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.columnSort'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.columnSort', ${!searchBean.searchOrderType})">栏目顺序</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.updateDate', ${!searchBean.searchOrderType})">更新日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.columnStatus'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.columnStatus', ${!searchBean.searchOrderType})">是否启用</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>
														<pm:auth authCode="role_editOrgRole">
															<a class="" href="javascript:;" onclick="openLayer('修改','${basePath }/cms/HelpColumns/editCmsColumns/${item[0] }', true)">
																<span>修改</span>
															</a>
														</pm:auth>
														<pm:auth authCode="organize_listLoginLog">
															<a class="" href="javascript:;" onclick="openLayer('查看详情','${basePath }/cms/HelpColumns/detailCmsColumns/${item[0]}',false)">
																<span>查看详情</span>
															</a>
														</pm:auth>
													</td>
													<td>${item[1] }</td>
													
													<pm:execute id="helpColumns" bean="helpColumnsBusinImpl" method="getHelpColumns">
															<pm:execute-param type="java.lang.String" value="${item[2] }" />
													</pm:execute>
													<td>${helpColumns.name }</td>
													<td>${item[3] }</td>
													<td>${item[4] }</td>
													<td><fmt:formatDate value="${item[6]}" pattern="yyyy-MM-dd"/></td>
													<td><fmt:formatDate value="${item[7]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<c:if test="${item[5]==0 }">
															<span>启用</span>
														</c:if>
														<c:if test="${item[5]==1 }">
															<span> 禁用</span>
														</c:if>
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