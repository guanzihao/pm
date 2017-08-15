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
															<c:when test="${item[2] eq '单证制作'}">
															       合同生效时间:<fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '信用证开证'}">
															               信用证开证生效时间:<fmt:formatDate value="${item[4] }" pattern="yyyy-MM-dd"/>
															     
															</c:when>
															<c:when test="${item[2] eq '收汇'}">
															       货款分拆确认时间:<fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/>
															     <p> 收款金额:${item[6]}</p>
															     <p>币种:${item[7]}</p>
															</c:when>
															<c:when test="${item[2] eq '出口清关'}">
															       出运单生效时间:<fmt:formatDate value="${item[8]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '结算开票'}">
															     结算单生效时间:<fmt:formatDate value="${item[9]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '退税申请'}">
															       退税申请单生效时间:<fmt:formatDate value="${item[10]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
												</td>
												<td>
												<c:choose>
												       <c:when test="${item[2] eq '单证制作'}">
															<%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/wmexcc/editDocsProduce/${item[0]}/${billId}',true)">
																<span><i class="fa fa-pencil"></i> 编辑</span>
															</a> --%>
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/wmexcc/viewDocsProduce/${item[0]}',true)">
																<span><i class="fa fa-pencil"></i> 查看</span>
															</a>
													   </c:when>
															<c:when test="${item[2] eq '信用证开证'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/wmexcc/editCreditCard/${item[0]}/${billId}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/wmexcc/viewCreditCard/${item[0]}',true)">
																        <span><i class="fa fa-pencil"></i> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '收汇'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/wmexcc/editExchangeEarnings/${item[0]}/${billId}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/wmexcc/saveExchangeEarnings/${item[0]}',true)">
																        <span><i class="fa fa-pencil"></i> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '出口清关'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/wmexcc/editExportCleaning/${item[0]}/${billId}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/wmexcc/viewExportCleaning/${item[0]}',true)">
																        <span><i class="fa fa-pencil"></i> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '结算开票'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/wmexcc/editSettlementInvoice/${item[0]}/${billId}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/wmexcc/viewSettlementInvoice/${item[0]}',true)">
																        <span><i class="fa fa-pencil"></i> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '退税申请'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('编辑','${basePath }/flow/wmexcc/editTaxRefund/${item[0]}/${billId}',true)">
																        <span><i class="fa fa-pencil"></i> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('查看','${basePath }/flow/wmexcc/viewTaxRefund/${item[0]}',true)">
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