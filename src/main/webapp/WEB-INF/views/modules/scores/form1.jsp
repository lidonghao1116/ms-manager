<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-成绩管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">班级管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/scores/list?examStatus=02">成绩列表</a></li>
		<li class="active"><a href="${ctx}/scores/form?id=${model.id}">成绩${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">班级信息</label>
					  <label class="col-md-2 controls" style="float: right;">考试信息：<a style="cursor: pointer;" id="exportBtn">EXCEL下载</a></label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">班级名称</label>
							<div class="col-sm-4 padding-7">
								${model.className}
							</div>
							<label class="col-sm-2 control-label">创建日期</label>
							<div class="col-sm-4 padding-7">
								<fmt:formatDate value="${model.addTime}" type="both"/>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="courseId" disabled="disabled"></select>
							</div>
							<label class="col-sm-2 control-label">报考学校<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="shoolId" disabled="disabled">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">班级标号</label>
							<div class="col-sm-4 padding-7">
								${model.classNumber}
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">考试信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">考试形式</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="examForm" disabled="disabled">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(2,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}"  <c:if test="${model.examForm==obj.value}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label">考试状态</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="examStatus" disabled="disabled">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(32,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${model.examStatus==obj.value}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">理论日期</label>
							<div class="col-sm-4 padding-7">
								<fmt:formatDate value="${model.theoryDate}" type="both"/>
							</div>
							<label class="col-sm-2 control-label">考场地址</label>
							<div class="col-sm-4 padding-7">
								${model.theoryAddress}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">实操日期</label>
							<div class="col-sm-4 padding-7">
								<fmt:formatDate value="${model.operationDate}" type="both"/>
							</div>
							<label class="col-sm-2 control-label">考场地址</label>
							<div class="col-sm-4 padding-7">
								${model.operationAddress}
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">考试结果</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group">
							<label class="col-sm-2 control-label">报名数</label>
							<div class="col-sm-4 padding-7">
								${model.examResult.totleNum}
							</div>
							<label class="col-sm-2 control-label">录入数</label>
							<div class="col-sm-4 padding-7">
								${model.examResult.inputNum}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">合格数</label>
							<div class="col-sm-4 padding-7">
								${model.examResult.qualifiedNum}
							</div>
							<label class="col-sm-2 control-label">合格率</label>
							<div class="col-sm-4 padding-7">
								${model.examResult.qualifiedRate}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">缺考数</label>
							<div class="col-sm-4 padding-7">
								${model.examResult.lackExamNum}
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				学员成绩（${fn:length(model.applyOrders)}人）
			</form>
			
			<div class="table-responsive">
		        <table id="contentTable" class="table table-bordered dr-table-bordered">
		            <thead>
		            <tr>
		                <th>序号</th>
		                <th>姓名</th>
		                <th>手机号</th>
		                <th>理论</th>
		                <th>实操</th>
		                <th>考试结果</th>
		                <th>证书编号</th>
		                <th>操作</th>
		            </tr>
		            </thead>
		            <tbody>
			            <c:forEach items="${model.applyOrders}" var="obj" varStatus="item">
			                <tr>
			                    <td>${item.index+1}</td>
			                    <td>${obj.userExtendInfo.userName}</td>
			                    <td>${obj.userBaseInfo.mobile}</td>
			                    <td>${obj.userScores.theoryScores}</td>
			                    <td>${obj.userScores.poScores}</td>
			                    <td>${obj.userScores.examResultName}</td>
			                    <td>${obj.userScores.certificateNo}</td>
			                    <td><a href="#" onclick="obj.openModel('${model.id}','${obj.userId}','${obj.id}')">编辑</a></td>
			                </tr>
			            </c:forEach>
			            <c:if test="${fn:length(model.applyOrders)<1}">
			                <tr><td colspan="12"><i class="icon-warning-sign"></i>暂无相关记录</td></tr>
			            </c:if>
		            </tbody>
		        </table>
		        
        	</div>
		</div>
	</div>
	<form class="form-horizontal" action="" id="queryForm" method="post" enctype="multipart/form-data" target="uploadTarget">
		<input name="id" value="${model.id}" type="hidden"/>
	</form>
	<iframe id="uploadTarget" name="uploadTarget" style='display:none;'></iframe>
	<script src="${ctxStatic}/modules/scores/form.js"></script>
		
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var defaultOption = '<option value="">---请选择---</option>';
		var courseId=${model.courseId!=null?model.courseId:'""'};
		var shoolId=${model.shoolId!=null?model.shoolId:'""'};
		
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
	      				$('<option value="'+obj.id+'" selected="selected">'+obj.typeName+'</option>').appendTo(selectGist);
	      			}else{
	      				$('<option value="'+obj.id+'">'+obj.typeName+'</option>').appendTo(selectGist);
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
			
			  $("#exportBtn").bind("click",function(){
				  $("#queryForm").attr("action",ctx+"/scores/export/01");
					$("#queryForm").submit();
			}); 
			
		});
	</script>
</body>
</html>