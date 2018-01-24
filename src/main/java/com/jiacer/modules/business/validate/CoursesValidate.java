package com.jiacer.modules.business.validate;

import com.jiacer.modules.business.service.CoursesService;
import com.jiacer.modules.common.constants.Constants;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

/** 
* @ClassName: CoursesValidate 
* @Description: 课程管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:15:23 
*  
*/
public class CoursesValidate {
	
	/**
	 * 新增方法参数校验
	 * @param learnTypesEntity
	 * @return
	 */
	public static JsonResult addValidate(LearnTypesEntity learnTypesEntity){
		
		if(learnTypesEntity.getTypeName()==null||"---请选择---".equals(learnTypesEntity.getTypeName())){
			return new JsonResult(false,"请选择课程名称",null);
		}
		if (StringUtils.isEmpty(learnTypesEntity.getTimeTemplate())||"-请选择上课时间-".equals(learnTypesEntity.getTimeTemplate())) {
			return new JsonResult(false,"请选择上课时间",null);
		}
		if (StringUtils.isEmpty(learnTypesEntity.getIsNeedHasPf())) {
			return new JsonResult(false,"请选择是否交社保",null);
		}
		if (StringUtils.isEmpty(learnTypesEntity.getStatus())) {
			return new JsonResult(false,"请选择课程状态",null);
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param learnTypesEntity
	 * @return
	 */
	public static JsonResult modifyValidate(LearnTypesEntity learnTypesEntity,CoursesService coursesService){
		
		if (StringUtils.isEmpty(learnTypesEntity.getTimeTemplate())) {
			return new JsonResult(false,"请选择上课时间",null);
		}
		if (StringUtils.isEmpty(learnTypesEntity.getIsNeedHasPf())) {
			return new JsonResult(false,"请选择是否交社保",null);
		}
		if (StringUtils.isEmpty(learnTypesEntity.getStatus())) {
			return new JsonResult(false,"请选择课程状态",null);
		}
		String status =coursesService.getBaseInfoStatus(learnTypesEntity);
		if ("01".equals(learnTypesEntity.getStatus())&&status.equals(Constants.COURSE_STATUS_OFFSHELF)) {
			return new JsonResult(false,"该课程总部已下架，不可修改为上架状态",null);
		}
		return new JsonResult(true,null,null);
	}

}
