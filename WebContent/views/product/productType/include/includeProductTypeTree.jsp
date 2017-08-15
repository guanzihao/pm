<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    String name = request.getParameter("name");
			String value = request.getParameter("value");
			String treeData = request.getParameter("treeData");
			boolean checkbox = (request.getParameter("checkbox") != null && !request.getParameter("checkbox").isEmpty())
					? Boolean.parseBoolean(request.getParameter("checkbox"))
					: false;
%>
<link
	href="${basePath }/resource/plugins/jstree/themes/default/style.min.css"
	rel="stylesheet" />
<script src="${basePath}/resource/plugins/jstree/jstree.min.js"></script>

<input type="hidden" id="<%=name%>" name="<%=name%>"
	value="<%=value%>" />
<input type="hidden" id="<%=name%>Name" name="<%=name%>Name"
	value="" />
<div id="jstree_<%=name%>"
	style="max-width: 100%; overflow: auto; font: 10px Verdana, sans-serif; box-shadow: 0 0 5px #ccc; padding: 10px; border-radius: 5px;"></div>
<script type="text/javascript">
$(function () {
	$('#jstree_<%=name%>').jstree({
		<%if (checkbox) {%>
			'plugins':["wholerow","checkbox"],
		<%}%>
		'core':{'data':<%=treeData%>}
	});
	$('#jstree_<%=name%>').on("changed.jstree", function (e, data) {
		$('#<%=name%>').val(data.selected);
		$('#<%=name%>Name').val(data.node.text);
		$("#productList").attr("src","${basePath }/sup/product/productType/listProductByType.do?productTypeId="+data.selected);
	});
	$('#jstree_<%=name%>').on("dblclick.jstree", function (e, data) {
		toolTree1();
		
	});
	
});
function toolTree(url, formId, check){
	if(check){
		if($('#<%=name%>').val() != '' && $('#<%=name%>').val() != '0' && $('#<%=name%>').val() != 'null'){
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
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
function toolTree1(){
		if($('#<%=name%>').val() != '' && $('#<%=name%>').val() != '0' && $('#<%=name%>').val() != 'null'){
			 parent.$('#productTypeId').val($('#<%=name%>').val());
			 parent.$('#productTypeName').val($('#<%=name%>Name').val());
			parent.layer.close(index);
		}
		else{
			parent.layer.msg('请选择数据进行操作');
		}
}

/**
 *  删除目录
 */
function removeToolTree(url, formId, check){
	if(check){
		if($('#<%=name%>').val() != '' && $('#<%=name%>').val() != '0' && $('#<%=name%>').val() != 'null'){
			parent.layer.confirm('是否确认操作？', {btn: ['确定','取消']	}, function(index){
				$.ajax({
					url:"${basePath}/sup/product/productType/ajaxCheckProductToType",
					type : 'post',async: false,
					data:{productTypeId:$('#<%=name%>').val()},
					success : function(data){
						if(data=="true"){
							$('#' + formId).attr("action", url);
							$('#' + formId).submit();
							parent.layer.close(index);
						}else{
							parent.layer.msg('目录下有产品，无法删除！');
						}
					}
				});
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