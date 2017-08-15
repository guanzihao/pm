<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>机构[部门]管理</h5>
	                    <small>（操作前，请检查信息是否正确。删除前，请从最下级开始删除）</small>
	                </div>
	                <div class="ibox-content">
						<form id="pageForm" action="${basePath }/organize/organiz/saveBindRoleDept" class="form-horizontal formValidate" method="post">
							<div class="row">
								<div class="col-sm-12 tables_search_label">
									<pm:auth authCode="dept_editOrgDept">
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTree('${basePath }/organize/dept/addOrgDept', 'pageForm', false)">
											<span> 添加</span>
										</a>
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTree('${basePath }/organize/dept/editOrgDept', 'pageForm', true)">
											<span> 修改</span>
										</a>
									</pm:auth>
									<pm:auth authCode="dept_removeOrgDept">
										<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTree('${basePath }/organize/dept/removeOrgDept', 'pageForm', true)">
											<span>删除</span>
										</a>
									</pm:auth>
									<pm:auth authCode="dept_editOrgDeptUser">
										<a class="btn btn-white btn-sm" href="javascript:;" onclick="toolTree('${basePath }/organize/dept/editOrgDeptUser', 'pageForm', true)">
											<span> 维护机构[部门]人员</span>
										</a>
									</pm:auth>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
		                        	<c:import url="/include/includeTree.jsp">
										<c:param name="name" value="deptId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
		                        </div>
		                    </div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>