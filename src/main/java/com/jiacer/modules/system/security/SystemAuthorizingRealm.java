package com.jiacer.modules.system.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.CaptchaCode;
import com.jiacer.modules.common.utils.Encodes;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.SysMenusEntity;
import com.jiacer.modules.mybatis.entity.SysRoleEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.service.SystemService;
import com.jiacer.modules.system.utils.UserUtils;


/**
 * 系统安全认证实现类
 * @author ThinkGem
 * @version 2014-7-5
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	SystemService systemService;
	
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";// 验证码放到session中的key

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		// 校验登录验证码
		if("true".equals(Global.getConfig(SysConstants.CAPTCHA))){
			Session session = UserUtils.getSession();
			CaptchaCode captchCodeCache=(CaptchaCode)session.getAttribute(RANDOMCODEKEY);
			if (token.getCaptcha() == null ||captchCodeCache==null || StringUtils.isBlank(captchCodeCache.getCode())  || !token.getCaptcha().toUpperCase().equals(captchCodeCache.getCode())){
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
			
			if(captchCodeCache.isExpired()){
				throw new AuthenticationException("msg:验证码已过期, 请重试.");
			}
		}
		
		// 校验用户名密码
		SysUsersEntity user = systemService.getUseByLoginAccount(token.getUsername());

		if (user != null) {
			if (Global.NO.equals(user.getLoginFlag())){
				throw new AuthenticationException("msg:该帐号已禁止登录.");
			}
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));//盐值
			return new SimpleAuthenticationInfo(new Principal(user), 
					user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		} else {
			logger.error(String.format("该帐号已不存在"));
			throw new AuthenticationException("msg:该帐号已不存在.");
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		SysUsersEntity user = systemService.getUseByLoginAccount(principal.getAccount());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			/*
			 *
			 // 添加用户权限
			List<SysMenusEntity> menus=user.getMenus();
			for(SysMenusEntity menu:menus)
			{
				info.addStringPermission(menu.getmCode());
			}
			 */

			
			info.addStringPermission("user");
			//添加用户角色
			List<SysRoleEntity> roles=user.getRoles();
			for(SysRoleEntity role:roles)
			{
				info.addRole(role.getRcode());
			}
			
			CacheUtils.put(SysConstants.USER_CACHE, SysConstants.USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(SysConstants.USER_CACHE, SysConstants.USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
			return info;
		} else {
			return null;
		}
	}
	
	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}
	
	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
        		authorizationValidate(permission);
            }
        }
		return super.isPermitted(permissions, info);
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}
	
	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
            	authorizationValidate(permission);
            }
        }
		return super.isPermittedAll(permissions, info);
	}
	
	/** 重写退出时缓存处理方法 */  
    protected void doClearCache(PrincipalCollection principalcollection) {  
    	Principal principal = (Principal) principalcollection.getPrimaryPrincipal();  
        clearCachedAuthorizationInfo(principal);
        UserUtils.clearCache();
    }  
	
	/**
	 * 授权验证方法
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SysConstants.HASH_ALGORITHM);
		matcher.setHashIterations(SysConstants.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(Principal principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private Integer id; // 编号
		private String account; // 登录账号
		private String name; // 姓名
		private String telephone;//手机号码
		private String isInit;//是否已初始化 0表示已经改过密码
		private Integer institutionInfoId;
		private String rootLoginAccount;//机构管理员账户
		private List<SysRoleEntity> roles;
		//private List<SysMenusEntity> menus;

		

		public Principal(SysUsersEntity user) {
			this.id = user.getId();
			this.account = user.getLoginAccount();
			this.name = user.getLoginName();
			this.telephone = user.getTelephone();
			this.isInit = user.getIsInit();
			this.institutionInfoId = user.getInstitutionInfoId();
			this.roles=user.getRoles();
		    //this.menus=user.getMenus();
			this.rootLoginAccount=user.getRootLoginAccount();
		}


		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getIsInit() {
			return isInit;
		}

		public void setIsInit(String isInit) {
			this.isInit = isInit;
		}

		public Integer getInstitutionInfoId() {
			return institutionInfoId;
		}


		public void setInstitutionInfoId(Integer institutionInfoId) {
			this.institutionInfoId = institutionInfoId;
		}
		
		public List<SysRoleEntity> getRoles() {
			return roles;
		}


		public void setRoles(List<SysRoleEntity> roles) {
			this.roles = roles;
		}

        /*
         * public List<SysMenusEntity> getMenus() {
			return menus;
		}


		public void setMenus(List<SysMenusEntity> menus) {
			this.menus = menus;
		}
         */
		

		public String getRootLoginAccount() {
			return rootLoginAccount;
		}


		public void setRootLoginAccount(String rootLoginAccount) {
			this.rootLoginAccount = rootLoginAccount;
		}


		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try{
				return String.valueOf(UserUtils.getSession().getId()) ;
			}catch (Exception e) {
				return "";
			}
		}

	}
}
