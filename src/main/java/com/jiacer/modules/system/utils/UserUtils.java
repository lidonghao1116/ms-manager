package com.jiacer.modules.system.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jiacer.modules.common.utils.CacheUtils;
import com.jiacer.modules.common.utils.SpringContextHolder;
import com.jiacer.modules.mybatis.dao.SysUsersMapper;
import com.jiacer.modules.mybatis.entity.SysRoleEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.security.SystemAuthorizingRealm.Principal;

/**
 * 用户工具类
 * @author hzp
 *
 */
public class UserUtils {
	
	private static SysUsersMapper userDao = SpringContextHolder.getBean(SysUsersMapper.class);
	
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	public static final String CACHE_MENU_LIST = "menuList";
	
	public static SysUsersEntity getUser(String loginName){
		SysUsersEntity user=(SysUsersEntity)CacheUtils.get(SysConstants.USER_CACHE, SysConstants.USER_CACHE_LOGIN_NAME_ + loginName);
		if (user!=null){
			return user;
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUsersEntity();
	}
	
	public static SysUsersEntity getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			SysUsersEntity user = get(principal.getId());
			if (user != null){
				user.setRootLoginAccount(principal.getRootLoginAccount());
				user.setRoles(principal.getRoles());
			//	user.setMenus(principal.getMenus());
				return user;
			}
			return new SysUsersEntity();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUsersEntity();
	}
	
	public static boolean isHasRole(String role){
		Principal principal = getPrincipal();
		if (principal!=null){
			SysUsersEntity user = get(principal.getId());
			if (user != null){
				  user.setRoles(principal.getRoles());
				//  user.setMenus(principal.getMenus());
				for(SysRoleEntity roleEntity:user.getRoles())
				{
					if(roleEntity.getRcode().equals(role))
						return true;
				}
				
				return false;
			}
			return false;
		}
		// 如果没有登录，则返回实例化空的User对象。
		return false;
	}
	
	
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(SysUsersEntity user){
		CacheUtils.remove(SysConstants.USER_CACHE, SysConstants.USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(SysConstants.USER_CACHE, SysConstants.USER_CACHE_LOGIN_NAME_ + user.getLoginName());
	}
	
	public static Session getSession() {
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	public static String getLoginUserId(){
    	Principal principal = UserUtils.getPrincipal();
    	if (principal!=null){
    		return String.valueOf(principal.getId());
		}
    	return "";
    }
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}


	public static String getAccount() {
		Principal principal = UserUtils.getPrincipal();
    	if (principal!=null){
    		return principal.getAccount();
		}
    	return "";
	}


	public static String getLoginPhone() {
		Principal principal = UserUtils.getPrincipal();
    	if (principal!=null){
    		return principal.getTelephone();
		}
    	return "";
	}
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static SysUsersEntity get(Integer id){
		SysUsersEntity user = (SysUsersEntity)CacheUtils.get(SysConstants.USER_CACHE, SysConstants.USER_CACHE_ID_ + id);
		if (user ==  null){
			user= userDao.getById(id);
			user.setRootLoginAccount(userDao.getRootAccount(user.getInstitutionInfoId()).getLoginAccount());
			if (user == null){
				return null;
			}
			CacheUtils.put(SysConstants.USER_CACHE, SysConstants.USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(SysConstants.USER_CACHE, SysConstants.USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
}
