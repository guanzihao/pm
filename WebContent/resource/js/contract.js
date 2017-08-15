
var aaa=false;
var bbb=false;
var ccc=false;
var ddd=false;
var basePath="/pm";
var isDelegation=0;
//保存成功后跳转的页面
var skipId='';

//模态框的链接
$(".tjqbdd").click(function(){
	var taskId=$("#taskId").val();
		$.ajax({
			type:"post",
			url:basePath+'/sup/order/OrderFrom/submitOrderFrom',
			dataType : "json",
			data:{
				taskId:taskId
			},
			async:true,    //或false,是否异步  
			success: function (obj) {
				if(obj.statusCode == "200"){
					parent.layer.msg('订单提交成功');
					var url=getTaskTypeIdToSkipPage(skipId);
					window.location.href=basePath+url;
				}
			},error: function(){
				parent.layer.msg('订单提交失败');
			}	
	});
});
$(".bccg").click(function(){
	parent.layer.msg('订单草稿提交成功');
	var url=getTaskTypeIdToSkipPage(skipId);
	window.location.href=basePath+url;
});
$(".jxxd").click(function(){
	window.location.href=basePath+'/sup/task/orderPrep';
});
$(".wddd").click(function(){
	var url=getTaskTypeIdToSkipPage(skipId);
	window.location.href=basePath+url;
});
	$(function(){
		var typeId=$("#iniPage").val();
		typeIdByPageShowOrHide(typeId);
		addClassctiveActive(typeId);
		
		
	});
//########################1.外贸进口################################################################		
	var wmIcQqwt=true;
	var wmIcFalg1=false;
	var wmIcFalg2=false;	
	var wmIcFalg3=false;	
	var wmIcFalg4=false;	
	var wmIcFalg5=false;	
	var wmIcFalg6=false;	
	$(".wmIc1-2").click(function() {
		 $("#insertWmIcForm").validate();
			if($("#insertWmIcForm").valid()&&aaa){
				if (isDelegation=='1') {
					$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
					$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
					$(".wmIc1-3").show();
					$(".wmIc4-3").hide();
				}else{
					wmIcFunction('1');
					wmIcFalg1=true;
				}
			}
		});
		$(".wmIc2-2").click(function() {
			 $("#insertWmIcForm").validate();
			if($("#insertWmIcForm").valid()&&bbb){
				wmIcFunction('2');
				wmIcFalg2=true;
			}
		});
		$(".wmIc3-2").click(function() {
			 $("#insertWmIcForm").validate();
			if($("#insertWmIcForm").valid()&&bbb&&aaa){
				wmIcFunction('3');
				wmIcFalg3=true;
			}
		});
		$(".wmIc4-2").click(function() {
			 $("#insertWmIcForm").validate();
			if($("#insertWmIcForm").valid()&&bbb&&aaa&&ccc){
				wmIcFunction('4');
				wmIcFalg4=true;
			}

		});
		$(".wmIc5-2").click(function() {
			 $("#insertWmIcForm").validate();
			if($("#insertWmIcForm").valid()&&bbb&&aaa&&ccc&&ddd){
				wmIcFunction('5');
				wmIcFalg5=true;
			}
		});
		$(".wmIc1-4").click(function() {
			 $("#insertWmIcForm").validate();
			   if($("#insertWmIcForm").valid()){
				   if (isDelegation=='1') {
					   $(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
						$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
						$(".wmIc1-3").hide();
						$(".wmIc4-3").show();
						wmIcFalg6=true;
				   }else{
					   wmIcFunction('2');
						aaa=true;
						wmIcFalg1=true;
				   }
			   }
		});
		
		$(".wmIc2-4").click(function() {
			 $("#insertWmIcForm").validate();
			 if($("#insertWmIcForm").valid()){
				wmIcFunction('3');
				bbb=true;
				wmIcFalg2=true;
			} 

		});
		$(".wmIc3-4").click(function() {
			 $("#insertWmIcForm").validate();
			   if($("#insertWmIcForm").valid()){
				 wmIcFunction('4');
				 ccc=true;
				 wmIcFalg3=true;
			   }
		});
		
		$(".wmIc4-4").click(function() {
			 $("#insertWmIcForm").validate();
			 if($("#insertWmIcForm").valid()){
				 if (isDelegation=='1') {
					   $(".wmIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
						$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
						$(".wmIc1-3").show();
						$(".wmIc4-3").hide();
						
				   }else{
					   wmIcFunction('5');
						 wmIcFalg4=true;
						ddd=true;
				   }
			 }
		});
		
		$(".wmIc5-4").click(function() {
			$("#insertWmIcForm").validate();
			if($("#insertWmIcForm").valid()){
				wmIcFunction('5');
				wmIcFalg5=true;
				ddd=true;
			}
		});
		//点击自助委托
		$("#wmIcRadio1").on("click",function(){
			$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
			$(".wmIcHidden").show();
			isDelegation=0;
			wmIcQqwt=true;
		});
		//点击全权委托
		$("#wmIcRadio2").on("click",function(){
			$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
			isDelegation=1;
			$(".wmIcHidden").hide();
			wmIcQqwt=false;
		});
//#######################外贸进口#################################################################		
		$(".wmIc_submit").click(function(){
			 $("#insertWmIcForm").validate();
			 if($("#insertWmIcForm").valid()){
				 var shipmentTimeWmIcId=$("#shipmentTimeWmIcId").val();
				 var deliveryTimeWmIcId=$("#deliveryTimeWmIcId").val();
				 var startDate=$("#startDateWmIcId").val();
				 var endDate=$("#endDateWmIcId").val();
				var flag2=false;
				 if (wmIcQqwt) {
					  if (wmIcFalg1==false) {
							wmIcFunction('2');
							wmIcFalg1=true;
							aaa=true;
							return false;
						}else if (wmIcFalg2==false) {
							wmIcFunction('3');
							bbb=true;
							wmIcFalg2=true;
							return false;
						}else if (wmIcFalg3==false) {
							wmIcFunction('4');
							ccc=true;
							wmIcFalg3=true;
							return false;
						}
					  var flag1=duibi(shipmentTimeWmIcId,deliveryTimeWmIcId,'装运时间','交货时间');
						if (flag1==false) {
							wmIcFunction('3');
							return false;
						}
						flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							wmIcFunction('4');
							return false;
						}
					}else{
						if (wmIcFalg6==false) {
							$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
							$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
							$(".wmIc1-3").hide();
							$(".wmIc4-3").show();
							aaa=true;
							return false;
						}
						 flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
							$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
							$(".wmIc1-3").hide();
							$(".wmIc4-3").show();
							return false;
						}
					}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 $(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
					$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
					$(".wmIc1-3").hide();
					$(".wmIc4-3").show();
					return false;
				 }
				 submitFunction('wmIc_submit');
				 var orderFromId=$("#wmIcOrderFromId").val();
				var explain=$("#explainWmIcId").val();
				var sellersId=$("#sellersId").val();
				var taskTypeId=$("#wmIciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var buyersWmIcId=$("#buyersWmIcId").val();
				var consignorwmIcId=$("#consignorwmIcId").val();
				var sellersWmIcId=$("#sellersWmIcId").val();
				var contractNoWmIcId=$("#contractNoWmIcId").val();
				var contractDateWmIcId=$("#contractDateWmIcId").val();
				var bAddressWmIcId=$("#bAddressWmIcId").val();
				var bFaxWmIcId=$("#bFaxWmIcId").val();
				var bTelphoneWmIcId=$("#bTelphoneWmIcId").val();
				var sAddressWmIcId=$("#sAddressWmIcId").val();
				var sFaxWmIcId=$("#sFaxWmIcId").val();
				var sTelphoneWmIcId=$("#sTelphoneWmIcId").val();
				var portShipmentWmIcId=$("#portShipmentWmIcId").val();
				var portDestinationWmIcId=$("#portDestinationWmIcId").val();
				var packingWmIcId=$("#packingWmIcId").val();
				var insuranceWmIcId=$("#insuranceWmIcId").val();
				var manufactoryWmIcId=$("#manufactoryWmIcId").val();
				var termPaymentWmIcId=$("#termPaymentWmIcId").val();
				var inspectionWmIcId=$("#inspectionWmIcId").val();
				var arbitrationWmIcId=$("#arbitrationWmIcId").val();
				var othersWmIcId=$("#othersWmIcId").val();
				var fileArray =document.getElementsByName("filesWmIc");
				var fileArrays=array(fileArray);
				//合同明细
				var commodityNameArray = [];
				$('.commodityName').each(function(i,e){
					commodityNameArray[i]=e.value;
					
				});
				
				var commodityNameIdArray = [];
				$('.commodityNameId').each(function(i,e){
					commodityNameIdArray[i]=e.value;
					
				});
				
				var modelsArray = [];
				$('.models').each(function(i,e){
					modelsArray[i]=e.value;
					
				});
				
				var quantityArray = [];
				$('.quantity').each(function(i,e){
					quantityArray[i]=e.value;
					
				});
				
				var unitPriceArray = [];
				$('.unitPrice').each(function(i,e){
					unitPriceArray[i]=e.value;
					
				});
				
				var totalAmountArray = [];
				$('.totalAmount').each(function(i,e){
					totalAmountArray[i]=e.value;
					
				});
					$.ajax({
						type:"post",
						url:basePath+'/sup/order/ic/saveImportContract',
						dataType : "json",
						data:{
							isDelegation:isDelegation,
							startDate:startDate,
							endDate:endDate,
							explain:explain,
							taskId:taskId,        
							 taskTypeId:taskTypeId,
							contractDate:contractDateWmIcId,
							 contractNo:contractNoWmIcId,
							 sAddress:sAddressWmIcId,
							 sTelphone:sTelphoneWmIcId,
							 sFax:sFaxWmIcId,
							 userId:userId,
							 consignor:consignorwmIcId,
							 sellers:sellersWmIcId,
							 buyers:buyersWmIcId,
							 bAddress:bAddressWmIcId,
							 bTelphone:bTelphoneWmIcId,
							 bFax:bFaxWmIcId,
							shipmentTime:shipmentTimeWmIcId,
							deliveryTime:deliveryTimeWmIcId,
							 portShipment:portShipmentWmIcId,
							 portDestination:portDestinationWmIcId,
							 packing:packingWmIcId,
							 insurance:insuranceWmIcId,
							 manufactory:manufactoryWmIcId,
							 termPayment:termPaymentWmIcId,    
							 inspection:inspectionWmIcId,
							 arbitration:arbitrationWmIcId,    
							 others:othersWmIcId,
							 commodityName:JSON.stringify(commodityNameArray).toString(),
							 commodityNameId:JSON.stringify(commodityNameIdArray).toString(),
							 models:JSON.stringify(modelsArray).toString(),
							 quantity:JSON.stringify(quantityArray).toString(),
							 unitPrice:JSON.stringify(unitPriceArray).toString(),
							 totalAmount:JSON.stringify(totalAmountArray).toString(),
							 fileArray:JSON.stringify(fileArray).toString(),
							 fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#wmIcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('外贸进口订单保存成功',{icon: 1});
								removeFunction('wmIc_submit');
								typeIdByPageShowOrHide(taskType);
								
							}
						}	
				});
			}
		});
		
		function wmIcFunction(i){
			switch (i) {
			case '1':
				$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".wmIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmIc1-3").show();
				$(".wmIc2-3").hide();
				$(".wmIc3-3").hide();
				$(".wmIc4-3").hide();
				$(".wmIc5-3").hide();
				break;
			case '2':
				$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmIc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".wmIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmIc1-3").hide();
				$(".wmIc2-3").show();
				$(".wmIc3-3").hide();
				$(".wmIc4-3").hide();
				$(".wmIc5-3").hide();
				break;
			case '3':
				$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmIc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmIc1-3").hide();
				$(".wmIc2-3").hide();
				$(".wmIc3-3").show();
				$(".wmIc4-3").hide();
				$(".wmIc5-3").hide();
				break;
			case '4':
				$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_c4.png");
				$(".wmIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmIc1-3").hide();
				$(".wmIc2-3").hide();
				$(".wmIc3-3").hide();
				$(".wmIc4-3").show();
				$(".wmIc5-3").hide();
				break;
			case '5':
				$(".wmIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmIc5-2").attr("src", basePath+"/resource/images/icon_c5.png");
				$(".wmIc1-3").hide();
				$(".wmIc2-3").hide();
				$(".wmIc3-3").hide();
				$(".wmIc4-3").hide();
				$(".wmIc5-3").show();
				break;
			}
		}
//######################2.外贸出口#################################################################
		var wmEcQqwt=true;
		var wmEcFalg1=false;
		var wmEcFalg2=false;	
		var wmEcFalg3=false;	
		var wmEcFalg4=false;	
		var wmEcFalg5=false;	
		var wmEcFalg6=false;	
		$(".wmEc1-2").click(function() {
			$("#insertWmEcForm").validate();
			if($("#insertWmEcForm").valid()&&aaa){
				wmEcFunction('1');
				wmEcFalg1=true;
			}

		});
		$(".wmEc2-2").click(function() {
			$("#insertWmEcForm").validate();
			if($("#insertWmEcForm").valid()&&bbb){
				wmEcFunction('2');
				wmEcFalg2=true;
			}

		});
		$(".wmEc3-2").click(function() {
			$("#insertWmEcForm").validate();
			if($("#insertWmEcForm").valid()&&bbb&&aaa){
				wmEcFunction('3');
				wmEcFalg3=true;
			}

		});
		
		$(".wmEc4-2").click(function() {
			$("#insertWmEcForm").validate();
			if($("#insertWmEcForm").valid()&&bbb&&aaa&&ccc){
				wmEcFunction('4');
				wmEcFalg4=true;
			}

		});
		$(".wmEc5-2").click(function() {
			$("#insertWmEcForm").validate();
			if($("#insertWmEcForm").valid()&&bbb&&aaa&&ccc&&ddd){
				wmEcFunction('5');
				 wmEcFalg5=true;
			}
			
		});
		$(".wmEc1-4").click(function() {
			 $("#insertWmEcForm").validate();
			 if($("#insertWmEcForm").valid()){
				 if (isDelegation=='1') {
					   $(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
						$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
						$(".wmEc1-3").hide();
						$(".wmEc4-3").show();
						wmEcFalg6=true;
						aaa=true;
				   }else{
					   wmEcFunction('2');
						wmEcFalg1=true;
						aaa=true;
				   }
			}

		});
		$(".wmEc2-4").click(function() {
			 $("#insertWmEcForm").validate();
			 if($("#insertWmEcForm").valid()){
				 wmEcFunction('3');
				  wmEcFalg2=true;
				bbb=true;
			} 

		});
		$(".wmEc3-4").click(function() {
			 $("#insertWmEcForm").validate();
			 if($("#insertWmEcForm").valid()){
				 wmEcFunction('4');
				  wmEcFalg3=true;
				ccc=true;
			} 
		});
		
		$(".wmEc4-4").click(function() {
			 $("#insertWmEcForm").validate();
			 if($("#insertWmEcForm").valid()){
				 if (isDelegation=='1') {
					   $(".wmEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
						$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
						$(".wmEc1-3").show();
						$(".wmEc4-3").hide();
				   }else{
					   wmEcFunction('5');
						  wmEcFalg4=true;
						ddd=true;
				   }
			} 
		});
		function wmEcFunction(i){
			switch (i) {
			case '1':
				$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".wmEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmEc1-3").show();
				$(".wmEc2-3").hide();
				$(".wmEc3-3").hide();
				$(".wmEc4-3").hide();
				$(".wmEc5-3").hide();
				break;
			case '2':
				$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmEc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".wmEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmEc1-3").hide();
				$(".wmEc2-3").show();
				$(".wmEc3-3").hide();
				$(".wmEc4-3").hide();
				$(".wmEc5-3").hide();
				break;
			case '3':
				$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmEc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmEc1-3").hide();
				$(".wmEc2-3").hide();
				$(".wmEc3-3").show();
				$(".wmEc4-3").hide();
				$(".wmEc5-3").hide();
				break;
			case '4':
				$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_c4.png");
				$(".wmEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".wmEc1-3").hide();
				$(".wmEc2-3").hide();
				$(".wmEc3-3").hide();
				$(".wmEc4-3").show();
				$(".wmEc5-3").hide();
				break;
			case '5':
				$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wmEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wmEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".wmEc5-2").attr("src", basePath+"/resource/images/icon_c5.png");
				$(".wmEc1-3").hide();
				$(".wmEc2-3").hide();
				$(".wmEc3-3").hide();
				$(".wmEc4-3").hide();
				$(".wmEc5-3").show();
				break;
			}
		}
		//点击自助委托
		$("#wmEcRadio1").on("click",function(){
			$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
			$(".wmEcHidden").show();
			isDelegation=0;
			wmEcQqwt=true;
		});
		//点击全权委托
		$("#wmEcRadio2").on("click",function(){
			$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
			isDelegation=1;
			wmEcQqwt=false;
			$(".wmEcHidden").hide();
			
		});
//##################################外贸出口#########################################################	
		$(".wmEc_submit").click(function(){
			 $("#insertWmEcForm").validate();
			 if($("#insertWmEcForm").valid()){
				 var shipmentTimeWmEcId=$("#shipmentTimeWmEcId").val();
				 var deliveryTimeWmEcId=$("#deliveryTimeWmEcId").val();
				 var startDate=$("#startDateWmEcId").val();
				var endDate=$("#endDateWmEcId").val();
				var flag2=false;
				 if (wmEcQqwt) {
					  if (wmEcFalg1==false) {
							wmEcFunction('2');
							wmEcFalg1=true;
							return false;
						}else if (wmEcFalg2==false) {
							wmEcFunction('3');
							aaa=true;
							wmEcFalg2=true;
							return false;
						}else if (wmEcFalg3==false) {
							wmEcFunction('4');
							aaa=true;
							bbb=true;
							wmEcFalg3=true;
							return false;
						}else if (wmEcFalg4==false) {
							wmEcFunction('5');
							aaa=true;
							bbb=true;
							ccc=true;
							wmEcFalg4=true;
							return false;
						}
					  var flag1=duibi(shipmentTimeWmEcId,deliveryTimeWmEcId,'装运时间','交货时间');
						if (flag1==false) {
							wmEcFunction('3');
							return false;
						}
						
					   flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							wmEcFunction('4');
							return false;
						}
					}else{
						if (wmEcFalg6==false) {
							$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
							$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
							$(".wmEc1-3").hide();
							$(".wmEc4-3").show();
							wmEcFalg6=true;
							aaa=true;
							return false;
						}
						 flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							$(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
							$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
							$(".wmEc1-3").hide();
							$(".wmEc4-3").show();
							return false;
						}
					}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 $(".wmEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
						$(".wmEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
						$(".wmEc1-3").hide();
						$(".wmEc4-3").show();
					return false;
				 }
				 submitFunction('wmEc_submit');
				 var orderFromId=$("#wmEcOrderFromId").val();
				var explain=$("#explainWmEcId").val();
				var taskTypeId=$("#wmEciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var consignor=$("#consignorwmEcId").val();
				var buyersWmEcId=$("#buyersWmEcId").val();
				var sellersWmEcId=$("#sellersWmEcId").val();
				var contractNoWmEcId=$("#contractNoWmEcId").val();
				var contractDateWmEcId=$("#contractDateWmEcId").val();
				var buyersWmEcId=$("#buyersWmEcId").val();
				var bAddressWmEcId=$("#bAddressWmEcId").val();
				var bFaxWmEcId=$("#bFaxWmEcId").val();
				var bTelphoneWmEcId=$("#bTelphoneWmEcId").val();
				var sAddressWmEcId=$("#sAddressWmEcId").val();
				var sFaxWmEcId=$("#sFaxWmEcId").val();
				var freightWmEcId=$("#freightWmEcId").val();
				var sTelphoneWmEcId=$("#sTelphoneWmEcId").val();
				var portShipmentWmEcId=$("#portShipmentWmEcId").val();
				var portDestinationWmEcId=$("#portDestinationWmEcId").val();
				var packingWmEcId=$("#packingWmEcId").val();
				var insuranceWmEcId=$("#insuranceWmEcId").val();
				var manufactoryWmEcId=$("#manufactoryWmEcId").val();
				var termPaymentWmEcId=$("#termPaymentWmEcId").val();
				var inspectionWmEcId=$("#inspectionWmEcId").val();
				var arbitrationWmEcId=$("#arbitrationWmEcId").val();
				var othersWmEcId=$("#othersWmEcId").val();
				var fileArray =document.getElementsByName("filesWmEc");
				var fileArrays=array(fileArray);
				//合同明细
				var commodityNameArray = [];
				$('.commodityNameWmEc').each(function(i,e){
					commodityNameArray[i]=e.value;
					
				});
				//合同明细
				var commodityNameIdArray = [];
				$('.commodityIdWmEc').each(function(i,e){
					commodityNameIdArray[i]=e.value;
					
				});
				
				var modelsArray = [];
				$('.modelsWmEc').each(function(i,e){
					modelsArray[i]=e.value;
					
				});
				
				var quantityArray = [];
				$('.quantityWmEc').each(function(i,e){
					quantityArray[i]=e.value;
					
				});
				
				var unitPriceArray = [];
				$('.unitPriceWmEc').each(function(i,e){
					unitPriceArray[i]=e.value;
					
				});
				
				var totalAmountArray = [];
				$('.totalAmountWmEc').each(function(i,e){
					totalAmountArray[i]=e.value;
					
				});
					$.ajax({
						type:"post",
						url:basePath+'/sup/order/ex/saveExportContract',
						dataType : "json",
						data:{
							taskId:taskId, 
							startDate:startDate,
							endDate:endDate,
							explain:explain,
							isDelegation:isDelegation,
							 taskTypeId:taskTypeId,
							 userId:userId,
							 freight:freightWmEcId,
							 consignor:consignor,
							contractDate:contractDateWmEcId,
							 contractNo:contractNoWmEcId,
							 sAddress:sAddressWmEcId,
							 sTelphone:sTelphoneWmEcId,
							 sFax:sFaxWmEcId,
							 buyers:buyersWmEcId,
							 sellers:sellersWmEcId,
							 bAddress:bAddressWmEcId,
							 bTelphone:bTelphoneWmEcId,
							 bFax:bFaxWmEcId,
							shipmentTime:shipmentTimeWmEcId,
							deliveryTime:deliveryTimeWmEcId,
							 portShipment:portShipmentWmEcId,
							 portDestination:portDestinationWmEcId,
							 packing:packingWmEcId,
							 insurance:insuranceWmEcId,
							 manufactory:manufactoryWmEcId,
							 termPayment:termPaymentWmEcId,    
							 inspection:inspectionWmEcId,
							 arbitration:arbitrationWmEcId,    
							 others:othersWmEcId,
							 commodityName:JSON.stringify(commodityNameArray).toString(),
							 commodityNameId:JSON.stringify(commodityNameIdArray).toString(),
							 models:JSON.stringify(modelsArray).toString(),
							 quantity:JSON.stringify(quantityArray).toString(),
							 unitPrice:JSON.stringify(unitPriceArray).toString(),
							 totalAmount:JSON.stringify(totalAmountArray).toString(),
							 fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#wmEcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('外贸出口订单保存成功',{icon: 1});
								removeFunction('wmEc_submit');
								typeIdByPageShowOrHide(taskType);
								
							}
						}	
				});
			 }
		});
//#####################3.报关进口###################################################################################
		var bgIcQqwt=true;
		var bgIcFalg1=false;
		var bgIcFalg2=false;
		var bgIcFalg3=false;
		$(".bgIc1-2").click(function() {
			 $("#insertBgIcForm").validate();
			if($("#insertBgIcForm").valid()&&aaa){
				bgIcFunction('1');
				bgIcFalg1=true;
				
			}

		});
		$(".bgIc2-2").click(function() {
			 $("#insertBgIcForm").validate();
			 if($("#insertBgIcForm").valid()&&bbb){
				 bgIcFunction('2');
				 bgIcFalg2=true;
			}
		});
		$(".bgIc3-2").click(function() {
			$("#insertBgIcForm").validate();
			if($("#insertBgIcForm").valid()&&aaa&&bbb){
				bgIcFunction('3');
			}
		});
		$(".bgIc1-4").click(function() {
			 $("#insertBgIcForm").validate();
			 if($("#insertBgIcForm").valid()){
				 bgIcFunction('2');
				 aaa=true;
				 bgIcFalg1=true;
				 bgIcFalg2=true;
				 
			}
		});
		$(".bgIc2-4").click(function() {
			$("#insertBgIcForm").validate();
			if($("#insertBgIcForm").valid()){
				var startDate=$("#startDateBgIcId").val();
				var endDate=$("#endDateBgIcId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					bgIcFunction('2');
					return false;
				}
				if (isDelegation=='1') {
					 $(".bgIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
						$(".bgIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
						$(".bgIc1-3").show();
						$(".bgIc2-3").hide();
				} else {
					bgIcFunction('3');
					bbb=true;
					bgIcFalg2=true;
				}
				
			}
		});
		function bgIcFunction(i){
			switch (i) {
			case '1':
				$(".bgIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".bgIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".bgIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".bgIc1-3").show();
				$(".bgIc2-3").hide();
				$(".bgIc3-3").hide();
				break;
			case '2':
				$(".bgIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".bgIc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".bgIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".bgIc1-3").hide();
				$(".bgIc2-3").show();
				$(".bgIc3-3").hide();
				break;
			case '3':
				$(".bgIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".bgIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".bgIc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".bgIc1-3").hide();
				$(".bgIc2-3").hide();
				$(".bgIc3-3").show();
				break;
			}
		}
		
		//报关进口点击自助委托
		$("#bgIcRadio1").on("click",function(){
			$(".bgIcHidden").show();
			isDelegation=0;
			bgIcQqwt=true;
		});
		//报关进口点击全权委托
		$("#bgIcRadio2").on("click",function(){
			isDelegation=1;
			$(".bgIcHidden").hide();
			bgIcQqwt=false;
		});
//#####################报关进口#################################################################		
		$(".bgIc_submit").click(function(){
			 $("#insertBgIcForm").validate();
			 if($("#insertBgIcForm").valid()){
				 if (bgIcQqwt) {
						if (bgIcFalg2==false) {
							bgIcFunction('2');
							aaa=true;
							bgIcFalg2=true;
							bgIcFalg1=true;
							return false;
						}else if (bgIcFalg1==false) {
							bgIcFunction('1');
							return false;
						}
					}else{
						if (bgIcFalg1==false) {
							bgIcFunction('2');
							bgIcFalg1=true;
							aaa=true;
							return false;
						}
					}
				 var startDate=$("#startDateBgIcId").val();
				var endDate=$("#endDateBgIcId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					bgIcFunction('2');
					return false;
				}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 bgIcFunction('2');
					return false;
				 }
				submitFunction('bgIc_submit');
				 var orderFromId=$("#bgIcOrderFromId").val();
				var explain=$("#explainBgIcId").val();
				var sellersId=$("#sellersId").val();
				var taskTypeId=$("#bgIciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var buyersId=$("#buyersId").val();
				var consignorBgIcId=$("#consignorBgIcId").val();
				var goodsNameBgIcId=$("#goodsNameBgIcId").val();
				var hsCodeBgIcId=$("#hsCodeBgIcId").val();
				var goodsTotalPriceBgIcId=$("#goodsTotalPriceBgIcId").val();
				var weightBgIcId=$("#weightBgIcId").val();
				var packDetailBgIcId=$("#packDetailBgIcId").val();
				var contractNoBgIcId=$("#contractNoBgIcId").val();
				var licenseFileNoBgIcId=$("#licenseFileNoBgIcId").val();
				var imExDateBgIcId=$("#imExDateBgIcId").val();
				var deliveryNumberBgIcId=$("#deliveryNumberBgIcId").val();
				var tradeTypeBgIcId=$("#tradeTypeBgIcId").val();
				var originPlaceBgIcId=$("#originPlaceBgIcId").val();
				var othersBgIcId=$("#othersBgIcId").val();
				var consigneeBgIcId=$("#consigneeBgIcId").val();
				var customsCodeBgIcId=$("#customsCodeBgIcId").val();
				var receiveDocsDateBgIcId=$("#receiveDocsDateBgIcId").val();
				var receiveDocsConditionBgIcId=$("#receiveDocsConditionBgIcId").val();
				var customsChargeBgIcId=$("#customsChargeBgIcId").val();
				var docsOthersBgIcId=$("#docsOthersBgIcId").val();
				var comIllustrationBgIcId=$("#comIllustrationBgIcId").val();
				var fileArray =document.getElementsByName("filesBgIc");
				var fileArrays=array(fileArray);
					var arr=[];
					var fileArray =document.getElementsByName("ooo");
					for (var i = 0; i < fileArray.length; i++) {
						arr[i]=fileArray[i].value;
					}
					$.ajax({
						type:"post",
						url:basePath+'/sup/order/CustomsDeAgreementimcc/saveCustomsDeAgreement',
						dataType : "json",
						data:{
							taskId:taskId,
							startDate:startDate,
							endDate:endDate,
							explain:explain,
							isDelegation:isDelegation,
							 taskTypeId:taskTypeId,
							 sellers:sellersId,
							 userId:userId,
							 consignor:consignorBgIcId,
							 goodsName:goodsNameBgIcId,
							 hsCode:hsCodeBgIcId,
							 contractNo: contractNoBgIcId ,
							 goodsTotalPrice:goodsTotalPriceBgIcId,
							 comIllustration:comIllustrationBgIcId,
							 imExDate:imExDateBgIcId,
							 deliveryNumber:deliveryNumberBgIcId,
							 tradeType:tradeTypeBgIcId,
							 originPlace:originPlaceBgIcId,
							 others:othersBgIcId,
							 consignee:consigneeBgIcId,
							 licenseFileNo: licenseFileNoBgIcId ,
							 customsCode:customsCodeBgIcId,
							 receiveDocsDate:receiveDocsDateBgIcId,
							 receiveDocsCondition:receiveDocsConditionBgIcId,
							 customsCharge:customsChargeBgIcId,
							 docsOthers: docsOthersBgIcId ,
							 packDetail:  packDetailBgIcId,
							 weight: weightBgIcId ,
							 fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#bgIcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('报关进口订单保存成功',{icon: 1});
								removeFunction('bgIc_submit');
								typeIdByPageShowOrHide(taskType);
							}
						}	
				});
			 }
		});
//########################4.报关出口################################################################################
		var bgEcQqwt=true;
		var bgEcFalg1=false;
		var bgEcFalg2=false;
		$(".bgEc1-2").click(function() {
			$("#insertBgEcForm").validate();
			if($("#insertBgEcForm").valid()&&aaa){
				bgEcFunction('1');
				 bgEcFalg1=true;
			}

		});
		$(".bgEc2-2").click(function() {
			 $("#insertBgEcForm").validate();
			 if($("#insertBgEcForm").valid()){
				 bgEcFunction('2');
				 bgEcFalg2=true;
			}
		});
		$(".bgEc3-2").click(function() {
			if(aaa&&bbb){
				bgEcFunction('3');
			}
		});
		$(".bgEc1-4").click(function() {
			 $("#insertBgEcForm").validate();
			 if($("#insertBgEcForm").valid()){
				 bgEcFunction('2');
				aaa=true;
				 bgEcFalg1=true;
				 bgEcFalg2=true;
			}

		});
		$(".bgEc2-4").click(function() {
			$("#insertBgEcForm").validate();
			if($("#insertBgEcForm").valid()){
				var startDate=$("#startDateBgEcId").val();
				 var endDate=$("#endDateBgEcId").val();
				 var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				 if (flag2==false) {
					 bgEcFunction('2');
					 return false;
				 }
				if (isDelegation=='1') {
					$(".bgEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
					$(".bgEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
					$(".bgEc1-3").show();
					$(".bgEc2-3").hide();
				} else {
					bgEcFunction('3');
					bbb=true;
					bgEcFalg2=true;
				}
				
			}
			
		});
		function bgEcFunction(i){
			switch (i) {
			case '1':
				$(".bgEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".bgEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".bgEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".bgEc1-3").show();
				$(".bgEc2-3").hide();
				$(".bgEc3-3").hide();
				break;
			case '2':
				$(".bgEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".bgEc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".bgEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".bgEc1-3").hide();
				$(".bgEc2-3").show();
				$(".bgEc3-3").hide();
				break;
			case '3':
				$(".bgEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".bgEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".bgEc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".bgEc1-3").hide();
				$(".bgEc2-3").hide();
				$(".bgEc3-3").show();
				break;
			
			}
		}
		//报关出口点击自助委托
		$("#bgEcRadio1").on("click",function(){
			$(".bgEcHidden").show();
			isDelegation=0;
			bgEcQqwt=true;
		});
		//报关出口点击全权委托
		$("#bgEcRadio2").on("click",function(){
			isDelegation=1;
			$(".bgEcHidden").hide();
			bgEcQqwt=false;
		});
		
		$(".bgEc_submit").click(function(){
			 $("#insertBgEcForm").validate();
			 if($("#insertBgEcForm").valid()){
				 if (bgEcQqwt) {
						if (bgEcFalg2==false) {
							bgEcFunction('2');
							aaa=true;
							bgEcFalg2=true;
							bgEcFalg1=true;
							return false;
						}else if (bgEcFalg1==false) {
							bgEcFunction('1');
							return false;
						}
					}else{
						if (bgEcFalg1==false) {
							bgEcFunction('2');
							aaa=true;
							bgEcFalg1=true;
							return false;
						}
					}
				 var startDate=$("#startDateBgEcId").val();
				 var endDate=$("#endDateBgEcId").val();
				 var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				 if (flag2==false) {
					 bgEcFunction('2');
					 return false;
				 }
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 bgEcFunction('2');
					return false;
				 }
				 submitFunction('bgEc_submit');
				 var orderFromId=$("#bgEcOrderFromId").val();
				var explain=$("#explainBgEcId").val();
				var taskTypeId=$("#bgEciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var consignorBgEcId=$("#consignorBgEcId").val();
				var goodsNameBgEcId=$("#goodsNameBgEcId").val();
				var hsCodeBgEcId=$("#hsCodeBgEcId").val();
				var goodsTotalPriceBgEcId=$("#goodsTotalPriceBgEcId").val();
				var weightBgEcId=$("#weightBgEcId").val();
				var packDetailBgEcId=$("#packDetailBgEcId").val();
				var contractNoBgEcId=$("#contractNoBgEcId").val();
				var licenseFileNoBgEcId=$("#licenseFileNoBgEcId").val();
				
				var imExDateBgEcId=$("#imExDateBgEcId").val();
				var deliveryNumberBgEcId=$("#deliveryNumberBgEcId").val();
				var tradeTypeBgEcId=$("#tradeTypeBgEcId").val();
				var originPlaceBgEcId=$("#originPlaceBgEcId").val();
				var othersBgEcId=$("#othersBgEcId").val();
				var consigneeBgEcId=$("#consigneeBgEcId").val();
				var customsCodeBgEcId=$("#customsCodeBgEcId").val();
				var receiveDocsDateBgEcId=$("#receiveDocsDateBgEcId").val();
				var receiveDocsConditionBgEcId=$("#receiveDocsConditionBgEcId").val();
				var customsChargeBgEcId=$("#customsChargeBgEcId").val();
				var docsOthersBgEcId=$("#docsOthersBgEcId").val();
				var customsChargeBgEcId=$("#customsChargeBgEcId").val();
				var comIllustrationBgEcId=$("#comIllustrationBgEcId").val();
				var fileArray =document.getElementsByName("filesBgEc");
				var fileArrays=array(fileArray);
					$.ajax({
						type:"post",
						url:basePath+'/sup/order/ExportCustomsDeAgreementexcc/saveExportCustomsDeAgreement',
						dataType : "json",
						data:{
							 userId: userId ,
							 startDate:startDate,
							endDate:endDate,
							explain:explain,
							 isDelegation:isDelegation,
							 taskTypeId:taskTypeId,
							 taskId: taskId ,
							 consignor: consignorBgEcId ,
							 goodsName: goodsNameBgEcId ,
							 hsCode: hsCodeBgEcId ,
							 weight: weightBgEcId ,
							 packDetail:  packDetailBgEcId,
							 contractNo: contractNoBgEcId ,
							 licenseFileNo: licenseFileNoBgEcId ,
							 goodsTotalPrice: goodsTotalPriceBgEcId ,
							 imExDate: imExDateBgEcId ,
							 deliveryNumber: deliveryNumberBgEcId ,
							 tradeType: tradeTypeBgEcId ,
							 originPlace: originPlaceBgEcId ,
							 others: othersBgEcId ,
							 consignee: consigneeBgEcId ,
							 customsCode: customsCodeBgEcId ,
							 receiveDocsDate: receiveDocsDateBgEcId ,
							 receiveDocsCondition: receiveDocsConditionBgEcId ,
							 docsOthers: docsOthersBgEcId ,
							 customsCharge: customsChargeBgEcId ,
							 comIllustration: comIllustrationBgEcId ,
							 fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#bgEcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('报关出口订单保存成功',{icon: 1});
								removeFunction('bgEc_submit');
								typeIdByPageShowOrHide(taskType);
								
							}
						}	
				});
		 }
	});
//########################5.物流进口################################################################################
		var wlIcQqwt=true;
		var wlIcFalg2=false;
		var wlIcFalg1=false;
		$(".wlIc1-2").click(function() {
			$("#insertWlIcForm").validate();
			if($("#insertWlIcForm").valid()&&aaa){
				wlIcFunction('1');
				wlIcFalg1=true;
			}

		});
		$(".wlIc2-2").click(function() {
			if($("#insertWlIcForm").valid()&&bbb){
				wlIcFunction('2');
				wlIcFalg2=true;
			}

		});
		$(".wlIc3-2").click(function() {
			if(bbb&&aaa){
				wlIcFunction('3');
			}
			
		});
		$(".wlIc1-4").click(function() {
			$("#insertWlIcForm").validate();
			if($("#insertWlIcForm").valid()){
				wlIcFunction('2');
				wlIcFalg1=true;
				aaa=true;
			}
		});
		$(".wlIc2-4").click(function() {
			$("#insertWlIcForm").validate();
			if($("#insertWlIcForm").valid()){
				var startDate=$("#startDateWlIcId").val();
				var endDate=$("#endDateWlIcId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					wlIcFunction('2');
					return false;
				}
				if (isDelegation=='1') {
					$(".wlIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
					$(".wlIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
					$(".wlIc1-3").show();
					$(".wlIc2-3").hide();
					bbb=true;
				}else{
					wlIcFunction('3');
					wlIcFalg2=true;
					bbb=true;
				}
			}
		});
		function wlIcFunction(i){
			switch (i) {
			case '1':
				$(".wlIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".wlIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wlIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wlIc1-3").show();
				$(".wlIc2-3").hide();
				$(".wlIc3-3").hide();
				break;
			case '2':
				$(".wlIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wlIc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".wlIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wlIc1-3").hide();
				$(".wlIc2-3").show();
				$(".wlIc3-3").hide();
				break;
			case '3':
				$(".wlIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wlIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wlIc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".wlIc1-3").hide();
				$(".wlIc2-3").hide();
				$(".wlIc3-3").show();
				break;
			}
		}
		//物流进口点击自助委托
		$("#wlIcRadio1").on("click",function(){
			$(".wlIcHidden").show();
			isDelegation=0;
			wlIcQqwt=true;
		});
		//物流进口点击全权委托
		$("#wlIcRadio2").on("click",function(){
			isDelegation=1;
			$(".wlIcHidden").hide();
			wlIcQqwt=false;
		});
//##################################物流进口#######################################################################
		$(".wlIc_submit").click(function(){
			$("#insertWlIcForm").validate();
			if($("#insertWlIcForm").valid()){
				if (wlIcQqwt) {
					if (wlIcFalg2==false) {
						wlIcFunction('2');
						aaa=true;
						wlIcFalg2=true;
						wlIcFalg1=true;
						return false;
					}else if (wlIcFalg1==false) {
						wlIcFunction('1');
						return false;
					}
				}else{
					if (wlIcFalg1==false) {
						wlIcFunction('2');
						aaa=true;
						wlIcFalg1=true;
						return false;
					}
				}
				var startDate=$("#startDateWlIcId").val();
				var endDate=$("#endDateWlIcId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					wlIcFunction('2');
					return false;
				}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 wlIcFunction('2');
					return false;
				 }
				 submitFunction('wlIc_submit');
				var orderFromId=$("#wlIcOrderFromId").val();
				var explain=$("#explainWlIcId").val();
				var wlIcshipper=$("#wlIcshipper").val();
				var wlIcsAddress=$("#wlIcsAddress").val();
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var taskTypeId=$("#wlIciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var consignor=$("#wlIcconsignor").val();
				var wlIcdeparturePort=$("#wlIcdeparturePort").val();
				var wlIcdischargePort=$("#wlIcdischargePort").val();
				var wlIcdestinationPort=$("#wlIcdestinationPort").val();
				var wlIcxhsNo=$("#wlIcxhsNo").val();
				var wlIcconsignee=$("#wlIcconsignee").val();
				var wlIccAddress=$("#wlIccAddress").val();
				var wlIcarrivalDate=$("#wlIcarrivalDate").val();
				var wlIcpayWay=$("#wlIcpayWay").val();
				var wlIccontainer=$("#wlIccontainer").val();
				var wlIcblt=$("#wlIcblt").val();
				var wlIcnotifier=$("#wlIcnotifier").val();
				var wlIcitem=$("#wlIcitem").val();
				var wlIcweight=$("#wlIcweight").val();
				var wlIcvolume=$("#wlIcvolume").val();
				var wlIcmarks=$("#wlIcmarks").val();
				var wlIcgoodsName=$("#wlIcgoodsName").val();
				var wlIctransExpenseCharge=$("#wlIctransExpenseCharge").val();
				var wlIctransClause=$("#wlIctransClause").val();
				var wlIccontactPerson=$("#wlIccontactPerson").val();
				var wlIccpPhone=$("#wlIccpPhone").val();
				var wlIccpFax=$("#wlIccpFax").val();
				var wlIccpMail=$("#wlIccpMail").val();
				var wlIcpreFlight=$("#wlIcpreFlight").val();
				var wlIcselfFull=$("#wlIcselfFull").val();
				var wlIcspecialNotes=$("#wlIcspecialNotes").val();
				var wlIcsignature=$("#wlIcsignature").val();
				var fileArray =document.getElementsByName("filesWlIc");
				var fileArrays=array(fileArray);
					$.ajax({
						type:"post",
						url:basePath+'/sup/wuliuimcc/saveShippingExCommission',
						dataType : "json",
						data:{
							shipper:wlIcshipper,
							startDate:startDate,
							endDate:endDate,
							explain:explain,
							isDelegation:isDelegation,
							taskTypeId:taskTypeId,
							userId:userId,
							taskId:taskId,
							consignor:consignor,
							sAddress:wlIcsAddress,
							departurePort:wlIcdeparturePort,
							dischargePort:wlIcdischargePort,
							destinationPort:wlIcdestinationPort,
							xhsNo:wlIcxhsNo,
							consignee:wlIcconsignee,
							cAddress:wlIccAddress,
							arrivalDate:wlIcarrivalDate,
							payWay:wlIcpayWay,
							container:wlIccontainer,
							blt:wlIcblt,
							notifier:wlIcnotifier,
							item:wlIcitem,
							weight:wlIcweight,
							volume:wlIcvolume,
							marks:wlIcmarks,
							goodsName:wlIcgoodsName,
							transExpenseCharge:wlIctransExpenseCharge,
							transClause:wlIctransClause,    
							contactPerson:wlIccontactPerson,
							cpPhone:wlIccpPhone,    
							cpFax:wlIccpFax,
							cpMail:wlIccpMail,
							preFlight:wlIcpreFlight,
							selfFull:wlIcselfFull,
							signature:wlIcsignature,
							specialNotes:wlIcspecialNotes,
							fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#wlIcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('进口物流订单保存成功',{icon: 1});
								removeFunction('wlIc_submit');
								typeIdByPageShowOrHide(taskType);
								
							}
						}	
				});
			}
		});
//#########################6.物流出口###############################################################################
		var wlEcQqwt=true;
		var wlEcFalg2=false;
		var wlEcFalg1=false;
		$(".wlEc1-2").click(function() {
			$("#insertWlEcForm").validate();
			if($("#insertWlEcForm").valid()&&aaa){
				wlEcFunction('1');
				wlEcFalg1=true;
			}

		});
		$(".wlEc2-2").click(function() {
			$("#insertWlEcForm").validate();
			if($("#insertWlEcForm").valid()&&bbb){
				wlEcFunction('2');
				wlEcFalg2=true;
			}

		});
		$(".wlEc3-2").click(function() {
			$("#insertWlEcForm").validate();
			if($("#insertWlEcForm").valid()&&aaa&&bbb){
				wlEcFunction('3');
			}
			
		});
		$(".wlEc1-4").click(function() {
			$("#insertWlEcForm").validate();
			if($("#insertWlEcForm").valid()){
				wlEcFunction('2');
				aaa=true;
				wlEcFalg1=true;
			}
		});
		$(".wlEc2-4").click(function() {
			$("#insertWlEcForm").validate();
			if($("#insertWlEcForm").valid()){
				var startDate=$("#startDateWlEcId").val();
				var endDate=$("#endDateWlEcId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					wlEcFunction('2');
					return false;
				}
				if (isDelegation=='1') {
					$(".wlEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
					$(".wlEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
					$(".wlEc1-3").show();
					$(".wlEc2-3").hide();
				}else{
					wlEcFunction('3');
					bbb=true;
					wlEcFalg2=true;
				}
			}
		});
		function wlEcFunction(i){
			switch (i) {
			case '1':
				$(".wlEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".wlEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wlEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wlEc1-3").show();
				$(".wlEc2-3").hide();
				$(".wlEc3-3").hide();
				break;
			case '2':
				$(".wlEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wlEc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".wlEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wlEc1-3").hide();
				$(".wlEc2-3").show();
				$(".wlEc3-3").hide();
				break;
			case '3':
				$(".wlEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wlEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wlEc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".wlEc1-3").hide();
				$(".wlEc2-3").hide();
				$(".wlEc3-3").show();
				break;
			}
		}
		//物流出口点击自助委托
		$("#wlEcRadio1").on("click",function(){
			$(".wlEcHidden").show();
			isDelegation=0;
			wlEcQqwt=true;
		});
		//物流出口点击全权委托
		$("#wlEcRadio2").on("click",function(){
			isDelegation=1;
			$(".wlEcHidden").hide();
			wlEcQqwt=false;
		});
//##################################物流出口#######################################################################
		$(".wlEc_submit").click(function(){
			$("#insertWlEcForm").validate();
			if($("#insertWlEcForm").valid()){
				if (wlEcQqwt) {
					if (wlEcFalg2==false) {
						wlEcFunction('2');
						wlEcFalg2=true;
						wlEcFalg1=true;
						aaa=true;
						return false;
					}else if (wlEcFalg1==false) {
						wlEcFunction('1');
						return false;
					}
				}else{
					if (wlEcFalg1==false) {
						wlEcFunction('2');
						wlEcFalg1=true;
						aaa=true;
						return false;
					}
				}
				var startDate=$("#startDateWlEcId").val();
				var endDate=$("#endDateWlEcId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					wlEcFunction('2');
					return false;
				}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 wlEcFunction('2');
					return false;
				 }
				submitFunction('wlEc_submit');
				var orderFromId=$("#wlEcOrderFromId").val();
				var explain=$("#explainWlEcId").val();
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var taskTypeId=$("#wlEciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var consignor=$("#wlEcconsignor").val();
				var wlEcshipper=$("#wlEcshipper").val();
				var wlEcsAddress=$("#wlEcsAddress").val();
				var wlEcdeparturePort=$("#wlEcdeparturePort").val();
				var wlEcdischargePort=$("#wlEcdischargePort").val();
				var wlEcdestinationPort=$("#wlEcdestinationPort").val();
				var wlEcxhsNo=$("#wlEcxhsNo").val();
				var wlEcconsignee=$("#wlEcconsignee").val();
				var wlEccAddress=$("#wlEccAddress").val();
				var wlEcarrivalDate=$("#wlEcarrivalDate").val();
				var wlEcpayWay=$("#wlEcpayWay").val();
				var wlEccontainer=$("#wlEccontainer").val();
				var wlEcblt=$("#wlEcblt").val();
				var wlEcnotifier=$("#wlEcnotifier").val();
				var wlEcitem=$("#wlEcitem").val();
				var wlEcweight=$("#wlEcweight").val();
				var wlEcvolume=$("#wlEcvolume").val();
				var wlEcmarks=$("#wlEcmarks").val();
				var wlEcgoodsName=$("#wlEcgoodsName").val();
				var wlEctransExpenseCharge=$("#wlEctransExpenseCharge").val();
				var wlEctransClause=$("#wlEctransClause").val();
				var wlEccontactPerson=$("#wlEccontactPerson").val();
				var wlEccpPhone=$("#wlEccpPhone").val();
				var wlEccpFax=$("#wlEccpFax").val();
				var wlEccpMail=$("#wlEccpMail").val();
				var wlEcpreFlight=$("#wlEcpreFlight").val();
				var wlEcselfFull=$("#wlEcselfFull").val();
				var wlEcspecialNotes=$("#wlEcspecialNotes").val();
				var wlEcsignature=$("#wlEcsignature").val();
				var fileArray =document.getElementsByName("filesWlEc");
				var fileArrays=array(fileArray);
						$.ajax({
							type:"post",
							url:basePath+'/sup/wuliuexcc/saveShippingExCommission',
							dataType : "json",
							data:{
								taskId:taskId,
								startDate:startDate,
								endDate:endDate,
								explain:explain,
								isDelegation:isDelegation,
								taskTypeId:taskTypeId,
								userId:userId,
								consignor:consignor,
								shipper:wlEcshipper,        
								sAddress:wlEcsAddress,
								departurePort:wlEcdeparturePort,
								dischargePort:wlEcdischargePort,
								destinationPort:wlEcdestinationPort,
								xhsNo:wlEcxhsNo,
								consignee:wlEcconsignee,
								cAddress:wlEccAddress,
								arrivalDate:wlEcarrivalDate,
								payWay:wlEcpayWay,
								container:wlEccontainer,
								blt:wlEcblt,
								notifier:wlEcnotifier,
								item:wlEcitem,
								weight:wlEcweight,
								volume:wlEcvolume,
								marks:wlEcmarks,
								goodsName:wlEcgoodsName,
								transExpenseCharge:wlEctransExpenseCharge,
								transClause:wlEctransClause,    
								contactPerson:wlEccontactPerson,
								cpPhone:wlEccpPhone,    
								cpFax:wlEccpFax,
								cpMail:wlEccpMail,
								selfFull:wlEcselfFull,
								specialNotes:wlEcspecialNotes,
								signature:wlEcsignature,
								fileArray:fileArrays.toString(),
								 orderFromId: orderFromId 
							},
							async:true,    //或false,是否异步  
							success: function (obj) {
								if(obj.statusCode == "200"){
									var taskType=obj.map.isTaskType;
									if (taskType=='1') {
										skipId=obj.map.oneTaskId;
										$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
									}
									var orderFromId=obj.map.orderFromId;
									$("#wlEcOrderFromId").val(orderFromId);
									taskTypeVal(taskType);
									parent.layer.msg('出口物流订单保存成功',{icon: 1});
									removeFunction('wlEc_submit');
									typeIdByPageShowOrHide(taskType);
									
								}
							}	
					});
			}
		});
//#########################7.物流运输###############################################################################
		var wlTropQqwt=true;
		var wlTropFalg1=false;
		var wlTropFalg2=false;
		$(".wlTrop1-2").click(function() {
			$("#insertWlTropForm").validate();
			if($("#insertWlTropForm").valid()&&aaa){
				wlTropFunction('1');
				wlTropFalg1=true;
			}

		});
		$(".wlTrop2-2").click(function() {
			$("#insertWlTropForm").validate();
			if($("#insertWlTropForm").valid()&&bbb){
				wlTropFunction('2');
				wlTropFalg2=true;
			}
		});
		$(".wlTrop3-2").click(function() {
			$("#insertWlTropForm").validate();
			if($("#insertWlTropForm").valid()&&aaa&&bbb){
				wlTropFunction('3');
			}
		});
		
		$(".wlTrop1-4").click(function() {
			$("#insertWlTropForm").validate();
			if($("#insertWlTropForm").valid()){
				wlTropFunction('2');
				aaa=true;
				wlTropFalg1=true;
			}
		});
		$(".wlTrop2-4").click(function() {
			$("#insertWlTropForm").validate();
			if($("#insertWlTropForm").valid()) {
				var startDate=$("#startDateWlTropId").val();
				var endDate=$("#endDateWlTropId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					wlTropFunction('2');
					return false;
				}
				if (isDelegation=='1') {
					$(".wlTrop1-2").attr("src", basePath+"/resource/images/icon_c1.png");
					$(".wlTrop2-2").attr("src", basePath+"/resource/images/icon_f2.png");
					$(".wlTrop1-3").show();
					$(".wlTrop2-3").hide();
				}else{
					wlTropFunction('3');
					bbb=true;
					wlTropFalg2=true;
				}
				
			} 
		});
		function wlTropFunction(i){
			switch (i) {
			case '1':
				$(".wlTrop1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".wlTrop2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wlTrop3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wlTrop1-3").show();
				$(".wlTrop2-3").hide();
				$(".wlTrop3-3").hide();
				break;
			case '2':
				$(".wlTrop1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wlTrop2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".wlTrop3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".wlTrop1-3").hide();
				$(".wlTrop2-3").show();
				$(".wlTrop3-3").hide();
				break;
			case '3':
				$(".wlTrop1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".wlTrop2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".wlTrop3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".wlTrop1-3").hide();
				$(".wlTrop2-3").hide();
				$(".wlTrop3-3").show();
				break;
			}
		}
		
		//物流出口点击自助委托
		$("#wlTropRadio1").on("click",function(){
			$(".wlTropHidden").show();
			isDelegation=0;
			wlTropQqwt=true;
		});
		//物流出口点击全权委托
		$("#wlTropRadio2").on("click",function(){
			isDelegation=1;
			$(".wlTropHidden").hide();
			wlTropQqwt=false;
		});
//##################################物流运输#######################################################################
		$(".wlTrop_submit").click(function(){
			$("#insertWlTropForm").validate();
			if($("#insertWlTropForm").valid()){
				if (wlTropQqwt) {
					if (wlTropFalg2==false) {
						wlTropFunction('2');
						wlTropFalg2=true;
						wlTropFalg1=true;
						aaa=true;
						return false;
					}else if (wlTropFalg1==false) {
						wlTropFunction('1');
						return false;
					}
				}else{
					if (wlTropFalg1==false) {
						wlTropFunction('2');
						wlTropFalg1=true;
						aaa=true;
						return false;
					}
				}
				var startDate=$("#startDateWlTropId").val();
				var endDate=$("#endDateWlTropId").val();
				var flag2=duibi(startDate,endDate,'开始时间','结束时间');
				if (flag2==false) {
					wlTropFunction('2');
					return false;
				}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 wlEcFunction('2');
					return false;
				 }
				submitFunction('wlTrop_submit');
				var orderFromId=$("#wlTcOrderFromId").val();
				var explain=$("#explainWlTropId").val();
				var userId=$("#userId").val();
				var taskId=$("#taskId").val();
				var taskTypeId=$("#wlTciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var consignor=$("#wlTropconsignor").val();
				var wlTropshipper=$("#wlTropshipper").val();
				var wlTropsAddress=$("#wlTropsAddress").val();
				var wlTropdeparturePort=$("#wlTropdeparturePort").val();
				var wlTropdischargePort=$("#wlTropdischargePort").val();
				var wlTropdestinationPort=$("#wlTropdestinationPort").val();
				var wlTropxhsNo=$("#wlTropxhsNo").val();
				var wlTropconsignee=$("#wlTropconsignee").val();
				var wlTropcAddress=$("#wlTropcAddress").val();
				var wlTroparrivalDate=$("#wlTroparrivalDate").val();
				var wlTroppayWay=$("#wlTroppayWay").val();
				var wlTropcontainer=$("#wlTropcontainer").val();
				var wlTropblt=$("#wlTropblt").val();
				var wlTropnotifier=$("#wlTropnotifier").val();
				var wlTropitem=$("#wlTropitem").val();
				var wlTropweight=$("#wlTropweight").val();
				var wlTropvolume=$("#wlTropvolume").val();
				var wlTropmarks=$("#wlTropmarks").val();
				var wlTropgoodsName=$("#wlTropgoodsName").val();
				var wlTroptransExpenseCharge=$("#wlTroptransExpenseCharge").val();
				var wlTroptransClause=$("#wlTroptransClause").val();
				var wlTropcontactPerson=$("#wlTropcontactPerson").val();
				var wlTropcpPhone=$("#wlTropcpPhone").val();
				var wlTropcpFax=$("#wlTropcpFax").val();
				var wlTropcpMail=$("#wlTropcpMail").val();
				var wlTroppreFlight=$("#wlTroppreFlight").val();
				var wlTropselfFull=$("#wlTropselfFull").val();
				var wlTropspecialNotes=$("#wlTropspecialNotes").val();
				var wlTropsignature=$("#wlTropsignature").val();
				var fileArray =document.getElementsByName("filesWlTrop");
				var fileArrays=array(fileArray);
					$.ajax({
						type:"post",
						url:basePath+'/sup/wuliutrop/saveShippingExCommission',
						dataType : "json",
						data:{
							shipper:wlTropshipper,
							startDate:startDate,
							endDate:endDate,
							explain:explain,
							isDelegation:isDelegation,
							taskId:taskId,
							taskTypeId:taskTypeId,
							userId:userId,
							sAddress:wlTropsAddress,
							consignor:consignor,
							departurePort:wlTropdeparturePort,
							dischargePort:wlTropdischargePort,
							destinationPort:wlTropdestinationPort,
							xhsNo:wlTropxhsNo,
							consignee:wlTropconsignee,
							cAddress:wlTropcAddress,
							arrivalDate:wlTroparrivalDate,
							payWay:wlTroppayWay,
							container:wlTropcontainer,
							blt:wlTropblt,
							notifier:wlTropnotifier,
							item:wlTropitem,
							weight:wlTropweight,
							volume:wlTropvolume,
							marks:wlTropmarks,
							goodsName:wlTropgoodsName,
							transExpenseCharge:wlTroptransExpenseCharge,
							transClause:wlTroptransClause,    
							contactPerson:wlTropcontactPerson,
							cpPhone:wlTropcpPhone,    
							cpFax:wlTropcpFax,
							cpMail:wlTropcpMail,
							preFlight:wlTropselfFull,
							specialNotes:wlTropspecialNotes,
							fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#wlTcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('物流运输订单保存成功',{icon: 1});
								removeFunction('wlTrop_submit');
								typeIdByPageShowOrHide(taskType);
								
							}
						}	
				});
			}
		});	
//#########################8.仓储进库###############################################################################
		var CcEcQqwt=true;
		var CcEcFalg1=false;
		var CcEcFalg2=false;	
		var CcEcFalg3=false;	
		var CcEcFalg4=false;
		var CcEcFalg5=false;
		$(".CcEc1-2").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()&&aaa){
				CcEcFunction('1');
				CcEcFalg1=true;
			}

		});
		$(".CcEc2-2").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()&&bbb){
				CcEcFunction('2');
				CcEcFalg2=true;
			}

		});
		$(".CcEc3-2").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()&&bbb&&aaa){
				CcEcFunction('3');
				CcEcFalg3=true;
			}

		});
		
		$(".CcEc4-2").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()&&bbb&&aaa&&ccc){
				CcEcFunction('4');
				CcEcFalg4=true;
			}

		});
		$(".CcEc5-2").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()&&bbb&&aaa&&ccc&&ddd){
				CcEcFunction('5');
			}
			
		});
		$(".CcEc1-4").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()){
				if (isDelegation=='1') {
					   $(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
						$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
						$(".CcEc1-3").hide();
						$(".CcEc4-3").show();
						CcEcFalg5=true;
				 }else{
					 CcEcFunction('2');
					CcEcFalg1=true;
					aaa=true;
				 }
				
			}

		});
		$(".CcEc2-4").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()){
				CcEcFunction('3');
				CcEcFalg2=true;
				bbb=true;
			} 

		});
		$(".CcEc3-4").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()){
				CcEcFunction('4');
				CcEcFalg3=true;
				ccc=true;
			} 
		});
		
		$(".CcEc4-4").click(function() {
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()){
				if (isDelegation=='1') {
					   $(".CcEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
						$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
						$(".CcEc1-3").show();
						$(".CcEc4-3").hide();
				 }else{
					 CcEcFunction('5');
					CcEcFalg4=true;
					ddd=true;
				 }
			} 
		});
		function CcEcFunction(i){
			switch (i) {
			case '1':
				$(".CcEc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".CcEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcEc1-3").show();
				$(".CcEc2-3").hide();
				$(".CcEc3-3").hide();
				$(".CcEc4-3").hide();
				$(".CcEc5-3").hide();
				break;
			case '2':
				$(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcEc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".CcEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcEc1-3").hide();
				$(".CcEc2-3").show();
				$(".CcEc3-3").hide();
				$(".CcEc4-3").hide();
				$(".CcEc5-3").hide();
				break;
			case '3':
				$(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcEc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcEc1-3").hide();
				$(".CcEc2-3").hide();
				$(".CcEc3-3").show();
				$(".CcEc4-3").hide();
				$(".CcEc5-3").hide();
				break;
			case '4':
				$(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_c4.png");
				$(".CcEc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcEc1-3").hide();
				$(".CcEc2-3").hide();
				$(".CcEc3-3").hide();
				$(".CcEc4-3").show();
				$(".CcEc5-3").hide();
				break;
			case '5':
				$(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcEc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcEc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcEc5-2").attr("src", basePath+"/resource/images/icon_c5.png");
				$(".CcEc1-3").hide();
				$(".CcEc2-3").hide();
				$(".CcEc3-3").hide();
				$(".CcEc4-3").hide();
				$(".CcEc5-3").show();
				break;
			}
		}
		//点击自助委托
		$("#ccEcRadio1").on("click",function(){
			$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
			$(".ccEcHidden").show();
			isDelegation=0;
			CcEcQqwt=true;
			 CcEcFalg1=false;
			 CcEcFalg2=false;	
			 CcEcFalg3=false;	
			 CcEcFalg4=false;
			 CcEcFalg5=false;
		});
		//点击全权委托
		$("#ccEcRadio2").on("click",function(){
			$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
			isDelegation=1;
			$(".ccEcHidden").hide();
			CcEcQqwt=false;
			CcEcFalg1=false;
			 CcEcFalg2=false;	
			 CcEcFalg3=false;	
			 CcEcFalg4=false;
			 CcEcFalg5=false;
		});
//####################仓储出库##############################################################################	
		$(".CcEc_submit").click(function(){
			$("#insertCcEcForm").validate();
			if($("#insertCcEcForm").valid()){
				var startDate=$("#startDateCcEcId").val();
				var endDate=$("#endDateCcEcId").val();
				if (CcEcQqwt) {
					  if (CcEcFalg1==false) {
							CcEcFunction('2');
							CcEcFalg1=true;
							return false;
						}else if (CcEcFalg2==false) {
							CcEcFunction('3');
							aaa=true;
							CcEcFalg2=true;
							return false;
						}else if (CcEcFalg3==false) {
							CcEcFunction('4');
							aaa=true;
							bbb=true;
							CcEcFalg3=true;
							CcEcFalg4=true;
							return false;
						}else if (CcEcFalg4==false) {
							CcEcFunction('5');
							aaa=true;
							bbb=true;
							ccc=true;
							CcEcFalg4=true;
							return false;
						}
					  var flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							CcEcFunction('4');
							return false;
						}
					}else{
						 if (CcEcFalg5==false) {
							 $(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
								$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
								$(".CcEc1-3").hide();
								$(".CcEc4-3").show();
								aaa=true;
								CcEcFalg5=true;
								return false;
						}
						 var flag2=duibi(startDate,endDate,'开始时间','结束时间');
							if (flag2==false) {
								$(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
								$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
								$(".CcEc1-3").hide();
								$(".CcEc4-3").show();
								return false;
							}
					}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 $(".CcEc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
						$(".CcEc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
						$(".CcEc1-3").hide();
						$(".CcEc4-3").show();
					return false;
				 }
				submitFunction('CcEc_submit');
				var orderFromId=$("#ccEcOrderFromId").val();
				var explain=$("#explainCcEcId").val();
				var userId=$("#userId").val();
				var taskTypeId=$("#ccEciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var taskId=$("#taskId").val();
				var purOrderNo=$("#purOrderNoCcEcId").val();
				var buyer=$("#buyersCcEcId").val();
				var bAddress=$("#bAddressCcEcId").val();
				var issueDate=$("#issueDateCcEcId").val();
				var project=$("#projectCcEcId").val();
				var bTelphone=$("#bTelphoneCcEcId").val();
				var bFax=$("#bFaxCcEcId").val();
				var supplier=$("#supplierCcEcId").val();
				var supplierNo=$("#supplierNoCcEcId").val();
				var contactPerson=$("#contactPersonCcEcId").val();
				var cpAddress=$("#cpAddressCcEcId").val();
				var cpTelephone=$("#cpTelephoneCcEcId").val();
				var cpFax=$("#cpFaxCcEcId").val();
				var consignor=$("#consignorCcEcId").val();
				var contractTerm=$("#contractTermCcEcId").val();
				var deliveryDate=$("#deliveryDateCcEcId").val();
				var instrDestination=$("#instrDestinationCcEcId").val();
				var paymentTerm=$("#paymentTermCcEcId").val();
				var tradeCondition=$("#tradeConditionCcEcId").val();
				var refDocuments=$("#refDocumentsCcEcId").val();
				var pcl=$("#pclCcEcId").val();
				var currency=$("#currencyCcEcId").val();
				var toolingCost=$("#toolingCostCcEcId").val();
				var payWay=$("#payWayCcEcId").val();
				var toolingLife=$("#toolingLifeCcEcId").val();
				var shareAmount=$("#shareAmountCcEcId").val();
				var sharePerPrice=$("#sharePerPriceCcEcId").val();
				var comments=$("#commentsCcEcId").val();
				var fileArray =document.getElementsByName("filesCcEc");
				var fileArrays=array(fileArray);
				var detailNoArray = [];
				$('.ccEcDetailNo').each(function(i,e){
					detailNoArray[i]=e.value;
					
				});
				
				var descriptionArray = [];
				$('.ccEcDescription').each(function(i,e){
					descriptionArray[i]=e.value;
					
				});
				
				var unitArray = [];
				$('.ccEcUnit').each(function(i,e){
					unitArray[i]=e.value;
					
				});
				var priceVilidityPeriodArray = [];
				$('.ccEcPriceVilidityPeriod').each(function(i,e){
					priceVilidityPeriodArray[i]=e.value;
					
				});
				
				var perPriceArray = [];
				$('.ccEcPerPrice').each(function(i,e){
					perPriceArray[i]=e.value;
					
				});
				
				var remarkArray = [];
				$('.ccEcRemark').each(function(i,e){
					remarkArray[i]=e.value;
					
				});
					$.ajax({
						type:"post",
						url:basePath+'/sup/order/ExportOrderFrom/saveExportOrderFrom',
						dataType : "json",
						data:{
							taskTypeId:taskTypeId,
							startDate:startDate,
							endDate:endDate,
							explain:explain,
							isDelegation:isDelegation,
							 taskId:taskId,
							 userId:userId,
							 purOrderNo:purOrderNo,
							 buyer:buyer,
							 bAddress:bAddress,
							 issueDate:issueDate,
							 project:project,
							 bTelphone:bTelphone,
							 bFax:bFax,
							 consignor:consignor,
							 supplier:supplier,
							 supplierNo:supplierNo,
							 contactPerson:contactPerson,
							 cpAddress:cpAddress,
							 cpTelephone:cpTelephone,
							 cpFax:cpFax,
							 contractTerm:contractTerm,
							 deliveryDate:deliveryDate,
							 instrDestination:instrDestination,
							 paymentTerm:paymentTerm,
							 tradeCondition:tradeCondition,
							 refDocuments:refDocuments,
							 pcl:pcl,
							 currency:currency,
							 toolingCost:toolingCost,
							 payWay:payWay,
							 toolingLife:toolingLife,
							 shareAmount:shareAmount,
							 sharePerPrice:sharePerPrice,
							 comments:comments,
							 detailNos:JSON.stringify(detailNoArray).toString(),
							 descriptions:JSON.stringify(unitArray).toString(),
							 units:JSON.stringify(priceVilidityPeriodArray).toString(),
							 priceVilidityPeriods:JSON.stringify(perPriceArray).toString(),
							 perPrices:JSON.stringify(perPriceArray).toString(),
							 remarks:JSON.stringify(remarkArray).toString(),
							 fileArray:fileArrays.toString(),
							 orderFromId: orderFromId 
						},
						async:true,    //或false,是否异步  
						success: function (obj) {
							if(obj.statusCode == "200"){
								var taskType=obj.map.isTaskType;
								if (taskType=='1') {
									skipId=obj.map.oneTaskId;
									$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
								}
								var orderFromId=obj.map.orderFromId;
								$("#ccEcOrderFromId").val(orderFromId);
								taskTypeVal(taskType);
								parent.layer.msg('仓储出库订单保存成功',{icon: 1});
								removeFunction('CcEc_submit');
								typeIdByPageShowOrHide(taskType);
							}
						}	
				});
			}
		});
//#########################9.仓储出库###############################################################################
		var CcIcQqwt=true;
		var CcIcFalg1=false;
		var CcIcFalg2=false;	
		var CcIcFalg3=false;	
		var CcIcFalg4=false;
		var CcIcFalg5=false;
		$(".CcIc1-2").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()&&aaa){
				CcIcFunction('1');
				CcIcFalg1=true;
			}

		});
		$(".CcIc2-2").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()&&bbb){
				CcIcFunction('2');
				CcIcFalg2=true;
			}

		});
		$(".CcIc3-2").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()&&bbb&&aaa){
				CcIcFunction('3');
				CcIcFalg3=true;
			}

		});
		
		$(".CcIc4-2").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()&&bbb&&aaa&&ccc){
				CcIcFunction('4');
				CcIcFalg4=true;
			}

		});
		$(".CcIc5-2").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()&&bbb&&aaa&&ccc&&ddd){
				CcIcFunction('5');
			}
			
		});
		$(".CcIc1-4").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()){
				if (isDelegation=='1') {
					   $(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
						$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
						$(".CcIc1-3").hide();
						$(".CcIc4-3").show();
						CcIcFalg5=false;
				 }else{
					CcIcFunction('2');
					aaa=true;
					CcIcFalg1=true;
				 }
			}

		});
		$(".CcIc2-4").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()){
				CcIcFunction('3');
				bbb=true;
				CcIcFalg2=true;
			} 

		});
		$(".CcIc3-4").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()){
				CcIcFunction('4');
				ccc=true;
				CcIcFalg3=true;
			} 
		});
		
		$(".CcIc4-4").click(function() {
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()){
				if (isDelegation=='1') {
					   $(".CcIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
						$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
						$(".CcIc1-3").show();
						$(".CcIc4-3").hide();
				 }else{
					 CcIcFunction('5');
						ddd=true;
						CcIcFalg4=true;
				 }
			} 
		});
		function CcIcFunction(i){
			switch (i) {
			case '1':
				$(".CcIc1-2").attr("src", basePath+"/resource/images/icon_c1.png");
				$(".CcIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcIc1-3").show();
				$(".CcIc2-3").hide();
				$(".CcIc3-3").hide();
				$(".CcIc4-3").hide();
				$(".CcIc5-3").hide();
				break;
			case '2':
				$(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcIc2-2").attr("src", basePath+"/resource/images/icon_c2.png");
				$(".CcIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcIc1-3").hide();
				$(".CcIc2-3").show();
				$(".CcIc3-3").hide();
				$(".CcIc4-3").hide();
				$(".CcIc5-3").hide();
				break;
			case '3':
				$(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcIc3-2").attr("src", basePath+"/resource/images/icon_c3.png");
				$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcIc1-3").hide();
				$(".CcIc2-3").hide();
				$(".CcIc3-3").show();
				$(".CcIc4-3").hide();
				$(".CcIc5-3").hide();
				break;
			case '4':
				$(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_c4.png");
				$(".CcIc5-2").attr("src", basePath+"/resource/images/icon_f5.png");
				$(".CcIc1-3").hide();
				$(".CcIc2-3").hide();
				$(".CcIc3-3").hide();
				$(".CcIc4-3").show();
				$(".CcIc5-3").hide();
				break;
			case '5':
				$(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
				$(".CcIc2-2").attr("src", basePath+"/resource/images/icon_f2.png");
				$(".CcIc3-2").attr("src", basePath+"/resource/images/icon_f3.png");
				$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
				$(".CcIc5-2").attr("src", basePath+"/resource/images/icon_c5.png");
				$(".CcIc1-3").hide();
				$(".CcIc2-3").hide();
				$(".CcIc3-3").hide();
				$(".CcIc4-3").hide();
				$(".CcIc5-3").show();
				break;
			}
		}
		//点击自助委托
		$("#ccIcRadio1").on("click",function(){
			$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f4.png");
			$(".ccIcHidden").show();
			isDelegation=0;
			CcIcQqwt=true;
			 CcIcFalg1=false;
			 CcIcFalg2=false;	
			 CcIcFalg3=false;	
			 CcIcFalg4=false;
			 CcIcFalg5=false;
			 CcIcFunction('1');
		});
		//点击全权委托
		$("#ccIcRadio2").on("click",function(){
			CcIcFunction('1');
			$(".ccIcHidden").hide();
			$(".CcIc4-2").attr("src", basePath+"/resource/images/icon_f2.png");
			isDelegation=1;
			CcIcQqwt=false;
			CcIcFalg1=false;
			 CcIcFalg2=false;	
			 CcIcFalg3=false;	
			 CcIcFalg4=false;
			 CcIcFalg5=false;
		});
		//############################仓储进库######################################################################	
		$(".CcIc_submit").click(function(){
			$("#insertCcIcForm").validate();
			if($("#insertCcIcForm").valid()){
				var startDate=$("#startDateCcIcId").val();
				var endDate=$("#endDateCcIcId").val();
				if (CcIcQqwt) {
					  if (CcIcFalg1==false) {
							CcIcFunction('2');
							CcIcFalg1=true;
							return false;
						}else if (CcIcFalg2==false) {
							CcIcFunction('3');
							aaa=true;
							CcIcFalg2=true;
							return false;
						}else if (CcIcFalg3==false) {
							CcIcFunction('4');
							aaa=true;
							bbb=true;
							CcIcFalg3=true;
							CcIcFalg4=true;
							return false;
						}else if (CcIcFalg4==false) {
							CcIcFunction('5');
							aaa=true;
							bbb=true;
							ccc=true;
							CcIcFalg4=true;
							return false;
						}
					  var flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							CcIcFunction('4');
							return false;
						}
					}else{
						if (CcIcFalg5==false) {
							 $(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
							 $(".CcIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
							 $(".CcIc1-3").hide();
							 $(".CcIc4-3").show();
							 aaa=true;
							 CcIcFalg5=true;
							 return false;
						}
						var flag2=duibi(startDate,endDate,'开始时间','结束时间');
						if (flag2==false) {
							 $(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
							 $(".CcIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
							 $(".CcIc1-3").hide();
							 $(".CcIc4-3").show();
							return false;
						}
					}
				 var ks=startDateTime(startDate);
				 if (ks==false) {
					 $(".CcIc1-2").attr("src", basePath+"/resource/images/icon_f1.png");
					 $(".CcIc4-2").attr("src", basePath+"/resource/images/icon_c2.png");
					 $(".CcIc1-3").hide();
					 $(".CcIc4-3").show();
					return false;
				 }
				submitFunction('CcIc_submit');
				var orderFromId=$("#ccIcOrderFromId").val();
				var explain=$("#explainCcIcId").val();
				var userId=$("#userId").val();
				var taskTypeId=$("#ccIciniPage").val();
				if (taskTypeId=='1') {
					taskTypeId=$("#iniPage").val();
				}
				var taskId=$("#taskId").val();
				var purOrderNo=$("#purOrderNoCcIcId").val();
				var buyer=$("#buyerCcIcId").val();
				var bAddress=$("#bAddressCcIcId").val();
				var issueDate=$("#issueDateCcIcId").val();
				var project=$("#projectCcIcId").val();
				var bTelphone=$("#bTelphoneCcIcId").val();
				var bFax=$("#bFaxCcIcId").val();
				var consignor=$("#consignorCcIcId").val();
				var supplier=$("#supplierCcIcId").val();
				var supplierNo=$("#supplierNoCcIcId").val();
				var contactPerson=$("#contactPersonCcIcId").val();
				var cpAddress=$("#cpAddressCcIcId").val();
				var cpTelephone=$("#cpTelephoneCcIcId").val();
				var cpFax=$("#cpFaxCcIcId").val();
				var contractTerm=$("#contractTermCcIcId").val();
				var deliveryDate=$("#deliveryDateCcIcId").val();
				var instrDestination=$("#instrDestinationCcIcId").val();
				var paymentTerm=$("#paymentTermCcIcId").val();
				var tradeCondition=$("#tradeConditionCcIcId").val();
				var refDocuments=$("#refDocumentsCcIcId").val();
				var pcl=$("#pclCcIcId").val();
				var currency=$("#currencyCcIcId").val();
				var toolingCost=$("#toolingCostCcIcId").val();
				var payWay=$("#payWayCcIcId").val();
				var toolingLife=$("#toolingLifeCcIcId").val();
				var shareAmount=$("#shareAmountCcIcId").val();
				var sharePerPrice=$("#sharePerPriceCcIcId").val();
				var comments=$("#commentsCcIcId").val();
				var fileArray =document.getElementsByName("filesCcIc");
				var fileArrays=array(fileArray);
					var detailNoArray = [];
					$('.detailNo').each(function(i,e){
						detailNoArray[i]=e.value;
						
					});
					
					var descriptionArray = [];
					$('.description').each(function(i,e){
						descriptionArray[i]=e.value;
						
					});
					
					var unitArray = [];
					$('.unit').each(function(i,e){
						unitArray[i]=e.value;
						
					});
					var priceVilidityPeriodArray = [];
					$('.priceVilidityPeriod').each(function(i,e){
						priceVilidityPeriodArray[i]=e.value;
						
					});
					
					var perPriceArray = [];
					$('.perPrice').each(function(i,e){
						perPriceArray[i]=e.value;
						
					});
					
					var remarkArray = [];
					$('.remark').each(function(i,e){
						remarkArray[i]=e.value;
						
					});
						$.ajax({
							type:"post",
							url:basePath+'/sup/order/OrderFrom/saveOrderFrom',
							dataType : "json",
							data:{
								taskTypeId:taskTypeId,
								startDate:startDate,
								endDate:endDate,
								explain:explain,
								isDelegation:isDelegation,
								 taskId:taskId,
								 userId:userId,
								 purOrderNo:purOrderNo,
								 buyer:buyer,
								 bAddress:bAddress,
								 issueDate:issueDate,
								 project:project,
								 bTelphone:bTelphone,
								 bFax:bFax,
								 consignor:consignor,
								 supplier:supplier,
								 supplierNo:supplierNo,
								 contactPerson:contactPerson,
								 cpAddress:cpAddress,
								 cpTelephone:cpTelephone,
								 cpFax:cpFax,
								 contractTerm:contractTerm,
								 deliveryDate:deliveryDate,
								 instrDestination:instrDestination,
								 paymentTerm:paymentTerm,
								 tradeCondition:tradeCondition,
								 refDocuments:refDocuments,
								 pcl:pcl,
								 currency:currency,
								 toolingCost:toolingCost,
								 payWay:payWay,
								 toolingLife:toolingLife,
								 shareAmount:shareAmount,
								 sharePerPrice:sharePerPrice,
								 comments:comments,
								 detailNos:JSON.stringify(detailNoArray).toString(),
								 descriptions:JSON.stringify(unitArray).toString(),
								 units:JSON.stringify(priceVilidityPeriodArray).toString(),
								 priceVilidityPeriods:JSON.stringify(perPriceArray).toString(),
								 perPrices:JSON.stringify(perPriceArray).toString(),
								 remarks:JSON.stringify(remarkArray).toString(),
								 fileArray:fileArrays.toString(),
								 orderFromId: orderFromId 
							},
							async:true,    //或false,是否异步  
							success: function (obj) {
								if(obj.statusCode == "200"){
									var taskType=obj.map.isTaskType;
									if (taskType=='1') {
										skipId=obj.map.oneTaskId;
										$('#myModal').modal({show:true,keyboard: false,backdrop:'static'});
									}
									var orderFromId=obj.map.orderFromId;
									$("#ccIcOrderFromId").val(orderFromId);
									taskTypeVal(taskType);
									parent.layer.msg('仓储进库订单保存成功',{icon: 1});
									removeFunction('CcIc_submit');
									typeIdByPageShowOrHide(taskType);
								}
							}	
					});
			}
		});
		function typeIdHide(typeId){
			switch (typeId) {
			case '7994F33B-798E-4495-885B-FB8F2F39B809'://进口报关
				$(".bgIc").css('display','block');
				break;
			case 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C'://出口报关
				$(".bgEc").css('display','block');
				break;
			case '8EF423F7-8AF0-43F2-B03E-2F6E42276FE5'://外贸进口
				$(".wmIc").css('display','block');
				break;
			case 'E8EA1196-D803-4040-B309-C65965A4F142'://外贸出口
				$(".wmEc").css('display','block');
				break;
			case 'B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7'://进口物流
				$(".wlIc").css('display','block');
				break;
			case '0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE'://出口物流
				$(".wlEc").css('display','block');
				break;
			case 'CF734401-5F26-4A3B-82B3-1B7A6DAFE35D'://物流运输
				$(".wlTrop").css('display','block');
				break;
			//仓储进库
			case 'D8994C5C-533C-42E2-9BFD-46E5639844D2'://仓储进库
				$(".CcIc").hide();
				break;
			case 'D33A899D-C46B-438A-90BC-C2F395E8AFCE'://仓储出口
				$(".CcEc").hide();
				break;
			}
			
		}

	function typeIdByPageShowOrHide(typeId){
		switch (typeId) {
		case '7994F33B-798E-4495-885B-FB8F2F39B809'://进口报关
			$(".bgIc1-3").show();
			$(".bgIc2-3").hide();
			$(".bgIc3-3").hide();
			$(".BgIcClass").addClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active"); 
			
			$(".aafff").removeClass("active");
			$(".aafff").removeClass("in"); 
			$(".bgIc").addClass("in"); 
			$(".bgIc").addClass("active"); 
			break;
		case 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C'://出口报关
			$(".bgEc1-3").show();
			$(".bgEc2-3").hide();
			$(".bgEc3-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").addClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active"); 
			
			$(".aafff").removeClass("active"); 
			$(".bgEc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".bgEc").addClass("in"); 
			break;
		case '8EF423F7-8AF0-43F2-B03E-2F6E42276FE5'://外贸进口
			$(".wmIc1-3").show();
			$(".wmIc2-3").hide();
			$(".wmIc3-3").hide();
			$(".wmIc4-3").hide();
			$(".wmIc5-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").addClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active");
			$(".aafff").removeClass("active"); 
			$(".wmIc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".wmIc").addClass("in"); 
			break;
		
		case 'E8EA1196-D803-4040-B309-C65965A4F142'://外贸出口
			
			$(".wmEc1-3").show();
			$(".wmEc2-3").hide();
			$(".wmEc3-3").hide();
			$(".wmEc4-3").hide();
			$(".wmEc5-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").addClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active");
			$(".aafff").removeClass("active"); 
			$(".wmEc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".wmEc").addClass("in"); 
			break;
		case 'B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7'://进口物流
			
			$(".wlIc1-3").show();
			$(".wlIc2-3").hide();
			$(".wlIc3-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").addClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active");
			$(".aafff").removeClass("active"); 
			$(".wlIc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".wlIc").addClass("in"); 
			break;
		case '0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE'://出口物流
			
			$(".wlEc1-3").show();
			$(".wlEc2-3").hide();
			$(".wlEc3-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").addClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active");
			$(".aafff").removeClass("active"); 
			$(".wlEc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".wlEc").addClass("in"); 
			break;
		case 'CF734401-5F26-4A3B-82B3-1B7A6DAFE35D'://物流运输
			
			$(".wlTrop1-3").show();
			$(".wlTrop2-3").hide();
			$(".wlTrop3-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").addClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").removeClass("active");
			$(".aafff").removeClass("active"); 
			$(".wlTrop").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".wlTrop").addClass("in"); 
			break;
		//仓储进库
		case 'D8994C5C-533C-42E2-9BFD-46E5639844D2'://仓储进库
			
			$(".CcIc1-3").show();
			$(".CcIc2-3").hide();
			$(".CcIc3-3").hide();
			$(".CcIc4-3").hide();
			$(".CcIc5-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").addClass("active"); 
			$(".CcEcClass").removeClass("active");
			$(".aafff").removeClass("active"); 
			$(".CcIc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".CcIc").addClass("in"); 
			break;
		case 'D33A899D-C46B-438A-90BC-C2F395E8AFCE'://仓储出口
			$(".CcEc1-3").show();
			$(".CcEc2-3").hide();
			$(".CcEc3-3").hide();
			$(".CcEc4-3").hide();
			$(".CcEc5-3").hide();
			$(".BgIcClass").removeClass("active"); 
			$(".BgEcClass").removeClass("active"); 
			$(".WmIcClass").removeClass("active"); 
			$(".WmEcClass").removeClass("active"); 
			$(".WlIcClass").removeClass("active"); 
			$(".WlEcClass").removeClass("active"); 
			$(".TropClass").removeClass("active"); 
			$(".CcIcClass").removeClass("active"); 
			$(".CcEcClass").addClass("active");
			$(".aafff").removeClass("active"); 
			$(".CcEc").addClass("active"); 
			$(".aafff").removeClass("in"); 
			$(".CcEc").addClass("in"); 
			break;
		}
		
	}
	function addClassctiveActive(typeId){
		switch (typeId) {
		case '7994F33B-798E-4495-885B-FB8F2F39B809'://进口报关
			
			$(".bgIc").addClass("active");
			
			break;
		case 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C'://出口报关
			$(".bgEc").addClass("active");
			break;
		case '8EF423F7-8AF0-43F2-B03E-2F6E42276FE5'://外贸进口
			$(".wmIc").addClass("active");
			
			break;
		
		case 'E8EA1196-D803-4040-B309-C65965A4F142'://外贸出口
			$(".wmEc").addClass("active");
			break;
		case 'B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7'://进口物流
			$(".wlIc").addClass("active");
			break;
		case '0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE'://出口物流
			$(".wlEc").addClass("active");
			break;
		case 'CF734401-5F26-4A3B-82B3-1B7A6DAFE35D'://物流运输
			$(".wlTrop").addClass("active");
			break;
		//仓储进库
		case 'D8994C5C-533C-42E2-9BFD-46E5639844D2'://仓储进库
			$(".CcIc").addClass("active");
			break;
		case 'D33A899D-C46B-438A-90BC-C2F395E8AFCE'://仓储出口
			$(".CcEc").addClass("active");
			break;
		}
	}
	function taskTypeVal(typeId){
		switch (typeId) {
		case '7994F33B-798E-4495-885B-FB8F2F39B809'://进口报关
			$("#bgIciniPage").val(typeId);
			break;
		case 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C'://出口报关
			$("#bgEciniPage").val(typeId);
			break;
		case '8EF423F7-8AF0-43F2-B03E-2F6E42276FE5'://外贸进口
			$("#wmIciniPage").val(typeId);
			break;
		case 'E8EA1196-D803-4040-B309-C65965A4F142'://外贸出口
			$("#wmEciniPage").val(typeId);
			break;
		case 'B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7'://进口物流
			$("#wlIciniPage").val(typeId);
			break;
		case '0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE'://出口物流
			$("#wlEciniPage").val(typeId);
			break;
		case 'CF734401-5F26-4A3B-82B3-1B7A6DAFE35D'://物流运输
			$("#wlTciniPage").val(typeId);
			break;
		case 'D8994C5C-533C-42E2-9BFD-46E5639844D2'://仓储进库
			$("#ccIciniPage").val(typeId);
			break;
		case 'D33A899D-C46B-438A-90BC-C2F395E8AFCE'://仓储出口
			$("#ccEciniPage").val(typeId);
			break;
		}
	}

	function array(fileArray){
		var arr=[];
		for (var i = 0; i < fileArray.length; i++) {
			arr[i]=fileArray[i].value;
		}
		return arr;
	}
	
	//根据任务类型Id跳转到对应的订单展示页面
	function getTaskTypeIdToSkipPage(skipId){
		var url='';
		switch (skipId) {
		case '7994F33B-798E-4495-885B-FB8F2F39B809'://进口报关
			url='/sup/supcompany/order/toFlowImccPage/1';
			break;
		case 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C'://出口报关
			url='/sup/supcompany/order/toBgFlowExccOrderPage/1';
			break;
		case '8EF423F7-8AF0-43F2-B03E-2F6E42276FE5'://外贸进口
			url='/sup/supcompany/order/toWmFlowImccOrderPage/1';
			break;
		
		case 'E8EA1196-D803-4040-B309-C65965A4F142'://外贸出口
			url='/sup/supcompany/order/toWmFlowExccOrderPage/1';
			break;
		case 'B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7'://进口物流
			url='/sup/supcompany/order/toWlFlowImccOrder/1';
			break;
		case '0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE'://出口物流
			url='/sup/supcompany/order/toWlFlowExccOrder/1';
			break;
		case 'CF734401-5F26-4A3B-82B3-1B7A6DAFE35D'://物流运输
			url='/sup/supcompany/order/toWlTransportOrder/1';
			break;
		//仓储进库
		case 'D8994C5C-533C-42E2-9BFD-46E5639844D2'://仓储进库
			url='/sup/supcompany/order/toCCFlowImccOrder/1';
			break;
		case 'D33A899D-C46B-438A-90BC-C2F395E8AFCE'://仓储出口
			url='/sup/supcompany/order/toCCFlowExccOrder/1';
			break;
		}
		return url;
	}
	
	//用于ajax提交订单对按钮进行处理
	function submitFunction(className){
		$("."+className).attr("disabled", true).text("处理中...");
	}
	function removeFunction(className){
		$("."+className).attr("disabled", false).text("提交订单");
	}
	function wmIcaddTr(tab, row, trHtml) {
		//获取table最后一行 $("#tab tr:last")
		//获取table第一行 $("#tab tr").eq(0)
		//获取table倒数第二行 $("#tab tr").eq(-2)
		var $tr = $("#" + tab + " tr").eq(row);
		if ($tr.size() == 0) {
			parent.layer.msg('指定的table 行数不存在！', {
				icon : 1
			});
			return;
		}
		$tr.after(trHtml);
	}

	function wmIcdelTr(ckb) {
		//获取选中的复选框，然后循环遍历删除
		var ckbs = $("input[name=" + ckb + "]:checked");
		if (ckbs.size() == 0) {
			parent.layer.msg('要删除指定行，需选中要删除的行！', {
				icon : 1
			});
			return;
		}
		ckbs.each(function() {
			$(this).parent().parent().remove();
		});
	}

	/**
	 * 全选
	 * 
	 * allCkb 全选复选框的id
	 * items 复选框的name
	 */
	function wmIcallCheck(allCkb, items) {
		$("#" + allCkb).click(function() {
			$('[name=' + items + ']:checkbox').attr("checked", this.checked);
		});
	}
	////////添加一行、删除一行测试方法///////
	$(function() {
		//全选
		allCheck("allCkb", "ckb");
	});
	var wmIcnum = 1;
	function wmIcaddTr2(tab, row) {
		var trHtml = '<tr align="center">'
				+ '<td><input type="checkbox" name="ckb"/></td>'
				+ '<td><input class="commodityNameId"  type="hidden" name="commodityNameId" id="commodityNameId_'+wmIcnum+'"><input class="commodityName" id="commodityName_'+wmIcnum+'"  type="text"  name="commodityName" readonly="readonly" onclick="randomDataWmIcOnclick(\'_'+wmIcnum+'\')" placeholder="点击获取商品信息"/>'
				+ '<td><input type="text" class="models" id="models_'+wmIcnum+'" name="models" readonly="readonly"/>'
				+ '<td><input type="text" class="quantity form-control"  name="quantity'+wmIcnum+'"  number="true" id="quantity_'+wmIcnum+'" onkeyup="quantityKeyup(\''+wmIcnum+'\')" onblur="quantityBlur(\''+wmIcnum+'\')" number="true" required=""  />'
				+ '<td><input type="text" class="unitPrice" id="unitPrice_'+wmIcnum+'" name="unitPrice" readonly="readonly"/>'
				+ '<td><input type="text" class="totalAmount" name="totalAmount" id="totalAmount_'+wmIcnum+'" readonly="readonly"/>'
				+ '</tr>';
		wmIcaddTr(tab, row, trHtml);
		wmIcnum++;
	}
	function wmIcdelTr2() {
		wmIcdelTr('ckb');
	}
//========================外贸出口===============================================================	
	function wmEcaddTr(tab, row, trHtml) {
		//获取table最后一行 $("#tab tr:last")
		//获取table第一行 $("#tab tr").eq(0)
		//获取table倒数第二行 $("#tab tr").eq(-2)
		var $tr = $("#" + tab + " tr").eq(row);
		if ($tr.size() == 0) {
			parent.layer.msg('指定的table 行数不存在！', {
				icon : 1
			});
			return;
		}
		$tr.after(trHtml);
	}

	function wmEcdelTr(ckb) {
		//获取选中的复选框，然后循环遍历删除
		var ckbs = $("input[name=" + ckb + "]:checked");
		if (ckbs.size() == 0) {
			parent.layer.msg('要删除指定行，需选中要删除的行！', {
				icon : 1
			});
			return;
		}
		ckbs.each(function() {
			$(this).parent().parent().remove();
		});
	}

	/**
	 * 全选
	 * 
	 * allCkb 全选复选框的id
	 * items 复选框的name
	 */
	function wmEcallCheck(allCkb, items) {
		$("#" + allCkb).click(function() {
			$('[name=' + items + ']:checkbox').attr("checked", this.checked);
		});
	}
	////////添加一行、删除一行测试方法///////
	$(function() {
		//全选
		allCheck("allCkb", "ckb");
	});
	var wmEcnum = 1;
	function wmEcaddTr2(tab, row) {
		var trHtml = '<tr align="center">'
				+ '<td><input type="checkbox" name="ckb"/></td>'
				+ '<td><input class="commodityIdWmEc"  type="hidden" name="commodityNameId" id="commodityNameWmEcId_'+wmEcnum+'"><input class="commodityNameWmEc" id="commodityNameWmEc_'+wmEcnum+'"  type="text"  name="commodityName" readonly="readonly" onclick="randomDataWmEcOnclick(\'_'+wmEcnum+'\')" placeholder="点击获取商品信息"/>'
				+ '<td><input type="text" class="modelsWmEc" id="modelsWmEc_'+wmEcnum+'" name="models" readonly="readonly"/>'
				+ '<td><input type="text" class="quantityWmEc form-control"  number="true" name="quantity'+wmEcnum+'" id="quantityWmEc_'+wmEcnum+'" onkeyup="quantityWmEcKeyup(\''+wmEcnum+'\')" onblur="quantityBlurWmEc(\''+wmEcnum+'\')" number="true" required=""  />'
				+ '<td><input type="text" class="unitPriceWmEc" id="unitPriceWmEc_'+wmEcnum+'" name="unitPrice" readonly="readonly"/>'
				+ '<td><input type="text" class="totalAmountWmEc" name="totalAmount" id="totalAmountWmEc_'+wmEcnum+'" readonly="readonly"/>'
				+ '</tr>';
		wmEcaddTr(tab, row, trHtml);
		wmEcnum++;
	}
	function wmEcdelTr2() {
		wmEcdelTr('ckb');
	}
	
	//外贸进口
	function quantityKeyup(wmIcnum){
		var quantity=$("#quantity_"+wmIcnum).val();
		var unitPrice=$("#unitPrice_"+wmIcnum).val();
		$("#totalAmount_"+wmIcnum).val(Number(quantity)*Number(unitPrice));
	}
	function quantityBlur(wmIcnum){
		var quantity=$("#quantity_"+wmIcnum).val();
		var unitPrice=$("#unitPrice_"+wmIcnum).val();
		$("#totalAmount_"+wmIcnum).val(Number(quantity)*Number(unitPrice));
	}
	//外贸出口
	function quantityWmEcKeyup(wmEcnum){
		var quantity=$("#quantityWmEc_"+wmEcnum).val();
		var unitPrice=$("#unitPriceWmEc_"+wmEcnum).val();
		$("#totalAmountWmEc_"+wmEcnum).val(Number(quantity)*Number(unitPrice));
	}
	function quantityBlurWmEc(wmEcnum){
		var quantity=$("#quantityWmEc_"+wmEcnum).val();
		var unitPrice=$("#unitPriceWmEc_"+wmEcnum).val();
		$("#totalAmountWmEc_"+wmEcnum).val(Number(quantity)*Number(unitPrice));
	}	
	
	
	
	
	
	
	
	
	
//=====================仓储进库======================================================

	function ccIcaddTr(tab, row, trHtml) {
		//获取table最后一行 $("#tab tr:last")
		//获取table第一行 $("#tab tr").eq(0)
		//获取table倒数第二行 $("#tab tr").eq(-2)
		var $tr = $("#" + tab + " tr").eq(row);
		if ($tr.size() == 0) {
		parent.layer.msg('指定的table 行数不存在！',{icon: 1});
			return;
		}
		$tr.after(trHtml);
	}

	function ccIcdelTr(ckb) {
		//获取选中的复选框，然后循环遍历删除
		var ckbs = $("input[name=" + ckb + "]:checked");
		if (ckbs.size() == 0) {
			parent.layer.msg('要删除指定行，需选中要删除的行！',{icon: 1});
			return;
		}
		ckbs.each(function() {
			$(this).parent().parent().remove();
		});
	}

	
	var ccIcNum=1;
	function ccIcaddTr2(tab, row) {
		var trHtml = '<tr align="center">'+
						'<td><input type="checkbox" name="ckb"/></td>'+
						'<td><input type="hidden" name="ids'+ccIcNum+'" value="1"  class="form-control ids"><input type="text"  name="description'+ccIcNum+'" class="form-control description input"  required=""   ></td>'+
						'<td><input type="text"  name="unit'+ccIcNum+'" class="form-control input unit"  required=""   ></td>'+
						'<td><input type="text"  name="priceVilidityPeriod'+ccIcNum+'" class="form-control input priceVilidityPeriod"  required=""   ></td>'+
						'<td><input type="text"  name="perPrice'+ccIcNum+'" class="form-control input perPrice" number="true" required=""   ></td>'+
						'<td><input type="text"  name="remark'+ccIcNum+'" class="form-control input remark"  required=""   ></td>'
					+'</tr>';
		ccIcaddTr(tab, row, trHtml);
		ccIcNum++;
	}
	
	function ccIcdelTr2() {
		ccIcdelTr('ckb');
	}
	//=====================仓储出库======================================================

	function ccEcaddTr(tab, row, trHtml) {
		//获取table最后一行 $("#tab tr:last")
		//获取table第一行 $("#tab tr").eq(0)
		//获取table倒数第二行 $("#tab tr").eq(-2)
		var $tr = $("#" + tab + " tr").eq(row);
		if ($tr.size() == 0) {
		parent.layer.msg('指定的table 行数不存在！',{icon: 1});
			return;
		}
		$tr.after(trHtml);
	}

	function ccEcdelTr(ckb) {
		//获取选中的复选框，然后循环遍历删除
		var ckbs = $("input[name=" + ckb + "]:checked");
		if (ckbs.size() == 0) {
			parent.layer.msg('要删除指定行，需选中要删除的行！',{icon: 1});
			return;
		}
		ckbs.each(function() {
			$(this).parent().parent().remove();
		});
	}

	
	var ccEcNum=1;
	function ccEcaddTr2(tab, row) {
		var trHtml = '<tr align="center">'+
						'<td><input type="checkbox" name="ckb"/></td>'+
						'<td><input class="ccEcDescription"  type="hidden" name="detailNo" id="description_'+ccEcNum+'"><input id="ccEcDescriptionName_'+ccEcNum+'" type="text"  name="descriptionName'+ccEcNum+'" class="form-control input"  required=""   ></td>'+
						'<td><input type="text" class="form-control input ccEcUnit"  name="unit'+ccEcNum+'" id="unit_'+ccEcNum+'" onkeyup="quantityKeyup(\''+ccEcNum+'\')" onblur="quantityBlur(\''+ccEcNum+'\')" required=""  /></td>'+
						'<td><input type="text" class="form-control input ccEcPriceVilidityPeriod" id="priceVilidityPeriod_'+ccEcNum+'" name="priceVilidityPeriod'+ccEcNum+'" required=""  /></td>'+
						'<td><input type="text"  name="perPrice'+ccEcNum+'" class="form-control input ccEcPerPrice"  number="true" id="perPrice_'+ccEcNum+'" required=""  /></td>'+
						'<td><input type="text"  class="form-control input ccEcRemark" name="remark'+ccEcNum+'" id="remark_'+ccEcNum+'" required=""  /></td>'
					+'</tr>';
		ccEcaddTr(tab, row, trHtml);
		ccEcNum++;
	}
	
	function ccEcdelTr2() {
		ccEcdelTr('ckb');
	}
	
	function duibi(startDate, endDate,startMessage,endMessage) {
		var arr = startDate.split("-");
		var starttime = new Date(arr[0], arr[1], arr[2]);
		var starttimes = starttime.getTime();

		var arrs = endDate.split("-");
		var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		var lktimes = lktime.getTime();
		if(starttimes > lktimes) {
			parent.layer.msg(startMessage+'不能大于'+endMessage+'，请重新输入！',{icon: 2});
			return false;
		} 
		return true;
	}
	function startDateTime(startDate) {
		var arr = startDate.split("-");
		var starttime = new Date(arr[0], arr[1], arr[2]);
		var starttimes = starttime.getTime();
		var endDate = new Date().Format("yyyy-MM-dd");
		var arrs = endDate.split("-");
		var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		var lktimes = lktime.getTime();
		if(starttimes < lktimes) {
			parent.layer.msg('开始时间不能小于当前时间，请重新输入！！',{icon: 2});
			return false;
		}
		return true;
	}
	Date.prototype.Format = function(fmt) { //author: meizz 
		var o = {
			"M+": this.getMonth() + 1, //月份 
			"d+": this.getDate(), //日 
			"h+": this.getHours(), //小时 
			"m+": this.getMinutes(), //分 
			"s+": this.getSeconds(), //秒 
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			"S": this.getMilliseconds() //毫秒 
		};
		if(/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for(var k in o)
			if(new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}