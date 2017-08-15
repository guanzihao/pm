<%@page import="com.pm.core.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	if(value != null && (value.indexOf("'")== -1 || value.indexOf("\"")== -1)){
	    value = "'" + value + "'";
	}
%>
<link href="${basePath}/resource/plugins/select2/select2.min.css" rel="stylesheet" />
<script src="${basePath}/resource/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
	$('.select2').select2({
		allowClear : true
	})
	<%if(!StringUtil.isEmpty(name) && !StringUtil.isEmpty(value)){ %>
		$('#<%=name %>').select2("val", [<%=value %>]);
	<%}%>
</script>