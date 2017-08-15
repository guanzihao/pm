<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>枚举列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sysconfig/info/listEnumInfo"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <span class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                </span>
									    <span class="tables_search_label">
		                                	状态：<select class="input-sm form-control" name="searchName1" style="padding: 0px;">
		                                		<option value="">--</option>
		                                		<option value="W" <c:if test="${pageBean.searchBean.searchName1 eq 'W'}">selected="selected"</c:if>>待审核</option>
		                                		<option value="Y" <c:if test="${pageBean.searchBean.searchName1 eq 'Y'}">selected="selected"</c:if>>已通过</option>
		                                		<option value="N" <c:if test="${pageBean.searchBean.searchName1 eq 'N'}">selected="selected"</c:if>>已驳回</option>
		                                	</select>
		                                </span>
		                                 <label class="tables_search_label">
		                                	  <button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/sysconfig/info/editEnumInfo/0',true)">
												<span> 添加</span>
											</a>
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sysconfig/info/enableSubCompanyInfo', 'ids', 'passToolTable', '已通过')">
												<span>通过</span>
											</a>
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sysconfig/info/disableSupCompanyInfo', 'ids', 'rejectToolTable', '已驳回')">
												<span>驳回</span>
											</a>
									
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.id'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.id', ${!searchBean.searchOrderType})">编号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.name', ${!searchBean.searchOrderType})">名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.status'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.status', ${!searchBean.searchOrderType})">状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayer('查看','${basePath }/sysconfig/info/viewEnumInfo/${item[0]}',false)">${item[1] }</a>
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
													
													<td>
														<pm:auth authCode="info_editCompanyInfo">
															<a class="" href="javascript:;" onclick="openLayer('维护','${basePath }/sysconfig/info/viewEnumInfo/${item[0]}',true)">
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