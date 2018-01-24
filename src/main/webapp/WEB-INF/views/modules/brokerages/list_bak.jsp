<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-提成管理列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">提成管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">提成列表</a></li>
		<li><a href="${ctx}/brokerage/form?id=">提成新增</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">方案名称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="schemeName" value="" />
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
	<script src="${ctxStatic}/modules/brokerage/list.js"></script>
	
</body>
</html>