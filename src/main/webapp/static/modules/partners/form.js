var obj={
	add:function(){
		var params={
			partnerName:$("#partnerName").val(),
			partnerType:GetSelectValue("#partnerType"),
			province:GetSelectValue("#province"),
			city:GetSelectValue("#city"),
			county:GetSelectValue("#county"),
			address:$("#address").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			storePhone:$("#storePhone").val(),
			brokerageId:$("#brokerageId").val(),
			zipCode:$("#zipCode").val(),
			salesman:$("#salesman").val()
		};
		
		//提交问题
		layer.confirm('确认添加此合作商吗？',function(){
	        $.ajax({
	          url :config.partnersAddUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	//layer.alert(data.msg, {icon:1});
	            	window.location.href=config.partnersListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}
	,
	modify:function(){
		var params={
			id:$("#id").val(),
			partnerName:$("#partnerName").val(),
			province:GetSelectValue("#province"),
			city:GetSelectValue("#city"),
			county:GetSelectValue("#county"),
			address:$("#address").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			storePhone:$("#storePhone").val(),
			brokerageId:$("#brokerageId").val(),
			zipCode:$("#zipCode").val(),
			salesman:$("#salesman").val()
		};
		
		//提交问题
		layer.confirm('确认修改此合作商吗？',function(){
	        $.ajax({
	          url :config.partnersModifyUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	//layer.alert(data.msg, {icon:1});
	            	window.location.href=config.partnersListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}	,
	goback:function(){window.location.href=config.partnersListUrl;}
	,
	init:function(){
		//初始化绑定事件
		$("#addBtn").bind("click",function(){
			obj.add();
		});
		$("#goback").bind("click",function(){
			obj.goback();
		});
		$("#modifyBtn").bind("click",function(){
			obj.modify();
		});
	}

}

$(function(){
	obj.init();
});