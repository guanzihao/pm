<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>服务商报关业务委托报价管理</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/pm/consignationQuote/listCcConsignationQuote"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                               		<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/pm/consignationQuote/saveSupplierConsignationQuote', 'ids', 'removeToolTableTr', '通过')">
												<span> 通过</span>
											</a>
									</div>
								</div>
		                        
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.supplierType'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.supplierType', ${!searchBean.searchOrderType})">类型</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.site'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.site', ${!searchBean.searchOrderType})">仓库地点</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.price'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.price', ${!searchBean.searchOrderType})">价格</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.checkState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.checkState', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>${item[1] }</td>
													<td>${item[2] }</td>
													<td>${item[7] }</td>
													<td>
														<c:if test="${item[10]=='1' }">
															<span class=" label label-info tongguo"> 已审核</span>
														</c:if>
														<c:if test="${item[10]=='0' }">
															<span class=" label label-warning shenghe"> 未审核</span>
														</c:if>
													</td>
													<td><fmt:formatDate value="${item[9]}" pattern="yyyy-MM-dd"/></td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<%@ include file="/include/includePage.jsp" %>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>