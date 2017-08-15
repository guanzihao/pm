<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>产品目录选择</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12 tables_search_label">
								<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTree1()">
									<span> 确定</span>
								</a>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<c:import url="/views/product/productType/include/includeProductTypeTree.jsp" >
									<c:param name="name" value="productTypeId" />
									<c:param name="treeData" value="${treeData }"/>
								</c:import>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>