<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
	    	<div class="col-sm-12 tables_search_label" style="margin-left:20px;margin-top:10px;">
	    		<a class="btn btn-success" href="${basePath }/sup/flow/bgexccs/listBgFlowExccOrderReceiving/${billId}/0">
					<span>接单</span>
				</a>
				<pm:authCom authCode="info_listAllComLoginLog">
		        	<a class="btn btn-info" href="javascript:;">
						<span>查验</span>
					</a>
				</pm:authCom>
				<pm:authCom authCode="sup_listAllComLoginLog">
					<a class="btn btn-success"href="${basePath }/sup/flow/bgexccs/listBgFlowExccGreenLight/${billId}/0">
						<span>放行</span>
					</a>
				</pm:authCom>	
				<a class="btn btn-success"  style="margin-left:600px;" href="${basePath }/sup/orderCccontroller/orderCc/${orderid.orderFrom.id}/bgex">
					<span>返回</span>
				</a>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/sup/flow/bgexccs/listBgFlowExccCheck/${billId}/0" class="form-horizontal formValidate" method="post">
								<div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:authCom authCode="organize_editInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="openLayerSize('添加','${basePath }/sup/flow/bgexccs/editFlowExccNodeCheck/0/${billId }',true,'90%','90%')">
												<span>添加</span>
											</a>
										</pm:authCom>
										<pm:authCom authCode="organize_removeInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTable('${basePath }/sup/flow/bgexccs/removeFlowExccNode', 'ids', 'removeToolTableTr', '删除')">
												<span>删除</span>
											</a>
										</pm:authCom>
									</div>
								</div>
								<div class="table-responsive">
								<input value="${close }" style="display:none;" id="close"/>
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th>序号</th>
												<th>操作</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.startDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.startDate', ${!searchBean.searchOrderType})">开始时间</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.endDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.endDate', ${!searchBean.searchOrderType})">完成时间</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.status'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.status', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.oneAccessory'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.oneAccessory', ${!searchBean.searchOrderType})">附件</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.inspectionDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.inspectionDate', ${!searchBean.searchOrderType})">接单时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>${status.index+1 }</td>
													<td>
														<pm:authCom authCode="role_editOrgRole">
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('修改','${basePath }/sup/flow/bgexccs/editFlowExccNodeCheck/${item[0] }/${billId }', true,'90%','90%')">
																<span>修改</span>
															</a>
														</pm:authCom>
														<pm:authCom authCode="organize_listLoginLog">
															<a class="btn btn-xs btn-white" href="javascript:;" onclick="openLayer('查看详情','${basePath }/sup/flow/bgexccs/viewFlowExccNodeCheck/${item[0]}/${billId }',false)">
																<span>查看详情</span>
															</a>
														</pm:authCom>
													</td>
													<td><fmt:formatDate value="${item[1]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td><fmt:formatDate value="${item[2]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<c:choose>
															<c:when test="${item[3]==0 }">
																<span class="label label-warning" id="label_${item[0]}">未完成</span>
																	<!-- <span><i class="fa"></i><a> 手动完成</a></span> -->
															</c:when>
															<c:otherwise>
																<span class="label label-info" id="label_${item[0]}">已完成</span>
															</c:otherwise>	
														</c:choose>
													</td>
													<td><pm:execute id="bgFlowExccBusin" bean="bgFlowExccBusinImpl" method="getFlowExccNode">
															<pm:execute-param type="java.lang.String" value="${item[0]}" />
														</pm:execute>
														<pm:fileList metaObject="${bgFlowExccBusin}" delete="false" metaColums="colums" /></td>
													<td><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	var close=$("#close").val();
	if (close=='close') {
		parent.window.location.reload();
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	}
});
</script>