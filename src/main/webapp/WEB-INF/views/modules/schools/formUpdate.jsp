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
		<li>教务管理>课程管理>报考学校</li>
	</ul>
	<!--内容区-->
	<div class="frame">
		<div class="content">
			<div class="content-area">
				<div class="infoBlock">
					<h1 class="title">基本信息-修改</h1>
					<div class="ib-content clearfix">
		 			<table style="width:100%">
						<tr>
							<td>学校名称：</td>
							<td class="secTd">
								${model.schoolName}
							</td>
						</tr>
		                <tr>
							<td>学校地址：</td>
							<td class="secTd">
								<input type="text" id="schoolAddress" name="" value="${model.schoolAddress}">
							</td>

						</tr>
						<tr>
							<td>联系人：</td>
							<td class="secTd">
								<input type="text" id="contacts" value="${model.contacts}" maxlength="50" class="form-control" />
							</td>
						</tr>
		                <tr>
							<td>联系电话：</td>
							<td class="secTd">
								<input type="text" id="contactPhone" value="${model.contactPhone}" maxlength="50" class="form-control" />
							</td>
						</tr>
		                <tr>
							<td>备注：</td>
							<td class="secTd">
								<textarea id="remarks" maxlength="1000" class="form-control">${model.remarks}</textarea>
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
							<td>课程：</td>
							<td class="secTd">
								<c:forEach items="${courses}" var="obj" varStatus="item">
									<div class="checkbox_box">
										<label for="cuiru">${obj.typeName}</label>
										<input type="checkbox" name="applyCourses" value="${obj.id}"/>
										<div class="check-box">
										</div>
									</div>
            		</c:forEach>
							</td>
						</tr>
					</table>
				</div>
				</div>
				<div class="btnCase clearfix">
					<button class="bc-cancel" type="button"  onclick="return goback();">取消</button>
				    <c:if test="${fns:isHasRole('jwgl_edit')}" >
					  <button class="bc-confirm" id="modifyBtn" type="button" onclick="return update();">保 存</button>
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
	<script src="${ctxStatic}/modules/schools/form2.js"></script>
	<input id="id" value="${model.id}" type="hidden"/>
	<input id="par" value="${model.applyCourses}" type="hidden"/>

	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
	function goback(){window.location.href=config.schoolsListUrl;}
		var flag=${update};
		if(flag){
			var boxs=$("#par").val();
			initCheckData(boxs,"applyCourses");
		}
		$(function(){
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
