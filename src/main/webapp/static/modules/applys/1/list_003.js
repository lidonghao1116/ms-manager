/** 已退学习 */
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.ordersQueryUrl+"?type=NOT_MATCH",
			datatype: "json",
			height:'auto',
			colNames:['报名时间','姓名','产品名称','课程名称','学历','是否交金','操作'],
			colModel:[
				{name:'orderTime',index:'orderTime',width:160,sortable:false,formatter:formatDate},
				{name:'stuUserInfo.userName',index:'stuUserInfo.userName', width:80,sortable:false},
				{name:'productName',index:'productName', width:210,sortable:false},
				{name:'typeName',index:'typeName', width:210,sortable:false},
				{name:'stuUserInfo.educationName',index:'stuUserInfo.educationName', width:150,sortable:false},
				{name:'isHasPf',index:'isHasPf', width:90,sortable:false,formatter:trueFalse},
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
			caption: "不符合学员列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		window.location.href=config.ordersFormUrl+"?type=NOT_MATCH&id="+id;
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
			packageId:GetSelectValue("#packageId"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.ordersQueryUrl+"?type=NOT_MATCH",postData:params}).trigger('reloadGrid');
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
});
