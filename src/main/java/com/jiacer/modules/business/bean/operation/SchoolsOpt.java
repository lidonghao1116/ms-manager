package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: ShoolsOpt 
* @Description: 学校对象参数构建
* @author 贺章鹏
* @date 2016年10月24日 下午2:05:29 
*  
*/
public class SchoolsOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(SchoolsEntity shoolsEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(shoolsEntity.getSchoolName())){
			map.put("schoolName", SqlUtils.like(shoolsEntity.getSchoolName()));
		}
		map.put("isUsable", DBStatus.IsUsable.TRUE);
		map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static SchoolsEntity buildAdd(SchoolsEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static SchoolsEntity buildUpdate(SchoolsEntity obj,SchoolsEntity param){
		obj.setRemarks(StringUtils.isEmpty(param.getRemarks().trim())?"":param.getRemarks().trim());
		obj.setContacts(param.getContacts());
		obj.setContactPhone(param.getContactPhone());
		obj.setApplyCourses(StringUtils.isEmpty(param.getApplyCourses().trim())?"":param.getApplyCourses().trim());
		obj.setModifyAccount(UserUtils.getAccount());
		obj.setModifyTime(new Date());
		obj.setSchoolAddress(param.getSchoolAddress());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static SchoolsEntity buildDelete(SchoolsEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}
}
