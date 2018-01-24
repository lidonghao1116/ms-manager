package com.jiacer.modules.common.constants;

import java.util.regex.Pattern;

/**
 * 
 * @ClassName:Constants.java
 * @Description:常量类
 * @Author:ticahmfock
 * @Date:Oct 7, 2017 12:08:59 PM
 */
public class Constants {
	
	public static final String AUTHORITY_GRADE="25";//鉴定等级
	public static final String COURSE_SHELEVES="22";//是否上架
	public static final String COURSE_STATUS_SHELEVES="01";//课程状态--上架
	public static final String COURSE_STATUS_OFFSHELF="02";//课程状态--下架
	public static final String EXAM_TYPE="1";//考试形式
	public static final String YES_NO="24";//是否
	public static final String COURSE_STATUS_NORMAL="正常";//课程状态--上架页面显示为正常
	public static final String COURSE_STATUS_STOP="停止";//课程状态--上架页面显示为停止

	public static final String COURSE_IS_START="1";//courseInstitution表中status状态

	public static final String CFG_PARAM_NATION_TYPE="6";//cfg nation TYPE


	public static final String YTX_DOMAIN = "sms.ytx.domain";
	public static final String YTX_PORT = "sms.ytx.port";
	public static final String YTX_ACCOUNT_SID = "sms.ytx.accountSid";
	public static final String YTX_AUTH_TOKEN = "sms.ytx.authToken";
	public static final String YTX_APP_ID = "sms.ytx.appId";


	public static final Pattern NUMERI_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");
}
