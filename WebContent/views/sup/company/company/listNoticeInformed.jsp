<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>公告通知列表</h5>
	                </div>
	                <div class="ibox-content">
	                	<div class="row">
			                <div class="col-lg-12">
			                    <div class="tabs-container">
			                        <div class="tab-content">
			                            <div id="tab-1" class="tab-pane active">
			                                <div class="panel-body">
			                                    <div class="dataTables_wrapper form-inline">
													<form id="pageForm" action="${basePath }/sup/company/company/listNoticeInformed" class="form-horizontal formValidate" method="post">
														<div class="row">
								                            <div class="col-sm-12">
								                                <label class="tables_search_label">
								                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
								                                </label>
								                                <label class="tables_search_label">
																	时间：<input type="text" class="form-control input-sm date-picker" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
								                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
								                                </label>
								                            </div>
								                        </div>
														<div class="table-responsive">
															<table class="table table-striped table-bordered table-hover dataTable">
												                <thead>
																	<tr>
																		<th>序号</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.noticeTitle'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.noticeTitle', ${!searchBean.searchOrderType})">标题</th>
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
																			<td>${item[3] }</td>
																			<td><fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/></td>
																			<td>
																				<pm:authCom authCode="noticeinformed_editComNoticeInformed">
																					<a class="" href="${basePath }/sup/company/company/viewComNoticeInformed/${item[0] }">
																						<span> 查看</span>
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
			            </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>