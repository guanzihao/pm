<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<jsp:include page="/views/internet/header.jsp"></jsp:include>

	<div class="white_bg h_change">
		<div class="container">
			
			<!-- 主体内容 -->
			<div class=" " style="display: block;">
				<div style="text-align: center;">
					<img src="${basePath }/imgServlet?imgName=${cMSContent.bigImg }" width="787"
						height="319" alt="" />
				</div>
				<div
					style="line-height: 30px; color: #666; padding: 20px 90px 20px 90px;margin-left:115px;">
						${cMSContent.content }
					</div>
				<div class="clear"></div>
			</div>


		</div>
	</div>

	<jsp:include page="/views/internet/footer.jsp"></jsp:include>

	<script type="text/javascript">
	</script>
</body>
</html>