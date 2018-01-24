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
                        <li>运营管理>销售管理>课程销售</li>
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
                                                <td>产品名称：</td>
                                                <td class="secTd"><input type="text" id="packageName" name="" value=""></td>
                                            </tr>
                                            <tr>
                                                <td>适合工种：</td>
                                                <td class="secTd"><input type="text" id="typeOfWork" name="" value=""></td>
                                            </tr>
                                            <tr>
                                                <td>产品简介：</td>
                                                <td class="secTd"><textarea id="summary" name="name" rows="8" cols="80"></textarea></td>
                                            </tr>
                                            <tr>
                                                <td>课程：</td>
                                                <td class="secTd">
                                                    <ul class="add_class">
                                                        <li>
                                                            <select name="className[0].classTimes">
																<option value="">---请选择---</option>
																<c:forEach items="${courses}" var="obj" varStatus="item">
																	<option value="${obj.id}">${obj.typeName}</option>
																</c:forEach>
                                                            </select>
                                                            <input id="" name="className[0].authenticateGrade" disabled="disabled" type="text" value="${appObj.authenticateGrade}">
                                                        </li>
                                                    </ul>
                                                    <span class="add_n">+新增课程</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>备注：</td>
                                                <td class="secTd"><textarea id="remarks" name="" rows="8" cols="80"></textarea></td>
                                            </tr>
                                            <tr>
                                                <td>排序：</td>
                                                <td class="secTd"><input type="text" id="sortNo" name="" value=""></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="infoBlock">
                                    <h1 class="title">费用信息</h1>
                                    <div class="ib-content clearfix">
                                        <table style="width:100%">
                                            <tr>
                                                <td>标准价：</td>
                                                <td class="secTd">
                                                    <input type="text" id="originalPrice" name="" value="">
                                                </td>
                                                <td style="text-align:right;">优惠价：</td>
                                                <td class="secTd">
                                                    <input type="text" id="price" name="" value="">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label for="youhui">优惠标志：</label></td>
                                                <td colspan="3" class="secTd">
                                                    <div class="checkbox_box">
                                                        <input type="checkbox" name="" id="isDiscount">
                                                        <div class="check-box">
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="btnCase clearfix">
                                    <button class="bc-cancel" type="button" id="goback">取消</button>
                                    <button class="bc-confirm" id="addBtn" type="button">创 建</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
                <script src="js/index.js" type="text/javascript" charset="utf-8"></script>
                <script src="${ctxStatic}/modules/coursesPackage/form.js"></script>
                <script type="text/javascript">
                    $(function() {
                        $(".add_n").click(function() {
                            $(this).siblings("ul").append($(".add_class li:first").clone());
                            $(".add_class li:last").append("<span class='delete'></span>");
                        })
                        $("body").on("click", ".delete", function() {
                            $(this).parent("li").remove();
                        })
                        $("body").on("change", ".checkbox_box input", function() {
                            var state = $(this).prop("checked");
                            if (state) {
                                $(this).siblings(".check-box").addClass("active");
                            } else {
                                $(this).siblings(".check-box").removeClass("active");
                            }
                        })
                        $(".add_class").on('change', "select", function() {
                            var $this = $(this);
                            var id = $this.children("option:selected").val();
                            $.ajax({
                                url: config.getAuthenticateGradeName,
                                type: "POST",
                                data: {
                                    id: id
                                },
                                success: function(data) {
                                    console.log(data);
                                    if (data != "") {
                                        $this.siblings("input").val(data.authenticateGrade);
                                    }
                                }
                            });
                        })
                    })
                </script>
            </body>

            </html>