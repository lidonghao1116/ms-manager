var params;

var obj = {
  unpass: function (optType) {
    var params={
      id: parseInt($("#id").val()),
    };
    //提交问题
    layer.confirm('确认修改订单信息吗？', function () {
      //alert(config.ordersModifyUrl);
      $.ajax({
        url: config.ordersUnpassUrl,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(params),
        success: function (data) {
          if (data.success) {
            //layer.alert(data.msg, {icon:1});
            window.location.href ="/jiacerconsole/index";
          } else {
            layer.msg(data.msg);
          }
        }
      });
    });

  },
  pass: function (optType) {

    var type = "PEND_APPLY";
    var sourceValue = '';
    if (GetSelectValue("#sourceType") == '01') {
      sourceValue = GetSelectValue("#sourceValue");
    } else if (GetSelectValue("#sourceType") == '02') {
      sourceValue = $("#sourceTypeValue").val();
    }
    params = {
      birthplace: $("[name='birthplace']").val(),
      mobile: $("[name='mobile']").val(),
      headImage: $("[name='headImage']").val(),

      age: $("[name='age']").val(),
      sex: $("[name='sex']").val(),
      // userExtendInfo: userExtendInfo,
      issueOrg: $("[name='issueOrg']").val(),
      expiredTime: $("[name='expiredTime']").val(),
      id: parseInt($("#id").val()),
      certNo: $("#certNo").val(),
      id: parseInt($("#id").val()),
      nation: $("#nation").val(),
      address: $("#address").val(),
      contacts: $("#contacts").val(),
      userName: $("#userName").val(),
      certNo: $("#certNo").val(),
      education: GetSelectValue("#education"),
      contactPhone: $("#contactPhone").val(),
      sourceRemarks: $("#sourceRemarks").val(),
      sourceType: GetSelectValue("#sourceType"),
      sourceTypeSec: GetSelectValue("#sourceTypeSec"),
      sourceValue: sourceValue,
      contactAddress: $("#contactAddress").val(),
      classNumber: GetSelectValue("#classNumber1"),
      isHasPf: getCheckData("input[name='isHasPf']", "value"),
      isStaging: $("input[name='isStaging']:checked").val(),
      isDeposit: $("input[name='isDeposit']:checked").val(),
      firstPay: $("#firstPay").val(),
      isClear: $("#isClear").val(),
      isDepositClear: $("#isDepositClear").val(),
      optType: optType,
      type: type,
      classTime: GetSelectValue("#classTime"),
      //isExam:GetSelectValue("#isExam"),
      isExam: getCheckData("input[name='isExam']", "value"),
      schoolFee: $("#schoolFee").val(),
      deposit: $("#deposit").val(),
      bookFree: $("#bookFree").val()
    }
    console.log(params);
    checkEmpty(params);
    if (checkEmpty(params)){
      layer.confirm('确认修改订单信息吗？', function () {
        checkIdCard();
      });
    };

    // console.log(userExtendInfo);
  },
  modify:function(optType){
    var params;
    var type="PEND_APPLY";
    if($("#isExist").val()=='false'){
      type="SPECIAL";

      var sourceValue='';
      if(GetSelectValue("#sourceType")=='01'){
        sourceValue=GetSelectValue("#sourceValue");
      }else if(GetSelectValue("#sourceType")=='02'){
        sourceValue=$("#sourceTypeValue").val();
      }
      var userExtendInfo={
        userName:$("#userName").val(),
        certNo:$("#certNo").val(),
        education:GetSelectValue("#education"),
        nation:GetSelectValue("#nation"),
        birthplace:GetSelectValue("#birthplace"),
        address:$("#address").val(),
        contacts:$("#contacts").val(),
        contactPhone:$("#contactPhone").val(),
        sourceType:GetSelectValue("#sourceType"),
        sourceTypeSec:GetSelectValue("#sourceTypeSec"),
        sourceValue:sourceValue,
        sourceRemarks:$("#sourceRemarks").val()
      }
      params={
        userExtendInfo:userExtendInfo,
        id:$("#id").val(),
        classNumber:GetSelectValue("#classNumber"),
        isHasPf:GetSelectValue("#isHasPf"),
        isStaging:$("input[name='isStaging']:checked").val(),
        isDeposit:$("input[name='isDeposit']:checked").val(),
        firstPay:$("#firstPay").val(),
        isClear:$("#isClear").val(),
        isDepositClear: $("#isDepositClear").val(),
        optType:optType,
        type:type,
        classTime:GetSelectValue("#classTime"),
        isExam:GetSelectValue("#isExam"),
        schoolFee:$("#schoolFee").val(),
        deposit:$("#deposit").val(),
        bookFree:$("#bookFree").val()
      }
    }else{
      params={
        id:$("#id").val(),
        classNumber:GetSelectValue("#classNumber"),
        isHasPf:GetSelectValue("#isHasPf"),
        isStaging:$("input[name='isStaging']:checked").val(),
        isDeposit:$("input[name='isDeposit']:checked").val(),
        firstPay:$("#firstPay").val(),
        isClear:$("#isClear").val(),
        isDepositClear: $("#isDepositClear").val(),
        optType:optType,
        type:type,
        classTime:GetSelectValue("#classTime"),
        isHasPf:GetSelectValue("#isHasPf"),
        isExam:GetSelectValue("#isExam"),
        schoolFee:$("#schoolFee").val(),
        deposit:$("#deposit").val(),
        bookFree:$("#bookFree").val()
      }
    }

    //提交问题
    layer.confirm('确认修改订单信息吗？',function(){
      alert(config.ordersModifyUrl);
      $.ajax({
        url :config.ordersModifyUrl,
        type : "POST",
        contentType:"application/json",
        data: JSON.stringify(params),
        success: function(data){
          if(data.success){
            layer.alert(data.msg, {icon:1});
            window.location.href=config.ordersListUrl+"?type=PEND_APPLY";
          }else{
            layer.msg(data.msg);
          }
        }
      });
    });

  },
  add:function () {
    var type = "PEND_APPLY";
    var sourceValue = '';
    if (GetSelectValue("#sourceType") == '01') {
      sourceValue = GetSelectValue("#sourceValue");
    } else if (GetSelectValue("#sourceType") == '02') {
      sourceValue = $("#sourceTypeValue").val();
    }
    params = {
      packageId: GetSelectValue("#packageId1"),
      courseId: GetSelectValue("#learnTypeId"),
      birthplace: $("[name='birthplace']").val(),
      mobile: $("[name='mobile']").val(),
      headImage: $("[name='headImage']").val(),
      code:$("#getSmsCode").val(),
      age: $("[name='age']").val(),
      sex: $("[name='sex']").val(),
      issueOrg: $("[name='issueOrg']").val(),
      expiredTime: $("[name='expiredTime']").val(),
      id: parseInt($("#id").val()),
      certNo: $("#certNo").val(),
      id: parseInt($("#id").val()),
      nation: $("#nation").val(),
      address: $("#address").val(),
      contacts: $("#contacts").val(),
      userName: $("#userName").val(),
      certNo: $("#certNo").val(),
      education: GetSelectValue("#education"),
      contactPhone: $("#contactPhone").val(),
      sourceRemarks: $("#sourceRemarks").val(),
      sourceType: GetSelectValue("#sourceType"),
      sourceTypeSec: GetSelectValue("#sourceTypeSec"),
      sourceValue: sourceValue,
      contactAddress: $("#contactAddress").val(),
      classNumber: GetSelectValue("#classNumber1"),
      isHasPf: getCheckData("input[name='isHasPf']", "value"),
      isStaging: $("input[name='isStaging']:checked").val(),
      isDeposit:$("input[name='isDeposit']:checked").val(),
      firstPay: $("#firstPay").val(),
      isDepositClear: $("#isDepositClear").val(),
      isClear: $("#isClear").val(),
      type: type,
      classTime: GetSelectValue("#classTime"),
      //isExam:GetSelectValue("#isExam"),
      isExam: getCheckData("input[name='isExam']", "value"),
      schoolFee: $("#schoolFee").val(),
      deposit: $("#deposit").val(),
      bookFree: $("#bookFree").val()
    }
    console.log(params);
    if (checkEmpty(params)){
      layer.confirm('确认新增学员吗？', function () {
        checkIdCards();
      });
    };
  },
  //验证是否有教务管理编辑的角色，没有的话，隐藏通过和不通过按钮
  isHasRole:function () {
	      $.ajax({
	          url: config.sysUserRoleQueryUrl,
	          type: "POST",
	          async:false,
	          contentType: "application/json",
	          success: function (data) {
	        	  //layer.msg(data.length);
	        	  var passBtn=document.getElementById('passBtn');
	        	  var unpassBtn=document.getElementById('unpassBtn');
	        	  var sys=document.getElementById('4');
	        	  var j=0;
	        	  var m=0;
	        	  for(var i=0;i<data.length;i++)
	               {
	        		if(data[i]=="jwgl_edit")
	        		{j++;}	
	        		if(data[i]=="sys")
	        		{m++;}
	        		
	        	   }
	        	  if(j==0)
	        		{   
	        		  
	        		  passBtn.style.display='none';
	        		  unpassBtn.style.display='none';
	        		}else{
	        			
	        			unpassBtn.style.display='';
	        			passBtn.style.display='';	
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
	  
  init: function () {
	  
    $("#passBtn").bind("click", function () {
      obj.pass("opt03");
    });
    $("#unpassBtn").bind("click", function () {
      obj.unpass();
    });
    $("#addBtn").bind("click", function () {
      obj.add();
    });
    obj.isHasRole();
  }
}

$(function () {
  obj.init();
});

function isEmp(param) {
  if ($.trim(param) != "" && $.trim(param) != null && param.length != 0) {
    return false;
  } else {
    return true;
  }
}

function checkEmpty(params) {
  if (isEmp(params.certNo) || isEmp(params.expiredTime) || isEmp(params.userName) || isEmp(params.age) || isEmp(params.birthplace) || isEmp(params.issueOrg) || isEmp(params.sex) || isEmp(params.nation) || isEmp(params.address)||isEmp(params.headImage)) {
    layer.msg('请读取身份证信息');
    return false;
  } else if (isEmp(params.sourceType||params.sourceTypeSec)) {
    layer.msg('请选择来源');
    return false;
  }else if (isEmp(params.classTime)){
    layer.msg('请选择上课时间');
    return false;
  }else if (isEmp(params.classNumber)){
    layer.msg('请选择班级标号');
    return false;
  }else {
    return true;
  }
}

function checkIdCard() {
  console.log("ok");
  $.ajax({
    url: "/jiacerconsole/user/idcard/read",
    type: "post",
    data:$("#formCard").serialize(),
    dataType: "json",
    async: false,
    success:function success(data) {
      if(data.success){
        $("#sign").val(data.jsonData.sign)
        console.log(data.jsonData.sign);
        console.log(data);
        params.sign=$("#sign").val();
        console.log(params)
        $.ajax({
          url: config.ordersPassUrl,
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify(params),
          success: function (data) {
            if (data.success) {
              //layer.alert(data.msg, {icon:1});
              // window.location.href = config.ordersListUrl + "?type=PEND_APPLY";
              window.location.href ="/jiacerconsole/index";
            } else {
              layer.msg(data.msg);
            }
          }
        });
      }else{
        layer.msg(data.msg);
        return;
      }
    },
    error:function (e) {
      console.log(e);
    }
  })
}

function checkIdCards() {
  console.log("okchk");
  $.ajax({
    url: "/jiacerconsole/user/idcard/read",
    type: "post",
    data:$("#formCard").serialize(),
    dataType: "json",
    async: false,
    success:function success(data) {
      if(data.success){
        $("#sign").val(data.jsonData.sign)
        console.log(data.jsonData.sign);
        console.log(data);
        params.sign=$("#sign").val();
        console.log(params)
        $.ajax({
          url: config.ordersAddUrl,
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify(params),
          success: function (data) {
            if (data.success) {
              //layer.alert(data.msg, {icon:1});
              // window.location.href = config.ordersListUrl + "?type=PEND_APPLY";
              window.location.href ="/jiacerconsole/index";
            } else {
              layer.msg(data.msg);
            }
          }
        });
      }else{
        layer.msg(data.msg);
        return;
      }
    },
    error:function (e) {
      console.log(e);
    }
  })
}