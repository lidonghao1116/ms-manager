<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-预报名受理管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">预报名受理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/orders/list?type=PEND_APPLY">预报名学员</a></li>
		<li><a href="${ctx}/orders/list?type=NOT_MATCH">不符合学员</a></li>
		<li><a href="${ctx}/orders/list?type=REP_APPLY">补考学员</a></li>
		<li class="active"><a href="${ctx}/orders/form?id=${model.id}&type=NOT_MATCH">不符合学员详细</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">身份信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">姓名：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.userName}
							</div>
							<label class="col-sm-2 control-label">身份号码：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.certNo}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">手机号码：</label>
							<div class="col-sm-4 padding-7">
								${model.userBaseInfo.mobile}
							</div>
							<label class="col-sm-2 control-label">报名时年龄：</label>
							<div class="col-sm-4 padding-7">
								${model.userAge}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学历：</label>
							<div class="col-sm-4 padding-7">
								${fns:getText(3,0,model.userExtendInfo.education)}
							</div>
							<label class="col-sm-2 control-label">性别：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.sex=='1'?"男":"女"}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">民族：</label>
							<div class="col-sm-4 padding-7">
								${fns:getText(6,0,model.userExtendInfo.nation)}
							</div>
							<label class="col-sm-2 control-label">籍贯：</label>
							<div class="col-sm-4 padding-7">
								${fns:getText(4,0,model.userExtendInfo.birthplace)}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">户籍地址：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.address}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">紧急联系人：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.contacts}
							</div>
							<label class="col-sm-2 control-label">联系电话：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.contactPhone}
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">来源信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">来源：</label>
							<div class="col-sm-2  padding-7">
								${model.userExtendInfo.sourceTypeName}
							</div>
							<div class="col-sm-2  padding-7">
								${model.userExtendInfo.sourceTypeSecName}
							</div>
							<div class="col-sm-2  padding-7">
								<c:if test="${empty model.userExtendInfo.sourceValueName}">
									--
								</c:if>
								${model.userExtendInfo.sourceValueName}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注：</label>
							<div class="col-sm-4 padding-7">
								${model.userExtendInfo.sourceRemarks}
							</div>
						</div>
					</div>

					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">报考信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">产品：</label>
							<div class="col-sm-4 padding-7">
								${model.productName}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程：</label>
							<div class="col-sm-4 padding-7">
								${model.courseName}
							</div>
							<label class="col-sm-2 control-label">班级标号：</label>
							<div class="col-sm-4 padding-7">
								${fns:getClassNumber(model.classNumber)}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">上课时间：</label>
							<div class="col-sm-4 padding-7">
								${fns:getText(16,0,model.classTime)}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否考试：</label>
							<div class="col-sm-4 padding-7">
								${model.isExam=='1'?"是":"否"}
							</div>
							<label class="col-sm-2 control-label">是否交社保：</label>
							<div class="col-sm-4 padding-7">
								${model.isHasPf=='1'?"是":"否"}
							</div>
						</div>
					</div>

					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">收费信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学费：</label>
							<div class="col-sm-4 padding-7">
								${model.schoolFee}元
							</div>
							<label class="col-sm-2 control-label">押金：</label>
							<div class="col-sm-4 padding-7">
								${model.deposit}元
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">书费：</label>
							<div class="col-sm-4 padding-7">
								${model.bookFree}元
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否分期：</label>
							<div class="col-sm-4 padding-7">
								${model.isStaging=='1'?"是":"否"}
							</div>
							<label class="col-sm-2 control-label">首付款：</label>
							<div class="col-sm-4 padding-7">
								${model.firstPay}元
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">补考费：</label>
							<div class="col-sm-4 padding-7">
								${model.makeupFee}元
							</div>
						</div>
					</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>
