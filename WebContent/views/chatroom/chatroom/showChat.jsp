<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>聊天室</h5>
	                </div>
	                <div class="ibox-content">
	                	<form class="form-horizontal formValidate">
	                        <div class="row">
	                            <div class="col-md-12 col-sm-12">
	                            	<textarea id="say" name="allSay" rows="15" readonly="readonly" class="form-control"></textarea>
	                            </div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
	                        <div class="form-group">
	                       	<label class="col-sm-2 control-label">平台接收人</label>
	                           <div class="col-sm-10">
	                           	<select id="userIds" name="userIds" class="form-control select2" multiple="multiple" style="width:100%">
									<c:forEach items="${userAccountList }" var="item">
										<option value="${item.id }">${item.userMail } [${item.userName }]</option>
									</c:forEach>
								</select>
	                           	<c:import url="/include/includeSelect2.jsp">
									<c:param name="name" value="userIds"/>
									<c:param name="value" value="${selectList}"/>
							    </c:import>
	                           </div>
	                       </div>
	                       <div class="form-group">
	                       	<label class="col-sm-2 control-label">客户接收人</label>
	                           <div class="col-sm-10">
	                           	<select id="comIds" name="comIds" class="form-control select2" multiple="multiple" style="width:100%">
									<c:forEach items="${companyInfoList }" var="item">
										<option value="${item.id }">${item.comEmail } [${item.comName }]</option>
									</c:forEach>
								</select>
	                           	<c:import url="/include/includeSelect2.jsp">
									<c:param name="name" value="comIds"/>
									<c:param name="value" value="${selectList}"/>
							    </c:import>
	                           </div>
	                       </div>
	                       <div class="form-group">
	                       	<label class="col-sm-2 control-label">供应商接收人</label>
	                           <div class="col-sm-10">
	                           	<select id="supIds" name="supIds" class="form-control select2" multiple="multiple" style="width:100%">
									<c:forEach items="${supCompanyInfoList }" var="item">
										<option value="${item.id }">${item.comEmail } [${item.comName }]</option>
									</c:forEach>
								</select>
	                           	<c:import url="/include/includeSelect2.jsp">
									<c:param name="name" value="supIds"/>
									<c:param name="value" value="${selectList}"/>
							    </c:import>
	                           </div>
	                       </div>
	                        <div class="row m-t-lg">
	                            <div class="col-md-8 col-sm-12 ">
	                            	<textarea name="says" id="says" rows="5" class="form-control"></textarea>
	                            </div>
	                            <div class="col-md-4 col-sm-12">
	                            	<input type="button" value="发送(Enter)" class="btn m-t-sm"  onclick="send()">
	                            </div>
	                        </div>
	                     </form>
                    </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
	function send()
	{
		var userIds =$("#userIds");
		var comIds =$("#comIds");
		var supIds =$("#supIds");
		var text =$("#says");
		if(userIds.val() == null && comIds.val() == null && supIds.val() == null)
		{
			layer.msg("请选择信息接收人!");
			return;
		}
		if(text.val() == '')
		{
			layer.msg("发送信息不能为空!");
			return;
		}
		$.ajax({
            type : "post",
			dataType : "html",
			url : "${basePath }/chatroom/chatroom/sendChat/"+ userIds.val() +"/" + comIds.val() +"/" + supIds.val(),
			data : {"text":text.val()},
			success: function(returnText){
				if(returnText == 'Y')
				{
					text.val("");
					updateChat();
				}
				else
				{
					layer.msg("发送失败");
				}
			}
		});
	}
	/* 加载聊天记录 */
	function updateChat()
	{
		$.ajax({
	        type : "post",
			dataType : "html",
			url : "${basePath }/chatroom/chatroom/listChat",			
			success: function(returnText){
				if(returnText != "")
				{
					document.all.say.value = returnText;
					var scrollTop = $("#say")[0].scrollHeight;  
                    $("#say").scrollTop(scrollTop); 
				}
			}
		});
	}
	setInterval("updateChat()", 5000);
	updateChat();
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>