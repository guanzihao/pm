<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>人员列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/organize/organize/listUserAccount" class="form-horizontal formValidate" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
									    <label class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchName2">
		                                		<option value="">--</option>
		                                		<option value="W" <c:if test="${pageBean.searchBean.searchName2 eq 'W'}">selected="selected"</c:if>>待审核</option>
		                                		<option value="Y" <c:if test="${pageBean.searchBean.searchName2 eq 'Y'}">selected="selected"</c:if>>已通过</option>
		                                		<option value="N" <c:if test="${pageBean.searchBean.searchName2 eq 'N'}">selected="selected"</c:if>>已驳回</option>
		                                	</select>
		                                </label>
		                                <label class="tables_search_label">
											注册时间：<input type="text" class="form-control input-sm date-picker" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="organize_editUserAccount">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/organize/organize/editUserAccount/0',true)">
												<span>添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_removeUserAccount">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/organize/organize/removeUserAccount', 'ids', 'removeToolTableTr', '删除')">
												<span>删除</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_enableUserAccount">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/organize/organize/enableUserAccount', 'ids', 'passToolTable', '已通过')">
												<span>启用</span>
											</a>
										</pm:auth>
										<pm:auth authCode="organize_disableUserAccount">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/organize/organize/disableUserAccount', 'ids', 'rejectToolTable', '已驳回')">
												<span> 禁用</span>
											</a>
										</pm:auth>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.userMail'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.userMail', ${!searchBean.searchOrderType})">邮箱</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.userName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.userName', ${!searchBean.searchOrderType})">用户名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.userTel'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.userTel', ${!searchBean.searchOrderType})">手机号码</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.userState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.userState', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">注册时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayer('查看','${basePath }/organize/organize/viewUserAccount/${item[0]}',false)">${item[1] }</a>
													</td>
													<td>${item[2] }</td>
													<td>${item[5] }</td>
													<td>
														<c:choose>
															<c:when test="${item[4] eq 'W'}"><span class="label label-warning" id="label_${item[0]}">待审核</span></c:when>
															<c:when test="${item[4] eq 'Y'}"><span class="label label-info" id="label_${item[0]}">已通过</span></c:when>
															<c:when test="${item[4] eq 'N'}"><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:when>
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<pm:auth authCode="organize_editUserAccount">
															<a class="" href="javascript:;" onclick="openLayer('修改信息','${basePath }/organize/organize/editUserAccount/${item[0]}',true)">
																<span>修改信息</span>
															</a>
														</pm:auth>
														<pm:auth authCode="organize_editUserAccountPwd">
										                    <a class="" href="javascript:;" onclick="openLayer('修改密码','${basePath }/organize/organize/editUserAccountPwd/${item[0]}',false)">
										                         修改密码
										                    </a>
									                    </pm:auth>
														<pm:auth authCode="organize_listLoginLog">
															<a class="" href="javascript:;" onclick="openLayer('查看登录日志','${basePath }/organize/organize/listLoginLog/${item[0]}',false)">
																<span>  查看登录日志</span>
															</a>
														</pm:auth>
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
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>