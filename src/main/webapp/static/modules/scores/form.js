function initExamResult(value) {
    $.post(ctx + '/system/params', { type: 12, pid: 0 }, function(data) {
        createExamResultData(data, value);
    });
};

function initDealResult(value) {
    $.post(ctx + '/system/params', { type: 13, pid: 0 }, function(data) {
        createDealResultData(data, value);
    });
};

function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}

function createExamResultData(data, value) {
    var selectGist = $("#examResult");
    selectGist.html('');
    selectGist.append(defaultOption);
    if (data) {
        $.each(data, function(i, obj) {
            if (value == obj.value) {
                $('<option value="' + obj.value + '" selected="selected">' + obj.text + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.value + '">' + obj.text + '</option>').appendTo(selectGist);
            }
        });
    }
}

function createDealResultData(data, value) {
    var selectGist = $("#dealResult");
    selectGist.html('');
    selectGist.append(defaultOption);
    if (data) {
        $.each(data, function(i, obj) {
            if (value == obj.value) {
                $('<option value="' + obj.value + '" selected="selected">' + obj.text + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.value + '">' + obj.text + '</option>').appendTo(selectGist);
            }
        });
    }
}


var addModal = '<div class="content-area">\
			<div class="infoBlock">\
				<h1 class="title">学员信息</h1>\
				<div class="ib-content clearfix">\
					<table style="width:100%">\
						<tr>\
							<td>姓名：</td>\
							<td id="userName" class="secTd"></td>\
							<td style="text-align:right;">手机号码：</td>\
							<td id="mobile" class="secTd"></td>\
						</tr>\
						<tr>\
							<td>身份证号：</td>\
							<td id="certNo" colspan="3" class="secTd"></td>\
						</tr>\
					</table>\
				</div>\
			</div>\
			<div class="infoBlock">\
				<h1 class="title">考试信息</h1>\
				<div class="ib-content clearfix">\
					<table style="width:100%">\
						<tr>\
							<td>证书名称：</td>\
							<td colspan="3" id="certName" class="secTd"></td>\
						</tr>\
						<tr>\
							<td>鉴定等级：</td>\
							<td id="authenticateGradeName" class="secTd"></td>\
							<td style="text-align:right;">综合成绩：</td>\
							<td class="secTd"><input type="text" id="comprehensiveScores" name="" value=""> </td>\
						</tr>\
						<tr>\
							<td>理论成绩：</td>\
							<td class="secTd"><input id="theoryScores" type="text" id="theoryScores" name="theoryScores" value=""></td>\
							<td style="text-align:right;">能力成绩：</td>\
							<td class="secTd"><input type="text" id="abilityScores" name="abilityScores" value=""></td>\
						</tr>\
						<tr>\
							<td>技能成绩：</td>\
							<td class="secTd"><input type="text" id="poScores" name="poScores" value=""></td>\
							<td style="text-align:right;">评定结果：</td>\
							<td class="secTd">\
								<select id="examResult" class="dealResult" name="examResult" onchange="isClick()">\
									<option value="">---请选择---</option>\
									<option value="01">合格</option>\
									<option value="02">不合格</option>\
									<option value="03">缺考</option>\
									<option value="04">优秀</option>\
									<option value="05">良好</option>\
								</select>\
							</td>\
						</tr>\
					</table>\
				</div>\
			</div>\
			<div class="infoBlock">\
				<h1 class="title">其他信息</h1>\
				<div class="ib-content clearfix">\
					<table style="width:100%">\
						<tr id="fzrq">\
							<td>发证日期：</td>\
							<td class="secTd"><input type="text" class="date-picker" id="issuingDate" name="issuingDate" value="" data-date-format="yyyy-mm-dd"/></td>\
							<td></td>\
							<td></td>\
						</tr>\
						<tr id="zsbh">\
							<td>证书编号：</td>\
							<td class="secTd"><input id="certificateNo" type="text" name="certificateNo" value=""></td>\
							<td></td>\
							<td></td>\
						</tr>\
						<tr id="fzjg">\
							<td>发证机构：</td>\
							<td id="certAuthorityName" class="secTd"></td>\
							<td></td>\
							<td></td>\
						</tr>\
						<tr id="sfbk">\
							<td>是否补考：</td>\
							<td class="secTd">\
								<select class="" name="isExam" id="isExam">\
									<option value="">--请选择--</option>\
									<option value="1">是</option>\
									<option value="0">否</option>\
								</select>\
							</td>\
							<td style="text-align:right;">补考费：</td>\
							<td class="secTd"><input type="text" id="makeupExamFree" name="makeupExamFree" value=""></td>\
						</tr>\
					</table>\
				</div>\
			</div>\
			<input id="classId" value="" type="hidden"/>\
			<input id="stuUserInfoId" value="" type="hidden"/>\
			<input id="certAuthorityId" value="" type="hidden"/>\
			<input id="authenticateGrade" value="" type="hidden"/>\
			<div class="btnCase clearfix">\
	              <button id="scoresModifyBtn" onclick="return update();" class="bc-confirm" >提交</button>\
				<button id="alert-close" class="bc-cancel">取消</button>\
			</div>\
		</div>';

function isClick() {
    if (GetSelectValue("#examResult") == "01" ||
        GetSelectValue("#examResult") == "04" ||
        GetSelectValue("#examResult") == "05") {
        document.getElementById("fzrq").style.display = "inline";
        document.getElementById("zsbh").style.display = "inline";
        document.getElementById("fzjg").style.display = "inline";
        document.getElementById("sfbk").style.display = "none";
    } else {
        document.getElementById("fzrq").style.display = "none";
        document.getElementById("zsbh").style.display = "none";
        document.getElementById("fzjg").style.display = "none";
        document.getElementById("sfbk").style.display = "inline";
    }
}

function update() {
    if (GetSelectValue("#examResult") == "") {
        alert("请选择评定结果！");
        return false;
    }
    var params = {
        classId: $("#classId").val(),
        stuUserInfoId: $("#stuUserInfoId").val(),
        certAuthorityId: $("#certAuthorityId").val(),
        authenticateGrade: $("#authenticateGrade").val(),
        comprehensiveScores: $("#comprehensiveScores").val(),
        abilityScores: $("#abilityScores").val(),
        theoryScores: $("#theoryScores").val(),
        poScores: $("#poScores").val(),
        issuingDate: $("#issuingDate").val(),
        certificateNo: $("#certificateNo").val(),
        isExam: GetSelectValue("#isExam"),
        examResult: GetSelectValue("#examResult"),
        makeupExamFree: $("#makeupExamFree").val()
    };
    //提交问题
    layer.confirm('确认修改此用户成绩吗？', function() {

        $.ajax({
            url: config.scoresAddUrl,
            type: "POST",
            data: params,
            success: function(data) {
                if (data.success) {
                    layer.alert(data.msg, { icon: 1 });
                    window.location.href = config.scoresFormUrl + "?id=" + params.classId;
                } else {
                    layer.msg(data.msg);
                }
            }
        });
        //layer.close(index);
    });
}

var obj = {

    exportExcel: function() {
        $("#queryForm").attr("action", ctx + "/scores/export/01");
        $("#queryForm").submit();
    },
    
    goBack:function(){    	window.location.href = config.scoresListUrl;  }

    ,
    init: function() {
        //obj.query();
        //初始化绑定事件
        $("#queryBtn").bind("click", function() {
            obj.refreshTable();
        });
		$("#goback").bind("click", function() {
            obj.goBack();
        });
        
        $("#scoresModifyBtn").bind("click", function() {
            obj.modify();
        });

        //初始化日期
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        });
    },
    //打开弹出框初始化参数
    openModel: function(classId, stuUserInfoId, orderId) {
        //获取用户数据
        var userScores = null;
        $.ajax({
            type: "POST",
            url: config.scoresInfoUrl,
            async: false,
            data: { "classId": classId, "stuUserInfoId": stuUserInfoId },
            success: function(data) {
                if (data.success) {
                    userScores = data.jsonData;
                } else {
                    layer.msg(data.msg);
                }
            }
        });

        var index = layer.open({
            type: 1,
            title: "编辑学员成绩 ",
            closeBtn: 1, //不显示关闭按钮
            shadeClose: true, //开启遮罩关闭,
            area: ['600px', '400px'],
            zIndex: 1000,
            content: addModal
        });
        obj.initPageData(userScores);




        $(".date-picker").datepicker({
            language: "zh-CN",
            autoclose: true, //选中之后自动隐藏日期选择框
            clearBtn: false, //清除按钮
            todayBtn: false, //今日按钮
            format: "yyyy-mm-dd" //日期格式，
        });



    },
    initPageData: function(userScores) {

        console.log(userScores);
        /**
        initExamResult(userScores!=null?userScores.examResult:"");
        $(".deal").hide();
        initDealResult(userScores!=null?userScores.dealResult:"");

        if(userScores!=null && userScores.examResult=='02'){//不合格时
        	$(".deal").show();
        }
        **/

        if (userScores != null) {
        	

            if (userScores.examResult == '01' || userScores.examResult == '04' || userScores.examResult == '05') {
                document.getElementById("fzrq").style.display = "inline";
                document.getElementById("zsbh").style.display = "inline";
                document.getElementById("fzjg").style.display = "inline";
                document.getElementById("sfbk").style.display = "none";
                $("#certificateNo").val(userScores != null ? userScores.certificateNo : "");
            } else {
                document.getElementById("fzrq").style.display = "none";
                document.getElementById("zsbh").style.display = "none";
                document.getElementById("fzjg").style.display = "none";
                document.getElementById("sfbk").style.display = "inline";
            }
            
  	      $.ajax({
	          url: config.sysUserRoleQueryUrl,
	          type: "POST",
	          contentType: "application/json",
	          success: function (data) {
	        	  var scoresModifyBtn=document.getElementById('scoresModifyBtn');
	        	  var j=0;
	        	  for(var i=0;i<data.length;i++)
	               {
	        		if(data[i]=="jwgl_edit")
	        		{j++;}	        		  
	        	   }
	        	  if(j==0)
	        		{   	  
	        		  scoresModifyBtn.style.display='none';
	        		}else{
	        		  scoresModifyBtn.style.display='';
	        		}
	          }
	        });
        }


        $("#classId").val(userScores != null ? userScores.classId : "");
        $("#stuUserInfoId").val(userScores != null ? userScores.stuUserInfo.id : "");
        $("#mobile").text(userScores != null ? userScores.stuUserInfo.mobile : "");
        $("#userName").text(userScores != null ? userScores.stuUserInfo.userName : "");
        $("#certNo").text(userScores != null ? userScores.stuUserInfo.certNo : "");
        $("#comprehensiveScores").val(userScores != null ? userScores.comprehensiveScores : "");
        $("#theoryScores").val(userScores != null ? userScores.theoryScores : "");
        $("#abilityScores").val(userScores != null ? userScores.abilityScores : "");
        $("#poScores").val(userScores != null ? userScores.poScores : "");
        $("#dealResult").text(userScores != null ? userScores.dealResult : "");
        // $("#certificateNo").val(userScores!=null?userScores.certificateNo:"");
        $("#makeupExamFree").val(userScores != null ? userScores.makeupExamFree : "");
        // $("#issuingDate").val(userScores != null ? new Date(userScores.issuingDate) : "");
        $("#authenticateGradeName").text(userScores != null ? userScores.authenticateGradeName : "");
        $("#certName").text(userScores != null ? userScores.certName : "");
        $("#certAuthorityName").text(userScores != null ? userScores.certAuthorityName : "");
        $("#certAuthorityId").val(userScores != null ? userScores.certAuthorityId : "");
        $("#authenticateGrade").val(userScores != null ? userScores.authenticateGrade : "");
        $("#theoryScores").val(userScores != null ? userScores.theoryScores : "");
        $("#poScores").val(userScores != null ? userScores.poScores : "");
        if (userScores != null && userScores.isExam != "") {
            $("#isExam").find("option[value=" + userScores.isExam + "]").attr('selected', true);
        }

        if (userScores != null && userScores.issuingDate != "" && userScores.issuingDate != undefined) {
            var oDate = new Date(userScores.issuingDate),
                year = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oTime = getzf(year) + '-' + getzf(oMonth) + '-' + getzf(oDay);
            $("#issuingDate").val(oTime);
        } else {
            $("#issuingDate").val("");
        }

        if (userScores != null && !isEmpty(userScores.examResult)) {
            $("#examResult").find("option[value=" + userScores.examResult + "]").attr('selected', true);
            $("#examResult").attr("disabled", "disabled");
        }
    }

}
jQuery(function($) {
    obj.init();
    $('#submit').on('click', function() {
        obj.add(classId, userId, index, orderId);
    });
    $("body").on("click", "#alert-close", function() {
        $(".layui-layer-shade,.layui-layer").remove();
    });
    /**
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
    });
    **/
});