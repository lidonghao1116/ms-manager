package com.jiacer.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;

@MyBatisDao
public interface CoursePackageMapper extends CrudDao<CoursePackageEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer count(Map<Object, Object> map);
	
	Integer getPackageNameCount(Map<Object, Object> map);
	
	CoursePackageEntity getById(Integer id);

	/**
	 * @param map
	 * @return
	 */
	List<CoursePackageEntity> getPageList(Map<Object, Object> map);
	
	List<CoursePackageEntity> getPayCoursePackage(Map<Object, Object> map);
	
	/**
	 * @author xiehui
	 */
	CoursePackageEntity status(String integer);
	/**
	 * 
	 * @MethodName:updateStatusByCourseStatsMap
	 * @Type:CoursePackageMapper
	 * @Description:修改status
	 * @Return:void
	 * @Param:@param map
	 * @Thrown:
	 * @Date:Oct 16, 2017 4:44:03 PM
	 */
	void updateStatusByCourseStatsMap(Map<Object, Object> map);


}