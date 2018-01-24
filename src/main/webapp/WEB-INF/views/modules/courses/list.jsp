<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-课程列表</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>教务管理&nbsp;>&nbsp;&nbsp;课程管理&nbsp;>&nbsp;课程管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>课程名称</label>
									<select class="" id="courseId">
										<option value="">--请选择--</option>
									</select>
									<label>课程状态</label>
									<select class="" id="status">
										<option value="">--请选择--</option>
									</select>
								</p>
								<p>
									<label>鉴定等级</label>
									<select class="" id="grade">
										<option value="">--请选择--</option>
									</select>
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							</div>
						</form>
					</div>
					<c:if test="${fns:isHasRole('jwgl_edit')}" >
					  <div class="top_btn">
						<a href="${ctx}/courses/form"><span id="add_class">+新增课程</span></a>
					  </div>
					</c:if>
					
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/courses/list.js"></script>
				<script type="text/javascript">
					var defaultOption = '<option value="">---请选择---</option>';
					/*课程名称信息  */
					function initCourses(){
				 		$.post(ctx+'/params/courses', {}, function(data){
					  		createCoursesData(data);
				 		});
					};
					/*鉴定等级信息 */
					function initGrade(){
				 		$.post(ctx+'/params/cfgParamsGrade', {}, function(data){
					  		createGradeData(data);
				 		});
					};
					/*课程状态信息  */
					function initCoursesStatus(){
				 		$.post(ctx+'/params/cfgParamsStatus', {}, function(data){
					  		createStatusData(data);
				 		});
					};
					/*课程名称信息  */
					function createCoursesData(data){
						var selectGist = $("#courseId");
						selectGist.html('');
						selectGist.append(defaultOption);
						if(data){
				      		$.each(data,function(i,obj){
				      			$('<option value="'+obj.id+'">'+obj.typeName+'</option>').appendTo(selectGist);
					      	});
					    }
					}
					/*鉴定等级信息  */
					function createGradeData(data){
						var selectGist = $("#grade");
						selectGist.html('');
						selectGist.append(defaultOption);
						if(data){
				      		$.each(data,function(i,obj){
				      			$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
					      	});
					    }
					}
					/*课程状态信息 */
					function createStatusData(data){
						var selectGist = $("#status");
						selectGist.html('');
						selectGist.append(defaultOption);
						if(data){
				      		$.each(data,function(i,obj){
				      			$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
					      	});
					    }
					}
					$(function(){
						initCourses();
						initGrade();
						initCoursesStatus();
					});
				</script>
			</div>
		</div>
	</body>
</html>
