<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/includeTag.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
       
       <jsp:include page="/views/internet/header.jsp"></jsp:include>
       
        <div class="white_bg h_change">
        	<div class="container">
        		<div class="left q_left">
        			<ul id="test">
        				<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
        					<c:if test="${item[2]=='0' }">
		        					<c:if test="${status.index == 0}">
		        						<input value="${item[0]}" style="display:none;" id="one"/>
		        					</c:if>
        						<li title="${item[0]}" class=""  >${item[1]}</li>
        							<ul>
        								<pm:execute id="helpColumnss" bean="helpColumnsBusinImpl" method="getHelpColumnss">
												<pm:execute-param type="java.lang.String" value="${item[0] }" />
										</pm:execute>
										<c:forEach items="${helpColumnss }" var="items">
											<li class="" title="${items.id}"   >${items.name }</li>
			        								<pm:execute id="helpColumnsss" bean="helpColumnsBusinImpl" method="getHelpColumnss">
													<pm:execute-param type="java.lang.String" value="${items.id }" />
													</pm:execute>
														<c:forEach items="${helpColumnsss }" var="itemss">
															<li style="margin-left:10px;" title="${itemss.id}" >${itemss.name }</li>
														</c:forEach>
			        					</c:forEach>
			        				</ul>
        					</c:if>
        				</c:forEach>
        			</ul>
        		</div>
        		
        		<div class="left q_right" id="webContent">
        			
        		</div>
        		
        		
        	</div>
        </div>
        
        <jsp:include page="/views/internet/footer.jsp"></jsp:include>

</body>
<script type="text/javascript">
	window.onload=function(){
		var html="";
		var info=$("#one").val();
		$("#webContent").html(null);
		$.ajax({
		    type : "post",
		    url:"${basePath}/common/help/findCMSConter",
		    dataType : "json",
		     data:{id:info},
		     success : function(res) {
		    	 $.each(res, function(i, e) {
		 			html=html+"<div class=\"show\">";
		 			html=html+"<img src=\"${basePath }/resource/images/help.jpg\"/>";
		 			html=html+"<span>";
		 			html=html+""+e.Tile+"";
		 			html=html+"<div>"+e.getContent+"</div>";
		 			html=html+"</span>";
		 			html=html+"</div>";
		 			html=html+"<div class=\"dot_line\"></div>";
		    	 })
		    	 $("#webContent").append(html);
		   }
		});
	}

	$("li").click(function(){
		var html="";
		var info=$(this).attr("title");
		$("#webContent").html(null);
		$.ajax({
            type : "post",
            url:"${basePath}/common/help/findCMSConter",
            dataType : "json",
		     data:{id:info},
		     success : function(res) {
		    	 $.each(res, function(i, e) {
	     			html=html+"<div class=\"show\">";
	     			html=html+"<img src=\"${basePath }/resource/images/help.jpg\"/>";
	     			html=html+"<span>";
	     			html=html+""+e.Tile+"";
	     			html=html+"<div>"+e.getContent+"</div>";
	     			html=html+"</span>";
	     			html=html+"</div>";
	     			html=html+"<div class=\"dot_line\"></div>";
		    	 })
		    	 $("#webContent").append(html);
		   }
      });
	});

$(document).ready(function(){


	$("#navul > li").hover(function(){

		$(this).addClass("navmoon");

	},function(){

		$(this).removeClass("navmoon");

	});
	


});
	</script>
</html>