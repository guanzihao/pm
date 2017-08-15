
<%@page import="com.pm.framework.util.GlobalUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<link href="${basePath}/resource/plugins/uploadify/uploadify.css" rel="stylesheet" />
<script src="${basePath}/resource/plugins/uploadify/jquery.uploadify.min.js"></script>
<%
	String propertyName = request.getParameter("propertyName");
	String propertyNameFile = propertyName+"_file";
	String metaColums = request.getParameter("metaColums");
%>
<div id="queue_<%=propertyName %>"></div>
<input id="<%=propertyNameFile %>" name="<%=propertyNameFile %>" type="file" />

<script type="text/javascript">
$(document).ready(function() {
    $("#<%=propertyNameFile %>").uploadify({
		'swf':'${basePath}/resource/plugins/uploadify/uploadify.swf',
		'uploader':'${basePath}/framework/file/ajaxUploadFile<%= (metaColums != null ? ("?metaColums=" +metaColums) : "")%>',
		'buttonText':'上传',
		'removeCompleted':false,
		'fileSizeLimit':'<%=GlobalUtil.getValue("upfile.fileSize")%>',
		'fileTypeExts':'<%=GlobalUtil.getValue("upfile.filetype")%>',
		'height':30,
		'width':120,
		
		'onUploadSuccess':function(file, data, response) {
            $("#queue_<%=propertyName %>").append("<input type='hidden' name='<%=propertyName %>' id='file_" + data +"' value='" + data + "'/>");
            var cancel=$("#"+file.id + " .cancel a");
			if(cancel) {
				cancel.on('click',function() {
					$.post("${basePath}/framework/file/removeFile/"+data);
					$("#file_" + data).remove();
					$(this).hide();
				});
			}
        }
    });
});
function removedUploadFile(fileId){
	if(window.confirm("确定删除？")){
		$.post("${basePath}/framework/file/removeFile/"+fileId, function(data){
			if(data == '1'){
				$("#li_" + fileId).remove();
			}
		});
	}
}
</script>