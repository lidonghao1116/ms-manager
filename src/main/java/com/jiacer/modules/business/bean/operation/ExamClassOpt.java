package com.jiacer.modules.business.bean.operation;

import java.util.Date;
import java.util.Map;
import com.google.common.collect.Maps;
import com.jiacer.modules.common.utils.SqlUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.system.config.DBStatus;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: ExamClassOpt 
* @Description: 班级对象参数构建
* @author 贺章鹏
* @date 2016年10月24日 下午2:16:24 
*  
*/
public class ExamClassOpt {
	/**
	 * 构建查询参数Map
	 * @param learnTypesEntity
	 * @return
	 */
	public static Map<Object, Object> buildMap(ExamClassEntity examClassEntity){
		Map<Object, Object> map=Maps.newHashMap();
		if(!StringUtils.isEmpty(examClassEntity.getClassName())){
			map.put("className", SqlUtils.like(examClassEntity.getClassName()));
		}
		if(!StringUtils.isEmpty(examClassEntity.getClassNumber())){
			map.put("classNumber", SqlUtils.like(examClassEntity.getClassNumber()));
		}
		if(examClassEntity.getCourseId()!=null){
			map.put("courseId", examClassEntity.getCourseId());
		}
		
		if(examClassEntity.getShoolId()!=null){
			map.put("shoolId", examClassEntity.getShoolId());
		}
		if(!StringUtils.isEmpty(examClassEntity.getExamStatus())){
			map.put("examStatus", examClassEntity.getExamStatus());
		}
		if(examClassEntity.getExamStatusList()!=null){
			map.put("examStatusList", examClassEntity.getExamStatusList());
		}
		map.put("isUsable", DBStatus.IsUsable.TRUE);
		return map;
	}
	
	/**
	 * 构建新增参数
	 * @param obj
	 * @return
	 */
	public static ExamClassEntity buildAdd(ExamClassEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.TRUE);
		obj.setAddAccount(UserUtils.getAccount());
		obj.setAddTime(new Date());
		obj.setExamStatus(DBStatus.ExamStatus.PENDING_DECLARE);
		obj.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		return obj;
	}
	
	/**
	 * 建构修改参数
	 * @param obj
	 * @param param
	 * @return
	 */
	public static ExamClassEntity buildUpdate(ExamClassEntity obj,ExamClassEntity param){
		obj.setTheoryAddress(StringUtils.isEmpty(param.getTheoryAddress().trim())?"":param.getTheoryAddress().trim());
		obj.setOperationAddress(StringUtils.isEmpty(param.getOperationAddress().trim())?"":param.getOperationAddress().trim());
		obj.setTheoryDate(param.getTheoryDate()!=null?param.getTheoryDate():null);
		obj.setOperationDate(param.getOperationDate()!=null?param.getOperationDate():null);
		obj.setExamStatus(StringUtils.isEmpty(param.getExamStatus().trim())?"":param.getExamStatus().trim());
		obj.setModifyAccount(UserUtils.getAccount());
		obj.setModifyTime(new Date());
		obj.setRemark(StringUtils.isEmpty(param.getRemark().trim())?"":param.getRemark().trim());
		return obj;
	}
	
	/**
	 * 构建删除参数
	 * @param obj
	 * @return
	 */
	public static ExamClassEntity buildDelete(ExamClassEntity obj){
		obj.setIsUsable(DBStatus.IsUsable.FALSE);
		return obj;
	}
}
