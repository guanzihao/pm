<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<c:set var="treeId" value="menu_150_6"/>
<body>
	<div class="wrapper">
		<%-- <%@ include file="/include/includeTop.jsp" %> --%>
		<section>
			<div class="content-wrapper">
            	<div class="content-heading">任务管理</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						任务管理
					</div>
					<div class="panel-body">
		                <div class="ibox-content" style="height: 100%;">
							<form id="pageForm1" action="${basePath }" method="post">
								<div class="col-sm-2">
									<c:import url="/views/task/includeTree.jsp">
										<c:param name="name" value="unitInfoId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
			                 	</div>
			                </form>
	                        <div class="col-sm-10">
	                       		 <iframe id="deptUserList"  frameborder="1" width="100%" height="500px;"></iframe>
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
				var iframe = document.getElementById("J_iframe");
				try {
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					iframe.height = height;
					console.log(height);
				} catch(ex) {}
			}
			window.setInterval("reinitIframe()", 200);
		</script>
	
	
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>