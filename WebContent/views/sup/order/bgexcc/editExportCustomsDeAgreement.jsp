<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<style>
<!--
.ibox-title {
	min-height: 0px;
}

label {
	font-weight: 400 !important;
	width: 99px !important;
	text-align: right;
	margin-top: 6px;
}

select{
	height: 34px;
	width: 216px;
}

.col-xs-4{
	padding-left: 6px;
}

.ired{
	color:red;
	margin: 5px;
}

-->
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>委托进口报关协议</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/order/ExportCustomsDeAgreementexcc/saveExportCustomsDeAgreement" method="post">
	                        <input type="hidden" id="taskTypeId" name="taskTypeId" value="${taskTypeId}">
	                        <input type="hidden"  name="taskId" value="${task.id}">
                        	<div class="form-group">
                        	<div class="col-xs-2">
	                        	<label class=" control-label">合同号码</label>
	                        	</div>
	                            <div class="col-xs-4 control-value">${task.taskId }</div>
	                        </div>
	                        
	                        
	                        <div class="form-group" style="display:none;">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">合同号码</label>
	                        	</div>
	                            <div class="col-xs-4 control-value"><input type="text" name="contractNo" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        	<div class="col-xs-2">
	                        	<label class=" control-label">合同日期</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="contractDate" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" dateISO="true"></div>
	                        </div>
	                        
	                        
	                         <div class="form-group">
	                         <div class="col-xs-2">
	                        	<label class=" control-label">委托方</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="consignor" class="form-control " required="" value="${companyInFo.comName }" maxlength="15" ></div>
	                        	<div class="col-xs-2">
	                        	<label class=" control-label">货物名称</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="goodsName" class="form-control " required="" value="" maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">HS编码</label>
	                        	</div>
	                            <div class="col-xs-4"><input  type="text" name="hsCode" class="form-control" required="" value="" maxlength="200"></div>
	                           
	                           <div class="col-xs-2">
	                            <label class=" control-label">重量</label>
	                            </div>
	                            <div class="col-xs-4"><input  type="text" name="grossweight" class="form-control" required="" value="" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">包装种类</label>
	                        	</div>
	                            <div class="col-xs-4"><input  type="text" name="packDetail" class="form-control" required="" value="" maxlength="50"></div>
	                        	<div class="col-xs-2">
	                        	<label class=" control-label">合同号</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="contractNo" id="sAddressId" class="form-control " required=""  maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">许可文件号</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="licenseFileNo" id="sFaxId" class="form-control" required=""  maxlength="200"></div>
	                           <div class="col-xs-2">
	                            <label class=" control-label">货物总价	</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="goodsTotalPrice" id="sTelphoneId" class="form-control" required=""  maxlength="50"></div>
	                        </div>
	                         
	                        
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">进出口日期</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="imExDate" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" ></div>
	                        	<div class="col-xs-2">
	                        	<label class=" control-label">提单号</label>
	                        	</div>
	                            <div class="col-sm-4"><input type="text" name="deliveryNumber" class="form-control" required="" value="" maxlength="15" ></div>
	                        </div>
	                        
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">贸易方式</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="tradeType" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                           <div class="col-xs-2" style="padding-left: 0px;">
	                            <label class=" control-label">原产地/货源地</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="originPlace" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        
	                         <div class="form-group">
	                         <div class="col-xs-2">
	                        	<label class=" control-label">其他要求</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="others" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                           <div class="col-xs-2">
	                            <label class=" control-label">被委托方</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="consignee" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                         <div class="col-xs-2">
	                        	<label class=" control-label">报关单编码</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="customsCode" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <div class="col-xs-2" style="padding-left: 0px;">
	                            <label class=" control-label">收到单证日期</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="receiveDocsDate" class="form-control date-picker" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2" style="padding-left: 0px;">
	                        	<label class=" control-label">收到单证情况</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="receiveDocsCondition" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <div class="col-xs-2" style="padding-left: 0px;">
	                            <label class=" control-label">其他单证情况</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="docsOthers" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        <div class="col-xs-2">
	                        	<label class=" control-label">报关收费</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="customsCharge" class="form-control" required="" value="${companyInfo.consignee }" maxlength="200"></div>
	                           <div class="col-xs-2">
	                            <label class=" control-label">承诺说明</label>
	                            </div>
	                            <div class="col-xs-4"><input type="text" name="comIllustration" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                         <div class="col-xs-2">
	                        	<label class=" control-label">客户Id</label>
	                        	</div>
	                            <div class="col-xs-4"><input type="text" name="userId" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                        </div>
	                        
	                        
	                       	 
	                       	  
	                       
	                        <div class="form-group" style="margin-left: 10px;">
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

</script>
<%@ include file="/include/includeFooter.jsp" %>