var obj = {
    modify: function(optType) {
        var params;
        var type = "PEND_APPLY";
        var sourceValue = '';
        if (GetSelectValue("#sourceType") == '01') {
            sourceValue = GetSelectValue("#sourceValue");
        } else if (GetSelectValue("#sourceType") == '02') {
            sourceValue = $("#sourceTypeValue").val();
        }
        params = {
            /***
            userName:$("#userName").val(),	
            certNo:$("#certNo").val(),
            education:GetSelectValue("#education"),
            nation:GetSelectValue("#nation"),
            birthplace:GetSelectValue("#birthplace"),
            address:$("#contactAddress").val(),
            contacts:$("#contacts").val(),
            contactPhone:$("#contactPhone").val(),
            mobile:$("#mobile").val(),
            sourceType:GetSelectValue("#sourceType"),
            sourceTypeSec:GetSelectValue("#sourceTypeSec"),
            //sourceValue:sourceValue,
            sourceRemarks:$("#sourceRemarks").val(),
            isHasPf:getCheckData("input[name='isHasPf']","value"),
            classNumber:GetSelectValue("#classNumber1"),
            courseId:GetSelectValue("#learnTypeId"),
            packageId:GetSelectValue("#packageId1"),
            isHasPf:GetSelectValue("#isHasPf"),
            isStaging:$("input[name='isStaging']:checked").val(),
            firstPay:$("#firstPay").val(),
            isClear:$("#isClear").is(":checked")?"1":"0",
            optType:optType,
            type:type,
            classTime:GetSelectValue("#classTime"),
            isExam:getCheckData("input[name='isExam']","value"),
            schoolFee:$("#schoolFee").val(),
            deposit:$("#deposit").val(),
            bookFree:$("#bookFree").val()
            **/
            userName: $("#userName").val(),
            certNo: $("#certNo").val(),
            education: GetSelectValue("#education"),
            nation: GetSelectValue("#nation"),
            address: $("#address").val(),
            contactAddress: $("#contactAddress").val(),
            contacts: $("#contacts").val(),
            contactPhone: $("#contactPhone").val(),
            mobile: $("#mobile").val(),
            sourceType: GetSelectValue("#sourceType"),
            sourceTypeSec: GetSelectValue("#sourceTypeSec"),
            sourceValue: sourceValue,
            sourceRemarks: $("#sourceRemarks").val(),
            isHasPf: getCheckData("input[name='isHasPf']", "value"),
            classNumber: GetSelectValue("#classNumber1"),
            courseId: GetSelectValue("#learnTypeId"),
            packageId: GetSelectValue("#packageId1"),
            isStaging: $("input[name='isStaging']:checked").val(),
            isDeposit: $("input[name='isDeposit']:checked").val(),
            firstPay: $("#firstPay").val(),
            isExam: getCheckData("input[name='isExam']", "value"),
            schoolFee: $("#schoolFee").val(),
            deposit: $("#deposit").val(),
            bookFree: $("#bookFree").val(),
            optType: optType,
            classTime: GetSelectValue("#classTime")
        }
        console.log(params);
        //提交问题
        layer.confirm('确认添加学员信息吗？', function() {
            //alert(config.ordersModifyUrl);
            $.ajax({
                url: config.ordersModifyUrl,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(params),
                success: function(data) {
                    if (data.success) {
                        //layer.alert(data.msg, {icon:1});
                        window.location.href = config.ordersListUrl + "?type=SUCCESS_APPLY";
                    } else {
                        layer.msg(data.msg);
                    }
                }
            });
        });

    },
    isHasRole:function () {
	      $.ajax({
	          url: config.sysUserRoleQueryUrl,
	          type: "POST",
	          contentType: "application/json",
	          success: function (data) {
	        	  var sys=document.getElementById('4');
	        	  var m=0;
	        	  for(var i=0;i<data.length;i++)
	               {
	        		if(data[i]=="sys")
	        		{m++;}
	        		
	        	   }

	        	  if(m==0)
	        		{   
	        		  
	        		  sys.style.display='none';
	        		  
	        		}else{
	        			
	        			sys.style.display='';
	        		}
	          }
	        });
	  },
    init: function() {
        $("#passBtn").bind("click", function() {
            obj.modify("opt10");
        });
        $("#unpassBtn").bind("click", function() {
            obj.modify("opt10");
        });
        obj.isHasRole();
    }
}

$(function() {
    obj.init();
});