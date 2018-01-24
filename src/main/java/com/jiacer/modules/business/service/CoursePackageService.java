package com.jiacer.modules.business.service;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;

/** 
* @ClassName: CoursePackageService 
* @Description: 课程销售管理接口服务
* @author 贺章鹏
* @date 2016年10月19日 下午4:05:56 
*  
*/
public interface CoursePackageService {

	/**
	 * 根据id获取课程销售对象
	 * @param id
	 * @return
	 */
	CoursePackageEntity getCoursePackageById(Integer id);
	
	Integer count(Map<Object, Object> map);
	
	CoursePackageEntity getById(Integer id);
	
	List<CoursePackageEntity> getPayCoursePackage(Map<Object, Object> map);

	/**
	 * 课程销售分页
	 * @param coursePackageEntity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<CoursePackageEntity> getCoursePackagePage(CoursePackageEntity coursePackageEntity, int pageNumber,
			int pageSize);

	/**
	 * 新增课程销售
	 * @param coursePackageEntity
	 * @throws Exception
	 */
	void addCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception;
	
	Integer getPackageNameCount(Map<Object, Object> map);

	/**
	 * 修改课程销售
	 * @param coursePackageEntity
	 * @throws Exception
	 */
	void modifyCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception;

	/**
	 * 删除课程销售
	 * @param coursePackageEntity
	 * @throws Exception
	 */
	void delCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception;

	/**
	 * 获取课程销售ID 和 Name 为<select> 赋值
	 * @return
	 */
	List<ControlEntity> getCoursePackageIdName();
	
}
