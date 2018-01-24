package com.jiacer.modules.system.security;

/**
 * 用户和密码（包含验证码）令牌类
 * @author ThinkGem
 * @version 2013-5-19
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;//验证码
	private String telephone;//手机号码
	private String smsCode;//手机验证码
	
	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha, String telephone,String smsCode) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.telephone = telephone;
		this.smsCode = smsCode;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
}