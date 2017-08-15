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
	                    <h5>添加模板信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sysconfig/Systemplate/saveSystemplate" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                         <input type="hidden" name="id" value="${systemplate.id}">

                        	<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>模板名称</span>
	                            <div class="col-sm-10"><input type="text" name="tempname" class="form-control" value="${systemplate.tempname }" required="" maxlength="200" remote="${basePath}/sysconfig/Systemplate/ajaxCheckSystemplate?id=${systemplate.id}"></div>
	                        </div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>模板内容</span>
	                            <div class="col-sm-10"><textarea  name="tempcontet" class="form-control"  rows="6">${systemplate.tempcontet }</textarea></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>模板类型</span>
	                        	<select name="temptype" >
			                       <c:forEach  items="${enumitems }" var="item">
			                      		 <c:choose>
										 	<c:when test="${item.id==systemplate.temptype }">
												 <option value="${item.id }" selected="selected">${item.name }</option>
											</c:when>
											<c:otherwise>
												  <option  class="form-control" value="${item.id}" >${item.name}</option>
											</c:otherwise>
										</c:choose>
			                          </c:forEach>
		                          </select>
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
<%@ include file="/include/includeFooter.jsp" %>