<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>


<div class="container">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="height: 30px;padding: 6px;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                         &times;
                   </button>
						<h4 class="modal-title" id="myModalLabel">
                     
                   </h4>
					</div>
					<div class="modal-body" style="width: 600px;text-align: center;font-size: 16px;">

						<p>您已成功下单，请等待客户为您分配服务商，届时服务商将主动跟您联系</p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default fhsy" data-dismiss="modal">
									                      	返回首页
									                    </button>
						<button type="button" class="btn btn-primary ljxd" >
									                      	在线下单
									                    </button>
						<button type="button" class="btn btn-primary ddgl">
									                      	订单管理
									                    </button>

					</div>
				</div>
			</div>
		</div>
</div>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".fhsy").click(function(){
			window.location.href='${basePath }/sup/task/orderPrep';
		});
		$(".ljxd").click(function(){
			window.location.href='${basePath }/sup/task/orderPrep';
		});
		$(".ddgl").click(function(){
			window.location.href='${basePath }/sup/task/orderPrep';
		});
	});
</script>