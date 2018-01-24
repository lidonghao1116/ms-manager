var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj = {
    query: function() {
        $(grid_selector).jqGrid({
            url: config.scoresQueryUrl + "?examStatus=02",
            datatype: "json",
            height: 'auto',
            colNames: ['班级名称', '课程', '课程等级', '班级标号', '报名数', '录入数', '合格数', '操作'],
            colModel: [
                { name: 'className', index: 'className', width: 150, sortable: false },
                { name: 'courseName', index: 'courseName', width: 200, sortable: false },
                { name: 'authenticateGrade', index: 'authenticateGrade', width: 160, sortable: false },
                { name: 'classNumber', index: 'classNumber', width: 170, sortable: false },
                { name: 'examResult.totleNum', index: 'examResult.totleNum', width: 150, sortable: false },
                { name: 'examResult.inputNum', index: 'examResult.inputNum', width: 80, sortable: false },
                { name: 'examResult.qualifiedNum', index: 'examResult.qualifiedNum', width: 80, sortable: false },
                {
                    name: '',
                    index: '',
                    width: 150,
                    fixed: true,
                    sortable: false,
                    resize: false,
                    formatter: actFormatter
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
    modifyObj: function(id) {
        window.location.href = config.scoresFormUrl + "?id=" + id;
    },
    init: function() {
        obj.query();
        //初始化绑定事件
        $("#queryBtn").bind("click", function() {
            obj.refreshTable();
        });
    },
    refreshTable: function() {
        var params = {
            className: encodeURIComponent($("#className").val()),
            classNumber: $("#classNumber").val(),
            courseId: GetSelectValue("#learnTypeId"),
            shoolId: GetSelectValue("#shoolId")
        };
        $(grid_selector).setGridParam({ url: config.scoresQueryUrl + "?examStatus=02", postData: params }).trigger('reloadGrid');
    }
};

function actFormatter(cellvalue, options, rawObject) {
    var div = '<div style="margin-left:8px;">';
    var edit = '<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"' +
        'onclick="obj.modifyObj(' + rawObject.id + ')"><span class="ui-icon ui-icon-pencil"></span></div>';
    return div + edit;
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