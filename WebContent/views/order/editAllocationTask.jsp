<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />

<div class="">
  	<form id="submitOnclickId" class="form-horizontal formValidate" role="form" action="${basePath }/order/saveOrderFrom" method="post">
  		<input type="hidden" name="id" value="${orderFrom.id}">
  		<input type="hidden" id="iniPage" name="flowTypeId" value="${flowType.id}">
  		<input type="hidden" name="taskId" value="${task.id}">
  		<input type="hidden" id="suppers" name="suppersId" value="">
  		<input id="flowNameId" type="hidden" value="${flowType.id }" name="flowName" class="form-control" required="" maxlength="50">
  		<div class="col-md-12" style="padding:0px;">
			<div class="panel panel-grey">
	        	<div class="yhxx">
	          		<label class="label" style="color:#333">分配任务信息</label>
	          	</div>
	          	<div class="panel-body">
	            	<div class="col-sm-12">
						<div class="form-group">
	                        <label class="col-sm-2 control-label"><i class="ired">*</i>任务流水号</label>
	                        <div class="col-sm-4"><input type="text" value="${task.taskId }" name="userName" class="form-control" required="" readonly="readonly" maxlength="200"></div>
	                        <label class="col-sm-2 control-label"><i class="ired">*</i>任务类型</label>
	                        <div class="col-sm-4"><input  type="text" value="${flowType.flowName }" name="flowName" class="form-control" required="" readonly="readonly" maxlength="50"></div>
	                    </div>
						<div class="form-group">
	                        <label class="col-sm-2 control-label"><i class="ired">*</i>发布客户</label>
	                        <div class="col-sm-4"><input type="text" value="${info.comName }" name="userName" class="form-control" required="" readonly="readonly" maxlength="200"></div>
	                        <label class="col-sm-2 control-label"><i class="ired">*</i>发布日期</label>
	                        <div class="col-sm-4"><input type="text" value="${task.issueDate }" class="form-control input-sm " name="issueDate" readonly="readonly" value=""></div>
	                       
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label"><i class="ired">*</i>指定服务商</label>
	                        <div class="col-sm-4">
								<input  type="text" readonly="readonly" value=""class="form-control name" required="" maxlength="200">
							</div>
							<div class="col-sm-1">
									<button type="button" onclick="layerOnclick()"  class="btn btn-default">获取供应商信息</button>
							</div>
	                    </div>
         				<div class="form-group">
	                        <label class="col-sm-2 control-label"><i class="ired">*</i>客服备注</label>
	                        <div class="col-sm-9"><textarea id="noticeText" name="orderOpinion" cols="98" rows="10">${orderFrom.orderOpinion}</textarea></div>
	                    </div>
	                    <div class="form-group">
	                        	<label class="col-sm-2 control-label">附件</label>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${orderFrom }" delete="true" name="orderFromFile" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
										<c:param name="propertyName" value="orderFromFile"/>
										<c:param name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
					</div>
				</div>
			</div>
		</div>
        <div class="text-center zc-bt">
      		<button class="btn btn-primary btn-1" id="submitOnclickId" type="button" onclick="submitOnclick()">提交</button>
      	</div>
	</form>
</div>

<%@ include file="/include/includeJs.jsp"%>
<%@ include file="/include/includeFooter.jsp"%>
<script>
function submitOnclick(){
	 var suppersId=$("#suppers").val();
	 if (suppersId==0) {
		 parent.layer.msg('请选择接单公司',{icon: 2});
	}else{
		$("#submitOnclickId").submit();
	}
}
function layerOnclick(){
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  area: ['780px', '500px'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: '${basePath }/order/listCompanyInfo'
	});
}

function returnValues_(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var commodityName = selectValues[1];
		$("#suppers").val(strId);
		$(".name").val(commodityName);
	}
	
}
</script>