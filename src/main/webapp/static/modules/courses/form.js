function add(){
	/**拼接上课时间**/
	var arr=new Array();
	$('.add_time select').each(function () {
		arr.push($(this)[0].value)
	})
	var strClassTimes=arr.join(",")
	//console.log(str);
	
	var params={
			id:$("#courseName").val(),
			typeName:$("#courseName option:selected").text(),
			remarks:$("#remarks").val(),
			timeTemplate:strClassTimes,
			isNeedHasPf:$("input[name='isNeedHasPf']:checked").val(),
			status:$("input[name='status']:checked").val(),
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
	/**拼接上课时间**/
	var arr=new Array();
	$('.add_time select').each(function () {
		arr.push($(this)[0].value)
	})
	var strClassTimes=arr.join(",")
	var params={
			id:$("#id").val(),
			remarks:$("#remarks").val(),
			timeTemplate:strClassTimes,
			isNeedHasPf:$("input[name='isNeedHasPf']:checked").val(),
			status:$("input[name='status']:checked").val(),
			examFee:$("#examFee").val(),
			certificateFee:$("#certificateFee").val(),
			otherFee:$("#otherFee").val()
		};
	//提交问题
	layer.confirm('确认修改课程信息吗？',function(){
		$.ajax({
			url :config.coursesModifyUrl,
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


$(function(){
	//obj.init();
	$(".bc-confirm").click(function(){
		event.preventDefault();
		//return notPass();
	})
});
