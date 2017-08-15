<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>留言管理</h5>
	                    
	                </div>
	                <div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/cms/board/findCMSBoard" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                	<!-- btn-primary -->
		                                	<button type="button" class="btn btn-sm  button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="role_editOrgRole">
											<!-- btn btn-white btn-sm -->
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/views/cms/board/addCmsBoard.jsp',true)">
												<span>添加</span>
											</a>
										</pm:auth>
									</div>
								</div>
								<div class="table-responsive">
								<!-- table-striped table-bordered table-hover dataTable -->
									<table class="table ">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th>操作</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.name', ${!searchBean.searchOrderType})">姓名</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.tel'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.tel', ${!searchBean.searchOrderType})">联系电话</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.company'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.company', ${!searchBean.searchOrderType})">公司名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.email'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.email', ${!searchBean.searchOrderType})">电子邮箱</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.stitle'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.stitle', ${!searchBean.searchOrderType})">标题</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.content'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.content', ${!searchBean.searchOrderType})">留言内容</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.isRead'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.isRead', ${!searchBean.searchOrderType})">是否已查看</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.readDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.readDate', ${!searchBean.searchOrderType})">查看日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">创建日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">更新日期</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center "><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>
														<pm:auth authCode="role_editOrgRole">
														<!-- btn btn-white btn-xs -->
															<a class="" href="javascript:;" onclick="openLayer('查看留言','${basePath }/common/board/detailCmsBoard/${item[0] }', true)">
																<c:choose>
																	<c:when test="${item[7]==0}">
																		<span><i class="fa ">查看留言</i> </span>
																	</c:when>
																	<c:otherwise>
																		<span><i class="fa color_red">已查看</i> </span>
																	</c:otherwise>
																</c:choose>
															</a>
														</pm:auth>
													</td>
													<td>${item[1] }</td>
													<td>${item[2] }</td>
													<td>${item[3] }</td>
													<td>${item[4] }</td>
													<td>${item[5] }</td>
													<td>${item[6] }</td>
													<td>
														<c:if test="${item[7]==0 }">
															<span><i class=" red"></i> 未查看</span>
														</c:if>
														<c:if test="${item[7]==1 }">
															<span><i class=" red color_red"></i> 已查看</span>
														</c:if>
													</td>
													<td><fmt:formatDate value="${item[8]}" pattern="yyyy-MM-dd"/></td>
													<td><fmt:formatDate value="${item[9]}" pattern="yyyy-MM-dd"/></td>
													<td><fmt:formatDate value="${item[10]}" pattern="yyyy-MM-dd"/></td>
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