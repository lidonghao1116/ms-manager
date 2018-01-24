package com.jiacer.modules.business.validate;

import javax.annotation.Resource;

import com.jiacer.modules.business.service.CoursesTimeService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.SchoolsEntity;

/** 
* @ClassName: ShoolsValidate 
* @Description: 学校管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:17:16 
*  
*/
public class CoursesTimeValidate {
	
	/**
	 * 新增方法参数校验
	 * @param shoolsEntity
	 * @return
	 */
	public static JsonResult addValidate(CoursesTimeEntity coursesTimeEntity,CoursesTimeService coursesTimeService){
		
		if(StringUtils.isEmpty(coursesTimeEntity.getTemplateName())){
			return new JsonResult(false,"模板名称不能为空",null);
		}
		
		if(StringUtils.isEmpty(coursesTimeEntity.getOpenCycle())){
			return new JsonResult(false,"请勾选开课周期",null);
		}
		
		if(coursesTimeEntity.getBeginTime().equals("-请选择上课开始时间-")){
			return new JsonResult(false,"请选择上课时间",null);
		}
		if(coursesTimeEntity.getEndTime().equals("-请选择下课开始时间-")){
			return new JsonResult(false,"请选择下课时间",null);
		}
		if(coursesTimeEntity != null){
			if(!coursesTimeService.isTemplateName(coursesTimeEntity)){
				return new JsonResult(false,"模板名称已存在，请重新填写",null);
			}
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param shoolsEntity
	 * @return
	 */
	public static JsonResult modifyValidate(CoursesTimeEntity coursesTimeEntity,CoursesTimeService coursesTimeService){

		if(StringUtils.isEmpty(coursesTimeEntity.getTemplateName())){
			return new JsonResult(false,"模板名称不能为空",null);
		}
		
		if(StringUtils.isEmpty(coursesTimeEntity.getOpenCycle())){
			return new JsonResult(false,"请勾选开课周期",null);
		}
		
		if(coursesTimeEntity.getBeginTime().equals("-请选择上课开始时间-")){
			return new JsonResult(false,"请选择上课时间",null);
		}
		if(coursesTimeEntity.getEndTime().equals("-请选择下课开始时间-")){
			return new JsonResult(false,"请选择下课时间",null);
		}
		if(coursesTimeEntity != null){
			CoursesTimeEntity coursesTimeEntityOld = coursesTimeService.getById(coursesTimeEntity.getId());
			if(!coursesTimeEntityOld.getTemplateName().equals(coursesTimeEntity.getTemplateName())){
				if(!coursesTimeService.isTemplateName(coursesTimeEntity)){
					return new JsonResult(false,"模板名称已存在，请重新填写",null);
				}
			}
		}
		return new JsonResult(true,null,null);
	}
}
