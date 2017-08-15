<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

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
															<c:when test="${item[2] eq '接单'}">
															       接单时间:<fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '查验'}">
															       查验时间:<fmt:formatDate value="${item[4] }" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '放行'}">
															       海关放行时间:<fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
												</td>
												<td>
												
												<c:choose>
												       <c:when test="${item[2] eq '接单'}">
															<%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/bgimcc/editFlowImccNode/${item[0]}/${item[6]}',true)">
																<span><i class="fa fa-pencil"></i> 编辑</span>
															</a> --%>
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/bgimcc/viewFlowImccNode/${item[0]}/${item[6]}',true)">
																<span><i class="fa fa-pencil"></i> 查看</span>
															</a>
													   </c:when>
															<c:when test="${item[2] eq '查验'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/bgimcc/editFlowImccNodeCheck/${item[0]}/${item[6]}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/bgimcc/viewFlowImccNodeCheck/${item[0]}/${item[6]}',true)">
																        <span><i class="fa fa-pencil"></i> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '放行'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/bgimcc/editFlowImccNodeGreenLight/${item[0]}/${item[6]}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/bgimcc/viewFlowImccNodeGreenLight/${item[0]}/${item[6]}',true)">
																        <span><i class="fa fa-pencil"></i> 查看</span>
															       </a>
															</c:when>
															<c:otherwise></c:otherwise>
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