<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学校列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>教务管理&nbsp;>&nbsp;报名管理&nbsp;>&nbsp;补考</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-tab clearfix">
					<div class="clearfix">
						<a href="${ctx}/orders/list?type=PEND_APPLY"><b></b>待审核</a>
						<a class="ct_on"><b></b>补考</a>
					</div>
				</div>
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>姓名</label>
									<input type="text" id="userName" name="" value="" />
								</p>
								<p>
									<label>课程名称</label>
									 <select class="" id="learnTypeId" name="">
										<option value="">--请选择--</option>
										<c:forEach items="${learnTypeList}" var="obj" varStatus="item">
					            			<option value="${obj.id}">${obj.typeName}</option>
					            		</c:forEach>
									</select>
								</p>
								<p>
									<label>报名时间</label>
									<input type="text" class=" date-picker" id="startDate" name="startDate" value="" data-date-format="yyyy-mm-dd"/>-
									<input type="text" class=" date-picker" id="endDate" name="startDate" value="" data-date-format="yyyy-mm-dd"/>
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							 </div>
						</form>
					</div>

					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxStatic}/modules/applys/1/list_002.js"></script>
		<script type="text/javascript">
			$(function(){
				var $div_a = $(".content-tab a");
				$div_a.click(function(){
					$(this).addClass("ct_on").siblings().removeClass("ct_on");
					var index = $div_a.index(this);
					$(".content-area_box .content-area").eq(index).show().siblings().hide();
				})
			})
		</script>
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
		<script type="text/javascript">
			var defaultOption = '<option value="">---请选择---</option>';
			function initCourses(){
		 		$.post(ctx+'/params/courses', {}, function(data){
			  		createCoursesData(data);
		 		});
			};
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
			$(function(){
				initCourses();
			});
		</script>
			</div>
		</div>
	</body>
</html>
