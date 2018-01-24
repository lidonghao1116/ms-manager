<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><h1>学校简介:</h1>
${Introduce}
</p>
<button id="update_introduce" name="update" onclick="updateSchoolsIntroduce()">修改</button>
</body>
<script type="text/javascript">
function updateSchoolsIntroduce(){
	window.open="/intro/getSchoolIntroduce"
}

</script>


</html>