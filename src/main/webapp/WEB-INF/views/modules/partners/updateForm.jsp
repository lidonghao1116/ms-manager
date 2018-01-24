<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>${fns:getConfig('productName')}-学校管理</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
<body>
	<form class="form-horizontal">
	<ul id="breadcrumb" style="display: none">
		<li>运营管理>合作商管理>合作商列表</li>
	</ul>
	<!--内容区-->
	<div class="frame">
		<div class="content">
			<div class="content-area">
				<div class="infoBlock">
					<h1 class="title">基本信息</h1>
					<div class="ib-content clearfix">
              <table style="width:100%">
                <tr>
                  <td>合作商类型：</td>
                  <td class="secTd">
                    <select class="" name="" id="partnerType">
					<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(5,28)}" var="obj" varStatus="item">
							<option value="${obj.value}" <c:if test="${model.partnerType==obj.value}">selected="selected" </c:if>>${obj.text}</option>
						</c:forEach>
                  </select>
                </td>
                  <td style="text-align:right;">合作商名称：</td>
                  <td class="secTd"><input type="text" id="partnerName" name="" value="${model.partnerName}"></td>
                </tr>
                <tr>
                  <td>合作商地址：</td>
                  <td colspan="3" class="secTd">
                    <select class="" id="province" name="">

                    </select>
                    <select class="" id="city" name="">

                    </select>
                    <select class="" id="county" name="">

                    </select>
                    <input type="text" id="address" name="" value="${model.address}">
                  </td>
                </tr>
                <tr>
                  <td>邮编：</td>
                  <td colspan="3" class="secTd">
                    <input type="text" id="zipCode" name="" value="${model.zipCode}">
                  </td>
                </tr>
                <tr>
                  <td>联系人：</td>
                  <td class="secTd">
                      <input type="text" name="" id="contacts" value="${model.contacts}">
                  </td>
                  <td style="text-align:right;">手机号码：</td>
                  <td class="secTd">
                      <input type="text" name="" id="contactPhone" value="${model.contactPhone}">
                  </td>
                </tr>
                <tr>
                  <td>门店电话：</td>
                  <td colspan="3" class="secTd">
                      <input type="text" name="" id="storePhone" value="${model.storePhone}">
                  </td>
                </tr>
                <tr>
                  <td>销售员：</td>
                  <td colspan="3" class="secTd">
                      <input type="text" name="" id="salesman" value="${model.salesman}">
                  </td>
                </tr>
              </table>
            </div>
          </div>
          <div class="infoBlock">
            <h1 class="title">管理信息</h1>

            <div class="ib-content clearfix">
              <table style="width:100%">
                <tr>
                  <td>提成方案：</td>
                  <td class="secTd">
                    <select class="" id="brokerageId" name="">
						<option value="">--请选择--</option>
						<c:forEach items="${schemes}" var="obj" varStatus="item">
							<option value="${obj.id}" <c:if test="${model.brokerageId==obj.id}">selected="selected"</c:if>>${obj.schemeName}</option>
						</c:forEach>
                    </select>
                  </td>
                </tr>
              </table>
            </div>
          </div>
				<div class="btnCase clearfix">
					<button class="bc-cancel" type="button"  onclick="return goback();">取消</button>
					 <c:if test="${fns:isHasRole('yygl_edit')}" >
					     <button class="bc-confirm" id="modifyBtn" type="button" onclick="return add();">保 存</button>
				     </c:if>
				</div>
			</div>
		</div>
	</div>

	</form>
	<input id="id" value="${model.id}" type="hidden" />
	<input id="o_province" value="${model.province}" type="hidden" />
	<input id="o_city" value="${model.city}" type="hidden" />
	<input id="o_county" value="${model.county}" type="hidden" />
	<script src="${ctxStatic}/modules/partners/form.js"></script>

	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		function goback()
		{window.location.href=config.partnersListUrl;}
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