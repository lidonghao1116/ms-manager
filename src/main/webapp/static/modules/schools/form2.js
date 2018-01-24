function add(){
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
function update(){
	var params={
			id:$("#id").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			remarks:$("#remarks").val(),
			applyCourses:getCheckData("input[name='applyCourses']","value")
		};
		//提交问题
		layer.confirm('确认修改学校信息吗？',function(){
	        $.ajax({
	          url :config.schoolsModifyUrl,
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

$(function(){
	//obj.init();
	$(".bc-confirm").click(function(){
		event.preventDefault();
		//return notPass();
	})
});


