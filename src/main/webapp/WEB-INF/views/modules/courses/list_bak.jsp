<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学校列表</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>教务管理>课程管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							<p>
								<label>课程</label>
								<select class="" id="courseId" name="">
									<option value="">--请选择--</option>
									<c:forEach items="${courses}" var="obj" varStatus="item">
				            			<option value="${obj.id}">${obj.typeName}</option>
				            		</c:forEach>
								</select>
							</p>
							<p>
								<label>状态</label>
								<select id="status" name="">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(2,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
							</p>
						 	<a id="queryBtn" class="search-btn"></a>
						</form>
					</div>
					<div class="top_btn">
						<a href="${ctx}/courses/form"><span id="add_class">+新增课程</span></a>
					</div>
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/courses/list.js"></script>
			</div>
		</div>
	</body>
</html>