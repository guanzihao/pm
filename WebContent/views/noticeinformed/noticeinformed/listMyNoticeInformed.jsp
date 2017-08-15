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
	                    <h5>公告通知</h5>
	                </div>
	                <div class="ibox-content" style="padding: 0px;">
	                	<div class="row">
			                <div class="col-lg-12">
			                    <div class="tabs-container">
			                        <div class="tab-content">
			                            <div id="tab-1" class="tab-pane active">
			                                <div class="panel-body">
			                                    <div class="dataTables_wrapper form-inline">
													<form id="pageForm" action="${basePath }/noticeinformed/noticeinformed/listMyNoticeInformed" class="form-horizontal formValidate" method="post">
														<div class="row" style="margin-bottom:10px;">
								                            <div class="col-sm-12">
								                                <span class="tables_search_label">
								                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
								                                </span>
								                                <span class="tables_search_label">
																	时间：<input type="text" class="form-control input-sm date-picker my_input" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker my_input" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
								                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
								                                </span>
								                            </div>
								                        </div>
								                        <div class="row">
															<div class="col-sm-12 tables_search_label">
																	<a class="btn btn-white btn-sm myBtn" href="${basePath }/noticeinformed/noticeinformed/editNoticeInformed/0">
																		<span><i class=""></i> 添加</span>
																	</a>
																	<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/noticeinformed/noticeinformed/removeNoticeInformed', 'ids', 'removeToolTableTr', '删除')">
																		<span><i class=""></i> 删除</span>
																	</a>
																	<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/noticeinformed/noticeinformed/enableNoticeInformed', 'ids', 'passToolTable', '已通过')">
																		<span><i class=""></i> 发布</span>
																	</a>
															</div>
														</div>
														<div class="table-responsive">
															<table class="table table-striped table-bordered table-hover dataTable">
												                <thead>
																	<tr>
																		<th>序号</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.noticeTitle'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.noticeTitle', ${!searchBean.searchOrderType})">标题</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.noticeType'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.noticeType', ${!searchBean.searchOrderType})">可见类型</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.noticeState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.noticeState', ${!searchBean.searchOrderType})">状态</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'b.userName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('b.userName', ${!searchBean.searchOrderType})">创建人</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.updateDate', ${!searchBean.searchOrderType})">发布时间</th>
																		<th>操作</th>
																	</tr>
																</thead>
												                <tbody>
												                	<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
																		<tr id="toolTr_${item[0]}">
																			<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
																			<td>${item[1] }</td>
																			<td>
																				<c:choose>
																					<c:when test="${item[2] == 1}"><span class="label label-info alluser" style="" id="label_${item[0]}">所有用户</span></c:when>
																					<c:when test="${item[2] == 2}"><span class="label label-danger onlypt" style="" id="label_${item[0]}">只平台用户</span></c:when>
																					<c:when test="${item[2] == 3}"><span class="label label-danger onlykh" style="" id="label_${item[0]}">只客户用户</span></c:when>
																					<c:when test="${item[2] == 4}"><span class="label label-danger onlygys" style="" id="label_${item[0]}">只供应商用户</span></c:when>
																					<c:otherwise><span class="label label-danger" id="label_${item[0]}">--</span></c:otherwise>
																				</c:choose>
																			</td>
																			<td>
																				<c:choose>
																					<c:when test="${item[3] }">已发布1</c:when>
																					<c:otherwise>未发布</c:otherwise>
																				</c:choose>
																			</td>
																			<td>${item[4] }</td>
																			<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/></td>
																			<td>
																					<a class="" href="${basePath }/noticeinformed/noticeinformed/viewNoticeInformed/${item[0] }">
																						<span><i class=""></i> 查看</span>
																					</a>
																					<a class="" href="${basePath }/noticeinformed/noticeinformed/editNoticeInformed/${item[0] }">
																						<span><i class=""></i> 编辑</span>
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
			            </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>