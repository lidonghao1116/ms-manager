<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-登录</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/base.css">
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/login.css">
<%-- <script src="${ctxStatic}/js/supersized.3.2.7.min.js"></script>
<script src="${ctxStatic}/js/supersized-init.js"></script> --%>
</head>
<body>
<!--头部-->
<header>
  <div class="login-content">
    <a class="login-link" href="http://b.jiacedu.com"></a>
  </div>
</header>
<!--登陆主体-->
<section>
  <div class="login-content">
    <img src="${ctxStatic}/loginImg/class.png" alt="" class="teach">
    <!--登陆界面-->
    <div class="login-box">
			<form id="loginForm" action="${ctx}/login" method="post">
	      <h4>会员登录</h4>
	      <div class="input-wrapper input-wrapper_sp mt25 mt25_bg">
					<input type="text" id="userName" name="username" placeholder="登录账号" value="${username}" class="required input-box bdc" maxlength="40"/>
	        <%-- <input type="text" name="account" id="account" placeholder="登录账号" class="input-box bdc" maxlength="40"> --%>
	      </div>
	      <div class="input-wrapper input-wrapper_sp mt22 mt22_bg">
					<input type="password" id="password" name="password" placeholder="登录密码" class="required input-box bdc" maxlength="40"/>
	        <%-- <input type="password" name="password" id="password" placeholder="登录密码" class="input-box bdc" maxlength="40"> --%>
	      </div>
	      <div class="input-wrapper mt22">
					<input type="text" placeholder="输入右侧验证码" id="captchCode" name="captchCode" class="required input-box-sm bdc pct100" maxlength="6"/>
	        <%-- <input type="text" name="security-code" id="security-code" placeholder="输入右侧验证码" class="input-box-sm bdc pct100" maxlength="6"> --%>
					<img id="captcha" src="${ctx}/common/getCaptcha" style="cursor: pointer;" onclick="changeCaptcha()">
				  <%-- <img src="" alt="" id="img-code"> --%>
	      </div>
	      <div class="btn-wrapper mt40">
	        <button class="" id="login-btn" type="submit"><i class="icon-key"></i>登录</button>
	      </div>
				<c:if test="${not empty message}">
					<div class="form-group col-md-12">
						<div class="error_t">
							<%-- <button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button> --%>
							${message}
						</div>
					</div>
				</c:if>
			</form>
    </div>
  </div>
</section>
<!--尾页-->
<footer>
  <p>上海家策教育科技有限公司 上海市徐汇区南丹东路300号亚都商务楼1201室</p>
  <p class="mt10">© 2016-2017 家策商学院 沪ICP备16022947号-2</p>
</footer>
<script>
	function changeCaptcha() {
		$("#captcha").attr("src",
				ctx + "/common/getCaptcha?date=" + new Date());
	}
</script>
</body>
</html>
