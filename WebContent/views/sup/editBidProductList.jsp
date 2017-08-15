<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<script type="text/javascript">
	var sp=1;
	function del(id){
		var tr = document.getElementById(id);
		productSelect.deleteRow(tr.rowIndex);
	}
	var priceType = ${bidProject.priceType};
	function addnewrow(){
		var tr = document.all.productSelect.insertRow(document.all.productSelect.rows.length);
		tr.id = "productSelectTab" + sp;
		tr.className="row1";
		td = tr.insertCell(0);
		td.innerHTML = "<input type='text' class='form-control'  name='codeStr"+sp+"'>";
		td = tr.insertCell(1);
		td.innerHTML = "<input type='hidden' name='ids' value='"+sp+"'><input type='text' class='form-control' required='' name='nameStr"+sp+"'>";
		td = tr.insertCell(2);
		td.innerHTML = "<input type='text' class='form-control' name='specStr"+sp+"'>";
		td = tr.insertCell(3);
		td.innerHTML = "<input type='text' class='form-control' required='' number='true' name='numStr"+sp+"'>";
		td = tr.insertCell(4);
		td.innerHTML = "<div class='input-group'>"+
							"<input type='text' class='form-control' required='' id='unitStr"+sp+"' name='unitStr"+sp+"'>"+
							"<div class='input-group-btn'>"+
				        	"<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span></button>"+
				        	"<ul class='dropdown-menu dropdown-menu-right' role='menu'>"+
						        "<c:forEach var='numberUnit' items='${numberUnitList }'>"+
							        "<li><a onclick=\"fuzhi('#unitStr"+sp+"','${numberUnit.numberName }')\" href=\"javascript:;\">${numberUnit.numberName }</a></li>"+
						        "</c:forEach>"+
				        	"</ul>"+
				    	"</div>"+
				    "</div>";
		/* == 2按照单间排名 有限价 */
		if(priceType == 2)
		{
			td = tr.insertCell(5);
			td.innerHTML = "<input type='text' class='form-control' number='true' name='priceStr"+sp+"'>";
			td = tr.insertCell(6);
			td.innerHTML = "<input type='text' class='form-control' name='productText"+sp+"'>";
			td = tr.insertCell(7);
			td.innerHTML = "<a class='btn btn-white btn-sm' href=javascript:del('productSelectTab"+sp+"')><span><i class='fa fa-trash red'></i> </span></a>";
		}else{
			td = tr.insertCell(5);
			td.innerHTML = "<input type='text' class='form-control' name='productText"+sp+"'>";
			td = tr.insertCell(6);
			td.innerHTML = "<a class='btn btn-white btn-sm' href=javascript:del('productSelectTab"+sp+"')><span><i class='fa fa-trash red'></i> </span></a>";
		}
		sp++;
	}

	function returnValue(products){
   		for(i=0; i< products.length; i++)
   		{
   			var product = products[i];
   			if(product[0] != "" && product[1]!="undefined")
   			{
				var tr = document.all.productSelect.insertRow(document.all.productSelect.rows.length);
				tr.id = "productSelectTab" + sp;
				tr.className="row1";
				td = tr.insertCell(0);
				td.innerHTML = "<input type='text' class='form-control'  value='"+product[2]+"' name='codeStr"+sp+"'>";
				td = tr.insertCell(1);
				td.innerHTML = "<input type='hidden' name='ids' value='"+sp+"'><input type='text' class='form-control' required=''  value='"+product[1]+"'   name='nameStr"+sp+"'>";
				td = tr.insertCell(2);
				td.innerHTML = "<input type='text' class='form-control' value='"+product[3]+"' name='specStr"+sp+"'>";
				td = tr.insertCell(3);
				td.innerHTML = "<input type='text' class='form-control' required='' number='true' name='numStr"+sp+"'>";
				td = tr.insertCell(4);
				td.innerHTML = "<div class='input-group'>"+
									"<input type='text' class='form-control' value='"+product[4]+"' required='' id='unitStr"+sp+"' name='unitStr"+sp+"'>"+
									"<div class='input-group-btn'>"+
						        	"<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span></button>"+
						        	"<ul class='dropdown-menu dropdown-menu-right' role='menu'>"+
								        "<c:forEach var='numberUnit' items='${numberUnitList }'>"+
									        "<li><a onclick=\"fuzhi('#unitStr"+sp+"','${numberUnit.numberName }')\" href=\"javascript:;\">${numberUnit.numberName }</a></li>"+
								        "</c:forEach>"+
						        	"</ul>"+
						    	"</div>"+
						    "</div>";
				/* == 2按照单间排名 有限价 */
				if(priceType == 2)
				{
					td = tr.insertCell(5);
					td.innerHTML = "<input type='text' class='form-control' number='true' name='priceStr"+sp+"'>";
					td = tr.insertCell(6);
					td.innerHTML = "<input type='text' class='form-control'  name='productText"+sp+"'>";
					td = tr.insertCell(7);
					td.innerHTML = "<a class='btn btn-white btn-sm' href=javascript:del('productSelectTab"+sp+"')><span><i class='fa fa-trash red'></i> </span></a>";
				}else{
					td = tr.insertCell(5);
					td.innerHTML = "<input type='text' class='form-control'  name='productText"+sp+"'>";
					td = tr.insertCell(6);
					td.innerHTML = "<a class='btn btn-white btn-sm' href=javascript:del('productSelectTab"+sp+"')><span><i class='fa fa-trash red'></i> </span></a>";
				}
				sp++;
   			}
   		}
	}
	
	function checkForm(){
		var subitemIds = document.getElementsByName('ids');
		var exp = /^[0-9]+\.?[0-9]*$/;
		var hasPrice = false;
		if(priceType == 2){
			for(i=0;i<subitemIds.length;i++){
				var subitemId = subitemIds[i].value;
				var priceStr = $("input[name=priceStr"+subitemId+"]").val();
				if (priceStr != "" && priceStr !=0) {
					hasPrice = true;
					break;
				}
			}
		}
		
		for(i=0;i<subitemIds.length;i++){
			var subitemId = subitemIds[i].value;
			var nameStr = $("input[name=nameStr"+subitemId+"]").val();
			var numStr = $("input[name=numStr"+subitemId+"]").val();
			var unitStr = $("input[name=unitStr"+subitemId+"]").val();
			if (nameStr == "") {
				layer.msg("产品名称不能为空,请检查");
				return false;
			}
			if (numStr == "") {
				layer.msg("数量不能为空,请检查");
				return false;
			}else {
				if (numStr == 0) {
					layer.msg("数量必须大于0,请检查");
					return false;
				}else{
					if(!exp.test(numStr)) {
						layer.msg('数量格式错误,请检查');
						return false;
					}
				}
			}
			if (unitStr == "") {
				layer.msg("单位不能为空,请检查");
				return false;
			}
			
			if(priceType == 2){
				if(hasPrice){
					var priceStr = $("input[name=priceStr"+subitemId+"]").val();
					if (priceStr == "") {
						layer.msg("限价不能为空,请检查");
						return false;
					}else {
						if (priceStr == 0) {
							layer.msg("限价必须大于0,请检查");
							return false;
						}else{
							if(!exp.test(priceStr)) {
								layer.msg('限价格式错误,请检查');
								return false;
							}
						}
					}
				}
			}	
		}
	}
</script>
<script type="text/javascript">
	 function selectProduct(){
			layer.open({
				title: '选择产品',
			    type: 2,
			    area: ['80%', '90%'],
			    fix: false, //不固定
			    maxmin: true,
			    content: '${basePath}/product/selectProduct/selectProductMultiple',
			});
	};
</script>
<body class="gray-bg" oncontextmenu="self.event.returnValue=false">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-sm-12 tables_search_label">
	        	<a class="btn btn-success" href="${basePath }/bidproject/bidproject/editBidProject/${bidProject.id}">
					<span>第一步：基本信息&nbsp;&nbsp;》</span>
				</a>
				<a class="btn btn-info" href="javascript:;">
					<span>第二步：产品信息&nbsp;&nbsp;》</span>
				</a>
				<a class="btn ${bidProject.bidRules != null ? 'btn-success' : 'btn-default'}" href="${basePath }/bidproject/bidrules/editBidRules/${bidProject.id}">
					<span>第三步：报价规则</span>
				</a>
	        </div>
		</div>
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>编辑产品信息</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper">
							<form id="pageForm" class="form-horizontal" action="${basePath }/bidproject/bidproduct/saveBidProductList" method="post" onsubmit="return checkForm()">
								<input type="hidden" name="bidProjectId" value="${bidProject.id }">
								<c:if test="${bidProject.priceType == 1 || bidProject.bidIssms == 2}">
									<div class="row">
										<div class="col-sm-12 tables_search_label">
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="addnewrow();">
												<span><i class="fa fa-plus"></i> 添加一行</span>
											</a>
											<a class="btn btn-white btn-sm" href="javascript:;" onclick="selectProduct()">
												<span><i class="fa fa-plus"></i> 选择产品</span>
											</a>
											<a class="btn btn-white btn-sm" href="${basePath }/bidproject/bidproduct/importBidProduct/${bidProject.id}">
												<span><i class="fa fa-plus"></i> 导入产品</span>
											</a>
										</div>
									</div>
								</c:if>
								<table id="productSelect" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>产品编号</th>
											<th><font>*</font>产品名称</th>
											<th>规格型号</th>
											<th><font>*</font>数量</th>
											<th width="10%"><font>*</font>单位</th>
											<!-- == 2按照单间排名 有限价 -->
											<c:if test="${bidProject.priceType == 2 }">
												<th>限价</th>
											</c:if>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty bidProject.bidProducts }">
												<c:forEach items="${bidProject.bidProducts }" var="item" varStatus="status">
													<tr>
														<td><input type="text" class="form-control" name="codeStr"></td>
														<td>
															<input type="hidden" name="ids" value="${item.id }">
															<input type="text" class="form-control" name="nameStr${item.id }" required="" value="${item.productName }">
														</td>
														<td><input type="text" class="form-control" name="specStr${item.id }" value="${item.productSpec }"></td>
														<td><input type="text" class="form-control" name="numStr${item.id }"  required="" number="true" value="<fmt:formatNumber value="${item.productNum }" pattern="#0.00"/>"></td>
														<td>
															<div class="input-group">
														    	<input type="text" class="form-control" id="unitStr${item.id }" name="unitStr${item.id }" required="" value="${item.productUnit }">
														    	<div class="input-group-btn">
														        	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
														        	<ul class="dropdown-menu dropdown-menu-right" role="menu">
																        <c:forEach var="numberUnit" items="${numberUnitList }">
																	        <li><a href="javascript:;" onclick="fuzhi('#unitStr${item.id }','${numberUnit.numberName }')">${numberUnit.numberName }</a></li>
																        </c:forEach>
														        	</ul>
														    	</div>
														    </div>
														</td>
														<!-- == 2按照单间排名 有限价 -->
														<c:if test="${bidProject.priceType == 2 }">
															<td><input type="text" class="form-control" name="priceStr${item.id }" number="true" value="<fmt:formatNumber value="${item.productPrice }" pattern="#0.00"/>"></td>
														</c:if>
														<td><input type="text" class="form-control" name="productText${item.id }" value="${item.productText }"></td>
														<td></td>
													</tr>
												</c:forEach>
											
											</c:when>
											<c:otherwise>
												<c:if test="${bidProject.priceType == 2 && bidProject.bidIssms == 1}">
													<tr>
														<td><input type="text" class="form-control" name="codeStr1"></td>
														<td>
															<input type="hidden" name="ids" value="1">
															<input type="text" class="form-control" name="nameStr1" required="">
														</td>
														<td><input type="text" class="form-control" name="specStr1"></td>
														<td><input type="text" class="form-control" name="numStr1"  required="" number="true"></td>
														<td>
															<div class="input-group">
														    	<input type="text" class="form-control" id="unitStr1" name="unitStr1" required="">
														    	<div class="input-group-btn">
														        	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
														        	<ul class="dropdown-menu dropdown-menu-right" role="menu">
																        <c:forEach var="numberUnit" items="${numberUnitList }">
																	        <li><a href="javascript:;" onclick="fuzhi('#unitStr1','${numberUnit.numberName }')">${numberUnit.numberName }</a></li>
																        </c:forEach>
														        	</ul>
														    	</div>
														    </div>
														</td>
														<!-- == 2按照单间排名 有限价 -->
														<c:if test="${bidProject.priceType == 2 }">
															<td><input type="text" class="form-control" name="priceStr1" number="true"></td>
														</c:if>
														<td><input type="text" class="form-control" name="productText1"></td>
														<td></td>
													</tr>
												</c:if>
											</c:otherwise>
										</c:choose>
									</tbody>	
								</table>
								<div class="form-group">
		                            <div class="col-sm-12 col-sm-offset-2">
		                                <button class="btn btn-primary" type="submit">下一步</button>
		                                
		                            </div>
		                        </div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
function fuzhi(vid,zhi)
{
	$(vid).val(zhi);
}
</script>
<%@ include file="/include/includeFooter.jsp" %>