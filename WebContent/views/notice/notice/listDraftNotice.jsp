<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>站内信息（草稿箱）</h5>
	                    <div class="ibox-tools">
	                       <a href="${basePath }/notice/notice/editNotice/0" class="btn btn-xs btn-primary">编写信息</a>
	                    </div>
	                </div>
	                <div class="ibox-content">
	                	<div class="row">
			                <div class="col-lg-12">
			                    <div class="tabs-container">
			                        <ul class="nav nav-tabs">
			                            <li><a href="${basePath }/notice/notice/listNotice"> 收件箱</a></li>
			                            <li><a href="${basePath }/notice/notice/listSendNotice"> 发件箱</a></li>
			                            <li class="active"><a href="${basePath }/notice/notice/listDraftNotice"> 草稿箱</a></li>
			                        </ul>
			                        <div class="tab-content">
			                            <div id="tab-1" class="tab-pane active">
			                                <div class="panel-body">
			                                    <div class="dataTables_wrapper form-inline">
													<form id="pageForm" action="${basePath }/notice/notice/listDraftNotice" class="form-horizontal formValidate" method="post">
														<div class="row">
								                            <div class="col-sm-12">
								                                <label class="tables_search_label">
								                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
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
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.notice_Title'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.notice_Title', ${!searchBean.searchOrderType})">标题</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'b.user_Name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('b.user_Name', ${!searchBean.searchOrderType})">发送人</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.obj_updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.obj_updateDate', ${!searchBean.searchOrderType})">保存时间</th>
																		<th>操作</th>
																	</tr>
																</thead>
												                <tbody>
												                	<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
																		<tr>
																			<td>${status.index+1 }</td>
																			<td>
																				<a href="${basePath }/notice/notice/viewNotice/${item[0]}">${item[1] }</a>
																				<c:if test="${item[2]}">&nbsp;&nbsp;</c:if>
																			</td>
																			<td>${item[4] }</td>
																			<td><fmt:formatDate value="${item[3]}" pattern="yyyy-MM-dd HH:mm"/></td>
																			<td>
																				<a class="btn btn-white btn-xs" href="${basePath }/notice/notice/editNotice/${item[0] }">
																					<span>编辑</span>
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