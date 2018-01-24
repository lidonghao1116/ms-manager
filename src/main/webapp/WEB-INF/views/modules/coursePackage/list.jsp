<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<head>
<title>${fns:getConfig('productName')}-课程销售列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>运营管理&nbsp;>&nbsp;销售管理&nbsp;>&nbsp;课程销售</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>产品名称</label>
		             	<input type="text" id="packageName" name="" value="">
								</p>
								<p>
									<label>状态</label>
									<select name="" id="status">
										<option value="">--请选择--</option>
										<c:forEach items="${fns:getParams(11,0)}" var="obj" varStatus="item">
		            			<option value="${obj.value}">${obj.text}</option>
		            		</c:forEach>
									</select>
								</p>
								<p>
									<label>创建日期</label>
									<input type="text" id="startDate" name="" value=""> -
		              <input type="text" id="endDate" name="" value="">
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							</div>
						</form>
					</div>
				     <c:if test="${fns:isHasRole('yygl_edit')}" >
					   <div class="top_btn">
						<a href="${ctx}/coursesPackage/form"><span id="add_class">+新增产品</span></a>
					   </div>
					 </c:if>	
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/coursesPackage/list.js"></script>
			</div>
		</div>
	</body>
</html>
