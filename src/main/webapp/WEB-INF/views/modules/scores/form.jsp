<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
        <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
            <html>

            <head>
                <title>${fns:getConfig('productName')}-学校管理</title>
                <%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
                    <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
                    <meta name="decorator" content="default" />
            </head>

            <body>
                <form class="form-horizontal" action="" id="queryForm" method="post" enctype="multipart/form-data" target="uploadTarget">
                    <ul id="breadcrumb" style="display: none">
                        <li>教务管理>考务管理>成绩管理</li>
                    </ul>
                    <div class="frame">
                        <div class="content">
                            <div class="content-area">
                                <div class="infoBlock">
                                    <h1 class="title">基本信息
									<c:if test="${fns:isHasRole('jwgl_export')}" >
									  <a id="exportBtn"><span id="add_class">Excel下载</span></a>
									</c:if>
                                        
                                    </h1>
                                    <div class="ib-content clearfix">
                                        <table style="width:100%">
                                            <tr>
                                                <td>班级名称：</td>
                                                <td class="secTd">${model.className}</td>
                                                <td style="text-align:right;">创建日期：</td>
                                                <td class="secTd">
                                                    <fmt:formatDate value="${model.addTime}" type="both" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>课程：</td>
                                                <td class="secTd">
                                     <select class="chosen-select form-control" id="" disabled="disabled">
									 	<option>${courseName }</option>
									 </select>
                                                </td>
                                                <td style="text-align:right;">报考学校：</td>
                                                <td class="secTd">
                                                    <!--
									<select class="chosen-select form-control" id="shoolId" disabled="disabled"></select>
									 -->
                                                    <select class="chosen-select form-control" id="" disabled="disabled">
									 	<option>${schoolsName }</option>
									 </select>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>班级标号：</td>
                                                <td colspan="3" class="secTd">${model.classNumber}</td>
                                            </tr>
                                            <tr>
                                            	<td>备注：</td>
                                            	<td class="secTd" colspan='3'>
                                      					<textarea rows="3" cols="20" style="line-height:1.2" id="remark" readOnly=true>${model.remark }</textarea>
                                            	</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">考试信息</h1>
                                    <div class="ib-content clearfix">
                                        <table style="width:100%">
                                            <tr>
                                                <td>考试形式：</td>
                                                <td class="secTd">
                                                    <select class="chosen-select form-control" id="examForm" disabled="disabled">
										<option value="">${examFormName }</option>
										<!--
										<c:forEach items="${fns:getParams(2,0)}" var="obj" varStatus="item">
					            			<option value="${obj.value}"  <c:if test="${model.examForm==obj.value}">selected="selected"</c:if>>${obj.text}</option>
					            		</c:forEach>
					            		 -->
									</select>
                                                </td>
                                                <td style="text-align:right;">考试状态：</td>
                                                <td class="secTd">
                                                    <select class="chosen-select form-control" id="examStatus" disabled="disabled">
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
                                                    <fmt:formatDate value="${model.theoryDate}" type="both" />
                                                </td>
                                                <td style="text-align:right;">考试场地：</td>
                                                <td class="secTd">${model.theoryAddress} </td>
                                            </tr>
                                            <tr>
                                                <td>实操日期：</td>
                                                <td class="secTd">
                                                    <fmt:formatDate value="${model.operationDate}" type="both" />
                                                </td>
                                                <td style="text-align:right;">考试场地：</td>
                                                <td class="secTd">${model.operationAddress}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">考试结果</h1>
                                    <div class="ib-content clearfix">
                                        <table style="width:100%">
                                            <tr>
                                                <td>报名数：</td>
                                                <td class="secTd">${model.examResult.totleNum}</td>
                                                <td style="text-align:right;">录入数：</td>
                                                <td class="secTd">${model.examResult.inputNum}</td>
                                            </tr>
                                            <tr>
                                                <td>合格数：</td>
                                                <td class="secTd">${model.examResult.qualifiedNum}</td>
                                                <td style="text-align:right;">合格率：</td>
                                                <td class="secTd">${model.examResult.qualifiedRate}</td>
                                            </tr>
                                            <tr>
                                                <td>缺考数：</td>
                                                <td colspan="3" class="secTd">${model.examResult.lackExamNum}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="btnCase clearfix">
                                    <button class="bc-cancel" id="goback" type="button">返回</button>
                                </div>
                                <p>学员成绩（${fn:length(model.applyOrders)}人）</p>
                                <table>
                                    <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>姓名</td>
                                            <td>手机号</td>
                                            <td>理论</td>
                                            <td>实操</td>
                                            <td>考试结果</td>
                                            <td>证书编号</td>
                                            <td>操作</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${model.applyOrders}" var="obj" varStatus="item">
                                            <tr>
                                                <td>${item.index+1}</td>
                                                <td>${obj.stuUserInfo.userName}</td>
                                                <td>${obj.stuUserInfo.mobile}</td>
                                                <td>${obj.userScores.theoryScores}</td>
                                                <td>${obj.userScores.poScores}</td>
                                                <td>${obj.userScores.examResultName}</td>
                                                <td>${obj.userScores.certificateNo}</td>
                                                <td><a href="#" onclick="obj.openModel('${model.id}','${obj.stuUserInfoId}','${obj.id}')">编辑</a></td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${fn:length(model.applyOrders)<1}">
                                            <tr>
                                                <td colspan="12"><i class="icon-warning-sign"></i>暂无相关记录</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <input name="id" value="${model.id}" type="hidden" />
                </form>

                <script src="${ctxStatic}/js/dateFormat.js"></script>
                <script src="${ctxStatic}/modules/scores/form.js"></script>

                <!-- 初始化页面checkbox -->
                <script type="text/javascript">
                    /**
                    		var defaultOption = '<option value="">---请选择---</option>';
                    		var courseId=${model.courseId!=null?model.courseId:'""'};
                    		var shoolId=${model.shoolId!=null?model.shoolId:'""'};
                    		**/
                    $(function() {
                        $("#exportBtn").bind("click", function() {
                            $("#queryForm").attr("action", ctx + "/exams/export/01");
                            $("#queryForm").submit();
                        });

                    });
                </script>
            </body>

            </html>