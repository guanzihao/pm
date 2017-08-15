<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<style>
		.ibox-content{
		padding: 15px 2px 20px 2px;
	}
	a{
		color: blue;
	}
</style>
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
											<th >订单号</th>
											<th >订单类型</th>
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
													${item[7]}
												</td>
												<td>
													${item[2]}
												</td>
												<td>
													<pm:execute id="bgFlowExcc" bean="bgFlowExccBusinImpl" method="getFlowNode">
																			<pm:execute-param type="java.lang.String" value="${item[4]}" />
												</pm:execute>
												${bgFlowExcc.nodeName }
												</td>
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