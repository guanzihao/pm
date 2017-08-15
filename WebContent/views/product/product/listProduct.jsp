<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
 
<style>
	th {
		width:125px;
	}
	label{
		font-weight: 400;
	}
	
</style>
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
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sup/product/product/listProduct" method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                            	<label class="tables_search_label" style="float: left;">
		                                	产品编号：<input type="text" class="input-sm form-control my_input" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                </label>
		                                <label class="tables_search_label">
		                                	产品名称：<input type="text" class="input-sm form-control my_input" name="searchName1" value="${pageBean.searchBean.searchName1 }">
		                                </label>
			                             <label class="tables_search_label">
		                                	产品目录：<input type="hidden" name="productTypeId" id="productTypeId" value="${productTypeId}" ><input type="text" id="productTypeName" name="productTypeName" class="form-control my_input" value="${productTypeName }"  ondblclick="selectProductType()" >
		                                  </label>	
		                                <label class="tables_search_label">
		                                	<button type="button" class="btn btn-sm button1" onclick="goSubmit()">查询</button>
		                                </label>
		                            </div>
		                        </div>
		                         <div class="row">
									<div class="col-sm-12 tables_search_label">
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayerSize('添加','${basePath }/sup/product/product/editProduct/0/0',true,'70%','66%')">
											<span> 添加</span>
										</a>						
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/product/product/removeProduct', 'ids', 'removeToolTableTr', '删除')">
											<span>删除</span>
										</a>	
											<a class="btn btn-white btn-sm myBtn" id="shangjia" href="javascript:;" onclick="toolTable('${basePath }/sup/product/product/enableproduct', 'ids', 'passToolTable', '上架')">
												<span>上架</span>
											</a>
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/product/product/enableproducts', 'ids', 'rejectToolTable', '下架')">
												<span>下架</span>
											</a>
									</div>
								</div>
								<div class="table-responsive">
									<table class="">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productCode'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productCode', ${!searchBean.searchOrderType})">产品编号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productName'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productName', ${!searchBean.searchOrderType})">产品名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productSpec'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productSpec', ${!searchBean.searchOrderType})">产品规格型号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productUnit'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productUnit', ${!searchBean.searchOrderType})">单位</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'p.productType'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('p.productType', ${!searchBean.searchOrderType})">产品目录</th>
												<th >状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/product/product/viewProduct/${item[0]}',false,'550px','500px')">${item[2] }</a>
													</td>
													<td>
														<a href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/product/product/viewProduct/${item[0]}',false,'550px','500px')">${item[1] }</a>
													</td>
													<td>
														${item[3] }
													</td>
													<td>${item[4] }</td>
													<td>
														${item[6].typeName }
													</td>
													<td>
													<c:choose>
															<c:when test="${item[7] eq 'N'}"><a class="" href="#" id="label_${item[0]}">下架</a></c:when>
															<c:when test="${item[7] eq 'Y'}"><a class="" href="#" id="label_${item[0]}">上架</a></c:when>
														</c:choose>
														
													</td>
													<td>
														<a class="" href="javascript:;" onclick="openLayerSize('修改信息','${basePath }/sup/product/product/editProduct/${item[0]}/${item[6].id}',true,'70%','66%')">
															<span> 修改信息</span>
														</a>														
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
//$('#searchName3').select2("val", ['${searchBean.searchName3}']);
</script>
<%@ include file="/include/includeFooter.jsp" %>