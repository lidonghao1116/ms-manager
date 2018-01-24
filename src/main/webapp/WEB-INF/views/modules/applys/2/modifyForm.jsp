<%@ page contentType="text/html;charset=UTF-8"%>
<div class="col-sm-12 text-center">
	<button class="btn btn-sm btn-primary" id="forceBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>强制退学</button>
</div>
<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">受理时间：</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">处理时间：</label>
		<div class="col-sm-4 padding-7">
			<fmt:formatDate value="${model.handleTime}" type="both"/>
		</div>
		<label class="col-sm-2 control-label">报名时间：</label>
		<div class="col-sm-4 padding-7">
			<fmt:formatDate value="${model.orderTime}" type="both"/>
		</div>
	</div>
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">受理人：</label>
		<div class="col-sm-4 padding-7">
			${model.handler}
		</div>
	</div>

	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">订单类别：</label>
		<div class="col-sm-4 padding-7">
			${model.orderType}
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
			${model.userExtendInfo.userName}
		</div>
		<label class="col-sm-2 control-label">身份证号：</label>
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
		<label class="col-sm-2 control-label">籍贯：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4 padding-7">
			${fns:getText(4,0,model.userExtendInfo.birthplace)}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">户籍地址：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4 padding-7">
			${model.userExtendInfo.address}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">紧急联系人：</label>
		<%-- <div class="col-sm-4 padding-7">
			${model.userExtendInfo.contacts}
		</div> --%>

		<div class="col-sm-4 padding-7">
			<input type="text" id="contacts" value="${model.userExtendInfo.contacts}" maxlength="50" class="form-control" />
		</div>

		<label class="col-sm-2 control-label">紧急联系电话：</label>
		<%-- <div class="col-sm-4 padding-7">
			${model.userExtendInfo.contactPhone}
		</div> --%>

		<div class="col-sm-4 padding-7">
			<input type="text" id="contactPhone" value="${model.userExtendInfo.contactPhone}" maxlength="50" class="form-control" />
		</div>

	</div>
</div>
<div class="form-group" style="margin-bottom: 5px;">
  <label class="col-md-2 controls">来源信息</label>
</div>
<div class="form-group form-color">
	<div class="form-group form-padding">
		<label class="col-sm-2 control-label">来源：</label>
			${model.sourceWholeText}
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">备注：</label>
		<div class="col-sm-4 padding-7">
			<input type="text" id="sourceRemarks" value="${model.sourceRemarks}" maxlength="50" class="form-control" />
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
		<label class="col-sm-2 control-label">班级标号：<span class="dr-asterisk">*</span></label>
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="classNumber"></select>
		</div>
	</div>
	<div class="form-group form-padding">
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
		<div class="col-sm-4">
			<select class="chosen-select form-control" id="isHasPf">
				<option value="">--请选择--</option>
           		<option value="1" <c:if test="${model.isHasPf=='1'}">selected="selected" </c:if>>是</option>
           		<option value="0" <c:if test="${model.isHasPf=='0'}">selected="selected" </c:if>>否</option>
			</select>
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
		<div class="col-sm-4">
			<input type="text" id="bookFree" maxlength="50" value="${model.bookFree}" class="form-control" style="width: 80%;display: initial;"/>元
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">是否分期：</label>
		<div class="col-sm-4  padding-7">
			<input type="radio" name="isStaging" value="1" <c:if test="${model.isStaging=='1'}">checked="checked" </c:if>>是
			<input type="radio" name="isStaging" value="0" <c:if test="${model.isStaging=='0'}">checked="checked" </c:if>>否
		</div>
		<label class="col-sm-2 control-label">首付款：</label>
		<div class="col-sm-4">
			<input type="text" id="firstPay" value="${model.firstPay}" class="form-control" style="width: 80%;display: initial;"/>元
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">费用已结清：</label>
		<div class="col-sm-4 padding-7">
			<input type="checkbox" name="isClear" <c:if test="${model.isClear=='1'}">checked="checked" </c:if>>已结清
		</div>
	</div>
</div>
