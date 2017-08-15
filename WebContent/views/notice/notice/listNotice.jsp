<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
<!--
.nav {
    height: 50px;
    line-height: 50px;
    color: #fff;
    background: none;
    background-image: none;
    min-width: 1100px;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 9999;
    width: 100%;
    color: #fff;
    font-size: 14px;
}

-->
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn" style="padding:0px">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="">
	                <div class="ibox-title" style="display:block;">
	                    <h5>站内信息（收件箱）</h5>
	                    <div class="ibox-tools">
	                       <a href="${basePath }/notice/notice/editNotice/0" class="button1" >编写信息</a>
	                    </div>
	                </div>
	                <div class="ibox-content" style="padding:0px">
	                	<div class="row">
	                	<div style="width:100%;height:50px;"></div>
			                <div class="col-lg-12">
			                    <div class="tabs-container" id="daohang">
			                    
			                        <ul class="nav nav-tabs" style="margin-top:52px;">
			                            <li class="active"><a href="${basePath }/notice/notice/listNotice"> 收件箱</a></li>
			                            <li><a href="${basePath }/notice/notice/listSendNotice"> 发件箱</a></li>
			                        </ul>
			                        <div class="tab-content">
			                            <div id="tab-1" class="tab-pane active">
			                                <div class="panel-body">
			                                    <div class="dataTables_wrapper form-inline">
													<form id="pageForm" action="${basePath }/notice/notice/listNotice" class="form-horizontal formValidate" method="post">
														<div class="row">
								                            <div class="col-sm-12" style="margin-bottom: 8px;">
								                                <span class="tables_search_label">
								                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
								                                </span>
								                                <span class="tables_search_label">
								                                	状态：<select class="input-sm form-control" name="searchInt1" style="padding: 1px;">
								                                		<option value="0">--</option>
								                                		<option value="1" <c:if test="${pageBean.searchBean.searchInt1 == 1}">selected="selected"</c:if>>未读</option>
								                                		<option value="2" <c:if test="${pageBean.searchBean.searchInt1 == 2}">selected="selected"</c:if>>已读</option>
								                                	</select>
								                                </span>
								                                 <span class="tables_search_label">
								                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
								                                </span>
								                            </div>
								                        </div>
														<div class="table-responsive">
															<table class="table table-striped table-bordered table-hover dataTable">
												                <thead>
																	<tr>
																		<th>序号</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.notice_Title'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.notice_Title', ${!searchBean.searchOrderType})">标题</th>
																		<th >发送人</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.notice_IsRead'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.notice_IsRead', ${!searchBean.searchOrderType})">状态</th>
																		<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.obj_updateDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.obj_updateDate', ${!searchBean.searchOrderType})">接收时间</th>
																	</tr>
																</thead>
												                <tbody>
												                	<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
																		<tr ${item[5] ? 'class=\"read\"' : 'class=\"unread\"'}>
																			<td>${status.index+1 }</td>
																			<td>
																				<a href="${basePath }/notice/notice/viewNotice/${item[0]}?isRead=true">${item[1] }</a>
																				<c:if test="${item[2]}">&nbsp;&nbsp;</c:if>
																			</td>
																			<td>${item[4] }</td>
																			<td>
																				<c:choose>
																					<c:when test="${item[5] }"><span class="label">已读</span></c:when>
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
			            </div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>