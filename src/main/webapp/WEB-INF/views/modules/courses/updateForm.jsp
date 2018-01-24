<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
									<td class="secTd">${model.typeName }<input type="hidden" id="typeName" name=""></td>
								</tr>
								<tr>
								<td>证书名称：</td>
									<td class="secTd">${model.certName }<input type="hidden" id="certName" name=""></td>
								</tr>
								<tr>
									<td>考试形式：</td>
									<td class="secTd">
									${model.examTypeName }<input type="hidden" id="examTypeName" name="">
										<%-- <select id="examType" name="examType">
											<option value="">-请选择考试形式-</option>
					                    	<c:forEach items="${examType}" var="obj" varStatus="item">
					                    		<option value="${obj.id}" <c:if test="${model.examType==obj.id}">selected="selected" </c:if>>${obj.value}</option>
								            </c:forEach>
										</select> --%>
									</td>
								</tr>
								<tr>
									<td>发证机构：</td>
									<td class="secTd">
										${model.authorityName }<input type="hidden" id="authorityName" name="">
										<%-- <select id="authorityId" name="authorityId">
											<option value="">-请选择发证机构-</option>
											<c:forEach items="${certList}" var="obj" varStatus="item">
							                	<option value="${obj.id}" <c:if test="${model.authorityId==obj.id}">selected="selected" </c:if>>${obj.value}</option>
										     </c:forEach>
										</select> --%>
									</td>
								</tr>
							</table>

							<table>
								<tr>
									<td>鉴定等级：</td>
									<td class="secTd">
									${model.authenticateGrade }<input type="hidden" id="authenticateGrade" name="">
										<%-- <select id="authenticateGrade" name="authenticateGrade">
											<option value="">-请选择鉴定等级-</option>
											<c:forEach items="${authenticateGrade}" var="obj" varStatus="item">
					                    		<option value="${obj.id}" <c:if test="${model.status==obj.id}">selected="selected" </c:if>>${obj.value}</option>
								            </c:forEach>
										</select> --%>
									</td>
								</tr>
								<tr>
									<td>总课时：</td>
									<td class="secTd">${model.totalHours}<input  type="hidden" id="totalHours" name="totalHours"  /></td>
								</tr>
							</table>

							<table style="width: 100%;">
								<tr>
									<td>备注：</td>
									<td class="secTd" style="padding-bottom: 10px;"><textarea id="remarks" name="remarks" rows="" cols="">${model.remarks}</textarea></td>
								</tr>
							</table>

							<ul>
								<li class="clearfix">
									<table>
										<tr>
											<td>上课时间：</td>
											 <td class="secTd">
												
												<ul class="add_time">
													<c:forEach items="${courseTimeTableList}" var="cttlObj" varStatus="item">
														<li>
															<select  name="classTimes[0].classTimes" >
																<option value="">-请选择上课时间-</option>
																<c:forEach items="${timeList}" var="obj" varStatus="item">
										                    		<option value="${obj.id}" <c:if test="${cttlObj.id==obj.id}">selected="selected" </c:if>>${obj.value}</option>
													            </c:forEach>
															</select>
														</li>
													</c:forEach>
													
												</ul>
												
												<c:if test="${fns:isHasRole('jwgl_edit')}" >
												 <span class="add_n">+新增上课时间</span>
												</c:if>

												
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
											<input type="radio" name="isNeedHasPf"  value="1" <c:if test="${model.isNeedHasPf=='1'}">checked="checked" </c:if>>
											<div class="circle-box active">
												<span class="circle"></span>
											</div>
										</div>
										<div class="radio_box">
											<label for="no">否</label>
											<input type="radio" name="isNeedHasPf" value="0" <c:if test="${model.isNeedHasPf=='0'}">checked="checked" </c:if>>
											<div class="circle-box">
												<span class="circle"></span>
											</div>
										</div>
									</td>
									<td style="text-align:right;">课程状态：</td>
									<td class="secTd" style="padding-bottom: 10px;">
										<div class="radio_box">
											<label for="yes">正常</label>
											<input type="radio" name="status" <c:if test="${model.status=='01'}">checked="true" </c:if> value="01">
											<div class="circle-box active">
												<span class="circle"></span>
											</div>
										</div>
										<div class="radio_box">
											<label for="no">停止</label>
											<input type="radio" name="status" <c:if test="${model.status=='02'}">checked="true" </c:if> value="02">
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
									<td class="secTd"><input type="text" id="examFee" name="examFee" value="${model.examFee}" /> 元</td>
								</tr>
								<tr>
									<td>证书费：</td>
									<td class="secTd"><input type="text" id="certificateFee" name="certificateFee" value="${model.certificateFee}" /> 元</td>
								</tr>
							</table>

							<table>
								<tr>
									<td>其他费：</td>
									<td class="secTd"><input type="text" id="otherFee" name="otherFee" value="${model.otherFee}" /> 元</td>
								</tr>
							</table>
						</div>
					</div>
					<input id="id" value="${model.id}" type="hidden"/>
          			<div class="btnCase clearfix">
						<button class="bc-cancel"  onclick="return goback();" type="button">取消</button>
						<c:if test="${fns:isHasRole('jwgl_edit')}" >
						 <button class="bc-confirm" id="modifyBtn" type="button" onclick="return update();">修改课程</button>
						</c:if>			
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
				$(".radio_box input").each(function() {
					if($(this).is(":checked")) {
						$(this).siblings(".circle-box").addClass("active");
						$(this).parent().siblings(".radio_box").children(".circle-box").removeClass("active");
					}
				})
			})
		</script>

</body>
</html>
