<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link rel="stylesheet" href="${basePath}/resource/css/dingdanguanli_1.css" />
<link rel="stylesheet" href="${basePath}/resource/css/font_1435131046_7356622.css" />
<%-- <link rel="stylesheet" href="${basePath}/resource/css/xiaozujian.css" /> --%>
<style>
	.ibox-content{
		padding: 0px;
	}
	/*logo 显示*/
.mo-mod-header-title h1 a {
    text-indent: -5000em;
    background:
    url(../resource/images/logo.jpg) !important;
     background-size : 115px 58px !important;
    width: 115px !important;
    height: 58px !important;
}
.mo-mod-header-title h1 a {
    text-indent: -5000em;
    background:
        url();
    background-size: 115px 43px;
    width: 115px;
    height: 43px;
}

.mo-mod-header-title {
    margin: 10px 0 0 2px;
}

body {
    -webkit-font-smoothing: auto;
}

.mo-list-nav .ui2-navigation-item {
    margin-right: 18px;
}

.awhite{
	background-color: #FFFFFF !important;
	border-bottom: 3px solid #FFFFFF !important;
}

th,tr,td{
	text-align: center !important;
}

/*
 九种类型的 背景颜色
 * */
.ui2-navigation-wide{
	background-color: rgba(229, 237, 249, 0.97);
}
.ssClass{
	display: none;
}	
</style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn" style="padding: 0px;">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-content">
	                	<div class="row">
			                <div class="col-lg-12">
			                    <div class="tabs-container">
			                        <div class="tab-content">
			                            <div id="tab-1" class="tab-pane active">
			                                <div class="panel-body">
			                                    <div class="dataTables_wrapper form-inline">
													<form id="pageForm" action="${basePath}/flow/bgimcc/listFlowImccOrder/${nodeType}/${flowId}/${companyid}" class="form-horizontal formValidate" method="post">
														<div class="row">
								                            <div class="col-sm-12" min-weight="900px;">
								                                <label class="tables_search_label">&thinsp;
								                                                                                                           订单号：<input type="text" class="input-sm form-control" name="searchName1" value="${searchBean.searchName1}">
								                                </label>
								                                <label class="tables_search_label">
																	时间：&thinsp;<input type="text" class="form-control input-sm date-picker" name="searchDate1" value="${searchBean.searchDate1}">-<input type="text" class="form-control input-sm date-picker" name="searchDate2" value="${searchBean.searchDate2}">
								                                	<button type="button" class="btn btn-sm button1" onclick="goSubmit()">查询</button>
								                                </label>
								                                 <label class="tables_search_label">
								                                 		<a href="${basePath}/order/getOrderExecl/${flowId}?orderCode=${searchBean.searchName1}&startDate=${searchBean.searchDate1}&endDate=${searchBean.searchDate2}$supName=${searchBean.searchName3}&name=${searchBean.searchName2}">导出</a>
								                                 		<a onclick="gjss()">高级搜索</a>
								                                </label>
								                            </div>
								                            <div class="col-sm-12 ssClass" style="margin-top:3px;margin-left:-5px;">
								                             	<label class="tables_search_label">
								                                                                                                          供应商 ：&thinsp;<input type="text" class="input-sm form-control" name="searchName3" value="${searchBean.searchName3}">
								                                </label>
								                             	<label class="tables_search_label">
								                                                                                                          客户 ：<input type="text" class="input-sm form-control" name="searchName2" value="${searchBean.searchName2}">
								                                </label>
								                             </div>
								                        </div>
					
														  <div class="table-responsive">
															<table class="table table-striped table-bordered table-hover dataTable">
												                <thead>
																	<tr>
																		<th>订单号</th>
																		<th>接单</th>
																		<th>查验</th>
																		<th>放行</th>
																		<th>状态</th>
																		<th>客户</th>
																		<th>服务商</th>
																		<th>完成率</th>
																		<th>操作</th>
																		<th><a data-role="spread-all-node" class="" href="javascript: void(0);" data-widget-cid="widget-22">
										                                    <span id="all_zhankai">展开</span> 
									                                        </a>
									                                    </th>
																	</tr>
																</thead>
												                <tbody>
												                	<c:forEach items="${pageBean.pageList }" var="item" varStatus="status">
																		<tr id="toolTr_${item[0]}">
																			<td>
																			    ${item[1] }
																			     <div style="display:none;margin-bottom:15px;"></div>
																			</td>
																			<td>
																			<c:choose>
		                                                                        <c:when test="${item[2] eq '0'}">进行中</c:when>
										                                        <c:when test="${item[2] eq '1'}">已完成</c:when>
										                                        <c:when test="${item[2] eq '3'}">未执行</c:when>
										                                        <c:otherwise>未执行</c:otherwise>
									                                        </c:choose>
																			<c:if test="${empty item[3]}">
									                                            <div style="display:none;margin-bottom:15px;"></div>
									                                         </c:if>
																			   <c:if test="${not empty item[3]}">
																			       <div style="display:none;">${item[3]}</div>
																			   </c:if>
																	       </td>
																			<td>
																			      <c:choose>
											                                             <c:when test="${item[4] eq '0'}">进行中</c:when>
											                                             <c:when test="${item[4] eq '1'}">已完成</c:when>
											                                             <c:when test="${item[4] eq '3'}">未执行</c:when>
											                                             <c:otherwise>未执行</c:otherwise>
									                                              </c:choose>
																			     <c:if test="${empty item[5]}">
									                                            <div style="display:none;margin-bottom:15px;"></div>
									                                         </c:if>
																			   <c:if test="${not empty item[5]}">
																			       <div style="display:none;">${item[5]}</div>
																			   </c:if>
																			</td>
																			<td>
																			      <c:choose>
											                                           <c:when test="${item[6] eq '0'}">进行中</c:when>
											                                           <c:when test="${item[6] eq '1'}">已完成</c:when>
											                                           <c:when test="${item[6] eq '3'}">未执行</c:when>
											                                           <c:otherwise>未执行</c:otherwise>
									                                              </c:choose>
																			     <c:if test="${empty item[7]}">
									                                            <div style="display:none;margin-bottom:15px;"></div>
									                                         </c:if>
																			   <c:if test="${not empty item[7]}">
																			       <div style="display:none;">${item[7]}</div>
																			   </c:if>
																			</td>
																		<td>
																		          <c:choose>
																		                 <c:when test="${item[8] eq '1'}">未提交</c:when>
																		                 <c:when test="${item[8] eq '2'}">未审核</c:when>
																		                 <c:when test="${item[8] eq '3'}">已取消</c:when>
																		                 <c:when test="${item[8] eq '4'}">终止</c:when>
																		                 <c:when test="${item[8] eq '5'}">草稿</c:when>
															                             <c:when test="${item[8] eq '6'}">处理中</c:when>
															                             <c:when test="${item[8] eq '7'}">已完成</c:when>
															                             <c:when test="${item[8] eq '8'}">未执行</c:when>
															                             <c:when test="${item[8] eq '9'}">已提交</c:when>
																		                 <c:when test="${item[8] eq '10'}">服务商已接收</c:when>
															                             <c:when test="${item[8] eq '11'}">服务商已拒绝</c:when>
															                             <c:when test="${item[8] eq '12'}">未分配</c:when>
															                             <c:when test="${item[8] eq '13'}">已分配</c:when>
															                             <c:when test="${item[8] eq '14'}">已终止</c:when>
															                             <c:when test="${item[8] eq '15'}">进行中</c:when>
															                             <c:otherwise>草稿</c:otherwise>
														                           </c:choose>
														                            <div style="display:none;margin-bottom:15px;"></div>
																		</td>
																			<td>${item[11]}</td>
																		<td>
																		     ${item[9]}
																		     <div style="display:none;margin-bottom:15px;"></div>
																		</td>
																		<td>
																		        ${item[10]}%
																		        </td>
																			<td>
																					<c:choose>
																				          <c:when test="${item[8] eq '12'}">
														                                      <a class="" target="iframe_PM" href="${basePath }/task/listAllocationTask">
																						           <span>订单分配</span>
																					           </a>
																					          <a class="quxiao" href="javascript:;" onclick="toolTableUpdate('${basePath }/flow/bgimcc/supupdateorder','${item[0] }','拒绝订单')">
																						           <span>拒绝</span>
																					           </a>  
																					           <a class="" target="iframe_PM" href="${basePath }/pm/order/CustomsDeAgreementimcc/viewCustomsDeAgreement/${item[0] }">
																						               <span> 查看</span>
																					             </a>    
																				          </c:when>
																				          <c:when test="${item[8] eq '11' }">
																				                  
																				                 <a class="quxiao" href="javascript:;" onclick="toolTableUpdate('${basePath }/flow/bgimcc/supupdateorder','${item[0] }','拒绝订单')">
																						           <span>拒绝</span>
																					           </a> 
																					           <a class="" target="iframe_PM" href="${basePath }/pm/order/CustomsDeAgreementimcc/viewCustomsDeAgreement/${item[0] }">
																						               <span> 查看</span>
																					             </a>
																				          </c:when>
																				           <c:otherwise>
																				                  <a class=""target="iframe_PM" href="${basePath }/pm/order/CustomsDeAgreementimcc/viewCustomsDeAgreement/${item[0] }">
																						               <span> 查看</span>
																					             </a>
																				           </c:otherwise>
																				    </c:choose>
																				     <a class="" onclick="openLayerSize('补充资料','${basePath }/pm/order/add/listorderadd/${item[0] }',true,'900px','600px')" href="javascript:;">
																						               <span> 补充信息</span>
																					             </a>
																				    <div style="display:none;margin-bottom:15px;"></div>
																			</td>
																			<td class="">
																				<a data-role="spread-node" class="" href="javascript: void(0);" data-widget-cid="widget-14">
																					<span class="spread-node ">展开</span><i class=""></i>
																				</a>
																				 <div style="display:none;margin-bottom:15px;"></div>
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
			            </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
   
        $('.sidebar-main-menu-item').hover(function(){
            // 移入显示
            $(this).children('ul').css('display','block');
        },function(){
            // 移出隐藏
            $(this).children('ul').css('display','none');
        }); 

		// 状态选择
       $('.ui2-tab-nav a').click(function(){
       		$('.ui2-tab-nav a').removeClass('awhite');
       		$(this).addClass('awhite');
       });
        
        // 9种类型选择
       $('.ui2-navigation-item').click(function(){
	       	$('.ui2-navigation-item').removeClass('ui2-navigation-item-current');
	       	$(this).addClass('ui2-navigation-item-current');
       });
       
       // 展开
       $(".spread-node").click(function(){
            //alert("1111");
            var domName=$(this).text();
            if(domName=="展开"){
                 $(this).parent().parent().parent().find("div").css("display","block");
                
                 $(this).html("收起");
             }else{
                 $(this).parent().parent().parent().find("div").css("display","none"); 
                  $(this).html("展开");
             }
           
        });
        
        //全部展开
        $("#all_zhankai").click(function(){
            var  domText=$(this).text();
             if(domText=="展开"){
                 $(this).parent().parent().parent().parent().parent().find("div").css("display","block");
                 $(this).html("收起");
             }else{
                  $(this).parent().parent().parent().parent().parent().find("div").css("display","none");
                 $(this).html("展开");
             }
        });
        var count=0;
        function gjss(){
        	if (count%2==0) {
        		$(".ssClass").show();
			}else{
				$(".ssClass").hide();
			}
        	count++;
        }
   </script>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>