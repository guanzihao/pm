<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<style>
		.ibox-content{
		padding: 15px 2px 20px 2px;
	}
</style>
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
							<form id="pageForm" action="${basePath }/sup/order/OrderFrom/listSupNoReceiveOrder" class="form-horizontal formValidate" method="post">
								<input type="hidden" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
								<div class="row">
									<div class="col-sm-12">
										<label class="tables_search_label"> 
											关键字：<input type="text" class="input-sm form-control my_input" name="searchName2" value="${pageBean.searchBean.searchName2 }">
											<button type="button" class="btn btn-sm btn-primary button1"
												onclick="goSubmit()">查询</button>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 tables_search_label">
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/order/OrderFrom/saveSupReceiveOrder', 'ids', 'removeToolTableTr', '删除')">
											<span>接收</span>
										</a>
									</div>
								</div>
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th>序号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'o.order_code'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('o.order_code', ${!searchBean.searchOrderType})">订单号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'ft.flow_name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('ft.flow_name', ${!searchBean.searchOrderType})">类型</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'ci.com_Name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('ci.com_Name', ${!searchBean.searchOrderType})">客户</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'suci.comName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('suci.comName', ${!searchBean.searchOrderType})">服务商</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'o.obj_createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('o.obj_createDate', ${!searchBean.searchOrderType})">创建日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'o.node_state'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('o.node_state', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'o.node_date'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('o.node_date', ${!searchBean.searchOrderType})">最新节点</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'o.node_date'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('o.node_date', ${!searchBean.searchOrderType})">更新日期</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item"
												varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>${status.index+1 }</td>
													<td>${item[2]}</td>
													<td>${item[4]}</td>
													<td>${item[5]}</td>
													<td>
														<c:choose>
															<c:when test="${item[6]==null or item[6] eq ''}">
																无
															</c:when>
															<c:otherwise>
																${item[6]}
															</c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[1]}" pattern="yyyy-MM-dd" /></td>
													
													<td>
														<span >未接收</span>
													</td>
													
													<td>
														<c:choose>
															<c:when test="${item[8]==null or item[8] eq ''}">
																无
															</c:when>
															<c:otherwise>
																${item[8]}
															</c:otherwise>
														</c:choose>
													
													</td>
													<td>
														<c:choose>
															<c:when test="${item[7]==null or item[7] eq ''}">
																无
															</c:when>
															<c:otherwise>
																${item[7]}
															</c:otherwise>
														</c:choose>
													</td>
													<td>
															<a  href="${basePath }/sup/order/OrderFrom/toPageDetailUrl/${item[0]}">
																<span> 查看详情</span>
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