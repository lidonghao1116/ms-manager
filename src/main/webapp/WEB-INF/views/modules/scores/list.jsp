<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
            <html>

            <head>
                <title>${fns:getConfig('productName')}-成绩管理列表</title>
                <meta name="decorator" content="default" />
                <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
            </head>

            <body>
                <ul id="breadcrumb" style="display: none">
                    <li>教务管理&nbsp;>&nbsp;考务管理&nbsp;>&nbsp;成绩管理</li>
                </ul>
                <!--内容区-->
                <div class="frame">
                    <div class="content">
                        <div class="content-area">
                            <div class="ca-filter clearfix query_box">
                                <form class="form-horizontal" action="" id="queryForm" method="GET">
                                    <div class="query">
                                        <p>
                                            <label>班级名称</label>
                                            <input type="text" id="className" value="" />
                                        </p>
                                        <p>
                                            <label>班级标号</label>
                                            <input type="text" id="classNumber" name="" value="">
                                        </p>
                                        <p>
                                            <label>课程</label>
                                            <select class="" id="learnTypeId" name="">
												<option value="">--请选择--</option>
												<c:forEach items="${learnTypeList}" var="obj" varStatus="item">
														<option value="${obj.id}">${obj.typeName}</option>
												</c:forEach>
											</select>
                                        </p>
                                        <p>
                                            <label>报考学校</label>
                                            <select class="" id="shoolId" name="">
												<option value="">--请选择--</option>
												<c:forEach items="${schoolsList}" var="obj" varStatus="item">
													<option value="${obj.id}">${obj.schoolName}</option>
												</c:forEach>
											</select>
                                        </p>
                                        <a id="queryBtn" class="search-btn"></a>
                                    </div>
                                </form>
                            </div>
                            <!--
					<div class="top_btn">
						<a href="${ctx}/schools/form"><span id="add_class">+新增学校</span></a>
					</div>
					 -->
                            <div class="page-body">
                                <table id="grid-table"></table>
                                <div id="grid-pager"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <script src="${ctxStatic}/modules/scores/list.js"></script>
                <script type="text/javascript">
                    // var defaultOption = '<option value="">---请选择---</option>';
                    // var courseId = "";
                    // var shoolId = "";
                    // var examForm = "";

                    // function initCourses() {
                    //     $.post(ctx + '/params/courses', {}, function(data) {
                    //         createCoursesData(data);
                    //     });
                    // };

                    // function initSchools() {
                    //     $.post(ctx + '/params/schools', {}, function(data) {
                    //         createSchoolsData(data);
                    //     });
                    // };

                    // function createCoursesData(data) {
                    //     var selectGist = $("#courseId");
                    //     selectGist.html('');
                    //     selectGist.append(defaultOption);
                    //     if (data) {
                    //         $.each(data, function(i, obj) {
                    //             if (courseId == obj.id) {
                    //                 $('<option value="' + obj.id + '" examForm="' + obj.examType + '" selected="selected">' + obj.typeName + '</option>').appendTo(selectGist);
                    //             } else {
                    //                 $('<option value="' + obj.id + '" examForm="' + obj.examType + '">' + obj.typeName + '</option>').appendTo(selectGist);
                    //             }
                    //         });
                    //     }
                    // }

                    // function createSchoolsData(data) {
                    //     var selectGist = $("#shoolId");
                    //     selectGist.html('');
                    //     selectGist.append(defaultOption);
                    //     if (data) {
                    //         $.each(data, function(i, obj) {
                    //             if (shoolId == obj.id) {
                    //                 $('<option value="' + obj.id + '"  selected="selected">' + obj.schoolName + '</option>').appendTo(selectGist);
                    //             } else {
                    //                 $('<option value="' + obj.id + '" >' + obj.schoolName + '</option>').appendTo(selectGist);
                    //             }
                    //         });
                    //     }
                    // }

                    $(function() {
                        // initCourses();
                        // initSchools();
                    });
                </script>
            </body>

            </html>