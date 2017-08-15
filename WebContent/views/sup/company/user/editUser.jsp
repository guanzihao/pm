<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
	}
	*{
		font-size:14px!important;
	}
	
	.form-group{
		margin-top: 20px;
	}
	
	
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5 style="margin-top: 5px;">编辑个人信息</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white " style="width:45px;height:28px;line-height: 25px" href="${basePath }/sup/company/user/viewUser">
		                         返回
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content" >
	                    <form id="submitForm" class="form-horizontal formValidate" action="${basePath }/sup/company/user/saveUser" onsubmit="submitFunction()" method="post">
	                        <input type="hidden" name="token" value="${token}">

							<div class="form-group" >
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>邮箱(登录名)</span>
	                            <div class="col-sm-10 control-value">${companyInfoUser.userMail }</div>
	                        </div>
	                       
	                        <div class="form-group" >
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>名称</span>
	                            <div class="col-sm-3"><input type="text" name="userName" class="form-control" value="${companyInfoUser.userName }" required="" maxlength="100"></div>
	                        </div>
	                        
	                        <div class="form-group" >
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>联系电话</span>
	                            <div class="col-sm-3"><input type="text" name="userNumber" class="form-control" value="${companyInfoUser.userNumber }" required="" maxlength="15"></div>
	                        </div>
	                       
	                        <div class="form-group" >
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>手机号码</span>
	                            <div class="col-sm-3"><input type="text" name="userTel" class="form-control phone" value="${companyInfoUser.userTel }" required="" maxlength="15" digits="true" remote="${basePath}/sup/company/user/ajaxCheckUser?id=${companyInfoUser.id}"></div>
	                        </div>
	                        
	                         <div class="form-group" >
	                            <span class="col-sm-2 control-span">传真</span>
	                            <div class="col-sm-3"><input type="text" name="userFax" class="form-control" value="${companyInfoUser.userFax }" maxlength="15"></div>
	                        </div>
	                        
	                        
	                        <div class="form-group" style="margin-top: 30px;">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary"  type="submit">保存</button>
	                                
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
<script type="text/javascript">
function submitFunction(){
	var phone = $(".phone")val();
	if(!(/^1[34578]\d{9}$/.test(phone))){ 
		parent.layer.msg('手机号码有误，请重新填写',{icon: 2});
	    return false; 
	} 
	    return true; 
}
</script>
