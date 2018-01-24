package com.jiacer.modules.system.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.system.utils.UserUtils;




/**
 * 表单验证（包含验证码）过滤类
 * @author ThinkGem
 * @version 2014-5-19
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captchCode";
	public static final String DEFAULT_MESSAGE_PARAM = "message";
	public static final String DEFAULT_TELEPHONE_PARAM = "telephone";
	public static final String DEFAULT_SMSCODE_PARAM = "smsCode";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	private String telephoneParam = DEFAULT_TELEPHONE_PARAM;
	private String smsCodeParam = DEFAULT_SMSCODE_PARAM;

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha = getCaptcha(request);//验证码
		String telephone = getTelePhone(request);//手机号码
		String smsCode = getSmsPhone(request);//短信验证码
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, telephone,smsCode);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMessageParam() {
		return messageParam;
	}
	
	public String getTelePhoneParam(){
		return telephoneParam;
	}
	
	protected String getTelePhone(ServletRequest request) {
		return WebUtils.getCleanParam(request, getTelePhoneParam());
	}
	
	public String getSmsCodeParam(){
		return smsCodeParam;
	}
	
	protected String getSmsPhone(ServletRequest request) {
		return WebUtils.getCleanParam(request, getSmsCodeParam());
	}
	
	
	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}
	
	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		 WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)){
			message = "用户或密码错误, 请重试.";
		}
		else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		}
		else{
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        return true;
	}
	
	   @Override
		protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
	    	HttpServletRequest req = (HttpServletRequest) request;  
	        HttpServletResponse res = (HttpServletResponse) response;  
	        if (UserUtils.getPrincipal() == null){  
	            // 如果是ajax请求响应头会有，x-requested-with；  
	            if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
	                res.setStatus(911);//表示session timeout  
	                return false;
	            }
	            return super.onAccessDenied(request, response);
	        }else{
	        	 return super.onAccessDenied(request, response);
	        }
	      
	    }
	
}