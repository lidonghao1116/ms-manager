package com.jiacer.modules.system.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: DBStatus 
* @Description: 系统业务数据库业务参数
* @author 贺章鹏
* @date 2016年10月19日 下午1:36:19 
*
 */
public class DBStatus {
	
	public static class IsUsable{
		public static String TRUE="1";//可用
		public static String FALSE="0";//不可用
		
		public static String getDesc(String status) {
			return msg.get(status);
		}
		
		public static Map<String, String> msg = new HashMap<String, String>();
		static {
			msg.put(TRUE, "是");
			msg.put(FALSE, "否");
		}
	}
	
	public static class UserStatus{
		public static String NOMAL="00";//正常
	}
	
	public static class CertType{
		public static String SFZ="00";//省份证
	}
	
	public static class OrderType{
		public static String YUYUE="01";//用户自己预约
		public static String LURU="02";//系统录入
		public static String BUKAO="03";//补考系统生成
		public static String ASSIGN="04";//重新分班系统生成
	}
	
	public static class OrderStatus{
		public static String SUCCESS="05";//订单成功
	}
	
	public static class HandleStatus{
		public static String PENDING="01";//待审核
		public static String SUCCESS="02";//审核已分班-报名成功
		public static String FAILED="03";//审核不通过-报名失败
		public static String OUT="04";//已退学
	}
	
	public static class CourseStatus{
		public static String NOMAL="01";	//正常
		public static String TNOMAL="02";	//停止
	}
	
	public static class ProductStatus{
		public static String ON_SHELF="01";
		public static String OFF_SHELF="02";
		public static String STOPED="03";
	}
	
	public static class BonusType{
		public static String NUMBER="01";
		public static String RATE="02";
	}
	
	public static class ExamStatus{
		public static String PENDING_DECLARE="01";//待申报
		public static String DECLARED="02";//已申报
		public static String FAILURE="03";//已失效
		public static String CLEARING="04";//已结清
	}
	
	
	public static class ExamResult{
		public static String PASS="01";//合格
		public static String UN_PASS="02";//不合格
		public static String MISSING="03";//缺考
		public static String GOOD="04";//优秀
		public static String FINE="05";//良好
		
		public static String getDesc(String status) {
			return msg.get(status);
		}
		
		public static Map<String, String> getExamResultAll = new HashMap<String, String>();
		static {
			getExamResultAll.put(PASS, "合格");
			getExamResultAll.put(UN_PASS, "不合格");
			getExamResultAll.put(MISSING, "缺考");
			getExamResultAll.put(PASS, "合格");
			getExamResultAll.put(GOOD, "优秀");
			getExamResultAll.put(FINE, "良好");
		}
		
		public static Map<String, String> msg = new HashMap<String, String>();
		static {
			msg.put(PASS, "合格");
			msg.put(UN_PASS, "不合格");
			msg.put(MISSING, "缺考");
		}
	}
	
	public static class DealResult{
		public static String BUKAO="01";//补考
		public static String ASSIGN="02";//重新分配
		public static String NOTHING="03";//不处理
	}
	
	public static class UserType{
		public static String NORMAL="00";//会员用户
		public static String SYSTEM_USER="01";//系统用户
	}
}
