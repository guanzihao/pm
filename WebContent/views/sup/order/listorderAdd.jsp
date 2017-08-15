<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="" style="width:32%;margin-left:10px;float:left;margin-top:10px;margin-bottom:10px;">订单号：${order.orderCode } </div>
	                <div class="" style="width:32%;float:left;margin-top:10px;margin-bottom:10px;">订单类型：${flowType.flowName } </div>
	                <div class="" style="width:32%;float:left;margin-top:10px;margin-bottom:10px;">订单时间：<fmt:formatDate value="${order.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/> </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sup/order/add/listorderadd/${orderid }"  method="post">
								<%-- <div class="row">
		                            <div class="col-sm-12">
		                                <span class="tables_search_label">
		                                	关键字：<input type="text" class="input-sm form-control my_input" name="searchName2" value="${pageBean.searchBean.searchName2 }">
		                                </span> 
		                            </div>
		                        </div> --%>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayerSize('添加','${basePath }/sup/order/add/editlistorderadd/${orderid }',true,'800px','700px')">
												 添加
											</a>
									</div>
								</div>
								<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
								<div style="border:1px solid #CCC;margin-top:10px;border-radius:10px 10px;">
									<div style="float:left;width:50%">创建人：<pm:execute id="user" bean="companyInfoBusinImpl" method="getUserAccount">
															<pm:execute-param type="java.lang.String" value="${item[3]}" />
														</pm:execute>
														${user.userName }
														
														
														<pm:execute id="users" bean="companyInfoBusinImpl" method="getCompanyInfoUser">
															<pm:execute-param type="java.lang.String" value="${item[3]}" />
														</pm:execute>
														${users.userName }</div>
									<div style="float:right;width:50%">创建时间：<fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/>
									
										
										<div style="float:right">
											<c:choose>
											 	<c:when test="${users.companyInfo != null }">
													《客户》
												</c:when>
												<c:when test="${users.supCompanyInfo != null }">
													《服务商》
												</c:when>
												<c:when test="${user != null }">
												《客服》
												</c:when>
												
												
											</c:choose>
										</div>
									</div>
									<div>内容：${item[5] }</div>
									<div>附件：<pm:execute id="orderFrom" bean="orderFromBusinImpl" method="getOrderAddtion">
															<pm:execute-param type="java.lang.String" value="${item[0]}" />
														</pm:execute>
														<pm:fileList metaObject="${orderFrom}" delete="false" metaColums="colums" /></div>
								</div>
								<div>
									<c:if test="${item[3] == ids }">
										<a class="" href="javascript:;" onclick="openLayerSize('维护','${basePath }/sup/order/add/editlistorderadd/${item[0]}',true,'800px','500px')">
											<span> 维护</span>
										</a>
										<a class="" href="javascript:;" onclick="openLayerSize('维护','${basePath }/sup/order/add/removelistorderadd/${item[0]}',true,'800px','500px')">
											<span> 删除</span>
										</a>
									</c:if>
									<%-- <a class=""  href="javascript:;" onclick="openLayerSize('维护','${basePath }/sup/order/add/viewlistorderadd/${item[0]}',true,'820px','500px')">
										<span>查看</span>
									</a> --%>
								</div>
								</c:forEach>
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