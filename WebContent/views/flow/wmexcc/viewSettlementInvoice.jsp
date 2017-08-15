<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>结算开票明细</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>开始时间</dt>
									<dd><fmt:formatDate value="${wmFlowExccNode.startDate }" pattern="yyyy-MM-dd" /></dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>结束时间</dt>
									<dd><fmt:formatDate value="${wmFlowExccNode.endDate }" pattern="yyyy-MM-dd" /></dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>结算单生效时间</dt>
									<dd><fmt:formatDate value="${wmFlowExccNode.balanceDate }" pattern="yyyy-MM-dd" /></dd>
								</dl>
							</div>
							
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>备注</dt>
									<dd>${wmFlowExccNode.remarks }</dd>
								</dl>
							</div>

							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>附件</dt>
									<dd><pm:fileList metaObject="${wmFlowExccNode}" delete="false" metaColums="colums"/></dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:choose>
											<c:when test="${wmFlowExccNode.state==0 }"><span class="label label-warning">未完成</span></c:when>
											<c:otherwise><span class="label label-info">已完成</span></c:otherwise>
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