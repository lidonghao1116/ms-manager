package com.jiacer.modules.business.bean.operation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: LearnTypesQuery 
* @Description: 课程管理构建参数操作
* @author 贺章鹏
* @date 2016年10月24日 上午11:15:52 
*  
*/
public class LearnTypesOpt {
	
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(LearnTypesEntity learnTypesEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(learnTypesEntity.getId() != null){
			if(!StringUtils.isEmpty(learnTypesEntity.getId().toString())){
				map.put("id", learnTypesEntity.getId());
			}
		}
		if(!StringUtils.isEmpty(learnTypesEntity.getTypeName())){
			map.put("typeName", SqlUtils.like(learnTypesEntity.getTypeName()));
		}
		if(!StringUtils.isEmpty(learnTypesEntity.getStatus())){
			map.put("status", learnTypesEntity.getStatus());
		}
		map.put("isUsable", DBStatus.IsUsable.TRUE);
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static LearnTypesEntity buildAdd(LearnTypesEntity obj){
		SysUsersEntity sysUser = UserUtils.getUser();//获取登录用户信息
		obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setInstitutionInfoId(sysUser.getInstitutionInfoId());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static LearnTypesEntity buildUpdate(LearnTypesEntity obj,LearnTypesEntity param){
		obj.setTypeName(param.getTypeName());
		obj.setExamType(param.getExamType());
		obj.setAuthorityId(param.getAuthorityId());
		obj.setAuthenticateGrade(param.getAuthenticateGrade());
		obj.setTotalHours(param.getTotalHours());
		obj.setRemarks(StringUtils.isEmpty(param.getRemarks().trim())?"":param.getRemarks().trim());
		obj.setTimeTemplate(param.getTimeTemplate());
		obj.setIsNeedHasPf(param.getIsNeedHasPf());
		obj.setStatus(param.getStatus());
		obj.setCertificateFee(param.getCertificateFee()!=null?param.getCertificateFee():BigDecimal.ZERO);
		obj.setExamFee(param.getExamFee()!=null?param.getExamFee():BigDecimal.ZERO);
		obj.setOtherFee(param.getOtherFee()!=null?param.getOtherFee():BigDecimal.ZERO);
		obj.setModifyAccount(UserUtils.getAccount());
		obj.setModifyTime(new Date());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static LearnTypesEntity buildDelete(LearnTypesEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}

}
