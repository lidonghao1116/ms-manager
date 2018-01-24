<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-班级学员模拟成绩管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">班级学员模拟成绩</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/exams/list">考试列表</a></li>
		<li class="active"><a href="${ctx}/exams/learnRecords/form?id=${classId}">班级学员模拟成绩</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group" style="margin-bottom: 5px;">
				  	<label class="col-md-2 controls" style="float: right;">考试信息：<a style="cursor: pointer;" id="exportBtn">EXCEL下载</a></label>
				</div>
			</form>
			<div class="table-responsive">
		        <table id="contentTable" class="table table-bordered dr-table-bordered">
		            <thead>
		            <tr>
		                <th>序号</th>
		                <th>姓名</th>
		                <th>联系方式</th>
		                <th>答题次数</th>
		                <th>最高成绩</th>
		            </tr>
		            </thead>
		            <tbody>
			            <c:forEach items="${model}" var="obj" varStatus="item">
			                <tr>
			                    <td>${item.index+1}</td>
			                    <td>${obj.userExtend.userName}</td>
			                    <td>${obj.userInfo.mobile}</td>
			                    <td>${obj.answersNum}</td>
			                    <td>${obj.scores}</td>
			                </tr>
			            </c:forEach>
			            <c:if test="${fn:length(model)<1}">
			                <tr><td colspan="12"><i class="icon-warning-sign"></i>暂无相关记录</td></tr>
			            </c:if>
		            </tbody>
		        </table>
		        
        	</div>
		</div>
	</div>
	<form class="form-horizontal" action="" id="queryForm" method="post" enctype="multipart/form-data" target="uploadTarget">
		<input name="id"  value="${classId}" type="hidden"/>
	</form>
	<iframe id="uploadTarget" name="uploadTarget" style='display:none;'></iframe>
	<script src="${ctxStatic}/modules/exams/recordForm.js"></script>
</body>
</html>