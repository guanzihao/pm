<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
<!--
	

.dl-horizontal{
	font-size:12px !important;
}

	*{
		font-size:14px!important;
	}
	
	dt{
		font-weight: 400 !important;
	}
	
	.col-sm-8{
		padding-left:50px;
	}
	
	dl{
		margin-top: 20px;
	}
-->
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	        	<div class="ibox float-e-margins">
					<div class="ibox-title" style="min-height: 0px;">
						<h5 style="font-size: 18px">个人信息</h5>
						<div class="ibox-tools" >
		                     <a class="" style="width: 60px" href="${basePath }/sup/company/user/editUser">
		                          修改信息
		                     </a>
						</div>
					</div>
		            <div class="ibox-content">
						<div class="row" style="">
							
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>状态：</dt>
									<dd>
										<c:if test="${companyInfoUser.userState eq 'W'}"><span class="label label-warning yishenhe">待审核</span></c:if>
										<c:if test="${companyInfoUser.userState eq 'Y'}"><span class="label label-info tongguo">已通过</span></c:if>
										<c:if test="${companyInfoUser.userState eq 'N'}"><span class="label label-danger bohui">已驳回</span></c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>是否主联系人：</dt>
									<dd>
										<c:if test="${companyInfoUser.userAdmin }">是</c:if>
										<c:if test="${!companyInfoUser.userAdmin }">否</c:if>
									</dd>
								</dl>
							</div>
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>邮箱[登录名]：</dt>
									<dd>${companyInfoUser.userMail }</dd>
								</dl>
							</div>
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>名称：</dt>
									<dd>${companyInfoUser.userName }</dd>
								</dl>
							</div>
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>联系电话：</dt>
									<dd>${companyInfoUser.userNumber }</dd>
								</dl>
							</div>
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>手机号码：</dt>
									<dd>${companyInfoUser.userTel }</dd>
								</dl>
							</div>
							<div class="col-sm-8">
								<dl class="dl-horizontal">
									<dt>传真：</dt>
									<dd>${companyInfoUser.userFax }</dd>
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