<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学校列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">学校列表2</a></li>
		<li><a href="${ctx}/schools/form?id=">学校新增</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">学校名称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="schoolName" value="" />
	            </div>
           	</div>
        </form>
    </div>
    <div class="page-content">
    	<div class="page-heading mb5">
        	<div class="text-left small">
	        	<button class="btn btn-sm btn-primary" type="button" id="queryBtn"><span class="fa fa-search"></span> 查询</button>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<script src="${ctxStatic}/modules/schools/list.js"></script>
	
</body>
</html>