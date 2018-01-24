<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>${fns:getConfig('productName')}-首页</title>
		<meta name="decorator" content="blank" />
		<style type="text/css">
			div#logo img { margin-top: 8px; }
			div.main_m{margin-left:20%}
			div.main_m ul{list-style:none;margin: 0;}
			div.main_m li{position: relative; float:left;height: 65px;line-height: 65px;width:100px;background: #6F9937;text-align: center;margin-left:10px;}
			div.main_m li a{color: white;font-size: 16px;}
			div.main_m li.active{background: #6F9937;}

			div.main_m li img{ position: absolute; bottom: 0; left: 50%; margin-left: -8.5px; display: none; line-height: 0;}
		div.main_m li.active img{display: block;}

			#logo ul{list-style:none;}
			#logo li{float:left;}

			.pname{padding-top: 14px;padding-left: 15px;font-size: 18px;color: white;}
			.verticalLine{height:45px; width:2px; border-left:2px #000 solid}
		</style>
	</head>
	<body class="no-skin">
		<!--系统头部区域-->
		<div id="navbar" class="navbar navbar-default" style="cursor: pointer;">
			<div class="navbar-container" id="navbar-container">
				<!-- 窗口缩小是菜单 -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- 系统logo部分 -->
				<div class="navbar-header pull-left" id="logo">
					<ul>
						<li>
							<!--
							<img class="" alt="" src="http://192.168.31.233:8080/jiacerconsole/static/loginImg/logo.png">
							-->
							<img class="" alt="" src="/jiacerconsole/static/loginImg/logo.png">
							<%-- <a href="#" class="navbar-brand">
								<small><i class="fa fa-leaf"></i>${fns:getConfig('productName')}</small>
							</a> --%>
						</li>
						<%-- <li>
							<div class="verticalLine"></div>
						</li>
						<li class="pname">
							<span>综合管理系统</span>
						</li> --%>
					</ul>
				</div>
				<!-- 右侧登录用户信息 -->
				<div class="main_m" id="first_menus">
					<ul></ul>
				</div>
				<!-- 右侧登录用户信息 -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctxStatic}/loginImg/account.png" alt="用户头像" />
								<span class="user-info">
									欢迎,${fns:getUser().loginName}
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>
							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#" data-toggle="modal"  data-target="#myModal">
										<i class="ace-icon fa fa-key"></i>
										修改密码
									</a>
								</li>
								<li>
									<a href="${ctx}/logout">
										<i class="ace-icon fa fa-power-off"></i>
										登出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="main-container" id="main-container">
			<div id="sidebar" class="sidebar responsive">
				<!-- 左侧菜单 sidebar-menu.js菜单-->
					<h1>教务管理</h1>
		       	<ul class="nav nav-list nl-list1" id="menu"></ul>
		       	<div class="sidebar-collapse" id="sidebar-collapse">
		        	<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
		       	</div>
				<!-- 收缩条 -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
			<div class="main-content">
				<div class="main-content-inner">
					<!-- #section:basics/content.breadcrumbs -->
					<div class="breadcrumbs" id="breadcrumbs">
						<!-- 面包屑 开始 -->
						<ul class="breadcrumb">
							<li><i class="ace-icon fa home-icon"></i><a href="#">Home</a></li>
							<li class="active">首页</li>
						</ul>
						<!-- 面包屑 结束 -->
					</div>
					<!-- 主内容 page-->
					<%--<iframe id="mainFrame" name="mainFrame" src="" frameborder="no" width="100%" height="100%" onLoad="iFrameHeight()">--%>
					<%--</iframe>--%>
	<webview id="foo" src="" style="display:inline-block; width:100%; height:100%"></webview>
				</div>
			</div>
			<!-- 页脚-->
			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-120">
						<a href="javascript:void(0)">${fns:getConfig('productName')}</a>
						&copy; 2015-${fns:getConfig('copyrightYear')} - Powered By ${fns:getConfig('productName')} ${fns:getConfig('version')}
						</span>
					</div>
				</div>
			</div>
			<!-- 直接返回顶部 -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>

		<div class="modal fade bs-example-modal-sm in" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title">修改密码</h4>
		            </div>
		            <div class="modal-body">
		                <form id="inputForm" method="post" class="form-horizontal dr-form-bordered">
							<div class="form-group-line"></div>
							<div class="form-group">
								<label class="col-md-3 control-label">旧密码:<span class="dr-asterisk">*</span></label>
								<div class="col-md-5">
									<input id="oldPassword" maxlength="10" value="" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'')" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">新密码:<span class="dr-asterisk">*</span></label>
								<div class="col-md-5">
									<input id="password" type="text" maxlength="10" value="" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'')" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">确认密码:<span class="dr-asterisk">*</span></label>
								<div class="col-md-5">
									<input id="repeatPassword" type="text" maxlength="10" value="" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'')"  class="form-control"/>
								</div>
							</div>
							<div class="form-group">
							<div class="col-sm-8 panel-toolbar text-center dr-slash-text">
								<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="user.modifyPwd();"/>
							</div>
							</div>
						</form>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- ace scripts -->
		<script src="${ctxStatic}/plugins/ace/js/ace.js"></script>
		<script src="${ctxStatic}/plugins/ace/js/ace.sidebar.js"></script>
		<script src="${ctxStatic}/js/map.js"></script>
		<script src="${ctxStatic}/js/menu.js"></script>
		<script type="text/javascript">
			//初始化zh菜单
			$(function(){
				menu.initJsonData();
			});

			var user={
			//保存用户数据
				modifyPwd:function(){
					var params={};
					params.oldPassword=$("#oldPassword").val();
					params.newPassword=$("#password").val();
					params.repeatPassword=$("#repeatPassword").val();
					var result=user.validParam(params);
					if(!result){
						return;
					}

					layer.confirm('确认修改密码吗？',function(){
				        $.ajax({
				          url :ctx+"/system/user/modifyPwd",
				          type : "POST",
				          data: params,
				          async: false,
				          success: function(data){
				            if(data.success){
				              layer.alert(data.msg);
				            }else{
				              layer.msg(data.msg);
				            }
				          }
				        });
		      		});
				}
				,
				//校验参数
				validParam:function(params){
					if(isEmpty(params.oldPassword)){
						layer.msg("请填写[旧密码]");
			    		return false;
					}

					if(isEmpty(params.newPassword)){
						layer.msg("请填写[新密码]");
			    		return false;
					}

					if(params.newPassword==params.oldPassword){
						layer.msg("请填写[新密码和旧密码一样]");
			    		return false;
					}

					if(params.newPassword!=params.repeatPassword){
						layer.msg("[两次密码不一致]");
			    		return false;
					}

					return true;
				}
			}
		</script>
	</body>
</html>
