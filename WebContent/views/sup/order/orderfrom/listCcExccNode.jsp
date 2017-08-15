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
					<div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th>序号</th>
											<th>操作环节</th>
											<th>状态</th>
											<th>关键信息</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageBean.pageList}" var="item"
											varStatus="status">
											<tr id="toolTr_${status.index+1 }">
												<td>${status.index+1 }</td>
												<td>${item[2]}</td>
												<td>
												        <c:choose>
															<c:when test="${item[1] eq '1'}">已完成</c:when>
															<c:when test="${item[1] eq '0'}">进行中</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
												</td>
												<td>
												      <c:choose>
															<c:when test="${item[2] eq '接单登记'}">
															       接单时间:<fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '出库'}">
															      <p> 司机签收时间:<fmt:formatDate value="${item[5] }" pattern="yyyy-MM-dd"/></p>
															      <p>送货地址:${item[6]}</p>
															</c:when>
															<c:when test="${item[2] eq '签收'}">
															       签收时间:<fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
												</td>
												<td>
												<pm:authCom authCode="organize_editInterface">
												<c:choose>
												       <c:when test="${item[2] eq '接单登记'}">
															<%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/ccexcc/editCcFlowExccNode/${item[0]}/${billId}',true,'600px','500px')">
																<span> 编辑</span>
															</a> --%>
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/ccexcc/viewCcFlowExccNode/${item[0]}/${billId}',true,'600px','500px')">
																<span> 查看</span>
															</a>
													   </c:when>
															<c:when test="${item[2] eq '出库'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/ccexcc/editCcFlowExccNodeExport/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/ccexcc/viewCcFlowExccNodeExport/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '签收'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/ccexcc/editCcFlowExccNodeSigning/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/ccexcc/viewCcFlowExccNodeSigning/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
												
															
														</pm:authCom>
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