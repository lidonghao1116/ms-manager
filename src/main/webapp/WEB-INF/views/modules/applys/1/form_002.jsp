<!-- 教务管理 > 报名管理 > 补考 -->

<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
        <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
            <html>

            <head>
                <title>${fns:getConfig('productName')}-补考</title>
                <meta name="decorator" content="default" />
                <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
            </head>

            <body>

                <form class="form-horizontal">
                    <ul id="breadcrumb" style="display: none">
                        <li>教务管理>报名管理>补考</li>
                    </ul>
                    <div class="frame">
                        <div class="content">
                            <div class="content-area">
                                <div class="infoBlock">
                                    <h1 class="title">身份信息</h1>
                                    <div class="ib-content clearfix">
                                        <table>
                                            <tr>
                                                <td>姓名：</td>
                                                <td class="secTd">${stuUserInfoEntity.userName}</td>
                                            </tr>
                                            <tr>
                                                <td>手机号码：</td>
                                                <td class="secTd">${stuUserInfoEntity.mobile}</td>
                                            </tr>
                                            <tr>
                                                <td>学历：</td>
                                                <td class="secTd">${fns:getText(3,0,stuUserInfoEntity.education)}</td>
                                            </tr>
                                            <tr>
                                                <td>民族：</td>
                                                <td class="secTd">${fns:getText(6,0,stuUserInfoEntity.nation)}</td>
                                            </tr>
                                            <tr>
                                                <td>户籍地址：</td>
                                                <td class="secTd">${stuUserInfoEntity.address}</td>
                                            </tr>
                                            <tr>
                                                <td>紧急联系人：</td>
                                                <td class="secTd"><input type="text" id="contacts" name="contacts" value="${stuUserInfoEntity.contacts}" /></td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>身份证号：</td>
                                                <td class="secTd">${stuUserInfoEntity.certNo}</td>
                                            </tr>
                                            <tr>
                                                <td>年龄：</td>
                                                <td class="secTd">${stuUserInfoEntity.age}</td>
                                            </tr>
                                            <tr>
                                                <td>性别：</td>
                                                <td class="secTd">${stuUserInfoEntity.sex=='1'?"男":(stuUserInfoEntity.sex=='0'?"女":"未知")}</td>
                                            </tr>
                                            <tr>
                                                <td>籍贯：</td>
                                                <td class="secTd">${stuUserInfoEntity.birthplace}</td>
                                            </tr>
                                            <tr>
                                                <td>是否交金：</td>
                                                <td class="secTd">
                                                    <div class="radio_box">
                                                        <label for="no_sp">否</label>
                                                        <input type="radio" checked="" id="no_sp" name="isHasPf" value="0" <c:if test="${model.isHasPf=='0'}">checked="checked" </c:if>>
                                                        <div class="circle-box">
                                                            <span class="circle"></span>
                                                        </div>
                                                    </div>
                                                    <div class="radio_box">
                                                        <label for="yes_sp">是</label>
                                                        <input type="radio" id="yes_sp" name="isHasPf" value="1" <c:if test="${model.isHasPf=='1'}">checked="checked" </c:if>>
                                                        <div class="circle-box active">
                                                            <span class="circle"></span>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>紧急联系人电话：</td>
                                                <td class="secTd"><input type="text" id="contactPhone" name="contactPhone" value="${stuUserInfoEntity.contactPhone}" /></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">来源信息</h1>
                                    <div class="ib-content clearfix">
                                        <table style="width: 100%;">
                                            <tr>
                                                <td>来源：</td>
                                                <td class="secTd">
                                                    ${model.sourceWholeText}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>备注：</td>
                                                <td class="secTd">
                                                    <textarea id="sourceRemarks" name="sourceRemarks" rows="" cols="">${model.sourceRemarks}</textarea>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">报考信息</h1>
                                    <div class="ib-content clearfix">
                                        <table>
                                            <tr>
                                                <td>课程名称：</td>
                                                <td class="secTd">${model.typeName}</td>
                                            </tr>
                                            <tr>
                                                <td>是否考试：</td>
                                                <td class="secTd">${model.isExam=='1'?"是":"否"}</td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>课程等级：</td>
                                                <td class="secTd">
                                                    ${model.authenticateGrade}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>班级标号：</td>
                                                <td class="secTd">
                                                    <select id="classNumber" name="classNumber">
										<option value="">--请选择--</option>
						           		<c:forEach items="${courseClassList}" var="obj" varStatus="item">
											<option value="${obj.id}">${obj.classNumber}</option>
						            	</c:forEach>
									</select>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">收费信息</h1>
                                    <div class="ib-content clearfix">
                                        <table>
                                            <tr>
                                                <td>补考费：</td>
                                                <td class="secTd"><input type="text" id="makeupFee" name="makeupFee" value="${model.makeupFee}" /> 元</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="btnCase clearfix">
                                    <!-- history.go(-1) -->
                                    <button type="button" id="goBack" onclick="">取消</button>
									<c:if test="${fns:isHasRole('jwgl_edit')}" >
										  <button class="bc-confirm" id="passBtn" type="button" onclick="">通过</button>
									</c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <input id="id" value="${model.id}" type="hidden" />
                <script src="${ctxStatic}/modules/applys/1/form_002.js"></script>
                <script src="${ctxStatic}/modules/help.js"></script>
                <!-- 初始化页面checkbox -->
                <script type="text/javascript">
                    $(function() {
                        //initExamClass();
                        $(".radio_box input").click(function() {
                            $(this).siblings(".circle-box").addClass("active");
                            $(this).parent().siblings(".radio_box").children(".circle-box").removeClass("active");
                        })
                        $(".radio_box input").each(function() {
                            if ($(this).is(":checked")) {
                                $(this).siblings(".circle-box").addClass("active");
                                $(this).parent().siblings(".radio_box").children(".circle-box").removeClass("active");
                            }
                        })
                    });
                </script>
            </body>

            </html>