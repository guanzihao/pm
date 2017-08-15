<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	String treeData = request.getParameter("treeData");
	boolean checkbox = (request.getParameter("checkbox")!=null && !request.getParameter("checkbox").isEmpty())? Boolean.parseBoolean(request.getParameter("checkbox")):false;
%>
<link href="${basePath }/resource/plugins/jstree/themes/default/style.min.css" rel="stylesheet" />
<script src="${basePath}/resource/plugins/jstree/jstree.min.js"></script>

<input type="hidden" id="<%= name %>" name="<%= name %>" value="<%= value %>" />
<div id="jstree_<%=name %>" style="max-width:100%; overflow:auto; font:10px Verdana, sans-serif; box-shadow:0 0 5px #ccc; padding:10px; border-radius:5px;"></div>
<script type="text/javascript">
$(function () {
	$('#jstree_<%=name %>').jstree({
		<% if (checkbox){%>
			'plugins':["wholerow","checkbox"],
		<%}%>
		'core':{'data':<%=treeData %>}
	});
	$('#jstree_<%=name %>').on("changed.jstree", function (e, data) {
		$('#<%=name%>').val(data.selected);
		$('#<%=name%>').val(data.node.text);
		$("#customerTask").attr("src","${basePath }/sup/task/listTaskDispose/"+data.selected+"/"+data.node.original.hierarchy+"/"+data.node.original.taskTypeId);
	});
});
function toolTree(url, formId, check){
	if(check){
		if($('#<%=name %>').val() != '' && $('#<%=name %>').val() != '0' && $('#<%=name %>').val() != 'null'){
			parent.layer.confirm('是否确认操作？', {btn: ['确定','取消']	}, function(index){
				$('#' + formId).attr("action", url);
				$('#' + formId).submit();
				parent.layer.close(index);
			});
		}
		else{
			parent.layer.msg('请选择数据进行操作');
		}
	}
	else{
		$('#' + formId).attr("action", url);
		$('#' + formId).submit();
	}
}
</script>