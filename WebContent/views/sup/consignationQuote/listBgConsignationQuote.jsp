<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

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
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sup/consignationQuote/listSupplierConsignationQuote/1"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                               		<button type="button" class="btn btn-sm btn-primary button1"  onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayerSize('添加','${basePath }/sup/consignationQuote/editSupplierConsignationQuote/0/${comType }',true,'800px','600px')">
												<span>添加</span>
											</a>
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/consignationQuote/removeSupplierConsignationQuote', 'ids', 'removeToolTableTr', '删除')">
												<span>删除</span>
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.site'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.site', ${!searchBean.searchOrderType})">地点</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.price'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.price', ${!searchBean.searchOrderType})">价格</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.checkState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.checkState', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建时间</th>
												<th>操作</th>
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
															<span class=" label label-info"> 已审核</span>
														</c:if>
														<c:if test="${item[10]=='0' }">
															<span class=" label label-warning"> 未审核</span>
														</c:if>
														<c:if test="${item[10]=='-1' }">
															<span class=" label label-danger"> 未提交</span>
														</c:if>
													</td>
													<td><fmt:formatDate value="${item[9]}" pattern="yyyy-MM-dd"/></td>
													
													<td>
														<c:if test="${item[10] eq '-1'}">
															<a class="" href="javascript:;" onclick="toolTableUpdate('${basePath }/sup/consignationQuote/saveSubmitConsignationQuote/${item[0] }','${item[0] }','提交委托报价')">
													              <span>提交</span>
												             </a>
												        </c:if>
														<pm:authCom authCode="role_editOrgRole">
															<a class="" href="javascript:;" onclick="openLayerSize('修改','${basePath }/sup/consignationQuote/editSupplierConsignationQuote/${item[0] }/${comType }', true,'800px','600px')">
																<span>修改</span>
															</a>
														</pm:authCom>
														<pm:authCom authCode="organize_listLoginLog">
															<a class="" href="javascript:;" onclick="openLayerSize('查看详情','${basePath }/sup/consignationQuote/viewSupplierConsignationQuote/${item[0]}/${comType }',false,'800px','600px')">
																<span>查看详情</span>
															</a>
														</pm:authCom>
													</td>
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