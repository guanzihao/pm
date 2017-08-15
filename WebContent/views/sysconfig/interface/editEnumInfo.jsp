<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>添加枚举信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sysconfig/info/saveEnumInfo" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                         <input type="hidden" name="id" value="${enumtype.id}">

                        	<div class="form-group">
	                        	<label class="col-sm-2 control-label">枚举名称<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="name" class="form-control" value="${enumtype.name }" required="" maxlength="200" remote="${basePath}/sysconfig/info/ajaxCheckEnumInfo?id=${enumtype.id}"></div>
	                        </div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label">显示顺序<font>*</font></label>
	                            <div class="col-sm-10"><input type="text" name="sort" class="form-control" value="${enumtype.sort }" required=""  maxlength="200" ></div>
	                        </div>
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
<%@ include file="/include/includeFooter.jsp" %>