var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.productQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['创建时间','产品名称','课程数','标准价','优惠价','状态','操作'],
			colModel:[
				{name:'addTime',index:'addTime',width:80,sortable:false,formatter:formatDate},
				{name:'packageName',index:'packageName', width:150,sortable:false},
				{name:'applyCourses',index:'applyCourses', width:150,sortable:false,formatter:countFormatter},
				{name:'originalPrice',index:'originalPrice', width:150,sortable:false},
				{name:'price',index:'price', width:150,sortable:false},
				{name:'statusName',index:'statusName', width:100,sortable:false},
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
			caption: "课程列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		window.location.href=config.productFormUrl+"?id="+id;
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
			packageName:encodeURIComponent($("#packageName").val()),
			status:GetSelectValue("#status"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.productQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';
	return div+ edit;
};

function countFormatter(cellvalue, options, rawObject){
	var applyCourses=rawObject.applyCourses;
	if(isEmpty(applyCourses)){
		return 0;
	}else{
		return applyCourses.split(",").length;
	}
}

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});