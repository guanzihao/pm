<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>通知提醒</h5>
	                </div>
	                <div class="ibox-content">
		            	<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/notice/sysnews/listSysNews" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
									    <label class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchInt1">
		                                		<option value="0">--</option>
		                                		<option value="1" <c:if test="${pageBean.searchBean.searchInt1 == 1}">selected="selected"</c:if>>未读</option>
		                                		<option value="2" <c:if test="${pageBean.searchBean.searchInt1 == 2}">selected="selected"</c:if>>已读</option>
		                                	</select>
		                                </label>
									    <label class="tables_search_label">
											时间：<input type="text" class="form-control input-sm date-picker" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
						                <thead>
											<tr>
												<th>序号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.newsTitle'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.newsTitle', ${!searchBean.searchOrderType})">标题</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.newsRead'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.newsRead', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.updateDate', ${!searchBean.searchOrderType})">发送时间</th>
											</tr>
										</thead>
						                <tbody>
						                	<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr ${item[2] ? 'class=\"read\"' : 'class=\"unread\"'}>
													<td>${status.index+1 }</td>
													<td>
														<a href="${basePath }/notice/sysnews/forwardSysNews/${item[0]}">${item[1] }</a>
													</td>
													<td>
														<c:choose>
															<c:when test="${item[2] }"><span class="label">已读</span></c:when>
															<c:otherwise><span class="label label-success">未读</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd HH:mm"/></td>
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