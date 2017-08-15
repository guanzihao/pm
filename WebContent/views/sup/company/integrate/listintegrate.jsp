<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>集成登录列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sup/integrate/integrate/listintegrate"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                               			<button type="button" class="btn btn-sm btn-primary button1"
												onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayerSize('添加','${basePath }/sup/integrate/integrate/editintegrate/0',true,'820px','400px')">
												<span> 添加</span>
											</a>
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/integrate/integrate/enableintegrate', 'ids', 'passToolTable', '已通过')">
												<span>通过</span>
											</a>
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/integrate/integrate/disableintegrate', 'ids', 'rejectToolTable', '已驳回')">
												<span>驳回</span>
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'b.loginname'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('b.loginname', ${!searchBean.searchOrderType})">系统名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.loginstate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.loginstate', ${!searchBean.searchOrderType})">状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/integrate/integrate/viewintegrate/${item[0]}',false , '800px' ,'350px')">${item[2] }</a>
													</td>
													<td>
														<c:choose>
															<c:when test="${item[5] eq 'W'}"><span class="label label-warning shenghe" id="label_${item[0]}">待审核</span></c:when>
															<c:when test="${item[5] eq 'Y'}"><span class="label label-info tongguo" id="label_${item[0]}">已通过</span></c:when>
															<c:when test="${item[5] eq 'N'}"><span class="label label-danger bohui" id="label_${item[0]}">已驳回</span></c:when>
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:otherwise>
														</c:choose>
													</td>
													<td>
															<a class="" href="javascript:;" onclick="openLayerSize('维护','${basePath }/sup/integrate/integrate/editintegrate/${item[0]}',true,'820px','400px')">
																<span>维护</span>
															</a>
															<a class="" href="javascript:;" onclick="window.open('${item[8]}?${item[6]}=${item[1]}&${item[7]}=${item[3]}')">
																<span> 打开页面</span>
															</a>
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