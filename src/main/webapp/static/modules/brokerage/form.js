var obj={
	add:function(){
		
		var arr=new Array();
		$('.secTd input').each(function () {
			arr.push($(this)[0].value)
		})
		var strClassName=arr.join(",")
		//alert(strClassName);
		var params={
			schemeName:$("#schemeName").val(),
			rules:getRules()
		};
		//console.log(params);
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
	goback:function(){window.location.href=config.brokerageListUrl;},
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
		$("#goback").bind("click",function(){
			obj.goback();
		});
		$("#modifyBtn").bind("click",function(){
			obj.modify();
		});
	}
}


function getRules(){
	var rules = new Array();
	var rule =null,id=$("#id").val();
	$("#tcValue").each(function(i,val){
		rule=new Object();
		rule.bonusAmount=$(this).find("input").val();
		rules.push(rule);
	});
	
	return rules;
}


$(function(){
	obj.init();
});