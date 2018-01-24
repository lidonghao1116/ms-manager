<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-学校管理</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
<body>
	<form class="form-horizontal">
		<ul id="breadcrumb" style="display: none">
			<li>教务管理>课程管理>上课时间管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
         			<div class="infoBlock">
						<h1 class="title">基本信息
 
					<c:if test="${fns:isHasRole('jwgl_edit')}" >
					  						
						    <span class="onOffShelf">是否启用：
			                	<label class="checkbox"><em></em><input type="checkbox" id="templateType" value="${model.templateType}"></label>
			           		</span> 
					</c:if>	
			            </h1>
						<div class="ib-content clearfix">
              				<table style="width:100%">
                				<tr>
									<td>模板名称：</td>
									<td class="secTd"><input type="text" id="templateName" name="templateName" value="${model.templateName}" /></td>
								</tr>
                				<tr>
									<td>开课周期：</td>
									<td class="secTd">
										<c:forEach items="${openCycleList}" var="obj" varStatus="item">
													<div class="checkbox_box">
													  <label for="monday">${obj.value}</label>
													  <input type="checkbox"  name="openCycle" value="${obj.id}" />
													  <div class="check-box">
													      <%-- <img src="img/input.png" alt=""> --%>
													  </div>
													</div>
				            </c:forEach>
                  	</td>
								</tr>
				                <tr>
				                	<td>上课时间：</td>
				                  	<td class="secTd">
					                	<select class="" id="beginTime" name="beginTime">
					                 		<option vlaue="">-请选择上课开始时间-</option>
					                    	<c:forEach items="${openingTimeList}" var="obj" varStatus="item">
					                    		<option vlaue="${obj.id}" <c:if test="${model.beginTime==obj.id}">selected="selected" </c:if>>${obj.value}</option>
								            </c:forEach>
					                    </select>~
					                    <select class="" id="endTime" name="endTime">
					                    	<option vlaue="">-请选择上课开始时间-</option>
					                    	<c:forEach items="${openingTimeList}" var="obj" varStatus="item">
					                    		<option vlaue="${obj.id}" <c:if test="${model.endTime==obj.id}">selected="selected" </c:if>>${obj.value}</option>
								            </c:forEach>
					                    </select>
				                  	</td>
				               	</tr>
				        	</table>
           				</div>
          			</div>
          			<div class="btnCase clearfix">
					<button class="bc-cancel" id="goback" type="button" onclick="return goBack();">取消</button>
				    <c:if test="${fns:isHasRole('jwgl_edit')}" >
					  <button class="bc-confirm" id="modifyBtn" type="button" onclick="return update();">修改模板</button>
					</c:if>		
					</div>
				</div>
			</div>
		</div>
	</form>
		<!--
	<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
	 -->
		<script src="${ctxStatic}/modules/coursesTime/form2.js"></script>
		<input id="par" value="${model.openCycle}" type="hidden"/>
		<input id="id" value="${model.id}" type="hidden"/>
		<!-- 初始化页面checkbox -->
		<script type="text/javascript">
			var flag=${update};
			if(flag){
				var boxs=$("#par").val();
				initCheckData(boxs,"openCycle");
			}
		</script>

	    <script type="text/javascript">
	    $(function() {
				if($("#templateType").val()=="0"){
					$(this).attr("checked","");
					$("#templateType").siblings("em").stop().animate({left:"1px"}).parent().css("background-color","#B3B3B3");
				}else if($("#templateType").val()=="1"){
					$(this).attr("checked","checked");
					$("#templateType").siblings("em").stop().animate({left:"17px"}).parent().css("background-color","#6F9937");
				}
	      $("body").on("change",".checkbox input",function(){
	      var state = $(this).prop("checked");
	      if (state){
	        $(this).val(1).siblings("em").stop().animate({left:"17px"}).parent().css("background-color","#6F9937");
	        document.getElementById("templateType").value="1";
	      } else {
	        $(this).val(0).siblings("em").stop().animate({left:"1px"}).parent().css("background-color","#B3B3B3");
	        document.getElementById("templateType").value="0";
	      }
	      });
				
				$("body").on("change",".checkbox_box input",function(){
					var state = $(this).prop("checked");
					if(state){
							$(this).siblings(".check-box").addClass("active");
					}else {
						$(this).siblings(".check-box").removeClass("active");
					}
				})
					$(".checkbox_box input").each(function(){
						var state = $(this).prop("checked");
						if(state){
								$(this).siblings(".check-box").addClass("active");
						}else {
							$(this).siblings(".check-box").removeClass("active");
						}
					})
	    })
	    </script>
</body>
</html>
