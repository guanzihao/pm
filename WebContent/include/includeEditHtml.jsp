<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<link href="${basePath}/resource/plugins/summernote/summernote.css" rel="stylesheet" />
<link href="${basePath}/resource/plugins/summernote/summernote-bs3.css" rel="stylesheet" />
<script src="${basePath}/resource/plugins/summernote/summernote.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $('.summernote').summernote({
    	height: 280,
    	toolbar: [
			['style', ['bold', 'italic', 'underline', 'clear']],
			['font', ['strikethrough']],
			['fontsize', ['fontsize']],
			['para', ['ul', 'ol', 'paragraph']],
			['color', ['color']],
			['height', ['height']],
			['table', ['table']],
		],
    	onBlur: function(e) {
    		var htmlId = $('.summernote').attr("htmlTxt");
    		$("#"+htmlId).text($('.summernote').code());
    	}
    });
});
</script>