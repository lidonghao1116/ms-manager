var obj={
	add:function(){

		var params={
			schoolName:$("#schoolName").val(),
			schoolAddress:$("#schoolAddress").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			remarks:$("#remarks").val(),
			applyCourses:getCheckData("input[name='applyCourses']","value")
		};
		//提交问题
		layer.confirm('确认添加此学校信息吗？',function(){
	        $.ajax({
	          url :config.schoolsAddUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.schoolsListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});

	}
	,
	goback:function(){window.location.href=config.schoolsListUrl;}
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
	$(".bc-confirm").click(function(){
		event.preventDefault();
		return notPass();
	})
});
