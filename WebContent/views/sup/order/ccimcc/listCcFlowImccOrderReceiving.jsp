<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
	    	<div class="col-sm-12 tables_search_label" style="margin-left:20px;margin-top:10px;">
	    		<a class="btn btn-info" href="javascript:;" >
					<span>接单登记</span>
				</a>
	        	<a class="btn btn-success" href="${basePath }/sup/flow/ccimccs/listCcFlowImccVehicleArrives/${billId}/0">
					<span>车辆到达</span>
				</a>
				<a class="btn btn-success" href="${basePath }/sup/flow/ccimccs/listCcFlowImccStoredSign/${billId}/0">
					<span>入库签收</span>
				</a>
				<a class="btn btn-success" style="margin-left:600px;" href="${basePath }/sup/orderCccontroller/orderCc/${orderid.orderFromId}/ccim">
					<span>返回</span>
				</a>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/sup/flow/ccimccs/listCcFlowImccOrderReceiving/${billId}/0" class="form-horizontal formValidate" method="post">
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:authCom authCode="organize_editInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="openLayerSize('添加','${basePath }/sup/flow/ccimccs/editCcFlowImccNode/0/${billId }',true,'90%','90%')">
												<span> 添加</span>
											</a>
										</pm:authCom>
										<pm:authCom authCode="organize_removeInterface">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTable('${basePath }/sup/flow/ccimccs/removeCcFlowImccNode', 'ids', 'removeToolTableTr', '删除')">
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
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.endDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.endDate', ${!searchBean.searchOrderType})">日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.state'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.state', ${!searchBean.searchOrderType})">状态</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.oneAccessory'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.oneAccessory', ${!searchBean.searchOrderType})">附件</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'a.orderReceivingDate'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('a.orderReceivingDate', ${!searchBean.searchOrderType})">接单登记时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0] }">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0] }"></td>
													<td>${status.index+1 }</td>
													<td>
														<pm:authCom authCode="role_editOrgRole">
															<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('修改','${basePath }/sup/flow/ccimccs/editCcFlowImccNode/${item[0] }/${billId }', true,'90%','90%')">
																<span> 修改</span>
															</a>
														</pm:authCom>
														<pm:authCom authCode="organize_listLoginLog">
															<a class="btn btn-xs btn-white" href="javascript:;" onclick="openLayer('查看详情','${basePath }/sup/flow/ccimccs/viewCcFlowImccNode/${item[0]}/${billId }',false)">
																<span> 查看详情</span>
															</a>
														</pm:authCom>
													</td>
													<td><fmt:formatDate value="${item[1]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<c:choose>
															<c:when test="${item[2]==0 }">
																<span class="label label-warning" id="label_${item[0]}">未完成</span>
															</c:when>
															<c:otherwise>
																<span class="label label-info" id="label_${item[0]}">已完成</span>
															</c:otherwise>	
														</c:choose>
													</td>
													<td>
														<pm:execute id="ccFlowImccNode" bean="ccFlowImccBusinImpl" method="getCcFlowImccNode">
															<pm:execute-param type="java.lang.String" value="${item[0]}" />
														</pm:execute>
														<pm:fileList metaObject="${ccFlowImccNode}" delete="false" metaColums="colums" />
													</td>
													<td><fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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