var obj={
	add:function(){
		
		var arr=new Array();
		$('.add_class select').each(function () {
			arr.push($(this)[0].value)
		})
		var strClassName=arr.join(",")
		var params={
			packageName:$("#packageName").val(),
			summary:encodeURIComponent($("#summary").val()),
			originalPrice:$("#originalPrice").val(),
			//applyCourses:getCheckData("input[name='applyCourses']","value"),
			applyCourses:strClassName,
			remarks:$("#remarks").val(),
			price:$("#price").val(),
			isDiscount:$("#isDiscount").is(":checked")?"1":"0",
			sortNo:$("#sortNo").val(),
			typeOfWork:$("#typeOfWork").val()
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
		
	},
	goback:function(){
    	window.location.href=config.productListUrl;		
	}
	,
	modify:function(){
		var arr=new Array();
		$('.add_class select').each(function () {
			arr.push($(this)[0].value)
		})
		var strClassName=arr.join(",")
		var params={
			id:$("#id").val(),
			applyCourses:$("#par").val(),
			packageName:$("#packageName").val(),
			status:GetSelectValue("#status1"),
			summary:encodeURIComponent($("#summary").val()),
			originalPrice:$("#originalPrice").val(),
			remarks:$("#remarks").val(),
			price:$("#price").val(),
			isDiscount:$("#isDiscount").is(":checked")?"1":"0",
			//applyCourses:getCheckData("input[name='applyCourses']","value"),
			applyCourses:strClassName,
			sortNo:$("#sortNo").val(),
			typeOfWork:$("#typeOfWork").val()
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