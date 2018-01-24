<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
                                                    <td>创建时间：</td>
                                                    <td class="secTd">

                                                        <fmt:formatDate value="${model.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>产品名称：</td>
                                                    <td class="secTd"><input type="text" id="packageName" name="" value="${model.packageName}"></td>
                                                </tr>
                                                <tr>
                                                    <td>产品状态：</td>
                                                    <td class="secTd">
                                                        <select id="status1">
									<option value="">---请选择---</option>
									<c:forEach items="${statusList}" var="obj" varStatus="item">
										<option value="${obj.id}" <c:if test="${obj.id==model.status}">selected="selected"</c:if>>${obj.value}</option>
						        	</c:forEach>
								</select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>适合工种：</td>
                                                    <td class="secTd"><input type="text" id="typeOfWork" name="" value="${model.typeOfWork}"></td>
                                                </tr>
                                                <tr>
                                                    <td>产品简介：</td>
                                                    <td class="secTd"><textarea id="summary" name="name" rows="8" cols="80">${model.summary}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <td>课程：</td>
                                                    <td class="secTd">
                                                        <ul class="add_class">
                                                            <c:forEach items="${appCouOldNameList}" var="appObj" varStatus="item">
                                                                <li>
                                                                    <select class="" name="" disabled="disabled">
																		<option value="">---请选择---</option>
																			<option value="${appObj.id}" selected="selected">${appObj.value}</option>
                                                                    </select>
                                                                    <input id="" name="" disabled="disabled" type="text" value="${appObj.authenticateGrade}">
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                        <!-- <span class="add_n">+新增课程</span> -->
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>备注：</td>
                                                    <td class="secTd"><textarea id="remarks" name="" rows="8" cols="80">${model.remarks}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <td>排序：</td>
                                                    <td class="secTd"><input type="text" id="sortNo" name="" value="${model.sortNo}"></td>
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
                                                        <input type="text" id="originalPrice" name="" value="${model.originalPrice}">
                                                    </td>
                                                    <td style="text-align:right;">优惠价：</td>
                                                    <td class="secTd">
                                                        <input type="text" id="price" name="" value="${model.price}">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><label for="youhui">优惠标志：</label></td>
                                                    <td colspan="3" class="secTd">
                                                        <div class="checkbox_box">
                                                            <input type="checkbox" <c:if test="${model.isDiscount=='1'}">checked="checked" </c:if> name="" id="isDiscount">
                                                            <div class="check-box">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="btnCase clearfix">
                                        <button class="bc-cancel" type="button" onclick="return goback();">取消</button>
                        
                                        <c:if test="${fns:isHasRole('yygl_edit')}" >
										   <button class="bc-confirm" id="modifyBtn" type="button" onclick="return update();">修 改</button>   
										 </c:if>	
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input id="id" value="${model.id}" type="hidden" />
                        <input id="par" value="${model.applyCourses}" type="hidden" />
                    </form>
                    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
                    <script src="js/index.js" type="text/javascript" charset="utf-8"></script>
                    <script src="${ctxStatic}/modules/coursesPackage/form.js"></script>
                    <script type="text/javascript">
                    function goback()
            		{window.location.href=config.productListUrl;	}
                        $(function() {
                            $(".li select").click(function() {
                                $(this).siblings("ul").append($(".add_class li:first").clone());
                                $(".add_class li:last").append("<span class='delete'></span>");
                            });
                            $(".add_n").click(function() {
                                $(this).siblings("ul").append($(".add_class li:first").clone());
                                $(".add_class li:last").append("<span class='delete'></span>");
                            })
                            $("body").on("click", ".delete", function() {
                                $(this).parent("li").remove();
                            });
                            $("body").on("change", ".checkbox_box input", function() {
                                var state = $(this).prop("checked");
                                if (state) {
                                    $(this).siblings(".check-box").addClass("active");
                                } else {
                                    $(this).siblings(".check-box").removeClass("active");
                                }
                            });
                            $(".checkbox_box input").each(function() {
                                var state = $(this).prop("checked");
                                if (state) {
                                    $(this).siblings(".check-box").addClass("active");
                                } else {
                                    $(this).siblings(".check-box").removeClass("active");
                                }
                            })
                        })
                    </script>
                </body>

                </html>