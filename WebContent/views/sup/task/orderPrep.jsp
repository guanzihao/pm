<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/include/includeHeader.jsp" %>
<link href="${basePath}/resource/css/font_1435131046_7356622.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${basePath}/resource/css/lijixiadan_1.css" />
<style>
			.acolor{
				border: 3px solid #66CD00 !important;
			}
			p {
				margin: 0;
			}
			
			button {
				border: none;
			}
			
			span {
				display: inline;
			}
			
			.container {
				margin: 0;
				padding: 0;
			}
			
			.row {
				height: 100%;
				margin: 0;
				padding: 0;
			}
			
			.col-xs-1,
			.col-sm-1,
			.col-md-1,
			.col-lg-1,
			.col-xs-2,
			.col-sm-2,
			.col-md-2,
			.col-lg-2,
			.col-xs-3,
			.col-sm-3,
			.col-md-3,
			.col-lg-3,
			.col-xs-4,
			.col-sm-4,
			.col-md-4,
			.col-lg-4,
			.col-xs-5,
			.col-sm-5,
			.col-md-5,
			.col-lg-5,
			.col-xs-6,
			.col-sm-6,
			.col-md-6,
			.col-lg-6,
			.col-xs-7,
			.col-sm-7,
			.col-md-7,
			.col-lg-7,
			.col-xs-8,
			.col-sm-8,
			.col-md-8,
			.col-lg-8,
			.col-xs-9,
			.col-sm-9,
			.col-md-9,
			.col-lg-9,
			.col-xs-10,
			.col-sm-10,
			.col-md-10,
			.col-lg-10,
			.col-xs-11,
			.col-sm-11,
			.col-md-11,
			.col-lg-11,
			.col-xs-12,
			.col-sm-12,
			.col-md-12,
			.col-lg-12 {
				padding: 0;
			}
			
			html,
			body {
				width: 100%;
				background: #f5f7fa;
				/*height: 100%;*/
			}
			
			#box {
				width: 100%;
				/*height: 100%;
			    overflow: scroll;*/
			}
			
			#main {
				min-width: 600px;
				/*min-height: 100%;*/
				background: #f5f7fa;
				float: none;
				margin: 0 auto;
			}
			
			// 清除浮动
			#main:after {
				display: block;
				clear: both;
				content: "";
				visibility: hidden;
				height: 0
			}
			
			#main {
				zoom: 1
			}
			
			a {
				color: #06c;
				text-decoration: none;
			}
			
			a:hover {
				color: #f90;
				text-decoration: underline;
			}
			
			.fr {
				float: right;
			}
			
			.title {
				font-size: 16px;
				color: #333;
				height: 60px;
				line-height: 60px;
			}
			/*主体内容*/
			
			#main_content {
				background: #fff;
				padding: 32px 18px;
				font-size: 14px;
				color: #333;
			}
			
			.small_title {
				height: 30px;
				line-height: 30px;
			}
			
			.xuan_one {
				padding-right: 20px;
				margin-bottom: 10px;
			}
			
			.xuan_one_son {
				height: 72px;
				border: 2px solid #d1e1f2;
				border-radius: 4px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
			
			.xuan_one_name {
				padding: 16px 14px 0 14px;
				font-size: 18px;
				color: #666666;
			}
			
			.xuan_one_explain {
				padding: 0 14px 0 14px;
				font-size: 12px;
				color: #999999;
			}
			/*橙色按钮*/
			
			.ui_form_button {
				background: #FF7519;
				color: #FFF;
				padding: 4px 10px;
				border-radius: 4px;
			}
			.xuan_one_son input{
				display: none;
			}
			.col-xs-12 .button {
				margin-right: 30px;
			}
			.dlwt{
				width: 20px;
				height: 20px;
			}
		</style>
<body>
		<div id="box" class="container">
			<div class="row">
			   <form class="form-horizontal formValidate submitForm" action="${basePath }/sup/task/constractDetail" method="post">
				<div id="main" class="col-xs-12">
					<div class="title">请选择您需要的云贸通服务</div>
					<div id="main_content" class="col-xs-12">
						<div class="one_step col-xs-12">
							<p class="small_title">
								报关业务
							</p>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">进口报关</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${bgImcc}" />
								</div>
							</div>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">出口报关</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${bgExcc}" />
								</div>
							</div>
						</div>
						<div class="one_step col-xs-12">
							<p class="small_title">
								物流业务
							</p>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">进口物流</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${wlImcc}" />
								</div>
							</div>

							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">出口物流</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${wlExcc}" />
								</div>
							</div>

							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">物流运输</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${wlTransport}" />
								</div>
							</div>

						</div>
						<div class="one_step col-xs-12">
							<p class="small_title">
								仓储业务
							</p>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">仓储进库</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${ccImcc}" />
								</div>
							</div>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">仓储出库</p>
									<span class="xuan_one_explain">外贸有保障,买家更放心</span>
									<input type="checkbox"  name="flowTypeId" value="${ccExcc}" />
								</div>
							</div>
						</div>
						<div class="one_step col-xs-12">
							<p class="small_title">
								外贸业务
							</p>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">外贸进口</p>
									<span class="xuan_one_explain">拼箱、整柜、船东专区</span>
									<input type="checkbox"  name="flowTypeId" value="${wmImcc}" />
								</div>
							</div>
							<div class="xuan_one col-xs-3">
								<div class="xuan_one_son">
									<p class="xuan_one_name">外贸出口</p>
									<span class="xuan_one_explain">中港、拖车、中俄欧铁路</span>
									<input type="checkbox"   name="flowTypeId" value="${wmExcc}" />
								</div>
							</div>
						</div>
						<div class="col-xs-12">
							<button class="btn btn-primary button" type="button">立即下单</button><input id="dlwtId" class="dlwt" type="checkbox" checked="checked" name="checkbox" value="true">代理委托 <a onclick="aaaa()" class="clarClass">查看代理委托协议</a>
						</div>
					</div>
				
				</div>
			  </form>
			</div>
		</div>
		<div class="container">
		<div class="modal fade bs-example-modal-lg"   id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="height: 30px;padding: 6px;">
						<h4 class="modal-title" id="myModalLabel">
                   </h4>
					</div>
					<div class="modal-body" style="width: 600px;text-align: center;font-size: 16px;">

						<p>用户协议</p>
						<p>《平台用户服务协议》（以下简称“协议”）是由平台的用户（以下简称“用户或您”）与平台的运营方，就网站交易、平台服务等相关事宜（包括但不限于以任何形式接通、进入、浏览或者使用各项服务、功能等）共同缔结。本协议具有合同效力，您应当阅读并遵守本协议。请您务必审慎阅读、充分理解各条款内容。</p>
					</div>
				</div>
			</div>
		</div>
</div>
	</body>

<%@ include file="/include/includeJs.jsp" %>
<script type="text/javascript">
  	$(".button").on("click",function(){
  		var arr = new Array();
		$("input[name='flowTypeId']:checked").each(function(){
			arr.push($(this).val());
		});
		var dlwt=$("#dlwtId").val();
		if (arr.length>0) {
			if (dlwt=='true') {
				$(".submitForm").submit();
			}else{
				parent.layer.msg('必须勾选代理委托！！',{icon: 2});
			}
		}else{
			parent.layer.msg('请选择要下单的类型！！',{icon: 2});
		}
  	});
  	$('.xuan_one_son').on('mouseenter', function() {
		$(this).css('border-color', '#6791bb');
	})

	$('.xuan_one_son').on('mouseleave', function() {
		$(this).css('border-color', '#d1e1f2');
	})
	$(".xuan_one").on('click',function(){
		if ($(this).find("input[type='checkbox']").is(":checked")) {
			$(this).find('input').prop('checked', false);
			$(this).find('.xuan_one_son').removeClass('acolor');
		} else{
			$(this).find('input').prop('checked', true);
			$(this).find('.xuan_one_son').toggleClass('acolor');
		}
	}); 
	$(".dlwt").on('click',function(){
		if ($('#dlwtId').val()=='true') {
			$('#dlwtId').val('false');
		} else{
			$('#dlwtId').val('true');
		}
	}); 
  	$(".clarClass").on('click',function(){
  		layer.open({
  			title: '用户协议',
  		    type: 2,
  		    offset: '20px',
  		    area: ['900px', '600px'],
  		    maxmin: true,
  		    content: '${basePath }/sup/task/userAgreement',
  		    cancel: function(index){
  		    	layer.close(index);
  		    }
  		});
  	});
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>