<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>任务列表</h5>
	                </div>
	                <div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/customtask/findCustomImccList" class="form-horizontal formValidate" method="post">
							    <input type="hidden" id="searchStatus" name="searchName1" />
							    <input type="hidden" id="searchTime"    name="searchDate1" />
								<div class="row">
		                            <div class="col-sm-12">
		                                   <label class="tables_search_label"><a  ownattr="全部" id="labelStatus"  style="text-decoration:underline;color:#44cef6;padding:20px;" onclick="findStatus(this)">全部</a></label>
		                                   <label class="tables_search_label"><a  ownattr="1"   id="labelStatus" style="text-decoration:none;color:#44cef6;padding:20px;" onclick="findStatus(this)">已发布</a></label>
		                                   <label class="tables_search_label"><a  ownattr="0"   id="labelStatus" style="text-decoration:none;color:#44cef6;padding:20px;" onclick="findStatus(this)">未发布</a></label>
		                            </div>
		                             <div class="col-sm-12" id="dateSearch">
		                                  <label class="tables_search_label">
		                                   <a   style="text-decoration:underline;color:#44cef6;padding-left:20px;" id="labelTime"ownattr="全部" onclick="findTime(this)">全部</a>
		                                     <select id="searchDateSelector"class="input-sm form-control"></select>
		                                  </label>
		                                   <label class="tables_search_label" id="dateMonth"></label>
		                                   <label class="tables_search_label">
		                                        <button type="button" class="btn btn-sm btn-primary button1" onclick="goSubmit()">查询</button>
		                                   </label>
		                            </div>
		                        </div>
		                        <div class="row">
									<div class="col-sm-12 tables_search_label">
										<pm:auth authCode="organize_editInterface">
											<a class="btn btn-white btn-sm myBtn" href="javascript:;" onclick="openLayer('添加','${basePath}/inter/interConfig/openAddInterfaceConfig',true)">
												<span>添加</span>
											</a>
										</pm:auth>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th>
													<div class="text-center"><input type="checkbox" class="checkbox" onclick="checkAll(this, 'ids')"></div>
												</th>
											    <th>操作</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'task_id'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>"  onclick="goSubmitSort('task_id', ${!searchBean.searchOrderType})">任务流水号</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'task_name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('task_name', ${!searchBean.searchOrderType})">项目名称</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'com_Name'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('com_Name', ${!searchBean.searchOrderType})">客户</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'issue_date'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('issue_date', ${!searchBean.searchOrderType})">发布日期</th>
												<th class="sorting<c:if test="${searchBean.searchOrderName eq 'is_issue'}">${searchBean.searchOrderType ? '_desc' : '_asc'}</c:if>" onclick="goSubmitSort('is_issue', ${!searchBean.searchOrderType})">状态</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
												<tr id="toolTr_${item[0]}">
													<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="${item[0]}"></td>
												  	<td>
														<pm:auth authCode="organize_editInterface">
															<a class="" href="javascript:;" onclick="openLayer('修改信息','${basePath }/inter/interConfig/editInterfaceConfig/${item[0]}',true)">
																<span>修改</span>
															</a>
														</pm:auth>
													</td>
													<td>${item[1]}</td>
													<td>${item[2]}</td>
													<td>${item[3]}</td>
													<td><fmt:formatDate value="${item[4]}" pattern="yyyy-MM-dd"/></td>
													<td>
														<c:choose>
															<c:when test="${item[5] eq '1'}"><span class="label label-warning" id="label_${item[0]}">已发布</span></c:when>
															<c:otherwise><span class="label label-danger" id="label_${item[0]}">未分配</span></c:otherwise>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<%@ include file="/include/includePage.jsp" %>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
      var date=new Date();
     var currentYear=date.getFullYear();
      $("#dateMonth").html("");
      $("#searchDateSelector").html("");
     $("#searchDateSelector").append("<option value='"+currentYear+"'>"+currentYear+"</option>");
     var month=date.getMonth();
      $("#dateMonth").append("<a   id='labelTime'  ownattr='全年'style='text-decoration:none;color:#44cef6;padding-left:20px;' onclick='findTime(this)'>全年</a>");
     for(var i=0;i<=month;i++){
        $("#dateMonth").append("<a id='labelTime'  ownattr='"+(i+1)+"' style='text-decoration:none;color:#44cef6;padding-left:20px;' onclick='findTime(this)'>"+(i+1)+"</a>");
     }
     
     function  findStatus(obj){
        var ownattr= obj.attributes["ownattr"].nodeValue; //自定义属性采用此方式获得
        if(ownattr=="全部"){
              $("#searchStatus").val("");
        }else{
            $("#searchStatus").val(ownattr);
        }
         $ (obj).css ('color', '#000').siblings ('a').css ('color', '#44cef6');
     }
    function findTime(obj){
         var ownattr= obj.attributes["ownattr"].nodeValue; //自定义属性采用此方式获得
         //alert(ownattr);
        if(ownattr=="全部"){
           $("#searchTime").val("");
        }else if(ownattr=="全年"){
             $("#searchTime").val(currentYear);
        }else{
        $("#searchTime").val(ownattr);
           }
             $ (obj).css ('color', '#000').siblings ('a').css ('color', '#44cef6');
     }
   
   
</script>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>