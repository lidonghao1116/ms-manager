<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>${fns:getConfig('productName')}-提成列表</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>运营管理&nbsp;>&nbsp;合作商管理&nbsp;>&nbsp;提成管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>方案名称</label>
									<input type="text"  id="schemeName" value="" />
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							 </div>
						</form>
					</div>
					 <c:if test="${fns:isHasRole('yygl_edit')}" >
					   <div class="top_btn">
						<a href="${ctx}/brokerage/form"><span id="add_class">+新增提成</span></a>
					  </div>
				     </c:if>
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/brokerage/list.js"></script>
			</div>
		</div>
	</body>
</html>
