<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<head>
<title>${fns:getConfig('productName')}-学员信息列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
</head>
<body>

	<ul id="breadcrumb" style="display: none">
		<li>教务管理&nbsp;>&nbsp;学员管理&nbsp;>&nbsp;学员信息</li>
	</ul>
	<div class="frame">
		<div class="content">
			<div class="content-area">
				<div class="ca-filter clearfix query_box">
					<form class="form-horizontal" action="" id="queryForm" method="GET">
						<div class="query">
							<p>
								<label>姓名</label>
								<input type="text" id="userName" name="" value="" />
							</p>
							<p>
								<label>联系方式</label>
								<input type="text" id="mobile" name="" value="" />
							</p>
							<a id="queryBtn" class="search-btn"></a>
						</div>
					</form>
				</div>
				<div class="top_btn">
					 <c:if test="${fns:isHasRole('jwgl_export')}" >
						  <a id="exportBtn">
						    <span id="download">导出EXCEL表</span>
					      </a>
					</c:if> 	
				</div>
				<div class="page-body">
					<table id="grid-table"></table>
					<div id="grid-pager"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/modules/users/list.js"></script>
	<script type="text/javascript">
	var defaultOption = '<option value="">---请选择---</option>';
	function initSourceSec(id){
 		$.post(ctx+'/system/params', {type:5,pid:id}, function(data){
 			createSourceSecData(data);
 		});
	};
	function createSourceSecData(data){
		var selectGist = $("#sourceTypeSec");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
   				$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	$(function(){
		$("#sourceType").bind('change',function(){
			var id=$(this).find("option:selected").attr("id");
			initSourceSec(id);
		});
	});
	$(function(){
		  $("#exportBtn").bind("click",function(){
			  $("#queryForm").attr("action",ctx+"/user/export/01");
				$("#queryForm").submit();
		});

	});
	</script>
</body>
</html>







