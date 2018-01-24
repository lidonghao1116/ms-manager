<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
        <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
                                                <td>班级名称：</td>
                                                <td class="secTd">
                                                    <input type="text" id="className" name="" value="" />
                                                </td>
                                                <td style="text-align:right;">班级标号：</td>
                                                <td class="secTd"><input type="text" id="classNumber" name="" value=""></td>
                                            </tr>
                                            <tr>
                                                <td>课程：</td>
                                                <td class="secTd">
                                                    <select class="" id="learnTypeId" name="">
														<option value="">--请选择--</option>
														<c:forEach items="${learnTypeList}" var="obj" varStatus="item">
															<option value="${obj.id}" examform="${obj.examType}">${obj.typeName}</option>
														</c:forEach>
													</select>
                                                </td>
                                                <td style="text-align:right;">考试形式：</td>
                                                <td class="secTd">
                                                    <select class="" id="examForm" name="" disabled="disabled">
														<option value="">--请选择--</option>
														<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
															<option value="${obj.value}">${obj.text}</option>
														</c:forEach>
													</select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:right;">报考学校：</td>
                                                <td class="secTd">
                                                    <!-- 
									<select class="" id="shoolId" name="">
									</select>
									 -->
                                                    <select class="" id="schoolId" name="">
														<option value="">--请选择--</option>
														<c:forEach items="${schoolsList}" var="obj" varStatus="item">
															<option value="${obj.id}">${obj.schoolName}</option>
														</c:forEach>
													</select>
													<div>
													<span style="color:#ccc;font-size:12px">（新增前往：课程管理->报考学校）</span>
													</div>
                                                </td>
                                                <td style="text-align:right;">课程等级：</td>
                                                <td class="secTd">
                                                    <input id="authenticateGrade" name="authenticateGrade" disabled="disabled" type="text" value="">
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">报考信息</h1>
                                    <div class="ib-content clearfix">
                                        <table style="width:100%">
                                            <tr>
                                                <td>理论日期：</td>
                                                <td class="secTd">
                                                    <div class="form-group">
                                                        <div class="col-md-4">
                                                            <div class="input-group">
                                                                <input type="text" class="date-picker" id="theoryDate" name="startDate" value="" data-date-format="yyyy-mm-dd" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td style="text-align:right;">考试场地：</td>
                                                <td class="secTd">
                                                    <input type="text" name="" id="theoryAddress" value="">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>实操日期：</td>
                                                <td class="secTd">
                                                    <div class="form-group">
                                                        <div class="col-md-4">
                                                            <div class="input-group">
                                                                <input type="text" class="date-picker" id="operationDate" name="startDate" value="" data-date-format="yyyy-mm-dd" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td style="text-align:right;">考试场地：</td>
                                                <td class="secTd">
                                                    <input type="text" id="operationAddress" name="" value="">
                                                </td>
                                            </tr>
                                            <tr>
                                            	<td>备注：</td>
                                            	<td class="secTd" colspan='3'>
                                            		<div class="form-group">
                                                        <div class="col-md-8">
                                                            <div class="input-group">
                                            					<textarea rows="3" cols="20" style="line-height:1.2" id="remark"></textarea>
                                            				</div>
                                            			</div>
                                            		</div>
                                            	</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="btnCase clearfix">
                                    <button class="bc-cancel" id="goback" type="button">取消</button>
                                    
									   <button class="bc-confirm" id="addBtn" type="button">保 存</button>
									
                                    
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
                <script src="${ctxStatic}/modules/exams/form.js"></script>
                <!-- 初始化页面checkbox -->
                <script type="text/javascript">
                    var defaultOption = '<option value="">---请选择---</option>';
                 
                    $(function() {
                        $("#learnTypeId").bind('change', function() {
                            $("#examForm").val($(this).find("option:selected").attr("examform"));
                            //课程等级
                            $.ajax({
                                url: config.getAuthenticateGradeName,
                                type: "POST",
                                data: {
                                    id: GetSelectValue("#learnTypeId")
                                },
                                success: function(data) {
                                    console.log(data);
                                    if (data != "") {
                                        $("#authenticateGrade").val(data.authenticateGrade);
                                    }
                                }
                            });
                        });
                    });
                </script>
            </body>

            </html>