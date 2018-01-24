var obj={
	add:function(){
		
		var params={
			schemeName:$("#schemeName").val(),
			rules:getRules()
		};
		
		//提交问题
		layer.confirm('确认添加此提成方案吗？',function(){
	        $.ajax({
	          url :config.brokerageAddUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.brokerageListUrl;
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
			schemeName:$("#schemeName").val(),
			rules:getRules()
		};
		
		//提交问题
		layer.confirm('确认修改提成方案吗？',function(){
	        $.ajax({
	          url :config.brokerageModifyUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.brokerageListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}
	,
	init:function(){
		//初始化绑定事件
		$("#addBtn").bind("click",function(){
			obj.add();
		});
		
		$("#modifyBtn").bind("click",function(){
			obj.modify();
		});
	}
}


function getRules(){
	var rules = new Array();
	var rule =null,id=$("#id").val();
	$(".rules").each(function(i,val){
		rule=new Object();
		rule.packageId=$(this).find("div:eq(0)").attr("id");
		rule.schemeId=id;
		rule.bonusAmount=$(this).find("div:eq(1)").find("input").val();
		rules.push(rule);
	});
	return rules;
}


$(function(){
	obj.init();
});