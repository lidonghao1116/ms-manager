function add(){
	var params={
			templateType:$("#templateType").val(),
			templateName:$("#templateName").val(),
			beginTime:$("#beginTime").val(),
			endTime:$("#endTime").val(),
			openCycle:$("#openCycle").val(),
			openCycle:getCheckData("input[name='openCycle']","value")
	};
	//提交问题
	layer.confirm('确认添加上课时间信息吗？',function(){
		$.ajax({
				url :config.coursesTimeAddUrl,
				type : "POST",
				data: params,
				success: function(data){
					if(data.success){
						layer.alert(data.msg, {icon:1});
						window.location.href=config.coursesTimeListUrl;
					}else{
						layer.msg(data.msg);
					}
				}
		});
	});
}

function update(){
	var params={
			templateType:$("#templateType").val(),
			templateName:$("#templateName").val(),
			beginTime:$("#beginTime").val(),
			endTime:$("#endTime").val(),
			openCycle:$("#openCycle").val(),
			openCycle:getCheckData("input[name='openCycle']","value")
		};
		
		//提交问题
		layer.confirm('确认修改学校信息吗？',function(){
	        $.ajax({
				url :config.coursesTimeModifyUrl,
				type : "POST",
				data: params,
				success: function(data){
					if(data.success){
						layer.alert(data.msg, {icon:1});
						window.location.href=config.coursesTimeListUrl;
					}else{
						layer.msg(data.msg);
					}
				}
		});
	});
}
var obj={
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
