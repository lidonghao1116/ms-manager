<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-班级列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">班级列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">班级列表</a></li>
		<li><a href="${ctx}/exams/form?id=">班级新增</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">班级名称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="className" value="" />
	            </div>
	            <label class="col-md-2 control-label">班级标号</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="classNumber"value="" />
	            </div>
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">课程</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="courseId"></select>
	            </div>
	            <label class="col-md-2 control-label">报考学校</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="shoolId"></select>
	            </div>
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">考试状态</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="examStatus">
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(32,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
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
	<script src="${ctxStatic}/modules/exams/list.js"></script>
	<script type="text/javascript">
		var defaultOption = '<option value="">---请选择---</option>';
		var courseId="";
		var shoolId="";
		var examForm="";
		
		function initCourses(){
	 		$.post(ctx+'/params/courses', {}, function(data){
		  		createCoursesData(data);
	 		});
		};
		
		function initSchools(){
	 		$.post(ctx+'/params/schools', {}, function(data){
		  		createSchoolsData(data);
	 		});
		};
		
		function createCoursesData(data){
			var selectGist = $("#courseId");
			selectGist.html('');
			selectGist.append(defaultOption);
			if(data){
	      		$.each(data,function(i,obj){
	      			if(courseId==obj.id){
	      				$('<option value="'+obj.id+'" examForm="'+obj.examType+'" selected="selected">'+obj.typeName+'</option>').appendTo(selectGist);
	      			}else{
	      				$('<option value="'+obj.id+'" examForm="'+obj.examType+'">'+obj.typeName+'</option>').appendTo(selectGist);
	      			}
		      	});
		    }
		}
		
		function createSchoolsData(data){
			var selectGist = $("#shoolId");
			selectGist.html('');
			selectGist.append(defaultOption);
			if(data){
	      		$.each(data,function(i,obj){
	      			if(shoolId==obj.id){
	      				$('<option value="'+obj.id+'"  selected="selected">'+obj.schoolName+'</option>').appendTo(selectGist);
	      			}else{
	      				$('<option value="'+obj.id+'" >'+obj.schoolName+'</option>').appendTo(selectGist);
	      			}
		      	});
		    }
		}
		
		$(function(){
			initCourses();
			initSchools();
		});
	</script>
</body>
</html>