<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
  
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>	订单分配管理</h5>
	                </div>
	                <div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="#" class="form-horizontal formValidate" method="post">
								<input type="hidden" value="${close }" id="closeId">
								<div class="row">
									<div class="col-sm-12">
										<label class="tables_search_label"> 订单号：<input
											type="text" class="input-sm form-control my_input" name="searchName1"
											value="${pageBean.searchBean.searchName1 }">
											<button type="button" class="btn btn-sm btn-primary button1"
												onclick="goSubmit()">查询</button>
										</label>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>序号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.order_code'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.order_code', ${!searchBean.searchOrderType})">订单号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.flow_name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.flow_name', ${!searchBean.searchOrderType})">类型</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.com_Name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.com_Name', ${!searchBean.searchOrderType})">客户公司</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.issue_date'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.issue_date', ${!searchBean.searchOrderType})">发布日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.order_state'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.order_state', ${!searchBean.searchOrderType})">状态</th>
												<th>操作</th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item"
												varStatus="status">
												<tr class="parent" id="${status.index+1 }" onclick="aaa('${item[0]}','${status.index+1 }')" >
													<td>${status.index+1 }</td>
													<td>${item[3]}</td>
													<td>${item[6]}</td>
													<td>${item[4]}</td>
													<td><fmt:formatDate value="${item[1]}" pattern="yyyy-MM-dd HH:mm"/></td>
													<td>
														<c:if test="${item[2]=='12' }">
															<span class=" label label-warning"> 未分配</span>
														</c:if>
														<c:if test="${item[2]=='11' }">
															<span class=" label label-danger"> 服务商已拒绝</span>
														</c:if>
													</td>
													<td>
															<a class="" href="javascript:;" onclick="ajaxTable('${basePath }/order/terminationOrderFrom/${item[0] }',  'removeToolTableTr', '终止订单')">
																<span>终止订单</span>
															</a>
															<a class="" href="javascript:;" onclick="openLayerSize('分配订单','${basePath }/order/editOrderFrom/${item[0] }', true,'800px','560px')">
																<span>分配订单 </span>
															</a>
															<%-- <a class="" href="javascript:;" onclick="openLayerSize('查看详情','${basePath }/order/viewOrderFrom/${item[0] }', true,'500px','400px')">
																<span>查看详情 </span>
															</a> --%>
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
<script type="text/javascript">
$(function(){
	var close=$("#closeId").val();
	if (close=='close') {
		parent.layer.msg('订单分配成功');
		parent.window.location.reload();
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	}
});
</script>
