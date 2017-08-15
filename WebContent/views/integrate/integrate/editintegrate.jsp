<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>

	span{
		text-align: right;
	}

</style>
<body class="gray-bg" style="">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑公司信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/integrate/integrate/saveintegrate" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${integrate.id}">

                        	<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>名称</span>
	                            <div class="col-sm-10"><input type="text" name="loginname" class="form-control" value="${integrate.loginname }" required=""  maxlength="200" remote="${basePath}/integrate/integrate/ajaxCheckintegrate?id=${integrate.id}"/></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>内网地址</span>
	                            <div class="col-sm-10"><input type="text" name="logininnerurl" class="form-control" value="${integrate.logininnerurl }" required="" maxlength="500"></div>
                            </div>
                            
                            <div class="form-group">
	                       		<span class="col-sm-2 control-span">外网地址</span>
	                            <div class="col-sm-10"><input type="text" name="loginouturl" class="form-control" value="${integrate.loginouturl}" maxlength="200"></div>
                             </div>
                             
	                        <div class="form-group">
	                        	 <span class="col-sm-2 control-span">账号参数名</span>
	                            <div class="col-sm-4"><input type="text" name="loginusercode" class="form-control" value="${integrate.loginusercode}" maxlength="50"></div>
	                        	<span class="col-sm-2 control-span">密码参数名</span>
	                            <div class="col-sm-4"><input type="text" name="loginpassword" class="form-control" value="${integrate.loginpassword}" maxlength="200"></div>
	                        </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">提交方式</span>
	                            <div class="col-sm-4"><input type="text" name="loginpostType" class="form-control" value="${integrate.loginpostType}" maxlength="200"></div>
	                         </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit" style="margin-right:10px;">保存</button>
	                                
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