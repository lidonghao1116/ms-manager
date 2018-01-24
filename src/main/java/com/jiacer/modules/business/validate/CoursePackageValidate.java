package com.jiacer.modules.business.validate;

import java.util.HashMap;
import java.util.Map;

import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

/** 
* @ClassName: CoursePackageValidate 
* @Description: 课程销售管理校验类
* @author 贺章鹏
* @date 2016年10月19日 下午4:15:11 
*  
*/
public class CoursePackageValidate {
	/**
	 * 新增方法参数校验
	 * @param coursePackageEntity
	 * @return
	 */
	public static JsonResult addValidate(CoursePackageEntity coursePackageEntity,CoursePackageService coursePackageService){
		if(StringUtils.isEmpty(coursePackageEntity.getPackageName())){
			return new JsonResult(false,"请填写产品名称",null);
		}else{
			Map map = new HashMap();
			map.put("packageName", coursePackageEntity.getPackageName());
			if(coursePackageService.getPackageNameCount(map) != 0){
				return new JsonResult(false,"产品名称已存在请重新填写",null);
			}
		}
		if(StringUtils.isEmpty(coursePackageEntity.getSummary())){
			return new JsonResult(false,"请填写产品简介",null);
		}
		if(coursePackageEntity.getPrice() == null){
			return new JsonResult(false,"优惠价不能为空",null);
		}
		if(StringUtils.isEmpty(coursePackageEntity.getApplyCourses())){
			return new JsonResult(false,"请填选课程",null);
		}else{
			  String[] couStr = coursePackageEntity.getApplyCourses().split(",");
			  for(int i=0;i<couStr.length-1;i++){
				  int count = i+1;
				  for(int j=count;j<couStr.length;j++){
					  String a = couStr[i];
					  String b = couStr[j];
					  if(a.equals(b)){
						  return new JsonResult(false,"课程不能相同，请重新选择",null);
					  }
				  }
			  }
		}
		if(coursePackageEntity.getSortNo()==null){
			return new JsonResult(false,"请填写排序",null);
		}
		return new JsonResult(true,null,null);
	}
	
	/**
	 * 修改方法参数校验
	 * @param coursesDao 
	 * @param learnTypesEntity
	 * @return
	 */
	public static JsonResult modifyValidate(CoursePackageEntity coursePackageEntity,CoursePackageService coursePackageService, LearnTypesMapper coursesDao){
		if(StringUtils.isEmpty(coursePackageEntity.getPackageName())){
			return new JsonResult(false,"请填写产品名称",null);
		}else{
			CoursePackageEntity coursePackageEntityOld = coursePackageService.getById(coursePackageEntity.getId());
			if(!coursePackageEntityOld.getPackageName().equals(coursePackageEntity.getPackageName())){
				Map map = new HashMap();
				map.put("packageName", coursePackageEntity.getPackageName());
				if(coursePackageService.getPackageNameCount(map) != 0){
					return new JsonResult(false,"产品名称已存在请重新填写",null);
				}
			}
		}
		
		if(StringUtils.isEmpty(coursePackageEntity.getSummary())){
			return new JsonResult(false,"请填写产品简介",null);
		}
		/**
		if(StringUtils.isEmpty(coursePackageEntity.getStatus())){
			return new JsonResult(false,"请填写课程状态",null);
		}
		**/
		if(StringUtils.isEmpty(coursePackageEntity.getApplyCourses())){
			return new JsonResult(false,"请填选课程",null);
		}else{
			  String[] couStr = coursePackageEntity.getApplyCourses().split(",");
			  for(int i=0;i<couStr.length-1;i++){
				  int count = i+1;
				  for(int j=count;j<couStr.length;j++){
					  String a = couStr[i];
					  String b = couStr[j];
					  if(a.equals(b)){
						  return new JsonResult(false,"课程不能相同，请重新选择",null);
					  }
				  }
			  }
		}
		
		String applyCourses = coursePackageEntity.getApplyCourses();
		String [] stringArr= applyCourses.split(",");
		for(int i = 0; i < stringArr.length; i++){
			LearnTypesEntity lety = coursesDao.getById(Integer.parseInt(stringArr[i]));
		if ("01".equals(coursePackageEntity.getStatus())&&"02".equals(lety.getStatus())) {
			return new JsonResult(false,"改产品中课程已下架，产品不可上架",null);
			}	
		} 
		
		if(coursePackageEntity.getSortNo()==null){
			return new JsonResult(false,"请填写排序",null);
		}
		return new JsonResult(true,null,null);
	}


}
