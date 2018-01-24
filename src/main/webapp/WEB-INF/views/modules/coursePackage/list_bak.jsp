<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-课程销售列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">课程销售列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">课程销售列表</a></li>
		<li><a href="${ctx}/coursesPackage/form?id=">课程销售新增</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">产品名称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="packageName" value="" />
	            </div>
	            <label class="col-md-2 control-label">状态</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="status">
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(11,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">创建时间</label>
	            <div class="col-md-4">
	            	<div class="input-group">
						<input type="text" class="form-control date-picker" id="startDate" name="startDate" value="" data-date-format="yyyy-mm-dd"/>
						<span class="input-group-addon">
							<i class="fa fa-calendar bigger-110"></i>
						</span>
					</div>
				</div>
				<div class="col-md-4">
					<div class="input-group">
						<input type="text" class="form-control date-picker" id="endDate" name="endDate" value="" data-date-format="yyyy-mm-dd"/>
						<span class="input-group-addon">
							<i class="fa fa-calendar bigger-110"></i>
						</span>
					</div>
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
	<script src="${ctxStatic}/modules/coursesPackage/list.js"></script>
	
</body>
</html>