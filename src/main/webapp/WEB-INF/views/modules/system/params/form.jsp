<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-参数列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">参数管理</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/system/params/list">参数列表</a></li>
		<li class="active"><a href="${ctx}/system/params/form?id=${user.id}">参数${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">参数code<span class="dr-asterisk">*</span></label>
					<div class="col-sm-5">
						<input type="text" id="value" placeholder="参数code" maxlength="2" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">参数text<span class="dr-asterisk">*</span></label>
					<div class="col-sm-5">
						<input type="text" id="text" placeholder="参数text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">排序<span class="dr-asterisk">*</span></label>
					<div class="col-sm-5">
						<input type="text" id="emial" placeholder="排序" class="form-control" />
					</div>
				</div>
				<div class="col-sm-7 text-center">
					<c:if test="${update == 'false'}">
						<button class="btn btn-sm btn-primary" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
					</c:if>
					<c:if test="${update == 'true'}">
						<button class="btn btn-sm btn-primary" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
					</c:if>
					<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>