package com.jiacer.modules.mybatis.dao;


import java.util.List;
import java.util.Map;

import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.model.CourseBaseInfo;

@MyBatisDao
public interface CoursesBaseInfoMapper extends CrudDao<CourseBaseInfo>  {

	/**
	 * 
	 * @MethodName:getstatusById
	 * @Type:CoursesBaseInfoMapper
	 * @Description:获取课程状态信息
	 * @Return:String
	 * @Param:@param courseBaseInfoId
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 9, 2017 7:03:58 AM
	 */
	String getstatusById(Integer courseBaseInfoId);
	
	/**
	 * 
	 * @MethodName:getcourseInfoByMap
	 * @Type:CoursesBaseInfoMapper
	 * @Description:获取各个机构未开的总控上架课程
	 * @Return:List<CourseBaseInfoEntity>
	 * @Param:@param map
	 * @Param:@return
	 * @Thrown:
	 * @Date:Oct 12, 2017 1:58:40 PM
	 */
	List<CourseBaseInfoEntity> getcourseInfoByMap(Map<Object, Object> map);

	CourseBaseInfoEntity getBaseCourseInfoById(Integer courseId);

}
