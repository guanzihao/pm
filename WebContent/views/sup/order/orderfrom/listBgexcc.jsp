<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
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
						<h5>报关出口单据</h5>
					</div>
					<div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered table-hover dataTable" style="border: none;">
									<thead>
										<tr>
											<th>序号</th>
											<th>单据名称</th>
											<!-- <th>项目名称</th> -->
											<th>单据类型</th>
											<!-- <th>业务流水号</th>
											<th>报关单号</th> -->
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
													<a href="${basePath }/sup/Bgexcc/listBgExccNode/${item[8]}" >${item[0]}</a>
												</td>
												<%-- <td>${item[1]}</td> --%>
												<td>${item[2]}</td>
												<%-- <td>${item[3]}</td>
												<td>${item[4]}</td> --%>
												<td>${item[6]}</td>
												<td>${item[7]}</td>
												<td>
														<c:choose>
															<c:when test="${item[5] eq '1'}"><span class="label label-info" id="label_${item[0]}">已完成</span></c:when>
															<c:when test="${item[5] eq '0'}"><span class="label label-danger" id="label_${item[0]}">已完成</span></c:when>
															 <c:otherwise>未完成</c:otherwise>
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