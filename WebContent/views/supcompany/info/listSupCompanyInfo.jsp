<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<body class="gray-bg" style="font-size:14px;">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>客户列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/supcompany/info/listSupCompanyInfo?id=${supcompanyInfo.id}"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                </label>
									    <label class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchName1">
		                                		<option value="">--</option>
		                                		<option value="W" <c:if test="${pageBean.searchBean.searchName1 eq 'W'}">selected="selected"</c:if>>待审核</option>
		                                		<option value="Y" <c:if test="${pageBean.searchBean.searchName1 eq 'Y'}">selected="selected"</c:if>>已通过</option>
		                                		<option value="N" <c:if test="${pageBean.searchBean.searchName1 eq 'N'}">selected="selected"</c:if>>已驳回</option>
		                                	</select>
		                                </label>
		                                <label class="tables_search_label">
											注册时间：<input type="text" class="form-control input-sm date-picker my_input" name="searchDate1" value="${pageBean.searchBean.searchDate1 }">-<input type="text" class="form-control input-sm date-picker my_input" name="searchDate2" value="${pageBean.searchBean.searchDate2 }">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="info_editCompanyInfo">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayerSize('添加','${basePath }/supcompany/info/editSupCompanyInfo/0',true,'800px','490px')">
												<span>添加</span>
											</a>
										</pm:auth>
										<pm:auth authCode="info_enableCompanyInfo">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/supcompany/info/enableSubCompanyInfo', 'ids', 'passToolTable', '已通过')">
												<span>通过</span>
											</a>
										</pm:auth>
										<pm:auth authCode="info_disableCompanyInfo">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/supcompany/info/disableSupCompanyInfo', 'ids', 'rejectToolTable', '已驳回')">
												<span>驳回</span>
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.comCode'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.comCode', ${!searchBean.searchOrderType})">编号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.comName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.comName', ${!searchBean.searchOrderType})">名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.comState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.comState', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">注册时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayer('查看','${basePath }/supcompany/info/viewSupCompanyInfo/${item[0]}',false)">${item[1] }</a>
													</td>
													<td>${item[2] }</td>
													<td>
														<c:choose>
															<c:when test="${item[3] eq 'W'}"><span class="label label-warning shenghe" id="label_${item[0]}">待审核</span></c:when>
															<c:when test="${item[3] eq 'Y'}"><span class="label label-info tongguo" id="label_${item[0]}">已通过</span></c:when>
															<c:when test="${item[3] eq 'N'}"><span class="label label-danger bohui" id="label_${item[0]}">已驳回</span></c:when>
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<pm:auth authCode="info_editCompanyInfo">
															<a class="" href="javascript:;" onclick="openLayerSize('维护','${basePath }/supcompany/info/editSupCompanyInfo/${item[0]}',true,'800px','490px')">
																<span><i class=""></i> 维护</span>
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