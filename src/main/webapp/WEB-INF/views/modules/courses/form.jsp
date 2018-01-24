<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-课程管理</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
<body>
	<form class="form-horizontal">
		<ul id="breadcrumb" style="display: none">
			<li>教务管理>课程管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
          			<div class="infoBlock">
						<h1 class="title">基本信息</h1>
						<div class="ib-content clearfix">
              				<table>
								<tr>
									<td>课程名称：</td>
									<td class="secTd">
										<select id="courseName" name="">
											<option value="">-请选择课程-</option>
											<c:forEach items="${courses}" var="obj" varStatus="item">
					                    		<option value="${obj.courseId}">${obj.courseName}</option>
								            </c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td>证书名称：</td>
									<td class="secTd">
										<span id="certName"></span>
									</td>
								</tr>
								<tr>
									<td>考试形式：</td>
									<td class="secTd">
										<span id="examType"></span>
									</td>
								</tr>
								<tr>
									<td>发证机构：</td>
									<td class="secTd">
										<span id="authorityId"></span>
									</td>
								</tr>
							</table>

							<table>
								<tr>
									<td>鉴定等级：</td>
									<td class="secTd">
										<span id="authenticateGrade"></span>
									</td>
								</tr>
								<tr>
									<td>总课时：</td>
									<td class="secTd">
										<span id="totalHours"></span>
									</td>
								</tr>
							</table>

							<table style="width: 100%;">
								<tr>
									<td>备注：</td>
									<td class="secTd" style="padding-bottom: 10px;">
										<textarea id="remarks"></textarea>
									</td>
									
								</tr>
							</table>

							<ul>
								<li class="clearfix">
									<table>
										<tr>
											<td>上课时间：</td>
											<td class="secTd">
												<ul class="add_time">
													<li>
														<select  name="classTimes[0].classTimes" >
															<option vlaue="">-请选择上课时间-</option>
															<c:forEach items="${timeList}" var="obj" varStatus="item">
									                    		<option value="${obj.id}">${obj.value}</option>
												            </c:forEach>
														</select>
													</li>
												</ul>
												<span class="add_n">+新增上课时间</span>
											</td>
										</tr>
									</table>
								</li>
							</ul>
							<table style="width: 100%;">
								<tr>
									<td>是否交社保：</td>
									<td class="secTd" style="padding-bottom: 10px;">
										<div class="radio_box">
											<label for="yes">是</label>
											<input type="radio" name="isNeedHasPf"  value="1">
											<div class="circle-box">
												<span class="circle"></span>
											</div>
										</div>
										<div class="radio_box">
											<label for="no">否</label>
											<input type="radio" name="isNeedHasPf" value="0" checked="checked">
											<div class="circle-box active">
													<span class="circle"></span>
											</div>
										</div>
									</td>
									<td style="text-align:right">课程状态：</td>
									<td class="secTd" style="padding-bottom: 10px;">
										<div class="radio_box">
											<label for="yes">正常</label>
											<input type="radio" name="status" checked="true" value="01">
											<div class="circle-box active">
												<span class="circle"></span>
											</div>
										</div>
										<div class="radio_box">
											<label for="no">停止</label>
											<input type="radio" name="status" value="02">
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
						<div class="ib-content clearfix">
							<table>
								<tr>
									<td>鉴考费：</td>
									<td class="secTd"><input type="text" id="examFee" name="" value="" /> 元</td>
								</tr>
								<tr>
									<td>证书费：</td>
									<td class="secTd"><input type="text" id="certificateFee" name="" value="" /> 元</td>
								</tr>
							</table>

							<table>
								<tr>
									<td>其他费：</td>
									<td class="secTd"><input type="text" id="otherFee" name="" value="" /> 元</td>
								</tr>
							</table>
						</div>
					</div>
          			<div class="btnCase clearfix">
						<button class="bc-cancel" type="button" onclick="return goback();">取消</button >
						<button class="bc-confirm" id="modifyBtn" type="button" onclick="return add();">创建课程</button>
					</div>
				</div>
			</div>
		</div>
	</form>
		<script src="${ctxStatic}/modules/courses/form.js"></script>
		<script type="text/javascript">
			function goback(){
				window.location.href=config.coursesListUrl;
			}
			$(function(){
				$(".add_n").click(function(){
					if( $(".add_time li").length >2 ){
						if(!($(this).nextAll().hasClass("no_add"))){
							$(this).after("<span class='no_add'>最多可添加3个上课时间</span>");
						}
					}else {
						var cl=$(this).siblings("ul").children("li:first-child").clone();
						var num = $(".add_time li").length;
						cl.append("<span class='delete'></span>");
						cl.find("select").attr("name","classTimes["+num+"].classTimes")
						$(this).siblings("ul").append(cl);
					}
				})
				$("body").on("click",".delete",function(){
					$(this).parent("li").remove();
					for (var i = 0; i < $(".add_time li").length; i++) {
						$(".add_time li").eq(i).find("select").attr("name","classTimes["+i+"].classTimes")
					}
					if( $(".add_time li").length < 3){
							$(".no_add").remove();
					}
				})
				$(".radio_box input").click(function() {
						$(this).siblings(".circle-box").addClass("active");
						$(this).parent().siblings(".radio_box").children(".circle-box").removeClass("active");
				})
				$("#courseName").bind('change', function() {
					var courseId = $("#courseName").val();
					if(courseId == null || courseId==""){
						$("#certName,#authenticateGrade,#totalHours,#examType,#authorityId").text("");
						$("#remarks").val("");
						return;
					}
                    //课程等级
                    $.ajax({
                        url: config.getCertNameUrl,
                        type: "POST",
                        data: {
                            courseId: courseId
                        },
                        success: function(data) {
                            console.log(data);
                            if (data != "") {
                                $("#certName").text(data.certName);
                                $("#authenticateGrade").text(data.authenticateGrade);
                                $("#totalHours").text(data.totalHours);
                                $("#examType").text(data.examType);
                                $("#authorityId").text(data.authorityName);
                                $("#remarks").val(data.remarks);
                            }
                        }
                    });
                });
			})
		</script>
		
</body>
</html>
