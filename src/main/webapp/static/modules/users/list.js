var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.usersQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['姓名','联系方式','课程数','操作'],
			colModel:[
				{name:'userName',index:'userName', width:150,sortable:false},
				{name:'mobile',index:'mobile', width:150,sortable:false},
				{name:'couresCount',index:'couresCount', width:150,sortable:false},
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
	
	init:function(){
		obj.query();
		//初始化绑定事件
		$("#queryBtn").bind("click",function(){
			obj.refreshTable();
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
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.usersQueryUrl,postData:params}).trigger('reloadGrid');
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