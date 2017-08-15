<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
		margin-top: 5px;
	}
	
	.col-sm-2{
		font-size:15px;
	}
	
	input[type="text"]{
		width:200px;
	}
	
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑集成登录信息  </h5>
	                </div>
	                <div class="ibox-title">
	                    <h5> 增加账号，密码都是第三方系统中的账号密码 </h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/integrate/integrate/saveintegrate" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${integrate.id}">
                        	<div class="form-group">
                        		<div class="col-sm-3"></div>
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>单点登录系统</span>
	                        	<select name="integLogins" style="height:30px;margin-left:14px;">
	                        		<c:forEach items="${integrates }" var="item">
	                        			<option class="form-control"  value="${item.id}">${item.loginname}</option>
	                        		</c:forEach>
	                        	</select>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
							<div class="col-sm-3"></div>
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>账号</span>
	                            <div class="col-sm-3"><input type="text" name="loginusercode" class="form-control" value="${integrate.loginusercode }" required="" maxlength="500"></div>
                            </div>
                            
                            <div class="form-group">
                           		<div class="col-sm-3"></div>
	                       		<span class="col-sm-2 control-span">密码</span>
	                            <div class="col-sm-3"><input type="password" name="loginpassword" class="form-control" value="${integrate.loginpassword}" maxlength="200"></div>
                             </div>
                             
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2" style="margin-left:35%;">
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