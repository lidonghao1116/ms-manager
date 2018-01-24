package com.jiacer.modules.mybatis.entity;

import com.jiacer.modules.business.utils.CoursesUtils;
import com.jiacer.modules.mybatis.model.Schools;

/**
 * 
* @ClassName: ShoolsEntity 
* @Description: 学校表
* @author 贺章鹏
* @date 2016年10月18日 下午12:08:20 
*
 */
public class SchoolsEntity extends Schools{

	private static final long serialVersionUID = 1L;
	
	//private static LearnTypesMapper coursesDao = SpringContextHolder.getBean(LearnTypesMapper.class);
	
	@SuppressWarnings("unused")
	private String applyCoursesName;

	public String getApplyCoursesName() {
		return applyCoursesName;
	}

	public void setApplyCoursesName(String applyCoursesName) {
		this.applyCoursesName = applyCoursesName;
	}
	
	/**
	public String getApplyCoursesName() {
		return CoursesUtils.getSchoolCourses(super.getApplyCourses());
		//return this.getSchoolCourses(super.getApplyCourses());
	}

	public void setApplyCoursesName(String applyCoursesName) {
		this.applyCoursesName = applyCoursesName;
	}
	**/
	/**
	
	private String getSchoolCourses(String values){
		String[] ss=StringUtils.split(values,",");
		StringBuilder result = new StringBuilder();
		if(ss != null){
			for(int i=0;i<ss.length;i++){
				String value=this.getName(Integer.parseInt(ss[i]));
				if(!StringUtils.isEmpty(value)){
					result.append(value).append(",");
				}
			}
			if(result!=null){
				if(ss.length != 0){
					if(result.length() != 0){
						return result.substring(0, result.length()-1);	
					}
				}
			}
		}
		return result.toString();
	}
	
	
	private String getName(Integer id){
		List<LearnTypesEntity> list=init();
		for(LearnTypesEntity learnTypesEntity:list){
			if(id==learnTypesEntity.getId()){
				return learnTypesEntity.getTypeName();
			}
		}
		return "";
	}
	
	private List<LearnTypesEntity> init(){
		List<LearnTypesEntity> result=Lists.newArrayList();
		LearnTypesEntity entity=new LearnTypesEntity();
		entity.setIsUsable(DBStatus.IsUsable.TRUE);//基础参数类型
		entity.setStatus(DBStatus.CourseStatus.NOMAL);
		entity.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
		try {
			result=coursesDao.findAllList(entity);
		} catch (Exception e) {
			Log.error("获取课程数据失败");
		}
		return result;
	}
	**/

}
