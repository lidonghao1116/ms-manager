//常量定义：来源值select input和select默认值
var sourceValueSelect = '<select class="chosen-select form-control" id="sourceValue">';
var sourceValueInput = '<input type="text" id="sourceTypeValue" maxlength="50" class="form-control"/>';
var defaultOption = '<option value="">---请选择---</option>';
//初始化课程时间
function initClassTimes(values, code) {
    $.post(ctx + '/system/params', { type: 16, pid: 0, values: values }, function(data) {
        createClassTime(data, code);
    });
};

//初始化课程
function initCourses(values, code) {
    $.post(ctx + '/params/courses', { values: values }, function(data) {
        createCoursesData(data, code);
    });
};

//初始化产品
function initProducts(code) {
    $.post(ctx + '/params/coursesPackage', {}, function(data) {
        createProductsData(data, code);
    });
};
//已报名列表新增查询班级标号初始化班级xiehui
function getClassNumber(values, code) {
    $.post(ctx + '/params/exams', {
        'courseId': values
    }, function(data) {
        createExamClassData(data, code);
    });
};

//初始化来源一
function initSource(id, code,codeSec) {
  $.ajaxSettings.async = false;
  $.post(ctx + '/system/params', { type: 5, pid: id }, function(data) {
    createSourceData(data, code,codeSec);
  });
  $.ajaxSettings.async = true;
};
//初始化来源二
function initSourceSec(id, code) {
  $.ajaxSettings.async = false;
    $.post(ctx + '/system/params', { type: 5, pid: id }, function(data) {
        createSourceSecData(data, code);
    });
  $.ajaxSettings.async = true;
};

//初始化班级
function initExamClass(code) {
    //alert("班级标号");
    var courseId = $("#courId").val();
    $.post(ctx + '/params/exams', { courseId: courseId }, function(data) {
        createExamClassData(data, code);
    });
};

//初始化来源值
function initSourceValue(type, code) {
    $.post(ctx + '/params/partners', { type: type }, function(data) {
        createSourceValue(data, code);
    });
};
//创建来源下拉列表
function createSourceData(data,code,codeSec) {
  console.log(data);
  console.log(code);
  var selectGist = $("#sourceType");
  selectGist.html('');
  selectGist.append(defaultOption);
  var html="";
  if(data){
      $.each(data,function (i, obj) {
        html+=`
        <option value="${obj.value}" id="${obj.id}">${obj.text}</option>
`
      })
    selectGist.append(html);
    $("#sourceType option[value='"+code+"']").attr("selected","selected");
  }
  var id=selectGist.find("option:selected").attr("id");
  console.log(id)

  initSourceSec(id, codeSec);
}
//创建下拉框--课程列表
function createCoursesData(data, code) {
    var selectGist = $("#courseId");
    selectGist.html('');
    selectGist.append(defaultOption);
    if (data) {
        $.each(data, function(i, obj) {
            if (code == obj.id) {
                $('<option value="' + obj.id + '" ct="' + obj.classTimes + '" selected="selected">' + obj.typeName + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.id + '" ct="' + obj.classTimes + '" >' + obj.typeName + '</option>').appendTo(selectGist);
            }
        });
    }
}

//创建下拉框--产品列表
function createProductsData(data, code) {
    var selectGist = $("#packageId");
    selectGist.html('');
    selectGist.append(defaultOption);
    if (data) {
        $.each(data, function(i, obj) {
            if (code == obj.id) {
                $('<option value="' + obj.id + '" ck="' + obj.applyCourses + '" selected="selected">' + obj.packageName + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.id + '"  ck="' + obj.applyCourses + '" >' + obj.packageName + '</option>').appendTo(selectGist);
            }
        });
    }

}

//创建下拉框--来源二列表
function createSourceSecData(data, code) {
    var selectGist = $("#sourceTypeSec");
    selectGist.html('');
    selectGist.append(defaultOption);
    var code=code.trim();
    if (data) {
        $.each(data, function(i, obj) {
            if (code == obj.value) {
                $('<option value="' + obj.value + '" selected="selected">' + obj.text + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.value + '">' + obj.text + '</option>').appendTo(selectGist);
            }
        });
    }
}

//创建下拉框--班级列表
function createExamClassData(data, code) {
    // var selectGist = $("#classNumber");
    // //alert("selectGist");
    // selectGist.html('');
    // selectGist.append(defaultOption);
    // if(data){
    // 	$.each(data,function(i,obj){
    // 		if(code==obj.id){
    // 			$('<option value="'+obj.id+'" selected="selected">'+obj.classNumber+'</option>').appendTo(selectGist);
    // 		}else{
    // 			$('<option value="'+obj.id+'">'+obj.classNumber+'</option>').appendTo(selectGist);
    // 		}
    //   	});
    // }
}

//创建下拉框--商户列表
function createSourceValue(data, code) {
    $("#sourceValueDiv").html("");
    $("#sourceValueDiv").append(sourceValueSelect);
    var selectGist = $("#sourceValue");
    selectGist.html('');
    selectGist.append(defaultOption);
    if (data) {
        $.each(data, function(i, obj) {
            if (code == obj.id) {
                $('<option value="' + obj.id + '" selected="selected">' + obj.partnerName + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.id + '">' + obj.partnerName + '</option>').appendTo(selectGist);
            }
        });
    }
}

//创建下拉框--上课时间
function createClassTime(data, code) {
    var selectGist = $("#classTime");
    selectGist.html('');
    selectGist.append(defaultOption);
    if (data) {
        $.each(data, function(i, obj) {
            if (code == obj.value) {
                $('<option value="' + obj.value + '" selected="selected">' + obj.text + '</option>').appendTo(selectGist);
            } else {
                $('<option value="' + obj.value + '">' + obj.text + '</option>').appendTo(selectGist);
            }
        });
    }
}