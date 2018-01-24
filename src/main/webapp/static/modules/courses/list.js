var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.coursesQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['课程名称',"证书名称",'鉴定等级','考试形式','监考费','其他费','证书费','课程状态','操作'],
			colModel:[
				{name:'typeName',index:'typeName',width:150,sortable:false},
				{name:'certName',index:'certName',width:150,sortable:false},
				{name:'authenticateGrade',index:'authenticateGrade',width:80,sortable:false},
				{name:'examTypeName',index:'examTypeName', width:150,sortable:false},
				{name:'examFee',index:'examFee', width:150,sortable:false},
				{name:'otherFee',index:'otherFee', width:150,sortable:false},
				{name:'certificateFee',index:'certificateFee', width:150,sortable:false},
				{name:'statusName',index:'statusName', width:100,sortable:false},
				{name:'',index:'', width:80, fixed:true, sortable:false, resize:false,
					formatter:actFormatter
				}
			],
			rownumbers: true,
			hidegrid: false,
			viewrecords : true,
			rowNum:10,
			pager : pager_selector,
	        loadComplete : function() {
				var table = this;
				setTimeout(function(){
					updatePagerIcons(table);
				}, 0);
			},
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		window.location.href=config.coursesFormUrl+"?id="+id;
	}
	,
	init:function(){
		obj.query();
		//初始化绑定事件
		$("#queryBtn").bind("click",function(){
			obj.refreshTable();
		});
	}
	,
	refreshTable:function(){
		var params={
			typeName:$("#courseId option:selected").text(),
			authenticateGrade:GetSelectValue("#grade"),
			status:GetSelectValue("#status")
		};
		$(grid_selector).setGridParam({url:config.coursesQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';
	return div+ edit;
};

jQuery(function($) {
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
	$("#jqgh_grid-table_rn span.s-ico").before("<span>序号</span>");
});
