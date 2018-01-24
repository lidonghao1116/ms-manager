<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
            <html>

            <head>
                <title>${fns:getConfig('productName')}-学校列表</title>
                <%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
                    <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
                    <meta name="decorator" content="default" />
            </head>

            <body>
                <ul id="breadcrumb" style="display: none">
                    <li>教务管理&nbsp;>&nbsp;报名管理&nbsp;>&nbsp;已处理&nbsp;>&nbsp;已退学</li>
                </ul>
                <!--内容区-->
                <div class="frame">
                    <div class="content">
                        <div class="content-tab clearfix">
                            <div class="clearfix">
                                <a href="${ctx}/orders/list?type=SUCCESS_APPLY"><b></b>已报名</a>
                                <a href="${ctx}/orders/list?type=NOT_MATCH"><b></b>不通过</a>
                                <a class="ct_on"><b></b>已退学</a>
                            </div>
                        </div>
                        <div class="content-area">
                            <div class="ca-filter clearfix query_box">
                                <form class="form-horizontal" action="" id="queryForm" method="GET">
                                    <div class="query">
                                        <p>
                                            <label>姓名</label>
                                            <input type="text" id="userName" name="userName" value="" />
                                        </p>
                                        <p>
                                            <label>产品名称</label>
                                            <!-- 
		  							<select id="packageId" name="packageId">
		  								<option value=""></option>
		  							</select>
		  							 -->
                                            <select class="" id="packageId1" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${coursePackageList}" var="obj" varStatus="item">
					            			<option value="${obj.id}">${obj.packageName}</option>
					            		</c:forEach>
									</select>
                                        </p>
                                        <p>
                                            <label>课程名称</label>
                                            <!-- 
		  							<select id="courseId" name="courseId">
		  								<option value=""></option>
		  							</select>
		  							 -->
                                            <select class="" id="learnTypeId" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${learnTypeList}" var="obj" varStatus="item">
					            			<option value="${obj.id}">${obj.typeName}</option>
					            		</c:forEach>
									</select>

                                        </p>
                                        <p>
                                            <label>退学时间</label>
                                            <input type="text" class=" date-picker" id="startDate" name="startDate" value="" data-date-format="yyyy-mm-dd" />-
                                            <input type="text" class=" date-picker" id="endDate" name="startDate" value="" data-date-format="yyyy-mm-dd" />
                                        </p>
                                        <a id="queryBtn" class="search-btn"></a>
                                    </div>
                                </form>
                            </div>

                            <div class="page-body">
                                <table id="grid-table"></table>
                                <div id="grid-pager"></div>
                            </div>
                        </div>
                        <script src="${ctxStatic}/modules/applys/2/list_out.js"></script>
                        <script type="text/javascript">
                            var defaultOption = '<option value="">---请选择---</option>';

                            function initCourses() {
                                $.post(ctx + '/params/courses', {}, function(data) {
                                    createCoursesData(data);
                                });
                            };

                            function initProducts() {
                                $.post(ctx + '/params/coursesPackage', {}, function(data) {
                                    createProductsData(data);
                                });
                            };

                            function initSourceSec(id) {
                                $.post(ctx + '/system/params', {
                                    type: 5,
                                    pid: id
                                }, function(data) {
                                    createSourceSecData(data);
                                });
                            };

                            function createCoursesData(data) {
                                var selectGist = $("#courseId");
                                selectGist.html('');
                                selectGist.append(defaultOption);
                                if (data) {
                                    $.each(data, function(i, obj) {
                                        $('<option value="' + obj.id + '">' + obj.typeName + '</option>').appendTo(selectGist);
                                    });
                                }
                            }

                            function createProductsData(data) {
                                var selectGist = $("#packageId");
                                selectGist.html('');
                                selectGist.append(defaultOption);
                                if (data) {
                                    $.each(data, function(i, obj) {
                                        $('<option value="' + obj.id + '">' + obj.packageName + '</option>').appendTo(selectGist);
                                    });
                                }
                            }

                            function createSourceSecData(data) {
                                var selectGist = $("#sourceTypeSec");
                                selectGist.html('');
                                selectGist.append(defaultOption);
                                if (data) {
                                    $.each(data, function(i, obj) {
                                        $('<option value="' + obj.value + '">' + obj.text + '</option>').appendTo(selectGist);
                                    });
                                }
                            }

                            $(function() {
                                initCourses();
                                initProducts();

                                $("#sourceType").bind('change', function() {
                                    var id = $(this).find("option:selected").attr("id");
                                    initSourceSec(id);
                                });
                            });
                        </script>
                    </div>
                </div>
            </body>

            </html>