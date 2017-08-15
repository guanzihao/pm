<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>

  <link href="${basePath }/resource/css/jiedian.css" rel="stylesheet" />


<meta charset="UTF-8">
		<title>物流进口</title>
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
			/点击前的样式/
			
			#daohang .nav-tabs>li>a {
				padding-top: 8px;
				line-height: 20px;
				border: 1px solid transparent;
				border-top-color: transparent;
				border-right-color: transparent;
				border-bottom-color: transparent;
				border-left-color: transparent;
				-webkit-border-radius: 4px 4px 0 0;
				-moz-border-radius: 4px 4px 0 0;
				border-radius: 4px 4px 0 0;
				color: #000000;
				background-color: #F7F8FA;
				border-top: 1px solid #DCDEE3;
				border-left: 1px solid #DCDEE3;
				border-right: 1px solid #DCDEE3;
				font-size: 14px;
				border-bottom: 0px solid #ddd;
			}
			/点击后的样式/
			
			#daohang .nav-tabs>.active>a,
			.nav-tabs>.active>a:hover {
				color: #EE7600;
				cursor: default;
				background-color: #ffffff;
				border: 1px solid #ddd;
				border-bottom-color: rgb(221, 221, 221);
				border-bottom-color: transparent;
				border-top: 3px solid #EE7600;
				font-size: 14px;
			}
			/* .mo-process-mark{
				border-bottom: 1px solid #ff7519 !important; 
				position：absolute;
			} */
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
										<li class="mo-process-item first mo-process-done-item " style="width:20%;">
	
											<span class="label" >接单</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item  " style="width:20%;">
	
											<span class="label">订舱</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item "style="width:20%;">
	
											<span class="label" >国内换单</span>
										</li>
	<li
											class="mo-process-item mo-process-done-item  " style="width:20%;">
	
											<span class="label">报关</span>
										</li>
	
										<li
											class="mo-process-item mo-process-done-item " style="width:20%;">
	
											<span class="label" >送货</span>
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
										 "   style="width:20%;">

											<div class="mo-process-mark">
												<i id="jiedians1" class="ui2-icon ui2-icon-dot is_no"></i>
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
										 "   style="width:20%;">

											<div class="mo-process-mark">
												<i id="jiedians2" class="ui2-icon ui2-icon-dot"></i>
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
											
										<li class="mo-process-item mo-process-done-item 
										 "   style="width:20%;" >

											<div class="mo-process-mark">
												<i id="jiedians3" class="ui2-icon ui2-icon-dot"></i>
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
										 "    style="width:20%;">

											<div class="mo-process-mark">
												<i id="jiedians4" class="ui2-icon ui2-icon-dot"></i>
											</div>
											<div class="mo-process-content" style="">
												<div class="" data-desc="" style="text-align:center">
													<a href="#">
													<input type="text" style="display:none;" ID="jiedian4" value="${order_code.node4State}"/>
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

										<li class="mo-process-item last mo-process-done-item 
										 "   style="width:20%;">

											<div class="mo-process-mark" >
												<i id="jiedians5" class="ui2-icon ui2-icon-dot"style="margin-left:70px;"></i>
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
								<c:if test="${item[33] eq '0' }">
									<c:if test="${order_code.orderState eq'10'}">
										<li>
											<a href="#weituohan" data-toggle="tab">
												委托函
											</a>
										</li>
									</c:if>
									<c:if test="${order_code.orderState eq'7'}">
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
								<div style="text-align:center;width:30%;border:1px solid black;border-left:0px;margin-left:299px;margin-top:-22px;">物流进口</div>
								<div style="margin-toP:100px;">二、委托信息</div>
								<div style="margin-top:20px;">
										<c:forEach items="${pageBean.pageList}" var="item">
														<div class="edit-block J-content " >
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-trading-country entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-74">
																<label class="ui-form-label" data-label="HS编码">
																	<i class="ui-form-required"></i> 起运港 :<span>${item[7]}</span>
																</label>
															</div>
														
														
														
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望开始时间 :<span class="J-display"><fmt:formatDate value="${item[34]}" pattern="yyyy-MM-dd"/> </span>
																</label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望完成时间 :<span class="J-display"><fmt:formatDate value="${item[35]}" pattern="yyyy-MM-dd"/> </span>
																</label>
	
																	
															</div>
														
														<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>备注说明 :<span class="J-display">${item[37]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>委托方 :<span class="J-display">${item[36]}</span>
																</label>
	
																	
															</div>
														
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>托运人 :<span class="J-display">${item[5]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>托运人地址:<span>${item[6]}</span>
																</label>
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-is-special-relation entity-type-radio ui-view-mode"
																data-widget-cid="widget-75">
																<label class="ui-form-label" data-label="重量">
																	<i class="ui-form-required"></i>  卸货港 :<span>${item[8]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-is-price-affect-confirm entity-type-radio ui-view-mode"
																data-widget-cid="widget-76" style="display: block;">
																<label class="ui-form-label" data-label="包装情况">
																	<i class="ui-form-required"></i>  最终目的 :<span>${item[9]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-is-royalty-payment entity-type-radio ui-view-mode"
																data-widget-cid="widget-77">
																<label class="ui-form-label" data-label="合同号">
																	<i class="ui-form-required"></i> 编号:<span>${item[10]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-target-country-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-78">
																<label class="ui-form-label" data-label="许可文件号">
																	<i class="ui-form-required"></i> 收货人:<span>${item[11]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-goods-supply-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-79">
																<label class="ui-form-label" data-label="货物总价">
																	<i class="ui-form-required"></i>收货人地址 :<span>${item[12]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-goods-save-adr entity-type-inventorys-information ui-view-mode"
																data-widget-cid="widget-80">
																<label class="ui-form-label" data-label="进出口日期">
																	<i class="ui-form-required"></i> 货到时间:<span><fmt:formatDate value="${item[13]}" pattern="yyyy-MM-dd"/> </span>
																</label>
																		
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-contract-type entity-type-select ui-view-mode"
																data-widget-cid="widget-81">
																<label class="ui-form-label" data-label="提单号">
																	<i class="ui-form-required"></i>支付方式 :<span>${item[14]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-biz-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-82" style="display: block;">
																<label class="ui-form-label" data-label="贸易方式">
																	<i class="ui-form-required"></i> 箱型/箱量 :<span class="J-display">${item[15]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-declaration-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-83" style="display: block;">
																<label class="ui-form-label" data-label="货源地">
																	<i class="ui-form-required"></i>  提单类型 :<span class="J-display">${item[16]}</span>
																</label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-not-order-task-number entity-type-text ui-view-mode"
																data-widget-cid="widget-84" style="display: block;">
																<label class="ui-form-label" data-label="其他要求">
																	通知人: <span class="J-display">${item[17]}</span></label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-order-remark-no entity-type-text ui-view-mode"
																data-widget-cid="widget-85" style="display: block;">
																<label class="ui-form-label" data-label="被委托方">
																	件数 : <span class="J-display">${item[18]}</span></label>
	
																	
															</div>
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" data-label="报关单编码">
																	毛重:<span class="J-display">${item[19]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	体积: <span class="J-display">${item[20]}</span></label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	唛头:<span class="J-display">${item[21]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	品名: <span class="J-display">${item[22]}</span></label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	运费确认: <span class="J-display">${item[23]}</span></label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	运费条款:<span class="J-display">${item[24]}</span> </label>
																	
															</div>
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	托运联系人:<span class="J-display">${item[26]}</span> </label>
	
																	
															</div>
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	电话:<span class="J-display">${item[27]}</span> </label>
	
																	
															</div>
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	传真:<span class="J-display">${item[28]}</span> </label>
	
																	
															</div>
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	邮箱: <span class="J-display">${item[28]}</span></label>
	
																	
															</div>
															
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	预配航班: <span class="J-display">${item[29]}</span></label>
	
																	
															</div>
															
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	自拉自报: <span class="J-display">${item[30]}</span></label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	特殊要求:<span class="J-display">${item[31]}</span> </label>
	
																	
															</div>
															
															<div style="width:50%;float:left;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	运输方式: <span class="J-display">${item[32]}</span></label>
	
																	
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
										确认时间：<fmt:formatDate value="${item[38]}" pattern="yyyy-MM-dd"/>
									</c:forEach>
								</div>
						</div>
						<div id="danjuxiangqing" class="tab-pane fade"> 
						       <div class="panel-body">
		                <div class="ibox-content" style="height: 100%;">
							<form id="pageForm1" action="${basePath }" method="post">
								<div class="col-sm-3">
									<c:import url="/views/order/wlexcc/WlExccOrderTree.jsp">
										<c:param name="name" value="unitInfoId"/>
										<c:param name="treeData" value="${treeData }"/>
								    </c:import>
			                 	</div>
			                </form>
	                        <div class="col-sm-9">
	                       		 <iframe id="deptUserList" src="${basePath }/Wlimcc/listWlimcc/${order_code.id}" frameborder="1" width="100%" ></iframe>
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
										物流进口信息
							</h2>
							<article class="step  node-accordion ui-view-mode" id="step03" data-widget-cid="widget-67" style="display: block;">
								
								<div class="payee-container  view-mode wl1-3">
									<h2 class="step-header">
															<i class="icon-step icon-step03 finish" ></i>

															<div class="title">物流进口信息</div>
														</h2>


									<div class="step-content " >
									
										<c:forEach items="${pageBean.pageList}" var="item">
														<div class="edit-block J-content " >
														
															<div 
																class="ui-form-item  node-entity entity-name-trading-country entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-74">
																<label class="ui-form-label" data-label="HS编码">
																	<i class="ui-form-required"></i> 起运港 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[7]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-is-price-affect-confirm entity-type-radio ui-view-mode"
																data-widget-cid="widget-76" style="display: block;">
																<label class="ui-form-label" data-label="包装情况">
																	<i class="ui-form-required"></i> 最终目的 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[9]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-contract-type entity-type-select ui-view-mode"
																data-widget-cid="widget-81">
																<label class="ui-form-label" data-label="提单号">
																	<i class="ui-form-required"></i>支付方式 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[14]}</span>
																</div>
															</div> 
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-biz-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-82" style="display: block;">
																<label class="ui-form-label" data-label="贸易方式">
																	<i class="ui-form-required"></i> 箱型/箱量 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[15]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-order-remark-no entity-type-text ui-view-mode"
																data-widget-cid="widget-85" style="display: block;">
																<label class="ui-form-label" data-label="被委托方">
																	件数 : </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[18]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" data-label="报关单编码">
																	毛重: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[19]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	体积: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[20]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	品名: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[22]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	运费确认: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[23]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	运费条款: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[24]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	托运联系人: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[26]}</span>
																</div>
															</div>
																<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	电话: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[27]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	邮箱: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[28]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	预配航班: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[29]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	运输方式: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[32]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	特殊要求: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[31]}</span>
																</div>
															</div>
															
															
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望开始时间 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display"><fmt:formatDate value="${item[34]}" pattern="yyyy-MM-dd"/> </span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>期望完成时间 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display"><fmt:formatDate value="${item[35]}" pattern="yyyy-MM-dd"/> </span>
																</div>
															</div>
														<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>备注说明 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[37]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>委托方:
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[36]}</span>
																</div>
															</div>
														
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-expect-declaration-date entity-type-calendar ui-view-mode"
																data-widget-cid="widget-68">
																<label class="ui-form-label" data-label="预计出货日期">
																	<i class="ui-form-required"></i>托运人 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[5]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-foreign-merchant entity-type-buyers-information ui-view-mode"
																data-widget-cid="widget-73">
																<label class="ui-form-label" >
																	<i class="ui-form-required"></i>托运人地址:
																</label>
	
																<div class="ui-form-control">
	
																	<div class="contact-preview">
																		<span>${item[6]}</span>
																	</div>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-is-special-relation entity-type-radio ui-view-mode"
																data-widget-cid="widget-75">
																<label class="ui-form-label" data-label="重量">
																	<i class="ui-form-required"></i>  卸货港 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[8]}</span>
																</div>
															</div>
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-is-royalty-payment entity-type-radio ui-view-mode"
																data-widget-cid="widget-77">
																<label class="ui-form-label" data-label="合同号">
																	<i class="ui-form-required"></i> 编号:
																</label>
	
																<div class="ui-form-control">
																	<span>${item[10]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-target-country-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-78">
																<label class="ui-form-label" data-label="许可文件号">
																	<i class="ui-form-required"></i> 收货人:
																</label>
	
																<div class="ui-form-control">
																	<span>${item[11]}</span>
																</div>
															</div>  
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-goods-supply-id entity-type-super-selector ui-view-mode"
																data-widget-cid="widget-79">
																<label class="ui-form-label" data-label="货物总价">
																	<i class="ui-form-required"></i>收货人地址 :
																</label>
	
																<div class="ui-form-control">
																	<span>${item[12]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-goods-save-adr entity-type-inventorys-information ui-view-mode"
																data-widget-cid="widget-80">
																<label class="ui-form-label" data-label="进出口日期">
																	<i class="ui-form-required"></i> 货到时间:
																</label>
																<div class="ui-form-control">
																	<div class="contact-preview">
																		<span><fmt:formatDate value="${item[13]}" pattern="yyyy-MM-dd"/> </span>
																	</div>
	
																</div>
															</div>
															
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-declaration-contract-no entity-type-text ui-view-mode"
																data-widget-cid="widget-83" style="display: block;">
																<label class="ui-form-label" data-label="货源地">
																	<i class="ui-form-required"></i>  提单类型 :
																</label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[16]}</span>
																</div>
															</div>
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-not-order-task-number entity-type-text ui-view-mode"
																data-widget-cid="widget-84" style="display: block;">
																<label class="ui-form-label" data-label="其他要求">
																	通知人: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[17]}</span>
																</div>
															</div>
															
															
															
															
															 
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	唛头: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[21]}</span>
																</div>
															</div>
															
															
															
															
															
															
															
															
															
															
															
														
															
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	传真: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[28]}</span>
																</div>
															</div>
															
															
															
															
															
															
															
															<div style="margin-top:4px;"
																class="ui-form-item  node-entity entity-name-out-goods-remark entity-type-textarea ui-view-mode"
																data-widget-cid="widget-86" style="display: block;">
																<label class="ui-form-label" >
																	自拉自报: </label>
	
																<div class="ui-form-control">
																	<span class="J-display">${item[30]}</span>
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
										<img src="${basePath }/resource/images/icon_f2.png" class="step_icon wl2-2" alt=""  />
										物流进口信息附件查看
							</h2>
							<article class="step  node-accordion ui-view-mode" id="step03" data-widget-cid="widget-67" style="display: block;">
									<div class="payee-container  view-mode wl2-3">
										<h2 class="step-header">
											<i class="icon-step icon-step03 finish" ></i>

											<div class="title">物流进口信息附件</div>
										</h2>
											<div class="step-content " >
											<c:forEach items="${pageBean.pageList}" var="item">
												 <pm:execute id="importContract" bean="shippingExCommissionBusinImpl" method="getShippingExCommission">
														<pm:execute-param type="java.lang.String" value="${item[0]}" />
												</pm:execute>
												<pm:fileList metaObject="${importContract}" delete="false" metaColums="columsWlIc" />
				                            
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
							<a class="btn btn-white btn-sm myBtn" href="${basePath }/sup/supcompany/order/supupdateorderok/${order_code.id}">
								<span> 确认</span>
							</a>
							<a class="btn btn-white btn-sm myBtn" href="${basePath }/sup/supcompany/order/supupdateorder/${order_code.id}">
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
  		$("#jiedians2").addClass("mo-process-item mo-process-current-item ");
  	}
  	if(jiedian1=='1'){
  		$("#jiedians1").removeClass();
  		$("#jiedians1").addClass("mo-process-item mo-process-current-item ");
  	}
  	if(jiedian3=='1'){
  		$("#jiedians3").removeClass();
  		$("#jiedians3").addClass("mo-process-item mo-process-current-item ");
  	}
  	
  	var jiedian5=$("#jiedian5").val();
  	var jiedian4=$("#jiedian4").val();
  	if(jiedian4=='1'){
  		$("#jiedians4").removeClass();
  		$("#jiedians4").addClass("mo-process-item mo-process-current-item ");
  	}
  	if(jiedian5=='1'){
  		$("#jiedians5").removeClass();
  		$("#jiedians5").addClass("mo-process-item last mo-process-current-item ");
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
		   var wl1=true;
		   var wl2=true;
		   $(".wl1-2").click(function() {
					if(wl1==true){
						$(".wl1-3").hide();
						$(".wl2-3").hide();
						$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						wl1=false;
					}else if(wl1==false){
						$(".wl1-2").attr("src", "${basePath }/resource/images/icon_c1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						$(".wl1-3").show();
						$(".wl2-3").hide();
						wl1=true;
					}
			});
		   $(".wl2-2").click(function() {
				   if(wl2==true){
					    $(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_c2.png");
						$(".wl1-3").hide();
						$(".wl2-3").show();
						wl2=false;
					}else if(wl2==false){
						$(".wl1-2").attr("src", "${basePath }/resource/images/icon_f1.png");
						$(".wl2-2").attr("src", "${basePath }/resource/images/icon_f2.png");
						$(".wl1-3").hide();
						$(".wl2-3").hide();
						wl2=true;
					}
			});
		   
		   function reinitIframe(){
				var iframe = document.getElementById("deptUserList");
				try{
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					iframe.height = height;
				}catch (ex){}
			}
			window.setInterval("reinitIframe()", 200);
	</SCRIPT>
</body>

<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>