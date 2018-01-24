package com.jiacer.modules.business.bean;

import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.business.utils.IdCardInfoUtils;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public  class DictionariesUtil {

	private final static Logger log = LoggerFactory.getLogger(DictionariesUtil.class);

	private final static DateFormat format1 = new SimpleDateFormat("yyyy");

	/**
	 * 获取考试形式列表
	 * @return
	 */
	public static List<ControlEntity> getExamType(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("01", "理论"));
		list.add(new ControlEntity ("02", "实操"));
		list.add(new ControlEntity ("03", "理论+实操"));
		list.add(new ControlEntity ("04", "其他"));
		return list;
	}
	public static Map<String,String> getExamTypeMap(){
		Map map = new HashMap();
		map.put("01", "理论");  
		map.put("02", "实操");        
		map.put("03", "理论+实操");     
		map.put("04", "其他");        
		return map;
		
	}
	/**
	 * 获取鉴定级别列表
	 * @return
	 */
	public static List<ControlEntity> getAuthenticateGrade(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("01", "专项职业能力"));
		list.add(new ControlEntity ("02", "初级/五级"));	
		list.add(new ControlEntity ("03", "中级/四级"));
		list.add(new ControlEntity ("04", "高级/三级"));
		list.add(new ControlEntity ("05", "技师/二级"));
		list.add(new ControlEntity ("06", "高级技师/一级"));
		return list;
	}
	
	public static String getAuthenticateGradeName(String key){
		Map map = new HashMap();
		map.put("01","专项职业能力");  
		map.put("02","初级/五级");	
		map.put("03","中级/四级");   
		map.put("04","高级/三级");   
		map.put("05","技师/二级");   
		map.put("06","高级技师/一级");
		return map.get(key).toString();
	}
	
	public static String getExamTypeName(String key){
		Map map = new HashMap();
		map.put("01", "理论");  
		map.put("02", "实操");        
		map.put("03", "理论+实操");     
		map.put("04", "其他"); 
		return map.get(key).toString();
	}
	
	
	/****
	 * 学历中文返显
	 * @param key
	 * @return
	 */
	public static String getEducationName(String key){
		Map map = new HashMap();
		map.put("01","小学");  
		map.put("02","初中");	
		map.put("03","高中");   
		map.put("04","中专");   
		map.put("05","大专");
		map.put("06","大学本科");
		map.put("07","硕士研究生");
		map.put("08","博士生");
		return map.get(key).toString();
	}
	public static List<ControlEntity> getEducationNameList(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("08", "博士生"));
		list.add(new ControlEntity ("07", "硕士研究生"));
		list.add(new ControlEntity ("06", "大学本科"));
		list.add(new ControlEntity ("05", "大专"));
		list.add(new ControlEntity ("04", "中专"));
		list.add(new ControlEntity ("03", "高中"));
		list.add(new ControlEntity ("02", "初中"));
		list.add(new ControlEntity ("01", "小学"));
		return list;
	}
	
	
	/***
	 * 获取周列表
	 * @return
	 */
	public static List<ControlEntity> getWeek(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("monday", "周一"));
		list.add(new ControlEntity ("tuesday", "周二"));
		list.add(new ControlEntity ("wednesday", "周三"));
		list.add(new ControlEntity ("thursday", "周四"));
		list.add(new ControlEntity ("friday", "周五"));
		list.add(new ControlEntity ("saturday", "周六"));
		list.add(new ControlEntity ("sunday", "周日"));
		return list;
	}
	
	/****
	 * 订单状态
	 * @param key
	 * @return
	 */
	public static String getorderFromTypeName(String key){
		Map map = new HashMap();
		map.put("01","正常");  
		map.put("02","补考");	
		return map.get(key).toString();
	}
	/***
	 * 订单类别列表
	 * @return
	 */
	public static List<ControlEntity> getorderFromTypeList(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("01", "正常"));
		list.add(new ControlEntity ("02", "补考"));
		return list;
	}
	
	public static List<ControlEntity> setCoursePackageStatus(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("01", "上架"));
		list.add(new ControlEntity ("02", "下架"));
		list.add(new ControlEntity ("03", "停售"));
		return list;
	}
	
	/**
	 * 时间段列表
	 * @return
	 */
	public static List<ControlEntity> getTimeSlot(){
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		list.add(new ControlEntity ("08:00", "08:00"));
		list.add(new ControlEntity ("08:30", "08:30"));
		list.add(new ControlEntity ("09:00", "09:00"));
		list.add(new ControlEntity ("09:30", "09:30"));
		list.add(new ControlEntity ("10:00", "10:00"));
		list.add(new ControlEntity ("10:30", "10:30"));
		list.add(new ControlEntity ("11:00", "11:00"));
		list.add(new ControlEntity ("11:30", "11:30"));
		list.add(new ControlEntity ("12:00", "12:00"));
		list.add(new ControlEntity ("12:30", "12:30"));
		list.add(new ControlEntity ("13:00", "13:00"));
		list.add(new ControlEntity ("13:30", "13:30"));
		list.add(new ControlEntity ("14:00", "14:00"));
		list.add(new ControlEntity ("14:30", "14:30"));
		list.add(new ControlEntity ("15:00", "15:00"));
		list.add(new ControlEntity ("15:30", "15:30"));
		list.add(new ControlEntity ("16:00", "16:00"));
		list.add(new ControlEntity ("16:30", "16:30"));
		list.add(new ControlEntity ("17:00", "17:00"));
		list.add(new ControlEntity ("17:30", "17:30"));
		list.add(new ControlEntity ("18:00", "18:00"));
		list.add(new ControlEntity ("18:30", "18:30"));
		list.add(new ControlEntity ("19:00", "19:00"));
		list.add(new ControlEntity ("19:30", "19:30"));
		list.add(new ControlEntity ("20:00", "20:00"));
		list.add(new ControlEntity ("20:30", "20:30"));
		list.add(new ControlEntity ("21:00", "21:00"));
		list.add(new ControlEntity ("21:30", "21:30"));
		list.add(new ControlEntity ("22:00", "22:00"));
		list.add(new ControlEntity ("22:30", "22:30"));
		list.add(new ControlEntity ("23:00","23:00"));
		list.add(new ControlEntity ("23:30","23:30"));	
		return list;
	}
	/***
	 * 班级标号 列表
	 * @param examsService
	 * @return
	 */
	public static List<ControlEntity> getClassNumberList(ExamsService examsService,ApplyOrdersEntity applyOrdersEntity){
		SysUsersEntity user = UserUtils.getUser();//获取登录用户信息
		
		List<ControlEntity> list = new ArrayList<ControlEntity>();
		Map map = new HashMap();
		map.put("courseId", applyOrdersEntity.getCourseId());
		map.put("institutionInfoId", user.getInstitutionInfoId());
		List<ExamClassEntity> examClassEntityList = examsService.findAllList(map);
		for(int i = 0; i < examClassEntityList.size(); i++){
			ExamClassEntity examClassEntity = examClassEntityList.get(i);
			list.add(new ControlEntity (examClassEntity.getId()+"",examClassEntity.getClassNumber()));
		}
		return list;
	}
	
	public static Map<String, String> placeOfOriginCodes = new HashMap<String, String>(); 
    static {  
        placeOfOriginCodes.put("11", "北京");  
        placeOfOriginCodes.put("12", "天津");  
        placeOfOriginCodes.put("13", "河北");  
        placeOfOriginCodes.put("14", "山西");  
        placeOfOriginCodes.put("15", "内蒙古");  
        placeOfOriginCodes.put("21", "辽宁");  
        placeOfOriginCodes.put("22", "吉林");  
        placeOfOriginCodes.put("23", "黑龙江");  
        placeOfOriginCodes.put("31", "上海");  
        placeOfOriginCodes.put("32", "江苏");  
        placeOfOriginCodes.put("33", "浙江");  
        placeOfOriginCodes.put("34", "安徽");  
        placeOfOriginCodes.put("35", "福建");  
        placeOfOriginCodes.put("36", "江西");  
        placeOfOriginCodes.put("37", "山东");  
        placeOfOriginCodes.put("41", "河南");  
        placeOfOriginCodes.put("42", "湖北");  
        placeOfOriginCodes.put("43", "湖南");  
        placeOfOriginCodes.put("44", "广东");  
        placeOfOriginCodes.put("45", "广西");  
        placeOfOriginCodes.put("46", "海南");  
        placeOfOriginCodes.put("50", "重庆");  
        placeOfOriginCodes.put("51", "四川");  
        placeOfOriginCodes.put("52", "贵州");  
        placeOfOriginCodes.put("53", "云南");  
        placeOfOriginCodes.put("54", "西藏");  
        placeOfOriginCodes.put("61", "陕西");  
        placeOfOriginCodes.put("62", "甘肃");  
        placeOfOriginCodes.put("63", "青海");  
        placeOfOriginCodes.put("64", "宁夏");  
        placeOfOriginCodes.put("65", "新疆");  
        placeOfOriginCodes.put("71", "台湾");  
        placeOfOriginCodes.put("81", "香港");  
        placeOfOriginCodes.put("82", "澳门");  
        placeOfOriginCodes.put("91", "国外");  
    }

	public static int getAge(String certNo){
		if(StringUtils.isBlank(certNo)){
			return 0;
		}
		Calendar now = Calendar.getInstance();
		try{
			return now.get(Calendar.YEAR)-Integer.parseInt(certNo.substring(6,10));
		}catch (Exception e){
			log.error(e.getMessage());
			return 0;
		}

	}

	public static int getSex(String certNo){
		try{
			if(StringUtils.isBlank(certNo)){
				return 1;
			}
			int gender = 1;
			// 获取性别
			String id17 = certNo.substring(16, 17);
			if (Integer.parseInt(id17) % 2 != 0) {
				gender = 1;//男
			} else {
				gender = 0;//女
			}
			return gender;
		}catch (Exception e){
			log.error(e.getMessage());
			return 1;
		}

	}
}
