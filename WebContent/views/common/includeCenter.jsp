<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeTag.jsp" %>

<div class="row">
    <div class="col-md-3 div_cal">
    	<c:choose>
        	<c:when test="${not empty currSup}">
        		<div class="active dl">
	            	<a href="javascipt:;" data-toggle="tab" class="text-left">登录信息</a>
	            </div>
	            <div id="myTabContent" class="tab-content">
	               <div class="" id="gys">
	                    <DIV class="panel-body table-bordered">
	                        <FORM class="form-horizontal" role="form">
	                            <DIV class="form-group text-center">
	                                <h4>当前登录账号</h4>
	                            </DIV>
	                            <DIV class="form-group user text-center">
	                                <LABEL class="col-sm-6 text-right">用户名：</LABEL>
	                                <LABEL class="col-sm-6 text-left">${currSup.userName }</LABEL>
	                            </DIV>
	                            <DIV class="form-group text-center">
	                                <a class="btn btn-primary btn-1" href="${basePath }/sup/index/supHome">进入系统</a>
	                                <a class="btn btn-default btn-1" href="${basePath }/common/suplogin/supExit">退出</a>
	                            </DIV>
	                        </FORM>
	                    </DIV>
	               </div>
	            </div>
        	</c:when>
        	<c:otherwise>
        		<%@ include file="includeLoginSup.jsp" %>
        	</c:otherwise>
        </c:choose>
    </div>
    <div class="col-md-6 div_cal">
      	<div class="banner">
          	<div class="bd">
            	<ul>
                  <li style="background:url(${basePath }/resource/images/banner1.jpg) center 0 no-repeat;"></li>
                  <li style="background:url(${basePath }/resource/images/banner2.jpg) center 0 no-repeat;"></li>
                  <li style="background:url(${basePath }/resource/images/banner3.jpg) center 0 no-repeat;"></li>
                  <li style="background:url(${basePath }/resource/images/banner4.jpg) center 0 no-repeat;"></li>
            	</ul>
          	</div>
          	<div class="hd"><ul><li class="">1</li><li class="">2</li><li class="">3</li><li class="on">4</li></ul></div>
        </div>
    </div>
    <div class="col-md-3 div_cal">
        <c:choose>
        	<c:when test="${not empty currUser}">
        		<div class="active dl">
	            	<a href="javascipt:;" data-toggle="tab" class="text-left">登录信息</a>
	            </div>
	            <div id="myTabContent" class="tab-content">
	               <div id="cgy">
	                    <DIV class="panel-body table-bordered">
	                        <FORM class="form-horizontal " role="form">
	                            <DIV class="form-group text-center">
	                                <h4>当前登录账号</h4>
	                            </DIV>
	                            <DIV class="form-group user text-center">
	                                <LABEL class="col-sm-6 text-right">用户名：</LABEL>
	                                <LABEL class="col-sm-6 text-left">${currUser.userName }</LABEL>
	                            </DIV>
	                            <DIV class="form-group text-center">
	                                <a class="btn btn-primary btn-1" href="${basePath }/organize/index/userHome">进入系统</a>
	                                <a class="btn btn-default btn-1" href="${basePath }/common/userlogin/userExit">退出</a>
	                            </DIV>
	                        </FORM>
	                    </DIV>
	               </div>
	            </div>
        	</c:when>
        	<c:otherwise>
        		<%@ include file="includeLogin.jsp" %>
        	</c:otherwise>
        </c:choose>
    </div>
</div>
<script src="${basePath }/resource/plugins/security/security.js"></script>
<script type="text/javascript">
	var exponent = '';
	var modulus = '';
	$(document).ready(function() {
		$.ajax({type:"post", url:"${basePath}/common/index/jsonSecurityKey", async:true, dataType:"json",success:function (data){
			exponent = data[0].exponent;
			modulus = data[0].modulus;
			$('.btn-1').removeAttr("disabled");
		}});
	});
	function updatekaptcha(id){
		$("#" + id).attr("src","${basePath }/kaptcha.jpg?log="+Math.random());
	}
</script>