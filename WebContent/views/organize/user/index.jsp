<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
            <div class="col-sm-12">
                <div class="alert alert-success alert-dismissable">
                    <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>欢迎使用电子采购系统！
                </div>
            </div>
	    </div>
	    <div class="row">
	        <div class="col-sm-12">
	            <div class="ibox ">
	                <div class="ibox-content">
	                    <div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>邮箱</dt>
									<dd>${currUser.userMail }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>用户名称</dt>
									<dd>${currUser.userName }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${currUser.userState eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${currUser.userState eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${currUser.userState eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
						</div>
	                </div>
	            </div>
	         </div>
	    </div>
	    <div class="row">
            <div class="col-sm-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true"> 待处理信息    <span class="badge badge-danger">${fn:length(noticeList)}</span></a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <table class="table table-hover no-margins">
				                    <thead>
				                        <tr>
				                        	<th>序号</th>
				                            <th>标题</th>
				                            <th>发件人</th>
				                            <th>日期</th>
				                            <th>操作</th>
				                        </tr>
				                    </thead>
									<tbody>
									    <c:forEach items="${noticeList }" var="notice" varStatus="status">
											<tr>
												<td>${status.index+1 }</td>
												<td>${notice.noticeTitle }</td>
												<td>${notice.userAccount.userName }</td>
												<td><fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd"/></td>
												<td>
				                                	<a class="btn btn-xs btn-outline btn-primary" href="javascript:;" onclick="openLayer('查看','${basePath }/notice/notice/viewNotice/${notice.id}?isRead=true',true)">查看</a>
				                                </td>
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