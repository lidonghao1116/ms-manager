var obj={
	modify:function(optType){
		var params={
			id:$("#id").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			makeupFee:$("#makeupFee").val(),
			sourceRemarks:$("#sourceRemarks").val(),
			classNumber:GetSelectValue("#classNumber"),
			isHasPf:$("input[name='isHasPf']:checked").val(),
			bookFree:$("#bookFree").val(),
			isStaging:$("input[name='isStaging']:checked").val(),
			firstPay:$("#firstPay").val(),
			isClear:$("#isClear").is(":checked")?"1":"0",
			optType:optType,
			type:"REP_APPLY"
		};
		
		//提交问题
		layer.confirm('确认修改订单信息吗？',function(){
	        $.ajax({
	          url :config.ordersModifyUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.ordersListUrl+"?type=REP_APPLY";
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	},
	goBack:function(){
		window.location.href=config.ordersListUrl+"?type=REP_APPLY";	
	}
	,
	init:function(){
		$("#passBtn").bind("click",function(){
			obj.modify("opt03");
		});
		$("#goBack").bind("click",function(){
			obj.goBack();
		});
		$("#unpassBtn").bind("click",function(){
			obj.modify("opt04");
		});
	}
}

$(function(){
	obj.init();
});