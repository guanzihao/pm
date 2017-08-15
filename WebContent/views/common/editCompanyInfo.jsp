<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑公司信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/common/suplogin/saveCompanyInfo" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="companyInfoUserId" value="${companyInfoUser.id}">
	                        

                        	<div class="form-group">
	                        	<label class="col-sm-2 control-label">公司名称<font>*</font></label>
	                            <div class="col-sm-10 control-value">${companyInfo.comName }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<label class="col-sm-2 control-label">公司地址<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comAddress" class="form-control" value="${companyInfo.comAddress }" required="" maxlength="500"></div>
	                            <label class="col-sm-2 control-label">公司网址</label>
	                            <div class="col-sm-4"><input type="text" name="comWebsite" class="form-control" value="${companyInfo.comWebsite }" maxlength="200"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                      
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">法定代表人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comPerson" class="form-control" value="${companyInfo.comPerson }" required="" maxlength="100"></div>
	                        	<label class="col-sm-2 control-label">成立时间<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comFoundingtime" class="form-control date-picker" required="" value="${companyInfo.comFoundingtime }" maxlength="15" dateISO="true"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">税务登记证号[三证合一]<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="comDutynum" class="form-control" required="" value="${companyInfo.comDutynum }" maxlength="50"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">开户银行<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comBank" class="form-control" required="" value="${companyInfo.comBank }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">开户银行账号<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comBankaccount" class="form-control" required="" value="${companyInfo.comBankaccount }" maxlength="50"></div>
	                        </div>
	                          <div class="form-group">
	                        	<label class="col-sm-2 control-label">供应商联系人<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comLink" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <label class="col-sm-2 control-label">供应商联系人电话<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comTel" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">供应商联系人邮箱<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="comEmail" class="form-control" required="" value="${companyInfo.comEmail }" maxlength="50"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">关联类型</label>
	                            <div class="col-sm-10">
	                            	<c:forEach items="${companyTypeList }" var="item">
	                            		<div><label><input type="checkbox" name="typeIds" id="${item.id }" value="${item.id }"> ${item.comTypename }</label></div>
	                            	</c:forEach>
	                            </div>
	                        </div>
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
</script>
<%@ include file="/include/includeFooter.jsp" %>