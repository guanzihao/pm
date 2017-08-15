<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>
<style>
*{
	font-size: 13px;
}

.ibox-title{
	min-height: 0px;
}

dt{
	float: left;
	font-weight: 400 !important;
	margin-right: 10px;
	width: 100px;
	margin-left: 30px;
}

dl{
	margin-top:20px;
}

</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>报关进口查验明细</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
						    <%-- <div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>报关单号</dt>
									<dd>
										${flowImccNode.customNo }
									</dd>
								</dl>
							</div> 
							 <div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>客户纳税识别码</dt>
									<dd>
										${flowImccNode.customCode}
									</dd>
								</dl>
							</div>  --%>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>开始时间</dt>
									<dd>
										<fmt:formatDate value="${flowImccNode.startDate }"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>结束时间</dt>
									<dd>
										<fmt:formatDate value="${flowImccNode.endDate }" pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>查验时间</dt>
									<dd>
										<fmt:formatDate value="${flowImccNode.inspectionDate}"
											pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<%-- <div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>供应商类型</dt>
									<dd>
									   <c:if test="${flowImccNode.type==0}">
									                       平台供应商
									   </c:if>
									   <c:if test="${flowImccNode.type==1}">
									                       外部供应商
									   </c:if>
									</dd>
								</dl>
							</div>
							<c:if test="${flowImccNode.type==0}">
							     <div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>操作一级部门</dt>
									<dd>
										${firstDept.comName}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>操作二级部门</dt>
									<dd>
										${secondDept.comName}
									</dd>
								</dl>
							</div>
							</c:if>
							<c:if test="${flowImccNode.type==1}">
							     <div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>操作公司</dt>
									<dd>
										${flowImccNode.opeComp}
									</dd>
								</dl>
							</div>
							</c:if> --%>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>操作人</dt>
									<dd>
										${flowImccNode.operatorName}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>完成情况说明</dt>
									<dd>
										${flowImccNode.remarks}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>备注</dt>
									<dd>
										${flowImccNode.note}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>附件</dt>
									<dd>
										<pm:fileList metaObject="${flowImccNode}" delete="false" metaColums="colums" />
									</dd>
								</dl>
							</div>
							
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:choose>
											<c:when test="${flowImccNode.status==0 }">
												<span class="label label-warning">进行中</span>
											</c:when>
											<c:otherwise>
												<span class="label label-info">已完成</span>
											</c:otherwise>
										</c:choose>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>