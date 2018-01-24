<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-合作商管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">合作商列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/partners/list">合作商列表</a></li>
		<li class="active"><a href="${ctx}/partners/form?id=${model.id}">合作商${update?'修改':'新增'}</a></li>
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
							<label class="col-sm-2 control-label">合作商类型<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="partnerType">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(5,28)}" var="obj"
										varStatus="item">
										<option value="${obj.value}">${obj.text}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label">合作商名称<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="partnerName" maxlength="50"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">合作商地址<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="province"></select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="city"></select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="county"></select>
							</div>
							<div class="col-sm-4">
								<input type="text" id="address" maxlength="50"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">邮编<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="zipCode" maxlength="50"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contacts" maxlength="50"
									class="form-control" />
							</div>
							<label class="col-sm-2 control-label">手机号码<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contactPhone" maxlength="50"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">门店号码<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="storePhone" maxlength="50"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">销售员<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="salesman" maxlength="50"
									class="form-control" />
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
						<label class="col-md-2 controls">管理信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">提成方案</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="brokerageId">
									<option value="">--请选择--</option>
									<c:forEach items="${schemes}" var="obj" varStatus="item">
										<option value="${obj.id}">${obj.schemeName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button"
							onclick="">
							<i class="fa fa-floppy-o"></i>保 存
						</button>
						<button class="btn btn-sm btn-primary" type="button"
							onclick="history.go(-1)">
							<i class="fa fa-reply"></i>返回
						</button>
					</div>
				</c:if>
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
						<label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">合作商编号</label>
							<div class="col-sm-4 padding-7">${model.partnerCode}</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">合作商类型<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="partnerType"
									disabled="disabled">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(5,28)}" var="obj"
										varStatus="item">
										<option value="${obj.value}"
											<c:if test="${model.partnerType==obj.value}">selected="selected" </c:if>>${obj.text}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合作商名称<span
									class="dr-asterisk">*</span></label>
								<div class="col-sm-4">
									<input type="text" id="partnerName" maxlength="50"
										class="form-control" value="${model.partnerName}" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">合作商地址<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="province">
								</select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="city">
								</select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="county">
								</select>
							</div>
							<div class="col-sm-4">
								<input type="text" id="address" maxlength="200"
									class="form-control" value="${model.address}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">邮编<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="zipCode" maxlength="50"
									class="form-control" value="${model.zipCode}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contacts" maxlength="50"
									class="form-control" value="${model.contacts}" />
							</div>
							<label class="col-sm-2 control-label">手机号码<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contactPhone" maxlength="50"
									class="form-control" value="${model.contactPhone}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">门店号码<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="storePhone" maxlength="50"
									class="form-control" value="${model.storePhone}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">销售员<span
								class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="salesman" maxlength="50"
									class="form-control" value="${model.salesman}" />
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
						<label class="col-md-2 controls">管理信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">提成方案</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="brokerageId">
									<option value="">--请选择--</option>
									<c:forEach items="${schemes}" var="obj" varStatus="item">
										<option value="${obj.id}"
											<c:if test="${model.brokerageId==obj.id}">selected="selected"</c:if>>${obj.schemeName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
						<label class="col-md-2 controls">合作信息</label>
					</div>

					<div class="form-group form-color">
						<h1
							style="margin: 0 20px; border-bottom: 1px solid #b5b5b5; line-height: 40px; font-size: 16px;">
							输出总人数：<span style="color: #333; font-size: 14px;">${numTotal}</span>
						</h1>
						<ul class="col-md-12 list_inline"
							style="line-height: 30px; padding-top: 10px; color: #333; list-style-type: none;"
							id="partId">
							<%-- <c:forEach items="${model.leatnList}" var="item">
								<li class="col-md-3 text-right" style="padding: 0;">${item.typeName}</li>
								<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${item.countTotal}</li>
							</c:forEach> --%>
							<c:choose>
								<c:when test="${model.leatnList[0].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[0].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[0].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">高级催乳师(进阶班)</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${model.leatnList[1].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[1].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[1].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">初级催乳师</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${model.leatnList[2].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[2].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[2].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">家庭营养师</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${model.leatnList[3].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[3].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[3].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">母婴护理专项</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${model.leatnList[4].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[4].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[4].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">育婴员(初级)</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${model.leatnList[5].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[5].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[5].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">高级催乳师(零基础)</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${model.leatnList[6].typeName!=null}">
									<li class="col-md-3 text-right" style="padding: 0;">${model.leatnList[6].typeName}</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;${model.leatnList[6].countTotal}</li>
								</c:when>
								<c:otherwise>
									<li class="col-md-3 text-right" style="padding: 0;">高级催乳师(直通班)</li>
									<li class="col-md-3 text-left" style="padding: 0;">&nbsp;&nbsp;0</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn"
							type="button" onclick="">
							<i class="fa fa-floppy-o"></i>保 存
						</button>
						<button class="btn btn-sm btn-primary" type="button"
							onclick="history.go(-1)">
							<i class="fa fa-reply"></i>返回
						</button>
					</div>

				</c:if>
			</form>
		</div>
	</div>
	<input id="id" value="${model.id}" type="hidden" />
	<input id="o_province" value="${model.province}" type="hidden" />
	<input id="o_city" value="${model.city}" type="hidden" />
	<input id="o_county" value="${model.county}" type="hidden" />
	<script src="${ctxStatic}/modules/partners/form.js"></script>

	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var item = [ "#province", "#city", "#county" ];
		var itemValue = [ $("#o_province").val(), $("#o_city").val(),
				$("#o_county").val() ];
		var defaultOption = '<option value="">---请选择---</option>';
		var flag = true;

		function getIndex($item) {
			for (var i = 0; i < item.length; i++) {
				if (item[i] == $item) {
					return i;
				}
			}
			return 0;
		}

		function createArearSelect(areaData) {
			resetSelect(item[0]);
			initSelectData(areaData, item[0], itemValue[0]);
			var update = $
			{
				update
			}
			;
			if (update) {
				$.each(areaData, function(i, param) {
					if (param.areaCode == itemValue[0]) {
						initSelectData(param.childList, $(item[1]),
								itemValue[1]);
						bindChange(item[1], param.childList);
						$.each(param.childList, function(i, obj) {
							if (obj.areaCode == itemValue[1]) {
								initSelectData(obj.childList, $(item[2]),
										itemValue[2]);
							}
						});
					}
				});
			}
			bindChange(item[0], areaData);
		};

		function bindChange($item, areaData) {
			var index = getIndex($item) + 1;
			if (index == item.length) {
				flag = false;
			} else {
				flag = true;
			}

			if (!flag) {
				return;
			}
			var selectGist1 = $(item[index - 1]);
			selectGist1.bind('change', function() {
				var $this = $(this).val();
				resetSelect("#" + $(this).attr("id"));
				getDataInfo(areaData, $this, itemValue[index - 1], index)
			});
		}
		//获取精确的数据根据arercode
		function getDataInfo(areaData, areaCode, selectValue, index) {
			var selectGist2 = $(item[index]);
			$.each(areaData, function(i, param) {
				if (param.areaCode == areaCode) {
					initSelectData(param.childList, selectGist2, selectValue);
					bindChange(item[index], param.childList);
					return false;
				}
			});
		}

		function resetSelect($item) {
			for (var i = 0; i < item.length; i++) {
				if ($item == item[i]) {
					for (var j = i + 1; j < item.length; j++) {
						$(item[j]).html("")
						$(item[j]).append(defaultOption);
					}
					return false;
				}
			}
		};

		function initSelectData(areaData, $item, value) {
			var selectGist = $($item);
			selectGist.html('');
			selectGist.append(defaultOption);
			if (areaData) {
				$.each(areaData, function(i, obj) {
					if (value == obj.areaCode) {
						$(
								'<option value="'+obj.areaCode+'" selected="selected">'
										+ obj.areaName + '</option>').appendTo(
								selectGist);
					} else {
						$(
								'<option value="'+obj.areaCode+'">'
										+ obj.areaName + '</option>').appendTo(
								selectGist);
					}
				});
			}
		}

		function getArearSelect(pCode, item_1, item_2, item_next) {
			$.post(ctx + '/system/areas/init', {}, function(data) {
				createArearSelect(data);
			});
		};

		$(function() {
			getArearSelect();
		});
	</script>
</body>
</html>