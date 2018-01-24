function add(){
	var params={
		className:$("#className").val(),
		classNumber:$("#classNumber").val(),
		courseId:GetSelectValue("#courseId"),
		shoolId:GetSelectValue("#shoolId"),
		examForm:GetSelectValue("#examForm"),
		theoryDate:$("#theoryDate").val(),
		theoryAddress:$("#theoryAddress").val(),
		operationDate:$("#operationDate").val(),
		operationAddress:$("#operationAddress").val()
	};
	//提交问题
	layer.confirm('确认添加此班级吗？',function(){
        $.ajax({
          url :config.examsAddUrl,
          type : "POST",
          data: params,
          success: function(data){
            if(data.success){
            	layer.alert(data.msg, {icon:1});
            	window.location.href=config.examsListUrl;
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
			className:$("#className").val(),
			classNumber:$("#classNumber").val(),
			courseId:GetSelectValue("#courseId"),
			shoolId:GetSelectValue("#shoolId"),
			examForm:GetSelectValue("#examForm"),
			theoryDate:$("#theoryDate").val(),
			theoryAddress:$("#theoryAddress").val(),
			operationDate:$("#operationDate").val(),
			operationAddress:$("#operationAddress").val()
	};
	//提交问题
	layer.confirm('确认修改此班级吗？',function(){
        $.ajax({
          url :config.examsModifyUrl,
          type : "POST",
          data: params,
          success: function(data){
            if(data.success){
            	layer.alert(data.msg, {icon:1});
            	window.location.href=config.examsListUrl;
            }else{
              	layer.msg(data.msg);
            }
          }
        });
	});
}
