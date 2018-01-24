<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <html>

                <head>
                    <title>${fns:getConfig('productName')}-学校管理</title>
                    <%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
                        <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
                        <meta name="decorator" content="default" />
                </head>

                <body>
                    <form class="form-horizontal">
                        <ul id="breadcrumb" style="display: none">
                            <li>教务管理>班级管理</li>
                        </ul>
                        <!--内容区-->
                        <div class="frame">
                            <div class="content">
                                <div class="content-area">
                                    <div class="infoBlock">
                                        <h1 class="title">基本信息</h1>
                                        <div class="ib-content clearfix">
                                            <table style="width:100%">
                                                <tr>
                                                    <td> 创建时间：</td>
                                                    <td class="secTd" colspan="3">
                                                        <fmt:formatDate value="${model.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>班级名称：</td>
                                                    <td class="secTd">
                                                        <input readonly="readonly" type="text" id="className" name="" value="${model.className }" />
                                                    </td>
                                                    <td style="text-align:right;">课程：</td>
                                                    <td class="secTd">
                                                        <select disabled="disabled" class="" id="learnTypeId" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${appCouOldNameList}" var="obj" varStatus="item">
					            			<option value="${obj.id}" <c:if test="${model.courseId==obj.id}">selected="selected" </c:if>>${obj.value}</option>
					            		</c:forEach>
									</select>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td style="text-align:right;">报考学校：</td>
                                                    <td class="secTd">
                                                        <select disabled="disabled" class="" id="schoolId" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${schoolsList}" var="obj" varStatus="item">
					            			<option value="${obj.id}" <c:if test="${model.shoolId==obj.id}">selected="selected" </c:if>>${obj.schoolName}</option>
					            		</c:forEach>
									</select>
                                                    </td>
                                                    <td style="text-align:right;">班级标号：</td>
                                                    <td class="secTd"><input readonly="readonly" type="text" id="classNumber" name="" value="${model.classNumber }"></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="infoBlock">
                                        <h1 class="title">报考信息</h1>
                                        <div class="ib-content clearfix">
                                            <table style="width:100%">
                                                <tr>
                                                    <td style="text-align:right;">考试形式：</td>
                                                    <td class="secTd">
                                                        <select disabled="disabled" class="" id="examForm" name="">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
					           			<option value="${obj.value}" <c:if test="${model.examForm==obj.value}">selected="selected" </c:if>>${obj.text}</option>
					           		</c:forEach>
								</select>
                                                    </td>
                                                    <td style="text-align:right;">考试状态：</td>
                                                    <td class="secTd">
                                                        <select class="chosen-select form-control" id="examStatus">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(32,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${model.examStatus==obj.value}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>理论日期：</td>
                                                    <td class="secTd">
                                                        <input type="text" class="date-picker" id="theoryDate" name="startDate" value="${theoryDate }" data-date-format="yyyy-mm-dd" />
                                                    </td>
                                                    <td style="text-align:right;">考试场地：</td>
                                                    <td class="secTd">
                                                        <input type="text" name="" id="theoryAddress" value="${model.theoryAddress }">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>实操日期：</td>
                                                    <td class="secTd">
                                                        <input type="text" class="date-picker" id="operationDate" name="startDate" value="${operationDate }" data-date-format="yyyy-mm-dd" />
                                                    </td>
                                                    <td style="text-align:right;">考试场地：</td>
                                                    <td class="secTd">
                                                        <input type="text" id="operationAddress" name="" value="${model.operationAddress }">
                                                    </td>
                                                </tr>
                                                <tr>
	                                            	<td>备注：</td>
	                                            	<td class="secTd" colspan='3'>
                                       					<textarea rows="3" cols="20" style="line-height:1.2" id="remark">${model.remark }</textarea>
	                                            	</td>
	                                            </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="btnCase clearfix">
                                        <button class="bc-cancel" type="button" id="goback">取消</button>
									<c:if test="${fns:isHasRole('jwgl_edit')}" >
									  <button class="bc-confirm" id="modifyBtn" type="button">保 存</button>
									</c:if>
                                        
                                    </div>
                                    <br/> 学员（${fn:length(model.applyOrders)}人）
                                    <table>
                                        <thead>
                                            <tr>
                                                <td>序号</td>
                                                <td>姓名</td>
                                                <td>手机号</d>
                                                    <td>年龄</td>
                                                    <td>学历</td>
                                                    <td>是否交金</td>
                                                    <td>来源</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${model.applyOrders}" var="obj" varStatus="item">
                                                <tr>
                                                    <td>${item.index+1}</td>
                                                    <td>${obj.stuUserInfo.userName}</td>
                                                    <td>${obj.stuUserInfo.mobile}</td>
                                                    <td>${obj.stuUserInfo.age}</td>
                                                    <td>${fns:getText(3,0,obj.stuUserInfo.education)}</td>
                                                    <td>${obj.isHasPf=='1'?"是":"否"}</td>
                                                    <td>${obj.sourceWholeText}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>

                    </form>
                    <!--
	<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
	 -->
                    <script src="${ctxStatic}/modules/exams/form.js"></script>
                    <input id="id" value="${model.id}" type="hidden" />
                    <!-- 初始化页面checkbox -->
                    <script type="text/javascript">
                        var defaultOption = '<option value="">---请选择---</option>';
                        var courseId = $ {
                            model.courseId != null ? model.courseId : '""'
                        };
                        var shoolId = $ {
                            model.shoolId != null ? model.shoolId : '""'
                        };

                        function initCourses() {
                            $.post(ctx + '/params/courses', {}, function(data) {
                                createCoursesData(data);
                            });
                        };

                        function initSchools() {
                            $.post(ctx + '/params/schools', {}, function(data) {
                                createSchoolsData(data);
                            });
                        };

                        function createCoursesData(data) {
                            var selectGist = $("#courseId");
                            selectGist.html('');
                            selectGist.append(defaultOption);
                            if (data) {
                                $.each(data, function(i, obj) {
                                    if (courseId == obj.id) {
                                        $('<option value="' + obj.id + '" examForm="' + obj.examType + '" selected="selected">' + obj.typeName + '</option>').appendTo(selectGist);
                                    } else {
                                        $('<option value="' + obj.id + '" examForm="' + obj.examType + '">' + obj.typeName + '</option>').appendTo(selectGist);
                                    }
                                });

                                selectGist.bind('change', function() {
                                    $("#examForm").val($(this).find("option:selected").attr("examForm"));
                                });
                            }
                        }

                        function createSchoolsData(data) {
                            var selectGist = $("#shoolId");
                            selectGist.html('');
                            selectGist.append(defaultOption);
                            if (data) {
                                $.each(data, function(i, obj) {
                                    if (shoolId == obj.id) {
                                        $('<option value="' + obj.id + '"  selected="selected">' + obj.schoolName + '</option>').appendTo(selectGist);
                                    } else {
                                        $('<option value="' + obj.id + '" >' + obj.schoolName + '</option>').appendTo(selectGist);
                                    }
                                });
                            }
                        }

                        $(function() {
                            initCourses();
                            initSchools();
                        });
                    </script>
                </body>

                </html>