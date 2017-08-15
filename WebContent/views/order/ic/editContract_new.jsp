<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>进口合同信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/order/ic/saveImportContract" method="post">
	                        <input type="hidden" id="taskTypeId" name="taskTypeId" value="${taskTypeId}">
	                        <input type="hidden"  name="taskId" value="${task.id}">
	                        <input type="hidden" name="buyers" value="${companyInFo.id }">
	                        <input type="hidden" id="sellersId" name="sellers" value="">
	                        
                        	<div class="form-group">
	                        	<label class="col-sm-2 control-label">合同号码<font>*</font></label>
	                            <div class="col-sm-10 control-value">${task.taskId }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">合同号码<font>*</font></label>
	                            <div class="col-sm-4 control-value"><input type="text" name="contractNo" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        	<label class="col-sm-2 control-label">合同日期<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="contractDate" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" dateISO="true"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">买方<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" class="form-control " required="" value="${companyInFo.comName }" maxlength="15" ></div>
	                        	<label class="col-sm-2 control-label">买方地址<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" name="bAddress" class="form-control " required="" value="${companyInFo.comWebsite }" maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">买方传真<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" name="bFax" class="form-control" required="" value="${companyInFo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">买方联系方式<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" name="bTelphone" class="form-control" required="" value="${companyInFo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">卖方<font>*</font></label>
	                            <div class="col-sm-3">
									<input id="companyInfoName" readonly="readonly" type="text" readonly="readonly" value=""class="form-control" required="" maxlength="200">
								</div>
								<div class="col-sm-1">
										<button type="button" onclick="SupCompanyInfoOnclick()"  class="btn btn-default">获取值</button>
								</div>
	                        	<label class="col-sm-2 control-label">卖方地址<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" name="sAddress" id="sAddressId" class="form-control " required=""  maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">卖方传真<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" name="sFax" id="sFaxId" class="form-control" required=""  maxlength="200"></div>
	                            <label class="col-sm-2 control-label">卖方联系方式<font>*</font></label>
	                            <div class="col-sm-4"><input readonly="readonly" type="text" name="sTelphone" id="sTelphoneId" class="form-control" required=""  maxlength="50"></div>
	                        </div>
	                         <div class="hr-line-dashed"></div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">装运时间<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="shipmentTime" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" dateISO="true"></div>
	                        	<label class="col-sm-2 control-label">交货时间<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="deliveryTime" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" dateISO="true"></div>
	                        </div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">装运口岸<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="portShipment" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">目的口岸<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="portDestination" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">包装<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="packing" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">保险<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="insurance" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">生产国别及制造厂商<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="manufactory" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">付款条件<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="termPayment" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">检查条款<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="inspection" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">仲裁条款<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="arbitration" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">其他<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="others" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">商品明细<font>*</font></label>
	                        	<div class="col-sm-10">
		                        	<input type="button" id="b1" value="添加一行" onclick="add()" class="btn btn-default"/>
				                       	<table width="700" border="0" cellspacing="0" cellpadding="0" id="addtr">
											  <tr>
											    <td height="30" align="center" bgcolor="#CCCCCC">商品名称</td>
											    <td align="center" bgcolor="#CCCCCC">型号</td>
											    <td align="center" bgcolor="#CCCCCC">数量</td>
											    <td align="center" bgcolor="#CCCCCC">单价</td>
											    <td align="center" bgcolor="#CCCCCC">总价</td>
											  </tr>
											  <tr>
											    <td height="30" align="center">
											    	<select id="kesssId" name="commodityName" onchange="kkk()">
											    		<option>请选择</option>
											    		<c:forEach items="${products}" var="item">
											    			<option value="${item.id }">${item.productName }</option>
											    			 <input type="hidden" id="${item.id }_1" value="${item.productSpec }" />
											    			 <input type="hidden" id="${item.id }_2" value="${item.productPrice }" />
											    		</c:forEach>
											    	</<select>
											    </td>
											    <td align="center"><input type="text" name="models" /></td>
											    <td align="center">
												    <input type="text" name="quantity" />
											    </td>
											    <td align="center"><input type="text" name="unitPrice" /></td>
											    <td align="center"><input type="text" name="totalAmount" /></td>
											  </tr>
										</table>
									</div>
	                        </div>
	                        		
	                        
	                       	 <div class="hr-line-dashed"></div>
	                       	  <div class="hr-line-dashed"></div>
	                       
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit">保存</button>
	                                
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
<script type="text/javascript">
$(document).ready(function (){
	var ids = [${selectList}];
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});

function add(){//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
 var oTr = document.getElementById("addtr").rows[1];
 var newTr = oTr.cloneNode(true);
 document.getElementById("addtr").getElementsByTagName("tbody")[0].appendChild(newTr);
 document.getElementById("b1").disabled = newTr.rowIndex ==20 ;
}

function SupCompanyInfoOnclick(){
	var taskTypeId=$("#taskTypeId").val();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['800px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/sup/task/listCompanyInfo/'+taskTypeId
	});
}
function returnValue(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var strName = selectValues[1];
		var sTelphone = selectValues[2];
		var sAddress = selectValues[3];
		$("#companyInfoName").val(strName);
		$("#sellersId").val(strId);
		$("#sAddressId").val(sAddress);
		$("#sTelphoneId").val(sTelphone);
	}
}
/* function kkk(){
	var kesssId=$("#kesssId").val();
	var aa=$("#"+kesssId+"_1").val();
	var bb=$("#"+kesssId+"_2").val();
} */

</script>
<%@ include file="/include/includeFooter.jsp" %>