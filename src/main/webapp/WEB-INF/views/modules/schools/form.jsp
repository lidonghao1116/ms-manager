<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<h1 class="title">基本信息</h1>
					<div class="ib-content clearfix">
		 			<table style="width:100%">
						<tr>
							<td>学校名称：</td>
							<td class="secTd">
								<input type="text" id="schoolName" name="" value="" />
							</td>
						</tr>
		                <tr>
							<td>学校地址：</td>
							<td class="secTd">
								<input type="text" id="schoolAddress" name="" value="">
							</td>
						</tr>
						<tr>
							<td>联系人：</td>
							<td class="secTd">
								<input type="text" id="contacts" name="" value="">
							</td>
						</tr>
		                <tr>
							<td>联系电话：</td>
							<td class="secTd">
								<input type="text" id="contactPhone" name="" value="">
							</td>
						</tr>
		                <tr>
							<td>备注：</td>
							<td class="secTd">
								<textarea id="remarks" name="name" rows="" cols=""></textarea>
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
					<button class="bc-cancel" id="goback" type="button">取消</button>
					<button class="bc-confirm" id="addBtn" type="button">保 存</button>
				</div>
			</div>
		</div>
	</div>

	</form>

	<script src="${ctxStatic}/modules/schools/form.js"></script>
	<input id="id" value="${model.id}" type="hidden"/>
	<input id="par" value="${model.applyCourses}" type="hidden"/>
<script type="text/javascript">
    $(function() {
			$("body").on("change",".checkbox_box input",function(){
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
