<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>

<head>
<title>${fns:getConfig('productName')}-已报名</title>
<meta name="decorator" content="default" />
<link rel="stylesheet"
	href="${ctxStatic}/plugins/date-time/css/global.css" />
<style>
span#edit_classNumbe {
	background-color: #6F9937;
	color: #fff;
	padding: 3px 9px;
	border-radius: 2px;
	font-size: 12px;
	outline: none;
	margin-bottom: 13px;
	margin-top: 4px;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}
</style>
</head>

<body>
	<form class="form-horizontal">
		<input id="stuUserId" value="${model.id}" type="hidden" />
		<ul id="breadcrumb" style="display: none">
			<li>教务管理>报名管理>已报名</li>
		</ul>
		<div class="frame">
			<div class="content">
				<div class="content-area">
			        <c:if test="${fns:isHasRole('jwgl_edit')}" >
						<span id="dropout" class="top_button">强制退学</span>
					</c:if>
					<div class="infoBlock">
						<h1 class="title">处理信息</h1>
						<div class="ib-content clearfix">
							<table>
								<tr>
									<td>报名时间：</td>
									<td class="secTd"><fmt:formatDate
											value="${model.orderTime}" type="both" /></td>
								</tr>
								<tr>
									<td>审核时间：</td>
									<td class="secTd"><fmt:formatDate
											value="${model.handleTime}" type="both" /></td>
								</tr>
								<tr>
									<td>最后处理：</td>
									<td class="secTd"><fmt:formatDate
											value="${model.modifyTime}" type="both" /></td>
								</tr>
								<tr>
									<td>订单类别：</td>
									<td class="secTd" id="">${model.formTypeName}</td>
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
									<td>最后处理人：</td>
									<td class="secTd">${model.modifyName}</td>
								</tr>
							</table>
						</div>
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
									<td class="secTd"><select class="" id="education">
											<option value="">--请选择--</option>
											<c:forEach items="${fns:getParams(3,0)}" var="obj"
												varStatus="item">
												<option value="${obj.value}"
													<c:if test="${obj.value==stuUserInfoEntity.education}">selected="selected"</c:if>>${obj.text}</option>
											</c:forEach>
									</select> <!-- ${fns:getText(3,0,stuUserInfoEntity.education)} --></td>
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
									<td class="secTd"><input type="text" id="contacts"
										name="contacts" value="${stuUserInfoEntity.contacts}" /></td>
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
									<td>联系地址：</td>
									<td class="secTd"><input type="text" id="contactAddress"
										name="" value="${stuUserInfoEntity.contactAddress}"></td>
								</tr>
								<tr>
									<td>紧急联系人电话：</td>
									<td class="secTd"><input type="text" id="contactPhone"
										name="contactPhone" value="${stuUserInfoEntity.contactPhone}" /></td>
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
									<td class="secTd">${model.sourceWholeText}</td>
								</tr>
								<tr>
									<td>备注：</td>
									<td class="secTd" style="padding-bottom: 10px;"><textarea
											id="sourceRemarks" name="sourceRemarks" rows="" cols="">${model.sourceRemarks}</textarea></td>
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
									<td class="secTd">${goOnClassTime }</td>
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
									<td class="secTd">${model.authenticateGrade}</td>
								</tr>
								<tr>
									<td>班级标号：</td>
									<td class="secTd"><select disabled="disabled"
										id="classNumberUpdate" name="classNumber">
											<option value="">--请选择--</option>
											<option value="${classNumberId}" selected="selected">${classNumber}</option>
									</select> 
									
							                    <c:if test="${fns:isHasRole('jwgl_edit')}" >
						                         <span id="edit_classNumbe">修改</span>
						                       </c:if> 
									</td>
								</tr>
								<tr>
									<td>是否交金：</td>
									<td class="secTd">
										<!-- <select class="chosen-select form-control" id="isHasPf">
																<option value="">--请选择--</option>
																<option value="1" <c:if test="${model.isHasPf=='1'}">selected="selected" </c:if>>是</option>
																<option value="0" <c:if test="${model.isHasPf=='0'}">selected="selected" </c:if>>否</option>
                                                            </select> -->
										<div class="radio_box">
											<label for="yes_sp">是</label> <input type="radio" id="yes_sp"
												name="isHasPf" value="1"
												<c:if test="${model.isHasPf=='1'}">checked="checked" </c:if>>
											<div class="circle-box">
												<span class="circle"></span>
											</div>
										</div>
										<div class="radio_box">
											<label for="no_sp">否</label> <input type="radio" id="no_sp"
												name="isHasPf" value="0"
												<c:if test="${model.isHasPf=='0'}">checked="checked" </c:if>>
											<div class="circle-box">
												<span class="circle"></span>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="infoBlock">
						<h1 class="title">收费信息</h1>
						<div class="ib-content clearfix" id="normal">
							<table>
								<tr>
									<td>学费&nbsp;：</td>
									<td class="secTd">${model.schoolFee}</td>
								</tr>
								<tr>
									<td>押金费：</td>
									<input type="text" style="display:none;" id="isDepositClearFlag">
									<c:if test="${model.isDeposit=='0'}">
										<td class="secTd">否</td>
									</c:if>
									<c:if test="${model.isDeposit=='1'}">
									    <td class="secTd">${model.deposit}
									    <c:if test="${model.isDepositClear=='0'}">	
									         <c:if test="${fns:isHasRole('jwgl_edit')}" >
												<button class="btnBase confirm" id="depositNoClear"  type="button" onclick="" >待退还</button>	
											 </c:if> 	
										     <c:if test="${fns:isHasRole('jwgl_edit')==false}" >
												<button   type="button"  disabled="disabled" >待退还</button>	
											 </c:if>   
						                  	
					                          				   
										  </c:if> 
										  <c:if test="${model.isDepositClear=='1'}">
											  <button  id="depositIsClear" type="button" disabled="disabled" onclick="">已退还</button>
											  <span><fmt:formatDate value="${model.depositClearTime }" type="both" /></span>
										  </c:if>	
										   <button  id="depositIsClear2" type="button" disabled="disabled" style="display:none;" onclick="">已退还</button>
									    </td>	
									</c:if>
								</tr>
								<tr>


								</tr>
							</table>
							<table>
								<tr>
									<td>分期首付款：</td>
									<input type="text" style="display:none;" id="isClearFlag"> 
									<c:if test="${model.isStaging=='0'}">
										<td class="secTd">否</td>
									</c:if>
									<c:if test="${model.isStaging=='1'}">
										<td class="secTd">${model.firstPay}
										  <c:if test="${model.isClear=='0'}">
										      <c:if test="${fns:isHasRole('jwgl_edit')}" >
												<button class="btnBase confirm" id="noClear" type="button" onclick="" >待结清</button>
											 </c:if> 	
										     <c:if test="${fns:isHasRole('jwgl_edit')==false}" >
												<button  type="button" disabled="disabled"  >待结清</button>
											 </c:if> 		
										  </c:if> 
										  <c:if test="${model.isClear=='1'}">
											  <button  id="isClear" type="button" disabled="disabled" onclick="">已结清</button>
											  <span><fmt:formatDate value="${model.stagingClearTime }" type="both" /></span>
										  </c:if>
										  <button  id="isClear2" style="display:none;" type="button" disabled="disabled" onclick="">已结清</button>	
										</td>		
									</c:if>
								</tr>
							</table>
						</div>
						<div class="ib-content clearfix" id="makeUp">
							<table>
								<tr>
									<td>补考费：</td>
									<td class="">${model.makeupFee}</td>
								</tr>
								<tr>
									<td>其他费：</td>
									<td class=""><input type="text" id="orterFee"
										name="orterFee" value="${model.orterFee}" /></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="btnCase btnCase_sp clearfix">
						<button type="button" id="goback" onclick="">返回</button>     
			         <c:if test="${fns:isHasRole('jwgl_edit')}" >
						<button class="bc-confirm" id="modifyBtn" type="button">保存</button>
					 </c:if>  	
					</div>
				</div>
			</div>
		</div>
	</form>
	<input id="id" value="${model.id}" type="hidden" />
	<input id="formType" value="${model.formType}" type="hidden" />
	<input id="courId" value="${model.courseId}" type="hidden" />
	<div class="module">
		<div class="popup" id="returnFee">
			<h3>确定要为该学员办理退学吗？</h3>
			<ul class="iptList">
				<li class="iptItem"><span class="iptTitle"> 退费&nbsp;: </span>
					<div class="ctnRt feeWrapper">
						<input type="text" id="retreatFee" name="retreatFee">
					</div> 元</li>
			</ul>
			<div class="btnBox">
				<a href="#" class="btnBase cancel">取消</a> <a id="retreatFeeBtn"
					class="btnBase confirm">确认</a>
			</div>
		</div>
	</div>
     
	<input type="hidden" id="flag" name="" value="${update}">
	<script src="${ctxStatic}/modules/applys/2/form.js"></script>
	<script src="${ctxStatic}/modules/help.js"></script>

	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
                    var flag = $("#flag").val();
                    $(function() {
                        initExamClass();
                        if ($("#formType").val() == "01") { //正常
                            $("#makeUp").hide();
                            $("#normal,#dropout").show();
                        } else if ($("#formType").val() == "02") { //补考
                            $("#normal,#dropout").hide();
                            $("#makeUp").show();

                        }
                        if (!flag) {
                            initProducts();
                            $("#sourceType").bind('change', function() {
                                $("#sourceValueDiv").html("");
                                var id = $(this).find("option:selected").attr("id");
                                initSourceSec(id, '');
                            });

                            $("#sourceTypeSec").bind('change', function() {
                                var pid = GetSelectValue("#sourceType");
                                if (pid == '01') {
                                    var id = GetSelectValue("#sourceTypeSec");
                                    initSourceValue(id, '');
                                } else if (pid == '02') {
                                    $("#sourceValueDiv").html("");
                                    $("#sourceValueDiv").append(sourceValueInput);
                                }
                            });

                            $("#packageId").bind('change', function() {
                                var values = $("#packageId").find("option:selected").attr("ck");
                                initCourses(values, '');
                            });
                            //修改班级标号
                            $("#edit_classNumbe").bind("click", function() {
                                var params = {
                                    id: GetSelectValue("#classNumberUpdate"),
                                    courseId: $("#courId").val()
                                };

                                $.ajax({
                                    url: config.examsGetIsExamsAvailable,
                                    type: "POST",
                                    data: params,
                                    success: function(data) {
                                        if (data.success) {
                                            var dataJD = data.jsonData;
                                            if (dataJD != "" && dataJD != null) {
                                                $("#classNumberUpdate").removeAttr("disabled");
                                                var html = "<option value=''>--请选择--</option>";
                                                $.each(dataJD, function(i, item) {
                                                    if (GetSelectValue("#classNumberUpdate") == item.id) {
                                                        html += "<option value='" + item.id + "' selected='selected' >" + item.classNumber + "</option>";
                                                    } else {
                                                        html += "<option value='" + item.id + "' >" + item.classNumber + "</option>";
                                                    }

                                                })
                                                $("#classNumberUpdate").html(html);
                                                $("#edit_classNumbe").unbind("click");
                                            } else if (dataJD == "" || dataJD == null) {
                                                layer.msg("无待申报班级可更换");
                                            }
                                        } else {
                                            layer.msg("班级已申报，无法更换班级标号");
                                        }
                                    }
                                });
                            });
                        }
                        $("body").on("change", ".checkbox_box input", function() {
                            var state = $(this).prop("checked");
                            if (state) {
                                $(this).siblings(".check-box").addClass("active");
                            } else {
                                $(this).siblings(".check-box").removeClass("active");
                            }
                        })
                        $(".checkbox_box input").each(function() {
                            var state = $(this).prop("checked");
                            if (state) {
                                $(this).siblings(".check-box").addClass("active");
                            } else {
                                $(this).siblings(".check-box").removeClass("active");
                            }
                        })
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
                            // 强制退学
                        $("#dropout").click(function() {
                                $(".module").show();
                            })
                         
                            // 取消退学
                        $(".cancel").click(function() {
                                $(".module").hide();
                            })  
                         
                            // 确认退学
                        $(".confirm").click(function() {

                        })
                       
                      
                          $("#noClear").click(function() {
                              layer.confirm('确认学员付清尾款？',
                            		    {title:'分期结清'}, 
                            		    function(index) {
                            		     $("#isClearFlag").attr("value","1");
                            		     $("#noClear").attr("style","display:none;");
                            		     $("#isClear2").attr("style","display:inline-block;");
                                    	  layer.close(index);
                                      });
                            })
                             $("#depositNoClear").click(function() {
                              layer.confirm('确认退还学生押金？',
                            		    {title:'押金退还'}, 
                            		    function(index) {
                            		     $("#isDepositClearFlag").attr("value","1");
                            		     $("#depositNoClear").attr("style","display:none;");
                            		     $("#depositIsClear2").attr("style","display:inline-block;");
                                    	  layer.close(index);
                                      });
                            })
                            
                       
                    })
                </script>

</body>

</html>