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
		                                <span class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </span>
									    <span class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchName2" style="padding: 1px;">
		                                		<option value="">--</option>
		                                		<option value="0" <c:if test="${pageBean.searchBean.searchName2 eq '0'}">selected="selected"</c:if>>正常</option>
		                                		<option value="-1" <c:if test="${pageBean.searchBean.searchName2 eq '-1'}">selected="selected"</c:if>>禁用</option>
		                                	</select>
		                                </span>
		                                <label class="tables_search_label">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询 </button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="organize_editInterface">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath}/inter/interColumn/showOpenInterface',true)">
												<span><i class=""></i> 添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_removeInterface">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/inter/interColumn/deleteInterface', 'ids', 'removeToolTableTr', '删除')">
												<span><i class=""></i> 删除</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_enableInterface">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/framework/interface/enableInterface', 'ids', 'passToolTable', '已通过')">
												<span><i class=""></i> 启用</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_disableInterface">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/framework/interface/disableInterface', 'ids', 'rejectToolTable', '已驳回')">
												<span><i class=""></i> 禁用</span>
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
												<th>操作</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intername'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.intername', ${!searchBean.searchOrderType})">接口名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intercontent'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.intercontent', ${!searchBean.searchOrderType})">接口详情</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intersort'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.intersort', ${!searchBean.searchOrderType})">显示顺序</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.intercheckState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.intercheckState', ${!searchBean.searchOrderType})">是否启用</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.obj_createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<pm:auth authCode="organize_editInterface">
															<a class="" href="javascript:;" onclick="openLayer('修改信息','${basePath }/inter/interColumn/editInterfaceColumns/${item[0]}',true)">
																<span><i class=""></i> 修改信息</span>
															</a>
														</pm:auth>
													</td>
													<td>
														<a href="javascript:;" onclick="openLayer('查看','${basePath }/framework/interface/viewInterface/${item[0]}',false)">${item[1] }</a>
													</td>
													<td>${item[2] }</td>
													<td>${item[3] }</td>
													<td>
														<c:choose>
															<c:when test="${item[4] eq '1'}"><span class="label label-warning" id="label_${item[0]}">启用</span></c:when>
														
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">禁用</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/></td>
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