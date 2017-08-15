<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
  
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>平台订单统计表</h5>
	                </div>
	                <div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="#" class="form-horizontal formValidate" method="post">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>订单号</th>
												<th>客户</th>
												<th>服务商</th>
												<th>服务商负责人</th>
												<th>下单时间 </th>
												<th>期望完成时间 </th>
												<th>实际完成时间 </th>
												<th>类型</th>
												
											</tr>
										</thead>
										<c:forEach items="${pageBean.pageList }" var="item"
												varStatus="status">
												<tr class="parent">
													<td>${item[0] }</td>
													<td>${item[1] }</td>
													<td>${item[2] }</td>
													<td> 无</td>
													<td><fmt:formatDate value="${item[3] }" pattern="yyyy-MM-dd"/></td>
													<td><fmt:formatDate value="${item[4] }" pattern="yyyy-MM-dd"/></td>
													<td><fmt:formatDate value="${item[5] }" pattern="yyyy-MM-dd"/></td>
													<td>${statusName }</td>
												</tr>
											</c:forEach>
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
<script type="text/javascript">
$(function(){
	var close=$("#closeId").val();
	if (close=='close') {
		parent.layer.msg('订单分配成功');
		parent.window.location.reload();
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	}
});
</script>
