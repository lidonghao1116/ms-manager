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
		                	<td class="secTd"><input type="text" name="" value="${model.schemeName}"></td>
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
				                <td class="secTd">
				                	<!-- 
				                	<input type="text" name="bonusAmount" value="">&nbsp;元
				                	 -->
				                	 <input type="text" name="className[0].classTimes" value="">&nbsp;元
				                </td>
			                </tr>
			            </c:forEach>
					</table>
				</div>
				</div>
				<div class="btnCase clearfix">
					<button class="bc-cancel" id="goback" type="button" >取消</button>
					
					<c:if test="${fns:isHasRole('yygl_edit')}" >
					   <button class="bc-confirm" id="modifyBtn" type="button" onclick="return update();">保 存</button>
				     </c:if>
				</div>
			</div>
		</div>
	</div>

	</form>
	<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctxStatic}/modules/brokerage/form.js"></script>
	<script>

    </script>
</body>
</html>
