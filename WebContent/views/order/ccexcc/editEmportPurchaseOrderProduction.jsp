<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>仓储出口</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/order/ExportOrderFrom/saveExportOrderFrom" method="post">
	                        <input type="hidden" id="taskTypeId" name="taskTypeId" value="${taskTypeId}">
	                        <input type="hidden"  name="taskId" value="${task.id}">
                        	<div class="form-group">
	                        	<label class="col-sm-2 control-label">合同号码<font>*</font></label>
	                            <div class="col-sm-10 control-value">${task.taskId }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        
	                        <div class="form-group" style="display:none;">
	                        	<label class="col-sm-2 control-label">合同号码<font>*</font></label>
	                            <div class="col-sm-4 control-value"><input type="text" name="contractNo" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        	<label class="col-sm-2 control-label">合同日期<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="contractDate" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" dateISO="true"></div>
	                        </div>
	                        
	                        
	                        
	                        
	                        <div class="hr-line-dashed"></div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">买方<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="buyer" class="form-control " required="" value="${companyInFo.comName }" maxlength="15" ></div>
	                        	<label class="col-sm-2 control-label">买方地址<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="bAddress" class="form-control " required="" value="" maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">发布日期<font>*</font></label>
	                            <div class="col-sm-4"><input  type="text" name="issueDate" class="form-control date-picker" required="" value="" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">项目<font>*</font></label>
	                            <div class="col-sm-4"><input  type="text" name="project" class="form-control" required="" value="" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">买方电话<font>*</font></label>
	                            <div class="col-sm-4"><input  type="text" name="bTelphone" class="form-control" required="" value="" maxlength="50"></div>
	                        	<label class="col-sm-2 control-label">买方传真<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="bFax" id="sAddressId" class="form-control " required=""  maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">供应商<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="supplier" id="sFaxId" class="form-control" required=""  maxlength="200"></div>
	                            <label class="col-sm-2 control-label">供应商编号	<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="supplierNo" id="sTelphoneId" class="form-control" required=""  maxlength="50"></div>
	                        </div>
	                         <div class="hr-line-dashed"></div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">进出口日期<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="imExDate" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" ></div>
	                        	<label class="col-sm-2 control-label">联系人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="contactPerson" class="form-control" required="" value="" maxlength="15" ></div>
	                        </div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">联系人地址<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cpAddress" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">联系人电话<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cpTelephone" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">联系人传真<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cpFax" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">合同期限<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="contractTerm" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">交付日期<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="deliveryDate" class="form-control date-picker" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">交付说明和交付地点<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="instrDestination" class="form-control " required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">交付条款<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="paymentTerm" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">国际贸易条件	<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="tradeCondition" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">相关文件<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="refDocuments" class="form-control" required="" value="${companyInfo.consignee }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">项目产能要求/LCR<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="pcl" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">明细编号<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="detailNo" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">币种<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="currency" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">工装费用<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="toolingCost" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">支付方式<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="payWay" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">工装寿命<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name=toolingLife class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">分摊量<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="shareAmount" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">分摊单价<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name=sharePerPrice class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">注释及说明<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comments" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">客户Id<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name=userId class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
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

</script>
<%@ include file="/include/includeFooter.jsp" %>