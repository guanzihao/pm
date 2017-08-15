<%@page import="com.pm.core.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<link href="${basePath}/resource/plugins/duallistbox/bootstrap-duallistbox.min.css" rel="stylesheet" />
<script src="${basePath}/resource/plugins/duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<script type="text/javascript">
	$('.duallistbox').bootstrapDualListbox({
		nonSelectedListLabel: '未选择',
		selectedListLabel: '已选择',
		infoText: '剩余 {0} 记录',
		infoTextEmpty: '无记录'
	});
</script>