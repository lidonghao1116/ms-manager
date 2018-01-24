var obj={
	add:function(){
		
		var params={
			packageName:$("#packageName").val(),
			summary:encodeURIComponent($("#summary").val()),
			originalPrice:$("#originalPrice").val(),
			applyCourses:getCheckData("input[name='applyCourses']","value"),
			remarks:$("#remarks").val(),
			price:$("#price").val(),
			isDiscount:$("#isDiscount").is(":checked")?"1":"0",
			sortNo:$("#sortNo").val()
		};
		
		//提交问题
		layer.confirm('确认添加此销售产品吗？',function(){
	        $.ajax({
	          url :config.productAddUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.productListUrl;
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
			packageName:$("#packageName").val(),
			summary:encodeURIComponent($("#summary").val()),
			originalPrice:$("#originalPrice").val(),
			remarks:$("#remarks").val(),
			price:$("#price").val(),
			status:$("#status").val(),
			isDiscount:$("#isDiscount").is(":checked")?"1":"0",
			applyCourses:getCheckData("input[name='applyCourses']","value"),
			sortNo:$("#sortNo").val()
		};
		
		//提交问题
		layer.confirm('确认修改销售产品吗？',function(){
	        $.ajax({
	          url :config.productModifyUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.productListUrl;
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



$(function(){
	obj.init();
});