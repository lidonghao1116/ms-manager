<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-提成管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">提成管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/brokerage/list">提成列表</a></li>
		<li class="active"><a href="${ctx}/brokerage/form?id=${model.id}">提成${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">方案名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schemeName" maxlength="50" class="form-control" />
							</div>
						</div>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<c:forEach items="${products}" var="obj" varStatus="item">
								<div class="form-group rules">
			            			<label class="col-sm-2 control-label">产品${item.index+1}:</label>
			            			<div class="col-sm-4 padding-7"  id="${obj.id}">${obj.packageName}</div>
			            			<label class="col-sm-2 control-label">提成<span class="dr-asterisk">*</span></label>
			            			<div class="col-sm-4">
			            				<input type="text" name="bonusAmount" value=""  style="width: 80%;display: initial;"/>元
			            			</div>
	            				</div>
			            	</c:forEach>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">方案名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schemeName" maxlength="50" class="form-control" value="${model.schemeName}"/>
							</div>
						</div>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<c:forEach items="${products}" var="obj" varStatus="item">
								<div class="form-group rules">
			            			<label class="col-sm-2 control-label">产品${item.index+1}:</label>
			            			<div class="col-sm-4 padding-7"  id="${obj.id}">${obj.packageName}</div>
			            			<label class="col-sm-2 control-label">提成<span class="dr-asterisk">*</span></label>
			            			<div class="col-sm-4">
			            				<input type="text" name="bonusAmount" value=""  style="width: 80%;display: initial;"/>元
			            			</div>
	            				</div>
			            	</c:forEach>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				
				</c:if>
			</form>
		</div>
	</div>
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