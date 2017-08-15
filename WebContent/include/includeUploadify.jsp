<%@page import="com.pm.framework.util.GlobalUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<link href="${basePath}/resource/plugins/uploadify/uploadify.css" rel="stylesheet" />
<script src="${basePath}/resource/plugins/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/resource/plugins/uploadifyh5/Huploadify.css"/>
<script type="text/javascript" src="${basePath}/resource/plugins/uploadifyh5/jquery.Huploadify.js"></script>
<%
	String propertyName = request.getParameter("propertyName");
	String propertyNameFile = propertyName+"_file";
	String metaColums = request.getParameter("metaColums");
%>
<style type="text/css">
	.uploadify-button{
		width: 140px;
		height: 34px;
	}
</style>
<div id="queue_<%=propertyName %>"></div>
<div id="append_<%=propertyName %>"></div>
<input type="hidden" class="flagClass" value="" />
<input id="<%=propertyNameFile %>" class="aaa" name="<%=propertyNameFile %>" type="file" />
<script type="text/javascript">
$(document).ready(function() {
	if (getExplorerInfo().type=='IE'&&getExplorerInfo().version<11) {
		var fls = flashChecker();  
		var s = "";  
		if (fls.f){
			$(".flagClass").val('false');
			$(".aaa").show();  
		}else{
			alert('由于你的浏览器版本较低,如果想上传附件,必须装flash插件,您也可以升级你的浏览器,谢谢您的使用！！');
		}
	} else {
		$(".aaa").hide();
		$(".flagClass").val('true');
	}
	var flagClass=$(".flagClass").val();
	if (flagClass=='true') {
		$('#queue_<%=propertyName %>').Huploadify({
			auto:true,
			fileTypeExts:'<%=GlobalUtil.getValue("upfile.filetype")%>',
			multi:true,
			fileSizeLimit:'<%=GlobalUtil.getValue("upfile.fileSize")%>',
			showUploadedPercent:true,//是否实时显示上传的百分比，如20%
			showUploadedSize:true,
			removeTimeout:9999999,
			'height':30,
			'width':120,
			uploader:'${basePath}/framework/file/ajaxUploadFile<%= (metaColums != null ? ("?metaColums=" +metaColums) : "")%>',
			onUploadStart:function(){
				},
			onInit:function(){
				 
				},
			onUploadComplete:function(file, data, response){
					$("#append_<%=propertyName %>").append("<input type='hidden' name='<%=propertyName %>' id='file_"+file.id+"' value='" + data + "'/>");
					var cancel=$("#"+file.id + " .cancel a");
					if(cancel) {
						cancel.on('click',function() {
							$.post("${basePath}/framework/file/removeFile/"+data);
							$("#file_" + data).remove();
							$(this).hide();
						});
					}
					
				},
			onCancel:function(file){
				var data=$('#file_'+file.id).val();
				$.post("${basePath}/framework/file/removeFile/"+data);
				$('#file_'+file.id).remove();
				$(this).hide();
			},onQueueComplete:function(queueData){
				layer.closeAll(); //疯狂模式，关闭所有层
			}
			
			});
	} else {
		 $("#<%=propertyNameFile %>").uploadify({
				'swf':'${basePath}/resource/plugins/uploadify/uploadify.swf',
				'uploader':'${basePath}/framework/file/ajaxUploadFile<%= (metaColums != null ? ("?metaColums=" +metaColums) : "")%>',
				'buttonText':'选择文件',
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
	}
});
function getExplorerInfo() {
	var explorer = window.navigator.userAgent.toLowerCase();
	//ie 
	if(explorer.indexOf("msie") >= 0) {
		var ver = explorer.match(/msie ([\d.]+)/)[1];
		return {
			type: "IE",
			version: ver
		};
	}
	//firefox 
	else if(explorer.indexOf("firefox") >= 0) {
		var ver = explorer.match(/firefox\/([\d.]+)/)[1];
		return {
			type: "Firefox",
			version: ver
		};
	}
	//Chrome
	else if(explorer.indexOf("chrome") >= 0) {
		var ver = explorer.match(/chrome\/([\d.]+)/)[1];
		return {
			type: "Chrome",
			version: ver
		};
	}
	//Opera
	else if(explorer.indexOf("opera") >= 0) {
		var ver = explorer.match(/opera.([\d.]+)/)[1];
		return {
			type: "Opera",
			version: ver
		};
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0) {
		var ver = explorer.match(/version\/([\d.]+)/)[1];
		return {
			type: "Safari",
			version: ver
		};
	}
}
function flashChecker() {  
    var hasFlash = 0;         //是否安装了flash  
    var flashVersion = 0; //flash版本  
    var isIE = /*@cc_on!@*/0;      //是否IE浏览器  

    if (isIE) {  
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');  
        if (swf) {  
            hasFlash = 1;  
            VSwf = swf.GetVariable("$version");  
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);  
        }  
    } else {  
        if (navigator.plugins && navigator.plugins.length > 0) {  
            var swf = navigator.plugins["Shockwave Flash"];  
            if (swf) {  
                hasFlash = 1;  
                var words = swf.description.split(" ");  
                for (var i = 0; i < words.length; ++i) {  
                    if (isNaN(parseInt(words[i]))) continue;  
                    flashVersion = parseInt(words[i]);  
                }  
            }  
        }  
    }  
    return { f: hasFlash, v: flashVersion };  
}  
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