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
	                    <h5>编辑枚举明细信息</h5>
	                    <div class="ibox-tools">
		                     <a class="btn btn-xs btn-white" href="${basePath }/sysconfig/info/viewEnumInfo/${enumitem.id}">
		                         <i class="fa fa-share"></i> 返回
		                     </a>
						</div>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/sysconfig/info/editAddEnumitems" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${enumitem.id}">
	                        <input type="hidden" name="comId" value="${enumtype.id}">
							<div class="form-group">
	                        	<span class="col-sm-2 control-label"><i class="ired">*</i>枚举名称</span>
	                            <div class="col-sm-10"><input type="text" name="name" class="form-control" value="${enumitem.name }" required=""   maxlength="100" remote="${basePath}/sysconfig/info/ajaxCheckEnumitem?id=${enumitem.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                        	<label class="col-sm-2 control-label"><i class="ired">*</i>显示顺序</label>
	                            <div class="col-sm-4"><input type="text" name="sort" class="form-control" value="${enumitem.sort }" required="" maxlength="100"></div>
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