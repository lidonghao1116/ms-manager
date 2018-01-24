<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-不符合学员列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">不符合学员列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/orders/list?type=PEND_APPLY">预报名学员</a></li>
		<li class="active"><a href="#">不符合学员</a></li>
		<li><a href="${ctx}/orders/list?type=REP_APPLY">补考学员</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">姓名</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="userName" value="" />
	            </div>
	            <label class="col-md-2 control-label">产品名称</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="packageId"></select>
	            </div>
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">报名日期</label>
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
	<script src="${ctxStatic}/modules/applys/1/list_003.js"></script>
	<script type="text/javascript">
	var defaultOption = '<option value="">---请选择---</option>';
	function initProducts(){
 		$.post(ctx+'/params/coursesPackage', {}, function(data){
	  		createProductsData(data);
 		});
	};
	function createProductsData(data){
		var selectGist = $("#packageId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
   				$('<option value="'+obj.id+'">'+obj.packageName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	$(function(){
		initProducts();
	});
	</script>
</body>
</html>