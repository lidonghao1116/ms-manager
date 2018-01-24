<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-课程销售管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">课程销售管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/coursesPackage/list">课程销售列表</a></li>
		<li class="active"><a href="${ctx}/coursesPackage/form?id=${model.id}">课程销售${update?'修改':'新增'}</a></li>
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
							<label class="col-sm-2 control-label">产品名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="packageName" maxlength="50" class="form-control" />
							</div>
							
							<label class="col-sm-2 control-label">适合工种</label>
							<div class="col-sm-4">
								<input type="text" id="originalPrice" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">产品简介<span class="dr-asterisk">*</span></label>
							<div class="col-sm-5">
								<textarea id="summary" maxlength="1000" class="form-control"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程<span class="dr-asterisk">*</span></label>
							<div class="col-sm-10 padding-7">
								<c:forEach items="${courses}" var="obj" varStatus="item">
			            			<input type="checkbox" name="applyCourses" value="${obj.id}"/>${obj.typeName}
			            		</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注<span class="dr-asterisk">*</span></label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="1000" class="form-control"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">排序<span class="dr-asterisk">*</span></label>
							<div class="col-sm-10 padding-7">
		            			<input type="input" id="sortNo" value="" maxlength="3"/>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">费用信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">标准价</label>
							<div class="col-sm-4">
								<input type="text" id="originalPrice" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
							<label class="col-sm-2 control-label">优惠价</label>
							<div class="col-sm-4">
								<input type="text" id="price" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">优惠标志</label>
							<div class="col-sm-4 padding-7">
								<input type="checkbox" id="isDiscount" />
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
							<label class="col-sm-2 control-label">创建时间<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								${model.addTime}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">产品名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								<input type="text" id="packageName" value="${model.packageName}" maxlength="50" class="form-control" />
							</div>
							
							<label class="col-sm-2 control-label">适合工种</label>
							<div class="col-sm-4">
								<input type="text" id="originalPrice" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>
							</div>
							
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">产品简介<span class="dr-asterisk">*</span></label>
							<div class="col-sm-5">
								<textarea id="summary" maxlength="1000" class="form-control">${model.summary}</textarea>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程<span class="dr-asterisk">*</span></label>
							<div class="col-sm-10 padding-7">
								<c:forEach items="${courses}" var="obj" varStatus="item">
									<!-- disabled="disabled" -->
			            			<input type="checkbox" name="applyCourses" value="${obj.id}"/>${obj.typeName}
			            		</c:forEach>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">产品状态<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(11,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}"  <c:if test="${obj.value==model.status}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="1000" class="form-control">${model.remarks}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">排序<span class="dr-asterisk">*</span></label>
							<div class="col-sm-10 padding-7">
		            			<input type="input" id="sortNo" value="${model.sortNo}" maxlength="3"/>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">费用信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">标准价</label>
							<div class="col-sm-4">
								<input type="text" id="originalPrice" maxlength="50" value="${model.originalPrice}" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
							<label class="col-sm-2 control-label">优惠价</label>
							<div class="col-sm-4">
								<input type="text" id="price" maxlength="50" value="${model.price}" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">优惠标志</label>
							<div class="col-sm-4 padding-7">
								<input type="checkbox" id="isDiscount" <c:if test="${model.isDiscount=='1'}">checked="checked"</c:if>/>
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
	<script src="${ctxStatic}/modules/coursesPackage/form.js"></script>
		
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