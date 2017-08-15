<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<style>
	span{
		text-align: right;
		margin-top:5px;
	}
</style>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title" style="margin-top:20px;margin-left:20px;">
	                    <h5>编辑公司信息</h5>
	                </div>
	                <div class="ibox-content">
	                    <form id="tijiao" class="form-horizontal formValidate" action="${basePath }/sup/company/company/saveCompanyInfo" method="post">
	                        <input type="hidden" name="token" value="${token}">

                        	<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>公司名称</span>
	                        	<c:if test="${companyInfo.comState eq 'Y'}"><div  class="col-sm-10 control-value">${companyInfo.comName }</div></c:if>
	                            <c:if test="${companyInfo.comState eq 'W'}"><div  class="col-sm-10 control-value">${companyInfo.comName }</div></c:if>
	                       		<c:if test="${companyInfo.comState eq 'C'}"><div  class="col-sm-4"><input type="text" name="comName" class="form-control" value="${companyInfo.comName }" required="" maxlength="500"></div></c:if>
	                       		<c:if test="${companyInfo.comState eq 'L'}"><div  class="col-sm-4"><input type="text" name="comName"  class="form-control" value="${companyInfo.comName }" required="" maxlength="500"></div></c:if>
	                       		<c:if test="${companyInfo.comState eq 'N'}"><div  class="col-sm-4"><input type="text" name="comName"  class="form-control" value="${companyInfo.comName }" required="" maxlength="500"></div></c:if>
	                        </div>
	                        <div class="hr-line-dashed"></div>
							<div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>公司地址</span>
	                            <div class="col-sm-4"><input type="text" name="comAddress" class="form-control" value="${companyInfo.comAddress }" required="" maxlength="500"></div>
	                           </div>
	                      
	                           <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>客户联系人</span>
	                            <div class="col-sm-4"><input type="text" name="comLink" class="form-control" required="" value="${companyInfo.comLink }" maxlength="200"></div>
	                            <span class="col-sm-2 control-span"><i class="ired">*</i>客户联系人电话</span>
	                            <div class="col-sm-4"><input type="text" name="comTel" class="form-control" required="" value="${companyInfo.comTel }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>客户联系人邮箱</span>
	                            <div class="col-sm-10"><input type="text" name="comEmail" class="form-control" required="" value="${companyInfo.comEmail }" maxlength="50"></div>
	                        </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>纳税识别码</span>
	                        	<c:if test="${companyInfo.comState eq 'Y'}"><div  class="col-sm-10 control-value">${companyInfo.taxnum }</div></c:if>
	                            <c:if test="${companyInfo.comState eq 'W'}"><div   class="col-sm-10 control-value">${companyInfo.taxnum }</div></c:if>
	                            <c:if test="${companyInfo.comState eq 'C'}"><div  class="col-sm-10"><input type="text"  name="comcustomcode" class="form-control comcustomcode" required="" value="${companyInfo.taxnum }" maxlength="50"></div></c:if>
	                        	<c:if test="${companyInfo.comState eq 'L'}"><div  class="col-sm-10"><input type="text"   name="comcustomcode" class="form-control comcustomcode" required="" value="${companyInfo.taxnum }" maxlength="50"></div></c:if>
	                      		<c:if test="${companyInfo.comState eq 'N'}"><div  class="col-sm-10"><input type="text"  name="comcustomcode" class="form-control comcustomcode" required="" value="${companyInfo.taxnum }" maxlength="50"></div></c:if>
	                        </div>
	                        
	                        
	                        <div class="form-group">
	                        	<span class="col-sm-2 control-span"><i class="ired">*</i>三证附件</span>
	                            <div class="col-sm-10">
	                            	<pm:fileList metaObject="${companyInfo }" delete="true" name="infoFiles" metaColums="colums"/>
	                            	<c:import url="/include/includeUploadify.jsp">
	                            		<c:param  name="propertyName" value="infoFiles"/>
										<c:param  name="metaColums" value="colums"/>
								    </c:import>
	                            </div>
	                        </div>
	                         <%-- <div class="form-group">
	                        	<span class="col-sm-2 control-span">外贸</span>
	                         	  <select name="comwaimao" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfowaimao}" var="item">
			                            <option class="form-control" value="${item.id}" >${item.comName}</option>
			                          </c:forEach>
		                          </select>
	                         
	                         </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">仓储</span>
								<select name="comcangchu" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfocangchu}" var="item">
			                            <option class="form-control" value="${item.id}" >${item.comName}</option>
			                          </c:forEach>
		                          </select>
	                         </div>
	                           <div class="hr-line-dashed"></div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">物流</span>
								<select name="comwuliu" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfowuliu}" var="item">
			                            <option class="form-control" value="${item.id}" >${item.comName}</option>
			                          </c:forEach>
		                          </select>
	                         </div>
	                         <div class="form-group">
	                        	<span class="col-sm-2 control-span">报关</span>
								<select name="combaoguan" style="height:30px;">
			                        <c:forEach  items="${supCompanyInfobaoguan}" var="item">
			                            <option class="form-control" value="${item.id}" >${item.comName}</option>
			                          </c:forEach>
		                          </select>
	                         </div> --%>
	                        <div class="form-group">
	                            <div class="col-sm-12 col-sm-offset-2">
	                                <button class="btn btn-primary"  onclick="comcustomecode()">保存</button>
	                                
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
function comcustomecode(){
	//检查纳税识别码是否相同
	var comcustomcode=$(".comcustomcode").val();
	$.ajax({
		 url:"${basePath }/sup/company/company/ajaxCheckCompanyInfocustoment", 
		 dataType:"json",
		 type:"post",
		 data:{comcustomcode:comcustomcode
		 },
		 success:function(data){
			 //$("#tijiao").submit();
			 if(data==0){
				 layer.alert('贵公司已经注册成功，已将您的账户加入贵公司人员中，请联系贵公司管理员批准!');
			 }
		 },error : function(data) { 
        }
	});  
		
}
</script>
<%@ include file="/include/includeFooter.jsp" %>