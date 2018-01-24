package com.jiacer.modules.system.config;

/**
 * @Description: 系统请求URL路径
 * @author hzp
 * @date 2016-5-23
 *
 */
public interface MappingURL {
	
	public static final String	BASE_ADMIN_URL="${adminPath}"; //系统基础地址
	
	public static final String	BASE_ADMIN_INDEX_URL="${adminPath}/index";//系统首页地址
	public static final String 	LOGIN_URL="${adminPath}/login";//用户登陆地址

	public static final String 	LOGIN_USER_URL="${adminPath}/login/user";//用户登陆查询
	
	//公用方法请求基础地址--不参与拦截
	public static final String 	COMMON_BASE_URL="${adminPath}/common";
	//获取验证码地址
	public static final String 	CAPTCHA_URL="/getCaptcha";
	//导出type表示请求的类型 0：excel 1:txt ...
	public static final String EXPORT="/export/{type}";
	//上传文件 type表示请求的类型 0：表示系统富文本初始化  1:txt 2:图片 3：视频  4：音频  5:excel 6.word 7:pdf
	public static final String UPLOAD_URL="/upload/{type}";
	
	//错误请求地址
	public static final String 	ERROR_403_URL="${adminPath}/error/403";//403错误地址
	public static final String 	ERROR_404_URL="${adminPath}/error/404";//404错误地址
	public static final String 	ERROR_500_URL="${adminPath}/error/500";//500错误地址
	
	//系统基础请求
	public static final String  FORM_URL="/form";//表单页面地址地址
	public static final String  FORM_YBM_URL="/formYBM";//已报名
	public static final String  LIST_URL="/list";//课程页面地址
	public static final String  ADD_URL="/add";//新增方法地址
	public static final String  ADD_TRME_URL="/addTrme";//新增参数跳转地址
	public static final String  MODIFY_URL="/modify";//修改方法地址
	public static final String  RESET_PWD_URL="/resetPwd";//修改方法地址
	public static final String  QUERY_URL="/query";//分页查询地址
	public static final String  QUERYSTU_URL="/queryStu";//分页查询地址 针对stu_user_info
	public static final String  DELETE_URL="/delete";//删除方法地址
	public static final String  APPROVE_URL="/approve";//审核方法地址
	public static final String  INFO_URL="/info";//详细信息、个人信息等
	public static final String  FLUSH_URL="/flush/{type}";//刷新
	public static final String  GET_PROVINCE="/getProvinceByIdCard";//通过身份证获取籍贯
	public static final String GET_VALIDCLASSNUMBER="/getValidClassNumber";//验证班级标号
	//public static final String GETSOURCETYPESEC="/getSourceTypeSec";
	public static final String GET_COU_PAC_LEARNTYPELIST="/getLearnTypesList"; //根据 course_package_id 获取learn_types 列表
	public static final String GET_EXAMS_LEARNTYPELIST="/getExamsList"; //根据 learn_types 获取learn_types 列表
	public static final String GET_TIME_LIST="/getTimeList"; //根据 learn_types 获取learn_types 列表
	public static final String GET_EXAMS_AVAILABLE= "getIsExamsAvailable"; //根据ID获取班级是否可用
	public static final String GET_COURSES_STATUS= "getcoursesStatus"; //根据ID获取班级是否可用
	public static final String GET_COURSES_INFO_BUY_RECORD= "getCourseInfoBuyRecrd"; //根据ID获取班级是否可用
	public static final String GET_COURSES_INFO_BUY_AUTHNAME= "getAuthenticateGradeName"; //根据ID获取班级是否可用
	public static final String GET_SCHOOL_INTRODUCE="/getSchoolIntroduce";//获取学校简介
	public static final String GET_CERT_NAME="/certName";//获取证书名称
	public static final String UPDATE_INSTITUTION="/updateInstitution";//修改机构的学校简介

	public static final String ORDER_FORM_URL="/apply/form";//报名审核页面
	public static final String ORDER_ADD_URL="/apply/add";//报名新增页面
	public static final String ORDER_PASS_URL="/apply/pass";//报名审核通过
	public static final String ORDER_UNPASS_URL="/apply/unpass";//报名审核拒绝
	public static final String READ_IDCARD_INFO= "/idcard/read"; //根据ID获取班级是否可用
	public static final String GO_BACK= "/goback"; //根据ID获取班级是否可用
	
	//系统system基础路径
	public static final String SYSTEM_BASE_URL="${adminPath}/system";
	//修改密码
	public static final String MODIFY_PWD="/modifyPwd";
	//用户（学员）
	public static final String USER_URL="/user";
	//常量
	public static final String CONSTANTS_URL="/constants";
	public static final String IS_SHOW_SYS_MENU="/isShowSysMenu";
	//缓存
	public static final String CACHE_URL="/cache";
	//角色
	public static final String ROLE_URL="/role";
	//角色
    public static final String ROLE_QUERY_URL="/rolequery";
	//字典参数
	public static final String PARAMS_URL="/params";
	//菜单参数
	public static final String MENU_URL="/menu";
	//权限参数
	public static final String PURVIEW_URL="/purview";
	//业务排序
	public static final String SORT_URL="/sort";
	//区域
	public static final String AREAS_URL="/areas";
	
	public static final String AREAS_INIT_URL="/init";

	public static final String SMS_URL="/sms";

	//业务请求--报名学员
	public static final String ORDERS_URL="/orders";
	//业务请求--考试管理
	public static final String EXAMS_URL="/exams";
	//业务请求--成绩管理
	public static final String SCORES_URL="/scores";
	//业务请求--课程管理
	public static final String COURSES_URL="/courses";
	//业务请求--上课时间管理
	public static final String COURSES_TIME_URL="/coursesTime";
	//业务请求--班级标号查询
	public static final String CLASSNUMBER_URL="/ClassNumuber";
	//业务请求--学校管理
	public static final String SCHOOLS_URL="/schools";
	//业务请求--课程销售管理
	public static final String COURSES_PACKAGE_URL="/coursesPackage";
	//业务请求--合作商管理
	public static final String PARTNERS_URL="/partners";
	//业务请求--提成管理
	public static final String BROKERAGE_URL="/brokerage";
	//业务请求--学习记录
	public static final String LEARN_URL="/learnRecords";
	//业务请求--鉴定等级
	public static final String CFG_PARAMS_GRADE_URL="/cfgParamsGrade";
	//业务请求--课程状态
	public static final String CFG_PARAMS_STATUS_URL="/cfgParamsStatus";

	public static final String INTRO  =  "/intro";
}
