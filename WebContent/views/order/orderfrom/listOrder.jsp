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
						<h5>订单列表</h5>
					</div>
					<div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th>序号</th>
											<th >任务流水号</th>
											<th >订单类型</th>
											<th >任务名称</th>
											<th >最新进度</th>
											<th >节点时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageBean.pageList }" var="item"
											varStatus="status">
											<tr id="toolTr_${item[0] }">
												<td>${status.index+1 }</td>
												<td>
												
													<a href="${basePath }/task/listTaskDispose/${item[0]}/2/${item[6]}" >${item[1]}</a>
												</td>
												<td>${item[2]}</td>
												<td>${item[3]}</td>
												<td>${item[4]}</td>
												<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/></td>
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