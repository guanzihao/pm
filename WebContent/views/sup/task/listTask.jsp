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
						<h5>订单管理</h5>
					</div>
					<div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/sup/task/listTask"
								class="form-horizontal formValidate" method="post">
								<div class="row">
									<div class="col-sm-12">
										<label class="tables_search_label"> 关键字：<input
											type="text" class="input-sm form-control my_input" name="searchName1"
											value="${pageBean.searchBean.searchName1 }">
											<button type="button" class="btn btn-sm btn-primary button1"
												onclick="goSubmit()">查询</button>
										</label>
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.task_id'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.task_id', ${!searchBean.searchOrderType})">订单号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.tack_type'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.tack_type', ${!searchBean.searchOrderType})">订单类型</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'pcc.com_Name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('pcc.com_Name', ${!searchBean.searchOrderType})">客户公司名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.issue_date'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.issue_date', ${!searchBean.searchOrderType})">发布日期</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item"
												varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center "><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>${status.index+1 }</td>
													<td>${item[1]}</td>
													<pm:execute id="name" bean="supTaskBusinImpl" method="getTaskIdByTaskName">
														<pm:execute-param type="java.lang.String" value="${item[0]}" />
													</pm:execute>
													<td>${name}</td>
													<td>${item[4]}</td>
													<td><fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd" /></td>
													
													<td>
															<a  href="${basePath }/sup/task/listTree/${item[0]}">
																<span> 查看任务进度</span>
															</a>
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