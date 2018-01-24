<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-学员管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">学员详情</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/user/list">学员列表</a></li>
		<li class="active"><a href="${ctx}/user/info?id=${model.userInfo.id}">学员详情</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls">注册信息</label>
				</div>
				<div class="form-group form-color">
					<div class="form-group form-padding">
						<label class="col-sm-2 control-label">注册时间：</label>
						<div class="col-sm-4 padding-7">
							<fmt:formatDate value="${model.userInfo.registerTime}" type="both"/>
						</div>
					</div>
					<div class="form-group form-padding">
						<label class="col-sm-2 control-label">客户号：</label>
						<div class="col-sm-4 padding-7">
							${model.userInfo.id}
						</div>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls">身份信息</label>
				</div>
				<div class="form-group form-color">
					<div class="form-group form-padding">
						<label class="col-sm-2 control-label">姓名：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.userName}
							</c:if>
						</div>
						<label class="col-sm-2 control-label">身份证：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.certNo}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机号码：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userInfo.mobile}
							</c:if>
						</div>
						<label class="col-sm-2 control-label">年龄：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.age}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">学历：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${fns:getText(3,0,model.userExtend.education)}
							</c:if>
						</div>
						<label class="col-sm-2 control-label">性别：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.sex=='1'?"男":"女"}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">民族：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${fns:getText(6,0,model.userExtend.nation)}
							</c:if>
						</div>
						<label class="col-sm-2 control-label">籍贯：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${fns:getText(4,0,model.userExtend.birthplace)}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">户籍地址：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.address}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">紧急联系人：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.contacts}
							</c:if>
						</div>
						<label class="col-sm-2 control-label">联系电话：</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.contactPhone}
							</c:if>
						</div>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls">报考信息信息</label>
				</div>
				<c:forEach items="${model.orderList}" var="obj" varStatus="item">
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程：</label>
							<div class="col-sm-4 padding-7">
								${obj.courseName}
							</div>
							<label class="col-sm-2 control-label">受理时间：</label>
							<div class="col-sm-4 padding-7">
								<fmt:formatDate value="${obj.handleTime}" type="both"/>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">是否考试：</label>
							<div class="col-sm-4 padding-7">
								${obj.isExam=="1"?"是":"否"}
							</div>
							<label class="col-sm-2 control-label">班级标号：</label>
							<div class="col-sm-4 padding-7">
								<c:if test="${not empty obj.examClass}">
								${obj.examClass.className}
								</c:if>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">考试状态：</label>
							<div class="col-sm-4 padding-7">
								<c:if test="${not empty obj.examClass}">
									${fns:getText(32,0,obj.examClass.examStatus)}
								</c:if>
							</div>
							<label class="col-sm-2 control-label">考试结果：</label>
							<div class="col-sm-4 padding-7">
								<c:if test="${not empty obj.userScores}">
									${fns:getText(12,0,obj.userScores.examResult)}
								</c:if>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">理论成绩：</label>
							<div class="col-sm-4 padding-7">
								<c:if test="${not empty obj.userScores}">
								${obj.userScores.theoryScores}
								</c:if>
							</div>
							<label class="col-sm-2 control-label">实操成绩：</label>
							<div class="col-sm-4 padding-7">
								<c:if test="${not empty obj.userScores}">
								${obj.userScores.poScores}
								</c:if>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">证书编号：</label>
							<div class="col-sm-4 padding-7">
								<c:if test="${not empty obj.userScores}">
								${obj.userScores.certificateNo}
								</c:if>
							</div>
							<label class="col-sm-2 control-label">是否交社保：</label>
							<div class="col-sm-4 padding-7">
								${obj.isHasPf=='1'?"是":"否"}
							</div>
						</div>
						<div class="form-group form-padding">
                            <label class="col-sm-2 control-label">来源信息：</label>
                            <div class="col-sm-4 padding-7">
                                <c:if test="${not empty obj.sourceWholeText}">
                                ${obj.sourceWholeText}
                                </c:if>
                            </div>
                        </div>
					</div>
				</c:forEach>
				<div class="col-sm-12 text-center">
					<button class="btn btn-sm btn-primary" type="button" onclick=""><i class="fa fa-reply"></i>返回</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
