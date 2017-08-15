<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>网站内容信息</h5>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内容标题</dt>
									<dd>${cmsContent.tile }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内容详情</dt>
									<dd>${cmsContent.content }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内容缩略图</dt>
									<dd>
										<img alt="" width="100px" height="100px" src="${basePath }/imgServlet?imgName=${cmsContent.img }">
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内容大图</dt>
									<dd>
										<img alt="" width="100px" height="100px" src="${basePath }/imgServlet?imgName=${cmsContent.bigImg }">
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内容顺序</dt>
									<dd>${cmsContent.sort }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>创建日期</dt>
									<dd>
										<fmt:formatDate value="${cmsContent.createDate }" pattern="yyyy-MM-dd"/>
											&nbsp;
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>更新日期</dt>
									<dd>
										<fmt:formatDate value="${cmsContent.updateDate }" pattern="yyyy-MM-dd"/>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>启用状态</dt>
									<dd>
										<c:if test="${cmsContent.status eq '0'}"><span class="label label-warning">启用</span></c:if>
										<c:if test="${cmsContent.status eq '1'}"><span class="label label-danger">禁止</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>关键字</dt>
									<dd>${cmsContent.keyWord }</dd>
								</dl>
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