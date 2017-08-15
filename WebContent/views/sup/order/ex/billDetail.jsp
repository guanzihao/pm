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
		                <div class="ibox-content" style="height: 580px;">
							<form id="pageForm1" action="${basePath }" method="post">
								<div class="col-sm-2">
									<c:import url="/views/task/includeTree.jsp">
										<c:param name="name" value="unitInfoId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
			                 	</div>
			                </form>
	                        <div class="col-sm-10">
	                       		 <iframe id="deptUserList"  frameborder="1" width="100%" height="500"></iframe>
	                        </div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>