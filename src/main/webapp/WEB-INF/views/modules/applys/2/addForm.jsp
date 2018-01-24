<%@ page contentType="text/html;charset=UTF-8"%>
<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">身份信息</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">姓名：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<input type="text" id="userName" maxlength="50" class="form-control" />
		</div>
		<label class="col-sm-2 control-label">身份号码：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<input type="text" id="certNo" maxlength="50" class="form-control" />
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">手机号码：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<input type="text" id="mobile" maxlength="50" class="form-control" />
		</div>
		<label class="col-sm-2 control-label">报名时年龄：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4 padding-7">
			<span id="age"></span>
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">学历：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="education">
				<option value="">--请选择--</option>
				<c:forEach items="${fns:getParams(3,0)}" var="obj" varStatus="item">
           			<option value="${obj.value}">${obj.text}</option>
           		</c:forEach>
			</select>
		</div>
		<label class="col-sm-2 control-label">性别：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4 padding-7">
			<span id="sex"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">民族：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="nation">
				<option value="">--请选择--</option>
				<c:forEach items="${fns:getParams(6,0)}" var="obj" varStatus="item">
           			<option value="${obj.value}">${obj.text}</option>
           		</c:forEach>
			</select>
		</div>
		<label class="col-sm-2 control-label">籍贯：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="birthplace">
				<option value="">--请选择--</option>
				<c:forEach items="${fns:getParams(4,0)}" var="obj" varStatus="item">
           			<option value="${obj.value}">${obj.text}</option>
           		</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">户籍地址：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<input type="text" id="address" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">紧急联系人：</label>
		<div class="col-sm-4">
			<input type="text" id="contacts" maxlength="50" class="form-control" />
		</div>
		<label class="col-sm-2 control-label">联系电话：</label>
		<div class="col-sm-4">
			<input type="text" id="contactPhone" maxlength="50" class="form-control" />
		</div>
	</div>
</div>
<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">来源信息</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">来源：</label>
		<div class="col-sm-2">
			<select class="chosen-select form-control" id="sourceType">
				<option value="">--请选择--</option>
				<c:forEach items="${fns:getParams(5,0)}" var="obj" varStatus="item">
           			<option value="${obj.value}" id="${obj.id}">${obj.text}</option>
           		</c:forEach>
			</select>
		</div>
		<div class="col-sm-2">
			<select class="chosen-select form-control" id="sourceTypeSec">
				<option value="">--请选择--</option>
			</select>
		</div>
		<div class="col-sm-2" id="sourceValueDiv">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">备注：</label>
		<div class="col-sm-6">
			<textarea id="sourceRemarks" maxlength="1000" class="form-control"></textarea>
		</div>
	</div>
</div>

<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">报考信息</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">产品：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="packageId"></select>
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">课程：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="courseId">
				<option value="">---请选择---</option>
			</select>
		</div>
		<label class="col-sm-2 control-label">班级标号：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="classNumber"></select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">上课时间：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="classTime">
				<option value="">---请选择---</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">是否考试：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="isExam">
				<option value="">--请选择--</option>
           		<option value="1">是</option>
           		<option value="0">否</option>
			</select>
		</div>
		<label class="col-sm-2 control-label">是否社保：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="isHasPf">
				<option value="">--请选择--</option>
           		<option value="1">是</option>
           		<option value="0">否</option>
			</select>
		</div>
	</div>
</div>

<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">收费信息</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">学费：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<input type="text" id="schoolFee" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
		</div>
		<label class="col-sm-2 control-label">押金：</label>
		<div class="col-sm-4">
			<input type="text" id="deposit" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">书费：</label>
		<div class="col-sm-4">
			<input type="text" id="bookFree" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">是否分期：</label>
		<div class="col-sm-4 padding-7">
			<input type="radio" name="isStaging" value="1">是
			<input type="radio" name="isStaging" value="0">否
		</div>
		<label class="col-sm-2 control-label">首付款：</label>
		<div class="col-sm-4">
			<input type="text" id="firstPay" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
		</div>
	</div>
</div>