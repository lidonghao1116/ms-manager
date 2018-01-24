var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.coursesTimeQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['模板ID','模板名称','开课周期','上课时间','是否启用','操作'],
			colModel:[
				{name:'templateId',index:'templateId',width:160,sortable:false},
				{name:'templateName',index:'templateName', width:160,sortable:false},
				{name:'openCycle',index:'openCycle', width:120,sortable:false},
				{name:'beginTime',index:'beginTime', width:160,sortable:false},
				{name:'templateType',index:'templateType', width:80,sortable:false},
				{name:'',index:'', width:150, fixed:true, sortable:false, resize:false,
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
		window.location.href=config.coursesTimeFormUrl+"?id="+id;
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
			templateName:encodeURIComponent($("#templateName").val())
		};
		$(grid_selector).setGridParam({url:config.coursesTimeQueryUrl,postData:params}).trigger('reloadGrid');
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
