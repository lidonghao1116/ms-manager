<%@ page contentType="text/html;charset=UTF-8"%>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.userName}
		</div>
		<label class="col-sm-2 control-label">身份号码</label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.certNo}
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">手机号码</label>
		<div class="col-sm-4 padding-7">
			${model.userBaseInfo.mobile}
		</div>
		<label class="col-sm-2 control-label">报名时年龄</label>
		<div class="col-sm-4 padding-7">
			${model.userAge}
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">学历</label>
		<div class="col-sm-4 padding-7">
			${fns:getText(3,0,model.userExtendInfo.education)}
		</div>
		<label class="col-sm-2 control-label">性别</label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.sex=='1'?"男":(model.userExtendInfo.sex=='0'?"女":"未知")}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">民族</label>
		<div class="col-sm-4 padding-7">
			${fns:getText(6,0,model.userExtendInfo.nation)}
		</div>
		<label class="col-sm-2 control-label">籍贯<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4 padding-7">
			${fns:getText(4,0,model.userExtendInfo.birthplace)}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">户籍地址<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.address}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">紧急联系人</label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.contacts}
		</div>
		<label class="col-sm-2 control-label">联系电话</label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.contactPhone}
		</div>
	</div>
</div>
<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">来源信息1</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">来源</label>
		<div class="col-sm-2 padding-7">
			${model.userExtendInfo.sourceTypeName}
		</div>
		<div class="col-sm-2 padding-7">
			${model.userExtendInfo.sourceTypeSecName}
		</div>
		<div class="col-sm-2 padding-7" >
			<c:if test="${empty model.userExtendInfo.sourceValueName}">
				--
			</c:if>
			${model.userExtendInfo.sourceValueName}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">备注</label>
		<div class="col-sm-6 padding-7">
			${model.userExtendInfo.sourceRemarks}
		</div>
	</div>
</div>