<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<style>
<!--
	dl{
		margin-top:25px;
	}
-->
</style>

<body class="gray-bg" style="font-size:14px;">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5 style="font-size: 16px">公司信息</h5>
						<div class="ibox-tools">
							<pm:authCom authCode="company_editCompanyInfo">
								<a class="btn btn-xs btn-white myBtn" href="${basePath }/sup/company/company/editCompanyInfo">
								    修改
								</a>
			                </pm:authCom>
			                <c:if test="${companyInfo.comState eq 'L'}">
			                <pm:authCom authCode="company_editCompanyInfo">
								<a class="btn btn-xs btn-white myBtn" href="${basePath }/sup/company/company/tijioaCompanyInfo?state=${companyInfo.comState}">
								    提交
								</a>
			                </pm:authCom>
			                </c:if>
						</div>
					</div>
		            <div class="ibox-content" style="font-size: 14px;">
						<div class="row" style="margin: 50px">
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>状态</dt>
									<dd>
										<c:if test="${companyInfo.comState eq 'W'}"><span class="label label-warning">待审核</span></c:if>
										<c:if test="${companyInfo.comState eq 'Y'}"><span class="label label-info">已通过</span></c:if>
										<c:if test="${companyInfo.comState eq 'N'}"><span class="label label-danger">已驳回</span></c:if>
										<c:if test="${companyInfo.comState eq 'C'}"><span class="label label-danger">资料不全</span></c:if>
										<c:if test="${companyInfo.comState eq 'L'}"><span class="label label-warning">资料齐全</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>公司名称</dt>
									<dd>${companyInfo.comName }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>公司地址</dt>
									<dd>${companyInfo.comAddress }</dd>
								</dl>
							</div>
								<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>公司联系人</dt>
									<dd>${companyInfo.comLink }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>公司联系人电话</dt>
									<dd>${companyInfo.comTel }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>公司联系人邮箱</dt>
									<dd>${companyInfo.comEmail }</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>公司纳税识别码</dt>
									<dd>${companyInfo.taxnum }</dd>
								</dl>
							</div>
							<div class="col-sm-12">
								<dl class="dl-horizontal">
									<dt>三证附件</dt>
									<dd>
										<pm:fileList metaObject="${companyInfo}" delete="false" metaColums="colums" />
									</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>物流</dt>
									<dd>${supCompanyInfowuliu.comName}</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>仓储</dt>
									<dd>${supCompanyInfocangchu.comName}</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>外贸</dt>
									<dd>${supCompanyInfowaimao.comName}</dd>
								</dl>
							</div>
							<div class="col-sm-6">
								<dl class="dl-horizontal">
									<dt>报关</dt>
									<dd>${supCompanyInfobaoguan.comName}</dd>
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