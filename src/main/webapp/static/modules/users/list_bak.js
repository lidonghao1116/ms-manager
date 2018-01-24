var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.usersQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['姓名','联系方式','课程数','来源','操作'],
			colModel:[
				{name:'userExtend.userName',index:'userExtend.userName', width:150,sortable:false},
				{name:'mobile',index:'mobile', width:150,sortable:false},
				{name:'couresCount',index:'couresCount', width:150,sortable:false},
				{name:'userExtend.sourceValueName',index:'userExtend.sourceValueName', width:150,sortable:false},
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
	viewObj:function(id){
		window.location.href=config.usersInfoUrl+"?id="+id;
	}
	,
	modifyObj:function(id){
		window.location.href=config.usersFormUrl+"?id="+id;
	}
	,
	exportExcel:function(){
		alert("开发中...");
	}
	,
	init:function(){
		obj.query();
		//初始化绑定事件
		$("#queryBtn").bind("click",function(){
			obj.refreshTable();
		});
		
		$("#exportBtn").bind("click",function(){
			obj.exportExcel();
		});
		
		//初始化日期
		$('.date-picker').datepicker({
			autoclose: true,
			todayHighlight: true
		});
	}
	,
	refreshTable:function(){
		var params={
			userName:encodeURIComponent($("#userName").val()),
			mobile:$("#mobile").val(),
			sourceType:$("#sourceType").val(),
			sourceTypeSec:$("#sourceTypeSec").val(),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.usersQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var view ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.viewObj('+ rawObject.id+')"><span class="ui-icon fa-eye"></span></div>';
	var edit = '<div style="float:left;margin-left:5px;" class="ui-pg-div ui-inline-del" '
		+'onclick="obj.modifyObj('+ rawObject.id+');"><span class="ui-icon ui-icon-pencil"></span></div></div>';
	return div+view+edit;
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