<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
  <link href="${basePath }/resource/css/jiedian.css" rel="stylesheet" />



<meta charset="UTF-8">
		<title>外贸出口</title>
		<link href="${basePath }/resource/css/font_1435131046_7356622.css" rel="stylesheet" type="text/css">

		<link rel="stylesheet" href="${basePath }/resource/css/dingdanguanli_2.css" />

		<link href="${basePath }/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
		<style type="text/css">
			body {
				margin: 0;
				font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
				font-size: 14px;
				line-height: 20px;
				color: #333333;
				background-color: #F5F7FA;
			}
			
			li {
				margin-left: 0px;
				display: list-item;
			}
			
			tr td {
				border: 1px solid black;
				text-align: center;
			}
			
				/已完成/
			.is_ok{
				color:#FF751A !important;
			}
			
			/未完成/
			.is_no{
				color:FFC299 !important;
			}
			
			.col-sm-9{
		padding:0px;
	}
		</style>
	<body>
		<div id="container" class="mo-box mo-detail-box" data-widget-cid="widget-2" >
		<div class="ibox-title">
                    <a class="btn btn-white btn-sm myBtn" onclick="JavaScript:history.back(-1);">
						<span>返回</span>
					</a>
                </div>
			<div data-widget-cid="widget-3">
				<div class="mo-list-box mo-order-list " >
					<div class="mo-list-head util-clearfix">
						<div class="mo-flux-100" >
							<div class="mo-f-inner-l mo-list-item-order util-clearfix">
								<div class="mo-process mo-order-process">
									<ul class="mo-process-10 util-clearfix">
										<li class="mo-process-item first mo-process-done-item " style="width:16%"  >
	
											<span class="label" >单证制作</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item  " style="width:16%">
	
											<span class="label">信用证开证</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item " style="width:16%">
	
											<span class="label" >收汇</span>
										</li>
										<li class="mo-process-item  mo-process-done-item " style="width:16%">
	
											<span class="label" >出口清关</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item  " style="width:16%">
	
											<span class="label">结算开票</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item " style="width:16%">
	
											<span class="label" >退税申请</span>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="mo-list-content">
						
						<div class="mo-list-item util-clearfix">
							<div class="mo-flux-100">
								<div class="mo-f-inner-l mo-list-item-order util-clearfix">
									<ul class="mo-order-base">
										<li><strong>订单:${order_code.orderCode}</strong></li>
									</ul>
									<div class="mo-process mo-order-process">
										<ul class="mo-process-10 util-clearfix">

											<li class="mo-process-item first mo-process-done-item 
										 "  style="width:16%">

											<div class="mo-process-mark">
												<i  id="jiedians1" class="ui2-icon ui2-icon-dot is_no" style="padding-left: 35% !important;"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian1" value="${order_code.node3State}"/>
													<c:choose>
														<c:when test="${order_code.node1State eq '１'}"><span class="label label-warning">已完成</span></c:when>
														<c:when test="${order_code.node1State eq '０'}"><span class="label label-warning">进行中</span></c:when>
														<c:when test="${order_code.node1State eq '3'}"><span class="label label-warning">未完成</span></c:when>
													</c:choose>
													</a>
												</div>
												<div class="mo-process-detail">
													<div class="mo-process-detail-inner"></div>
												</div>
											</div>
											</li>

										<li class="mo-process-item mo-process-done-item 
										 " style="width:16%" >

											<div class="mo-process-mark">
												<i id="jiedians2" class="ui2-icon ui2-icon-dot is_no"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian2" value="${order_code.node2State}"/>
													<c:choose>
														<c:when test="${order_code.node2State eq '１'}"><span class="label label-warning">已完成</span></c:when>
														<c:when test="${order_code.node2State eq '０'}"><span class="label label-warning">进行中</span></c:when>
														<c:when test="${order_code.node2State eq '3'}"><span class="label label-warning">未完成</span></c:when>
													</c:choose>
													</a>
												</div>
												<div class="mo-process-detail">
													<div class="mo-process-detail-inner"></div>
												</div>
											</div>
											</li>
											
										<li class="mo-process-item  mo-process-done-item 
										 " style="width:16%">

											<div class="mo-process-mark">
												<i id="jiedians3" class="ui2-icon ui2-icon-dot is_no"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian3" value="${order_code.node1State}"/>
													<c:choose>
														<c:when test="${order_code.node3State eq '１'}"><span class="label label-warning">已完成</span></c:when>
														<c:when test="${order_code.node3State eq '０'}"><span class="label label-warning">进行中</span></c:when>
														<c:when test="${order_code.node3State eq '3'}"><span class="label label-warning">未完成</span></c:when>
													</c:choose>
													</a>
												</div>
												<div class="mo-process-detail">
													<div class="mo-process-detail-inner"></div>
												</div>
											</div>
											</li>
											
											
											
											
											
											<li class="mo-process-item mo-process-done-item 
										 "  id="jiedians4" style="width:16%">

											<div class="mo-process-mark">
												<i id="jiedians4" class="ui2-icon ui2-icon-dot is_no"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian3" value="${order_code.node4State}"/>
													<c:choose>
														<c:when test="${order_code.node4State eq '１'}"><span class="label label-warning">已完成</span></c:when>
														<c:when test="${order_code.node4State eq '０'}"><span class="label label-warning">进行中</span></c:when>
														<c:when test="${order_code.node4State eq '3'}"><span class="label label-warning">未完成</span></c:when>
													</c:choose>
													</a>
												</div>
												<div class="mo-process-detail">
													<div class="mo-process-detail-inner"></div>
												</div>
											</div>
											</li>

										<li class="mo-process-item mo-process-done-item 
										 " style="width:16%">

											<div class="mo-process-mark">
												<i  id="jiedians5" class="ui2-icon ui2-icon-dot is_no"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian5" value="${order_code.node5State}"/>
													<c:choose>
														<c:when test="${order_code.node5State eq '１'}"><span class="label label-warning">已完成</span></c:when>
														<c:when test="${order_code.node5State eq '０'}"><span class="label label-warning">进行中</span></c:when>
														<c:when test="${order_code.node5State eq '3'}"><span class="label label-warning">未完成</span></c:when>
													</c:choose>
													</a>
												</div>
												<div class="mo-process-detail">
													<div class="mo-process-detail-inner"></div>
												</div>
											</div>
											</li>
											
										<li class="mo-process-item last mo-process-done-item 
										 "  style="width:16%">

											<div class="mo-process-mark">
												<i id="jiedians6" class="ui2-icon ui2-icon-dot is_no" style="padding-right:62%"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian6" value="${order_code.node6State}"/>
													<c:choose>
														<c:when test="${order_code.node6State eq '１'}"><span class="label label-warning">已完成</span></c:when>
														<c:when test="${order_code.node6State eq '０'}"><span class="label label-warning">进行中</span></c:when>
														<c:when test="${order_code.node6State eq '3'}"><span class="label label-warning">未完成</span></c:when>
													</c:choose>
													</a>
												</div>
												<div class="mo-process-detail">
													<div class="mo-process-detail-inner"></div>
												</div>
											</div>
											</li>
										</ul>
									</div>
									<div class="mo-order-type"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="daohang" style="margin-bottom:29px;border:0px solid red;">
						<ul id="myTab" class=" nav-tabs" style="list-style: none; border:none;">
							<li class="active">
								<a href="#jibenxinxi" data-toggle="tab">
									基本信息
								</a>
							</li>
							<c:forEach items="${pageBean.pageList}" var="item">
							
								<c:if test="${item[27]=='0' }">
									<c:if test="${order_code.orderState =='10'}">
										<li>
											<a href="#weituohan" data-toggle="tab">
												委托函
											</a>
										</li>
									</c:if>
									<c:if test="${order_code.orderState =='7'}">
										<li>
											<a href="#weituohan" data-toggle="tab">
												委托函
											</a>
										</li>
									</c:if>
									<c:if test="${order_code.orderState =='15'}">
										<li>
											<a href="#weituohan" data-toggle="tab">
												委托函
											</a>
										</li>
									</c:if>
								</c:if>
							</c:forEach>
							<li>
								<a href="#danjuxiangqing" data-toggle="tab">
								单据详情
							</a>
						</li>
					</ul>
				</div>
			<div class="ui2-tab ui2-tab-primary" data-widget-cid="widget-22" >
				
				<div class="ui2-tab-body ui2-tab-content">
			
					<div id="myTabContent" class="tab-content" style="border: 1px solid #dedede; border-top: 0px;padding: 5px;">
						<div id="weituohan" class="tab-pane fade"> 
								<div style="width:100%;text-align:center;font-size:18px;">委托函</div>
								<div >致云贸通：<div style="float:right;">订单号:${order_code.orderCode}</div></div>
								<div style="margin-top:10px;"> 我方了解并同意：《本服务委托函》（简称“委托函”），为我方与贵司签署的《服务协议书》（简称“服务协议”）的补充内容，委托函的内容与服务协议中约定不一致的，以委托函中内容为准；其他未尽事宜，以服务协议中内容为准。
										</div>
								<div style="margin-top:10px;">
								       我方了解并同意：本委托函出具后，需待贵司确认接受委托后双方的委托代理关系方成立。</div>
						     	<div style="margin-top:10px;">  我方现就服务委托贵司办理的事项及货物相关信息确认如下：</div>
						     	
						     	
						     	
						     	<div style="margin-top:20px;"> 一、委托事项</div>
								<div style="text-align:center;width:10%;margin-left:20%;margin-top:20px;border:1px solid black;border-bottom:0px;float:left;">序号 </div>
								<div style="text-align:center;width:30%;margin-top:20px;border:1px solid black;border-bottom:0px;border-left:0px;float:left;">办理项目</div>
								<div style="text-align:center;width:10%;border:1px solid black;margin-left:20%;margin-top:40px;">1 </div>
								<div style="text-align:center;width:30%;border:1px solid black;border-left:0px;margin-left:299px;margin-top:-22px;">外贸出口</div>
								<div style="margin-toP:100px;">二、委托信息</div>
								<div style="margin-top:20px;">
										<c:forEach items="${pageBean.pageList}" var="item">
													
													
														<div class="edit-block J-content " >
															<div   class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68" style="width:50%;float:left;">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望开始时间:<span class="J-display"><fmt:formatDate value="${item[28]}" pattern="yyyy-MM-dd"/> </span>
																</label>
	
																	
															</div>
															<div  class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68" style="width:50%;float:left;">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望完成时间 :<span class="J-display"><fmt:formatDate value="${item[29]}" pattern="yyyy-MM-dd"/> </span>
																</label>
	
																	
															</div>
														<div  class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68" style="width:50%;float:left;">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>备注说明 :<span class="J-display">${item[30]}</span>
																</label>
	
																	
															</div>
															<div  class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68" style="width:50%;float:left;">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>委托方:<span class="J-display">${item[31]}</span>
																</label>
	
																	
															</div>
														
														
															<div  class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68" style="width:50%;float:left;">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>合同日期 :<span class="J-display"><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/> </span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>合同号:<span>${item[6]}</span>
																</label>
	
	
																		
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-trading-country entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-74">
																<label class="ui-form-label" data-label="HS编码">
																	<i class="ui-form-required"></i> 卖方 :<span>${item[7]}</span>
																</label>
	
																	<%-- <pm:execute id="supcompanyinfo" bean="companyInfoBusinImpl" method="getSupCompanyInfo">
																			<pm:execute-param type="java.lang.String" value="${item[7]}" />
																	</pm:execute>
																	<span>${supcompanyinfo.comName}</span> --%>
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-is-special-relation entity-type-radio ui-view-mode"
																data-widget-cid="widget-75">
																<label class="ui-form-label" data-label="重量">
																	<i class="ui-form-required"></i>  卖方地址 :<span>${item[8]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-is-price-affect-confirm entity-type-radio ui-view-mode"
																data-widget-cid="widget-76" style="display: block;">
																<label class="ui-form-label" data-label="包装情况">
																	<i class="ui-form-required"></i>  卖方联系电话 :<span>${item[9]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-is-royalty-payment entity-type-radio ui-view-mode"
																data-widget-cid="widget-77">
																<label class="ui-form-label" data-label="合同号">
																	<i class="ui-form-required"></i>  卖方传真:<span>${item[10]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-target-country-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-78">
																<label class="ui-form-label" data-label="许可文件号">
																	<i class="ui-form-required"></i>  买方:<span>${item[11]}</span>
																</label>
	
																	<%-- <pm:execute id="companyinfouser" bean="companyInfoBusinImpl" method="getCompanyInfoUser">
																			<pm:execute-param type="java.lang.String" value="${item[11]}" />
																	</pm:execute>
																	<pm:execute id="companyinfo" bean="companyInfoBusinImpl" method="getCompanyInfo">
																			<pm:execute-param type="java.lang.String" value="${companyinfouser.companyInfo.id}" />
																	</pm:execute>
																	<span>${companyinfo.comName}</span> --%>
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-goods-supply-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-79">
																<label class="ui-form-label" data-label="货物总价">
																	<i class="ui-form-required"></i>买方地址:<span>${item[12]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-goods-save-adr entity-type-inventorys-information ui-view-mode"
																data-widget-cid="widget-80">
																<label class="ui-form-label" data-label="进日期">
																	<i class="ui-form-required"></i> 买方联系电话:<span>${item[13]}</span>
																</label>
																		
	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-biz-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-82" style="display: block;">
																<label class="ui-form-label" data-label="贸易方式">
																	<i class="ui-form-required"></i> 装运时间 :<span class="J-display">${item[15]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-goods-save-adr entity-type-inventorys-information ui-view-mode"
																data-widget-cid="widget-80">
																<label class="ui-form-label" data-label="进日期">
																	<i class="ui-form-required"></i> 买方传真:${item[14]}
																</label>
																
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-declaration-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-83" style="display: block;">
																<label class="ui-form-label" data-label="货源地">
																	<i class="ui-form-required"></i>  交货时间 :<span class="J-display"><fmt:formatDate value="${item[16]}" pattern="yyyy-MM-dd"/> </span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-not-order-task-number entity-type-text ui-view-mode"
																data-widget-cid="widget-84" style="display: block;">
																<label class="ui-form-label" data-label="其他要求">
																	 装运口岸:<span class="J-display">${item[17]}</span> </label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-order-remark-no entity-type-text ui-view-mode"
																data-widget-cid="widget-85" style="display: block;">
																<label class="ui-form-label" data-label="被委托方">
																	目的口岸:<span class="J-display">${item[18]}</span> </label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" data-label="报关单编码">
																	包装:<span class="J-display">${item[19]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	保险:<span class="J-display">${item[20]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	成交方式 :<span class="J-display">${item[21]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	付款条件:<span class="J-display">${item[22]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	币制:<span class="J-display">${item[23]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	仲裁条款:<span class="J-display">${item[24]}</span> </label>
	
																	
															</div>
															
															 
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	其他:<span class="J-display">${item[25]}</span> </label>
	
																	
															</div>
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	客户公司:<span class="J-display">${item[1]}</span> </label>
	
																	
															</div>
													</div>
													</c:forEach>
								</div>
								<div style="float:left;width:100%;margin-top:50px;">
									<c:forEach items="${imContractDetail}" var="item">
											<div style="width:50%;float:left;"
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													 商品名称:<span class="J-display">${item[0]}</span> </label>
													
											</div>
											<div style="width:50%;float:left;"
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													 商品型号:<span class="J-display">${item[1]}</span> </label>

													
											</div>
											<div style="width:50%;float:left;"
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													商品数量:<span class="J-display">${item[2]}</span> </label>

													
											</div>
											<div style="width:50%;float:left;"
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													商品单价:<span class="J-display">${item[3]}</span> </label>

													
											</div>
											<div style="width:50%;float:left;"
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													商品总价:<span class="J-display">${item[4]}</span> </label>

													
											</div>
										</c:forEach>
								</div>
								
								<div style="width:100%;margin-top:50px;float:left;">三、委托承诺</div>
								<div style="width:100%;margin-top:20px;float:left;">      
											我方承诺对上述委托及信息的真实性、准确性、合法性及有效性负责，如因上述信息错误导致的一切费用、损失及责任均由我方承担，包括但是不限于海关查验，无法向税务局申请出口退税等。
								</div>
								<div style="margin-top:10px;float:left;">我方承诺上述货物已与海关买家签订相关的贸易合同并与海外买家约定由贵司代为出口及代收外汇。</div>
								<div style="margin-top:10px;float:left;">  我方同意，贵司有权将上述服务项目的一部分或者全部交由贵司之关联公司操作，我方承担配合贵司及、或贵司之关联公司的操作并接受其服务。</div>
								<div style="margin-top:10px;float:left;"> 我方了解并同意，有关服务及财务事项（特别是收付汇款账号及发票信息），应以法律认可书面方式与贵司沟通及确认，否则由此导致的损失由我方自行承担。</div>
								
								
								<div style="margin-top:30px;width:100%;float:left;">四、费用与结算</div>
								<div style="width:100%;margin-top:10px;float:left;">
								以上委托的费用及相关付款条款由双方在《服务协议》约定，我方了同意此服务委托单之费用结算以实际发生的为准。
								</div>
								<div style="float:right;">
									<c:forEach items="${pageBean.pageList}" var="item">
										确认时间：<fmt:formatDate value="${item[35]}" pattern="yyyy-MM-dd"/>
									</c:forEach>
								</div>
						</div>
						<div id="danjuxiangqing" class="tab-pane fade"> 
						     <div class="panel-body">
						     <div class="col-sm-12 tables_search_label">
										<a class="" href="javascript:;" onclick="ajaxTable('${basePath }/sup/order/OrderFrom/saveFlow/${order_code.id}/E8EA1196-D803-4040-B309-C65965A4F142',  'removeToolTableTr', '添加单据信息')">
											<span>添加外贸出口单据</span>
										</a>
									</div>
		                <div class="ibox-content" style="height: 100%;">
							<form id="pageForm1" action="${basePath }" method="post">
								<div class="col-sm-3">
									<c:import url="/views/sup/order/ex/WmExccOrderTree.jsp">
										<c:param name="name" value="unitInfoId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
			                 	</div>
			                </form>
	                        <div class="col-sm-9">
	                       		 <iframe id="deptUserList" src="${basePath}/sup/Wmexcc/listWmexcc/${order_code.id}" frameborder="1" width="100%" style="min-height: 580px;"></iframe>
	                        </div>
						</div>
					</div>
                        </div>
						<div class="ui-form ui-form-horizontal ui-form-grouping node-controller-accordion tab-pane fade in active" id="jibenxinxi" data-widget-cid="widget-26" style="display: block;">
							<div style="width: 100%;">

								<div class="zTreeDemoBackground left" >

								</div>

							</div>
							<h2 class="step_header">
										<img src="${basePath }/resource/images/icon_c1.png" class="step_icon wl1-2" alt=""  />
										外贸出口信息
							</h2>
							<article class="step  node-accordion ui-view-mode" id="step03" data-widget-cid="widget-67" style="display: block;">
								
								<div class="payee-container  view-mode wl1-3">
									<h2 class="step-header">
															<i class="icon-step icon-step03 finish" ></i>

															<div class="title">仓储信息</div>
														</h2>


									<div class="step-content " >
									
										<c:forEach items="${pageBean.pageList}" var="item">
													
													
														<div class="edit-block J-content " >
															<div
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望完成时间:
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display"><fmt:formatDate value="${item[28]}" pattern="yyyy-MM-dd"/> </span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>期望完成时间:
																</label>
	
																<div class="ui-form-control">
	
																	<div class="contact-preview">
																		<span><fmt:formatDate value="${item[29]}" pattern="yyyy-MM-dd"/> </span>
																	</div>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>备注说明:
																</label>
	
																<div class="ui-form-control">
	
																	<div class="contact-preview">
																		<span>${item[30]}</span>
																	</div>
																</div>
															</div>
														
														<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>委托方:
																</label>
	
																<div class="ui-form-control">
	
																	<div class="contact-preview">
																		<span>${item[31]}</span>
																	</div>
																</div>
															</div>
														
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>合同日期 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display"><fmt:formatDate value="${item[5]}" pattern="yyyy-MM-dd"/> </span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>合同号:
																</label>
	
																<div class="ui-form-control">
	
																	<div class="contact-preview">
																		<span>${item[6]}</span>
																	</div>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-trading-country entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-74">
																<label class="ui-form-label" data-label="HS编码">
																	<i class="ui-form-required"></i> 卖方 :
																</label>
	
																<div class="ui-form-control">
																	<pm:execute id="supcompanyinfo" bean="companyInfoBusinImpl" method="getSupCompanyInfo">
																			<pm:execute-param type="java.lang.String" value="${item[7]}" />
																	</pm:execute>
																	<span>${supcompanyinfo.comName}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-is-special-relation entity-type-radio ui-view-mode"
																data-widget-cid="widget-75">
																<label class="ui-form-label" data-label="重量">
																	<i class="ui-form-required"></i>  卖方地址 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[8]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-is-price-affect-confirm entity-type-radio ui-view-mode"
																data-widget-cid="widget-76" style="display: block;">
																<label class="ui-form-label" data-label="包装情况">
																	<i class="ui-form-required"></i>  卖方联系电话 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[9]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-is-royalty-payment entity-type-radio ui-view-mode"
																data-widget-cid="widget-77">
																<label class="ui-form-label" data-label="合同号">
																	<i class="ui-form-required"></i>  卖方传真:
																</label>
	
																<div class="ui-form-control">
																	<span>${item[10]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-target-country-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-78">
																<label class="ui-form-label" data-label="许可文件号">
																	<i class="ui-form-required"></i>  买方:
																</label>
	
																<div class="ui-form-control">
																	<pm:execute id="companyinfouser" bean="companyInfoBusinImpl" method="getCompanyInfoUser">
																			<pm:execute-param type="java.lang.String" value="${item[11]}" />
																	</pm:execute>
																	<pm:execute id="companyinfo" bean="companyInfoBusinImpl" method="getCompanyInfo">
																			<pm:execute-param type="java.lang.String" value="${companyinfouser.companyInfo.id}" />
																	</pm:execute>
																	<span>${companyinfo.comName}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-goods-supply-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-79">
																<label class="ui-form-label" data-label="货物总价">
																	<i class="ui-form-required"></i>买方地址:
																</label>
	
																<div class="ui-form-control">
																	<span>${item[12]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-goods-save-adr entity-type-inventorys-information ui-view-mode"
																data-widget-cid="widget-80">
																<label class="ui-form-label" data-label="进日期">
																	<i class="ui-form-required"></i> 买方联系电话:
																</label>
																<div class="ui-form-control">
																	<div class="contact-preview">
																		<span>${item[13]}</span>
																	</div>
	
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-contract-type entity-type-select ui-view-mode"
																data-widget-cid="widget-81">
																<label class="ui-form-label" data-label="提单号">
																	<i class="ui-form-required"></i>买方传真:
																</label>
	
																<div class="ui-form-control">
																	<span>${item[14]}</span>
																</div>
															</div> 
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-biz-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-82" style="display: block;">
																<label class="ui-form-label" data-label="贸易方式">
																	<i class="ui-form-required"></i> 装运时间 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display"><fmt:formatDate value="${item[15]}" pattern="yyyy-MM-dd"/> </span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-declaration-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-83" style="display: block;">
																<label class="ui-form-label" data-label="货源地">
																	<i class="ui-form-required"></i>  交货时间 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[16]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-not-order-task-number entity-type-text ui-view-mode"
																data-widget-cid="widget-84" style="display: block;">
																<label class="ui-form-label" data-label="其他要求">
																	 装运口岸: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[17]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-order-remark-no entity-type-text ui-view-mode"
																data-widget-cid="widget-85" style="display: block;">
																<label class="ui-form-label" data-label="被委托方">
																	目的口岸: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[18]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" data-label="报关单编码">
																	包装: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[19]}</span>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	保险: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[20]}</span>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	成交方式 : </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[21]}</span>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	付款条件: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[22]}</span>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	币制: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[23]}</span>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	仲裁条款: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[24]}</span>
																</div>
															</div>
															
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	其他: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[25]}</span>
																</div>
															</div>
															
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	客户公司: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[1]}</span>
																</div>
															</div>
													</div>
													</c:forEach>
									</div>
								</div>
							</article>
							
							
							<h2 class="step_header">
										<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wl3-2" alt=""  />
										外贸出口明细信息
							</h2>
							<article class="step  node-accordion ui-view-mode" id="step03" data-widget-cid="widget-67" style="display: block;">
									<div class="payee-container  view-mode wl3-3">
										<h2 class="step-header">
											<i class="icon-step icon-step03 finish" ></i>
											<div class="title">外贸出口明细信息</div>
											
										</h2>
										<c:forEach items="${imContractDetail}" var="item">
										<div class="edit-block J-content " style="width:1255px;margin-left:35px;" >
											<div
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													 商品名称: </label>
												<div class="ui-form-control">
													<span class="J-display">${item[0]}</span>
												</div>
											</div>
											<div
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													 商品型号: </label>

												<div class="ui-form-control">
													<span class="J-display">${item[1]}</span>
												</div>
											</div>
											<div
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													商品数量: </label>

												<div class="ui-form-control">
													<span class="J-display">${item[2]}</span>
												</div>
											</div>
											<div
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													商品单价: </label>

												<div class="ui-form-control">
													<span class="J-display">${item[3]}</span>
												</div>
											</div>
											<div
												class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
												data-widget-cid="widget-86" style="display: block;">
												<label class="ui-form-label" >
													商品总价: </label>

												<div class="ui-form-control">
													<span class="J-display">${item[4]}</span>
												</div>
											</div>
											</div>
										</c:forEach>
									</div>
							</article>
							
							
							
							
							
							
							
							
							
							
							
							
							<h2 class="step_header">
										<img src="${basePath }/resource/images/icon_f3.png" class="step_icon wl2-2" alt=""  />
										外贸出口信息附件查看
							</h2>
							<article class="step  node-accordion ui-view-mode" id="step03" data-widget-cid="widget-67" style="display: block;">
									<div class="payee-container  view-mode wl2-3">
										<h2 class="step-header">
											<i class="icon-step icon-step03 finish" ></i>

											<div class="title">外贸出口信息附件</div>
										</h2>
											<div class="step-content " >
											<c:forEach items="${pageBean.pageList}" var="item">
												<pm:execute id="importContract" bean="exContractDetailBusinImpl" method="getExportContract">
														<pm:execute-param type="java.lang.String" value="${item[0]}" />
												</pm:execute>
												<pm:fileList metaObject="${importContract}" delete="false" metaColums="columsWmEc" />
				                            </c:forEach>
											</div>
									</div>
							</article>
						</div>
					</div>
					<div class="ui2-tab-pane J-panel-confirmContract"></div>
					<div class="ui2-tab-pane J-panel-invoice"></div>
					<div class="ui2-tab-pane J-panel-serviceSupport"></div>
					<div class="ui2-tab-pane J-panel-customDeclarationDetail"></div>
					<div class="ui2-tab-pane J-panel-orderStateLog"></div>
					<c:if test="${usertype =='sup'}">
						<c:if test="${order_code.orderState =='9'}">
							<a class="btn btn-white btn-sm myBtn" href="${basePath }/sup/supcompany/order/supupdateorderoks/${order_code.id}/${nodeType}/${flowId}/${isCompany}/wmex">
								<span> 接收</span>
							</a>
							<a class="btn btn-white btn-sm myBtn" href="${basePath }/sup/supcompany/order/supupdateorders/${order_code.id}/${nodeType}/${flowId}/${isCompany}/wmex">
								<span> 拒绝</span>
							</a>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>

	<script type="text/javascript">
	var jiedian2=$("#jiedian2").val();
  	var jiedian1=$("#jiedian1").val();
  	var jiedian3=$("#jiedian3").val();
  	if(jiedian2=='1'){
  		$("#jiedians2").removeClass();
  		$("#jiedians2").addClass("ui2-icon ui2-icon-dot is_ok ");
  	}
  	if(jiedian1=='1'){
  		$("#jiedians1").removeClass();
  		$("#jiedians1").addClass("ui2-icon ui2-icon-dot is_ok ");
  	}
  	if(jiedian3=='1'){
  		$("#jiedians3").removeClass();
  		$("#jiedians3").addClass("ui2-icon ui2-icon-dot is_ok ");
  	}
  	var jiedian4=$("#jiedian4").val();
  	var jiedian5=$("#jiedian5").val();
  	var jiedian6=$("#jiedian6").val();
  	if(jiedian4=='1'){
  		$("#jiedians4").removeClass();
  		$("#jiedians4").addClass("ui2-icon ui2-icon-dot is_ok ");
  	}
  	if(jiedian5=='1'){
  		$("#jiedians5").removeClass();
  		$("#jiedians5").addClass("ui2-icon ui2-icon-dot is_ok ");
  	}
  	if(jiedian6=='1'){
  		$("#jiedians6").removeClass();
  		$("#jiedians6").addClass("ui2-icon ui2-icon-dot is_ok ");
  	}
	
	
		
		$('.sidebar-main-menu-item').hover(function() {
			// 移入显示
			$(this).children('ul').css('display', 'block');
		}, function() {
			// 移出隐藏
			$(this).children('ul').css('display', 'none');
		});

		$('.hot-question-robot-root').click(function(event) {
			//如果存在（不存在）就删除（添加）一个类。
			$('.hot-question-robot-root').toggleClass("mini-size");
		});
	</script>
	<SCRIPT type="text/javascript">
				
		   var zTreeObj;
		   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
		   var setting = {};
		   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
		   var zNodes = [
		   {name:"test1", open:true, children:[
		      {name:"test1_1"}, {name:"test1_2"}]},
		   {name:"test2", open:true, children:[
		      {name:"test2_1"}, {name:"test2_2"}]}
		   ];
		   $(document).ready(function(){
		      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		   });
		   $(".wl2-3").hide();
		   $(".wl3-3").hide();
		   var wl1=true;
		   var wl3=true;
		   var wl2=true;
		   $(".wl1-2").click(function() {
				   if(wl1==true){
					    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f3.png");
						$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						$(".wl1-3").hide();
						$(".wl3-3").hide();
						$(".wl2-3").hide();
					   wl1=false
				   }else{
					    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f3.png");
						$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						$(".wl1-3").show();
						$(".wl3-3").hide();
						$(".wl2-3").hide();
					   wl1=true
				   }
			
			});
		   $(".wl2-2").click(function() {
				   if(wl2==true){
					    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_c3.png");
						$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						$(".wl1-3").hide();
						$(".wl3-3").hide();
						$(".wl2-3").show();
					   wl2=false
				   }else{
					    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f3.png");
						$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						$(".wl1-3").hide();
						$(".wl3-3").hide();
						$(".wl2-3").hide();
					   wl2=true
				   }
			});
		   $(".wl3-2").click(function() {
			   if(wl3==true){
				    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
					$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f3.png");
					$(".wl3-2").attr("src", "${basePath }/resource/images/icon_c2.png");
					$(".wl1-3").hide();
					$(".wl2-3").hide();
					$(".wl3-3").show();
				   wl3=false
			   }else{
				    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
					$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f3.png");
					$(".wl3-2").attr("src", "${basePath }/resource/images/icon_f2.png");
					$(".wl1-3").hide();
					$(".wl2-3").hide();
					$(".wl3-3").hide();
				   wl3=true
			   }
		});
		   
			function reinitIframe(){
				var iframe = document.getElementById("deptUserList");
				try{
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					iframe.height = height;
					console.log(height);
				}catch (ex){}
			}
			window.setInterval("reinitIframe()", 200);

	</SCRIPT>
</body>

<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>