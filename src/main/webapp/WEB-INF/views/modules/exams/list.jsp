<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学校列表</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>教务管理&nbsp;>&nbsp;考务管理&nbsp;>&nbsp;班级管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>班级名称</label>
									<input type="text" id="className" name="" value="">
								</p>
								<p>
									<label>班级标号</label>
									<input type="text" id="classNumber" name="" value="">
								</p>
								<p>
									<label>课程</label>
									<!-- 
									<select class="" id="courseId" name="">
									</select>
									 -->
									 <select class="" id="learnTypeId" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${learnTypeList}" var="obj" varStatus="item">
					            			<option value="${obj.id}">${obj.typeName}</option>
					            		</c:forEach>
									</select>
								</p>
								<p>
									<label>报考学校</label>
									<!-- 
									<select class="" id="shoolId" name="">
									</select>
									 -->
									 <select class="" id="schoolId" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${schoolsList}" var="obj" varStatus="item">
					            			<option value="${obj.id}">${obj.schoolName}</option>
					            		</c:forEach>
									</select>
									 
								</p>
								<p>
									<label>考试状态</label>
									<select class="" id="examStatus" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${fns:getParams(32,0)}" var="obj" varStatus="item">
					            			<option value="${obj.value}">${obj.text}</option>
					            		</c:forEach>
									</select>
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							</div>
						</form>
					</div>
					
					<c:if test="${fns:isHasRole('jwgl_edit')}" >
					   <div class="top_btn">
						 <a href="${ctx}/exams/form"><span id="add_class">+新增班级</span></a>
					   </div>
					</c:if>
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/exams/list.js"></script>
				<!--
	<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
	 -->
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
			</div>
		</div>
	</body>
</html>
