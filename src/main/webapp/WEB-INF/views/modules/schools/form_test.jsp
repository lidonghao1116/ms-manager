<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-学校管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">学校管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/schools/list">学校列表</a></li>
		<li class="active"><a href="${ctx}/schools/form?id=${model.id}">学校${update?'修改':'新增'}</a></li>
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
							<label class="col-sm-2 control-label">学校名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schoolName" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学校地址<span class="dr-asterisk">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="schoolAddress" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contacts" maxlength="50" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">联系电话<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contactPhone" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="1000" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">报考信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程</label>
							<div class="col-sm-10 padding-7">
								<c:forEach items="${courses}" var="obj" varStatus="item">
			            			<input type="checkbox" name="applyCourses" value="${obj.id}"/>${obj.typeName}
			            		</c:forEach>
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
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学校名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								${model.schoolName}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学校地址<span class="dr-asterisk">*</span></label>
							<div class="col-sm-8 padding-7">
								${model.schoolAddress}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contacts" value="${model.contacts}" maxlength="50" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">联系电话<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contactPhone" value="${model.contactPhone}" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="1000" class="form-control">${model.remarks}</textarea>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">报考信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程</label>
							<div class="col-sm-10 padding-7">
								<c:forEach items="${courses}" var="obj" varStatus="item">
			            			<input type="checkbox" name="applyCourses" value="${obj.id}"/>${obj.typeName}
			            		</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				
				</c:if>
			</form>
		</div>
	</div>
	<input id="id" value="${model.id}" type="hidden"/>
	<input id="par" value="${model.applyCourses}" type="hidden"/>
	<script src="${ctxStatic}/modules/schools/form.js"></script>
		
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var flag=${update};
		if(flag){
			var boxs=$("#par").val();
			initCheckData(boxs,"applyCourses");
		}
	</script>
</body>
</html>