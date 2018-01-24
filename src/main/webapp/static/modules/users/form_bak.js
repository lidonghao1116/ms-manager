var obj={
	modify:function(){
		var userBaseInfo={
			mobile:$("#mobile").val(),
			id:$("#id").val()
		}
		var sourceValue='';
		if(GetSelectValue("#sourceType")=='01'){
			sourceValue=GetSelectValue("#sourceValue");
		}else if(GetSelectValue("#sourceType")=='02'){
			sourceValue=$("#sourceTypeValue").val();
		}
		var userExtendInfo={
			userId:$("#id").val(),
			userName:$("#userName").val(),	
			certNo:$("#certNo").val(),
			education:GetSelectValue("#education"),
			nation:GetSelectValue("#nation"),
			birthplace:GetSelectValue("#birthplace"),
			address:$("#address").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			sourceType:GetSelectValue("#sourceType"),
			sourceTypeSec:GetSelectValue("#sourceTypeSec"),
			sourceValue:sourceValue,
			sourceRemarks:$("#sourceRemarks").val()
		}
		
		var params={
			userInfo:userBaseInfo,
			userExtend:userExtendInfo
		};
		
		layer.confirm('确认修改此用户信息吗？',function(){
	        $.ajax({
	          url :config.usersModifyUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.usersListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}
	,
	init:function(){
		$("#modifyBtn").bind("click",function(){
			obj.modify();
		});
	}
}

$(function(){
	obj.init();
});