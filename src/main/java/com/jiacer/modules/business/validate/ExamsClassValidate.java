package com.jiacer.modules.business.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiacer.modules.business.service.ExamsService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.ExamClassMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.ExamClassEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/** 
* @ClassName: ExamsValidate 
* @Description: 考试管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:15:37 
*  
*/
public class ExamsClassValidate {

	/**
	 * 新增方法参数校验
	 * @param examClassEntity
	 * @param learnTypesDao 
	 * @return
	 */
	public static JsonResult addValidate(ExamClassEntity examClassEntity,ExamClassMapper examClassDao, LearnTypesMapper learnTypesDao){
		
		if(StringUtils.isEmpty(examClassEntity.getClassName())){
			return new JsonResult(false,"请填写班级名称",null);
		}
		if(examClassEntity.getCourseId()==null){
			return new JsonResult(false,"请选择课程",null);
		}
		if(examClassEntity.getExamForm()==null){
			return new JsonResult(false,"请选择考试形式",null);
		}
		if(examClassEntity.getShoolId()==null){
			return new JsonResult(false,"请选择报考学校",null);
		}
		LearnTypesEntity learnTypesEntity = learnTypesDao.getById(examClassEntity.getCourseId());
		if("02".equals(learnTypesEntity.getStatus())){
			return new JsonResult(false,"课程已下架",null);
		}
		if(StringUtils.isEmpty(examClassEntity.getClassNumber())){
			return new JsonResult(false,"请填写班级标号",null);
		}else{
			ExamClassEntity examc = new ExamClassEntity();
			examc.setClassNumber(examClassEntity.getClassNumber());
			examc.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
			List<ExamClassEntity> list = examClassDao.getExam(examc);
			if(list.size() != 0){
				return new JsonResult(false,"班级标号已存在",null);
			}
		}
		
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param examClassEntity
	 * @return
	 */
	public static JsonResult modifyValidate(ExamClassEntity examClassEntity,ExamsService examsService){
		ExamClassEntity examClassEntityOld = examsService.getById(examClassEntity.getId());
		if(StringUtils.isEmpty(examClassEntity.getClassName())){
			return new JsonResult(false,"请填写班级名称",null);
		}else{
			if(!examClassEntityOld.getClassName().equals(examClassEntity.getClassName())){
				Map map = new HashMap();
				map.put("className", examClassEntity.getClassName());
				if(examsService.getExamClass(map) != null){
					return new JsonResult(false,"班级名称已存在请重新输入",null);
				}
			}
		}
		/**
		if(examClassEntityOld.getExamStatus().equals("01") && examClassEntity.getExamStatus().equals("04")) {
			return new JsonResult(false,"班级状态更新有无，请检查确认",null);
		}
		**/
		if(!examClassEntityOld.getExamStatus().equals(examClassEntity.getExamStatus())){
			if(examClassEntityOld.getExamStatus().equals("01")&&examClassEntity.getExamStatus().equals("03")){
				// 01 - 03
			}else if(examClassEntityOld.getExamStatus().equals("01")&&examClassEntity.getExamStatus().equals("02")){
				//01 - 02
			}else if(examClassEntityOld.getExamStatus().equals("02")&&examClassEntity.getExamStatus().equals("04")){
				//02 - 04
			}else{
				return new JsonResult(false,"班级状态更新有误，请检查确认",null);
			}
		}
		
		/**
		if((examClassEntityOld.getExamStatus().equals("01")&&examClassEntity.getExamStatus().equals("03"))||
			(examClassEntityOld.getExamStatus().equals("01")&&examClassEntity.getExamStatus().equals("02"))||
			(examClassEntityOld.getExamStatus().equals("02")&&examClassEntity.getExamStatus().equals("04")) ||
			(examClassEntityOld.getExamStatus().equals("03")&&examClassEntity.getExamStatus().equals("04"))
		){
			return new JsonResult(false,"班级状态更新有无，请检查确认",null);
		}
		**/
		
		
		
		
		/**
		if((examClassEntityOld.getExamStatus().equals("02") || 
				examClassEntityOld.getExamStatus().equals("03")) && 
				examClassEntity.getExamStatus().equals("03")){
			return new JsonResult(false,"班级状态更新有无，请检查确认",null);
		}
		if(examClassEntityOld.getExamStatus().equals("04") &&(examClassEntityOld.getExamStatus().equals("02") || 
				examClassEntity.getExamStatus().equals("03")||examClassEntity.getExamStatus().equals("01"))){
			return new JsonResult(false,"班级状态更新有无，请检查确认",null);
		}
		**/
		
		
		if(examClassEntity.getCourseId()==null){
			return new JsonResult(false,"请选择课程",null);
		}
		if(examClassEntity.getExamForm()==null){
			return new JsonResult(false,"请选择考试形式",null);
		}
		if(examClassEntity.getShoolId()==null){
			return new JsonResult(false,"请选择报考学校",null);
		}
		if(StringUtils.isEmpty(examClassEntity.getClassNumber())){
			return new JsonResult(false,"请填写班级标号",null);
		}
		
		return new JsonResult(true,null,null);
	}
	
}

















