<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
		margin-top: 5px;
	}
</style>


<body class="gray-bg" >
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>编辑公司信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal formValidate" action="${basePath }/company/info/saveCompanyInfo" method="post">
	                        <input type="hidden" name="token" value="${token}">
	                        <input type="hidden" name="id" value="${companyInfo.id}">

                        	<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>公司名称</span>
	                            <div class="col-sm-10"><input type="text" name="comName" class="form-control" value="${companyInfo.comName }" required="" minlength="5" maxlength="200" remote="${basePath}/company/info/ajaxCheckCompanyInfo?comName=${companyInfo.comName}&id=${companyInfo.id}"></div>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>公司地址</span>
	                            <div class="col-sm-10"><input type="text" name="comAddress" class="form-control" value="${companyInfo.comAddress }" required="" maxlength="500"></div>
	                             </div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span">纳税识别码</span>
	                            <div class="col-sm-4"><input type="text" name="taxnum" class="form-control" value="${companyInfo.taxnum}" maxlength="200"></div>
	                        </div>
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span">客户联系人</span>
	                            <div class="col-sm-4"><input type="text" name="comLink" class="form-control" value="${companyInfo.comLink}" maxlength="200"></div>
	                            <span class="col-sm-2 control-span">客户联系人电话</span>
	                            <div class="col-sm-4"><input type="text" name="comTel" class="form-control" value="${companyInfo.comTel}" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">客户联系人邮箱</span>
	                            <div class="col-sm-4"><input type="text" name="comEmail" class="form-control" value="${companyInfo.comEmail}" maxlength="200"></div>
	                         </div>
	                        <div class="hr-line-dashed"></div>
	                        
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">外贸</span>
	                        	<div class="col-sm-4">
	                         	  <select name="comwaimao" class="waimao" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfowuliu}" var="item">
			                        	<c:choose>
										 	<c:when test="${item.id==companyInfo.comwaimao }">
												 <option value="${item.id }" selected="selected">${item.comName }</option>
											</c:when>
											<c:otherwise>
												  <option  class="form-control" value="${item.id}" >${item.comName}</option>
											</c:otherwise>
										</c:choose>
			                          </c:forEach>
		                          </select>
		                          </div>
		                           <div class="col-sm-4"><input  type="text" class="form-control" id="waimaoss" value="${waimao.userName}"/></div>
		                          
		                          <div style="display:none;">
		                          	<input type='text' id='waimaos'name="waimaouser"/>
		                          </div>
	                         	<input type="button" class="btn btn-primary" onclick="waimao()" value="选择责任人"/>
	                         </div>
	                         
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">仓储</span>
	                        	<div class="col-sm-4">
								<select name="comcangchu" class="cangchu" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfowuliu}" var="item" >
			                           <c:choose>
										 	<c:when test="${item.id==companyInfo.comcangchu }">
												 <option value="${item.id }" selected="selected">${item.comName }</option>
											</c:when>
											<c:otherwise>
												  <option  class="form-control" value="${item.id}" >${item.comName}</option>
											</c:otherwise>
										</c:choose>
			                          </c:forEach>
		                          </select>
		                          </div>
		                           <div class="col-sm-4"><input  type="text" class="form-control" id="cangchuss" value="${cangchu.userName}"/></div>
		                          
		                          <div style="display:none;">
		                          
		                          	<input type='text' id='cangchus' name="cangchuuser"/>
		                          </div>
		                          <input type="button" class="btn btn-primary" onclick="cangchu()" value="选择责任人"/>
	                         </div>
	                         
	                           <div class="hr-line-dashed"></div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">物流</span>
	                        	<div class="col-sm-4">
								<select name="comwuliu" class="wuliu" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfowuliu}" var="item">
			                           <c:choose>
										 	<c:when test="${item.id==companyInfo.comwuliu }">
												 <option value="${item.id }" selected="selected">${item.comName }</option>
											</c:when>
											<c:otherwise>
												  <option  class="form-control" value="${item.id}" >${item.comName}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
		                          </select>
		                          </div>
		                          <div class="col-sm-4"> <input  type="text" class="form-control" id="wuliuss" value="${wuliu.userName}"/></div>
		                          <div style="display:none;">
		                          	<input type='text' id='wulius' name="wuliuuser"/>
		                          </div>
		                          <input type="button" class="btn btn-primary" onclick="wuliu()" style="" value="选择责任人"/>
	                         </div>
	                         
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">报关</span>
	                        	<div class="col-sm-4">
									<select class="baoguan" name="combaoguan" style="height:30px;" >
				                        <c:forEach  items="${supCompanyInfowuliu}" var="item">
				                           <c:choose>
										 	<c:when test="${item.id==companyInfo.combaoguan }">
												 <option value="${item.id }" selected="selected">${item.comName }</option>
											</c:when>
											<c:otherwise>
												  <option  class="form-control" value="${item.id}" >${item.comName}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
			                          </select>
		                          </div>
		                          
		                           <div class="col-sm-4"><input  type="text" class='form-control' id="baoguanss" value="${baoguan.userName}"/></div>
		                           <input type="button" class="btn btn-primary" onclick="baoguan()" value="选择责任人"/>
		                          <div style="display:none;">
		                          	<input type='text' class="form-control" id='baoguans' name="baoguanuser"/>
		                          </div>
		                          
		                         
	                         </div>
	                          <div class="form-group" style="margin-left:100px;">
	                        	 <div class="col-sm-10">
	                            	<pm:fileList metaObject="${companyInfo }" delete="true" name="infoFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
	                            		<c:param  name="propertyName" value="infoFiles"/>
										<c:param  name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                         </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">备注:</span>
	                            <div class="col-sm-4">
	                            	<input type="text" name=beizhu class="form-control" value="${companyInfo.beizhu}" maxlength="200">
                            	</div>
	                         </div>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary" type="submit">保存</button>
	                                
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
$(document).ready(function (){
	var ids = [${selectList}];
	for(var i=0; i < ids.length; i++){
		$('#' + ids[i]).attr("checked",'true');
	}
});

function waimao(){
	var id=$('.waimao').val();
	var name="waimao";
	layer.open({
		  type: 2,
		  area: ['800px', '500px'],
		  fixed: true, //不固定
		  maxmin: true,
		  content: '${basePath }/company/info/listSupCompanyInfouser/'+id+'/'+name
		});
}

function cangchu(){
	var id=$('.cangchu').val();
	var name="cangchu";
	layer.open({
		  type: 2,
		  area: ['800px', '500px'],
		  fixed: true, //不固定
		  maxmin: true,
		  content: '${basePath }/company/info/listSupCompanyInfouser/'+id+'/'+name
		});
}

function wuliu(){
	var id=$('.wuliu').val();
	var name="wuliu";
	layer.open({
		  type: 2,
		  area: ['800px', '500px'],
		  fixed: true, //不固定
		  maxmin: true,
		  content: '${basePath }/company/info/listSupCompanyInfouser/'+id+'/'+name
		});
}
function baoguan(){
	var options=$(".baoguan");
	        
	var id=options.val();
	var name="baoguan";
	layer.open({
		  type: 2,
		  area: ['800px', '500px'],
		  fixed: true, //不固定
		  maxmin: true,
		  content: '${basePath }/company/info/listSupCompanyInfouser/'+id+'/'+name
		});
}

function returnValue(selectValues){
	if(selectValues !='' || selectValues.length > 0){
		var strId = selectValues[0];
		var strName = selectValues[1];
		var Name = selectValues[3];
		if(Name=='waimao'){
			$("#waimaoss").val(strName);
			$("#waimaos").val(strId);
		}
		if(Name=='baoguan'){
			$("#baoguanss").val(strName);
			$("#baoguans").val(strId);
		}
		if(Name=='cangchu'){
			$("#cangchuss").val(strName);
			$("#cangchus").val(strId);
		}
		if(Name=='wuliu'){
			$("#wuliuss").val(strName);
			$("#wulius").val(strId);
		}
	}
}
</script>
<%@ include file="/include/includeFooter.jsp" %>