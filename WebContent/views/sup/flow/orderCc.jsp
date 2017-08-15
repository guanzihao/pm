<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<body class="gray-bg">
  	<div class="wrapper wrapper-content animated fadeIn">
	    	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5 style="margin-left:20px;margin-top:6px;">单据信息</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
			                  <form id="pageForm" class="form-horizontal formValidate" action="${basePath }/sup/orderCccontroller/orderCc/${orderid.id}/${type}" method="post">
			                  		<div class="row">
			                           <div class="col-sm-12" style="margin-left:20px;">
			                               <label class="tables_search_label">
			                                      	 单据名称:<input style="margin-left: 5px;" type="text" class="input-sm form-control" name="searchName1" value="${pageBean.searchBean.searchName1 }">
			                                       <button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
			                               </label>
			                           </div>
			                       </div>
			                       <div class="table-responsive">
										<table class="table table-striped table-bordered table-hover dataTable">
							                <thead>
												<tr>
													<th>订单号</th>
													<th>单据名称</th>
												</tr>
											</thead>
							                <tbody>
							                	<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
													<tr id="toolTr_${item[0]}">
														<td>${orderid.orderCode}</td>
														<td>
																	<pm:authCom authCode="noticeinformed_editComNoticeInformed">
																		<a class="" href="${basePath }${cctype}/${item[0]}">
																			<span>${item[1]}</span>
																		</a>
																	</pm:authCom>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
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