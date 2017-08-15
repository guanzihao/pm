<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>任务管理</h5>
					</div>
					<div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/task/listTask"
								class="form-horizontal formValidate" method="post">
								<div class="row">
									<div class="col-sm-12">
										<span class="tables_search_label"> 关键字：<input
											type="text" class="input-sm form-control" name="searchName1"
											value="${pageBean.searchBean.searchName1 }">
											<button type="button" class="btn btn-sm btn-primary button1"
												onclick="goSubmit()">查询</button>
										</span>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="role_editOrgRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;"
												onclick="openLayer('添加','${basePath }/task/initSzveTask/0',true)">
												<span>添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="role_removeOrgRole">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;"
												onclick="toolTable('${basePath }/task/removeTask', 'ids', 'removeToolTableTr', '删除')">
												<span> 删除</span>
											</a>
										</pm:auth>
									</div>
								</div>
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center">
														<input type="checkbox" class="checkbox"
															onclick="checkAll(this, 'ids')">
													</div>
												</th>
												<th>序号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.taskName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.taskName', ${!searchBean.searchOrderType})">任务名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.issueDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.issueDate', ${!searchBean.searchOrderType})">日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.isIssue'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.isIssue', ${!searchBean.searchOrderType})">状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item"
												varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center "><input type="checkbox"
														class="checkbox" name="ids" value="${item[0] }"></td>
													<td>${status.index+1 }</td>
													<td>${item[1]}</td>
													<td><fmt:formatDate value="${item[2]}" pattern="yyyy-MM-dd" /></td>
													<td>
														<c:if test="${item[3]=='0'}">
															<span class="label label-warning">未提交</span>
														</c:if> <c:if test="${item[3]=='1'}">
															<span class="label label-info">已提交</span>
														</c:if>
														<c:if test="${item[3]=='3' }">
															<span class=" label label-info"> 已分配</span>
														</c:if>
														<c:if test="${item[3]=='2' }">
															<span class=" label label-warning"> 分配中</span>
														</c:if>
													</td>
													<td>
														<c:choose>
															<c:when test="${item[3]==0}">
																<pm:auth authCode="role_editOrgRole">
																	<a class="" href="javascript:;"
																		onclick="openLayer('提交','${basePath }/task/submitTask/${item[0]}', true)">
																		<span><i class=""></i> 提交</span>
																	</a>
																</pm:auth>
															</c:when>
															<c:otherwise>
																<pm:auth authCode="organize_listLoginLog">
																	<a class="" href="javascript:;" onclick="openLayer('查看详情','${basePath }/task/viewTask/${item[0]}',false)">
																		<span> <i class=""></i> 查看详情</span>
																	</a>
																</pm:auth> 
																<pm:auth authCode="organize_listLoginLog">
																	<a class="" href="${basePath }/task/listTree/${item[0]}">
																		<span> <i class=""></i> 查看任务进度</span>
																	</a>
																</pm:auth>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<%@ include file="/include/includePage.jsp"%>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>