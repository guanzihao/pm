<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>物流进口</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/wuliuimcc/saveShippingExCommission" method="post">
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
	                        	<label class="col-sm-2 control-label">托运人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="shipper" class="form-control " required="" value="${companyInFo.comName }" maxlength="15" ></div>
	                        	<label class="col-sm-2 control-label">托运人地址<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="sAddress" class="form-control " required="" value="" maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">起运港<font>*</font></label>
	                            <div class="col-sm-4"><input  type="text" name="departurePort" class="form-control date-picker" required="" value="" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">卸货港<font>*</font></label>
	                            <div class="col-sm-4"><input  type="text" name="dischargePort" class="form-control" required="" value="" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">目的地<font>*</font></label>
	                            <div class="col-sm-4"><input  type="text" name="destinationPort" class="form-control" required="" value="" maxlength="50"></div>
	                        	<label class="col-sm-2 control-label">编号<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="xhsNo" id="sAddressId" class="form-control " required=""  maxlength="15" ></div>
	                        	
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">收货人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="consignee" id="sFaxId" class="form-control" required=""  maxlength="200"></div>
	                            <label class="col-sm-2 control-label">收货人地址	<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cAddress" id="sTelphoneId" class="form-control" required=""  maxlength="50"></div>
	                        </div>
	                         <div class="hr-line-dashed"></div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">货到时间<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="arrivalDate" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" ></div>
	                        	<label class="col-sm-2 control-label">支付方式<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="payWay" class="form-control" required="" value="" maxlength="15" ></div>
	                        </div>
	                        
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">箱型/箱量<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="container" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">提单类型<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="blt" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">通知人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="notifier" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">件数<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="item" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">毛重<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="weight" class="form-control date-picker" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">体积<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="volume" class="form-control " required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">唛头<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="marks" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">品名	<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="goodsName" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">运费确认<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="transExpense_charge" class="form-control" required="" value="${companyInfo.consignee }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">运输条款<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="transClause" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">托运联系人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="contactPerson" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">托运人电话<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cpPhone" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">传真<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cpFax" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">邮箱<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="cpMail" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">预配船期<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name=preFlight class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">自拉自报<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="selfFull" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">特殊要求<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name=specialNotes class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                       		<label class="col-sm-2 control-label">托运人签名盖章<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="signature" class="form-control" required="" value="${companyInfo.freight }" maxlength="50"></div>
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