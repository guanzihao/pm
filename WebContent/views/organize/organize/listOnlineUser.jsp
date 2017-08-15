<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>在线人员列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th>邮箱</th>
											<th>用户名称</th>
											<th>IP</th>
											<th>上线时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${onlineUserList }" var="item" varStatus="status">
											<tr>
												<pm:execute id="userAccount" bean="organizeBusinImpl" method="getUserAccount">
													<pm:execute-param type="java.lang.String" value="${item.userId }"/>
												</pm:execute>
												<td>${userAccount.userMail }</td>
												<td>${userAccount.userName }</td>
												<td>${item.userIp }</td>
												<td><fmt:formatDate value="${item.userDate}" pattern="yyyy-MM-dd HH:mm"/></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
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