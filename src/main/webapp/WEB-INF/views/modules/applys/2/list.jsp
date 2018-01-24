<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
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
                    <li>教务管理&nbsp;>&nbsp;报名管理&nbsp;>&nbsp;已处理&nbsp;>&nbsp;已报名</li>
                </ul>
                <!--内容区-->
                <div class="frame">
                    <div class="content">
                        <div class="content-tab clearfix">
                            <div class="clearfix">
                                <a class="ct_on"><b></b>已报名</a>
                                <a href="${ctx}/orders/list?type=NOT_MATCH"><b></b>不通过</a>
                                <a href="${ctx}/orders/list?type=OUT_APPLY"><b></b>已退学</a>
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
                                            <label>来源</label>
                                            <select name="sourceType" id="sourceType">
		  								<option value="">--请选择--</option>
										<c:forEach items="${fns:getParams(5,0)}" var="obj" varStatus="item">
					            			<option value="${obj.value}" id="${obj.id}">${obj.text}</option>
					            		</c:forEach>
		  							</select>
                                            <select name="sourceTypeSec" id="sourceTypeSec">
		  								<option value="">--请选择--</option>
		  							</select>
                                        </p>
                                        <p>
                                            <label>产品名称</label>
                                            <!-- 
		  							<select id="packageId1" name="packageId1">
		  								<option value=""></option>
		  							</select>
		  							 -->
                                            <select class="" id="packageId" name="packageId">
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
                                            <select class="" id="learnTypeId" name="learnTypeId">
										<option value="">--请选择--</option>
										<c:forEach items="${learnTypeList}" var="obj" varStatus="item">
					            			<option value="${obj.id}">${obj.typeName}</option>
					            		</c:forEach>
									</select>

                                        </p>
                                        <p>
                                            <label>处理时间</label>
                                            <input type="text" class=" date-picker" id="startDate" name="startDate" value="" data-date-format="yyyy-mm-dd" />-
                                            <input type="text" class=" date-picker" id="endDate" name="endDate" value="" data-date-format="yyyy-mm-dd" />
                                        </p>
                                        <p>
                                            <label>订单类别</label>
                                            <select id="orderStatus" name="orderStatus">
		  								<option value="">---请选择---</option>
		  								<c:forEach items="${orderStatusList}" var="obj" varStatus="item">
					            			<option value="${obj.id}" id="${obj.id}">${obj.value}</option>
					            		</c:forEach>
		  							</select>
                                        </p>
                                        <a id="queryBtn" class="search-btn"></a>
                                        <br/>

                                    </div>
                                </form>
                            </div>
                            <div class="top_btn">
                              <c:if test="${fns:isHasRole('jwgl_edit')}" >
								 <a href="${ctx}/orders/form"><span id="add_class">+新增学员</span></a>
							  </c:if>
							  <c:if test="${fns:isHasRole('jwgl_export')}" >
								  <a id="exportBtn"><span id="download">导出EXCEL表</span></a>
							
								  <a style="color:grey;margin-left:5px;">请在上方选择导出条件</a>
							  </c:if>
                            </div>
                            <div class="page-body">
                                <table id="grid-table"></table>
                                <div id="grid-pager"></div>
                            </div>
                        </div>
                        <script src="${ctxStatic}/modules/applys/2/list.js"></script>
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
                                var selectGist = $("#packageId1");
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

                            function addFrom() {
                                alert("1");
                            }
                        </script>
                    </div>
                </div>
            </body>

            </html>