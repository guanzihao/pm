<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<c:set var="treeId" value="menu_150_6"/>
<link rel="stylesheet" href="${basePath }/resource/css/dingdanguanli_2.css" />
<meta charset="UTF-8">
		<title>报关进口</title>
		<link href="${basePath }/resource/css/font_1435131046_7356622.css" rel="stylesheet" type="text/css">

		<link rel="stylesheet" href="${basePath }/resource/css/dingdanguanli_2.css" />

		<link href="${basePath }/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
		<style type="text/css">
			.hidden-mod {
				display: none
			}
			
			.mo-mod-header-title h1 a {
				text-indent: -5000em;
				background: url();
				background-size: 115px 43px;
				width: 115px;
				height: 43px;
			}
			
			.mo-mod-header-title {
				margin: 10px 0 0 2px;
			}
			
			body {
				-webkit-font-smoothing: auto;
			}
			
			.mo-list-nav .ui2-navigation-item {
				margin-right: 18px;
			}
			
			input[type="text"] {
				height: 30px!important;
			}
			
				th,
			td {
				padding: 5px !important;
			}
			
			.treeColor{
				background-color: #66CDAA;
				color: #FFFFFF;
			}
			.mo-process-action-item .mo-process-mark i:after {
			    content: none;
			    font-size: 12px;
			    color: #CC1414;
			    text-align: center;
			    width:100%;
			}
			
			
		</style>
		<style>
			.zuihou {
				cursor: pointer;
				/*鼠标形状*/
			}
			
			.zuihou:hover {
				border: 1px solid #4F94CD;
			}
			
			/*状态下面的边线*/
			
			.mo-list-head {
				border-bottom: none;
			}
			/*边距*/
			
			.mo-order-list .mo-list-item-order {
				padding-bottom: 0px;
			}
			
			.mo-process-mark:after,.mo-process-mark:before {
			width:94% !important;
			}
			
			
			/*
			 第一个节点
			 * */
			.first{
				text-align: left !important;
			}
			/*
			 最后一个节点
			 * */
			.last{
				text-align: right !important;
			}
		</style>
<body>
	<div class="" style="background-color: #FFFFFF !important;">
		<%-- <%@ include file="/include/includeTop.jsp" %> --%>
		<section>
			<div class="content-wrapper">
				<div class="panel panel-default">
					<div class="panel-heading">
						订单管理
						
					</div>
					<a style="float:right;" class="btn btn-white btn-sm myBtn" onclick="JavaScript:history.back(-1);">
						<span>返回</span>
					</a>
					<div class="panel-body">
						<div id="" style="width: 100%;">
							<div class="mo-list-box mo-order-list ">
									<div class="mo-list-head util-clearfix">
										<div class="mo-flux-100">
											<div class="mo-f-inner-l mo-list-item-order util-clearfix">
												<div class="mo-process mo-order-process">
													<ul class="mo-process-10 util-clearfix">
													<c:forEach items="${orderFrom }" var="item" varStatus="status">
														<c:if test="${status.index <'1' }">
															<li class="mo-process-item
																first 
						
																mo-process-done-item  "  style="width:10%" >
																		<pm:execute id="flow" bean="supplierConsignationQuoteServiceImpl" method="getFlowType">
																				<pm:execute-param type="java.lang.String" value="${item.flowType.id}" />
																		</pm:execute>
																		
																<span class="label" style="font-size: 13px;color: #FF751A;">${flow.flowName}</span>
															</li>
														</c:if>
														<c:if test="${status.index >'0' }">
															<li class="mo-process-item
																mo-process-done-item   " style="width:10%" >
																		<pm:execute id="flow" bean="supplierConsignationQuoteServiceImpl" method="getFlowType">
																				<pm:execute-param type="java.lang.String" value="${item.flowType.id}" />
																		</pm:execute>
																		
																<span class="label" style="font-size: 13px;color: #FF751A;">${flow.flowName}</span>
															</li>
														</c:if>
													</c:forEach>
													<li class="mo-process-item
															last 
															mo-process-unstart-item 
															mo-process-action-item 
															mo-process-current-item  "  style="width:10%" >
														<span class="label" style="font-size: 13px;color: #FF751A;">任务进度</span>
													</li>	
													</ul>
												</div>
											</div>
										</div>
									</div>
									<div class="mo-list-content">
										<div class="mo-list-item util-clearfix">
											<div class="mo-flux-100">
												<div class="mo-f-inner-l mo-list-item-order util-clearfix">
													<ul class="mo-order-base">
														<li><strong>订单：${task.taskId}</strong></li>
													</ul>
													<div class="mo-process mo-order-process">
														<ul class="mo-process-10 util-clearfix">
															<c:forEach items="${orderFrom }" var="item" varStatus="status">
																<c:if test="${status.index <'1' }">
																	<c:if test="${item.orderState eq '15'}">
																		<li class="mo-process-item
																			first 
																				mo-process-done-item
																				mo-process-current-item 
																			 " style="width:10%">
																				<div class="mo-process-mark">
																					<i id="ss" class="ui2-icon ui2-icon-dot"></i>
																				</div>
																				
																				<div class="mo-process-content">
																					<div class="mo-process-text" data-desc="">
																						<a href="#" title="进行中">进行中</a>
																					</div>
																					<div class="mo-process-detail">
																						<div class="mo-process-detail-inner">
																						</div>
																					</div>
																				</div>
																			</li>
																		</c:if>
																		<c:if test="${item.orderState eq '7'}">
																		<li class="mo-process-item
																			first 
																				mo-process-done-item
																				mo-process-current-item 
																			 " style="width:10%">
																				<div class="mo-process-mark">
																					<i id="ss" class="ui2-icon ui2-icon-dot"></i>
																				</div>
																				
																				<div class="mo-process-content">
																					<div class="mo-process-text" data-desc="">
																						<a href="#" title="进行中">已完成</a>
																					</div>
																					<div class="mo-process-detail">
																						<div class="mo-process-detail-inner">
																						</div>
																					</div>
																				</div>
																			</li>
																		</c:if>
																		<c:if test="${item.orderState != '15'}">
																			<c:if test="${item.orderState != '7'}">
																			<li class="mo-process-item
																			first
																			mo-process-done-item 
																			 " style="width:10%">
																				<div class="mo-process-mark">
																					<i class="ui2-icon ui2-icon-dot"></i>
																				</div>
																				
																				<div class="mo-process-content">
																					<div class="mo-process-text" data-desc="">
																						<a href="#" title="进行中">已完成</a>
																					</div>
																					<div class="mo-process-detail">
																						<div class="mo-process-detail-inner">
																						</div>
																					</div>
																				</div>
																			</li>
																			</c:if>
																		</c:if>
																	</c:if>
																	<c:if test="${status.index >'0' }">
																		<c:if test="${item.orderState eq '15'}">
																			<li class="mo-process-item
																					mo-process-done-item 
																					mo-process-current-item 
																			 " style="width:10%">
																				<div class="mo-process-mark">
																					<i class="ui2-icon ui2-icon-dot"></i>
																				</div>
																				
																				<div class="mo-process-content">
																					<div class="mo-process-text" data-desc="">
																						<a href="#" title="进行中">进行中</a>
																					</div>
																					<div class="mo-process-detail">
																						<div class="mo-process-detail-inner">
																						</div>
																					</div>
																				</div>
																			</li>
																		</c:if>
																		<c:if test="${item.orderState eq '7'}">
																			<li class="mo-process-item
																			mo-process-done-item 
																					mo-process-current-item 
																			 " style="width:10%">
																				<div class="mo-process-mark">
																					<i class="ui2-icon ui2-icon-dot"></i>
																				</div>
																				
																				<div class="mo-process-content">
																					<div class="mo-process-text" data-desc="">
																						<a href="#" title="进行中">已完成</a>
																					</div>
																					<div class="mo-process-detail">
																						<div class="mo-process-detail-inner">
																						</div>
																					</div>
																				</div>
																			</li>
																		</c:if>
																		<c:if test="${item.orderState != '15'}">
																			<c:if test="${item.orderState != '7'}">
																			<li class="mo-process-item
																			mo-process-done-item 
																			 " style="width:10%">
																				<div class="mo-process-mark">
																					<i class="ui2-icon ui2-icon-dot"></i>
																				</div>
																				
																				<div class="mo-process-content">
																					<div class="mo-process-text" data-desc="">
																						<a href="#" title="进行中">已完成</a>
																					</div>
																					<div class="mo-process-detail">
																						<div class="mo-process-detail-inner">
																						</div>
																					</div>
																				</div>
																			</li>
																			</c:if>
																		</c:if>
																	</c:if>
															</c:forEach>
															<c:if test="${task.taskState != 15}">
																<li class="mo-process-item
																	last 
																	mo-process-done-item 
																	 " style="width:10%">
																	<div class="mo-process-mark">
																		<i class="ui2-icon ui2-icon-dot"></i>
																	</div>
																	<div class="mo-process-content">
																		<div class="mo-process-text" data-desc="">
																			<a href="#" title="进行中">已完成</a>
																		</div>
																		<div class="mo-process-detail">
																			<div class="mo-process-detail-inner"></div>
																		</div>
																	</div>
																</li>
															</c:if>
															<c:if test="${task.taskState eq 15}">
																<li class="mo-process-item
																	last 
																	mo-process-done-item 
																	mo-process-current-item 
																	 " style="width:20%">
																	<div class="mo-process-mark">
																		<i class="ui2-icon ui2-icon-dot"></i>
																	</div>
																	<div class="mo-process-content">
																		<div class="mo-process-text" data-desc="">
																			<a href="#" title="进行中">已完成</a>
																		</div>
																		<div class="mo-process-detail">
																			<div class="mo-process-detail-inner"></div>
																		</div>
																	</div>
																</li>
															</c:if>
														</ul>
													</div>
														<div class="mo-order-type"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
							</div>
		                <div class="ibox-content">
							<form id="pageForm1" action="${basePath }" method="post">
								<div class="col-sm-3">
									<c:import url="/views/sup/task/includeTree.jsp">
										<c:param name="name" value="unitInfoId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
			                 	</div>
			                </form>
	                        <div class="col-sm-9">
	                        	<iframe runat="server" id="customerTask" src="${basePath}/sup/task/listTaskDispose/${task.id}/1/${task.tackType}"  width="100%" style="min-height: 880px;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
	                        </div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<!--
        	iframe 自适应高度
        -->
		<script type="text/javascript">
			function reinitIframe() {
				var iframe = document.getElementById("customerTask");
				try {
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					iframe.height = height;
				} catch(ex) {}
			}
			window.setInterval("reinitIframe()", 200);
		</script>
	
	
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>