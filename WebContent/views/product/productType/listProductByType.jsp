<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
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
							<form id="pageForm" action="${basePath }/sup/product/productType/listProductByType" class="form-horizontal formValidate" method="post">
								<input type="hidden" name="productTypeId" value="${productTypeId}">
								<div class="row">
				                     <div class="col-sm-12" style="">
				                     	<label class="" style="font-weight: normal;">
				                           	产品编号：<input style="" type="text" class="input-sm form-control" name="searchName2" value="${pageBean.searchBean.searchName2 }">
				                        </label>
				                         <label class="" style="font-weight: normal;">
				                         	产品名称：<input style="" type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
				                         </label>
				                         <label class="tables_search_label" style="height:20px;">
		                                	<button type="button" class="btn btn-sm button1" style="" onclick="goSubmit()">查询</button>
		                                </label>
				                     </div>
					            </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath }/sup/product/product/editProduct/0/${productTypeId}',true)">
											<span>添加</span>
										</a>						
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTable('${basePath }/sup/product/product/removeProduct', 'ids', 'removeToolTableTr', '删除')">
											<span> 删除</span>
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
												<th >产品编号</th>
												<th >产品名称</th>
												<th >产品规格型号</th>
												<th >单位</th>
												<th >产品目录</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
													<td>
														<a href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/product/product/viewProduct/${item[0]}',false,'500px','450px')">${item[2] }</a>
													</td>
													<td>
														<a href="javascript:;" onclick="openLayerSize('查看','${basePath }/sup/product/product/viewProduct/${item[0]}',false,'500px','450px')">${item[1] }</a>
													</td>
													
													<td>
														${item[3] }
													</td>
													<td>${item[4] }</td>
													<td>
														${item[6].typeName }
													</td>
													<td>
													<!-- btn btn-white btn-xs -->
														<a class="btn btn-white btn-xs" href="javascript:;" onclick="openLayerSize('修改信息','${basePath }/sup/product/product/editProduct/${item[0]}/${item[6].id}',true,'550px','460px')">
															<span> 修改信息</span>
														</a>														
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<input type="hidden" name="searchOrderName" id="searchOrderName" value="${pageBean.searchBean.searchOrderName }">
<input type="hidden" name="searchOrderType" id="searchOrderType" value="${pageBean.searchBean.searchOrderType }">
<div class="row" style="margin: 20px 0px;">
	<div class="col-xs-12 col-sm-6">
			<span >共&nbsp;</span>${pageBean.totalSize }
			<span >&nbsp;条&nbsp;&nbsp;第&nbsp;
			<input style="width: 20px!important;" type="text" digits="true" required class="" name="currentPage" id="currentPage" size="1" value="${pageBean == null ? 0 : pageBean.currentPage }" onchange="goPage()">
			&nbsp;/&nbsp;<span ><strong>${pageBean.totalPage }</strong>&nbsp;页</span>
			</span>
		<div style="float:right;margin:0;">
			<ul class="pagination" style="margin: 2px 0;white-space: nowrap;">
				<c:choose>
					<c:when test="${pageBean.hasPrePage}">
						<li class="paginate_button"><a href="javascript:;" onclick="goFirstPage()">首页</a></li>
						<li class="paginate_button"><a href="javascript:;" onclick="goPrePage()">前一页</a></li>
					</c:when>
					<c:otherwise>
						<li class="paginate_button disabled"><a href="javascript:;">首页</a></li>
						<li class="paginate_button disabled"><a href="javascript:;">前一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.hasNextPage}">
						<li class="paginate_button"><a href="javascript:;" onclick="goNextPage()">后一页</a></li>
						<li class="paginate_button"><a href="javascript:;" onclick="goLastPage()">末页</a></li>
					</c:when>
					<c:otherwise>
						<li class="paginate_button disabled"><a href="javascript:;">后一页</a></li>
						<li class="paginate_button disabled"><a href="javascript:;">末页</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function goFirstPage(){$("#currentPage").val(1);goPage();}
    function goLastPage(){$("#currentPage").val(${pageBean.totalPage});goPage();}
    function goNextPage(){$("#currentPage").val(${pageBean.nextPage});goPage();}
    function goPrePage(){$("#currentPage").val(${pageBean.prePage});goPage();}
    function goPage(){$("#pageForm").submit();}
    function goSubmitSort(orderName,orderType){$("#searchOrderName").val(orderName);$("#searchOrderType").val(orderType);goPage();}
    function goSubmit(){$("#searchOrderName").val("");goPage();}
    $(function(){
    	document.onkeydown = function(e){var ev = document.all ? window.event : e;if(ev.keyCode==13){goSubmit();}}
    });
</script>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>