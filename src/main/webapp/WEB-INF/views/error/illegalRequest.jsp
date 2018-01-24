<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>
<!DOCTYPE html>
<html>
<head>
	<title>非法请求</title>
</head>
<body>
	<h3>非法请求,请求参数异常或数据状态已被处理</h3>
    <p><a href="${ctx}/">返回</a></p>
</body>
</html>