<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<script type="text/javascript">
	 function selectProductType(){
			layer.open({
				title: '选择',
			    type: 2,
			    area: ['80%', '60%'],
			    fix: false, //不固定
			    offset: ['50px', '100px'],
			    maxmin: true,
			    content: '${basePath }/sup/product/productType/selectProductType',
			});
	};
</script>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>产品列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sup/task/listProduct/${taskTypeId}/${flag}" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                            	<label class="tables_search_label">
		                                	产品编号：<input type="text" class="input-sm form-control" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                </label>
		                                <label class="tables_search_label">
		                                	产品名称：<input type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
			                             <label class="tables_search_label">
		                                	产品目录：<input type="hidden" name="productTypeId" id="productTypeId" value="${productTypeId}" ><input type="text" id="productTypeName" name="productTypeName" class="form-control" value="${productTypeName }"  onclick="selectProductType()" >
		                                  </label>	
		                                <label class="tables_search_label">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th >带回数据</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productCode'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productCode', ${!searchBean.searchOrderType})">产品编号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productName', ${!searchBean.searchOrderType})">产品名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productSpec'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productSpec', ${!searchBean.searchOrderType})">产品规格型号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productUnit'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productUnit', ${!searchBean.searchOrderType})">产品价格</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productUnit'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productUnit', ${!searchBean.searchOrderType})">单位</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productType'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productType', ${!searchBean.searchOrderType})">产品目录</th>
												<th >状态</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}" ondblclick="hide('${item[0]}');">
													<td>
														<img alt="点击带回数据" onclick="hide('${item[0]}');" style="width: 30px;height: 30px;" src="${basePath }/resource/images/daihui.png">
													</td>
													<td>
														<input type="hidden" id="${item[0]}_1" class="checkbox" name="ids" value="${item[0]}">
														<input type="hidden"  id="${item[0]}_2" value="${item[1]}">
														<input type="hidden"  id="${item[0]}_3" value="${item[3]}">
														<input type="hidden"  id="${item[0]}_4" value="${item[5]}">
														${item[2] }
													</td>
													<td>
														${item[1] }
													</td>
													<td>
														${item[3] }
													</td>
													<td>
														${item[5] }
													</td>
													<td>${item[4] }</td>
													<td>
														${item[6].typeName }
													</td>
													<td>
													<c:choose>
															<c:when test="${item[7] eq 'N'}"><span class="label label-warning" id="label_${item[0]}">下架</span></c:when>
															<c:when test="${item[7] eq 'Y'}"><span class="label label-info" id="label_${item[0]}">上架</span></c:when>
														</c:choose>
														
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
<script type="text/javascript">
$('#searchName3').select2("val", ['${searchBean.searchName3}']);
</script>
<script type="text/javascript">
function hide(ids){
	var toValue = new Array();
	toValue.push($("#"+ids+"_1").val());
	toValue.push($("#"+ids+"_2").val());
	toValue.push($("#"+ids+"_3").val());
	toValue.push($("#"+ids+"_4").val());
	var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
	parent.returnValueSpWmEc(toValue);
	parent.layer.close(index);
}
</script>

<%@ include file="/include/includeFooter.jsp" %>