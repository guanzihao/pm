<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>留言详情页面</h5>
					</div>
		            <div class="ibox-content">
						<div class="row">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>姓名</dt>
									<dd>${board.name }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>联系电话</dt>
									<dd>${board.tel }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>内公司名称</dt>
									<dd>${board.company }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>电子邮箱</dt>
									<dd>${board.email }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>标题</dt>
									<dd>${board.stitle }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>留言内容</dt>
									<dd>${board.content }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>创建日期</dt>
									<dd>
										<fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
											&nbsp;
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>更新日期</dt>
									<dd>
										<fmt:formatDate value="${board.updateDate }" pattern="yyyy-MM-dd"/>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>是否已查看</dt>
									<dd>
										<c:if test="${board.isRead eq '0'}"><span class="label label-warning">未查看</span></c:if>
										<c:if test="${board.isRead eq '1'}"><span class="label label-danger">已查看</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>查看日期</dt>
									<dd>
										<fmt:formatDate value="${board.readDate }" pattern="yyyy-MM-dd"/>
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
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>