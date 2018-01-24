package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

/** 
* @ClassName: CoursesService 
* @Description: 课程管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:08 
*  
*/
public interface CoursesService {

	/**
	 * 根据id获取课程分类管理对象
	 * @param id
	 * @return
	 */
	LearnTypesEntity getCoursesById(Integer id);
	
	LearnTypesEntity getLearnTypes(Map<Object, Object> map);
	
	List<LearnTypesEntity> findAllList(Map<Object, Object> map);

	List<LearnTypesEntity> getLearnTypeNameList(Map<Object, Object> map);
	
	/**
	 * 课程管理分页
	 * @param learnTypesEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<LearnTypesEntity> getCoursesPage(LearnTypesEntity learnTypesEntity, int pageNumber, int pageSize);

	/**
	 * 新增课程
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void addCourses(LearnTypesEntity learnTypesEntity) throws Exception;

	/**
	 * 修改课程
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void modifyCourses(LearnTypesEntity learnTypesEntity) throws Exception;

	/**
	 * 删除课程
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void delCourses(LearnTypesEntity learnTypesEntity) throws Exception;
	
	/**
	 * 获取课程ID 和 Name 为<select> 赋值
	 * @return
	 */
	List<ControlEntity> getCourseIdName();
	
	/***
	 * 判断课程名称是否存在
	 * @param learnTypesEntity
	 * @return
	 */
	public boolean isCoursesName(LearnTypesEntity learnTypesEntity);
	
	public LearnTypesEntity getById(Integer id);
	
	Integer count(Map<Object, Object> map);
	
	List<CoursesTimeEntity> getCourseTimeTable(Map<Object, Object> map);
	
	/**
	 * 
	 * @param map 
	 * @MethodName:getBaseCourseInfo
	 * @Type:CoursesService
	 * @Description:获取learnType表中学校未开设的全部课程
	 * @Return:List<LearnTypesEntity>
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 8, 2017 6:11:22 PM
	 */
	List<CourseBaseInfoEntity> getBaseCourseInfo(Map<Object, Object> map);
	

	/**
	 * 
	 * @MethodName:getCourseInfoById
	 * @Type:CoursesService
	 * @Description:获取单个课程信息
	 * @Return:LearnTypesEntity
	 * @Param:@param id
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 8, 2017 10:38:06 PM
	 */
	LearnTypesEntity getCourseInfoById(Integer id);
	
	/**
	 * 
	 * @MethodName:getBaseInfoStatus
	 * @Type:CoursesService
	 * @Description:获取总控课程状态信息
	 * @Return:String
	 * @Param:@param learnTypesEntity
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 10, 2017 10:28:30 PM
	 */
	String getBaseInfoStatus(LearnTypesEntity learnTypesEntity);
	
	/**
	 * 
	 * @MethodName:getBaseCourseInfoById
	 * @Type:CoursesService
	 * @Description:获取总控课程对应的信息
	 * @Return:CourseBaseInfoEntity
	 * @Param:@param courseId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 12, 2017 2:07:02 PM
	 */
	CourseBaseInfoEntity getBaseCourseInfoById(Integer courseId);

}
