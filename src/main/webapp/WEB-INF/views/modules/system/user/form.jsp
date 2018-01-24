<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-登录</title>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
<style type="text/css">

 .permission  {
      font-size: 15px;
      font-weight:bold ;
    }
 

</style>
</head>
<body>
	<ul id="breadcrumb" style="display: none">
			<li>系统管理&nbsp;>&nbsp;账号管理&nbsp;>&nbsp;
	      <c:if test="${!update}">		
		        新增账号
		  </c:if> 
		  <c:if test="${update}">
			 编辑账号
		  </c:if>
		  </li>
	</ul>
	<div class="frame">
       <div class="content">
         <div class="content-area">
			<form class="form-horizontal">
			  <div class="infoBlock">
			  	<h1 class="title"> </h1>
			    <div class="ib-content clearfix">
			    	<table style="width:100%">
			    		<tr>
			    			<td><span class="dr-asterisk">*</span>账号：</td>
			    			<td class="secTd">
			    				<c:if test="${!update}">
								    ${fns:getUser().getRootLoginAccount()}
								    <input type="text" id="loginAccount" placeholder="(三位数字，如：001)" style="margin-left:2px; width:166px;" />
								    <a style="color:grey;margin-left:5px;">(创建后，默认密码000000)</a>
								</c:if>
								
								<c:if test="${update}">
									${user.loginAccount}
		                            <input type="hidden" id="loginAccount" placeholder="登陆账号" value="${user.loginAccount}"  class="form-control" />
								    <input style="width:70px;height:24px;line-height:24px;font-size:10px;" type="button" id="btnResetPwd" value="重置密码" />
								    <span id="resetPwdTips" ></span>
								</c:if>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td><span class="dr-asterisk">*</span>姓名：</td>
			    			<td class="secTd">
			    				<input type="text" id="loginName" value="${user.loginName}" placeholder="姓名" class="form-control" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td><span class="dr-asterisk">*</span>手机：</td>
			    			<td class="secTd">
			    				<input type="text" id="telephone" value="${user.telephone}" placeholder="手机" class="form-control" />
			    			</td>
			    		</tr>
			    		<c:if test="${update}">
			    		<tr>
	                    	<td><span class="dr-asterisk">*</span>是否允许登录：</td>
	                        <td class="secTd">
	                            <select class="chosen-select form-control" id="loginFlag"  data-placeholder="Choose a State...">
	                                <option value="1" <c:if test="${user.loginFlag=='1'}">selected</c:if>>是</option>
	                                <option value="0" <c:if test="${user.loginFlag=='0'}">selected</c:if>>否</option>
	                            </select>
	                       </td>
	                      </tr>
						</c:if>
			    	</table>
			      </div>
			    </div>
			    <div class="infoBlock">
			   		 <h1 class="title"> </h1>
			    	<div class="ib-content clearfix">
			    	<table>
				    	<tr>
			    			<td><span class="dr-asterisk">*</span>授权：</td>
			    			<td class="secTd">
			    				<div style="background-color: #FFF;padding: 10px;border: 1px solid #CCCCCC;">
			    				    <input  id="roles" value="${user.roles}" hidden />
			    					<p>
				    					<label>教务管理：</label>
				    					 <span  style="margin-left:10px"><input name="" id="jwgl_edit" type="checkbox" 	value="jwgl_edit"			    					 
				    					 		<c:forEach items="${user.roles}" var="p"> 
													<c:if test="${p.rcode eq 'jwgl_edit'}" >
												       checked
													</c:if>    
												</c:forEach>			    					  
				    					     >编辑</span>
                       					 <span  style="margin-left:10px"><input name="" id="jwgl_export" type="checkbox"  value="jwgl_export"
                       					 		<c:forEach items="${user.roles}" var="p"> 
													<c:if test="${p.rcode eq 'jwgl_export'}" >
												       checked
													</c:if>    
												</c:forEach>	
                       					    >导出</span>
			    					</p>
			    					<p>
				    					<label>运营管理：</label>
				    					<span  style="margin-left:10px"><input name="" id="yygl_edit" type="checkbox" value="yygl_edit"
				    					        <c:forEach items="${user.roles}" var="p"> 
													<c:if test="${p.rcode eq 'yygl_edit'}" >
												       checked
													</c:if>    
												</c:forEach>
				    					    >编辑</span>
				    					<span  style="margin-left:10px"><input name="" id="yygl_export" type="checkbox"  value="yygl_export"
				    					        <c:forEach items="${user.roles}" var="p"> 
													<c:if test="${p.rcode eq 'yygl_export'}" >
												       checked
													</c:if>    
												</c:forEach>
				    					    >导出</span>
			    					</p>
			    					<p>
				    					<label>系统管理：</label>
				    					<span  style="margin-left:10px">无浏览权限</span>
				    					<span></span>
			    					</p>
			    				</div>
			    			</td>
			    		</tr>
			    	</table>
			     </div>
			    </div>
			    <div class="btnCase btnCase_sp clearfix">
				<c:if test="${update == 'false'}">
                   <button class="bc-confirm" type="button" id="addBtn" onclick="">保存</button>
                   </c:if>
                   <c:if test="${update == 'true'}">
                  <button class="bc-confirm" id="modifyBtn" type="button" onclick="">保存</button>
                  </c:if>
                  <button type="button" id="goBack" onclick="history.go(-1)">返回</button>
                </div>
	
			</form>
		</div>
	</div>
</div>
	<input type="hidden" id="id" value="${user.id}" />
	<script src="${ctxStatic}/modules/system/user_form.js"></script>
</body>
</html>