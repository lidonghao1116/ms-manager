<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>
<!DOCTYPE html>
<html>
<head>
	<title>401</title>
</head>
<body>
	<h3>401 - 未经授权禁止访问</h3>
    <p><a href="${ctx}/">返回</a></p>
</body>
</html>