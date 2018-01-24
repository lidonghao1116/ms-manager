var obj={
	modify:function(){
		var params={	
		};
		
		//提交问题
		layer.confirm('确认修改学校简介吗？',function(){
			if($("#logoimg").val()=="/jiacerconsole/static/loginImg/peixun.png"){
				params =  {
	                    id:$("#id").val(),
	                    privince:$("#province").val(),
	                    city:$("#city").val(),
	                    area:$("#area").val(),
	                    schoolAddress:$("#schoolAddress").val(),
	                    schoolPhone:$("#schoolPhone").val(),
	                    contacts:$("#contacts").val()
	                };
			}else{
				params = {
                    id:$("#id").val(),
                    privince:$("#province").val(),
                    city:$("#city").val(),
                    area:$("#area").val(),
                    schoolAddress:$("#schoolAddress").val(),
                    schoolPhone:$("#schoolPhone").val(),
                    contacts:$("#contacts").val(),
                    logoUrl:$("#logoimg").val()
                };
			}
			
            $.ajax({
                url :config.schoolsIntroUrl,
                type : "POST",
                data: params,
                success: function(data){
                    if(data.success){
                        layer.alert(data.msg, {icon:1});
                        window.location.reload();
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
		$("#modifyBtn").bind("click",function(){
			obj.modify();
		});
	}
}

$(function(){
    //初始化面包屑
	initBreadcrumb();
	obj.init();
});