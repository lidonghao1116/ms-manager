<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<li>运营管理>合作商管理>提成学校</li>
	</ul>
	<!--内容区-->
	<div class="frame">
		<div class="content">
			<div class="content-area">
				<div class="infoBlock">
					<p>&nbsp;</p>
					<div class="ib-content clearfix">
		 			<table style="width:100%">
		            	<tr>
		                	<td>方案名称</td>
		                	<td class="secTd"><input type="text" id="schemeName" name="" value=""></td>
		                </tr>
		              </table>
		            </div>
				</div>
				<div class="infoBlock">
					<h1 class="title">报考信息</h1>
					<div class="ib-content clearfix">
					<table style="width:100%">
						<tr>
							<td>课程：</td>
							<td class="secTd">
								<c:forEach items="${courses}" var="obj" varStatus="item">
			            			<input type="checkbox" name="applyCourses" value="${obj.id}"/>${obj.typeName}
			            		</c:forEach>
							</td>
						</tr>
						<c:forEach items="${products}" var="obj" varStatus="item">
							<tr>
								<td>产品${item.index+1}:</td>
				                <td class="secTd">${obj.packageName}</td>
				                <td style="text-align:right;">提成：</td>
				                <td class="secTd" id="tcValue">
		            				<input type="text" name="bonusAmount" value=""/>&nbsp;元
				            	</td>
			                </tr>
			            </c:forEach>
					</table>
				</div>
				</div>
				<div class="btnCase clearfix">
					<button class="bc-cancel" id="goback" type="button">取消</button>
					<button class="bc-confirm" id="addBtn" type="button">保 存</button>
				</div>
			</div>
		</div>
	</div>

	</form>
	<input id="id" value="${model.id}" type="hidden"/>
	<input id="rules" value="${rules}" type="hidden"/>
	<script src="${ctxStatic}/modules/brokerage/form.js"></script>
	<script src="${ctxStatic}/js/map.js"></script>
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var flag=${update};
		if(flag){
			$.ajax({
		          url :ctx+"/brokerage/rules",
		          type : "POST",
		          data:{id:$("#id").val()},
		          success: function(data){
		            if(data.success){
		            	var rulesMap=new Map();//规则
		            	var rulesObj=data.jsonData;
		            	for(i=0;i<rulesObj.length;i++){
		    				rulesMap.put(rulesObj[i].packageId,rulesObj[i]);
		    			}
		            	$(".rules").each(function(i,val){
		            		var rule=rulesMap.get($(this).find("div:eq(0)").attr("id"));
		            		if(rule!=null){
		            			$(this).find("div:eq(1)").find("input").val(rule.bonusAmount);
		            		}
		            	});
		            }else{
		              	layer.msg(data.msg);
		            }
		          }
	        });
		}
	</script>
</body>
</html>
