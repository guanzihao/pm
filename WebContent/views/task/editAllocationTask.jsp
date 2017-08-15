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
  	<form class="form-horizontal formValidate" role="form" action="${basePath }/common/register/saveCompany" method="post">
  		<input type="hidden" name="token" value="${token}">
  		<div class="col-md-12" style=" margin-top:30px;">
			<div class="panel panel-grey">
	        	<div class="yhxx">
	          		<label class="label" style="color:#333">添加任务信息</label>
	          	</div>
	          	<div class="panel-body">
	            	<div class="col-sm-12">
						<div class="form-group">
	                        <label class="col-sm-2 control-label">任务流水号<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="userName" class="form-control" required="" maxlength="200"></div>
	                        <label class="col-sm-2 control-label">任务类型<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="userNumber" class="form-control" required="" maxlength="50"></div>
	                    </div>
						<div class="form-group">
	                        <label class="col-sm-2 control-label">发布客户<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="userName" class="form-control" required="" maxlength="200"></div>
	                        <label class="col-sm-2 control-label">发布日期<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" class="form-control input-sm date-picker" name="searchDate2" value=""></div>
	                       
	                    </div>
	                    <div class="form-group">
                      		<label class="col-sm-2 control-label">项目名称<font>*</font></label>
                          	<div class="col-sm-4"><input type="text" name="comAddress" class="form-control" required="" maxlength="500"></div>
                        </div>
                        <div class="form-group">
	                        <label class="col-sm-2 control-label">开启日期<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" class="form-control input-sm date-picker" name="searchDate2" value=""></div>
	                        <label class="col-sm-2 control-label">完成日期<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" class="form-control input-sm date-picker" name="searchDate2" value=""></div>
	                    </div>
                        <div class="form-group">
	                        <label class="col-sm-2 control-label">计划开启日期<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" class="form-control input-sm date-picker" name="searchDate2" value=""></div>
	                        <label class="col-sm-2 control-label">计划完成日期<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" class="form-control input-sm date-picker" name="searchDate2" value=""></div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label">指定报关公司<font>*</font></label>
	                        <div class="col-sm-4">
	                        	<select>
	                        		<c:forEach items="${supCompanyInfobaoguan }" var="item">
		                        		<option value="${item.id }">${item.comName }</option>
	                        		</c:forEach>
	                        	</select>
	                        </div>
	                        <label class="col-sm-2 control-label">指定仓储公司<font>*</font></label>
	                    	<div class="col-sm-4">
	                        	<select>
	                        		<c:forEach items="${supCompanyInfocangchu }" var="item">
		                        		<option value="${item.id }">${item.comName }</option>
	                        		</c:forEach>
	                        	</select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label">指定外贸公司<font>*</font></label>
	                        <div class="col-sm-4">
	                        	<select>
	                        		<c:forEach items="${supCompanyInfowaimao }" var="item">
		                        		<option value="${item.id }">${item.comName }</option>
	                        		</c:forEach>
	                        	</select>
	                        </div>
	                        <label class="col-sm-2 control-label">指定物流公司<font>*</font></label>
	                    	<div class="col-sm-4">
	                        	<select>
	                        		<c:forEach items="${supCompanyInfowuliu }" var="item">
		                        		<option value="${item.id }">${item.comName }</option>
	                        		</c:forEach>
	                        	</select>
	                        </div>
	                    </div>
         				<div class="form-group">
	                        <label class="col-sm-2 control-label">任务描述<font>*</font></label>
	                        <div class="col-sm-10"><textarea id="noticeText" name="content" cols="98" rows="10">${notice.noticeText }</textarea></div>
	                    </div>
         				<div class="form-group">
	                        <label class="col-sm-2 control-label">客服备注<font>*</font></label>
	                        <div class="col-sm-10"><textarea id="noticeText" name="content" cols="98" rows="10">${notice.noticeText }</textarea></div>
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

<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>