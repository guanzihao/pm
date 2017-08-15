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
							<form id="pageForm" action="${basePath }/inter/interColumn/findInterfaceColumns" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
									    <label class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchName2">
		                                		<option value="">--</option>
		                                		<option value="0" <c:if test="${pageBean.searchBean.searchName2 eq '0'}">selected="selected"</c:if>>正常</option>
		                                		<option value="1" <c:if test="${pageBean.searchBean.searchName2 eq '1'}">selected="selected"</c:if>>禁用</option>
		                                	</select>
		                                </label>
		                                <label class="tables_search_label">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="organize_editInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="openLayer('添加','${basePath}/inter/interColumn/showOpenInterface',true)">
												<span><i class=""></i> 添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_removeInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTable('${basePath }/inter/interColumn/deleteInterface', 'ids', 'removeToolTableTr', '删除')">
												<span><i class=""></i> 删除</span>
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intername'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>"  onclick="goSubmitSort('a.intername', ${!searchBean.searchOrderType})">接口名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intercontent'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.intercontent', ${!searchBean.searchOrderType})">接口详情</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intersort'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.intersort', ${!searchBean.searchOrderType})">显示顺序</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.interstatus'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.interstatus', ${!searchBean.searchOrderType})">是否启用</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														${item[1]}
													</td>
													<td>${item[2] }</td>
													<td>${item[3] }</td>
													<td>
														<c:choose>
															<c:when test="${item[4] eq '0'}"><span class="label label-warning qiyong" id="label_${item[0]}">启用</span></c:when>
															<c:otherwise><span class="label label-danger jinyong" id="label_${item[0]}">禁用</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<pm:auth authCode="organize_editInterface">
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('修改信息','${basePath }/inter/interColumn/editInterfaceColumns/${item[0]}',true)">
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