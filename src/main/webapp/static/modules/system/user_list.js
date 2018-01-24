var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj = {
    query: function() {
        $(grid_selector).jqGrid({
            url: config.sysUserQueryUrl,
            datatype: "json",
            height: 'auto',
            colNames: [ '账号', '姓名','创建时间', '状态',  '操作'],
            colModel: [
				{name:'loginAccount',index:'loginAccount',width:80,sortable:false},
   				{name:'loginName',index:'loginName', width:100,sortable:false},
   				{name:'addTime',index:'addTime', width:100,sortable:false,formatter:formatDate},
   				{name:'loginFlag',index:'loginFlag', width:100,sortable:false,formatter:stuFormatter},
   				{name:'',index:'', width:160, fixed:true, sortable:false, resize:false,
   					formatter:actFormatter
   				}
            ],
            rownumbers: true,
            hidegrid: false,
            viewrecords: true,
            rowNum: 10,
            pager: pager_selector,
            loadComplete: function() {
                var table = this;
                setTimeout(function() {
                    updatePagerIcons(table);
                }, 0);
            },
            autowidth: true
        });
    },
    editUser: function(id) {
    	window.location.href=config.sysUserFormUrl+"?id="+id;
    },
	delUser:function(id){
   		layer.confirm('确认删除此用户吗？',function(){
   	        $.ajax({
   	          url :config.sysUserDeleteUrl,
   	          type : "POST",
   	          data: {"id":id},
   	          async: false,
   	          success: function(data){
   	            if(data.success){
   	            	layer.alert(data.msg, {icon:1});
   	            	obj.refreshTable();
   	            }else{
   	              	layer.msg(data.msg);
   	            }
   	          }
   	        });
   		});
   	}
   	,
    init: function() {
        obj.query();
        //初始化绑定事件
        $("#queryBtn").bind("click", function() {
            obj.refreshTable();
        });

        //初始化日期
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        });
    },
    refreshTable: function() {
    	var params={
   			 loginName:encodeURIComponent($("#loginName").val()),
   			 startDate: $("#startDate").val(),
             endDate: $("#endDate").val()
		    };
		$(grid_selector).setGridParam({url:config.sysUserQueryUrl,postData:params})
		                .trigger('reloadGrid');
		}
};

function actFormatter(cellvalue, options, rawObject) {
 	var div='<div style="margin-left:8px;">';

  var edit ='<div style="width:auto;display:inline;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
   				+'onclick="obj.editUser('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';

   	var del = '<div style="width:auto;display:inline;margin-left:20px;" class="ui-pg-div ui-inline-del" '
   				+'onclick="obj.delUser('+ rawObject.id+');"><span class="ui-icon ui-icon-trash"></span></div></div>';
   	/*
   var edit ='<a  style="color:blue" onclick="obj.editUser('+ rawObject.id+');">修改</a> &nbsp;';
   var del = '<a style="color:blue" onclick="obj.delUser('+ rawObject.id+');">删除</a></div>';
   */
   	var result = div+ edit;
   	if(rawObject.loginAccount != '${fns:getUser().getLoginAccount()}'){
   	    result = result + del;
   	}
   	return result;
};

function stuFormatter(cellvalue, options, rawObject) {
    if( cellvalue==1 ){
        return '正常';
     }else{
         return '禁用';
     }
};

jQuery(function($) {
    $(window).on('resize.jqGrid', function() {
        $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
    });
    //初始化面包屑
    initBreadcrumb();
    obj.init();
    $("#jqgh_grid-table_rn span.s-ico").before("<span>序号</span>");
});