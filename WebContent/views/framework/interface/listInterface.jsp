<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>接口列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/framework/interface/listInterface" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
									    <label class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchName2">
		                                		<option value="">--</option>
		                                		<option value="0" <c:if test="${pageBean.searchBean.searchName2 eq '0'}">selected="selected"</c:if>>正常</option>
		                                		<option value="-1" <c:if test="${pageBean.searchBean.searchName2 eq '-1'}">selected="selected"</c:if>>禁用</option>
		                                	</select>
		                                </label>
		                                <label class="tables_search_label">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="organize_editInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="openLayer('添加','${basePath }/framework/interface/editInterface/0',true)">
												<span><i class="fa fa-plus"></i> 添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_removeInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTable('${basePath }/framework/interface/removeInterface', 'ids', 'removeToolTableTr', '删除')">
												<span><i class="fa fa-trash red"></i> 删除</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_enableInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTable('${basePath }/framework/interface/enableInterface', 'ids', 'passToolTable', '已通过')">
												<span><i class="fa fa-check"></i> 启用</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_disableInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTable('${basePath }/framework/interface/disableInterface', 'ids', 'rejectToolTable', '已驳回')">
												<span><i class="fa fa-times red"></i> 禁用</span>
											</a>
										</pm:auth>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.name', ${!searchBean.searchOrderType})">接口名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.content'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.content', ${!searchBean.searchOrderType})">接口详情</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.sort'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.sort', ${!searchBean.searchOrderType})">显示顺序</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.checkstate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.checkstate', ${!searchBean.searchOrderType})">是否启用</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayer('查看','${basePath }/framework/interface/viewInterface/${item[0]}',false)">${item[1] }</a>
													</td>
													<td>${item[2] }</td>
													<td>${item[5] }</td>
													<td>
														<c:choose>
															<c:when test="${item[4] eq 'W'}"><span class="label label-warning" id="label_${item[0]}">待审核</span></c:when>
															<c:when test="${item[4] eq 'Y'}"><span class="label label-info" id="label_${item[0]}">已通过</span></c:when>
															<c:when test="${item[4] eq 'N'}"><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:when>
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<pm:auth authCode="organize_editInterface">
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('修改信息','${basePath }/framework/interface/editInterface/${item[0]}',true)">
																<span><i class="fa fa-pencil"></i> 修改信息</span>
															</a>
														</pm:auth>
													
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