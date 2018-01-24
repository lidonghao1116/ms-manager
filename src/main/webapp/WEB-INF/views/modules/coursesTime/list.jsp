<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<head>
<title>${fns:getConfig('productName')}-上课时间列表</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>教务管理&nbsp;>&nbsp;课程管理&nbsp;>&nbsp;上课时间管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>模板名称</label>
									<input type="text"  id="templateName" value="" />
								</p>
								<a id="queryBtn" class="search-btn"></a>
							</div>
						</form>
					</div>
				    <c:if test="${fns:isHasRole('jwgl_edit')}" >
					  <div class="top_btn">
						<a href="${ctx}/coursesTime/addTrme"><span id="add_class">+新增时间模板</span></a>
					   </div>
					</c:if>	

					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/coursesTime/list.js"></script>
			</div>
		</div>
	</body>
</html>
