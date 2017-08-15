<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<style>
	tr,th,td{
		height:20px !important;
		padding:0px !important;
	}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>客户列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/order/listCompanyInfo/${taskTypeId}"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                	<button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>带回数据</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.comCode'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.comCode', ${!searchBean.searchOrderType})">编号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.comName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.comName', ${!searchBean.searchOrderType})">名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.comState'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.comState', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.createDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.createDate', ${!searchBean.searchOrderType})">注册时间</th>
												<!-- <th>操作</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr ondblclick="hide('${item[0]}');">
													<td>
														<img alt="点击带回数据" onclick="hide('${item[0]}');" style="width: 30px;height: 30px;" src="${basePath }/resource/images/daihui.png">
													</td>	
													<td>
														<input type="hidden" id="${item[0]}_1" name="ids" value="${item[0]}">
														<input type="hidden"  id="${item[0]}_2" value="${item[2]}">
														<input type="hidden"  id="${item[0]}_3" value="${item[3]}">
														<input type="hidden"  id="${item[0]}_4" value="${item[4]}">
														${item[1] }
													</td>
													<td>${item[2] }</td>
													<td>
														<c:choose>
															<c:when test="${item[5] eq 'W'}"><span class="label label-warning" id="label_${item[0]}">待审核</span></c:when>
															<c:when test="${item[5] eq 'Y'}"><span class="label label-info" id="label_${item[0]}">已通过</span></c:when>
															<c:when test="${item[5] eq 'N'}"><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:when>
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">已驳回</span></c:otherwise>
														</c:choose>
													</td>
													<td><fmt:formatDate value="${item[6]}" pattern="yyyy-MM-dd"/></td>
													<%-- <td>
														<pm:auth authCode="info_editCompanyInfo">
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayer('维护','${basePath }/company/info/editCompanyInfo/${item[0]}',true)">
																<span><i class="fa fa-pencil"></i> 维护</span>
															</a>
														</pm:auth>
													</td> --%>
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
<script type="text/javascript">
	function hide(ids) {
		var toValue = new Array();
		toValue.push($("#"+ids+"_1").val());
		toValue.push($("#"+ids+"_2").val());
		var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
		parent.returnValues_(toValue);
		parent.layer.close(index);
	}
</script>