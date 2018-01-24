<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-班级管理</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">班级管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/exams/list">考试列表</a></li>
		<li class="active"><a href="${ctx}/exams/form?id=${model.id}">班级${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">班级名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="className" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="courseId"></select>
							</div>
							<label class="col-sm-2 control-label">报考学校<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="shoolId"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">班级标号<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="classNumber" maxlength="50" class="form-control" />
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
									<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">理论日期</label>
							<div class="col-sm-4">
								<div class="input-group">
									<input type="text" class="form-control date-picker" id="theoryDate"  value="" data-date-format="yyyy-mm-dd"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar bigger-110"></i>
									</span>
								</div>
							</div>
							<label class="col-sm-2 control-label">考场地址</label>
							<div class="col-sm-4">
								<input type="text" id="theoryAddress" maxlength="50" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">实操日期</label>
							<div class="col-sm-4">
								<div class="input-group">
									<input type="text" class="form-control date-picker" id="operationDate"  value="" data-date-format="yyyy-mm-dd"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar bigger-110"></i>
									</span>
								</div>
							</div>
							<label class="col-sm-2 control-label">考场地址</label>
							<div class="col-sm-4">
								<input type="text" id="operationAddress" maxlength="50" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
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
									<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}"  <c:if test="${model.examForm==obj.value}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label">考试状态</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="examStatus">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(32,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${model.examStatus==obj.value}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">理论日期</label>
							<div class="col-sm-4">
								<div class="input-group">
									<input type="text" class="form-control date-picker" id="theoryDate"  value="<fmt:formatDate value="${model.theoryDate}" type="both"/>" data-date-format="yyyy-mm-dd"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar bigger-110"></i>
									</span>
								</div>
							</div>
							<label class="col-sm-2 control-label">考场地址</label>
							<div class="col-sm-4">
								<input type="text" id="theoryAddress" maxlength="50" value="${model.theoryAddress}" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">实操日期</label>
							<div class="col-sm-4">
								<div class="input-group">
									<input type="text" class="form-control date-picker" id="operationDate"  value="<fmt:formatDate value="${model.operationDate}" type="both"/>" data-date-format="yyyy-mm-dd"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar bigger-110"></i>
									</span>
								</div>
							</div>
							<label class="col-sm-2 control-label">考场地址</label>
							<div class="col-sm-4">
								<input type="text" id="operationAddress" maxlength="50" value="${model.operationAddress}" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				学员（${fn:length(model.applyOrders)}人）
			</form>
			
			<div class="table-responsive">
		        <table id="contentTable" class="table table-bordered dr-table-bordered">
		            <thead>
		            <tr>
		                <th>序号</th>
		                <th>姓名</th>
		                <th>手机号</th>
		                <th>年龄</th>
		                <th>学历</th>
		                <th>是否交金</th>
		                <th>来源</th>
		            </tr>
		            </thead>
		            <tbody>
			            <c:forEach items="${model.applyOrders}" var="obj" varStatus="item">
			                <tr>
			                    <td>${item.index+1}</td>
			                    <td>${obj.userExtendInfo.userName}</td>
			                    <td>${obj.userBaseInfo.mobile}</td>
			                    <td>${obj.userExtendInfo.age}</td>
			                    <td>${fns:getText(3,0,obj.userExtendInfo.education)}</td>
			                    <td>${obj.isHasPf=='1'?"是":"否"}</td>
			                    <td>${obj.userExtendInfo.sourceValueName}</td>
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
		<input name="id"  value="${model.id}" type="hidden"/>
	</form>
	<iframe id="uploadTarget" name="uploadTarget" style='display:none;'></iframe>
	
	<input id="id" value="${model.id}" type="hidden"/>
	<script src="${ctxStatic}/modules/exams/form.js"></script>
		
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
	      				$('<option value="'+obj.id+'" examForm="'+obj.examType+'" selected="selected">'+obj.typeName+'</option>').appendTo(selectGist);
	      			}else{
	      				$('<option value="'+obj.id+'" examForm="'+obj.examType+'">'+obj.typeName+'</option>').appendTo(selectGist);
	      			}
		      	});
	      		
	      		selectGist.bind('change',function(){
					$("#examForm").val($(this).find("option:selected").attr("examForm"));
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