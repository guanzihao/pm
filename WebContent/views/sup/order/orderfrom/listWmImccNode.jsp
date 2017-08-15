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
															<c:when test="${item[2] eq '单证制作'}">
															       合同生效时间:<fmt:formatDate value="${item[6]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '信用证开证'}">
															               信用证开证生效时间:<fmt:formatDate value="${item[5] }" pattern="yyyy-MM-dd"/>
															     
															</c:when>
															<c:when test="${item[2] eq '收货款'}">
															       货款分拆确认时间:<fmt:formatDate value="${item[11]}" pattern="yyyy-MM-dd"/>
															     <p> 收款金额:${item[12]}</p>
															     <p>币种:${item[9]}</p>
															</c:when>
															<c:when test="${item[2] eq '付货款'}">
															       实付时间:<fmt:formatDate value="${item[7]}" pattern="yyyy-MM-dd"/>
															   <p>付货款金额:${item[8]}</p>
															   <p>币种:${item[10]}</p>
															</c:when>
															<c:when test="${item[2] eq '进口到货'}">
															       到货生效时间:<fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '进口清关'}">
															      清关生效时间:<fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:when test="${item[2] eq '业务结算'}">
															       结算单生效时间:<fmt:formatDate value="${item[13]}" pattern="yyyy-MM-dd"/>
															</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
												</td>
												<td>
												<pm:authCom authCode="organize_editInterface">
												<c:choose>
												       <c:when test="${item[2] eq '单证制作'}">
															<%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editDocsProduce/${item[0]}/${billId}',true,'600px','500px')">
																<span> 编辑</span>
															</a> --%>
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewDocsProduce/${item[0]}',true,'600px','500px')">
																<span> 查看</span>
															</a>
													   </c:when>
															<c:when test="${item[2] eq '信用证开证'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editCreditCard/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewCreditCard/${item[0]}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '收货款'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editReceipts/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewReceipts/${item[0]}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '付货款'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editPayments/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewPayments/${item[0]}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '进口到货'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editArrivals/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewArrivals/${item[0]}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '进口清关'}">
															       <%-- <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editCleaning/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewCleaning/${item[0]}',true,'600px','500px')">
																        <span> 查看</span>
															       </a>
															</c:when>
															<c:when test="${item[2] eq '业务结算'}">
															      <%--  <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('编辑','${basePath }/sup/flow/wmimcc/editSettlement/${item[0]}/${billId}',true,'600px','500px')">
																        <span> 编辑</span>
															       </a> --%>
															       <a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/flow/wmimcc/viewSettlement/${item[0]}',true,'600px','500px')">
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