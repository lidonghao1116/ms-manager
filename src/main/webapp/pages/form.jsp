<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-刷新缓存</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#"></a></li>
		<li class="active">刷新缓存</li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
		</div>
	</div>
	<script type="text/javascript">
		$.ajax({
	        url :ctx+"/system/params/flush",
	        type : "POST",
	        data: {},
	        success: function(data){
	          if(data.success){
	          	layer.alert(data.msg, {icon:1});
	          }else{
            	layer.msg(data.msg);
	          }
	        }
	    });
	</script>
</body>
</html>