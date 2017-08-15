<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<%@ include file="/include/includeTag.jsp" %>
<html>
<head>

</head>
<body>
    <jsp:include page="/views/internet/header.jsp"></jsp:include>

	</div>
		<form action="${basePath }/common/board/addCmsBoard" method="post">
        <div class="white_bg h_change">
        	<div style="padding: 20px 0px;">
       <div class=" ly_txt">
       	<label>网站留言<span>*</span></label>
       	<textarea name="content" rows="10" cols="10"></textarea>
       </div>
              <div class="ly_txt" style="margin-top: 15px;">
       	<label>联系方式</label>
       	<input type="text" id="" name="tel" value="" />
      
       </div>
       	 	<div class="tj_btn"><input type="submit" value="提交留言"/></div>
        	</div>
        </div>
</form>

<jsp:include page="/views/internet/footer.jsp"></jsp:include>

</body>
<script type="text/javascript">

$(document).ready(function(){


	$("#navul > li").hover(function(){

		$(this).addClass("navmoon");

	},function(){

		$(this).removeClass("navmoon");

	});
	


});
	</script>
</html>