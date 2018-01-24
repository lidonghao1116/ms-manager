<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
            <html>

            <head>
                <title>${fns:getConfig('productName')}-已退学详情</title>
                <meta name="decorator" content="default" />
                <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
            </head>

            <body>

                <form class="form-horizontal">
                    <ul id="breadcrumb" style="display: none">
                        <li>教务管理>报名管理>已退学详情</li>
                    </ul>
                    <div class="frame">
                        <div class="content">
                            <div class="content-area">
                                <div class="infoBlock">
                                    <h1 class="title">处理信息</h1>
                                    <div class="ib-content clearfix">
                                        <table>
                                            <tr>
                                                <td>报名时间：</td>
                                                <td class="secTd">
                                                    <fmt:formatDate value="${model.orderTime}" type="both" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>审核时间：</td>
                                                <td class="secTd">
                                                    <fmt:formatDate value="${model.handleTime}" type="both" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>退学时间：</td>
                                                <td class="secTd">
                                                    <fmt:formatDate value="${model.retreatFeeDate}" type="both" />
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>审核人：</td>
                                                <td class="secTd">${model.handlerName}</td>
                                            </tr>
                                            <tr>
                                                <td>处理人：</td>
                                                <td class="secTd">${model.modifyName}</td>
                                            </tr>
                                        </table>
                                    </div>
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
                                                    <td class="secTd">${stuUserInfoEntity.educationName}</td>
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
                                                    <td class="secTd">${stuUserInfoEntity.contacts}</td>
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
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td>紧急联系人电话：</td>
                                                    <td class="secTd">${stuUserInfoEntity.contactPhone}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="infoBlock">
                                        <h1 class="title">来源信息</h1>
                                        <div class="ib-content clearfix">
                                            <table>
                                                <tr>
                                                    <td>来源：</td>
                                                    <td class="secTd">
                                                        ${model.sourceWholeText}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>备注：</td>
                                                    <td class="secTd">${model.sourceRemarks}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="infoBlock">
                                        <h1 class="title">报考信息</h1>
                                        <div class="ib-content clearfix">
                                            <table>
                                                <tr>
                                                    <td>产品名称：</td>
                                                    <td class="secTd">${model.productName}</td>
                                                </tr>
                                                <tr>
                                                    <td>课程名称：</td>
                                                    <td class="secTd">${model.typeName}</td>
                                                </tr>
                                                <tr>
                                                    <td>上课时间：</td>
                                                    <td class="secTd">${goOnClassTime}</td>
                                                </tr>
                                                <tr>
                                                    <td>是否考试：</td>
                                                    <td class="secTd">${model.isExam=='1'?"是":"否"}</td>
                                                </tr>
                                            </table>
                                            <table>
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td>课程等级：</td>
                                                    <td class="secTd">
                                                        ${model.authenticateGrade}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>班级标号：</td>
                                                    <td class="secTd">${fns:getClassNumber(model.classNumber)}</td>
                                                </tr>
                                                <tr>
                                                    <td>是否交金：</td>
                                                    <td class="secTd">${model.isHasPf=='1'?"是":"否"}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="infoBlock">
                                        <h1 class="title">收费信息</h1>
                                        <div class="ib-content clearfix">
                                            <table>
                                                <tr>
                                                    <td>学费：</td>
                                                    <td class="secTd">${model.schoolFee}</td>
                                                </tr>
                                                <tr>
                                                    <td>退费：</td>
                                                    <td class="secTd">${model.retreatFee}</td>
                                                </tr>
                                            </table>
                                            <table>
                                                <tr>
                                                    <td>押金费：</td>
                                                    <td class="secTd">${model.deposit}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="btnCase btnCase_sp clearfix">
                                        <button class="bc-cancel" type="button" onclick="history.go(-1)">返回</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                </form>
            </body>

            </html>