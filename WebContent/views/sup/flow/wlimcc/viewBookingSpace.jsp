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
						<h5>物流进口订舱明细</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>开始时间</dt>
									<dd>
										<fmt:formatDate value="${wlFlowImccNode.startDate }" pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>结束时间</dt>
									<dd>
										<fmt:formatDate value="${wlFlowImccNode.endDate }" pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>起运港</dt>
									<dd>
										${wlFlowImccNode.pol}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>目的港</dt>
									<dd>
										${wlFlowImccNode.pod}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>船名</dt>
									<dd>
										${wlFlowImccNode.shipName}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>航次/航班</dt>
									<dd>
										${wlFlowImccNode.flight}
									</dd>
								</dl>
							</div>
							<%-- <div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>主单号</dt>
									<dd>
										${wlFlowImccNode.mblNo}
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>分单号</dt>
									<dd>
										${wlFlowImccNode.hblNo}
									</dd>
								</dl>
							</div> --%>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>开航日期</dt>
									<dd>
										<fmt:formatDate value="${wlFlowImccNode.sailDate }" pattern="yyyy-MM-dd" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>备注</dt>
									<dd>
										${wlFlowImccNode.remarks }
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>附件</dt>
									<dd><pm:fileList metaObject="${wlFlowImccNode}" delete="false" metaColums="colums" /></dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:choose>
											<c:when test="${wlFlowImccNode.state==0 }">
												<span class="label label-warning">未完成</span>
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