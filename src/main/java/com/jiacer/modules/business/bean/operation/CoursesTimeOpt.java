package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: ShoolsOpt 
* @Description: 上课时间对象参数构建
* @author 贺章鹏
* @date 2016年10月24日 下午2:05:29 
*  
*/
public class CoursesTimeOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(CoursesTimeEntity coursesTimeEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(coursesTimeEntity.getTemplateName())){
			map.put("templateName", SqlUtils.like(coursesTimeEntity.getTemplateName()));
		}
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static CoursesTimeEntity buildAdd(CoursesTimeEntity obj){
		obj.setCreateDate(new Date());
		obj.setModifyDate(new Date());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static CoursesTimeEntity buildUpdate(CoursesTimeEntity obj,CoursesTimeEntity param){
		obj.setModifyDate(new Date());
		obj.setTemplateName(param.getTemplateName());
		obj.setTemplateType(param.getTemplateType());
		obj.setBeginTime(param.getBeginTime());
		obj.setEndTime(param.getEndTime());
		obj.setOpenCycle(param.getOpenCycle());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static CoursesTimeEntity buildDelete(CoursesTimeEntity obj){
		//obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}
}
