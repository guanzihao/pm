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
						<h5>仓库进库单据</h5>
					</div>
					<div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th>序号</th>
											<th>单据名称</th>
											<th>项目名称</th>
											<th>单据类型</th>
											<th>出库单据</th>
											<th>节点时间</th>
											<th>目前最新节点</th>
											<th>状态</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageBean.pageList }" var="item"
											varStatus="status">
											<tr id="toolTr_${item[0] }">
												<td>${status.index+1 }</td>
												<td>
													<a href="${basePath }/Ccimcc/listCcImccNode/${item[7]}" >${item[0]}</a>
												</td>
												<td>${item[1]}</td>
												<td>${item[2]}</td>
												<td>${item[3]}</td>
												<td>${item[5]}</td>
												<td>${item[6]}</td>
												<td>
														<c:choose>
															<c:when test="${item[4] eq '0'}"><span class="label label-warning" id="label_${item[0]}">未完成</span></c:when>
															<c:when test="${item[4] eq '1'}"><span class="label label-info" id="label_${item[0]}">已完成</span></c:when>
														</c:choose>
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
</body>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>