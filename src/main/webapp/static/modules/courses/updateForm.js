function add(){
	alert($("#authenticateGrade").val());
	var params={
			typeName:$("#typeName").val(),
			examType:$("#examType").val(),
			//authorityId:$("#authorityId").val(),
			authenticateGrade:$("#authenticateGrade").val(),
			totalHours:$("#totalHours").val(),
			classTimes:$("#classTimes").val(),
			isNeedHasPf:$("input[name='isNeedHasPf']:checked").val(),
			examFee:$("#examFee").val(),
			certificateFee:$("#certificateFee").val(),
			otherFee:$("#otherFee").val()
		};
		//提交问题
		layer.confirm('确认添加此课程吗？',function(){
	        $.ajax({
	          url :config.coursesAddUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.coursesListUrl;
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
function goBack(){
	window.location.href=config.coursesListUrl;
    
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
			$("#goback").bind("click", function() {
	            obj.goBack();
	        });
		}
	}
	$(function(){
		obj.init();
	});
