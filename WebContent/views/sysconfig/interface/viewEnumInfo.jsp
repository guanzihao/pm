<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />

<style>
dt{
	margin: 8px 0;
}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>枚举信息</h5>
						<div class="ibox-tools">
		                     <pm:auth authCode="info_editCompanyInfo">
			                     <a class="btn btn-xs btn-white" href="${basePath }/sysconfig/info/editEnumInfo/${enumType.id}">
			                         <i class=""></i> 修改
			                     </a>
		                     </pm:auth>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${enumType.status eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${enumType.status eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${enumType.status eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>枚举名称</dt>
									<dd>${enumType.name }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>显示顺序</dt>
									<dd>${enumType.sort }</dd>
								</dl>
							</div>
							
						</div>
					</div>
				</div>
	        </div>
	    </div>
	 	  <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>枚举明细信息</h5>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="dataTables_wrapper form-inline">
		                        <form id="pageForm" method="post">
			                        <div class="row" style="">
										<div class="col-sm-12 tables_search_label" style="margin-bottom: 10px;">
												<a class="myBtn" href="${basePath }/sysconfig/info/editEnumitemInfo/${enumType.id}/0">
													<span><i class=""></i> 添加</span>
												</a>
											
												<a class=" myBtn" href="javascript:;" onclick="toolTable('${basePath }/sysconfig/info/enableEnumitems', 'ids', 'passToolTable', '启用')">
													<span><i class=""></i>通过</span>
												</a>
												<a class="myBtn" href="javascript:;" onclick="toolTable('${basePath }/sysconfig/info/disableEnumitems', 'ids', 'rejectToolTable', '禁用')">
													<span><i class=""></i>反驳</span>
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
													
													<th>名称</th>
													<th>显示顺序</th>
													<th>是否启用</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${enumitems }" var="item" varStatus="status">
													<tr id="toolTr_${item.id}">
														<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item.id }"></td>
														<td>${item.name }</td>
														<td>${item.sort }</td>
														<td>
															<c:choose>
																<c:when test="${item.status eq 'W'}"><span class="label label-warning shenghe" id="label_${item.id}">待审核</span></c:when>
																<c:when test="${item.status eq 'Y'}"><span class="label label-info qiyong" id="label_${item.id}">启用</span></c:when>
																<c:when test="${item.status eq 'N'}"><span class="label label-danger jinyong" id="label_${item.id}">禁用</span></c:when>
																<c:otherwise><span class="label label-danger" id="label_${item.id}">禁用</span></c:otherwise>
															</c:choose>
														</td>
														<td>
																<a class="" href="${basePath }/sysconfig/info/editEnumitemInfo/${enumType.id}/${item.id }">
																	<span><i class=""></i> 修改</span>
																</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>