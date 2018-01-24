<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>
<!DOCTYPE html>
<html>
<head>
	<title>500</title>
</head>
<body>
	<h3 style="margin-top: 10px;">500 - 系统发生内部错误</h3>
    <p><%=exception%></p>
</body>
</html>