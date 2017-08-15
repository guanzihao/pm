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
        		
        					<a id="test1" class="q_txt co_txt" style="margin-left:10px;color:#000;margin-top:20px;" href="${basePath }/common/content/findCMSContentss/1" onclick="zhengce()">政策法规</a><br>
        					<a id="test2" style="margin-left:10px;color:#000;margin-top:20px;" onclick="hangye()" href="${basePath }/common/content/findCMSContentss/2">行业新闻</a><br>
        					<a  id="test3"style="margin-left:10px;color:#000;margin-top:20px;" onclick="fuwushang()" href="${basePath }/common/content/findCMSContentss/3">服务商动态</a><br>
        					<input type="text" id="info" value="${zhengce} " style="display:none;">
        		</div>
        		<div class="left q_right">
        			<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
        			
        					<c:if test="${status.index <4 }">
        							<c:if test="${item[3]%2==0}">
					        			<div class="ad left m_left">
					        				<div class="left"><img src="${basePath }/imgServlet?imgName=${item[5] }"/></div>
						        			<div class="left l_txt">
						        				<div >${item[7] }：${item[1]}</div>
						        				<div class="txt_f">${item[6] }</div>
						        				<div class="txt_f" style="line-height: 20px;">${item[2] }<a href="${basePath }/common/content/findCMSContentsdetail/${item[0] }"><span>详情</span></a></div>
						        			</div>
				        				</div>
				        			</c:if>
				        			<c:if test="${item[3]%2==1}">
					        			<div class="ad left ">
					        				<div class="left"><img src="${basePath }/imgServlet?imgName=${item[5] }"/></div>
					        			<div class="left l_txt">
					        				<div >${item[7] }：${item[1]}</div>
					        				<div class="txt_f">${item[6] }</div>
					        				<div class="txt_f" style="line-height: 20px;">${item[2] }<a href="${basePath }/common/content/findCMSContentsdetail/${item[0] }"><span>详情</span></a></div>
					        			</div>
					        			</div>
				        			</c:if>
			        			</c:if>
        			  </c:forEach>      
        			  <c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
        				<c:if test="${status.index >3 }">
			        			<div class="clear"></div>
			        			<c:if test="${item[3]%2==0}" >
				        			<div class="date " style="background: #f9f9f9;">
				        				<div class="date_num left"><div style="font-size: 32px;">06</div><div style="font-size: 12px;">17-07</div></div>
						        			<div class="left date_cont">
						        				<div style="font-weight: bold;">${item[7] }？${item[1]}</div>
						        				<div style="color: #666;">${item[2] }</div>
						        			</div>
					        			<div class="l_btn right"><a href="${basePath }/common/content/findCMSContentsdetail/${item[0] }"><span>详情</span></a></div>
					        		</div>
				        		</c:if>
				        		
				        		<c:if test="${item[3]%2==1}">
				        			<div class="date " >
				        				<div class="date_num left"><div style="font-size: 32px;">06</div><div style="font-size: 12px;">17-07</div></div>
						        			<div class="left date_cont">
						        				<div style="font-weight: bold;">${item[7] }？${item[1]}</div>
						        				<div style="color: #666;">${item[2] }</div>
						        			</div>
					        			<div class="l_btn right"><a href="${basePath }/common/content/findCMSContentsdetail/${item[0] }"><span>详情</span></a></div>
					        		</div>
				        		</c:if>
			        		</c:if>
         			</c:forEach>
        	</div>
        </div>
</div>
		<jsp:include page="/views/internet/footer.jsp"></jsp:include>

		<script type="text/javascript">
		var info=$("#info").val();
		if(info==1){
			$("#test1").removeClass;
			$("#test1").addClass("q_txt co_txt");
			$("#test2").removeClass();
			$("#test3").removeClass();
		}else if (info==2){
			$("#test2").removeClass();
			$("#test2").addClass("q_txt co_txt");
			$("#test1").removeClass();
			$("#test3").removeClass();
		}else{
			$("#test3").removeClass();
			$("#test3").addClass("q_txt co_txt");
			$("#test1").removeClass();
			$("#test2").removeClass();
		}
		
		function zhengce(){
			$("#test1").removeClass;
			$("#test1").addClass("q_txt co_txt");
			$("#test2").removeClass();
			$("#test3").removeClass();
			
		}
		function hangye(){
			$("#test2").removeClass();
			$("#test2").addClass("q_txt co_txt");
			$("#test1").removeClass();
			$("#test3").removeClass();
		}
		function fuwushang(){
			$("#test3").removeClass();
			$("#test3").addClass("q_txt co_txt");
			$("#test1").removeClass();
			$("#test2").removeClass();
			
		} 
		
		
		
		
$(document).ready(function(){


	$("#navul > li").hover(function(){

		$(this).addClass("navmoon");

	},function(){

		$(this).removeClass("navmoon");

	});
});
	</script>
</body>
</html>