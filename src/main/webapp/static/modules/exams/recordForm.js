var obj={
	exportExcel:function(){
		$("#queryForm").attr("action",ctx+"/exams/learnRecords/export/01");
		$("#queryForm").submit();
	}
	,
	init:function(){
		$("#exportBtn").bind("click",function(){
			obj.exportExcel();
		});
		
	}
}

$(function(){
	obj.init();
});