<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<nav class="navbar navbar-default navbar-fixed-top site-navbar">
	<div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" type="button" data-toggle="collapse" data-target="#navbar">
            	<SPAN class="sr-only">EPS</SPAN>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand">
                <a href="${basePath }/common/index/index">
                    <span class="logo"></span>
                </a>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
            <UL class="nav navbar-nav">
                <LI><A href="${basePath }/common/index/index" class="fhsy"><i class="icon-home user-r fhsy-icon"></i>返回首页</A></LI>
            </UL>
        </div>
    </div>
</nav>
<div class="container">
  	<form class="form-horizontal formValidate" role="form" action="${basePath}/inter/interColumn/updateInterface" method="post">
  		<input type="hidden" name="token" value="${token}">
  		<input type="hidden" name="id" value="${interColumn.id }">
  		<div class="col-md-12" style=" margin-top:30px;">
			<div class="panel panel-grey">
	        	<div class="yhxx">
	          		<label class="label" style="color:#333"></label>
	          	</div>
	          	<div class="panel-body">
	            	<div class="col-sm-12">
	            		<div class="form-group">
	                      	<label class="col-sm-2 control-label">接口名称<font>*</font></label>
							<div class="col-sm-10"><input type="text" name="comName" class="form-control" value="${interColumn.intername}" required="" minlength="6" maxlength="200"></div>
						</div>
						<div class="form-group">
	                        <label class="col-sm-2 control-label">接口详情<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="userName" class="form-control" value="${interColumn.intercontent}" required="" maxlength="200"></div>
	                    </div>
                        <div class="form-group">
                      		<label class="col-sm-2 control-label">显示顺序<font>*</font></label>
                          	<div class="col-sm-4"><input type="text" name="comAddress" class="form-control" value="${interColumn.intersort}" required="" maxlength="500"></div>
                        </div>
                        <div class="form-group">
								<label class="col-sm-2 control-label">是否启用<font>*</font></label>
								<div class="col-sm-10" >
									<select class="selectpicker" name="columnStatus">
										<c:if test="${interColumn.interstatus==0 }">
											  <option value="0"  selected="selected">启用</option>
											  <option value="1">禁用</option>
										</c:if>
										<c:if test="${interColumn.interstatus==1 }">
											  <option value="0" >启用</option>
											  <option value="1" selected="selected">禁用</option>
										</c:if>
									</select>  
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
        <div class="text-center zc-bt">
      		<button class="btn btn-primary btn-1" type="submit">提交</button>
        	<button class="btn btn-1" type="reset">重置</button>
      	</div>
	</form>
</div>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>