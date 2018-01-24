<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>
<!DOCTYPE html>
<html>
<head>
	<title>400</title>
</head>
<body>
	<h3>400 - 错误的请求，访问的链接或表单校验有误</h3>
    <p><a href="${ctx}/">返回</a></p>
</body>
</html>