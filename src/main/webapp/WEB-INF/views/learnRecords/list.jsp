<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学习记录列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">学习记录列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">学习记录列表</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="post" enctype="multipart/form-data" target="uploadTarget">
            <div class="form-group">
            	<label class="col-md-2 control-label">学员姓名</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="userName" name="userName" value="" />
	            </div>
	            <!-- <label class="col-md-2 control-label">班级标号</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="classNumber" name="classNumber" value="" />
	            </div> -->
           	</div>
        </form>
    </div>
    <div class="page-content">
    	<div class="page-heading mb5">
        	<div class="text-left small">
	        	<button class="btn btn-sm btn-primary" type="button" id="queryBtn"><span class="fa fa-search"></span> 查询</button>
	    		<span class="col-md-2" style="float: right;paddind-top: 3px">下载学习记录：<a style="cursor: pointer;" id="exportBtn">EXCEL下载</a></span>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<iframe id="uploadTarget" name="uploadTarget" style='display:none;'></iframe>
	<script src="${ctxStatic}/modules/learnRecords/list.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>