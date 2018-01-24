package com.jiacer.modules.system.config;

public class SysConstants{
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final int SALT_SIZE_TEN = 10;
	public static final String  ENTRY_STRING= "{[(%s)]}";
	public static final String DEL_FLAG_NORMAL="0";
	public static final String USER_CACHE = "userCache";
	public static final String SYS_CACHE = "sysCache";
	public final static String DEFAULT_PAGE_NO="0";//默认当前第几页
	public final static String DEFAULT_PAGE_SIZE="10";//默认分页条数
	public static final String CAPTCHA="user.captcha";//是否有验证码
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String UPLOAD_PATH = "uploadPath";
	
	//字典参数顶级id
	public static final int PARAM_BASIC_ID=0;
	
	public static final String MIN_TIME="00:00:00";
	public static final String MAX_TIME="23:59:59";
	
	public static class CacheType{
		public static String TYPE_AREA="01";//区域缓存
		public static String TYPE_PARAMS="02";//参数缓存
		public static String TYPE_COURSE="03";//课程缓存
		public static String TYPE_SCHOOLS="04";//学校缓存
	}
}
