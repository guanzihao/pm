<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeHeader.jsp"%>

<body>
<input type="hidden" id="taskTypeId" value="${taskTypeId }">
<input type="hidden" id="urlId" value="${url }">
<input type="hidden" id="taskId" value="${task.id}">

	<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="navbar-collapse" id="navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<c:forEach items="${names}" var="item" varStatus="status">
							<c:choose>
								<c:when test="${item.url eq url }">
									
									<li class="active"><a target="jjjjj" href="${basePath }${item.url }/${task.id}/${item.id}" ><span class="glyphicon glyphicon-home"></span> ${item.name }</a></li>
								</c:when>
								<c:otherwise>
									<li ><a  target="jjjjj" href="${basePath }${item.url }/${task.id}/${item.id}"><span class="glyphicon glyphicon-home"></span> ${item.name }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
			</div>
		</nav>
		<iframe id="iframeId" name="jjjjj" frameborder="1" width="100%" height="500"></iframe>
</body>
<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>
<script type="text/javascript">
	$(function(){
		var taskTypeId=$("#taskTypeId").val();
		var urlId=$("#urlId").val();
		var taskId=$("#taskId").val();
		var url="${basePath }"+urlId+""+taskId+"/"+taskTypeId;
		$("#iframeId").attr("src",url);
		
	});
</script>

