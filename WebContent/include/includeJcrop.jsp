<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String imgPath = request.getParameter("imgPath");
%>
<link href="${basePath }/resource/plugins/jcrop/jquery.Jcrop.min.css" rel="stylesheet" />
<script src="${basePath }/resource/plugins/jcrop/jquery.Jcrop.min.js"></script>
<script type="text/javascript">
jQuery(function($){
    var boundx,boundy,$preview = $('#preview-pane'),$pcnt = $('#preview-pane .preview-container'),$pimg = $('#preview-pane .preview-container img'),xsize = $pcnt.width(),ysize = $pcnt.height();
    $('#targetImg').Jcrop({
    	onChange: updatePreview,
    	onSelect: updatePreview,
    	aspectRatio: 1,
    	boxWidth : 600
    },function(){var bounds = this.getBounds();boundx = bounds[0];boundy = bounds[1];$preview.appendTo(this.ui.holder);});
    function updatePreview(c){
      if (parseInt(c.w) > 0){
        var rx = xsize / c.w;var ry = ysize / c.h;
        $pimg.css({width: Math.round(rx * boundx) + 'px',height: Math.round(ry * boundy) + 'px',marginLeft: '-' + Math.round(rx * c.x) + 'px',marginTop: '-' + Math.round(ry * c.y) + 'px'});
        $('#x').val(Math.round(c.x));$('#y').val(Math.round(c.y));$('#width').val(Math.round(c.w));$('#height').val(Math.round(c.h));
      }
    };
  });
</script>
<input type="hidden" name="x" id="x" value="0">
<input type="hidden" name="y" id="y" value="0">
<input type="hidden" name="width" id="width" value="0">
<input type="hidden" name="height" id="height" value="0">
<img src="${basePath }/upfile/<%=imgPath %>" id="targetImg"/>
<div id="preview-pane"><div class="preview-container">
    <img src="${basePath }/upfile/<%=imgPath %>" class="jcrop-preview"/>
</div></div>