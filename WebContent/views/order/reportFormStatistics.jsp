<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
  <link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>平台订单统计表</h5>
	                </div>
	                <div class="ibox-content ">
						<div class="dataTables_wrapper form-inline">
							<form id="pageForm" action="${basePath }/order/reportFormStatistics" class="form-horizontal formValidate" method="post">
								<div class="row">
									<div class="col-sm-12">
										<label class="tables_search_label"> 客户：
											<input type="text" class="input-sm form-control my_input" name="name" value="${name }">
										</label>
										<label class="tables_search_label"> 供应商：
											<input type="text" class="input-sm form-control my_input" name="supName" value="${supName }">
										</label>
										<label class="tables_search_label"> 下单时间：
											<input type="text" class="form-control input-sm date-picker my_input" name="startDate" value="${startDate }">-<input type="text" class="form-control input-sm date-picker my_input" name="endDate" value="${endDate }">
										</label>
		                                <button type="submit" class="btn btn-sm btn-primary button1" >查询</button>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th >业务类型</th>
												<th >订单数</th>
												<th>延迟数</th>
												<th >延迟率</th>
												<th >按时数</th>
												<th>按时率</th>
												<th>提前数</th>
												<th>提前率</th>
												
											</tr>
										</thead>
										<tbody>
											<tr class="parent">
												<td>${bgIc }</td>
												<td>${bgIcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('报关出口延迟订单查看','${basePath }/order/listReportFormStatistics/${bgIcId  }/1/<c:choose> <c:when test="${name eq '' or name==null }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null }">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${bgIcCountYC2 }</span>
													</a>
												</td>
												<td>${bgIcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('报关出口按时订单查看','${basePath }/order/listReportFormStatistics/${bgIcId  }/0/<c:choose> <c:when test="${name eq ''or name==null }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${bgIcCountAS2 }</span>
													</a>
												</td>
												<td>${bgIcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('报关出口提前订单查看','${basePath }/order/listReportFormStatistics/${bgIcId  }/-1/<c:choose> <c:when test="${name eq '' or name==null }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${bgIcCountTQ2 }</span>
													</a>
												</td>
												<td>${bgIcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${bgEc }</td>
												<td>${bgEcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('报关进口延迟订单查看','${basePath }/order/listReportFormStatistics/${bgEcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${bgEcCountYC2 }</span>
													</a>
												</td>
												<td>${bgEcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('报关进口按时订单查看','${basePath }/order/listReportFormStatistics/${bgEcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${bgEcCountAS2 }</span>
													</a>
												</td>
												<td>${bgEcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('报关进口提前订单查看','${basePath }/order/listReportFormStatistics/${bgEcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${bgEcCountTQ2 }</span>
													</a>
												</td>
												<td>${bgEcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${wmIc }</td>
												<td>${wmIcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('外贸进口延迟订单查看','${basePath }/order/listReportFormStatistics/${wmIcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wmIcCountYC2 }</span>
													</a>
												</td>
												<td>${wmIcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('外贸进口按时订单查看','${basePath }/order/listReportFormStatistics/${wmIcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wmIcCountAS2 }</span>
													</a>
												</td>
												<td>${wmIcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('外贸进口提前订单查看','${basePath }/order/listReportFormStatistics/${wmIcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wmIcCountTQ2 }</span>
													</a>
												</td>
												<td>${wmIcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${wmEc }</td>
												<td>${wmEcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('外贸出口延迟订单查看','${basePath }/order/listReportFormStatistics/${wmEcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wmEcCountYC2 }</span>
													</a>
												</td>
												<td>${wmEcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('外贸出口按时订单查看','${basePath }/order/listReportFormStatistics/${wmEcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wmEcCountAS2 }</span>
													</a>
												</td>
												<td>${wmEcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('外贸出口提前订单查看','${basePath }/order/listReportFormStatistics/${wmEcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wmEcCountTQ2 }</span>
													</a>
												</td>
												<td>${wmEcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${wlIc }</td>
												<td>${wlIcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流进库延迟订单查看','${basePath }/order/listReportFormStatistics/${wlIcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlIcCountYC2 }</span>
													</a>
												</td>
												<td>${wlIcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流进库按时订单查看','${basePath }/order/listReportFormStatistics/${wlIcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlIcCountAS2 }</span>
													</a>
												</td>
												<td>${wlIcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流进库提前订单查看','${basePath }/order/listReportFormStatistics/${wlIcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlIcCountTQ2 }</span>
													</a>
												</td>
												<td>${wlIcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${wlEc }</td>
												<td>${wlEcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流出库延迟订单查看','${basePath }/order/listReportFormStatistics/${wlEcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlEcCountYC2 }</span>
													</a>
												</td>
												<td>${wlEcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流出库按时订单查看','${basePath }/order/listReportFormStatistics/${wlEcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlEcCountAS2 }</span>
													</a>
												</td>
												<td>${wlEcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流出库提前订单查看','${basePath }/order/listReportFormStatistics/${wlEcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlEcCountTQ2 }</span>
													</a>
												</td>
												<td>${wlEcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${wlTransport }</td>
												<td>${wlTransportCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流运输延迟订单查看','${basePath }/order/listReportFormStatistics/${wlTransportId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlTransportCountYC2 }</span>
													</a>
												</td>
												<td>${wlTransportCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流运输按时订单查看','${basePath }/order/listReportFormStatistics/${wlTransportId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlTransportCountAS2 }</span>
													</a>
												</td>
												<td>${wlTransportCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('物流运输提前订单查看','${basePath }/order/listReportFormStatistics/${wlTransportId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${wlTransportCountTQ2 }</span>
													</a>
												</td>
												<td>${wlTransportCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${ccIc }</td>
												<td>${ccIcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('仓储进库延迟订单查看','${basePath }/order/listReportFormStatistics/${ ccIcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${ccIcCountYC2 }</span>
													</a>
												</td>
												<td>${ccIcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('仓储进库按时订单查看','${basePath }/order/listReportFormStatistics/${ ccIcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${ccIcCountAS2 }</span>
													</a>
												</td>
												<td>${ccIcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('仓储进库提前订单查看','${basePath }/order/listReportFormStatistics/${ ccIcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${ccIcCountTQ2 }</span>
													</a>
												</td>
												<td>${ccIcCountTQ1 }</td>
											</tr>
											<tr class="parent">
												<td>${ccEc }</td>
												<td>${ccEcCount }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('仓储出库延迟订单查看','${basePath }/order/listReportFormStatistics/${ ccEcId }/1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${ccEcCountYC2 }</span>
													</a>
												</td>
												<td>${ccEcCountYC1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('仓储出库按时订单查看','${basePath }/order/listReportFormStatistics/${ ccEcId }/0/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${ccEcCountAS2 }</span>
													</a>
												</td>
												<td>${ccEcCountAS1 }</td>
												<td>
													<a class="" href="javascript:;" onclick="openLayerSize('仓储出库提前订单查看','${basePath }/order/listReportFormStatistics/${ ccEcId }/-1/<c:choose> <c:when test="${name eq '' or name==null  }">0</c:when><c:otherwise>${name}</c:otherwise></c:choose>/<c:choose> <c:when test="${supName eq ''  or supName==null}">0</c:when><c:otherwise>${supName}</c:otherwise></c:choose>/<c:choose> <c:when test="${startDate eq '' or startDate==null  }">0</c:when><c:otherwise>${startDate}</c:otherwise></c:choose>/<c:choose> <c:when test="${endDate eq '' or endDate==null }">0</c:when><c:otherwise>${endDate}</c:otherwise></c:choose>',   true,'90%', '80%')">
														<span>${ccEcCountTQ2 }</span>
													</a>
												</td>
												<td>${ccEcCountTQ1 }</td>
											</tr>
										</tbody>
									</table>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/include/includeJs.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>
<script type="text/javascript">
$(function(){
	var close=$("#closeId").val();
	if (close=='close') {
		parent.layer.msg('订单分配成功');
		parent.window.location.reload();
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	}
});
</script>
