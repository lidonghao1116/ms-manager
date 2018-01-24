var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj = {
    query: function() {
        $(grid_selector).jqGrid({
            url: config.examsQueryUrl,
            datatype: "json",
            height: 'auto',
            colNames: ['班级名称', '课程', '课程等级', '班级标号', '报考学校', '班级状态', '人数', '操作'],
            colModel: [
                { name: 'className', index: 'className', width: 150, sortable: false },
                { name: 'courseName', index: 'courseName', width: 160, sortable: false },
                { name: 'authenticateGrade', index: 'authenticateGrade', width: 160, sortable: false },
                { name: 'classNumber', index: 'classNumber', width: 160, sortable: false },
                { name: 'schoolName', index: 'schoolName', width: 160, sortable: false },
                { name: 'examStatusName', index: 'examStatusName', width: 120, sortable: false },
                { name: 'examResult.totleNum', index: 'examResult.totleNum', width: 80, sortable: false },
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
        window.location.href = config.examsFormUrl + "?id=" + id;
    },
    learnRecordObj: function(id) {
        window.location.href = config.examsLearnUrl + "?id=" + id;
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
            shoolId: GetSelectValue("#schoolId"),
            examStatus: GetSelectValue("#examStatus")
        };
        $(grid_selector).setGridParam({ url: config.examsQueryUrl, postData: params }).trigger('reloadGrid');
    }
};

function actFormatter(cellvalue, options, rawObject) {
    var div = '<div style="margin-left:8px;">';
    var edit = '<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"' +
        'onclick="obj.modifyObj(' + rawObject.id + ')"><span class="ui-icon ui-icon-pencil"></span></div>';
    /**
    var learnRecord ='<a style="float:right;cursor:pointer;padding-right: 20px;"'
    	+'onclick="obj.learnRecordObj('+ rawObject.id+')">学员模拟成绩</a>';

    return div+ edit+learnRecord;
    **/
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