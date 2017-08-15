<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>已发邮件记录</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/framework/mail/listMailLog" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
		                                <label class="tables_search_label">
		                                	发送时间：<input type="text" class="form-control input-sm date-picker" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()" style="margin-top: 5px;margin-left: 10px;">查询</button>
		                                </label>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.mailToId'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.mailToId', ${!searchBean.searchOrderType})">收件人</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.mailSubject'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.mailSubject', ${!searchBean.searchOrderType})">标题</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.mailBody'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.mailBody', ${!searchBean.searchOrderType})">内容</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.updateDate', ${!searchBean.searchOrderType})">发送时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td>${item[1] }</td>
													<td>${item[0] }</td>
													<td>${item[2] }</td>
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