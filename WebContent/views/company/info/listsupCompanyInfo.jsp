<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>供应商人员列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/company/info/listSupCompanyInfouser/${id }/${name}"  method="post">
								<div class="row">
		                            <div class="col-sm-12">
		                                <label class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="goSubmit()">查询</button>
		                                	<button type="button" class="btn btn-sm btn-primary" onclick="hide()">带回数据</button>
		                                </label>
		                            </div>
		                        </div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center">
													
														<input type="checkbox" readonly="readonly" class="checkbox" onclick="checkAll(this, 'ids')">
														
													</div>
												</th>
												<th >名称</th>
												<th >电话</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr>
													<td class="text-center">
														<input type="checkbox" class="checkbox" name="ids" value="${item[0]}">
														<input type="hidden" name='ids' id="${item[0]}_2" value="${item[1]}">
														<input type="hidden" name='ids' id="${item[0]}_3" value="${item[2]}">
														<input type="hidden" name='ids' id="${item[0]}_4" value="${name}">
													</td>
													<td>${item[1]}</td>
													<td>${item[2]}</td>
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
	function hide() {
		var ids = document.getElementsByName('ids');
		var toValue = new Array();
		for (i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				toValue.push(ids[i].value);
				toValue.push($("#"+(ids[i].value)+"_2").val());
				toValue.push($("#"+(ids[i].value)+"_3").val());
				toValue.push($("#"+(ids[i].value)+"_4").val());
			}
		}
		var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
		parent.returnValue(toValue);
		parent.layer.close(index);
	}
</script>