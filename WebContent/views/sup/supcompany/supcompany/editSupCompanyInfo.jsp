<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑供应商信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sup/supcompany/company/saveSupCompanyInfo" method="post">
	                        <input type="hidden" name="token" value="${token}">

                        	<div class="form-group">
	                        	<label class="col-sm-2 control-label">供应商名称<font>*</font></label>
	                            <div class="col-sm-10 control-value">${supcompanyInfo.comName }</div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                           <div class="form-group">
	                            <label class="col-sm-2 control-label">供应商电话<font>*</font></label>
	                            <div class="col-sm-4"><input type="text" name="comTel" class="form-control" required="" value="${supcompanyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<label class="col-sm-2 control-label">供应商邮箱<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="comEmail" class="form-control" required="" value="${supcompanyInfo.comEmail }" maxlength="50"></div>
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
<%@ include file="/include/includeFooter.jsp" %>