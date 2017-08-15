<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5 style="margin-top:4px;">产品目录管理</h5>
	                    <small style="margin-top:4px;">（操作前，请检查信息是否正确。删除前，请从最下级开始删除）</small>
	                </div>
	                <div class="ibox-content" style="height: 580px;">
						<form id="pageForm1" action="${basePath }/sup/product/productType/saveBindRoleDept" method="post">
							<div class="row" style="margin-bottom:10px">
								<div class="col-sm-12 tables_search_label" >
									<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTree('${basePath }/sup/product/productType/addProductType', 'pageForm1', false)">
										<span> 添加(目录)</span>
									</a>
									<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="toolTree('${basePath }/sup/product/productType/editProductType', 'pageForm1', true)">
										<span> 修改(目录)</span>
									</a>
									<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="removeToolTree('${basePath }/sup/product/productType/removeProductType', 'pageForm1', true)">
										<span> 删除(目录)</span>
									</a>
								</div>
							</div>										
							<div class="col-sm-3">
	                        	<c:import url="/views/product/productType/include/includeProductTypeTree.jsp">
									<c:param name="name" value="productTypeId"/>
									<c:param name="treeData" value="${treeData }"/>
							    </c:import>
		                 	</div>
		                </form>
                        <div class="col-sm-9">
                       		 <iframe id="productList" frameborder="0" style="min-height: 560px !important;width: 100%;" onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>
                        </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>