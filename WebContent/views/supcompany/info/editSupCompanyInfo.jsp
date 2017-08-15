<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>添加供应商信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/supcompany/info/saveSupCompanyInfo" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${supCompanyInfo.id}">

                        	<div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>供应商名称</label>
	                            <div class="col-sm-10"><input style="padding:0px;" type="text" name="comName" class="form-control" value="${supCompanyInfo.comName }" required=""  maxlength="200" remote="${basePath}/supcompany/info/ajaxCheckSupCompanyInfo?id=${supCompanyInfo.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>供应商电话</label>
	                            <div class="col-sm-4"><input type="text" name="comTel" class="form-control" value="${supCompanyInfo.comTel }" required="" ></div>
	                            <label class="col-sm-2 control-label">供应商邮箱</label>
	                            <div class="col-sm-4"><input type="text" name="comEmail" class="form-control" value="${supCompanyInfo.comEmail }" Email="true" maxlength="200"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>供应商编号</label>
	                            <div class="col-sm-4"><input type="text" name="comCode" class="form-control" value="${supCompanyInfo.comCode }" required="" maxlength="36"></div>
	                        </div>
	                        <div class="form-group">
	                        <label class="col-sm-2 control-label">选择供应商类型</label>
	                        	<select name="comType"  style="margin-left:15px">
			                        <c:forEach  items="${enumitems }" var="item">
			                            <option class="form-control" value="${item.id }" >${item.name }</option>
			                          </c:forEach>
		                          </select>
                            </div>
	                        <div class="form-group" style="margin-top:40px;">
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