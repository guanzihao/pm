<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>系统访问日志</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/framework/methodlog/listMethodLog" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <span class="tables_search_label">
		                                	时间：<input type="text" class="form-control input-sm date-picker my_input" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker my_input" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()" style="margin-top: 5px;margin-left: 10px;">查询</button>
		                                </span>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>序号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.ipInfo'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.ipInfo', ${!searchBean.searchOrderType})">IP</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.logMethod'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.logMethod', ${!searchBean.searchOrderType})">方法</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'b.userName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('b.userName', ${!searchBean.searchOrderType})">访问人</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">访问时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td>${status.index+1 }</td>
													<td><a href="javascript:;" onclick="openLayer('数据对象','${basePath }/framework/methodlog/viewMethodLog/${item[0]}',false)">${item[1] }</a></td>
													<td>${item[2] }</td>
													<td>${item[3] }</td>
													<td><fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd HH:mm"/></td>
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