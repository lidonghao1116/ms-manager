package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.CoursesTimeEntity;

/** 
* @ClassName: CoursesService 
* @Description: 上课时间管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:06:08 
*  
*/
public interface CoursesTimeService {

	/**
	 * 根据id获取上课信息
	 * @param id
	 * @return
	 */
	CoursesTimeEntity getCoursesTimeById(Integer id);

	/**
	 * 上课管理分页
	 * @param learnTypesEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<CoursesTimeEntity> getCoursesPage(CoursesTimeEntity coursesTimeEntity, int pageNumber, int pageSize);

	/**
	 * 新增上课信息
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void addCourses(CoursesTimeEntity coursesTimeEntity) throws Exception;

	/**
	 * 修改上课信息
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void modifyCourses(CoursesTimeEntity coursesTimeEntity) throws Exception;

	/**
	 * 删除上课信息
	 * @param learnTypesEntity
	 * @throws Exception
	 */
	void delCourses(CoursesTimeEntity coursesTimeEntity) throws Exception;
	
	/***
	 * 获取模板编号
	 * @return
	 */
	Integer countNum();
	
	public List<CoursesTimeEntity> getAllTimeYes();
	
	public boolean isTemplateName(CoursesTimeEntity coursesTimeEntity);
	
	public CoursesTimeEntity getById(Integer id);
	
	public List<CoursesTimeEntity> getTemplate(Map<Object, Object> map);

}
