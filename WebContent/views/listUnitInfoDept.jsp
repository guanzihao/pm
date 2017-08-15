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
						部门管理
					</div>
					<div class="panel-body">
		                <div class="ibox-content" style="height: 580px;">
							<form id="pageForm1" action="${basePath }/buy/buyunitinfo/saveUnitInfoDept" method="post">
								<div class="row">
									<div class="col-sm-12 tables_search_label">
										<a class="btn btn-default btn-sm" href="javascript:;" onclick="addUnitInfoDept('${basePath }/buy/buyunitinfo/addUnitInfoDept', 'pageForm1')">
											<span>添加</span>
										</a>
										<a class="btn btn-default btn-sm" href="javascript:;" onclick="editUnitInfoDept('${basePath }/buy/buyunitinfo/editUnitInfoDept', 'pageForm1')">
											<span>修改</span>
										</a>
										<a class="btn btn-default btn-sm" href="javascript:;" onclick="removeUnitInfoDept('${basePath }/buy/buyunitinfo/removeUnitInfoDept', 'pageForm1')">
											<span>删除</span>
										</a>
									</div>
								</div>										
								<div class="col-sm-3">
									<c:import url="/include/includeTree.jsp">
										<c:param name="name" value="unitInfoId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
			                 	</div>
			                </form>
	                        <div class="col-sm-9">
	                       		 <iframe id="deptUserList" frameborder="1" width="100%" height="500"></iframe>
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