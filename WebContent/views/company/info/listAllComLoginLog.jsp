<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
	    	<div class="col-sm-12 tables_search_label">
	    		<pm:auth authCode="organize_listAllLoginLog">
		    		<a class="btn btn-success" href="${basePath }/organize/organize/listAllLoginLog">
						<span>平台登录日志</span>
					</a>
				</pm:auth>
	        	<a class="btn btn-info" href="javascript:;">
					<span>客户登录日志</span>
				</a>
				<pm:auth authCode="sup_listAllComLoginLog">
					<a class="btn btn-success" href="${basePath }/supcompany/info/listAllComLoginLog">
						<span>供应商登录日志</span>
					</a>
				</pm:auth>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>客户登录日志列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/company/info/listAllComLoginLog" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
		                                <label class="tables_search_label">
		                                	登录时间：<input type="text" class="form-control input-sm date-picker" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>序号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'c.comName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('c.comName', ${!searchBean.searchOrderType})">公司名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'b.userName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('b.userName', ${!searchBean.searchOrderType})">登录人</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'b.userMail'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('userMail', ${!searchBean.searchOrderType})">登录账号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.ipInfo'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.ipInfo', ${!searchBean.searchOrderType})">登录IP</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.hostInfo'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.hostInfo', ${!searchBean.searchOrderType})">Host信息</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.userBrowse'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.userBrowse', ${!searchBean.searchOrderType})">登录信息</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.updateDate', ${!searchBean.searchOrderType})">登录时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td>${status.index+1 }</td>
													<td>${item[1] }</td>
													<td>${item[2] }</td>
													<td>${item[3] }</td>
													<td>${item[4] }</td>
													<td>${item[5] }</td>
													<td>${item[6] }</td>
													<td><fmt:formatDate value="${item[7]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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