
var addModal = '<div class="page-content">\
	 <div class="page-body">\
	   <form class="form-horizontal">\
			<div class="form-group" style="margin-bottom: 5px;">\
			  <label class="col-md-2 controls">学员信息</label>\
			</div>\
			<div class="form-group form-color">\
				<div class="form-group form-padding">\
					<label class="col-sm-2 control-label">姓名</label>\
					<div class="col-sm-4 padding-7">\
						<span id="userName"></span>\
					</div>\
					<label class="col-sm-2 control-label">手机号码</label>\
					<div class="col-sm-4 padding-7">\
						<span id="mobile"></span>\
					</div>\
				</div>\
			</div>\
			<div class="form-group" style="margin-bottom: 5px;">\
			  <label class="col-md-2 controls">考试信息</label>\
			</div>\
			<div class="form-group form-color">\
				<div class="form-group form-padding">\
					<label class="col-sm-2 control-label">理论成绩</label>\
					<div class="col-sm-4">\
						<input type="text" id="theoryScores" value="" maxlength="50" class="form-control"/>\
					</div>\
					<label class="col-sm-2 control-label">实操成绩</label>\
					<div class="col-sm-4">\
						<input type="text" id="poScores" value="" maxlength="50" class="form-control"/>\
					</div>\
				</div>\
				<div class="form-group form-padding">\
					<label class="col-sm-2 control-label">考试结果</label>\
					<div class="col-sm-4">\
						<select class="chosen-select form-control" id="examResult"></select>\
					</div>\
					<label class="col-sm-2 control-label deal">处理结果</label>\
					<div class="col-sm-4 deal">\
						<select class="chosen-select form-control" id="dealResult"></select>\
					</div>\
				</div>\
				<div class="form-group form-padding cert">\
					<label class="col-sm-2 control-label">证书编号</label>\
					<div class="col-sm-10">\
						<input type="text" id="certificateNo" value="" maxlength="50" class="form-control"/>\
					</div>\
				</div>\
			</div>\
			<div class="form-group">\
			  	<div class="col-sm-12 center">\
					<button type="button" class="btn btn-sm btn-success" id="submit"><i class="ace-icon fa fa-check"></i>保存</button>\
				</div>\
			</div>\
	   </form>\
	 </div>\
</div>';

var obj={
	add:function(classId,userId,index,orderId){
		
		var params={
			theoryScores:$("#theoryScores").val(),
			poScores:$("#poScores").val(),
			examResult:GetSelectValue("#examResult"),
			dealResult:GetSelectValue("#dealResult"),
			certificateNo:$("#certificateNo").val(),
			classId:classId,
			userId:userId,
			orderId:orderId
		};
		//提交问题
		layer.confirm('确认修改此用户成绩吗？',function(){
	        $.ajax({
	          url :config.scoresAddUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.scoresFormUrl+"?id="+classId;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
	        layer.close(index);
		});
		
	}
	,
	exportExcel:function(){
		$("#queryForm").attr("action",ctx+"/scores/export/01");
		$("#queryForm").submit();
	}
	,
	//打开弹出框初始化参数
	openModel:function(classId,userId,orderId){
		//获取用户数据
		var userScores=null;
		$.ajax({
			type: "POST",
			url: config.scoresInfoUrl,
			async: false,
			data: {"classId":classId,"userId":userId},
			success: function (data) {
				if(data.success){
					userScores=data.jsonData;
				}else{
					layer.msg(data.msg);
				}
             }
		});
		
		var index = layer.open({
			  type: 1,
			  title:"编辑学员成绩 ",
			  closeBtn: 1, //不显示关闭按钮
			  shadeClose: true, //开启遮罩关闭,
			  area: ['600px', '400px'],
			  zIndex:1000,
			  content: addModal
		});
		
		obj.initPageData(userScores);
		
		$('#submit').on('click',function() {
			obj.add(classId,userId,index,orderId);
		})
	}
	,
	initPageData:function(userScores){
		initExamResult(userScores!=null?userScores.examResult:"");
		$(".deal").hide();
		initDealResult(userScores!=null?userScores.dealResult:"");
		if(userScores!=null && userScores.examResult=='02'){//不合格时
			$(".deal").show();
		}else if(userScores!=null && !isEmpty(userScores.examResult) && userScores.examResult!='02'){
			$("#examResult").attr("disabled","disabled");
		}
		$("#mobile").text(userScores!=null?userScores.userInfo.mobile:"");
		$("#userName").text(userScores!=null?userScores.userExtend.userName:"");
		$("#theoryScores").val(userScores!=null?userScores.theoryScores:"");
		$("#poScores").val(userScores!=null?userScores.poScores:"");
		if(userScores!=null && userScores.examResult=='01'){
			$(".cert").show();
			$("#certificateNo").val(userScores!=null?userScores.certificateNo:"");
		}else{
			$(".cert").hide();
		}
		
		$("#examResult").bind("click",function(){
			var $this=GetSelectValue("#examResult");
			if($this=='02'){//不合格时
				$(".deal").show();
			}else{
				$(".deal").hide();
			}
			if($this=='01'){
				$(".cert").show();
			}else{
				$(".cert").hide();
			}
		})
	}
	
}

function initExamResult(value){
		$.post(ctx+'/system/params', {type:12,pid:0}, function(data){
			createExamResultData(data,value);
		});
};

function initDealResult(value){
	$.post(ctx+'/system/params', {type:13,pid:0}, function(data){
		createDealResultData(data,value);
	});
};

function createExamResultData(data,value){
	var selectGist = $("#examResult");
	selectGist.html('');
	selectGist.append(defaultOption);
	if(data){
  		$.each(data,function(i,obj){
  			if(value==obj.value){
  				$('<option value="'+obj.value+'" selected="selected">'+obj.text+'</option>').appendTo(selectGist);
  			}else{
  				$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
  			}
      	});
    }
}

function createDealResultData(data,value){
	var selectGist = $("#dealResult");
	selectGist.html('');
	selectGist.append(defaultOption);
	if(data){
  		$.each(data,function(i,obj){
  			if(value==obj.value){
  				$('<option value="'+obj.value+'" selected="selected">'+obj.text+'</option>').appendTo(selectGist);
  			}else{
  				$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
  			}
      	});
    }
}

